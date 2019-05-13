package com.spiraxcalibration.servicesIMPL;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spiraxcalibration.dao.LOVDao;
import com.spiraxcalibration.models.LOVData;
import com.spiraxcalibration.services.LOVIService;

@Service
public class LOVServiceIMPL implements LOVIService{
	Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	LOVDao lOVDao;

	@Override
	public List<LOVData> getLovDetails() {
		logger.info("INSIDE LOVServiceIMPL START METHOD getLovDetails ::");
		List<LOVData> lOVData = lOVDao.getLovDetails();
		if(lOVData == null){
			logger.error("INSIDE LOVServiceIMPL ===> lOVData :"+lOVData);
		}
		logger.info("INSIDE LOVServiceIMPL END METHOD getLovDetails ::");
		return lOVData;
	}

	@Override
	public LOVData findLOVByLid(Integer lovId) {
		logger.info("INSIDE LOVServiceIMPL START METHOD prFindLOVByLid ::");
		LOVData  lOVData = lOVDao.findLOVByLid(lovId);
		if(lOVData == null){
			logger.error("INSIDE LOVServiceIMPL ===> lOVData :"+lOVData);
		}
		logger.info("INSIDE LOVServiceIMPL END METHOD prFindLOVByLid ::");
		return lOVData;
	}

	@Override
	public int updateLov(LOVData lOVData) {
		logger.info("INSIDE LOVServiceIMPL START METHOD updateLov ::");
		int num =  lOVDao.updateLov(lOVData);
		logger.info("INSIDE LOVServiceIMPL END METHOD updateLov ::");
		return num;
	}

	@Override
	public int addLov(LOVData lOVData) {
		logger.info("INSIDE LOVServiceIMPL START METHOD addLov ::");
		String lovName = lOVData.getLovName().trim();
		lOVData.setLovName(lovName);
		int num = lOVDao.addLov(lOVData);
		logger.info("INSIDE LOVServiceIMPL END METHOD addLov ::");
		return num;
	}

	@Override
	public int deleteLov(Integer lovId) {
		logger.info("INSIDE LOVServiceIMPL START METHOD deleteLov ::");
		int num = lOVDao.deleteLov(lovId);
		logger.info("INSIDE LOVServiceIMPL END METHOD deleteLov ::");
		return num;
	}

	@Override
	public List<String> getEquipmentType() {
		logger.info("INSIDE LOVServiceIMPL START METHOD getEquipmentType ::");
		List<String> equipmentTypeList = lOVDao.getEquipmentType();
		if(equipmentTypeList != null){
			return equipmentTypeList;
		}else{
			logger.error("INSIDE LOVServiceIMPL ===> equipmentTypeList :"+equipmentTypeList);
		}
		logger.info("INSIDE LOVServiceIMPL END METHOD getEquipmentType ::");
		return 	equipmentTypeList;
	}

	@Override
	public List<LOVData> getProcessNames() {
		logger.info("INSIDE LOVServiceIMPL START METHOD getProcessNames ::");
		List<LOVData> processList = lOVDao.getProcessNames();	
		if(processList == null){
			logger.error("INSIDE LOVServiceIMPL ===> processList :"+processList);
		}
		logger.info("INSIDE LOVServiceIMPL END METHOD getProcessNames ::");
		return processList;
	}

	@Override
	public HashMap<String, String> getLovProcessWithName() {
		logger.info("INSIDE LookupServiceIMPL START METHOD getEquipmentDescription ::");
		List<LOVData> lovDataList = lOVDao.getLovProcessWithName();
		HashMap<String,String> lovProcessWithNameMap = new HashMap<String,String>();
		for(LOVData  lOVData : lovDataList){
			lovProcessWithNameMap.put(lOVData.getLovProcess(),lOVData.getLovName());
		}
		logger.info("INSIDE LookupServiceIMPL END METHOD getEquipmentDescription ::");
		return 	lovProcessWithNameMap;
	}

	@Override
	public List<String> getLovNamesByLovProcess(int lovIdbyLovProcess) {
		logger.info("INSIDE LOVServiceIMPL START METHOD getLovNamesByLovProcess ::");
		List<String> processNameList = lOVDao.getLovNamesByLovProcess(lovIdbyLovProcess);
		if(processNameList != null){
			return processNameList;
		}else{
			logger.error("INSIDE LOVServiceIMPL ===> getLovNamesByLovProcess :"+processNameList);
		}
		logger.info("INSIDE LOVServiceIMPL END METHOD getLovNamesByLovProcess ::");
		return 	processNameList;
	}

