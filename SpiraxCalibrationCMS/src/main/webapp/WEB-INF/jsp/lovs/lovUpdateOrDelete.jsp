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

<script type="text/javascript">
	function CheckStatus(val) {
		var element = document.getElementById('instrumentStatus');
		if (val == 'others')
			element.style.display = 'block';
		else
			element.style.display = 'none';
		    document.getElementById("instrumentStatus").value = val;
	};
	function onChangeProcess(val) {
		if (val == 1) {
			document.getElementById("processSelect").value = 1;
			document.getElementById("lovform").submit();
			document.getElementById("lovNames").value = document.getElementById("lovName").value;

		}
	};

	function CheckProcessName(val) {
		var element = document.getElementById("lovNames");
		if (val == 'others')
			element.style.display = 'block';
		else
			element.style.display = 'none';
		document.getElementById("lovNames").value = val;

	};
	function processNameChange(val) {
		document.getElementById("myLov").value = val.value;
	};
	
	window.onload = function() {
		document.getElementById("lovNames").value = document.getElementById("myLov").value;
	};
	
</script>

<spring:url value="customcss/formCss.css" var="bootstrapCssURL"></spring:url>
<link href="${bootstrapCssURL}" rel="stylesheet">

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
		<span><a href=<%=appsPropertyFile.getURLForKey("homePageURL")%>  id="hrefCss">Home</a></span>
		<span>/</span> <span><a
			href=<%=appsPropertyFile.getURLForKey("lovMaintenenceURL")%>  id="hrefCss">Lov
				List</a></span> <span>/</span> <span><a href=""><h  id="hrefCss">${addOrUpdate}</h></a></span>
		<br> <br>
		<div align="center">
			<b id="successMsgCss">${msg}</b>
		</div>
		<div align="center">
			<b id="errorMsgCss">${error}</b>
		</div>

		<spring:url value="/saveLov" var="saveURL" />
		<form:form modelAttribute="lovData" method="post" action="${saveURL}"
			style="font-size:12px;" id="lovform">
			<form:hidden path="lId" />
			<input type="hidden" value="0" id="processSelect"
				name="processSelect" />
			<div class="row" align="left">
				<div class="col-sm-4" id="searchboxCss">
					<div class="form-group">
						<label for="usr">Lov Process :</label>
						<form:select path="lovProcess" class="form-control"
							name="processNameLookup" id="lovProcess"
							onchange="onChangeProcess(1)">
							<c:forEach items="${processNameListLookup}"
								var="processNameLookup">
								<c:choose>
									<c:when test="${processNameLookup.lId == lId}">
										<option value="${processNameLookup.lId}" selected>${processNameLookup.lovProcess}</option>
									</c:when>
									<c:when
										test="${processNameLookup.lovProcess == lovData.lovProcess}">
										<option value="${processNameLookup.lId}" selected>${processNameLookup.lovProcess}</option>
									</c:when>
									<c:otherwise>
										<option value="0" style="display: none">-- Select
											Process Name ---</option>
										<option value="${processNameLookup.lId}">${processNameLookup.lovProcess}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</form:select>
					</div>
				</div>

				<div class="col-sm-4" id="searchboxCss">
					<div class="form-group">
						<label for="usr">LOV Name :</label>
						<form:select path="" class="form-control" name="lovNames"
							onchange='CheckProcessName(this.value);' id="myLov">
							<c:forEach items="${lovProcessNameList}" var="lovProcessName">
								<c:choose>
									<c:when test="${lovData.lovName == lovProcessName}">
										<option value="${lovData.lovName}" selected>${lovData.lovName}</option>
									</c:when>
									<c:otherwise>
										<option value="${lovProcessName}">${lovProcessName}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							<option value="others">others</option>
						</form:select>
						<form:input path="lovName" type="text" name="lovNames"
							id="lovNames" style='display: none;'
							onchange="processNameChange(this.value);" class="form-control" />
					</div>
				</div>

				<div class="col-sm-4" id="searchboxCss">
					<div class="form-group">
						<label for="usr">LOV Description :</label>
						<form:input path="description" class="form-control" autocomplete="off"/>
						<div></div>
					</div>
				</div>
			</div>
			<div class="row" align="left" id="searchboxCss">
				<div class="col-sm-4">
					<div class="form-group">
						<div class="form-group">
							<label for="usr">LOV Comment :</label>
							<form:input path="lovComment" class="form-control" autocomplete="off"/>
						</div>
					</div>
				</div>

				<div class="col-sm-4" id="searchboxCss">
					<div class="form-group">

						<label for="usr">LOV Value:</label>
						<form:input path="lovValue" class="form-control" autocomplete="off"/>
					</div>
				</div>
			</div>
			<br>
			<div class="row" align="center">
				<div class="col-md-4"></div>
				<div class="col-md-4">
					<button type="submit" class="btn btn-primary" id="searchBtnCss">Save</button>
				</div>
				<div class="col-md-4"></div>

			</div>
		</form:form>
	</div>
</body>
</html>