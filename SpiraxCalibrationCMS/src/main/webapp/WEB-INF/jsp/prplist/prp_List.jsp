<%@page import="com.spiraxcalibration.models.CalibMainData"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="com.spiraxcalibration.controllers.PrController"%>
<%@page import="com.spiraxcalibration.WebConfig.AppsPropertyFile"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


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


<style>
.tableColumnGreen {
	background-color: green;
}
</style>

<link rel="stylesheet" href="customcss/formCss.css">

</head>

<body>

	<div id="header">
		<jsp:include page="/WEB-INF/jsp/header/header.jsp" />
	</div>
	<div align="center">
		<h3 id="formtitle">Purchase Requisition</h3>
	</div>
	<div class="container-fluid">
		<span id="hereCss">You are here : </span>
		<%
			AppsPropertyFile appsPropertyFile = new AppsPropertyFile();
		%>
		<span><a href=<%=appsPropertyFile.getURLForKey("homePageURL")%> id="hrefCss">home</a></span><span> /</span>
		<span><a href=<%=appsPropertyFile.getURLForKey("purchaseRequesitionURL")%>><h id="hrefCss">purchaseRequisitionList</h></a></span> <br> <br>
		<br>
		<!--  
		<form action="raise_pr" method="POST">
			<div class="row">
				<div class="col-sm-4">
					<label for="usr">Supplier Name:</label> <select
						class="form-control" name="supplier"
						style="height: 33px; width: 300px;">
						<option value="" style="display: none">-- Select Supplier
							Name --</option>
						<c:forEach items="${supSupplierList}" var="supSupplier">
							<option value="${supSupplier.supSupplierNumber}">${supSupplier.supSupplierName}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col-sm-4"></div>
				<div class="col-sm-4"></div>
			</div>

			<div class="row">

				<div class="col-sm-4">
					<br> <input type=submit value="Raise PR"
						class="btn btn-success" class="form-control">
				</div>
				<div class="col-sm-4"></div>
				<div class="col-sm-4"></div>
			</div>
			<div class="row"></div>
		</form>
-->
		<br>
		<form action="/search" method="POST">
			<div class="row">
				<div class="col-sm-2">
					<select class="form-control" name="supplierid"
						style="height: 33px; width: 300px;" id="searchboxCss" onchange="this.form.submit()">
						<option value="">-- Select Supplier Name --</option>
					
						<c:forEach items="${supSupplierList}" var="supSupplier">
							<c:choose>
								<c:when test="${calibMainData == null}">
									<option value="" style="display: none">-- Select
										Supplier --</option>
								</c:when>
								<c:otherwise>
									<option value="${supSupplier.supSupId}">${supSupplier.supSupplierName}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						
						
					</select>
				</div>
				<div class="col-sm-1"></div>
				<!-- <div class="col-sm-8">
					<input type=submit value="Search" class="btn btn-info"
						class="form-control" id="searchBtnCss">
				</div> -->
			</div>
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />

		</form>

		<form:form action="/generatePR" method="POST">
			<c:choose>
				<c:when test="${hiddenFlag != null}">
					<input type="hidden"
						value="${calibMainData.get(0).mainEcSupplierId}" name="supID">
				</c:when>
			</c:choose>
			<div class="row">
				<div class="col-sm-4">
					<br> <input type=submit value="Generate PR"
						class="btn" class="form-control"
						<c:if test="${RaisePR == null}"><c:out value="disabled='disabled'"/></c:if> id="addNewCss" style="background-color:${color}">
				</div>
				<div class="col-sm-4"></div>
				<div class="col-sm-4"></div>
			</div>

			<br>
			<div align="center">
				<b style="color: green; font-size: 15px;">${msg}</b>
			</div>
			<div style="overflow-x: auto;" id="displayTagCss">
				<spring:url value="/purchaseRequisitionList" var="listURL" />
				<display:table name="calibMainData" id="displaytable"
					requestURI="${listURL}" pagesize="10" class="table">

					<display:column title="Sr. no.">
						<c:out value="${displaytable_rowNum}" />
					</display:column>

					<input type="hidden" value="${displaytable_rowNum}"
						name="hidden_calibID+${num}" />

					<display:column property="mainEcCalibId"
						title="Calibration Ref. No."></display:column>


					<display:column property="mainSerial" title="Serial Number"></display:column>
					<display:column property="mainIdentificationId"
						title="Identification Number"></display:column>
					<display:column property="mainSupplierName" title="Supplier Name"></display:column>
					<display:column property="mainCalibrationDate"
						title="Calibration Date"></display:column>

					<display:column property="mainReminderDate"
						title="Calibration Reminder Date" style="background-color:yellow;"></display:column>

					<display:column property="mainDueDate" title="calibration Due date"
						style="background-color:${displaytable.colorFlag}"></display:column>
				</display:table>
				<spring:url value="bootstrap/js/bootstrap.min.js"
					var="boostrapJsURL"></spring:url>
				<spring:url value="bootstrap/js/jquery-1.7.1.min.js"
					var="jqueryJsURL"></spring:url>
				<script src="${boostrapJsURL}"></script>
				<script src="${jqueryJsURL}"></script>
			</div>
		</form:form>
	</div>
</body>
</html>