package com.spiraxcalibration.servicesIMPL;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spiraxcalibration.dao.LookupIDao;
import com.spiraxcalibration.models.CalibFrequencyData;
import com.spiraxcalibration.models.PrEquipmentDescription;
import com.spiraxcalibration.models.UserData;
import com.spiraxcalibration.services.LookupIService;

@Service
public class LookupServiceIMPL implements LookupIService{

	Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	LookupIDao  lookupIDao;

	@Override
	public List<String> getMakeFromProduct() {
		logger.info("INSIDE LookupServiceIMPL START METHOD getMakeFromProduct ::");
		List<String> makeList =  lookupIDao.getMakeFromProduct();
		if(makeList != null){
			return makeList;
		}else{
			logger.error("INSIDE LookupServiceIMPL ====> makeList :"+makeList);
		}
		logger.info("INSIDE LookupServiceIMPL END METHOD getMakeFromProduct ::");
		return makeList;
	}

	@Override
	public List<String> getLocationFromProduct() {
		logger.info("INSIDE LookupServiceIMPL START METHOD getLocationFromProduct ::");
		List<String> locationList = lookupIDao.getLocationFromProduct();
		if(locationList != null){
			return locationList;
		}else{
			logger.error("INSIDE LookupServiceIMPL =====> locationList :"+locationList);
		}
		logger.info("INSIDE LookupServiceIMPL END METHOD getLocationFromProduct ::");
		return locationList;
	}

	@Override
	public List<String> getCalibStandardFromProduct() {
		logger.info("INSIDE LookupServiceIMPL START METHOD getCalibStandardFromProduct ::");
		List<String> calibStandardList = lookupIDao.getCalibStandardFromProduct();
		if(calibStandardList != null){
			return calibStandardList;
		}else{
			logger.error("INSIDE LookupServiceIMPL ===> calibStandardList :"+calibStandardList);
		}
		logger.info("INSIDE LookupServiceIMPL END METHOD getCalibStandardFromProduct ::");
		return calibStandardList;
	}

	@Override
	public List<String> getInstruStatusFromProduct() {
		logger.info("INSIDE LookupServiceIMPL START METHOD getInstruStatusFromProduct ::");
		List<String> instrumentStatusList = lookupIDao.getInstruStatusFromProduct();
		if(instrumentStatusList != null){
			return instrumentStatusList;
		}else{
			logger.error("INSIDE LookupServiceIMPL ===> instrumentStatusList :"+instrumentStatusList);
		}
		logger.info("INSIDE LookupServiceIMPL END METHOD getInstruStatusFromProduct ::");
		return instrumentStatusList;
	}

	@Override
	public List<String> getCategoryFromProduct() {
		logger.info("INSIDE LookupServiceIMPL START METHOD getCategoryFromProduct ::");
		List<String> list =  lookupIDao.getCategoryFromProduct();
		logger.info("INSIDE LookupServiceIMPL END METHOD getCategoryFromProduct ::");
		return list;
	}

	@Override
	public List<String> getCalibAgencyFromProduct() {
		logger.info("INSIDE LookupServiceIMPL START METHOD getCalibAgencyFromProduct ::");
		List<String> calibAgencyList = lookupIDao.getCalibAgencyFromProduct();
		if(calibAgencyList != null){
			return calibAgencyList;
		}else{
			logger.error("INSIDE LookupServiceIMPL ===> :"+calibAgencyList);
		}
		logger.info("INSIDE LookupServiceIMPL END METHOD getCalibAgencyFromProduct ::");
		return 	calibAgencyList;
	}

	@Override
	public List<String> getCalibrationType() {
		logger.info("INSIDE LookupServiceIMPL START METHOD getCalibrationType ::");
		List<String> CalibrationTypeList = lookupIDao.getCalibrationType();
		if(CalibrationTypeList != null){
			return CalibrationTypeList;
		}else{
			logger.info("INSIDE LookupServiceIMPL ===> :"+CalibrationTypeList);
		}
		logger.info("INSIDE LookupServiceIMPL END METHOD getCalibrationType ::");
		return 	CalibrationTypeList;
	}

	@Override
	public List<String> getCalibstatus() {
		logger.info("INSIDE LookupServiceIMPL START METHOD getCalibstatus ::");
		List<String> CalibrationdStatusList = lookupIDao.getCalibstatus();
		if(CalibrationdStatusList != null){
			return CalibrationdStatusList;
		}else{
			logger.info("INSIDE LookupServiceIMPL ===> :"+CalibrationdStatusList);
		}
		logger.info("INSIDE LookupServiceIMPL END METHOD getCalibstatus ::");
		return 	CalibrationdStatusList;
	}

