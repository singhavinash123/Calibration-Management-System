<%@page import="java.util.List"%>
<%@page import="com.spiraxcalibration.controllers.PrController"%>
<%@page import="com.spiraxcalibration.WebConfig.AppsPropertyFile"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<!-- Bootstrap CSS CDN -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">

<!-- Our Custom CSS -->
<link rel="stylesheet" href="customcss/style.css">
<link rel="stylesheet" href="customcss/search.css">
<link rel="stylesheet" href="customcss/equipmentList.css">
<link rel="stylesheet" href="customcss/htmltable.css">

<!-- Scrollbar Custom CSS -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.5/jquery.mCustomScrollbar.min.css">

<!-- Font Awesome JS -->
<script defer
	src="https://use.fontawesome.com/releases/v5.0.13/js/solid.js"></script>

<script defer
	src="https://use.fontawesome.com/releases/v5.0.13/js/fontawesome.js"></script>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<script src="static/bootstrap/js/jquery-3.3.1.js"></script>
<script src="static/bootstrap/js/bootstrap.min.js"></script>
<script src="static/bootstrap/js/jquery-1.7.1.min.js"></script>
<script language="javascript">
	function DoPost() {
		alert("hii");
		$().redirect("/details_equipment_id/", {
			name : "val"
		}); //Your values here..
	}
</script>
<spring:url value="bootstrap/css/bootstrap.min.css"
	var="bootstrapCssURL"></spring:url>
<link href="${bootstrapCssURL}" rel="stylesheet">

<link rel="stylesheet" href="customcss/formCss.css">

