package com.spiraxcalibration.daoIMPL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.spiraxcalibration.WebConfig.AppsPropertyFile;
import com.spiraxcalibration.dao.WhoColumnIDao;

@Repository
public class WhoColumnDaoIMPL implements WhoColumnIDao {

	Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	AppsPropertyFile dBQueryPropertyFile = new AppsPropertyFile();

	@Autowired
	JdbcTemplate  jdbcTemplate1;
	
	public int getUserIdByUserName(String name) {
		logger.info("INSIDE WhoColumnDaoIMPL START METHOD getUserIdByUserName");
		String getUserIdFromUserByUserNameQuery = dBQueryPropertyFile.getQueryForKey("getUserIdFromUserByUserNameQuery");
		int userId =  jdbcTemplate1.queryForObject(getUserIdFromUserByUserNameQuery,new Object[]{name},Integer.class);
		logger.info("INSIDE WhoColumnDaoIMPL END METHOD getUserIdByUserName");
		return userId;	
	}
				
}
