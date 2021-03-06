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
					<label for="usr">Identification Number :</label> <input type="text"
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
		</div>

		<spring:url value="/saveApprover" var="saveURL" />
		<form:form modelAttribute="CalibForm" method="post"
			action="${saveURL}" style="font-size:12px;">
			<form:hidden path="CalibProdId" />
			<form:hidden path="calibSerialNumber" />
			<form:hidden path="calibIdentificationNo" />
			<form:hidden path="calibId" />

			<div class="row" align="left">
				<div class="col-sm-3">
					<div class="form-group">
						<label for="usr">Calibration Type :</label>
						<form:select path="calibCalibrationType" class="form-control"
							name="processNameLookup" readonly="${disabled}">
							<option value="${CalibForm.calibCalibrationType}">${CalibForm.calibCalibrationType}</option>
							<c:forEach items="${calibrationTypeLookup}" var="calibrationType">
								<option value="${calibrationType}">${calibrationType}</option>
							</c:forEach>
						</form:select>
					</div>
				</div>

				<div class="col-sm-3">
					<div class="form-group">
						<label for="usr">Calibration Frequency :</label>
						<form:input path="calibCalibrationFrequency" class="form-control"
							readonly="${disabled}" />
						<div>
							<form:errors path="calibCalibrationFrequency" cssClass="error"></form:errors>
						</div>
					</div>
				</div>

				<div class="col-sm-3">
					<div class="form-group">
						<label for="usr">Calibration Date :</label>
						<form:input type="Date" path="calibCalibrationDate"
							class="form-control" readonly="${disabled}" />
						<div>
							<form:errors path="calibCalibrationDate" cssClass="error"></form:errors>
						</div>
					</div>
				</div>

				<div class="col-sm-3">
					<div class="form-group">

						<label for="usr">Due Date :</label>
						<form:input type="text" path="calibCalibrationDueDate"
							class="form-control" readonly="${disabled}" />
						<div>
							<form:errors path="calibCalibrationDueDate" cssClass="error"></form:errors>
						</div>
					</div>
				</div>
			</div>


			<div class="row" align="left">
				<div class="col-sm-3">
					<div class="form-group">
						<label for="usr">Calibration reminder Date :</label>
						<form:input type="text" path="calibCalibrationReminderDate"
							class="form-control" readonly="${disabled}" />
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
			</div>
			<div class="row">
				<div class="col-sm-3">
					<div class="form-group">
						<label for="usr">Approver Name :</label>
						<form:input path="calibApprover1Name" class="form-control" />
					</div>
				</div>

				<div class="col-sm-3">
					<div class="form-group">
						<label for="usr">Approval Status :</label>
						<form:select path="calibApprover1Status" class="form-control"
							name="calibrationStatusLookup">
							<option value="${CalibForm.calibApprover1Status}"
								selected="selected">${CalibForm.calibApprover1Status}</option>
							<option value="Pending">Pending</option>
							<option value="Approved">Approved</option>
							<option value="Approved">Rejected</option>

						</form:select>
					</div>
				</div>
				<div class="col-sm-3">
					<div class="form-group">
						<label for="usr">Approval Comments :</label>
						<form:input path="calibApprover1Comments" class="form-control" />
					</div>
				</div>
			</div>

		<!--
			<div class="row">
			
			<div class="col-sm-3">
					<div class="form-group">
						<label for="usr">Approval2 Name :</label>
						<form:input path="calibApprover2Name" class="form-control" />
					</div>
				</div>

				<div class="col-sm-3">
					<div class="form-group">
						<label for="usr">Approval2 Status :</label>
						<form:select path="calibApprover2Status" class="form-control"
							name="calibrationStatusLookup">
							<option value="${CalibForm.calibApprover2Status}"
								selected="selected">${CalibForm.calibApprover2Status}</option>
							<option value="Pending">Pending</option>
							<option value="Approved">Approved</option>
							<option value="Approved">Rejected</option>
						</form:select>
					</div>
				</div>
				<div class="col-sm-3">
					<div class="form-group">
						<label for="usr">Approval2 Comments :</label>
						<form:input path="calibApprover2Comments" rows="5"
							class="form-control" />
					</div>
				</div>
			</div>
			-->
			
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