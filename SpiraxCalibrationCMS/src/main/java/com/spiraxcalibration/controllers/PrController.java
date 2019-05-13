package com.spiraxcalibration.controllers;

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

import com.spiraxcalibration.models.PrData;
import com.spiraxcalibration.services.LOVIService;
import com.spiraxcalibration.services.LookupIService;
import com.spiraxcalibration.services.PrIService;

@Controller
public class PrController {
	Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	PrIService prIService;

	@Autowired
	LOVIService  lOVIService;

	@Autowired
	LookupIService lookupIService;

	// GETTING ALL THE PRODUCTS...
	@RequestMapping(value="/productlist",method=RequestMethod.GET)
	public ModelAndView prGetProductDetails(){
		logger.info("INSIDE PrController START METHOD prGetProductDetails ::");
		ModelAndView  model = new ModelAndView("products/productList");
		model.addObject("productList",prIService.prGetProductDetails());
		model.addObject("makeList",lookupIService.getMakeFromProduct());
		model.addObject("locationList",lookupIService.getLocationFromProduct());
		model.addObject("supplierList",lookupIService.getSupplierName());
		logger.info("INSIDE PrController END METHOD prGetProductDetails ::");
		return model;
	}

	// SEARCHING THE PRODUCT..
	@RequestMapping(value = "/searchProduct")
	public ModelAndView prSearchProductByCondition(HttpServletRequest request, HttpServletResponse response){
		logger.info("INSIDE PrController START METHOD searchProductByCondition");
		ModelAndView model = new ModelAndView("products/productList");
		String identity = request.getParameter("identityNum");
		String serialNumber = request.getParameter("serialNumber");
		String location = request.getParameter("location");
		String make = request.getParameter("make");
		String supplierName =  request.getParameter("supplier");
		if(identity != null || serialNumber != null  || location != null || make != null || supplierName != null){
			model.addObject("productList" , prIService.prSearchByCondition(identity, serialNumber, location, make , supplierName));
		}else{
			model.addObject("productList", prIService.prGetProductDetails());
		}
		model.addObject("makeList",lookupIService.getMakeFromProduct());
		model.addObject("locationList",lookupIService.getLocationFromProduct());
		model.addObject("supplierList",lookupIService.getSupplierName());

		logger.info("INSIDE PrController END METHOD searchProductByCondition");
		return model;
	}	

	// UPDATING THE PRODUCT..
	@RequestMapping(value = "/updateProduct/{prodId}" , method = RequestMethod.GET)
	public ModelAndView prUpdateProduct(@PathVariable("prodId") Integer prodId){
		logger.info("INSIDE PrController START METHOD prUpdateProduct");
		ModelAndView model = new ModelAndView("products/addORUpdateProduct");
		
		PrData  prData = prIService.prFindProductByProdId(prodId);
		HashMap<String, String> equipmentDescription = lookupIService.getEquipmentDescription();
		model.addObject("equipmentDescWithNumber", equipmentDescription);
		
		model.addObject("equipmentForm",prData);
		model.addObject("equipmentTypeLookup",lOVIService.getEquipmentType());
		model.addObject("categoryLookup",lookupIService.getCategoryFromProduct());
		model.addObject("calibrationAgencyLookup",lookupIService.getCalibAgencyFromProduct());
		model.addObject("equipmentLocationLookup",lookupIService.getLocationFromProduct());
		model.addObject("makeLookup",lookupIService.getMakeFromProduct());
		model.addObject("clibrationStandardLookup",lookupIService.getCalibStandardFromProduct());
		model.addObject("instrumentStatusLookup",lookupIService.getInstruStatusFromProduct());
		model.addObject("supplierNameLookup",lookupIService.getSupplierName());
		model.addObject("equipmentStatusLookup",lookupIService.getEquipmentStatus());
		model.addObject("addOrUpdate","Update Equipment");
		model.addObject("addORupdatePrheading","Update Equipment");
		model.addObject("disabled",true);

		logger.info("INSIDE PrController END METHOD prUpdateProduct");
		return model;
	}

