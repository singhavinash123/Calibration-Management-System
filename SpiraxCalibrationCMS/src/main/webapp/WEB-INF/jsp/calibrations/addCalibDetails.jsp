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

<script>
	function myFunction() {

		var calibDate = document.getElementById("calibDate").value;
		var date = new Date(calibDate);
		var newdate = new Date(date);

		var frequencyDays = document.getElementById("frequency").value;
		newdate.setDate(newdate.getDate() + parseInt(frequencyDays));

		var dd = newdate.getDate();
		var mm = newdate.getMonth() + 1;
		var y = newdate.getFullYear();

		var someFormattedDate = dd + '-' + mm + '-' + y;
		document.getElementById("duedate").value = someFormattedDate;

		document.getElementById("hiddenDueDate").value = y + '-' + mm + '-'
				+ dd;

		getComboA(document.getElementById("alertfrequency").value);

	};

	window.onload = function() {

		document.getElementById("frequency").value = ${CalibForm.calibCalibrationFrequency};
        var calibfreq = ${CalibForm.calibCalibrationFrequency};
		if(calibfreq == 0 ){
			var today = new Date();

			var dd = today.getDate();
			var mm = today.getMonth() + 1;
			var y = today.getFullYear();

			document.getElementById("duedate").value = dd + '-' + mm + '-' + y;
			document.getElementById("reminderDate").value = dd + '-' + mm + '-' + y;
			document.getElementById("hiddenDueDate").value = y + '-' + mm + '-' + dd;
			getComboA(document.getElementById("alertfrequency").value);
			
		}
		
	};
	function getComboA(value) {
		document.getElementById("alertfrequency").value = value
		
		var dueDate = document.getElementById("hiddenDueDate").value;
		
		var date = new Date(dueDate);
		var newdDueDate = new Date(date);

		newdDueDate.setDate(newdDueDate.getDate() - parseInt(value));

		var dd = newdDueDate.getDate();
		var mm = newdDueDate.getMonth() + 1;
		var y = newdDueDate.getFullYear();

		var someFormattedDate = dd + '-' + mm + '-' + y;
		document.getElementById("reminderDate").value = someFormattedDate;

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
				List</a></span> <span>/</span> <span><a href="">${addOrUpdate}</a></span>
						<h3 align="center"><b>${addORupdatePrheading}</b></h3>
				 <br>
		<div align="center">
			<b style="color: green; font-size: 15px;">${msg}</b>
		</div>
		<div align="center">
			<b style="color: red; font-size: 15px;">${alreadyExist}</b>
		</div>
		<br>
		<div class="row" align="left">
			<div class="col-sm-4">
				<div class="form-group">
					<label for="usr">Identity Number :</label> <input type="text"
						name="identityNum" class="form-control"
						value="${CalibForm.calibIdentificationNo}" readonly><br>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="form-group">
					<label for="usr">Serial number :</label> <input type="text"
						name="serialNum" class="form-control"
						value="${CalibForm.calibSerialNumber}" readonly><br>
					<div></div>
				</div>
			</div>

			<div class="col-sm-4">
				<div class="form-group">
					<label for="usr">Supplier Name :</label> <input type="text"
						name="supplierName" class="form-control"
						value="${CalibForm.calibSupplierName}" readonly><br>
					<div></div>
				</div>
			</div>
		</div>

		<spring:url value="/saveCalibration" var="saveURL" />
		<form:form modelAttribute="CalibForm" method="post"
			action="${saveURL}" style="font-size:12px;">
			<form:hidden path="CalibProdId" />
			<form:hidden path="calibSerialNumber" />
			<form:hidden path="calibIdentificationNo" />
			<form:hidden path="calibId" />
			<form:hidden path="calibApprover1Status" />
			<form:hidden path="calibApprover2Status" />
			<form:hidden path="calibSupplierName" />

			<div class="row" align="left">
				<div class="col-sm-3">
					<div class="form-group">
						<label for="usr">Calibration Type :</label>
						<form:select path="calibCalibrationType" class="form-control"
							name="processNameLookup" onchange="getComboA(this.value)">
							<c:forEach items="${calibrationTypeLookup}" var="calibrationType">
								<option value="${calibrationType.getValue()}">${calibrationType.getKey()}</option>
							</c:forEach>
						</form:select>
					</div>
				</div>

				<div class="col-sm-3">
					<div class="form-group">
						<label for="usr">Alert Frequency (days):</label>
						<form:input id="alertfrequency" value="${selectedFrequency}"
							path="AlertFrequency" class="form-control" readonly="true" />
					</div>
				</div>
				<div class="col-sm-3">
					<div class="form-group">
						<label for="usr">Calibration Frequency (days):</label>
						<form:input id="frequency" path="calibCalibrationFrequency"
							class="form-control"
							value="${CalibForm.calibCalibrationFrequency}"
							onchange="myFunction();" />
						<div>
							<form:errors path="calibCalibrationFrequency" cssClass="error"></form:errors>
						</div>
					</div>
				</div>

				<div class="col-sm-3">
					<div class="form-group">
						<label for="usr">Calibration Date :</label>
						<form:input type="Date" path="calibCalibrationDate"
							class="form-control" onchange="myFunction();" id="calibDate" />
						<div>
							<form:errors path="calibCalibrationDate" cssClass="error"></form:errors>
						</div>
					</div>
				</div>

				<div class="col-sm-3">
					<div class="form-group">

						<label for="usr">Due Date :</label>
						<form:input id="duedate" path="calibCalibrationDueDate"
							class="form-control" readonly="true" />
						<input type="hidden" id="hiddenDueDate">
						<div>
							<form:errors path="calibCalibrationDueDate" cssClass="error"></form:errors>
						</div>
					</div>
				</div>
				<div class="col-sm-3">
					<div class="form-group">
						<label for="usr">Calibration reminder Date :</label>
						<form:input id="reminderDate" path="calibCalibrationReminderDate"
							class="form-control" readonly="true" />
					</div>
				</div>

				<div class="col-sm-3">
					<div class="form-group">
						<label for="usr">Calibration Status :</label>
						<form:select path="calibCalibStatus" class="form-control"
							name="calibrationStatusLookup">
							<option value="${CalibForm.calibCalibStatus}">${CalibForm.calibCalibStatus}</option>
							<c:forEach items="${calibrationStatusLookup}"
								var="calibrationStatus">
								<option value="${calibrationStatus}">${calibrationStatus}</option>
							</c:forEach>
						</form:select>
					</div>
				</div>

				<div class="col-sm-3">
					<div class="form-group">
						<label for="usr">Approver Email :</label>
						<form:select path="calibAprroverMailId" class="form-control"
							name="calibrationStatusLookup">
							<c:forEach items="${ApproverMailList}" var="ApprovermailId">
								<c:choose>
									<c:when test="${CalibForm.calibAprroverMailId == ApprovermailId}">
										<option value="${CalibForm.calibAprroverMailId}" selected>${CalibForm.calibAprroverMailId}</option>
									</c:when>
									<c:otherwise>
										<option value="${ApprovermailId}">${ApprovermailId}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</form:select>
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
		</form:form>
	</div>
</body>
</html>