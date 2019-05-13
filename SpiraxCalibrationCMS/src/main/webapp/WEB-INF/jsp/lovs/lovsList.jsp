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

<script>
	function Checklovprocess(val) {
		document.getElementById("lovName").value = val;
		List<String> data =  getDataWithProcess(val);

	};
	function getLovNameByProcess() {
		var x = document.getElementById('lovProcess').value
		document.getElementById("lovName").value = x;

	}
</script>

<link rel="stylesheet" href="customcss/formCss.css">

</head>
<body onload="getLovNameByProcess()">

	<div id="header">
		<jsp:include page="/WEB-INF/jsp/header/header.jsp" />
	</div>
	<div align="center">
		<h3 id="formtitle">Lov List</h3>
	</div>
	<div class="container-fluid">
		<span id="hereCss">You are here : </span>
		<%
			AppsPropertyFile appsPropertyFile = new AppsPropertyFile();
		%>
		<span><a href=<%=appsPropertyFile.getURLForKey("homePageURL")%>
			id="hrefCss">Home</a></span><span> /</span> <span><a href=""><h
					id="hrefCss">Lov List</h></a></span> <br> <br>
		<form action="/searchlov" method="post">
			<div class="row">
				<div class="col-sm-4" id="searchboxCss">
					<div class="form-group">
						<label for="usr">Lov Process :</label> <select id="lovProcess"
							class="form-control" name="lovProcess"
							onchange='Checklovprocess(this.value);' style="height: 35px;">
							<option value="" style="display: none">-- Select Lov
								Process --</option>
							<c:forEach items="${lovProcessWithNameList}"
								var="lovProcessWithName">
								<option value="${lovProcessWithName.getKey()}">${lovProcessWithName.getKey()}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="col-sm-3" id="searchboxCss">
					<div class="form-group">
						<label for="usr">Lov Name :</label> <select id="lovName"
							class="form-control" name="lovName" style="height: 35px;">
							<option value="" style="display: none">-- Select Lov
								Name --</option>
							<c:forEach items="${lovName}" var="lovProcessWithName">
								<option value="${lovProcessWithName}">${lovProcessWithName}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="col-sm-4">
				     <a href="/tutorialVideos/LOV_MASTER_MAINTENANCE.xlsx" target="_blank">DownLoad Excel Sheet</a>.
				</div>
			</div>
			<div class="row">
				<div class="col">
					<input type=submit value="Search" class="btn btn-info"
						class="form-control" id="searchBtnCss">
				</div>
			</div>
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form>
		<br>
		<div class="row">
			<div class="col-sm-4">
				<spring:url value="/addLov" var="AddLovURL"></spring:url>
				<a href="${AddLovURL}"><button type="button"
						class="btn btn-success" id="addNewCss">Add Lov</button> </a>
			</div>
		</div>
		<!--  
		<div class="row">
			<form method="Post" action="/searchProduct">
				<div class="container">
					<div class="row">
						<div class="col-md-auto">
							<label for="usr">Enter Identity Number:</label> <input
								type="text" placeholder="Identity Number..." name="identityNum"
								class="form-control">
						</div>
						<div class="col-md-auto">
							<label for="usr">Enter Serial Number:</label> <input type="text"
								placeholder="Serial Number..." name="serialNumber"
								class="form-control">
						</div>
						<div class="col-md-auto">
							<label for="usr">Select Location:</label> <select
								class="form-control" name="location"
								style="height: 33px; width: 300px;">
								<option value=""></option>
								<c:forEach items="${locationList}" var="location">
									<option value="${location}">"${location}"</option>
								</c:forEach>
								<option value="">others</option>
							</select>
						</div>
						<div class="col-md-auto">
							<label for="usr">Select Make:</label> <select
								class="form-control" name="make"
								style="height: 33px; width: 300px;">
								<option value=""></option>
								<c:forEach items="${makeList}" var="make">
									<option value="${make}">"${make}"</option>
								</c:forEach>
								<option value="">others</option>
							</select>
						</div>
					</div>
					<div class="row">
						<div class="col">
							<br> <input type=submit value="Search" class="btn btn-info"
								class="form-control"> <input type="button"
								class="btn btn-warning" name="reset_form" value="Clear"
								onclick="this.form.reset();">

						</div>
						
						<div class="col-12"></div>

					</div>

				</div>
			</form>
		</div>
-->
		<br>
		<div align="center">
			<b style="color: green; font-size: 15px;">${msg}</b>
		</div>

		<div align="center">
			<b style="color: red; font-size: 15px;">${error}</b>
		</div>

		<div style="overflow-x: auto;" id="displayTagCss">
			<spring:url value="/lovMaintenance" var="listURL" />
			<display:table name="lovsList" id="displaytable"
				requestURI="${listURL}" pagesize="10" class="table"
				sort="list">
				
				<display:column title="Sr. no.">
					<c:out value="${displaytable_rowNum}" />
				</display:column>
				
				<display:column title="Action">
					<spring:url value="/updateLov/${displaytable.lId}"
						var="updateLovURL"></spring:url>
					<a href="${updateLovURL}"><button type="button"
							class="btn btn-primary" id="searchBtnCss">Update</button></a>
				</display:column>

				<display:column title="Action">
					<spring:url value="/deleteLov/${displaytable.lId}"
						var="deleteLovURL"></spring:url>
					<a href="${deleteLovURL}"><button type="button"
							class="btn btn-danger"
							onclick="return ConfirmDelete(${displaytable_rowNum});"
							id="searchBtnCss">Delete</button></a>
				</display:column>


				<display:column property="lovProcess" title="LOV PROCESS"></display:column>
				<display:column property="lovName" title="LOV NAME"></display:column>
				<display:column property="lovValue" title="LOV VALUE"></display:column>
				<display:column property="description" title="DESCRIPTION"></display:column>
				<display:column property="lovComment" title="LOV COMMENT"></display:column>
					
			</display:table>

			<spring:url value="bootstrap/js/bootstrap.min.js" var="boostrapJsURL"></spring:url>
			<spring:url value="bootstrap/js/jquery-1.7.1.min.js"
				var="jqueryJsURL"></spring:url>

			<script src="${boostrapJsURL}"></script>
			<script src="${jqueryJsURL}"></script>

		</div>
	</div>
</body>
<script>
	function ConfirmDelete(value) {
		console.log("Inside ConfirmDelete Method" + value);
		var x = confirm("Are you sure you want to delete record no. " + value + " ??");
		if (x)
			return true;
		else
			return false;
	}
</script>
</html>