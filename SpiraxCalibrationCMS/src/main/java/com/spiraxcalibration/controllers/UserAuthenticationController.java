package com.spiraxcalibration.controllers;

import java.text.MessageFormat;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spiraxcalibration.WebConfig.AppsPropertyFile;
import com.spiraxcalibration.models.UserData;
import com.spiraxcalibration.services.EmailIService;
import com.spiraxcalibration.services.LOVIService;
import com.spiraxcalibration.services.LookupIService;
import com.spiraxcalibration.services.PrIService;
import com.spiraxcalibration.services.UserAuthenticationIService;
import com.spiraxcalibration.services.UserIService;
import com.spiraxcalibration.utils.EmailUtils;

@Controller
public class UserAuthenticationController {
	Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	AppsPropertyFile dBQueryPropertyFile = new AppsPropertyFile();

	@Autowired
	PrIService prIService;

	@Autowired
	LOVIService  lOVIService;

	@Autowired
	LookupIService lookupIService;

	@Autowired
	UserAuthenticationIService userAuthenticationIService;

	@Autowired
	EmailIService  emailIService;

	@Autowired
	UserIService userIService;

	@Autowired
	EmailUtils emailUtils;

	// HOME PAGE..
	@RequestMapping(value="/firstpage")
	public ModelAndView getFirstPage(){
		logger.info("INSIDE UserAuthenticationController START METHOD getFirstPage");
		ModelAndView model = new ModelAndView("initialDashboard/intialPage");

		String adminRole = dBQueryPropertyFile.getUserDetail("spring.role.admin");
		String userRole = dBQueryPropertyFile.getUserDetail("spring.role.user");
		String managerRole = dBQueryPropertyFile.getUserDetail("spring.role.manager");
		String engineerRole = dBQueryPropertyFile.getUserDetail("spring.role.engineer");
		String manufacture_associatesRole = dBQueryPropertyFile.getUserDetail("spring.role.manufacturing_associates");
		String approver1Role = dBQueryPropertyFile.getUserDetail("spring.role.approver1");
		String approver2Role = dBQueryPropertyFile.getUserDetail("spring.role.approver2");
		String procurement_engineerRole = dBQueryPropertyFile.getUserDetail("spring.role.procurement_engineer");

		model.addObject("adminRole", adminRole);
		model.addObject("userRole", userRole);
		model.addObject("managerRole", managerRole);
		model.addObject("engineerRole", engineerRole);
		model.addObject("manufacture_associatesRole", manufacture_associatesRole);
		model.addObject("approver1Role", approver1Role);
		model.addObject("approver2Role", approver2Role);
		model.addObject("procurement_engineerRole", procurement_engineerRole);

		logger.info("INSIDE UserAuthenticationController END METHOD getFirstPage");
		return model;
	}


