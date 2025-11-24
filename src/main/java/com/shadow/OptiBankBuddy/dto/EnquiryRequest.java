package com.shadow.OptiBankBuddy.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class EnquiryRequest {
	@Schema(description = "Unique account number", example = "2025567890")
	private String accountNumber;

	public EnquiryRequest() {
		// TODO Auto-generated constructor stub
	}

	public EnquiryRequest(String accountNumber) {
		super();
		this.accountNumber = accountNumber;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	@Override
	public String toString() {
		return "EnquiryRequest [accountNumber=" + accountNumber + "]";
	}

	
	
	
	
}
