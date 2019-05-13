package com.spiraxcalibration.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spiraxcalibration.WebConfig.AppsPropertyFile;
import com.spiraxcalibration.models.CalibData;
import com.spiraxcalibration.models.PrData;
import com.spiraxcalibration.services.CalibIService;
import com.spiraxcalibration.services.LookupIService;
import com.spiraxcalibration.services.PrIService;
import com.spiraxcalibration.utils.DateUtils;

@Controller
public class CalibController {

	Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	AppsPropertyFile dBQueryPropertyFile = new AppsPropertyFile();

	@Autowired
	CalibIService calibIService;

	@Autowired
	PrIService  prIService;

	@Autowired
	LookupIService lookupIService;

	@RequestMapping(value="/calibrationlist", method=RequestMethod.GET)
	public ModelAndView getCalibrationdetails(){
		logger.info("INSIDE CalibController START METHOD getCalibrationdetails ::");
		ModelAndView  model = new ModelAndView("calibrations/calibList");
		model.addObject("productList",calibIService.calibGetProductDetails());
		model.addObject("locationList",lookupIService.getLocationFromProduct());
		model.addObject("agencyList",lookupIService.getCalibAgencyFromProduct());
		logger.info("INSIDE CalibController END METHOD getCalibrationdetails ::");
		return model;		
	}

	// SEARCHING THE PRODUCT..
	@RequestMapping(value = "/searchCalibProduct")
	public ModelAndView calibSearchProductByCondition(HttpServletRequest request, HttpServletResponse response){
		logger.info("INSIDE CalibController START METHOD searchProductByCondition");
		ModelAndView model = new ModelAndView("calibrations/calibList");
		String identity = request.getParameter("identityNum");
		String serialNumber = request.getParameter("serialNumber");
		String location = request.getParameter("location");
		String calibAgency = request.getParameter("agency");

		if(identity != null || serialNumber != null || location != null || calibAgency != null){
			model.addObject("productList" , calibIService.calibSearchByCondition(identity, serialNumber, location, calibAgency));
		}else{
			model.addObject("productList" , calibIService.calibGetProductDetails());
		}
		model.addObject("agencyList",lookupIService.getCalibAgencyFromProduct());
		model.addObject("locationList",lookupIService.getLocationFromProduct());
		logger.info("INSIDE CalibController END METHOD searchProductByCondition");
		return model;
	}	

	// SEARCHING THE PRODUCT..
	@RequestMapping(value = "/calibrate/{prodId}")
	public ModelAndView calibbrateById(@PathVariable("prodId") Integer prodId){
		logger.info("INSIDE CalibController START METHOD calibbrateById");
		ModelAndView model = new ModelAndView("calibrations/calibDetailsInfo");
		List<CalibData> calibDataList = calibIService.calibGetCalibrationsById(prodId);
		for(CalibData calibData : calibDataList){
			if(calibData.getCalibApprover1Status().equalsIgnoreCase("Rejected")){
				calibData.setCalibApproverStatusColorFlag("red");
			}else if(calibData.getCalibApprover1Status().equalsIgnoreCase("Approved")){
				calibData.setCalibApproverStatusColorFlag("green");
			}else if(calibData.getCalibSendForApprovalStatusFlag() == 0){
				calibData.setCalibApprover1Status("Not Yet Submitted");
				calibData.setCalibApproverStatusColorFlag("grey");
			}else {
				calibData.setCalibApproverStatusColorFlag("yellow");
			}
		}
		PrData prdata = prIService.prFindProductByProdId(prodId);

		model.addObject("calibData" , calibDataList);
		model.addObject("prdata", prdata);
		model.addObject("calibInfo", "Calibration Information");
		int num = calibIService.calibGetcalibSatusCountIfCalibratedByProdID(prodId);
		if(num > 0){
			model.addObject("disabled",true);
		}else{
			model.addObject("disabled",false);
		}
		logger.info("INSIDE CalibController END METHOD calibbrateById");
		return model;
	}	

