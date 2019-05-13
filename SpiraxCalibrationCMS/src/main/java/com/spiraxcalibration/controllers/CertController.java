package com.spiraxcalibration.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spiraxcalibration.WebConfig.AppsPropertyFile;
import com.spiraxcalibration.models.CalibMainData;
import com.spiraxcalibration.models.CertData;
import com.spiraxcalibration.services.CalibIService;
import com.spiraxcalibration.services.CertUploadDownloadIService;
import com.spiraxcalibration.services.LOVIService;

@Controller
public class CertController {
	Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	CalibIService calibIService;

	@Autowired
	LOVIService lOVService;

	@Autowired
	CertUploadDownloadIService  certUploadDownloadIService;

	AppsPropertyFile dBQueryPropertyFile = new AppsPropertyFile();

	@RequestMapping(value = "/uploadOrDownloadcrtificate/{calibId}" , method = RequestMethod.GET)
	public ModelAndView certUploadCertificate(@PathVariable("calibId") Integer calibId){
		logger.info("INSIDE CertController START METHOD certUploadCertificate");

		ModelAndView model = new ModelAndView("downloaduploadcertificate/uploadWarrantyCert");

		CalibMainData calibData =  calibIService.calibGetCalibrationById(calibId);
		List<CertData> certDataList =  certUploadDownloadIService.certGetCertificateDetails(calibId);
		List<String> certificateOptionList  = lOVService.getCertificateOptionName();

		model.addObject("calibData",calibData);
		model.addObject("certData",certDataList);
		model.addObject("certificateLookup",certificateOptionList);
		model.addObject("calibCertInfo","Calibration Certificate");
		logger.info("INSIDE CertController END METHOD certUploadCertificate");
		return model;
	}
	//
	//	@RequestMapping(value = "/uploadOrDownloadcrtificate/{calibId}" , method = RequestMethod.GET)
	//	public ModelAndView certUploadCertificate(@PathVariable("calibId") Integer calibId){
	//		logger.info("INSIDE CertController START METHOD certUploadCertificate");
	//		
	//		ModelAndView model = new ModelAndView("downloaduploadcertificate/uploadWarrantyCert");
	//		
	//		CalibData calibData =  calibIService.calibGetCalibrationById(calibId);
	//
	//		System.out.println("calibration Date :::::"+calibData.getCalibCalibrationDate());
	//		List<CertData> certDataList =  certUploadDownloadIService.certGetCertificateDetails(calibId);
	//		for(CertData certData : certDataList){
	//			certData.setCertCalibrationDateByCalibId(calibData.getCalibCalibrationDate());
	//		}
	//		
	//		model.addObject("calibData",calibData);
	//		model.addObject("certData",certDataList);
	//		model.addObject("calibCertInfo","Calibration Certificate");
	//		logger.info("INSIDE CertController END METHOD certUploadCertificate");
	//		return model;
	//	}

	@RequestMapping(value = "/uploadCertificate", method=RequestMethod.POST)
	public ModelAndView certFileUploadToDB(HttpServletRequest request, HttpServletResponse response , RedirectAttributes redir){
		logger.info("INSIDE CertController START METHOD certFileUploadToDB");
		Part filePart = null;
		String calibId = request.getParameter("calibid");
		String dueDate = request.getParameter("duedate");
		String identityNum = request.getParameter("identitynum");
		String certName = request.getParameter("certname");
		String certificateOptionName = request.getParameter("certificateoption");
		ModelAndView model = new ModelAndView("redirect:/uploadOrDownloadcrtificate"+"/"+calibId);
		try {
			filePart = request.getPart("certificate");
		} catch (IOException | ServletException e ) {
			e.printStackTrace();
		}
		int num = certUploadDownloadIService.certUploadCertificate(calibId,identityNum,dueDate,certName,certificateOptionName,filePart);
		if(num > 0){
			redir.addFlashAttribute("msg","Calibration Certificate for "+identityNum+" Uploaded Successfully!!");
			return model;
		}else if(num == 0){
			redir.addFlashAttribute("onlyPdf","Only PDF File Can Be Upload!!");
			return model;
		}
		return model;
	}

