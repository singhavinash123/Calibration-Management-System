<%@page import="java.io.OutputStreamWriter"%>
<%@page import="java.io.OutputStream"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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

<!-- Scrollbar Custom CSS -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.5/jquery.mCustomScrollbar.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<!-- Font Awesome JS -->
<script defer
	src="https://use.fontawesome.com/releases/v5.0.13/js/solid.js"></script>

<script defer
	src="https://use.fontawesome.com/releases/v5.0.13/js/fontawesome.js"></script>

<script type="text/javascript" src="bootstrap/js/jquery-3.3.1.js"></script>
<style>
table {
	border-collapse: collapse;
	border-spacing: 0;
	width: 100%;
	border: 2px solid #ddd;
	font-size: 20px;
}

th, td {
	text-align: left;
	padding: 8px;
}

tr:nth-child(even) {
	background-color: white
}
</style>
</head>
<body>
	<div id="header">
		<jsp:include page="/WEB-INF/jsp/header/header.jsp" />
	</div>
	<div class="container-fluid">
		<h3 align="center"><b>${ScannedIdentity}</b></h3>
		<div style="overflow-x: auto;">

			<table border="1">
				<c:forEach items="${calibDataList}" var="calibData">
					<tbody>
						<tr>
							<td>Validity Status</td>
							<td bgcolor="${calibData.calibStatusFlag}">${calibData.calibStatusData}</td>
						</tr>

						<tr>
							<td>Identification Number</td>
							<td>${calibData.calibIdentificationNo}</td>
						</tr>

						<tr>
							<td>Serial Number</td>
							<td>${calibData.calibSerialNumber}</td>
						</tr>
						<tr>
							<td>Calibration Ref Number</td>
							<td>${calibData.calibId}</td>
						</tr>

						<tr>
							<td>Supplier Name</td>
							<td>${calibData.calibSupplierName}</td>
						</tr>
						<tr>
							<td>Reminder Date</td>
							<td>${calibData.calibCalibrationReminderDate}</td>
						</tr>

						<tr>
							<td>Due Date</td>
							<td>${calibData.calibCalibrationDueDate}</td>
						</tr>
					</tbody>
				</c:forEach>
			</table>


		</div>
	</div>
</body>
</html>