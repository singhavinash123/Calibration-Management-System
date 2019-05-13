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
<!-- Bootstrap CSS CDN -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">

<!-- Our Custom CSS -->
<link rel="stylesheet" href="customcss/style.css">
<link rel="stylesheet" href="customcss/search.css">
<link rel="stylesheet" href="customcss/equipmentList.css">
<link rel="stylesheet" href="customcss/htmltable.css">

<style>
#wrapCss {
	white-space: nowrap;
	text-align: center;
	border: 1px solid black;
	padding: 4px 4px;
}

tbody td:nth-child(even) {
	background-color: #D3D3D3;
	font-family: Cambria;
	font-size: 16px;
}

tbody td:nth-child(odd) {
	background-color: #20B2AA;
	font-family: Cambria;
	font-size: 16px;
}

#identityBtn {
	background-color: #D3D3D3;
	color: green;
	padding: 0;
	border: none;
}
</style>

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
<script>
	var tableToExcel = (function() {
		var uri = 'data:application/vnd.ms-excel;base64,', template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><table>{table}</table></body></html>', base64 = function(
				s) {
			return window.btoa(unescape(encodeURIComponent(s)))
		}, format = function(s, c) {
			return s.replace(/{(\w+)}/g, function(m, p) {
				return c[p];
			})
		}
		return function(table, name) {
			if (!table.nodeType)
				table = document.getElementById(table)

			var tableHtml = document.getElementById('tblProductList').innerHTML;
			var modifiedTable = $('<table/>').html(tableHtml);
			modifiedTable.find('.nothide').remove();
			console.log(modifiedTable.html());

			var ctx = {
				worksheet : name || 'Worksheet',
				table : modifiedTable.html()
			}
			window.location.href = uri + base64(format(template, ctx))
		}
	})()
