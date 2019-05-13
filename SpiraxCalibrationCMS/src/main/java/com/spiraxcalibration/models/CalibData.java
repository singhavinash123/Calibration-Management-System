package com.spiraxcalibration.models;

import javax.validation.constraints.NotBlank;

public class CalibData {

	private Integer calibId;
	private Integer calibProdId;
	private String  calibSerialNumber;
	private Integer calibCalibrationFrequency = 0;
	private String  calibCalibrationDate = "";
	private String  calibCalibrationReminderDate;
	private String  calibCalibrationDueDate;
	private String  calibCalibrationType;
	private String  calibCalibStatus;
	private String  calibDateOfCalibration;
	private String  calibInstrumentRange;
	private String  calibCalibrationCertificate;
	private String  calibCalibratedBy;
	private String  calibIdentificationNo;
	private String  calibInstrumentLocation;
	private  String calibResults;
	private  String calibVerifiedBy;
	private Integer calibCalibrationPhase;
	private String  calibAccountId;
	private String  calibCreationDate;
	private Integer calibCreatedBy;
	private String  calibLastUpdateDate;
	private Integer calibLastUpdatedBy;
	private Integer calibLastUpdateLogin;
	
	private String calibApprover1Name;
	private String calibApprover1Status;
	
	private String calibApprover1Comments;

	private String calibApprover2Name;
	private String calibApprover2Status;
	private String calibApprover2Comments;
	
	private String calibStatusFlag;
	private String calibStatusData;
	private Boolean calibStatusForExpired;
	
	private String AlertFrequency;
	private String calibSupplierName;
	private String calibSupplierNumber;
	private Integer calibSendForApprovalStatusFlag;
	private String calibAprroverMailId;
	
	private String calibApproverStatusColorFlag;
	
	private Integer calibPrPrId;
	
