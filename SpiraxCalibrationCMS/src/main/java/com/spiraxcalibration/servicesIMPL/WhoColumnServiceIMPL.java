package com.spiraxcalibration.servicesIMPL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spiraxcalibration.daoIMPL.WhoColumnDaoIMPL;
import com.spiraxcalibration.services.WhoCloumnIService;

@Service
public class WhoColumnServiceIMPL implements WhoCloumnIService{

	@Autowired
	WhoColumnDaoIMPL whoColumnDaoIMPL;
	
	public int getUserIdByUserName(String name) {
		return whoColumnDaoIMPL.getUserIdByUserName(name);
	}

}
