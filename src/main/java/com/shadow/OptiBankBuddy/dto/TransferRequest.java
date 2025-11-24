package com.shadow.OptiBankBuddy.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;

public class TransferRequest {
	@Schema(description = "Source account number", example = "2025567890")
	private String sourceAccount;
	@Schema(description = "Destination account number", example = "2025567891")
	private String destinationAccount;
	@Schema(description = "Amount to be transferred", example = "750.00")
	private BigDecimal amount;
	
	
	public TransferRequest() {
		// TODO Auto-generated constructor stub
	}
	public TransferRequest(String sourceAccount, String destinationAccount, BigDecimal amount) {
		super();
		this.sourceAccount = sourceAccount;
		this.destinationAccount = destinationAccount;
		this.amount = amount;
	}
	public String getSourceAccount() {
		return sourceAccount;
	}
	public void setSourceAccount(String sourceAccount) {
		this.sourceAccount = sourceAccount;
	}
	public String getDestinationAccount() {
		return destinationAccount;
	}
	public void setDestinationAccount(String destinationAccount) {
		this.destinationAccount = destinationAccount;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "TransferRequest [sourceAccount=" + sourceAccount + ", destinationAccount=" + destinationAccount
				+ ", amount=" + amount + "]";
	}
	
	
}
