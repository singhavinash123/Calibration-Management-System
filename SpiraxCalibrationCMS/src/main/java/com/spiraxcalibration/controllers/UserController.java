package com.spiraxcalibration.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itextpdf.text.log.SysoCounter;
import com.spiraxcalibration.models.UserData;
import com.spiraxcalibration.services.UserIService;

@Controller
public class UserController {
	Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	UserIService userIService;

	@RequestMapping(value="/userMaintenance" , method=RequestMethod.GET)
	public ModelAndView userGetUsersDetails(){
		logger.info("INSIDE UserController START METHOD userGetUsersDetails ::");
		ModelAndView  model = new ModelAndView("users/userDetails");
		List<UserData>  userDataList = userIService.userGetUsersDetails();
		model.addObject("userList",userDataList);
		List<String> empCodeList = userIService.getEmpcodeList();
		//List<String> userRoleList = userIService.getUserRoleList();
		List<String> userDepartmentList = userIService.getDepartmentList();
		model.addObject("empCodeList",empCodeList);
		//model.addObject("userRoleList",userRoleList);
		model.addObject("departList",userDepartmentList);
		logger.info("INSIDE UserController END METHOD userGetUsersDetails ::");
		return model;
	}

	@RequestMapping(value = "/addUser" , method = RequestMethod.GET)
	public ModelAndView userAddUser(){
		logger.info("INSIDE UserController START METHOD userAddUser");
		ModelAndView model = new ModelAndView("users/userUpdateOrDelete");
		UserData  userData = new UserData();
		model.addObject("userData",userData);
		// check in future..
		List<String> userRoleList = userIService.userGetUserRoles();
		model.addObject("userRolesLookUp",userRoleList);
		
//		Set<String> userRoleList = getUserRoles();
//		model.addObject("userRolesLookUp",userRoleList);

		List<String> userDeptList = userIService.userGetUserDept();
		model.addObject("userDepartmentList",userDeptList);
		model.addObject("addOrUpdate","Add User");
		model.addObject("addORupdatePrheading","Add User");
		logger.info("INSIDE UserController END METHOD userAddUser");
		return model;
	}


	@RequestMapping(value = "/saveRole" , method = RequestMethod.POST)
	public ModelAndView userUpdateUserRoleDetails(@ModelAttribute("userData") @Valid UserData userData, BindingResult result , HttpServletRequest request , RedirectAttributes redir){
		logger.info("INSIDE UserController START METHOD userSaveUserDetails");

		ModelAndView  model1 = new ModelAndView("users/addUserRole");
		ModelAndView  model2 = new ModelAndView("redirect:/userMaintenance");
		
		List<String> userRoleList = userIService.userGetUserRolesForUpdate();
		model1.addObject("userRoleList",userRoleList);
		
		if (result.hasErrors()) {
			if(userData.getUserUserId() != null){
				model1.addObject("addOrUpdate","Add Role");
				model1.addObject("addORupdatePrheading","Add Role");
			}else{
				model1.addObject("addORupdatePrheading","Add Role");
			}
			return model1;
		}else{
			if(userData != null && userData.getUserUserId() != null){
				int num = userIService.userUpdateUserRoleProduct(userData);
				if(num == -1){
					model2.addObject("addORupdatePrheading","Add Role");
					redir.addFlashAttribute("error","User "+userData.getUseUserName()+" with "+userData.getUserUserRoleName()+" role already exist");
					return model2;
				}if(num == -2){
					model2.addObject("addORupdatePrheading","Add Role");
					redir.addFlashAttribute("error","User role  '" +userData.getUserUserRoleName()+"'  already exist");
					return model2;
				}
				model2.addObject("addORupdatePrheading","Add Role");
				redir.addFlashAttribute("msg","User "+userData.getUseUserName()+" successfully Updated");
				return model2;
			}
		}
		logger.info("INSIDE UserController END METHOD userSaveUserDetails");
		return model2;
	}

