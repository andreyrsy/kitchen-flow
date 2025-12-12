package dev.andreyrsy.orderly.exception;

import dev.andreyrsy.orderly.exception.business.*;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidacao(MethodArgumentNotValidException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("timestamp", LocalDateTime.now());
        response.put("message", "Erro de validação nos campos");
        response.put("erros", ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> Map.of("campo", Objects.requireNonNull(error.getField()), "mensagem",
                        error.getDefaultMessage()))
                .collect(Collectors.toList()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleViolacao(ConstraintViolationException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("timestamp", LocalDateTime.now());
        response.put("message", "Erro de validação nos campos");
        response.put("erros", ex.getConstraintViolations()
                .stream()
                .map(error -> Map.of("campo", error.getPropertyPath().toString(), "mensagem", error.getMessage()))
                .collect(Collectors.toList()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(ProdutoNaoEncontradoException.class)
    public ResponseEntity<?> handleProdutoNaoEncontrado(ProdutoNaoEncontradoException exception) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.NOT_FOUND.value());
        response.put("timestamp", LocalDateTime.now());
        response.put("message", exception.getMessage());
        response.put("tipo", "PRODUTO_NAO_ENCONTRADO");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(CategoriaNaoEncontradaException.class)
    public ResponseEntity<?> handleCategoriaNaoEncontrada(CategoriaNaoEncontradaException exception) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.NOT_FOUND.value());
        response.put("timestamp", LocalDateTime.now());
        response.put("message", exception.getMessage());
        response.put("tipo", "CATEGORIA_NAO_ENCONTRADA");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(LoteNaoEncontradoException.class)
    public ResponseEntity<?> handleLoteNaoEncontrado(LoteNaoEncontradoException exception) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.NOT_FOUND.value());
        response.put("timestamp", LocalDateTime.now());
        response.put("message", exception.getMessage());
        response.put("tipo", "LOTE_NAO_ENCONTRADO");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(ProdutoDuplicadoException.class)
    public ResponseEntity<?> handleProdutoDuplicado(ProdutoDuplicadoException exception) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("timestamp", LocalDateTime.now());
        response.put("message", exception.getMessage());
        response.put("tipo", "PRODUTO_DUPLICADO");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(QuantidadeInsuficienteException.class)
    public ResponseEntity<?> handleQuantidadeInsuficiente(QuantidadeInsuficienteException exception) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("timestamp", LocalDateTime.now());
        response.put("message", exception.getMessage());
        response.put("tipo", "QUANTIDADE_INSUFICIENTE");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(DataInvalidaException.class)
    public ResponseEntity<?> handleDataInvalida(DataInvalidaException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("timestamp", LocalDateTime.now());
        response.put("message", ex.getMessage());
        response.put("tipo", "DATA_INVALIDA");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(RuntimeException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("timestamp", LocalDateTime.now());
        response.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
