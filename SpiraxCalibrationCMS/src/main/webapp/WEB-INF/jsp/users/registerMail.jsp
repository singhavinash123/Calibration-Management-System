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
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
	
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">

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

</head>
<body>
	<div id="header">
		<jsp:include page="/WEB-INF/jsp/header/header.jsp" />
	</div>
	<br>
	<div class="container-fluid">
		<span id="hereCss">You are here : </span>
		<%
			AppsPropertyFile appsPropertyFile = new AppsPropertyFile();
		%>
		<span><a href=<%=appsPropertyFile.getURLForKey("homePageURL")%>
			id="hrefCss">Home</a></span> <span>/</span> <span><a
			href="" id="hrefCss">register_Email_For_Notification</a></span> 
		<br> <br>

		<div align="center">
			<b id="successMsgCss">${msg}</b>
		</div>
		<div align="center">
			<b id="errorMsgCss">${error}</b>
		</div>

		<div class="row" style="display: flex;
  justify-content: center;">
			<div class="col-md-4 col-md-offset-4">
				<div class="panel panel-default">
					<div class="panel-body">
						<div class="text-center">
							<h3>
								<i class="fa fa-lock fa-4x"></i>
							</h3>
							<h2 class="text-center">Register Service Mail</h2>
							<div class="panel-body">
								<form:form action="/saveRegisterMail" method="POST">
									<div class="form-group">
										<div class="input-group">
											<span class="input-group-addon"><i
										class="glyphicon glyphicon-envelope"></i></span> <input id="emailname"
												name="emailname" class="form-control"
												placeholder="Enter Email.." onchange="userEmailChange();"
												autocomplete="off" />
										</div>
									</div>

									<div class="form-group">
										<div class="input-group">
											<span class="input-group-addon"><i
												class="glyphicon glyphicon-lock"></i></span> <input type="password"
												class="form-control" placeholder="New Password"
												onchange="check();" name="password" id="password">
										</div>
									</div>

									<div class="form-group">
										<div class="input-group">
											<span class="input-group-addon"><i
												class="glyphicon glyphicon-lock"></i></span><input type="password"
												class="form-control" placeholder="Confirm Password"
												name="confirm_password" id="confirm_password"
												onchange="check();">
										</div>
										<span id="message"></span>

									</div>

									<div class="form-group">
										<input name="recover-submit"
											class="btn btn-lg btn-primary btn-block" value="submit"
											type="submit" id="submit">
									</div>
								</form:form>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>