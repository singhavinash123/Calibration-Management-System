package com.spiraxcalibration.dao;

import java.util.List;

import com.spiraxcalibration.models.PRPData;
import com.spiraxcalibration.models.PrData;

public interface PrIDao {
	public List<PrData> prGetProductDetails();
	public List<PrData> prSearchByCondition(String identityNumber, String srNum, String location, String make, String supplierName);
	public void prUpdateProduct(PrData prData);
	public int prAddProduct(PrData prData);
	public PrData prFindProductByProdId(Integer prodId);
//	public List<String> getCategoryFromProduct();
//	public List<String> getCalibAgencyFromProduct();
//	public List<String> getCalibStandardFromProduct();
//	public List<String> getInstruStatusFromProduct();
//	public List<String> getMakeFromProduct();
//	public List<String> getLocationFromProduct();
	public List<PRPData> pRpSearchByCondition(String pRpId, String supplierName, String approver1Status,
			String approver2Status);

}
