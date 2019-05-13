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
						loadData();
						function loadData() {
							var dataCount = 0;
							$.ajax({
										url : '/master_view/',
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
																		.append('<tr id="myRow"><td id="wrapCss"">'
																				+ (++dataCount)
																				+ '</td><td id="wrapCss">'
																				+ product.certCalibId
																				+ '</td><td id="wrapCss"">'
																				+ product.certIndentiytNumber
																				+ '</td><td id="wrapCss"">'
																				+ product.certCertificateName
																				+ '</td><td id="wrapCss"">'
																				+ product.certCalibCertificateOptionName
																				+ '</td><td id="wrapCss"">'
																				
																				+ product.certCertificateNumber
																				+ '</td><td id="wrapCss"">'
																				+ product.certCertificateDueDate
																				+ '</td><td id="wrapCss"">'
																				+ product.certCertificateValidatedBy
																				+ '</td><td id="wrapCss"">'
																				+ product.certUploadCertDate
																				+ '</td><td>');

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
			href=<%=appsPropertyFile.getURLForKey("master_view_certificate")%>
			id="hrefCss">master_view_certificate</a></span> <br> <br>

		<%-- <div class="d-flex justify-content-between" id="searchboxCss">
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
				<input class="MyButton" type="button" value="Your Text Here"
					id="searchBtn"
					onclick="window.location.href='/master_view_certificate'" />
			</form>
		</div> --%>
		<br>
		<div class="d-flex justify-content-between">
			<div align="left" id="searchboxCss">
				<input type="button"
					onclick="tableToExcel('tblProductList', 'W3C Example Table')"
					value="Export to Excel" class="btn btn-default" id="searchboxCss">
				<label id="totalData" id="searchboxCss"></label>
			</div>

			<!-- <div align="right" id="searchboxCss">
				<label id="dataloaded"></label>
				<button id="next_button_id" class="btn btn-default btn-xs">
					<h id="searchboxCss">Next >></h>
				</button>


				<button id="last_button_id" class="btn btn-default btn-xs">
					<h id="searchboxCss">Last >></h>
				</button>
			</div> -->
		</div>

		<br>
		<div style="overflow-x: auto;">
			<table id="tblProductList" border="1">
				<thead>
					<tr id="tableHeadingCSS">
						<th id="wrapCss">Sr. No.</th>
						<th id="wrapCss">Calibration Ref. No.</th>
						<th id="wrapCss">Identification Number</th>
						<th id="wrapCss">Certificate Name</th>
						<th id="wrapCss">Certificate Type</th>

						<th id="wrapCss">Certificate Name</th>
						<th id="wrapCss">Due Date</th>
						<th id="wrapCss">Certificate Validated By</th>

						<th id="wrapCss">Upload Time</th>
					</tr>
				</thead>
				<tbody>

				</tbody>
			</table>
		</div>
	</div>
</body>
</html>