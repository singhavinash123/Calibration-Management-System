<%@page import="com.spiraxcalibration.WebConfig.AppsPropertyFile"%>
<%@page import="java.io.OutputStreamWriter"%>
<%@page import="java.io.OutputStream"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Bootstrap CSS CDN -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">

<!-- Scrollbar Custom CSS -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.5/jquery.mCustomScrollbar.min.css">

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>

<!-- Font Awesome JS -->
<script defer
	src="https://use.fontawesome.com/releases/v5.0.13/js/solid.js"></script>

<script defer
	src="https://use.fontawesome.com/releases/v5.0.13/js/fontawesome.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$("header").hide();

		('#qrcode').qrcode(window.location.href);
	});
</script>

<style>
table {
	border-collapse: collapse;
	border-spacing: 0;
	width: 100%;
	border: 1px solid #ddd;
	font-size: 16px;
}

th, td {
	text-align: left;
	padding: 8px;
}

tr:nth-child(even) {
	background-color: white
}

@media print {
	#header {
		display: none;
	}
	#hereId {
		display: none;
	}
	#printPageButton {
		display: none;
	}
	#status {
		display: none;
	}
	#inner {
		width: 50%;
		margin: 0 auto;
	}
}
</style>
<link rel="stylesheet" href="customcss/formCss.css">
<style>
#supplierCSS {
	border: 2px solid #20B2AA;
	border-radius: 12px;
}

#searchBtnCss {
	color: white;
	font-family: Cambria;
	font-size: 16px;
	font-weight: bold;
}

#equipmentCSS {
	border: 2px solid #20B2AA;
	border-radius: 12px;
}

#calibrationCSS {
	border: 2px solid #20B2AA;
	border-radius: 12px;
}

#certificateCSS {
	border: 2px solid #20B2AA;
	border-radius: 12px;
}

#headingCSS {
	color: #20B2AA;
	font-family: Cambria;
	font-size: 22px;
}

#backgroundGreen {
	background-color: #48D1CC;
	font-family: Cambria;
	font-size: 16px;
}

#formtitle {
	color: black;
	font-family: Cambria;
	font-size: 22px;
	font-weight: bold;
}

#backgroundGrey {
	background-color: #DCDCDC;
	font-family: Cambria;
	font-size: 16px;
}

#hrefCss {
	color: #48D1CC;
	font-family: Cambria;
	font-size: 16px;
	font-weight: bold;
}

#qrBarCodeCss {
	color: black;
	font-family: Cambria;
	font-size: 18px;
	font-weight: bold;
}

#hereCss {
	color: black;
	font-family: Cambria;
	font-size: 16px;
	font-weight: bold;
}

#addNewCss {
	color: white;
	font-family: Cambria;
	font-size: 16px;
	font-weight: bold;
}

#searchboxCss {
	color: black;
	font-family: Cambria;
	font-size: 16px;
	font-weight: bold;
}

#loginCSS {
	border: 2px solid #20B2AA;
	border-radius: 12px;
}

#messageCss {
	font-family: Cambria;
	font-size: 16px;
	font-weight: bold;
}

#errorMsgCss {
	font-family: Cambria;
	font-size: 16px;
	font-weight: bold;
	color: red;
}

#successMsgCss {
	font-family: Cambria;
	font-size: 16px;
	font-weight: bold;
	color: green;
}

#btnCss {
	color: white;
	font-family: Cambria;
	font-size: 16px;
	font-weight: bold;
	background-color: #20B2AA;
}

#usernamePasswordCss {
	font-family: Cambria;
	background-color: #33afaf;
	width: 100%;
	color: #fff;
	padding: 5px; "
	font-weight: bold;
}

#forgotpasswordCss {
	font-family: Cambria;
	font-weight: bold;
	text-align: center;
}

#firstPageBtnCss {
	font-family: Cambria;
	background-color: #33afaf;
	width: 100%;
	color: #fff;
	padding: 5px;
	display: block;
	text-align: center;
	font-size: 16px;
	font-weight: bold;
}

#sideBardHesdingCss {
	font-family: Cambria;
	width: 100%;
	color: #fff;
	padding: 5px;
	display: block;
	font-size: 16px;
	font-weight: bold;
}

body {
	margin: 0;
	font-family: Arial, Helvetica, sans-serif;
}

.topnav {
	overflow: hidden;
	background-color: #e9e9e9;
}

.topnav a {
	float: left;
	display: block;
	color: black;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
	font-size: 17px;
}

.topnav a:hover {
	background-color: #ddd;
	color: black;
}

.topnav a.active {
	background-color: #20B2AA;
	color: white;
}

.topnav .search-container {
	float: right;
}

