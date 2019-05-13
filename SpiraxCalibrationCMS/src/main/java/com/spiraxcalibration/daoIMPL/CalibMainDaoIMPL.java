package com.spiraxcalibration.daoIMPL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.spiraxcalibration.WebConfig.AppsPropertyFile;
import com.spiraxcalibration.controllers.WhoColumnsController;
import com.spiraxcalibration.dao.CalibMainIDao;
import com.spiraxcalibration.models.CalibMainData;
import com.spiraxcalibration.models.SUPData;

@Repository
public class CalibMainDaoIMPL implements CalibMainIDao {

	Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	AppsPropertyFile dBQueryPropertyFile = new AppsPropertyFile();

	@Autowired
	JdbcTemplate jdbcTemplate1;

	@Autowired
	WhoColumnsController whoColumnsController;

	@Override
	public int mainSaveCalibDetails(CalibMainData calibMainData) {
		logger.info("INSIDE CalibMainDaoIMPL START METHOD mainSaveCalibDetails");
		String getMaxIdFromMainCalibQuery = dBQueryPropertyFile.getQueryForKey("getMaxIdFromMainCalibQuery");
		String getMaxFromProd = jdbcTemplate1.queryForObject(getMaxIdFromMainCalibQuery, String.class);
		if (getMaxFromProd == null) {
			getMaxFromProd = "0";
		}
		// getting the maximum id from the database....
		int maxId = Integer.parseInt(getMaxFromProd);
		int equipmentId = maxId + 1;
		String ecNumber = calibMainData.getMainEquipmentType();

		// constructing the identification number....
		String identiFicationNumber = ecNumber + equipmentId;
		calibMainData.setMainIdentificationId(identiFicationNumber);

		String getEquipmentTypeByIdQuery = dBQueryPropertyFile.getQueryForKey("getEquipmentTypeByIdQuery");
		String equipmentType = jdbcTemplate1.queryForObject(getEquipmentTypeByIdQuery,
				new Object[] { calibMainData.getMainEquipmentType() }, String.class);
		calibMainData.setMainEquipmentType(equipmentType);

		String getMaxCalib_Id_FromMainCalibQuery = dBQueryPropertyFile
				.getQueryForKey("getMaxCalib_Id_FromMainCalibQuery");
		String getMaxCalib_Id_FromProd = jdbcTemplate1.queryForObject(getMaxCalib_Id_FromMainCalibQuery, String.class);
		if (getMaxCalib_Id_FromProd == null) {
			getMaxCalib_Id_FromProd = "0";
		}
		int max_calib_Id = Integer.parseInt(getMaxCalib_Id_FromProd);
		int calib_id = max_calib_Id + 1;
		calibMainData.setMainEcCalibId(calib_id);

		String getSupplierIdBySupplierNameQuery = dBQueryPropertyFile
				.getQueryForKey("getSupplierIdBySupplierNameQuery");
		try {
			SUPData sUPData = jdbcTemplate1.queryForObject(getSupplierIdBySupplierNameQuery,
					new Object[] { calibMainData.getMainSupplierName() }, new SupplierDataMapper());
			calibMainData.setMainEcSupplierId(sUPData.getSupSupId());
			calibMainData.setMainSupplierNumber(sUPData.getSupSupplierNumber());
		} catch (Exception e) {
			return -3;
		}
		String checkIfSupplierExistQuery = dBQueryPropertyFile.getQueryForKey("checkIfSupplierExistQuery");
		int getIFSupplierNameExist = jdbcTemplate1.queryForObject(checkIfSupplierExistQuery,
				new Object[] { calibMainData.getMainSupplierName() }, Integer.class);
		if (getIFSupplierNameExist <= 0) {
			return -3;
		}

		Map<String, String> whoColumnMap = whoColumnsController.getWhoColumnsInfo();
		if (whoColumnMap != null) {
			calibMainData.setMainCreatedBy((String) whoColumnMap.get("userId"));
			calibMainData.setMainCreatedDate((String) whoColumnMap.get("currentDate"));
		}
		String addMainCalibDetailsQuery = dBQueryPropertyFile.getQueryForKey("addMainCalibDetailsQuery");
		logger.info("INSIDE CalibMainDaoIMPL END METHOD mainSaveCalibDetails");
		return jdbcTemplate1.update(addMainCalibDetailsQuery, calibMainData.getMainInstrumentRequestor(),
				calibMainData.getMainSupplierName(), calibMainData.getMainPurchaseOrder(),
				calibMainData.getMainInvoice(), calibMainData.getMainUnitPrice(), calibMainData.getMainAssetCode(),
				calibMainData.getMainSupplierService(), calibMainData.getMainMake(),
				calibMainData.getMainInstrumentGauge(), calibMainData.getMainModel(), calibMainData.getMainSerial(),
				calibMainData.getMainLeast(), calibMainData.getMainCalibrationStandard(),
				calibMainData.getMainForcastPrice(), calibMainData.getMainEquipmentType(),
				calibMainData.getMainCalibrationType(), calibMainData.getMainAlertFrequency(),
				calibMainData.getMainCalibrationDate(), calibMainData.getMainDueDate(),
				calibMainData.getMainCalibrationStatus(), calibMainData.getMainCalibrationScrapedDate(),
				calibMainData.getMainReminderDate(), calibMainData.getMainAcceptanceCriteria(),
				calibMainData.getMainLocation(), calibMainData.getMainCalibrationAgency(),
				calibMainData.getMainCalibrationFrequency(), calibMainData.getMainCalibrationCertificate(),
				calibMainData.getMainCertificateValidatedBy(), calibMainData.getMainPartCode(),
				calibMainData.getMainIdentificationId(), calibMainData.getMainInstrumentGaugeRange(),
				calibMainData.getMainEcCalibId(), calibMainData.getMainEcSupplierId(),
				calibMainData.getMainCalibrationPrice(), calibMainData.getMainCalibrationCategory(),
				calibMainData.getMainCreatedBy(), calibMainData.getMainCreatedDate(),
				calibMainData.getMainCalibrationResult(), calibMainData.getMainSupplierNumber(),
				calibMainData.getMainCalibrationSource());

	}