	/**
	 * @return the calibPrPrId
	 */
	public Integer getCalibPrPrId() {
		return calibPrPrId;
	}
	/**
	 * @param calibPrPrId the calibPrPrId to set
	 */
	public void setCalibPrPrId(Integer calibPrPrId) {
		this.calibPrPrId = calibPrPrId;
	}
	/**
	 * @return the calibSupplierNumber
	 */
	public String getCalibSupplierNumber() {
		return calibSupplierNumber;
	}
	/**
	 * @param calibSupplierNumber the calibSupplierNumber to set
	 */
	public void setCalibSupplierNumber(String calibSupplierNumber) {
		this.calibSupplierNumber = calibSupplierNumber;
	}
	/**
	 * @return the calibApproverStatusColorFlag
	 */
	public String getCalibApproverStatusColorFlag() {
		return calibApproverStatusColorFlag;
	}
	/**
	 * @param calibApproverStatusColorFlag the calibApproverStatusColorFlag to set
	 */
	public void setCalibApproverStatusColorFlag(String calibApproverStatusColorFlag) {
		this.calibApproverStatusColorFlag = calibApproverStatusColorFlag;
	}
	/**
	 * @return the calibAprroverMailId
	 */
	public String getCalibAprroverMailId() {
		return calibAprroverMailId;
	}
	/**
	 * @param calibAprroverMailId the calibAprroverMailId to set
	 */
	public void setCalibAprroverMailId(String calibAprroverMailId) {
		this.calibAprroverMailId = calibAprroverMailId;
	}
	/**
	 * @return the calibSendForApprovalStatusFlag
	 */
	public Integer getCalibSendForApprovalStatusFlag() {
		return calibSendForApprovalStatusFlag;
	}
	/**
	 * @param calibSendForApprovalStatusFlag the calibSendForApprovalStatusFlag to set
	 */
	public void setCalibSendForApprovalStatusFlag(Integer calibSendForApprovalStatusFlag) {
		this.calibSendForApprovalStatusFlag = calibSendForApprovalStatusFlag;
	}
	/**
	 * @return the calibSupplierName
	 */
	public String getCalibSupplierName() {
		return calibSupplierName;
	}
	/**
	 * @param calibSupplierName the calibSupplierName to set
	 */
	public void setCalibSupplierName(String calibSupplierName) {
		this.calibSupplierName = calibSupplierName;
	}
	/**
	 * @return the alertFrequency
	 */
	public String getAlertFrequency() {
		return AlertFrequency;
	}
	/**
	 * @param alertFrequency the alertFrequency to set
	 */
	public void setAlertFrequency(String alertFrequency) {
		AlertFrequency = alertFrequency;
	}
	/**
	 * @return the calibStatusForExpired
	 */
	public Boolean getCalibStatusForExpired() {
		return calibStatusForExpired;
	}
	/**
	 * @param calibStatusForExpired the calibStatusForExpired to set
	 */
	public void setCalibStatusForExpired(Boolean calibStatusForExpired) {
		this.calibStatusForExpired = calibStatusForExpired;
	}
	public String getCalibStatusData() {
		return calibStatusData;
	}
	/**
	 * @param calibStatusData the calibStatusData to set
	 */
	public void setCalibStatusData(String calibStatusData) {
		this.calibStatusData = calibStatusData;
	}
	/**
	 * @return the calibStatusFlag
	 */
	public String getCalibStatusFlag() {
		return calibStatusFlag;
	}
	/**
	 * @param calibStatusFlag the calibStatusFlag to set
	 */
	public void setCalibStatusFlag(String calibStatusFlag) {
		this.calibStatusFlag = calibStatusFlag;
	}
	/**
	 * @return the calibApprover1Name
	 */
	public String getCalibApprover1Name() {
		return calibApprover1Name;
	}
	/**
	 * @param calibApprover1Name the calibApprover1Name to set
	 */
	public void setCalibApprover1Name(String calibApprover1Name) {
		this.calibApprover1Name = calibApprover1Name;
	}
	/**
	 * @return the calibApprover2Name
	 */
	public String getCalibApprover2Name() {
		return calibApprover2Name;
	}
	/**
	 * @param calibApprover2Name the calibApprover2Name to set
	 */
	public void setCalibApprover2Name(String calibApprover2Name) {
		this.calibApprover2Name = calibApprover2Name;
	}
	/**
	 * @return the calibApprover1Status
	 */
	public String getCalibApprover1Status() {
		return calibApprover1Status;
	}
	/**
	 * @param calibApprover1Status the calibApprover1Status to set
	 */
	public void setCalibApprover1Status(String calibApprover1Status) {
		this.calibApprover1Status = calibApprover1Status;
	}
	/**
	 * @return the calibApprover1Comments
	 */
	public String getCalibApprover1Comments() {
		return calibApprover1Comments;
	}
	/**
	 * @param calibApprover1Comments the calibApprover1Comments to set
	 */
	public void setCalibApprover1Comments(String calibApprover1Comments) {
		this.calibApprover1Comments = calibApprover1Comments;
	}
	
	
	/**
	 * @return the calibApprover2Status
	 */
	public String getCalibApprover2Status() {
		return calibApprover2Status;
	}
	/**
	 * @param calibApprover2Status the calibApprover2Status to set
	 */
	public void setCalibApprover2Status(String calibApprover2Status) {
		this.calibApprover2Status = calibApprover2Status;
	}
	/**
	 * @return the calibApprover2Comments
	 */
	public String getCalibApprover2Comments() {
		return calibApprover2Comments;
	}
	/**
	 * @param calibApprover2Comments the calibApprover2Comments to set
	 */
	public void setCalibApprover2Comments(String calibApprover2Comments) {
		this.calibApprover2Comments = calibApprover2Comments;
	}
	/**
	 * @return the calibId
	 */
	public Integer getCalibId() {
		return calibId;
	}
	/**
	 * @param calibId the calibId to set
	 */
	public void setCalibId(Integer calibId) {
		this.calibId = calibId;
	}
	/**
	 * @return the calibProdId
	 */
	public Integer getCalibProdId() {
		return calibProdId;
	}
	/**
	 * @param calibProdId the calibProdId to set
	 */
	public void setCalibProdId(Integer calibProdId) {
		this.calibProdId = calibProdId;
	}
	/**
	 * @return the calibDateOfCalibration
	 */
	public String getCalibDateOfCalibration() {
		return calibDateOfCalibration;
	}
	/**
	 * @param calibDateOfCalibration the calibDateOfCalibration to set
	 */
	public void setCalibDateOfCalibration(String calibDateOfCalibration) {
		this.calibDateOfCalibration = calibDateOfCalibration;
	}
	/**
	 * @return the calibInstrumentRange
	 */
	public String getCalibInstrumentRange() {
		return calibInstrumentRange;
	}
	/**
	 * @param calibInstrumentRange the calibInstrumentRange to set
	 */
	public void setCalibInstrumentRange(String calibInstrumentRange) {
		this.calibInstrumentRange = calibInstrumentRange;
	}
	/**
	 * @return the calibCalibrationCertificate
	 */
	public String getCalibCalibrationCertificate() {
		return calibCalibrationCertificate;
	}
	/**
	 * @param calibCalibrationCertificate the calibCalibrationCertificate to set
	 */
	public void setCalibCalibrationCertificate(String calibCalibrationCertificate) {
		this.calibCalibrationCertificate = calibCalibrationCertificate;
	}
	/**
	 * @return the calibCalibratedBy
	 */
	public String getCalibCalibratedBy() {
		return calibCalibratedBy;
	}
	/**
	 * @param calibCalibratedBy the calibCalibratedBy to set
	 */
	public void setCalibCalibratedBy(String calibCalibratedBy) {
		this.calibCalibratedBy = calibCalibratedBy;
	}
	/**
	 * @return the calibCalibrationDueDate
	 */
	public String getCalibCalibrationDueDate() {
		return calibCalibrationDueDate;
	}
	/**
	 * @param calibCalibrationDueDate the calibCalibrationDueDate to set
	 */
	public void setCalibCalibrationDueDate(String calibCalibrationDueDate) {
		this.calibCalibrationDueDate = calibCalibrationDueDate;
	}
	/**
	 * @return the calibIdentificationNo
	 */
	public String getCalibIdentificationNo() {
		return calibIdentificationNo;
	}
	/**
	 * @param calibIdentificationNo the calibIdentificationNo to set
	 */
	public void setCalibIdentificationNo(String calibIdentificationNo) {
		this.calibIdentificationNo = calibIdentificationNo;
	}
	/**
	 * @return the calibInstrumentLocation
	 */
	public String getCalibInstrumentLocation() {
		return calibInstrumentLocation;
	}
	/**
	 * @param calibInstrumentLocation the calibInstrumentLocation to set
	 */
	public void setCalibInstrumentLocation(String calibInstrumentLocation) {
		this.calibInstrumentLocation = calibInstrumentLocation;
	}
	/**
	 * @return the calibResults
	 */
	public String getCalibResults() {
		return calibResults;
	}
	/**
	 * @param calibResults the calibResults to set
	 */
	public void setCalibResults(String calibResults) {
		this.calibResults = calibResults;
	}
	/**
	 * @return the calibVerifiedBy
	 */
	public String getCalibVerifiedBy() {
		return calibVerifiedBy;
	}
	/**
	 * @param calibVerifiedBy the calibVerifiedBy to set
	 */
	public void setCalibVerifiedBy(String calibVerifiedBy) {
		this.calibVerifiedBy = calibVerifiedBy;
	}
	/**
	 * @return the calibCalibrationPhase
	 */
	public Integer getCalibCalibrationPhase() {
		return calibCalibrationPhase;
	}
	/**
	 * @param calibCalibrationPhase the calibCalibrationPhase to set
	 */
	public void setCalibCalibrationPhase(Integer calibCalibrationPhase) {
		this.calibCalibrationPhase = calibCalibrationPhase;
	}
	/**
	 * @return the calibCalibStatus
	 */
	public String getCalibCalibStatus() {
		return calibCalibStatus;
	}
	/**
	 * @param calibCalibStatus the calibCalibStatus to set
	 */
	public void setCalibCalibStatus(String calibCalibStatus) {
		this.calibCalibStatus = calibCalibStatus;
	}
	/**
	 * @return the calibAccountId
	 */
	public String getCalibAccountId() {
		return calibAccountId;
	}
	/**
	 * @param calibAccountId the calibAccountId to set
	 */
	public void setCalibAccountId(String calibAccountId) {
		this.calibAccountId = calibAccountId;
	}
	/**
	 * @return the calibCreationDate
	 */
	public String getCalibCreationDate() {
		return calibCreationDate;
	}
	/**
	 * @param calibCreationDate the calibCreationDate to set
	 */
	public void setCalibCreationDate(String calibCreationDate) {
		this.calibCreationDate = calibCreationDate;
	}
	/**
	 * @return the calibCreatedBy
	 */
	public Integer getCalibCreatedBy() {
		return calibCreatedBy;
	}
	/**
	 * @param calibCreatedBy the calibCreatedBy to set
	 */
	public void setCalibCreatedBy(Integer calibCreatedBy) {
		this.calibCreatedBy = calibCreatedBy;
	}
	/**
	 * @return the calibLastUpdateDate
	 */
	public String getCalibLastUpdateDate() {
		return calibLastUpdateDate;
	}
	/**
	 * @param calibLastUpdateDate the calibLastUpdateDate to set
	 */
	public void setCalibLastUpdateDate(String calibLastUpdateDate) {
		this.calibLastUpdateDate = calibLastUpdateDate;
	}
	/**
	 * @return the calibLastUpdatedBy
	 */
	public Integer getCalibLastUpdatedBy() {
		return calibLastUpdatedBy;
	}
	/**
	 * @param calibLastUpdatedBy the calibLastUpdatedBy to set
	 */
	public void setCalibLastUpdatedBy(Integer calibLastUpdatedBy) {
		this.calibLastUpdatedBy = calibLastUpdatedBy;
	}
	/**
	 * @return the calibLastUpdateLogin
	 */
	public Integer getCalibLastUpdateLogin() {
		return calibLastUpdateLogin;
	}
	/**
	 * @param calibLastUpdateLogin the calibLastUpdateLogin to set
	 */
	public void setCalibLastUpdateLogin(Integer calibLastUpdateLogin) {
		this.calibLastUpdateLogin = calibLastUpdateLogin;
	}
	/**
	 * @return the calibCalibrationFrequency
	 */
	public Integer getCalibCalibrationFrequency() {
		return calibCalibrationFrequency;
	}
	/**
	 * @param calibCalibrationFrequency the calibCalibrationFrequency to set
	 */
	public void setCalibCalibrationFrequency(Integer calibCalibrationFrequency) {
		this.calibCalibrationFrequency = calibCalibrationFrequency;
	}
	/**
	 * @return the calibCalibrationDate
	 */
	public String getCalibCalibrationDate() {
		return calibCalibrationDate;
	}
	/**
	 * @param calibCalibrationDate the calibCalibrationDate to set
	 */
	public void setCalibCalibrationDate(String calibCalibrationDate) {
		this.calibCalibrationDate = calibCalibrationDate;
	}
	/**
	 * @return the calibCalibrationReminderDate
	 */
	public String getCalibCalibrationReminderDate() {
		return calibCalibrationReminderDate;
	}
	/**
	 * @param calibCalibrationReminderDate the calibCalibrationReminderDate to set
	 */
	public void setCalibCalibrationReminderDate(String calibCalibrationReminderDate) {
		this.calibCalibrationReminderDate = calibCalibrationReminderDate;
	}

