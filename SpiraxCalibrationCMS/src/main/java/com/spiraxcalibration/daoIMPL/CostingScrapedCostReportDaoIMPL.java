package com.spiraxcalibration.daoIMPL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.spiraxcalibration.WebConfig.AppsPropertyFile;
import com.spiraxcalibration.dao.CostingScrapedCostReportIDao;
import com.spiraxcalibration.models.CostPricesData;

@Repository
public class CostingScrapedCostReportDaoIMPL implements CostingScrapedCostReportIDao{

	Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	AppsPropertyFile dBQueryPropertyFile = new AppsPropertyFile();

	@Autowired
	JdbcTemplate  jdbcTemplate1;

	@Override
	public List<CostPricesData> LoadTheCalibrationCostingForcastCurrentYear() {
		logger.info("INSIDE CostingScrapedCostReportDaoIMPL START METHOD LoadTheCalibrationCostingForcastCurrentYear ::");
		String selectCostPriceListQuery = dBQueryPropertyFile.getQueryForKey("selectCostPriceListQuery");
		List<CostPricesData> CostPricesDataList = jdbcTemplate1.query(selectCostPriceListQuery, new CostPricesDataMapper());
		logger.info("INSIDE CostingScrapedCostReportDaoIMPL END METHOD LoadTheCalibrationCostingForcastCurrentYear ::");
		return CostPricesDataList;

	}

	private static final class CostPricesDataMapper implements RowMapper<CostPricesData>{
		@Override
		public CostPricesData mapRow(ResultSet rs , int rowNum) throws SQLException {
			CostPricesData calibPriceData = new CostPricesData();
			calibPriceData.setCostPrice(rs.getString("unitprice"));
			calibPriceData.setCostMonth(rs.getString("months"));
			return calibPriceData;
		}}

	@Override
	public List<CostPricesData> LoadTheCalibrationCostingForcastYear() {
		logger.info("INSIDE CostingScrapedCostReportDaoIMPL START METHOD LoadTheCalibrationCostingForcastYear ::");
		String selectForeCastPriceListQuery = dBQueryPropertyFile.getQueryForKey("selectForeCastPriceListQuery");
		List<CostPricesData> CostPricesDataList = jdbcTemplate1.query(selectForeCastPriceListQuery, new CostPricesDataMapper());
		logger.info("INSIDE CostingScrapedCostReportDaoIMPL END METHOD LoadTheCalibrationCostingForcastYear ::");
		return CostPricesDataList;
	}

	@Override
	public List<CostPricesData> LoadTheInstrumentScrapedCurrentYear() {
		logger.info("INSIDE CostingScrapedCostReportDaoIMPL START METHOD LoadTheInstrumentScrapedCurrentYear ::");
		String selectIntrumentScrappedCurrentYearQuery = dBQueryPropertyFile.getQueryForKey("selectIntrumentScrappedCurrentYearQuery");
		List<CostPricesData> CostPricesDataList = jdbcTemplate1.query(selectIntrumentScrappedCurrentYearQuery, new CostScrappedDataMapper());
		logger.info("INSIDE CostingScrapedCostReportDaoIMPL END METHOD LoadTheInstrumentScrapedCurrentYear ::");
		return CostPricesDataList;
	}

	@Override
	public List<CostPricesData> LoadTheInstrumentScrapedPriorCurrentYear() {
		logger.info("INSIDE CostingScrapedCostReportDaoIMPL START METHOD LoadTheInstrumentScrapedPriorCurrentYear ::");
		String selectIntrumentScrappedPriorYearQuery = dBQueryPropertyFile.getQueryForKey("selectIntrumentScrappedPriorYearQuery");
		List<CostPricesData> CostPricesDataList = jdbcTemplate1.query(selectIntrumentScrappedPriorYearQuery, new CostScrappedDataMapper());
		logger.info("INSIDE CostingScrapedCostReportDaoIMPL END METHOD LoadTheInstrumentScrapedPriorCurrentYear ::");
		return CostPricesDataList;
	}
	
	private static final class CostScrappedDataMapper implements RowMapper<CostPricesData>{
		@Override
		public CostPricesData mapRow(ResultSet rs , int rowNum) throws SQLException {
			CostPricesData calibPriceData = new CostPricesData();
			calibPriceData.setCostScrappedCount(rs.getString("scrapnum"));
			calibPriceData.setCostMonth(rs.getString("months"));
			return calibPriceData;
		}}

	@Override
	public List<CostPricesData> LoadTheInstrumentCountCurrentYearDataList() {
		logger.info("INSIDE CostingScrapedCostReportDaoIMPL START METHOD LoadTheInstrumentCountCurrentYearDataList ::");
		String selectIntrumentCountPlanCurrentYearQuery = dBQueryPropertyFile.getQueryForKey("selectIntrumentCountPlanCurrentYearQuery");
		List<CostPricesData> CostPricesDataList = jdbcTemplate1.query(selectIntrumentCountPlanCurrentYearQuery, new CostPlanedActualDataMapper());
		logger.info("INSIDE CostingScrapedCostReportDaoIMPL END METHOD LoadTheInstrumentCountCurrentYearDataList ::");
		return CostPricesDataList;
	}

	private static final class CostPlanedActualDataMapper implements RowMapper<CostPricesData>{
		@Override
		public CostPricesData mapRow(ResultSet rs , int rowNum) throws SQLException {
			CostPricesData calibPriceData = new CostPricesData();
			calibPriceData.setCostInstrumentCount(rs.getString("num_due_date"));
			calibPriceData.setCostMonth(rs.getString("months"));
			return calibPriceData;
		}}

	@Override
	public List<CostPricesData> LoadTheinstrumentCountCurrentYearActualData() {
		logger.info("INSIDE CostingScrapedCostReportDaoIMPL START METHOD LoadTheinstrumentCountCurrentYearActualData ::");
		String selectIntrumentCountActualCurrentYearQuery = dBQueryPropertyFile.getQueryForKey("selectIntrumentCountActualCurrentYearQuery");
		List<CostPricesData> CostPricesDataList = jdbcTemplate1.query(selectIntrumentCountActualCurrentYearQuery, new CostPlanedActualDataMapper());
		logger.info("INSIDE CostingScrapedCostReportDaoIMPL END METHOD LoadTheinstrumentCountCurrentYearActualData ::");
		return CostPricesDataList;
	}
}
