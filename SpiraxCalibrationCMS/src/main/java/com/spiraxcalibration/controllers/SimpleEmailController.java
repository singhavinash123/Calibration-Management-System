package com.spiraxcalibration.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SimpleEmailController {
    
	Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	public JavaMailSender  javaMailSender;
	
	@RequestMapping(value="/sendmail" , method = RequestMethod.POST)
	public ModelAndView sendEmail(HttpServletRequest request, HttpServletResponse response,RedirectAttributes redir){
		SimpleMailMessage message = new SimpleMailMessage();
		ModelAndView  model = new ModelAndView("redirect:/mail");

		String toMailId = request.getParameter("email");
		String messageBody = request.getParameter("message");

		message.setTo(toMailId.trim());
		message.setSubject("Testing");
		message.setText(messageBody);
		javaMailSender.send(message);
		
		redir.addFlashAttribute("msg","Mail Sent Successfuly!!");
		return model;
	}
	
	@RequestMapping(value="/mail",method=RequestMethod.GET)
	public ModelAndView mail(){
		logger.info("INSIDE PrController START METHOD mail ::");
		ModelAndView  model = new ModelAndView("mailSender/mailsend");
		logger.info("INSIDE PrController END METHOD mail ::");
		return model;
	}
	
}
