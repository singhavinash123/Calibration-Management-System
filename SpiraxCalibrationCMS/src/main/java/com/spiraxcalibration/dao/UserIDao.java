package com.spiraxcalibration.dao;

import java.util.List;

import com.spiraxcalibration.models.UserData;

public interface UserIDao {
	List<UserData> userGetUsersDetails();
	List<String> userGetUserRoles();
	int userAddUser(UserData userData);
	int userDeleteUser(Integer userId);
	UserData userFindUserByProdId(Integer userId);
	int  userUpdateProduct(UserData userData);
	List<UserData> getEmpcodeWithUserRole();
	List<String> getEmpcodeList();
	List<String> getUserRoleList();
	List<String> getDepartmentList();
	List<String> userGetUserDept();
	List<String> userGetUserRolesForUpdate();
	int userUpdateUserRoleProduct(UserData userDataRole);
	List<UserData> userSearchByCondition(String trim, String trim2, String trim3);
	int saveRegisterEmailForNotification(String userName, String encryptedString);
	UserData getServiceMailIdAndPassword();
	UserData getFullNameByUserId(String getpRpApprover2Name);
	int userDeleteUserRole(Integer userId);
	int deleteRoleByUserId(String userId, String userRole);
	
}
