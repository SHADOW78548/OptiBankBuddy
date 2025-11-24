package com.shadow.OptiBankBuddy.service;







import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.shadow.OptiBankBuddy.dao.TransactionDao;
import com.shadow.OptiBankBuddy.dao.UserDao;
import com.shadow.OptiBankBuddy.dto.EmailDetails;
import com.shadow.OptiBankBuddy.entity.Transaction;
import com.shadow.OptiBankBuddy.entity.UserInfo;




@Component
public class BankStatement {
	@Autowired
	private UserDao userDao;
	@Autowired 
	private TransactionDao transactionDao;
	@Autowired
	private EmailService emailService;
	
	
	private static final String FILE_PATH="C:\\Users\\2403435\\OneDrive - Cognizant\\Desktop\\Bluebolt\\Bank Statement\\BankStatement.pdf";
	
	public BankStatement(TransactionDao transactionDao) {
		this.transactionDao = transactionDao;
	}


	public List<Transaction> generateBankStatement(String accountNumber,String fromDate, String toDate) throws DocumentException, FileNotFoundException {
		// TODO Auto-generated method stub
		
		LocalDate start= LocalDate.parse(fromDate,DateTimeFormatter.ISO_DATE);
		LocalDate end= LocalDate.parse(toDate,DateTimeFormatter.ISO_DATE);
		
		List<Transaction> transactions = transactionDao.findAll()
				.stream()
                .filter(t -> t.getAccountNumber().equals(accountNumber))
                .filter(e->e.getTransactionId()!=null)
                .filter(e->e.getCreatedDate().isEqual(start))
                .filter(e->e.getUpdatedDate().isEqual(end))
                .toList();
		
		
		
		UserInfo user = userDao.findByAccountNumber(accountNumber);
		String customerName = user.getFirstName() + " " + user.getLastName();
		String address=user.getAddress();
		
		
		
		// Define document size and initialize
		Rectangle pageSize = PageSize.A4;
		Document document = new Document(pageSize);

		OutputStream outputStream = new FileOutputStream(FILE_PATH);
		PdfWriter.getInstance(document, outputStream);
		document.open();

		// -------------------- Bank Header (One Line, Styled) --------------------
		PdfPTable bankHeaderTable = new PdfPTable(2);
		bankHeaderTable.setWidthPercentage(100);
		bankHeaderTable.setWidths(new float[]{2f, 3f}); // Adjust column ratio

		Font bankNameFont = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD, BaseColor.WHITE);
		Font bankAddressFont = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.WHITE);

		PdfPCell bankNameCell = new PdfPCell(new Phrase("OptiBankBuddy", bankNameFont));
		PdfPCell bankAddressCell = new PdfPCell(new Phrase("Kolkata, West Bengal, India", bankAddressFont));

		for (PdfPCell cell : new PdfPCell[]{bankNameCell, bankAddressCell}) {
		    cell.setBackgroundColor(BaseColor.GRAY);
		    cell.setBorder(Rectangle.NO_BORDER);
		    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		    cell.setPadding(8f);
		}

		bankNameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		bankAddressCell.setHorizontalAlignment(Element.ALIGN_RIGHT);

		bankHeaderTable.addCell(bankNameCell);
		bankHeaderTable.addCell(bankAddressCell);

		// -------------------- Statement Header Section --------------------
		PdfPTable statementHeaderTable = new PdfPTable(2);
		statementHeaderTable.setWidthPercentage(100);
		statementHeaderTable.setSpacingBefore(10f);
		statementHeaderTable.setSpacingAfter(10f);

		Font labelFont = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
		Font valueFont = new Font(Font.FontFamily.HELVETICA, 10);

		PdfPCell startDateCell = new PdfPCell(new Phrase("Start Date : " + fromDate, valueFont));
		PdfPCell statementTitleCell = new PdfPCell(new Phrase("STATEMENT OF ACCOUNT", labelFont));
		PdfPCell endDateCell = new PdfPCell(new Phrase("End Date : " + toDate, valueFont));
		PdfPCell spacerCell = new PdfPCell(new Phrase(""));

		for (PdfPCell cell : new PdfPCell[]{startDateCell, statementTitleCell, endDateCell, spacerCell}) {
		    cell.setBorder(Rectangle.NO_BORDER);
		    cell.setPadding(5f);
		    statementHeaderTable.addCell(cell);
		}

		// -------------------- Customer Info Section --------------------
		PdfPCell customerNameCell = new PdfPCell(new Phrase("Customer Name : " + customerName, valueFont));
		PdfPCell accountNumberCell = new PdfPCell(new Phrase("Account Number : " + accountNumber, valueFont));
		PdfPCell addressCell = new PdfPCell(new Phrase("Address : " + address, valueFont));

		for (PdfPCell cell : new PdfPCell[]{customerNameCell, accountNumberCell, addressCell}) {
		    cell.setBorder(Rectangle.NO_BORDER);
		    cell.setPadding(5f);
		    statementHeaderTable.addCell(cell);
		}

		// -------------------- Transaction Table Section --------------------
		PdfPTable transactionTable = new PdfPTable(4);
		transactionTable.setWidthPercentage(100);
		transactionTable.setSpacingBefore(10f);

		String[] headers = {"Date", "Type", "Amount", "Status"};
		BaseColor headerColor = new BaseColor(224, 224, 224);

		for (String header : headers) {
		    PdfPCell headerCell = new PdfPCell(new Phrase(header, labelFont));
		    headerCell.setBackgroundColor(headerColor);
		    headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		    headerCell.setPadding(8f);
		    headerCell.setBorderWidthBottom(1f);
		    transactionTable.addCell(headerCell);
		}

		transactions.forEach(t -> {
		    transactionTable.addCell(new PdfPCell(new Phrase(t.getCreatedDate().toString(), valueFont)));
		    transactionTable.addCell(new PdfPCell(new Phrase(t.getTransactionType(), valueFont)));
		    transactionTable.addCell(new PdfPCell(new Phrase(t.getAmount().toString(), valueFont)));
		    transactionTable.addCell(new PdfPCell(new Phrase(t.getStatus(), valueFont)));
		});

		// -------------------- Final Assembly --------------------
		document.add(bankHeaderTable);
		document.add(statementHeaderTable);
		document.add(transactionTable);

		document.close();


		
		EmailDetails emailDetails = new EmailDetails();
		emailDetails.setRecipient(user.getEmail());
		emailDetails.setSubject("Your Bank Statement from OptiBankBuddy");
		emailDetails.setMsgBody("Dear " + customerName + ",\n\nPlease find attached your bank statement from " + fromDate + " to " + toDate + ".\n\nBest regards,\nOptiBankBuddy");
		emailDetails.setAttachment(FILE_PATH);
		emailService.sendEmailWithAttachment(emailDetails);
		
		
		return transactions;
	}
			

}

