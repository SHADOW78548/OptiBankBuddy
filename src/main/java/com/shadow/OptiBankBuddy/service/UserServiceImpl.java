package com.shadow.OptiBankBuddy.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shadow.OptiBankBuddy.dao.UserDao;
import com.shadow.OptiBankBuddy.dto.AccountInfo;
import com.shadow.OptiBankBuddy.dto.BankResponse;
import com.shadow.OptiBankBuddy.dto.CreditDebitRequest;
import com.shadow.OptiBankBuddy.dto.EmailDetails;
import com.shadow.OptiBankBuddy.dto.EnquiryRequest;
import com.shadow.OptiBankBuddy.dto.TransactionDto;
import com.shadow.OptiBankBuddy.dto.TransferRequest;
import com.shadow.OptiBankBuddy.dto.UserRequest;
import com.shadow.OptiBankBuddy.entity.UserInfo;
import com.shadow.OptiBankBuddy.utils.AccountUtils;


@Service
public class UserServiceImpl implements UserService {

  

	@Autowired
	UserDao userDao;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	TransactionService transactionService;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	
	@Override
	public BankResponse createAccount(UserRequest userrequest) {
		
		if (userDao.existsByEmail(userrequest.getEmail())) {
			BankResponse response1= new BankResponse();
			response1.setResponseCode(AccountUtils.ACCOUNT_EXIST_CODE);
			response1.setResponseMessage(AccountUtils.ACCOUNT_EXIST_MESSAGE);
			response1.setAccountInfo(null);
			return response1;
		}else{
		
		// TODO Auto-generated method stub
			UserInfo newUser= new UserInfo();
			newUser.setFirstName(userrequest.getFirstName());
			newUser.setLastName(userrequest.getLastName());
			newUser.setGender(userrequest.getGender());
			newUser.setAddress(userrequest.getAddress());
			newUser.setEmail(userrequest.getEmail());
			newUser.setPassword(passwordEncoder.encode( userrequest.getPassword()));
			newUser.setPhoneNumber(userrequest.getPhoneNumber());
			newUser.setStatus("ACTIVE");
			newUser.setAccountNumber(AccountUtils.generateAccountNumber());
			newUser.setBalance(BigDecimal.ZERO);
		
			UserInfo savedUser= userDao.save(newUser);
			
			
			// Sending Welcome Email
			EmailDetails details= new EmailDetails();
			details.setRecipient(savedUser.getEmail());
			details.setSubject("Welcome to OptiBankBuddy - Your Account is Created Successfully!");
			details.setMsgBody("Dear " + savedUser.getFirstName() + ",\n\n"
					+ "We are thrilled to welcome you to OptiBankBuddy! Your account has been successfully created.\n\n"
					+ "Account Number: " + savedUser.getAccountNumber() + "\n" + "Account Holder: "
					+ savedUser.getFirstName() + " " + savedUser.getLastName() + "\n\n"
					+ "Thank you for choosing OptiBankBuddy. We look forward to serving your banking needs.\n\n"
					+ "Best Regards,\n" + "OptiBankBuddy Team");
			details.setAttachment(null);
			emailService.sendEmail(details);
			
			AccountInfo newInfo= new AccountInfo();
			newInfo.setAccountName(savedUser.getFirstName()+" "+savedUser.getLastName());
			newInfo.setAccountBalance(savedUser.getBalance());
			newInfo.setAccountNumber(savedUser.getAccountNumber());
			
			BankResponse response= new BankResponse();
			response.setResponseCode(AccountUtils.ACCOUNT_CREATION_SUCCESS_CODE);
			response.setResponseMessage(AccountUtils.ACCOUNT_CREATION_SUCCESS_MESSAGE);
			response.setAccountInfo(newInfo);
			return response;
		
		}
	}

	@Override
	public BankResponse balanceEnquiry(EnquiryRequest enquiryRequest) {
		// TODO Auto-generated method stub
		if(!userDao.existsByAccountNumber(enquiryRequest.getAccountNumber())) {
            BankResponse response= new BankResponse();
            response.setResponseCode(AccountUtils.ACCOUNT_NOT_EXIST_CODE);
            response.setResponseMessage(AccountUtils.ACCOUNT_NOT_EXIST_MESSAGE);
            response.setAccountInfo(null);
            return response;
		    } else { 
		    	UserInfo existUser=userDao.findByAccountNumber(enquiryRequest.getAccountNumber());
		    	
		    	AccountInfo newInfo= new AccountInfo();
		    	newInfo.setAccountBalance(existUser.getBalance());
		    	newInfo.setAccountNumber(enquiryRequest.getAccountNumber());
		    	newInfo.setAccountName(existUser.getFirstName()+" "+existUser.getLastName());
		    	
		    	
		    	BankResponse response=new BankResponse();
		    	response.setResponseCode(AccountUtils.ACCOUNT_FIND_SUCCES_CODE);
		    	response.setResponseMessage(AccountUtils.ACCOUNT_FIND_SUCCESS_MESSAGE);
		    	response.setAccountInfo(newInfo);
		    	
		    	return response;
		    }
	}

