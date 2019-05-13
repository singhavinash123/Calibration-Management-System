package com.spiraxcalibration.dao;

import java.util.List;
import com.spiraxcalibration.models.LOVData;

public interface LOVDao {
	public List<LOVData> getLovDetails();
	public int addLov(LOVData lOVData);
	public int updateLov(LOVData lOVData);
	public int deleteLov(Integer lovId);
	public LOVData findLOVByLid(Integer lovId);
	public List<String> getEquipmentType();
	public List<LOVData> getProcessNames();
	public List<LOVData> getLovProcessWithName();
	public List<String> getLovNamesByLovProcess(int lovIdbyLovProcess);
	public List<LOVData> lovSearchLovByCondition(String trim, String trim2);
	public List<String> getCertificateOptionName();
	public List<String> getUserEmails();
	public String getUserEmailsForApprover1();
	public String getUserEmailsForApprover2();
	public List<String> getCalibMakeList();
	public List<String> getCalibModelList();
	public List<String> getCalibLocationList();
	public List<String> getAcceptanceCriteriaList();
	public List<String> getCalibCategoryList();
	public List<String> getCalibAgencyList();
	public List<String> getCalibPartCodeList();
	public List<String> getSupplierServiceType();
	public List<String> getLovNameList();

}
