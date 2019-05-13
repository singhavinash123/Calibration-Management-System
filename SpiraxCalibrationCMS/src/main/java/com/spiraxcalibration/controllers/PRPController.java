package com.spiraxcalibration.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.MessageFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.spiraxcalibration.WebConfig.AppsPropertyFile;
import com.spiraxcalibration.models.CalibMainData;
import com.spiraxcalibration.models.PRPData;
import com.spiraxcalibration.models.PrData;
import com.spiraxcalibration.models.SUPData;
import com.spiraxcalibration.services.CertUploadDownloadIService;
import com.spiraxcalibration.services.EmailIService;
import com.spiraxcalibration.services.LOVIService;
import com.spiraxcalibration.services.PRPIService;
import com.spiraxcalibration.services.PrIService;
import com.spiraxcalibration.services.SUPIService;
import com.spiraxcalibration.services.UserIService;
import com.spiraxcalibration.utils.DateUtils;
import com.spiraxcalibration.utils.EmailUtils;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,   // 2MB
maxFileSize = 1024 * 1024 * 10,         // 10MB
maxRequestSize = 1024 * 1024 * 50)      // 
@Controller
public class PRPController {
	final String resoureseFilePath = "src/main/resources/templates/";

	Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	AppsPropertyFile dBQueryPropertyFile = new AppsPropertyFile();

	@Autowired
	PRPIService pRPIService;

	@Autowired
	PrIService  prIService;

	@Autowired
	SUPIService  sUPIService;

	@Autowired
	LOVIService  lOVIService;

	@Autowired
	EmailIService  emailIService;

	@Autowired
	CertUploadDownloadIService  certUploadDownloadIService;

	@Autowired
	UserIService userIService;

	@Autowired
	private EmailUtils emailUtils;


	@RequestMapping(value="/purchaseRequisitionList", method=RequestMethod.GET)
	public ModelAndView pRPgetCalibDetails(){
		logger.info("INSIDE PRPController START METHOD pRPgetCalibDetails ::");
		ModelAndView  model = new ModelAndView("prplist/prp_List");
		List<CalibMainData> calibMainData = pRPIService.pRPgetCalibDetails();
		List<SUPData> supSupplierList = sUPIService.supGetSupplierNameWithId();
		if(calibMainData != null){
			setColorFlag(calibMainData);
		}else{
			model.addObject("hiddenFlag", null);
		}
		model.addObject("supSupplierList", supSupplierList);
		model.addObject("calibMainData", calibMainData);
		model.addObject("RaisePR",null);
		logger.info("INSIDE PRPController END METHOD pRPgetCalibDetails ::");
		return model;
	}


	@RequestMapping(value="/viewApproverPr", method=RequestMethod.GET)
	public ModelAndView pRPgetPrDetails(){
		logger.info("INSIDE PRPController START METHOD pRPgetPrDetails ::");
		ModelAndView  model = new ModelAndView("prplist/viewApproverList");

		List<PRPData> pRpDataList = pRPIService.pRPgetprDetails();

		List<String> supplierList = pRPIService.getSupplierNameList();
		model.addObject("supplierList",supplierList);

		List<String> approver1statusList = pRPIService.getApprover1StatusList();
		model.addObject("approver1statusList",approver1statusList);

		List<String> approver2statusList = pRPIService.getApprover2StatusList();
		model.addObject("approver2statusList",approver2statusList);

		List<String> pRpIdList =  pRPIService.getPrpIDList();
		model.addObject("prpIdList",pRpIdList);

		model.addObject("pRpDataList",pRpDataList);
		logger.info("INSIDE PRPController END METHOD pRPgetPrDetails ::");
		return model;
	}

	@RequestMapping(value = "/search_pr")
	public ModelAndView pRpSearchProductByCondition(HttpServletRequest request, HttpServletResponse response){
		logger.info("INSIDE PRPController START METHOD pRpSearchProductByCondition");
		ModelAndView model = new ModelAndView("prplist/viewApproverList");

		String pRpNumber = request.getParameter("prpId");
		String supplierName = request.getParameter("supSupplier");
		String approver1Status = request.getParameter("approver1status");
		String approver2Status = request.getParameter("approver2status");

		if(pRpNumber != null && !pRpNumber.isEmpty() || supplierName != null && !supplierName.isEmpty() || approver1Status != null  && !approver1Status.isEmpty()|| approver2Status != null && !approver2Status.isEmpty()){
			model.addObject("pRpDataList" , pRPIService.pRpSearchByCondition(pRpNumber, supplierName, approver1Status, approver2Status));
		}else{
			model.addObject("pRpDataList", pRPIService.pRPgetprDetails());
		}

		List<String> supplierList = pRPIService.getSupplierNameList();
		model.addObject("supplierList",supplierList);

		List<String> approver1statusList = pRPIService.getApprover1StatusList();
		model.addObject("approver1statusList",approver1statusList);

		List<String> approver2statusList = pRPIService.getApprover2StatusList();
		model.addObject("approver2statusList",approver2statusList);

		List<String> pRpIdList =  pRPIService.getPrpIDList();
		model.addObject("prpIdList",pRpIdList);
		logger.info("INSIDE PrController END METHOD pRpSearchProductByCondition");
		return model;
	}	

