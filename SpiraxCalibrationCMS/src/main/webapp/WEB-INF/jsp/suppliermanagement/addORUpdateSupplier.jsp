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

<link rel="stylesheet" href="customcss/validationsError.css">

<script type="text/javascript">
	function CheckStatus(val) {
		var element = document.getElementById('instrumentStatus');
		if (val == 'others')
			element.style.display = 'block';
		else
			element.style.display = 'none';
		document.getElementById("instrumentStatus").value = val;
	};
	function CheckCategory(val) {
		var element = document.getElementById('category');
		if (val == 'others')
			element.style.display = 'block';
		else
			element.style.display = 'none';
		document.getElementById("category").value = val;

	};
	function CheckAgency(val) {
		var element = document.getElementById('calibrationAgency');
		if (val == 'others')
			element.style.display = 'block';
		else
			element.style.display = 'none';
		document.getElementById("calibrationAgency").value = val;
	};
	function CheckLocation(val) {
		var element = document.getElementById('location');
		if (val == 'others')
			element.style.display = 'block';
		else
			element.style.display = 'none';
		document.getElementById("location").value = val;
	};
	function CheckStandard(val) {
		var element = document.getElementById('clibrationStandard');
		if (val == 'others')
			element.style.display = 'block';
		else
			element.style.display = 'none';
		document.getElementById("clibrationStandard").value = val;
	};
	function Checkmake(val) {
		var element = document.getElementById('make');
		if (val == 'others')
			element.style.display = 'block';
		else
			element.style.display = 'none';
		document.getElementById("make").value = val;

	};

	function validateForm() {
		
		var name = document.getElementById("suppliername").value;
		var supnum = document.getElementById("supnum").value;
		var email = document.getElementById("emailid").value;

		
		if (name == "") {
			window.alert("Please enter supplier name.");
			name.focus();
			return false;
		}
		if (supnum == "") {
			window.alert("Please enter supplier number.");
			supRegistNum.focus();
			return false;
		}
		if (email == "") {
			window.alert("Please enter a valid e-mail address.");
			email.focus();
			return false;
		}
		if (email.indexOf("@", 0) < 0) {
			window.alert("Please enter a valid e-mail address.");
			email.focus();
			return false;
		}
		if (email.indexOf(".", 0) < 0) {
			window.alert("Please enter a valid e-mail address.");
			email.focus();
			return false;
		}
		if (supNum == "") {
			window.alert("Please enter Supplier number.");
			supNum.focus();
			return false;
		}

		return true;
	};
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

