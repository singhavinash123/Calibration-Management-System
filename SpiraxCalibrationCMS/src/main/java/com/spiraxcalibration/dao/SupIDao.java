package com.spiraxcalibration.dao;

import java.util.List;

import com.spiraxcalibration.models.SUPData;

public interface SupIDao {
	List<SUPData> supGetSuppliersDetails();
	int supAddSupplier(SUPData supData);
	int supUpdateSupplier(SUPData supData);
	SUPData supFindSupplierBySupId(Integer sup_Id);
	List<SUPData> supSearchByCondition(String supplierName, String supplierNumber, String supplierStatus);
	SUPData supShowSupplierDetailBySupplierNum(String supplier_Num);
	List<SUPData> supGetSupplierNameWithId();
}
