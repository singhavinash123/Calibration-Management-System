package com.spiraxcalibration.WebConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppsPropertyFile {

	protected Properties DBQueryProp = null;
	protected InputStream input = this.getClass().getClassLoader().getResourceAsStream("databaseQueries.properties");

	protected Properties UrlProp = null;
	protected InputStream urlPropInput = this.getClass().getClassLoader().getResourceAsStream("urlconfiguration.properties");

	protected Properties MeasurementProp = null;
	protected InputStream MeasurementPropInput = this.getClass().getClassLoader().getResourceAsStream("measurment.properties");

	protected Properties SideBarContentProp = null;
	protected InputStream SideBarContentPropInput = this.getClass().getClassLoader().getResourceAsStream("sidebarcontent.properties");

	protected Properties usersProp = null;
	protected InputStream usersPropPropInput = this.getClass().getClassLoader().getResourceAsStream("user.properties");

	protected Properties messagesProp = null;
	protected InputStream messagesPropInput = this.getClass().getClassLoader().getResourceAsStream("messages.properties");

	public AppsPropertyFile(){
		DBQueryProp = new Properties();
		UrlProp = new Properties();
		MeasurementProp = new Properties();
		SideBarContentProp = new Properties();
		usersProp = new Properties();
		messagesProp = new Properties();
		try {
			DBQueryProp.load(input);
			UrlProp.load(urlPropInput);
			MeasurementProp.load(MeasurementPropInput);
			SideBarContentProp.load(SideBarContentPropInput);
			usersProp.load(usersPropPropInput);
			messagesProp.load(messagesPropInput);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getQueryForKey(String key){
		return DBQueryProp.getProperty(key);
	}
	
	
	public String getURLForKey(String key){
		return UrlProp.getProperty(key);
	}
	
	public String getmeasurment(String key){
		return MeasurementProp.getProperty(key);
	}
	
	public String getSideBarContent(String key){
		return SideBarContentProp.getProperty(key);
	}

	public String getUserDetail(String key){
		return usersProp.getProperty(key);
	}
	
	public String getMessageDetail(String key){
		return messagesProp.getProperty(key);
	}
}
