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

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>

<!-- Font Awesome JS -->
<script defer
	src="https://use.fontawesome.com/releases/v5.0.13/js/solid.js"></script>

<script defer
	src="https://use.fontawesome.com/releases/v5.0.13/js/fontawesome.js"></script>

<script type="text/javascript" src="bootstrap/js/jquery-3.3.1.js"></script>

<link rel="stylesheet" href="customcss/formCss.css">

</head>
<body>
	<div id="header">
		<jsp:include page="/WEB-INF/jsp/header/header.jsp" />
	</div>
	<div align="center">
		<h3 id="formtitle">Scan QR/BAR Code</h3>
	</div>

	<div class="container-fluid">
		<span id="hereCss">You are here : </span>
		<%
			AppsPropertyFile appsPropertyFile = new AppsPropertyFile();
		%>
		<span><a href=<%=appsPropertyFile.getURLForKey("homePageURL")%>
			id="hrefCss">Home</a></span><span> / </span> <span><a href=<%=""%>><h
					id="hrefCss">print_QR_BAR_Code</h></a></span>

		<div align="center">
			<b id="errorMsgCss">${msg}</b>
		</div>
		<br>
		<div class="container" align="center">
			<div class="row">

				<form action="/scanAndValidate" method="POST">
					<div class="row">
						<div class="col-sm-10">
							<input type="text" name="scanText" id="scanText"
								class="form-control" placeholder="Enter Identification number.."
								id="searchboxCss" />
						</div>
						<div class="col-sm-2">
							<input type="submit" value="Scan" value="Search"
								class="btn btn-info" id="searchBtnCss" />
						</div>


						<div class="col-sm-2"></div>

					</div>
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />

				</form>
			</div>
		</div>
		

	</div>
</body>
</html>