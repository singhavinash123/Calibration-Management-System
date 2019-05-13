package com.spiraxcalibration.dao;

import java.util.List;

import com.spiraxcalibration.models.CalibData;

public interface ApprovalIDao {
	List<CalibData> approveGetCalibDetails();
	List<CalibData> approveSearchCalibByCondition(String identity, String serialNumber);

}
