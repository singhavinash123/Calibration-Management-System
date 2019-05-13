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
import com.spiraxcalibration.dao.CalibIDao;
import com.spiraxcalibration.models.CalibData;
import com.spiraxcalibration.models.CalibMainData;
import com.spiraxcalibration.models.PrData;
import com.spiraxcalibration.models.UserData;

@Repository
public class CalibDaoIMPL implements CalibIDao{

	Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	AppsPropertyFile dBQueryPropertyFile = new AppsPropertyFile();

	@Autowired
	JdbcTemplate  jdbcTemplate1;

	@Override
	public List<PrData> calibGetProductDetails() {
		logger.info("INSIDE CalibDaoIMPL START METHOD prGetProductDetails ::");
		String selectPrListQuery = dBQueryPropertyFile.getQueryForKey("selectPrListQuery");
		List<PrData> prList = jdbcTemplate1.query(selectPrListQuery, new PrDataMapper());
		logger.info("INSIDE CalibDaoIMPL END METHOD prGetProductDetails ::");
		return prList;
	}

	@Override
	public List<PrData> calibSearchByCondition(String identityNumber, String serialNumber, String location, String calibAgency) {
		logger.info("INSIDE CalibDaoIMPL START METHOD prSearchByCondition ::");
		String selectCalibPrListByConditionQuery = dBQueryPropertyFile.getQueryForKey("selectCalibPrListByConditionQuery");
		List<PrData> searchedList = jdbcTemplate1.query(selectCalibPrListByConditionQuery, new Object[]{identityNumber+"%", serialNumber+"%", location+"%", calibAgency+"%"} , new PrDataMapper());
		logger.info("INSIDE CalibDaoIMPL END METHOD prSearchByCondition ::");
		return searchedList;
	}
	private static final class PrDataMapper implements RowMapper<PrData>{
		@Override
		public PrData mapRow(ResultSet rs , int rowNum) throws SQLException {
			PrData prData = new PrData();
			prData.setPrcalibratedBy(rs.getString("calibrated_by"));
			prData.setPrCalibrationReminderDate(rs.getString("Calibration_Reminder_Date"));
			prData.setPrCalibrationStd(rs.getString("Calibration_Std"));
			prData.setPrCALIBStatus(rs.getString("CALIB_Status"));
			prData.setPrCreatedBy(rs.getInt("created_by"));
			prData.setPrCreationDate(rs.getString("creation_date"));
			prData.setPrCurrentEQDate(rs.getString("Current_EQ_Date"));
			prData.setPrDaysPending(rs.getString("Days_Pending"));
			prData.setPrInstrumentLineItem(rs.getString("Instrument_Line_Item"));
			prData.setPrInstrumentStatus(rs.getString("Instrument_Status"));
			prData.setPrLastUpdateDate(rs.getString("last_update_date"));
			prData.setPrLastUpdatedBy(rs.getInt("last_updated_by"));
			prData.setPrLastUpdateLogin(rs.getInt("last_update_login"));
			prData.setPrMake(rs.getString("Make"));
			prData.setPrModel(rs.getString("Model"));
			prData.setPrResult(rs.getString("Result"));
			prData.setPrsupplierMeasuredResult(rs.getString("supplier_measured_result"));
			prData.setPrVerifiedBy(rs.getString("Verified_by"));
			prData.setPrProdId(rs.getInt("PROD_ID"));
			prData.setPrDescription(rs.getString("Description"));
			prData.setPrIdentificationNo(rs.getString("Identification_no"));
			prData.setPrSerialNo(rs.getString("Serial_No"));
			prData.setPrPRODRange(rs.getString("PROD_Range"));
			prData.setPrLeastCount(rs.getInt("Least_Count"));
			prData.setPrCategories(rs.getString("Categories"));
			prData.setPrCalibrationAgency(rs.getString("Calibration_Agency"));
			prData.setPrEquipmentLocation(rs.getString("Equipment_Location"));
			prData.setPrBorrower(rs.getString("Borrower"));
			prData.setPrCalibrationFrequecy(rs.getString("Calibration_Frequecy"));
			prData.setPrCalibrationFrequecyD(rs.getString("Calibration_Frequecy_D"));
			prData.setPrCalibrationDate(rs.getString("Calibration_Date"));
			prData.setPrDueDate(rs.getString("Due_Date"));
			prData.setPrEquipmentStatus(rs.getString("Equipment_Status"));
			prData.setPrSupplierName(rs.getString("SUP_Name"));

			return prData;
		}
	}

