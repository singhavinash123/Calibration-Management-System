package com.spiraxcalibration.dao;

import java.util.List;

import com.spiraxcalibration.models.CalibFrequencyData;
import com.spiraxcalibration.models.CalibMainData;
import com.spiraxcalibration.models.PrEquipmentDescription;
import com.spiraxcalibration.models.UserData;

public interface LookupIDao {

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
	List<CalibFrequencyData> getCalibrationFreqType();
	List<PrEquipmentDescription> getEquipmentDescription();
	List<String> getApproverMailIds();
	List<PrEquipmentDescription> getCalibFrequencyLookUp();
	List<UserData> getCalibUserData();
	List<String> getSupplierServiceType();
	List<String> getCalibAgencyLookUp();
	List<String> getCalibCategoryLookUp();
	List<String> calibrationStandardLookUp();
	List<String> calibrationcertValidByLookUp();
	List<String> getCalibResultList();
	List<String> getCalibSourceList();

}
