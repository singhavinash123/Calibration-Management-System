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
import com.spiraxcalibration.dao.LookupIDao;
import com.spiraxcalibration.models.CalibFrequencyData;
import com.spiraxcalibration.models.PrEquipmentDescription;
import com.spiraxcalibration.models.UserData;

@Repository
public class LookupDaoIMPL implements LookupIDao{

	Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	AppsPropertyFile dBQueryPropertyFile = new AppsPropertyFile();

	@Autowired
	JdbcTemplate  jdbcTemplate1;

	@Override
	public List<String> getMakeFromProduct() {
		logger.info("INSIDE LookupDaoIMPL START METHOD getMakeFromProduct ::");
		String makeQuery = dBQueryPropertyFile.getQueryForKey("makeQuery");
		List<String> list =  jdbcTemplate1.queryForList(makeQuery, String.class);
		logger.info("INSIDE LookupDaoIMPL END METHOD getMakeFromProduct ::");
		return list;
	}

	@Override
	public List<String> getLocationFromProduct() {
		logger.info("INSIDE LookupDaoIMPL START METHOD getLocationFromProduct ::");
		String equipmentLocationQuery = dBQueryPropertyFile.getQueryForKey("equipmentLocationQuery");
		List<String> list =  jdbcTemplate1.queryForList(equipmentLocationQuery, String.class);
		logger.info("INSIDE LookupDaoIMPL END METHOD getLocationFromProduct ::");
		return list;
	}

	@Override
	public List<String> getCalibStandardFromProduct() {
		logger.info("INSIDE LookupDaoIMPL START METHOD getCalibStandardFromProduct ::");
		String calibrationStanduredQuery = dBQueryPropertyFile.getQueryForKey("calibrationStanduredQuery");
		List<String> list =  jdbcTemplate1.queryForList(calibrationStanduredQuery, String.class);
		logger.info("INSIDE LookupDaoIMPL END METHOD getCalibStandardFromProduct ::");
		return list;
	}

	@Override
	public List<String> getInstruStatusFromProduct() {
		logger.info("INSIDE LookupDaoIMPL START METHOD getInstruStatusFromProduct ::");
		String instrumentStatus = dBQueryPropertyFile.getQueryForKey("instrumentStatus");
		List<String> list =  jdbcTemplate1.queryForList(instrumentStatus, String.class);
		logger.info("INSIDE LookupDaoIMPL END METHOD getInstruStatusFromProduct ::");
		return list;
	}

	@Override
	public List<String> getCategoryFromProduct() {
		logger.info("INSIDE LookupDaoIMPL START METHOD getCategoryFromProduct ::");
		String categoriesQuery = dBQueryPropertyFile.getQueryForKey("categoriesQuery");
		List<String> list =  jdbcTemplate1.queryForList(categoriesQuery, String.class);
		logger.info("INSIDE LookupDaoIMPL END METHOD getCategoryFromProduct ::");
		return list;
	}

	@Override
	public List<String> getCalibAgencyFromProduct() {
		logger.info("INSIDE LookupDaoIMPL START METHOD getCalibAgencyFromProduct ::");
		String calibrationAgencyQuery = dBQueryPropertyFile.getQueryForKey("calibrationAgencyQuery");
		List<String> list =  jdbcTemplate1.queryForList(calibrationAgencyQuery, String.class);
		logger.info("INSIDE LookupDaoIMPL END METHOD getCalibAgencyFromProduct ::");
		return list;
	}

	@Override
	public List<String> getCalibrationType() {
		logger.info("INSIDE LookupDaoIMPL START METHOD getCalibrationType ::");
		String calibrationtypeQuery = dBQueryPropertyFile.getQueryForKey("calibrationtypeQuery");
		List<String> list =  jdbcTemplate1.queryForList(calibrationtypeQuery, String.class);
		logger.info("INSIDE LookupDaoIMPL END METHOD getCalibrationType ::");
		return list;
	}

	@Override
	public List<CalibFrequencyData> getCalibrationFreqType() {
		logger.info("INSIDE LookupDaoIMPL START METHOD getCalibrationType ::");
		String calibFrequnecyDataQuery = dBQueryPropertyFile.getQueryForKey("calibFrequnecyDataQuery");
		List<CalibFrequencyData> calibFrequencyListData = jdbcTemplate1.query(calibFrequnecyDataQuery, new CalibFreqMapper());
		logger.info("INSIDE LookupDaoIMPL END METHOD getCalibrationType ::");
		return calibFrequencyListData;
	}

