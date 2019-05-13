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

<link rel="stylesheet" href="customcss/formCss.css">

</head>
<body>


	<div id="header">
		<jsp:include page="/WEB-INF/jsp/header/firstpageheader.jsp" />
	</div>

	<div class="container-fluid" style="height:100%;">
		<div class="row">
			<div class="col-md-6">
				<img src="images/loginLeft.jpg" class="img-responsive">
			</div>
			<div class="col-md-6">
				<!-- <img src="images/logo.jpg" class="img-responsive">
				 -->
				<div style="min-height: 150px;"></div>
				<div class="row">
				
				   <security:authorize
					access="hasAuthority('${adminRole}') or hasAuthority('${userRole}')  or hasAuthority('${engineerRole}') or hasAuthority('${managerRole}') or hasAuthority('${approver1Role}') or hasAuthority('${approver2Role}')">
					<div class="col-md-4">
						<a href="/home" id="firstPageBtnCss">Dashboard</a>
					</div>
					</security:authorize>
					
					<security:authorize
					access="hasAuthority('${adminRole}') or hasAuthority('${userRole}')  or hasAuthority('${engineerRole}') or hasAuthority('${managerRole}') or hasAuthority('${approver1Role}') or hasAuthority('${approver2Role}')">
					<div class="col-md-4">
						<a href="/calibrationMain" id="firstPageBtnCss">calibration</a>
					</div>
					</security:authorize>

					<div class="col-md-4">
						<a href="/scanText" id="firstPageBtnCss">Trace
							Me</a>
					</div>
				</div>

				<div style="min-height: 150px;"></div>
				<img src="images/customerFirst.jpg" class="img-responsive">
			</div>
		</div>
	</div>
</body>
</html>