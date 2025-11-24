package com.shadow.OptiBankBuddy.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;

public class CreditDebitRequest {
	
	@Schema(description = "Unique account number", example = "2025567890")
	private String accountNumber;
	@Schema(description = "Amount to be credited or debited", example = "500.00")
	private BigDecimal balance;
	
	
	public CreditDebitRequest() {
		// TODO Auto-generated constructor stub
	}
	public CreditDebitRequest(String accountNumber, BigDecimal balance) {
		super();
		this.accountNumber = accountNumber;
		this.balance = balance;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "CreditDebitRequest [accountNumber=" + accountNumber + ", balance=" + balance + "]";
	}
	
	

}
