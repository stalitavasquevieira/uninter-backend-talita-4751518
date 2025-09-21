package com._8.tarefas_api_uninter.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

@ExceptionHandler(ResourceNotFoundException.class)
public ResponseEntity<?> handleNotFound(ResourceNotFoundException ex) {
Map<String, Object> body = new HashMap<>();
body.put("erro", ex.getMessage());
return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
}

@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<?> handleValidation(MethodArgumentNotValidException ex) {
Map<String, Object> body = new HashMap<>();
body.put("erro", "Validação falhou");
Map<String, String> fields = new HashMap<>();
ex.getBindingResult().getFieldErrors()
.forEach(e -> fields.put(e.getField(), e.getDefaultMessage()));
body.put("fields", fields);
return ResponseEntity.badRequest().body(body);
}
}