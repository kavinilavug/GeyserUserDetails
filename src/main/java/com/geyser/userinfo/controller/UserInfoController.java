package com.geyser.userinfo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.geyser.userinfo.customexception.BusinessException;
import com.geyser.userinfo.customexception.ControllerException;
import com.geyser.userinfo.entity.UserDetails;
import com.geyser.userinfo.model.SuccesMessage;
import com.geyser.userinfo.service.UserDetailsService;

@RestController
@RequestMapping("/geyser")
public class UserInfoController {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private SuccesMessage succesMessage;

	@GetMapping("/check")
	String checkingApi() {
		return "Welcome geyser";
	}

	@PostMapping("/user")
	public ResponseEntity<?> createUser(@RequestBody UserDetails userDetails) {
		try {
			UserDetails userdet = userDetailsService.saveUser(userDetails);
			succesMessage.setSuccessCode("701");
			succesMessage.setSuccessmessage("User is created");
			return new ResponseEntity<>(succesMessage, HttpStatus.CREATED);
		} catch (BusinessException e)// custom Exception
		{
			ControllerException exp = new ControllerException(e.getErrorCode(), e.getErrorMessage());
			return new ResponseEntity<>(exp, HttpStatus.BAD_REQUEST);
		} catch (Exception e)// generic exception
		{
			ControllerException exp = new ControllerException("501", "Something went wrong in controller");
			return new ResponseEntity<>(exp, HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/user/{userid}")
	public ResponseEntity<?> updateUser(@PathVariable("userid") Long userid, @RequestBody UserDetails userDetails) {
		try {
			userDetailsService.updateUserById(userid, userDetails);
			succesMessage.setSuccessCode("702");
			succesMessage.setSuccessmessage("User updated successfully");
			return new ResponseEntity<>(succesMessage, HttpStatus.OK);
		} catch (BusinessException e)// custom Exception
		{
			ControllerException exp = new ControllerException(e.getErrorCode(), e.getErrorMessage());
			return new ResponseEntity<ControllerException>(exp, HttpStatus.BAD_REQUEST);
		} catch (Exception e)// generic exception
		{
			ControllerException exp = new ControllerException("502", "Something went wrong in controller");
			return new ResponseEntity<ControllerException>(exp, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/user")
	public ResponseEntity<?> getAllUser() {
		try {
			List<UserDetails> userdetailsList = userDetailsService.selectAllUser();
			return new ResponseEntity<List<UserDetails>>(userdetailsList, HttpStatus.OK);

		} catch (BusinessException e)// custom Exception
		{
			ControllerException exp = new ControllerException(e.getErrorCode(), e.getErrorMessage());
			return new ResponseEntity<ControllerException>(exp, HttpStatus.BAD_REQUEST);
		} catch (Exception e)// generic exception
		{
			ControllerException exp = new ControllerException("503", "Something went wrong in controller");
			return new ResponseEntity<ControllerException>(exp, HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/user/{userid}")
	public ResponseEntity<?> getAllUser(@PathVariable Long userid) {
		UserDetails userdata;
		try {
			userdata = userDetailsService.selectUserId(userid);
			return new ResponseEntity<UserDetails>(userdata, HttpStatus.OK);
		} catch (BusinessException e)// custom Exception
		{
			ControllerException exp = new ControllerException(e.getErrorCode(), e.getErrorMessage());
			return new ResponseEntity<>(exp, HttpStatus.BAD_REQUEST);
		} catch (Exception e)// generic exception
		{
			ControllerException exp = new ControllerException("503", "Something went wrong controller");
			return new ResponseEntity<>(exp, HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/user/{userid}")
	public ResponseEntity<?> deleteUser(@PathVariable("userid") Long userid) {
		try {
			userDetailsService.deleteUserById(userid);
			succesMessage.setSuccessCode("703");
			succesMessage.setSuccessmessage("Deleted successfully");
			return new ResponseEntity<>(succesMessage, HttpStatus.OK);
		} catch (BusinessException e)// custom Exception
		{
			ControllerException exp = new ControllerException(e.getErrorCode(), e.getErrorMessage());
			return new ResponseEntity<ControllerException>(exp, HttpStatus.BAD_REQUEST);
		} catch (Exception e)// generic exception
		{
			ControllerException exp = new ControllerException("504", "Something went wrong controller");
			return new ResponseEntity<ControllerException>(exp, HttpStatus.BAD_REQUEST);
		}
		
		
	}
}
