package com.spiraxcalibration.dao;

import java.io.InputStream;
import java.util.List;

import com.spiraxcalibration.models.CertData;
import com.spiraxcalibration.models.PRPData;

public interface CertUploadDownloadDao {
	List<CertData> certGetCertificateDetails(Integer calibId);
	int certDeleteCertificate(Integer warrantyId);
	CertData calibGetCalibIdByCertId(Integer warrantyId);
	CertData certDownloadCertificate(Integer warrantyId);
	int certUploadCertificate(String calibId, String identityNum, String fileName, InputStream inputStream,
			String currentDate, String certificateOptionName);
	int certUploadCertificate(String createdFileName, InputStream inputStream, String prpId);
	PRPData downloadPDFCertificateByPRID(int prId);
	List<CertData> certGetCertificateDetails();
	List<CertData> getMasterViewCertificate();
	
}
