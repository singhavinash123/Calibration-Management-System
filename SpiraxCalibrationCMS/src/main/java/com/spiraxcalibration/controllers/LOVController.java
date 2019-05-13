package com.spiraxcalibration.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spiraxcalibration.models.LOVData;
import com.spiraxcalibration.services.LOVIService;

@Controller
public class LOVController {
	Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	LOVIService lOVService;

	@RequestMapping(value="/lovMaintenance" , method=RequestMethod.GET)
	public ModelAndView getLovDetails(){
		logger.info("INSIDE LOVController START METHOD getLovDetails ::");
		ModelAndView  model = new ModelAndView("lovs/lovsList");
		model.addObject("lovsList",lOVService.getLovDetails());
		HashMap<String, String> lovProcessWithName = lOVService.getLovProcessWithName();

		List<String> lovName = lOVService.getLovNameList();
		model.addObject("lovName", lovName);
		model.addObject("lovProcessWithNameList", lovProcessWithName);

		logger.info("INSIDE LOVController END METHOD getLovDetails ::");
		return model;
	}

	@RequestMapping(value="/loaddata" , method=RequestMethod.POST)
	public ModelAndView getLovLoadData(){
		logger.info("INSIDE LOVController START METHOD getLovLoadData ::");
		logger.info("INSIDE LOVController END METHOD getLovLoadData ::");
		return new ModelAndView("lovs/lovUpdateOrDelete");
	}

//	@RequestMapping(value = "/download_lov_data")
//	public void certDownloadByCertId(HttpServletRequest request,HttpServletResponse response){
//		try { 
//			response.setContentType("application/vnd.ms-excel"); 
//			String fileName = "/tutorialVideos/LOV_MASTER_MAINTENANCE.xlsx";
//			response.setHeader("Content-disposition", "inline;filename="+fileName+""); 
//			write(response.getOutputStream()); 
//		} catch (Exception e) { 
//			throw new RuntimeException(e); 
//		} 
//	}

//	private void write(ServletOutputStream outputStream) {
//		HSSFWorkbook wb = new HSSFWorkbook(); 
//		//write(wb); 
//		try {
//			wb.write(outputStream);
//		} catch (IOException e) {
//			e.printStackTrace();
//		} 		
//	}

	@RequestMapping(value = "/addLov" , method = RequestMethod.GET)
	public ModelAndView addLov(){
		logger.info("INSIDE LOVController START METHOD addLov");
		ModelAndView model = new ModelAndView("lovs/lovUpdateOrDelete");
		LOVData  lOVData = new LOVData();
		model.addObject("processNameListLookup",lOVService.getProcessNames());
		model.addObject("lovData",lOVData);
		model.addObject("addOrUpdate","Add LOV");
		model.addObject("addORupdatePrheading","Add LOV");
		logger.info("INSIDE LOVController END METHOD addLov");
		return model;
	}

	@RequestMapping(value = "/updateLov/{lovId}" , method = RequestMethod.GET)
	public ModelAndView updateLov(@PathVariable("lovId") Integer lovId){
		logger.info("INSIDE LOVController START METHOD updateLov");
		ModelAndView model = new ModelAndView("lovs/lovUpdateOrDelete");

		LOVData lovData = lOVService.findLOVByLid(lovId);
		model.addObject("lovData",lOVService.findLOVByLid(lovId));
		List<LOVData> lovDataList = lOVService.getProcessNames();
		model.addObject("processNameListLookup",lovDataList);
		List<String> lovProcessNameList =  lOVService.getLovNamesByLovProcess(lovData.getLovLovId());
		model.addObject("lovProcessNameList",lovProcessNameList);
		model.addObject("addORupdatePrheading","Update LOV");
		model.addObject("addOrUpdate","Update LOV");

		logger.info("INSIDE LOVController END METHOD updateLov");
		return model;
	}

