package com.spiraxcalibration.models;

import java.sql.Blob;

public class PRPData {
	private Integer pRpPRId;
	private Integer pRpCalibId;
	private Integer pRpProdId;
	private Integer pRpEquipId;
	private Integer pRpSupplierId;
	private String  pRpSupplierName;
	private String pRpSupplierNumber;
	private String  pRpApprover1;
	private String  pRpApprover2;
	
	private String pRpApprover1Status;
	private String pRpApprover1Comments;
	private String pRpApprover2Status;
	private String pRpApprover2Comments;
	
	private String pRpApprover1Date;
	private String pRpApprover2Date;

	private String pRpIdentificationNum;
	private String pRpGeneratePrDate;
	
	private String pRpEquipmentDescription;
	
	private String pRpApprover1Name;
	private String pRpApprover2Name;
	private String pRpPrStatus;
	
	private String pRpRaisePRMailId;
	
	private String pRpPdfName;
	private Blob pRpPdfData;
	
	private String pRpApprover1FullName;
	private String pRpApprover2FullName;
	private String pRpRaisedPrFullName;
	
	private String pRpPrNumber;
	
	private String pRpCreatedBy;
	private String pRpCreatedDate;
	
	private String pRpUpdatedBy;
	private String pRpUpdatedDate;
	
	private String pRpPartCode;
	private String pRpDeliveryDate;
	private String pRpBudgetryCost;
	
