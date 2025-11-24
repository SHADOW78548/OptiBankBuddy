package com.shadow.OptiBankBuddy.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class EmailDetails {
	@Schema(description = "Recipient's email address", example = "sujit@gmail.com")
	private String recipient;
	@Schema(description = "Subject of the email", example = "Account Statement")
	private String subject;
	@Schema(description = "Body of the email", example = "Please find attached your account statement.")
	private String msgBody;
	@Schema(description = "Path to the attachment file", example = "/path/to/statement.pdf")
	private String attachment;

	public EmailDetails() {
		// TODO Auto-generated constructor stub
	}
	
	public EmailDetails(String recipient, String subject, String msgBody, String attachment) {
		super();
		this.recipient = recipient;
		this.subject = subject;
		this.msgBody = msgBody;
		this.attachment = attachment;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMsgBody() {
		return msgBody;
	}

	public void setMsgBody(String msgBody) {
		this.msgBody = msgBody;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	@Override
	public String toString() {
		return "EmailDetails [recipient=" + recipient + ", subject=" + subject + ", msgBody=" + msgBody
				+ ", attachment=" + attachment + "]";
	}
	
	

}
