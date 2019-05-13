package com.spiraxcalibration.daoIMPL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import com.spiraxcalibration.WebConfig.AppsPropertyFile;
import com.spiraxcalibration.controllers.WhoColumnsController;
import com.spiraxcalibration.dao.PRPIDao;
import com.spiraxcalibration.dao.SupIDao;
import com.spiraxcalibration.models.CalibMainData;
import com.spiraxcalibration.models.PRPData;
import com.spiraxcalibration.models.PrData;
import com.spiraxcalibration.models.SUPData;
import com.spiraxcalibration.models.UserData;
import com.spiraxcalibration.utils.DateUtils;

@Repository
public class PRPDaoIMPL implements PRPIDao{

	Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	AppsPropertyFile dBQueryPropertyFile = new AppsPropertyFile();

	@Autowired
	JdbcTemplate  jdbcTemplate1;

	@Autowired
	SupIDao  supIDao;
	
	@Autowired
	WhoColumnsController  whoColumnsController;


	@Override
	public List<CalibMainData> pRPgetCalibDetails() {
		logger.info("INSIDE PRPDaoIMPL START METHOD pRPgetCalibDetails ::");
		String purchaseRquisitionsQuery = dBQueryPropertyFile.getQueryForKey("purchaseRquisitionsQuery");
		List<CalibMainData> calibDataList = jdbcTemplate1.query(purchaseRquisitionsQuery, new MainCalibDataMapper());
		logger.info("INSIDE PRPDaoIMPL END METHOD pRPgetCalibDetails ::");
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
	public List<PRPData> pRPgetDetailsByID(Integer pRp_Id) {
		logger.info("INSIDE PRPDaoIMPL START METHOD pRPgetCalibDetails");
		String FindPrpByIdQuery = dBQueryPropertyFile.getQueryForKey("FindPrpByIdQuery");
		logger.info("INSIDE PRPDaoIMPL END METHOD pRPgetCalibDetails");
		return jdbcTemplate1.query(FindPrpByIdQuery,  new Object[] { pRp_Id }, new prPDataMapper());
	}

	private static final class prPDataMapper implements RowMapper<PRPData>{
		@Override
		public PRPData mapRow(ResultSet rs , int rowNum) throws SQLException {
			PRPData calibData = new PRPData();
			calibData.setpRpPRId(rs.getInt("pr_id"));
			calibData.setpRpCalibId(rs.getInt("calib_id"));
			calibData.setpRpEquipId(rs.getInt("Equip_Id"));
			calibData.setpRpApprover1Comments(rs.getString("Approver1_Comments"));
			calibData.setpRpApprover2Comments(rs.getString("Approver2_comments"));
			calibData.setpRpApprover1Status(rs.getString("Approver1_Status"));
			calibData.setpRpApprover2Status(rs.getString("Approver2_Status"));
			calibData.setpRpIdentificationNum(rs.getString("Identification_No"));
			calibData.setpRpEquipmentDescription(rs.getString("Equipment_Desc"));
			calibData.setpRpSupplierName(rs.getString("Supplier_Name"));
			calibData.setpRpPrNumber(rs.getString("Pr_Number"));
			return calibData;
		}
	}

	private static final class prPDataApproverMapper implements RowMapper<PRPData>{
		@Override
		public PRPData mapRow(ResultSet rs , int rowNum) throws SQLException {
			PRPData calibData = new PRPData();
			calibData.setpRpPRId(rs.getInt("pr_id"));
			calibData.setpRpApprover1Comments(rs.getString("Approver1_Comments"));
			calibData.setpRpApprover2Comments(rs.getString("Approver2_comments"));
			calibData.setpRpApprover1Status(rs.getString("Approver1_Status"));
			calibData.setpRpApprover2Status(rs.getString("Approver2_Status"));
			calibData.setpRpIdentificationNum(rs.getString("Identification_No"));
			calibData.setpRpEquipmentDescription(rs.getString("Equipment_Desc"));
			calibData.setpRpSupplierName(rs.getString("Supplier_Name"));
			calibData.setpRpPrNumber(rs.getString("Pr_Number"));
			return calibData;
		}
	}


	@Override
	public PRPData pRpSavePRDetailsForSupplierNumberBySupNum(String supplierNumber) {
		logger.info("INSIDE PRPDaoIMPL START METHOD pRpSavePRDetailsForSupplierNumberBySupNum");
		SUPData sUPData = supIDao.supShowSupplierDetailBySupplierNum(supplierNumber);
		PRPData pRPData =  null;
		if(sUPData != null){
			pRPData = new PRPData();
			Date date = new Date();
			String prTrackDate = DateUtils.gettrackDate(date);
			pRPData.setpRpSupplierName(sUPData.getSupSupplierName());
			pRPData.setpRpSupplierId(sUPData.getSupSupId());
			pRPData.setpRpSupplierNumber(sUPData.getSupSupplierNumber());
			Map<String,String> whoColumnMap = whoColumnsController.getWhoColumnsInfo();
			if(whoColumnMap != null){
				pRPData.setpRpCreatedBy((String) whoColumnMap.get("userId"));
				pRPData.setpRpCreatedDate((String) whoColumnMap.get("currentDate"));
			}
			
			String pRPsavePrpDetailsQuery =  dBQueryPropertyFile.getQueryForKey("pRPsavePrpDetailsQuery");
			int num = jdbcTemplate1.update(pRPsavePrpDetailsQuery,
					pRPData.getpRpSupplierId(),
					pRPData.getpRpSupplierName(),
					pRPData.getpRpSupplierNumber(),
					prTrackDate,
					pRPData.getpRpCreatedBy(),
					pRPData.getpRpCreatedDate()
					);

			if(num > 0){
				String pRpGetMaxPrIdQuery = dBQueryPropertyFile.getQueryForKey("pRpGetMaxPrIdQuery");
				int prID = jdbcTemplate1.queryForObject(pRpGetMaxPrIdQuery, new Object[]{prTrackDate}  ,Integer.class);
				if(prID > 0){
					System.out.println("testing data :::"+prID);
					pRPData.setpRpPRId(prID);
				}
			}
			return pRPData;
		}
		logger.info("INSIDE PRPDaoIMPL END METHOD pRpSavePRDetailsForSupplierNumberBySupNum");
		return pRPData;
	}

	@Override
	public List<PrData> pRpGetAllIdentificationNumber(String supplierNumber) {
		logger.info("INSIDE PRPDaoIMPL START METHOD pRpGetAllIdentificationNumber");
		String getAllIdentificationNumberBySupplierNum = dBQueryPropertyFile.getQueryForKey("getAllIdentificationNumberBySupplierNum");
		List<PrData> getEquipmentTypeList =  jdbcTemplate1.query(getAllIdentificationNumberBySupplierNum, new Object[]{supplierNumber}, new PrDataMapper());
		logger.info("INSIDE PRPDaoIMPL END METHOD pRpGetAllIdentificationNumber");
		return getEquipmentTypeList;
	}

	private static final class PrDataMapper implements RowMapper<PrData>{
		@Override
		public PrData mapRow(ResultSet rs , int rowNum) throws SQLException {
			PrData prData = new PrData();
			prData.setPrIdentificationNo(rs.getString("Identification_no"));
			prData.setPrEquipmentType(rs.getString("EquipmentType"));
			return prData;
		}
	}

	@Override
	public PRPData updatePrpDetails(String supplierID) {
		logger.info("INSIDE PRPDaoIMPL START METHOD updatePrpDetails");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		PRPData pRPData = new PRPData();
		pRPData.setpRpApprover1Status("Pending");
		pRPData.setpRpApprover2Status("Pending");
		if(authentication.getName() != null){
			pRPData.setpRpRaisePRMailId(authentication.getName());
			String getRaisedPRFullNameByIDQuery = dBQueryPropertyFile.getQueryForKey("getRaisedPRFullNameByIDQuery");
			UserData userRaisedPrData =  jdbcTemplate1.queryForObject(getRaisedPRFullNameByIDQuery, new Object[]{pRPData.getpRpRaisePRMailId()}, new UserDataMapper());
			if(userRaisedPrData != null){
				String userFullName = userRaisedPrData.getUserFirstName()+" "+userRaisedPrData.getUserLastName();
				pRPData.setpRpRaisedPrFullName(userFullName);
			}
		}
		String getSupplierDetailsByIdQuery =  dBQueryPropertyFile.getQueryForKey("getSupplierDetailsByIdQuery");
		SUPData supData = jdbcTemplate1.queryForObject(getSupplierDetailsByIdQuery , new Object[]{supplierID} , new supDataMapper());
		if(supData != null){
			String supplierName = supData.getSupSupplierName();
			String supplierNumber = supData.getSupSupplierNumber();
			pRPData.setpRpSupplierName(supplierName);
			pRPData.setpRpSupplierNumber(supplierNumber);
			pRPData.setpRpSupplierId(supData.getSupSupId());
		}
		Date date = new Date();
		String prTrackDate = DateUtils.gettrackDate(date);

		String todayDate = DateUtils.convertToDDMMMYYYY(date); 
		if(todayDate != null){
			pRPData.setpRpGeneratePrDate(todayDate);
		}
		String getApprover1FullNameQuery = dBQueryPropertyFile.getQueryForKey("getApprover1FullNameQuery");
		UserData userDataApprover1 =  jdbcTemplate1.queryForObject(getApprover1FullNameQuery, new UserDataMapper());
		String Approver1FullName = userDataApprover1.getUserFirstName()+" "+userDataApprover1.getUserLastName();
		String Approver1MailId = userDataApprover1.getUseUserName();
		if(userDataApprover1 != null){
			pRPData.setpRpApprover1FullName(Approver1FullName);
			pRPData.setpRpApprover1Name(Approver1MailId);
		}
		String getApprover2FullNameQuery = dBQueryPropertyFile.getQueryForKey("getApprover2FullNameQuery");
		UserData userDataApprover2 =  jdbcTemplate1.queryForObject(getApprover2FullNameQuery, new UserDataMapper());
		String Approver2FullName = userDataApprover2.getUserFirstName()+" "+userDataApprover2.getUserLastName();
		String Approver2MailId = userDataApprover1.getUseUserName();
		if(userDataApprover2 != null){
			pRPData.setpRpApprover2FullName(Approver2FullName);
			pRPData.setpRpApprover2Name(Approver2MailId);
		}
		
		Map<String,String> whoColumnMap = whoColumnsController.getWhoColumnsInfo();
		if(whoColumnMap != null){
			pRPData.setpRpCreatedBy((String) whoColumnMap.get("userId"));
			pRPData.setpRpCreatedDate((String) whoColumnMap.get("currentDate"));
		}
		
		String pRPsavePrpDetailsQuery =  dBQueryPropertyFile.getQueryForKey("pRPsavePrpDetailsQuery");
		int num = jdbcTemplate1.update(pRPsavePrpDetailsQuery,
				pRPData.getpRpSupplierId(),
				pRPData.getpRpSupplierName(),
				pRPData.getpRpSupplierNumber(),
				pRPData.getpRpGeneratePrDate(),
				pRPData.getpRpApprover1Status(),
				pRPData.getpRpApprover2Status(),
				pRPData.getpRpRaisePRMailId(),
				pRPData.getpRpApprover1FullName(),
				pRPData.getpRpApprover2FullName(),
				pRPData.getpRpApprover1Name(),
				pRPData.getpRpApprover2Name(),
				pRPData.getpRpRaisedPrFullName(),
				prTrackDate,
				pRPData.getpRpCreatedBy(),
				pRPData.getpRpCreatedDate());
		if(num > 0){
			String pRpGetMaxPrIdQuery = dBQueryPropertyFile.getQueryForKey("pRpGetMaxPrIdQuery");
			int prID = jdbcTemplate1.queryForObject(pRpGetMaxPrIdQuery, new Object[]{prTrackDate}  ,Integer.class);
			if(prID > 0){
				pRPData.setpRpPRId(prID);
			}if(pRPData.getpRpPRId() != null){
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(new Date());
				String generatePrNumber = "SXS-PR-"+pRPData.getpRpPRId()+"-"+calendar.get(Calendar.YEAR);
				String getCalibMainDetailsBySupplierIdQuery =  dBQueryPropertyFile.getQueryForKey("getCalibMainDetailsBySupplierIdQuery");
				List<CalibMainData> calibDataList = jdbcTemplate1.query(getCalibMainDetailsBySupplierIdQuery, new Object[]{supplierID}, new MainCalibDataMapper());
				if(calibDataList != null){
					String pRpSaveItemDetailsQuery =  dBQueryPropertyFile.getQueryForKey("pRpSaveItemDetailsQuery");
					for(CalibMainData calibMainData : calibDataList){

						boolean hasUserRole = authentication.getAuthorities().stream()
								.anyMatch(r -> r.getAuthority().equals(dBQueryPropertyFile.getUserDetail("spring.role.admin")));

						boolean hasManagerRole = authentication.getAuthorities().stream()
								.anyMatch(r -> r.getAuthority().equals(dBQueryPropertyFile.getUserDetail("spring.role.manager")));

						if(hasUserRole || hasManagerRole || checkIfDueDateFuture(calibMainData)){
							jdbcTemplate1.update(pRpSaveItemDetailsQuery,
									pRPData.getpRpPRId(),
									calibMainData.getMainEcCalibId(),
									calibMainData.getMainEcSupplierId(),
									calibMainData.getMainIdentificationId(),
									calibMainData.getMainInstrumentGauge(),
									generatePrNumber);
						}
					}
					String pRpUpdatePurchaseIdQuery =  dBQueryPropertyFile.getQueryForKey("pRpUpdatePurchaseIdQuery");
					jdbcTemplate1.update(pRpUpdatePurchaseIdQuery,pRPData.getpRpPRId(),pRPData.getpRpPRId());
				}
			}
		}
		logger.info("INSIDE PRPDaoIMPL END METHOD updatePrpDetails");
		return pRPData;
	}

	private static final class UserDataMapper implements RowMapper<UserData>{

		@Override
		public UserData mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserData userData = new UserData();
			userData.setUserFirstName(rs.getString("First_Name"));
			userData.setUserLastName(rs.getString("Last_Name"));
			userData.setUseUserName(rs.getString("User_Name"));
			return userData;
		}
	}
	