	@RequestMapping(value="/save_Approval1", method=RequestMethod.POST)
	public ModelAndView pRpSaveApprover1Details(HttpServletRequest request , HttpServletResponse responce, Authentication authentication){
		logger.info("INSIDE PRPController START METHOD pRPgetPrDetails ::");
		ModelAndView  model = new ModelAndView("prplist/viewApproverList");
		PRPData  pRPData = new PRPData();

		String apporver1Name = request.getParameter("approver1name");
		String apporver1Status = request.getParameter("approver1status");
		String apporver1Comments = request.getParameter("approver1comment");
		String prpId = request.getParameter("prpId");
		String prNumber = request.getParameter("prNumber");

		pRPData.setpRpPRId(Integer.parseInt(prpId));
		pRPData.setpRpApprover1Name(apporver1Name);
		pRPData.setpRpApprover1Status(apporver1Status);
		pRPData.setpRpApprover1Comments(apporver1Comments);
		pRPData.setpRpPrNumber(prNumber);

		int num = pRPIService.saveApproverDetails(pRPData);
		if(num > 0){
			if(pRPData.getpRpApprover1Status().equalsIgnoreCase("Approved")){
				pRPData.setpRpApprover2Name(request.getParameter("aprover2mailid"));
				String fullName = userIService.getFullNameByUserId(pRPData.getpRpApprover2Name());
				String todayDate = DateUtils.convertToDDMMMYYYY(new Date());
				String subjectForMailMessage = dBQueryPropertyFile.getMessageDetail("approver2NotificationSubject.message");
				String approver2NotificationSubject = MessageFormat.format(subjectForMailMessage, pRPData.getpRpPrNumber() , todayDate);
				
				String baseUrl = dBQueryPropertyFile.getURLForKey("baseURL");
				String approvalApproverURL = dBQueryPropertyFile.getURLForKey("ApproverPage");
				String mainUrl = baseUrl+approvalApproverURL;
				String link = "\n<a href='"+mainUrl+pRPData.getpRpPRId()+"'>"+pRPData.getpRpPrNumber()+"</a>";

				String supplier1BodyMessage = dBQueryPropertyFile.getMessageDetail("approver2NotificationBodyMessage.message");
				String approver2NotificationBodyMessage = MessageFormat.format(supplier1BodyMessage, fullName, pRPData.getpRpPrNumber(),link);

				String loggedInEmailId = authentication.getName();
				String sendFromfullName = userIService.getFullNameByUserId(loggedInEmailId);
				
				if(pRPData.getpRpApprover2Name() != null){
					try {
						emailUtils.sendEmailWithAttachment(pRPData.getpRpApprover2Name(),sendFromfullName, approver2NotificationSubject, approver2NotificationBodyMessage);
					} catch (MessagingException e) {
						e.printStackTrace();
					}
					//	sendMailTo(pRPData.getpRpApprover2Name(), messageBody, subjectForMail);
				}
			}
			model.addObject("msg", "Approver1 Successfully Approved PR Number :"+pRPData.getpRpPrNumber());
		}

		List<String> supplierList = pRPIService.getSupplierNameList();
		model.addObject("supplierList",supplierList);

		List<String> approver1statusList = pRPIService.getApprover1StatusList();
		model.addObject("approver1statusList",approver1statusList);

		List<String> approver2statusList = pRPIService.getApprover2StatusList();
		model.addObject("approver2statusList",approver2statusList);

		List<String> pRpIdList =  pRPIService.getPrpIDList();
		model.addObject("prpIdList",pRpIdList);
		List<PRPData> pRpDataList = pRPIService.pRPgetprDetails();
		model.addObject("pRpDataList",pRpDataList);
		logger.info("INSIDE PRPController END METHOD pRPgetPrDetails ::");
		return model;
	}

