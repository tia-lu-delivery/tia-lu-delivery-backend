package br.com.fooddelivery.tialudeliveryback.Service;

import br.com.fooddelivery.tialudeliveryback.Entity.Estabelecimento;
import br.com.fooddelivery.tialudeliveryback.Entity.Produto;
import br.com.fooddelivery.tialudeliveryback.Repository.EstabelecimentoRepository;
import br.com.fooddelivery.tialudeliveryback.Repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductDisableService {

    private final ProdutoRepository produtoRepository;
    private final EstabelecimentoRepository estabelecimentoRepository;

    public ProductDisableService(ProdutoRepository produtoRepository, EstabelecimentoRepository estabelecimentoRepository) {
        this.produtoRepository = produtoRepository;
        this.estabelecimentoRepository = estabelecimentoRepository;
    }


    public Produto disableProduct(Long idEstabelecimento, Long idProduto){
        Estabelecimento estabelecimento = estabelecimentoRepository.
                findById(idEstabelecimento).orElseThrow(() -> new RuntimeException("Estabelecimento não encontrado"));
        Produto produto = produtoRepository.
                findById(idProduto).orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        produto.setDisponivel(false);
        return produtoRepository.save(produto);
    }
}