	//	@RequestMapping(value = "/uploadCertificate", method=RequestMethod.POST)
	//	public ModelAndView certFileUploadToDB(HttpServletRequest request, HttpServletResponse response , RedirectAttributes redir){
	//		logger.info("INSIDE CertController START METHOD certFileUploadToDB");
	//		Part filePart = null;
	//		String calibId = request.getParameter("calibid");
	//		ModelAndView model = new ModelAndView("redirect:/uploadOrDownloadcrtificate"+"/"+calibId);
	//		String calibProdId = request.getParameter("prodid");
	//		String serialNum = request.getParameter("serialnum");
	//		String identityNum = request.getParameter("identitynum");
	//		System.out.println(calibId+":"+calibProdId+":"+serialNum+":"+identityNum);
	//		try {
	//			filePart = request.getPart("certificate");
	//		} catch (IOException | ServletException e ) {
	//			e.printStackTrace();
	//		}
	//		int num = certUploadDownloadIService.certUploadCertificate(calibId,calibProdId,identityNum,serialNum,filePart);
	//		if(num > 0){
	//			redir.addFlashAttribute("msg","Calibration Certificate for "+identityNum+" Uploaded Successfully!!");
	//			return model;
	//		}else if(num == 0){
	//			redir.addFlashAttribute("onlyPdf","Only PDF File Can Be Upload!!");
	//			return model;
	//		}
	//		return model;
	//	}


