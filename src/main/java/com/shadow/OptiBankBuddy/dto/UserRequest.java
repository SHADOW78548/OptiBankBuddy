package com.shadow.OptiBankBuddy.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class UserRequest {
	@Schema(description = "User's first name", example = "Sujit")
	private String firstName;
	@Schema(description = "User's last name", example = "Mondal")
	private String lastName;
	@Schema(description="Gender of the User", example="Male")
	private String gender;
	@Schema(description = "User's address", example = "Kolkata, India")
	private String address;
	@Schema(description = "User's email address", example = "sujit@gmail.com")
	private String email;
	@Schema(description = "User's password", example = "Sujit@1234")
	private String password;
	@Schema(description = "User's phone number", example = "+918768571849")
	private String phoneNumber;
	
	public UserRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserRequest(String firstName, String lastName, String gender, String address, String email, String password,
			String phoneNumber) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.address = address;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "UserRequest [firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender + ", address="
				+ address + ", email=" + email + ", password=" + password + ", phoneNumber=" + phoneNumber + "]";
	}
	
}
