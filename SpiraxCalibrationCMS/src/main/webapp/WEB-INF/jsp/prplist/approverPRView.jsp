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
<style>
.purchase table, th, tr {
	border: 2px solid black;
	border-collapse: collapse;
	text-align: center;
	width: 300px;
}

.purchase table {
	height: 20px;
}

.purchase td {
	padding: 0px;
	text-align: center;
	width: 200px;
}

.imgeye {
	margin: -20px;
	margin-bottom: 30px;
	padding: 0px;
	float: right;
	float: top;
	width: 150px;
	height: 100px;
	margin-left: 20px;
	margin-right: 1px;
}

.grid-container {
	display: grid;
	grid: 10px/auto auto auto;
	grid-gap: 5px;
	padding: -300px;
}

.purchasenum1 td {
	padding: 3px;
	height: 1px;
	width: -90px;
	text-align: left;
}

.purchasenum1 table {
	padding: 0px;
	width: 2px;
}

.purchasenum3 table {
	border: 1px solid black;
	margin-top: -100px;
}

.purchasenum5 table {
	height: 100px;
}

.purchasenum6 table {
	border: 0px solid black;
	margin-bottom: -20px;
}

.purchasenum6 th {
	padding: 13px;
	border-style: solid;
	border-top-color: black;
}

.purchasenum6 td {
	padding: 8px;
}

.purchasenum7 table {
	border: 1px solid black;
	width: 100%
}

.purchasenum7 th {
	width: 25%
}

.purchasenum10 th {
	padding: 10px;
	text-align: center;
}
</style>

</head>

