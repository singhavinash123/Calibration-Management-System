package com.spiraxcalibration.servicesIMPL;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spiraxcalibration.dao.AlertIDao;
import com.spiraxcalibration.models.CalibMainData;
import com.spiraxcalibration.services.AlertIService;

@Service
public class AlertServiceIMPL implements AlertIService{

	Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	AlertIDao alertIDao;
	
	@Override
	public List<CalibMainData> getIfReminderDateEqualToSysDate() {
		logger.info("INSIDE CalibServiceIMPL START METHOD getIfReminderDateEqualToSysDate ::");
		List<CalibMainData> list =  alertIDao.getIfReminderDateEqualToSysDate();
		logger.info("INSIDE CalibServiceIMPL END METHOD getIfReminderDateEqualToSysDate ::");
		return list;
	}

	@Override
	public List<CalibMainData> getIfDueDateEqualToSysDate() {
		logger.info("INSIDE CalibServiceIMPL START METHOD getIfReminderDateEqualToSysDate ::");
		List<CalibMainData> list =  alertIDao.getIfDueDateEqualToSysDate();
		logger.info("INSIDE CalibServiceIMPL END METHOD getIfReminderDateEqualToSysDate ::");
		return list;
	}

	@Override
	public void saveCalibDetailsForReminderDate(List<CalibMainData> calibMainDataReminderDate) {
		logger.info("INSIDE CalibServiceIMPL START METHOD saveCalibDetailsForReminderDate ::");
		logger.info("INSIDE CalibServiceIMPL END METHOD saveCalibDetailsForReminderDate ::");
		alertIDao.saveCalibDetailsForReminderDate(calibMainDataReminderDate);		
	}

	@Override
	public void saveCalibDetailsForDueDate(List<CalibMainData> calibMainDataDueDate) {
		logger.info("INSIDE CalibServiceIMPL START METHOD saveCalibDetailsForReminderDate ::");
		logger.info("INSIDE CalibServiceIMPL END METHOD saveCalibDetailsForReminderDate ::");
		alertIDao.saveCalibDetailsForDueDate(calibMainDataDueDate);	
	}

}
