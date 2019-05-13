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

<spring:url value="bootstrap/css/bootstrap.min.css"
	var="bootstrapCssURL"></spring:url>
<link href="${bootstrapCssURL}" rel="stylesheet">


</head>
<body>

	<div id="header">
		<jsp:include page="/WEB-INF/jsp/header/header.jsp" />
	</div>
	<div class="container-fluid">
		<span>You are here : </span>
		<%
			AppsPropertyFile appsPropertyFile = new AppsPropertyFile();
		%>
		<span><a href=<%=appsPropertyFile.getURLForKey("homePageURL")%>>Home</a></span><span> /</span>
		<span><a href=<%=""%>>${addOrUpdate}</a></span>
				<h3 align="center"><b>QR/BAR Code List</b></h3>
		<br> 
		<div class="row">
			<form method="Post" action="/searchQR_And_BAR_List">
				<div class="container">
					<div class="row">
						<div class="col-md-auto">
							<label for="usr">Select Identification No:</label> <select
								class="form-control" name=identityno
								style="height: 33px; width: 300px;">
								<option value="">--Select Identification Number--</option>
								<c:forEach items="${identityNoList}" var="identityno">
									<option value="${identityno}">${identityno}</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-md-auto">
							<label for="usr">Select Serial No:</label> <select
								class="form-control" name="serialno"
								style="height: 33px; width: 300px;">
								<option value="">--Select Serial Number</option>
								<c:forEach items="${serialNoList}" var="serialno">
									<option value="${serialno}">${serialno}</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-md-auto">
							<br> <input type=submit value="Search" class="btn btn-info"
								class="form-control">
						</div>
					</div>
				</div>
			</form>
		</div>

		<div align="center">
			<b style="color: green; font-size: 15px;">${msg}</b>
		</div>
		<div style="overflow-x: auto;">
			<spring:url value="/print_QR_BAR_Code" var="listURL" />
			<display:table name="CalibDataList" id="displaytable"
				requestURI="${listURL}" pagesize="5" class="table">

				<display:column title="Action">
					<spring:url value="/printQR_BAR_Code/${displaytable.calibId}"
						var="CalibrateURL"></spring:url>
					<a href="${CalibrateURL}"><button type="button"
							class="btn btn-primary" <c:if test="${displaytable.calibStatusForExpired == true}"><c:out value="disabled='disabled'"/></c:if>>Print QR/BAR Code</button></a>
				</display:column>

				<display:column title="Sr. no.">
					<c:out value="${displaytable_rowNum}" />
				</display:column>

				<display:column property="calibIdentificationNo"
					title="Identification Number"></display:column>

				<display:column property="calibSerialNumber" title="SerialNumber"></display:column>

				<display:column property="calibId" title="Calib ID"></display:column>

				<display:column property="calibCalibrationFrequency"
					title="Calib Frequency"></display:column>

				<display:column property="calibCalibrationReminderDate"
					title="Reminder Date"></display:column>

				<display:column property="calibCalibrationDueDate"
					title="Calib Due Date"></display:column>

				<display:column
					style="background-color:${displaytable.calibStatusFlag}"
					property="calibStatusData" title="Status"></display:column>

				<display:column property="calibApprover1Status"
					title="Approver Status"></display:column>

			</display:table>

			<spring:url value="bootstrap/js/bootstrap.min.js" var="boostrapJsURL"></spring:url>
			<spring:url value="bootstrap/js/jquery-1.7.1.min.js"
				var="jqueryJsURL"></spring:url>
			<script src="${boostrapJsURL}"></script>
			<script src="${jqueryJsURL}"></script>
		</div>
	</div>
</body>
</html>