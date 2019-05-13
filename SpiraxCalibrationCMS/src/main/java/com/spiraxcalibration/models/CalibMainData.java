package com.spiraxcalibration.models;


import javax.validation.constraints.NotBlank;

public class CalibMainData {

	private Integer mainEcId;
	private Integer mainEcCalibId;
	private Integer mainEcSupplierId;
	private String  mainIdentificationId;
	private String  mainOldIdentificationId;
	private String mainUpdatedIdentificationId;
	

	/***========Supplier Tracer ======***/
	@NotBlank(message="Please select supplier name")
	private String mainInstrumentRequestor;
	
	@NotBlank(message="Supplier name can't be empty")
	private String mainSupplierName;
	
	private String mainSupplierNumber;
	
	
	@NotBlank(message="Please enter purchase order")
	private String mainPurchaseOrder;
	
	@NotBlank(message="Please enter supplier invoice")
	private String mainInvoice;
	
	@NotBlank(message="Please enter unit price")
	private String mainUnitPrice;
	
	@NotBlank(message="Please enter asset code")
	private String mainAssetCode;
	
	@NotBlank(message="Please select supplier type")
	private String mainSupplierService;

	/***========Equipment Tracer ======***/
	@NotBlank(message="Please select make")
	private String mainMake;
	
	@NotBlank(message="Please select instrument gauge")
	private String mainInstrumentGauge;
	
	@NotBlank(message = "Please provide model number")
	private String mainModel;
	
	@NotBlank(message = "Please provide serial number")
	private String mainSerial;
	
	@NotBlank(message = "Please provide least count")
	private String mainLeast;
	
	@NotBlank(message = "Please select calibration standard")
	private String mainCalibrationStandard;
	
	@NotBlank(message = "Please provide forecast price")
	private String mainForcastPrice;
	
	@NotBlank(message = "Please provide guage range")
	private String mainInstrumentGaugeRange;

	/***========Calibration Tracer ======***/
	private String mainEquipmentType;
	private String  mainCalibrationType;
	private String mainAlertFrequency;
	private String mainCalibrationDate;
	private String mainDueDate;
	private String mainCalibrationStatus;
	private String mainCalibrationScrapedDate;
	private String mainReminderDate;
	
	@NotBlank(message = "Please select acceptance criteria")
	private String mainAcceptanceCriteria;
	
	@NotBlank(message = "Please provide calibration price")
	private String mainCalibrationPrice;
	

	/***========Certificate Tracer ======***/
	@NotBlank(message = "Please select location")
	private String mainLocation;
	
	@NotBlank(message = "Please select category")
	private String mainCalibrationCategory;
	
	@NotBlank(message = "Please select agency")
	private String mainCalibrationAgency;
	private String mainCalibrationFrequency;
	
	@NotBlank(message = "Please provide certificate no.")
	private String mainCalibrationCertificate;
	
	@NotBlank(message = "Please select certificate validatedBy")
	private String mainCertificateValidatedBy;
	
	@NotBlank(message = "Please select calibration result")
	private String mainCalibrationResult;
	
	@NotBlank(message = "Please select part code")
	private String mainPartCode;
	
	private String colorFlag ;
	private Boolean ifDueDatePassed;
	
	private String mainCreatedBy;
	private String mainCreatedDate;
	
	private String mainUpdatedBy;
	private String mainUpdatedDate;
	
	private String  mainCalibrationSource;
	
