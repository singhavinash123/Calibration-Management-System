package com.spiraxcalibration.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserData {
	
	 	private Integer userUserId;
	 	
		@NotBlank(message = "Please provide your first name")
		private String  userFirstName;
		
		private String  userLastName;
		
		@NotBlank(message="User Name Required")
		private String  useUserName;
		
		private String  userUserRoleName;
		private int userUserRoleId;
		
		
		@Email(message = "Please provide a valid e-mail")
		@NotBlank(message = "Please provide an e-mail")
        private String  userEmailAddress;
		
		private String  userContactNumber;
		
		private String  userCreationDate;
		private String  userCreatedBy;
		private String  userLastUpdateDate;
		private String  userUpdatedBy;
		private String  userUpdateLogin;
		
		private String  userDepartment;
		
		@NotBlank(message = "Password must not be blank.")
		@Size(min = 3, max = 250, message = "Password must between {min} to {max} Characters.")
		private String  userPassWord;
		
		@NotBlank(message = "Password must not be blank.")
		@Size(min = 3, max = 250, message = "Password must between {min} to {max} Characters.")
		private String  userConfirmPassword;
		private Integer userRelatedId;
		private Integer userRelatedAccountId;
		private String userUserGrants;
		private String userAccessLevel;
		private String userDesignation;
		
		private String setResetToken;
		
		/**
		 * @return the userUserRoleId
		 */
		public int getUserUserRoleId() {
			return userUserRoleId;
		}
		/**
		 * @param userUserRoleId the userUserRoleId to set
		 */
		public void setUserUserRoleId(int userUserRoleId) {
			this.userUserRoleId = userUserRoleId;
		}
		/**
		 * @return the setResetToken
		 */
		public String getSetResetToken() {
			return setResetToken;
		}
		/**
		 * @param setResetToken the setResetToken to set
		 */
		public void setSetResetToken(String setResetToken) {
			this.setResetToken = setResetToken;
		}
		
		@NotNull(message="Employee Code must not be blank.")
		private String userEmpCode;
		
		private String userFullName;
		/**
		 * @return the userFullName
		 */
		public String getUserFullName() {
			return userFullName;
		}
		/**
		 * @param userFullName the userFullName to set
		 */
		public void setUserFullName(String userFullName) {
			this.userFullName = userFullName;
		}
		/**
		 * @return the userDepartment
		 */
		public String getUserDepartment() {
			return userDepartment;
		}
		/**
		 * @param userDepartment the userDepartment to set
		 */
		public void setUserDepartment(String userDepartment) {
			this.userDepartment = userDepartment;
		}
		/**
		 * @return the userEmpCode
		 */
		public String getUserEmpCode() {
			return userEmpCode;
		}
		/**
		 * @param userEmpCode the userEmpCode to set
		 */
		public void setUserEmpCode(String userEmpCode) {
			this.userEmpCode = userEmpCode;
		}
		/**
		 * @return the userConfirmPassword
		 */
		public String getUserConfirmPassword() {
			return userConfirmPassword;
		}
		/**
		 * @param userConfirmPassword the userConfirmPassword to set
		 */
		public void setUserConfirmPassword(String userConfirmPassword) {
			this.userConfirmPassword = userConfirmPassword;
		}
		/**
		 * @return the userUserId
		 */
		public Integer getUserUserId() {
			return userUserId;
		}
		/**
		 * @param userUserId the userUserId to set
		 */
		public void setUserUserId(Integer userUserId) {
			this.userUserId = userUserId;
		}
		public String getUserFirstName() {
			return userFirstName;
		}
		/**
		 * @param userFirstName the userFirstName to set
		 */
		public void setUserFirstName(String userFirstName) {
			this.userFirstName = userFirstName;
		}
		/**
		 * @return the userLastName
		 */
		public String getUserLastName() {
			return userLastName;
		}
		/**
		 * @param userLastName the userLastName to set
		 */
		public void setUserLastName(String userLastName) {
			this.userLastName = userLastName;
		}
		/**
		 * @return the useUserName
		 */
		public String getUseUserName() {
			return useUserName;
		}
		/**
		 * @param useUserName the useUserName to set
		 */
		public void setUseUserName(String useUserName) {
			this.useUserName = useUserName;
		}
		/**
		 * @return the userUserRoleName
		 */
		public String getUserUserRoleName() {
			return userUserRoleName;
		}
		/**
		 * @param userUserRoleName the userUserRoleName to set
		 */
		public void setUserUserRoleName(String userUserRoleName) {
			this.userUserRoleName = userUserRoleName;
		}
		/**
		 * @return the userEmailAddress
		 */
		public String getUserEmailAddress() {
			return userEmailAddress;
		}
		/**
		 * @param userEmailAddress the userEmailAddress to set
		 */
		public void setUserEmailAddress(String userEmailAddress) {
			this.userEmailAddress = userEmailAddress;
		}
		/**
		 * @return the userContactNumber
		 */
		public String getUserContactNumber() {
			return userContactNumber;
		}
		/**
		 * @param userContactNumber the userContactNumber to set
		 */
		public void setUserContactNumber(String userContactNumber) {
			this.userContactNumber = userContactNumber;
		}
		/**
		 * @return the userCreationDate
		 */
		public String getUserCreationDate() {
			return userCreationDate;
		}
		/**
		 * @param userCreationDate the userCreationDate to set
		 */
		public void setUserCreationDate(String userCreationDate) {
			this.userCreationDate = userCreationDate;
		}
		/**
		 * @return the userCreatedBy
		 */
		public String getUserCreatedBy() {
			return userCreatedBy;
		}
		/**
		 * @param userCreatedBy the userCreatedBy to set
		 */
		public void setUserCreatedBy(String userCreatedBy) {
			this.userCreatedBy = userCreatedBy;
		}
		/**
		 * @return the userLastUpdateDate
		 */
		public String getUserLastUpdateDate() {
			return userLastUpdateDate;
		}
		/**
		 * @param userLastUpdateDate the userLastUpdateDate to set
		 */
		public void setUserLastUpdateDate(String userLastUpdateDate) {
			this.userLastUpdateDate = userLastUpdateDate;
		}
		/**
		 * @return the userUpdatedBy
		 */
		public String getUserUpdatedBy() {
			return userUpdatedBy;
		}
		/**
		 * @param userUpdatedBy the userUpdatedBy to set
		 */
		public void setUserUpdatedBy(String userUpdatedBy) {
			this.userUpdatedBy = userUpdatedBy;
		}
		/**
		 * @return the userUpdateLogin
		 */
		public String getUserUpdateLogin() {
			return userUpdateLogin;
		}
		/**
		 * @param userUpdateLogin the userUpdateLogin to set
		 */
		public void setUserUpdateLogin(String userUpdateLogin) {
			this.userUpdateLogin = userUpdateLogin;
		}
		/**
		 * @return the userPassWord
		 */
		public String getUserPassWord() {
			return userPassWord;
		}
		/**
		 * @param userPassWord the userPassWord to set
		 */
		public void setUserPassWord(String userPassWord) {
			this.userPassWord = userPassWord;
		}
		/**
		 * @return the userRelatedId
		 */
		public Integer getUserRelatedId() {
			return userRelatedId;
		}
		/**
		 * @param userRelatedId the userRelatedId to set
		 */
		public void setUserRelatedId(Integer userRelatedId) {
			this.userRelatedId = userRelatedId;
		}
		/**
		 * @return the userRelatedAccountId
		 */
		public Integer getUserRelatedAccountId() {
			return userRelatedAccountId;
		}
		/**
		 * @param userRelatedAccountId the userRelatedAccountId to set
		 */
		public void setUserRelatedAccountId(Integer userRelatedAccountId) {
			this.userRelatedAccountId = userRelatedAccountId;
		}
		/**
		 * @return the userUserGrants
		 */
		public String getUserUserGrants() {
			return userUserGrants;
		}
		/**
		 * @param userUserGrants the userUserGrants to set
		 */
		public void setUserUserGrants(String userUserGrants) {
			this.userUserGrants = userUserGrants;
		}
		/**
		 * @return the userAccessLevel
		 */
		public String getUserAccessLevel() {
			return userAccessLevel;
		}
		/**
		 * @param userAccessLevel the userAccessLevel to set
		 */
		public void setUserAccessLevel(String userAccessLevel) {
			this.userAccessLevel = userAccessLevel;
		}
		/**
		 * @return the userDesignation
		 */
		public String getUserDesignation() {
			return userDesignation;
		}
		/**
		 * @param userDesignation the userDesignation to set
		 */
		public void setUserDesignation(String userDesignation) {
			this.userDesignation = userDesignation;
		}
		
}