	private static final class SupplierDataMapper implements RowMapper<SUPData> {
		@Override
		public SUPData mapRow(ResultSet rs, int rowNum) throws SQLException {
			SUPData sUPData = new SUPData();
			sUPData.setSupSupplierNumber(rs.getString("supplier_number"));
			sUPData.setSupSupId(rs.getInt("Sup_Id"));
			return sUPData;
		}
	}

	@Override
	public CalibMainData mainSearchByCondition(String identiFicationNum) {
		logger.info("INSIDE CalibMainDaoIMPL START METHOD mainSearchByCondition ::");
		String selectMainCalibByConditionQuery = dBQueryPropertyFile.getQueryForKey("selectMainCalibByConditionQuery");
		CalibMainData calibMainData = null;
		try {
			calibMainData = jdbcTemplate1.queryForObject(selectMainCalibByConditionQuery,
					new Object[] { identiFicationNum.trim() }, new MainCalibDataMapper());
		} catch (Exception e) {
			return null;
		}
		logger.info("INSIDE CalibMainDaoIMPL END METHOD mainSearchByCondition ::");
		return calibMainData;
	}

	@Override
	public CalibMainData mainGetProductDetails() {
		logger.info("INSIDE CalibMainDaoIMPL START METHOD mainGetProductDetails ::");
		String selectMainCalibDetailQuery = dBQueryPropertyFile.getQueryForKey("selectMainCalibDetailQuery");
		CalibMainData mainCalibData = null;
		try {
			mainCalibData = jdbcTemplate1.queryForObject(selectMainCalibDetailQuery, new MainCalibDataMapper());
		} catch (Exception e) {
			return null;
		}
		logger.info("INSIDE CalibMainDaoIMPL END METHOD mainGetProductDetails ::");
		return mainCalibData;
	}

