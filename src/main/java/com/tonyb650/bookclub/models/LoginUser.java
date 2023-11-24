package com.tonyb650.bookclub.models;

import jakarta.validation.constraints.NotBlank;

public class LoginUser {
	
	// *** MEMBER VARIABLES ***
	@NotBlank (message = "Please enter a valid email address.")
	private String email;
	
	@NotBlank (message = "Please enter a password.")
	private String password;

	// *** CONSTUCTORS ***
	public LoginUser() {
	}

	// *** GETTERS AND SETTERS ***
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
