package com.spiraxcalibration.alertschedular;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.spiraxcalibration.models.CalibMainData;
import com.spiraxcalibration.services.AlertIService;
import com.spiraxcalibration.utils.EmailUtils;

@Component
public class AlertController {
	Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	EmailUtils emailUtils;

	@Autowired
	AlertIService alertIService;

	@Scheduled(cron = "0 0 6,19 * * *")
	public void checkReminderDate() {
		logger.info("INSIDE THE AlertController START METHOD checkReminderDate");

		// check if reminder date is met the scheduler....
		List<CalibMainData> calibMainDataReminderDate  = alertIService.getIfReminderDateEqualToSysDate(); 
		if(calibMainDataReminderDate != null && !calibMainDataReminderDate.isEmpty()){
			for(CalibMainData calibMainData : calibMainDataReminderDate){
				logger.info("loop for active");
				if(!calibMainData.getMainCalibrationStatus().trim().equalsIgnoreCase("Scrap")){
					calibMainData.setMainCalibrationStatus("Active");
				}
			}
		}
		alertIService.saveCalibDetailsForReminderDate(calibMainDataReminderDate);
		List<CalibMainData> calibMainDataDueDate  = alertIService.getIfDueDateEqualToSysDate(); 
		if(calibMainDataDueDate != null && !calibMainDataDueDate.isEmpty()){
			logger.info("loop for Inactive");
			for(CalibMainData calibMainData : calibMainDataDueDate){
				if(!calibMainData.getMainCalibrationStatus().trim().equalsIgnoreCase("Scrap")){
					calibMainData.setMainCalibrationStatus("Inactive");
				}
			}
		}
		alertIService.saveCalibDetailsForDueDate(calibMainDataDueDate);

//		// String mailId = getTheMaildID();
//		String mailId = "singhavinash857@gmail.com";
//		String messageBody = "message Body";
//		String subjectBody = "Testing Alert Subject";
//		try {
//			emailUtils.sendEmailWithAttachment(mailId, subjectBody, messageBody);
//		} catch (MessagingException e) {
//			e.printStackTrace();
//		}
		logger.info("INSIDE THE AlertController END METHOD checkReminderDate");
	}
}
