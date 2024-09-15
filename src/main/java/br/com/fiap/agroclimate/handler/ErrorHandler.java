package br.com.fiap.agroclimate.handler;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Void> error404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(InternalError.class)
    public ResponseEntity<Void> error500() {
        return ResponseEntity.status(500).build();
    }

    @ExceptionHandler(HttpClientErrorException.Unauthorized.class)
    public ResponseEntity<Void> error401() {
        return ResponseEntity.status(401).build();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Void> error400() {
        return ResponseEntity.badRequest().build();
    }
}
