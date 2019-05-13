<%@page import="com.spiraxcalibration.WebConfig.AppsPropertyFile"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Bootstrap CSS CDN -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="customcss/validationsError.css">

<script defer
	src="https://use.fontawesome.com/releases/v5.0.13/js/solid.js"></script>
<script defer
	src="https://use.fontawesome.com/releases/v5.0.13/js/fontawesome.js"></script>

<script type="text/javascript">
	function CheckStatus(val) {
		var element = document.getElementById('instrumentStatus');
		if (val == 'others')
			element.style.display = 'block';
		else
		   element.style.display = 'none';
		   document.getElementById("instrumentStatus").value = val;
	};
	function CheckCategory(val) {
		var element = document.getElementById('category');
		if (val == 'others')
			element.style.display = 'block';
		else
		  element.style.display = 'none';
		  document.getElementById("category").value = val;

	};
	function CheckAgency(val) {
		var element = document.getElementById('calibrationAgency');
		if (val == 'others')
			element.style.display = 'block';
		else
			element.style.display = 'none';
		document.getElementById("calibrationAgency").value = val;
	};
	function CheckLocation(val) {
		var element = document.getElementById('location');
		if (val == 'others')
			element.style.display = 'block';
		else
			element.style.display = 'none';
		document.getElementById("location").value = val;
	};
	function CheckStandard(val) {
		var element = document.getElementById('clibrationStandard');
		if (val == 'others')
			element.style.display = 'block';
		else
			element.style.display = 'none';
		document.getElementById("clibrationStandard").value = val;
	};
	function Checkmake(val) {
		var element = document.getElementById('make');
		if (val == 'others')
			element.style.display = 'block';
		else
			element.style.display = 'none';
		document.getElementById("make").value = val;

	};
	function CheckEquipType(val) {
		var element = document.getElementById('equiptype');
		document.getElementById("equiptype").value = val;

	};
	function equipType() {
		var x = document.getElementById('equipmentType').value
		document.getElementById("equiptype").value = x;

	}
</script>

