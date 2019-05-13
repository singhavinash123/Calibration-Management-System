package com.spiraxcalibration.services;

import java.util.List;

import com.spiraxcalibration.models.CostPricesData;

public interface CostingScrapedCostReportIService {

	List<CostPricesData> LoadTheCalibrationCostingForcastCurrentYear();
	List<CostPricesData> LoadTheCalibrationCostingForcastYear();
	List<CostPricesData> LoadTheInstrumentScrapedCurrentYear();
	List<CostPricesData> LoadTheInstrumentScrapedPriorCurrentYear();
	List<CostPricesData> LoadTheInstrumentCountCurrentYearDataList();
	List<CostPricesData> LoadTheinstrumentCountCurrentYearActualData();
}
