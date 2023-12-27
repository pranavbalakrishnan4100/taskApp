package com.onebox.onebox;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.onebox.onebox.transformers.ResponseTransformer;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
	
	 @ExceptionHandler(IllegalArgumentException.class)
	 public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex) {
	        // Customize the response for IllegalArgumentException
	        return ResponseTransformer.transformToAPIResponse(null, HttpStatus.BAD_REQUEST, ex);
	    }

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		
        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("timestamp", new Date());
        responseBody.put("status", status.value());
         
        List<String> errors = ex.getBindingResult().getFieldErrors()
            .stream()
            .map(x -> x.getDefaultMessage())
            .collect(Collectors.toList());
         
        responseBody.put("errors", errors);
        return new ResponseEntity<>(responseBody, headers, status);
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<Object> handleEnityNotFoundException(EntityNotFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		return ResponseTransformer.transformToAPIResponse(null, HttpStatus.BAD_REQUEST, ex);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		return ResponseTransformer.transformToAPIResponse(null, HttpStatus.BAD_REQUEST, ex);
	}
	
}
