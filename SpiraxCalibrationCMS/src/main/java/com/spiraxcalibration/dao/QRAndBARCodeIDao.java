package com.spiraxcalibration.dao;

import java.util.List;

import com.spiraxcalibration.models.CalibData;
import com.spiraxcalibration.models.CalibMainData;
import com.spiraxcalibration.models.PRPData;

public interface QRAndBARCodeIDao {
	List<CalibData> QR_BARgetCalibrationDetails();
	List<CalibMainData> detailsForQrAndBarCode(Integer calib_Id);
	List<String> QR_BARgetIdentityNumberFromCalibration();
	List<String> QR_BARgetSerialNumberFromCalibration();
	List<CalibData> QRAndBARSearchProductByCondition(String identity, String serialNumber);
	CalibMainData detailsForQrAndBarCodeScan(String identityFicationNum);
	PRPData getThePrInformationByIdentyNum(String identityFicationNum);
//	List<CalibData> detailsForQrAndBarCodeScan(String identityFicationNum);

}
