package com.spiraxcalibration.controllers;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

import com.spiraxcalibration.models.CalibMainData;
import com.spiraxcalibration.models.UserData;
import com.spiraxcalibration.services.CalibMainIService;
import com.spiraxcalibration.services.LOVIService;
import com.spiraxcalibration.services.LookupIService;
import com.spiraxcalibration.services.PrIService;
import com.spiraxcalibration.utils.MapUtils;

@Controller
public class CalibMainController {

	Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	PrIService prIService;

	@Autowired
	LOVIService  lOVIService;

	@Autowired
	LookupIService lookupIService;

	@Autowired
	CalibMainIService  calibMainIService;

	@RequestMapping(value="/calibrationMain")
	public ModelAndView getcalibrationMainPage(){
		logger.info("INSIDE CalibMainController START METHOD getcalibrationMainPage");

		ModelAndView model = new ModelAndView("calibrationmain/calibmain");
		ModelAndView model2 = new ModelAndView("redirect:/add_main_Calibration");

		model.addObject("equipmentDescWithNumber", getEquipmentDescriptionWithCode());
		model.addObject("supplierNameLookup",getSupplierNameLookup());

		CalibMainData calibMainData = calibMainIService.mainGetProductDetails();
		if(calibMainData == null){
			return model2;
		}
		String frequnecy = calibMainData.getMainCalibrationFrequency();

		Map<String, String> yearsMap = getYearsFromFrequencyValue(frequnecy);
		model.addObject("yearsMap",yearsMap);

		Map<String, String> monthsMap = getMonthsFromFrequencyValue(frequnecy);
		model.addObject("monthsMap",monthsMap);

		Map<String, String> daysMap = getDaysFromFrequencyValue(frequnecy);
		model.addObject("daysMap",daysMap);

		getColorForStatus(calibMainData);
		
		HashMap<String,Integer> mapFreque =  getCalibrationTypeWithFrequency();
		model.addObject("calibrationTypeLookup",mapFreque);

		model.addObject("equipmentForm",calibMainData);

		List<UserData> UserDataList = getCalibUserData();
		List<String> calibStatus = getCalibStatus();
		Map<String,String> calibFrequnecyYearsList = getcalibFrequnecyYearsList();
		Map<String,String> calibFrequnecyMonthsList = getcalibFrequnecyMonthsList();
		Map<String,String> calibFrequnecyDaysList = getcalibFrequnecyDaysList();
		List<String> calibAgencyList = getCalibAgencyList();
		List<String> calibCategoryList = getCalibCategoryList();
		List<String> calibAcceptanceCriteriaList = getAcceptanceCriteriaList();
		List<String> calibMakeList =  getCalibMakeList();
		List<String> calibModel = getCalibModelList();
		List<String> calibLocationList = getCalibLocationList();
		List<String> calibPartCodeList = getCalibPartCodeList();
		List<String> calibServiceTypeList = getSupplierServiceType();
		List<String> calibgetCalibCategoryLookUpList = getCalibCategoryLookUp();
		model.addObject("calibCategoryLookup",calibgetCalibCategoryLookUpList);
		model.addObject("UserDataList",UserDataList);
		model.addObject("calibCategoryLookup",calibCategoryList);
		model.addObject("calibAgencyListLookup",calibAgencyList);
		model.addObject("calibrationStatusLookup",calibStatus);
		model.addObject("calibFrequnecyYearsLookup",calibFrequnecyYearsList);
		model.addObject("calibFrequnecyMonthsLookup",calibFrequnecyMonthsList);
		model.addObject("calibFrequnecyDaysLookup",calibFrequnecyDaysList);
		model.addObject("calibPartCodeListLookup",calibPartCodeList);
		model.addObject("calibLocationLookup",calibLocationList);
		model.addObject("calibMakeLookup",calibMakeList);
		model.addObject("calibAcceptanceCriteriaLookup",calibAcceptanceCriteriaList);
		model.addObject("calibLocationLookup",calibLocationList);
		model.addObject("calibModelLookup",calibModel);
		model.addObject("supplierServiceTypeLookup",calibServiceTypeList);
		model.addObject("calibrationStandardLookUp",calibrationStandardLookUp());
		model.addObject("certificateValidatedBy",calibrationcertValidByLookUp());

		List<String> calibResultList = getCalibResultList();
		model.addObject("calibrationResultLookup",calibResultList);
		
		List<String> calibSourceList = getCalibSourceList();
		model.addObject("calibrationSourceLookup",calibSourceList);

		logger.info("INSIDE CalibMainController END METHOD getcalibrationMainPage");
		return model;

	}

