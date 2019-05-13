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
import com.spiraxcalibration.dao.ApprovalIDao;
import com.spiraxcalibration.models.CalibData;

@Repository
public class ApprovalDaoIMPL implements ApprovalIDao{

	Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	AppsPropertyFile dBQueryPropertyFile = new AppsPropertyFile();

	@Autowired
	JdbcTemplate  jdbcTemplate1;

	@Override
	public List<CalibData> approveGetCalibDetails() {
		logger.info("INSIDE ApprovalDaoIMPL START METHOD approveGetCalibDetails ::");
		String selectPrListQueryForActiveCalibStatus = dBQueryPropertyFile.getQueryForKey("selectPrListQueryForActiveCalibStatus");
		List<CalibData> prList = jdbcTemplate1.query(selectPrListQueryForActiveCalibStatus, new calibDataMapper());
		logger.info("INSIDE ApprovalDaoIMPL END METHOD approveGetCalibDetails ::");
		return prList;
	}
	
	private static final class calibDataMapper implements RowMapper<CalibData>{
		@Override
		public CalibData mapRow(ResultSet rs , int rowNum) throws SQLException {
			CalibData calibData = new CalibData();
			calibData.setCalibAccountId(rs.getString("Account_ID"));
			calibData.setCalibSerialNumber(rs.getString("Serial_Number"));
			calibData.setCalibCalibratedBy(rs.getString("Calibrated_By"));
			calibData.setCalibCalibrationCertificate(rs.getString("Calibration_Certificate"));
			calibData.setCalibCalibrationDate(rs.getString("Calibration_Date"));
			calibData.setCalibCalibrationDueDate(rs.getString("Calibration_Due_Date"));
			calibData.setCalibCalibrationFrequency(rs.getInt("Calibration_Frequency"));
			calibData.setCalibCalibrationPhase(rs.getInt("Calibration_phase"));
			calibData.setCalibCalibrationReminderDate(rs.getString("Calibration_Reminder_Date"));
			calibData.setCalibCalibStatus(rs.getString("Calib_Status"));
			calibData.setCalibCreatedBy(rs.getInt("created_by"));
			calibData.setCalibCreationDate(rs.getString("creation_date"));
			calibData.setCalibDateOfCalibration(rs.getString("Calibration_Date"));
			calibData.setCalibId(rs.getInt("Calib_ID"));
			calibData.setCalibIdentificationNo(rs.getString("Identification_No"));
			calibData.setCalibInstrumentLocation(rs.getString("Instrument_Location"));
			calibData.setCalibInstrumentRange(rs.getString("Instrument_Range"));
			calibData.setCalibLastUpdateDate(rs.getString("last_update_date"));
			calibData.setCalibLastUpdatedBy(rs.getInt("last_updated_by"));
			calibData.setCalibLastUpdateLogin(rs.getInt("last_update_login"));
			calibData.setCalibProdId(rs.getInt("PROD_ID"));
			calibData.setCalibResults(rs.getString("Results"));
			calibData.setCalibVerifiedBy(rs.getString("Verified_By"));
			calibData.setCalibCalibrationType(rs.getString("Calibration_Type"));
			calibData.setCalibApprover1Name(rs.getString("Approver1Name"));
			calibData.setCalibApprover1Status(rs.getString("Approver1Status"));
			calibData.setCalibApprover1Comments(rs.getString("Approver1Comments"));
			calibData.setCalibApprover2Name(rs.getString("Approver2Name"));
			calibData.setCalibApprover2Status(rs.getString("Approver2Status"));
			calibData.setCalibApprover2Comments(rs.getString("Approver2Comments"));
			calibData.setCalibSupplierName(rs.getString("Sup_Name"));

			return calibData;
		}
	}

	
	@Override
	public List<CalibData> approveSearchCalibByCondition(String identity, String serialNumber) {
		logger.info("INSIDE ApprovalDaoIMPL START METHOD approveSearchCalibByCondition ::");
		String selectApprovedCalibListByConditionQuery = dBQueryPropertyFile.getQueryForKey("selectApprovedCalibListByConditionQuery");
		List<CalibData> searchedList = jdbcTemplate1.query(selectApprovedCalibListByConditionQuery, new Object[]{identity+"%", serialNumber+"%"} , new calibDataMapper());
		logger.info("INSIDE ApprovalDaoIMPL END METHOD approveSearchCalibByCondition ::");
		return searchedList;
	}


}
