package com.shadow.OptiBankBuddy.controller;

import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.DocumentException;
import com.shadow.OptiBankBuddy.entity.Transaction;
import com.shadow.OptiBankBuddy.service.BankStatement;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
	
	private BankStatement bankStatement;
	
	
	public TransactionController(BankStatement bankStatement) {
		this.bankStatement = bankStatement;
	}
	
	
	@Operation(summary = "Get Bank Statement", description = "Generates bank statement for the specified account and date range.")
	@GetMapping
	public List<Transaction> getBankStatement(@RequestParam String accountNumber, @RequestParam String fromDate, @RequestParam String toDate) throws FileNotFoundException, DocumentException {
		return bankStatement.generateBankStatement(accountNumber, fromDate, toDate);
	}
 
	
	
	
}
