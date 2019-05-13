package com.spiraxcalibration.servicesIMPL;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spiraxcalibration.dao.CertUploadDownloadDao;
import com.spiraxcalibration.models.CertData;
import com.spiraxcalibration.models.PRPData;
import com.spiraxcalibration.services.CertUploadDownloadIService;

@Service
public class CertUploadDownloadServiceIMPL implements CertUploadDownloadIService{
	Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	CertUploadDownloadDao  certUploadDownloadDao;

	@Override
	public int certUploadCertificate(String calibId,String identityNum, String dueDate,
			String certName, String certificateOptionName, Part filePart) {
		logger.info("INSIDE CertUploadDownloadServiceIMPL START METHOD certUploadCertificate");
		InputStream inputStream = null;
		String fileName = null;
		String fileExtension = null;
		String createdFileName = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		String currentDate = sdf.format(new Date());
		try {
			fileName = getFileName(filePart);
			// createdFileName =  calibId+"_"+identityNum+"_"+dueDate;
			if(filePart != null){
				long fileSize = filePart.getSize();
				String fileContent = filePart.getContentType();
				inputStream = filePart.getInputStream();
				fileExtension =  getFileExtension(fileName);
				createdFileName =  calibId+"_"+identityNum+"_"+certName+"_"+dueDate+"_"+certificateOptionName+"."+fileExtension;
			}	
		}catch (IOException e) {
			e.printStackTrace();
		}
		if(fileExtension.equalsIgnoreCase("pdf")){
			return certUploadDownloadDao.certUploadCertificate(calibId,identityNum ,createdFileName,inputStream,currentDate,certificateOptionName);	
		}
		logger.info("INSIDE CertUploadDownloadServiceIMPL END METHOD certUploadCertificate");
		return 0;
	}

	private String getFileName(final Part filePart) {
//		final String partHeader = filePart.getHeader("content-disposition");
		for(String content : filePart.getHeader("content-disposition").split(";")){
			if(content.trim().startsWith("filename")){
				return content.substring(content.indexOf("=") + 1).trim().replace("\"","");
			}
		}
		return null;
	}

	private static String getFileExtension(String fileName) {
		//String fileName = file.getName();
		if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
			return fileName.substring(fileName.lastIndexOf(".")+1);
		else return "";
	}

	@Override
	public List<CertData> certGetCertificateDetails(Integer calibId) {
		logger.info("INSIDE CertUploadDownloadServiceIMPL START METHOD certGetCertificateDetails");
		List<CertData> list =  certUploadDownloadDao.certGetCertificateDetails(calibId);
		logger.info("INSIDE CertUploadDownloadServiceIMPL END METHOD certGetCertificateDetails");
		return list;
	}

	@Override
	public CertData certDownloadCertificate(Integer warrantyId) {
		logger.info("INSIDE CertUploadDownloadServiceIMPL START METHOD certDownloadCertificate");
		CertData certData = certUploadDownloadDao.certDownloadCertificate(warrantyId);
		logger.info("INSIDE CertUploadDownloadServiceIMPL END METHOD certDownloadCertificate");
		return certData;
	}

	@Override
	public int certDeleteCertificate(Integer warrantyId) {
		logger.info("INSIDE CertUploadDownloadServiceIMPL START METHOD certDeleteCertificate");
		int num = certUploadDownloadDao.certDeleteCertificate(warrantyId);
		if(num <= 0){
			logger.error("INSIDE CertUploadDownloadServiceIMPL method certDeleteCertificate the certificate not deleted!!");
		}
		logger.info("INSIDE CertUploadDownloadServiceIMPL END METHOD certDeleteCertificate");
		return num;
	}

	@Override
	public CertData calibGetCalibIdByCertId(Integer warrantyId) {
		logger.info("INSIDE CertUploadDownloadServiceIMPL END METHOD calibGetCalibIdByCertId");
		CertData certData = certUploadDownloadDao.calibGetCalibIdByCertId(warrantyId);
		logger.info("INSIDE CertUploadDownloadServiceIMPL END METHOD calibGetCalibIdByCertId");
		return certData;
	}

	@Override
	public int certUploadCertificate(String prpId , Part filePart) {
		logger.info("INSIDE CertUploadDownloadServiceIMPL START METHOD certUploadCertificate");
		InputStream inputStream = null;
		String fileName = null;
		String fileExtension = null;
		String createdFileName = null;
		try {
			fileName = getFileName(filePart);
			if(filePart != null){
				long fileSize = filePart.getSize();
				String fileContent = filePart.getContentType();
				inputStream = filePart.getInputStream();
				fileExtension =  getFileExtension(fileName);
				createdFileName =  fileName;
			}	
		}catch (IOException e) {
			e.printStackTrace();
		}
		if(fileExtension.equalsIgnoreCase("pdf")){
			return certUploadDownloadDao.certUploadCertificate(createdFileName,inputStream,prpId);	
		}
		logger.info("INSIDE CertUploadDownloadServiceIMPL END METHOD certUploadCertificate");
		return 0;
	}

	@Override
	public PRPData downloadPDFCertificateByPRID(int prId) {
		return certUploadDownloadDao.downloadPDFCertificateByPRID(prId);
	}

	@Override
	public List<CertData> certGetCertificateDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CertData> getMasterViewCertificate() {
		return certUploadDownloadDao.getMasterViewCertificate();
	}

	//	@Override
	//	public int certUploadCertificate(String calibId, String identityNum, Part filePart) {
	//		logger.info("INSIDE CertUploadDownloadServiceIMPL START METHOD certUploadCertificate");
	//		InputStream inputStream = null;
	//		String fileName = null;
	//		String fileExtension = null;
	//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	//		String currentDate = sdf.format(new Date());
	//		System.out.println("current Date :"+currentDate);
	//		try {
	//			fileName = getFileName(filePart);
	//			System.out.println("file name is :"+fileName);
	//			if(filePart != null){
	//				long fileSize = filePart.getSize();
	//				String fileContent = filePart.getContentType();
	//				inputStream = filePart.getInputStream();
	//
	//				fileExtension =  getFileExtension(fileName);
	//
	//				System.out.println("size :"+fileSize);
	//				System.out.println("fileContent :"+fileContent);
	//				System.out.println("inputStream :"+inputStream);
	//				System.out.println("File extension is: "+fileExtension);
	//			}	
	//		}catch (IOException e) {
	//			e.printStackTrace();
	//		}
	//		if(fileExtension.equalsIgnoreCase("pdf")){
	//			System.out.println("inside pdf extension !!");
	//			return certUploadDownloadDao.certUploadCertificate(calibId,identityNum,fileName,inputStream,currentDate,certificateOptionName);	
	//		}
	//		logger.info("INSIDE CertUploadDownloadServiceIMPL END METHOD certUploadCertificate");
	//		return 0;
	//
	//	}


}
