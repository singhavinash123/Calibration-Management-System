package com.spiraxcalibration.services;

import java.util.List;

import javax.validation.Valid;

import com.spiraxcalibration.models.CalibMainData;

public interface CalibMainIService {
	int mainSaveCalibDetails(CalibMainData calibMainData);
	CalibMainData mainSearchByCondition(String identiFicationNum);
	CalibMainData mainGetProductDetails();
	int mainUpdateCalibDetails(@Valid CalibMainData calibMainData);
	List<CalibMainData> getEquipmentCalibList();
	List<CalibMainData> getEquipmentList(int pageNum);
	List<CalibMainData> getEquipmentByIdentity(String identityNum);
}
