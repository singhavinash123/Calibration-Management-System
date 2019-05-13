package com.spiraxcalibration.daoIMPL;

import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.spiraxcalibration.WebConfig.AppsPropertyFile;
import com.spiraxcalibration.dao.CertUploadDownloadDao;
import com.spiraxcalibration.models.CertData;
import com.spiraxcalibration.models.PRPData;

@Repository
public class CertUploadDownloadDaoIMPL implements CertUploadDownloadDao{

	Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	AppsPropertyFile dBQueryPropertyFile = new AppsPropertyFile();

	@Autowired
	JdbcTemplate  jdbcTemplate1;

	@Override
	public int certUploadCertificate(String calibId,String identityNum,
			String fileName, InputStream inputStream, String currentDate,String CertificateOptionName) {
		logger.info("INSIDE CertUploadDownloadDaoIMPL START METHOD certUploadCertificate");
		String uploadCertQuery =  dBQueryPropertyFile.getQueryForKey("uploadCertQuery");
		int AfterInsertnumber = jdbcTemplate1.update(uploadCertQuery,
				calibId,
				identityNum,
				fileName,
				inputStream,
				currentDate,
				CertificateOptionName);
		logger.info("INSIDE CertUploadDownloadDaoIMPL END METHOD certUploadCertificate");
		return 	AfterInsertnumber;	
	}

	@Override
	public List<CertData> certGetCertificateDetails(Integer calibId) {
		logger.info("INSIDE CertUploadDownloadDaoIMPL START METHOD certGetCertificateDetails");
		String getCertificatesByIdQuery =  dBQueryPropertyFile.getQueryForKey("getCertificatesByIdQuery");
		List<CertData> certificatesDataList = jdbcTemplate1.query(getCertificatesByIdQuery,  new Object[] { calibId }, new certDataMapper());
		logger.info("INSIDE CertUploadDownloadDaoIMPL END METHOD certGetCertificateDetails");
		return certificatesDataList;
	}


	private static final class certDataMapper implements RowMapper<CertData>{
		@Override
		public CertData mapRow(ResultSet rs , int rowNum) throws SQLException {
			CertData certData = new CertData();
			certData.setCerrtSerialnumber(rs.getString("serial_number"));
			certData.setCertCalibId(rs.getInt("calib_id"));
			certData.setCertCertificateName(rs.getString("Certicate_Name"));
			certData.setCertIndentiytNumber(rs.getString("identification_number"));
			certData.setCertWarrantyId(rs.getInt("warranty_id"));
			certData.setCertCertificatePdf(rs.getBlob("Certificate_PDF"));
			certData.setCertUploadCertDate(rs.getString("upload_time"));
			certData.setCertCalibCertificateOptionName(rs.getString("certificate_Option_Name"));
			return  certData;
		}
	}
	

	@Override
	public int certDeleteCertificate(Integer warrantyId) {
		logger.info("INSIDE CertUploadDownloadDaoIMPL START METHOD certDeleteCertificate ::");
		String deleteCertificateByCertIdQuery = dBQueryPropertyFile.getQueryForKey("deleteCertificateByCertIdQuery");
		logger.info("INSIDE CertUploadDownloadDaoIMPL END METHOD certDeleteCertificate ::");
		return jdbcTemplate1.update(deleteCertificateByCertIdQuery , warrantyId);
	}

	@Override
	public CertData calibGetCalibIdByCertId(Integer warrantyId) {
		logger.info("INSIDE CertUploadDownloadDaoIMPL START METHOD calibGetCalibIdByCertId");
		String getCertificatesByCertIdQuery =  dBQueryPropertyFile.getQueryForKey("getCertificatesByCertIdQuery");
		CertData certData = jdbcTemplate1.queryForObject(getCertificatesByCertIdQuery,  new Object[] { warrantyId }, new certDataMapper());
		logger.info("INSIDE CertUploadDownloadDaoIMPL END METHOD calibGetCalibIdByCertId");
		return certData;
	}

	@Override
	public CertData certDownloadCertificate(Integer warrantyId) {
		logger.info("INSIDE CertUploadDownloadDaoIMPL START METHOD certDownloadCertificate");
		String selectCertificateBycertIdQuery = dBQueryPropertyFile.getQueryForKey("selectCertificateBycertIdQuery");
		CertData certData = jdbcTemplate1.queryForObject(selectCertificateBycertIdQuery,  new Object[] { warrantyId }, new certDataMapper());
		logger.info("INSIDE CertUploadDownloadDaoIMPL END METHOD certDownloadCertificate");
		return certData;
	}

