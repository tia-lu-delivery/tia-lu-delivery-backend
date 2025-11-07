package com.tialu.delivery.services;

import com.tialu.delivery.dtos.ProductRequestDTO;
import com.tialu.delivery.dtos.ProductResponseDTO;
import com.tialu.delivery.models.Product;
import com.tialu.delivery.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductResponseDTO createProduct(Long idCardapio, Long idCategoria, ProductRequestDTO request) {


        Optional<Product> produtoExistente = productRepository.findByNomeAndCategoriaId(request.getNome(), idCategoria);
        if (produtoExistente.isPresent()) {
            throw new RuntimeException("DUPLICATE_NAME");
        }


        Product novoProduto = new Product();
        novoProduto.setNome(request.getNome());
        novoProduto.setPrecoUnitario(request.getPrecoUnitario());
        novoProduto.setDescricao(request.getDescricao());
        novoProduto.setQuantidadeEstoque(request.getQuantidadeEstoque());
        novoProduto.setImagemUrl(request.getImagemUrl());
        novoProduto.setDisponivel(request.isDisponivel());
        novoProduto.setCategoriaId(idCategoria);


        Product produtoSalvo = productRepository.save(novoProduto);


        ProductResponseDTO response = new ProductResponseDTO();
        response.setIdProduto(produtoSalvo.getId().toString());
        response.setMensagem("Produto '" + produtoSalvo.getNome() + "' criado e adicionado à categoria.");

        ProductResponseDTO.DadosProdutoResponse dadosProduto = new ProductResponseDTO.DadosProdutoResponse();
        dadosProduto.setNome(produtoSalvo.getNome());
        dadosProduto.setPrecoUnitario(produtoSalvo.getPrecoUnitario());
        dadosProduto.setCategoriaId(produtoSalvo.getCategoriaId());
        response.setDadosProduto(dadosProduto);

        return response;
    }
}