	private Map<String, String> getDaysFromFrequencyValue(String frequnecy) {
		String[] frequencyArray = frequnecy.split(",");
		String days = MapUtils.getDaysFromMapByValue(frequencyArray[2]);
		Map<String, String> daysMap = new HashMap<String,String>();
		daysMap.put("days", days);
		daysMap.put("totalDays", frequencyArray[2]);
		return daysMap;
	}


	private Map<String, String> getMonthsFromFrequencyValue(String frequnecy) {
		String[] frequencyArray = frequnecy.split(",");
		String months = MapUtils.getMonthsFromMapByValue(frequencyArray[1]);
		Map<String, String> monthsMap = new HashMap<String,String>();
		monthsMap.put("months", months);
		monthsMap.put("daysInMonth", frequencyArray[1]);
		return monthsMap;
	}


	private Map<String, String> getYearsFromFrequencyValue(String frequnecy) {
		String[] frequencyArray = frequnecy.split(",");
		String years = MapUtils.getYearsFromMapByValue(frequencyArray[0]);
		Map<String, String> yearsMap = new HashMap<String,String>();
		if(years == null){
			yearsMap.put("years", "0 year");
			yearsMap.put("daysInYear", "0");
		}
		yearsMap.put("years", years);
		yearsMap.put("daysInYear", frequencyArray[0]);
		return yearsMap;
	}


	@RequestMapping(value="/products_list")
	public ModelAndView getEquipmentList(){
		logger.info("INSIDE CalibMainController START METHOD getEquipmentList");
		ModelAndView model = new ModelAndView("calibrationmain/masterEquipView2");
		logger.info("INSIDE CalibMainController END METHOD getEquipmentList");
		return model;
	}

	@RequestMapping(value="/add_main_Calibration")
	public ModelAndView mainSaveMainCalibration(){
		logger.info("INSIDE CalibMainController START METHOD mainSaveMainCalibration");

		ModelAndView model = new ModelAndView("calibrationmain/add_main_calbs");
		CalibMainData calibMainData = new CalibMainData();
		model.addObject("equipmentForm",calibMainData);
		model.addObject("equipmentDescWithNumber", getEquipmentDescriptionWithCode());
		model.addObject("supplierNameLookup",getSupplierNameLookup());

		HashMap<String,Integer> mapFreque =  getCalibrationTypeWithFrequency();
		model.addObject("calibrationTypeLookup",mapFreque);
		model.addObject("selectedFrequency", mapFreque.get("Statutory Calibration"));

		List<String> calibStatus = getCalibStatus();
		List<UserData> UserDataList = getCalibUserData();
		Map<String,String> calibFrequnecyYearsList = getcalibFrequnecyYearsList();
		Map<String,String> calibFrequnecyMonthsList = getcalibFrequnecyMonthsList();
		Map<String,String> calibFrequnecyDaysList = getcalibFrequnecyDaysList();

		List<String> calibMakeList =  getCalibMakeList();
		List<String> calibModel = getCalibModelList();
		List<String> calibLocationList = getCalibLocationList();
		List<String> calibPartCodeList = getCalibPartCodeList();
		List<String> calibAgencyList = getCalibAgencyList();
		List<String> calibCategoryList = getCalibCategoryList();
		List<String> calibAcceptanceCriteriaList = getAcceptanceCriteriaList();

		model.addObject("UserDataList",UserDataList);
		model.addObject("calibrationStatusLookup",calibStatus);
		model.addObject("calibFrequnecyYearsLookup",calibFrequnecyYearsList);
		model.addObject("calibFrequnecyMonthsLookup",calibFrequnecyMonthsList);
		model.addObject("calibFrequnecyDaysLookup",calibFrequnecyDaysList);
		model.addObject("calibCategoryLookup",calibCategoryList);
		model.addObject("calibAgencyListLookup",calibAgencyList);
		model.addObject("calibPartCodeListLookup",calibPartCodeList);
		model.addObject("calibLocationLookup",calibLocationList);
		model.addObject("calibModelLookup",calibModel);
		model.addObject("calibMakeLookup",calibMakeList);
		model.addObject("calibAcceptanceCriteriaLookup",calibAcceptanceCriteriaList);
		model.addObject("equipmentDescWithNumber", getEquipmentDescriptionWithCode());

		List<String> calibgetCalibCategoryLookUpList = getCalibCategoryLookUp();
		model.addObject("calibCategoryLookup",calibgetCalibCategoryLookUpList);
		model.addObject("calibrationStandardLookUp",calibrationStandardLookUp());
		model.addObject("certificateValidatedBy",calibrationcertValidByLookUp());

		List<String> calibServiceTypeList = getSupplierServiceType();
		model.addObject("supplierServiceTypeLookup",calibServiceTypeList);
		HashMap<String, String> calibFrequencyList = getCalibFrequencyLookUp();
		model.addObject("calibFrequencyLookup",calibFrequencyList);

		List<String> calibResultList = getCalibResultList();
		model.addObject("calibrationResultLookup",calibResultList);
		
		List<String> calibSourceList = getCalibSourceList();
		model.addObject("calibrationSourceLookup",calibSourceList);

		logger.info("INSIDE CalibMainController END METHOD mainSaveMainCalibration");
		return model;
	}

