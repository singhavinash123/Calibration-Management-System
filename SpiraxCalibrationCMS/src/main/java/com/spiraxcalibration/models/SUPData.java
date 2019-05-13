package com.spiraxcalibration.models;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class SUPData {
	
	private Integer supSupId;
	
	@NotBlank(message="Supplier name is required")
	private String supSupplierName;
	
	private String supRegistrationNum;
	
	@Email(message="Not a valid email formate number is required")
	private String supEmailId;
	
	private String supSupplierServiceType;
	
	@NotBlank(message="Supplier number is required")
	private String supSupplierNumber;
	private String supContact;
	private String supStatus;
	private String supAddressLine1;
	private String supAddressLine2;
	private String supAddressLine3;
	private String supAddressLine4;
	private String supAddressLine5;
	private String supAddressLine6;
	private String supFullAddress;
	
	private String supCreatedby;
	private String supCreatedDate;
	
	private String supUpdatedBy;
	private String supUpdatedDate;
	
	/**
	 * @return the supSupplierServiceType
	 */
	public String getSupSupplierServiceType() {
		return supSupplierServiceType;
	}
	/**
	 * @param supSupplierServiceType the supSupplierServiceType to set
	 */
	public void setSupSupplierServiceType(String supSupplierServiceType) {
		this.supSupplierServiceType = supSupplierServiceType;
	}
	/**
	 * @return the supCreatedby
	 */
	public String getSupCreatedby() {
		return supCreatedby;
	}
	/**
	 * @param supCreatedby the supCreatedby to set
	 */
	public void setSupCreatedby(String supCreatedby) {
		this.supCreatedby = supCreatedby;
	}
	/**
	 * @return the supCreatedDate
	 */
	public String getSupCreatedDate() {
		return supCreatedDate;
	}
	/**
	 * @param supCreatedDate the supCreatedDate to set
	 */
	public void setSupCreatedDate(String supCreatedDate) {
		this.supCreatedDate = supCreatedDate;
	}
	/**
	 * @return the supUpdatedBy
	 */
	public String getSupUpdatedBy() {
		return supUpdatedBy;
	}
	/**
	 * @param supUpdatedBy the supUpdatedBy to set
	 */
	public void setSupUpdatedBy(String supUpdatedBy) {
		this.supUpdatedBy = supUpdatedBy;
	}
	/**
	 * @return the supUpdatedDate
	 */
	public String getSupUpdatedDate() {
		return supUpdatedDate;
	}
	/**
	 * @param supUpdatedDate the supUpdatedDate to set
	 */
	public void setSupUpdatedDate(String supUpdatedDate) {
		this.supUpdatedDate = supUpdatedDate;
	}
	/**
	 * @return the supFullAddress
	 */
	public String getSupFullAddress() {
		return supFullAddress;
	}
	/**
	 * @param supFullAddress the supFullAddress to set
	 */
	public void setSupFullAddress(String supFullAddress) {
		this.supFullAddress = supFullAddress;
	}
	/**
	 * @return the supSupId
	 */
	public Integer getSupSupId() {
		return supSupId;
	}
	/**
	 * @param supSupId the supSupId to set
	 */
	public void setSupSupId(Integer supSupId) {
		this.supSupId = supSupId;
	}
	/**
	 * @return the supSupplierName
	 */
	public String getSupSupplierName() {
		return supSupplierName;
	}
	/**
	 * @param supSupplierName the supSupplierName to set
	 */
	public void setSupSupplierName(String supSupplierName) {
		this.supSupplierName = supSupplierName;
	}
	/**
	 * @return the supRegistrationNum
	 */
	public String getSupRegistrationNum() {
		return supRegistrationNum;
	}
	/**
	 * @param supRegistrationNum the supRegistrationNum to set
	 */
	public void setSupRegistrationNum(String supRegistrationNum) {
		this.supRegistrationNum = supRegistrationNum;
	}
	/**
	 * @return the supEmailId
	 */
	public String getSupEmailId() {
		return supEmailId;
	}
	/**
	 * @param supEmailId the supEmailId to set
	 */
	public void setSupEmailId(String supEmailId) {
		this.supEmailId = supEmailId;
	}
	/**
	 * @return the supSupplierNumber
	 */
	public String getSupSupplierNumber() {
		return supSupplierNumber;
	}
	/**
	 * @param supSupplierNumber the supSupplierNumber to set
	 */
	public void setSupSupplierNumber(String supSupplierNumber) {
		this.supSupplierNumber = supSupplierNumber;
	}
	/**
	 * @return the supContact
	 */
	public String getSupContact() {
		return supContact;
	}
	/**
	 * @param supContact the supContact to set
	 */
	public void setSupContact(String supContact) {
		this.supContact = supContact;
	}
	/**
	 * @return the supStatus
	 */
	public String getSupStatus() {
		return supStatus;
	}
	/**
	 * @param supStatus the supStatus to set
	 */
	public void setSupStatus(String supStatus) {
		this.supStatus = supStatus;
	}
	/**
	 * @return the supAddressLine1
	 */
	public String getSupAddressLine1() {
		return supAddressLine1;
	}
	/**
	 * @param supAddressLine1 the supAddressLine1 to set
	 */
	public void setSupAddressLine1(String supAddressLine1) {
		this.supAddressLine1 = supAddressLine1;
	}
	/**
	 * @return the supAddressLine2
	 */
	public String getSupAddressLine2() {
		return supAddressLine2;
	}
	/**
	 * @param supAddressLine2 the supAddressLine2 to set
	 */
	public void setSupAddressLine2(String supAddressLine2) {
		this.supAddressLine2 = supAddressLine2;
	}
	/**
	 * @return the supAddressLine3
	 */
	public String getSupAddressLine3() {
		return supAddressLine3;
	}
	/**
	 * @param supAddressLine3 the supAddressLine3 to set
	 */
	public void setSupAddressLine3(String supAddressLine3) {
		this.supAddressLine3 = supAddressLine3;
	}
	/**
	 * @return the supAddressLine4
	 */
	public String getSupAddressLine4() {
		return supAddressLine4;
	}
	/**
	 * @param supAddressLine4 the supAddressLine4 to set
	 */
	public void setSupAddressLine4(String supAddressLine4) {
		this.supAddressLine4 = supAddressLine4;
	}
	/**
	 * @return the supAddressLine5
	 */
	public String getSupAddressLine5() {
		return supAddressLine5;
	}
	/**
	 * @param supAddressLine5 the supAddressLine5 to set
	 */
	public void setSupAddressLine5(String supAddressLine5) {
		this.supAddressLine5 = supAddressLine5;
	}
	/**
	 * @return the supAddressLine6
	 */
	public String getSupAddressLine6() {
		return supAddressLine6;
	}
	/**
	 * @param supAddressLine6 the supAddressLine6 to set
	 */
	public void setSupAddressLine6(String supAddressLine6) {
		this.supAddressLine6 = supAddressLine6;
	}
}
