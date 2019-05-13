package com.spiraxcalibration.services;

import org.springframework.mail.SimpleMailMessage;

public interface EmailIService {
	public void sendEmail(SimpleMailMessage email);
}