<body>

	<div id="header">
		<jsp:include page="/WEB-INF/jsp/header/header.jsp" />
	</div>
	<div class="container-fluid">
		<h4 align="center">View And Approve Purchase Requisition</h4>
		<span>You are here : </span>
		<%
			AppsPropertyFile appsPropertyFile = new AppsPropertyFile();
		%>
		<span><a href=<%=appsPropertyFile.getURLForKey("homePageURL")%>>home</a></span><span>/</span>
		<span><a href=<%=""%>>purchaseRequisitionList</a></span> <br> <br>
		<br> <br>

		<div align="center">
			<b style="color: green; font-size: 15px;">${msg}</b>
		</div>

		<div>
			<div class="container" id="data">
				<div class="image">
					<img class="imgeye" style="width: 180px; height: 99px;"
						src="/images/unnamed.png" alt="my eye">
				</div>
				<table class="purchase">
					<thead>
						<tr>
							<th colspan="6" style="width: 10%"><h3>Purchase
									Requisition</h3></th>
						</tr>
				</table>
				<div class="grid-container"></div>
				<table border="1" class="purchasenum1"
					style="width: 29.5%; margin-left: 945px; border-collapse: collapse;">
					<tr>
						<td><strong>PurchaseRequisitionNo.</strong></td>
						<td><strong>Auto</strong></td>
					</tr>
					<tr>
						<td><strong>Date</strong></td>
						<td><strong>Auto</strong></td>
					</tr>
					<tr>
						<td><strong>Purchase Order No.</strong></td>
						<td><strong>Blank</strong></td>
					</tr>
				</table>

				<div class="grid-container"></div>
				<div class="container" style="width: 100%;">
					<div>
						<div>
							<table rules="rows" class="purchasenum2"
								style="width: 33%; border: 1px solid black;">
								<tbody>
									<tr>
										<td height="30px"><strong>name of the department</strong></td>
									</tr>
									<tr>
										<td height="30px"><strong>to be used(for a
												machine)</strong></td>
									</tr>

								</tbody>
							</table>

						</div>
						<table rules="rows" class="purchasenum3"
							style="width: 1%; margin-left: 450px; margin-top: -72px;">
							<tr>
								<td height="30px">:</td>
							</tr>
							<tr>
								<td height="30px">:</td>
							</tr>
						</table>
						<div>
							<table rules="rows" class="purchasenum4"
								style="width: 65%; margin-left: 473px; margin-top: -64px; border: 1px solid black">
								<tbody>
									<tr>
										<td height="30px"><strong>Quality Functions</strong></td>
									</tr>
									<tr>
										<td height="30px"><strong>Periodical
												Calibrations</strong></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div class="grid-container"></div>
				<table class="purchasenum5">
					<tfoot>
						<tr>
							<th colspan="10"
								style="text-align: left; height: 40px; padding: 10px; width: 10%;">Kindly
								approve and process procurement of following material(s):</th>
						</tr>
					</tfoot>
				</table>
				<table border="1" class="purchasenum6"
					style="width: 100%; margin-top: -4px; border-collapse: collapse;">
					<tr>
						<th style="width: 10%;"><strong>Sl.No</strong></th>
						<th style="width: 20%;"><strong>Part Code(If Any)</strong></th>
						<th style="width: 40%;"><strong>Product Description</strong></th>
						<th style="width: 4%;"><strong>Quantity</strong></th>
						<th style="width: 10%;"><strong>Budgetrie Cost</strong></th>
						<th style="width: 48%;"><strong>Delivery</strong></th>
					</tr>
					<tr>
						<td><strong>1</strong></td>
						<td><strong></strong></td>
						<td><strong>Vernier Calipers</strong></td>
						<td><strong>1</strong></td>
						<td><strong></strong></td>
						<td><strong></strong></td>
					</tr>
					<tr>
						<td><strong>2</strong></td>
						<td><strong>Auto</strong></td>
						<td><strong>Auto</strong></td>
						<td><strong>Auto</strong></td>
						<td><strong>Auto</strong></td>
						<td><strong>Pick Calibration Due Date</strong></td>
					</tr>
					<tr>
						<td><strong>3</strong></td>
						<td><strong></strong></td>
						<td><strong></strong></td>
						<td><strong></strong></td>
						<td><strong></strong></td>
						<td><strong></strong></td>
					</tr>
					<tr>
						<td><strong>4</strong></td>
						<td><strong></strong></td>
						<td><strong></strong></td>
						<td><strong></strong></td>
						<td><strong></strong></td>
						<td><strong></strong></td>
					</tr>
					<tr>
						<td><strong>5</strong></td>
						<td><strong></strong></td>
						<td><strong></strong></td>
						<td><strong></strong></td>
						<td><strong></strong></td>
						<td><strong></strong></td>
					</tr>
					<tr>
						<td><strong>6</strong></td>
						<td><strong></strong></td>
						<td><strong></strong></td>
						<td><strong></strong></td>
						<td><strong></strong></td>
						<td><strong></strong></td>
					</tr>
					<tr>
						<td><strong>7</strong></td>
						<td><strong></strong></td>
						<td><strong></strong></td>
						<td><strong></strong></td>
						<td><strong></strong></td>
						<td><strong></strong></td>
					</tr>
					<tr>
						<td><strong>8</strong></td>
						<td><strong></strong></td>
						<td><strong></strong></td>
						<td><strong></strong></td>
						<td><strong></strong></td>
						<td><strong></strong></td>
					</tr>
					<tr>
						<td><strong>9</strong></td>
						<td><strong></strong></td>
						<td><strong></strong></td>
						<td><strong></strong></td>
						<td><strong></strong></td>
						<td><strong></strong></td>
					</tr>
					<tr>
						<td><strong>10</strong></td>
						<td><strong></strong></td>
						<td><strong></strong></td>
						<td><strong></strong></td>
						<td><strong></strong></td>
						<td><strong></strong></td>
					</tr>
				</table>
				<div class="grid-container"></div>
				<table class="purchasenum7">
					<tr>
						<th style="padding: 15px; text-align: left;"><strong>Purchase
								Justification</strong></th>
						<td style="margin-left: 10px; border: 1px solid white"><strong>:</strong></td>
						<td
							style="padding: 15px; margin-left: 10px; border: 2px solid black; text-align: left; width: 75%"><strong>Periodic
								Calibration</strong></td>
					</tr>
				</table>
				<div class="grid-container"></div>
				<table class="purchasenum8">
					<tr>
						<th style="padding: 15px; text-align: left;"><strong>Budgetry
								Quotation </strong></th>
						<td style="margin-left: 10px; border: 1px solid white"><strong>:</strong></td>
						<td
							style="padding: 15px; margin-left: 10px; border: 2px solid black; text-align: center; width: 75%"><strong>Yes
						</strong></td>
					</tr>
				</table>
				<div class="grid-container"></div>
				<table class="purchasenum9">
					<tr>
						<th style="padding: 15px; text-align: left;"><strong>Suggested/Preferred
								Supplier </strong></th>
						<td style="margin-left: 10px; border: 1px solid white"><strong>:</strong></td>
						<td
							style="padding: 15px; margin-left: 10px; border: 2px solid black; text-align: center; width: 75%"><strong>Auto
						</strong></td>
					</tr>
				</table>
				<div class="grid-container"></div>
				<table border="1" class="purchasenum10"
					style="width: 100%; margin-top: -4px; border-collapse: collapse; text-align: center">
					<tr>
						<th style="width: 60%; padding: 30px;"><strong>Requested
								By(Name,E.code & Signature)</strong></th>
						<th style="width: 20%;"><strong>Approved
								By(Name,E.code & Signature)Head Engineering Quality</strong></th>
						<th style="width: 40%;"><strong>Approved
								By(Name,E.code & Signature)Head Operations</strong></th>
					</tr>
					<tr>
						<td style="padding: 20px">UserName</td>
						<td style="padding: 20px">Ravi</td>
						<td style="padding: 20px">Subhash</td>
					</tr>

				</table>
				<div>
					<button onclick="javascript:demoFromHTML();">GENERATE PDF</button>
				</div>
			</div>
		</div>

	</div>
</body>
</html>