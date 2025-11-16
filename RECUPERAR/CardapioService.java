package com.fooddelivery.tialudeliveryback.service;

import com.tialu.delivery.dto.CardapioRequest;
import com.tialu.delivery.dto.CardapioResponse;
import com.tialu.delivery.entity.Cardapio;
import com.tialu.delivery.entity.Estabelecimento;
import com.tialu.delivery.entity.StatusCardapio;
import com.tialu.delivery.exception.DuplicateResourceException;
import com.tialu.delivery.exception.ResourceNotFoundException;
import com.tialu.delivery.repository.CardapioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public class CardapioService {
    
    private final CardapioRepository cardapioRepository;
    private final EstabelecimentoService estabelecimentoService;
    
    public CardapioService(CardapioRepository cardapioRepository, 
                          EstabelecimentoService estabelecimentoService) {
        this.cardapioRepository = cardapioRepository;
        this.estabelecimentoService = estabelecimentoService;
    }
    
    public CardapioResponse criarCardapio(String idEstabelecimento, CardapioRequest request) {
   
        Estabelecimento estabelecimento = estabelecimentoService
            .buscarEstabelecimentoAtivoPorId(idEstabelecimento)
            .orElseThrow(() -> new ResourceNotFoundException(
                "Estabelecimento não encontrado com ID: " + idEstabelecimento));
        
        validarNomeCardapioUnico(request.getNomeCardapio(), idEstabelecimento);
        
        Cardapio cardapio = criarNovoCardapio(request, estabelecimento);
        Cardapio cardapioSalvo = cardapioRepository.save(cardapio);
        
        return criarResponseDeSucesso(cardapioSalvo);
    }
    
    private void validarNomeCardapioUnico(String nomeCardapio, String idEstabelecimento) {
        if (cardapioRepository.existsByNomeCardapioAndEstabelecimentoIdEstabelecimento(
            nomeCardapio, idEstabelecimento)) {
            throw new DuplicateResourceException(
                "O nome '" + nomeCardapio + "' já está em uso por outro cardápio deste estabelecimento");
        }
    }
    
    private Cardapio criarNovoCardapio(CardapioRequest request, Estabelecimento estabelecimento) {
        Cardapio cardapio = new Cardapio();
        cardapio.setNomeCardapio(request.getNomeCardapio());
        cardapio.setDescricao(request.getDescricao());
        cardapio.setEstabelecimento(estabelecimento);
        cardapio.setStatus(StatusCardapio.RASCUNHO);
        cardapio.setDataCriacao(LocalDateTime.now());
        return cardapio;
    }
    
    private CardapioResponse criarResponseDeSucesso(Cardapio cardapio) {
        CardapioResponse response = new CardapioResponse();
        response.setIdCardapio(cardapio.getIdCardapio());
        response.setMensagem("Cardápio criado com sucesso. Você já pode cadastrar os produtos.");
        
        CardapioResponse.DadosCardapio dados = new CardapioResponse.DadosCardapio();
        dados.setNomeCardapio(cardapio.getNomeCardapio());
        dados.setStatus(cardapio.getStatus().name().toLowerCase());
        dados.setDescricao(cardapio.getDescricao());
        dados.setDataCriacao(cardapio.getDataCriacao());
        
        response.setDadosCardapio(dados);
        return response;
    }
}