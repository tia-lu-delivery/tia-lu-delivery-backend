package com.delivery.endereco.controller;

import com.delivery.endereco.entity.Endereco;
import com.delivery.endereco.service.EnderecoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/enderecos")
@CrossOrigin(origins = "*")
public class EnderecoController {

    private final EnderecoService service;

    public EnderecoController(EnderecoService service) {
        this.service = service;
    }

    //aqui cria um novo end
    @PostMapping
    public Endereco criar(@RequestBody Endereco endereco) {
        return service.salvar(endereco);
    }

    // lista tds os endereços
    @GetMapping
    public List<Endereco> listar() {
        return service.listarTodos();
    }

    // busca o endereço por id
    @GetMapping("/{id}")
    public Optional<Endereco> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    // busca o endereço por cep
    @GetMapping("/cep/{cep}")
    public Endereco buscarPorCep(@PathVariable String cep) {
        return service.buscarPorCep(cep);
    }

    // deleta o endereço por id
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