	/**
	 * @return the pRpApprover1Date
	 */
	public String getpRpApprover1Date() {
		return pRpApprover1Date;
	}
	/**
	 * @param pRpApprover1Date the pRpApprover1Date to set
	 */
	public void setpRpApprover1Date(String pRpApprover1Date) {
		this.pRpApprover1Date = pRpApprover1Date;
	}
	/**
	 * @return the pRpApprover2Date
	 */
	public String getpRpApprover2Date() {
		return pRpApprover2Date;
	}
	/**
	 * @param pRpApprover2Date the pRpApprover2Date to set
	 */
	public void setpRpApprover2Date(String pRpApprover2Date) {
		this.pRpApprover2Date = pRpApprover2Date;
	}
	/**
	 * @return the pRpPartCode
	 */
	public String getpRpPartCode() {
		return pRpPartCode;
	}
	/**
	 * @param pRpPartCode the pRpPartCode to set
	 */
	public void setpRpPartCode(String pRpPartCode) {
		this.pRpPartCode = pRpPartCode;
	}
	/**
	 * @return the pRpDeliveryDate
	 */
	public String getpRpDeliveryDate() {
		return pRpDeliveryDate;
	}
	/**
	 * @param pRpDeliveryDate the pRpDeliveryDate to set
	 */
	public void setpRpDeliveryDate(String pRpDeliveryDate) {
		this.pRpDeliveryDate = pRpDeliveryDate;
	}
	/**
	 * @return the pRpBudgetryCost
	 */
	public String getpRpBudgetryCost() {
		return pRpBudgetryCost;
	}
	/**
	 * @param pRpBudgetryCost the pRpBudgetryCost to set
	 */
	public void setpRpBudgetryCost(String pRpBudgetryCost) {
		this.pRpBudgetryCost = pRpBudgetryCost;
	}
	/**
	 * @return the pRpCreatedBy
	 */
	public String getpRpCreatedBy() {
		return pRpCreatedBy;
	}
	/**
	 * @param pRpCreatedBy the pRpCreatedBy to set
	 */
	public void setpRpCreatedBy(String pRpCreatedBy) {
		this.pRpCreatedBy = pRpCreatedBy;
	}
	/**
	 * @return the pRpCreatedDate
	 */
	public String getpRpCreatedDate() {
		return pRpCreatedDate;
	}
	/**
	 * @param pRpCreatedDate the pRpCreatedDate to set
	 */
	public void setpRpCreatedDate(String pRpCreatedDate) {
		this.pRpCreatedDate = pRpCreatedDate;
	}
	/**
	 * @return the pRpUpdatedBy
	 */
	public String getpRpUpdatedBy() {
		return pRpUpdatedBy;
	}
	/**
	 * @param pRpUpdatedBy the pRpUpdatedBy to set
	 */
	public void setpRpUpdatedBy(String pRpUpdatedBy) {
		this.pRpUpdatedBy = pRpUpdatedBy;
	}
	/**
	 * @return the pRpUpdatedDate
	 */
	public String getpRpUpdatedDate() {
		return pRpUpdatedDate;
	}
	/**
	 * @param pRpUpdatedDate the pRpUpdatedDate to set
	 */
	public void setpRpUpdatedDate(String pRpUpdatedDate) {
		this.pRpUpdatedDate = pRpUpdatedDate;
	}
	/**
	 * @return the pRpPrNumber
	 */
	public String getpRpPrNumber() {
		return pRpPrNumber;
	}
	/**
	 * @param pRpPrNumber the pRpPrNumber to set
	 */
	public void setpRpPrNumber(String pRpPrNumber) {
		this.pRpPrNumber = pRpPrNumber;
	}
	/**
	 * @return the pRpRaisedPrFullName
	 */
	public String getpRpRaisedPrFullName() {
		return pRpRaisedPrFullName;
	}
	/**
	 * @param pRpRaisedPrFullName the pRpRaisedPrFullName to set
	 */
	public void setpRpRaisedPrFullName(String pRpRaisedPrFullName) {
		this.pRpRaisedPrFullName = pRpRaisedPrFullName;
	}
	/**
	 * @return the pRpApprover1FullName
	 */
	public String getpRpApprover1FullName() {
		return pRpApprover1FullName;
	}
	/**
	 * @param pRpApprover1FullName the pRpApprover1FullName to set
	 */
	public void setpRpApprover1FullName(String pRpApprover1FullName) {
		this.pRpApprover1FullName = pRpApprover1FullName;
	}
	/**
	 * @return the pRpApprover2FullName
	 */
	public String getpRpApprover2FullName() {
		return pRpApprover2FullName;
	}
	/**
	 * @param pRpApprover2FullName the pRpApprover2FullName to set
	 */
	public void setpRpApprover2FullName(String pRpApprover2FullName) {
		this.pRpApprover2FullName = pRpApprover2FullName;
	}
	/**
	 * @return the pRpPdfName
	 */
	public String getpRpPdfName() {
		return pRpPdfName;
	}
	/**
	 * @param pRpPdfName the pRpPdfName to set
	 */
	public void setpRpPdfName(String pRpPdfName) {
		this.pRpPdfName = pRpPdfName;
	}
	/**
	 * @return the pRpPdfData
	 */
	public Blob getpRpPdfData() {
		return pRpPdfData;
	}
	/**
	 * @param pRpPdfData the pRpPdfData to set
	 */
	public void setpRpPdfData(Blob pRpPdfData) {
		this.pRpPdfData = pRpPdfData;
	}
	/**
	 * @return the pRpRaisePRMailId
	 */
	public String getpRpRaisePRMailId() {
		return pRpRaisePRMailId;
	}
	/**
	 * @param pRpRaisePRMailId the pRpRaisePRMailId to set
	 */
	public void setpRpRaisePRMailId(String pRpRaisePRMailId) {
		this.pRpRaisePRMailId = pRpRaisePRMailId;
	}
	/**
	 * @return the pRpPrStatus
	 */
	public String getpRpPrStatus() {
		return pRpPrStatus;
	}
	/**
	 * @param pRpPrStatus the pRpPrStatus to set
	 */
	public void setpRpPrStatus(String pRpPrStatus) {
		this.pRpPrStatus = pRpPrStatus;
	}
	/**
	 * @return the pRpApprover1Name
	 */
	public String getpRpApprover1Name() {
		return pRpApprover1Name;
	}
	/**
	 * @param pRpApprover1Name the pRpApprover1Name to set
	 */
	public void setpRpApprover1Name(String pRpApprover1Name) {
		this.pRpApprover1Name = pRpApprover1Name;
	}
	/**
	 * @return the pRpApprover2Name
	 */
	public String getpRpApprover2Name() {
		return pRpApprover2Name;
	}
	/**
	 * @param pRpApprover2Name the pRpApprover2Name to set
	 */
	public void setpRpApprover2Name(String pRpApprover2Name) {
		this.pRpApprover2Name = pRpApprover2Name;
	}
	/**
	 * @return the pRpEquipmentDescription
	 */
	public String getpRpEquipmentDescription() {
		return pRpEquipmentDescription;
	}
	/**
	 * @param pRpEquipmentDescription the pRpEquipmentDescription to set
	 */
	public void setpRpEquipmentDescription(String pRpEquipmentDescription) {
		this.pRpEquipmentDescription = pRpEquipmentDescription;
	}
	/**
	 * @return the pRpGeneratePrDate
	 */
	public String getpRpGeneratePrDate() {
		return pRpGeneratePrDate;
	}
	/**
	 * @param pRpGeneratePrDate the pRpGeneratePrDate to set
	 */
	public void setpRpGeneratePrDate(String pRpGeneratePrDate) {
		this.pRpGeneratePrDate = pRpGeneratePrDate;
	}
	/**
	 * @return the pRpIdentificationNum
	 */
	public String getpRpIdentificationNum() {
		return pRpIdentificationNum;
	}
	/**
	 * @param pRpIdentificationNum the pRpIdentificationNum to set
	 */
	public void setpRpIdentificationNum(String pRpIdentificationNum) {
		this.pRpIdentificationNum = pRpIdentificationNum;
	}
	/**
	 * @return the pRpApprover1Status
	 */
	public String getpRpApprover1Status() {
		return pRpApprover1Status;
	}
	/**
	 * @param pRpApprover1Status the pRpApprover1Status to set
	 */
	public void setpRpApprover1Status(String pRpApprover1Status) {
		this.pRpApprover1Status = pRpApprover1Status;
	}
	/**
	 * @return the pRpApprover1Comments
	 */
	public String getpRpApprover1Comments() {
		return pRpApprover1Comments;
	}
	/**
	 * @param pRpApprover1Comments the pRpApprover1Comments to set
	 */
	public void setpRpApprover1Comments(String pRpApprover1Comments) {
		this.pRpApprover1Comments = pRpApprover1Comments;
	}
	/**
	 * @return the pRpApprover2Status
	 */
	public String getpRpApprover2Status() {
		return pRpApprover2Status;
	}
	/**
	 * @param pRpApprover2Status the pRpApprover2Status to set
	 */
	public void setpRpApprover2Status(String pRpApprover2Status) {
		this.pRpApprover2Status = pRpApprover2Status;
	}


