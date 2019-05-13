package com.spiraxcalibration.controllers;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spiraxcalibration.models.SUPData;
import com.spiraxcalibration.services.LOVIService;
import com.spiraxcalibration.services.LookupIService;
import com.spiraxcalibration.services.SUPIService;

@Controller
public class SUPController {
	Logger logger = LoggerFactory.getLogger(this.getClass().getName());	

	@Autowired
	SUPIService  sUPIService;
	
	@Autowired
	LookupIService lookupIService;
	
	@Autowired
	LOVIService  lOVIService;


	@RequestMapping(value="/supplierList",method=RequestMethod.GET)
	public ModelAndView supGetSupplierDetails(){
		logger.info("INSIDE SUPController START METHOD supGetSupplierDetails ::");
		ModelAndView  model = new ModelAndView("suppliermanagement/supplierDetails");
		List<SUPData> supSupplierList = sUPIService.supGetSuppliersDetails();
		model.addObject("supplierList", supSupplierList);
		model.addObject("SupplierStatusList", getSupplierStatusList());
		logger.info("INSIDE SUPController END METHOD supGetSupplierDetails ::");
		return model;
	}

	@RequestMapping(value = "/addSupplier" , method = RequestMethod.GET)
	public ModelAndView supAddSupplier(){
		logger.info("INSIDE SUPController START METHOD supAddSupplier");
		ModelAndView model = new ModelAndView("suppliermanagement/addORUpdateSupplier");
		model.addObject("SupData",new SUPData());
		model.addObject("addOrUpdate","addSupplier");
		model.addObject("addORupdatePrheading","Add Supplier");
		model.addObject("SupplierStatusList", getSupplierStatusList());
		List<String> calibServiceTypeList = getSupplierServiceType();
		model.addObject("SupplierServiceTypeList", calibServiceTypeList);
		logger.info("INSIDE SUPController END METHOD supAddSupplier");
		return model;
	}

	// SAVING WHEN CLICK THE ADD AND UPDATE PRODUCT
	@RequestMapping(value = "/saveSupplier" , method = RequestMethod.POST)
	public ModelAndView supSaveSupplier(@ModelAttribute("SupData") @Valid SUPData supData,
			BindingResult result, RedirectAttributes redir, HttpServletRequest request){
		logger.info("INSIDE SUPController START METHOD supSaveSupplier");

		ModelAndView  model1 = new ModelAndView("suppliermanagement/addORUpdateSupplier");
		ModelAndView  model2 = new ModelAndView("redirect:/supplierList");
		ModelAndView  model3 = new ModelAndView("redirect:/addSupplier");

		model1.addObject("addOrUpdate","addSupplier");
		model2.addObject("addOrUpdate","addSupplier");
		model1.addObject("SupplierStatusList", getSupplierStatusList());
		List<String> calibServiceTypeList = getSupplierServiceType();
		model1.addObject("SupplierServiceTypeList", calibServiceTypeList);

		if (result.hasErrors()) {
			if(supData.getSupSupId() != null){
				model1.addObject("addORupdatePrheading","Update Supplier");
				model1.addObject("addOrUpdate","updateProduct");
			}else{
				model1.addObject("addORupdatePrheading","Add Supplier");
			}
			return model1;
		}else{
			if(supData != null && supData.getSupSupId()!= null){
				int num = sUPIService.supUpdateSupplier(supData);
				model2.addObject("addORupdatePrheading","Update Supplier");
//				if(num == -1){
//					redir.addFlashAttribute("alreadyExist", "Supplier name "+supData.getSupSupplierName() +" Already exist!!");
//					return model3;
//				}
//				if(num == -2){
//					redir.addFlashAttribute("alreadyExist", "Supplier number "+supData.getSupSupplierNumber() +" Already exist!!");
//					return model3;
//				}
//				if(num == -3){
//					redir.addFlashAttribute("alreadyExist", "Contact number "+supData.getSupRegistrationNum() +" Already exist!!");
//					return model3;
//				}
				if(num > 0 ){
					redir.addFlashAttribute("msg", "Supplier "+supData.getSupSupplierName()+" Updated Successfully!!");
					return model2;
				}
				
			}else{
				int num = sUPIService.supAddSupplier(supData);
				model2.addObject("addORupdatePrheading","Add Supplier");
				if(num == -1){
					redir.addFlashAttribute("alreadyExist", "Supplier name "+supData.getSupSupplierName() +" Already exist!!");
					return model3;
				}
				if(num == -2){
					redir.addFlashAttribute("alreadyExist", "Supplier number "+supData.getSupSupplierNumber() +" Already exist!!");
					return model3;
				}
				if(num == -3){
					redir.addFlashAttribute("alreadyExist", "Contact number "+supData.getSupRegistrationNum() +" Already exist!!");
					return model3;
				}
				if(num > 0 ){
					redir.addFlashAttribute("msg", "Supplier "+supData.getSupSupplierName() +" Added Successfully!!");
					return model2;
				}
			}
		}
		logger.info("INSIDE SUPController END METHOD supSaveSupplier");
		return model2; 
	}

