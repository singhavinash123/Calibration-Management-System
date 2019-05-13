package com.spiraxcalibration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.spiraxcalibration.controllers.PRPController;

public class test {
	static final String FROM = "services@transformedge.com";
	static final String FROMNAME = "Avinash Singh";

	// Replace recipient@example.com with a "To" address. If your account 
	// is still in the sandbox, this address must be verified.
	static final String TO = "avinash.singh@transformedge.com";

	// Replace smtp_username with your Amazon SES SMTP user name.
	static final String SMTP_USERNAME = "services@transformedge.com";
	static final String SMTP_PASSWORD = "Teserv321";
	static final String HOST = "smtp.gmail.com";
	static final int PORT = 587;

	static final String SUBJECT = "Amazon SES test (SMTP interface accessed using Java)";

	static final String BODY = String.join(
			System.getProperty("line.separator"),
			"<h1>Amazon SES SMTP Email Test</h1>",
			"<p>This email was sent with Amazon SES using the ", 
			"<a href='https://github.com/javaee/javamail'>Javamail Package</a>",
			" for <a href='https://www.java.com'>Java</a>."
			);

	public static void main(String[] args) throws Exception {

		// Create a Properties object to contain connection configuration information.
		Properties props = System.getProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.port", PORT); 
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");

		// Create a Session object to represent a mail session with the specified properties. 
		Session session = Session.getDefaultInstance(props);

		// Create a message with the specified information. 
		MimeMessage msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(FROM,FROMNAME));
		msg.setRecipient(Message.RecipientType.TO, new InternetAddress(TO));
		msg.setSubject(SUBJECT);
		msg.setContent(BODY,"text/html");

		Transport transport = session.getTransport();

		// Send the message.
		try
		{
			System.out.println("Sending...");
			transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);
			transport.sendMessage(msg, msg.getAllRecipients());
			System.out.println("Email sent!");
		}
		catch (Exception ex) {
			System.out.println("The email was not sent.");
			System.out.println("Error message: " + ex.getMessage());
		}
		finally
		{
			// Close and terminate the connection.
			transport.close();
		}
	} 
}
