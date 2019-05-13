package com.spiraxcalibration.controllers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.spiraxcalibration.WebConfig.AppsPropertyFile;
import com.spiraxcalibration.models.CalibMainData;
import com.spiraxcalibration.models.PRPData;
import com.spiraxcalibration.services.QRAndBARCodeIService;

@Controller
public class QRandBARCodeController {

	Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	AppsPropertyFile webPropertyFile = new AppsPropertyFile();

	@Autowired
	QRAndBARCodeIService qrAndBARCodeIService;

	//	@RequestMapping(value="/print_QR_BAR_Code" , method=RequestMethod.GET)
	//	public ModelAndView QRBARgetCalibrationForQRAndBARCode() {
	//		logger.info("INSIDE QRandBARCodeController START METHOD QRBARgetCalibrationForQRAndBARCode ::");
	//		ModelAndView model = new ModelAndView("QR_BarCode/QRandBarCodeList");
	//		
	//		List<CalibData> calibDataList = qrAndBARCodeIService.QR_BARgetCalibrationDetails();
	//		model.addObject("CalibDataList",calibDataList);
	//		
	//		List<String> identityNoList = qrAndBARCodeIService.QR_BARgetIdentityNumberFromCalibration();
	//		List<String> serialNoList = qrAndBARCodeIService.QR_BARgetSerialNumberFromCalibration();
	//
	//		model.addObject("identityNoList",identityNoList);
	//		model.addObject("serialNoList",serialNoList);
	//		model.addObject("addOrUpdate", "QR/BAR Code List");
	//		logger.info("INSIDE QRandBARCodeController START METHOD QRBARgetCalibrationForQRAndBARCode ::");
	//		return model;
	//	}

	@RequestMapping(value = "/printQR_BAR_Code/{calib_Id}" , method = RequestMethod.GET)
	public ModelAndView QrBargetQRAndBrcodes(@PathVariable(value="calib_Id") Integer calib_Id){
		logger.info("INSIDE QRandBARCodeController START METHOD generateQRAndBrcodes::");
		ModelAndView model = new ModelAndView("QR_BarCode/QrAndBarCodeDisplay");
		List<CalibMainData> calibDataList = null;
		if(calib_Id != null){
			calibDataList = qrAndBARCodeIService.detailsForQrAndBarCode(calib_Id);
		}
		model.addObject("calibData", calibDataList);
		model.addObject("calibdata", calibDataList.get(0));
		model.addObject("calibId", calib_Id);
		logger.info("INSIDE QRandBARCodeController END METHOD generateQRAndBrcodes::");
		return model;
	}


	@RequestMapping("/generateBarCode")
	public void generateBarcode(HttpServletRequest request, HttpServletResponse response) throws IOException{
		logger.info("INSIDE QRAndBarCodeController START METHOD generateBarcode");
		String calibId =  request.getParameter("productId");
		//String QrBarCodeURL = webPropertyFile.getURLForKey("qrAndBarCodeURL");
		response.setContentType("image/png");
		OutputStream outputStream = response.getOutputStream();
		if(calibId != null && !calibId.isEmpty()){
			//	String QrBarCodeURLs = QrBarCodeURL + productId;
			String QrBarCodeURLs = calibId;
			//	String QrBarCodeURLs = "191";
			outputStream.write(getBARCodeImage(QrBarCodeURLs ,Integer.parseInt(webPropertyFile.getmeasurment("barCodeWidth")) , Integer.parseInt(webPropertyFile.getmeasurment("barCodeHeight"))));
		}
		outputStream.flush();
		outputStream.close();
		logger.info("INSIDE QRAndBarCodeController END METHOD generateBarcode");
	}

