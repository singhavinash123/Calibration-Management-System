<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List"%>
<%@page import="com.spiraxcalibration.controllers.PrController"%>
<%@page import="com.spiraxcalibration.WebConfig.AppsPropertyFile"%>
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

<link rel="stylesheet" href="customcss/formCss.css">

</head>
<body>
	<div id="header">
		<jsp:include page="/WEB-INF/jsp/header/header.jsp" />
	</div>
	<div align="center">
		<h3 id="formtitle">Supplier List</h3>
	</div>
	<div class="container-fluid">
		<span id="hereCss">You are here : </span>
		<%
			AppsPropertyFile appsPropertyFile = new AppsPropertyFile();
		%>
		<span><a href=<%=appsPropertyFile.getURLForKey("homePageURL")%>
			id="hrefCss">Home</a></span><span> /</span> <span><a
			href=<%=appsPropertyFile.getURLForKey("supplierListURL")%>
			id="hrefCss">Supplier List</a> </span> <br>
		<br>


		<div class="row">
			<div class="col-sm-4">
				<spring:url value="/addSupplier" var="AddEquipmentURL"></spring:url>
				<a href="${AddEquipmentURL}"><button type="button"
						class="btn btn-success" id="addNewCss">Add New supplier</button> </a>
			</div>
		</div>
		<br>
		<div class="row">
			<form method="Post" action="/searchSupplier">
				<div class="container">
					<div class="row">
						<div class="col-md-auto" id="searchboxCss">
							<label for="usr">Supplier Name :</label> <input type="text"
								placeholder="Enter Supplier Name..." name="supplierName"
								class="form-control" id="searchboxCss" autocomplete="off">
						</div>

						<div class="col-md-auto id="searchboxCss"">
							<label for="usr">Supplier Number :</label> <input type="text"
								placeholder="Enter Supplier Number..." name="supplierNumber"
								class="form-control" id="searchboxCss" autocomplete="off">
						</div>
						<div class="col-md-auto" id="searchboxCss">
							<label for="usr">Status:</label> <select class="form-control"
								name=supplierStatus style="height: 33px; width: 300px;">
								<option value="" style="display: none" id="searchboxCss">--
									select Status --</option>
								<c:forEach items="${SupplierStatusList}" var="SupplierStatus">
									<option value="${SupplierStatus}">${SupplierStatus}</option>
								</c:forEach>
							</select>
						</div>

					</div>
					<div class="row">
						<div class="col">
							<br> <input type=submit value="Search" class="btn btn-info"
								class="form-control" id="searchBtnCss">
						</div>
						<div class="col-12"></div>
					</div>
				</div>
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />

			</form>
		</div>
		<br>
		<div align="center">
			<b style="color: green; font-size: 15px;">${msg}</b>
		</div>
		<div style="overflow-x: auto;" id="displayTagCss">
			<spring:url value="/supplierList" var="listURL" />
			<display:table name="supplierList" id="displaytable"
				requestURI="${listURL}" pagesize="20" class="table" export="true"
				sort="list">

				<display:column title="Action" media="html">
					<spring:url value="/updateSupplier/${displaytable.supSupId}"
						var="updateSupplierURL"></spring:url>
					<a href="${updateSupplierURL}"><button type="button"
							class="btn btn-primary" id="addNewCss">Update</button></a>
				</display:column>

				<display:column title="Sr. no.">
					<c:out value="${displaytable_rowNum}" />
				</display:column>

				<display:column property="supSupplierNumber"
					href="/showSupplierDetail" paramId="Supplier_Num"
					paramProperty="supSupplierNumber" title="Supplier number"></display:column>
				<display:column property="supSupplierName" title="Supplier Name" style="white-space:nowrap;"></display:column>
				<display:column property="supSupplierServiceType" title="Supplier Type" style="white-space:nowrap;"></display:column>

				<display:column property="supRegistrationNum"
					title="Contact Number" style="white-space:nowrap;"></display:column>

				<display:column property="supEmailId" title="Email Id" style="white-space:nowrap;"></display:column>
				<display:column property="supContact" title="Contact Person" style="white-space:nowrap;"></display:column>
				<display:column property="supStatus" title="Status" style="white-space:nowrap;"></display:column>
				<display:column property="supAddressLine1" title="Address Line1" style="white-space:nowrap;"></display:column>
				<display:column property="supAddressLine2" title="Address Line2" style="white-space:nowrap;"></display:column>
				<display:column property="supAddressLine3" title="Address Line3" style="white-space:nowrap;"></display:column>
				<display:column property="supAddressLine4" title="Address Line4" style="white-space:nowrap;"></display:column>
				<display:column property="supAddressLine5" title="Address Line5" style="white-space:nowrap;"></display:column>
				<display:column property="supAddressLine6" title="Address Line6" style="white-space:nowrap;"></display:column>
				
				<display:setProperty name="export.csv.filename"
					value="MasterSupplierReport.csv" />
				<display:setProperty name="export.excel.filename"
					value="MasterSupplierReport.xls" />
				<display:setProperty name="export.xml.filename"
					value="MasterSupplierReport.xml" />
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