package com.spiraxcalibration.services;

import java.util.List;

import com.spiraxcalibration.models.UserData;

public interface UserIService {
	List<UserData> userGetUsersDetails();
	List<String> userGetUserRoles();
	int userAddUser(UserData userData);
	int userUpdateProduct(UserData userData);
	int userDeleteUser(Integer userId);
	UserData userFindUserByProdId(Integer userId);
	List<UserData> getEmpcodeWithUserRole();
	List<String> getEmpcodeList();
	List<String> getUserRoleList();
	List<String> getDepartmentList();
	List<String> userGetUserDept();
	List<UserData> userSearchByCondition(String empCode, String department, String userRole);
	List<String> userGetUserRolesForUpdate();
	int userUpdateUserRoleProduct(UserData userData);
	int saveRegisterEmailForNotification(String userName, String password);
	String getFullNameByUserId(String getpRpApprover2Name);
	int userDeleteUserRole(Integer userId);
	int deleteRoleByUserId(String userId, String userRole);
}
