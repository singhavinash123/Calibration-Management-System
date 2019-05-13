package com.spiraxcalibration.servicesIMPL;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spiraxcalibration.dao.SupIDao;
import com.spiraxcalibration.models.SUPData;
import com.spiraxcalibration.services.SUPIService;

@Service
public class SUPServiceIMPL implements SUPIService{

	Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	SupIDao supIDao;

	@Override
	public List<SUPData> supGetSuppliersDetails() {
		logger.info("INSIDE SUPServiceIMPL START METHOD supGetSuppliersDetails ::");
		List<SUPData> list =  supIDao.supGetSuppliersDetails();
		logger.info("INSIDE SUPServiceIMPL END METHOD supGetSuppliersDetails ::");
		return list;
	}

	@Override
	public int supAddSupplier(SUPData supData) {
		logger.info("INSIDE SUPServiceIMPL START METHOD supAddSupplier ::");
		int num = supIDao.supAddSupplier(supData);
		logger.info("INSIDE SUPServiceIMPL END METHOD supAddSupplier ::");
		return num;
	}

	@Override
	public int supUpdateSupplier(SUPData supData) {
		logger.info("INSIDE SUPServiceIMPL START METHOD supUpdateSupplier ::");
		int num =  supIDao.supUpdateSupplier(supData);	
		logger.info("INSIDE SUPServiceIMPL END METHOD supUpdateSupplier ::");
		return num;
	}

	@Override
	public SUPData supFindSupplierBySupId(Integer sup_Id) {
		logger.info("INSIDE SUPServiceIMPL START METHOD supFindSupplierBySupId ::");
		SUPData sUPData = supIDao.supFindSupplierBySupId(sup_Id);
		logger.info("INSIDE SUPServiceIMPL END METHOD supFindSupplierBySupId ::");
		return sUPData;
	}

	@Override
	public List<SUPData> supSearchByCondition(String supplierName, String supplierNumber, String supplierStatus) {
		logger.info("INSIDE SUPServiceIMPL START METHOD supSearchByCondition ::");
		String supplierNameTrim =  supplierName.trim();
		String supplierNumberTrim = supplierNumber.trim();
		String supplierStatusTrim = supplierStatus.trim();
		logger.info("INSIDE SUPServiceIMPL END METHOD supSearchByCondition ::");
		return supIDao.supSearchByCondition(supplierNameTrim,supplierNumberTrim,supplierStatusTrim);
	}

	@Override
	public SUPData supShowSupplierDetailBySupplierNum(String supplier_Num) {
		logger.info("INSIDE SUPServiceIMPL START METHOD supShowSupplierDetailBySupplierNum ::");
		SUPData sUPData =  supIDao.supShowSupplierDetailBySupplierNum(supplier_Num);
		logger.info("INSIDE SUPServiceIMPL START METHOD supShowSupplierDetailBySupplierNum ::");
		return sUPData;
	}

	@Override
	public List<SUPData> supGetSupplierNameWithId() {
		logger.info("INSIDE SUPServiceIMPL START METHOD supGetSuppliersDetails ::");
		List<SUPData> list =  supIDao.supGetSupplierNameWithId();
		logger.info("INSIDE SUPServiceIMPL END METHOD supGetSuppliersDetails ::");
		return list;
	}

}
