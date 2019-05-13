package com.spiraxcalibration.daoIMPL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.spiraxcalibration.WebConfig.AppsPropertyFile;
import com.spiraxcalibration.controllers.WhoColumnsController;
import com.spiraxcalibration.dao.SupIDao;
import com.spiraxcalibration.models.SUPData;

@Repository
public class SupDaoIMPL implements SupIDao{

	Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	AppsPropertyFile dBQueryPropertyFile = new AppsPropertyFile();

	@Autowired
	JdbcTemplate  jdbcTemplate1;
	
	@Autowired
	WhoColumnsController  whoColumnsController;

	@Override
	public List<SUPData> supGetSuppliersDetails() {
		logger.info("INSIDE SupDaoIMPL START METHOD supGetSuppliersDetails");
		String selectSupplierDetailsQuery =  dBQueryPropertyFile.getQueryForKey("selectSupplierDetailsQuery");
		List<SUPData> supDataList = jdbcTemplate1.query(selectSupplierDetailsQuery, new supDataMapper());
		logger.info("INSIDE SupDaoIMPL END METHOD supGetSuppliersDetails");
		return supDataList;
	}

	private static final class supDataMapper implements RowMapper<SUPData>{
		@Override
		public SUPData mapRow(ResultSet rs , int rowNum) throws SQLException {
			SUPData supData = new SUPData();
			supData.setSupSupId(rs.getInt("Sup_Id"));
			supData.setSupContact(rs.getString("contact"));
			supData.setSupEmailId(rs.getString("Email_Id"));
			supData.setSupRegistrationNum(rs.getString("Registration_Num"));
			supData.setSupStatus(rs.getString("Sup_Status"));
			supData.setSupSupplierName(rs.getString("Supplier_Name"));
			supData.setSupSupplierNumber(rs.getString("supplier_number"));
			supData.setSupAddressLine1(rs.getString("Address_Line1"));
			supData.setSupAddressLine2(rs.getString("Address_Line2"));
			supData.setSupAddressLine3(rs.getString("Address_Line3"));
			supData.setSupAddressLine4(rs.getString("Address_Line4"));
			supData.setSupAddressLine5(rs.getString("Address_Line5"));
			supData.setSupAddressLine6(rs.getString("Address_Line6"));
			supData.setSupSupplierServiceType(rs.getString("Supplier_Service_Type"));
			return supData;
		}
	}

	@Override
	public int supAddSupplier(SUPData supData) {
		logger.info("INSIDE SupDaoIMPL START METHOD supAddSupplier");

		String checkSupplierNameExistQuery = dBQueryPropertyFile.getQueryForKey("checkSupplierNameExistQuery");
		int getCountIfSupplierExist = jdbcTemplate1.queryForObject(checkSupplierNameExistQuery, new Object[]{supData.getSupSupplierName()}, Integer.class);
		if(getCountIfSupplierExist > 0){
			return -1;
		}
		String getCountSupplierNumberExistQuery = dBQueryPropertyFile.getQueryForKey("getCountSupplierNumberExistQuery");
		int getCountIfSupplierNumberExist = jdbcTemplate1.queryForObject(getCountSupplierNumberExistQuery, new Object[]{supData.getSupSupplierNumber()}, Integer.class);
		if(getCountIfSupplierNumberExist > 0){
			return -2;
		}
//		String getCountRegistrationNumExistQuery = dBQueryPropertyFile.getQueryForKey("getCountRegistrationNumExistQuery");
//		int getCountIfRegistrationNumberExist = jdbcTemplate1.queryForObject(getCountRegistrationNumExistQuery, new Object[]{supData.getSupRegistrationNum()}, Integer.class);
//		if(getCountIfRegistrationNumberExist > 0){
//			return -3;
//		}
		Map<String,String> whoColumnMap = whoColumnsController.getWhoColumnsInfo();
		if(whoColumnMap != null){
			supData.setSupCreatedby((String) whoColumnMap.get("userId"));
			supData.setSupCreatedDate((String) whoColumnMap.get("currentDate"));
		}
		String addSupplierQuery =  dBQueryPropertyFile.getQueryForKey("addSupplierQuery");
		logger.info("INSIDE SupDaoIMPL END METHOD supAddSupplier");
		return  jdbcTemplate1.update(addSupplierQuery,
				supData.getSupSupplierName(),
				supData.getSupRegistrationNum(),
				supData.getSupEmailId(),
				supData.getSupSupplierNumber(),
				supData.getSupContact(),
				supData.getSupStatus(),
				supData.getSupAddressLine1(),
				supData.getSupAddressLine2(),
				supData.getSupAddressLine3(),
				supData.getSupAddressLine4(),
				supData.getSupAddressLine5(),
				supData.getSupAddressLine6(),
				supData.getSupSupplierServiceType(),
				supData.getSupCreatedby(),
				supData.getSupCreatedDate()
				);
	}

