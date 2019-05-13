package com.spiraxcalibration.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.spiraxcalibration.models.CalibMainData;
import com.spiraxcalibration.models.CertData;
import com.spiraxcalibration.services.CalibMainIService;
import com.spiraxcalibration.services.CertUploadDownloadIService;

@RestController
public class EquipmentListController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	
	@Autowired
	CalibMainIService  calibMainIService;
	
	@Autowired
	CertUploadDownloadIService  certUploadDownloadIService;
	
	@GetMapping("/product_master_list_by_id/{identityNum}")
	public List<CalibMainData> getEquipmentByIdentity(@PathVariable("identityNum") String identityNum){
		logger.info("INSIDE EquipmentListController START METHOD getEquipmentByIdentity");
		System.out.println("identityNum ::::"+identityNum);
		List<CalibMainData> equipmentList = calibMainIService.getEquipmentByIdentity(identityNum);
		System.err.println(equipmentList.size());
		logger.info("INSIDE EquipmentListController START METHOD getEquipmentByIdentity");
		return equipmentList;
	}
	
	@GetMapping("/master_view/")
	public List<CertData> getMasterViewCertificate(){
		logger.info("INSIDE EquipmentListController START METHOD getMasterViewCertificate");
		List<CertData> certDataList =  certUploadDownloadIService.getMasterViewCertificate();
		logger.info("INSIDE EquipmentListController END METHOD getMasterViewCertificate");
		return certDataList;
	}

	@GetMapping("/product_master_list/{pageNum}")
	public List<CalibMainData> getEquipmentList(@PathVariable("pageNum") int pageNum){
		logger.info("INSIDE EquipmentListController START METHOD getEquipmentList");
		List<CalibMainData> equipmentList = calibMainIService.getEquipmentList(pageNum);
		logger.info("INSIDE EquipmentListController END METHOD getEquipmentList");
		return equipmentList;
	}
}
