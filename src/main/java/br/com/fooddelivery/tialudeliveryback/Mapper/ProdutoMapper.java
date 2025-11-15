package br.com.fooddelivery.tialudeliveryback.Mapper;

import br.com.fooddelivery.tialudeliveryback.DTO.ProdutoDTO;
import br.com.fooddelivery.tialudeliveryback.Entity.Produto;

public class ProdutoMapper {

    public static ProdutoDTO toDTO(Produto produto) {
        if (produto == null) return null;

        ProdutoDTO dto = new ProdutoDTO();
        dto.setId(produto.getId());
        dto.setNome(produto.getNome());
        dto.setDescricao(produto.getDescricao());
        dto.setPreco(produto.getPreco());
        dto.setAtivo(produto.isAtivo());

        return dto;
    }

    public static Produto toEntity(ProdutoDTO dto) {
        if (dto == null) return null;

        Produto produto = new Produto();
        produto.setId(dto.getId());
        produto.setNome(dto.getNome());
        produto.setDescricao(dto.getDescricao());
        produto.setPreco(dto.getPreco());
        produto.setAtivo(dto.isAtivo());

        return produto;
    }
}
