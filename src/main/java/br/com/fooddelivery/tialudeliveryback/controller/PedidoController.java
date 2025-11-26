package br.com.fooddelivery.tialudeliveryback.controller;

import br.com.fooddelivery.tialudeliveryback.dto.DetalheErroDTO;
import br.com.fooddelivery.tialudeliveryback.dto.ErroResponseDTO;
import br.com.fooddelivery.tialudeliveryback.dto.ListaPedidosResponseDTO;
import br.com.fooddelivery.tialudeliveryback.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user/orders")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    // CA-001/CA-002/CA-006: Endpoint para listar e paginar pedidos
    // O retorno agora é ResponseEntity<Object> para permitir 200 (DTO de Lista) ou 401 (DTO de Erro)
    @GetMapping
    public ResponseEntity<Object> listarPedidosUsuario(
            // ATUALIZADO: O cabeçalho agora é required = false para evitar o 400 Bad Request
            @RequestHeader(name = "Authorization", required = false) String token,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        // CA-002: Validação Manual do Token para garantir o 401 Unauthorized e o JSON de Erro
        if (token == null || !token.startsWith("Bearer ")) {

            // Cria o objeto de erro conforme a especificação
            DetalheErroDTO detalhe = new DetalheErroDTO(
                    "NAO_AUTORIZADO",
                    "Token de autenticação ausente ou inválido. Não foi possível carregar o histórico."
            );

            ErroResponseDTO erroResponse = new ErroResponseDTO(detalhe);

            // Retorna HTTP 401 Unauthorized com o corpo JSON formatado
            return ResponseEntity.status(401).body(erroResponse);
        }

        // --- Lógica de Sucesso (Se o token estiver presente/válido ---

        // Mock do ID do usuário (baseado no token mockado 'Bearer ABC...')
        // Neste ponto, o token foi validado como existente.
        Long userId = 123L;

        ListaPedidosResponseDTO response = pedidoService.listarPedidosUsuario(userId, page, size);

        // Retorna HTTP 200 OK
        return ResponseEntity.ok(response);
    }
}