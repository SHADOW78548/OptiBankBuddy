package com.shadow.OptiBankBuddy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shadow.OptiBankBuddy.dto.AuthRequest;
import com.shadow.OptiBankBuddy.exceptions.UserNotFoundException;
import com.shadow.OptiBankBuddy.jwt.JwtService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	JwtService jwtService;
	
	
	@Autowired
    private AuthenticationManager authenticationManager;
	
	
	@Operation(summary = "User Login", description = "Authenticate user and generate JWT token")
	@PostMapping("/login")
	public String login(@RequestBody AuthRequest user) throws UserNotFoundException {
		//TODO: process POST request
		Authentication auth=authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword()));
		
		if(auth.isAuthenticated()) {
			return jwtService.generateToken(user.getEmail());
		}else {
			throw new UserNotFoundException("Invalid user credentials");
		}
	}
}
