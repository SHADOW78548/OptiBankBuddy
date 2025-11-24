package com.shadow.OptiBankBuddy.utils;

import java.time.Year;

public class AccountUtils {
	
	public static final String ACCOUNT_EXIST_CODE="01";
	public static final String ACCOUNT_EXIST_MESSAGE="Account With Given Email Already Exists!!!";
	
	public static final String ACCOUNT_CREATION_SUCCESS_CODE="02";
	public static final String ACCOUNT_CREATION_SUCCESS_MESSAGE="Account Created Successfully.";
	
	
	public static final String ACCOUNT_NOT_EXIST_CODE="03";
	public static final String ACCOUNT_NOT_EXIST_MESSAGE="Account With Given Account Number Does Not Exist!!!";
	
	public static final String ACCOUNT_FIND_SUCCES_CODE="04";
	public static final String ACCOUNT_FIND_SUCCESS_MESSAGE="Account Found Successfully !!";
	
	public static final String ACCOUNT_CREDIT_SUCCESS_C0DE="05";
	public static final String ACCOUNT_CREDIT_SUCCESS_MESSAGE="Account Credited Successfully !!";
	
	public static final String INSUFFICIENT_BALANCE_CODE="06";
	public static final String INSUFFICIENT_BALANCE_MESSAGE="OOPS!! User Has Insufficient Balance To Transfer !!";
	
	public static final String ACCOUNT_DEBIT_SUCCESS_CODE="07";
	public static final String ACCOUNT_DEBIT_SUCCESS_MESSAGE="Account Debited Successfully !!";
	
	public static final String FUND_TRANSFER_SUCCESS_CODE="08";
	public static final String FUND_TRANSFER_SUCCESS_MESSAGE="Fund Transfer Successful !!";

	//convert the current year and account number to string, then concatenate them
	
	public static String generateAccountNumber() {

		Year currentYear = Year.now();
		int min=100000;
		int max=999999;
		
		//generate a random number between min and max
		int randomNumber = (int) (Math.random() * (max - min + 1) + min);
		
		//convert the current year and account number to string, then concatenate them
		String accountNumber = String.valueOf(currentYear.getValue()) + String.valueOf(randomNumber);
		return accountNumber;
	}

}