	private List<String> getCalibSourceList() {
		return lookupIService.getCalibSourceList();
	}

	private Object calibrationcertValidByLookUp() {
		return lookupIService.calibrationcertValidByLookUp();
	}

	@RequestMapping(value = "/save_main_calib_details" , method = RequestMethod.POST)
	public ModelAndView mainSaveCalibDetails(@ModelAttribute("equipmentForm") @Valid CalibMainData calibMainData,
			BindingResult result, RedirectAttributes redir, HttpServletRequest request){
		logger.info("INSIDE CalibMainController START METHOD mainSaveCalibDetails");
		ModelAndView  model1 = new ModelAndView("calibrationmain/add_main_calbs");
		ModelAndView  model2 = new ModelAndView("redirect:/calibrationMain");
		model1.addObject("addOrUpdate","Add Details");

		model1.addObject("equipmentDescWithNumber", getEquipmentDescriptionWithCode());
		model1.addObject("supplierNameLookup",getSupplierNameLookup());

		HashMap<String,Integer> mapFreque =  getCalibrationTypeWithFrequency();
		model1.addObject("calibrationTypeLookup",mapFreque);
		model1.addObject("selectedFrequency", mapFreque.get("Statutory Calibration"));

		List<String> calibStatus = getCalibStatus();
		model1.addObject("calibrationStatusLookup",calibStatus);

		List<String> calibMakeList =  getCalibMakeList();
		List<String> calibModel = getCalibModelList();
		List<String> calibLocationList = getCalibLocationList();
		List<String> calibPartCodeList = getCalibPartCodeList();
		List<String> calibAgencyList = getCalibAgencyList();
		List<String> calibCategoryList = getCalibCategoryList();
		List<String> calibAcceptanceCriteriaList = getAcceptanceCriteriaList();

		model1.addObject("calibCategoryLookup",calibCategoryList);
		model1.addObject("calibAgencyListLookup",calibAgencyList);
		model1.addObject("calibPartCodeListLookup",calibPartCodeList);
		model1.addObject("calibLocationLookup",calibLocationList);
		model1.addObject("calibModelLookup",calibModel);
		model1.addObject("calibMakeLookup",calibMakeList);
		model1.addObject("calibAcceptanceCriteriaLookup",calibAcceptanceCriteriaList);

		List<String> calibServiceTypeList = getSupplierServiceType();
		model1.addObject("supplierServiceTypeLookup",calibServiceTypeList);

		Map<String,String> calibFrequnecyYearsList = getcalibFrequnecyYearsList();
		Map<String,String> calibFrequnecyMonthsList = getcalibFrequnecyMonthsList();
		Map<String,String> calibFrequnecyDaysList = getcalibFrequnecyDaysList();

		model1.addObject("calibFrequnecyYearsLookup",calibFrequnecyYearsList);
		model1.addObject("calibFrequnecyMonthsLookup",calibFrequnecyMonthsList);
		model1.addObject("calibFrequnecyDaysLookup",calibFrequnecyDaysList);

		List<String> calibgetCalibCategoryLookUpList = getCalibCategoryLookUp();
		model1.addObject("calibCategoryLookup",calibgetCalibCategoryLookUpList);

		model1.addObject("calibrationStandardLookUp",calibrationStandardLookUp());
		model1.addObject("certificateValidatedBy",calibrationcertValidByLookUp());

		List<UserData> UserDataList = getCalibUserData();
		model1.addObject("UserDataList",UserDataList);

		List<String> calibResultList = getCalibResultList();
		model1.addObject("calibrationResultLookup",calibResultList);
		
		List<String> calibSourceList = getCalibSourceList();
		model1.addObject("calibrationSourceLookup",calibSourceList);

		HashMap<String, String> calibFrequencyList = getCalibFrequencyLookUp();
		model1.addObject("calibFrequencyLookup",calibFrequencyList);
		if (result.hasErrors()) {
			 if(calibMainData.getMainEcId() != null){
				 return model2;
			 }
			return model1;
		}else if(calibMainData != null && calibMainData.getMainEcId() != null){
			int num = calibMainIService.mainUpdateCalibDetails(calibMainData);
			if(num > 0){
				redir.addFlashAttribute("msg","Equipment "+calibMainData.getMainIdentificationId()+" updated successfully!!");
				return model2;
			}
		}else{
			if(calibMainData.getMainCalibrationStatus().isEmpty()){
				calibMainData.setMainCalibrationStatus("Calibrated");
			}
			//  SAVING...
			int num = calibMainIService.mainSaveCalibDetails(calibMainData);
			if(num == -1){
				model1.addObject("alreadyExist","Serial Number Already Exist!!");
				return model1;
			}else if(num == -2){
				model1.addObject("alreadyExist","Indentification Number Already Exist!!");
				return model1;
			}else if(num == -3){
				model1.addObject("alreadyExist","Supplier is not exist please select a existing supplier!!");
				return model1;
			}else if(num > 0 ){
				redir.addFlashAttribute("msg","Equipment successfully Added");
				return model2;
			}
		}
		logger.info("INSIDE CalibMainController END METHOD prSaveProduct");
		return model2; 
	}