</head>
<body onload="equipType()">

	<div id="header">
		<jsp:include page="/WEB-INF/jsp/header/header.jsp" />
	</div>
	<br>

	<div class="container-fluid">
		<span>You are here : </span>
		<%
			AppsPropertyFile appsPropertyFile = new AppsPropertyFile();
		%>
		<span><a href=<%=appsPropertyFile.getURLForKey("homePageURL")%>>Home</a></span>
		<span>/</span> <span><a
			href=<%=appsPropertyFile.getURLForKey("productListURL")%>>Equipment
				List</a></span> <span>/</span> <span><a href="">${addOrUpdate}</a></span>
		<h3 align="center">
			<b>${addORupdatePrheading}</b>
		</h3>
		<br>
		<div align="center">
			<b style="color: green; font-size: 15px;">${msg}</b>
		</div>
		<div align="center">
			<b style="color: red; font-size: 15px;">${alreadyExist}</b>
		</div>
		<br>
		<spring:url value="/saveEquipment" var="saveURL" />
		<form:form modelAttribute="equipmentForm" method="post"
			action="${saveURL}" style="font-size:12px;">
			<form:hidden path="prProdId" />
			<form:hidden path="prIdentificationNo" />
			<div class="row" align="left">

				<div class="col-sm-3">
					<div class="form-group">
						<label for="usr">Equipment Type* :</label>
						<form:select path="prEquipmentType" class="form-control"
							name="equipmentdesc" onchange='CheckEquipType(this.value);'
							id="equipmentType">
							<c:forEach items="${equipmentDescWithNumber}" var="equipmentdesc">
								<option value="${equipmentdesc.getValue()}">${equipmentdesc.getKey()}</option>
							</c:forEach>
						</form:select>
						<div>
							<form:errors path="prEquipmentType" cssClass="error"></form:errors>
						</div>
					</div>
				</div>

				<div class="col-sm-3">
					<div class="form-group">
						<label for="usr">Equipment Code :</label>
						<div class="input-group">
							<div class="input-group-prepend"></div>
							<form:input readonly="true" path="" type="text"
								class="form-control" id="equiptype" />
						</div>
					</div>
				</div>

				<div class="col-sm-3" style="display: none;">
					<div class="form-group">
						<label for="usr">Identification No.* :</label>
						<form:input readonly="true" path="" class="form-control" />
						<div>
							<form:errors path="prIdentificationNo" cssClass="error"></form:errors>
						</div>
					</div>
				</div>

				<div class="col-sm-3">
					<div class="form-group">
						<label for="usr">serial Number* :</label>
						<form:input path="prSerialNo" class="form-control" />
						<div>
							<form:errors path="prSerialNo" cssClass="error"></form:errors>
						</div>
					</div>
				</div>

				<div class="col-sm-3">
					<div class="form-group">

						<label for="usr">Supplier Name :</label>
						<form:select path="prSupplierName" class="form-control"
							name="prSupplierName">
							<c:forEach items="${supplierNameLookup}" var="supplierName">
								<c:choose>
									<c:when test="${equipmentForm.prSupplierName == supplierName}">
										<option value="${equipmentForm.prSupplierName}" selected>${equipmentForm.prSupplierName}</option>
									</c:when>
									<c:otherwise>
										<option value="${supplierName}">${supplierName}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</form:select>
						<div>
							<form:errors path="prSupplierName" cssClass="error"></form:errors>
						</div>
					</div>
				</div>

				<div class="col-sm-3">
					<div class="form-group">
						<label for="usr">Equipment Status* :</label>
						<form:select path="prEquipmentStatus" class="form-control"
							name="equipmentstatus">
							<c:forEach items="${equipmentStatusLookup}" var="equipmentstatus">
								<c:choose>
									<c:when
										test="${equipmentForm.prEquipmentStatus == equipmentstatus}">
										<option value="${equipmentForm.prEquipmentStatus}" selected>${equipmentForm.prEquipmentStatus}</option>
									</c:when>
									<c:otherwise>
										<option value="${equipmentstatus}">${equipmentstatus}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</form:select>
						<div>
							<form:errors path="prEquipmentStatus" cssClass="error"></form:errors>
						</div>
					</div>
				</div>

				<div class="col-sm-3">
					<div class="form-group">
						<label for="usr">Description* :</label>
						<form:input path="prDescription" class="form-control" />
						<div>
							<form:errors path="prDescription" cssClass="error"></form:errors>
						</div>
					</div>
				</div>
				<!-- 
				<div class="col-sm-3">
					<div class="form-group">
						<label for="usr">Identity Number* :</label>
						<form:input readonly="${disabled}" path="prIdentificationNo"
							class="form-control" />
						<div>
							<form:errors path="prIdentificationNo" cssClass="error"></form:errors>
						</div>
					</div>
				</div>
 -->
				<!-- 
			<div class="col-sm-3">
					<div class="form-group">
						<label for="usr">Identification Number* :</label>
						<div class="input-group">
							<div class="input-group-prepend"></div>
							<form:input readonly="true" path="prEquipmentType"
								type="text" class="form-control" name="prEquipmentType" id="equiptype"/>
								
							<form:input readonly="${disabled}" path="prNumber"
								type="text" class="form-control" name="number" placeholder="Enter Number.."/>
						</div>
					</div>
				</div>
			 -->

			</div>
			<div class="row" align="left">
				<div class="col-sm-3">
					<div class="form-group">

						<label for="usr">Least Count :</label>
						<form:input path="prLeastCount" class="form-control" />
					</div>
				</div>

				<div class="col-sm-3">
					<div class="form-group">
						<label for="usr">Category :</label>

						<form:select path="" class="form-control" name="category"
							onchange='CheckCategory(this.value);'>
							<option value="${equipmentForm.prCategories}">${equipmentForm.prCategories}</option>
							<c:forEach items="${categoryLookup}" var="category">
								<option value="${category}">${category}</option>
							</c:forEach>

							<option value="others" class="form-control">others</option>
						</form:select>

						<form:input path="prCategories" type="text" name="category"
							id="category" style='display: none;' class="form-control" />
						<div>
							<form:errors path="prCategories" cssClass="error"></form:errors>
						</div>
					</div>
				</div>

				<div class="col-sm-3">
					<div class="form-group">
						<label for="usr">Category Agency:</label>
						<form:select path="" class="form-control" name="calibrationAgency"
							onchange='CheckAgency(this.value);'>
							<option value="${equipmentForm.prCalibrationAgency}">${equipmentForm.prCalibrationAgency}</option>
							<c:forEach items="${calibrationAgencyLookup}"
								var="calibrationAgency">
								<option value="${calibrationAgency}">${calibrationAgency}</option>
							</c:forEach>
							<option value="others">others</option>
						</form:select>
						<form:input path="prCalibrationAgency" type="text"
							name="calibrationAgency" id="calibrationAgency"
							style='display: none;' class="form-control"  />
							<div>
							<form:errors path="prCategories" cssClass="error"></form:errors>
						</div>
					</div>
				</div>
				<div class="col-sm-3">
					<div class="form-group">
						<label for="usr">Product Location :</label>
						<form:select path="" class="form-control" name="location"
							onchange='CheckLocation(this.value);'>
							<option value="${equipmentForm.prEquipmentLocation}">${equipmentForm.prEquipmentLocation}</option>

							<c:forEach items="${equipmentLocationLookup}" var="location">
								<c:choose>
									<c:when test="${equipmentForm.prEquipmentLocation == location}">
										<option value="${equipmentForm.prEquipmentLocation}" selected>${equipmentForm.prEquipmentLocation}</option>
									</c:when>
									<c:otherwise>
										<option value="${location}">${location}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							<option value="others">others</option>
						</form:select>
						<form:input path="prEquipmentLocation" type="text" name="location"
							id="location" style='display: none;' class="form-control" />
					</div>
				</div>
			</div>

			<div class="row" align="left">

				<div class="col-sm-3">
					<div class="form-group">
						<label for="usr">Borrower :</label>
						<form:input path="prBorrower" class="form-control" />
					</div>
				</div>

				<div class="col-sm-3">
					<div class="form-group">
						<label for="usr">Make :</label>
						<form:select path="" class="form-control" name="make"
							onchange='Checkmake(this.value);'>
							<option value="${equipmentForm.prMake}">${equipmentForm.prMake}</option>
							<c:forEach items="${makeLookup}" var="make">
								<c:choose>
									<c:when test="${equipmentForm.prMake == make}">
										<option value="${equipmentForm.prMake}" selected>${equipmentForm.prMake}</option>
									</c:when>
									<c:otherwise>
										<option value="${make}">${make}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							<option value="others" class="form-control">others</option>
						</form:select>
						<form:input path="prMake" type="text" name="make" id="make"
							style='display: none;' class="form-control" />
					</div>
				</div>
				<div class="col-sm-3">
					<div class="form-group">

						<label for="usr">Model :</label>
						<form:input path="prModel" class="form-control" />
					</div>
				</div>
				<div class="col-sm-3">
					<div class="form-group">
						<label for="usr">Calibration Standard :</label>
						<form:select path="" class="form-control"
							name="clibrationStandard" onchange='CheckStandard(this.value);'>
							<option value="${equipmentForm.prCalibrationStd}">${equipmentForm.prCalibrationStd}</option>
							<c:forEach items="${clibrationStandardLookup}"
								var="clibrationStandard">
								<option value="${clibrationStandard}">${clibrationStandard}</option>
							</c:forEach>
							<option value="others" class="form-control">others</option>
						</form:select>
						<form:input path="prCalibrationStd" type="text"
							name="clibrationStandard" id="clibrationStandard"
							style='display: none;' class="form-control" />
					</div>
				</div>
			</div>

			<div class="row" align="left">
				<div class="col-sm-3">
					<div class="form-group">
						<label for="usr">Supplier Measurement Result :</label>
						<form:input path="prsupplierMeasuredResult" class="form-control" />
					</div>
				</div>

				<div class="col-sm-3">
					<div class="form-group">

						<label for="usr">Instrument Line Item :</label>
						<form:input path="prInstrumentLineItem" class="form-control" />
					</div>
				</div>
				<div class="col-sm-3">
					<div class="form-group">
						<label for="usr">Instrument Status :</label>
						<form:select path="" class="form-control" name="instrumentStatus"
							onchange='CheckStatus(this.value);'>
							<c:choose>
								<c:when
									test="${equipmentForm.prInstrumentStatus == instrumentStatus}">
									<option value="${equipmentForm.prInstrumentStatus}" selected>${equipmentForm.prInstrumentStatus}</option>
								</c:when>
								<c:otherwise>
									<option value="${instrumentStatus}">${instrumentStatus}</option>
								</c:otherwise>
							</c:choose>
							<option value="others" class="form-control">others</option>
						</form:select>
						<form:input path="prInstrumentStatus" type="text"
							name="instrumentStatus" id="instrumentStatus"
							style='display: none;' />
					</div>
				</div>
				<div class="col-sm-3">
					<div class="form-group">
						<div class="form-group">
							<label for="usr">Range :</label>
							<form:input path="prPRODRange" class="form-control" />
						</div>
					</div>
				</div>
			</div>
			<br>
			<div class="row" align="center">
				<div class="col-md-4"></div>
				<div class="col-md-4">
					<button type="submit" class="btn btn-primary">Save</button>
				</div>
				<div class="col-md-4"></div>
			</div>
			<br>
		</form:form>
	</div>
</body>
</html>