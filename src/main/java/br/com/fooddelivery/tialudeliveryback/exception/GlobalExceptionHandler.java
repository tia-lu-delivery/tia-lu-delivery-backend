package br.com.fooddelivery.tialudeliveryback.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import br.com.fooddelivery.tialudeliveryback.dto.ErrorDTO;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ErrorDTO error = new ErrorDTO();
        error.setCodigoErro("RESOURCE_NOT_FOUND");
        error.setMensagem(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleGenericException(Exception ex) {
        ErrorDTO error = new ErrorDTO();
        error.setCodigoErro("INTERNAL_SERVER_ERROR");
        error.setMensagem(ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
