package br.com.fooddelivery.tialudeliveryback.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
public class UserUpdateController {
    
    @PatchMapping("/me")
    public ResponseEntity<?> updateUserProfile(
            @RequestHeader(value = "Authorization", required = false) String token,
            @RequestBody Map<String, Object> updateData) {
        
        if (token == null || !token.startsWith("Bearer ")) {
            return createErrorResponse(
                HttpStatus.UNAUTHORIZED,
                "NAO_AUTORIZADO",
                "Credenciais inválidas. Faça login para atualizar o perfil."
            );
        }
        
        if (updateData == null || updateData.isEmpty()) {
            return createErrorResponse(
                HttpStatus.BAD_REQUEST,
                "DADOS_INVALIDOS",
                "Nenhum dado fornecido para atualização."
            );
        }
        
        String[] camposProibidos = {"id", "senha", "status_conta", "data_cadastro", "email"};
        for (String campo : camposProibidos) {
            if (updateData.containsKey(campo)) {
                return createErrorResponse(
                    HttpStatus.FORBIDDEN,
                    "CAMPOS_RESTRITOS",
                    "Campo '" + campo + "' não pode ser atualizado por este endpoint."
                );
            }
        }
        
        String[] camposPermitidos = {"nomeCompleto", "dataNascimento", "telefone", 
                                    "endereco_padrao", "foto_perfil"};
        boolean hasValidField = false;
        for (String campo : camposPermitidos) {
            if (updateData.containsKey(campo)) {
                hasValidField = true;
                break;
            }
        }
        
        if (!hasValidField) {
            return createErrorResponse(
                HttpStatus.BAD_REQUEST,
                "CAMPOS_INVALIDOS",
                "Apenas os seguintes campos podem ser atualizados: nomeCompleto, dataNascimento, telefone, endereco_padrao, foto_perfil"
            );
        }
        
        Map<String, Object> response = new HashMap<>();
        response.put("mensagem", "Perfil atualizado com sucesso");
        response.put("dados_atualizados", updateData);
        response.put("timestamp", java.time.LocalDateTime.now().toString());
        response.put("status", "sucesso");
        
        return ResponseEntity.ok(response);
    }
    
    @SuppressWarnings("null")
    private ResponseEntity<?> createErrorResponse(HttpStatus status, 
                                                 String codigo, 
                                                 String detalhe) {
        Map<String, Object> errorResponse = new HashMap<>();
        Map<String, String> error = new HashMap<>();
        
        error.put("codigo", codigo);
        error.put("detalhe", detalhe);
        errorResponse.put("erro", error);
        
        return ResponseEntity.status(status).body(errorResponse);
    }
}