package com.spiraxcalibration.servicesIMPL;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spiraxcalibration.dao.PRPIDao;
import com.spiraxcalibration.models.CalibMainData;
import com.spiraxcalibration.models.PRPData;
import com.spiraxcalibration.models.PrData;
import com.spiraxcalibration.services.PRPIService;

@Service
public class PRPServiceIMPL implements PRPIService{

	Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	PRPIDao pRPIDao;
	
	@Override
	public List<CalibMainData> pRPgetCalibDetails() {
		return pRPIDao.pRPgetCalibDetails();
	}

	@Override
	public List<PRPData> pRPgetDetailsByID(Integer pRp_Id) {
		logger.info("INSIDE THE PRPServiceIMPL START METHOD pRPgetDetailsByID");
		List<PRPData> list = pRPIDao.pRPgetDetailsByID(pRp_Id);
		logger.info("INSIDE THE PRPServiceIMPL END METHOD pRPgetDetailsByID");
		return list;
	}

	@Override
	public PRPData pRpSavePRDetailsForSupplierNumberBySupNum(String supplierNumber) {
		logger.info("INSIDE THE PRPServiceIMPL START METHOD pRpSavePRDetailsForSupplierNumberBySupNum");
		PRPData pPData =  pRPIDao.pRpSavePRDetailsForSupplierNumberBySupNum(supplierNumber);
		logger.info("INSIDE THE PRPServiceIMPL END METHOD pRpSavePRDetailsForSupplierNumberBySupNum");
		return pPData ;
	}

	@Override
	public List<PrData> pRpGetAllIdentificationNumber(String supplierNumber) {
		logger.info("INSIDE THE PRPServiceIMPL START METHOD pRpGetAllIdentificationNumber");
		List<PrData> list = pRPIDao.pRpGetAllIdentificationNumber(supplierNumber);
		logger.info("INSIDE THE PRPServiceIMPL END METHOD pRpGetAllIdentificationNumber");
		return list;
	}

	@Override
	public PRPData updatePrpDetails(String calibId) {
		logger.info("INSIDE THE PRPServiceIMPL START METHOD updatePrpDetails");
		PRPData pRPData =  pRPIDao.updatePrpDetails(calibId);
		logger.info("INSIDE THE PRPServiceIMPL END METHOD updatePrpDetails");
		return  pRPData;
		
	}

	@Override
	public List<CalibMainData> mainCalibSearchByCondition(String supplierid) {
		logger.info("INSIDE THE PRPServiceIMPL START METHOD mainCalibSearchByCondition");
		List<CalibMainData> list =  pRPIDao.mainCalibSearchByCondition(supplierid);
		logger.info("INSIDE THE PRPServiceIMPL END METHOD mainCalibSearchByCondition");
		return list;
	}

	@Override
	public List<PRPData> pRPgetprDetails() {
		logger.info("INSIDE THE PRPServiceIMPL START METHOD pRPgetprDetails");
		List<PRPData> list =  pRPIDao.pRPgetprDetails();
		logger.info("INSIDE THE PRPServiceIMPL END METHOD pRPgetprDetails");
		return list;
	}

	@Override
	public List<PRPData> pRPgetprDetailsByPrpIdFromItem(Integer pr_Id) {
		logger.info("INSIDE THE PRPServiceIMPL START METHOD pRPgetprDetailsByPrpIdFromItem");
		List<PRPData> list = pRPIDao.pRPgetprDetailsByPrpId(pr_Id);
		logger.info("INSIDE THE PRPServiceIMPL END METHOD pRPgetprDetailsByPrpIdFromItem");
		return list;
	}

	@Override
	public PRPData getPrPDetailsByPrpID(Integer pr_Id) {
		logger.info("INSIDE THE PRPServiceIMPL START METHOD getPrPDetailsByPrpID");
		PRPData pRPData = pRPIDao.getPrPDetailsByPrpID(pr_Id);
		logger.info("INSIDE THE PRPServiceIMPL END METHOD getPrPDetailsByPrpID");
		return pRPData;
	}

	@Override
	public void saveApproverDetails(String apporver1Name, String apporver1Status, String apporver1Comments) {
		logger.info("INSIDE THE PRPServiceIMPL START METHOD saveApproverDetails");
		logger.info("INSIDE THE PRPServiceIMPL END METHOD saveApproverDetails");
		pRPIDao.saveApproverDetails(apporver1Name,apporver1Status,apporver1Comments) ;
	}