	@Override
	public List<String> getCalibstatus() {
		logger.info("INSIDE LookupDaoIMPL START METHOD getCalibstatus ::");
		String calibStatusQuery = dBQueryPropertyFile.getQueryForKey("calibStatusQuery");
		List<String> list =  jdbcTemplate1.queryForList(calibStatusQuery, String.class);
		logger.info("INSIDE LookupDaoIMPL END METHOD getCalibstatus ::");
		return list;
	}

	@Override
	public List<String> getEquipmentStatus() {
		logger.info("INSIDE LookupDaoIMPL START METHOD getEquipmentStatus ::");
		String equipmentStatusQuery = dBQueryPropertyFile.getQueryForKey("equipmentStatusQuery");
		List<String> list =  jdbcTemplate1.queryForList(equipmentStatusQuery, String.class);
		logger.info("INSIDE LookupDaoIMPL END METHOD getEquipmentStatus ::");
		return list;
	}

	@Override
	public List<String> getSupplierName() {	
		logger.info("INSIDE LookupDaoIMPL START METHOD getSupplierName ::");
		String supplierNameQuery = dBQueryPropertyFile.getQueryForKey("supplierNameQuery");
		List<String> list =  jdbcTemplate1.queryForList(supplierNameQuery, String.class);
		logger.info("INSIDE LookupDaoIMPL END METHOD getSupplierName ::");
		return list;
	}

	private static final class CalibFreqMapper implements RowMapper<CalibFrequencyData>{
		@Override
		public CalibFrequencyData mapRow(ResultSet rs , int rowNum) throws SQLException {
			CalibFrequencyData calibFrequencyData = new CalibFrequencyData();
			calibFrequencyData.setCalibType(rs.getString("calib_type"));
			calibFrequencyData.setCalibFrequency(rs.getInt("alert_frequency"));
			return calibFrequencyData; 
		}
	}

	@Override
	public List<PrEquipmentDescription> getEquipmentDescription() {
		logger.info("INSIDE LookupDaoIMPL START METHOD getEquipmentDescription ::");
		String equipmentDescriptionListQuery = dBQueryPropertyFile.getQueryForKey("equipmentDescriptionListQuery");
		List<PrEquipmentDescription> equipmentDescListData = jdbcTemplate1.query(equipmentDescriptionListQuery, new EquipmentDescMapper());
		logger.info("INSIDE LookupDaoIMPL END METHOD getEquipmentDescription ::");
		return equipmentDescListData;
	}

	private static final class EquipmentDescMapper implements RowMapper<PrEquipmentDescription>{
		@Override
		public PrEquipmentDescription mapRow(ResultSet rs , int rowNum) throws SQLException {
			PrEquipmentDescription prEquipmentDescription = new PrEquipmentDescription();
			prEquipmentDescription.setPrEquipmentDescription(rs.getString("LOVDESCRIPTION"));
			prEquipmentDescription.setPrEquipmentIdentityNum(rs.getString("LOVVALUE"));
			return prEquipmentDescription; 
		}
	}

	@Override
	public List<String> getApproverMailIds() {
		logger.info("INSIDE LookupDaoIMPL START METHOD getApproverMailIds ::");
		String ApproverMailIdQuery = dBQueryPropertyFile.getQueryForKey("ApproverMailIdQuery");
		List<String> list =  jdbcTemplate1.queryForList(ApproverMailIdQuery, String.class);
		logger.info("INSIDE LookupDaoIMPL END METHOD getApproverMailIds ::");
		return list;
	}

	@Override
	public List<PrEquipmentDescription> getCalibFrequencyLookUp() {
		logger.info("INSIDE LookupDaoIMPL START METHOD getCalibFrequencyLookUp ::");
		String calibFrequencyQuency = dBQueryPropertyFile.getQueryForKey("calibFrequencyQuency");
		List<PrEquipmentDescription> list =  jdbcTemplate1.query(calibFrequencyQuency, new EquipmentDescMapper());
		logger.info("INSIDE LookupDaoIMPL END METHOD getCalibFrequencyLookUp ::");
		return list;
	}