</script>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						var currentPageNumber = 1;
						var count = 0;
						var flag = 1;
						loadData(currentPageNumber);

						$("#next_button_id").click(function() {
							if (flag == 1) {
								currentPageNumber = currentPageNumber + 1;
								loadData(currentPageNumber);
							}
						});

						$("#last_button_id").click(function() {
							var newProductTable = $('#tblProductList tbody');
							$('#tblProductList tbody').empty();
							count = 0;
							flag = -1;
							loadData(flag);
						});

						$("#searchBtn")
								.click(
										function() {
											var identityNum = document
													.getElementById('idNum').value;
											if (identityNum == "") {
												alert("Please enter identification number!!");
											} else {
												loadIdentityNum(identityNum
														.trim());

											}
										});

						function loadIdentityNum(identityNum) {
							var dataCount = 0;
							$
									.ajax({
										url : '/product_master_list_by_id/'
												+ identityNum,
										method : 'GET',
										dataType : 'json',
										success : function(data) {
											var newProductTable = $('#tblProductList tbody');
											$('#tblProductList tbody').empty();
											if (data == "" || data == null) {
												alert("No record found for "
														+ identityNum
														+ " number");
											}

											$(data)
													.each(
															function(index,
																	product) {
																newProductTable
																		.append('<tr id="myRow"><td>'
																				+ (++dataCount)
																				+ '</td><td id="wrapCss" class="nothide">'
																				+ ' <form method="Post" action="/search_calib_equipment"> '
																				+ "<input type='hidden' name='identiFicationNum' value='"+product.mainIdentificationId+"' class='form-control'>"
																				+ '<input type="submit" value="'+product.mainIdentificationId+'" class="btn btn-info" id="identityBtn"/>'
																				+ '<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />'
																				+ '</form>'

																				+ '</td><td id="wrapCss">'
																				+ product.mainInstrumentRequestor
																				+ '</td><td id="wrapCss">'
																				+ product.mainSupplierNumber
																				+ '</td><td id="wrapCss">'
																				+ product.mainSupplierName
																				+ '</td><td id="wrapCss">'
																				+ product.mainPurchaseOrder
																				+ '</td><td id="wrapCss">'
																				+ product.mainInvoice
																				+ '</td><td id="wrapCss">'
																				+ product.mainUnitPrice
																				+ '</td><td id="wrapCss">'
																				+ product.mainAssetCode
																				+ '</td><td id="wrapCss">'
																				+ product.mainSupplierService
																				+ '</td><td id="wrapCss">'
																				+ product.mainMake
																				+ '</td><td id="wrapCss">'
																				+ product.mainInstrumentGauge
																				+ '</td><td id="wrapCss"">'
																				+ product.mainModel
																				+ '</td><td id="wrapCss">'
																				+ product.mainSerial
																				+ '</td><td id="wrapCss">'
																				+ product.mainLeast
																				+ '</td><td id="wrapCss">'
																				+ product.mainCalibrationStandard
																				+ '</td><td id="wrapCss">'
																				+ product.mainForcastPrice
																				+ '</td><td id="wrapCss">'
																				+ product.mainInstrumentGaugeRange
																				+ '</td><td id="wrapCss">'
																				+ product.mainEquipmentType
																				+ '</td><td id="wrapCss">'
																				+ product.mainCalibrationType
																				+ '</td><td id="wrapCss">'
																				+ product.mainAlertFrequency
																				+ '</td><td id="wrapCss">'
																				+ product.mainCalibrationDate
																				+ '</td><td id="wrapCss">'
																				+ product.mainDueDate
																				+ '</td><td id="wrapCss">'
																				+ product.mainCalibrationStatus
																				+ '</td><td id="wrapCss">'
																				+ product.mainCalibrationScrapedDate
																				+ '</td><td id="wrapCss">'
																				+ product.mainReminderDate
																				+ '</td><td id="wrapCss">'
																				+ product.mainAcceptanceCriteria
																				+ '</td><td id="wrapCss">'
																				+ product.mainCalibrationPrice
																				+ '</td><td id="wrapCss">'
																				+ product.mainLocation
																				+ '</td><td id="wrapCss">'
																				+ product.mainCalibrationCategory
																				+ '</td><td id="wrapCss">'
																				+ product.mainCalibrationAgency
																				+ '</td><td id="wrapCss">'
																				+ product.mainCalibrationFrequency
																				+ '</td><td id="wrapCss">'
																				+ product.mainCalibrationCertificate
																				+ '</td><td id="wrapCss">'
																				+ product.mainCertificateValidatedBy
																				+ '</td><td id="wrapCss">'
																				+ product.mainPartCode
																				+ '</td><td id="wrapCss">'
																				+ product.mainCalibrationResult
																				+ '</td><td id="wrapCss">'
																				+ product.mainOldIdentificationId
																				+ '</td><td id="wrapCss">'
																				+ product.mainCalibrationSource
																				+ '</td><td id="wrapCss">'
																				+ product.mainIdentificationId
																				+ '</td><td>');
															})

										},
										error : function(jqXHR, textStatus,
												errorThrown) {
											alert(textStatus);
											if (jqXHR.status == 500) {
												alert('Internal error: '
														+ jqXHR.responseText);
											} else {
												alert('Unexpected error.');
											}
										}
									})
						}
						function loadData(currentPage) {
							$
									.ajax({
										url : '/product_master_list/'
												+ currentPage,
										method : 'GET',
										//data: {pageNumber: currentPage, pageSize: 50},
										dataType : 'json',
										success : function(data) {
											var productTable = $('#tblProductList tbody');
											$(data)
													.each(
															function(index,
																	product) {
																productTable
																		.append('<tr id="myRow"><td id="wrapCss">'
																				+ (++count)
																				+ '</td><td id="wrapCss" media="html" class="nothide">'
																				/* + '<a href="#" id='+product.mainIdentificationId+'>'
																				+ product.mainIdentificationId
																				+ '</a>' */

																				+ ' <form method="Post" action="/search_calib_equipment"> '
																				+ "<input type='hidden' name='identiFicationNum' value='"+product.mainIdentificationId+"' class='form-control'>"
																				+ '<input type="submit" value="'+product.mainIdentificationId+'" class="btn" id="identityBtn"/>'
																				+ '<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />'
																				+ '</form>'

																				+ '</td><td id="wrapCss"">'
																				+ product.mainInstrumentRequestor
																				+ '</td><td id="wrapCss"">'
																				+ product.mainSupplierNumber
																				+ '</td><td id="wrapCss"">'
																				+ product.mainSupplierName
																				+ '</td><td id="wrapCss"">'
																				+ product.mainPurchaseOrder
																				+ '</td><td id="wrapCss"">'
																				+ product.mainInvoice
																				+ '</td><td id="wrapCss"">'
																				+ product.mainUnitPrice
																				+ '</td><td id="wrapCss"">'
																				+ product.mainAssetCode
																				+ '</td><td id="wrapCss">'
																				+ product.mainSupplierService
																				+ '</td><td id="wrapCss">'
																				+ product.mainMake
																				+ '</td><td id="wrapCss">'
																				+ product.mainInstrumentGauge
																				+ '</td><td id="wrapCss"">'
																				+ product.mainModel
																				+ '</td><td id="wrapCss">'
																				+ product.mainSerial
																				+ '</td><td id="wrapCss">'
																				+ product.mainLeast
																				+ '</td><td id="wrapCss">'
																				+ product.mainCalibrationStandard
																				+ '</td><td id="wrapCss">'
																				+ product.mainForcastPrice
																				+ '</td><td id="wrapCss">'
																				+ product.mainInstrumentGaugeRange
																				+ '</td><td id="wrapCss">'
																				+ product.mainEquipmentType
																				+ '</td><td id="wrapCss">'
																				+ product.mainCalibrationType
																				+ '</td><td id="wrapCss">'
																				+ product.mainAlertFrequency
																				+ '</td><td id="wrapCss">'
																				+ product.mainCalibrationDate
																				+ '</td><td id="wrapCss">'
																				+ product.mainDueDate
																				+ '</td><td id="wrapCss">'
																				+ product.mainCalibrationStatus
																				+ '</td><td id="wrapCss">'
																				+ product.mainCalibrationScrapedDate
																				+ '</td><td id="wrapCss">'
																				+ product.mainReminderDate
																				+ '</td><td id="wrapCss">'
																				+ product.mainAcceptanceCriteria
																				+ '</td><td id="wrapCss">'
																				+ product.mainCalibrationPrice
																				+ '</td><td id="wrapCss">'
																				+ product.mainLocation
																				+ '</td><td id="wrapCss">'
																				+ product.mainCalibrationCategory
																				+ '</td><td id="wrapCss">'
																				+ product.mainCalibrationAgency
																				+ '</td><td id="wrapCss">'
																				+ product.mainCalibrationFrequency
																				+ '</td><td id="wrapCss">'
																				+ product.mainCalibrationCertificate
																				+ '</td><td id="wrapCss">'
																				+ product.mainCertificateValidatedBy
																				+ '</td><td id="wrapCss">'
																				+ product.mainPartCode
																				+ '</td><td id="wrapCss">'
																				+ product.mainCalibrationResult
																				+ '</td><td id="wrapCss">'
																				+ product.mainOldIdentificationId
																				+ '</td><td id="wrapCss">'
																				+ product.mainCalibrationSource
																				+ '</td><td id="wrapCss">'
																				+ product.mainIdentificationId
																				+ '</td><td>');

																document
																		.getElementById('dataloaded').innerHTML = "Showing "
																		+ "1"
																		+ " - "
																		+ ((parseInt(document
																				.getElementById("tblProductList").rows.length)) - 1);
																+" Records";

																document
																		.getElementById('totalData').innerHTML = "Showing Total "
																		+ ((parseInt(document
																				.getElementById("tblProductList").rows.length)) - 1)
																		+ " Record";
															})
										}

									});
						}
						;

					});
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
			id="hrefCss">Equipment List</a></span> <br> <br>

		<div class="d-flex justify-content-between" id="searchboxCss">
			<div align="left" id="searchboxCss">
				<div class="input-group mb-3">
					<input type="text" name="idNum" id="idNum"
						placeholder="Enter identity number.." class="form-control">
					<div class="input-group-append">
						<button id="searchBtn" class="btn btn-primary">Search</button>
					</div>
				</div>
			</div>
			<form>
				<input  type="button" value="View/Export Certificates"
					onclick="window.location.href='/master_view_certificate'" class="btn btn-default" id="searchboxCss"/>
			</form>
		</div>
		<br>
		<div class="d-flex justify-content-between">
			<div align="left" id="searchboxCss">
				<input type="button"
					onclick="tableToExcel('tblProductList', 'W3C Example Table')"
					value="Export to Excel" class="btn btn-default" id="searchboxCss">
				<label id="totalData" id="searchboxCss"></label>
			</div>

			<div align="right" id="searchboxCss">
				<label id="dataloaded"></label>
				<button id="next_button_id" class="btn btn-default btn-xs">
					<h id="searchboxCss">Next >></h>
				</button>


				<button id="last_button_id" class="btn btn-default btn-xs">
					<h id="searchboxCss">Last >></h>
				</button>
			</div>
		</div>

		<br>
		<div style="overflow-x: auto;">
			<table id="tblProductList" border="1">
				<thead>
					<tr id="tableHeadingCSS">
						<th id="wrapCss">Sr. No.</th>
						<th class="nothide" id="wrapCss">Identification Number</th>
						<th id="wrapCss">Instrument Requestor</th>
						<th id="wrapCss">Supplier Number</th>
						<th id="wrapCss">Supplier Name</th>
						<th id="wrapCss">Purchase Order</th>
						<th id="wrapCss">Supplier Invoice</th>
						<th id="wrapCss">Unit Price</th>
						<th id="wrapCss">Capex Code</th>
						<th id="wrapCss">Supplier Service</th>
						<th id="wrapCss">Make</th>
						<th id="wrapCss">Instrument Gauge Description</th>
						<th id="wrapCss">Model</th>
						<th id="wrapCss">Serial</th>
						<th id="wrapCss">Least Count/Accuracy Specification</th>
						<th id="wrapCss">Calibration Standard Used</th>
						<th id="wrapCss">Forecast Price</th>
						<th id="wrapCss">Instrument/Guage Range</th>
						<th id="wrapCss">Equipment Type</th>
						<th id="wrapCss">Calibration Type</th>
						<th id="wrapCss">Alert Frequency</th>
						<th id="wrapCss">Calibration Date</th>
						<th id="wrapCss">Due Date</th>
						<th id="wrapCss">Calibration Status</th>
						<th id="wrapCss">Scraped Date</th>
						<th id="wrapCss">Reminder Date</th>
						<th id="wrapCss">Acceptance Criteria</th>
						<th id="wrapCss">Calibration Price</th>
						<th id="wrapCss">Location</th>
						<th id="wrapCss">Calibration Category</th>
						<th id="wrapCss">Calibration Agency</th>
						<th id="wrapCss">alibration Frequency</th>
						<th id="wrapCss">Calibration Certificate</th>
						<th id="wrapCss">Certificate Validated By</th>
						<th id="wrapCss">Part Code</th>
						<th id="wrapCss">Calibration Result</th>
						<th id="wrapCss">Calibration Ref. No.</th>
						<th id="wrapCss">Calibration Source Type</th>
						<th id="wrapCss">Identification Number</th>
					</tr>
				</thead>
				<tbody>

				</tbody>
			</table>
		</div>
	</div>
</body>
</html>