	@Override
	public List<CalibData> calibGetCalibrationsById(Integer prodId) {
		logger.info("INSIDE CalibDaoIMPL START METHOD calibGetCalibrationById");
		String FindCalibrationByIdQuery =  dBQueryPropertyFile.getQueryForKey("FindCalibrationByIdQuery");
		List<CalibData> calibDataList = jdbcTemplate1.query(FindCalibrationByIdQuery,  new Object[] { prodId }, new calibDataMapper());
		logger.info("INSIDE CalibDaoIMPL END METHOD calibGetCalibrationById");
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
			calibData.setCalibSendForApprovalStatusFlag(rs.getInt("SendForApprovalStatusFlag"));
			calibData.setCalibAprroverMailId(rs.getString("Email_Id"));
			return calibData;
		}
	}

	private static final class UserDataMapper implements RowMapper<UserData>{
		@Override
		public UserData mapRow(ResultSet rs , int rowNum) throws SQLException {
			UserData userData = new UserData();
			userData.setUserFirstName(rs.getString("First_Name"));
			userData.setUserLastName(rs.getString("Last_Name"));
			return userData;
		}
	}
	@Override
	public int calibSaveCalibrationDetail(CalibData calibData) {
		String ApproverNamefromMailIdQuery = dBQueryPropertyFile.getQueryForKey("ApproverNamefromMailIdQuery");
		UserData userData =  jdbcTemplate1.queryForObject(ApproverNamefromMailIdQuery,  new Object[] { calibData.getCalibAprroverMailId() }, new UserDataMapper());
		if(userData != null){
			calibData.setCalibApprover1Name(userData.getUserFirstName()+" "+userData.getUserLastName());
		}
		String addCalibrationQuery =  dBQueryPropertyFile.getQueryForKey("addCalibrationQuery");
		int AfterInsertnumber = jdbcTemplate1.update(addCalibrationQuery,
				calibData.getCalibCalibrationFrequency(),
				calibData.getCalibCalibrationDate(),
				calibData.getCalibCalibrationDueDate(),
				calibData.getCalibCalibrationReminderDate(),
				calibData.getCalibCalibStatus(),
				calibData.getCalibProdId(),
				calibData.getCalibSerialNumber(),
				calibData.getCalibIdentificationNo(),
				calibData.getCalibCalibrationType(),
				calibData.getCalibApprover1Status(),
				calibData.getCalibApprover2Status(),
				calibData.getCalibSupplierName(),
				calibData.getCalibApprover1Name(),
				calibData.getCalibAprroverMailId());
		if(AfterInsertnumber > 0){
			String updateEquipStatusFromEquipMasterQuery = dBQueryPropertyFile.getQueryForKey("updateEquipStatusFromEquipMasterQuery");
			int numberAfterUpdate = jdbcTemplate1.update(updateEquipStatusFromEquipMasterQuery,calibData.getCalibIdentificationNo());
		}
		return 	AfterInsertnumber;	
	}

	@Override
	public CalibMainData calibGetCalibrationById(Integer calibId) {
		logger.info("INSIDE CalibDaoIMPL START METHOD calibGetCalibrationById");
		String FindCalibforUploadByIdQuery =  dBQueryPropertyFile.getQueryForKey("FindCalibforUploadByIdQuery");
		CalibMainData calibMainData = jdbcTemplate1.queryForObject(FindCalibforUploadByIdQuery,  new Object[] { calibId }, new MainCalibDataMapper());
		logger.info("INSIDE CalibDaoIMPL END METHOD calibGetCalibrationById");
	    return calibMainData;

	}
	
//	@Override
//	public CalibMainData calibGetCalibrationById(Integer calibId) {
//		logger.info("INSIDE CalibDaoIMPL START METHOD calibGetCalibrationById");
//		String FindCalibforUploadByIdQuery =  dBQueryPropertyFile.getQueryForKey("FindCalibforUploadByIdQuery");
//		logger.info("INSIDE CalibDaoIMPL END METHOD calibGetCalibrationById");
//		return jdbcTemplate1.queryForObject(FindCalibforUploadByIdQuery,  new Object[] { calibId }, new MainCalibDataMapper());
//
//	}
	
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
			return calibMainData;
		}
	}

