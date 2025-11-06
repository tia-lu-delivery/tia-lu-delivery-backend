package br.com.fooddelivery.tialudeliveryback.service;

import org.springframework.stereotype.Service;
import br.com.fooddelivery.tialudeliveryback.dto.request.CategoriaRequestDTO;
import br.com.fooddelivery.tialudeliveryback.dto.response.CategoriaResponseDTO;

@Service // Marca a classe como um Service do Spring
public class CategoriaService {

    public CategoriaResponseDTO createCategoria(String idCardapio, CategoriaRequestDTO requestDTO) {
        // Implementação real da lógica (verificação de 404, 409, persistência, etc.) virá aqui.
        // Por enquanto, apenas retorna um mock para compilar e passar no Controller.
        
        // Exemplo de retorno mock:
        CategoriaResponseDTO.CategoriaData data = new CategoriaResponseDTO.CategoriaData();
        // ... setar dados ...

        CategoriaResponseDTO response = new CategoriaResponseDTO();
        response.setIdCategoria("e8d7c6b5a4f3e2d1");
        response.setMensagem("Categoria '"+ requestDTO.getNomeCategoria() +"' criada e vinculada ao cardápio com sucesso.");
        response.setDados(data); 

        return response;
        
        // **IMPORTANTE:** O desenvolvedor responsável pelo Service precisa implementar AQUI
        // a lógica de lançar CardapioNotFoundException e CategoriaConflictException.
    }
}