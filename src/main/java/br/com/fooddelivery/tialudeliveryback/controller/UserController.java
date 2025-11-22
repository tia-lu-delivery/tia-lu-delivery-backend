package br.com.fooddelivery.tialudeliveryback.controller;

import br.com.fooddelivery.tialudeliveryback.dto.request.UserRegisterRequest;
import br.com.fooddelivery.tialudeliveryback.dto.response.ErrorResponse;
import br.com.fooddelivery.tialudeliveryback.dto.response.UserRegisterResponse;
import br.com.fooddelivery.tialudeliveryback.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping
  public ResponseEntity<?> register(@RequestBody UserRegisterRequest request) {

    List<ErrorResponse.ValidationErrorDetail> validationErrors = new ArrayList<>();

    if (request.nomeCompleto() == null || request.nomeCompleto().trim().isEmpty())
      validationErrors.add(new ErrorResponse.ValidationErrorDetail("nomeCompleto", "O nome completo é obrigatório"));

    if (request.dataNascimento() == null)
      validationErrors
          .add(new ErrorResponse.ValidationErrorDetail("dataNascimento", "A data de nascimento é obrigatória"));

    if (request.email() == null || request.email().trim().isEmpty())
      validationErrors.add(new ErrorResponse.ValidationErrorDetail("email", "O e-mail é obrigatório"));

    if (request.senha() == null || request.senha().trim().isEmpty())
      validationErrors.add(new ErrorResponse.ValidationErrorDetail("senha", "A senha é obrigatória"));

    if (request.endereco() == null) {
      validationErrors.add(new ErrorResponse.ValidationErrorDetail("endereco", "O endereço é obrigatório"));
    } else {
      if (isBlank(request.endereco().cep()))
        validationErrors.add(new ErrorResponse.ValidationErrorDetail("endereco.cep", "CEP é obrigatório"));
      if (isBlank(request.endereco().logradouro()))
        validationErrors
            .add(new ErrorResponse.ValidationErrorDetail("endereco.logradouro", "Logradouro é obrigatório"));
      if (isBlank(request.endereco().numero()))
        validationErrors.add(new ErrorResponse.ValidationErrorDetail("endereco.numero", "Número é obrigatório"));
      if (isBlank(request.endereco().bairro()))
        validationErrors.add(new ErrorResponse.ValidationErrorDetail("endereco.bairro", "Bairro é obrigatório"));
      if (isBlank(request.endereco().cidade()))
        validationErrors.add(new ErrorResponse.ValidationErrorDetail("endereco.cidade", "Cidade é obrigatória"));
      if (isBlank(request.endereco().estado()))
        validationErrors.add(new ErrorResponse.ValidationErrorDetail("endereco.estado", "Estado é obrigatório"));
    }

    if (!validationErrors.isEmpty()) {
      ErrorResponse error = new ErrorResponse(
          "VALIDATION_ERROR",
          "Um ou mais campos contêm erros de validação.",
          null,
          validationErrors);
      return ResponseEntity.badRequest().body(error);
    }

    try {
      UserRegisterResponse response = userService.register(request);

      return ResponseEntity
          .status(HttpStatus.CREATED)
          .body(response);

    } catch (IllegalArgumentException ex) {
      ErrorResponse.ValidationErrorDetail detail = new ErrorResponse.ValidationErrorDetail(
          extractField(ex.getMessage()), ex.getMessage());

      ErrorResponse error = new ErrorResponse(
          "VALIDATION_ERROR",
          "Um ou mais campos contêm erros de validação.",
          null,
          List.of(detail));
      return ResponseEntity.badRequest().body(error);

    } catch (RuntimeException ex) {
      if (ex.getMessage() != null && ex.getMessage().contains("já está cadastrado")) {
        ErrorResponse error = new ErrorResponse(
            "EMAIL_ALREADY_REGISTERED",
            ex.getMessage(),
            "Solicitar login ou recuperação de senha.");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error); // 409
      }

      return ResponseEntity
          .status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(new ErrorResponse("INTERNAL_ERROR", "Erro interno no servidor.", null));
    }
  }

  private boolean isBlank(String str) {
    return str == null || str.trim().isEmpty();
  }

  private String extractField(String message) {
    if (message == null)
      return "desconhecido";
    if (message.toLowerCase().contains("senha"))
      return "senha";
    if (message.toLowerCase().contains("idade") || message.toLowerCase().contains("nascimento"))
      return "dataNascimento";
    if (message.toLowerCase().contains("e-mail") || message.toLowerCase().contains("email"))
      return "email";
    return "desconhecido";
  }
}