	@Override
	public List<String> getEquipmentStatus() {
		logger.info("INSIDE LookupServiceIMPL START METHOD getEquipmentStatus ::");
		List<String> EquipmentStatusList = lookupIDao.getEquipmentStatus();
		if(EquipmentStatusList != null){
			return EquipmentStatusList;
		}else{
			logger.info("INSIDE LookupServiceIMPL ===> :"+EquipmentStatusList);
		}
		logger.info("INSIDE LookupServiceIMPL END METHOD getEquipmentStatus ::");
		return 	EquipmentStatusList;
	}

	@Override
	public List<String> getSupplierName() {
		logger.info("INSIDE LookupServiceIMPL START METHOD getSupplierName ::");
		List<String> SupplierNameList = lookupIDao.getSupplierName();
		if(SupplierNameList != null){
			return SupplierNameList;
		}else{
			logger.info("INSIDE LookupServiceIMPL ===> :"+SupplierNameList);
		}
		logger.info("INSIDE LookupServiceIMPL END METHOD getSupplierName ::");
		return 	SupplierNameList;
	}

	@Override
	public HashMap<String, Integer> getCalibrationFreqType() {
		logger.info("INSIDE LookupServiceIMPL START METHOD getSupplierName ::");
		List<CalibFrequencyData> SupplierNameList = lookupIDao.getCalibrationFreqType();
		HashMap<String,Integer> freqMap = new HashMap<String,Integer>();
		for(CalibFrequencyData  calibFrequencyData : SupplierNameList){
			freqMap.put(calibFrequencyData.getCalibType().trim(), calibFrequencyData.getCalibFrequency());
		}
		logger.info("INSIDE LookupServiceIMPL END METHOD getSupplierName ::");
		return 	freqMap;
	}

	@Override
	public HashMap<String, String> getEquipmentDescription() {
		logger.info("INSIDE LookupServiceIMPL START METHOD getEquipmentDescription ::");
		List<PrEquipmentDescription> equipmentDescriptionList = lookupIDao.getEquipmentDescription();
		HashMap<String,String> equipDescMap = new HashMap<String,String>();
		for(PrEquipmentDescription  prEquipmentDescription : equipmentDescriptionList){
			equipDescMap.put(prEquipmentDescription.getPrEquipmentDescription(), prEquipmentDescription.getPrEquipmentIdentityNum());
		}
		logger.info("INSIDE LookupServiceIMPL END METHOD getEquipmentDescription ::");
		return 	equipDescMap;
	}

	@Override
	public List<String> getApproverMailIds() {
		logger.info("INSIDE LookupServiceIMPL START METHOD getApproverMailIds ::");
		List<String> ApproverMailIdList = lookupIDao.getApproverMailIds();
		if(ApproverMailIdList != null){
			return ApproverMailIdList;
		}else{
			logger.info("INSIDE LookupServiceIMPL ===> :"+ApproverMailIdList);
		}
		logger.info("INSIDE LookupServiceIMPL END METHOD getApproverMailIds ::");
		return 	ApproverMailIdList;
	}

	@Override
	public HashMap<String, String> getCalibFrequencyLookUp() {
		logger.info("INSIDE LookupServiceIMPL START METHOD getEquipmentDescription ::");
		List<PrEquipmentDescription> calibFrequencyList = lookupIDao.getCalibFrequencyLookUp();
		HashMap<String,String> equipDescMap = new HashMap<String,String>();
		for(PrEquipmentDescription  prEquipmentDescription : calibFrequencyList){
			equipDescMap.put(prEquipmentDescription.getPrEquipmentDescription(), prEquipmentDescription.getPrEquipmentIdentityNum());
		}
		logger.info("INSIDE LookupServiceIMPL END METHOD getEquipmentDescription ::");
		return 	equipDescMap;
	}

	@Override
	public List<UserData> getCalibUserData() {
		logger.info("INSIDE LookupServiceIMPL START METHOD getCalibUserData ::");
		List<UserData> userDataList = lookupIDao.getCalibUserData();
		for(UserData userData : userDataList){
			userData.setUserFullName(userData.getUserFirstName()+" "+userData.getUserLastName());
		}
		if(userDataList != null){
			return userDataList;
		}else{
			logger.info("INSIDE LookupServiceIMPL ===> :"+userDataList);
		}
		logger.info("INSIDE LookupServiceIMPL END METHOD getCalibUserData ::");
		return 	userDataList;
	}

