package com.spiraxcalibration.servicesIMPL;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spiraxcalibration.dao.QRAndBARCodeIDao;
import com.spiraxcalibration.models.CalibData;
import com.spiraxcalibration.models.CalibMainData;
import com.spiraxcalibration.models.PRPData;
import com.spiraxcalibration.services.QRAndBARCodeIService;

@Service
public class QRAndBARCodeServiceIMPL implements QRAndBARCodeIService{

	Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	QRAndBARCodeIDao qrAndBARCodeIDao;

	@Override
	public List<CalibData> QR_BARgetCalibrationDetails() {
		logger.info("INSIDE QRAndBARCodeServiceIMPL START METHOD QR_BARgetCalibrationDetails ::");
		List<CalibData>  calibDataList = qrAndBARCodeIDao.QR_BARgetCalibrationDetails();
		getValidityByDates(calibDataList);
		logger.info("INSIDE QRAndBARCodeServiceIMPL END METHOD QR_BARgetCalibrationDetails ::");
		return calibDataList;
	}

	private void getValidityByDates(List<CalibData> calibDataList) {
		for(CalibData calibData : calibDataList){

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String todaySystemDate1 = sdf.format(Calendar.getInstance().getTime());
			Date  todaySystemDate2 = null;
			Date dueDate2 = null;
			String dueDate1 =  calibData.getCalibCalibrationDueDate();
			try {
				todaySystemDate2 = sdf.parse(todaySystemDate1);
				dueDate2 = sdf.parse(dueDate1);
			} catch (ParseException e) {
				e.printStackTrace();
			}if (todaySystemDate2.compareTo(dueDate2) < 0) {
				calibData.setCalibStatusFlag("green");
				calibData.setCalibStatusData("Valid");
			} else if (todaySystemDate2.compareTo(dueDate2) > 0) {
				calibData.setCalibStatusFlag("red");
				calibData.setCalibStatusData("Expired");
				calibData.setCalibStatusForExpired(true);
				// model.addObject("disabled",true);

			} else if (todaySystemDate2.compareTo(dueDate2) == 0) {
				calibData.setCalibStatusFlag("yellow");
				calibData.setCalibStatusData("Valid Till Date");
			}
		}		
	}

	@Override
	public List<CalibMainData> detailsForQrAndBarCode(Integer calib_Id) {
		logger.info("INSIDE QRAndBARCodeServiceIMPL START METHOD detailsForQrAndBarCode ::");
		List<CalibMainData> calibDataList = qrAndBARCodeIDao.detailsForQrAndBarCode(calib_Id);
		//	getValidityByDates(calibDataList);
		logger.info("INSIDE QRAndBARCodeServiceIMPL END METHOD detailsForQrAndBarCode ::");
		return calibDataList;
	}

	@Override
	public List<String> QR_BARgetIdentityNumberFromCalibration() {
		logger.info("INSIDE QRAndBARCodeServiceIMPL START METHOD QR_BARgetIdentityNumberFromCalibration ::");
		logger.info("INSIDE QRAndBARCodeServiceIMPL END METHOD QR_BARgetIdentityNumberFromCalibration ::");
		List<String> list =  qrAndBARCodeIDao.QR_BARgetIdentityNumberFromCalibration();
		logger.info("INSIDE QRAndBARCodeServiceIMPL END METHOD QR_BARgetIdentityNumberFromCalibration ::");
		return list;
	}

	@Override
	public List<String> QR_BARgetSerialNumberFromCalibration() {
		logger.info("INSIDE QRAndBARCodeServiceIMPL START METHOD QR_BARgetSerialNumberFromCalibration ::");
		List<String> list = qrAndBARCodeIDao.QR_BARgetSerialNumberFromCalibration();
		logger.info("INSIDE QRAndBARCodeServiceIMPL END METHOD QR_BARgetSerialNumberFromCalibration ::");
		return list;
	}

	@Override
	public List<CalibData> QRAndBARSearchProductByCondition(String identity, String serialNumber) {
		logger.info("INSIDE QRAndBARCodeServiceIMPL START METHOD QRAndBARSearchProductByCondition ::");
		logger.info("INSIDE QRAndBARCodeServiceIMPL END METHOD QRAndBARSearchProductByCondition ::");
		List<CalibData> calibDataList = qrAndBARCodeIDao.QRAndBARSearchProductByCondition(identity,serialNumber);
		getValidityByDates(calibDataList);
		logger.info("INSIDE QRAndBARCodeServiceIMPL END METHOD QRAndBARSearchProductByCondition ::");
		return calibDataList;
	}

	@Override
	public CalibMainData detailsForQrAndBarCodeScan(String identityFicationNum) {
		logger.info("INSIDE QRAndBARCodeServiceIMPL START METHOD detailsForQrAndBarCodeScan ::");
		CalibMainData calibDataList = qrAndBARCodeIDao.detailsForQrAndBarCodeScan(identityFicationNum);
		logger.info("INSIDE QRAndBARCodeServiceIMPL END METHOD detailsForQrAndBarCodeScan ::");
		return calibDataList;
	}

	@Override
	public PRPData getThePrInformationByIdentyNum(String identityFicationNum) {
		logger.info("INSIDE QRAndBARCodeServiceIMPL START METHOD getThePrInformationByIdentyNum ::");
		PRPData pRPData = qrAndBARCodeIDao.getThePrInformationByIdentyNum(identityFicationNum);
		logger.info("INSIDE QRAndBARCodeServiceIMPL END METHOD getThePrInformationByIdentyNum ::");
		return pRPData;
	}

	//	@Override
	//	public List<CalibData> detailsForQrAndBarCodeScan(String identityFicationNum) {
	//		logger.info("INSIDE QRAndBARCodeServiceIMPL START METHOD detailsForQrAndBarCodeScan ::");
	//		List<CalibData> calibDataList = qrAndBARCodeIDao.detailsForQrAndBarCodeScan(identityFicationNum);
	//		getValidityByDates(calibDataList);
	//		logger.info("INSIDE QRAndBARCodeServiceIMPL END METHOD detailsForQrAndBarCodeScan ::");
	//		return calibDataList;
	//	}

}
