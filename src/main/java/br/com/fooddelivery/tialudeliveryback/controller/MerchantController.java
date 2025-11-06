package br.com.fooddelivery.tialudeliveryback.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

// Define a classe como um Controller REST.
@RestController
// Mapeia todas as requisições que começam com "/api/v1/merchant" para este controller.
@RequestMapping("/api/v1/merchant")

public class MerchantController {

    // Injeta a dependência do MerchantService.
    @Autowired
    private MerchantService merchantService; // Declarando o que injetar

    //Criando o método que responde ao POST
    @PostMapping
    // Pegando Json do corpo da requisição e aciona a validação
    public ResponseEntity<?> cadastrar(@RequestBody @Valid MerchantResquestDTO dto) {
        try {
            // Tenta chamar o service
            String idEstabelecimento = merchantService.cadastrar(dto);
            // Prepara o DTO de resposta de sucesso com o ID gerado
            MerchantSucessResponseDTO reponse = new MerchantSucessResponseDTO(idEstabelecimento, "Estabelecimento cadastrado...");

            // Retorna status http de sucesso na criação "201" caso o try funcione
            return ResponseEntity.status(HttpStatus.CREATED).body(reponse);

        }
        catch (CnpjDuplicadoException e ) {

            // Prepara o DTO de resposta de erro (Conflito)
            ConflictErrorResponseDTO erro = new ConflictErrorResponseDTO("DUPLICATE_ENTITY", e.getMessage(), "Solicitar login...");

            // Retorna status http de conflito "409" caso o cnpj ja exista e o try nao funcione
            return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
        }
    }

}
