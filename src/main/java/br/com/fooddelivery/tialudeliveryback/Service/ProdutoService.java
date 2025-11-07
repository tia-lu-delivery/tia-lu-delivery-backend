package br.com.fooddelivery.tialudeliveryback.Service;

import Entity.Estabelecimento;
import Entity.Produto;
import Repository.EstabelecimentoRepository;
import Repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final EstabelecimentoRepository estabelecimentoRepository;

    public ProdutoService(ProdutoRepository produtoRepository, EstabelecimentoRepository estabelecimentoRepository) {
        this.produtoRepository = produtoRepository;
        this.estabelecimentoRepository = estabelecimentoRepository;
    }


    public void disableProduct(Long idEstabelecimento, Long idProduto){
        Produto produto = produtoRepository.findById(idProduto).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        Estabelecimento estabelecimento = estabelecimentoRepository.findById(idEstabelecimento).orElseThrow(() -> new RuntimeException("Estabelecimento não encontrado"));


    }
}