	/**
	 * @return the mainUpdatedIdentificationId
	 */
	public String getMainUpdatedIdentificationId() {
		return mainUpdatedIdentificationId;
	}
	/**
	 * @param mainUpdatedIdentificationId the mainUpdatedIdentificationId to set
	 */
	public void setMainUpdatedIdentificationId(String mainUpdatedIdentificationId) {
		this.mainUpdatedIdentificationId = mainUpdatedIdentificationId;
	}
	/**
	 * @return the mainCalibrationSource
	 */
	public String getMainCalibrationSource() {
		return mainCalibrationSource;
	}
	/**
	 * @param mainCalibrationSource the mainCalibrationSource to set
	 */
	public void setMainCalibrationSource(String mainCalibrationSource) {
		this.mainCalibrationSource = mainCalibrationSource;
	}
	/**
	 * @return the mainSupplierNumber
	 */
	public String getMainSupplierNumber() {
		return mainSupplierNumber;
	}
	/**
	 * @param mainSupplierNumber the mainSupplierNumber to set
	 */
	public void setMainSupplierNumber(String mainSupplierNumber) {
		this.mainSupplierNumber = mainSupplierNumber;
	}
	/**
	 * @return the mainOldIdentificationId
	 */
	public String getMainOldIdentificationId() {
		return mainOldIdentificationId;
	}
	/**
	 * @param mainOldIdentificationId the mainOldIdentificationId to set
	 */
	public void setMainOldIdentificationId(String mainOldIdentificationId) {
		this.mainOldIdentificationId = mainOldIdentificationId;
	}
	/**
	 * @return the mainCalibrationResult
	 */
	public String getMainCalibrationResult() {
		return mainCalibrationResult;
	}
	/**
	 * @param mainCalibrationResult the mainCalibrationResult to set
	 */
	public void setMainCalibrationResult(String mainCalibrationResult) {
		this.mainCalibrationResult = mainCalibrationResult;
	}
	/**
	 * @return the mainCreatedBy
	 */
	public String getMainCreatedBy() {
		return mainCreatedBy;
	}
	/**
	 * @param mainCreatedBy the mainCreatedBy to set
	 */
	public void setMainCreatedBy(String mainCreatedBy) {
		this.mainCreatedBy = mainCreatedBy;
	}
	/**
	 * @return the mainCreatedDate
	 */
	public String getMainCreatedDate() {
		return mainCreatedDate;
	}
	/**
	 * @param mainCreatedDate the mainCreatedDate to set
	 */
	public void setMainCreatedDate(String mainCreatedDate) {
		this.mainCreatedDate = mainCreatedDate;
	}
	/**
	 * @return the mainUpdatedBy
	 */
	public String getMainUpdatedBy() {
		return mainUpdatedBy;
	}
	/**
	 * @param mainUpdatedBy the mainUpdatedBy to set
	 */
	public void setMainUpdatedBy(String mainUpdatedBy) {
		this.mainUpdatedBy = mainUpdatedBy;
	}
	/**
	 * @return the mainUpdatedDate
	 */
	public String getMainUpdatedDate() {
		return mainUpdatedDate;
	}
	/**
	 * @param mainUpdatedDate the mainUpdatedDate to set
	 */
	public void setMainUpdatedDate(String mainUpdatedDate) {
		this.mainUpdatedDate = mainUpdatedDate;
	}
	/**
	 * @return the mainCalibrationPrice
	 */
	public String getMainCalibrationPrice() {
		return mainCalibrationPrice;
	}
	/**
	 * @param mainCalibrationPrice the mainCalibrationPrice to set
	 */
	public void setMainCalibrationPrice(String mainCalibrationPrice) {
		this.mainCalibrationPrice = mainCalibrationPrice;
	}
	/**
	 * @return the ifDueDatePassed
	 */
	public Boolean getIfDueDatePassed() {
		return ifDueDatePassed;
	}
	/**
	 * @param ifDueDatePassed the ifDueDatePassed to set
	 */
	public void setIfDueDatePassed(Boolean ifDueDatePassed) {
		this.ifDueDatePassed = ifDueDatePassed;
	}
	/**
	 * @return the colorFlag
	 */
	public String getColorFlag() {
		return colorFlag;
	}
	/**
	 * @param colorFlag the colorFlag to set
	 */
	public void setColorFlag(String colorFlag) {
		this.colorFlag = colorFlag;
	}
	/**
	 * @return the mainEcCalibId
	 */
	public Integer getMainEcCalibId() {
		return mainEcCalibId;
	}
	/**
	 * @return the mainInstrumentGaugeRange
	 */
	public String getMainInstrumentGaugeRange() {
		return mainInstrumentGaugeRange;
	}
	/**
	 * @param mainInstrumentGaugeRange the mainInstrumentGaugeRange to set
	 */
	public void setMainInstrumentGaugeRange(String mainInstrumentGaugeRange) {
		this.mainInstrumentGaugeRange = mainInstrumentGaugeRange;
	}
	/**
	 * @return the mainEcId
	 */
	public Integer getMainEcId() {
		return mainEcId;
	}
	/**
	 * @param mainEcId the mainEcId to set
	 */
	public void setMainEcId(Integer mainEcId) {
		this.mainEcId = mainEcId;
	}
	/**
	 * @return the mainEcCalibId
	 */
	public Integer calibMainData() {
		return mainEcCalibId;
	}
	/**
	 * @param mainEcCalibId the mainEcCalibId to set
	 */
	public void setMainEcCalibId(Integer mainEcCalibId) {
		this.mainEcCalibId = mainEcCalibId;
	}
	/**
	 * @return the mainEcSupplierId
	 */
	public Integer getMainEcSupplierId() {
		return mainEcSupplierId;
	}
	/**
	 * @param mainEcSupplierId the mainEcSupplierId to set
	 */
	public void setMainEcSupplierId(Integer mainEcSupplierId) {
		this.mainEcSupplierId = mainEcSupplierId;
	}
	