	// ADDING THE NEW CALIBRTION....
	@RequestMapping(value = "/addCalibration/{prodId}" , method = RequestMethod.GET)
	public ModelAndView calibAddProduct(@PathVariable("prodId") Integer prodId , HttpServletRequest request){
		logger.info("INSIDE CalibController START METHOD calibAddProduct");
		ModelAndView model = new ModelAndView("calibrations/addCalibDetails");

		String supplierName = request.getParameter("supplierName");
		String supplierNumber = request.getParameter("supplierNumber");
		CalibData  calibData = new CalibData();
		Date todayDate = new Date();
		String todaysDate = DateUtils.convertToYYYYMMDD(todayDate);
		calibData.setCalibCalibrationDate(todaysDate);
		calibData.setCalibSupplierName(supplierName);
		calibData.setCalibSupplierNumber(supplierNumber);
		calibData.setCalibProdId(prodId);
		calibData.setCalibIdentificationNo(request.getParameter("identityNum"));
		calibData.setCalibSerialNumber(request.getParameter("serialNum"));
		model.addObject("CalibForm",calibData);
		HashMap<String, Integer>  mapFreque = lookupIService.getCalibrationFreqType();
		List<String>   ApproverMailIds = lookupIService.getApproverMailIds();
		
		model.addObject("ApproverMailList",ApproverMailIds);

		model.addObject("calibrationTypeLookup",mapFreque);
		model.addObject("selectedFrequency", mapFreque.get("Statutory Calibration"));
		List<String> calibStatus = lookupIService.getCalibstatus();
		calibStatus.clear();
		model.addObject("calibrationStatusLookup",calibStatus);
		calibData.setCalibCalibStatus("Active");
		model.addObject("addOrUpdate","Add Calibration");
		model.addObject("addORupdatePrheading","Add Calibration");

		logger.info("INSIDE CalibController END METHOD calibAddProduct");
		return model;
	}

	// SAVING WHEN CLICK THE ADD CALIBRATION..
	@RequestMapping(value = "/saveCalibration" , method = RequestMethod.POST)
	public ModelAndView calibSaveCalib(@ModelAttribute("CalibForm") @Valid CalibData calibData,
			BindingResult result, RedirectAttributes redir, HttpServletRequest request){
		logger.info("INSIDE CalibController START METHOD calibSaveCalib");

		ModelAndView  model1 = new ModelAndView("calibrations/addCalibDetails");
		ModelAndView  model2 = new ModelAndView("redirect:/calibrate/"+calibData.getCalibProdId());
		model1.addObject("addOrUpdate","Add Calibration");
		model2.addObject("addORupdatePrheading","Add Calibration");

		if (result.hasErrors()) {
			if(calibData.getCalibId() != null){
				model1.addObject("addORupdatePrheading","Update Calibration");
				model1.addObject("addOrUpdate","Update Calibration");
			}else{
				model1.addObject("addORupdatePrheading","Add Calibration");
			}
			return model1;
		}else{
			if(calibData != null && calibData.getCalibId() != null){
				int num = calibIService.calibUpdateCalibDetails(calibData);
				if(num > 0 ){
					model2.addObject("addORupdatePrheading","Calibration Details Updated Successfully");
					redir.addFlashAttribute("msg","Calibration "+calibData.getCalibIdentificationNo()+" Details Updated Successfully!!");
				}else if(num == -1){
					// num = for not update 
					//model2.addObject("addORupdatePrheading","CalibrationDetails Updated Successfully");
					redir.addFlashAttribute("inactiveMSG","Already Calibration Is Active!!");

				}
				return model2;
			}else{
				int num = calibIService.calibSaveCalibrationDetail(calibData);
				if(num > 0){
					redir.addFlashAttribute("msg","Calibration For "+calibData.getCalibIdentificationNo()+" Added Successfully!!");
				}
				return model2;
			} 
		}
	}


//	// UPDATING THE CALIBRATION..
//	@RequestMapping(value = "/updateCalib" , method = RequestMethod.GET)
//	public ModelAndView calibUpdateProduct(@RequestParam("calib_Id") Integer calibId){
//		logger.info("INSIDE CalibController START METHOD calibUpdateProduct");
//		ModelAndView  model = new ModelAndView("calibrations/addCalibDetails");
//
//		CalibData calibData = calibIService.calibGetCalibrationById(calibId);
//
//		List<String> calibStatus =  lookupIService.getCalibstatus();
//		HashMap<String,Integer> calibTypeMap = lookupIService.getCalibrationFreqType();
//
//		calibStatus.remove(calibData.getCalibCalibStatus());
//		calibTypeMap.remove(calibData.getCalibCalibrationType());
//
//		model.addObject("ApproverMailList",lookupIService.getApproverMailIds());
//		model.addObject("CalibForm", calibData);
//		model.addObject("selectedFrequency", calibTypeMap.get("Statutory Calibration"));
//
//		model.addObject("calibrationTypeLookup",calibTypeMap);
//		model.addObject("calibrationStatusLookup",calibStatus);
//		model.addObject("disabled",true);
//		model.addObject("addORupdatePrheading","Update Calibration");
//		model.addObject("addOrUpdate","Update Calibration");
//
//
//		logger.info("INSIDE CalibController END METHOD calibUpdateProduct");
//		return model;
//	}

