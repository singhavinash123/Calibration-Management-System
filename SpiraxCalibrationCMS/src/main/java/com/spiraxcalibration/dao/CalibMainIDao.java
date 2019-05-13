package com.spiraxcalibration.dao;

import java.util.List;

import com.spiraxcalibration.models.CalibMainData;

public interface CalibMainIDao {

	int mainSaveCalibDetails(CalibMainData calibMainData);
	CalibMainData mainSearchByCondition(String identiFicationNum);
	CalibMainData mainGetProductDetails();
	int mainUpdateCalibDetails(CalibMainData calibMainData);
	List<CalibMainData> getEquipmentCalibList();
	List<CalibMainData> getEquipmentList(int lowerLimit,int upperLimit);
	List<CalibMainData> getEquipmentByIdentity(String identityNum);

}
