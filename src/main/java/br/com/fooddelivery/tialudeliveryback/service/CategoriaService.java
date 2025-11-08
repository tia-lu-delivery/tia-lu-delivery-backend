package br.com.fooddelivery.tialudeliveryback.service;

import br.com.fooddelivery.tialudeliveryback.dto.request.CategoriaRequestDTO;
import br.com.fooddelivery.tialudeliveryback.dto.response.CategoriaResponseDTO;
import br.com.fooddelivery.tialudeliveryback.dto.response.CategoriaResponseDTO.CategoriaData;
import br.com.fooddelivery.tialudeliveryback.entity.Categoria;
import br.com.fooddelivery.tialudeliveryback.entity.Menu; 
import br.com.fooddelivery.tialudeliveryback.exception.CardapioNotFoundException;
import br.com.fooddelivery.tialudeliveryback.exception.CategoriaConflictException;
import br.com.fooddelivery.tialudeliveryback.repository.CategoriaRepository;
import br.com.fooddelivery.tialudeliveryback.repository.MenuRepository;

import org.springframework.stereotype.Service;

import java.util.UUID; 

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final MenuRepository menuRepository;

    public CategoriaService(CategoriaRepository categoriaRepository, MenuRepository menuRepository) {
        this.categoriaRepository = categoriaRepository;
        this.menuRepository = menuRepository;
    }

    public CategoriaResponseDTO createCategoria(String idCardapio, CategoriaRequestDTO requestDTO) {
        
        UUID cardapioId;
        try {
            cardapioId = UUID.fromString(idCardapio);
        } catch (IllegalArgumentException e) {
            throw new CardapioNotFoundException(idCardapio);
        }

        // 1. VERIFICAÇÃO 404
        Menu menu = menuRepository.findById(cardapioId)
                .orElseThrow(() -> new CardapioNotFoundException(idCardapio));

        // 2. VERIFICAÇÃO 409
        boolean nomeEmUso = categoriaRepository.existsByNomeCategoriaAndCardapioId(
            requestDTO.getNomeCategoria(), 
            cardapioId
        );

        if (nomeEmUso) {
            throw new CategoriaConflictException(requestDTO.getNomeCategoria(), "nomeCategoria");
        }

        // 3. PERSISTÊNCIA
        Categoria novaCategoria = new Categoria();
        novaCategoria.setNomeCategoria(requestDTO.getNomeCategoria());
        novaCategoria.setDescricao(requestDTO.getDescricao());
        novaCategoria.setDisponivel(requestDTO.getDisponivel());
        novaCategoria.setCardapio(menu);
        novaCategoria.setOrdem(menu.getCategorias().size() + 1); 

        Categoria categoriaSalva = categoriaRepository.save(novaCategoria);

        // 5. RETORNO DE SUCESSO
        return buildCategoriaResponseDTO(categoriaSalva);
    }

    private CategoriaResponseDTO buildCategoriaResponseDTO(Categoria categoria) {
        CategoriaData data = new CategoriaData();
        data.setNomeCategoria(categoria.getNomeCategoria());
        data.setOrdem(categoria.getOrdem());
        data.setDisponivel(categoria.getDisponivel());

        CategoriaResponseDTO response = new CategoriaResponseDTO();
        response.setIdCategoria(categoria.getId().toString());
        response.setMensagem(String.format("Categoria '%s' criada e vinculada ao cardápio com sucesso.", categoria.getNomeCategoria()));
        response.setDados(data); 

        return response;
    }

}