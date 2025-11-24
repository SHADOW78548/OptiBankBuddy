package com.shadow.OptiBankBuddy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.shadow.OptiBankBuddy.dto.EmailDetails;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender javaMailSender;
	
	
	@Value("${spring.mail.username}")
	private String senderMail;
	
	@Override
	public void sendEmail(EmailDetails details) {
		// TODO Auto-generated method stub
		try {
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			
			mailMessage.setFrom(senderMail);
			mailMessage.setTo(details.getRecipient());
			mailMessage.setSubject(details.getSubject());
			mailMessage.setText(details.getMsgBody());
			javaMailSender.send(mailMessage);
			System.out.println("Mail Sent Successfully...");
				
		} catch (Exception e) {
			throw new RuntimeException("Error while sending mail to " + details.getRecipient(), e);
		}

	}

	@Override
	public void sendEmailWithAttachment(EmailDetails details) {
		// TODO Auto-generated method stub
		
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper;
		try {
			 mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			mimeMessageHelper.setFrom(senderMail);
			mimeMessageHelper.setTo(details.getRecipient());
			mimeMessageHelper.setSubject(details.getSubject());
			mimeMessageHelper.setText(details.getMsgBody());

			FileSystemResource file = new FileSystemResource(details.getAttachment());
			mimeMessageHelper.addAttachment(file.getFilename(), file);

			javaMailSender.send(mimeMessage);
			System.out.println("Mail with Attachment Sent Successfully...");
		} catch (MessagingException e) {
			throw new RuntimeException("Error while sending mail to " + details.getRecipient(), e);
		}
	}

}