	private static final class MainCalibDataMapper implements RowMapper<CalibMainData> {
		@Override
		public CalibMainData mapRow(ResultSet rs, int rowNum) throws SQLException {
			CalibMainData calibMainData = new CalibMainData();
			calibMainData.setMainInstrumentRequestor(rs.getString("Instrument_Requestor"));
			calibMainData.setMainSupplierName(rs.getString("Supplier_Name"));
			calibMainData.setMainPurchaseOrder(rs.getString("Purchase_Order"));
			calibMainData.setMainInvoice(rs.getString("Invoice"));
			calibMainData.setMainUnitPrice(rs.getString("Unit_Price"));
			calibMainData.setMainAssetCode(rs.getString("AssetCode"));
			calibMainData.setMainSupplierService(rs.getString("SupplierService"));
			calibMainData.setMainMake(rs.getString("Make"));
			calibMainData.setMainInstrumentGauge(rs.getString("Instrument_Gauge"));
			calibMainData.setMainModel(rs.getString("Model"));
			calibMainData.setMainSerial(rs.getString("Serial_Num"));
			calibMainData.setMainLeast(rs.getString("Least"));
			calibMainData.setMainCalibrationStandard(rs.getString("Calibration_Standard"));
			calibMainData.setMainForcastPrice(rs.getString("Forcast_Price"));
			calibMainData.setMainEquipmentType(rs.getString("Equipment_Type"));
			calibMainData.setMainCalibrationType(rs.getString("Calibration_Type"));
			calibMainData.setMainAlertFrequency(rs.getString("Alert_Frequency"));
			calibMainData.setMainCalibrationDate(rs.getString("Calibration_Date"));
			calibMainData.setMainDueDate(rs.getString("Due_Date"));
			calibMainData.setMainCalibrationStatus(rs.getString("Calibration_Status"));
			calibMainData.setMainCalibrationScrapedDate(rs.getString("Calibration_Scraped_Date"));
			calibMainData.setMainReminderDate(rs.getString("Reminder_Date"));
			calibMainData.setMainAcceptanceCriteria(rs.getString("Acceptance_Criteria"));
			calibMainData.setMainLocation(rs.getString("Location"));
			calibMainData.setMainCalibrationAgency(rs.getString("Calibration_Agency"));
			calibMainData.setMainCalibrationFrequency(rs.getString("Calibration_Frequency"));
			calibMainData.setMainCalibrationCertificate(rs.getString("Calibration_Certificate"));
			calibMainData.setMainCertificateValidatedBy(rs.getString("Certificate_ValidatedBy"));
			calibMainData.setMainPartCode(rs.getString("Part_Code"));
			calibMainData.setMainIdentificationId(rs.getString("EC_identification_id"));
			calibMainData.setMainEcCalibId(rs.getInt("EC_Calib_Id"));
			calibMainData.setMainEcId(rs.getInt("EC_Id"));
			calibMainData.setMainEcSupplierId(rs.getInt("EC_Supplier_Id"));
			calibMainData.setMainSupplierNumber(rs.getString("sup_number"));
			calibMainData.setMainCalibrationCategory(rs.getString("Calibration_Category"));
			calibMainData.setMainCalibrationPrice(rs.getString("calibration_Price"));
			calibMainData.setMainInstrumentGaugeRange(rs.getString("Equipment_Range"));
			calibMainData.setMainCalibrationResult(rs.getString("calib_result"));
			calibMainData.setMainOldIdentificationId(rs.getString("old_identification_number"));
			calibMainData.setMainCalibrationSource(rs.getString("calib_source_type"));
			return calibMainData;
		}
	}