	/**
	 * @return the mainIdentificationId
	 */
	public String getMainIdentificationId() {
		return mainIdentificationId;
	}
	/**
	 * @param mainIdentificationId the mainIdentificationId to set
	 */
	public void setMainIdentificationId(String mainIdentificationId) {
		this.mainIdentificationId = mainIdentificationId;
	}
	/**
	 * @return the mainInstrumentRequestor
	 */
	public String getMainInstrumentRequestor() {
		return mainInstrumentRequestor;
	}
	/**
	 * @param mainInstrumentRequestor the mainInstrumentRequestor to set
	 */
	public void setMainInstrumentRequestor(String mainInstrumentRequestor) {
		this.mainInstrumentRequestor = mainInstrumentRequestor;
	}
	/**
	 * @return the mainSupplierName
	 */
	public String getMainSupplierName() {
		return mainSupplierName;
	}
	/**
	 * @param mainSupplierName the mainSupplierName to set
	 */
	public void setMainSupplierName(String mainSupplierName) {
		this.mainSupplierName = mainSupplierName;
	}
	/**
	 * @return the mainPurchaseOrder
	 */
	public String getMainPurchaseOrder() {
		return mainPurchaseOrder;
	}
	/**
	 * @param mainPurchaseOrder the mainPurchaseOrder to set
	 */
	public void setMainPurchaseOrder(String mainPurchaseOrder) {
		this.mainPurchaseOrder = mainPurchaseOrder;
	}
	/**
	 * @return the mainInvoice
	 */
	public String getMainInvoice() {
		return mainInvoice;
	}
	/**
	 * @param mainInvoice the mainInvoice to set
	 */
	public void setMainInvoice(String mainInvoice) {
		this.mainInvoice = mainInvoice;
	}
	
