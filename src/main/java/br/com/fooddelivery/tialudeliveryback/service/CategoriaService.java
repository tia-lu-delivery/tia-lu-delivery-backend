package br.com.fooddelivery.tialudeliveryback.service;

import org.springframework.stereotype.Service;
import br.com.fooddelivery.tialudeliveryback.dto.request.CategoriaRequestDTO;
import br.com.fooddelivery.tialudeliveryback.dto.response.CategoriaResponseDTO;
import br.com.fooddelivery.tialudeliveryback.dto.response.CategoriaResponseDTO.CategoriaData;

@Service
public class CategoriaService {

    public CategoriaResponseDTO createCategoria(String idCardapio, CategoriaRequestDTO requestDTO) {
        
        // 1. Instancia o objeto de dados
        CategoriaData data = new CategoriaData();
        
        // 2. CORREÇÃO: Preenche os dados usando o que veio na Requisição (requestDTO)
        data.setNomeCategoria(requestDTO.getNomeCategoria());
        data.setOrdem(1); // Valor fixo para teste
        data.setDisponivel(requestDTO.getDisponivel());

        // 3. Cria a resposta final
        CategoriaResponseDTO response = new CategoriaResponseDTO();
        response.setIdCategoria("e8d7c6b5a4f3e2d1"); // ID Mock
        response.setMensagem("Categoria '" + requestDTO.getNomeCategoria() + "' criada e vinculada ao cardápio com sucesso.");
        
        // 4. Coloca os dados preenchidos dentro da resposta
        response.setDados(data); 

        return response;
    }
}