	// SEARCHING THE PRODUCT..
	@RequestMapping(value = "/search_calib_equipment" ,method=RequestMethod.POST)
	public ModelAndView mainSearchEquipmentByCondition(HttpServletRequest request, HttpServletResponse response){
		logger.info("INSIDE CalibMainController START METHOD mainSearchEquipmentByCondition");
		ModelAndView model = new ModelAndView("calibrationmain/calibmain");
		String identiFicationNum = request.getParameter("identiFicationNum");
		HashMap<String,Integer> mapFreque =  getCalibrationTypeWithFrequency();
		model.addObject("equipmentDescWithNumber", getEquipmentDescriptionWithCode());
		model.addObject("supplierNameLookup",getSupplierNameLookup());
		model.addObject("calibrationTypeLookup",mapFreque);

		List<UserData> UserDataList = getCalibUserData();
		List<String> calibStatus = getCalibStatus();
		Map<String,String> calibFrequnecyYearsList = getcalibFrequnecyYearsList();
		Map<String,String> calibFrequnecyMonthsList = getcalibFrequnecyMonthsList();
		Map<String,String> calibFrequnecyDaysList = getcalibFrequnecyDaysList();
		List<String> calibAgencyList = getCalibAgencyList();
		List<String> calibCategoryList = getCalibCategoryList();
		List<String> calibAcceptanceCriteriaList = getAcceptanceCriteriaList();
		List<String> calibMakeList =  getCalibMakeList();
		List<String> calibModel = getCalibModelList();
		List<String> calibLocationList = getCalibLocationList();
		List<String> calibPartCodeList = getCalibPartCodeList();

		model.addObject("UserDataList",UserDataList);
		model.addObject("calibCategoryLookup",calibCategoryList);
		model.addObject("calibAgencyListLookup",calibAgencyList);
		model.addObject("calibrationStatusLookup",calibStatus);
		model.addObject("calibFrequnecyYearsLookup",calibFrequnecyYearsList);
		model.addObject("calibFrequnecyMonthsLookup",calibFrequnecyMonthsList);
		model.addObject("calibFrequnecyDaysLookup",calibFrequnecyDaysList);
		model.addObject("calibPartCodeListLookup",calibPartCodeList);
		model.addObject("calibLocationLookup",calibLocationList);
		model.addObject("calibMakeLookup",calibMakeList);
		model.addObject("calibAcceptanceCriteriaLookup",calibAcceptanceCriteriaList);
		model.addObject("calibLocationLookup",calibLocationList);
		model.addObject("calibModelLookup",calibModel);
		List<String> calibServiceTypeList = getSupplierServiceType();
		model.addObject("supplierServiceTypeLookup",calibServiceTypeList);
		model.addObject("calibrationStandardLookUp",calibrationStandardLookUp());
		model.addObject("certificateValidatedBy",calibrationcertValidByLookUp());
		
		List<String> calibSourceList = getCalibSourceList();
		model.addObject("calibrationSourceLookup",calibSourceList);

		CalibMainData calibMainData = null; 

		if(identiFicationNum != null && !identiFicationNum.isEmpty()){
			calibMainData = calibMainIService.mainSearchByCondition(identiFicationNum);
			if(calibMainData == null){
				model.addObject("errorMsg", "Equipment number "+identiFicationNum+" is not exist!!");
				calibMainData = calibMainIService.mainGetProductDetails();
				getColorForStatus(calibMainData);

				String frequnecy = calibMainData.getMainCalibrationFrequency();

				Map<String, String> yearsMap = getYearsFromFrequencyValue(frequnecy);
				model.addObject("yearsMap",yearsMap);

				Map<String, String> monthsMap = getMonthsFromFrequencyValue(frequnecy);
				model.addObject("monthsMap",monthsMap);

				Map<String, String> daysMap = getDaysFromFrequencyValue(frequnecy);
				model.addObject("daysMap",daysMap);
				model.addObject("equipmentForm", calibMainData);
				return model;
			}
			getColorForStatus(calibMainData);
			model.addObject("equipmentForm" , calibMainData);
		}else{
			calibMainData = calibMainIService.mainGetProductDetails();
			getColorForStatus(calibMainData);
			model.addObject("equipmentForm", calibMainData);
		}

		String frequnecy = calibMainData.getMainCalibrationFrequency();

		Map<String, String> yearsMap = getYearsFromFrequencyValue(frequnecy);
		model.addObject("yearsMap",yearsMap);

		Map<String, String> monthsMap = getMonthsFromFrequencyValue(frequnecy);
		model.addObject("monthsMap",monthsMap);

		Map<String, String> daysMap = getDaysFromFrequencyValue(frequnecy);
		model.addObject("daysMap",daysMap);

		logger.info("INSIDE CalibMainController END METHOD mainSearchEquipmentByCondition");
		return model;
	}

