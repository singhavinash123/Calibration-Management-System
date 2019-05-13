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
import com.spiraxcalibration.dao.AlertIDao;
import com.spiraxcalibration.models.CalibMainData;

@Repository
public class AlertDaoIMPL implements AlertIDao{

	Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	AppsPropertyFile dBQueryPropertyFile = new AppsPropertyFile();

	@Autowired
	JdbcTemplate  jdbcTemplate1;

	@Override
	public List<CalibMainData> getIfReminderDateEqualToSysDate() {
		logger.info("INSIDE AlertDaoIMPL START METHOD getIfReminderDateEqualToSysDate ::");
		String getIfReminderDateEqualToSysDateQuery = dBQueryPropertyFile.getQueryForKey("getIfReminderDateEqualToSysDateQuery");
		List<CalibMainData> calibDataList = jdbcTemplate1.query(getIfReminderDateEqualToSysDateQuery, new MainCalibDataMapper());
		logger.info("INSIDE AlertDaoIMPL END METHOD getIfReminderDateEqualToSysDate ::");
		return calibDataList;
	}

	@Override
	public List<CalibMainData> getIfDueDateEqualToSysDate() {
		logger.info("INSIDE AlertDaoIMPL START METHOD getIfDueDateEqualToSysDate ::");
		String getIfDueDateEqualToSysDateQuery = dBQueryPropertyFile.getQueryForKey("getIfDueDateEqualToSysDateQuery");
		List<CalibMainData> calibDataList = jdbcTemplate1.query(getIfDueDateEqualToSysDateQuery, new MainCalibDataMapper());
		logger.info("INSIDE AlertDaoIMPL END METHOD getIfDueDateEqualToSysDate ::");
		return calibDataList;
	}

	private static final class MainCalibDataMapper implements RowMapper<CalibMainData>{
		@Override
		public CalibMainData mapRow(ResultSet rs , int rowNum) throws SQLException {
			CalibMainData calibMainData = new CalibMainData();
			calibMainData.setMainInstrumentRequestor(rs.getString("Instrument_Requestor"));
			calibMainData.setMainSupplierName(rs.getString("Supplier_Name"));
			calibMainData.setMainPurchaseOrder(rs.getString("Purchase_Order"));
			calibMainData.setMainInvoice(rs.getString("Invoice"));
			calibMainData.setMainUnitPrice(rs.getString("Unit_Price"));
			calibMainData.setMainAssetCode(rs.getString("AssetCode"));
			calibMainData.setMainSupplierService(rs.getString("SupplierService"));
			calibMainData.setMainMake(rs.getString("Make"));
			calibMainData.setMainInstrumentGauge(rs.getString("Instrument_Gauge"));
			calibMainData.setMainModel(rs.getString("Model"));
			calibMainData.setMainSerial(rs.getString("Serial_Num"));
			calibMainData.setMainLeast(rs.getString("Least"));
			calibMainData.setMainCalibrationStandard(rs.getString("Calibration_Standard"));
			calibMainData.setMainForcastPrice(rs.getString("Forcast_Price"));
			calibMainData.setMainEquipmentType(rs.getString("Equipment_Type"));
			calibMainData.setMainCalibrationType(rs.getString("Calibration_Type"));
			calibMainData.setMainAlertFrequency(rs.getString("Alert_Frequency"));
			calibMainData.setMainCalibrationDate(rs.getString("Calibration_Date"));
			calibMainData.setMainDueDate(rs.getString("Due_Date"));
			calibMainData.setMainCalibrationStatus(rs.getString("Calibration_Status"));
			calibMainData.setMainCalibrationScrapedDate(rs.getString("Calibration_Scraped_Date"));
			calibMainData.setMainReminderDate(rs.getString("Reminder_Date"));
			calibMainData.setMainAcceptanceCriteria(rs.getString("Acceptance_Criteria"));
			calibMainData.setMainLocation(rs.getString("Location"));
			calibMainData.setMainCalibrationAgency(rs.getString("Calibration_Agency"));
			calibMainData.setMainCalibrationFrequency(rs.getString("Calibration_Frequency"));
			calibMainData.setMainCalibrationCertificate(rs.getString("Calibration_Certificate"));
			calibMainData.setMainCertificateValidatedBy(rs.getString("Certificate_ValidatedBy"));
			calibMainData.setMainPartCode(rs.getString("Part_Code"));
			calibMainData.setMainIdentificationId(rs.getString("EC_identification_id"));
			calibMainData.setMainEcCalibId(rs.getInt("EC_Calib_Id"));
			calibMainData.setMainEcId(rs.getInt("EC_Id"));
			calibMainData.setMainEcSupplierId(rs.getInt("EC_Supplier_Id"));
			calibMainData.setMainCalibrationCategory(rs.getString("Calibration_Category"));
			return calibMainData;
		}
	}

	@Override
	public void saveCalibDetailsForReminderDate(List<CalibMainData> calibMainDataReminderDate) {
		logger.info("INSIDE AlertDaoIMPL START METHOD saveCalibDetailsForReminderDate ::");

		String updateCalibStatusForReminderDateQuery =  dBQueryPropertyFile.getQueryForKey("updateCalibStatusForReminderDateQuery");
		for(CalibMainData calibMainData : calibMainDataReminderDate ){
			jdbcTemplate1.update(updateCalibStatusForReminderDateQuery,
					calibMainData.getMainCalibrationStatus(),
					calibMainData.getMainEcId());	
		}
		logger.info("INSIDE AlertDaoIMPL END METHOD saveCalibDetailsForReminderDate ::");
	}

	@Override
	public void saveCalibDetailsForDueDate(List<CalibMainData> calibMainDataDueDate) {
		logger.info("INSIDE AlertDaoIMPL START METHOD saveCalibDetailsForDueDate ::");
		String updateCalibStatusForDueDateQuery =  dBQueryPropertyFile.getQueryForKey("updateCalibStatusForDueDateQuery");
		for(CalibMainData calibMainData : calibMainDataDueDate ){
			jdbcTemplate1.update(updateCalibStatusForDueDateQuery,
					calibMainData.getMainCalibrationStatus(),
					calibMainData.getMainEcId());	
		}
		logger.info("INSIDE AlertDaoIMPL END METHOD saveCalibDetailsForDueDate ::");
	}

}
