package br.com.fooddelivery.tialudeliveryback.Mapper;

import br.com.fooddelivery.tialudeliveryback.dto.ProdutoInativadoRes;
import br.com.fooddelivery.tialudeliveryback.repository.model.Produto;

public class ProdutoMapper {

    public static ProdutoInativadoRes toProdutoInativadoRes(Produto produto) {
        return new ProdutoInativadoRes(
                produto.getId().toString(),
                "PRODUTO_INATIVADO",
                "O produto foi inativado com sucesso.",
                produto.isDisponivel()
        );
    }
}
