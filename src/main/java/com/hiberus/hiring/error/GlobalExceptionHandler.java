package com.hiberus.hiring.error;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<Map<String, Object>> handleValidationException(EntityNotFoundException ex) {
		log.warn("EntityNotFound: {}", ex.getMessage());

		Map<String, Object> errorResponse = new HashMap<>();
		errorResponse.put("timestamp", LocalDateTime.now());
		errorResponse.put("status", HttpStatus.NO_CONTENT.value());
		errorResponse.put("error", HttpStatus.NO_CONTENT.getReasonPhrase());
		errorResponse.put("message", ex.getMessage());

		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<Map<String, Object>> handleValidationException(ValidationException ex) {
		log.warn("Validation Error: {}", ex.getMessage());

		Map<String, Object> errorResponse = new HashMap<>();
		errorResponse.put("timestamp", LocalDateTime.now());
		errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
		errorResponse.put("error", HttpStatus.BAD_REQUEST.getReasonPhrase());
		errorResponse.put("message", ex.getMessage());

		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> handleValidationException(MethodArgumentNotValidException ex) {
		log.warn("Validation Error: {}", ex.getMessage());

		Map<String, Object> errorResponse = new HashMap<>();
		errorResponse.put("timestamp", LocalDateTime.now());
		errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
		errorResponse.put("error", HttpStatus.BAD_REQUEST.getReasonPhrase());
		errorResponse.put("message", ex.getMessage());

		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, Object>> handleGeneralException(Exception ex) {
		log.error("Internal Server Error: ", ex);

		Map<String, Object> errorResponse = new HashMap<>();
		errorResponse.put("timestamp", LocalDateTime.now());
		errorResponse.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorResponse.put("error", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		errorResponse.put("message", "An unexpected error occurred. Please contact support.");

		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