	private Boolean checkIfDueDateFuture(CalibMainData calibMain) {

		String date = DateUtils.convertToDDMMMYYYY(new Date());
//		Date sysDate = null;

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, +7);
		Date sysDate = cal.getTime();
		System.out.println("datecal 7 :"+sysDate);
		//	sysDate = DateUtils.convertToDDMMMYYYYParseToDate(date);
		try {
			Date date2 = DateUtils.convertToDDMMMYYYYParseToDate(calibMain.getMainDueDate());
			if(date2.compareTo(sysDate) < 0){
				// check if due date is less than the system date plus 7 days..
				return false;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public List<CalibMainData> mainCalibSearchByCondition(String supplierid) {
		logger.info("INSIDE PRPDaoIMPL START METHOD mainCalibSearchByCondition ::");
		String selectMainCalibConditionQuery = dBQueryPropertyFile.getQueryForKey("selectMainCalibConditionQuery");
		List<CalibMainData> searchedList = jdbcTemplate1.query(selectMainCalibConditionQuery, new Object[]{supplierid} , new MainCalibDataMapper());
		logger.info("INSIDE PRPDaoIMPL END METHOD mainCalibSearchByCondition ::");
		return searchedList;
	}

	@Override
	public List<PRPData> pRPgetprDetails() {
		logger.info("INSIDE PRPDaoIMPL START METHOD pRPgetprDetails ::");
		String getprDetailsForApproveQuery = dBQueryPropertyFile.getQueryForKey("getprDetailsForApproveQuery");
		List<PRPData> calibDataList = jdbcTemplate1.query(getprDetailsForApproveQuery,new prPDataApproverMapper());
		logger.info("INSIDE PRPDaoIMPL END METHOD pRPgetprDetails ::");
		return calibDataList;
	}

	@Override
	public List<PRPData> pRPgetprDetailsByPrpId(Integer pr_Id) {
		logger.info("INSIDE PRPDaoIMPL START METHOD pRPgetprDetailsByPrpId ::");
		String getprItemDetailsByPrpIdQuery = dBQueryPropertyFile.getQueryForKey("getprItemDetailsByPrpIdQuery");
		List<PRPData> calibDataList = jdbcTemplate1.query(getprItemDetailsByPrpIdQuery, new Object[]{pr_Id},new prPFormDataMapper());
		logger.info("INSIDE PRPDaoIMPL END METHOD pRPgetprDetailsByPrpId ::");
		return calibDataList;
	}

	private static final class prPFormDataMapper implements RowMapper<PRPData>{
		@Override
		public PRPData mapRow(ResultSet rs , int rowNum) throws SQLException {
			PRPData calibData = new PRPData();
			calibData.setpRpPRId(rs.getInt("pr_id"));
			calibData.setpRpEquipmentDescription(rs.getString("Equipment_Desc"));
			calibData.setpRpEquipId(rs.getInt("Equip_Id"));
			calibData.setpRpIdentificationNum(rs.getString("Identification_No"));
			calibData.setpRpPartCode(rs.getString("Part_Code"));
			calibData.setpRpBudgetryCost(rs.getString("calibration_Price"));
			calibData.setpRpDeliveryDate(rs.getString("Due_Date"));
			return calibData;
		}
	}

	private static final class supDataMapper implements RowMapper<SUPData>{
		@Override
		public SUPData mapRow(ResultSet rs , int rowNum) throws SQLException {
			SUPData supData = new SUPData();
			supData.setSupSupplierName(rs.getString("Supplier_Name"));
			supData.setSupSupplierNumber(rs.getString("supplier_number"));
			supData.setSupSupId(rs.getInt("Sup_Id"));
			return supData;
		}
	}

	@Override
	public PRPData getPrPDetailsByPrpID(Integer pr_Id) {
		logger.info("INSIDE PRPDaoIMPL START METHOD pRPgetprDetailsByPrpId ::");
		String getprDetailsByPrpIdQuery = dBQueryPropertyFile.getQueryForKey("getprDetailsByPrpIdQuery");
		PRPData calibData = jdbcTemplate1.queryForObject(getprDetailsByPrpIdQuery, new Object[]{pr_Id},new prPSingleFormDataMapper());
		logger.info("INSIDE PRPDaoIMPL END METHOD pRPgetprDetailsByPrpId ::");
		return calibData;
	}

	private static final class prPSingleFormDataMapper implements RowMapper<PRPData>{
		@Override
		public PRPData mapRow(ResultSet rs , int rowNum) throws SQLException {
			PRPData calibData = new PRPData();
			calibData.setpRpPRId(rs.getInt("pr_id"));
			calibData.setpRpApprover1Comments(rs.getString("Approver1_Comments"));
			calibData.setpRpApprover2Comments(rs.getString("Approver2_comments"));
			calibData.setpRpApprover1Status(rs.getString("Approver1_Status"));
			calibData.setpRpApprover2Status(rs.getString("Approver2_Status"));
			calibData.setpRpGeneratePrDate(rs.getString("Pr_Raised_Date"));
			calibData.setpRpSupplierId(rs.getInt("Supplier_Id"));
			calibData.setpRpSupplierName(rs.getString("Supplier_Name"));
			calibData.setpRpApprover1FullName(rs.getString("Approver1_FullName"));
			calibData.setpRpApprover2FullName(rs.getString("Approver2_FullName"));
			calibData.setpRpRaisedPrFullName(rs.getString("Raised_PR_FullName"));
			calibData.setpRpPrNumber(rs.getString("Pr_Number"));
			calibData.setpRpCalibId(rs.getInt("Calib_Id"));
			return calibData;
		}
	}

	@Override
	public void saveApproverDetails(String apporver1Name, String apporver1Status, String apporver1Comments) {

	}

	@Override
	public int saveApproverDetails(PRPData pRPData) {
		logger.info("INSIDE PRPDaoIMPL START METHOD saveApproverDetails");
		if(pRPData.getpRpApprover1Status().equalsIgnoreCase("Rejected")){
			pRPData.setpRpPrStatus("Inactive");
			String pRpUpdatePurchaseIdForInactiveQuery =  dBQueryPropertyFile.getQueryForKey("pRpUpdatePurchaseIdForInactiveQuery");
			jdbcTemplate1.update(pRpUpdatePurchaseIdForInactiveQuery,
					0,pRPData.getpRpPRId());
		}
		Map<String,String> whoColumnMap = whoColumnsController.getWhoColumnsInfo();
		if(whoColumnMap != null){
			pRPData.setpRpUpdatedBy((String) whoColumnMap.get("userId"));
			pRPData.setpRpUpdatedDate((String) whoColumnMap.get("currentDate"));
		}
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");  
		String strDate = dateFormat.format(date); 
		
		String updateApproverDetailsQuery =  dBQueryPropertyFile.getQueryForKey("updateApproverDetailsQuery");
		int num = jdbcTemplate1.update(updateApproverDetailsQuery,
				pRPData.getpRpApprover1Name(),
				pRPData.getpRpApprover1Status(),
				pRPData.getpRpApprover1Comments(),
				pRPData.getpRpPrStatus(),
				pRPData.getpRpUpdatedBy(),
				pRPData.getpRpUpdatedDate(),
				strDate,
				pRPData.getpRpPRId());
		if(num > 0){
			return num;
		}
		logger.info("INSIDE PRPDaoIMPL END METHOD saveApproverDetails");
		return num;
	}

	@Override
	public List<String> getSupplierNameList() {
		logger.info("INSIDE PRPDaoIMPL START METHOD getSupplierNameList ::");
		String getSupplierNameForPrQuery = dBQueryPropertyFile.getQueryForKey("getSupplierNameForPrQuery");
		List<String> supplierNameList = jdbcTemplate1.queryForList(getSupplierNameForPrQuery,String.class);
		logger.info("INSIDE PRPDaoIMPL END METHOD getSupplierNameList ::");
		return supplierNameList;
	}
	
	
	@Override
	public List<String> getPrpIDList() {
		logger.info("INSIDE PRPDaoIMPL START METHOD getSupplierNameList ::");
		String getPrNumberForPrQuery = dBQueryPropertyFile.getQueryForKey("getPrNumberForPrQuery");
		List<String> supplierNameList = jdbcTemplate1.queryForList(getPrNumberForPrQuery,String.class);
		logger.info("INSIDE PRPDaoIMPL END METHOD getSupplierNameList ::");
		return supplierNameList;
	}
	@Override
	public List<String> getApprover1StatusList() {
		logger.info("INSIDE PRPDaoIMPL START METHOD getApprover1StatusList ::");
		String getApprover1StatusQuery = dBQueryPropertyFile.getQueryForKey("getApprover1StatusQuery");
		List<String> approver1StatusList = jdbcTemplate1.queryForList(getApprover1StatusQuery,String.class);
		logger.info("INSIDE PRPDaoIMPL END METHOD getApprover1StatusList ::");
		return approver1StatusList;
	}

	@Override
	public List<String> getApprover2StatusList() {
		logger.info("INSIDE PRPDaoIMPL START METHOD getApprover2StatusList ::");
		String getApprover2StatusQuery = dBQueryPropertyFile.getQueryForKey("getApprover2StatusQuery");
		List<String> approver1StatusList = jdbcTemplate1.queryForList(getApprover2StatusQuery,String.class);
		logger.info("INSIDE PRPDaoIMPL END METHOD getApprover2StatusList ::");
		return approver1StatusList;
	}

//	@Override
//	public List<String> getPrpIDList() {
//		logger.info("INSIDE PRPDaoIMPL START METHOD getPrpIDList ::");
//		String getPrpIdListQuery = dBQueryPropertyFile.getQueryForKey("getPrpIdListQuery");
//		List<String> approver1StatusList = jdbcTemplate1.queryForList(getPrpIdListQuery,String.class);
//		logger.info("INSIDE PRPDaoIMPL END METHOD getPrpIDList ::");
//		return approver1StatusList;
//	}

	@Override
	public List<PRPData> pRpSearchByCondition(String pRpNumber, String supplierName, String approver1Status,
			String approver2Status) {
		logger.info("INSIDE PrDaoIMPL START METHOD pRpSearchByCondition ::");
		String selectPrPListByConditionQuery = dBQueryPropertyFile.getQueryForKey("selectPrPListByConditionQuery");
		List<PRPData> searchedList = jdbcTemplate1.query(selectPrPListByConditionQuery, new Object[]{pRpNumber+"%", supplierName+"%", approver1Status+"%" , approver2Status+"%"} , new prPDataMapper());
		logger.info("INSIDE PrDaoIMPL END METHOD pRpSearchByCondition ::");
		return searchedList;
	}

	@Override
	public List<CalibMainData> pRPgetCalibDetails(String supID) {
		logger.info("INSIDE PRPDaoIMPL START METHOD pRPgetCalibDetails ::");
		String selectMainCalibConditionByIdQuery = dBQueryPropertyFile.getQueryForKey("selectMainCalibConditionByIdQuery");
		List<CalibMainData> searchedList = jdbcTemplate1.query(selectMainCalibConditionByIdQuery, new Object[]{supID} , new MainCalibDataMapper());
		logger.info("INSIDE PRPDaoIMPL END METHOD pRPgetCalibDetails ::");
		return searchedList;
	}

	@Override
	public int saveApprover2Details(PRPData pRPData) {
		logger.info("INSIDE THE START METHOD saveApprover2Details");
		if(pRPData.getpRpApprover2Status().equalsIgnoreCase("Rejected")){
			pRPData.setpRpPrStatus("Inactive");
			String pRpUpdatePurchaseIdForInactiveQuery =  dBQueryPropertyFile.getQueryForKey("pRpUpdatePurchaseIdForInactiveQuery");
			jdbcTemplate1.update(pRpUpdatePurchaseIdForInactiveQuery,
					0,pRPData.getpRpPRId());
		}
		Map<String,String> whoColumnMap = whoColumnsController.getWhoColumnsInfo();
		if(whoColumnMap != null){
			pRPData.setpRpUpdatedBy((String) whoColumnMap.get("userId"));
			pRPData.setpRpUpdatedDate((String) whoColumnMap.get("currentDate"));
		}
		
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");  
		String strDate = dateFormat.format(date); 
		String updateApprover2DetailsQuery =  dBQueryPropertyFile.getQueryForKey("updateApprover2DetailsQuery");
		int num = jdbcTemplate1.update(updateApprover2DetailsQuery,
				pRPData.getpRpApprover2Name(),
				pRPData.getpRpApprover2Status(),
				pRPData.getpRpApprover2Comments(),
				pRPData.getpRpPrStatus(),
				pRPData.getpRpUpdatedBy(),
				pRPData.getpRpUpdatedDate(),
				strDate,
				pRPData.getpRpPRId());
		if(num > 0){
			return num;
		}
		logger.info("INSIDE THE END METHOD saveApprover2Details");
		return num;
	}

	@Override
	public String getRaisedPRMailID(String prpId) {
		logger.info("INSIDE PRPDaoIMPL START METHOD getRaisedPRMailID ::");
		String getRaisedUserMailIdQuery = dBQueryPropertyFile.getQueryForKey("getRaisedUserMailIdQuery");
		String supplierMailId = jdbcTemplate1.queryForObject(getRaisedUserMailIdQuery, new Object[]{prpId} , String.class);
		logger.info("INSIDE PRPDaoIMPL END METHOD getRaisedPRMailID ::");
		return supplierMailId;
	}

	@Override
	public String getSupplierMailId(String supplierId) {
		logger.info("INSIDE PRPDaoIMPL START METHOD getSupplierMailId ::");
		String getSupplierMailIdQuery = dBQueryPropertyFile.getQueryForKey("getSupplierMailIdQuery");
		String supplierMailId = jdbcTemplate1.queryForObject(getSupplierMailIdQuery, new Object[]{supplierId} , String.class);
		logger.info("INSIDE PRPDaoIMPL END METHOD getSupplierMailId ::");
		return supplierMailId;
	}

	@Override
	public String getPRNumberByPrId(Integer pRpPRId) {
		logger.info("INSIDE PRPDaoIMPL START METHOD getPRNumberByPrId ::");
		String getPrNumberQuery = dBQueryPropertyFile.getQueryForKey("getPrNumberQuery");
		String prNumber = jdbcTemplate1.queryForObject(getPrNumberQuery, new Object[]{pRpPRId} , String.class);
		logger.info("INSIDE PRPDaoIMPL END METHOD getPRNumberByPrId ::");
		return prNumber;
	}

	@Override
	public String getProcurementMailId() {
		logger.info("INSIDE PRPDaoIMPL START METHOD getProcurementMailId ::");
		String getProcurementMailIdQuery = dBQueryPropertyFile.getQueryForKey("getProcurementMailIdQuery");
		String procurementMailId = null;
		try{
			procurementMailId = jdbcTemplate1.queryForObject(getProcurementMailIdQuery,String.class);
		}catch(Exception e){
			return null;
		}
		logger.info("INSIDE PRPDaoIMPL END METHOD getProcurementMailId ::");
		return procurementMailId;
	}

}
