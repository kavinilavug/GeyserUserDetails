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

<<<<<<< HEAD
@ControllerAdvice
public class UserGlobalException extends ResponseEntityExceptionHandler {

	@ExceptionHandler(BusinessException.class)
	ResponseEntity<?> handleBusinessException(BusinessException exe) {
		CustomErrorResponse res = new CustomErrorResponse(exe.isSuccess(), exe.getMessage());
		return new ResponseEntity<CustomErrorResponse>(res, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(BadRequestException.class)
	ResponseEntity<?> handleBadRequestException(BadRequestException req) {
		CustomErrorResponse res = new CustomErrorResponse(req.isSuccess(),req.getMessage());
		return new ResponseEntity<CustomErrorResponse>(res, HttpStatus.BAD_REQUEST);// 400
	}
	
	@ExceptionHandler(NullPointerException.class)
	ResponseEntity<?> handleNullPointerException(NullPointerException req) {
		CustomErrorResponse res = new CustomErrorResponse(false, "the value should not be null");
		return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);// 400
	}
	@ExceptionHandler(NoSuchElementException.class)
	ResponseEntity<?> handleNoSuchElenent(NoSuchElementException elementException) {
		// Assume this method fetches a user by ID
		// if the value is not there that tym we will get it
		ApiResponse<Map<String, String>> response = new ApiResponse<>(false, "No value is present in DB for userid");
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);// 400
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ApiResponse<Map<String, String>> response = new ApiResponse<>(false, "please change the http method type");
		return new ResponseEntity<Object>(response, HttpStatus.METHOD_NOT_ALLOWED);// 405
	}

	@ExceptionHandler(Exception.class)
	ResponseEntity<?> handleException(Exception req) {
		BusinessException res = new BusinessException(false,"Please Check The error"+req.getStackTrace());
		return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);// 400
=======
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
>>>>>>> branch 'main' of https://github.com/kavinilavug/GeyserUserDetails.git
	}
}