	@RequestMapping(value = "/downloadCerticate/{warrantyId}")
	public void certDownloadByCertId(@PathVariable("warrantyId") Integer warrantyId,HttpServletRequest request,
			HttpServletResponse response){
		logger.info("INSIDE CertController START METHOD certDownloadByCertId");
		final int BUFFER_SIZE = Integer.parseInt(dBQueryPropertyFile.getmeasurment("BUFFER_SIZE"));

		CertData certData = certUploadDownloadIService.certDownloadCertificate(warrantyId);
		Blob blob = certData.getCertCertificatePdf();
		String fileName = certData.getCertCertificateName();
		InputStream inputStream = null;
		@SuppressWarnings("unused")
		int fileLength = 0;
		try {
			inputStream = blob.getBinaryStream();
			fileLength = inputStream.available();
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
//		String headerKey = "Content-Disposition";
//		String headerValue = String.format("attachment; filename=\"%s\"", fileName);
//		response.setContentType("APPLICATION/OCTET-STREAM");
//		response.setContentLength(fileLength);
//		response.setHeader(headerKey, headerValue);

		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "inline; filename="+fileName+"");


		OutputStream outStream = null;
		byte[] buffer = new byte[BUFFER_SIZE];
		int bytesRead = -1;
		try {
			outStream = response.getOutputStream();
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, bytesRead);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				inputStream.close();
				outStream.close();   
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		logger.info("INSIDE CertController END METHOD certDownloadByCertId");
	}

//	@RequestMapping(value = "/EmailAttachment",method=RequestMethod.POST)
//	public ModelAndView certEmailAttachment(HttpServletRequest request , HttpServletResponse response) throws IOException, ServletException{
//		logger.info("INSIDE CertController START METHOD certEmailAttachment");
//		ModelAndView model = new ModelAndView();
//		
//		System.out.println("request.getPart()::::"+request.getPart("attachedFile"));
//		Part filePart = request.getPart("attachedFile");
//		System.out.println(filePart);
//
//		// save the selected PDF into the database....
//		int num = certUploadDownloadIService.certUploadCertificate(filePart);
//		if(num > 0){
//			
//			System.out.println("uploaded successfully:::");
//			final int BUFFER_SIZE = Integer.parseInt(dBQueryPropertyFile.getmeasurment("BUFFER_SIZE"));
//
//			PRPData pRPData = certUploadDownloadIService.downloadPDFCertificateByPRID(35);
//		
//			Blob blob = pRPData.getpRpPdfData();
//			String fileName = pRPData.getpRpPdfName();
//			
//			System.out.println("blob:::"+blob);
//			System.out.println("fileName:::"+fileName);
//
//			
//			InputStream inputStream = null;
//			int fileLength = 0;
//			try {
//				inputStream = blob.getBinaryStream();
//				fileLength = inputStream.available();
//			} catch (SQLException | IOException e) {
//				e.printStackTrace();
//			}
//			System.out.println("certificate fileLength = " + fileLength);
//			response.setContentType("APPLICATION/OCTET-STREAM");
//			response.setContentLength(fileLength);
//			String headerKey = "Content-Disposition";
//			String headerValue = String.format("attachment; filename=\"%s\"", fileName);
//			response.setHeader(headerKey, headerValue);
//
//			OutputStream outStream = null;
//			byte[] buffer = new byte[BUFFER_SIZE];
//			int bytesRead = -1;
//			try {
//				outStream = response.getOutputStream();
//				while ((bytesRead = inputStream.read(buffer)) != -1) {
//					outStream.write(buffer, 0, bytesRead);
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}finally{
//				try {
//					inputStream.close();
//					outStream.close();   
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//			
//		}
//
//		//authentication info
//		final String username = "services@transformedge.com";
//		final String password = "Teserv321";
//		String fromEmail = "services@transformedge.com";
//		String toEmail = "avinash.singh@transformedge.com";
//
//		Properties properties = new Properties();
//		properties.put("mail.smtp.auth", "true");
//		properties.put("mail.smtp.starttls.enable", "true");
//		properties.put("mail.smtp.host", "smtp.gmail.com");
//		properties.put("mail.smtp.port", "587");
//
//		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
//			protected PasswordAuthentication getPasswordAuthentication() {
//				return new PasswordAuthentication(username,password);
//			}
//		});
//		//Start our mail message
//		MimeMessage msg = new MimeMessage(session);
//		try {
//			//msg.setFrom(new InternetAddress(fromEmail));
//			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
//			msg.setSubject("Subject Line");
//
//			Multipart emailContent = new MimeMultipart();
//
//			//Text body part
//			MimeBodyPart textBodyPart = new MimeBodyPart();
//			textBodyPart.setText("My multipart text");
//
//			//Attachment body part.
//			MimeBodyPart pdfAttachment = new MimeBodyPart();
//			pdfAttachment.attachFile("C://Users//ADMIN//Downloads//Digital_Signatures.pdf");
//
//			//Attach body parts
//			emailContent.addBodyPart(textBodyPart);
//			emailContent.addBodyPart(pdfAttachment);
//
//			//Attach multipart to message
//			msg.setContent(emailContent);
//
//			Transport.send(msg);
//			System.out.println("Sent message");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return model;
//	}

	
//	private String extraxtFileName(Part part) {
//		String contentDisp = part.getHeader("content-disposition");
//		String[] items = contentDisp.split(";");
//		for(String s : items){
//			if(s.trim().startsWith("filename")){
//				return s.substring(s.indexOf("=")+2, s.length()-1);
//			}
//		}
//		return "";
//	}

	@RequestMapping(value = "/deleteCerticate/{warrantyId}")
	public ModelAndView certDeteletCertificate(@PathVariable("warrantyId") Integer warrantyId,RedirectAttributes redir){
		logger.info("INSIDE CertController START METHOD certDeteletCertificate");
		ModelAndView model = new ModelAndView();
		CertData  certData =  certUploadDownloadIService.calibGetCalibIdByCertId(warrantyId);
		if(certData != null){
			model.setViewName("redirect:/uploadOrDownloadcrtificate"+"/"+certData.getCertCalibId());
		}
		int num = certUploadDownloadIService.certDeleteCertificate(warrantyId);
		if(num > 0){
			redir.addFlashAttribute("msg","File Deleted successfuly!!");
		}
		logger.info("INSIDE CertController END METHOD certDeteletCertificate");
		return model;
	}
	
	@RequestMapping(value="/master_view_certificate")
	public ModelAndView getMasterCertView(){
		logger.info("INSIDE CalibMainController START METHOD getMasterCertView");
		ModelAndView model = new ModelAndView("downloaduploadcertificate/masterCertificateView");
		logger.info("INSIDE CalibMainController END METHOD getMasterCertView");
		return model;
	}
	
}
