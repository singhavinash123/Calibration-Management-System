package com.spiraxcalibration.dao;

import java.util.List;

import com.spiraxcalibration.models.CalibMainData;

public interface AlertIDao {
	List<CalibMainData> getIfReminderDateEqualToSysDate();
	List<CalibMainData> getIfDueDateEqualToSysDate();
	void saveCalibDetailsForReminderDate(List<CalibMainData> calibMainDataReminderDate);
	void saveCalibDetailsForDueDate(List<CalibMainData> calibMainDataDueDate);

}
