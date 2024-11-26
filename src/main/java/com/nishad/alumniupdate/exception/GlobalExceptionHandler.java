package com.nishad.alumniupdate.exception;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  // Structure for error response
  private record ErrorResponse(
          LocalDateTime timestamp,
          int status,
          String error,
          String message,
          String path
  ) {}

  @ExceptionHandler(ResourceNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
    log.error("Resource not found: {}", ex.getMessage());
    return createErrorResponse(HttpStatus.NOT_FOUND, "Resource Not Found", ex.getMessage());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach(error -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    });

    Map<String, Object> response = new HashMap<>();
    response.put("timestamp", LocalDateTime.now());
    response.put("status", HttpStatus.BAD_REQUEST.value());
    response.put("error", "Validation Failed");
    response.put("errors", errors);

    return ResponseEntity.badRequest().body(response);
  }

  @ExceptionHandler(BadCredentialsException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public ResponseEntity<ErrorResponse> handleBadCredentialsException(BadCredentialsException ex) {
    log.error("Authentication failed: {}", ex.getMessage());
    return createErrorResponse(HttpStatus.UNAUTHORIZED, "Authentication Failed", "Invalid email or password");
  }

  @ExceptionHandler(UsernameNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<ErrorResponse> handleUsernameNotFoundException(UsernameNotFoundException ex) {
    log.error("User not found: {}", ex.getMessage());
    return createErrorResponse(HttpStatus.NOT_FOUND, "User Not Found", ex.getMessage());
  }

  @ExceptionHandler(ExpiredJwtException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public ResponseEntity<ErrorResponse> handleExpiredJwtException(ExpiredJwtException ex) {
    log.error("JWT token expired: {}", ex.getMessage());
    return createErrorResponse(HttpStatus.UNAUTHORIZED, "Token Expired", "Your session has expired. Please login again");
  }

  @ExceptionHandler(SignatureException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public ResponseEntity<ErrorResponse> handleJwtSignatureException(SignatureException ex) {
    log.error("Invalid JWT signature: {}", ex.getMessage());
    return createErrorResponse(HttpStatus.UNAUTHORIZED, "Invalid Token", "Invalid authentication token");
  }

  @ExceptionHandler(IllegalArgumentException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
    log.error("Invalid argument: {}", ex.getMessage());
    return createErrorResponse(HttpStatus.BAD_REQUEST, "Invalid Request", ex.getMessage());
  }

  @ExceptionHandler(ConstraintViolationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException ex) {
    log.error("Constraint violation: {}", ex.getMessage());
    return createErrorResponse(HttpStatus.BAD_REQUEST, "Validation Failed", ex.getMessage());
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ResponseEntity<ErrorResponse> handleAllUncaughtException(Exception ex) {
    log.error("Unexpected error occurred", ex);
    return createErrorResponse(
            HttpStatus.INTERNAL_SERVER_ERROR,
            "Internal Server Error",
            "An unexpected error occurred. Please try again later"
    );
  }

  private ResponseEntity<ErrorResponse> createErrorResponse(HttpStatus status, String error, String message) {
    ErrorResponse errorResponse = new ErrorResponse(
            LocalDateTime.now(),
            status.value(),
            error,
            message,
            null  // path can be added through a request attribute if needed
    );
    return new ResponseEntity<>(errorResponse, status);
  }
}
