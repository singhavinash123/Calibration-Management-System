package com.spiraxcalibration.servicesIMPL;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spiraxcalibration.dao.PrIDao;
import com.spiraxcalibration.models.PRPData;
import com.spiraxcalibration.models.PrData;
import com.spiraxcalibration.services.PrIService;

@Service
public class PrServiceIMPL implements PrIService{

	Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	PrIDao  prIDao;

	@Override
	public List<PrData> prGetProductDetails() {
		logger.info("INSIDE PrServiceIMPL START METHOD prGetProductDetails ::");
		List<PrData> prDataList = null;
		prDataList = prIDao.prGetProductDetails();
		if(prDataList != null){
			return prDataList;
		}else{
			logger.error("INSIDE PrServiceIMPL ===> prDataList :"+prDataList);
		}
		logger.info("INSIDE PrServiceIMPL END METHOD prGetProductDetails ::");
		return prDataList;
	}

	@Override
	public List<PrData> prSearchByCondition(String identityNumber, String srNum, String location, String make, String supplierName) {
		logger.info("INSIDE PrServiceIMPL START METHOD prSearchByCondition ::");
		List<PrData> prDataList = null;
		prDataList = prIDao.prSearchByCondition(identityNumber.trim(),srNum.trim(),location,make,supplierName);
		if(prDataList != null){
			return prDataList;
		}else{
			logger.error("INSIDE PrServiceIMPL ====> prDataList :"+prDataList);
		}
		logger.info("INSIDE PrServiceIMPL END METHOD prSearchByCondition ::");
		return prDataList;
	}

	@Override
	public void prUpdateProduct(PrData prData) {
		logger.info("INSIDE PrServiceIMPL START METHOD prUpdateProduct ::");
		logger.info("INSIDE PrServiceIMPL END METHOD prUpdateProduct ::");
		prIDao.prUpdateProduct(prData);
	}

	@Override
	public int prAddProduct(PrData prData) {
		return prIDao.prAddProduct(prData);
	}

	@SuppressWarnings("unused")
	@Override
	public PrData prFindProductByProdId(Integer prodId) {
		logger.info("INSIDE PrServiceIMPL START METHOD prFindProductByProdId ::");
		PrData prData = null;
		prData = prIDao.prFindProductByProdId(prodId);
		String indetityFicationNumber = prData.getPrIdentificationNo();
		if(prData != null){
			return prData;
		}else{
			logger.error("INSIDE PrServiceIMPL  ===> prData :"+prData);
		}
		logger.info("INSIDE PrServiceIMPL END METHOD prFindProductByProdId ::");
		return prData;
	}

	@Override
	public List<PRPData> pRpSearchByCondition(String pRpId, String supplierName, String approver1Status,
			String approver2Status) {
		return prIDao.pRpSearchByCondition(pRpId, supplierName, approver1Status, approver2Status);
	}

	//	@Override
	//	public List<String> getCategoryFromProduct() {
	//		logger.info("INSIDE PrServiceIMPL START METHOD getCategoryFromProduct ::");
	//		logger.info("INSIDE PrServiceIMPL END METHOD getCategoryFromProduct ::");
	//
	//		return prIDao.getCategoryFromProduct();	
	//	}
	//
	//	@Override
	//	public List<String> getCalibAgencyFromProduct() {
	//		logger.info("INSIDE PrServiceIMPL START METHOD getCalibAgencyFromProduct ::");
	//		logger.info("INSIDE PrServiceIMPL END METHOD getCalibAgencyFromProduct ::");
	//
	//		return prIDao.getCalibAgencyFromProduct();	
	//	}

	//	@Override
	//	public List<String> getCalibStandardFromProduct() {
	//		logger.info("INSIDE PrServiceIMPL START METHOD getCalibStandardFromProduct ::");
	//		logger.info("INSIDE PrServiceIMPL END METHOD getCalibStandardFromProduct ::");
	//
	//		return prIDao.getCalibStandardFromProduct();	
	//	}
	//
	//	@Override
	//	public List<String> getInstruStatusFromProduct() {
	//		logger.info("INSIDE PrServiceIMPL START METHOD getInstruStatusFromProduct ::");
	//		logger.info("INSIDE PrServiceIMPL END METHOD getInstruStatusFromProduct ::");
	//		return prIDao.getInstruStatusFromProduct();
	//	}

	//	@Override
	//	public List<String> getMakeFromProduct() {
	//		logger.info("INSIDE PrServiceIMPL START METHOD getMakeFromProduct ::");
	//		logger.info("INSIDE PrServiceIMPL END METHOD getMakeFromProduct ::");
	//		return prIDao.getMakeFromProduct();
	//	}
	//
	//	@Override
	//	public List<String> getLocationFromProduct() {
	//		logger.info("INSIDE PrServiceIMPL START METHOD getLocationFromProduct ::");
	//		logger.info("INSIDE PrServiceIMPL END METHOD getLocationFromProduct ::");
	//
	//		return prIDao.getLocationFromProduct();
	//	}

}
