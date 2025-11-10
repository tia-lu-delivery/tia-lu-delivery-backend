package br.com.fooddelivery.tialudeliveryback.service;

import br.com.fooddelivery.tialudeliveryback.dto.ItemRequestDTO;
import br.com.fooddelivery.tialudeliveryback.dto.PedidoRequestDTO;
import br.com.fooddelivery.tialudeliveryback.dto.PedidoResponseDTO;
import br.com.fooddelivery.tialudeliveryback.exception.ValidacaoPedidoException;
import br.com.fooddelivery.tialudeliveryback.model.Endereco;
import br.com.fooddelivery.tialudeliveryback.model.Estabelecimento;
import br.com.fooddelivery.tialudeliveryback.model.ItemPedido;
import br.com.fooddelivery.tialudeliveryback.model.Pedido;
import br.com.fooddelivery.tialudeliveryback.model.Produto;
import br.com.fooddelivery.tialudeliveryback.model.Usuario;
import br.com.fooddelivery.tialudeliveryback.repository.EnderecoRepository;
import br.com.fooddelivery.tialudeliveryback.repository.EstabelecimentoRepository;
import br.com.fooddelivery.tialudeliveryback.repository.ItemPedidoRepository;
import br.com.fooddelivery.tialudeliveryback.repository.PedidoRepository;
import br.com.fooddelivery.tialudeliveryback.repository.ProdutoRepository;
import br.com.fooddelivery.tialudeliveryback.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class PedidoService {

    private final ProdutoRepository produtoRepository;
    private final EnderecoRepository enderecoRepository;
    private final UsuarioRepository usuarioRepository;
    private final EstabelecimentoRepository estabelecimentoRepository;
    private final PedidoRepository pedidoRepository;
    private final ItemPedidoRepository itemPedidoRepository;

    public PedidoService(ProdutoRepository produtoRepository,
                         EnderecoRepository enderecoRepository,
                         UsuarioRepository usuarioRepository,
                         EstabelecimentoRepository estabelecimentoRepository,
                         PedidoRepository pedidoRepository,
                         ItemPedidoRepository itemPedidoRepository) {
        this.produtoRepository = produtoRepository;
        this.enderecoRepository = enderecoRepository;
        this.usuarioRepository = usuarioRepository;
        this.estabelecimentoRepository = estabelecimentoRepository;
        this.pedidoRepository = pedidoRepository;
        this.itemPedidoRepository = itemPedidoRepository;
    }

    @Transactional
    public PedidoResponseDTO registrarPedido(PedidoRequestDTO request) {

        System.out.println("Iniciando registro de pedido...");

        // Usuário fixo de simulação (ID 1 vem do data.sql)
        Usuario usuarioLogado = usuarioRepository.findById(1L)
                .orElseThrow(() -> new ValidacaoPedidoException(
                        "Usuário de simulação (ID 1) não encontrado no data.sql."));

        // CA 1.3 — validação de endereço usando findByIdAndUsuarioId
        Endereco endereco = enderecoRepository
                .findByIdAndUsuarioId(request.getIdEnderecoEntrega(), usuarioLogado.getId())
                .orElseThrow(() -> new ValidacaoPedidoException(
                        "Endereço com ID " + request.getIdEnderecoEntrega()
                                + " não encontrado para o usuário autenticado."));

        System.out.println("CA 1.3: Endereço validado com sucesso.");

        // Busca o estabelecimento do pedido
        Estabelecimento estabelecimento = estabelecimentoRepository.findById(request.getIdEstabelecimento())
                .orElseThrow(() -> new ValidacaoPedidoException(
                        "Estabelecimento com ID " + request.getIdEstabelecimento() + " não encontrado."));

        // CA 1.4 — Cobertura de entrega
        System.out.println("CA 1.4: Validando cobertura de entrega...");

        String cepDoEndereco = endereco.getCep();
        String cepsPermitidos = estabelecimento.getCepsDeEntrega();

        if (cepsPermitidos == null || !cepsPermitidos.contains(cepDoEndereco)) {
            throw new ValidacaoPedidoException(
                    String.format(
                            "O estabelecimento '%s' não faz entregas na região (CEP: %s).",
                            estabelecimento.getNome(), cepDoEndereco
                    )
            );
        }

        System.out.println("CA 1.4: Cobertura de entrega validada.");

        // CA 1.5, 1.7 e 1.6 — validação dos itens e cálculo do subtotal
        System.out.println("CA 1.5, 1.7 e 1.6 (Cálculo): Validando itens, estoque, e calculando subtotal...");

        BigDecimal subtotalCalculado = BigDecimal.ZERO;
        Map<Long, Produto> produtosValidados = new HashMap<>();

        // 🔁 AQUI já usamos request.getItens()
        for (ItemRequestDTO itemDTO : request.getItens()) {

            Produto produto = produtoRepository.findById(itemDTO.getIdProduto())
                    .orElseThrow(() -> new ValidacaoPedidoException(
                            "Produto com ID " + itemDTO.getIdProduto() + " não encontrado."));

            if (produto.getEstabelecimento() == null
                    || produto.getEstabelecimento().getId() == null
                    || !produto.getEstabelecimento().getId().equals(estabelecimento.getId())) {
                throw new ValidacaoPedidoException(
                        "O produto '" + produto.getNome() + "' não pertence ao estabelecimento informado.");
            }

            if (produto.getEstoque() == null || produto.getEstoque() < itemDTO.getQuantidade()) {
                throw new ValidacaoPedidoException(
                        "Estoque insuficiente para o produto '" + produto.getNome()
                                + "'. Restam: " + produto.getEstoque());
            }

            BigDecimal precoRealDoProduto = produto.getPreco();
            BigDecimal quantidade = new BigDecimal(itemDTO.getQuantidade());
            BigDecimal totalItem = precoRealDoProduto.multiply(quantidade);

            subtotalCalculado = subtotalCalculado.add(totalItem);
            produtosValidados.put(produto.getId(), produto);
        }

        System.out.println("CA 1.6: Validando preço total (anti-fraude)...");

        // Taxa de entrega fixa
        BigDecimal taxaEntrega = new BigDecimal("5.00");

        // 💸 Novo modelo de desconto: request.getDesconto().getValorDesconto()
        BigDecimal desconto = BigDecimal.ZERO;
        if (request.getDesconto() != null && request.getDesconto().getValorDesconto() != null) {
            desconto = request.getDesconto().getValorDesconto();
        }

        BigDecimal valorTotalCalculado = subtotalCalculado.add(taxaEntrega).subtract(desconto);
        BigDecimal valorTotalEnviado = request.getValorTotalEnviado();

        if (valorTotalEnviado.compareTo(valorTotalCalculado) != 0) {
            throw new ValidacaoPedidoException(
                    String.format(
                            "Divergência no valor total (anti-fraude). " +
                                    "Enviado: R$%.2f | Calculado: R$%.2f (Subtotal: %.2f + Taxa: %.2f - Desconto: %.2f)",
                            valorTotalEnviado, valorTotalCalculado, subtotalCalculado, taxaEntrega, desconto
                    )
            );
        }

        System.out.println("Pedido validado! Salvando no banco...");

        Pedido novoPedido = new Pedido();
        novoPedido.setUsuario(usuarioLogado);
        novoPedido.setEstabelecimento(estabelecimento);
        novoPedido.setEnderecoEntrega(endereco);
        novoPedido.setSubtotal(subtotalCalculado);
        novoPedido.setTaxaEntrega(taxaEntrega);
        novoPedido.setDesconto(desconto);
        novoPedido.setValorTotal(valorTotalCalculado);
        novoPedido.setStatus("CRIADO");
        novoPedido.setDataCriacao(LocalDateTime.now());

        Pedido pedidoSalvo = pedidoRepository.save(novoPedido);

        // 🔁 Aqui também usamos request.getItens()
        for (ItemRequestDTO itemDTO : request.getItens()) {
            Produto produto = produtosValidados.get(itemDTO.getIdProduto());

            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setPedido(pedidoSalvo);
            itemPedido.setProduto(produto);
            itemPedido.setQuantidade(itemDTO.getQuantidade());
            itemPedido.setPrecoUnitario(produto.getPreco());

            itemPedidoRepository.save(itemPedido);
        }

        return new PedidoResponseDTO(pedidoSalvo.getId(), pedidoSalvo.getStatus());
    }
}