	@Override
	public List<LOVData> lovSearchLovByCondition(String lovProcess, String lovName) {
		logger.info("INSIDE LOVServiceIMPL START METHOD lovSearchLovByCondition ::");
		List<LOVData> lOVDataList = null;
		lOVDataList = lOVDao.lovSearchLovByCondition(lovProcess.trim(),lovName.trim());
		if(lOVDataList != null){
			return lOVDataList;
		}else{
			logger.error("INSIDE LOVServiceIMPL ====> prDataList :"+lOVDataList);
		}
		logger.info("INSIDE LOVServiceIMPL END METHOD lovSearchLovByCondition ::");
		return lOVDataList;
	}

	@Override
	public List<String> getCertificateOptionName() {
		logger.info("INSIDE LOVServiceIMPL START METHOD getCertificateOptionName ::");
		List<String> certificateNameList = lOVDao.getCertificateOptionName();
		if(certificateNameList != null){
			return certificateNameList;
		}else{
			logger.error("INSIDE LOVServiceIMPL ===> certificateNameList :"+certificateNameList);
		}
		logger.info("INSIDE LOVServiceIMPL END METHOD getCertificateOptionName ::");
		return 	certificateNameList;
	}

	@Override
	public List<String> getUserEmails() {
		logger.info("INSIDE LOVServiceIMPL START METHOD getUserEmails ::");
		List<String> userEmailList = lOVDao.getUserEmails();
		if(userEmailList != null){
			return userEmailList;
		}else{
			logger.error("INSIDE LOVServiceIMPL ===> userEmailList :"+userEmailList);
		}
		logger.info("INSIDE LOVServiceIMPL END METHOD getUserEmails ::");
		return 	userEmailList;
	}

	@Override
	public String getUserEmailsForApprover1() {
		logger.info("INSIDE LOVServiceIMPL START METHOD getUserEmailsForApprover1 ::");
		String userEmailList = lOVDao.getUserEmailsForApprover1();
		if(userEmailList != null){
			return userEmailList;
		}else{
			logger.error("INSIDE LOVServiceIMPL ===> userEmailList :"+userEmailList);
		}
		logger.info("INSIDE LOVServiceIMPL END METHOD getUserEmailsForApprover1 ::");
		return 	userEmailList;
	}

	@Override
	public String getUserEmailsForApprover2() {
		logger.info("INSIDE LOVServiceIMPL START METHOD getUserEmailsForApprover2 ::");
	    String userEmailList = lOVDao.getUserEmailsForApprover2();
		if(userEmailList != null){
			return userEmailList;
		}else{
			logger.error("INSIDE LOVServiceIMPL ===> userEmailList :"+userEmailList);
		}
		logger.info("INSIDE LOVServiceIMPL END METHOD getUserEmailsForApprover2 ::");
		return 	userEmailList;
	}

	@Override
	public List<String> getCalibPartCodeList() {
		logger.info("INSIDE LOVServiceIMPL START METHOD getUserEmailsForApprover2 ::");
	    List<String> getCalibPartCodeList = lOVDao.getCalibPartCodeList();
		if(getCalibPartCodeList != null){
			return getCalibPartCodeList;
		}else{
			logger.error("INSIDE LOVServiceIMPL ===> userEmailList :"+getCalibPartCodeList);
		}
		logger.info("INSIDE LOVServiceIMPL END METHOD getUserEmailsForApprover2 ::");
		return 	getCalibPartCodeList;
	}

	@Override
	public List<String> getCalibAgencyList() {
		logger.info("INSIDE LOVServiceIMPL START METHOD getCalibAgencyList ::");
	    List<String> getCalibAgencyList = lOVDao.getCalibAgencyList();
		if(getCalibAgencyList != null){
			return getCalibAgencyList;
		}else{
			logger.error("INSIDE LOVServiceIMPL ===> getCalibAgencyList :"+getCalibAgencyList);
		}
		logger.info("INSIDE LOVServiceIMPL END METHOD getCalibAgencyList ::");
		return 	getCalibAgencyList;
	}

