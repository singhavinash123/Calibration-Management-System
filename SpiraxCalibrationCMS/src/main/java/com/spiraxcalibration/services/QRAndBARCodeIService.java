package com.spiraxcalibration.services;

import java.util.List;

import com.spiraxcalibration.models.CalibData;
import com.spiraxcalibration.models.CalibMainData;
import com.spiraxcalibration.models.PRPData;

public interface QRAndBARCodeIService {
	List<CalibMainData> detailsForQrAndBarCode(Integer calib_Id);
	List<CalibData> QR_BARgetCalibrationDetails();
	List<String> QR_BARgetIdentityNumberFromCalibration();
	List<String> QR_BARgetSerialNumberFromCalibration();
	List<CalibData> QRAndBARSearchProductByCondition(String identity, String serialNumber);
	//List<CalibData> detailsForQrAndBarCodeScan(String identityFicationNum);
	CalibMainData detailsForQrAndBarCodeScan(String identityFicationNum);
	PRPData getThePrInformationByIdentyNum(String identityFicationNum);

}
