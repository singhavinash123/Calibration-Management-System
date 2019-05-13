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

#headingCSS {
	color: #20B2AA;
	font-family: Cambria;
	font-size: 22px;
}

#formtitle {
	color: black;
	font-family: Cambria;
	font-size: 22px;
	font-weight: bold;
}

#hrefCss {
	color: #48D1CC;
	font-family: Cambria;
	font-size: 16px;
	font-weight: bold;
}

#hereCss {
	color: black;
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

tbody td:nth-child(even) {
	background-color: #D3D3D3;
	font-family: Cambria;
	font-size: 16px;
}

tbody td:nth-child(odd) {
	background-color: #20B2AA;
	font-family: Cambria;
	font-size: 16px;
}

</style>
</head>
<body>

	<div id="header">
		<jsp:include page="/WEB-INF/jsp/header/header.jsp" />
	</div>
	<div align="center">
		<h3 id="formtitle">${calibCertInfo}</h3>
	</div>
	<div class="container-fluid">
		<span id="hereCss">You are here : </span>
		<%
			AppsPropertyFile appsPropertyFile = new AppsPropertyFile();
		%>
		<span><a href=<%=appsPropertyFile.getURLForKey("homePageURL")%> id="hrefCss">Home</a></span>
		<span>/ </span><span><a href=""><h id="hrefCss">Calibration Information</h></a></span> <br>
		<br>

		<div class="row" align="left" id="searchboxCss">
			<div class="col-sm-3">
				<div class="form-group">
					<label for="usr">Calibration Reference Number:</label> <input
						type="text" name="serialNum" class="form-control"
						value="${calibData.mainEcCalibId}" readonly><br>
					<div></div>
				</div>
			</div>

			<div class="col-sm-3">
				<div class="form-group">
					<label for="usr">Identification Number :</label> <input type="text"
						name="identityNum" class="form-control"
						value="${calibData.mainIdentificationId}" readonly><br>
				</div>
			</div>

			<div class="col-sm-3">
				<div class="form-group">
					<label for="usr">Due Date :</label> <input type="text"
						name="identityNum" class="form-control"
						value="${calibData.mainDueDate}" readonly><br>
				</div>
			</div>
			<div class="col-sm-3">
				<div class="form-group">
					<label for="usr">Certificate Number :</label> <input type="text"
						name="identityNum" class="form-control"
						value="${calibData.mainCalibrationCertificate}" readonly><br>
				</div>
			</div>
		</div>
		<div align="center">
			<b id="successMsgCss">${msg}</b>
			 <b id="errorMsgCss">${onlyPdf}</b>
		</div>

		<div align="center">
			<div style="overflow-x: auto;">
				<display:table name="certData" id="displaytable" pagesize="10"
					class="table">

					<display:column title="Sr. No.">
						<c:out value="${displaytable_rowNum}" />
					</display:column>

					<display:column property="certCalibId"
						title="Ref. No." style="white-space: nowrap;"></display:column>
					<display:column property="certCertificateName"
						title="Certificate Name" style="white-space: nowrap;"></display:column>

					<display:column property="certCalibCertificateOptionName"
						title="Certificate Type" style="white-space: nowrap;"></display:column>

					<display:column property="certUploadCertDate"
						title="Certificate Upload Date" style="white-space: nowrap;"></display:column>

					<display:column title="Action">
						<spring:url
							value="/downloadCerticate/${displaytable.certWarrantyId}"
							var="downloadCerticateURL"></spring:url>
						<a href="${downloadCerticateURL}"><button type="button"
								class="btn btn-success" id="searchBtnCss">View/Download</button></a>
					</display:column>

					<display:column title="Action">
						<spring:url
							value="/deleteCerticate/${displaytable.certWarrantyId}"
							var="removecrtificateURL"></spring:url>
						<a href="${removecrtificateURL}"><button type="button"
								class="btn btn-danger"
								onclick="return ConfirmDelete(${displaytable.certWarrantyId});" id="searchBtnCss">Remove</button></a>
					</display:column>

				</display:table>

				<spring:url value="bootstrap/js/bootstrap.min.js"
					var="boostrapJsURL"></spring:url>
				<spring:url value="bootstrap/js/jquery-1.7.1.min.js"
					var="jqueryJsURL"></spring:url>

				<script src="${boostrapJsURL}"></script>
				<script src="${jqueryJsURL}"></script>
			</div>
			<br>
			<div class="row" align="center">
				<div class="col-sm-4"></div>
				<div class="col-sm-4">
					<form method="Post" action="/uploadCertificate"
						enctype="multipart/form-data">
						<input type="hidden" id="calibid" name="calibid"
							value="${calibData.mainEcCalibId}"> <input type="hidden"
							id="duedate" name="duedate" value="${calibData.mainDueDate}">

						<input type="hidden" id="identitynum" name="identitynum"
							value="${calibData.mainIdentificationId}"> <input
							type="hidden" id="certname" name="certname"
							value="${calibData.mainCalibrationCertificate}">

						<div class="row">
							<div class="col-sm-8">
								<select class="form-control" name="certificateoption"
									style="height: 33px;" id="searchboxCss">
									<option value="">-- Select Certificate Type --</option>
									<c:forEach items="${certificateLookup}" var="certificate">
										<option value="${certificate}">${certificate}</option>
									</c:forEach>
								</select>
							</div>
							<div class="col-sm-1"></div>
							<div class="col-sm-3">
								<div class="form-group">
									<input type="file" name="certificate" size="50" id="searchBtnCss"> <br>
									<br> <input type="submit" value="Upload"
										class="btn btn-info" id="searchBtnCss">
								</div>
							</div>

							<div class="col-sm-4"></div>
						</div>
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
					</form>
				</div>
				<div class="col-sm-4"></div>
			</div>
		</div>
	</div>
</body>
<script>
	function ConfirmDelete(value) {
		console.log("Inside ConfirmDelete Method" + value);
		var x = confirm("Are you sure you want to delete " + value + " ??");
		if (x)
			return true;
		else
			return false;
	}
</script>
</html>