	@Override
	public int mainUpdateCalibDetails(CalibMainData calibMainData) {
		logger.info("INSIDE CalibMainDaoIMPL START METHOD mainUpdateCalibDetails");
		Map<String, String> whoColumnMap = whoColumnsController.getWhoColumnsInfo();
		if (whoColumnMap != null) {
			calibMainData.setMainUpdatedBy((String) whoColumnMap.get("userId"));
			calibMainData.setMainUpdatedDate((String) whoColumnMap.get("currentDate"));
		}
		String getSupplierIdBySupplierNameQuery = dBQueryPropertyFile
				.getQueryForKey("getSupplierIdBySupplierNameQuery");
		try {
			
			System.out.println("sup data  test in sup name ::"+calibMainData.getMainSupplierName());

			SUPData sUPData = jdbcTemplate1.queryForObject(getSupplierIdBySupplierNameQuery,
					new Object[] { calibMainData.getMainSupplierName().trim()}, new SupplierDataMapper());
			
			calibMainData.setMainEcSupplierId(sUPData.getSupSupId());
			calibMainData.setMainSupplierNumber(sUPData.getSupSupplierNumber());
			
			System.out.println("sup data  test in sup id ::"+sUPData.getSupSupId());
			System.out.println("sup data  test sun number ::"+sUPData.getSupSupplierNumber());


		} catch (Exception e) {
			System.out.println("sup data exception ::");

			return -3;
		}
		String updateMainCalibQuery = dBQueryPropertyFile.getQueryForKey("updateMainCalibQuery");
		logger.info("INSIDE CalibMainDaoIMPL END METHOD mainUpdateCalibDetails");
		int num =  jdbcTemplate1.update(updateMainCalibQuery, calibMainData.getMainInstrumentRequestor(),
				calibMainData.getMainSupplierName(), calibMainData.getMainPurchaseOrder(),
				calibMainData.getMainInvoice(), calibMainData.getMainUnitPrice(), calibMainData.getMainAssetCode(),
				calibMainData.getMainSupplierService(), calibMainData.getMainMake(),
				calibMainData.getMainInstrumentGauge(), calibMainData.getMainModel(), calibMainData.getMainSerial(),
				calibMainData.getMainLeast(), calibMainData.getMainCalibrationStandard(),
				calibMainData.getMainForcastPrice(), calibMainData.getMainCalibrationType(),
				calibMainData.getMainAlertFrequency(), calibMainData.getMainCalibrationDate(),
				calibMainData.getMainDueDate(), calibMainData.getMainCalibrationStatus(),
				calibMainData.getMainCalibrationScrapedDate(), calibMainData.getMainReminderDate(),
				calibMainData.getMainAcceptanceCriteria(), calibMainData.getMainLocation(),
				calibMainData.getMainCalibrationAgency(), calibMainData.getMainCalibrationFrequency(),
				calibMainData.getMainCalibrationCertificate(), calibMainData.getMainCertificateValidatedBy(),
				calibMainData.getMainPartCode(), calibMainData.getMainInstrumentGaugeRange(),
				calibMainData.getMainCalibrationPrice(), calibMainData.getMainCalibrationCategory(),
				calibMainData.getMainUpdatedBy(), calibMainData.getMainUpdatedDate(),
				calibMainData.getMainCalibrationResult(), calibMainData.getMainOldIdentificationId(),
				calibMainData.getMainEcSupplierId(), calibMainData.getMainSupplierNumber(),
				calibMainData.getMainCalibrationSource(),
				calibMainData.getMainIdentificationId());
		System.out.println("num in dao :::"+num);
		return num;
	}

	@Override
	public List<CalibMainData> getEquipmentCalibList() {
		logger.info("INSIDE CalibMainDaoIMPL START METHOD mainGetProductDetails ::");
		String selectAllMainEquipCalibListQuery = dBQueryPropertyFile.getQueryForKey("selectAllMainEquipCalibListQuery");
		List<CalibMainData> mainEquipmentCalibList = null;
		try {
			mainEquipmentCalibList = jdbcTemplate1.query(selectAllMainEquipCalibListQuery,new MainCalibDataMapper());
		} catch (Exception e) {
			return null;
		}
		System.out.println("mainEquipmentCalibList :::" + mainEquipmentCalibList);
		return mainEquipmentCalibList;
	}

	@Override
	public List<CalibMainData> getEquipmentList(int lowerLimit, int upperLimit) {
		logger.info("INSIDE CalibMainDaoIMPL START METHOD mainGetProductDetails ::");
	    String selectMainEquipCalibListQuery = dBQueryPropertyFile.getQueryForKey("selectMainEquipCalibListQuery");
		List<CalibMainData> mainEquipmentCalibList = null;
		try {
			mainEquipmentCalibList = jdbcTemplate1.query(String
					.format(selectMainEquipCalibListQuery, lowerLimit, upperLimit),
					new MainCalibDataMapper());
		} catch (Exception e) {
			return null;
		}
		System.out.println("mainEquipmentCalibList :::" + mainEquipmentCalibList);
		return mainEquipmentCalibList;
	}

	@Override
	public List<CalibMainData> getEquipmentByIdentity(String identityNum) {
		logger.info("INSIDE CalibMainDaoIMPL START METHOD getEquipmentByIdentity ::");
	    String selectMainEquipCalibListByIdentityQuery = dBQueryPropertyFile.getQueryForKey("selectMainEquipCalibListByIdentityQuery");
	    List<CalibMainData> mainEquipmentCalibList = null;
		try {
			mainEquipmentCalibList = jdbcTemplate1.query(selectMainEquipCalibListByIdentityQuery, new Object[]{identityNum} ,new MainCalibDataMapper());
		} catch (Exception e) {
			return null;
		}
		return mainEquipmentCalibList;
	}

}
