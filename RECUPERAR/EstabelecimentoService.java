package com.fooddelivery.tialudeliveryback.service;

import com.tialu.delivery.entity.Estabelecimento;
import com.tialu.delivery.repository.EstabelecimentoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EstabelecimentoService {
    
    private final EstabelecimentoRepository estabelecimentoRepository;
    
    public EstabelecimentoService(EstabelecimentoRepository estabelecimentoRepository) {
        this.estabelecimentoRepository = estabelecimentoRepository;
    }
    
    public Optional<Estabelecimento> buscarEstabelecimentoAtivoPorId(String idEstabelecimento) {
        return estabelecimentoRepository.findByIdEstabelecimentoAndAtivoTrue(idEstabelecimento);
    }
    
    public boolean existeEstabelecimento(String idEstabelecimento) {
        return estabelecimentoRepository.findByIdEstabelecimentoAndAtivoTrue(idEstabelecimento).isPresent();
    }
}