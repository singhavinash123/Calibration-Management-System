package com.spiraxcalibration.utils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.spiraxcalibration.servicesIMPL.UserServiceIMPL;

@Component
public class EmailUtils {

	Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Value("${spring.mail.username}")
	private  String serviceUserName;

	@Value("${spring.mail.password}")
	private  String password;

	@Value("${spring.mail.port}")
	private  String port;

	@Value("${spring.mail.properties.mail.smtp.starttls.enable}")
	private  String starttls;

	@Value("${spring.mail.host}")
	private  String host;

	@Autowired
	JdbcTemplate  jdbcTemplate1;

	@Autowired
	UserServiceIMPL userServiceIMPL;

	public void sendEmailWithAttachment(List<String> mailsIds,String sendFromFullName, String subjectForMail, String messageBody, List<File> attachedFiles)
			throws AddressException, MessagingException {
		Properties properties = getProperties();
		//	UserData userData = userServiceIMPL.getServiceMailIdAndPassword();

		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(serviceUserName, password);
			}
		};

		Session session = Session.getInstance(properties, auth);
		// creates a new e-mail message
		Message msg = new MimeMessage(session);

		//msg.setFrom(new InternetAddress(userName));

		InternetAddress[] recipientAddress = new InternetAddress[mailsIds.size()];
		int counter = 0;
		for (String recipient : mailsIds) {
			recipientAddress[counter] = new InternetAddress(recipient.trim());
			counter++;
		}

		try {
			msg.setFrom(new InternetAddress(serviceUserName, sendFromFullName));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		msg.setRecipients(Message.RecipientType.TO, recipientAddress);
		msg.setSubject(subjectForMail);
		msg.setSentDate(new Date());

		// creates message part
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent(messageBody, "text/html");

		// creates multi-part
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);

		// adds attachments
		if (attachedFiles != null && attachedFiles.size() > 0) {
			for (File aFile : attachedFiles) {
				MimeBodyPart attachPart = new MimeBodyPart();

				try {
					attachPart.attachFile(aFile);
				} catch (IOException ex) {
					ex.printStackTrace();
				}

				multipart.addBodyPart(attachPart);
			}
		}

		// sets the multi-part as e-mail's content
		msg.setContent(multipart);

		// sends the e-mail
		Transport.send(msg);
	}

	public void sendEmailWithAttachment(String getpRpApprover2Name, String sendFromFullName, String subjectForMail, String messageBody) throws MessagingException {

		Properties properties = getProperties();

		//	UserData userData = userServiceIMPL.getServiceMailIdAndPassword();
		//		String  getServiceEmailId = null;
		//		String  getServicePassword = null;
		//		
		//		if(userData != null){
		//			getServiceEmailId = userData.getUserEmailAddress();
		//			getServicePassword = userData.getUserConfirmPassword();
		//			
		//			System.out.println("getEmailId ::"+getServiceEmailId);
		//			System.out.println("getPassword ::"+getServicePassword);
		//
		//		}
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(serviceUserName , password);
			}
		};

		Session session = Session.getInstance(properties, auth);
		Message msg = new MimeMessage(session);

		InternetAddress[] toAddresses = {
				new InternetAddress(getpRpApprover2Name) 
		};

		if(sendFromFullName == null){
			sendFromFullName = "Calibration Alert";
		}
		try {
			msg.setFrom(new InternetAddress(serviceUserName, sendFromFullName));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}		
	
		msg.setRecipients(Message.RecipientType.TO, toAddresses);
		msg.setSubject(subjectForMail);
		msg.setSentDate(new Date());

		// creates message part
		MimeBodyPart messageBodyPart = new MimeBodyPart();

		messageBodyPart.setText(messageBody, "UTF-8", "html");

		//	messageBodyPart.setContent(messageBody, "text/html ; charset=utf-8");

		// creates multi-part
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);

		msg.setContent(multipart);
		Transport.send(msg);		
	}

	private Properties getProperties() {
		Properties properties = new Properties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", port);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", starttls);
		return properties;
	}
}
