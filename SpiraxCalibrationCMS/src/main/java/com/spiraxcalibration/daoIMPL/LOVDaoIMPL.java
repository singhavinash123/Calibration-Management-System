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
import com.spiraxcalibration.dao.LOVDao;
import com.spiraxcalibration.models.LOVData;

@Repository
public class LOVDaoIMPL implements LOVDao{

	Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	AppsPropertyFile dBQueryPropertyFile = new AppsPropertyFile();

	@Autowired
	JdbcTemplate  jdbcTemplate1;

	@Override
	public List<LOVData> getLovDetails() {
		logger.info("INSIDE LOVDaoIMPL START METHOD getLovDetails ::");
		String selectLovsQuery = dBQueryPropertyFile.getQueryForKey("selectLovsQuery");
		List<LOVData> lovList = jdbcTemplate1.query(selectLovsQuery, new LOVDataMapper());
		logger.info("INSIDE LOVDaoIMPL END METHOD getLovDetails ::");
		return lovList;
	}
	private static final class LOVDataMapper implements RowMapper<LOVData>{

		@Override
		public LOVData mapRow(ResultSet rs, int rowNum) throws SQLException {
			LOVData lOVData = new LOVData();

			lOVData.setDescription(rs.getString("LOVDESCRIPTION"));
			lOVData.setlId(rs.getInt("Lid"));
			lOVData.setLovComment(rs.getString("LOVCOMMENT"));
			lOVData.setLovName(rs.getString("LOVNAME"));
			lOVData.setLovProcess(rs.getString("LOVProcess"));
			lOVData.setLovValue(rs.getString("LOVVALUE"));
			lOVData.setLovLovId(rs.getInt("LOVID"));
			return lOVData;
		}
	}
	@Override
	public LOVData findLOVByLid(Integer lovId) {
		logger.info("INSIDE LOVDaoIMPL START METHOD prFindLOVByLid");
		String FindLOVByLoviDQuery =  dBQueryPropertyFile.getQueryForKey("FindLOVByLoviDQuery");
		logger.info("INSIDE LOVDaoIMPL END METHOD prFindLOVByLid");
		return jdbcTemplate1.queryForObject(FindLOVByLoviDQuery,  new Object[] { lovId }, new LOVDataMapper());
	}
	@Override
	public int addLov(LOVData lOVData) {
		logger.info("INSIDE LOVDaoIMPL START METHOD addLov");
		String selectCountForLOVValueAndNameQuery = dBQueryPropertyFile.getQueryForKey("selectCountForLOVValueAndNameQuery");
		
		if(lOVData.getLovName().equalsIgnoreCase("UserRole")){
			String insertIntoUserRoleQuery = dBQueryPropertyFile.getQueryForKey("insertIntoUserRoleQuery");
			//insertIntoUserRoleQuery=insert into user_roles(user_role) values(?);
			return jdbcTemplate1.update(insertIntoUserRoleQuery);
		}
		int count = jdbcTemplate1.queryForObject(selectCountForLOVValueAndNameQuery,
				new Object[]{lOVData.getLovProcess(),
						lOVData.getLovName(),
						lOVData.getLovValue()}, Integer.class);

		logger.info("INSIDE LOVDaoIMPL START METHOD updateLov ::");
		if(count > 0){
			return -1;
		}else{
			String insertIntoLovQuery = dBQueryPropertyFile.getQueryForKey("insertIntoLovQuery");
			logger.info("INSIDE LOVDaoIMPL END METHOD addLov");
			return jdbcTemplate1.update(insertIntoLovQuery,
					lOVData.getLovProcess(),
					lOVData.getLovName(),
					lOVData.getLovValue(),
					lOVData.getDescription(),
					lOVData.getLovComment(),
					lOVData.getLovProcess()
					);
		}

	}

