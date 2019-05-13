package com.spiraxcalibration.services;

import com.spiraxcalibration.models.UserData;

public interface UserAuthenticationIService {
	String getUserPasswordByUserName(String userName);
	UserData findUserByEmail(String userName);
	int updateResetToken(UserData userData);
	UserData findUserByResetToken(String token);
	int updatePasswordUser(UserData userData);

}
