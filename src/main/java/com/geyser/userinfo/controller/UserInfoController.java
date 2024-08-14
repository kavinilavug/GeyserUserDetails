package com.geyser.userinfo.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.geyser.userinfo.customexception.ApiResponse;
import com.geyser.userinfo.customexception.BadRequestException;
import com.geyser.userinfo.entity.UserDetails;
import com.geyser.userinfo.model.UserDetailModel;
import com.geyser.userinfo.service.UserDetailsService;

@RestController
@RequestMapping("/geyser")
@Validated
public class UserInfoController {

	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private UserDetails userDetails;

	@GetMapping("/check")
	String checkingApi() {
		return "Welcome geyser";
	}

	@PostMapping("/user")
	public ResponseEntity<?> createUser(@Valid @RequestBody UserDetailModel UserDetailModel) {
		userDetails.setId(null);//if you not giving nulll next tym wont be insert the record
		userDetails.setUsername(UserDetailModel.getUsername());
		userDetails.setPassword(UserDetailModel.getPassword());
		userDetails.setEmail(UserDetailModel.getEmail());
		userDetails.setAge(UserDetailModel.getAge());
		userDetails.setPhoneNumber(UserDetailModel.getPhoneNumber());

		UserDetails userdet = userDetailsService.saveUser(userDetails);
		
		UserDetailModel.setUsername(userdet.getUsername());
		UserDetailModel.setPassword(userdet.getPassword());
		UserDetailModel.setAge(userdet.getAge());
		UserDetailModel.setPhoneNumber(userdet.getPhoneNumber());
		UserDetailModel.setEmail(userdet.getEmail());

		ApiResponse<UserDetailModel> response = new ApiResponse<>(true, "User registered successfully!",
				UserDetailModel);
		return new ResponseEntity<>(response, HttpStatus.CREATED);// Httpstatus code for created : 201

	}

	@PutMapping("/user/{userid}")
	public ResponseEntity<?> updateUser(@PathVariable("userid") Long userid,
			@RequestBody UserDetailModel userDetailModel) {
		userDetails.setUsername(userDetailModel.getUsername());
		userDetails.setPassword(userDetailModel.getPassword());
		userDetails.setEmail(userDetailModel.getEmail());
		userDetails.setAge(userDetailModel.getAge());
		userDetails.setPhoneNumber(userDetailModel.getPhoneNumber());

		UserDetails updateUser = userDetailsService.updateUserById(userid, userDetails);

		System.out.println(updateUser);
		userDetailModel.setUsername(updateUser.getUsername());
		userDetailModel.setPassword(updateUser.getPassword());
		userDetailModel.setAge(updateUser.getAge());
		userDetailModel.setPhoneNumber(updateUser.getPhoneNumber());
		userDetailModel.setEmail(updateUser.getEmail());

		ApiResponse<UserDetailModel> response = new ApiResponse<>(true, "User updated successfully!", userDetailModel);
		return new ResponseEntity<>(response, HttpStatus.OK);// Httpstatus code for update : 200
	}

	@GetMapping("/user")
	public ResponseEntity<?> getAllUser() {
		List<UserDetails> userdetailsList = userDetailsService.selectAllUser();
		ApiResponse<List<UserDetails>> response = new ApiResponse<>(true, "get the user details successfully!",
				userdetailsList);
		return new ResponseEntity<>(response, HttpStatus.OK);// Httpstatus code for update : 200
	}

	@GetMapping("/user/{userid}")
	public ResponseEntity<?> getAllUser(@PathVariable(required = true) @NotBlank Long userid) {
		try {
			if (userid <= 0) {
				throw new BadRequestException(false, "ID must be a positive integer.");
			}
			UserDetails userdata = userDetailsService.selectUserId(userid);
			ApiResponse<UserDetails> response = new ApiResponse<>(true, "get the user detail successfully!", userdata);
			return new ResponseEntity<>(response, HttpStatus.OK);// Httpstatus code for update : 200
		} catch (NumberFormatException n) {
			throw new BadRequestException(false, "ID must be a valid integer.");
		}
	}

	@DeleteMapping("/user/{userid}")
	public <T> ResponseEntity<?> deleteUser(@PathVariable("userid") Long userid) {
		try {
			if (userid <= 0) {
				throw new BadRequestException(false, "ID must be a positive integer.");
			}
			userDetailsService.deleteUserById(userid);
			ApiResponse<T> response = new ApiResponse<>(true, "get the user detail successfully!");
			return new ResponseEntity<>(response, HttpStatus.OK);// Httpstatus code for update : 204
		} catch (NumberFormatException n) {
			throw new BadRequestException(false, "ID must be a valid integer.");
		}
	}
}
