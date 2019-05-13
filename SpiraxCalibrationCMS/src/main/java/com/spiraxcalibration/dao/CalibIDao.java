package com.spiraxcalibration.dao;

import java.util.List;

import com.spiraxcalibration.models.CalibData;
import com.spiraxcalibration.models.CalibMainData;
import com.spiraxcalibration.models.PrData;

public interface CalibIDao {
	List<PrData> calibGetProductDetails();
	List<PrData> calibSearchByCondition(String identity, String serialNumber, String location, String calibAgency);
	List<CalibData> calibGetCalibrationsById(Integer prodId);
	int calibSaveCalibrationDetail(CalibData calibData);
	CalibMainData calibGetCalibrationById(Integer calibId);
	//	CalibData calibGetCalibrationById(Integer calibId);

	int calibGetcalibSatusCountIfCalibratedByProdID(Integer prodId);
	int calibUpdateCalibDetails(CalibData calibData);
	CalibData calibGetCalibrationByCertId(Integer warrantyId);
	int calibSaveApprover(CalibData calibData);
	int setPendingForApproval(Integer calibId);
	int calibApprovedOrReject(CalibData calibData);
}