	@Override
	public List<UserData> getCalibUserData() {
		logger.info("INSIDE LookupDaoIMPL START METHOD getCalibUserData ::");
		String getUserNameQuery = dBQueryPropertyFile.getQueryForKey("getUserNameQuery");
		List<UserData> list =  jdbcTemplate1.query(getUserNameQuery, new userNameWithEmailIdMapper());
		logger.info("INSIDE LookupDaoIMPL END METHOD getCalibUserData ::");
		return list;
	}

	private static final class userNameWithEmailIdMapper implements RowMapper<UserData>{
		@Override
		public UserData mapRow(ResultSet rs , int rowNum) throws SQLException {
			UserData userData = new UserData();
			userData.setUserFirstName(rs.getString("First_Name"));
			userData.setUserLastName(rs.getString("Last_Name"));
			userData.setUseUserName(rs.getString("User_Name"));
			return userData; 
		}
	}

	@Override
	public List<String> getSupplierServiceType() {
		logger.info("INSIDE LookupDaoIMPL START METHOD getSupplierServiceType ::");
		String getSupplierServiceTypeQuery = dBQueryPropertyFile.getQueryForKey("getSupplierServiceTypeQuery");
		List<String> list =  jdbcTemplate1.queryForList(getSupplierServiceTypeQuery, String.class);
		logger.info("INSIDE LookupDaoIMPL END METHOD getSupplierServiceType ::");
		return list;
	}

	@Override
	public List<String> getCalibAgencyLookUp() {
		logger.info("INSIDE LookupDaoIMPL START METHOD getCalibAgencyLookUp ::");
		String getgetCalibAgencyLookUpTypeQuery = dBQueryPropertyFile.getQueryForKey("getgetCalibAgencyLookUpTypeQuery");
		List<String> list =  jdbcTemplate1.queryForList(getgetCalibAgencyLookUpTypeQuery, String.class);
		logger.info("INSIDE LookupDaoIMPL END METHOD getCalibAgencyLookUp ::");
		return list;
	}

	@Override
	public List<String> getCalibCategoryLookUp() {
		logger.info("INSIDE LookupDaoIMPL START METHOD getCalibCategoryLookUp ::");
		String getCalibCategoryQuery = dBQueryPropertyFile.getQueryForKey("getCalibCategoryQuery");
		List<String> list =  jdbcTemplate1.queryForList(getCalibCategoryQuery, String.class);
		logger.info("INSIDE LookupDaoIMPL END METHOD getCalibCategoryLookUp ::");
		return list;
	}

	@Override
	public List<String> calibrationStandardLookUp() {
		logger.info("INSIDE LookupDaoIMPL START METHOD calibrationStandardLookUp ::");
		String getcalibrationStandardLookUpQuery = dBQueryPropertyFile.getQueryForKey("getcalibrationStandardLookUpQuery");
		List<String> list =  jdbcTemplate1.queryForList(getcalibrationStandardLookUpQuery, String.class);
		logger.info("INSIDE LookupDaoIMPL END METHOD calibrationStandardLookUp ::");
		return list;
	}

	@Override
	public List<String> calibrationcertValidByLookUp() {
		logger.info("INSIDE LookupDaoIMPL START METHOD calibrationcertValidByLookUp ::");
		String getcalibrationcertValidByLookUpQuery = dBQueryPropertyFile.getQueryForKey("getcalibrationcertValidByLookUpQuery");
		List<String> list =  jdbcTemplate1.queryForList(getcalibrationcertValidByLookUpQuery, String.class);
		logger.info("INSIDE LookupDaoIMPL END METHOD calibrationcertValidByLookUp ::");
		return list;
	}

	@Override
	public List<String> getCalibResultList() {
		logger.info("INSIDE LookupDaoIMPL START METHOD getCalibResultList ::");
		String getCalibResultListQuery = dBQueryPropertyFile.getQueryForKey("getCalibResultListQuery");
		List<String> list =  jdbcTemplate1.queryForList(getCalibResultListQuery, String.class);
		logger.info("INSIDE LookupDaoIMPL END METHOD getCalibResultList ::");
		return list;
	}

	@Override
	public List<String> getCalibSourceList() {
		logger.info("INSIDE LookupDaoIMPL START METHOD getCalibSourceList ::");
		String getCalibSourceListQuery = dBQueryPropertyFile.getQueryForKey("getCalibSourceListQuery");
		List<String> list =  jdbcTemplate1.queryForList(getCalibSourceListQuery, String.class);
		logger.info("INSIDE LookupDaoIMPL END METHOD getCalibSourceList ::");
		return list;
	}


}
