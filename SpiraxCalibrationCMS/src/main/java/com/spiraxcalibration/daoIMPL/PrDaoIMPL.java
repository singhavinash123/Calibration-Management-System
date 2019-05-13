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
import com.spiraxcalibration.dao.PrIDao;
import com.spiraxcalibration.models.PRPData;
import com.spiraxcalibration.models.PrData;

@Repository
public class PrDaoIMPL implements PrIDao{

	Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	AppsPropertyFile dBQueryPropertyFile = new AppsPropertyFile();

	@Autowired
	JdbcTemplate  jdbcTemplate1;

	@Override
	public List<PrData> prGetProductDetails() {
		logger.info("INSIDE PrDaoIMPL START METHOD prGetProductDetails ::");
		String selectPrListQuery = dBQueryPropertyFile.getQueryForKey("selectPrListQuery");
		List<PrData> prList = jdbcTemplate1.query(selectPrListQuery, new PrDataMapper());
		logger.info("INSIDE PrDaoIMPL END METHOD prGetProductDetails ::");
		return prList;
	}


	@Override
	public List<PrData> prSearchByCondition(String identityNumber, String srNum, String location, String make, String supplierName) {
		logger.info("INSIDE PrDaoIMPL START METHOD prSearchByCondition ::");
		String selectPrListByConditionQuery = dBQueryPropertyFile.getQueryForKey("selectPrListByConditionQuery");
		List<PrData> searchedList = jdbcTemplate1.query(selectPrListByConditionQuery, new Object[]{identityNumber+"%", srNum+"%", location+"%", make+"%", supplierName+"%"} , new PrDataMapper());
		logger.info("INSIDE PrDaoIMPL END METHOD prSearchByCondition ::");
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
			prData.setPrSupplierName(rs.getString("SUP_Name"));
			prData.setPrSupplierNumber(rs.getString("SUP_Number"));
			prData.setPrEquipmentType(rs.getString("EquipmentType"));
			prData.setPrEquipmentStatus(rs.getString("Equipment_Status"));
			return prData;
		}
	}

	//	@Override
	//	public List<String> getMakeFromProduct() {
	//		logger.info("INSIDE PrDaoIMPL START METHOD getMakeFromProduct ::");
	//		String makeQuery = dBQueryPropertyFile.getQueryForKey("makeQuery");
	//		List<String> list =  jdbcTemplate1.queryForList(makeQuery, String.class);
	//		logger.info("INSIDE PrDaoIMPL END METHOD getMakeFromProduct ::");
	//		return list;
	//	}
	//
	//
	//	@Override
	//	public List<String> getLocationFromProduct() {
	//		logger.info("INSIDE PrDaoIMPL START METHOD getLocationFromProduct ::");
	//		String equipmentLocationQuery = dBQueryPropertyFile.getQueryForKey("equipmentLocationQuery");
	//		List<String> list =  jdbcTemplate1.queryForList(equipmentLocationQuery, String.class);
	//		logger.info("INSIDE PrDaoIMPL END METHOD getLocationFromProduct ::");
	//		return list;
	//	}


	@Override
	public void prUpdateProduct(PrData prData) {
		logger.info("INSIDE EquipmentDaoIMPL START METHOD prUpdateProduct");

		String getSupNumberBySupNameQuery = dBQueryPropertyFile.getQueryForKey("getSupNumberBySupNameQuery");
		String getSupplierNumber = jdbcTemplate1.queryForObject(getSupNumberBySupNameQuery, new Object[]{prData.getPrSupplierName()}, String.class);
		if(getSupplierNumber != null){
			prData.setPrSupplierNumber(getSupplierNumber);
		}
		String updateProductQuery =  dBQueryPropertyFile.getQueryForKey("updateProductQuery");
		jdbcTemplate1.update(updateProductQuery,
				prData.getPrDescription(),
				prData.getPrIdentificationNo(),
				prData.getPrSerialNo(),
				prData.getPrPRODRange(),
				prData.getPrLeastCount(),
				prData.getPrCategories(),
				prData.getPrCalibrationAgency(),
				prData.getPrEquipmentLocation(),
				prData.getPrBorrower(),
				prData.getPrMake(),
				prData.getPrModel(),
				prData.getPrCalibrationStd(),
				prData.getPrsupplierMeasuredResult(),
				prData.getPrInstrumentLineItem(),
				prData.getPrInstrumentStatus(),
				prData.getPrEquipmentStatus(),
				prData.getPrSupplierName(),
				prData.getPrSupplierNumber(),
				prData.getPrProdId());
		logger.info("INSIDE EquipmentDaoIMPL END METHOD prUpdateProduct");

	}


	@Override
	public int prAddProduct(PrData prData) {
		logger.info("INSIDE PrDaoIMPL START METHOD prAddProduct");

		String equipmentNumber = prData.getPrEquipmentType();
		String getMaxIdFromProductQuery = dBQueryPropertyFile.getQueryForKey("getMaxIdFromProductQuery");
		String getMaxFromProd =  jdbcTemplate1.queryForObject(getMaxIdFromProductQuery,String.class);
		if(getMaxFromProd  == null){
			getMaxFromProd = "0";
		}
		// getting the maximum id from the database....
		int maxId = Integer.parseInt(getMaxFromProd);
		int equipmentId = maxId + 1;

		// constructing the identification number....
		prData.setPrIdentificationNo(equipmentNumber+equipmentId);

		String getSupNumberBySupNameQuery = dBQueryPropertyFile.getQueryForKey("getSupNumberBySupNameQuery");
		String getSupplierNumber = jdbcTemplate1.queryForObject(getSupNumberBySupNameQuery, new Object[]{prData.getPrSupplierName()}, String.class);

		if(getSupplierNumber != null){
			prData.setPrSupplierNumber(getSupplierNumber);
		}
		String chackCountForSerialNumber = dBQueryPropertyFile.getQueryForKey("chackCountForSerialNumber");
		String addProductQuery =  dBQueryPropertyFile.getQueryForKey("addProductQuery");
		int serialNumIfExistCount = jdbcTemplate1.queryForObject(chackCountForSerialNumber, new Object[]{prData.getPrSerialNo()}, Integer.class);

		String checkCountForIdentityNumber = dBQueryPropertyFile.getQueryForKey("checkCountForIdentityNumber");
		int IdentityNumIfExistCount = jdbcTemplate1.queryForObject(checkCountForIdentityNumber, new Object[]{prData.getPrIdentificationNo()}, Integer.class);
		if(serialNumIfExistCount > 0){
			return  -1;
		}else if(IdentityNumIfExistCount > 0){
			return -2;
		}else{
			int num =  jdbcTemplate1.update(addProductQuery,
					prData.getPrDescription() ,
					prData.getPrIdentificationNo(),
					prData.getPrSerialNo(),
					prData.getPrPRODRange(),
					prData.getPrLeastCount(),
					prData.getPrCategories(),
					prData.getPrCalibrationAgency(),
					prData.getPrEquipmentLocation(),
					prData.getPrBorrower(),
					prData.getPrMake(),
					prData.getPrModel(),
					prData.getPrCalibrationStd(),
					prData.getPrsupplierMeasuredResult(),
					prData.getPrInstrumentLineItem(),
					prData.getPrInstrumentStatus(),
					prData.getPrEquipmentType(),
					prData.getPrEquipmentStatus(),
					prData.getPrSupplierName(),
					prData.getPrSupplierNumber());
			logger.info("INSIDE PrDaoIMPL END METHOD prAddProduct");
			return  num;
		}
	}


	@Override
	public PrData prFindProductByProdId(Integer prodId) {
		logger.info("INSIDE PrDaoIMPL START METHOD prFindProductByProdId");
		String FindEquipmentByIdQuery =  dBQueryPropertyFile.getQueryForKey("FindEquipmentByIdQuery");
		logger.info("INSIDE PrDaoIMPL END METHOD prFindProductByProdId");
		return jdbcTemplate1.queryForObject(FindEquipmentByIdQuery,  new Object[] { prodId }, new PrDataMapper());
	}


	@Override
	public List<PRPData> pRpSearchByCondition(String pRpId, String supplierName, String approver1Status,
			String approver2Status) {
		// TODO Auto-generated method stub
		return null;
	}

	//	@Override
	//	public List<String> getCategoryFromProduct() {
	//		logger.info("INSIDE PrDaoIMPL START METHOD getCategoryFromProduct ::");
	//		String categoriesQuery = dBQueryPropertyFile.getQueryForKey("categoriesQuery");
	//		List<String> list =  jdbcTemplate1.queryForList(categoriesQuery, String.class);
	//		logger.info("INSIDE PrDaoIMPL END METHOD getCategoryFromProduct ::");
	//		return list;
	//	}
	//
	//
	//	@Override
	//	public List<String> getCalibAgencyFromProduct() {
	//		logger.info("INSIDE PrDaoIMPL START METHOD getCalibAgencyFromProduct ::");
	//		String calibrationAgencyQuery = dBQueryPropertyFile.getQueryForKey("calibrationAgencyQuery");
	//		List<String> list =  jdbcTemplate1.queryForList(calibrationAgencyQuery, String.class);
	//		logger.info("INSIDE PrDaoIMPL END METHOD getCalibAgencyFromProduct ::");
	//		return list;
	//	}


	//	@Override
	//	public List<String> getCalibStandardFromProduct() {
	//		logger.info("INSIDE PrDaoIMPL START METHOD getCalibStandardFromProduct ::");
	//		String calibrationStanduredQuery = dBQueryPropertyFile.getQueryForKey("calibrationStanduredQuery");
	//		List<String> list =  jdbcTemplate1.queryForList(calibrationStanduredQuery, String.class);
	//		logger.info("INSIDE PrDaoIMPL END METHOD getCalibStandardFromProduct ::");
	//		return list;
	//	}
	//
	//
	//	@Override
	//	public List<String> getInstruStatusFromProduct() {
	//		logger.info("INSIDE PrDaoIMPL START METHOD getInstruStatusFromProduct ::");
	//		String instrumentStatus = dBQueryPropertyFile.getQueryForKey("instrumentStatus");
	//		List<String> list =  jdbcTemplate1.queryForList(instrumentStatus, String.class);
	//		logger.info("INSIDE PrDaoIMPL END METHOD getInstruStatusFromProduct ::");
	//		return list;
	//	}

	//	@Override
	//	public List<String> getMakeFromProduct() {
	//		logger.info("INSIDE PrDaoIMPL START METHOD getMakeFromProduct ::");
	//		String makeQuery = dBQueryPropertyFile.getQueryForKey("makeQuery");
	//		List<String> list =  jdbcTemplate1.queryForList(makeQuery, String.class);
	//		logger.info("INSIDE PrDaoIMPL END METHOD getMakeFromProduct ::");
	//		return list;
	//	}
	//
	//
	//	@Override
	//	public List<String> getLocationFromProduct() {
	//		logger.info("INSIDE PrDaoIMPL START METHOD getLocationFromProduct ::");
	//		String equipmentLocationQuery = dBQueryPropertyFile.getQueryForKey("equipmentLocationQuery");
	//		List<String> list =  jdbcTemplate1.queryForList(equipmentLocationQuery, String.class);
	//		logger.info("INSIDE PrDaoIMPL END METHOD getLocationFromProduct ::");
	//		return list;
	//	}


}