	/**
	 * @return the calibCalibrationType
	 */
	public String getCalibCalibrationType() {
		return calibCalibrationType;
	}
	/**
	 * @param calibCalibrationType the calibCalibrationType to set
	 */
	public void setCalibCalibrationType(String calibCalibrationType) {
		this.calibCalibrationType = calibCalibrationType;
	}
	/**
	 * @return the calibSerialNumber
	 */
	public String getCalibSerialNumber() {
		return calibSerialNumber;
	}
	/**
	 * @param calibSerialNumber the calibSerialNumber to set
	 */
	public void setCalibSerialNumber(String calibSerialNumber) {
		this.calibSerialNumber = calibSerialNumber;
	}
	/*
	 Calib_ID	int(11)
PROD_ID	int(11)
Date_of_Calibration	date
Instrument_Range	varchar(360)
Calibration_Certificate	blob
Calibrated_By	varchar(360)
Calibration_Due_Date	date
Identification_No	varchar(360)
Instrument_Location	varchar(360)
Results	varchar(360)
Verified_By	varchar(360)
Calibration_phase	int(11)
Calib_Status	varchar(100)
Account_ID	int(11)
creation_date	date
created_by	int(11)
last_update_date	date
last_updated_by	int(11)
last_update_login	int(11)
	 */
}
