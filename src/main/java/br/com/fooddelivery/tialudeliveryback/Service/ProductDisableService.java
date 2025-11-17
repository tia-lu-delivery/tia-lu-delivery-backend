package br.com.fooddelivery.tialudeliveryback.Service;

import br.com.fooddelivery.tialudeliveryback.dto.ProdutoInativadoRes;
import br.com.fooddelivery.tialudeliveryback.Mapper.ProdutoMapper;
import br.com.fooddelivery.tialudeliveryback.repository.model.Produto;
import br.com.fooddelivery.tialudeliveryback.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductDisableService {

    private final ProdutoRepository produtoRepository;

    public ProdutoInativadoRes disableProduct(Long idProduto){
        Produto produto = produtoRepository.findById(idProduto)
            .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        if (!produto.isDisponivel()) {
            throw new RuntimeException("Produto já está inativado");
        }

        produto.setDisponivel(false);

        Produto salvo = produtoRepository.save(produto);
        return ProdutoMapper.toProdutoInativadoRes(salvo);
    }
}