package com.geyser.userinfo.customexception;

import java.util.NoSuchElementException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class UserGlobalException  extends ResponseEntityExceptionHandler{

	@ExceptionHandler(NoSuchElementException.class)
	ResponseEntity<?> handleNoSuchElenent(NoSuchElementException elementException){
		return new ResponseEntity<String>("No value is present in DB",HttpStatus.BAD_REQUEST);	
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return new ResponseEntity<Object>("please change the http method type",HttpStatus.NOT_FOUND);	
	}

	@ExceptionHandler(IllegalArgumentException.class)
	ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException illegalException){
		return new ResponseEntity<String>("No value is present in DB",HttpStatus.BAD_REQUEST);	
	}
	
	@ExceptionHandler(Exception.class)
	ResponseEntity<?> handleException(Exception exp){
		return new ResponseEntity<String>("Something went wrong in service layer",HttpStatus.BAD_REQUEST);	
	}
}
