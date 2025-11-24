package com.shadow.OptiBankBuddy.service;

import com.shadow.OptiBankBuddy.dto.EmailDetails;

public interface EmailService {
	
	void sendEmail(EmailDetails details);
	void sendEmailWithAttachment(EmailDetails details);

}
