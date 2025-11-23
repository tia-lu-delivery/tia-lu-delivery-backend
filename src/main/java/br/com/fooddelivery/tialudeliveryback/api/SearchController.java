package br.com.fooddelivery.tialudeliveryback.api;

import br.com.fooddelivery.tialudeliveryback.api.dto.SearchResponseDTO;
import br.com.fooddelivery.tialudeliveryback.api.dto.ErrorResponseDTO;
import br.com.fooddelivery.tialudeliveryback.service.SearchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/v1/merchant")
public class SearchController {

        private final SearchService searchService;

        public SearchController(SearchService searchService) {
                this.searchService = searchService;
        }

    @GetMapping("/search")
    public ResponseEntity<?> searchPlates(
            @RequestHeader(value = "Authorization", required = false) String token,
            @RequestParam(name = "plate", required = false) String plateTerm
    ) {

        // 401 Unauthorized
        if (token == null || token.isBlank()) {
            ErrorResponseDTO erro = new ErrorResponseDTO(
                    "NAO_AUTORIZADO",
                    "Token de autenticação ausente ou inválido. Faça login para realizar pesquisas."
            );
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(erro);
        }

        // 400 Bad Request
        if (plateTerm == null || plateTerm.isBlank()) {
            ErrorResponseDTO erro = new ErrorResponseDTO(
                    "PARAMETRO_OBRIGATORIO_FALTANDO",
                    "O parâmetro 'plate' (termo de pesquisa) é obrigatório."
            );
            return ResponseEntity.badRequest().body(erro);
        }

        // Delega a busca ao service (pode retornar resultados reais ou mockados pelo repository)
        SearchResponseDTO response = searchService.searchRestaurantsByPlate(plateTerm);
        // Proteção extra: caso o service retorne null (não deveria), retornamos lista vazia
        if (Objects.isNull(response)) {
            response = new SearchResponseDTO(plateTerm, java.util.Collections.emptyList());
        }

        return ResponseEntity.ok(response);
    }
}