	@Override
	public int updateLov(LOVData lOVData) {
		logger.info("INSIDE LOVDaoIMPL START METHOD updateLov ::");
		String updateLovQuery =  dBQueryPropertyFile.getQueryForKey("updateLovQuery");
		int num = jdbcTemplate1.update(updateLovQuery,
				lOVData.getLovProcess(),
				lOVData.getLovProcess(),
				lOVData.getLovName(),
				lOVData.getLovValue(),
				lOVData.getDescription(),
				lOVData.getLovComment(),
				lOVData.getlId());

		logger.info("INSIDE LOVDaoIMPL END METHOD updateLov ::");
		return num;
	}

	@Override
	public int deleteLov(Integer lovId) {
		logger.info("INSIDE LOVDaoIMPL START METHOD deleteLov ::");
		String deleteLOVByLovIdQuery = dBQueryPropertyFile.getQueryForKey("deleteLOVByLovIdQuery");
		logger.info("INSIDE LOVDaoIMPL START METHOD deleteLov ::");
		return jdbcTemplate1.update(deleteLOVByLovIdQuery , lovId);
	}
	@Override
	public List<String> getEquipmentType() {
		logger.info("INSIDE LOVDaoIMPL START METHOD getEquipmentType ::");
		String getEquipmentTypeQuery = dBQueryPropertyFile.getQueryForKey("getEquipmentTypeQuery");
		List<String> getEquipmentTypeList =  jdbcTemplate1.queryForList(getEquipmentTypeQuery, String.class);
		logger.info("INSIDE LOVDaoIMPL START METHOD getEquipmentType ::");
		return getEquipmentTypeList;	
	}
	@Override
	public List<LOVData> getProcessNames() {
		logger.info("INSIDE LOVDaoIMPL START METHOD getProcessNames ::");
		String getProcessNameQuery = dBQueryPropertyFile.getQueryForKey("getProcessNameQuery");
		List<LOVData> getProcessNameList =  jdbcTemplate1.query(getProcessNameQuery,new LOVProcessNameMaper());
		logger.info("INSIDE LOVDaoIMPL START METHOD getProcessNames ::");
		return getProcessNameList;
	}
	@Override
	public List<LOVData> getLovProcessWithName() {
		logger.info("INSIDE LOVDaoIMPL START METHOD getLovProcessWithName ::");
		String selectLovProcessWithName = dBQueryPropertyFile.getQueryForKey("selectLovProcessWithName");
		List<LOVData> lovList = jdbcTemplate1.query(selectLovProcessWithName, new LOVProcessWithNameMapper());
		logger.info("INSIDE LOVDaoIMPL END METHOD getLovProcessWithName ::");
		return lovList;
	}

	private static final class LOVProcessWithNameMapper implements RowMapper<LOVData>{
		@Override
		public LOVData mapRow(ResultSet rs, int rowNum) throws SQLException {
			LOVData lOVData = new LOVData();
			lOVData.setLovProcess(rs.getString("LOVProcess"));
			lOVData.setLovName(rs.getString("LOVNAME"));
			return lOVData;
		}
	}

