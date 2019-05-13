package com.spiraxcalibration.servicesIMPL;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spiraxcalibration.dao.CalibIDao;
import com.spiraxcalibration.models.CalibData;
import com.spiraxcalibration.models.CalibMainData;
import com.spiraxcalibration.models.PrData;
import com.spiraxcalibration.services.CalibIService;
import com.spiraxcalibration.utils.DateUtils;

@Service
public class CalibServiceIMPL implements CalibIService{

	Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	CalibIDao  calibIDao;
	
	@Override
	public List<PrData> calibGetProductDetails() {
		logger.info("INSIDE CalibServiceIMPL START METHOD calibGetProductDetails ::");
		List<PrData> prDataList = calibIDao.calibGetProductDetails();
		if(prDataList != null){
			return prDataList;
		}else{
			logger.error("INSIDE CalibServiceIMPL END METHOD calibGetProductDetails ::");
		}
		logger.info("INSIDE CalibServiceIMPL END METHOD calibGetProductDetails ::");
		return prDataList;
	}

	@Override
	public Object calibSearchByCondition(String identity, String serialNumber, String location, String calibAgency) {
		logger.info("INSIDE CalibServiceIMPL START METHOD prSearchByCondition ::");
		List<PrData> prDataList = null;
		prDataList = calibIDao.calibSearchByCondition(identity.trim(),serialNumber.trim(),location,calibAgency);
		if(prDataList != null){
			return prDataList;
		}else{
			logger.error("INSIDE CalibServiceIMPL ====> prDataList :"+prDataList);
		}
		logger.info("INSIDE CalibServiceIMPL END METHOD prSearchByCondition ::");
		return prDataList;
	}

	@Override
	public List<CalibData> calibGetCalibrationsById(Integer prodId) {
		logger.info("INSIDE CalibServiceIMPL START METHOD calibGetCalibrationById ::");
		List<CalibData>  list =  calibIDao.calibGetCalibrationsById(prodId);
		logger.info("INSIDE CalibServiceIMPL END METHOD calibGetCalibrationById ::");
		return list;
	}

	@Override
	public int calibSaveCalibrationDetail(CalibData calibData) {
		logger.info("INSIDE CalibServiceIMPL START METHOD calibSaveCalibrationDetail ::");
		calibData.setCalibApprover1Status("Pending");
		calibData.setCalibApprover2Status("Pending");
		calibFormatDateString(calibData);
		int num = calibIDao.calibSaveCalibrationDetail(calibData);
		logger.info("INSIDE CalibServiceIMPL END METHOD calibSaveCalibrationDetail ::");
		return num;
	}

