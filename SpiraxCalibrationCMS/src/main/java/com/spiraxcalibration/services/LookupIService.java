package com.spiraxcalibration.services;

import java.util.HashMap;
import java.util.List;

import com.spiraxcalibration.models.CalibMainData;
import com.spiraxcalibration.models.PrEquipmentDescription;
import com.spiraxcalibration.models.UserData;

public interface LookupIService {

	List<String> getMakeFromProduct();
	List<String> getLocationFromProduct();
	List<String> getCalibStandardFromProduct();
	List<String> getInstruStatusFromProduct();
	List<String> getCategoryFromProduct();
	List<String> getCalibAgencyFromProduct();
	List<String> getCalibrationType();
	List<String> getCalibstatus();
	List<String> getEquipmentStatus();
	List<String> getSupplierName();
	HashMap<String, Integer> getCalibrationFreqType();
	HashMap<String, String> getEquipmentDescription();
	List<String> getApproverMailIds();
	HashMap<String, String> getCalibFrequencyLookUp();
	List<UserData> getCalibUserData();
	List<String> getSupplierServiceType();
	List<String> getCalibAgencyLookUp();
	List<String> getCalibCategoryLookUp();
	List<String> getMakeLookUp();
	List<String> calibrationStandardLookUp();
	Object calibrationcertValidByLookUp();
	List<String> getCalibResultList();
	List<String> getCalibSourceList();

}