	@Override
	public int saveApproverDetails(PRPData pRPData) {
		logger.info("INSIDE THE PRPServiceIMPL START METHOD saveApproverDetails");
		int num = pRPIDao.saveApproverDetails(pRPData);		
		logger.info("INSIDE THE PRPServiceIMPL END METHOD saveApproverDetails");
		return num;
	}

	@Override
	public List<String> getSupplierNameList() {
		logger.info("INSIDE THE PRPServiceIMPL START METHOD getSupplierNameList");
		List<String> list =  pRPIDao.getSupplierNameList();
		logger.info("INSIDE THE PRPServiceIMPL END METHOD getSupplierNameList");
		return list;
	}

	@Override
	public List<String> getApprover1StatusList() {
		logger.info("INSIDE THE PRPServiceIMPL START METHOD getApprover1StatusList");
		List<String> list =  pRPIDao.getApprover1StatusList();
		logger.info("INSIDE THE PRPServiceIMPL END METHOD getApprover1StatusList");
		return list;
	}

	@Override
	public List<String> getApprover2StatusList() {
		logger.info("INSIDE THE PRPServiceIMPL START METHOD getApprover2StatusList");
		List<String> list = pRPIDao.getApprover2StatusList();
		logger.info("INSIDE THE PRPServiceIMPL END METHOD getApprover2StatusList");
		return list;
	}

	@Override
	public List<String> getPrpIDList() {
		logger.info("INSIDE THE PRPServiceIMPL START METHOD getPrpIDList");
		List<String> list = pRPIDao.getPrpIDList();
		logger.info("INSIDE THE PRPServiceIMPL END METHOD getPrpIDList");
		return list;
	}

	@Override
	public List<PRPData> pRpSearchByCondition(String pRpId, String supplierName, String approver1Status,
			String approver2Status) {
		logger.info("INSIDE THE PRPServiceIMPL START METHOD pRpSearchByCondition");
		List<PRPData> list =  pRPIDao.pRpSearchByCondition(pRpId,supplierName,approver1Status,approver2Status);
		logger.info("INSIDE THE PRPServiceIMPL END METHOD pRpSearchByCondition");
		return list;
	}

	@Override
	public List<CalibMainData> pRPgetCalibDetails(String supID) {
		logger.info("INSIDE THE PRPServiceIMPL START METHOD pRPgetCalibDetails");
		List<CalibMainData> list =  pRPIDao.pRPgetCalibDetails(supID);
		logger.info("INSIDE THE PRPServiceIMPL END METHOD pRPgetCalibDetails");
		return list;
	}

	@Override
	public int saveApprover2Details(PRPData pRPData) {
		logger.info("INSIDE THE PRPServiceIMPL START METHOD saveApprover2Details");
		int num =  pRPIDao.saveApprover2Details(pRPData);
		logger.info("INSIDE THE PRPServiceIMPL END METHOD saveApprover2Details");
		return num;
	}

	@Override
	public String getRaisedPRMailID(String prpId) {
		logger.info("INSIDE THE PRPServiceIMPL START METHOD getRaisedPRMailID");
		String data =  pRPIDao.getRaisedPRMailID(prpId);
		logger.info("INSIDE THE PRPServiceIMPL END METHOD getRaisedPRMailID");
		return data;
	}

	@Override
	public String getSupplierMailId(String supplierId) {
		logger.info("INSIDE THE PRPServiceIMPL START METHOD getSupplierMailId");
		String data =  pRPIDao.getSupplierMailId(supplierId);	
		logger.info("INSIDE THE PRPServiceIMPL END METHOD getSupplierMailId");
		return data;
	}

	@Override
	public String getPRNumberByPrId(Integer pRpPRId) {
		logger.info("INSIDE THE PRPServiceIMPL START METHOD getPRNumberByPrId");
		String data =  pRPIDao.getPRNumberByPrId(pRpPRId);	
		logger.info("INSIDE THE PRPServiceIMPL END METHOD getPRNumberByPrId");
		return data;
	}

	@Override
	public String getProcurementMailId() {
		logger.info("INSIDE THE PRPServiceIMPL START METHOD getProcurementMailId");
		String procurementMailId =  pRPIDao.getProcurementMailId();	
		logger.info("INSIDE THE PRPServiceIMPL END METHOD getProcurementMailId");
		return procurementMailId;
	}

}
