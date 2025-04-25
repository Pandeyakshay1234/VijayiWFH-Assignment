package com.taskmanagement.execptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler  {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	
	public ResponseEntity<?> handleResourceNotFound(ResourceNotFoundException ex) {
		
		 Map<String, Object> errorBody = new HashMap<>();
		 
		 errorBody.put("message", ex.getMessage());
		 
	      errorBody.put("status", HttpStatus.BAD_REQUEST.value());
	      
	      return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
	
		
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	
	public ResponseEntity<?> handleIllegalArgument (IllegalArgumentException ex) {
		
		
		Map<String , Object> errorBody = new HashMap<>();
		
		errorBody.put("timestamp", LocalDateTime.now());
        errorBody.put("message", ex.getMessage());
        errorBody.put("status", HttpStatus.BAD_REQUEST.value());
        
        return new ResponseEntity<> (errorBody , HttpStatus.BAD_REQUEST);
		
	}
	

}
