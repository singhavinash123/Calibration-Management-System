package com.spiraxcalibration.services;

import java.util.List;

import com.spiraxcalibration.models.CalibData;
import com.spiraxcalibration.models.CalibMainData;
import com.spiraxcalibration.models.PrData;

public interface CalibIService {
	public List<PrData> calibGetProductDetails();
	public Object calibSearchByCondition(String identity, String serialNumber, String location, String calibAgency);
	public List<CalibData> calibGetCalibrationsById(Integer prodId);
	public int calibSaveCalibrationDetail(CalibData calibData);
	//public CalibData calibGetCalibrationById(Integer calibId);
	public CalibMainData calibGetCalibrationById(Integer calibId);
	public int calibGetcalibSatusCountIfCalibratedByProdID(Integer prodId);
	public int calibUpdateCalibDetails(CalibData calibData);
	public CalibData calibGetCalibrationByCertId(Integer warrantyId);
	public int calibSaveApprover(CalibData calibData);
	public int setPendingForApproval(Integer calibId);
	public int calibApprovedOrReject(CalibData calibData);
}