	@Override
	public String nameEnquiry(EnquiryRequest enquiryRequest) {
		// TODO Auto-generated method stub
		if(!userDao.existsByAccountNumber(enquiryRequest.getAccountNumber())) {			
			return AccountUtils.ACCOUNT_NOT_EXIST_MESSAGE;	
		}
		
		UserInfo foundUser=userDao.findByAccountNumber(enquiryRequest.getAccountNumber());
		return foundUser.getFirstName()+" "+foundUser.getLastName();
		
	}

	@Override
	public BankResponse creditAccount(CreditDebitRequest request) {
		// TODO Auto-generated method stub
		if(!userDao.existsByAccountNumber(request.getAccountNumber())) {
			BankResponse response=new BankResponse();
			response.setResponseCode(AccountUtils.ACCOUNT_NOT_EXIST_CODE);
			response.setResponseMessage(AccountUtils.ACCOUNT_NOT_EXIST_MESSAGE);
			response.setAccountInfo(null);
			return response;	
		}
		UserInfo userToCredit=userDao.findByAccountNumber(request.getAccountNumber());
		
		userToCredit.setBalance(userToCredit.getBalance().add(request.getBalance()));
		userDao.save(userToCredit);
		
		TransactionDto transactionDto= new TransactionDto();
		transactionDto.setTransactionType("CREDIT");
		transactionDto.setAmount(request.getBalance());
		transactionDto.setAccountNumber(request.getAccountNumber());
		transactionDto.setStatus("SUCCESS");
		transactionService.saveTransaction(transactionDto);
		
		
		AccountInfo creditedInfo= new AccountInfo();
		creditedInfo.setAccountName(userToCredit.getFirstName()+" "+userToCredit.getLastName());
		creditedInfo.setAccountNumber(request.getAccountNumber());
		creditedInfo.setAccountBalance(userToCredit.getBalance());
		
		
		BankResponse response = new BankResponse();
		response.setResponseCode(AccountUtils.ACCOUNT_CREDIT_SUCCESS_C0DE);
		response.setResponseMessage(AccountUtils.ACCOUNT_CREDIT_SUCCESS_MESSAGE);
		response.setAccountInfo(creditedInfo);
		
		return response;
	}

	@Override
	public BankResponse debitAccount(CreditDebitRequest request) {
		// TODO Auto-generated method stub
		if(!userDao.existsByAccountNumber(request.getAccountNumber())) {
			
			BankResponse response = new BankResponse();
			response.setResponseCode(AccountUtils.ACCOUNT_NOT_EXIST_CODE);
			response.setResponseMessage(AccountUtils.ACCOUNT_NOT_EXIST_MESSAGE);
			response.setAccountInfo(null);
			return response;
			
		}
		
		UserInfo userToDebit= userDao.findByAccountNumber(request.getAccountNumber());
		//Comparing Two bigDecimal Values 
		if(userToDebit.getBalance().compareTo(request.getBalance())==-1) {
			
			BankResponse response= new BankResponse();
			response.setResponseCode(AccountUtils.INSUFFICIENT_BALANCE_CODE);
			response.setResponseMessage(AccountUtils.INSUFFICIENT_BALANCE_MESSAGE);
			response.setAccountInfo(null);
			return response;
		}else {
			userToDebit.setBalance(userToDebit.getBalance().subtract(request.getBalance()));
			userDao.save(userToDebit);
			
			TransactionDto transactionDto= new TransactionDto();
			transactionDto.setTransactionType("DEBIT");
			transactionDto.setAmount(request.getBalance());
			transactionDto.setAccountNumber(request.getAccountNumber());
			transactionDto.setStatus("SUCCESS");
			transactionService.saveTransaction(transactionDto);
			
			
			
			AccountInfo debitedInfo= new AccountInfo();
			debitedInfo.setAccountName(userToDebit.getFirstName()+" "+userToDebit.getLastName());
			debitedInfo.setAccountNumber(request.getAccountNumber());
			debitedInfo.setAccountBalance(userToDebit.getBalance());
			
			BankResponse response= new BankResponse();
			response.setResponseCode(AccountUtils.ACCOUNT_DEBIT_SUCCESS_CODE);
			response.setResponseMessage(AccountUtils.ACCOUNT_DEBIT_SUCCESS_MESSAGE);
			response.setAccountInfo(debitedInfo);
			return response;
			
			
		}
	
	}