	@Override
	public CalibMainData calibGetCalibrationById(Integer calibId) {
		logger.info("INSIDE CalibServiceIMPL START METHOD calibGetCalibrationById ::");
		CalibMainData calibData = calibIDao.calibGetCalibrationById(calibId);
		logger.info("INSIDE CalibServiceIMPL END METHOD calibGetCalibrationById ::");
		return calibData;
	}

//	@Override
//	public CalibData calibGetCalibrationById(Integer calibId) {
//		logger.info("INSIDE CalibServiceIMPL START METHOD calibGetCalibrationById ::");
//		logger.info("INSIDE CalibServiceIMPL END METHOD calibGetCalibrationById ::");
//		CalibData calibData = calibIDao.calibGetCalibrationById(calibId);
//		calibFormatFormDateString(calibData);
//		return calibData;
//		//return calibIDao.calibGetCalibrationById(calibId);
//	}
	private void calibFormatFormDateString(CalibData calibData) {
		// having this formate...
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dueDate = null;
		Date reminderDate = null;
		try {
			dueDate = sdf.parse(calibData.getCalibCalibrationDueDate());
			reminderDate = sdf.parse(calibData.getCalibCalibrationReminderDate());

		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		// converting to this formate...
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");
		String dueDateFormated = sdf2.format(dueDate);
		String reminderDateFormated = sdf2.format(reminderDate);

		calibData.setCalibCalibrationDueDate(dueDateFormated);
		calibData.setCalibCalibrationReminderDate(reminderDateFormated);		
	}

	@Override
	public int calibGetcalibSatusCountIfCalibratedByProdID(Integer prodId) {
		logger.info("INSIDE CalibServiceIMPL START METHOD calibGetcalibSatusCountIfCalibratedByProdID ::");
		int num = calibIDao.calibGetcalibSatusCountIfCalibratedByProdID(prodId);
		logger.info("INSIDE CalibServiceIMPL END METHOD calibGetcalibSatusCountIfCalibratedByProdID ::");
		return num;
	}

	@Override
	public int calibUpdateCalibDetails(CalibData calibData) {
		logger.info("INSIDE CalibServiceIMPL START METHOD calibUpdateCalibDetails ::");
		calibFormatDate(calibData);
		int num = calibIDao.calibUpdateCalibDetails(calibData);
		logger.info("INSIDE CalibServiceIMPL END METHOD calibUpdateCalibDetails ::");
		return num;
	}
	
	private void calibFormatDate(CalibData calibData) {
		Date dueDate = null;
		Date reminderDate = null;
		try {
			dueDate = DateUtils.convertToDDMMYYYYParseToDate(calibData.getCalibCalibrationDueDate());
			reminderDate = DateUtils.convertToDDMMYYYYParseToDate(calibData.getCalibCalibrationReminderDate());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String dueDateFormated = DateUtils.convertToYYYYMMDD(dueDate);
		String reminderDateFormated = DateUtils.convertToYYYYMMDD(reminderDate);
		calibData.setCalibCalibrationDueDate(dueDateFormated);
		calibData.setCalibCalibrationReminderDate(reminderDateFormated);
	}

	private void calibFormatDateString(CalibData calibData) {
		Date dueDate = null;
		Date reminderDate = null;
		try {
			dueDate = DateUtils.convertToDDMMYYYYParseToDate(calibData.getCalibCalibrationDueDate());
			reminderDate = DateUtils.convertToDDMMYYYYParseToDate(calibData.getCalibCalibrationReminderDate());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// converting to this formate...
		String dueDateFormated = DateUtils.convertToYYYYMMDD(dueDate);
		String reminderDateFormated = DateUtils.convertToYYYYMMDD(reminderDate);
		calibData.setCalibCalibrationDueDate(dueDateFormated);
		calibData.setCalibCalibrationReminderDate(reminderDateFormated);
	}

	@Override
	public CalibData calibGetCalibrationByCertId(Integer warrantyId) {
		logger.info("INSIDE CalibServiceIMPL START METHOD calibGetCalibrationByCertId ::");
		CalibData calibData =  calibIDao.calibGetCalibrationByCertId(warrantyId);
		logger.info("INSIDE CalibServiceIMPL END METHOD calibGetCalibrationByCertId ::");
		return calibData;
	}

	@Override
	public int calibSaveApprover(CalibData calibData) {
		logger.info("INSIDE CalibServiceIMPL START METHOD calibSaveApprover ::");
		calibFormatDate(calibData);
		int num =  calibIDao.calibUpdateCalibDetails(calibData);
		logger.info("INSIDE CalibServiceIMPL END METHOD calibSaveApprover ::");
		return num;

	}

	@Override
	public int setPendingForApproval(Integer calibId) {
		logger.info("INSIDE CalibServiceIMPL START METHOD setPendingForApproval ::");
		int num =  calibIDao.setPendingForApproval(calibId);
		logger.info("INSIDE CalibServiceIMPL END METHOD setPendingForApproval ::");
		return num;
	}

	@Override
	public int calibApprovedOrReject(CalibData calibData) {
		logger.info("INSIDE CalibServiceIMPL START METHOD calibApprovedOrReject ::");
		if(calibData.getCalibSendForApprovalStatusFlag() == 1){
			calibData.setCalibApprover1Status("Approved");
		}else if(calibData.getCalibSendForApprovalStatusFlag() == 2){
			calibData.setCalibApprover1Status("Rejected");
		}
		int num = calibIDao.calibApprovedOrReject(calibData);
		logger.info("INSIDE CalibServiceIMPL END METHOD calibApprovedOrReject ::");
		return num;
	}
}
