package com.shadow.OptiBankBuddy.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;


public class AccountInfo {
	
	@Schema(description = "Name of the account holder", example = "Sujit Mondal")
	private String accountName;
	
	@Schema(description = "Current balance of the account", example = "10000.50")
	private BigDecimal accountBalance;
	
	@Schema(description = "Unique account number", example = "20253456789")
	private String accountNumber;
	
	
	public AccountInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AccountInfo(String accountName, BigDecimal accountBalance, String accountNumber) {
		super();
		this.accountName = accountName;
		this.accountBalance = accountBalance;
		this.accountNumber = accountNumber;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public BigDecimal getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(BigDecimal accountBalance) {
		this.accountBalance = accountBalance;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	@Override
	public String toString() {
		return "AccountInfo [accountName=" + accountName + ", accountBalance=" + accountBalance + ", accountNumber="
				+ accountNumber + "]";
	}
	
	

}