	/**
	 * @return the mainUnitPrice
	 */
	public String getMainUnitPrice() {
		return mainUnitPrice;
	}
	/**
	 * @param mainUnitPrice the mainUnitPrice to set
	 */
	public void setMainUnitPrice(String mainUnitPrice) {
		this.mainUnitPrice = mainUnitPrice;
	}
	public String getMainAssetCode() {
		return mainAssetCode;
	}
	/**
	 * @param mainAssetCode the mainAssetCode to set
	 */
	public void setMainAssetCode(String mainAssetCode) {
		this.mainAssetCode = mainAssetCode;
	}
	/**
	 * @return the mainSupplierService
	 */
	public String getMainSupplierService() {
		return mainSupplierService;
	}
	/**
	 * @param mainSupplierService the mainSupplierService to set
	 */
	public void setMainSupplierService(String mainSupplierService) {
		this.mainSupplierService = mainSupplierService;
	}
	/**
	 * @return the mainMake
	 */
	public String getMainMake() {
		return mainMake;
	}
	/**
	 * @param mainMake the mainMake to set
	 */
	public void setMainMake(String mainMake) {
		this.mainMake = mainMake;
	}
	/**
	 * @return the mainInstrumentGauge
	 */
	public String getMainInstrumentGauge() {
		return mainInstrumentGauge;
	}
	/**
	 * @param mainInstrumentGauge the mainInstrumentGauge to set
	 */
	public void setMainInstrumentGauge(String mainInstrumentGauge) {
		this.mainInstrumentGauge = mainInstrumentGauge;
	}
	/**
	 * @return the mainModel
	 */
	public String getMainModel() {
		return mainModel;
	}
	/**
	 * @param mainModel the mainModel to set
	 */
	public void setMainModel(String mainModel) {
		this.mainModel = mainModel;
	}
	/**
	 * @return the mainSerial
	 */
	public String getMainSerial() {
		return mainSerial;
	}
	/**
	 * @param mainSerial the mainSerial to set
	 */
	public void setMainSerial(String mainSerial) {
		this.mainSerial = mainSerial;
	}
	/**
	 * @return the mainLeast
	 */
	public String getMainLeast() {
		return mainLeast;
	}
	/**
	 * @param mainLeast the mainLeast to set
	 */
	public void setMainLeast(String mainLeast) {
		this.mainLeast = mainLeast;
	}
	/**
	 * @return the mainCalibrationStandard
	 */
	public String getMainCalibrationStandard() {
		return mainCalibrationStandard;
	}
	/**
	 * @param mainCalibrationStandard the mainCalibrationStandard to set
	 */
	public void setMainCalibrationStandard(String mainCalibrationStandard) {
		this.mainCalibrationStandard = mainCalibrationStandard;
	}
	/**
	 * @return the mainForcastPrice
	 */
	public String getMainForcastPrice() {
		return mainForcastPrice;
	}
	/**
	 * @param mainForcastPrice the mainForcastPrice to set
	 */
	public void setMainForcastPrice(String mainForcastPrice) {
		this.mainForcastPrice = mainForcastPrice;
	}
	/**
	 * @return the mainEquipmentType
	 */
	public String getMainEquipmentType() {
		return mainEquipmentType;
	}
	/**
	 * @param mainEquipmentType the mainEquipmentType to set
	 */
	public void setMainEquipmentType(String mainEquipmentType) {
		this.mainEquipmentType = mainEquipmentType;
	}
	/**
	 * @return the mainCalibrationType
	 */
	public String getMainCalibrationType() {
		return mainCalibrationType;
	}
	/**
	 * @param mainCalibrationType the mainCalibrationType to set
	 */
	public void setMainCalibrationType(String mainCalibrationType) {
		this.mainCalibrationType = mainCalibrationType;
	}
	/**
	 * @return the mainAlertFrequency
	 */
	public String getMainAlertFrequency() {
		return mainAlertFrequency;
	}
	/**
	 * @param mainAlertFrequency the mainAlertFrequency to set
	 */
	public void setMainAlertFrequency(String mainAlertFrequency) {
		this.mainAlertFrequency = mainAlertFrequency;
	}
	/**
	 * @return the mainCalibrationDate
	 */
	public String getMainCalibrationDate() {
		return mainCalibrationDate;
	}
	/**
	 * @param mainCalibrationDate the mainCalibrationDate to set
	 */
	public void setMainCalibrationDate(String mainCalibrationDate) {
		this.mainCalibrationDate = mainCalibrationDate;
	}
	/**
	 * @return the mainDueDate
	 */
	public String getMainDueDate() {
		return mainDueDate;
	}
	/**
	 * @param mainDueDate the mainDueDate to set
	 */
	public void setMainDueDate(String mainDueDate) {
		this.mainDueDate = mainDueDate;
	}
	/**
	 * @return the mainCalibrationStatus
	 */
	public String getMainCalibrationStatus() {
		return mainCalibrationStatus;
	}
	/**
	 * @param mainCalibrationStatus the mainCalibrationStatus to set
	 */
	public void setMainCalibrationStatus(String mainCalibrationStatus) {
		this.mainCalibrationStatus = mainCalibrationStatus;
	}
	/**
	 * @return the mainCalibrationScrapedDate
	 */
	public String getMainCalibrationScrapedDate() {
		return mainCalibrationScrapedDate;
	}
	/**
	 * @param mainCalibrationScrapedDate the mainCalibrationScrapedDate to set
	 */
	public void setMainCalibrationScrapedDate(String mainCalibrationScrapedDate) {
		this.mainCalibrationScrapedDate = mainCalibrationScrapedDate;
	}
	/**
	 * @return the mainReminderDate
	 */
	public String getMainReminderDate() {
		return mainReminderDate;
	}
	/**
	 * @param mainReminderDate the mainReminderDate to set
	 */
	public void setMainReminderDate(String mainReminderDate) {
		this.mainReminderDate = mainReminderDate;
	}
	/**
	 * @return the mainAcceptanceCriteria
	 */
	public String getMainAcceptanceCriteria() {
		return mainAcceptanceCriteria;
	}
	/**
	 * @param mainAcceptanceCriteria the mainAcceptanceCriteria to set
	 */
	public void setMainAcceptanceCriteria(String mainAcceptanceCriteria) {
		this.mainAcceptanceCriteria = mainAcceptanceCriteria;
	}
	/**
	 * @return the mainLocation
	 */
	public String getMainLocation() {
		return mainLocation;
	}
	/**
	 * @param mainLocation the mainLocation to set
	 */
	public void setMainLocation(String mainLocation) {
		this.mainLocation = mainLocation;
	}
	/**
	 * @return the mainCalibrationCategory
	 */
	public String getMainCalibrationCategory() {
		return mainCalibrationCategory;
	}
	/**
	 * @param mainCalibrationCategory the mainCalibrationCategory to set
	 */
	public void setMainCalibrationCategory(String mainCalibrationCategory) {
		this.mainCalibrationCategory = mainCalibrationCategory;
	}
	/**
	 * @return the mainCalibrationAgency
	 */
	public String getMainCalibrationAgency() {
		return mainCalibrationAgency;
	}
	/**
	 * @param mainCalibrationAgency the mainCalibrationAgency to set
	 */
	public void setMainCalibrationAgency(String mainCalibrationAgency) {
		this.mainCalibrationAgency = mainCalibrationAgency;
	}
	/**
	 * @return the mainCalibrationFrequency
	 */
	public String getMainCalibrationFrequency() {
		return mainCalibrationFrequency;
	}
	/**
	 * @param mainCalibrationFrequency the mainCalibrationFrequency to set
	 */
	public void setMainCalibrationFrequency(String mainCalibrationFrequency) {
		this.mainCalibrationFrequency = mainCalibrationFrequency;
	}
	/**
	 * @return the mainCalibrationCertificate
	 */
	public String getMainCalibrationCertificate() {
		return mainCalibrationCertificate;
	}
	/**
	 * @param mainCalibrationCertificate the mainCalibrationCertificate to set
	 */
	public void setMainCalibrationCertificate(String mainCalibrationCertificate) {
		this.mainCalibrationCertificate = mainCalibrationCertificate;
	}
	
	/**
	 * @return the mainCertificateValidatedBy
	 */
	public String getMainCertificateValidatedBy() {
		return mainCertificateValidatedBy;
	}
	/**
	 * @param mainCertificateValidatedBy the mainCertificateValidatedBy to set
	 */
	public void setMainCertificateValidatedBy(String mainCertificateValidatedBy) {
		this.mainCertificateValidatedBy = mainCertificateValidatedBy;
	}
	/**
	 * @return the mainPartCode
	 */
	public String getMainPartCode() {
		return mainPartCode;
	}
	/**
	 * @param mainPartCode the mainPartCode to set
	 */
	public void setMainPartCode(String mainPartCode) {
		this.mainPartCode = mainPartCode;
	}
	
}
