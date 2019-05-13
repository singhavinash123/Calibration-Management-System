package com.spiraxcalibration.dao;

import java.util.List;

import com.spiraxcalibration.models.CostPricesData;

public interface CostingScrapedCostReportIDao {

	List<CostPricesData> LoadTheCalibrationCostingForcastCurrentYear();
	List<CostPricesData> LoadTheCalibrationCostingForcastYear();
	List<CostPricesData> LoadTheInstrumentScrapedCurrentYear();
	List<CostPricesData> LoadTheInstrumentScrapedPriorCurrentYear();
	List<CostPricesData> LoadTheInstrumentCountCurrentYearDataList();
	List<CostPricesData> LoadTheinstrumentCountCurrentYearActualData();

}
