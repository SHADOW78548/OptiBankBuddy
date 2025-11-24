package com.shadow.OptiBankBuddy.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;

public class TransactionDto {
	@Schema(description = "Type of the transaction", example = "CREDIT or DEBIT")
	private String transactionType;
	@Schema(description = "Amount involved in the transaction", example = "1500.00")
	private BigDecimal amount;
	@Schema(description = "Unique account number associated with the transaction", example = "2025567890")
	private String accountNumber;
	@Schema(description = "Status of the transaction", example = "SUCCESS or FAILED")
	private String status;
	
	public TransactionDto() {
		// TODO Auto-generated constructor stub
	}

	public TransactionDto(String transactionType, BigDecimal amount, String accountNumber, String status) {
		super();
		this.transactionType = transactionType;
		this.amount = amount;
		this.accountNumber = accountNumber;
		this.status = status;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "TransactionDto [transactionType=" + transactionType + ", amount=" + amount + ", accountNumber="
				+ accountNumber + ", status=" + status + "]";
	}
	
}
