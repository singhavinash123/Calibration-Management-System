package com.spiraxcalibration.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spiraxcalibration.WebConfig.AppsPropertyFile;
import com.spiraxcalibration.models.CostPricesData;
import com.spiraxcalibration.services.CostingScrapedCostReportIService;

@Controller
public class CostingScrapedCostReportController {
	Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	CostingScrapedCostReportIService  costingScrapedCostReportIService;
	
	AppsPropertyFile dBQueryPropertyFile = new AppsPropertyFile();

	// HOME PAGE..
	@RequestMapping(value="/home")
	public ModelAndView getDashBoard(){
		logger.info("INSIDE CostingScrapedCostReportController START METHOD getDashBoard");
		ModelAndView model =  new ModelAndView("welcome/welcome3");
		String adminRole = dBQueryPropertyFile.getUserDetail("spring.role.admin");
		String userRole = dBQueryPropertyFile.getUserDetail("spring.role.user");
		String managerRole = dBQueryPropertyFile.getUserDetail("spring.role.manager");
		String engineerRole = dBQueryPropertyFile.getUserDetail("spring.role.engineer");
		String manufacture_associatesRole = dBQueryPropertyFile.getUserDetail("spring.role.manufacturing_associates");
		String approver1Role = dBQueryPropertyFile.getUserDetail("spring.role.approver1");
		String approver2Role = dBQueryPropertyFile.getUserDetail("spring.role.approver2");
		String procurement_engineerRole = dBQueryPropertyFile.getUserDetail("spring.role.procurement_engineer");

		model.addObject("adminRole", adminRole);
		model.addObject("userRole", userRole);
		model.addObject("managerRole", managerRole);
		model.addObject("engineerRole", engineerRole);
		model.addObject("manufacture_associatesRole", manufacture_associatesRole);
		model.addObject("approver1Role", approver1Role);
		model.addObject("approver2Role", approver2Role);
		model.addObject("procurement_engineerRole", procurement_engineerRole);
		
		int year = Calendar.getInstance().get(Calendar.YEAR);
		List<CostPricesData> currentYearPriceData = costingScrapedCostReportIService.LoadTheCalibrationCostingForcastCurrentYear();
		ArrayList<String> costPriceCurrentYearList = new ArrayList<String>(Collections.nCopies(12, "0"));
		for(CostPricesData  costPricesData: currentYearPriceData){
			if(costPricesData.getCostMonth() != null){
				int monthNum = Integer.parseInt(costPricesData.getCostMonth());
				if(monthNum > 0){
					costPriceCurrentYearList.set(monthNum-1 , costPricesData.getCostPrice());
				}
			}
		}
		
		model.addObject("currentYearCostPrice", costPriceCurrentYearList);
		model.addObject("currentYear", year);

		List<CostPricesData> forcastYearPrice = costingScrapedCostReportIService.LoadTheCalibrationCostingForcastYear();
		ArrayList<String> costPriceForecastYearList = new ArrayList<String>(Collections.nCopies(12, "0"));

		for(CostPricesData  costPricesData: forcastYearPrice){
			if(costPricesData.getCostMonth() != null){
				int monthNum = Integer.parseInt(costPricesData.getCostMonth());
				if(monthNum > 0){
					costPriceForecastYearList.set(monthNum-1 , costPricesData.getCostPrice());
				}
			}
		}

		model.addObject("forcastYearPrice", costPriceForecastYearList);
		model.addObject("forcastYear", year+1);
		List<CostPricesData> instrumentScrapedCurrentYearData = costingScrapedCostReportIService.LoadTheInstrumentScrapedCurrentYear();
		ArrayList<String> scrappedInstrumentCurrentYearList = new ArrayList<String>(Collections.nCopies(12, "0"));

		for(CostPricesData  costPricesData: instrumentScrapedCurrentYearData){
			if(costPricesData.getCostMonth() != null){
				int monthNum = Integer.parseInt(costPricesData.getCostMonth());
				if(monthNum > 0){
					scrappedInstrumentCurrentYearList.set(monthNum-1 , costPricesData.getCostScrappedCount());
				}
			}
		}
		
		model.addObject("scrappedInstrumentCurrentYear", scrappedInstrumentCurrentYearList);

		List<CostPricesData> instrumentScrapedPriorYearData = costingScrapedCostReportIService.LoadTheInstrumentScrapedPriorCurrentYear();
		ArrayList<String> scrappedInstrumentPriorYearList = new ArrayList<String>(Collections.nCopies(12, "0"));

		for(CostPricesData  costPricesData: instrumentScrapedPriorYearData){
			if(costPricesData.getCostMonth() != null){
				int monthNum = Integer.parseInt(costPricesData.getCostMonth());
				if(monthNum > 0){
					scrappedInstrumentPriorYearList.set(monthNum-1 , costPricesData.getCostScrappedCount());
				}
			}
		}
		
		model.addObject("scrappedInstrumentPriorYear", scrappedInstrumentPriorYearList);

		List<CostPricesData> instrumentCountNextPlanData = costingScrapedCostReportIService.LoadTheInstrumentCountCurrentYearDataList();
		ArrayList<String> instrumentCountCurrentNextYearDataList = new ArrayList<String>(Collections.nCopies(12, "0"));

		for(CostPricesData  costPricesData: instrumentCountNextPlanData){
			if(costPricesData.getCostMonth() != null){
				int monthNum = Integer.parseInt(costPricesData.getCostMonth());
				if(monthNum > 0){
					instrumentCountCurrentNextYearDataList.set(monthNum-1 , costPricesData.getCostInstrumentCount());
				}
			}
		}
		
		model.addObject("instrumentCountCurrentNextYearDataList", instrumentCountCurrentNextYearDataList);

		List<CostPricesData> instrumentCountCurrentYearActualData = costingScrapedCostReportIService.LoadTheinstrumentCountCurrentYearActualData();
		ArrayList<String> instrumentCountCurrentYearActualDataList = new ArrayList<String>(Collections.nCopies(12, "0"));

		for(CostPricesData  costPricesData: instrumentCountCurrentYearActualData){
			if(costPricesData.getCostMonth() != null){
				int monthNum = Integer.parseInt(costPricesData.getCostMonth());
				if(monthNum > 0){
					instrumentCountCurrentYearActualDataList.set(monthNum-1 , costPricesData.getCostInstrumentCount());
				}
			}
		}
		model.addObject("instrumentCountCurrentYearActualDataList", instrumentCountCurrentYearActualDataList);
		model.addObject("PlanYear", year);
		model.addObject("ActualYear", year);
		
		logger.info("INSIDE CostingScrapedCostReportController END METHOD getDashBoard");
		return model;
	}
}
