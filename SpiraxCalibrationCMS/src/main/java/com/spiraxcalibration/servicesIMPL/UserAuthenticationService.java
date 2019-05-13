package com.spiraxcalibration.servicesIMPL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spiraxcalibration.dao.UserAuthenticationIDao;
import com.spiraxcalibration.models.UserData;
import com.spiraxcalibration.services.UserAuthenticationIService;

@Service
public class UserAuthenticationService implements UserAuthenticationIService{

	@Autowired
	UserAuthenticationIDao userAuthenticationIDao;
	
	@Override
	public String getUserPasswordByUserName(String userName) {
		return userAuthenticationIDao.getUserPasswordByUserName(userName);
	}

	@Override
	public UserData findUserByEmail(String userName) {
		return userAuthenticationIDao.findUserByEmail(userName);
	}

	@Override
	public int updateResetToken(UserData userData) {
		return userAuthenticationIDao.updateResetToken(userData);
	}

	@Override
	public UserData findUserByResetToken(String token) {
		return userAuthenticationIDao.findUserByResetToken(token);
	}

	@Override
	public int updatePasswordUser(UserData userData) {
		return userAuthenticationIDao.updatePasswordUser(userData);	
	}

}
