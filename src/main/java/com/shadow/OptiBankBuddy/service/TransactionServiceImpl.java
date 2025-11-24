package com.shadow.OptiBankBuddy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shadow.OptiBankBuddy.dao.TransactionDao;
import com.shadow.OptiBankBuddy.dto.TransactionDto;
import com.shadow.OptiBankBuddy.entity.Transaction;
@Service
public class TransactionServiceImpl implements TransactionService {
	
	@Autowired
	TransactionDao transactionDao;
	
	@Override
	public void saveTransaction(TransactionDto transactionDto) {
		// TODO Auto-generated method stub
		Transaction transaction = new Transaction();
		transaction.setTransactionType(transactionDto.getTransactionType());
		transaction.setAmount(transactionDto.getAmount());
		transaction.setAccountNumber(transactionDto.getAccountNumber());
		transaction.setStatus("Success");
		transactionDao.save(transaction);
		
		System.out.println("Transaction Saved SuccessFully !!");
		
		
	}

}