	@RequestMapping(value="/save_Approval2", method=RequestMethod.POST)
	public ModelAndView pRPsaveApprover2Details(HttpServletRequest request , HttpServletResponse response, Authentication authentication){
		logger.info("INSIDE PRPController START METHOD pRPgetPrDetails ::");
		ModelAndView  model = new ModelAndView("prplist/viewApproverList");

		List<File> uploadedFiles = null;

		String apporver2Name = request.getParameter("approver2name");
		String apporver2Status = request.getParameter("approver2status");
		String apporver2Coments = request.getParameter("approver2comment");
		String prpId = request.getParameter("prpId");
		String supplierId = request.getParameter("supplierID");
		String prNumber = request.getParameter("prNumber");

		PRPData  pRPData = new PRPData();

		pRPData.setpRpPRId(Integer.parseInt(prpId));
		pRPData.setpRpApprover2Name(apporver2Name);
		pRPData.setpRpApprover2Status(apporver2Status);
		pRPData.setpRpApprover2Comments(apporver2Coments);
		pRPData.setpRpPrNumber(prNumber);

		int num2 = pRPIService.saveApprover2Details(pRPData);
		if(num2 > 0){
			if(pRPData.getpRpApprover2Status().equalsIgnoreCase("Approved")){
				String raisedPRMaildId = pRPIService.getRaisedPRMailID(prpId);
				String supplierMailID = pRPIService.getSupplierMailId(supplierId);
				String procurementEngineerMailId = pRPIService.getProcurementMailId();
				List<String> mailsIds = new ArrayList<String>();

				if(raisedPRMaildId != null && !raisedPRMaildId.isEmpty()){
					mailsIds.add(raisedPRMaildId);
				}if(supplierMailID != null && !raisedPRMaildId.isEmpty()){
					mailsIds.add(supplierMailID);
				}if(procurementEngineerMailId != null && procurementEngineerMailId.isEmpty()){
					mailsIds.add(procurementEngineerMailId);
				}
				try {
					//uploadedFiles = saveUploadedFiles(request);
					uploadedFiles = getPdfFileForAttachment(resoureseFilePath);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					String todayDate = DateUtils.convertToDDMMMYYYY(new Date());
					
					String baseUrl = dBQueryPropertyFile.getURLForKey("baseURL");
					String approvalApproverURL = dBQueryPropertyFile.getURLForKey("ApproverPage");
					String mainUrl = baseUrl+approvalApproverURL;
					String link = "\n<a href='"+mainUrl+pRPData.getpRpPRId()+"'>"+pRPData.getpRpPrNumber()+"</a>";

					String loggedInEmailId = authentication.getName();
					String sendFromfullName = userIService.getFullNameByUserId(loggedInEmailId);
				
					if(raisedPRMaildId != null){
						List<String> mails = new ArrayList<String>();
						String raisePrUserNotificationSubjectMessage = dBQueryPropertyFile.getMessageDetail("userNotificationSubject.message");
						String raisePrUserNotificationSubjectBodyMessges = MessageFormat.format(raisePrUserNotificationSubjectMessage,pRPData.getpRpPrNumber(),todayDate);
						String fullName = userIService.getFullNameByUserId(raisedPRMaildId);
						String raisePrUserBodyMessage = dBQueryPropertyFile.getMessageDetail("userNotificationBodyMessage.message");
						String raisePrUserNotificationBodyMessage = MessageFormat.format(raisePrUserBodyMessage,fullName, pRPData.getpRpPrNumber(),link);
						try {
							//raisedPRMaildId = "avinash.singh857@gmail.com";
							mails.add(raisedPRMaildId);
							emailUtils.sendEmailWithAttachment(mails,sendFromfullName,raisePrUserNotificationSubjectBodyMessges,raisePrUserNotificationBodyMessage,uploadedFiles);
	//						emailUtils.sendEmailWithAttachment(raisedPRMaildId, raisePrUserNotificationSubjectBodyMessges, raisePrUserNotificationBodyMessage);
						} catch (MessagingException e) {
							e.printStackTrace();
						}
					}if(supplierMailID != null){
						
						List<String> mails = new ArrayList<String>();

						String raisePrUserNotificationSubjectMessage = dBQueryPropertyFile.getMessageDetail("userNotificationSubject.message");
						String raisePrUserNotificationSubjectBodyMessges = MessageFormat.format(raisePrUserNotificationSubjectMessage,pRPData.getpRpPrNumber(),todayDate);
						String fullName = userIService.getFullNameByUserId(supplierMailID);
						String raisePrUserBodyMessage = dBQueryPropertyFile.getMessageDetail("userNotificationBodyMessage.message");
						String raisePrUserNotificationBodyMessage = MessageFormat.format(raisePrUserBodyMessage,fullName, pRPData.getpRpPrNumber(),link);
						try {
						//	supplierMailID = "avinash.singh857@gmail.com";
							mails.add(supplierMailID);
				//			emailUtils.sendEmailWithAttachment(supplierMailID, raisePrUserNotificationSubjectBodyMessges, raisePrUserNotificationBodyMessage);
							emailUtils.sendEmailWithAttachment(mails,sendFromfullName,raisePrUserNotificationSubjectBodyMessges,raisePrUserNotificationBodyMessage,uploadedFiles);
						} catch (MessagingException e) {
							e.printStackTrace();
						}
					}if(procurementEngineerMailId != null){
						List<String> mails = new ArrayList<String>();
						String raisePrUserNotificationSubjectMessage = dBQueryPropertyFile.getMessageDetail("userNotificationSubject.message");
						String raisePrUserNotificationSubjectBodyMessges = MessageFormat.format(raisePrUserNotificationSubjectMessage,pRPData.getpRpPrNumber(),todayDate);
						String fullName = userIService.getFullNameByUserId(procurementEngineerMailId);
						String raisePrUserBodyMessage = dBQueryPropertyFile.getMessageDetail("userNotificationBodyMessage.message");
						String raisePrUserNotificationBodyMessage = MessageFormat.format(raisePrUserBodyMessage,fullName, pRPData.getpRpPrNumber(),link);
						try {
							mails.add(procurementEngineerMailId);
							emailUtils.sendEmailWithAttachment(mails,sendFromfullName,raisePrUserNotificationSubjectBodyMessges,raisePrUserNotificationBodyMessage,uploadedFiles);
							//emailUtils.sendEmailWithAttachment(raisedPRMaildId, raisePrUserNotificationSubjectBodyMessges, raisePrUserNotificationBodyMessage);
						} catch (MessagingException e) {
							e.printStackTrace();
						}
					}
			//			emailUtils.sendEmailWithAttachment(mailsIds,supplier1NotificationSubject,supplier1NotificationBodyMessage,uploadedFiles);
				} catch (Exception ex) {
					ex.printStackTrace();
				} finally {
					//	deleteUploadFiles(uploadedFiles);
					deleteUploadFilesFromResourses(resoureseFilePath);
					model.addObject("msg", "Approver2 Successfully Approved PR Number :"+pRPData.getpRpPrNumber());
				}
			}
		}
		List<PRPData> pRpDataList = pRPIService.pRPgetprDetails();
		List<String> supplierList = pRPIService.getSupplierNameList();
		model.addObject("supplierList",supplierList);

		List<String> approver1statusList = pRPIService.getApprover1StatusList();
		model.addObject("approver1statusList",approver1statusList);

		List<String> approver2statusList = pRPIService.getApprover2StatusList();
		model.addObject("approver2statusList",approver2statusList);

		List<String> pRpIdList =  pRPIService.getPrpIDList();
		model.addObject("prpIdList",pRpIdList);

		model.addObject("pRpDataList",pRpDataList);
		logger.info("INSIDE PRPController END METHOD pRPgetPrDetails ::");
		return model;
	}