	// UPDATING THE PRODUCT..
//		@RequestMapping(value = "/updateApproval" , method = RequestMethod.GET)
//		public ModelAndView calibUpdateForApprove(@RequestParam("calib_Id") Integer calibId, RedirectAttributes redir){
//			logger.info("INSIDE CalibController START METHOD calibUpdateForApprove");
//	
//			ModelAndView  model1 = new ModelAndView("calibrations/updateForApprovers");
//			CalibData calibData = calibIService.calibGetCalibrationById(calibId);
//			ModelAndView  model2 = new ModelAndView("redirect:/calibrate/"+calibData.getCalibProdId());
//	
//			if(calibData.getCalibCalibStatus().equalsIgnoreCase("Inactive")){
//				redir.addFlashAttribute("inactiveMSG","You Can Not Update For Inactive Calibration Status!!");
//				return model2;
//			}
//			model1.addObject("CalibForm",calibData);
//			
//			List<String> calibTypeList =  lookupIService.getCalibrationType();
//			calibTypeList.remove(calibData.getCalibCalibrationType());
//			List<String>  calibStatusList = lookupIService.getCalibstatus();
//			calibStatusList.remove(calibData.getCalibCalibStatus());
//	
//			model1.addObject("calibrationTypeLookup",calibTypeList);
//			model1.addObject("calibrationStatusLookup",calibStatusList);
//			model1.addObject("addORupdatePrheading","Approve Calibration");
//			model1.addObject("addOrUpdate","Approve Calibration");
//			model1.addObject("disabled",true);
//	
//			logger.info("INSIDE CalibController END METHOD calibUpdateForApprove");
//			return model1;
//		}

