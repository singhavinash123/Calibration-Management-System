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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.spiraxcalibration.WebConfig.AppsPropertyFile;
import com.spiraxcalibration.controllers.WhoColumnsController;
import com.spiraxcalibration.dao.UserIDao;
import com.spiraxcalibration.models.UserData;

@Repository
public class UserDaoIMPL implements UserIDao{

	Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	AppsPropertyFile dBQueryPropertyFile = new AppsPropertyFile();

	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

	@Autowired
	JdbcTemplate  jdbcTemplate1;

	@Autowired
	WhoColumnsController whoColumnsController;

	@Override
	public List<UserData> userGetUsersDetails() {
		logger.info("INSIDE UserDaoIMPL START METHOD userGetUsersDetails ::");
		String selectUsersQuery = dBQueryPropertyFile.getQueryForKey("selectUsersQuery");
		List<UserData> userDataList = jdbcTemplate1.query(selectUsersQuery, new UserDataMapper());
		logger.info("INSIDE UserDaoIMPL END METHOD userGetUsersDetails ::");
		return userDataList;
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
			userData.setUserLastUpdateDate(rs.getString("last_update_date"));
			userData.setUserPassWord(rs.getString("Password"));
			userData.setUserRelatedAccountId(rs.getInt("Related_Account_ID"));
			userData.setUserRelatedId(rs.getInt("Related_ID"));
			userData.setUserUpdatedBy(rs.getString("last_updated_by"));
			userData.setUserUpdateLogin(rs.getString("last_update_login"));
			userData.setUserEmpCode(rs.getString("Emp_Code"));
			userData.setUserDepartment(rs.getString("Emp_Dept"));
			userData.setUserUserRoleName(rs.getString("user_role"));

			userData.setUserUserRoleId(rs.getInt("user_roles_user_role_id"));

			return userData;
		}
	}

	@Override
	public List<String> userGetUserRoles() {
		logger.info("INSIDE UserDaoIMPL START METHOD userGetUserRoles ::");
		String userRoleList = dBQueryPropertyFile.getQueryForKey("userRoleList");
		List<String> getUserRoleList =  jdbcTemplate1.queryForList(userRoleList, String.class);
		logger.info("INSIDE UserDaoIMPL START METHOD userGetUserRoles ::");
		return getUserRoleList;	
	}

	@Override
	public int userAddUser(UserData userData) {
		logger.info("INSIDE UserDaoIMPL START METHOD userAddProduct");
		String data = dBQueryPropertyFile.getUserDetail("approver1");
		if(userData.getUserUserRoleName().equalsIgnoreCase("PR_APPROVER1") || userData.getUserUserRoleName().equalsIgnoreCase("PR_APPROVER2")){
			String checkApproverAlreadyExistQuery = dBQueryPropertyFile.getQueryForKey("checkApproverAlreadyExistQuery");
			int getCountIfApproverExist = jdbcTemplate1.queryForObject(checkApproverAlreadyExistQuery, new Object[]{userData.getUserUserRoleName()}, Integer.class);
			if(getCountIfApproverExist > 0){
				return -4;
			}
		}
		String checkEmailAlreadyExistQuery = dBQueryPropertyFile.getQueryForKey("checkEmailAlreadyExistQuery");
		int getCountIfEmailExist = jdbcTemplate1.queryForObject(checkEmailAlreadyExistQuery, new Object[]{userData.getUserEmailAddress()}, Integer.class);
		if(getCountIfEmailExist > 0){
			return -1;
		}
		String chackCountForUserNameIFExistQuery = dBQueryPropertyFile.getQueryForKey("chackCountForUserNameIFExistQuery");
		int getCountIfUserNameExist = jdbcTemplate1.queryForObject(chackCountForUserNameIFExistQuery, new Object[]{userData.getUseUserName()}, Integer.class);
		if(getCountIfUserNameExist > 0){
			return -2;
		}
		String checkIFEmpCodeExist = dBQueryPropertyFile.getQueryForKey("checkIFEmpCodeExist");
		int getcheckIFEmpCodeExist = jdbcTemplate1.queryForObject(checkIFEmpCodeExist, new Object[]{userData.getUserEmpCode()}, Integer.class);
		if(getcheckIFEmpCodeExist > 0){
			return -3;
		}else{
			Map<String,String> whoColumnMap = whoColumnsController.getWhoColumnsInfo();
			if(whoColumnMap != null){
				userData.setUserCreatedBy((String) whoColumnMap.get("userId"));
				userData.setUserCreationDate((String) whoColumnMap.get("currentDate"));
			}

			String confirmPassword = bCryptPasswordEncoder.encode(userData.getUserConfirmPassword());
			String addUserDetailsQuery = dBQueryPropertyFile.getQueryForKey("addUserDetailsQuery");
			int num =  jdbcTemplate1.update(addUserDetailsQuery,
					userData.getUserFirstName(),
					userData.getUserLastName(),
					userData.getUseUserName(),
					userData.getUserEmailAddress(),
					userData.getUserContactNumber(),
					confirmPassword,
					userData.getUserEmpCode(),
					userData.getUserDepartment(),
					true,
					userData.getUserCreatedBy(),
					userData.getUserCreationDate());
			if(num > 0){
				String getInsertedUserIdQuery =  dBQueryPropertyFile.getQueryForKey("getInsertedUserIdQuery");
				int getUserId = jdbcTemplate1.queryForObject(getInsertedUserIdQuery, new Object[]{userData.getUseUserName()}, Integer.class);
				if(getUserId > 0 ){
					String getRoleIdQuery =  dBQueryPropertyFile.getQueryForKey("getRoleIdQuery");
					int getRoleId = jdbcTemplate1.queryForObject(getRoleIdQuery, new Object[]{userData.getUserUserRoleName()}, Integer.class);
					if(getRoleId > 0 ){
						String insertIntoUserIdIntoRolesQuery =  dBQueryPropertyFile.getQueryForKey("insertIntoUserIdIntoRolesQuery");
						int num2 =  jdbcTemplate1.update(insertIntoUserIdIntoRolesQuery,
								getUserId,
								getRoleId);
						if(num2 > 0){
							return num2;
						}
					}
				}
			}
		}
		logger.info("INSIDE UserDaoIMPL END METHOD userAddProduct");
		return 0;
	}

	@Override
	public int userDeleteUser(Integer userId) {
		logger.info("INSIDE UserDaoIMPL START METHOD userDeleteUser ::");
		Map<String,String> whoColumnMap = whoColumnsController.getWhoColumnsInfo();
		String updated_User_Id = null;
		String currentDate = null;
		if(whoColumnMap != null){
			updated_User_Id = (String) whoColumnMap.get("userId");
			currentDate  = (String) whoColumnMap.get("currentDate");
		}
		String deleteUserByUserIdQuery = dBQueryPropertyFile.getQueryForKey("deleteUserByUserIdQuery");
		int num = jdbcTemplate1.update(deleteUserByUserIdQuery,false,updated_User_Id,currentDate,userId);
		logger.info("INSIDE UserDaoIMPL END METHOD userDeleteUser ::");
		return num;
	}

	@Override
	public UserData userFindUserByProdId(Integer userId) {
		logger.info("INSIDE UserDaoIMPL START METHOD userFindUserByProdId");
		String FindUserByIdQuery =  dBQueryPropertyFile.getQueryForKey("FindUserByIdQuery");
		logger.info("INSIDE UserDaoIMPL END METHOD userFindUserByProdId");
		return jdbcTemplate1.queryForObject(FindUserByIdQuery,  new Object[] { userId }, new UserDataMapper());
	}

	@Override
	public int userUpdateProduct(UserData userData) {
		logger.info("INSIDE UserDaoIMPL START METHOD userUpdateProduct");
		String updateUserQuery =  dBQueryPropertyFile.getQueryForKey("updateUserQuery");
		String confirmPassword = bCryptPasswordEncoder.encode(userData.getUserConfirmPassword());
		Map<String,String> whoColumnMap = whoColumnsController.getWhoColumnsInfo();
		if(whoColumnMap != null){
			userData.setUserUpdatedBy((String) whoColumnMap.get("userId"));
			userData.setUserLastUpdateDate((String) whoColumnMap.get("currentDate"));
		}
		int num = jdbcTemplate1.update(updateUserQuery,
				userData.getUserFirstName(),
				userData.getUserLastName(),
				userData.getUseUserName(),
				userData.getUserEmailAddress(),
				userData.getUserContactNumber(),
				confirmPassword,
				userData.getUserEmpCode(),
				userData.getUserDepartment(),
				userData.getUserUpdatedBy(),
				userData.getUserLastUpdateDate(),
				userData.getUserUserId());
		if(num > 0){
			String getRoleIdQuery =  dBQueryPropertyFile.getQueryForKey("getRoleIdQuery");
			int getRoleId = jdbcTemplate1.queryForObject(getRoleIdQuery, new Object[]{userData.getUserUserRoleName()}, Integer.class);
			if(getRoleId > 0 ){
				System.out.println("Role Id::::"+getRoleId);
				String updateUserIdIntoRolesQuery =  dBQueryPropertyFile.getQueryForKey("updateUserIdIntoRolesQuery");
				int num2 =  jdbcTemplate1.update(updateUserIdIntoRolesQuery,
						getRoleId,
						userData.getUserUserId());
				if(num2 > 0){
					return num2;
				}
			}
		}
		logger.info("INSIDE UserDaoIMPL END METHOD userUpdateProduct");
		return num;
	}

	@Override
	public List<UserData> getEmpcodeWithUserRole() {
		logger.info("INSIDE UserDaoIMPL START METHOD getEmpcodeWithUserRole ::");
		String selectEmpCodeWithUserRoleQuery = dBQueryPropertyFile.getQueryForKey("selectEmpCodeWithUserRoleQuery");
		List<UserData> userDataList = jdbcTemplate1.query(selectEmpCodeWithUserRoleQuery, new UserEmpCodeWithRoleDataMapper());
		logger.info("INSIDE UserDaoIMPL END METHOD getEmpcodeWithUserRole ::");
		return userDataList;
	}

	private static final class UserEmpCodeWithRoleDataMapper implements RowMapper<UserData>{
		@Override
		public UserData mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserData userData = new UserData();
			userData.setUserEmpCode(rs.getString("Emp_Code"));
			userData.setUserUserRoleName(rs.getString("User_role"));
			return userData;
		}
	}

	@Override
	public List<String> getEmpcodeList() {
		logger.info("INSIDE UserDaoIMPL START METHOD getEmpcodeList ::");
		String empCodeListQuery = dBQueryPropertyFile.getQueryForKey("empCodeListQuery");
		List<String> getEmpCodeList =  jdbcTemplate1.queryForList(empCodeListQuery, String.class);
		logger.info("INSIDE UserDaoIMPL START METHOD getEmpcodeList ::");
		return getEmpCodeList;	
	}

	@Override
	public List<String> getUserRoleList() {
		logger.info("INSIDE UserDaoIMPL START METHOD getUserRoleList ::");
		String userRoleList = dBQueryPropertyFile.getQueryForKey("userRoleList");
		List<String> getUserRoleList =  jdbcTemplate1.queryForList(userRoleList, String.class);
		logger.info("INSIDE UserDaoIMPL START METHOD getUserRoleList ::");
		return getUserRoleList;	
	}

	@Override
	public List<String> getDepartmentList() {
		logger.info("INSIDE UserDaoIMPL START METHOD getDepartmentList ::");
		String userDepartmentListQuery = dBQueryPropertyFile.getQueryForKey("userDepartmentListQuery");
		List<String> getDepartList =  jdbcTemplate1.queryForList(userDepartmentListQuery, String.class);
		logger.info("INSIDE UserDaoIMPL START METHOD getDepartmentList ::");
		return getDepartList;	
	}

	@Override
	public List<String> userGetUserDept() {
		logger.info("INSIDE UserDaoIMPL START METHOD getDepartmentList ::");
		String userDeptQuery = dBQueryPropertyFile.getQueryForKey("userDeptQuery");
		List<String> getDepartList =  jdbcTemplate1.queryForList(userDeptQuery, String.class);
		logger.info("INSIDE UserDaoIMPL START METHOD getDepartmentList ::");
		return getDepartList;
	}

	@Override
	public List<UserData> userSearchByCondition(String empCode, String dept, String userRole) {
		logger.info("INSIDE UserDaoIMPL START METHOD userSearchByCondition ::");
		
//		select * from XXSPIRAX_USER_MASTER u inner join XXSPIRAX_USER_MASTER_user_roles ur 
//		on(u.User_ID=ur.XXSPIRAX_USER_MASTER_User_ID) inner join user_roles r
//		 on(ur.user_roles_user_role_id=r.user_role_id) where enabled = 1 AND Emp_Code like ? AND Emp_Dept like ?
//		 order by u.User_ID desc 
//		 
//		 SELECT *FROM XXSPIRAX_USER_MASTER WHERE Emp_Code like ? AND Emp_Dept like ?
				 
		String selectUserListByConditionQuery = dBQueryPropertyFile.getQueryForKey("selectUserListByConditionQuery");
		List<UserData> UserDataList = jdbcTemplate1.query(selectUserListByConditionQuery, new Object[]{empCode+"%", dept+"%"} , new UserDataMapper());
		logger.info("INSIDE UserDaoIMPL END METHOD userSearchByCondition ::");
		return UserDataList;
	}

	@Override
	public List<String> userGetUserRolesForUpdate() {
		logger.info("INSIDE UserDaoIMPL START METHOD userGetUserRolesForUpdate ::");
		String userRoleList = dBQueryPropertyFile.getQueryForKey("userRoleList");
		List<String> getUserRoleList =  jdbcTemplate1.queryForList(userRoleList, String.class);
		logger.info("INSIDE UserDaoIMPL END METHOD userGetUserRolesForUpdate ::");
		return getUserRoleList;	
	}

	@Override
	public int userUpdateUserRoleProduct(UserData userDataRole) {
		logger.info("INSIDE UserDaoIMPL START METHOD userUpdateUserRoleProduct");

		if(userDataRole.getUserUserRoleName().equalsIgnoreCase("PR_APPROVER1") || userDataRole.getUserUserRoleName().equalsIgnoreCase("PR_APPROVER2")){
			String checkApproverAlreadyExistQuery = dBQueryPropertyFile.getQueryForKey("checkApproverAlreadyExistQuery");
			int getCountIfApproverExist = jdbcTemplate1.queryForObject(checkApproverAlreadyExistQuery, new Object[]{userDataRole.getUserUserRoleName()}, Integer.class);
			if(getCountIfApproverExist > 0){
				return -2;
			}
		}
		String checkUserRoleAlreadyExistQuery = dBQueryPropertyFile.getQueryForKey("checkUserRoleAlreadyExistQuery");
		int getCountIfRoleExist = jdbcTemplate1.queryForObject(checkUserRoleAlreadyExistQuery, new Object[]{userDataRole.getUseUserName() , userDataRole.getUserUserRoleName()}, Integer.class);
		if(getCountIfRoleExist > 0){
			return -1;
		}
		else{
			String getInsertedUserIdQuery =  dBQueryPropertyFile.getQueryForKey("getInsertedUserIdQuery");
			int getUserId = jdbcTemplate1.queryForObject(getInsertedUserIdQuery, new Object[]{userDataRole.getUseUserName()}, Integer.class);
			if(getUserId > 0 ){
				System.out.println("User ID :::"+getUserId);
				String getRoleIdQuery =  dBQueryPropertyFile.getQueryForKey("getRoleIdQuery");
				int getRoleId = jdbcTemplate1.queryForObject(getRoleIdQuery, new Object[]{userDataRole.getUserUserRoleName()}, Integer.class);
				if(getRoleId > 0 ){
					System.out.println("Role Id::::"+getRoleId);
					String insertIntoUserIdIntoRolesQuery =  dBQueryPropertyFile.getQueryForKey("insertIntoUserIdIntoRolesQuery");
					int num2 =  jdbcTemplate1.update(insertIntoUserIdIntoRolesQuery,
							getUserId,
							getRoleId);
					if(num2 > 0){
						return num2;
					}
				}
			}
		}
		logger.info("INSIDE UserDaoIMPL END METHOD userUpdateUserRoleProduct");
		return 0;
	}

	@Override
	public int saveRegisterEmailForNotification(String userName, String encryptedString) {
		String deleteFromTableQuery = dBQueryPropertyFile.getQueryForKey("deleteFromTableQuery");
		int num = jdbcTemplate1.update(deleteFromTableQuery);
		if(num >= 0){
			String registerEmailForNotificationQuery = dBQueryPropertyFile.getQueryForKey("registerEmailForNotificationQuery");
			int numIfInserted = jdbcTemplate1.update(registerEmailForNotificationQuery,userName,encryptedString);
			return numIfInserted;
		}
		return 0;
	}

	@Override
	public UserData getServiceMailIdAndPassword() {
		String selectUserForEmailForNotificationQuery = dBQueryPropertyFile.getQueryForKey("selectUserForEmailForNotificationQuery");
		UserData userData = jdbcTemplate1.queryForObject(selectUserForEmailForNotificationQuery, new UserDataMapperForMail());
		return userData;
	}

	private static final class UserDataMapperForMail implements RowMapper<UserData>{
		@Override
		public UserData mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserData userData = new UserData();
			userData.setUserEmailAddress(rs.getString("email_id"));
			userData.setUserConfirmPassword(rs.getString("password"));
			return userData;
		}
	}

	@Override
	public UserData getFullNameByUserId(String getpRpApprover2Name) {
		String selectUserForByUserIdQuery = dBQueryPropertyFile.getQueryForKey("selectUserForByUserIdQuery");
		UserData userData = jdbcTemplate1.queryForObject(selectUserForByUserIdQuery, new Object[]{getpRpApprover2Name}, new UserDataFullName());
		return userData;
	}

	private static final class UserDataFullName implements RowMapper<UserData>{
		@Override
		public UserData mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserData userData = new UserData();
			userData.setUserFirstName(rs.getString("First_Name"));
			userData.setUserLastName(rs.getString("Last_Name"));
			return userData;
		}
	}

	@Override
	public int userDeleteUserRole(Integer userId) {
		logger.info("INSIDE UserDaoIMPL START METHOD userDeleteUserRole ::");
		String deleteUserRoleByUserIdQuery = dBQueryPropertyFile.getQueryForKey("deleteUserRoleByUserIdQuery");
		int num = jdbcTemplate1.update(deleteUserRoleByUserIdQuery,userId);
		logger.info("INSIDE UserDaoIMPL END METHOD userDeleteUserRole ::");
		return num;
	}

	@Override
	public int deleteRoleByUserId(String userId, String userRole) {
		logger.info("INSIDE UserDaoIMPL START METHOD deleteRoleByUserId ::");
		String selectUserRoleByRoleId = dBQueryPropertyFile.getQueryForKey("selectUserRoleByRoleId");
		String role = jdbcTemplate1.queryForObject(selectUserRoleByRoleId, new Object[]{userRole}, String.class);
		if(role.equalsIgnoreCase("ADMIN")){
			System.out.println("role :::"+role);
	        String checkIfOneAdminExistQuery = dBQueryPropertyFile.getQueryForKey("checkIfOneAdminExistQuery");
			int numIfAdminExists = jdbcTemplate1.queryForObject(checkIfOneAdminExistQuery, new Object[]{userRole}, int.class);
			if(numIfAdminExists <= 1){
				return -3;
			}
		}

		String checkIfUserIdWithUserRoleMultipleQuery = dBQueryPropertyFile.getQueryForKey("checkIfUserIdWithUserRoleMultipleQuery");
		int num = jdbcTemplate1.queryForObject(checkIfUserIdWithUserRoleMultipleQuery, new Object[]{userId}, int.class);
		if(num <= 1){
			return -1;
		}else{
			String deleteRoleByUserIdQuery = dBQueryPropertyFile.getQueryForKey("deleteRoleByUserIdQuery");
			int num2 = jdbcTemplate1.update(deleteRoleByUserIdQuery,userId,userRole);
			if(num2 > 0){
				return -2;
			}
		}
		logger.info("INSIDE UserDaoIMPL END METHOD deleteRoleByUserId ::");
		return num;
	}

}
