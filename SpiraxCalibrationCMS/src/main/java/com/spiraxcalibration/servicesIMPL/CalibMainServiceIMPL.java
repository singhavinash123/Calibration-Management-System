package com.spiraxcalibration.servicesIMPL;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.log.SysoCounter;
import com.spiraxcalibration.dao.CalibMainIDao;
import com.spiraxcalibration.models.CalibMainData;
import com.spiraxcalibration.services.CalibMainIService;

@Service
public class CalibMainServiceIMPL implements CalibMainIService {

	Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	CalibMainIDao calibMainIDao;

	@Override
	public int mainSaveCalibDetails(CalibMainData calibMainData) {
		logger.info("INSIDE CalibServiceIMPL START METHOD mainSaveCalibDetails ::");
		int num = calibMainIDao.mainSaveCalibDetails(calibMainData);
		logger.info("INSIDE CalibServiceIMPL END METHOD mainSaveCalibDetails ::");
		return num;
	}

	@Override
	public CalibMainData mainSearchByCondition(String identiFicationNum) {
		logger.info("INSIDE CalibServiceIMPL START METHOD mainSearchByCondition ::");
		CalibMainData calibMainData = calibMainIDao.mainSearchByCondition(identiFicationNum.trim());
		if (calibMainData != null) {
			return calibMainData;
		} else {
			logger.error("INSIDE CalibServiceIMPL ====> calibMainData :" + calibMainData);
		}
		logger.info("INSIDE CalibServiceIMPL END METHOD mainSearchByCondition ::");
		return calibMainData;
	}

	@Override
	public CalibMainData mainGetProductDetails() {
		logger.info("INSIDE CalibServiceIMPL START METHOD mainGetProductDetails ::");
		CalibMainData calibMainData = calibMainIDao.mainGetProductDetails();
		logger.info("INSIDE CalibServiceIMPL END METHOD mainGetProductDetails ::");
		return calibMainData;
	}

	@Override
	public int mainUpdateCalibDetails(CalibMainData calibMainData) {
		logger.info("INSIDE CalibServiceIMPL START METHOD mainUpdateCalibDetails ::");
		int num = calibMainIDao.mainUpdateCalibDetails(calibMainData);
		logger.info("INSIDE CalibServiceIMPL END METHOD mainUpdateCalibDetails ::");
		return num;
	}

	@Override
	public List<CalibMainData> getEquipmentCalibList() {
		logger.info("INSIDE CalibServiceIMPL START METHOD getEquipmentCalibList ::");
		List<CalibMainData> equipCalibList = calibMainIDao.getEquipmentCalibList();
		logger.info("INSIDE CalibServiceIMPL END METHOD getEquipmentCalibList ::");
		return equipCalibList;
	}

	@Override
	public List<CalibMainData> getEquipmentList(int pageNum) {
		logger.info("INSIDE CalibServiceIMPL START METHOD getEquipmentList ::");
		List<CalibMainData> equipCalibList = null;
		if(pageNum < 0){
			System.out.println("pageNum ::"+pageNum);
		    equipCalibList = calibMainIDao.getEquipmentCalibList();
			return equipCalibList;
		}else{
			int lowerLimit = 10 * (pageNum - 1);
			int upperLimit = 10;
			equipCalibList = calibMainIDao.getEquipmentList(lowerLimit,upperLimit);
			return equipCalibList;
		}
	}

	@Override
	public List<CalibMainData> getEquipmentByIdentity(String identityNum) {
		logger.info("INSIDE CalibServiceIMPL START METHOD getEquipmentByIdentity ::");
		List<CalibMainData> equipCalibList = calibMainIDao.getEquipmentByIdentity(identityNum);
		logger.info("INSIDE CalibServiceIMPL END METHOD getEquipmentByIdentity ::");
		return equipCalibList;
	}

}