	@RequestMapping(value = "/saveUser" , method = RequestMethod.POST)
	public ModelAndView userSaveUserDetails(@ModelAttribute("userData") @Valid UserData userData, BindingResult result , HttpServletRequest request , RedirectAttributes redir){
		logger.info("INSIDE UserController START METHOD userSaveUserDetails");

		ModelAndView  model1 = new ModelAndView("users/userUpdateOrDelete");
		ModelAndView  model2 = new ModelAndView("redirect:/userMaintenance");
		List<String> userRoleList = userIService.userGetUserRoles();
		model1.addObject("userRolesLookUp",userRoleList);
		List<String> userDeptList = userIService.userGetUserDept();
		model1.addObject("userDepartmentList",userDeptList);

		model1.addObject("addOrUpdate","Add User");
		model2.addObject("addOrUpdate","Update User");
		if (result.hasErrors()) {
			if(userData.getUserUserId() != null){
				model1.addObject("addORupdatePrheading","Update User");
				model1.addObject("addOrUpdate","Update User");
			}else{
				model1.addObject("addORupdatePrheading","Add User");
			}
			return model1;
		}else{
			if(userData != null && userData.getUserUserId() != null){
				// UPDATING...
				int num = userIService.userUpdateProduct(userData);
				if(num > 0){
					model2.addObject("addORupdatePrheading","Update User");
					redir.addFlashAttribute("msg","User "+userData.getUseUserName()+" successfully Updated");
					return model2;
				}
			}else{
				//  ADDING..
				int num = userIService.userAddUser(userData);
				model2.addObject("addORupdatePrheading","Add User");
				// num == 0 for already exist data...
				if(num == -1){
					model1.addObject("alreadyExist","UserName Already Exist!!");
					model1.addObject("addORupdatePrheading","Add User");
					return model1;
				}else if(num == -2){
					model1.addObject("alreadyExist","UserName Already Exist!!");
					model1.addObject("addORupdatePrheading","Add User");
					return model1;
				}else if(num == -3){
					model1.addObject("alreadyExist","Employee Code Already Exist!!");
					model1.addObject("addORupdatePrheading","Add User");
					return model1;
				}else if(num == -4){
					model1.addObject("alreadyExist","UserRole "+userData.getUserUserRoleName()+" Already Exist!!");
					model1.addObject("addORupdatePrheading","Add User");
					return model1;
				}
				else if(num > 0 ){
					redir.addFlashAttribute("msg","User "+userData.getUseUserName()+" successfully Added");
					return model2;
				}
			}
		}
		logger.info("INSIDE UserController END METHOD userSaveUserDetails");
		return model1; 
	}

	@RequestMapping(value = "/deleteUser/{userId}" , method = RequestMethod.GET)
	public ModelAndView userDeleteUser(@PathVariable(value="userId") Integer userId , RedirectAttributes redir){
		logger.info("INSIDE UserController START METHOD userDeleteUser ::");
		ModelAndView model = new ModelAndView("redirect:/userMaintenance");
		int num = userIService.userDeleteUserRole(userId);
		if(num > 0){
			redir.addFlashAttribute("msg","User deleted successfully!!");
		}
		logger.info("INSIDE UserController END METHOD userDeleteUser ::");
		return model;
	}
	
	
	@RequestMapping(value = "/deleteRole" , method = RequestMethod.GET)
	public ModelAndView userDeleteUserRole(HttpServletRequest request , HttpServletResponse response, RedirectAttributes redir){
		logger.info("INSIDE UserController START METHOD userDeleteUserRole ::");
		ModelAndView model = new ModelAndView("redirect:/userMaintenance");
		String userId = request.getParameter("user_id");
		String userRole = request.getParameter("user_role_id");
		int num = userIService.deleteRoleByUserId(userId,userRole);
		if(num == -1){
			redir.addFlashAttribute("error","Atleast one user should have one role!!");
		}if(num == -2){
			redir.addFlashAttribute("msg","User role deleted successfully!!");
		}if(num == -3){
			redir.addFlashAttribute("error","Atleast one user role 'ADMIN' should exist!!");
		}
		logger.info("INSIDE UserController START METHOD userDeleteUserRole ::");
		return model;
	}
	
	
	@RequestMapping(value = "/register_Email_For_Notification" , method = RequestMethod.GET)
	public ModelAndView userRegisterForEmailNotification(){
		logger.info("INSIDE UserController START METHOD userRegisterForEmailNotification ::");
		ModelAndView model = new ModelAndView("users/registerMail");
		logger.info("INSIDE UserController END METHOD userRegisterForEmailNotification ::");
		return model;
	}
	