</head>
<body>

	<div id="header">
		<jsp:include page="/WEB-INF/jsp/header/header.jsp" />
	</div>
	<div align="center">
		<h3 id="formtitle">Equipment List</h3>
	</div>
	<div class="container-fluid">
		<span id="hereCss">You are here : </span>
		<%
			AppsPropertyFile appsPropertyFile = new AppsPropertyFile();
		%>
		<span><a href=<%=appsPropertyFile.getURLForKey("homePageURL")%>
			id="hrefCss">Home</a></span><span> /</span> <span><a
			href=<%=appsPropertyFile.getURLForKey("masterViewPage")%>
			id="hrefCss">Equipment List</a></span>
		<div style="overflow-x: auto;">
			<spring:url value="/products_list" var="listURL" />
			<display:table name="equipmentList" id="displaytable"
				requestURI="${listURL}" pagesize="20" class="table" export="true"
				sort="list">

				<display:column title="Sr. no.">
					<c:out value="${displaytable_rowNum}" />
				</display:column>

				<display:column title="Identification Number" media="html"
					style="white-space:nowrap;">
					<form:form method="Post" action="/search_calib_equipment">
					 <input type="hidden" name="identiFicationNum" value="${displaytable.mainIdentificationId}" class="form-control">
						<input type="submit" value="${displaytable.mainIdentificationId}"class="btn btn-info" style="color: blue;background-color: #FFFFFF;"/>
					</form:form>
				</display:column>

				<display:column property="mainInstrumentRequestor"
					title="Instrument Requestor" style="white-space:nowrap;"></display:column>
				<display:column property="mainSupplierNumber"
					title="Supplier Number" style="white-space:nowrap;"></display:column>
				<display:column property="mainSupplierName" title="Supplier Name"
					style="white-space:nowrap;"></display:column>
				<display:column property="mainPurchaseOrder" title="Purchase Order"
					style="white-space:nowrap;"></display:column>
				<display:column property="mainInvoice" title="Supplier Invoice"
					style="white-space:nowrap;"></display:column>
				<display:column property="mainUnitPrice" title="Unit Price"
					style="white-space:nowrap;"></display:column>
				<display:column property="mainAssetCode" title="Capex Code"
					style="white-space:nowrap;"></display:column>
				<display:column property="mainSupplierService"
					title="Supplier Service" style="white-space:nowrap;"></display:column>
				<display:column property="mainMake" title="Make"
					style="white-space:nowrap;"></display:column>
				<display:column property="mainInstrumentGauge"
					title="Instrument Gauge Description" style="white-space:nowrap;"></display:column>
				<display:column property="mainModel" title="Model"
					style="white-space:nowrap;"></display:column>
				<display:column property="mainSerial" title="Serial"
					style="white-space:nowrap;"></display:column>
				<display:column property="mainLeast"
					title="Least Count/Accuracy Specification"
					style="white-space:nowrap;"></display:column>
				<display:column property="mainCalibrationStandard"
					title="Calibration Standard Used" style="white-space:nowrap;"></display:column>
				<display:column property="mainForcastPrice" title="Forecast Price"
					style="white-space:nowrap;"></display:column>
				<display:column property="mainInstrumentGaugeRange"
					title="Instrument/Guage Range" style="white-space:nowrap;"></display:column>
				<display:column property="mainEquipmentType" title="Equipment Type"
					style="white-space:nowrap;"></display:column>
				<display:column property="mainCalibrationType"
					title="Calibration Type" style="white-space:nowrap;"></display:column>
				<display:column property="mainAlertFrequency"
					title="Alert Frequency" style="white-space:nowrap;"></display:column>
				<display:column property="mainCalibrationDate"
					title="Calibration Date" style="white-space:nowrap;"></display:column>
				<display:column property="mainDueDate" title="Due Date"
					style="white-space:nowrap;"></display:column>
				<display:column property="mainCalibrationStatus"
					title="Calibration Status" style="white-space:nowrap;"></display:column>
				<display:column property="mainCalibrationScrapedDate"
					title="Scraped Date" style="white-space:nowrap;"></display:column>
				<display:column property="mainReminderDate" title="Reminder Date"
					style="white-space:nowrap;"></display:column>
				<display:column property="mainAcceptanceCriteria"
					title="Acceptance Criteria" style="white-space:nowrap;"></display:column>
				<display:column property="mainCalibrationPrice"
					title="Calibration Price" style="white-space:nowrap;"></display:column>
				<display:column property="mainLocation" title="Location"
					style="white-space:nowrap;"></display:column>
				<display:column property="mainCalibrationCategory"
					title="Calibration Category" style="white-space:nowrap;"></display:column>
				<display:column property="mainCalibrationAgency"
					title="Calibration Agency" style="white-space:nowrap;"></display:column>
				<display:column property="mainCalibrationFrequency"
					title="Calibration Frequency" style="white-space:nowrap;"></display:column>
				<display:column property="mainCalibrationCertificate"
					title="Calibration Certificate" style="white-space:nowrap;"></display:column>
				<display:column property="mainCertificateValidatedBy"
					title="Certificate Validated By" style="white-space:nowrap;"></display:column>
				<display:column property="mainPartCode" title="Part Code"></display:column>
				
				<display:column property="mainCalibrationResult" title="Calibration Result" style="white-space:nowrap;">></display:column>
				<display:column property="mainOldIdentificationId" title="Calibration Ref. No." style="white-space:nowrap;">></display:column>
		    	<display:column property="mainCalibrationSource" title="Calibration Source Type" style="white-space:nowrap;">></display:column>
				
				
				<display:column property="mainIdentificationId"
					title="Identification Number" media="html"
					style="white-space:nowrap;">
				</display:column>
				<display:setProperty name="export.csv.filename"
					value="MasterReport.csv" />
				<display:setProperty name="export.excel.filename"
					value="MasterReport.xls" />
				<display:setProperty name="export.xml.filename"
					value="MasterReport.xml" />

			</display:table>

			<spring:url value="bootstrap/js/bootstrap.min.js" var="boostrapJsURL"></spring:url>
			<spring:url value="bootstrap/js/jquery-1.7.1.min.js"
				var="jqueryJsURL"></spring:url>

			<script src="${boostrapJsURL}"></script>
			<script src="${jqueryJsURL}"></script>

		</div>
	</div>
</body>
<script>
	function ConfirmDelete(value) {
		console.log("Inside ConfirmDelete Method" + value);
		var x = confirm("Are you sure you want to delete " + value + " ??");
		if (x)
			return true;
		else
			return false;
	}
</script>
</html>