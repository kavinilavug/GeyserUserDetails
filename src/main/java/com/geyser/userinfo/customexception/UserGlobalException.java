package com.geyser.userinfo.customexception;

import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.geyser.userinfo.model.UserDetailModel;

@ControllerAdvice
public class UserGlobalException extends ResponseEntityExceptionHandler {

	@ExceptionHandler(NoSuchElementException.class)
	ResponseEntity<?> handleNoSuchElenent(NoSuchElementException elementException) {
		// Assume this method fetches a user by ID
		// if the value is not there that tym we will get it
		ApiResponse<Map<String, String>> response = new ApiResponse<>(false, "No value is present in DB for userid");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);//400 
	}

	@ExceptionHandler(BusinessException.class)
	ResponseEntity<?> handleBusinessException(BusinessException exe) {
		ControllerException exp = new ControllerException(exe.getErrorCode(), exe.getErrorMessage());
		return new ResponseEntity<ControllerException>(exp, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		ApiResponse<Map<String, String>> response = new ApiResponse<>(false, "please change the http method type");
		return new ResponseEntity<Object>(response, HttpStatus.METHOD_NOT_ALLOWED);//405 
	}

	@ExceptionHandler(IllegalArgumentException.class)
	ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException illegalException) {
		ApiResponse<Map<String, String>> response = new ApiResponse<>(false, "No value is present in DB");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);//400
	}
}