	// SEARCHING THE PRODUCT..
	@RequestMapping(value = "/details_equipment_id/{identityNum}" ,method=RequestMethod.GET)
	public ModelAndView mainSearchEquipmentById(@PathVariable("identityNum") String idNum,HttpServletRequest request){
		request.setAttribute("", idNum);
		ModelAndView  model2 = new ModelAndView("redirect:/search_calib_equipment");
		return model2;
	}

	private void getColorForStatus(CalibMainData calibMainData) {
		if(calibMainData.getMainCalibrationStatus().equalsIgnoreCase("Inactive")){
			calibMainData.setColorFlag("red");
		}else if(calibMainData.getMainCalibrationStatus().equalsIgnoreCase("Active")){
			calibMainData.setColorFlag("#7cfc00");
		}else if(calibMainData.getMainCalibrationStatus().equalsIgnoreCase("Calibrated")){
			calibMainData.setColorFlag("#7cfc00");
		}
	}


	private List<String> getCalibPartCodeList() {
		return lOVIService.getCalibPartCodeList();
	}

	private List<String> getCalibAgencyList() {
		return lOVIService.getCalibAgencyList();
	}

	private List<String> getCalibCategoryList() {
		return lOVIService.getCalibCategoryList();
	}

	private List<String> getAcceptanceCriteriaList() {
		return lOVIService.getAcceptanceCriteriaList();
	}