	@RequestMapping(value = "/saveRegisterMail" , method = RequestMethod.POST)
	public ModelAndView saveUserRegisterForEmailNotification(HttpServletRequest request , HttpServletResponse response,RedirectAttributes redir){
		logger.info("INSIDE UserController START METHOD userRegisterForEmailNotification ::");
		ModelAndView model = new ModelAndView("redirect:/register_Email_For_Notification");
		String userName =  request.getParameter("emailname");
		String password = request.getParameter("confirm_password");
		int num  = userIService.saveRegisterEmailForNotification(userName,password);
		if(num > 0 ){
			redir.addFlashAttribute("msg","Email created successfully");
		}
		logger.info("INSIDE UserController END METHOD userRegisterForEmailNotification ::");
		return model;
	}
	

	// UPDATING THE USER..
	@RequestMapping(value = "/updateUser/{userId}" , method = RequestMethod.GET)
	public ModelAndView userUpdateUser(@PathVariable("userId") Integer userId){
		logger.info("INSIDE UserController START METHOD userUpdateUser");
		ModelAndView model = new ModelAndView("users/userUpdateOrDelete");
		UserData  userData = userIService.userFindUserByProdId(userId);
		List<String> userRoleList = userIService.userGetUserRoles();
		model.addObject("userRolesLookUp",userRoleList);
		List<String> userDeptList = userIService.userGetUserDept();
		model.addObject("userDepartmentList",userDeptList);
		model.addObject("userData",userData);
		model.addObject("addOrUpdate","Update User");
		model.addObject("addORupdatePrheading","Update User");
		model.addObject("disabled",true);
		logger.info("INSIDE UserController END METHOD userUpdateUser");
		return model;
	}



	// UPDATING THE USER..
	@RequestMapping(value = "/addRole/{userId}" , method = RequestMethod.GET)
	public ModelAndView userAddUpdateUser(@PathVariable("userId") Integer userId){
		logger.info("INSIDE UserController START METHOD userUpdateUser");
		ModelAndView model = new ModelAndView("users/addUserRole");
		UserData  userData = userIService.userFindUserByProdId(userId);
		List<String> userRoleList = userIService.userGetUserRolesForUpdate();
		model.addObject("userRoleList",userRoleList);
		model.addObject("userData",userData);
		model.addObject("addOrUpdate","Add Role");
		model.addObject("addORupdatePrheading","Add Role");
		model.addObject("disabled",true);
		logger.info("INSIDE UserController END METHOD userUpdateUser");
		return model;
	}
	@RequestMapping(value = "/searchUser")
	public ModelAndView userSearchUserByCondition(HttpServletRequest request, HttpServletResponse response){
		logger.info("INSIDE UserController START METHOD userSearchUserByCondition");
		ModelAndView model = new ModelAndView("users/userDetails");
		String empCode = request.getParameter("empcode");
		String department = request.getParameter("depart");
		//String userRole = request.getParameter("userrole");
		
		String userRole = "";

		if(empCode != null || department != null || userRole != null){
			if(empCode.isEmpty() && department.isEmpty() && userRole.isEmpty()){
				model.addObject("userList", userIService.userGetUsersDetails());
			}else{
				model.addObject("userList" , userIService.userSearchByCondition(empCode, department, userRole));
			}
		}else{
			model.addObject("userList", userIService.userGetUsersDetails());
		}
		List<String> empCodeList = userIService.getEmpcodeList();
		List<String> userRoleList = userIService.getUserRoleList();
		List<String> userDepartmentList = userIService.getDepartmentList();
		model.addObject("empCodeList",empCodeList);
		model.addObject("userRoleList",userRoleList);
		model.addObject("departList",userDepartmentList);
		logger.info("INSIDE UserController END METHOD userSearchUserByCondition");
		return model;
	}	
}