	@Override
	public List<String> getCalibCategoryList() {
		logger.info("INSIDE LOVServiceIMPL START METHOD getCalibCategoryList ::");
	    List<String> getCalibCategoryList = lOVDao.getCalibCategoryList();
		if(getCalibCategoryList != null){
			return getCalibCategoryList;
		}else{
			logger.error("INSIDE LOVServiceIMPL ===> getCalibCategoryList :"+getCalibCategoryList);
		}
		logger.info("INSIDE LOVServiceIMPL END METHOD getCalibCategoryList ::");
		return 	getCalibCategoryList;
	}

	@Override
	public List<String> getAcceptanceCriteriaList() {
		logger.info("INSIDE LOVServiceIMPL START METHOD getAcceptanceCriteriaList ::");
	    List<String> getAcceptanceCriteriaList = lOVDao.getAcceptanceCriteriaList();
		if(getAcceptanceCriteriaList != null){
			return getAcceptanceCriteriaList;
		}else{
			logger.error("INSIDE LOVServiceIMPL ===> getAcceptanceCriteriaList :"+getAcceptanceCriteriaList);
		}
		logger.info("INSIDE LOVServiceIMPL END METHOD getAcceptanceCriteriaList ::");
		return 	getAcceptanceCriteriaList;
	}

	@Override
	public List<String> getCalibLocationList() {
		logger.info("INSIDE LOVServiceIMPL START METHOD getCalibLocationList ::");
	    List<String> getCalibLocationList = lOVDao.getCalibLocationList();
		if(getCalibLocationList != null){
			return getCalibLocationList;
		}else{
			logger.error("INSIDE LOVServiceIMPL ===> getCalibLocationList :"+getCalibLocationList);
		}
		logger.info("INSIDE LOVServiceIMPL END METHOD getCalibLocationList ::");
		return 	getCalibLocationList;
	}

	@Override
	public List<String> getCalibModelList() {
		logger.info("INSIDE LOVServiceIMPL START METHOD getCalibModelList ::");
	    List<String> getCalibModelList = lOVDao.getCalibModelList();
		if(getCalibModelList != null){
			return getCalibModelList;
		}else{
			logger.error("INSIDE LOVServiceIMPL ===> getCalibModelList :"+getCalibModelList);
		}
		logger.info("INSIDE LOVServiceIMPL END METHOD getCalibModelList ::");
		return 	getCalibModelList;
	}

	@Override
	public List<String> getCalibMakeList() {
		logger.info("INSIDE LOVServiceIMPL START METHOD getCalibMakeList ::");
	    List<String> getCalibMakeList = lOVDao.getCalibMakeList();
		if(getCalibMakeList != null){
			return getCalibMakeList;
		}else{
			logger.error("INSIDE LOVServiceIMPL ===> getCalibMakeList :"+getCalibMakeList);
		}
		logger.info("INSIDE LOVServiceIMPL END METHOD getCalibMakeList ::");
		return 	getCalibMakeList;
	}

	@Override
	public List<String> getSupplierServiceType() {
		logger.info("INSIDE LOVServiceIMPL START METHOD getSupplierServiceType ::");
	    List<String> getSupplierServiceTypeList = lOVDao.getSupplierServiceType();
		if(getSupplierServiceTypeList != null){
			return getSupplierServiceTypeList;
		}else{
			logger.error("INSIDE LOVServiceIMPL ===> getSupplierServiceTypeList :"+getSupplierServiceTypeList);
		}
		logger.info("INSIDE LOVServiceIMPL END METHOD getSupplierServiceType ::");
		return 	getSupplierServiceTypeList;
	}

	@Override
	public List<String> getLovNameList() {
		logger.info("INSIDE LOVServiceIMPL START METHOD getLovNameList ::");
	    List<String> getLovNameListList = lOVDao.getLovNameList();
		if(getLovNameListList != null){
			return getLovNameListList;
		}else{
			logger.error("INSIDE LOVServiceIMPL ===> getSupplierServiceTypeList :"+getLovNameListList);
		}
		logger.info("INSIDE LOVServiceIMPL END METHOD getLovNameList ::");
		return 	getLovNameListList;
	}
}