.topnav input[type=text] {
	padding: 6px;
	margin-top: 8px;
	font-size: 17px;
	border: none;
}

.topnav .search-container button {
	float: right;
	padding: 6px 10px;
	margin-top: 8px;
	margin-right: 16px;
	background: #ddd;
	font-size: 17px;
	border: none;
	cursor: pointer;
}

.topnav .search-container button:hover {
	background: #ccc;
}

@media screen and (max-width: 600px) {
	.topnav .search-container {
		float: none;
	}
	.topnav a, .topnav input[type=text], .topnav .search-container button {
		float: none;
		display: block;
		text-align: left;
		width: 100%;
		margin: 0;
		padding: 14px;
	}
	.topnav input[type=text] {
		border: 1px solid #ccc;
	}
}

#welcomeCss {
	color: black;
	font-family: Cambria;
	font-size: 22px;
	font-weight: bold;
}

#loggedinCss {
	color: #20B2AA;
	font-family: Cambria;
	font-size: 20px;
	font-weight: bold;
}

#loginbtnCss {
	color: white;
	font-family: Cambria;
	font-weight: bold;
	font-size: 16px;
	background-color: #20B2AA;
}

#homeCss {
	color: white;
	font-family: Cambria;
	font-weight: bold;
	font-size: 18px;
}
</style>
</head>
<body>
	<div id="header">
		<jsp:include page="/WEB-INF/jsp/header/header.jsp" />
	</div>
	<div align="center">
		<h3 id="formtitle">QR/BAR Code List</h3>
	</div>

	<div class="container-fluid">
		<div id="hereId">
			<span>You are here : </span>
			<%
				AppsPropertyFile appsPropertyFile = new AppsPropertyFile();
			%>
			<span><a
				href=<%=appsPropertyFile.getURLForKey("homePageURL")%> id="hrefCss">Home</a></span><span>
				/</span> <span><a href=<%=""%>><h id="hrefCss">print_QR_BAR_Code</h></a></span>
			<br> <br>
			<div style="overflow-x: auto;">
				<table width="100%" border="1">
					<thead>
						<tr>
							<th>Calibration Ref. No.</th>
							<th>Identification Number</th>
							<th>Calibrated Date</th>
							<th>Due Date</th>
						</tr>
					</thead>

					<c:forEach items="${calibData}" var="calibdata">
						<tbody>
							<tr>
								<td>${calibdata.mainEcCalibId}</td>
								<td>${calibdata.mainIdentificationId}</td>
								<td>${calibdata.mainCalibrationDate}</td>
								<td>${calibdata.mainDueDate}</td>

							</tr>
						</tbody>
					</c:forEach>
				</table>
			</div>
		</div>
		<br>
		<div class="container" align="center">
			<div class="row" align="center">

				<div class="col-sm-3"></div>
				<div class="col-sm-6">
					<div style="text-align: center;" id="hrefCss">


						<br> <a id="status"
							href="${pageContext.request.contextPath }/detailsEquipment/${calibdata.mainIdentificationId}">View
							Status</a>

						<div id="qrcode">
							<table>
								<tr>
									<td>
										<div align="center" id="searchboxCss">
											${calibdata.mainEcCalibId} <img
												src="${pageContext.request.contextPath }/generateQrCode?productId=${calibdata.mainIdentificationId}">
											${calibdata.mainIdentificationId}
										</div> <!-- 
										<table>
											<tr>
												<th>ID. No.</th>
												<th>Ref. No.</th>
											</tr>
											<tr id="qrBarCodeCss">
												<td>${calibdata.mainIdentificationId}</td>
												<td>${calibdata.mainEcCalibId}</td>
											</tr>
											</table>
											 -->
									</td>
									<td></td>
									<td></td>
									<td>
										<div align="center" id="searchboxCss">
											${calibdata.mainEcCalibId} <img
												src="${pageContext.request.contextPath }/generateBarCode?productId=${calibdata.mainIdentificationId}">
											${calibdata.mainIdentificationId}
										</div> 
										<!-- 
										<br>
										<table>
											<tr>
												<th>ID. No.</th>
												<th>Ref. No.</th>
											</tr>
											<tr id="qrBarCodeCss">
												<td>${calibdata.mainIdentificationId}</td>
												<td>${calibdata.mainEcCalibId}</td>
											</tr>
										</table>
										 -->
									</td>
									<td></td>
								</tr>
							</table>
						</div>

						<div id="searchBtnCss">
							<br> <input type="button" value="Print" id="printPageButton"
								onclick="javascript:window.print()"
								class="btn btn-primary btn-sm" aria-hidden="true">
						</div>
					</div>
				</div>
				<div class="col-sm-3"></div>

			</div>
		</div>
	</div>
</body>
</html>