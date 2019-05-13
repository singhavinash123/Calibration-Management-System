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
	function check() {
		if (document.getElementById("password").value == document
				.getElementById("confirm_password").value) {
			document.getElementById('message').style.color = 'green';
			document.getElementById('message').innerHTML = 'Password Matches';
			document.getElementById("submit").disabled = false;

		} else {
			document.getElementById('message').style.color = 'red';
			document.getElementById('message').innerHTML = 'Password does not match';
			document.getElementById("submit").disabled = true;

		}
	};
	function userNameChange() {
		document.getElementById("emailname").value = document
				.getElementById("username").value;

	};
	function userEmailChange() {
		document.getElementById("username").value = document
				.getElementById("emailname").value;

	};
</script>

<spring:url value="customcss/formCss.css"
	var="bootstrapCssURL"></spring:url>
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
		<span><a href=<%=appsPropertyFile.getURLForKey("homePageURL")%> id="hrefCss">Home</a></span>
		<span>/</span> <span><a
			href=<%=appsPropertyFile.getURLForKey("userList")%> id="hrefCss">User List</a></span> <span>/</span>
		<span><a href=""><h id="hrefCss">${addOrUpdate}</h></a></span>
		<br>
		<br>
		<div align="center">
			<b style="color: green; font-size: 15px;">${msg}</b>
		</div>
		<div align="center">
			<b style="color: red; font-size: 15px;">${error}</b>
		</div>

		<div align="center">
			<b style="color: red; font-size: 15px;">${alreadyExist}</b>
		</div>

		<spring:url value="/saveRole" var="saveURL" />
		<form:form modelAttribute="userData" method="post" action="${saveURL}"
			style="font-size:12px;" id="lovform">
			<form:hidden path="userUserId" />
			<div class="row" align="left" id="searchboxCss">

				<div class="col-sm-4">
					<div class="form-group">
						<label for="usr">First Name* :</label>
						<form:input path="userFirstName" class="form-control"
							placeholder="Enter First Name.." readonly="true" />
						<div>
							<form:errors path="userFirstName" cssClass="error" />
						</div>
					</div>
				</div>

				<div class="col-sm-4">
					<div class="form-group">
						<label for="usr">Last Name :</label>
						<form:input path="userLastName" class="form-control"
							placeholder="Enter Last Name.." readonly="true" />
						<div></div>
					</div>
				</div>

				<div class="col-sm-4">
					<div class="form-group">
						<label for="usr">User Name* :</label>
						<form:input path="useUserName" id="username" class="form-control"
							placeholder="Enter User Name.." readonly="true" />
						<div>
							<form:errors path="useUserName" cssClass="error" />
						</div>
					</div>
				</div>
			</div>

			<div class="row" align="left" id="searchboxCss">
				<div class="col-sm-4">
					<div class="form-group">
						<label for="usr">Employee code* :</label>
						<form:input path="userEmpCode" id="EmpCode" class="form-control"
							placeholder="Enter Employee Code.." readonly="true" />
						<div>
							<form:errors path="userEmpCode" cssClass="error" />
						</div>
					</div>
				</div>

				<div class="col-sm-4">
					<div class="form-group">
						<label for="usr">Department* :</label>
						<form:input path="userDepartment" id="userDept" name="userDept"
							class="form-control" placeholder="Enter User Name.."
							readonly="true" />
					</div>
				</div>

				<div class="col-sm-4">
					<div class="form-group">
						<label for="usr">Email Address* :</label>
						<form:input path="userEmailAddress" id="emailname"
							class="form-control" placeholder="Enter Email.." readonly="true" />
						<div>
							<form:errors path="userEmailAddress" cssClass="error" />
						</div>
					</div>
				</div>

			</div>
			<div class="row" id="searchboxCss">
				<div class="col-sm-4">
					<div class="form-group">
						<label for="usr">Contact :</label>
						<form:input path="userContactNumber" class="form-control"
							placeholder="Enter Contact No..." readonly="true" />
						<div>
							<form:errors path="userContactNumber" cssClass="error" />
						</div>
					</div>
				</div>

				<div class="col-sm-4">
					<div class="form-group">
						<label for="usr">User Role* :</label>
						<form:select id="userrole" class="form-control" name="userrole"
							style="height: 35px;" path="userUserRoleName">
							<c:forEach items="${userRoleList}" var="userRole">
								<c:choose>
									<c:when test="${userData.userUserRoleName == userRole}">
										<option value="${userData.userUserRoleName}" selected>${userData.userUserRoleName}</option>
									</c:when>
									<c:otherwise>
										<option value="${userRole}">${userRole}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</form:select>
					</div>
				</div>

				<div class="col-sm-4" style="visibility: hidden">
					<div class="form-group">
						<label for="usr">Password* :</label>
						<form:input path="userPassWord" placeholder="Enter Password.."
							class="form-control" name="password" id="password"
							type="password" onchange="check();" />
						<div>
							<form:errors path="userPassWord" cssClass="error" />
						</div>
					</div>
				</div>

				<div class="col-sm-4" style="visibility: hidden">
					<div class="form-group">
						<label for="usr">Confirm Password* :</label>
						<form:input path="userConfirmPassword"
							placeholder="Confirmed Password.." class="form-control"
							type="password" name="confirm_password" id="confirm_password"
							onchange="check();" value="${userData.userPassWord}"/>
						<span id="message"></span>
						<div>
							<form:errors path="userConfirmPassword" cssClass="error" />
						</div>
					</div>
				</div>

			</div>
			<div class="row" align="center">
				<div class="col-md-4"></div>
				<div class="col-md-4" id="searchBtnCss">
					<button type="submit" id="submit" name="submit"
						class="btn btn-primary">Save</button>
				</div>
				<div class="col-md-4"></div>
			</div>
		</form:form>
	</div>
</body>
</html>