	@RequestMapping(value = "/saveLov" , method = RequestMethod.POST)
	public ModelAndView lovsSaveLove(@ModelAttribute("lovData") LOVData lOVData,HttpServletRequest request , RedirectAttributes redir){
		logger.info("INSIDE LOVController START METHOD lovsSaveLove");
		ModelAndView  model1 = new ModelAndView("lovs/lovUpdateOrDelete");
		ModelAndView  model2 = new ModelAndView("redirect:/lovMaintenance");

		model1.addObject("addOrUpdate","Add LOV");
		model2.addObject("addOrUpdate","Update LOV");

		if(lOVData != null && lOVData.getlId() != null){
			ModelAndView  model3 = new ModelAndView("redirect:/updateLov/"+lOVData.getlId());
			if(Integer.parseInt(lOVData.getLovProcess()) == 0){
				redir.addFlashAttribute("msg","Please select the Process!!");
				return model3;
			}
			String processHiddenValue = request.getParameter("processSelect");
			int lId = Integer.parseInt(lOVData.getLovProcess());
			if(Integer.parseInt(processHiddenValue) == 1){
				List<String> lovProcessNameList =  lOVService.getLovNamesByLovProcess(lId);
				redir.addFlashAttribute("lovProcessNameList",lovProcessNameList);
				redir.addFlashAttribute("lId",lId);
				return model3;
			}
			int countLovValueWithLovName = lOVService.updateLov(lOVData);
			if(countLovValueWithLovName == -1){
				redir.addFlashAttribute("error","Data Already Exist "+lOVData.getLovName()+"-"+lOVData.getLovValue());
				return model3;
			}
			model2.addObject("addORupdatePrheading","Update LOV");
			redir.addFlashAttribute("msg","LOV Details Updated Successfully!!");
			return model2;
		}else{
			ModelAndView  model3 = new ModelAndView("redirect:/addLov");
			if(Integer.parseInt(lOVData.getLovProcess()) == 0){
				redir.addFlashAttribute("msg","Please select the Process!!");
				return model3;
			}
			String processHiddenValue = request.getParameter("processSelect");
			int lId = Integer.parseInt(lOVData.getLovProcess());
			if(Integer.parseInt(processHiddenValue) == 1){
				List<String> lovProcessNameList =  lOVService.getLovNamesByLovProcess(lId);
				redir.addFlashAttribute("lovProcessNameList",lovProcessNameList);
				redir.addFlashAttribute("lId",lId);
				return model3;
			}
			int num = lOVService.addLov(lOVData);
			if(num == -1){
				ModelAndView  model4 = new ModelAndView("redirect:/addLov");
				redir.addFlashAttribute("error","Data Already Exist "+lOVData.getLovName()+"-"+lOVData.getLovValue());
				return model4;
			}
			model2.addObject("addORupdatePrheading","Add LOV");
			if(num > 0 ){
				redir.addFlashAttribute("msg","LOV Details Inserted Successfully!!");
				return model2;
			}
		}
		logger.info("INSIDE LOVController END METHOD lovsSaveLove");
		return model1; 
	}

	@RequestMapping(value = "/deleteLov/{lovId}" , method = RequestMethod.GET)
	public ModelAndView LovDeleteLov(@PathVariable(value="lovId") Integer lovId , RedirectAttributes redir){
		logger.info("INSIDE LOVController END METHOD LovDeleteLov ::");
		ModelAndView model = new ModelAndView("redirect:/lovMaintenance");
		int num = lOVService.deleteLov(lovId);
		if(num > 0){
			redir.addFlashAttribute("msg","LOV Deleted successfully!!");
		}
		logger.info("INSIDE LOVController END METHOD LovDeleteLov ::");
		return model;
	}

	// 

	// SEARCHING THE PRODUCT..
	@RequestMapping(value = "/searchlov")
	public ModelAndView lovSearchLovByCondition(HttpServletRequest request, HttpServletResponse response){
		logger.info("INSIDE LOVController START METHOD lovSearchLovByCondition");
		ModelAndView model = new ModelAndView("lovs/lovsList");
		String lovProcess = request.getParameter("lovProcess");
		String lovName = request.getParameter("lovName");
		if(lovProcess != null || lovName != null){
			model.addObject("lovsList" , lOVService.lovSearchLovByCondition(lovProcess, lovName));
		}else{
			model.addObject("lovsList", lOVService.getLovDetails());
		}
		HashMap<String, String> lovProcessWithName = lOVService.getLovProcessWithName();
		model.addObject("lovProcessWithNameList", lovProcessWithName);

		List<String> lovNames = lOVService.getLovNameList();
		model.addObject("lovName", lovNames);
		logger.info("INSIDE LOVController END METHOD lovSearchLovByCondition");
		return model;
	}	
}
