package br.com.fooddelivery.tialudeliveryback.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
public class UserProfileController {
    
    @GetMapping("/me")
    public ResponseEntity<?> getMyProfile(
            @RequestHeader(value = "Authorization", required = false) String token) {
        
        if (token == null || !token.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(createErrorResponse("NAO_AUTORIZADO", 
                          "Credenciais inválidas. Faça login para acessar o perfil."));
        }

        Map<String, Object> profile = new HashMap<>();
        profile.put("id_usuario", 7890);
        profile.put("nomeCompleto", "Alice Souza Oliveira");
        profile.put("dataNascimento", "1995-03-20");
        profile.put("email", "alice.souza.novo@exemplo.com");
        profile.put("status_conta", "ativo");
        profile.put("data_cadastro", "2023-10-25T10:00:00-03:00");
        
        Map<String, Object> endereco = new HashMap<>();
        endereco.put("id_endereco", 12345);
        endereco.put("cep", "01002-000");
        endereco.put("logradouro", "Rua Exemplo");
        endereco.put("numero", "123B");
        endereco.put("cidade", "São Paulo");
        endereco.put("estado", "SP");
        endereco.put("tipo", "Residencial");
        
        profile.put("endereco_padrao", endereco);
        
        return ResponseEntity.ok(profile);
    }
    
    private Map<String, Object> createErrorResponse(String codigo, String detalhe) {
        Map<String, Object> errorResponse = new HashMap<>();
        Map<String, String> error = new HashMap<>();
        
        error.put("codigo", codigo);
        error.put("detalhe", detalhe);
        errorResponse.put("erro", error);
        
        return errorResponse;
    }
}