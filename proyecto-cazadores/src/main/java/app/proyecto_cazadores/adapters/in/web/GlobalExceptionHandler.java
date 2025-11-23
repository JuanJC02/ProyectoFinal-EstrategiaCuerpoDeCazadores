package app.proyecto_cazadores.adapters.in.web;

import app.proyecto_cazadores.domain.exception.BadRequestException;
import app.proyecto_cazadores.domain.exception.MensajeNotFoundException;
import app.proyecto_cazadores.domain.exception.PilarNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PilarNotFoundException.class)
    public ResponseEntity<String> handlePilarNotFound(PilarNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(MensajeNotFoundException.class)
    public ResponseEntity<String> handleMensajeNotFound(MensajeNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> handleBadRequest(BadRequestException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleOther(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error interno.");
    }
}
