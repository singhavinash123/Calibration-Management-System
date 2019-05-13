package com.spiraxcalibration.servicesIMPL;

import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spiraxcalibration.dao.UserIDao;
import com.spiraxcalibration.models.UserData;
import com.spiraxcalibration.services.UserIService;

@Service
public class UserServiceIMPL implements UserIService{
	Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	UserIDao userIDao;
	
	private static final String key = "aesEncryptionKey";
	private static final String initVector = "encryptionIntVec";
	
	@Override
	public List<UserData> userGetUsersDetails() {
		logger.info("INSIDE UserServiceIMPL START METHOD userGetUsersDetails ::");
		List<UserData> userData = userIDao.userGetUsersDetails();
		if(userData == null){
			logger.error("INSIDE UserServiceIMPL ===> userData :"+userData);
		}
		logger.info("INSIDE UserServiceIMPL END METHOD userGetUsersDetails ::");
		return userData;
	}

	@Override
	public List<String> userGetUserRoles() {
		logger.info("INSIDE UserServiceIMPL START METHOD userGetUserRoles ::");
		List<String> userRoleList = userIDao.userGetUserRoles();
		if(userRoleList == null){
			logger.error("INSIDE UserServiceIMPL ===> userRoleList :"+userRoleList);
		}
		logger.info("INSIDE UserServiceIMPL END METHOD userGetUserRoles ::");
		return userRoleList;
	}

	@Override
	public int userAddUser(UserData userData) {
		logger.info("INSIDE UserServiceIMPL START METHOD userAddProduct ::");
		int num = userIDao.userAddUser(userData);
		logger.info("INSIDE UserServiceIMPL END METHOD userAddProduct ::");
		return num;
	}

	@Override
	public int userUpdateProduct(UserData userData) {
		logger.info("INSIDE UserServiceIMPL START METHOD userUpdateProduct ::");
	    int  num =  userIDao.userUpdateProduct(userData);
		logger.info("INSIDE UserServiceIMPL END METHOD userUpdateProduct ::");
	    return  num;
	}

	@Override
	public int userDeleteUser(Integer userId) {
		logger.info("INSIDE UserServiceIMPL START METHOD userDeleteUser ::");
		int num =  userIDao.userDeleteUser(userId);
		logger.info("INSIDE UserServiceIMPL END METHOD userDeleteUser ::");
		return num;
	}

	@Override
	public UserData userFindUserByProdId(Integer userId) {
		logger.info("INSIDE UserServiceIMPL START METHOD prFindProductByProdId ::");
		UserData userData = null;
		userData = userIDao.userFindUserByProdId(userId);
		if(userData != null){
			return userData;
		}else{
			logger.error("INSIDE UserServiceIMPL  ===> userData :"+userData);
		}
		logger.info("INSIDE UserServiceIMPL END METHOD prFindProductByProdId ::");
		return userData;
	}

	@Override
	public List<UserData> getEmpcodeWithUserRole() {
		logger.info("INSIDE UserServiceIMPL START METHOD getEmpcodeWithUserRole ::");
		List<UserData> userData = userIDao.getEmpcodeWithUserRole();
		if(userData == null){
			logger.error("INSIDE UserServiceIMPL ===> userData :"+userData);
		}
		logger.info("INSIDE UserServiceIMPL END METHOD getEmpcodeWithUserRole ::");
		return userData;
	}

	@Override
	public List<String> getEmpcodeList() {
		logger.info("INSIDE UserServiceIMPL START METHOD getEmpcodeList ::");
		List<String> empCodeList = userIDao.getEmpcodeList();
		if(empCodeList == null){
			logger.error("INSIDE UserServiceIMPL ===> empCodeList :"+empCodeList);
		}
		logger.info("INSIDE UserServiceIMPL END METHOD getEmpcodeList ::");
		return empCodeList;
	}

	@Override
	public List<String> getUserRoleList() {
		logger.info("INSIDE UserServiceIMPL START METHOD getUserRoleList ::");
		List<String> userRoleList = userIDao.getUserRoleList();
		if(userRoleList == null){
			logger.error("INSIDE UserServiceIMPL ===> userRoleList :"+userRoleList);
		}
		logger.info("INSIDE UserServiceIMPL END METHOD getUserRoleList ::");
		return userRoleList;
	}

	@Override
	public List<String> getDepartmentList() {
		logger.info("INSIDE UserServiceIMPL START METHOD getDepartmentList ::");
		List<String> userDepartmentList = userIDao.getDepartmentList();
		if(userDepartmentList == null){
			logger.error("INSIDE UserServiceIMPL ===> userDepartmentList :"+userDepartmentList);
		}
		logger.info("INSIDE UserServiceIMPL END METHOD getDepartmentList ::");
		return userDepartmentList;
	}