	private static final class LOVProcessNameMaper implements RowMapper<LOVData>{
		@Override
		public LOVData mapRow(ResultSet rs, int rowNum) throws SQLException {
			LOVData lOVData = new LOVData();
			lOVData.setlId(rs.getInt("Lid"));
			lOVData.setLovProcess(rs.getString("LOVProcess"));
			return lOVData;
		}
	}
	@Override
	public List<String> getLovNamesByLovProcess(int lovId) {
		logger.info("INSIDE LOVDaoIMPL START METHOD getLovNamesByLovProcess ::");
		String getProcessNameByLovIdQuery = dBQueryPropertyFile.getQueryForKey("getProcessNameByLovIdQuery");
		List<String> getProcessNameByLovIdList =  jdbcTemplate1.queryForList(getProcessNameByLovIdQuery , new Object[]{lovId} , String.class);
		logger.info("INSIDE LOVDaoIMPL START METHOD getLovNamesByLovProcess ::");
		return getProcessNameByLovIdList;
	}
	@Override
	public List<LOVData> lovSearchLovByCondition(String lovProcess, String lovName) {
		logger.info("INSIDE LOVDaoIMPL START METHOD lovSearchLovByCondition ::");
		String selectLovListtByConditionQuery = dBQueryPropertyFile.getQueryForKey("selectLovListtByConditionQuery");
		List<LOVData> searchedList = jdbcTemplate1.query(selectLovListtByConditionQuery, new Object[]{lovProcess+"%", lovName+"%" }, new LOVDataMapper());
		logger.info("INSIDE LOVDaoIMPL END METHOD lovSearchLovByCondition ::");
		return searchedList;
	}
	@Override
	public List<String> getCertificateOptionName() {
		logger.info("INSIDE LOVDaoIMPL START METHOD getCertificateOptionName ::");
		String getCertificateOptionsQuery = dBQueryPropertyFile.getQueryForKey("getCertificateOptionsQuery");
		List<String> getCertificateOptionList =  jdbcTemplate1.queryForList(getCertificateOptionsQuery, String.class);
		logger.info("INSIDE LOVDaoIMPL START METHOD getCertificateOptionName ::");
		return getCertificateOptionList;	
	}
	@Override
	public List<String> getUserEmails() {
		logger.info("INSIDE LOVDaoIMPL START METHOD getUserEmails ::");
		String getUserEmailsQuery = dBQueryPropertyFile.getQueryForKey("getUserEmailsQuery");
		List<String> getUserEmailsList =  jdbcTemplate1.queryForList(getUserEmailsQuery, String.class);
		logger.info("INSIDE LOVDaoIMPL END METHOD getUserEmails ::");
		return getUserEmailsList;	
	}
	@Override
	public String getUserEmailsForApprover1() {
		logger.info("INSIDE LOVDaoIMPL START METHOD getUserEmailsForApprover1 ::");
		String getApprover1MailQuery = dBQueryPropertyFile.getQueryForKey("getApprover1MailQuery");
		String getUserEmailsList =  jdbcTemplate1.queryForObject(getApprover1MailQuery, String.class);
		logger.info("INSIDE LOVDaoIMPL END METHOD getUserEmailsForApprover1 ::");
		return getUserEmailsList;
	}
	@Override
	public String getUserEmailsForApprover2() {
		logger.info("INSIDE LOVDaoIMPL START METHOD getUserEmailsForApprover2 ::");
		String getApprover2MailQuery = dBQueryPropertyFile.getQueryForKey("getApprover2MailQuery");
		String getUserEmailsList =  jdbcTemplate1.queryForObject(getApprover2MailQuery, String.class);
		logger.info("INSIDE LOVDaoIMPL END METHOD getUserEmailsForApprover2 ::");
		return getUserEmailsList;
	}
	@Override
	public List<String> getCalibMakeList() {
		logger.info("INSIDE LOVDaoIMPL START METHOD getCalibMakeList ::");
		String getCalibMakeListQuery = dBQueryPropertyFile.getQueryForKey("getCalibMakeListQuery");
		List<String> getCalibMakeList =  jdbcTemplate1.queryForList(getCalibMakeListQuery, String.class);
		logger.info("INSIDE LOVDaoIMPL END METHOD getCalibMakeList ::");
		return getCalibMakeList;
	}
	@Override
	public List<String> getCalibModelList() {
		logger.info("INSIDE LOVDaoIMPL START METHOD getCalibModelList ::");
		String getCalibModelListQuery = dBQueryPropertyFile.getQueryForKey("getCalibModelListQuery");
		List<String> getCalibModelList =  jdbcTemplate1.queryForList(getCalibModelListQuery, String.class);
		logger.info("INSIDE LOVDaoIMPL END METHOD getCalibModelList ::");
		return getCalibModelList;
	}
	@Override
	public List<String> getCalibLocationList() {
		logger.info("INSIDE LOVDaoIMPL START METHOD getCalibLocationList ::");
		String getCalibLocationListQuery = dBQueryPropertyFile.getQueryForKey("getCalibLocationListQuery");
		List<String> getCalibLocationList =  jdbcTemplate1.queryForList(getCalibLocationListQuery, String.class);
		logger.info("INSIDE LOVDaoIMPL END METHOD getCalibLocationList ::");
		return getCalibLocationList;
	}
	@Override
	public List<String> getAcceptanceCriteriaList() {
		logger.info("INSIDE LOVDaoIMPL START METHOD getAcceptanceCriteriaList ::");
		String getAcceptanceCriteriaListQuery = dBQueryPropertyFile.getQueryForKey("getAcceptanceCriteriaListQuery");
		List<String> getAcceptanceCriteriaList =  jdbcTemplate1.queryForList(getAcceptanceCriteriaListQuery, String.class);
		logger.info("INSIDE LOVDaoIMPL END METHOD getAcceptanceCriteriaList ::");
		return getAcceptanceCriteriaList;
	}
	@Override
	public List<String> getCalibCategoryList() {
		logger.info("INSIDE LOVDaoIMPL START METHOD getCalibCategoryList ::");
		String getCalibCategoryListQuery = dBQueryPropertyFile.getQueryForKey("getCalibCategoryListQuery");
		List<String> getCalibAgencyList =  jdbcTemplate1.queryForList(getCalibCategoryListQuery, String.class);
		logger.info("INSIDE LOVDaoIMPL END METHOD getCalibCategoryList ::");
		return getCalibAgencyList;
	}
	@Override
	public List<String> getCalibAgencyList() {
		logger.info("INSIDE LOVDaoIMPL START METHOD getCalibAgencyList ::");
		String getCalibAgencyListQuery = dBQueryPropertyFile.getQueryForKey("getCalibAgencyListQuery");
		List<String> getCalibAgencyList =  jdbcTemplate1.queryForList(getCalibAgencyListQuery, String.class);
		logger.info("INSIDE LOVDaoIMPL END METHOD getCalibAgencyList ::");
		return getCalibAgencyList;
	}
	@Override
	public List<String> getCalibPartCodeList() {
		logger.info("INSIDE LOVDaoIMPL START METHOD getCalibPartCodeList ::");
		String getCalibPartCodeListQuery = dBQueryPropertyFile.getQueryForKey("getCalibPartCodeListQuery");
		List<String> getCalibPartCodeList =  jdbcTemplate1.queryForList(getCalibPartCodeListQuery, String.class);
		logger.info("INSIDE LOVDaoIMPL END METHOD getCalibPartCodeList ::");
		return getCalibPartCodeList;	
	}
	
	@Override
	public List<String> getSupplierServiceType() {
		logger.info("INSIDE LOVDaoIMPL START METHOD getSupplierServiceType ::");
		String getSupplierServiceTypeQuery = dBQueryPropertyFile.getQueryForKey("getSupplierServiceTypeQuery");
		List<String> getCalibPartCodeList =  jdbcTemplate1.queryForList(getSupplierServiceTypeQuery, String.class);
		logger.info("INSIDE LOVDaoIMPL END METHOD getSupplierServiceType ::");
		return getCalibPartCodeList;
	}
	@Override
	public List<String> getLovNameList() {
		logger.info("INSIDE LOVDaoIMPL START METHOD getLovNameList ::");
		String getLovNameListQuery = dBQueryPropertyFile.getQueryForKey("getLovNameListQuery");
		List<String> list =  jdbcTemplate1.queryForList(getLovNameListQuery, String.class);
		logger.info("INSIDE LOVDaoIMPL END METHOD getLovNameList ::");
		return list;
	}
}
