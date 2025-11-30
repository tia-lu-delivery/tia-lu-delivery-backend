package br.com.fooddelivery.tialudeliveryback.service;

import br.com.fooddelivery.tialudeliveryback.dto.ClienteDTO;
import br.com.fooddelivery.tialudeliveryback.dto.EnderecoEntregaDTO;
import br.com.fooddelivery.tialudeliveryback.dto.ItemPedidoDTO;
import br.com.fooddelivery.tialudeliveryback.dto.PedidoResponseDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {

    public PedidoResponseDTO buscarPorNumero(String numeroPedido) {

        // MOCK temporário só para teste: retorna um pedido quando numeroPedido == "123"
        if (!"123".equals(numeroPedido)) {
            return null;
        }

        PedidoResponseDTO dto = new PedidoResponseDTO();
        dto.setNumero_pedido("123");
        dto.setHorario_abertura("2025-02-10T10:00:00");
        dto.setStatus_pedido("Em Preparação");

        // Cliente (usa os setters conforme seu ClienteDTO: nome, numero_cliente)
        ClienteDTO cliente = new ClienteDTO();
        cliente.setNome("João da Silva");
        cliente.setNumero_cliente("11999999999");
        dto.setCliente(cliente);

        // Endereço de entrega (usa os setters conforme seu EnderecoEntregaDTO: rua, numero, complemento, bairro, cidade, cep)
        EnderecoEntregaDTO endereco = new EnderecoEntregaDTO();
        endereco.setRua("Rua Exemplo");
        endereco.setNumero("100");
        endereco.setComplemento("Apto 10");
        endereco.setBairro("Centro");
        endereco.setCidade("São Paulo");
        endereco.setCep("01000-000");
        dto.setEndereco_entrega(endereco);

        // Itens do pedido (usa os setters conforme seu ItemPedidoDTO: quantidade, item, preco_unitario)
        List<ItemPedidoDTO> itens = new ArrayList<>();

        ItemPedidoDTO item1 = new ItemPedidoDTO();
        item1.setQuantidade(2);
        item1.setItem("Hambúrguer Clássico");
        item1.setPreco_unitario(25.90);

        ItemPedidoDTO item2 = new ItemPedidoDTO();
        item2.setQuantidade(1);
        item2.setItem("Batata Frita Grande");
        item2.setPreco_unitario(12.50);

        ItemPedidoDTO item3 = new ItemPedidoDTO();
        item3.setQuantidade(1);
        item3.setItem("Refrigerante Cola Lata");
        item3.setPreco_unitario(6.00);

        itens.add(item1);
        itens.add(item2);
        itens.add(item3);

        dto.setItens_pedido(itens);

        // Valores
        double totalItens = item1.getQuantidade() * item1.getPreco_unitario()
                + item2.getQuantidade() * item2.getPreco_unitario()
                + item3.getQuantidade() * item3.getPreco_unitario();

        dto.setValor_total_itens(totalItens);
        dto.setTaxa_entrega(5.00);
        dto.setValor_final(totalItens + dto.getTaxa_entrega());

        return dto;
    }
}