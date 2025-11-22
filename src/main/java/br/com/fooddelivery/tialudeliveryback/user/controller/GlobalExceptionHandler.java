package br.com.fooddelivery.tialudeliveryback.user.controller;

import br.com.fooddelivery.tialudeliveryback.user.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleValidationExceptions(
            MethodArgumentNotValidException ex) {

        List<ErrorResponseDto.CampoErro> camposComErro = new ArrayList<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String campo = ((FieldError) error).getField();
            String mensagem = error.getDefaultMessage();

            if ("dataNascimento".equals(campo) && mensagem != null &&
                (mensagem.contains("data") || mensagem.contains("date"))) {
                mensagem = "Formato de data inválido. Use YYYY-MM-DD.";
            }

            camposComErro.add(new ErrorResponseDto.CampoErro(campo, mensagem));
        });

        ErrorResponseDto.Erro erro = new ErrorResponseDto.Erro(
                "ERRO_FORMATO_DADOS",
                "A requisição contém erros de formato.",
                camposComErro
        );

        ErrorResponseDto response = new ErrorResponseDto(erro);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<ErrorResponseDto> handleDateTimeParseException(
            DateTimeParseException ex) {

        List<ErrorResponseDto.CampoErro> camposComErro = new ArrayList<>();
        camposComErro.add(new ErrorResponseDto.CampoErro(
                "dataNascimento",
                "Formato de data inválido. Use YYYY-MM-DD."
        ));

        ErrorResponseDto.Erro erro = new ErrorResponseDto.Erro(
                "ERRO_FORMATO_DADOS",
                "A requisição contém erros de formato.",
                camposComErro
        );

        ErrorResponseDto response = new ErrorResponseDto(erro);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
