package com.spiraxcalibration.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spiraxcalibration.models.CalibData;
import com.spiraxcalibration.services.ApprovalIService;
import com.spiraxcalibration.services.CalibIService;
import com.spiraxcalibration.services.LookupIService;
import com.spiraxcalibration.services.PrIService;

@Controller
public class ApproveController {

	Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	CalibIService calibIService;

	@Autowired
	ApprovalIService approvalIService;

	@Autowired
	PrIService  prIService;

	@Autowired
	LookupIService lookupIService;

	@RequestMapping(value="/result", method=RequestMethod.POST)
	public void result(HttpServletRequest request, HttpServletResponse response){
	}
	
	@RequestMapping(value="/approverDetails", method=RequestMethod.GET)
	public ModelAndView approveGetCalibDetails(){
		logger.info("INSIDE ApproveController START METHOD approveGetCalibDetails ::");
		ModelAndView  model = new ModelAndView("approvals/pedingForApprove");
		model.addObject("productList",approvalIService.approveGetCalibDetails());
		logger.info("INSIDE ApproveController END METHOD approveGetCalibDetails ::");
		return model;
	}

//	@RequestMapping(value = "/calibApprovalPage/{calibId}" , method = RequestMethod.GET)
//	public ModelAndView calibUpdateForApprove(@PathVariable("calibId") Integer calibId){
//		logger.info("INSIDE ApproveController START METHOD calibUpdateForApprove");
//
//		CalibData calibData = calibIService.calibGetCalibrationById(calibId);
//
//		ModelAndView  model1 = new ModelAndView("approvals/ApproveOrRejectForApprovers");
//
//		model1.addObject("CalibForm",calibData);
//		model1.addObject("addORupdatePrheading","Approve Calibration");
//		model1.addObject("addOrUpdate","Approve Calibration");
//		model1.addObject("disabled",true);
//
//		logger.info("INSIDE ApproveController END METHOD calibUpdateForApprove");
//		return model1;
//	}

	@RequestMapping(value = "/approverAction" , method = RequestMethod.POST)
	public ModelAndView calibSaveApproverForApproveOrReject(@ModelAttribute("CalibForm") @Valid CalibData calibData,
			BindingResult result, RedirectAttributes redir, HttpServletRequest request){
		logger.info("INSIDE ApproveController START METHOD calibSaveApproverForApproveOrReject");
		ModelAndView  model = new ModelAndView("redirect:/approverDetails");

		System.out.println("testing details ::::::::"+calibData.getCalibSendForApprovalStatusFlag());
		if (result.hasErrors()) {
			if(calibData.getCalibId() != null){
				ModelAndView  model2 = new ModelAndView("redirect:/calibApprovalPage/"+calibData.getCalibId());
				model2.addObject("addORupdatePrheading","Approve Calibration");
				return model2;
			}
		}else{
			if(calibData != null && calibData.getCalibId() != null){
				int num  = calibIService.calibApprovedOrReject(calibData);
				if(num > 0 ){
					if(calibData.getCalibSendForApprovalStatusFlag() == 1){
						redir.addFlashAttribute("msg","Calibration Ref. No. "+calibData.getCalibId() +" Approved"+ " Successfully!!");
					}else if(calibData.getCalibSendForApprovalStatusFlag() == 2){
						redir.addFlashAttribute("msg","Calibration Ref. No. "+calibData.getCalibId() +" Rejected"+ " Successfully!!");
					}
				}
				return model;
			}
		}
		logger.info("INSIDE ApproveController START METHOD calibSaveApproverForApproveOrReject");
		return model;
	}

	@RequestMapping(value = "/search_Active_Product")
	public ModelAndView approveSearchCalibByCondition(HttpServletRequest request, HttpServletResponse response){
		logger.info("INSIDE ApproveController START METHOD prSearchProductByCondition");
		ModelAndView model = new ModelAndView("approvals/pedingForApprove");

		String identity = request.getParameter("identificationNum");
		String serialNumber = request.getParameter("serialNum");

		if(identity != null || serialNumber != null){
			model.addObject("productList" , approvalIService.approveSearchCalibByCondition(identity, serialNumber));
		}else{
			model.addObject("productList", approvalIService.approveGetCalibDetails());
		}
		logger.info("INSIDE ApproveController END METHOD prSearchProductByCondition");
		return model;
	}	
}
