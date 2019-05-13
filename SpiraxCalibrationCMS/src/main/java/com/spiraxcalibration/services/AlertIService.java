package com.spiraxcalibration.services;

import java.util.List;

import com.spiraxcalibration.models.CalibMainData;

public interface AlertIService {

	List<CalibMainData> getIfReminderDateEqualToSysDate();
	List<CalibMainData> getIfDueDateEqualToSysDate();
	void saveCalibDetailsForReminderDate(List<CalibMainData> calibMainDataReminderDate);
	void saveCalibDetailsForDueDate(List<CalibMainData> calibMainDataDueDate);
}