	@RequestMapping(value="/users",method=RequestMethod.GET)
	public ModelAndView userGetUserDetails(){
		logger.info("INSIDE UserAuthenticationController START METHOD userGetUserDetails ::");
		ModelAndView  model = new ModelAndView("/users/usersDetails");
		model.addObject("productList",prIService.prGetProductDetails());
		model.addObject("makeList",lookupIService.getMakeFromProduct());
		model.addObject("locationList",lookupIService.getLocationFromProduct());
		model.addObject("supplierList",lookupIService.getSupplierName());
		logger.info("INSIDE UserAuthenticationController END METHOD userGetUserDetails ::");
		return model;
	}


	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, String error, String logout) {
		logger.info("INSIDE UserAuthenticationController START METHOD login ::");
		if (error != null)
			model.addAttribute("errorMsg", "Your username and password are invalid.");
		if (logout != null)
			model.addAttribute("msg", "You have been logged out successfully.");
		logger.info("INSIDE UserAuthenticationController END METHOD login ::");
		return "login/login2";
	}

	@RequestMapping(value="/forgotpassword")
	public ModelAndView userForgetPassword(){
		logger.info("INSIDE UserAuthenticationController START METHOD userForgetPassword ::");
		ModelAndView model = new ModelAndView("login/forgotpassword");
		logger.info("INSIDE UserAuthenticationController END METHOD userForgetPassword ::");
		return model;
	}

	@RequestMapping(value="/passwordform", method=RequestMethod.POST )
	public ModelAndView processForgotPasswordForm(HttpServletRequest request , HttpServletResponse response, RedirectAttributes redir){
		logger.info("INSIDE UserAuthenticationController START METHOD userForgotPasswordform ::");
		ModelAndView model = new ModelAndView("redirect:/login");
		ModelAndView model2 = new ModelAndView("redirect:/forgotpassword");

		String userName = request.getParameter("username");
		UserData  userData = userAuthenticationIService.findUserByEmail(userName);
		if (userData == null) {
			redir.addFlashAttribute("errorMessage", "We didn't find an account for that e-mail address.");
			return model2;
		} else {
			userData.setSetResetToken(UUID.randomUUID().toString());
			int num = userAuthenticationIService.updateResetToken(userData);
			if(num > 0){
				String baseUrl = dBQueryPropertyFile.getURLForKey("baseURL");
				String message = dBQueryPropertyFile.getMessageDetail("resetBody.message");
				String message1 = org.apache.commons.lang.StringEscapeUtils.unescapeJava(message);

				String formattedMessage = MessageFormat.format(message1, baseUrl, userData.getSetResetToken());
				String subjectForMail = dBQueryPropertyFile.getMessageDetail("resetSubject.message");

//				String messageBody = "To reset your password, click the link below:\n" + baseUrl
//						+ "/reset?token=" + userData.getSetResetToken();
			//	String subjectForMail = "Password Reset Request";
				
				try {
					emailUtils.sendEmailWithAttachment(userData.getUseUserName(),null, subjectForMail, formattedMessage);
				} catch (MessagingException e) {
					e.printStackTrace();
				}
				//	sendMailTo(userData.getUseUserName(), messageBody, subjectForMail);
				redir.addFlashAttribute("successmsg", "A password reset link has been sent to " + userData.getUseUserName());
				return model;
			}
		}
		logger.info("INSIDE UserAuthenticationController END METHOD userForgotPasswordform ::");
		return model;
	}

	// Display form to reset password
	@RequestMapping(value = "/reset", method = RequestMethod.GET)
	public ModelAndView displayResetPasswordPage(ModelAndView modelAndView, @RequestParam("token") String token) {
		logger.info("INSIDE UserAuthenticationController START METHOD displayResetPasswordPage ::");
		ModelAndView model = new ModelAndView("login/resetPassword");
		UserData  userData = userAuthenticationIService.findUserByResetToken(token);
		if (userData != null) {
			// Token found in DB...
			model.addObject("resetToken", token);
		}if(userData == null){ 
			// Token not found in DB...
			ModelAndView model2 = new ModelAndView("login/forgotpassword");
			model2.addObject("errorMessage", "Oops!  This link has been expired!!");
			return model2;
		}
		logger.info("INSIDE UserAuthenticationController END METHOD displayResetPasswordPage ::");
		return model;
	}

	// Display form to reset password
	@RequestMapping(value = "/reset_password", method = RequestMethod.POST)
	public ModelAndView displayResetPasswordPage(HttpServletRequest request , HttpServletResponse response, RedirectAttributes redir) {
		logger.info("INSIDE UserAuthenticationController START METHOD displayResetPasswordPage ::");
		ModelAndView model = new ModelAndView("redirect:/login");
		String token = request.getParameter("token");
		String confirmedPassword = request.getParameter("confirmedpassword");
		UserData  userData = userAuthenticationIService.findUserByResetToken(token);
		if(userData != null){
			userData.setUserConfirmPassword(confirmedPassword);
			userData.setSetResetToken(null);
			int num = userAuthenticationIService.updatePasswordUser(userData);
			if(num > 0 ){
				redir.addFlashAttribute("successmsg", "Password has been reset successfully!!");
				return model;
			}
		}
		logger.info("INSIDE UserAuthenticationController END METHOD displayResetPasswordPage ::");
		return model;
	}

	//	private void sendMailTo(String approverMailId, String messageBody, String subjectForMail) {
	//		SimpleMailMessage message = new SimpleMailMessage();
	//		System.out.println(approverMailId+"::"+messageBody+"::"+subjectForMail);
	//		message.setTo(approverMailId.trim());
	//		message.setSubject(subjectForMail);
	//		message.setText(messageBody);
	//		emailIService.sendEmail(message);
	//	}

	@RequestMapping(value="/403")
	public ModelAndView errorPage(){
		logger.info("INSIDE UserAuthenticationController START METHOD errorPage ::");
		logger.info("INSIDE UserAuthenticationController END METHOD errorPage ::");
		return new ModelAndView("login/403");
	}
}