	private byte[] getBARCodeImage(String code , int width , int height){
		if(code != null && !code.isEmpty()){
			try {
				Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<EncodeHintType, ErrorCorrectionLevel>();
				hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
				//   String code1 = "ABC-abc-12";
				//	Writer writer = new Code128Writer();

				//	BitMatrix bitMatrix = writer.encode(code, BarcodeFormat.CODE_128, width, height);
				BitMatrix bitMatrix = new Code128Writer().encode(code, BarcodeFormat.CODE_128, width, height);

				ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
				MatrixToImageWriter.writeToStream(bitMatrix, "png", byteArrayOutputStream);
				return byteArrayOutputStream.toByteArray();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@RequestMapping("/generateQrCode")
	public void generateQRCode(HttpServletRequest request, HttpServletResponse response) throws IOException{
		logger.info("INSIDE QRAndBarCodeController START METHOD generateQRCode");
		String calibId =  request.getParameter("productId");
		response.setContentType("image/png");
		//String QrBarCodeURL = webPropertyFile.getURLForKey("qrAndBarCodeURL");
		OutputStream outputStream = response.getOutputStream();
		if(calibId != null &&  !calibId.isEmpty()){
			//	String QrBarCodeURLs = QrBarCodeURL + productId;
			String QrBarCodeURLs = calibId;
			outputStream.write(getQRCodeImage(QrBarCodeURLs , Integer.parseInt(webPropertyFile.getmeasurment("qrCodeHeigth")) , Integer.parseInt(webPropertyFile.getmeasurment("qrCodeWidth"))));
		}
		outputStream.flush();
		outputStream.close();
		logger.info("INSIDE QRAndBarCodeController END METHOD generateQRCode");
	}

	private byte[] getQRCodeImage(String code , int width , int height){
		QRCodeWriter  qrCodeWriter = new QRCodeWriter();
		if(code != null){
			try {
				BitMatrix bitMatrix = qrCodeWriter.encode(code, BarcodeFormat.QR_CODE, width, height);
				ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
				MatrixToImageWriter.writeToStream(bitMatrix, "png", byteArrayOutputStream);
				return byteArrayOutputStream.toByteArray();
			} catch (WriterException | IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@RequestMapping("/qrAndBarCode")
	public ModelAndView generateQRAndBarcode(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		logger.info("INSIDE QRAndBarCodeController Start METHOD generateQRAndBarcode");
		ModelAndView model = new ModelAndView("qrcodes/QrAndBarCodeDisplay");
		String productId = request.getParameter("productId");
		model.addObject("productId",productId);
		logger.info("INSIDE QRAndBarCodeController END METHOD generateQRAndBarcode");
		return model;
	}

	//	@RequestMapping(value = "/detailsEquipment/{calibId}" , method = RequestMethod.GET)
	//	public ModelAndView detailsEquipmentAfterScan(@PathVariable(value="calibId") Integer calibId) throws ParseException{
	//		logger.info("INSIDE QRAndBarCodeController Start METHOD detailsEquipmentAfterScan");
	//		ModelAndView model = new ModelAndView("QR_BarCode/productDetailsScanned");
	//		List<CalibData> calibDataList = null;
	//		if(calibId != null){
	//			calibDataList = qrAndBARCodeIService.detailsForQrAndBarCode(calibId);
	//		}
	//		System.out.println("calibId ::::::"+calibId);
	//		model.addObject("calibDataList", calibDataList);
	//		model.addObject("calibId", calibId);
	//		logger.info("INSIDE QRAndBarCodeController END METHOD detailsEquipmentAfterScan");
	//		return model;
	//	}

	//	@RequestMapping(value = "/scannedProductDetails/{calibId}" , method = RequestMethod.GET)
	//	public ModelAndView detailsProductAfterScan(@PathVariable(value="calibId") Integer calibId) throws ParseException{
	//		logger.info("INSIDE QRAndBarCodeController Start METHOD detailsEquipmentAfterScan");
	//		ModelAndView model = new ModelAndView("QR_BarCode/scanedProductDetails");
	//		List<CalibData> calibDataList = null;
	//		if(calibId != null){
	//			calibDataList = qrAndBARCodeIService.detailsForQrAndBarCode(calibId);
	//		}
	//		System.out.println("calibId ::::::"+calibId);
	//		model.addObject("calibDataList", calibDataList);
	//		model.addObject("calibId", calibId);
	//		logger.info("INSIDE QRAndBarCodeController END METHOD detailsEquipmentAfterScan");
	//		return model;
	//	}
	//	
	@RequestMapping(value = "/searchQR_And_BAR_List")
	public ModelAndView QRAndBARSearchProductByCondition(HttpServletRequest request, HttpServletResponse response){
		logger.info("INSIDE QRAndBarCodeController START METHOD QRAndBARSearchProductByCondition");
		ModelAndView model = new ModelAndView("QR_BarCode/QRandBarCodeList");

		String identity = request.getParameter("identityno");
		String serialNumber = request.getParameter("serialno");
		if(identity != null || serialNumber != null){
			model.addObject("CalibDataList",qrAndBARCodeIService.QRAndBARSearchProductByCondition(identity, serialNumber));
			List<String> identityNoList = qrAndBARCodeIService.QR_BARgetIdentityNumberFromCalibration();
			List<String> serialNoList = qrAndBARCodeIService.QR_BARgetSerialNumberFromCalibration();

			model.addObject("identityNoList",identityNoList);
			model.addObject("serialNoList",serialNoList);
		}
		model.addObject("addOrUpdate", "QR/BAR Code List");
		logger.info("INSIDE QRAndBarCodeController END METHOD QRAndBARSearchProductByCondition");
		return model;
	}	

	@RequestMapping(value = "/scanText")
	public ModelAndView QR_BARGetScanText(){
		logger.info("INSIDE QRAndBarCodeController START METHOD QR_BARGetScanText");
		ModelAndView model = new ModelAndView("scanAndValidate/equipDetailsByIdentificationId");
		model.addObject("flag",true);
		logger.info("INSIDE QRAndBarCodeController END METHOD QR_BARGetScanText");
		return model;
	}

	@RequestMapping(value = "/scanAndValidate" , method=RequestMethod.POST)
	public ModelAndView QR_BARScanText(HttpServletRequest request , HttpServletResponse responce, RedirectAttributes redir){
		logger.info("INSIDE QRAndBarCodeController START METHOD QR_BARScanText");
		ModelAndView model = new ModelAndView("scanAndValidate/equipDetailsByIdentificationId");
		ModelAndView model2 = new ModelAndView("redirect:/scanText");
		String identificationNum = request.getParameter("scanText");
		CalibMainData calibMainData = null;
		PRPData pRPData = null;
		if(identificationNum != null){
			calibMainData = qrAndBARCodeIService.detailsForQrAndBarCodeScan(identificationNum);
			pRPData = qrAndBARCodeIService.getThePrInformationByIdentyNum(identificationNum);
			if(calibMainData == null){
				redir.addFlashAttribute("errorMsg","Identification number does not exist!");
				redir.addFlashAttribute("flag",true);
				return model2;
			}else{
				getColorForStatus(calibMainData);
			}
		}else{
			redir.addFlashAttribute("errorMsg","Please enter the identification number!");
			redir.addFlashAttribute("flag",true);
			return model2;
		}
		model.addObject("CalibMainData", calibMainData);
		model.addObject("pRPData", pRPData);
		logger.info("INSIDE QRAndBarCodeController END METHOD QR_BARScanText");
		return model;
	}
	
//	@RequestMapping(value = "/details_equipment/{identificationNum}" , method=RequestMethod.GET)
//	public ModelAndView QR_BARScanText(@PathVariable(value="identificationNum") String identityFicationNum , RedirectAttributes redir){
//		ModelAndView model = new ModelAndView("scanAndValidate/equipDetailsByIdentificationId");
//		CalibMainData calibMainData = null;
//		PRPData pRPData = null;
//		if(identityFicationNum != null){
//			calibMainData = qrAndBARCodeIService.detailsForQrAndBarCodeScan(identityFicationNum);
//			pRPData = qrAndBARCodeIService.getThePrInformationByIdentyNum(identityFicationNum);
//			if(calibMainData == null){
//				redir.addFlashAttribute("errorMsg","Identification number does not exist");
//				redir.addFlashAttribute("flag",true);
//				return model;
//			}else{
//				getColorForStatus(calibMainData);
//			}
//		}
//		model.addObject("CalibMainData", calibMainData);
//		model.addObject("pRPData", pRPData);
//		return model;
//	}

	@RequestMapping(value = "/detailsEquipment/{identificationNum}" , method = RequestMethod.GET)
	public ModelAndView detailsEquipmentAfterScan(@PathVariable(value="identificationNum") String identityFicationNum , RedirectAttributes redir) throws ParseException{
		logger.info("INSIDE QRAndBarCodeController START METHOD detailsEquipmentAfterScan");
		ModelAndView model = new ModelAndView("QR_BarCode/equipmentDetails");
		ModelAndView model2 = new ModelAndView("redirect:/scanText");
		CalibMainData calibMainData = null;
		PRPData pRPData = null;
		if(identityFicationNum != null){
			calibMainData = qrAndBARCodeIService.detailsForQrAndBarCodeScan(identityFicationNum);
			pRPData = qrAndBARCodeIService.getThePrInformationByIdentyNum(identityFicationNum);
			if(calibMainData == null){
				redir.addFlashAttribute("msg","Identification number does not exist");
				return model2;
			}else{
				getColorForStatus(calibMainData);
			}
		}
		model.addObject("CalibMainData", calibMainData);
		model.addObject("pRPData", pRPData);

		model.addObject("ScannedIdentity", identityFicationNum+" Equipment Details" );
		logger.info("INSIDE QRAndBarCodeController END METHOD detailsEquipmentAfterScan");
		return model;
	}

	private void getColorForStatus(CalibMainData calibMainData) {
		if(calibMainData.getMainCalibrationStatus().equalsIgnoreCase("Inactive")){
			calibMainData.setColorFlag("red");
		}else if(calibMainData.getMainCalibrationStatus().equalsIgnoreCase("Active")){
			calibMainData.setColorFlag("#7cfc00");
		}else if(calibMainData.getMainCalibrationStatus().equalsIgnoreCase("Calibrated")){
			calibMainData.setColorFlag("#7cfc00");
		}else if(calibMainData.getMainCalibrationStatus().equalsIgnoreCase("Scrapped")){
			calibMainData.setColorFlag("red");
		}		
	}


	@RequestMapping(value = "/scannedProductDetails/{identificationNum}" , method = RequestMethod.GET)
	public ModelAndView detailsScanedEquipment(@PathVariable(value="identificationNum") String identityFicationNum) throws ParseException{
		logger.info("INSIDE QRAndBarCodeController Start METHOD detailsEquipmentAfterScan");
		ModelAndView model = new ModelAndView("QR_BarCode/equipmentDetailsScanned");
		CalibMainData calibMainData = null;
		PRPData  pRPData = null;
		if(identityFicationNum != null){
			calibMainData = qrAndBARCodeIService.detailsForQrAndBarCodeScan(identityFicationNum);
			pRPData = qrAndBARCodeIService.getThePrInformationByIdentyNum(identityFicationNum);
			if(calibMainData != null){
				getColorForStatus(calibMainData);
			}
		}
		model.addObject("CalibMainData", calibMainData);
		model.addObject("pRPData", pRPData);
		logger.info("INSIDE QRAndBarCodeController END METHOD detailsEquipmentAfterScan");
		return model;
	}
}
