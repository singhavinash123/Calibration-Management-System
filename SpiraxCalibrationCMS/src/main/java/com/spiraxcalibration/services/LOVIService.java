package com.spiraxcalibration.services;

import java.util.HashMap;
import java.util.List;

import com.spiraxcalibration.models.LOVData;

public interface LOVIService {
			public List<LOVData> getLovDetails();
			public LOVData findLOVByLid(Integer lovId);
			public int updateLov(LOVData lOVData);
			public int addLov(LOVData lOVData);
			public int deleteLov(Integer lovId);
			public List<String> getEquipmentType();
			public List<LOVData> getProcessNames();
			public HashMap<String, String> getLovProcessWithName();
			public List<String> getLovNamesByLovProcess(int lovIdbyLovProcess);
			public List<LOVData> lovSearchLovByCondition(String lovProcess, String lovProcess2);
			public List<String> getCertificateOptionName();
			public List<String> getUserEmails();
			public String getUserEmailsForApprover1();
			public String getUserEmailsForApprover2();
			public List<String> getCalibPartCodeList();
			public List<String> getCalibAgencyList();
			public List<String> getCalibCategoryList();
			public List<String> getAcceptanceCriteriaList();
			public List<String> getCalibLocationList();
			public List<String> getCalibModelList();
			public List<String> getCalibMakeList();
			public List<String> getSupplierServiceType();
			public List<String> getLovNameList();
}