	private List<String> getCalibLocationList() {
		return lOVIService.getCalibLocationList();
	}

	private List<String> getCalibModelList() {
		return lOVIService.getCalibModelList();
	}

	private List<String> getCalibMakeList() {
		return lOVIService.getCalibMakeList();
	}

	private List<String> getCalibResultList() {
		return lookupIService.getCalibResultList();
	}
	private List<String> calibrationStandardLookUp() {
		return lookupIService.calibrationStandardLookUp();
	}


	private Map<String,String> getcalibFrequnecyDaysList() {
		Map<String,String> map = new LinkedHashMap<String,String>();
		map.put("0 day", "0");
		map.put("1 day", "1");
		map.put("2 day", "2");
		map.put("3 day", "3");
		map.put("4 day", "4");
		map.put("5 day", "5");
		map.put("6 day", "6");
		map.put("7 day", "7");
		map.put("8 day", "8");
		map.put("9 day", "9");
		map.put("10 day", "10");
		map.put("11 day", "11");
		map.put("12 day", "12");
		map.put("13 day", "13");
		map.put("14 day", "14");
		map.put("15 day", "15");
		map.put("16 day", "16");
		map.put("17 day", "17");
		map.put("18 day", "18");
		map.put("19 day", "19");
		map.put("20 day", "20");
		map.put("21 day", "21");
		map.put("22 day", "22");
		map.put("23 day", "23");
		map.put("24 day", "24");
		map.put("25 day", "25");
		map.put("26 day", "26");
		map.put("27 day", "27");
		map.put("28 day", "28");
		map.put("29 day", "29");
		map.put("30 day", "30");
		map.put("31 day", "31");
		return map;
	}

	private Map<String,String> getcalibFrequnecyMonthsList() {
		Map<String,String> map = new LinkedHashMap<String,String>();
		map.put("0 month", "0");
		map.put("1 month", "30");
		map.put("2 month", "60");
		map.put("3 month", "90");
		map.put("4 month", "120");
		map.put("5 month", "150");
		map.put("6 month", "180");
		map.put("7 month", "210");
		map.put("8 month", "240");
		map.put("9 month", "270");
		map.put("10 month", "300");
		map.put("11 month", "330");
		map.put("12 month", "365");

		return map;
	}

	private Map<String,String> getcalibFrequnecyYearsList() {
		Map<String,String> map = new LinkedHashMap<String,String>();
		map.put("0 Year", "0");
		map.put("1 Year", "364");
		map.put("2 Year", "730");
		map.put("3 Year", "1095");
		map.put("4 Year", "1460");
		map.put("5 Year", "1825");
		map.put("6 Year", "2190");
		map.put("7 Year", "2555");
		map.put("8 Year", "2920");
		map.put("9 Year", "3285");
		map.put("10 Year", "3650");
		map.put("11 Year", "4015");
		map.put("12 Year", "4380");

		return map;
	}

	private List<String> getSupplierServiceType() {
		return lOVIService.getSupplierServiceType();

	}

	private List<UserData> getCalibUserData() {
		return lookupIService.getCalibUserData();

	}

	private HashMap<String, String> getCalibFrequencyLookUp() {
		return lookupIService.getCalibFrequencyLookUp();
	}

	private List<String> getCalibCategoryLookUp() {
		return lookupIService.getCalibCategoryLookUp();
	}

	private List<String> getCalibStatus() {
		return lookupIService.getCalibstatus();
	}

	private HashMap<String, Integer> getCalibrationTypeWithFrequency() {
		HashMap<String, Integer>  mapFreque = lookupIService.getCalibrationFreqType();
		return mapFreque;
	}

	private HashMap<String, String> getEquipmentDescriptionWithCode() {
		HashMap<String, String> equipmentDescription = lookupIService.getEquipmentDescription();
		return equipmentDescription;
	}

	private List<String> getSupplierNameLookup() {
		return lookupIService.getSupplierName();
	}

}
