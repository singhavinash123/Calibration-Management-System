package com.spiraxcalibration.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class PrData {
	
	@Size(min = 2, max = 30)
	private String prSerialNo;
	
	@Size(message="identityfication number should be greater than 1 and less than 30")
	private String prIdentificationNo;
	
	@NotBlank(message="Equipment description can't be empty")
	private String prDescription;
	
	@NotBlank(message="Equipment status cant be empty")
	private String prEquipmentStatus;
	
	private String prEquipmentType;
	private String prEquipmentDescription;
	
	private String prDueDate;
	private String prCalibrationDate;
	private String prCalibrationFrequecyD;
	private String prCalibrationFrequecy;
	private  String prBorrower;
	private String prEquipmentLocation;
	private String prCalibrationAgency;
	private String prCategories;
	private Integer prLeastCount;
	private String prPRODRange;
	private Integer prProdId;
	private String prCalibrationReminderDate;
	private String prCurrentEQDate;
	private String prDaysPending;
	private String prCALIBStatus;
	private String prMake;
	private String prModel;
	private String prCalibrationStd;
	private String prsupplierMeasuredResult;
	private String prcalibratedBy;
	private String prResult;
	private String  prVerifiedBy;
	private  String prInstrumentLineItem;
	private String  prInstrumentStatus; 
	private String  prCreationDate;
	private Integer prCreatedBy;
	private String  prLastUpdateDate;
	private Integer prLastUpdatedBy;
	private Integer prLastUpdateLogin;
	private String prAttribute1;
	private String prAttribute2;
	private String prAttribute3;
	private String prAttribute4;
	private String prAttribute5;
	private String prAttribute6;
	private String prAttribute7;
	private String prAttribute8;
	private String prAttribute9;
	private String prAttribute10;
	
	private String prSupplierName;
	private String prSupplierNumber;
	private Integer prNumber;
	
	private String colorStatusFlag;
	
	/**
	 * @return the colorStatusFlag
	 */
	public String getColorStatusFlag() {
		return colorStatusFlag;
	}

	/**
	 * @param colorStatusFlag the colorStatusFlag to set
	 */
	public void setColorStatusFlag(String colorStatusFlag) {
		this.colorStatusFlag = colorStatusFlag;
	}

	/**
	 * @return the prEquipmentDescription
	 */
	public String getPrEquipmentDescription() {
		return prEquipmentDescription;
	}

	/**
	 * @param prEquipmentDescription the prEquipmentDescription to set
	 */
	public void setPrEquipmentDescription(String prEquipmentDescription) {
		this.prEquipmentDescription = prEquipmentDescription;
	}

	/**
	 * @return the prNumber
	 */
	public Integer getPrNumber() {
		return prNumber;
	}

	/**
	 * @param prNumber the prNumber to set
	 */
	public void setPrNumber(Integer prNumber) {
		this.prNumber = prNumber;
	}

	/**
	 * @return the prSupplierNumber
	 */
	public String getPrSupplierNumber() {
		return prSupplierNumber;
	}

	/**
	 * @param prSupplierNumber the prSupplierNumber to set
	 */
	public void setPrSupplierNumber(String prSupplierNumber) {
		this.prSupplierNumber = prSupplierNumber;
	}

	/**
	 * @return the prSupplierName
	 */
	public String getPrSupplierName() {
		return prSupplierName;
	}

	/**
	 * @param prSupplierName the prSupplierName to set
	 */
	public void setPrSupplierName(String prSupplierName) {
		this.prSupplierName = prSupplierName;
	}

	/**
	 * @return the prEquipmentStatus
	 */
	public String getPrEquipmentStatus() {
		return prEquipmentStatus;
	}

	/**
	 * @param prEquipmentStatus the prEquipmentStatus to set
	 */
	public void setPrEquipmentStatus(String prEquipmentStatus) {
		this.prEquipmentStatus = prEquipmentStatus;
	}
	/**
	 * @return the prEquipmentType
	 */
	public String getPrEquipmentType() {
		return prEquipmentType;
	}
	
	/**
	 * @param prEquipmentType the prEquipmentType to set
	 */
	public void setPrEquipmentType(String prEquipmentType) {
		this.prEquipmentType = prEquipmentType;
	}
	
	/**
	 * @return the prCalibrationReminderDate
	 */
	public String getPrCalibrationReminderDate() {
		return prCalibrationReminderDate;
	}
	/**
	 * @param prCalibrationReminderDate the prCalibrationReminderDate to set
	 */
	public void setPrCalibrationReminderDate(String prCalibrationReminderDate) {
		this.prCalibrationReminderDate = prCalibrationReminderDate;
	}
	/**
	 * @return the prCurrentEQDate
	 */
	public String getPrCurrentEQDate() {
		return prCurrentEQDate;
	}
	/**
	 * @param prCurrentEQDate the prCurrentEQDate to set
	 */
	public void setPrCurrentEQDate(String prCurrentEQDate) {
		this.prCurrentEQDate = prCurrentEQDate;
	}
	/**
	 * @return the prDaysPending
	 */
	public String getPrDaysPending() {
		return prDaysPending;
	}
	/**
	 * @param prDaysPending the prDaysPending to set
	 */
	public void setPrDaysPending(String prDaysPending) {
		this.prDaysPending = prDaysPending;
	}
	/**
	 * @return the prCALIBStatus
	 */
	public String getPrCALIBStatus() {
		return prCALIBStatus;
	}
	/**
	 * @param prCALIBStatus the prCALIBStatus to set
	 */
	public void setPrCALIBStatus(String prCALIBStatus) {
		this.prCALIBStatus = prCALIBStatus;
	}
	/**
	 * @return the prMake
	 */
	public String getPrMake() {
		return prMake;
	}
	/**
	 * @param prMake the prMake to set
	 */
	public void setPrMake(String prMake) {
		this.prMake = prMake;
	}
	/**
	 * @return the prModel
	 */
	public String getPrModel() {
		return prModel;
	}
	/**
	 * @param prModel the prModel to set
	 */
	public void setPrModel(String prModel) {
		this.prModel = prModel;
	}
	/**
	 * @return the prCalibrationStd
	 */
	public String getPrCalibrationStd() {
		return prCalibrationStd;
	}
	/**
	 * @param prCalibrationStd the prCalibrationStd to set
	 */
	public void setPrCalibrationStd(String prCalibrationStd) {
		this.prCalibrationStd = prCalibrationStd;
	}
	/**
	 * @return the prsupplierMeasuredResult
	 */
	public String getPrsupplierMeasuredResult() {
		return prsupplierMeasuredResult;
	}
	/**
	 * @param prsupplierMeasuredResult the prsupplierMeasuredResult to set
	 */
	public void setPrsupplierMeasuredResult(String prsupplierMeasuredResult) {
		this.prsupplierMeasuredResult = prsupplierMeasuredResult;
	}
	/**
	 * @return the prcalibratedBy
	 */
	public String getPrcalibratedBy() {
		return prcalibratedBy;
	}
	/**
	 * @param prcalibratedBy the prcalibratedBy to set
	 */
	public void setPrcalibratedBy(String prcalibratedBy) {
		this.prcalibratedBy = prcalibratedBy;
	}
	/**
	 * @return the prResult
	 */
	public String getPrResult() {
		return prResult;
	}
	/**
	 * @param prResult the prResult to set
	 */
	public void setPrResult(String prResult) {
		this.prResult = prResult;
	}
	/**
	 * @return the prVerifiedBy
	 */
	public String getPrVerifiedBy() {
		return prVerifiedBy;
	}
	/**
	 * @param prVerifiedBy the prVerifiedBy to set
	 */
	public void setPrVerifiedBy(String prVerifiedBy) {
		this.prVerifiedBy = prVerifiedBy;
	}
	/**
	 * @return the prInstrumentLineItem
	 */
	public String getPrInstrumentLineItem() {
		return prInstrumentLineItem;
	}
	/**
	 * @param prInstrumentLineItem the prInstrumentLineItem to set
	 */
	public void setPrInstrumentLineItem(String prInstrumentLineItem) {
		this.prInstrumentLineItem = prInstrumentLineItem;
	}
	/**
	 * @return the prInstrumentStatus
	 */
	public String getPrInstrumentStatus() {
		return prInstrumentStatus;
	}
	/**
	 * @param prInstrumentStatus the prInstrumentStatus to set
	 */
	public void setPrInstrumentStatus(String prInstrumentStatus) {
		this.prInstrumentStatus = prInstrumentStatus;
	}
	/**
	 * @return the prCreationDate
	 */
	public String getPrCreationDate() {
		return prCreationDate;
	}
	/**
	 * @param prCreationDate the prCreationDate to set
	 */
	public void setPrCreationDate(String prCreationDate) {
		this.prCreationDate = prCreationDate;
	}
	/**
	 * @return the prCreatedBy
	 */
	public Integer getPrCreatedBy() {
		return prCreatedBy;
	}
	/**
	 * @param prCreatedBy the prCreatedBy to set
	 */
	public void setPrCreatedBy(Integer prCreatedBy) {
		this.prCreatedBy = prCreatedBy;
	}
	/**
	 * @return the prLastUpdateDate
	 */
	public String getPrLastUpdateDate() {
		return prLastUpdateDate;
	}
	/**
	 * @param prLastUpdateDate the prLastUpdateDate to set
	 */
	public void setPrLastUpdateDate(String prLastUpdateDate) {
		this.prLastUpdateDate = prLastUpdateDate;
	}
	/**
	 * @return the prLastUpdatedBy
	 */
	public Integer getPrLastUpdatedBy() {
		return prLastUpdatedBy;
	}
	/**
	 * @param prLastUpdatedBy the prLastUpdatedBy to set
	 */
	public void setPrLastUpdatedBy(Integer prLastUpdatedBy) {
		this.prLastUpdatedBy = prLastUpdatedBy;
	}
	/**
	 * @return the prLastUpdateLogin
	 */
	public Integer getPrLastUpdateLogin() {
		return prLastUpdateLogin;
	}
	/**
	 * @param prLastUpdateLogin the prLastUpdateLogin to set
	 */
	public void setPrLastUpdateLogin(Integer prLastUpdateLogin) {
		this.prLastUpdateLogin = prLastUpdateLogin;
	}
	/**
	 * @return the prAttribute1
	 */
	public String getPrAttribute1() {
		return prAttribute1;
	}
	/**
	 * @param prAttribute1 the prAttribute1 to set
	 */
	public void setPrAttribute1(String prAttribute1) {
		this.prAttribute1 = prAttribute1;
	}
	/**
	 * @return the prAttribute2
	 */
	public String getPrAttribute2() {
		return prAttribute2;
	}
	/**
	 * @param prAttribute2 the prAttribute2 to set
	 */
	public void setPrAttribute2(String prAttribute2) {
		this.prAttribute2 = prAttribute2;
	}
	/**
	 * @return the prAttribute3
	 */
	public String getPrAttribute3() {
		return prAttribute3;
	}
	/**
	 * @param prAttribute3 the prAttribute3 to set
	 */
	public void setPrAttribute3(String prAttribute3) {
		this.prAttribute3 = prAttribute3;
	}
	/**
	 * @return the prAttribute4
	 */
	public String getPrAttribute4() {
		return prAttribute4;
	}
	/**
	 * @param prAttribute4 the prAttribute4 to set
	 */
	public void setPrAttribute4(String prAttribute4) {
		this.prAttribute4 = prAttribute4;
	}
	/**
	 * @return the prAttribute5
	 */
	public String getPrAttribute5() {
		return prAttribute5;
	}
	/**
	 * @param prAttribute5 the prAttribute5 to set
	 */
	public void setPrAttribute5(String prAttribute5) {
		this.prAttribute5 = prAttribute5;
	}
	/**
	 * @return the prAttribute6
	 */
	public String getPrAttribute6() {
		return prAttribute6;
	}
	/**
	 * @param prAttribute6 the prAttribute6 to set
	 */
	public void setPrAttribute6(String prAttribute6) {
		this.prAttribute6 = prAttribute6;
	}
	/**
	 * @return the prAttribute7
	 */
	public String getPrAttribute7() {
		return prAttribute7;
	}
	/**
	 * @param prAttribute7 the prAttribute7 to set
	 */
	public void setPrAttribute7(String prAttribute7) {
		this.prAttribute7 = prAttribute7;
	}
	/**
	 * @return the prAttribute8
	 */
	public String getPrAttribute8() {
		return prAttribute8;
	}
	/**
	 * @param prAttribute8 the prAttribute8 to set
	 */
	public void setPrAttribute8(String prAttribute8) {
		this.prAttribute8 = prAttribute8;
	}
	/**
	 * @return the prAttribute9
	 */
	public String getPrAttribute9() {
		return prAttribute9;
	}
	/**
	 * @param prAttribute9 the prAttribute9 to set
	 */
	public void setPrAttribute9(String prAttribute9) {
		this.prAttribute9 = prAttribute9;
	}
	/**
	 * @return the prAttribute10
	 */
	public String getPrAttribute10() {
		return prAttribute10;
	}
	/**
	 * @param prAttribute10 the prAttribute10 to set
	 */
	public void setPrAttribute10(String prAttribute10) {
		this.prAttribute10 = prAttribute10;
	}
	/**
	 * @return the prDueDate
	 */
	public String getPrDueDate() {
		return prDueDate;
	}
	/**
	 * @param prDueDate the prDueDate to set
	 */
	public void setPrDueDate(String prDueDate) {
		this.prDueDate = prDueDate;
	}
	/**
	 * @return the prCalibrationDate
	 */
	public String getPrCalibrationDate() {
		return prCalibrationDate;
	}
	/**
	 * @param prCalibrationDate the prCalibrationDate to set
	 */
	public void setPrCalibrationDate(String prCalibrationDate) {
		this.prCalibrationDate = prCalibrationDate;
	}
	/**
	 * @return the prCalibrationFrequecyD
	 */
	public String getPrCalibrationFrequecyD() {
		return prCalibrationFrequecyD;
	}
	/**
	 * @param prCalibrationFrequecyD the prCalibrationFrequecyD to set
	 */
	public void setPrCalibrationFrequecyD(String prCalibrationFrequecyD) {
		this.prCalibrationFrequecyD = prCalibrationFrequecyD;
	}
	/**
	 * @return the prCalibrationFrequecy
	 */
	public String getPrCalibrationFrequecy() {
		return prCalibrationFrequecy;
	}
	/**
	 * @param prCalibrationFrequecy the prCalibrationFrequecy to set
	 */
	public void setPrCalibrationFrequecy(String prCalibrationFrequecy) {
		this.prCalibrationFrequecy = prCalibrationFrequecy;
	}
	/**
	 * @return the prBorrower
	 */
	public String getPrBorrower() {
		return prBorrower;
	}
	/**
	 * @param prBorrower the prBorrower to set
	 */
	public void setPrBorrower(String prBorrower) {
		this.prBorrower = prBorrower;
	}
	/**
	 * @return the prEquipmentLocation
	 */
	public String getPrEquipmentLocation() {
		return prEquipmentLocation;
	}
	/**
	 * @param prEquipmentLocation the prEquipmentLocation to set
	 */
	public void setPrEquipmentLocation(String prEquipmentLocation) {
		this.prEquipmentLocation = prEquipmentLocation;
	}
	/**
	 * @return the prCalibrationAgency
	 */
	public String getPrCalibrationAgency() {
		return prCalibrationAgency;
	}
	/**
	 * @param prCalibrationAgency the prCalibrationAgency to set
	 */
	public void setPrCalibrationAgency(String prCalibrationAgency) {
		this.prCalibrationAgency = prCalibrationAgency;
	}
	/**
	 * @return the prCategories
	 */
	public String getPrCategories() {
		return prCategories;
	}
	/**
	 * @param prCategories the prCategories to set
	 */
	public void setPrCategories(String prCategories) {
		this.prCategories = prCategories;
	}
	/**
	 * @return the prLeastCount
	 */
	public Integer getPrLeastCount() {
		return prLeastCount;
	}
	/**
	 * @param prLeastCount the prLeastCount to set
	 */
	public void setPrLeastCount(Integer prLeastCount) {
		this.prLeastCount = prLeastCount;
	}
	/**
	 * @return the prPRODRange
	 */
	public String getPrPRODRange() {
		return prPRODRange;
	}
	/**
	 * @param prPRODRange the prPRODRange to set
	 */
	public void setPrPRODRange(String prPRODRange) {
		this.prPRODRange = prPRODRange;
	}
	/**
	 * @return the prSerialNo
	 */
	public String getPrSerialNo() {
		return prSerialNo;
	}
	/**
	 * @param prSerialNo the prSerialNo to set
	 */
	public void setPrSerialNo(String prSerialNo) {
		this.prSerialNo = prSerialNo;
	}
	/**
	 * @return the prIdentificationNo
	 */
	public String getPrIdentificationNo() {
		return prIdentificationNo;
	}
	/**
	 * @param prIdentificationNo the prIdentificationNo to set
	 */
	public void setPrIdentificationNo(String prIdentificationNo) {
		this.prIdentificationNo = prIdentificationNo;
	}
	/**
	 * @return the prDescription
	 */
	public String getPrDescription() {
		return prDescription;
	}
	/**
	 * @param prDescription the prDescription to set
	 */
	public void setPrDescription(String prDescription) {
		this.prDescription = prDescription;
	}
	/**
	 * @return the prProdId
	 */
	public Integer getPrProdId() {
		return prProdId;
	}
	/**
	 * @param prProdId the prProdId to set
	 */
	public void setPrProdId(Integer prProdId) {
		this.prProdId = prProdId;
	}
	
	/*
	 * Due_Date	date
Calibration_Reminder_Date	date
Current_EQ_Date	date
Days_Pending	varchar(360)
CALIB_Status	varchar(100)
Make	varchar(360)
Model	varchar(360)
Calibration_Std	varchar(360)
supplier_measured_result	varchar(360)
calibrated_by	varchar(360)
Result	varchar(360)
Verified_by	varchar(100)
Instrument_Line_Item	varchar(360)
Instrument_Status	varchar(100)
Attribute1	varchar(360)
Attribute2	varchar(360)
Attribute3	varchar(360)
Attribute4	varchar(360)
Attribute5	varchar(360)
Attribute6	varchar(360)
Attribute7	varchar(360)
Attribute8	varchar(360)
Attribute9	varchar(360)
Attribute10	varchar(360)
creation_date	date
created_by	int(11)
last_update_date	date
last_updated_by	int(11)
last_update_login	int(11)
PROD_ID	int(11)
Description	varchar(1000)
Identification_no	varchar(360)
Serial_No	varchar(1000)
PROD_Range	varchar(360)
Least_Count	int(11)
Categories	varchar(360)
Calibration_Agency	varchar(360)
Equipment_Location	varchar(360)
Borrower	varchar(360)
Calibration_Frequecy	varchar(360)
Calibration_Frequecy_D	varchar(360)
Calibration_Date	date
Due_Date	date
	 * 
	 */
	
	
}
