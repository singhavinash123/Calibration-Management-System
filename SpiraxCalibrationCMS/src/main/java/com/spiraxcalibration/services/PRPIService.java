package com.spiraxcalibration.services;

import java.util.List;

import com.spiraxcalibration.models.CalibMainData;
import com.spiraxcalibration.models.PRPData;
import com.spiraxcalibration.models.PrData;

public interface PRPIService {
	List<CalibMainData> pRPgetCalibDetails();
	List<PRPData> pRPgetDetailsByID(Integer pRp_Id);
	PRPData pRpSavePRDetailsForSupplierNumberBySupNum(String supplierNumber);
	List<PrData> pRpGetAllIdentificationNumber(String supplierNumber);
	PRPData updatePrpDetails(String calibId);
	List<CalibMainData> mainCalibSearchByCondition(String supplierid);
	List<PRPData> pRPgetprDetails();
	List<PRPData> pRPgetprDetailsByPrpIdFromItem(Integer pr_Id);
	PRPData getPrPDetailsByPrpID(Integer pr_Id);
	void saveApproverDetails(String apporver1Name, String apporver1Status, String apporver1Comments);
	int saveApproverDetails(PRPData pRPData);
	List<String> getSupplierNameList();
	List<String> getApprover1StatusList();
	List<String> getApprover2StatusList();
	List<String> getPrpIDList();
	List<PRPData> pRpSearchByCondition(String pRpId, String supplierName, String approver1Status, String approver2Status);
	List<CalibMainData> pRPgetCalibDetails(String supID);
	int saveApprover2Details(PRPData pRPData);
	String getRaisedPRMailID(String prpId);
	String getSupplierMailId(String supplierId);
	String getPRNumberByPrId(Integer getpRpPRId);
	String getProcurementMailId();
}