	@Override
	public int certUploadCertificate(String createdFileName, InputStream inputStream, String prId) {
		logger.info("INSIDE CertUploadDownloadDaoIMPL END METHOD certUploadCertificate");
		String uploadPDFToApprover2Query =  dBQueryPropertyFile.getQueryForKey("uploadPDFToApprover2Query");
		int AfterInsertnumber = jdbcTemplate1.update(uploadPDFToApprover2Query,
				createdFileName,
				inputStream,
				prId);
		logger.info("INSIDE CertUploadDownloadDaoIMPL END METHOD certUploadCertificate");
		return 	AfterInsertnumber;	
	}

	@Override
	public PRPData downloadPDFCertificateByPRID(int prId) {
		logger.info("INSIDE CertUploadDownloadDaoIMPL START METHOD downloadPDFCertificateByPRID");
		String selectPDFDbyPr_IdQuery = dBQueryPropertyFile.getQueryForKey("selectPDFDbyPr_IdQuery");
		PRPData pRPData = jdbcTemplate1.queryForObject(selectPDFDbyPr_IdQuery, new Object[] { prId } , new prPDataMapper());
		logger.info("INSIDE CertUploadDownloadDaoIMPL END METHOD downloadPDFCertificateByPRID");
		return pRPData;
	}
	
	private static final class prPDataMapper implements RowMapper<PRPData>{
		@Override
		public PRPData mapRow(ResultSet rs , int rowNum) throws SQLException {
			PRPData calibData = new PRPData();
			calibData.setpRpPRId(rs.getInt("pr_id"));
			calibData.setpRpPdfName(rs.getString("Upload_Pdf_Name"));
			calibData.setpRpPdfData(rs.getBlob("Upload_Pdf_Data"));
			return calibData;
		}
	}


	@Override
	public List<CertData> certGetCertificateDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CertData> getMasterViewCertificate() {
		logger.info("INSIDE CertUploadDownloadDaoIMPL START METHOD getMasterViewCertificate");
		String getgetMasterViewCertificateQuery =  dBQueryPropertyFile.getQueryForKey("getgetMasterViewCertificateQuery");
		List<CertData> certificatesDataList = jdbcTemplate1.query(getgetMasterViewCertificateQuery,  new certMasterDataMapper());
		logger.info("INSIDE CertUploadDownloadDaoIMPL END METHOD getMasterViewCertificate");
		return certificatesDataList;
	}
	
	private static final class certMasterDataMapper implements RowMapper<CertData>{
		@Override
		public CertData mapRow(ResultSet rs , int rowNum) throws SQLException {
			CertData certData = new CertData();
			certData.setCertCalibId(rs.getInt("calib_id"));
			certData.setCertCertificateName(rs.getString("Certicate_Name"));
			certData.setCertIndentiytNumber(rs.getString("identification_number"));
			certData.setCertUploadCertDate(rs.getString("upload_time"));
			certData.setCertCalibCertificateOptionName(rs.getString("certificate_Option_Name"));
			certData.setCertCertificateDueDate(rs.getString("Due_Date"));
			certData.setCertCertificateNumber(rs.getString("Calibration_Certificate"));
			certData.setCertCertificateValidatedBy(rs.getString("Certificate_ValidatedBy"));
			return  certData;
		}
	}

//	@Override
//	public int certUploadCertificate(String calibId, String identityNum, String fileName, InputStream inputStream,
//			String currentDate) {
//		
//		String uploadCertQuery =  dBQueryPropertyFile.getQueryForKey("uploadCertQuery");
//		int AfterInsertnumber = jdbcTemplate1.update(uploadCertQuery,
//				calibId,
//				identityNum,
//				fileName,
//				inputStream,
//				currentDate);
//		return 	AfterInsertnumber;
//	}
	
//	@Override
//	public int certUploadCertificate(String calibId, String identityNum, String fileName, InputStream inputStream,
//			String currentDate) {
//		String uploadCertQuery =  dBQueryPropertyFile.getQueryForKey("uploadCertQuery");
//		int AfterInsertnumber = jdbcTemplate1.update(uploadCertQuery,
//				calibId,
//				calibProdId,
//				identityNum,
//				serialNum,
//				fileName,
//				inputStream,
//				currentDate);
//		return 	AfterInsertnumber;
//	}

}
