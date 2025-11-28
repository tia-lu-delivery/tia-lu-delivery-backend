package br.com.fooddelivery.tialudeliveryback.controller;

import br.com.fooddelivery.tialudeliveryback.model.Endereco;
import br.com.fooddelivery.tialudeliveryback.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @GetMapping
    public ResponseEntity<List<Endereco>> listarTodos() {
        List<Endereco> enderecos = enderecoRepository.findAll();
        return ResponseEntity.ok(enderecos);
    }
}