package br.com.fooddelivery.tialudeliveryback.Mapper;

import br.com.fooddelivery.tialudeliveryback.dto.DeleteCategoryResDTO;

public class DeleteCategoryResMapper {

    public static DeleteCategoryResDTO toSucesso(String idCategoria, String idCardapio) {
        return DeleteCategoryResDTO.sucesso(idCardapio, idCategoria);
    }

    public static DeleteCategoryResDTO toCategoriaNaoEncontrada(String idCategoria, String idCardapio) {
        String detalhe = String.format(
                "A categoria com ID '%s' não foi encontrada no cardápio '%s'.",
                idCategoria,
                idCardapio
        );
        return DeleteCategoryResDTO.erro("CATEGORIA_NAO_ENCONTRADA", detalhe);
    }

    public static DeleteCategoryResDTO toCardapioNaoEncontrado(String idCategoria, String idCardapio) {
        String detalhe = String.format(
                "O cardápio com ID '%s' não foi encontrado para excluir a categoria '%s'.",
                idCardapio,
                idCategoria
        );
        return DeleteCategoryResDTO.erro("CARDAPIO_NAO_ENCONTRADO", detalhe);
    }

    public static DeleteCategoryResDTO toNaoAutorizado() {
        return DeleteCategoryResDTO.erro(
                "NAO_AUTORIZADO",
                "Acesso negado. Token de estabelecimento inválido."
        );
    }
}

