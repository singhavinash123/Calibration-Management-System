package com.spiraxcalibration.services;

import java.util.List;

import javax.servlet.http.Part;

import com.spiraxcalibration.models.CertData;
import com.spiraxcalibration.models.PRPData;
import com.spiraxcalibration.models.PrData;

public interface CertUploadDownloadIService {

	int certUploadCertificate(String calibId, String identityNum, String dueDate ,String certName, String certificateOptionName, Part filePart);
	List<CertData> certGetCertificateDetails(Integer calibId);
	CertData certDownloadCertificate(Integer certId);
	int certDeleteCertificate(Integer warrantyId);
	CertData calibGetCalibIdByCertId(Integer warrantyId);
	//int certUploadCertificate(String calibId, String identityNum, Part filePart);
	int certUploadCertificate(String prpId, Part filePart);
	PRPData downloadPDFCertificateByPRID(int prId);
	List<CertData> certGetCertificateDetails();
	List<CertData> getMasterViewCertificate();

}