	// ADDING THE NEW PRODUCT....
	@RequestMapping(value = "/addProduct" , method = RequestMethod.GET)
	public ModelAndView prAddProduct(){
		logger.info("INSIDE PrController START METHOD prAddProduct");
		ModelAndView model = new ModelAndView("products/addORUpdateProduct");
		
		PrData prData = new PrData();
		prData.setPrEquipmentStatus("New");
		model.addObject("equipmentForm",prData);
		
		HashMap<String, String> equipmentDescription = lookupIService.getEquipmentDescription();
		model.addObject("equipmentDescWithNumber", equipmentDescription);
		
		model.addObject("equipmentTypeLookup",lOVIService.getEquipmentType());
		model.addObject("categoryLookup",lookupIService.getCategoryFromProduct());
		model.addObject("calibrationAgencyLookup",lookupIService.getCalibAgencyFromProduct());
		model.addObject("equipmentLocationLookup",lookupIService.getLocationFromProduct());
		model.addObject("makeLookup",lookupIService.getMakeFromProduct());
		model.addObject("clibrationStandardLookup",lookupIService.getCalibStandardFromProduct());
		model.addObject("instrumentStatusLookup",lookupIService.getInstruStatusFromProduct());
		List<String> equipmentStatusLookup = lookupIService.getEquipmentStatus();
		//equipmentStatusLookup.remove(prData.getPrEquipmentStatus());
		model.addObject("equipmentStatusLookup",equipmentStatusLookup);
		model.addObject("supplierNameLookup",lookupIService.getSupplierName());

		model.addObject("addOrUpdate","Add Equipment");
		model.addObject("addORupdatePrheading","Add Equipment");
		
		logger.info("INSIDE PrController END METHOD prAddProduct");
		return model;
	}

	// SAVING WHEN CLICK THE ADD AND UPDATE PRODUCT
	@RequestMapping(value = "/saveEquipment" , method = RequestMethod.POST)
	public ModelAndView prSaveProduct(@ModelAttribute("equipmentForm") @Valid PrData prData,
			BindingResult result, RedirectAttributes redir, HttpServletRequest request){
		logger.info("INSIDE PrController START METHOD prSaveProduct");
		String supplierName = request.getParameter("prSupplierName");
		prData.setPrSupplierName(supplierName);
		ModelAndView  model1 = new ModelAndView("products/addORUpdateProduct");
		ModelAndView  model2 = new ModelAndView("redirect:/productlist");
		model1.addObject("addOrUpdate","Add Equipment");
		model2.addObject("addOrUpdate","Add Equipment");
		
		HashMap<String, String> equipmentDescription = lookupIService.getEquipmentDescription();
		model1.addObject("equipmentDescWithNumber", equipmentDescription);
		
	//	model1.addObject("equipmentTypeLookup",lOVIService.getEquipmentType());
		model1.addObject("categoryLookup",lookupIService.getCategoryFromProduct());
		model1.addObject("calibrationAgencyLookup",lookupIService.getCalibAgencyFromProduct());
		model1.addObject("equipmentLocationLookup",lookupIService.getLocationFromProduct());
		model1.addObject("makeLookup",lookupIService.getMakeFromProduct());
		model1.addObject("clibrationStandardLookup",lookupIService.getCalibStandardFromProduct());
		model1.addObject("instrumentStatusLookup",lookupIService.getInstruStatusFromProduct());
		model1.addObject("equipmentStatusLookup",lookupIService.getEquipmentStatus());
		model1.addObject("supplierNameLookup",lookupIService.getSupplierName());

		if (result.hasErrors()) {
			if(prData.getPrProdId() != null){
				model1.addObject("addORupdatePrheading","Update Equipment");
				model1.addObject("addOrUpdate","Update Equipment");
			}else{
				model1.addObject("addORupdatePrheading","Add Equipment");
			}
			return model1;
		}else{
			if(prData != null && prData.getPrProdId() != null){
				// UPDATING...
				prIService.prUpdateProduct(prData);
				model2.addObject("addORupdatePrheading","Update Equipment");
				redir.addFlashAttribute("msg","Equipment "+prData.getPrIdentificationNo()+" successfully Updated");
				return model2;
			}else{
				int num = prIService.prAddProduct(prData);
				//  ADDING..
				model2.addObject("addORupdatePrheading","Add Equipment");
				// num == 0 for already exist data...
				if(num == -1){
					model1.addObject("alreadyExist","Serial Number Already Exist!!");
					model1.addObject("addORupdatePrheading","Add Equipment");
					return model1;
				}else if(num == -2){
					model1.addObject("alreadyExist","Indentification Number Already Exist!!");
					model1.addObject("addORupdatePrheading","Add Equipment");
					return model1;
				}else if(num > 0 ){
					redir.addFlashAttribute("msg","Equipment "+prData.getPrIdentificationNo()+" successfully Added");
					return model2;
				}
			}
		}
		logger.info("INSIDE PrController END METHOD prSaveProduct");
		return model2; 
	}

}	
