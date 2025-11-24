package com.shadow.OptiBankBuddy.service;

import com.shadow.OptiBankBuddy.dto.BankResponse;
import com.shadow.OptiBankBuddy.dto.CreditDebitRequest;
import com.shadow.OptiBankBuddy.dto.EnquiryRequest;
import com.shadow.OptiBankBuddy.dto.TransferRequest;
import com.shadow.OptiBankBuddy.dto.UserRequest;

public interface UserService {
	
	BankResponse createAccount(UserRequest userequest);
	BankResponse balanceEnquiry(EnquiryRequest enquiryRequest);
	String nameEnquiry(EnquiryRequest enquiryRequest);
	
	BankResponse creditAccount(CreditDebitRequest request);
	BankResponse debitAccount(CreditDebitRequest request);
	
	BankResponse transferFunds(TransferRequest request);
}
