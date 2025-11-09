package br.com.fooddelivery.tialudeliveryback.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.fooddelivery.tialudeliveryback.dto.MenuDTO;
import br.com.fooddelivery.tialudeliveryback.repository.CardapioRepository;
import br.com.fooddelivery.tialudeliveryback.repository.CategoriaRepository;
import br.com.fooddelivery.tialudeliveryback.repository.ProdutoRepository;
import br.com.fooddelivery.tialudeliveryback.entity.CardapioEntity;
import br.com.fooddelivery.tialudeliveryback.entity.CategoriaEntity;
import br.com.fooddelivery.tialudeliveryback.entity.ProdutoEntity;
import br.com.fooddelivery.tialudeliveryback.exception.ResourceNotFoundException;

@Service
public class MenuService {

    private final CategoriaRepository categoriaRepository;
    private final ProdutoRepository produtoRepository;
    private final CardapioRepository cardapioRepository;

    // Construtor manual (substitui o @RequiredArgsConstructor)
    public MenuService(CategoriaRepository categoriaRepository, ProdutoRepository produtoRepository,
                       CardapioRepository cardapioRepository) {
        this.categoriaRepository = categoriaRepository;
        this.produtoRepository = produtoRepository;
        this.cardapioRepository = cardapioRepository;
    }

    public MenuDTO getMenuById(String idCardapio) {
        CardapioEntity cardapio = cardapioRepository.findById(idCardapio)
                .orElseThrow(() -> new ResourceNotFoundException("O cardápio com ID " + idCardapio + " não foi encontrado"));

        List<CategoriaEntity> categorias = categoriaRepository.findCategoriasComProdutosDisponiveis(idCardapio);

        List<MenuDTO.CategoriaDTO> categoriasDto = categorias.stream()
                .map(categoria -> {
                    List<ProdutoEntity> produtos = produtoRepository.findDisponiveisByCardapio(categoria.getId());
                    List<MenuDTO.ProdutoDTO> produtosDto = produtos.stream()
                            .map(produto -> {
                                MenuDTO.ProdutoDTO produtoDTO = new MenuDTO.ProdutoDTO();
                                produtoDTO.setIdProduto(produto.getId());
                                produtoDTO.setNomeProduto(produto.getNomeProduto());
                                produtoDTO.setDescricao(produto.getDescricao());
                                produtoDTO.setPrecoUnitario(produto.getPrecoUnitario());
                                produtoDTO.setImagemUrl(produto.getImagemUrl());
                                produtoDTO.setDisponivel(produto.getDisponivel());
                                produtoDTO.setEstoque(produto.getEstoque());
                                return produtoDTO;
                            })
                            .collect(Collectors.toList());

                    MenuDTO.CategoriaDTO categoriaDTO = new MenuDTO.CategoriaDTO();
                    categoriaDTO.setIdCategoria(categoria.getId());
                    categoriaDTO.setNomeCategoria(categoria.getNomeCategoria());
                    categoriaDTO.setOrdem(categoria.getOrdem());
                    categoriaDTO.setDisponivel(categoria.getDisponivel());
                    categoriaDTO.setProdutos(produtosDto);
                    return categoriaDTO;
                })
                .collect(Collectors.toList());

        MenuDTO.EstabelecimentoDTO estabelecimento = new MenuDTO.EstabelecimentoDTO();
        estabelecimento.setIdEstabelecimento(cardapio.getEstabelecimento().getId());
        estabelecimento.setNomeFantasia(cardapio.getEstabelecimento().getNomeFantasia());

        MenuDTO response = new MenuDTO();
        response.setIdCardapio(cardapio.getId());
        response.setNomeCardapio(cardapio.getNomeCardapio());
        response.setDataAtualizacao(cardapio.getDataAtualizacao());
        response.setEstabelecimento(estabelecimento);
        response.setCategorias(categoriasDto);

        if (categoriasDto.isEmpty()) {
            response.setMensagem("O cardápio está ativo, mas não possui categorias ou produtos disponíveis no momento.");
        }

        return response;
    }
}