#qrcodeformCss {
	color: black;
	font-family: Cambria;
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
		<h3 id="formtitle">${addORupdatePrheading}</h3>
	</div>
	<div class="container-fluid">
		<span id="hereCss">You are here : </span>
		<%
			AppsPropertyFile appsPropertyFile = new AppsPropertyFile();
		%>
		<span><a href=<%=appsPropertyFile.getURLForKey("homePageURL")%>
			id="hrefCss">Home</a></span> <span>/</span> <span><a
			href=<%=appsPropertyFile.getURLForKey("supplierListURL")%>
			id="hrefCss">Supplier List</a></span> <span>/</span> <span><a href=""><h
					id="hrefCss">${addOrUpdate}</h></a></span> <br> <br>
		<div align="center">
			<b style="color: green; font-size: 15px;">${msg}</b>
		</div>
		<div align="center">
			<b id="errorMsgCss">${alreadyExist}</b>
		</div>
		<br>
		<spring:url value="/saveSupplier" var="saveURL" />
		<form:form modelAttribute="SupData" method="post" action="${saveURL}"
			style="font-size:12px;" onsubmit="return validateForm()"
			id="SupDataForm">
			<form:hidden path="SupSupId" />
			<div class="row" align="left" id="searchboxCss">
				<div class="col-sm-3">
					<div class="form-group">
						<label for="usr">Supplier Name* :</label>
						<form:input path="SupSupplierName" id="suppliername"
							class="form-control" autocomplete="off"/>
						<div>
							<form:errors path="SupSupplierName" cssClass="error"></form:errors>
						</div>
					</div>
				</div>

				<div class="col-sm-3">
					<div class="form-group">
						<label for="usr">Supplier Number* :</label>
						<form:input path="SupSupplierNumber" id="supnum" name="supnum"
							class="form-control" autocomplete="off"/>
						<div>
							<form:errors path="SupSupplierNumber" cssClass="error"></form:errors>
						</div>
					</div>
				</div>

				<!--  
				<div class="col-sm-3">
					<div class="form-group">
						<label for="usr">Registration Number* :</label>
						<form:input path="SupRegistrationNum" id="registernumer" class="form-control" />
						<div>
							<form:errors path="SupRegistrationNum" cssClass="error"></form:errors>
						</div>
					</div>
				</div>
				-->

				<div class="col-sm-3">
					<div class="form-group">
						<label for="usr">Email Id :</label>
						<form:input path="SupEmailId" id="emailid" name="emailid" class="form-control" autocomplete="off"/>
						<div>
							<form:errors path="SupEmailId" cssClass="error"></form:errors>
						</div>
					</div>
				</div>

				<div class="col-sm-3">
					<div class="form-group">
						<label for="usr">Supplier Service Type:</label>
						<form:select path="supSupplierServiceType" class="form-control"
							name="supplierServiceType" style="height: 33px; width: 300px;">
							<c:forEach items="${SupplierServiceTypeList}"
								var="supSupplierServiceType">
								<c:choose>
									<c:when test="${SupData.supSupplierServiceType == supSupplierServiceType}">
										<option value="${SupData.supSupplierServiceType}" selected>${SupData.supSupplierServiceType}</option>
									</c:when>
									<c:otherwise>
										<option value="${supSupplierServiceType}">${supSupplierServiceType}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							<option value="none">none</option>
						</form:select>
						<div>
							<form:errors path="SupStatus" cssClass="error"></form:errors>
						</div>
					</div>
				</div>

				<!--  
				<div class="col-sm-3">
					<div class="form-group">
						<label for="usr">Supplier Number* :</label>
						<form:input path="SupSupplierNumber" id="supnum" class="form-control" />
						<div>
							<form:errors path="SupSupplierNumber" cssClass="error"></form:errors>
						</div>
					</div>
				</div>
				 -->

				<div class="col-sm-3">
					<div class="form-group">
						<label for="usr">Status :</label>
						<form:select path="supStatus" class="form-control"
							name="supStatus" style="height: 33px; width: 300px;">
							<c:forEach items="${SupplierStatusList}" var="SupplierStatus">
								<c:choose>
									<c:when test="${SupData.supStatus == SupplierStatus}">
										<option value="${SupData.supStatus}" selected>${SupData.supStatus}</option>
									</c:when>
									<c:otherwise>
										<option value="${SupplierStatus}">${SupplierStatus}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</form:select>
						<div>
							<form:errors path="SupStatus" cssClass="error"></form:errors>
						</div>
					</div>
				</div>




			</div>
			<div class="row" align="left" id="searchboxCss">
				<!--  
				<div class="col-sm-3">
					<div class="form-group">
						<label for="usr">Contact :</label>
						<form:input path="SupContact" class="form-control" />
						<div>
							<form:errors path="SupContact" cssClass="error"></form:errors>
						</div>
					</div>
				</div>
				-->
				<div class="col-sm-3">
					<div class="form-group">
						<label for="usr">Contact Number:</label>
						<form:input path="SupRegistrationNum" class="form-control" autocomplete="off"/>
						<div>
							<form:errors path="SupRegistrationNum" cssClass="error"></form:errors>
						</div>
					</div>
				</div>
				<div class="col-sm-3">
					<div class="form-group">
						<label for="usr">Contact Person:</label>
						<form:input path="SupContact" class="form-control"
							autocomplete="off" />
						<div>
							<form:errors path="SupContact" cssClass="error"></form:errors>
						</div>
					</div>
				</div>


				<div class="col-sm-3">
					<div class="form-group">
						<label for="usr">Address Line1 :</label>
						<form:input path="SupAddressLine1" class="form-control" autocomplete="off"/>
						<div>
							<form:errors path="SupAddressLine1" cssClass="error"></form:errors>
						</div>
					</div>
				</div>

				<div class="col-sm-3">
					<div class="form-group">
						<label for="usr">Address Line2 :</label>
						<form:input path="SupAddressLine2" class="form-control" autocomplete="off"/>
						<div>
							<form:errors path="SupAddressLine2" cssClass="error"></form:errors>
						</div>
					</div>
				</div>
			</div>

			<div class="row" align="left" id="searchboxCss">
				<div class="col-sm-3">
					<div class="form-group">
						<label for="usr">Address Line3 :</label>
						<form:input path="SupAddressLine3" class="form-control" autocomplete="off"/>
						<div>
							<form:errors path="SupAddressLine3" cssClass="error"></form:errors>
						</div>
					</div>
				</div>
				<div class="col-sm-3">
					<div class="form-group">
						<label for="usr">Address Line4 :</label>
						<form:input path="SupAddressLine4" class="form-control" autocomplete="off" />
						<div>
							<form:errors path="SupAddressLine4" cssClass="error"></form:errors>
						</div>
					</div>
				</div>

				<div class="col-sm-3">
					<div class="form-group">
						<label for="usr">Address Line5 :</label>
						<form:input path="SupAddressLine5" class="form-control" autocomplete="off" />
						<div>
							<form:errors path="SupAddressLine5" cssClass="error"></form:errors>
						</div>
					</div>
				</div>

				<div class="col-sm-3">
					<div class="form-group">
						<label for="usr">Address Line6 :</label>
						<form:input path="SupAddressLine6" class="form-control" autocomplete="off"/>
						<div>
							<form:errors path="SupAddressLine6" cssClass="error"></form:errors>
						</div>
					</div>
				</div>
			</div>
			<br>
			<div class="row" align="center">
				<div class="col-md-4"></div>
				<div class="col-md-4">
					<button type="submit" class="btn btn-primary" id="#searchBtnCss">Save</button>
				</div>
				<div class="col-md-4"></div>
			</div>
		</form:form>
	</div>
</body>
</html>