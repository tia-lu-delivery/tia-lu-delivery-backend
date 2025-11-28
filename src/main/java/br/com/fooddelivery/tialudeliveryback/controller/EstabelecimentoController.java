package br.com.fooddelivery.tialudeliveryback.controller;

import br.com.fooddelivery.tialudeliveryback.model.Estabelecimento;
import br.com.fooddelivery.tialudeliveryback.repository.EstabelecimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/estabelecimentos")
public class EstabelecimentoController {

    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;

    @GetMapping
    public ResponseEntity<List<Estabelecimento>> listarTodos() {
        List<Estabelecimento> estabelecimentos = estabelecimentoRepository.findAll();
        return ResponseEntity.ok(estabelecimentos);
    }
}