	// UPDATING THE PRODUCT..
//	@RequestMapping(value = "/updateApproval/{calib_Id}" , method = RequestMethod.GET)
//	public ModelAndView calibUpdateForApprove(@PathVariable("calib_Id") Integer calibId, RedirectAttributes redir){
//		logger.info("INSIDE CalibController START METHOD calibUpdateForApprove");
//
//		CalibData calibData = calibIService.calibGetCalibrationById(calibId);
//		ModelAndView  model2 = new ModelAndView("redirect:/calibrate/"+calibData.getCalibProdId());
//		logger.info("INSIDE CalibController END METHOD calibUpdateForApprove");
//
//		if(calibData.getCalibCalibStatus().equalsIgnoreCase("Inactive")){
//			redir.addFlashAttribute("inactiveMSG","Inactive Calibration Status Can Not Be send For Approval!!");
//			return model2;
//		}else{
//			// this will set 1 for 
//			int ifChangeTheStatus = calibIService.setPendingForApproval(calibId);
//			if(ifChangeTheStatus > 0){
//				System.out.println("testing:::::calibData:::"+calibData.getCalibAprroverMailId());
//				String approverMailId = "avinash.singh@transformedge.com";
//				String messageBody = "To Testing Only";
//				String subjectForMail = "Testing";
//				
//				sendMailTo(approverMailId,messageBody,subjectForMail);
//				
//				redir.addFlashAttribute("msg","Calibration Ref. No. "+calibId+" sent For Approval!!");
//				return model2;
//			}
//		}
//		return model2;
//	}

//	@RequestMapping(value = "/updateApproval" , method = RequestMethod.GET)
//	public ModelAndView calibUpdateForApprove(@RequestParam(value="mail_Id" , required = false) String mailId
//		   , @RequestParam("calib_id") Integer calibId,RedirectAttributes redir){
//		logger.info("INSIDE CalibController START METHOD calibUpdateForApprove");
//
//		System.out.println("::::::::::"+mailId);
//		System.out.println("::::::::::"+calibId);
//
//		CalibData calibData = calibIService.calibGetCalibrationById(calibId);
//		ModelAndView  model2 = new ModelAndView("redirect:/calibrate/"+calibData.getCalibProdId());
//		logger.info("INSIDE CalibController END METHOD calibUpdateForApprove");
//
//		if(calibData.getCalibCalibStatus().equalsIgnoreCase("Inactive")){
//			redir.addFlashAttribute("inactiveMSG","Inactive Calibration Status Can Not Be send For Approval!!");
//			return model2;
//		}else{
//			// this will set 1 for 
//			int ifChangeTheStatus = calibIService.setPendingForApproval(calibId);
//			if(ifChangeTheStatus > 0){
//				String approvalApproverURL = dBQueryPropertyFile.getURLForKey("ApproverPage");
//			//	String approvalApproveForCalibId = approvalApproverURL+"calibId;
//				//String  calibId = ""
//				String approverMailId = calibData.getCalibAprroverMailId();
//				String messageBody = "Calibration Ref No. "+calibId+" submitted for your Approval."
//						+ " Please Take Action \""+approvalApproverURL+calibId+"\"" ;
//				
//				String subjectForMail = calibId+" Updated For Your Approval";
//				
//				sendMailTo(approverMailId,messageBody,subjectForMail);
//				
//				redir.addFlashAttribute("msg","Calibration Ref. No. "+calibId+" sent For Approval!!");
//				return model2;
//			}
//		}
//		return model2;
//	}

//	private void sendMailTo(String approverMailId, String messageBody, String subjectForMail) {
//			SimpleMailMessage message = new SimpleMailMessage();
//			System.out.println(approverMailId+"::"+messageBody+"::"+subjectForMail);
//			message.setTo(approverMailId.trim());
//			message.setSubject(subjectForMail);
//			message.setText(messageBody);
//			emailService.sendEmail(message);
//	}

//	@RequestMapping(value = "/saveApprover" , method = RequestMethod.POST)
//	public ModelAndView calibSaveApprover(@ModelAttribute("CalibForm") @Valid CalibData calibData,
//			BindingResult result, RedirectAttributes redir, HttpServletRequest request){
//		logger.info("INSIDE CalibController START METHOD calibSaveApprover");
//		ModelAndView  model = new ModelAndView("redirect:/calibrate/"+calibData.getCalibProdId());
//		if (result.hasErrors()) {
//			if(calibData.getCalibId() != null){
//				model.addObject("addORupdatePrheading","Approve Calibration");
//			}
//			return model;
//		}else{
//			if(calibData != null && calibData.getCalibId() != null){
//				System.out.println("inside update calibration :::");
//				System.out.println("Avinash test::"+calibData.getCalibCalibrationDueDate());
//				System.out.println("Avinash test::"+calibData.getCalibCalibrationReminderDate());
//
//				//	int num = calibIService.calibUpdateCalibDetails(calibData);
//				int num  = calibIService.calibSaveApprover(calibData);
//				if(num > 0 ){
//					redir.addFlashAttribute("msg","Approver Approved Successfully!!");
//				}
//				return model;
//			}
//		}
//		return model;
//	}
	
}