	@Override
	public int supUpdateSupplier(SUPData supData) {
		logger.info("INSIDE SupDaoIMPL START METHOD supUpdateSupplier");
		
		Map<String,String> whoColumnMap = whoColumnsController.getWhoColumnsInfo();
		if(whoColumnMap != null){
			supData.setSupUpdatedBy((String) whoColumnMap.get("userId"));
			supData.setSupUpdatedDate((String) whoColumnMap.get("currentDate"));
		}
//		String checkSupplierNameExistQuery = dBQueryPropertyFile.getQueryForKey("checkSupplierNameExistQuery");
//		int getCountIfSupplierExist = jdbcTemplate1.queryForObject(checkSupplierNameExistQuery, new Object[]{supData.getSupSupplierName()}, Integer.class);
//		if(getCountIfSupplierExist > 0){
//			return -1;
//		}
//		String getCountSupplierNumberExistQuery = dBQueryPropertyFile.getQueryForKey("getCountSupplierNumberExistQuery");
//		int getCountIfSupplierNumberExist = jdbcTemplate1.queryForObject(getCountSupplierNumberExistQuery, new Object[]{supData.getSupSupplierNumber()}, Integer.class);
//		if(getCountIfSupplierNumberExist > 0){
//			return -2;
//		}
//		String getCountRegistrationNumExistQuery = dBQueryPropertyFile.getQueryForKey("getCountRegistrationNumExistQuery");
//		int getCountIfRegistrationNumberExist = jdbcTemplate1.queryForObject(getCountRegistrationNumExistQuery, new Object[]{supData.getSupRegistrationNum()}, Integer.class);
//		if(getCountIfRegistrationNumberExist > 0){
//			return -3;
//		}
		String updateSupplierQuery =  dBQueryPropertyFile.getQueryForKey("updateSupplierQuery");
		int num = jdbcTemplate1.update(updateSupplierQuery,
				supData.getSupSupplierName(),
				supData.getSupRegistrationNum(),
				supData.getSupEmailId(),
				supData.getSupSupplierNumber(),
				supData.getSupContact(),
				supData.getSupStatus(),
				supData.getSupAddressLine1(),
				supData.getSupAddressLine2(),
				supData.getSupAddressLine3(),
				supData.getSupAddressLine4(),
				supData.getSupAddressLine5(),
				supData.getSupAddressLine6(),
				supData.getSupSupplierServiceType(),
				supData.getSupCreatedby(),
				supData.getSupCreatedDate(),
				supData.getSupSupId());
		logger.info("INSIDE SupDaoIMPL END METHOD supUpdateSupplier");
		return num;
	}

	@Override
	public SUPData supFindSupplierBySupId(Integer sup_Id) {
		logger.info("INSIDE SupDaoIMPL START METHOD supFindSupplierBySupId");
		String FindSupplierByIdQuery =  dBQueryPropertyFile.getQueryForKey("FindSupplierByIdQuery");
		logger.info("INSIDE SupDaoIMPL END METHOD supFindSupplierBySupId");
		return jdbcTemplate1.queryForObject(FindSupplierByIdQuery,  new Object[] { sup_Id }, new supDataMapper());

	}

	@Override
	public List<SUPData> supSearchByCondition(String supplierName, String supplierNumber, String supplierStatus) {
		logger.info("INSIDE SupDaoIMPL START METHOD supSearchByCondition ::");
		String selectSupplierListByConditionQuery = dBQueryPropertyFile.getQueryForKey("selectSupplierListByConditionQuery");
		List<SUPData> searchedList = jdbcTemplate1.query(selectSupplierListByConditionQuery, new Object[]{supplierName+"%", supplierNumber+"%", supplierStatus+"%"} , new supDataMapper());
		logger.info("INSIDE SupDaoIMPL END METHOD supSearchByCondition ::");
		return searchedList;
	}

	@Override
	public SUPData supShowSupplierDetailBySupplierNum(String supplier_Num) {
		logger.info("INSIDE SupDaoIMPL START METHOD supShowSupplierDetailBySupplierNum");
		String FindSupplierBySupIdQuery =  dBQueryPropertyFile.getQueryForKey("FindSupplierBySupIdQuery");
		logger.info("INSIDE SupDaoIMPL END METHOD supShowSupplierDetailBySupplierNum");
		return jdbcTemplate1.queryForObject(FindSupplierBySupIdQuery,  new Object[] { supplier_Num }, new supDataMapper());
	}

	@Override
	public List<SUPData> supGetSupplierNameWithId() {
		logger.info("INSIDE SupDaoIMPL START METHOD supGetSupplierNameWithId");
		String selectSupplierDetailsWithIdQuery =  dBQueryPropertyFile.getQueryForKey("selectSupplierDetailsWithIdQuery");
		List<SUPData> supDataList = jdbcTemplate1.query(selectSupplierDetailsWithIdQuery, new supNameWithIdDataMapper());
		logger.info("INSIDE SupDaoIMPL END METHOD supGetSupplierNameWithId");
		return supDataList;
	}

	private static final class supNameWithIdDataMapper implements RowMapper<SUPData>{
		@Override
		public SUPData mapRow(ResultSet rs , int rowNum) throws SQLException {
			SUPData supData = new SUPData();
			supData.setSupSupplierName(rs.getString("Supplier_Name"));
			supData.setSupSupId(rs.getInt("EC_Supplier_Id"));
			return supData;
		}
	}
}
