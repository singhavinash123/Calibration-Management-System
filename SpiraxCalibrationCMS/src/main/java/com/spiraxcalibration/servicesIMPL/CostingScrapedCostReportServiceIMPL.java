package com.spiraxcalibration.servicesIMPL;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spiraxcalibration.dao.CostingScrapedCostReportIDao;
import com.spiraxcalibration.models.CostPricesData;
import com.spiraxcalibration.services.CostingScrapedCostReportIService;

@Service
public class CostingScrapedCostReportServiceIMPL implements CostingScrapedCostReportIService{

	Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	CostingScrapedCostReportIDao costingScrapedCostReportIDao;

	@Override
	public List<CostPricesData> LoadTheCalibrationCostingForcastCurrentYear() {
		logger.info("INSIDE LOVServiceIMPL START METHOD LoadTheCalibrationCostingForcastCurrentYear ::");
		List<CostPricesData> list =  costingScrapedCostReportIDao.LoadTheCalibrationCostingForcastCurrentYear();
		logger.info("INSIDE LOVServiceIMPL END METHOD LoadTheCalibrationCostingForcastCurrentYear ::");
		return list;
	}

	@Override
	public List<CostPricesData> LoadTheCalibrationCostingForcastYear() {
		logger.info("INSIDE LOVServiceIMPL START METHOD LoadTheCalibrationCostingForcastYear ::");
		List<CostPricesData> list =  costingScrapedCostReportIDao.LoadTheCalibrationCostingForcastYear();
		logger.info("INSIDE LOVServiceIMPL END METHOD LoadTheCalibrationCostingForcastYear ::");
		return list;
	}

	@Override
	public List<CostPricesData> LoadTheInstrumentScrapedCurrentYear() {
		logger.info("INSIDE LOVServiceIMPL START METHOD LoadTheInstrumentScrapedCurrentYear ::");
		List<CostPricesData> list = costingScrapedCostReportIDao.LoadTheInstrumentScrapedCurrentYear();
		logger.info("INSIDE LOVServiceIMPL END METHOD LoadTheInstrumentScrapedCurrentYear ::");
		return list;
	}

	@Override
	public List<CostPricesData> LoadTheInstrumentScrapedPriorCurrentYear() {
		logger.info("INSIDE LOVServiceIMPL START METHOD LoadTheInstrumentScrapedPriorCurrentYear ::");
		List<CostPricesData> list =  costingScrapedCostReportIDao.LoadTheInstrumentScrapedPriorCurrentYear();
		logger.info("INSIDE LOVServiceIMPL END METHOD LoadTheInstrumentScrapedPriorCurrentYear ::");
		return list;

	}

	@Override
	public List<CostPricesData> LoadTheInstrumentCountCurrentYearDataList() {
		logger.info("INSIDE LOVServiceIMPL START METHOD LoadTheInstrumentCountCurrentYearDataList ::");
		List<CostPricesData> list =  costingScrapedCostReportIDao.LoadTheInstrumentCountCurrentYearDataList();
		logger.info("INSIDE LOVServiceIMPL END METHOD LoadTheInstrumentCountCurrentYearDataList ::");
		return list;
	}

	@Override
	public List<CostPricesData> LoadTheinstrumentCountCurrentYearActualData() {
		logger.info("INSIDE LOVServiceIMPL START METHOD LoadTheinstrumentCountCurrentYearActualData ::");
		List<CostPricesData> list =  costingScrapedCostReportIDao.LoadTheinstrumentCountCurrentYearActualData();
		logger.info("INSIDE LOVServiceIMPL END METHOD LoadTheinstrumentCountCurrentYearActualData ::");
		return list;
	}
}
