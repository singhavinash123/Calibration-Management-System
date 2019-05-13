package com.spiraxcalibration.servicesIMPL;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spiraxcalibration.dao.ApprovalIDao;
import com.spiraxcalibration.models.CalibData;
import com.spiraxcalibration.services.ApprovalIService;

@Service
public class ApprovalServiceIMPL implements ApprovalIService{

	Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	ApprovalIDao  approvalIDao;
	
	@Override
	public List<CalibData> approveGetCalibDetails() {
		logger.info("INSIDE ApprovalServiceIMPL START METHOD approveGetCalibDetails ::");
		List<CalibData> prDataList = approvalIDao.approveGetCalibDetails();
		if(prDataList != null){
			return prDataList;
		}else{
			logger.error("INSIDE ApprovalServiceIMPL END METHOD approveGetCalibDetails ::");
		}
		logger.info("INSIDE ApprovalServiceIMPL END METHOD approveGetCalibDetails ::");
		return prDataList;
	}

	@Override
	public List<CalibData> approveSearchCalibByCondition(String identity, String serialNumber) {
		logger.info("INSIDE ApprovalServiceIMPL START METHOD approveSearchCalibByCondition ::");
		List<CalibData> prDataList = approvalIDao.approveSearchCalibByCondition(identity,serialNumber);
		if(prDataList != null){
			return prDataList;
		}else{
			logger.error("INSIDE ApprovalServiceIMPL END METHOD approveGetCalibDetails ::");
		}
		logger.info("INSIDE ApprovalServiceIMPL END METHOD approveSearchCalibByCondition ::");
		return prDataList;
	}

}
