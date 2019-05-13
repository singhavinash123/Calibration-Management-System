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
		<h4 align="center">Purchase Requisition</h4>
		<span>You are here : </span>
		<%
			AppsPropertyFile appsPropertyFile = new AppsPropertyFile();
		%>
		<span><a href=<%=appsPropertyFile.getURLForKey("homePageURL")%>>home</a></span><span>/</span>
		<span><a href=<%=""%>>purchaseRequisitionList</a></span> <br>

		<div class="row">
			<div class="col-sm-4">
				<label for="usr">PR ID :</label> <input type="text" readonly="true"
					value="${pRPData.pRpPRId}" class="form-control">
			</div>
			<div class="col-sm-4">
				<label for="usr">Supplier Number :</label> <input type="text"
					readonly="true" value="${pRPData.pRpSupplierNumber}" class="form-control">
			</div>
			<div class="col-sm-4">
				<label for="usr">Supplier Name :</label> <input type="text"
					readonly="true" value="${pRPData.pRpSupplierName}" class="form-control">
			</div>
		</div>
		<br>
		<div class="row">
			<div class="col-sm-4">
				<label for="usr">Identification Number:</label> <select
					class="form-control" name="supplier"
					style="height: 33px; width: 300px;">
					<option value="" style="display: none">-- Select Identification
						Number --</option>
						<c:forEach items="${identificationWithEquiType}" var="prData">
									<option value="${prData.prIdentificationNo}">${prData.prIdentificationNo}</option>
								</c:forEach>
						
				</select>
			</div>

			<div class="col-sm-4">
				<label for="usr">Equipment Description:</label>
			</div>

			<div class="col-sm-4">
				<label for="usr">Serial Number:</label> <select class="form-control"
					name="supplier" style="height: 33px; width: 300px;">
					<option value="" style="display: none">-- Select Supplier
						Name --</option>
				</select>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-4">
				<input type=submit value="Add Item" class="btn btn-info"
					class="form-control">
			</div>
			<div class="col-sm-4">
				<input type="button" class="btn btn-lg btn-block " id="addrow"
					value="Add Row" />
			</div>
			<div class="col-sm-4"></div>

		</div>
		<div class="row">
			<table id="myTable" class=" table order-list">
				<thead>
					<tr>
						<td>S. No.</td>
						<td>Identification Number</td>
						<td>Identification Description</td>
						<td>Serial Number</td>
						<td>Quantity</td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td class="col-sm-1"><input type="text" name="name"
							class="form-control" /></td>
						<td class="col-sm-3"><input type="text" name="mail"
							class="form-control" /></td>
						<td class="col-sm-3"><input type="text" name="phone"
							class="form-control" /></td>
						<td class="col-sm-3"><input type="text" name="phone"
							class="form-control" /></td>
						<td class="col-sm-1"><input type="text" name="Quantity" value="1"
							class="form-control" /></td>
						<td class="col-sm-1"><a class="deleteRow"></a></td>
					</tr>
				</tbody>
				<tfoot>
				</tfoot>
			</table>
		</div>



		<!-- 
		<div class="row">
			<form method="Post" action="/searchCalibProduct">
				<div class="container">
					<div class="row">
						<div class="col-md-auto">
							<label for="usr">Enter Identity Number:</label> <input
								type="text" placeholder="Identity Number..." name="identityNum"
								class="form-control">
						</div>
						<div class="col-md-auto">
							<label for="usr">Enter Serial Number:</label> <input type="text"
								placeholder="Serial Number..." name="serialNumber"
								class="form-control">
						</div>
						<div class="col-md-auto">
							<label for="usr">Select Location:</label> <select
								class="form-control" name="location"
								style="height: 33px; width: 300px;">
								<option value=""></option>
								<c:forEach items="${locationList}" var="location">
									<option value="${location}">${location}</option>
								</c:forEach>
								<option value="">others</option>
							</select>
						</div>
						<div class="col-md-auto">
							<label for="usr">Select Calib Agency:</label> <select
								class="form-control" name="agency"
								style="height: 33px; width: 300px;">
								<option value=""></option>
								<c:forEach items="${agencyList}" var="agency">
									<option value="${agency}">${agency}</option>
								</c:forEach>
								<option value="">others</option>
							</select>
						</div>
					</div>
					<div class="row">
						<div class="col">
							<br> <input type=submit value="Search" class="btn btn-info"
								class="form-control"> <input type="button"
								class="btn btn-warning" name="reset_form" value="Clear"
								onclick="this.form.reset();">
						</div>
						<div class="col-12"></div>
					</div>
				</div>
			</form>
		</div>
		-->

		<!--  
		<br>
		<div align="center">
			<b style="color: green; font-size: 15px;">${msg}</b>
		</div>
		<div style="overflow-x: auto;">
			<spring:url value="/PR_Raise/${pRPData.pRpPRId}" var="listURL" />
			<display:table name="pRPData" id="displaytable"
				requestURI="${listURL}" pagesize="10" class="table">

				<display:column title="Sr. no.">
					<c:out value="${displaytable_rowNum}" />
				</display:column>

				<display:column property="pRpCalibId" title="Calibration ID"></display:column>

				<display:column property="pRpProdId"
					title="Identification Number"></display:column>

				<display:column property="pRpApprover1"
					title="Approver1 Status"></display:column>

				<display:column property="pRpApprover2"
					title="Calibration Frequency"></display:column>

			</display:table>

			<spring:url value="bootstrap/js/bootstrap.min.js" var="boostrapJsURL"></spring:url>
			<spring:url value="bootstrap/js/jquery-1.7.1.min.js"
				var="jqueryJsURL"></spring:url>
			<script src="${boostrapJsURL}"></script>
			<script src="${jqueryJsURL}"></script>
		</div>
	</div>
	-->
	</div>
</body>
</html>