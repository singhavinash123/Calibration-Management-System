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
import com.spiraxcalibration.dao.QRAndBARCodeIDao;
import com.spiraxcalibration.models.CalibData;
import com.spiraxcalibration.models.CalibMainData;
import com.spiraxcalibration.models.PRPData;

@Repository
public class QRAndBARCodeDaoIMPL implements QRAndBARCodeIDao{

	Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	AppsPropertyFile dBQueryPropertyFile = new AppsPropertyFile();

	@Autowired
	JdbcTemplate  jdbcTemplate1;

	@Override
	public List<CalibData> QR_BARgetCalibrationDetails() {
		logger.info("INSIDE QRAndBARCodeDaoIMPL START METHOD QR_BARgetCalibrationDetails");
		String FindCalibrationDetailsQuery =  dBQueryPropertyFile.getQueryForKey("FindCalibrationDetailsQuery");
		List<CalibData> calibDataList = jdbcTemplate1.query(FindCalibrationDetailsQuery, new calibDataMapper());
		logger.info("INSIDE QRAndBARCodeDaoIMPL START METHOD QR_BARgetCalibrationDetails");
		return calibDataList;
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
	public List<CalibMainData> detailsForQrAndBarCode(Integer calib_Id) {
		logger.info("INSIDE QRAndBARCodeDaoIMPL START METHOD detailsForQrAndBarCode");
		String FindCalibrationDetailsByIdQuery =  dBQueryPropertyFile.getQueryForKey("FindCalibrationDetailsByIdQuery");
		List<CalibMainData> calibData = jdbcTemplate1.query(FindCalibrationDetailsByIdQuery, new Object[] { calib_Id }, new MainCalibDataMapper());
		logger.info("INSIDE QRAndBARCodeDaoIMPL START METHOD detailsForQrAndBarCode");
		return calibData;
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
			calibMainData.setMainInstrumentGaugeRange(rs.getString("Equipment_Range"));
			return calibMainData;
		}
	}

	@Override
	public List<String> QR_BARgetIdentityNumberFromCalibration() {
		logger.info("INSIDE QRAndBARCodeDaoIMPL START METHOD QR_BARgetIdentityNumberFromCalibration ::");
		String selectDistinctIdentityNumberQuery = dBQueryPropertyFile.getQueryForKey("selectDistinctIdentityNumberQuery");
		List<String> getIdnetiyNoList =  jdbcTemplate1.queryForList(selectDistinctIdentityNumberQuery, String.class);
		logger.info("INSIDE QRAndBARCodeDaoIMPL START METHOD QR_BARgetIdentityNumberFromCalibration ::");
		return getIdnetiyNoList;
	}

	@Override
	public List<String> QR_BARgetSerialNumberFromCalibration() {
		logger.info("INSIDE QRAndBARCodeDaoIMPL START METHOD QR_BARgetSerialNumberFromCalibration ::");
		String selectDistinctSerialNumberQuery = dBQueryPropertyFile.getQueryForKey("selectDistinctSerialNumberQuery");
		List<String> getSerialNoList =  jdbcTemplate1.queryForList(selectDistinctSerialNumberQuery, String.class);
		logger.info("INSIDE QRAndBARCodeDaoIMPL START METHOD QR_BARgetSerialNumberFromCalibration ::");
		return getSerialNoList;
	}

	@Override
	public List<CalibData> QRAndBARSearchProductByCondition(String identity, String serialNumber) {
		logger.info("INSIDE QRAndBARCodeDaoIMPL START METHOD QRAndBARSearchProductByCondition ::");
		String selectQR_BAR_CalibListByConditionQuery = dBQueryPropertyFile.getQueryForKey("selectQR_BAR_CalibListByConditionQuery");
		List<CalibData> searchedList = jdbcTemplate1.query(selectQR_BAR_CalibListByConditionQuery, new Object[]{identity+"%", serialNumber+"%"} , new calibDataMapper());
		logger.info("INSIDE QRAndBARCodeDaoIMPL END METHOD QRAndBARSearchProductByCondition ::");
		return searchedList;

	}

	@Override
	public CalibMainData detailsForQrAndBarCodeScan(String identityFicationNum) {
		logger.info("INSIDE QRAndBARCodeDaoIMPL START METHOD detailsForQrAndBarCode");
		String CheckIfExistcountQuery =  dBQueryPropertyFile.getQueryForKey("CheckIfExistcountQuery");
		int num = jdbcTemplate1.queryForObject(CheckIfExistcountQuery,new Object[]{identityFicationNum.trim()} , Integer.class);
		if(num <= 0){
			return  null;
		}
		String FindCalibrationDetailAfterScan =  dBQueryPropertyFile.getQueryForKey("FindCalibrationDetailAfterScan");
		CalibMainData calibData = jdbcTemplate1.queryForObject(FindCalibrationDetailAfterScan, new Object[] { identityFicationNum.trim() }, new MainCalibDataMapper());
		logger.info("INSIDE QRAndBARCodeDaoIMPL START METHOD detailsForQrAndBarCode");
		return calibData;
	}

	@Override
	public PRPData getThePrInformationByIdentyNum(String identityFicationNum) {
		String getThePrInformationByIdentyNumQuery =  dBQueryPropertyFile.getQueryForKey("getThePrInformationByIdentyNumQuery");
		PRPData pRPData = null;
		try{
			pRPData = jdbcTemplate1.queryForObject(getThePrInformationByIdentyNumQuery, new Object[] { identityFicationNum.trim()}, new prPDataMapper());
		}catch(Exception e){
			return null;
		}
		return pRPData;
	}
	
	private static final class prPDataMapper implements RowMapper<PRPData>{
		@Override
		public PRPData mapRow(ResultSet rs , int rowNum) throws SQLException {
			PRPData calibData = new PRPData();
			calibData.setpRpPRId(rs.getInt("PR_ID"));
			calibData.setpRpApprover1FullName(rs.getString("Approver1_FullName"));
			calibData.setpRpApprover2FullName(rs.getString("Approver2_FullName"));
			calibData.setpRpApprover1Comments(rs.getString("Approver1_Comments"));
			calibData.setpRpApprover2Comments(rs.getString("Approver2_comments"));
			calibData.setpRpApprover1Status(rs.getString("Approver1_Status"));
			calibData.setpRpApprover2Status(rs.getString("Approver2_Status"));
			calibData.setpRpIdentificationNum(rs.getString("Identification_No"));
			calibData.setpRpPrNumber(rs.getString("Pr_Number"));
			calibData.setpRpApprover1Date(rs.getString("approve_date"));
			calibData.setpRpApprover2Date(rs.getString("approver2_date"));

			return calibData;
		}
	}

	//	@Override
	//	public List<CalibData> detailsForQrAndBarCodeScan(String identityFicationNum) {
	//		logger.info("INSIDE QRAndBARCodeDaoIMPL START METHOD detailsForQrAndBarCode");
	//		String FindCalibrationDetailAfterScan =  dBQueryPropertyFile.getQueryForKey("FindCalibrationDetailAfterScan");
	//		List<CalibData> calibData = jdbcTemplate1.query(FindCalibrationDetailAfterScan, new Object[] { identityFicationNum }, new calibDataMapper());
	//		logger.info("INSIDE QRAndBARCodeDaoIMPL START METHOD detailsForQrAndBarCode");
	//		return calibData;
	//	}


}