	/**
	 * @return the pRpApprover2Comments
	 */
	public String getpRpApprover2Comments() {
		return pRpApprover2Comments;
	}
	/**
	 * @param pRpApprover2Comments the pRpApprover2Comments to set
	 */
	public void setpRpApprover2Comments(String pRpApprover2Comments) {
		this.pRpApprover2Comments = pRpApprover2Comments;
	}
	/**
	 * @return the pRpSupplierNumber
	 */
	public String getpRpSupplierNumber() {
		return pRpSupplierNumber;
	}
	/**
	 * @param pRpSupplierNumber the pRpSupplierNumber to set
	 */
	public void setpRpSupplierNumber(String pRpSupplierNumber) {
		this.pRpSupplierNumber = pRpSupplierNumber;
	}
	/**
	 * @return the pRpEquipId
	 */
	public Integer getpRpEquipId() {
		return pRpEquipId;
	}
	/**
	 * @param pRpEquipId the pRpEquipId to set
	 */
	public void setpRpEquipId(Integer pRpEquipId) {
		this.pRpEquipId = pRpEquipId;
	}
	/**
	 * @return the pRpSupplierId
	 */
	public Integer getpRpSupplierId() {
		return pRpSupplierId;
	}
	/**
	 * @param pRpSupplierId the pRpSupplierId to set
	 */
	public void setpRpSupplierId(Integer pRpSupplierId) {
		this.pRpSupplierId = pRpSupplierId;
	}
	/**
	 * @return the pRpSupplierName
	 */
	public String getpRpSupplierName() {
		return pRpSupplierName;
	}
	/**
	 * @param pRpSupplierName the pRpSupplierName to set
	 */
	public void setpRpSupplierName(String pRpSupplierName) {
		this.pRpSupplierName = pRpSupplierName;
	}
	/**
	 * @return the pRpPRId
	 */
	public Integer getpRpPRId() {
		return pRpPRId;
	}
	/**
	 * @param pRpPRId the pRpPRId to set
	 */
	public void setpRpPRId(Integer pRpPRId) {
		this.pRpPRId = pRpPRId;
	}
	/**
	 * @return the pRpCalibId
	 */
	public Integer getpRpCalibId() {
		return pRpCalibId;
	}
	/**
	 * @param pRpCalibId the pRpCalibId to set
	 */
	public void setpRpCalibId(Integer pRpCalibId) {
		this.pRpCalibId = pRpCalibId;
	}
	/**
	 * @return the pRpProdId
	 */
	public Integer getpRpProdId() {
		return pRpProdId;
	}
	/**
	 * @param pRpProdId the pRpProdId to set
	 */
	public void setpRpProdId(Integer pRpProdId) {
		this.pRpProdId = pRpProdId;
	}
	/**
	 * @return the pRpApprover1
	 */
	public String getpRpApprover1() {
		return pRpApprover1;
	}
	/**
	 * @param pRpApprover1 the pRpApprover1 to set
	 */
	public void setpRpApprover1(String pRpApprover1) {
		this.pRpApprover1 = pRpApprover1;
	}
	/**
	 * @return the pRpApprover2
	 */
	public String getpRpApprover2() {
		return pRpApprover2;
	}
	/**
	 * @param pRpApprover2 the pRpApprover2 to set
	 */
	public void setpRpApprover2(String pRpApprover2) {
		this.pRpApprover2 = pRpApprover2;
	}
}
