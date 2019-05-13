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
<title>User Form</title>
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


<spring:url value="bootstrap/css/bootstrap.min.css"
	var="bootstrapCssURL"></spring:url>
<link href="${bootstrapCssURL}" rel="stylesheet">

<script type="text/javascript">
	function CheckStatus(val) {
		var element = document.getElementById('instrumentStatus');
		if (val == 'others')
			element.style.display = 'block';
		else
			element.style.display = 'none';
		document.getElementById("instrumentStatus").value = val;
	}
</script>

</head>
<body>

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
			href=<%=appsPropertyFile.getURLForKey("calibrationlistURL")%>>Calibration
				List</a></span> <span>/</span> <span><a href="">Calibration
				Information</a></span> 
						<h3 align="center"><b>${calibInfo}</b></h3>
				
				<br>

		<form method="GET" action="/addCalibration/${prdata.prProdId}">
			<div class="row" align="left">
				<div class="col-sm-4">
					<div class="form-group">
						<label for="usr">Identification Number :</label> <input
							type="text" name="identityNum" class="form-control"
							value="${prdata.prIdentificationNo}" readonly><br>
					</div>
				</div>
				<div class="col-sm-4">
					<div class="form-group">
						<label for="usr">Serial number :</label> <input type="text"
							name="serialNum" class="form-control"
							value="${prdata.prSerialNo}" readonly><br>
						<div></div>
					</div>
				</div>


				<div class="col-sm-4">
					<div class="form-group">
						<label for="usr">Supplier Name :</label> <input type="text"
							name="supplierName" class="form-control"
							value="${prdata.prSupplierName}" readonly><br>
						<div></div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-4">
					<button type="submit" class="btn btn-success"
						<c:if test="${disabled == true}"><c:out value="disabled='disabled'"/></c:if>>Add
						Calibration</button>
				</div>
			</div>
		</form>
		<br>
		
		<div align="center">
			<b style="color: green; font-size: 15px;">${msg}</b> <br>
			<div align="center">
				<b style="color: red; font-size: 15px;">${inactiveMSG}</b> <br>
				<div style="overflow-x: auto;">
					<spring:url value="/calibrate/${prdata.prProdId}" var="listURL" />
					<display:table name="calibData" id="displaytable"
						requestURI="${listURL}" pagesize="10" class="table">

						<display:column title="Sr. No.">
							<c:out value="${displaytable_rowNum}" />
						</display:column>

						<display:column property="calibId" title="Calibration Ref. No."></display:column>

						<display:column property="calibCalibrationFrequency"
							title="Calibration Frequency"></display:column>

						<display:column property="calibCalibrationDate"
							title="Calibration Date"></display:column>

						<display:column property="calibCalibrationDueDate"
							title="Due Date"></display:column>
						<display:column property="calibCalibrationReminderDate"
							title="Reminder Date"></display:column>

						<display:column property="calibCalibStatus"
							title="Calibration Status" href="/updateCalib" paramId="calib_Id"
							paramProperty="calibId" />

						<display:column title="Approver Mail Id" property="calibAprroverMailId">
						</display:column>

						<display:column title="Approval">
							<a href="/updateApproval?mail_Id=${displaytable.calibAprroverMailId}&calib_id=${displaytable.calibId}" <c:if test="${displaytable.calibSendForApprovalStatusFlag == 1}"><c:out value="hidden"/></c:if>>Send To Approver</a>
						</display:column>
						
						<display:column property="calibApprover1Name"
							title="Approver Name" paramProperty="calibId" />

						<display:column property="calibApprover1Status"
							title="Approval Status" paramProperty="calibId" style="background-color:${displaytable.calibApproverStatusColorFlag}"/>

						<display:column property="calibApprover1Comments"
							title="Approver Comments" paramProperty="calibId" />

						

						<display:column title="Action">
							<spring:url
								value="/uploadOrDownloadcrtificate/${displaytable.calibId}"
								var="uploadOrDownloadcrtificateURL"></spring:url>
							<a href="${uploadOrDownloadcrtificateURL}"><button
									type="button" class="btn btn-warning"  <c:if test="${displaytable.calibCalibStatus != 'Active'}"><c:out value="disabled='disabled'"/></c:if>>Upload/Download
									Calibration Certificate</button></a>
						</display:column>

					</display:table>

					<spring:url value="bootstrap/js/bootstrap.min.js"
						var="boostrapJsURL"></spring:url>
					<spring:url value="bootstrap/js/jquery-1.7.1.min.js"
						var="jqueryJsURL"></spring:url>

					<script src="${boostrapJsURL}"></script>
					<script src="${jqueryJsURL}"></script>
				</div>

			</div>
		</div>
	</div>
</body>
</html>