	public void deleteUploadFilesFromResourses(String resoureseFilePath2) {
		logger.info("INSIDE PRPController START METHOD deleteUploadFilesFromResourses ::");
		File folder = new File(resoureseFilePath2);
		File[] listOfFiles = folder.listFiles();
		if(listOfFiles.length > 0){
			for(File file : listOfFiles){
				file.delete();
			}
		}
		logger.info("INSIDE PRPController END METHOD deleteUploadFilesFromResourses ::");
	}


	public List<File> getPdfFileForAttachment(String resourseFilePath) {
		logger.info("INSIDE PRPController START METHOD getPdfFileForAttachment ::");

		List<File> fileList = new ArrayList<File>();
		File folder = new File(resourseFilePath);
		File[] listOfFiles = folder.listFiles();
		for(File file : listOfFiles){
			fileList.add(file);
		}
		logger.info("INSIDE PRPController END METHOD getPdfFileForAttachment ::");
		return fileList;
	}


	private List<File> saveUploadedFiles(HttpServletRequest request)
			throws IllegalStateException, IOException, ServletException {
		List<File> listFiles = new ArrayList<File>();
		byte[] buffer = new byte[4096];
		int bytesRead = -1;
		Collection<Part> multiparts = request.getParts();
		logger.info("multiparts::"+multiparts);
		if (multiparts.size() > 0) {
			for (Part part : request.getParts()) {
				// creates a file to be saved
				String fileName = extractFileName(part);
				if (fileName == null || fileName.equals("")) {
					// not attachment part, continue
					continue;
				}
				File saveFile = new File(fileName);
				logger.info("saveFile: " + saveFile.getAbsolutePath());
				FileOutputStream outputStream = new FileOutputStream(saveFile);
				// saves uploaded file
				InputStream inputStream = part.getInputStream();
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, bytesRead);
				}
				outputStream.close();
				inputStream.close();

				listFiles.add(saveFile);
			}
		}
		return listFiles;
	}

	/**
	 * Retrieves file name of a upload part from its HTTP header
	 */
	private String extractFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				return s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		return null;
	}

	/**
	 * Deletes all uploaded files, should be called after the e-mail was sent.
	 */
	private void deleteUploadFiles(List<File> listFiles) {
		if (listFiles != null && listFiles.size() > 0) {
			for (File aFile : listFiles) {
				aFile.delete();
			}
		}
	}

	//	private void sendMailTo(String approverMailId, String messageBody, String subjectForMail) {
	//		SimpleMailMessage message = new SimpleMailMessage();
	//		System.out.println(approverMailId+"::"+messageBody+"::"+subjectForMail);
	//		message.setTo(approverMailId.trim());
	//		message.setSubject(subjectForMail);
	//		message.setText(messageBody);
	//		emailIService.sendEmail(message);
	//	}


	@RequestMapping(value="/approverPR/{pr_Id}", method=RequestMethod.GET)
	public ModelAndView pRPgetviewApproverDetails(@PathVariable("pr_Id") Integer pr_Id){
		logger.info("INSIDE PRPController START METHOD pRPgetPrDetails ::");
		ModelAndView  model = new ModelAndView("prplist/prPrintForm");

		List<PRPData>  itemDataList = pRPIService.pRPgetprDetailsByPrpIdFromItem(pr_Id);
		PRPData prDataListByPrpId = pRPIService.getPrPDetailsByPrpID(pr_Id);

		String userEmailListForApprover1 =  lOVIService.getUserEmailsForApprover1();
		String userEmailListForApprover2 =  lOVIService.getUserEmailsForApprover2();

		prDataListByPrpId.setpRpApprover1Name(userEmailListForApprover1);
		prDataListByPrpId.setpRpApprover2Name(userEmailListForApprover2);
		deleteUploadFilesFromResourses(resoureseFilePath);
		generatePdf(itemDataList,prDataListByPrpId,resoureseFilePath);
		if(itemDataList != null && !itemDataList.isEmpty()){
			model.addObject("pRPData", itemDataList);
			model.addObject("pRPSingleDataObject", prDataListByPrpId);
			model.addObject("userEmailListForApprover1", userEmailListForApprover1);
			model.addObject("userEmailListForApprover2", userEmailListForApprover2);
			model.addObject("pr_Id", pr_Id);
		}
		logger.info("INSIDE PRPController END METHOD pRPgetPrDetails ::");
		return model;
	}

	private void setColorFlag(List<CalibMainData> calibMainData) {

		String date = DateUtils.convertToDDMMMYYYY(new Date());
		//Date sysDate = null;
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, +7);
		Date sysDate = cal.getTime();
		System.out.println("datecal 7 :"+sysDate);
		for(CalibMainData calibMain : calibMainData){
			try {
				Date date2 = DateUtils.convertToDDMMMYYYYParseToDate(calibMain.getMainDueDate());
				if(date2.compareTo(sysDate) > 0){
					// if the duedate is greater than the sysdate + 7 days..
					calibMain.setColorFlag("green");
				}else if(date2.compareTo(sysDate) < 0){
					// if the duedate is less than the sysdate + 7 days..
					System.out.println("today Date ::"+sysDate+"and duedate:"+date2);
					calibMain.setIfDueDatePassed(true);
					calibMain.setColorFlag("red");
				}else{
					calibMain.setColorFlag("yellow");
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}


	@RequestMapping(value = "/raise_pr" , method = RequestMethod.POST)
	public ModelAndView prPGetDetails(HttpServletRequest request , HttpServletResponse response){
		logger.info("INSIDE PRPController START METHOD prPGetDetails");
		ModelAndView model = new ModelAndView("prplist/raise_Pr");
		String supplierNumber  = request.getParameter("supplier");
		if(supplierNumber != null){
			PRPData pRPData = pRPIService.pRpSavePRDetailsForSupplierNumberBySupNum(supplierNumber);
			model.addObject("pRPData", pRPData);
		}
		List<PrData> identificationList = pRPIService.pRpGetAllIdentificationNumber(supplierNumber);
		model.addObject("identificationWithEquiType", identificationList);
		logger.info("INSIDE PRPController END METHOD prPGetDetails");
		return model;
	}

	@RequestMapping(value="/generatePR", method=RequestMethod.POST)
	public ModelAndView pRPgetCalibDetails(HttpServletRequest request , HttpServletResponse response,RedirectAttributes redir,Authentication authentication){
		logger.info("INSIDE PRPController START METHOD pRPgetCalibDetails ::");
		ModelAndView  model = new ModelAndView("redirect:/purchaseRequisitionList");
		String supID = request.getParameter("supID");
		List<CalibMainData> calibMainData = pRPIService.pRPgetCalibDetails(supID);
		if(calibMainData != null){
			setColorFlag(calibMainData);
		}
		PRPData pRPData = pRPIService.updatePrpDetails(supID);
		if(pRPData != null){
			String prNumber = pRPIService.getPRNumberByPrId(pRPData.getpRpPRId());
			redir.addFlashAttribute("msg","PR Number :"+prNumber+" Generated Sucessfully!!");
			String userEmailListForApprover1 =  lOVIService.getUserEmailsForApprover1();
			pRPData.setpRpApprover1Name(userEmailListForApprover1);
			String fullName = userIService.getFullNameByUserId(pRPData.getpRpApprover1Name());

			String subjectForMailMessage = dBQueryPropertyFile.getMessageDetail("approver1NotificationSubject.message");

			String todayDate = DateUtils.convertToDDMMMYYYY(new Date());

			String baseUrl = dBQueryPropertyFile.getURLForKey("baseURL");
			String approvalApproverURL = dBQueryPropertyFile.getURLForKey("ApproverPage");
			String mainUrl = baseUrl+approvalApproverURL;

			String link = "\n<a href='"+mainUrl+pRPData.getpRpPRId()+"'>"+prNumber+"</a>";
		
		//	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String loggedInEmailId = authentication.getName();
			String sendFromfullName = userIService.getFullNameByUserId(loggedInEmailId);
			
			String approver1NotificationSubject = MessageFormat.format(subjectForMailMessage, prNumber,todayDate);
			String supplier1BodyMessage = dBQueryPropertyFile.getMessageDetail("approver1NotificationBodyMessage.message");
			String approver1NotificationBodyMessage = MessageFormat.format(supplier1BodyMessage, fullName, prNumber , link);
			try {
				emailUtils.sendEmailWithAttachment(userEmailListForApprover1,sendFromfullName, approver1NotificationSubject, approver1NotificationBodyMessage);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
		logger.info("INSIDE PRPController END METHOD pRPgetCalibDetails ::");
		return model;
	}

	@RequestMapping(value = "/search")
	public ModelAndView mainCalibSearchByCondition(HttpServletRequest request, HttpServletResponse response){
		logger.info("INSIDE CalibController START METHOD mainCalibSearchByCondition");
		ModelAndView model = new ModelAndView("prplist/prp_List");
		String supplierid = request.getParameter("supplierid");

		if(supplierid != null && !supplierid.isEmpty()){
			List<CalibMainData> calibMainData = pRPIService.mainCalibSearchByCondition(supplierid.trim());
			if(calibMainData != null){
				setColorFlag(calibMainData);
			}else{
				model.addObject("hiddenFlag", null);
			}
			model.addObject("calibMainData" ,calibMainData);
			if(!calibMainData.isEmpty()){
				model.addObject("hiddenFlag", "");
				model.addObject("RaisePR","");
				model.addObject("color","green");
				model.addObject("msg","Search result for supplier "+calibMainData.get(0).getMainSupplierName());
			}
		}else{
			model.addObject("RaisePR",null);
			List<CalibMainData> calibMainData = pRPIService.pRPgetCalibDetails();
			//List<CalibMainData> calibMainData = pRPIService.mainCalibSearchByCondition(supplierid.trim());
			if(calibMainData != null){
				setColorFlag(calibMainData);
			}else{
				model.addObject("hiddenFlag", null);
			}
			model.addObject("calibMainData" , calibMainData);
		}
		List<SUPData> supSupplierList = sUPIService.supGetSupplierNameWithId();
		model.addObject("supSupplierList", supSupplierList);
		logger.info("INSIDE CalibController END METHOD mainCalibSearchByCondition");
		return model;
	}	

	private void generatePdf(List<PRPData> itemDataList, PRPData prDataListByPrpId, String resourseFilePath) {
		try {

			String fileName = prDataListByPrpId.getpRpPrNumber()+"_"+"Purchase_Requition.pdf";
			OutputStream file = new FileOutputStream(new File(resourseFilePath+fileName));
			Font font1 = new Font(Font.getFamily("Cambria"), 9, 0, BaseColor.BLACK);
			Font font2 = new Font(Font.getFamily("Cambria"), 9, 0, BaseColor.BLUE);
			
			Font tableHeaderFont = new Font(Font.getFamily("Cambria"), 7, 0, BaseColor.BLACK);

			//	OutputStream file = new FileOutputStream(new File("D://pdf//PDFJAVAS.pdf"));
			Document document = new Document();
			PdfWriter.getInstance(document, file);

			//Inserting Image in PDF
			Image image = Image.getInstance ("src/main/resources/static/images/Spirax-Sarco-logo.png");
			image.scaleAbsolute(120f, 60f);//image width,height
			image.setAlignment(Image.ALIGN_RIGHT);

			//Inserting Table in PDF
			PdfPTable table=new PdfPTable(3);
			table.setWidthPercentage(100);
			Font font = new Font(Font.getFamily("Cambria"), 16, Font.BOLDITALIC);
			PdfPCell cell = new PdfPCell (new Paragraph ("Purchase Requisition",font));
			cell.setColspan (2);

			table.addCell(cell);						               
			cell = new PdfPCell(new Phrase(""));
			cell.setColspan(1);
			table.addCell(image);
			cell = new PdfPCell(new Phrase("        "));
			cell.setColspan(1);
			cell.setRowspan(3);
			table.addCell(cell);
			cell = new PdfPCell(new Phrase("Purchase Requisition No.",font1));
			cell.setColspan(1);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(prDataListByPrpId.getpRpPrNumber(),font2));
			cell.setColspan(1);
			table.addCell(cell);
			cell.setColspan(3);
			cell = new PdfPCell(new Phrase("Date",font1));
			cell.setColspan(1);
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(prDataListByPrpId.getpRpGeneratePrDate(),font2));
			cell.setColspan(1);
			table.addCell(cell);
			cell = new PdfPCell(new Phrase("Purchase Order No",font1));
			cell.setColspan(1);
			table.addCell(cell);
			cell = new PdfPCell(new Phrase("-",font2));
			cell.setColspan(1);
			table.addCell(cell);

			table.setSpacingBefore(15.0f);       
			table.setSpacingAfter(15.0f);

			PdfPTable table1=new PdfPTable(3);
			table1.setWidthPercentage(100);

			PdfPCell cell1 = new PdfPCell (new Paragraph ("Name of the department	:",font1));
			cell1.setColspan (2);
			table1.addCell(cell1);
			cell1 = new PdfPCell(new Phrase("Quality Functions",font2));
			cell1.setColspan(2);
			table1.addCell(cell1);
			cell1 = new PdfPCell(new Phrase("to be used(for a machine)	:",font1));
			cell1.setColspan(2);
			table1.addCell(cell1);
			cell1 = new PdfPCell(new Phrase("Periodical Calibrations",font2));
			cell1.setColspan(1);
			table1.addCell(cell1);

			table1.setSpacingBefore(10.0f);      
			table1.setSpacingAfter(10.0f);

			PdfPTable table2=new PdfPTable(6);
			table2.setWidthPercentage(100);
			float[] columnWidths = { 0.5f,2f,4f,1f,2f,2f }; // Second column will be
            table2.setWidths(columnWidths);


			PdfPCell cell2 = new PdfPCell (new Paragraph ("slno"));
			cell2 = new PdfPCell(new Phrase("Kindly approve and process procurement of following material(s):",font1));
			cell2.setColspan(6);
			table2.addCell(cell2);
			cell2 = new PdfPCell(new Phrase("Sr. No.",tableHeaderFont));
			
			cell2.setColspan(1);
			table2.addCell(cell2);
			cell2 = new PdfPCell(new Phrase("Part Code(If Any)",tableHeaderFont));
			
			cell2.setColspan(1);
			table2.addCell(cell2);
			cell2 = new PdfPCell(new Phrase("Product Description",tableHeaderFont));
			
			cell2.setColspan(1);
			table2.addCell(cell2);
			cell2 = new PdfPCell(new Phrase("Quantity",tableHeaderFont));
			
			cell2.setColspan(1);
			table2.addCell(cell2);
			cell2 = new PdfPCell(new Phrase("Budgetry Cost",tableHeaderFont));
			cell2.setColspan(1);
			table2.addCell(cell2);
			cell2 = new PdfPCell(new Phrase("Delivery",tableHeaderFont));
			cell2.setColspan(1);
			table2.addCell(cell2);
			// 7 row * 6 col = 48

			System.out.println("data items::"+itemDataList.size());
			Phrase ph;
			double total = 0.0f;
			
			for(int aw=0; aw < itemDataList.size(); aw++){
				
				PRPData pRpDataItems = itemDataList.get(aw);
				cell2 = new PdfPCell();
				ph = new Phrase(""+(aw+1),font2);
				cell2.addElement(ph);
				table2.addCell(cell2);

				cell2 = new PdfPCell();
				ph = new Phrase(""+pRpDataItems.getpRpPartCode(),font2);
				cell2.addElement(ph);
				table2.addCell(cell2);

				cell2 = new PdfPCell();
				ph = new Phrase(""+pRpDataItems.getpRpEquipmentDescription(),font2);
				cell2.addElement(ph);
				table2.addCell(cell2);

				cell2 = new PdfPCell();
				ph = new Phrase("1",font2);
				cell2.addElement(ph);
				table2.addCell(cell2);

				cell2 = new PdfPCell();
				ph = new Phrase(""+pRpDataItems.getpRpBudgetryCost(),font2);
				cell2.addElement(ph);
				table2.addCell(cell2);
				if(!pRpDataItems.getpRpBudgetryCost().isEmpty()){
					total = total + Double.parseDouble(pRpDataItems.getpRpBudgetryCost());
				}

				cell2 = new PdfPCell();
				ph = new Phrase(""+pRpDataItems.getpRpDeliveryDate(),font2);
				cell2.addElement(ph);
				table2.addCell(cell2);
			}
			
			Font font6 = new Font(Font.getFamily("Cambria"), 11, Font.BOLDITALIC);
			cell2 = new PdfPCell(new Phrase("Total Budgetry Cost :",font6));
			cell2.setColspan(4);
			table2.addCell(cell2);
			cell2 = new PdfPCell(new Phrase(""+total,font2));
			cell2.setColspan(2);
			table2.addCell(cell2);

			table2.setSpacingBefore(15.0f);      
			table2.setSpacingAfter(15.0f);

			PdfPTable table3=new PdfPTable(3);
			table3.setWidthPercentage(100);
			PdfPCell cell3 = new PdfPCell (new Paragraph ("Purchase Justification	:",font1));
			cell3.setColspan (2);
			table3.addCell(cell3);
			cell3 = new PdfPCell(new Phrase("Periodic Calibration",font2));
			cell3.setColspan(2);
			table3.addCell(cell3);
			cell3 = new PdfPCell(new Phrase("Budgetry Quotation	:",font1));
			cell3.setColspan(2);
			table3.addCell(cell3);
			cell3 = new PdfPCell(new Phrase("Yes",font2));
			cell3.setColspan(1);
			table3.addCell(cell3);
			cell3 = new PdfPCell(new Phrase("Suggested/Preferred Supplier	:",font1));
			cell3.setColspan(2);
			table3.addCell(cell3);
			cell3 = new PdfPCell(new Phrase(prDataListByPrpId.getpRpSupplierName(),font2));
			cell3.setColspan(1);
			table3.addCell(cell3);

			table3.setSpacingBefore(15.0f);      
			table3.setSpacingAfter(15.0f);

			PdfPTable table4=new PdfPTable(3);
			table4.setWidthPercentage(100);
			Font bottomHeadingFont = new Font(Font.getFamily("cambria"), 8, Font.BOLDITALIC);
			
			PdfPCell cell4= new PdfPCell (new Paragraph ("Requested By (Name,E.code & Signature)",bottomHeadingFont));
			cell4.setColspan (1);
			table4.addCell(cell4);
			
			cell4 = new PdfPCell(new Phrase("Approved By (Name,E.code & Signature) Head Engineering Quality",bottomHeadingFont));
			cell4.setColspan(1);
			table4.addCell(cell4);
			
			cell4 = new PdfPCell(new Phrase("Approved By (Name,E.code & Signature)Head Operations",bottomHeadingFont));
			cell4.setColspan(1);
			table4.addCell(cell4);
			
			cell4 = new PdfPCell(new Phrase(prDataListByPrpId.getpRpRaisedPrFullName(),font2));
			cell4.setColspan(1);
			table4.addCell(cell4);
			cell4 = new PdfPCell(new Phrase(prDataListByPrpId.getpRpApprover1FullName(),font2));
			cell4.setColspan(1);
			table4.addCell(cell4);
			cell4 = new PdfPCell(new Phrase(prDataListByPrpId.getpRpApprover2FullName(),font2));
			cell4.setColspan(1);
			table4.addCell(cell4);

			document.open();//PDF document opened........			       
			document.add(Chunk.NEWLINE);   //Something like in HTML ðŸ™‚

			PdfPTable nestedTable = new PdfPTable(1);
			PdfPCell nestedCell = new PdfPCell();

			nestedCell.addElement(table);
			nestedCell.addElement(table1);
			nestedCell.addElement(table2);
			nestedCell.addElement(table3);
			nestedCell.addElement(table4);

			nestedTable.addCell(nestedCell);

			document.add(nestedTable);
			document.add(Chunk.NEWLINE);   //Something like in HTML ðŸ™‚							    
			document.newPage();            //Opened new page
			document.close();

			file.close();

			System.out.println("Pdf created successfully..");

		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

}
