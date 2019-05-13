package com.spiraxcalibration.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.spiraxcalibration.servicesIMPL.WhoColumnServiceIMPL;

@Component
public class WhoColumnsController {

	Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	WhoColumnServiceIMPL whoColumnServiceIMPL;
	
	public Map<String, String> getWhoColumnsInfo(){
		logger.info("INSIDE WhoColumnsController START METHOD getWhoColumnsInfo");
		String name  = SecurityContextHolder.getContext().getAuthentication().getName();
		Map<String,String> map = new HashMap<String,String>();
		if(name != null){
			int userId = getUserIdByUserName(name);
			Date date = new Date();
			DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss");  
			String strDate = dateFormat.format(date); 
			String user_Id = Integer.toString(userId);
			map.put("userId", user_Id);
			map.put("currentDate", strDate);
			return map;
		}
		logger.info("INSIDE WhoColumnsController END METHOD getWhoColumnsInfo");
		return null;
	}

	private int getUserIdByUserName(String name) {
		logger.info("INSIDE WhoColumnsController START METHOD getUserIdByUserName");
		int userId = whoColumnServiceIMPL.getUserIdByUserName(name);
		logger.info("INSIDE WhoColumnsController END METHOD getUserIdByUserName");
		return userId;
	}
}
