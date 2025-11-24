package com.shadow.OptiBankBuddy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shadow.OptiBankBuddy.dto.BankResponse;
import com.shadow.OptiBankBuddy.dto.CreditDebitRequest;
import com.shadow.OptiBankBuddy.dto.EnquiryRequest;
import com.shadow.OptiBankBuddy.dto.UserRequest;
import com.shadow.OptiBankBuddy.exceptions.UserAlreadyExistsException;
import com.shadow.OptiBankBuddy.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/users")
@Tag(name = "User Controller", description = "APIs for user account management and transactions")
public class UserController {

    private final UserDetailsService getUsers;
	@Autowired
	UserService userService;

    UserController(UserDetailsService getUsers) {
        this.getUsers = getUsers;
    }
	
	@Operation(
			summary = "Create User Account", 
			description = "Creates a new user account with the provided details."
			)
	@ApiResponse(
			responseCode = "201", 
			description = "User account created successfully"
			)
	
	@PostMapping("/create")
	public ResponseEntity<BankResponse> createUser(@RequestBody UserRequest userRequest) throws UserAlreadyExistsException {
		// Implementation for creating a user
		
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.createAccount(userRequest));
	}
	@Operation(summary = "Balance Enquiry", description = "Retrieves the balance for the specified user account.")
	@ApiResponse(responseCode = "200", description = "Balance retrieved successfully")
	@GetMapping("/balance")
	public ResponseEntity<BankResponse> balanceEnquiry(@RequestBody EnquiryRequest request){
		
		return ResponseEntity.ok(userService.balanceEnquiry(request));
	}
	
	
	@Operation(summary = "Name Enquiry", description = "Retrieves the name associated with the specified user account.")
	@ApiResponse(responseCode = "200", description = "Name retrieved successfully")
	@GetMapping("/name")
	public ResponseEntity<String> nameEnquiry(@RequestBody EnquiryRequest request){
		
		return ResponseEntity.ok(userService.nameEnquiry(request));
	}
	
	
	@Operation(summary = "Credit Account", description = "Credits"
			+ " the specified amount to the user account.")
	@ApiResponse(responseCode = "200", description = "Account credited successfully")
	@PostMapping("/credit")
	public ResponseEntity<BankResponse> creditAccount(@RequestBody CreditDebitRequest request){
		
		return ResponseEntity.ok(userService.creditAccount(request));
	}
	
	
	@Operation(summary = "Debit Account", description = "Debits the specified amount from the user account.")
	@ApiResponse(responseCode = "200", description = "Account debited successfully")
	@PostMapping("/debit")
	public ResponseEntity<BankResponse> debitAccount(@RequestBody CreditDebitRequest request) {

		return ResponseEntity.ok(userService.debitAccount(request));
	}
	
	
	@Operation(summary = "Transfer Funds", description = "Transfers funds between user accounts.")
	@ApiResponse(responseCode = "200", description = "Funds transferred successfully")
	@PostMapping("/transfer")
	public ResponseEntity<BankResponse> transferFunds(@RequestBody com.shadow.OptiBankBuddy.dto.TransferRequest request) {

		return ResponseEntity.ok(userService.transferFunds(request));
	}
	
}