	// UPDATING THE PRODUCT..
	@RequestMapping(value = "/updateSupplier/{sup_Id}" , method = RequestMethod.GET)
	public ModelAndView supUpdateSupplier(@PathVariable("sup_Id") Integer sup_Id){
		logger.info("INSIDE SUPController START METHOD supUpdateSupplier");
		ModelAndView model = new ModelAndView("suppliermanagement/addORUpdateSupplier");
		model.addObject("SupData",sUPIService.supFindSupplierBySupId(sup_Id));
		model.addObject("addORupdatePrheading","Update Supplier");
		model.addObject("addOrUpdate","updateSupplier");
		model.addObject("SupplierStatusList", getSupplierStatusList());
		List<String> calibServiceTypeList = getSupplierServiceType();
		model.addObject("SupplierServiceTypeList", calibServiceTypeList);
		logger.info("INSIDE SUPController END METHOD supUpdateSupplier");
		return model;
	}

	@RequestMapping(value = "/searchSupplier")
	public ModelAndView supSearchSupplierByCondition(HttpServletRequest request, HttpServletResponse response){
		logger.info("INSIDE SUPController START METHOD supSearchSupplierByCondition");
		ModelAndView model = new ModelAndView("suppliermanagement/supplierDetails");

		String supplierName = request.getParameter("supplierName");
		String supplierNumber = request.getParameter("supplierNumber");
		String supplierStatus = request.getParameter("supplierStatus");
		if(supplierName != null || supplierNumber != null || supplierStatus != null){
			model.addObject("supplierList",sUPIService.supSearchByCondition(supplierName, supplierNumber, supplierStatus));
		}else{
			model.addObject("supplierList", sUPIService.supGetSuppliersDetails());
		}
		model.addObject("SupplierStatusList", getSupplierStatusList());
		logger.info("INSIDE SUPController END METHOD supSearchSupplierByCondition");
		return model;
	}	

	@RequestMapping(value = "/showSupplierDetail" , method = RequestMethod.GET)
	public ModelAndView supShowSupplierDetailBySupplierNum(@RequestParam("Supplier_Num") String Supplier_Num, RedirectAttributes redir){
		logger.info("INSIDE SUPController START METHOD calibUpdateForApprove");

		ModelAndView  model = new ModelAndView("suppliermanagement/viewSupplierDetails");
		SUPData sUPData = sUPIService.supShowSupplierDetailBySupplierNum(Supplier_Num);
		String address = sUPData.getSupAddressLine1() 
				+" "+sUPData.getSupAddressLine2()
				+" "+sUPData.getSupAddressLine3() 
				+" "+sUPData.getSupAddressLine4() 
				+" "+sUPData.getSupAddressLine5() 
				+" "+sUPData.getSupAddressLine6();
		sUPData.setSupFullAddress(address);
		model.addObject("supData",sUPData);
		logger.info("INSIDE SUPController END METHOD calibUpdateForApprove");
		return model;
	}

	private List<String> getSupplierStatusList() {
		List<String>  SupplierStatusList = new ArrayList<String>();
		SupplierStatusList.add("Active");
		SupplierStatusList.add("Inactive");
		return SupplierStatusList;
	}
	
	
	private List<String> getSupplierServiceType() {
		return lOVIService.getSupplierServiceType();
	}
	
}