	@Override
	public List<String> userGetUserDept() {
		logger.info("INSIDE UserServiceIMPL START METHOD getUserRoleList ::");
		List<String> userDeptList = userIDao.userGetUserDept();
		if(userDeptList == null){
			logger.error("INSIDE UserServiceIMPL ===> userDeptList :"+userDeptList);
		}
		logger.info("INSIDE UserServiceIMPL END METHOD getUserRoleList ::");
		return userDeptList;
	}

	@Override
	public List<UserData> userSearchByCondition(String empCode, String department, String userRole) {
		logger.info("INSIDE UserServiceIMPL START METHOD userSearchByCondition ::");
		List<UserData> userDataList = null;
		userDataList = userIDao.userSearchByCondition(empCode.trim(),department.trim(),userRole.trim());
		if(userDataList != null){
			return userDataList;
		}else{
			logger.error("INSIDE UserServiceIMPL ====> userDataList :"+userDataList);
		}
		logger.info("INSIDE UserServiceIMPL END METHOD userSearchByCondition ::");
		return userDataList;
	}

	@Override
	public List<String> userGetUserRolesForUpdate() {
		logger.info("INSIDE UserServiceIMPL START METHOD userGetUserRolesForUpdate ::");
		List<String> userRoleList = userIDao.userGetUserRolesForUpdate();
		if(userRoleList == null){
			logger.error("INSIDE UserServiceIMPL ===> userRoleList :"+userRoleList);
		}
		logger.info("INSIDE UserServiceIMPL END METHOD userGetUserRolesForUpdate ::");
		return userRoleList;
	}

	@Override
	public int userUpdateUserRoleProduct(UserData UserDataRole) {
		logger.info("INSIDE UserServiceIMPL START METHOD userUpdateUserRoleProduct ::");
		int num = userIDao.userUpdateUserRoleProduct(UserDataRole);
		logger.info("INSIDE UserServiceIMPL END METHOD userUpdateUserRoleProduct ::");
		return num;
	}

	@Override
	public int saveRegisterEmailForNotification(String userName, String password) {
		logger.info("INSIDE UserServiceIMPL START METHOD saveRegisterEmailForNotification ::");
		String encryptedString = encrypt(password);
		int num = userIDao.saveRegisterEmailForNotification(userName,encryptedString);
		logger.info("INSIDE UserServiceIMPL END METHOD saveRegisterEmailForNotification ::");
		return num;
	}

	private String encrypt(String password) {
		try {
			IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
			byte[] encrypted = cipher.doFinal(password.getBytes());
			return Base64.encodeBase64String(encrypted);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public UserData getServiceMailIdAndPassword() {
		logger.info("INSIDE UserServiceIMPL START METHOD getServiceMailIdAndPassword ::");
		UserData userData = userIDao.getServiceMailIdAndPassword();
		String decryptedString = decrypt(userData.getUserConfirmPassword());
		UserData  userDataForEmail = new UserData();
		userDataForEmail.setUserEmailAddress(userData.getUserEmailAddress());
		userDataForEmail.setUserConfirmPassword(decryptedString);
		logger.info("INSIDE UserServiceIMPL START METHOD getServiceMailIdAndPassword ::");
		return userDataForEmail;
	}

	private String decrypt(String userConfirmPassword) {
		try {
			IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			byte[] original = cipher.doFinal(Base64.decodeBase64(userConfirmPassword));
			return new String(original);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public String getFullNameByUserId(String getpRpApprover2Name) {
		logger.info("INSIDE UserServiceIMPL START METHOD getFullNameByUserId ::");
		UserData userData = null;
		userData = userIDao.getFullNameByUserId(getpRpApprover2Name);
		if(userData != null){
			return userData.getUserFirstName() +" "+userData.getUserLastName();
			
		}else{
			logger.error("INSIDE UserServiceIMPL  ===> userData :"+userData);
		}
		logger.info("INSIDE UserServiceIMPL END METHOD getFullNameByUserId ::");
		return null;
	}

	@Override
	public int userDeleteUserRole(Integer userId) {
		logger.info("INSIDE UserServiceIMPL START METHOD userDeleteUserRole ::");
		int num =  userIDao.userDeleteUserRole(userId);
		logger.info("INSIDE UserServiceIMPL END METHOD userDeleteUserRole ::");
		return num;
	}

	@Override
	public int deleteRoleByUserId(String userId, String userRole) {
		logger.info("INSIDE UserServiceIMPL START METHOD deleteRoleByUserId ::");
		int num =  userIDao.deleteRoleByUserId(userId,userRole);
		logger.info("INSIDE UserServiceIMPL END METHOD deleteRoleByUserId ::");
		return num;
	}
}