//	@Override
//	public CalibData calibGetCalibrationById(Integer calibId) {
//		logger.info("INSIDE CalibDaoIMPL START METHOD calibGetCalibrationById");
//		String FindCalibforUploadByIdQuery =  dBQueryPropertyFile.getQueryForKey("FindCalibforUploadByIdQuery");
//		logger.info("INSIDE CalibDaoIMPL END METHOD calibGetCalibrationById");
//		return jdbcTemplate1.queryForObject(FindCalibforUploadByIdQuery,  new Object[] { calibId }, new calibDataMapper());
//
//	}

	@Override
	public int calibGetcalibSatusCountIfCalibratedByProdID(Integer prodId) {
		logger.info("INSIDE CalibDaoIMPL START METHOD calibGetcalibSatusCountIfCalibratedByProdID");
		String getCalibcountForCalibStatusQuery = dBQueryPropertyFile.getQueryForKey("getCalibcountForCalibStatusQuery");
		int IdentityNumIfExistCount = jdbcTemplate1.queryForObject(getCalibcountForCalibStatusQuery, new Object[]{prodId}, Integer.class);
		logger.info("INSIDE CalibDaoIMPL END METHOD calibGetcalibSatusCountIfCalibratedByProdID");
		return IdentityNumIfExistCount;
	}

	@Override
	public int calibUpdateCalibDetails(CalibData calibData) {
		logger.info("INSIDE CalibDaoIMPL START METHOD calibUpdateCalibDetails");
		String updateCalibrationQuery =  dBQueryPropertyFile.getQueryForKey("updateCalibrationQuery");
		String ApproverNamefromMailIdQuery = dBQueryPropertyFile.getQueryForKey("ApproverNamefromMailIdQuery");
		UserData userData =  jdbcTemplate1.queryForObject(ApproverNamefromMailIdQuery,  new Object[] { calibData.getCalibAprroverMailId() }, new UserDataMapper());
		if(userData != null){
			calibData.setCalibApprover1Name(userData.getUserFirstName()+" "+userData.getUserLastName());
		}
		String checkIfCalibStatusAlreadyActiveQuery = dBQueryPropertyFile.getQueryForKey("checkIfCalibStatusAlreadyActiveQuery");
		int ifStatusActiveCount = jdbcTemplate1.queryForObject(checkIfCalibStatusAlreadyActiveQuery, new Object[]{calibData.getCalibIdentificationNo()}, Integer.class);
		if(calibData.getCalibCalibStatus().equalsIgnoreCase("Inactive") || (calibData.getCalibCalibStatus().equalsIgnoreCase("Active") && ifStatusActiveCount <= 0) ){
			int AfterUpdatenumber = jdbcTemplate1.update(updateCalibrationQuery,
					calibData.getCalibCalibrationFrequency(),
					calibData.getCalibCalibrationDate(),
					calibData.getCalibCalibrationDueDate(),
					calibData.getCalibCalibrationReminderDate(),
					calibData.getCalibCalibStatus(),
					calibData.getCalibProdId(),
					calibData.getCalibSerialNumber(),
					calibData.getCalibIdentificationNo(),
					calibData.getCalibCalibrationType(),
					calibData.getCalibApprover1Status(),
					calibData.getCalibApprover2Status(),
					calibData.getCalibApprover1Name(),
					calibData.getCalibApprover2Name(),
					calibData.getCalibApprover1Comments(),
					calibData.getCalibApprover2Comments(),
					calibData.getCalibAprroverMailId(),
					calibData.getCalibId());
			return 	AfterUpdatenumber;	
		}
		else if(ifStatusActiveCount > 0){
			return -1;
		}
		logger.info("INSIDE CalibDaoIMPL END METHOD calibUpdateCalibDetails");
		return 0;
	}

	@Override
	public CalibData calibGetCalibrationByCertId(Integer warrantyId) {
		logger.info("INSIDE CalibDaoIMPL START METHOD calibGetCalibrationByCertId");
		String FindCalibByCertIdQuery =  dBQueryPropertyFile.getQueryForKey("FindCalibByCertIdQuery");
		CalibData calibData =  jdbcTemplate1.queryForObject(FindCalibByCertIdQuery,  new Object[] { warrantyId }, new calibDataMapper());
		logger.info("INSIDE CalibDaoIMPL END METHOD calibGetCalibrationByCertId");
		return calibData;
	}

	@Override
	public int setPendingForApproval(Integer calibId) {
		String UpdateTheApprovalStatusFlagQuery = dBQueryPropertyFile.getQueryForKey("UpdateTheApprovalStatusFlagQuery");
		int num =  jdbcTemplate1.update(UpdateTheApprovalStatusFlagQuery , new Object[] { calibId });
		if(num > 0){
			// call the mail util from properties....
		}
		return num;
	}


	@Override
	public int calibApprovedOrReject(CalibData calibData) {
		String ApproverApprovedORrejectedQuery = dBQueryPropertyFile.getQueryForKey("ApproverApprovedORrejectedQuery");
		return jdbcTemplate1.update(ApproverApprovedORrejectedQuery,
				calibData.getCalibSendForApprovalStatusFlag(),
				calibData.getCalibApprover1Name(),
				calibData.getCalibApprover1Status(),
				calibData.getCalibApprover1Comments(),
				calibData.getCalibId());
	}

	@Override
	public int calibSaveApprover(CalibData calibData) {
		return 0;
	}

}
