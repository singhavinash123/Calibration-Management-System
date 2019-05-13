package com.spiraxcalibration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpiraxCalibrationApplication{
	public static void main(String[] args) {
		SpringApplication.run(SpiraxCalibrationApplication.class, args);
	}
	
//	 @Override
//	    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//	        return application.sources(SpiraxCalibrationApplication.class);
//	    }
}