	@Override
	public List<String> getSupplierServiceType() {
		logger.info("INSIDE LookupServiceIMPL START METHOD getSupplierServiceType ::");
		List<String> SupplierServiceTypeList = lookupIDao.getSupplierServiceType();
		if(SupplierServiceTypeList != null){
			return SupplierServiceTypeList;
		}else{
			logger.info("INSIDE LookupServiceIMPL ===> :"+SupplierServiceTypeList);
		}
		logger.info("INSIDE LookupServiceIMPL END METHOD getSupplierServiceType ::");
		return 	SupplierServiceTypeList;
	}

	@Override
	public List<String> getCalibAgencyLookUp() {
		logger.info("INSIDE LookupServiceIMPL START METHOD getCalibAgencyLookUp ::");
		List<String> getCalibAgencyLookUpList = lookupIDao.getCalibAgencyLookUp();
		if(getCalibAgencyLookUpList != null){
			return getCalibAgencyLookUpList;
		}else{
			logger.info("INSIDE LookupServiceIMPL ===> :"+getCalibAgencyLookUpList);
		}
		logger.info("INSIDE LookupServiceIMPL END METHOD getCalibAgencyLookUp ::");
		return 	getCalibAgencyLookUpList;
	}

	@Override
	public List<String> getCalibCategoryLookUp() {
		logger.info("INSIDE LookupServiceIMPL START METHOD getCalibCategoryLookUp ::");
		List<String> getCalibCategoryLookUpList = lookupIDao.getCalibCategoryLookUp();
		if(getCalibCategoryLookUpList != null){
			return getCalibCategoryLookUpList;
		}else{
			logger.info("INSIDE LookupServiceIMPL ===> :"+getCalibCategoryLookUpList);
		}
		logger.info("INSIDE LookupServiceIMPL END METHOD getCalibCategoryLookUp ::");
		return 	getCalibCategoryLookUpList;
	}

	@Override
	public List<String> getMakeLookUp() {
		logger.info("INSIDE LookupServiceIMPL START METHOD getMakeLookUp ::");
		List<String> getCalibCategoryLookUpList = lookupIDao.getCalibCategoryLookUp();
		if(getCalibCategoryLookUpList != null){
			return getCalibCategoryLookUpList;
		}else{
			logger.info("INSIDE LookupServiceIMPL ===> :"+getCalibCategoryLookUpList);
		}
		logger.info("INSIDE LookupServiceIMPL END METHOD getMakeLookUp ::");
		return 	getCalibCategoryLookUpList;
	}

	@Override
	public List<String> calibrationStandardLookUp() {
		logger.info("INSIDE LookupServiceIMPL START METHOD calibrationStandardLookUp ::");
		List<String> getcalibrationStandardLookUpList = lookupIDao.calibrationStandardLookUp();
		if(getcalibrationStandardLookUpList != null){
			return getcalibrationStandardLookUpList;
		}else{
			logger.info("INSIDE LookupServiceIMPL ===> :"+getcalibrationStandardLookUpList);
		}
		logger.info("INSIDE LookupServiceIMPL END METHOD calibrationStandardLookUp ::");
		return 	getcalibrationStandardLookUpList;
	}

	@Override
	public Object calibrationcertValidByLookUp() {
		logger.info("INSIDE LookupServiceIMPL START METHOD calibrationcertValidByLookUp ::");
		List<String> getcalibrationcertValidByLookUpList = lookupIDao.calibrationcertValidByLookUp();
		if(getcalibrationcertValidByLookUpList != null){
			return getcalibrationcertValidByLookUpList;
		}else{
			logger.info("INSIDE LookupServiceIMPL ===> :"+getcalibrationcertValidByLookUpList);
		}
		logger.info("INSIDE LookupServiceIMPL END METHOD calibrationcertValidByLookUp ::");
		return 	getcalibrationcertValidByLookUpList;
	}

	@Override
	public List<String> getCalibResultList() {
		logger.info("INSIDE LookupServiceIMPL START METHOD getCalibResultList ::");
		List<String> getCalibResultListList = lookupIDao.getCalibResultList();
		if(getCalibResultListList != null){
			return getCalibResultListList;
		}else{
			logger.info("INSIDE LookupServiceIMPL ===> :"+getCalibResultListList);
		}
		logger.info("INSIDE LookupServiceIMPL END METHOD getCalibResultList ::");
		return 	getCalibResultListList;
	}

	@Override
	public List<String> getCalibSourceList() {
		logger.info("INSIDE LookupServiceIMPL START METHOD getCalibSourceList ::");
		List<String> getCalibSourceList = lookupIDao.getCalibSourceList();
		if(getCalibSourceList != null){
			return getCalibSourceList;
		}else{
			logger.info("INSIDE LookupServiceIMPL ===> :"+getCalibSourceList);
		}
		logger.info("INSIDE LookupServiceIMPL END METHOD getCalibSourceList ::");
		return 	getCalibSourceList;
	}

}
