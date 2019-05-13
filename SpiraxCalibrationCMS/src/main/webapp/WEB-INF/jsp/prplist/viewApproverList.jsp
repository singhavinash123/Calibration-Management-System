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
		<h3 id="formtitle">View And Approve Purchase Requisition</h3>
	</div>
	<div class="container-fluid">
		<h4 align="center"></h4>
		<span id="hereCss">You are here : </span>
		<%
			AppsPropertyFile appsPropertyFile = new AppsPropertyFile();
		%>
		<span><a href=<%=appsPropertyFile.getURLForKey("homePageURL")%> id="hrefCss">home</a></span><span> /</span>
		<span><a href=<%=""%>><h id="hrefCss">purchaseRequisitionList</h></a></span> <br> <br>
		<br>

		<div class="row">
			<form action="/search_pr" method="POST">
				<div class="row">
					<div class="col-sm-3" id="searchboxCss">
						<select class="form-control" name="prpId"
							style="height: 33px; width: 300px;">
							<option value="" style="display: none">-- Select PR. No..
								--</option>
							<c:forEach items="${prpIdList}" var="prpId">
								<option value="${prpId}">${prpId}</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-sm-3" id="searchboxCss">
						<select class="form-control" name="supSupplier"
							style="height: 33px; width: 300px;">
							<option value="" style="display: none">-- Select
								Supplier --</option>
							<c:forEach items="${supplierList}" var="supSupplier">
								<option value="${supSupplier}">${supSupplier}</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-sm-3" id="searchboxCss">
						<select class="form-control" name="approver1status"
							style="height: 33px; width: 300px;">
							<option value="" style="display: none">-- Select
								Approver1 Status --</option>
							<c:forEach items="${approver1statusList}" var="approver1status">
								<option value="${approver1status}">${approver1status}</option>
							</c:forEach>
						</select>
					</div>

					<div class="col-sm-3"id="searchboxCss">
						<select class="form-control" name="approver2status"
							style="height: 33px; width: 300px;">
							<option value="" style="display: none">-- Select
								Approver2 Status --</option>
							<c:forEach items="${approver2statusList}" var="approver2status">
								<option value="${approver2status}">${approver2status}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<br>
				<div class="col-sm-3">
					<input type=submit value="Search" class="btn btn-info"
						class="form-control" id="searchBtnCss">
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
			<spring:url value="/viewApproverPr" var="listURL" />
			<display:table name="pRpDataList" id="displaytable"
				requestURI="${listURL}" pagesize="10" class="table">
				<display:column title="Sr. no.">
					<c:out value="${displaytable_rowNum}" />
				</display:column>
				<input type="hidden" value="${displaytable_rowNum}"
					name="hidden_calibID+${num}" />


				<display:column title="PR Number" style="background-color:green;">
					<a href="/approverPR/${displaytable.pRpPRId}"><b
						style="color: white;">${displaytable.pRpPrNumber}</b></a>
				</display:column>

				<display:column property="pRpPRId" title="PR. ID">
				
				</display:column>

				<c:choose>
					<c:when test="${displaytable.pRpApprover1Status == 'Approved'}">
						<c:set var="css" value="green" />
					</c:when>
					<c:when test="${displaytable.pRpApprover1Status == 'Rejected'}">
						<c:set var="css" value="red" />
					</c:when>
					<c:otherwise>
						<c:set var="css" value="yellow" />
					</c:otherwise>
				</c:choose>

				<display:column property="pRpSupplierName" title="Supplier Name"></display:column>

				<display:column property="pRpApprover1Status"
					title="Approver1 Status" style="background-color:${css}"></display:column>



				<display:column property="pRpApprover1Comments"
					title="Approver1 Comments"></display:column>

				<c:choose>
					<c:when test="${displaytable.pRpApprover2Status == 'Approved'}">
						<c:set var="css" value="green" />
					</c:when>
					<c:when test="${displaytable.pRpApprover2Status == 'Rejected'}">
						<c:set var="css" value="red" />
					</c:when>
					<c:otherwise>
						<c:set var="css" value="yellow" />
					</c:otherwise>
				</c:choose>
				<display:column property="pRpApprover2Status"
					title="Approver2 Status" style="background-color:${css}"></display:column>

				<display:column property="pRpApprover2Comments"
					title="Approver2 Comments"></display:column>

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