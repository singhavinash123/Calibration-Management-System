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
	border: 1px solid #ddd;
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
		<h3 align="center"><b>Supplier Detail</b></h3>
		<br>
		<div style="overflow-x: auto;">
			<table width="100%" border="1">
				<tbody>
					<tr>
						<td>Supplier Name</td>
						<td>${supData.supSupplierName}</td>
					</tr>

					<tr>
						<td>Registration Number</td>
						<td>${supData.supRegistrationNum}</td>
					</tr>

					<tr>
						<td>Email Id</td>
						<td>${supData.supEmailId}</td>
					</tr>
					<tr>
						<td>Supplier Number</td>
						<td>${supData.supSupplierNumber}</td>
					</tr>

					<tr>
						<td>Contact</td>
						<td>${supData.supContact}</td>
					</tr>
					
					<tr>
						<td>Status</td>
						<td>${supData.supStatus}</td>
					</tr>

					<tr>
						<td>Full Address</td>
						<td>${supData.supFullAddress}</td>
					</tr>

				</tbody>
			</table>
		</div>
	</div>
</body>
</html>