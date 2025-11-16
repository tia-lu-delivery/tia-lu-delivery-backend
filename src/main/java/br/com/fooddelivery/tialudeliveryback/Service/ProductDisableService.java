package br.com.fooddelivery.tialudeliveryback.Service;

import br.com.fooddelivery.tialudeliveryback.Entity.Estabelecimento;
import br.com.fooddelivery.tialudeliveryback.dto.ProdutoInativadoRes;
import br.com.fooddelivery.tialudeliveryback.Mapper.ProdutoMapper;
import br.com.fooddelivery.tialudeliveryback.repository.model.Produto;
import br.com.fooddelivery.tialudeliveryback.Repository.EstabelecimentoRepository;
import br.com.fooddelivery.tialudeliveryback.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductDisableService {

    private final ProdutoRepository produtoRepository;
    private final EstabelecimentoRepository estabelecimentoRepository;

    public ProdutoInativadoRes disableProduct(Long idEstabelecimento, Long idProduto){
        Estabelecimento estabelecimento = estabelecimentoRepository.
                findById(idEstabelecimento).orElseThrow(() -> new RuntimeException("Estabelecimento não encontrado"));
        Produto produto = produtoRepository.
                findById(idProduto).orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        Produto salvo = produtoRepository.save(produto);
        return ProdutoMapper.toProdutoInativadoRes(salvo);
    }
}