<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Reference Bootstrap files -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="customcss/formCss.css">

<script>
	function myFunction() {
		var x = document.getElementById("password");
		if (x.type === "password") {
			x.type = "text";
		} else {
			x.type = "password";
		}
	}
</script>
</head>

<body>

	<div class="container-fluid" style="height: 100%;">
		<div class="row">
			<div class="col-md-6">
				<img src="images/loginLeft.jpg" class="img-responsive" style="height:650px;">
			</div>
			<div class="col-md-6">
				<br>
				<div class="row">
						<div class="col-md-8"></div>
						<div class="col-md-4"><img src="images/SXSNewLogo.jpg" class="img-responsive"></div>
				</div>
				<br>
				<br>
				<div id="loginCSS">
					<form:form action="${pageContext.request.contextPath}/login"
						method="POST" class="form-horizontal">
						<!-- Place for messages: error, alert etc ... -->
						<div class="form-group">
							<div class="col-xs-15">
								<div>
									<!-- Check for login error -->
									<c:if test="${param.error != null}">
										<div class="alert alert-danger col-xs-offset-1 col-xs-10"
											id="messageCss">Invalid username and password.</div>
									</c:if>
									<c:if test="${param.logout != null }">
										<div class="alert alert-success col-xs-offset-1 col-xs-10"
											id="messageCss">You have been logged out.</div>
									</c:if>
								</div>

								<br>
								<div align="center">
									<b style="color: green; font-size: 15px;" id="messageCss">${successmsg}</b>
								</div>
								<div align="center">
									<b style="color: red; font-size: 15px;" id="messageCss">${alreadyExist}</b>
								</div>
								<br>
							</div>
						</div>
						<div class="row">
							<div class="col-md-2"></div>
							<div class="offset-md-2 col-md-4">
								<label id="usernamePasswordCss">User ID</label>
							</div>

							<div class="col-md-4" id="searchboxCss">
								<div class="input-group">
									<!-- <span class="input-group-addon"><i
										class="glyphicon glyphicon-envelope"></i></span>  -->
									<input name="username" id="username" placeholder="username"
										class="form-control" type="email" autocomplete="off"
										style="width: 270px;">
								</div>
							</div>
						</div>
						<br>
						<div class="row">
							<div class="col-md-2"></div>
							<div class="offset-md-2 col-md-4">
								<label id="usernamePasswordCss">Password</label>
							</div>
							<div class="col-md-4" id="searchboxCss">
								<div class="input-group">
									<!-- <span class="input-group-addon"><i
										class="glyphicon glyphicon-lock"></i></span> -->
									<input type="password" name="password" id="password"
										placeholder="password" class="form-control"
										style="width: 270px;"> <input type="checkbox"
										onclick="myFunction()">Show Password
								</div>
								<br>
								<button type="submit" value="LOG IN"
									style="background-color: #33afaf; width: 100%; color: #fff; padding: 5px;"
									id="btnCss">Login</button>
								<br> <br>
								<div align="center">
									<a href="/forgotpassword" id="forgotpasswordCss">Forgot
										Password</a>
								</div>


							</div>
							<br>
						</div>
					</form:form>
				</div>
			<div class="row">
			<br>
			<br>
			<br>
					<div class="col-md-1">
					
					</div>
					<div class="col-md-8">
						<img src="images/customerFirst.jpg" class="img-responsive">
					</div>
					<div class="col-md-1">
					</div>
			</div>
				
				<!-- <br> <img src="images/four.jpg" class="img-responsive"> -->
			</div>
		</div>
	</div>

</body>
</html>