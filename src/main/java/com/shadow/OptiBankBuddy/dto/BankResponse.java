package com.shadow.OptiBankBuddy.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class BankResponse {
	
	@Schema(description = "Response code indicating the status of the operation", example = "002")
	private String responseCode;
	
	@Schema(description = "Detailed message about the response", example = "Operation successful")
	private String responseMessage;
	
	@Schema(description = "Information about the account involved in the operation")
	private  AccountInfo accountInfo;
	
	public BankResponse() {
		// TODO Auto-generated constructor stub
	}
	public BankResponse(String responseCode, String responseMessage, AccountInfo accountInfo) {
		super();
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
		this.accountInfo = accountInfo;
	}
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	public AccountInfo getAccountInfo() {
		return accountInfo;
	}
	public void setAccountInfo(AccountInfo accountInfo) {
		this.accountInfo = accountInfo;
	}
	@Override
	public String toString() {
		return "BankResponse [responseCode=" + responseCode + ", responseMessage=" + responseMessage + ", accountInfo="
				+ accountInfo + "]";
	}
	
	
	

}
