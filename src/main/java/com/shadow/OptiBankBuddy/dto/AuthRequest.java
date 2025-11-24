package com.shadow.OptiBankBuddy.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class AuthRequest {
	@Schema(description = "User email address", example = "sujit@gmail.com")
	private String email;
	@Schema(description = "User password", example = "Sujit@1234")
	private String password;
	@Schema(description = "User role", example = "USER")
	private String role="USER";
	public AuthRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AuthRequest(String email, String password, String role) {
		super();
		this.email = email;
		this.password = password;
		this.role = role;
	}
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "AuthRequest [email=" + email + ", password=" + password + ", role=" + role + "]";
	}
	
	
}
