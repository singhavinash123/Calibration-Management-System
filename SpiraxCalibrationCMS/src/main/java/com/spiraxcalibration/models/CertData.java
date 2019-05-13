package com.spiraxcalibration.models;

import java.sql.Blob;

public class CertData {
	
	private Integer certWarrantyId;
	private Integer certCalibId;
	private Integer certProdId;
	private String certIndentiytNumber;
	private String cerrtSerialnumber;
	private String certCertificateName;
	private Blob   certCertificatePdf;
	private String certUploadCertDate;
	private String certCalibrationDateByCalibId;
	private String certCalibCertificateOptionName;
	
	private String certCertificateNumber;
	private String certCertificateDueDate;
	private String certCertificateValidatedBy;

	/**
	 * @return the certCertificateValidatedBy
	 */
	public String getCertCertificateValidatedBy() {
		return certCertificateValidatedBy;
	}
	/**
	 * @param certCertificateValidatedBy the certCertificateValidatedBy to set
	 */
	public void setCertCertificateValidatedBy(String certCertificateValidatedBy) {
		this.certCertificateValidatedBy = certCertificateValidatedBy;
	}
	/**
	 * @return the certCertificateNumber
	 */
	public String getCertCertificateNumber() {
		return certCertificateNumber;
	}
	/**
	 * @param certCertificateNumber the certCertificateNumber to set
	 */
	public void setCertCertificateNumber(String certCertificateNumber) {
		this.certCertificateNumber = certCertificateNumber;
	}
	/**
	 * @return the certCertificateDueDate
	 */
	public String getCertCertificateDueDate() {
		return certCertificateDueDate;
	}
	/**
	 * @param certCertificateDueDate the certCertificateDueDate to set
	 */
	public void setCertCertificateDueDate(String certCertificateDueDate) {
		this.certCertificateDueDate = certCertificateDueDate;
	}
	/**
	 * @return the certCalibCertificateOptionName
	 */
	public String getCertCalibCertificateOptionName() {
		return certCalibCertificateOptionName;
	}
	/**
	 * @param certCalibCertificateOptionName the certCalibCertificateOptionName to set
	 */
	public void setCertCalibCertificateOptionName(String certCalibCertificateOptionName) {
		this.certCalibCertificateOptionName = certCalibCertificateOptionName;
	}
	/**
	 * @return the certCalibrationDateByCalibId
	 */
	public String getCertCalibrationDateByCalibId() {
		return certCalibrationDateByCalibId;
	}
	/**
	 * @param certCalibrationDateByCalibId the certCalibrationDateByCalibId to set
	 */
	public void setCertCalibrationDateByCalibId(String certCalibrationDateByCalibId) {
		this.certCalibrationDateByCalibId = certCalibrationDateByCalibId;
	}
	/**
	 * @return the certUploadCertDate
	 */
	public String getCertUploadCertDate() {
		return certUploadCertDate;
	}
	/**
	 * @param certUploadCertDate the certUploadCertDate to set
	 */
	public void setCertUploadCertDate(String certUploadCertDate) {
		this.certUploadCertDate = certUploadCertDate;
	}
	/**
	 * @return the certWarrantyId
	 */
	public Integer getCertWarrantyId() {
		return certWarrantyId;
	}
	/**
	 * @param certWarrantyId the certWarrantyId to set
	 */
	public void setCertWarrantyId(Integer certWarrantyId) {
		this.certWarrantyId = certWarrantyId;
	}
	/**
	 * @return the certCalibId
	 */
	public Integer getCertCalibId() {
		return certCalibId;
	}
	/**
	 * @param certCalibId the certCalibId to set
	 */
	public void setCertCalibId(Integer certCalibId) {
		this.certCalibId = certCalibId;
	}
	/**
	 * @return the certProdId
	 */
	public Integer getCertProdId() {
		return certProdId;
	}
	/**
	 * @param certProdId the certProdId to set
	 */
	public void setCertProdId(Integer certProdId) {
		this.certProdId = certProdId;
	}
	/**
	 * @return the certIndentiytNumber
	 */
	public String getCertIndentiytNumber() {
		return certIndentiytNumber;
	}
	/**
	 * @param certIndentiytNumber the certIndentiytNumber to set
	 */
	public void setCertIndentiytNumber(String certIndentiytNumber) {
		this.certIndentiytNumber = certIndentiytNumber;
	}
	/**
	 * @return the cerrtSerialnumber
	 */
	public String getCerrtSerialnumber() {
		return cerrtSerialnumber;
	}
	/**
	 * @param cerrtSerialnumber the cerrtSerialnumber to set
	 */
	public void setCerrtSerialnumber(String cerrtSerialnumber) {
		this.cerrtSerialnumber = cerrtSerialnumber;
	}
	/**
	 * @return the certCertificateName
	 */
	public String getCertCertificateName() {
		return certCertificateName;
	}
	/**
	 * @param certCertificateName the certCertificateName to set
	 */
	public void setCertCertificateName(String certCertificateName) {
		this.certCertificateName = certCertificateName;
	}
	/**
	 * @return the certCertificatePdf
	 */
	public Blob getCertCertificatePdf() {
		return certCertificatePdf;
	}
	/**
	 * @param certCertificatePdf the certCertificatePdf to set
	 */
	public void setCertCertificatePdf(Blob certCertificatePdf) {
		this.certCertificatePdf = certCertificatePdf;
	}
	
	/*warranty_id	int(11)
	calib_id	int(11)
	prod_id	int(11)
	identification_number	varchar(30)
	serial_number	varchar(11)
	Certificate_PDF	longblob
	Certicate_Name	varchar(30)*/
}
