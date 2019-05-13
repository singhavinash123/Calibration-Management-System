package com.spiraxcalibration.daoIMPL;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.spiraxcalibration.WebConfig.AppsPropertyFile;
import com.spiraxcalibration.controllers.WhoColumnsController;
import com.spiraxcalibration.dao.UserAuthenticationIDao;
import com.spiraxcalibration.models.UserData;

@Repository
public class UserAuthenticationDaoIMPL implements UserAuthenticationIDao{

	Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	AppsPropertyFile dBQueryPropertyFile = new AppsPropertyFile();

	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

	@Autowired
	JdbcTemplate  jdbcTemplate1;
	
	@Autowired
	WhoColumnsController whoColumnsController;
	
	@Override
	public String getUserPasswordByUserName(String userName) {
		String getPasswordByUserNameQuery = dBQueryPropertyFile.getQueryForKey("getPasswordByUserNameQuery");
		String password =  jdbcTemplate1.queryForObject(getPasswordByUserNameQuery, new Object[]{userName}, String.class);
		return password;
	}

	@Override
	public UserData findUserByEmail(String userName) {
		logger.info("INSIDE UserDaoIMPL START METHOD findUserByEmail ::");
		String getUserByUserNameQuery = dBQueryPropertyFile.getQueryForKey("getUserByUserNameQuery");
		UserData userData = null;
		try{
			userData = jdbcTemplate1.queryForObject(getUserByUserNameQuery,  new Object[]{userName} ,new UserDataMapper());
		}catch(Exception e){
			return userData;
		}
		logger.info("INSIDE UserDaoIMPL END METHOD findUserByEmail ::");
		return userData;
	}

	private static final class UserDataMapper implements RowMapper<UserData>{

		@Override
		public UserData mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserData userData = new UserData();
			userData.setUserUserId(rs.getInt("User_ID"));
			userData.setUserFirstName(rs.getString("First_Name"));
			userData.setUserLastName(rs.getString("Last_Name"));
			userData.setUserUserGrants(rs.getString("User_Grants"));
			userData.setUseUserName(rs.getString("User_Name"));
			userData.setUserUserId(rs.getInt("User_ID"));
			userData.setUserAccessLevel(rs.getString("Access_Level"));
			userData.setUserContactNumber(rs.getString("Contact_No"));
			userData.setUserCreatedBy(rs.getString("created_by"));
			userData.setUserCreationDate(rs.getString("creation_date"));
			userData.setUserDesignation(rs.getString("Designation"));
			userData.setUserEmailAddress(rs.getString("Email_Address"));
			userData.setUserFirstName(rs.getString("First_Name"));
			userData.setUserLastName(rs.getString("Last_Name"));
			userData.setUserLastUpdateDate(rs.getString("last_update_date"));
			userData.setUserPassWord(rs.getString("Password"));
			userData.setUserRelatedAccountId(rs.getInt("Related_Account_ID"));
			userData.setUserRelatedId(rs.getInt("Related_ID"));
			userData.setUserUpdatedBy(rs.getString("last_updated_by"));
			userData.setUserUpdateLogin(rs.getString("last_update_login"));
			userData.setUserEmpCode(rs.getString("Emp_Code"));
			userData.setUserDepartment(rs.getString("Emp_Dept"));
			return userData;
		}
	}
	@Override
	public int updateResetToken(UserData userData) {
		logger.info("INSIDE UserDaoIMPL START METHOD updateResetToken ::");
		String updateTheUserDataByResetTokenQuery = dBQueryPropertyFile.getQueryForKey("updateTheUserDataByResetTokenQuery");
		int num =  jdbcTemplate1.update(updateTheUserDataByResetTokenQuery,
				userData.getSetResetToken(),
				userData.getUseUserName());
		logger.info("INSIDE UserDaoIMPL END METHOD updateResetToken ::");
		return num;
	}

	@Override
	public UserData findUserByResetToken(String token) {
		logger.info("INSIDE UserDaoIMPL START METHOD findUserByResetToken ::");
		String getUserByResetTokenQuery = dBQueryPropertyFile.getQueryForKey("getUserByResetTokenQuery");
		UserData userData = null;
		try{
			 userData = jdbcTemplate1.queryForObject(getUserByResetTokenQuery,  new Object[]{token} ,new UserDataMapper());
		}catch(Exception e){
			return null;
		}
		logger.info("INSIDE UserDaoIMPL END METHOD findUserByResetToken ::");
		return userData;
	}

	@Override
	public int updatePasswordUser(UserData userData) {
		logger.info("INSIDE UserDaoIMPL START METHOD updatePasswordUser ::");
		String confirmPassword = bCryptPasswordEncoder.encode(userData.getUserConfirmPassword());
		String resetPasswordQuery = dBQueryPropertyFile.getQueryForKey("resetPasswordQuery");
		int num =  jdbcTemplate1.update(resetPasswordQuery,confirmPassword,userData.getSetResetToken(),userData.getUseUserName());
		logger.info("INSIDE UserDaoIMPL END METHOD updatePasswordUser ::");
		return num;
	}

}
