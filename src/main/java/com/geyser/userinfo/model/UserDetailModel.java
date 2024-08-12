package com.geyser.userinfo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

@Component
public class UserDetailModel implements Serializable{
	@NotBlank(message = "Username is mandatory")
	@Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
	private String username;

	@NotBlank(message = "Password is mandatory")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$",
             message = "Password must contain at least one digit, one lowercase letter, one uppercase letter, and one special character (@#$%^&+=)")
	private String password;
	
	@NotBlank(message = "email is mandatory")
	@Email(message = "Please provide a valid email address")
	private String email;

	@NotNull(message = "Age is mandatory")
	@Min(value = 0, message = "Age must be between 0 and 99")
    @Max(value = 99, message = "Age must be between 0 and 99")
	private Integer age;

	@Pattern(regexp = "\\d{3}-\\d{3}-\\d{4}", message = "Kindly Provide a Valid phone number")
	private String phoneNumber;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
