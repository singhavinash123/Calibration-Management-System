package com.spiraxcalibration.services;

import java.util.List;

import com.spiraxcalibration.models.CalibData;

public interface ApprovalIService {
	List<CalibData> approveGetCalibDetails();
	List<CalibData> approveSearchCalibByCondition(String identity, String serialNumber);
}