	@Override
	public BankResponse transferFunds(TransferRequest request) {
		// TODO Auto-generated method stub
		//check the amount is enough to debit
		//Debit the account
		//Check the credit account exists
		//Credit the account
		
		if (!userDao.existsByAccountNumber(request.getDestinationAccount())) {
			BankResponse response = new BankResponse();
			response.setResponseCode(AccountUtils.ACCOUNT_NOT_EXIST_CODE);
			response.setResponseMessage("Destination " + AccountUtils.ACCOUNT_NOT_EXIST_MESSAGE);
			response.setAccountInfo(null);
			return response;
		}
		
		
		UserInfo sourceUser= userDao.findByAccountNumber(request.getSourceAccount());
		if(sourceUser.getBalance().compareTo(request.getAmount())==-1) {
            BankResponse response= new BankResponse();
            response.setResponseCode(AccountUtils.INSUFFICIENT_BALANCE_CODE);
            response.setResponseMessage(AccountUtils.INSUFFICIENT_BALANCE_MESSAGE);
            response.setAccountInfo(null);
            return response;
		}
		
		sourceUser.setBalance(sourceUser.getBalance().subtract(request.getAmount()));
		userDao.save(sourceUser);
		
		TransactionDto transactionDto= new TransactionDto();
		transactionDto.setTransactionType("FUND TRANSFER DEBIT");
		transactionDto.setAmount(request.getAmount());
		transactionDto.setAccountNumber(request.getSourceAccount());
		transactionDto.setStatus("SUCCESS");
		transactionService.saveTransaction(transactionDto);
		
		
		EmailDetails details= new EmailDetails();
		details.setRecipient(sourceUser.getEmail());
		details.setSubject("Debit Alert from OptiBankBuddy");
		details.setMsgBody("Dear " + sourceUser.getFirstName() + ",\n\n" + "This is to inform you that an amount of "
				+ request.getAmount() + " has been debited from your account " + sourceUser.getAccountNumber()
				+ " towards a transfer to account number " + request.getDestinationAccount() + ".\n\n"
				+ "If you did not authorize this transaction, please contact our customer support immediately.\n\n"
				+ "Best Regards,\n" + "OptiBankBuddy Team");
		details.setAttachment(null);
		emailService.sendEmail(details);
		
		
		UserInfo destUser= userDao.findByAccountNumber(request.getDestinationAccount());
		destUser.setBalance(destUser.getBalance().add(request.getAmount()));
		userDao.save(destUser);
		
		TransactionDto transactionDto2= new TransactionDto();
		transactionDto2.setTransactionType("FUND TRANSFER CREDIT");
		transactionDto2.setAmount(request.getAmount());
		transactionDto2.setAccountNumber(request.getDestinationAccount());
		transactionDto2.setStatus("SUCCESS");
		transactionService.saveTransaction(transactionDto2);
		
		
		EmailDetails details2= new EmailDetails();
		details2.setRecipient(destUser.getEmail());
		details2.setSubject("Credit Alert from OptiBankBuddy");
		details2.setMsgBody("Dear " + destUser.getFirstName() + ",\n\n" + "This is to inform you that an amount of "
				+ request.getAmount() + " has been credited to your account " + destUser.getAccountNumber()
				+ " from account number " + request.getSourceAccount() + ".\n\n" + "Best Regards,\n"
				+ "OptiBankBuddy Team");
		details2.setAttachment(null);
		emailService.sendEmail(details2);
		
		AccountInfo transferInfo= new AccountInfo();
		transferInfo.setAccountName(sourceUser.getFirstName()+" "+sourceUser.getLastName());
		transferInfo.setAccountNumber(sourceUser.getAccountNumber());
		transferInfo.setAccountBalance(sourceUser.getBalance());
		
		BankResponse response= new BankResponse();
		response.setResponseCode(AccountUtils.FUND_TRANSFER_SUCCESS_CODE);
		response.setResponseMessage(AccountUtils.FUND_TRANSFER_SUCCESS_MESSAGE);
		response.setAccountInfo(transferInfo);
		return response;
	
	}
}

