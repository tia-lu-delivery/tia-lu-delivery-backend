package br.com.fooddelivery.tialudeliveryback.controller;

import br.com.fooddelivery.tialudeliveryback.service.UserService;
import br.com.fooddelivery.tialudeliveryback.service.dto.UserRequestDTO;
import br.com.fooddelivery.tialudeliveryback.service.dto.UserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.Period;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

  // Minimal local error response types to structure validation errors
  static class ValidationErrorDetail {
    private final String field;
    private final String message;

    ValidationErrorDetail(String field, String message) {
      this.field = field;
      this.message = message;
    }

    public String getField() {
      return field;
    }

    public String getMessage() {
      return message;
    }
  }

  static class ErrorResponse {
    private final String code;
    private final String message;
    private final String suggestion;
    private final List<ValidationErrorDetail> details;

    ErrorResponse(String code, String message, String suggestion) {
      this(code, message, suggestion, null);
    }

    ErrorResponse(String code, String message, String suggestion, List<ValidationErrorDetail> details) {
      this.code = code;
      this.message = message;
      this.suggestion = suggestion;
      this.details = details;
    }

    public String getCode() {
      return code;
    }

    public String getMessage() {
      return message;
    }

    public String getSuggestion() {
      return suggestion;
    }

    public List<ValidationErrorDetail> getDetails() {
      return details;
    }
  }

  @Autowired
  private UserService userService;

  @PostMapping
  public ResponseEntity<?> register(@RequestBody UserRequestDTO request) {

    List<ValidationErrorDetail> validationErrors = new ArrayList<>();

    if (request.getNomeCompleto() == null || request.getNomeCompleto().trim().isEmpty())
      validationErrors.add(new ValidationErrorDetail("nomeCompleto", "O nome completo é obrigatório"));

    if (request.getDataNascimento() == null) {
      validationErrors.add(new ValidationErrorDetail("dataNascimento", "A data de nascimento é obrigatória"));
    } else {
      int age = Period.between(request.getDataNascimento(), LocalDate.now()).getYears();
      if (age < 18) {
        validationErrors.add(new ValidationErrorDetail("dataNascimento", "A idade mínima para registro é de 18 anos"));
      }
    }

    if (request.getEmail() == null || request.getEmail().trim().isEmpty()) {
      validationErrors.add(new ValidationErrorDetail("email", "O e-mail é obrigatório"));
    } else {
      String email = request.getEmail().trim();
      // Regex simples para e-mail válido
      String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
      if (!email.matches(emailRegex)) {
        validationErrors.add(new ValidationErrorDetail("email", "O e-mail informado é inválido"));
      }
    }

    if (request.getSenha() == null || request.getSenha().trim().isEmpty())
      validationErrors.add(new ValidationErrorDetail("senha", "A senha é obrigatória"));
    else if (request.getSenha().trim().length() < 8)
      validationErrors.add(new ValidationErrorDetail("senha",
          "A senha deve conter no mínimo 8 caracteres, uma letra maiúscula, uma letra minúscula e um número"));

    if (request.getEndereco() == null) {
      validationErrors.add(new ValidationErrorDetail("endereco", "O endereço é obrigatório"));
    } else {
      if (isBlank(request.getEndereco().getCep()))
        validationErrors.add(new ValidationErrorDetail("endereco.cep", "CEP é obrigatório"));
      if (isBlank(request.getEndereco().getLogradouro()))
        validationErrors.add(new ValidationErrorDetail("endereco.logradouro", "Logradouro é obrigatório"));
      if (isBlank(request.getEndereco().getNumero()))
        validationErrors.add(new ValidationErrorDetail("endereco.numero", "Número é obrigatório"));
      if (isBlank(request.getEndereco().getBairro()))
        validationErrors.add(new ValidationErrorDetail("endereco.bairro", "Bairro é obrigatório"));
      if (isBlank(request.getEndereco().getCidade()))
        validationErrors.add(new ValidationErrorDetail("endereco.cidade", "Cidade é obrigatória"));
      if (isBlank(request.getEndereco().getEstado()))
        validationErrors.add(new ValidationErrorDetail("endereco.estado", "Estado é obrigatório"));
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
      UserResponseDTO response = userService.registrarUsuario(request);

      return ResponseEntity
          .status(HttpStatus.CREATED)
          .body(response);

    } catch (IllegalArgumentException ex) {
      ValidationErrorDetail detail = new ValidationErrorDetail(
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