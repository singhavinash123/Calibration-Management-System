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

<link rel="stylesheet" href="customcss/validationsError.css">

<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<spring:url value="bootstrap/css/bootstrap.min.css"
	var="bootstrapCssURL"></spring:url>
<link href="${bootstrapCssURL}" rel="stylesheet">


<link rel="stylesheet" href="customcss/formCss.css">

<script>
	$(function() {
		$("#calibrationDate").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd-M-yy"
		}).datepicker("setDate", "${equipmentForm.mainCalibrationDate}")
		
		$("#dueDate").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd-M-yy"
		}).datepicker("setDate", "${equipmentForm.mainDueDate}")
		
		$("#calibrationScrapedDate").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd-M-yy"
		}).datepicker("setDate", "${equipmentForm.mainCalibrationScrapedDate}")
	
		$("#reminderDate").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd-M-yy"
		}).datepicker("setDate", "${equipmentForm.mainReminderDate}")
		
	});
</script>
<script>
	function getCalibTypeChange(value) {
		var month_abbrs = [ 'Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 
                            'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'
        ];
		var tt = document.getElementById('dueDate').value;
		var date = new Date(tt);
	    var newdate = new Date(date);
	    var frequency;
		 if(value == "Statutory Calibration" || value == "Environmental Calibration" ){
		    	frequency = 30;
		 }else if(value == "Utilities Calibration" || value == "Process Calibration" || value == "Product Calibration"){
		    	frequency = 15;
		 }else if(value == "Trivial Calibration"){
		    	frequency = 45;
		 }else if(value == "0"){
		    	frequency = 0;
		}
	     document.getElementById("alertfrequency").value = frequency;
	    newdate.setDate(newdate.getDate() - parseInt(frequency));
	    var dd = newdate.getDate();
	    var mm = newdate.getMonth();
	    var y = newdate.getFullYear();

	    var someFormattedDate = dd  + '-' + month_abbrs[mm] + '-' + y;
	    document.getElementById('reminderDate').value = someFormattedDate;
	    
	};
	
	function CheckYear(value) {
		var month_abbrs = [ 'Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul',
				'Aug', 'Sep', 'Oct', 'Nov', 'Dec' ];
		
		var selectedMonthsDays  = document.getElementById('months').value;
		var selectedDays = document.getElementById('days').value;
		var total =  parseInt(selectedMonthsDays) +  parseInt(selectedDays) + parseInt(value);
			
		var tt = document.getElementById('calibrationDate').value;
		var date = new Date(tt);
		var newdate = new Date(date);
		newdate.setDate(newdate.getDate() + parseInt(total));
		var dd = newdate.getDate();
		var mm = newdate.getMonth();
		var y = newdate.getFullYear();
		var someFormattedDate = dd + '-' + month_abbrs[mm] + '-' + y;
		document.getElementById('dueDate').value = someFormattedDate;

		changeCalibStatusWhenDueDateChange(someFormattedDate);

		var tt = document.getElementById('dueDate').value;
			var date = new Date(tt);
			var newdate = new Date(date);
			newdate.setDate(newdate.getDate() - parseInt(document.getElementById("alertfrequency").value));
			var dd = newdate.getDate();
			var mm = newdate.getMonth();
			var y = newdate.getFullYear();

			var someFormattedDate = dd + '-' + month_abbrs[mm] + '-' + y;
			document.getElementById('reminderDate').value = someFormattedDate;
			
			changeCalibStatusWhenReminderDateChange(someFormattedDate);

	};

	function checkMonths(value) {

		var month_abbrs = [ 'Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul',
				'Aug', 'Sep', 'Oct', 'Nov', 'Dec' ];
		var tt = document.getElementById('calibrationDate').value;
		
		var selectedYearDays  = document.getElementById('years').value;
		var selectedDays = document.getElementById('days').value;
		var total =  parseInt(selectedYearDays) +  parseInt(selectedDays) + parseInt(value);
		
		var date = new Date(tt);
		var newdate = new Date(date);
		newdate.setDate(newdate.getDate() + parseInt(total));
		var dd = newdate.getDate();
		var mm = newdate.getMonth();
		var y = newdate.getFullYear();

		var someFormattedDate = dd + '-' + month_abbrs[mm] + '-' + y;
		document.getElementById('dueDate').value = someFormattedDate;

		changeCalibStatusWhenDueDateChange(someFormattedDate);

		var tt = document.getElementById('dueDate').value;
			var date = new Date(tt);
			var newdate = new Date(date);
			newdate.setDate(newdate.getDate() - parseInt(document.getElementById("alertfrequency").value));
			var dd = newdate.getDate();
			var mm = newdate.getMonth();
			var y = newdate.getFullYear();

			var someFormattedDate = dd + '-' + month_abbrs[mm] + '-' + y;
			document.getElementById('reminderDate').value = someFormattedDate;
			changeCalibStatusWhenReminderDateChange(someFormattedDate);

	};

	function checkDays(value) {

		var month_abbrs = [ 'Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul',
				'Aug', 'Sep', 'Oct', 'Nov', 'Dec' ];

		var tt = document.getElementById('calibrationDate').value;
		
		var selectedYearDays  = document.getElementById('years').value;
		var selectedDays = document.getElementById('months').value;
		var total =  parseInt(selectedYearDays) +  parseInt(selectedDays) + parseInt(value);
		
		var date = new Date(tt);
		var newdate = new Date(date);
		newdate.setDate(newdate.getDate() + parseInt(total));
		var dd = newdate.getDate();
		var mm = newdate.getMonth();
		var y = newdate.getFullYear();

		var someFormattedDate = dd + '-' + month_abbrs[mm] + '-' + y;
		document.getElementById('dueDate').value = someFormattedDate;
		
		changeCalibStatusWhenDueDateChange(someFormattedDate);

		 var tt = document.getElementById('dueDate').value;
			var date = new Date(tt);
			var newdate = new Date(date);
			newdate.setDate(newdate.getDate() - parseInt(document.getElementById("alertfrequency").value));
			var dd = newdate.getDate();
			var mm = newdate.getMonth();
			var y = newdate.getFullYear();

			var someFormattedDate = dd + '-' + month_abbrs[mm] + '-' + y;
			document.getElementById('reminderDate').value = someFormattedDate;
			changeCalibStatusWhenReminderDateChange(someFormattedDate);

	};
	
	function changeCalibDate(value) {

		var month_abbrs = [ 'Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul',
				'Aug', 'Sep', 'Oct', 'Nov', 'Dec' ];

		var selectedYearDays = document.getElementById('years').value;
		var selectedDays = document.getElementById('days').value;
		var monthsDays = document.getElementById('months').value;

		var total = parseInt(selectedYearDays) + parseInt(selectedDays)
				+ parseInt(monthsDays);

		var tt = document.getElementById('calibrationDate').value;
		var date = new Date(tt);
		var newdate = new Date(date);
		newdate.setDate(newdate.getDate() + parseInt(total));
		var dd = newdate.getDate();
		var mm = newdate.getMonth();
		var y = newdate.getFullYear();

		var someFormattedDate = dd + '-' + month_abbrs[mm] + '-' + y;
		document.getElementById('dueDate').value = someFormattedDate;
		changeCalibStatusWhenDueDateChange(someFormattedDate);

		var tt = document.getElementById('dueDate').value;
		var date = new Date(tt);
		var newdate = new Date(date);
		newdate.setDate(newdate.getDate()
				- parseInt(document.getElementById("alertfrequency").value));
		var dd = newdate.getDate();
		var mm = newdate.getMonth();
		var y = newdate.getFullYear();

		var someFormattedDate = dd + '-' + month_abbrs[mm] + '-' + y;
		document.getElementById('reminderDate').value = someFormattedDate;
		changeCalibStatusWhenReminderDateChange(someFormattedDate);

	};
	
	function changeCalibStatusWhenReminderDateChange(val) {
		var ReminderDateForCalibStatus = new Date(val);
		var newReminderDateForCalibStatus = new Date(ReminderDateForCalibStatus);
		var TodayDate = new Date();

		var dueDate = document.getElementById('dueDate').value;
		var date = new Date(dueDate);
		var newDueDate = new Date(date);
		var selectCalibResult = document.getElementById("calibResult");

		
		if (TodayDate.getTime() > newReminderDateForCalibStatus
				&& TodayDate.getTime() < newDueDate) {
			var selectBox = document.getElementById("calibStatus");
			selectBox.options[0] = new Option('Active', 'Active', true, true);
			document.getElementById("calibStatus").style.color = "#7cfc00";
			document.getElementById("calibStatus").style.fontSize = "22px";
			document.getElementById("calibStatus").style.backgroundColor = "black";
			
			selectCalibResult.options[0] = new Option('OK', 'OK', true, true);

		} else if (TodayDate.getTime() > newDueDate
				&& TodayDate.getTime() > newReminderDateForCalibStatus) {
			var selectBox = document.getElementById("calibStatus");
			selectBox.options[0] = new Option('Inactive', 'Inactive', true, true);
			document.getElementById("calibStatus").style.color = "red";
			document.getElementById("calibStatus").style.fontSize = "22px";
			document.getElementById("calibStatus").style.backgroundColor = "black";
			
			selectCalibResult.options[0] = new Option('OK', 'OK', true, true);

		} else {
			var selectBox = document.getElementById("calibStatus");
			selectBox.options[0] = new Option('Calibrated', 'Calibrated', true, true);
			selectBox.style.fontFamily = "Arial";
			document.getElementById("calibStatus").style.color = "#7cfc00";
			document.getElementById("calibStatus").style.fontSize = "22px";
			document.getElementById("calibStatus").style.backgroundColor = "black";
			
			selectCalibResult.options[0] = new Option('OK', 'OK', true, true);

		}

		document.getElementById('dueDate').style.visibility = "visible";
		document.getElementById('reminderDate').style.visibility = "visible";

	};

	window.onload = function() {
		
	    if("${equipmentForm.mainUpdatedIdentificationId}" != null){
	    	document.getElementById("identiFicationNum").value = "${equipmentForm.mainUpdatedIdentificationId}";
	    }
	    
		
		var selectCalibResult = document.getElementById("calibResult");
	
			
		if(document.getElementById("calibStatus").value == "Inactive"){
			document.getElementById("calibrationScrapedDate").style.visibility = "hidden";
			document.getElementById("calibStatus").style.color = "red";
			document.getElementById("calibStatus").style.fontSize = "20px";
			document.getElementById("calibStatus").style.backgroundColor = "black";
			
			selectCalibResult.options[0] = new Option('OK', 'OK', true, true);
			selectCalibResult.options[1] = new Option('NOT OK', 'NOT OK', false, false);


		}if(document.getElementById("calibStatus").value == "Active"){
			document.getElementById("calibrationScrapedDate").style.visibility = "hidden";
			document.getElementById("calibStatus").style.color = "#7cfc00";
			document.getElementById("calibStatus").style.fontSize = "20px";
			document.getElementById("calibStatus").style.backgroundColor = "black";
			
			selectCalibResult.options[0] = new Option('OK', 'OK', true, true);
			selectCalibResult.options[1] = new Option('NOT OK', 'NOT OK', false, false);


		}if(document.getElementById("calibStatus").value == "Calibrated"){
			document.getElementById("calibrationScrapedDate").style.visibility = "hidden";
			document.getElementById("calibStatus").style.color = "#7cfc00";
			document.getElementById("calibStatus").style.fontSize = "20px";
			document.getElementById("calibStatus").style.backgroundColor = "black";
			
			selectCalibResult.options[0] = new Option('OK', 'OK', true, true);
			selectCalibResult.options[1] = new Option('NOT OK', 'NOT OK', false, false);


		}if (document.getElementById("calibStatus").value == "Scrap") {
			document.getElementById("calibStatus").style.color = "black";
			document.getElementById("calibStatus").style.backgroundColor = "red";
			document.getElementById("calibStatus").style.fontSize = "20px";
			document.getElementById("calibrationScrapedDate").style.visibility = "visible";
			
			selectCalibResult.options[0] = new Option('NOT OK', 'NOT OK', true, true);
			selectCalibResult.options[1] = new Option('NOT OK', 'NOT OK', false, false);


		}
			//getCalibTypeChange(document.getElementById("alertfrequency").value);
			if(${equipmentForm.mainEcId} != null){
		        document.getElementById("equipmentType").disabled=true;
			}
	};
	
	function resetForm() {
		document.getElementById("equipmentType").disabled = false;
		getCalibTypeChange(document.getElementById("alertfrequency").value);
		var x = document.getElementById('equipmentType').value
		document.getElementById("equiptype").value = x;
		document.getElementById("mainform").reset();
	};
	
	function CheckEquipType(val) {
		document.getElementById("equiptype").value = val;
	};
	
	function changeCalibStatus(val) {
		
		var selectCalibResult = document.getElementById("calibResult");
		if (val == "Inactive") {
			document.getElementById("calibStatus").style.color = "red";
			document.getElementById("calibStatus").style.backgroundColor = "black";
			selectCalibResult.options[0] = new Option('OK', 'OK', true, true);
		}
		if (val == "Active") {
			document.getElementById("calibStatus").style.color = "#7cfc00";
			document.getElementById("calibStatus").style.backgroundColor = "black";
			selectCalibResult.options[0] = new Option('OK', 'OK', true, true);
		}
		if (val == "Calibrated") {
			document.getElementById("calibStatus").style.color = "#7cfc00";
			document.getElementById("calibStatus").style.backgroundColor = "black";
			selectCalibResult.options[0] = new Option('OK', 'OK', true, true);
		}if (val == "Scrap") {
			document.getElementById("calibStatus").style.color = "black";
			document.getElementById("calibStatus").style.backgroundColor = "red";
			selectCalibResult.options[0] = new Option('NOT OK', 'NOT OK', true, true);
		}
		
		if(val == "Scrap"){
			document.getElementById("calibrationScrapedDate").style.visibility = "visible";
			$("#calibrationScrapedDate").datepicker({
				changeMonth : true,
				changeYear : true,
				dateFormat : "dd-M-yy"
			}).datepicker("setDate", "${equipmentForm.mainCalibrationScrapedDate}")
		}else{
			document.getElementById("calibrationScrapedDate").style.visibility = "hidden";
			$("#calibrationScrapedDate").datepicker({
				changeMonth : true,
				changeYear : true,
				dateFormat : "dd-M-yy"
			}).datepicker("setDate", null)
		}
	};
	function changeCalibStatusDate(value) {
		var month_abbrs = [ 'Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul',
				'Aug', 'Sep', 'Oct', 'Nov', 'Dec' ];

		var selectedYearDays = document.getElementById('years').value;
		var selectedDays = document.getElementById('days').value;
		var monthsDays = document.getElementById('months').value;

		var total = parseInt(selectedYearDays) + parseInt(selectedDays)
				+ parseInt(monthsDays);

		var tt = document.getElementById('dueDate').value;
		var date = new Date(tt);
		var newdate = new Date(date);
		newdate.setDate(newdate.getDate() + parseInt(total));
		var dd = newdate.getDate();
		var mm = newdate.getMonth();
		var y = newdate.getFullYear();

		var someFormattedDate = dd + '-' + month_abbrs[mm] + '-' + y;
		changeCalibStatusWhenDueDateChange(someFormattedDate);

	};

	function changeCalibStatusWhenDueDateChange(val) {
		var DueDateForCalibStatus = new Date(val);
		var newDueDateForCalibStatus = new Date(DueDateForCalibStatus);

		var reminderDate = document.getElementById('reminderDate').value
		var ReminderDateForCalibStatus = new Date(reminderDate);
		var newReminderDateForCalibStatus = new Date(ReminderDateForCalibStatus);
		var TodayDate = new Date();
		
		var selectCalibResult = document.getElementById("calibResult");

		if (TodayDate.getTime() > newReminderDateForCalibStatus
				&& TodayDate.getTime() < newDueDateForCalibStatus) {
			var selectBox = document.getElementById("calibStatus");
			selectBox.options[0] = new Option('Active', 'Active', true, true);
			document.getElementById("calibStatus").style.color = "#7cfc00";
			document.getElementById("calibStatus").style.fontSize = "22px";
			document.getElementById("calibStatus").style.backgroundColor = "black";
			
			selectCalibResult.options[0] = new Option('OK', 'OK', true, true);

		} else if (TodayDate.getTime() > newDueDateForCalibStatus
				&& TodayDate.getTime() > newReminderDateForCalibStatus ||  TodayDate.getTime() < newReminderDateForCalibStatus && TodayDate.getTime() > newDueDateForCalibStatus) {
			var selectBox = document.getElementById("calibStatus");
			selectBox.options[0] = new Option('Inactive', 'Inactive', true, true);
			document.getElementById("calibStatus").style.color = "red";
			document.getElementById("calibStatus").style.fontSize = "22px";
			document.getElementById("calibStatus").style.backgroundColor = "black";
			
			selectCalibResult.options[0] = new Option('OK', 'OK', true, true);

		} else {
			var selectBox = document.getElementById("calibStatus");
			selectBox.options[0] = new Option('Calibrated', 'Calibrated', true, true);
			document.getElementById("calibStatus").style.color = "#7cfc00";
			document.getElementById("calibStatus").style.fontSize = "22px";
			document.getElementById("calibStatus").style.backgroundColor = "black";
			
			selectCalibResult.options[0] = new Option('OK', 'OK', true, true);

		}
		document.getElementById('dueDate').style.visibility = "visible";
		document.getElementById('reminderDate').style.visibility = "visible";
	};
	
	
</script>

<style>
.container-fluid{
		max-width: 100%;
		min-width: 100%;
		margin: 0 auto;
}

.mainform{
	width : 800px;
}
	
</style>
</head>
<body>
	<div id="header">
		<jsp:include page="/WEB-INF/jsp/header/header.jsp" />
	</div>
	<div align="center">
		<h3 id="formtitle">Gauge and Equipment Master</h3>
	</div>
	<div class="container-fluid">

		<span id="hereCss">You are here : </span>
		<%
			AppsPropertyFile appsPropertyFile = new AppsPropertyFile();
		%>
		<span><a href=<%=appsPropertyFile.getURLForKey("homePageURL")%>
			id="hrefCss">Home</a></span><span> /</span> <span><a
			href=<%=appsPropertyFile.getURLForKey("equipmentmaster")%>
			id="hrefCss">Gauge and Equipment Master</a></span> <br> <br>
		<div class="row">
			<div class="col-sm-4">
				<spring:url value="/add_main_Calibration" var="AddEquipmentURL"></spring:url>
				<a href="${AddEquipmentURL}"><button type="button"
						class="btn btn-success" onclick="resetForm();" id="addNewCss">Add
						New</button> </a>
			</div>
		</div>
		<br>
		<div class="row">
			<form method="POST" action="/search_calib_equipment">
				<div class="container">
					<div class="row">
						<div class="col-md-auto" id="searchboxCss">
							<label for="usr">Identification Number:</label> <input
								type="text" placeholder="Enter Equipment No..."
								name="identiFicationNum" id="identiFicationNum" class="form-control">
						</div>
						<div>
							<div class="col">
								<br> <input type=submit value="Search" class="btn"
									class="form-control" id="addNewCss" style="color: white; background-color: #20B2AA;">
									
									 <input
									type="button" class="btn" name="reset_form"
									value="Clear" onclick="this.form.reset();" id="addNewCss"style="color: black;background-color: #DCDCDC;">
							</div>
						</div>
					</div>
				</div>
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
			</form>
		</div>
		<div align="center">
			<b id="successMsgCss">${msg}</b>
		</div>

		<div align="center">
			<b id="errorMsgCss">${errorMsg}</b>
		</div>

		<spring:url value="/save_main_calib_details" var="saveURL" />
		<form:form modelAttribute="equipmentForm" method="post"
			action="${saveURL}" style="font-size:12px;" id="mainform">
			<form:hidden path="mainEcId" />
			<input type="hidden" name="USER_NAME" value="NOT_ADMIN">
			<div class="row">
				<div class="col-sm-1"></div>
				<div class="col-sm-5" id="supplierCSS">
					<h3 align="center">
						<b id="headingCSS">Supplier Tracer</b>
					</h3>
					<br>
					<div class="row">
						<div class="col-md-6" id="backgroundGreen">
							<label for="usr" id="labelCalibForCSS">Instrument
								Requestor</label>
						</div>

						<div class="col-md-6">
							<form:select path="mainInstrumentRequestor" class="form-control"
								name="instrumentRequestor" style="height:35px;"
								id="backgroundGreen">
								<c:forEach items="${UserDataList}" var="instrumentRequestor">
									<c:choose>
										<c:when
											test="${equipmentForm.mainInstrumentRequestor == instrumentRequestor.userFullName}">
											<option value="${equipmentForm.mainInstrumentRequestor}"
												selected>${equipmentForm.mainInstrumentRequestor}</option>
										</c:when>
										<c:otherwise>
											<option value="${instrumentRequestor.useUserName}">${instrumentRequestor.userFullName}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</form:select>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6" id="backgroundGrey">
							<label for="usr" id="labelCalibForCSS">Source Of Calibration</label>
						</div>
						<div class="col-sm-6">
							<form:input path="mainSupplierName" list="browsers"
								id="backgroundGrey" name="myBrowser" class="form-control" />

							<datalist id="browsers"> <c:forEach
								items="${supplierNameLookup}" var="supplierName">
								<c:choose>
									<c:when
										test="${equipmentForm.mainSupplierName == supplierName}">
										<option value="${equipmentForm.mainSupplierName}" selected>${equipmentForm.mainSupplierName}</option>
									</c:when>
									<c:otherwise>
										<option value="${supplierName}">${supplierName}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach> </datalist>
							<div>
								<form:errors path="mainSupplierName" cssClass="error"></form:errors>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6" id="backgroundGreen">
							<label for="usr" id="labelCalibForCSS">Purchase Order #</label>
						</div>
						<div class="col-sm-6">
							<form:input path="mainPurchaseOrder" type="text"
								placeholder="Enter Purchase Order..." name="serialNumber"
								class="form-control" id="backgroundGreen" />
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6" id="backgroundGrey">
							<label for="usr" id="labelCalibForCSS">Supplier Invoice #</label>
						</div>
						<div class="col-sm-6">
							<form:input path="mainInvoice" type="text"
								placeholder="Enter Supplier Invoice..." name="serialNumber"
								class="form-control" id="backgroundGrey" />
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6" id="backgroundGreen">
							<label for="usr" id="labelCalibForCSS">Unit Price (INR)</label>
						</div>
						<div class="col-sm-6">
							<form:input path="mainUnitPrice" type="text"
								placeholder="Enter Unit Price..." name="unitPrice"
								class="form-control" id="backgroundGreen" />
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6" id="backgroundGrey">
							<label for="usr" id="labelCalibForCSS">Capex Code (If
								Any)</label>
						</div>
						<div class="col-sm-6">
							<form:input path="mainAssetCode" type="text"
								placeholder="Enter Capex Code..." name="assetCode"
								class="form-control" id="backgroundGrey" />
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6" id="backgroundGreen">
							<label for="usr" id="labelCalibForCSS">Supplier Service
								Type</label>
						</div>
						<div class="col-sm-6">
							<form:select path="mainSupplierService" class="form-control"
								name="supplierService" style="height:35px;" id="backgroundGreen">
								<c:forEach items="${supplierServiceTypeLookup}"
									var="supplierService">
									<c:choose>
										<c:when
											test="${equipmentForm.mainSupplierService == supplierService}">
											<option value="${equipmentForm.mainSupplierService}" selected>${equipmentForm.mainSupplierService}</option>
										</c:when>
										<c:otherwise>
											<option value="${supplierService}">${supplierService}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<option value="none">none</option>
							</form:select>
						</div>
					</div>
				</div>
				<div class="col-sm-5" id="equipmentCSS">
					<h3 align="center">
						<b id="headingCSS">Equipment Tracer</b>
					</h3>
					<br>
					<div class="row">
						<div class="col-sm-6" id="backgroundGreen">
							<label for="usr" id="labelCalibForCSS">make</label>
						</div>
						<div class="col-sm-6">

							<form:input path="mainMake" type="text"
								placeholder="Enter Make..." name="make" class="form-control"
								id="backgroundGreen" />
							<!--  	
							<form:select path="mainMake" class="form-control" name="make"
								style="height: 35px;" id="backgroundGreen">
								<c:forEach items="${calibMakeLookup}" var="calibMake">
									<c:choose>
										<c:when test="${equipmentForm.mainMake == calibMake}">
											<option value="${equipmentForm.mainMake}" selected>${equipmentForm.mainMake}</option>
										</c:when>
										<c:otherwise>
											<option value="${calibMake}">${calibMake}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<option value="none">none</option>
							</form:select>
							-->
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6" id="backgroundGrey">
							<label for="usr" id="labelCalibForCSS">Instrument / Gauge
								Description</label>
						</div>
						<div class="col-sm-6">
							<form:select path="mainInstrumentGauge" class="form-control"
								name="instrumentGauge" style="height:35px;" id="backgroundGrey">
								<c:forEach items="${equipmentDescWithNumber}"
									var="instrumentGauge">
									<c:choose>
										<c:when
											test="${equipmentForm.mainInstrumentGauge == instrumentGauge.getKey()}">
											<option value="${equipmentForm.mainInstrumentGauge}" selected>${equipmentForm.mainInstrumentGauge}</option>
										</c:when>
										<c:otherwise>
											<option value="${instrumentGauge.getKey()}">${instrumentGauge.getKey()}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<option value="none">none</option>
							</form:select>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6" id="backgroundGreen">
							<label for="usr" id="labelCalibForCSS">Model</label>
						</div>
						<div class="col-sm-6">
							<form:input path="mainModel" type="text"
								placeholder="Enter Model..." name="model" class="form-control"
								id="backgroundGreen" />
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6" id="backgroundGrey">
							<label for="usr" id="labelCalibForCSS">Serial #</label>
						</div>
						<div class="col-sm-6">
							<form:input path="mainSerial" type="text"
								placeholder="Enter Serial No..." name="serial"
								class="form-control" id="backgroundGrey" />
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6" id="backgroundGreen">
							<label for="usr" id="labelCalibForCSS">Instrument / Gauge
								Range</label>
						</div>
						<div class="col-sm-6">
							<form:input path="mainInstrumentGaugeRange" type="text"
								placeholder="Enter Instrument Range.."
								name="instrumentGaugeRange" class="form-control"
								id="backgroundGreen" />
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6" id="backgroundGrey">
							<label for="usr" id="labelCalibForCSS">Least Count /
								Accuracy Specification</label>
						</div>
						<div class="col-sm-6">
							<form:input path="mainLeast" type="text"
								placeholder="Enter Least Count..." name="serialNumber"
								class="form-control" id="backgroundGrey" />
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6" id="backgroundGreen">
							<label for="usr" id="labelCalibForCSS">Calibration
								Standard Used</label>
						</div>
						<div class="col-sm-6">
							<form:select path="mainCalibrationStandard" class="form-control"
								name="calibrationStandard" style="height:35px;"
								id="backgroundGreen">
								<c:forEach items="${calibrationStandardLookUp}"
									var="calibrationStandard">
									<c:choose>
										<c:when
											test="${equipmentForm.mainCalibrationStandard == calibrationStandard}">
											<option value="${equipmentForm.mainCalibrationStandard}"
												selected>${equipmentForm.mainCalibrationStandard}</option>
										</c:when>
										<c:otherwise>
											<option value="${calibrationStandard}">${calibrationStandard}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<option value="none">none</option>
							</form:select>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6" id="backgroundGrey">
							<label for="usr" id="labelCalibForCSS">Calibration
								Forecast Price-(INR)</label>
						</div>
						<div class="col-sm-6">
							<form:input path="mainForcastPrice" type="text"
								placeholder="Enter ForeCast Price..." name="forcastPrice"
								class="form-control" id="backgroundGrey" />
						</div>
					</div>
					<br>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-1"></div>
				<div class="col-sm-5" id="calibrationCSS">
					<h3 align="center">
						<b id="headingCSS">Calibration Tracer</b>
					</h3>
					<br>
					<!-- 
					<div class="row" id="">
						<div class="col-sm-6">
							<label for="usr">Equipment Type:</label>
						</div>
						<div class="col-sm-6">
							<form:select path="mainEquipmentType" class="form-control"
								name="equipmentdesc" onchange='CheckEquipType(this.value);'
								id="equipmentType" style="height:35px;">
								<c:forEach items="${equipmentDescWithNumber}"
									var="equipmentdesc">
									<option value="${equipmentdesc.getValue()}">${equipmentdesc.getKey()}</option>
								</c:forEach>
							</form:select>
						</div>
					</div>
					 -->
					<div class="row">
						<div class="col-sm-6" id="backgroundGreen">
							<label for="usr" id="labelCalibForCSS">Guage Unique
								Identification #</label>
						</div>
						<div class="col-sm-6">
							<form:input readonly="true" path="mainIdentificationId"
								type="text" value="${equipmentForm.mainIdentificationId}"
								class="form-control" id="equiptype"
								style="background-color:#20B2AA; font-weight: bold; color:white; border: 0px solid white; border-radius: 0px;" />
						</div>
					</div>

					<div class="row">
						<div class="col-sm-6" id="backgroundGrey">
							<label for="usr" id="labelCalibForCSS">Reference Number #</label>
						</div>
						<div class="col-sm-6">
							<form:input path="mainOldIdentificationId" type="text"
								value="${equipmentForm.mainOldIdentificationId}"
								class="form-control" id="backgroundGrey" />
						</div>
					</div>

					<div class="row">
						<div class="col-sm-6" id="backgroundGreen">
							<label for="usr" id="labelCalibForCSS">Calibration type</label>
						</div>
						<div class="col-sm-6">
							<form:select path="mainCalibrationType" class="form-control"
								name="processNameLookup"
								onchange="getCalibTypeChange(this.value)" style="height:35px;"
								id="backgroundGreen">
								<c:forEach items="${calibrationTypeLookup}"
									var="calibrationType">
									<c:choose>
										<c:when
											test="${equipmentForm.mainCalibrationType == calibrationType.getKey()}">
											<option value="${equipmentForm.mainCalibrationType}" selected>${equipmentForm.mainCalibrationType}</option>
										</c:when>
										<c:otherwise>
											<option value="${calibrationType.getKey()}">${calibrationType.getKey()}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</form:select>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6" id="backgroundGrey">
							<label for="usr" id="labelCalibForCSS">Alert Frequency</label>
						</div>
						<div class="col-sm-6">
							<form:input id="alertfrequency" value="${selectedFrequency}"
								path="mainAlertFrequency" class="form-control" readonly="true"
								style="background-color:#DCDCDC;font-weight: bold;  color:black; border: 0px solid white; border-radius: 0px;" />
						</div>
					</div>
					<div class="row" id="backgroundWhite">
						<div class="col-sm-6">
							<label for="usr" id="labelCalibForCSS">Calibration
								Frequency</label>
						</div>
						<div class="col-sm-6">
							<div class="row">
								<div class="col-sm-4">
									<label for="usr"></label>
									<form:select path="mainCalibrationFrequency"
										class="form-control" name="calibrationFrequency"
										style="height: 35px; width:90px"
										onchange='CheckYear(this.value);' id="years">
										<option value="${yearsMap.get("daysInYear")}" selected>${yearsMap.get("years")}</option>
										<c:forEach items="${calibFrequnecyYearsLookup}"
											var="calibFrequency">
											<option value="${calibFrequency.getValue()}">${calibFrequency.getKey()}</option>
										</c:forEach>
									</form:select>
								</div>
								<div class="col-sm-4">
									<label for="usr"></label>
									<form:select path="mainCalibrationFrequency"
										class="form-control" name="calibrationFrequency"
										style="height: 35px; width:90px"
										onchange='checkMonths(this.value);' id="months">
										<option value="${monthsMap.get("daysInMonth")}" selected>${monthsMap.get("months")}</option>
										<c:forEach items="${calibFrequnecyMonthsLookup}"
											var="calibFrequency">
											<option value="${calibFrequency.getValue()}">${calibFrequency.getKey()}</option>
										</c:forEach>
									</form:select>
								</div>
								<div class="col-sm-4">
									<label for="usr"></label>
									<form:select path="mainCalibrationFrequency"
										class="form-control" name="calibrationFrequency"
										style="height: 35px; width:70px"
										onchange='checkDays(this.value);' id="days">
										<option value="${daysMap.get("totalDays")}" selected>${daysMap.get("days")}</option>
										<c:forEach items="${calibFrequnecyDaysLookup}"
											var="calibFrequency">
											<option value="${calibFrequency.getValue()}">${calibFrequency.getKey()}</option>
										</c:forEach>
									</form:select>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6" id="backgroundGreen">
							<label for="usr" id="labelCalibForCSS">Date Of
								Calibration</label>
						</div>
						<div class="col-sm-6">
							<form:input path="mainCalibrationDate" type="text"
								name="calibrationDate" class="form-control" readonly="readonly"
								id="calibrationDate" onchange='changeCalibDate(value);'
								style="background-color:#20B2AA; font-weight: bold; color:white; border: 0px solid white; border-radius: 0px;" />
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6" id="backgroundGrey">
							<label for="usr" id="labelCalibForCSS">Calibration Due
								Date</label>
						</div>
						<div class="col-sm-6">
							<form:input path="mainDueDate" type="text" name="dueDate"
								class="form-control" readonly="readonly" id="dueDate"
								value="${equipmentForm.mainDueDate}"
								onchange='changeCalibStatusDate(this.value);'
								style="border: 0px solid white;border-radius: 0px; font-weight: bold;background-color: #DCDCDC;font-family: Cambria;" />
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6" id="backgroundGreen">
							<label for="usr" id="labelCalibForCSS">Calibration Status</label>
						</div>
						<div class="col-sm-6">
							<form:select path="mainCalibrationStatus" class="form-control"
								name="calibrationStatusLookup"
								style="height:42px; font-size:22px; background-color:black; color:${equipmentForm.colorFlag}"
								onchange='changeCalibStatus(this.value);' id="calibStatus">
								<c:forEach items="${calibrationStatusLookup}"
									var="calibrationStatus">
									<option value="${equipmentForm.mainCalibrationStatus}" selected>${equipmentForm.mainCalibrationStatus}</option>
									<option value="${calibrationStatus}">${calibrationStatus}</option>
								</c:forEach>
							</form:select>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6" id="backgroundGrey">
							<label for="usr" id="labelCalibForCSS">Scrap Date</label>
						</div>
						<div class="col-sm-6">
							<form:input path="mainCalibrationScrapedDate" type="text"
								name="calibrationScrapedDate" class="form-control"
								readonly="readonly" id="calibrationScrapedDate"
								style="border: 0px solid white; font-weight: bold; border-radius: 0px;background-color: #DCDCDC;font-family: Cambria;" />
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6" id="backgroundGreen">
							<label for="usr" id="labelCalibForCSS">Calibration
								Reminder Date</label>
						</div>
						<div class="col-sm-6">
							<form:input path="mainReminderDate" type="text"
								name="reminderDate" class="form-control" readonly="readonly"
								id="reminderDate"
								style="background-color:#20B2AA; font-weight: bold; color:white; border: 0px solid white; border-radius: 0px;" />

						</div>
					</div>
					<div class="row">
						<div class="col-sm-6" id="backgroundGrey">
							<label for="usr" id="labelCalibForCSS">Acceptance
								Criteria</label>
						</div>

						<div class="col-sm-6">
							<form:select path="mainAcceptanceCriteria" class="form-control"
								name="acceptanceCriteria" style="height:35px;"
								id="backgroundGrey">
								<c:forEach items="${calibAcceptanceCriteriaLookup}"
									var="acceptanceCriteria">
									<c:choose>
										<c:when
											test="${equipmentForm.mainAcceptanceCriteria == acceptanceCriteria}">
											<option value="${equipmentForm.mainAcceptanceCriteria}"
												selected>${equipmentForm.mainAcceptanceCriteria}</option>
										</c:when>
										<c:otherwise>
											<option value="${acceptanceCriteria}">${acceptanceCriteria}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<option value="none">none</option>
							</form:select>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6" id="backgroundGreen">
							<label for="usr" id="labelCalibForCSS">Calibration Price
								(INR)</label>
						</div>
						<div class="col-sm-6">
							<form:input type="text" path="mainCalibrationPrice"
								class="form-control" id="backgroundGreen"></form:input>
						</div>
					</div>
				</div>
				<div class="col-sm-5" id="certificateCSS">
					<h3 align="center">
						<b id="headingCSS">Certificate Tracer</b>
					</h3>
					<br>
					<div class="row">
						<div class="col-sm-6" id="backgroundGreen">
							<label for="usr" id="labelCalibForCSS">Instrument
								Location</label>
						</div>
						<div class="col-sm-6">
							<form:select path="mainLocation" class="form-control"
								name="location" style="height:35px;" id="backgroundGreen">
								<c:forEach items="${calibLocationLookup}" var="location">
									<c:choose>
										<c:when test="${equipmentForm.mainLocation == location}">
											<option value="${equipmentForm.mainLocation}" selected>${equipmentForm.mainLocation}</option>
										</c:when>
										<c:otherwise>
											<option value="${location}">${location}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<option value="none">none</option>
							</form:select>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6" id="backgroundGrey">
							<label for="usr" id="labelCalibForCSS">Calibration
								Category</label>
						</div>
						<div class="col-sm-6">
							<form:select path="mainCalibrationCategory" class="form-control"
								name="calibrationCategory" style="height: 35px;"
								id="backgroundGrey">
								<c:forEach items="${calibCategoryLookup}" var="calibCategory">
									<c:choose>
										<c:when
											test="${equipmentForm.mainCalibrationCategory == calibCategory}">
											<option value="${equipmentForm.mainCalibrationCategory}"
												selected>${equipmentForm.mainCalibrationCategory}</option>
										</c:when>
										<c:otherwise>
											<option value="${calibCategory}">${calibCategory}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<option value="none">none</option>
							</form:select>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6" id="backgroundGreen">
							<label for="usr" id="labelCalibForCSS">Calibration Agency</label>
						</div>
						<div class="col-sm-6">
							<form:select path="mainCalibrationAgency" class="form-control"
								name="calibrationAgency" style="height: 35px;"
								id="backgroundGreen">
								<c:forEach items="${calibAgencyListLookup}" var="calibAgency">
									<c:choose>
										<c:when
											test="${equipmentForm.mainCalibrationAgency == calibAgency}">
											<option value="${equipmentForm.mainCalibrationAgency}"
												selected>${equipmentForm.mainCalibrationAgency}</option>
										</c:when>
										<c:otherwise>
											<option value="${calibAgency}">${calibAgency}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<option value="none">none</option>
							</form:select>
						</div>
					</div>
					<!-- 
			<div class="row">
				<div class="col-sm-6">
					<label for="usr">Calibration Frequency:</label>
				</div>
				<div class="col-sm-6">
					<form:select path="mainCalibrationFrequency" class="form-control"
						name="calibrationFrequency" style="height: 35px;">
						<c:forEach items="${calibFrequencyLookup}" var="calibFrequency">
							<c:choose>
								<c:when
									test="${equipmentForm.mainCalibrationFrequency == calibFrequency}">
									<option value="${equipmentForm.mainCalibrationFrequency}"
										selected>${equipmentForm.mainCalibrationFrequency}</option>
								</c:when>
								<c:otherwise>
									<option value="${calibFrequency.getValue()}">${calibFrequency.getKey()}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</form:select>
				</div>
			</div>
			
			-->
					<div class="row">
						<div class="col-sm-6" id="backgroundGrey">
							<label for="usr" id="labelCalibForCSS">Calibration
								Certificate #</label>
						</div>
						<div class="col-sm-6">
							<form:input path="mainCalibrationCertificate" type="text"
								class="form-control" id="backgroundGrey"></form:input>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6" id="backgroundGreen">
							<label for="usr" id="labelCalibForCSS">Certificate
								Validated By</label>
						</div>
						<div class="col-sm-6">
							<form:select path="mainCertificateValidatedBy"
								class="form-control" name="certificateValidatedBy"
								style="height: 35px;" id="backgroundGreen">
								<c:forEach items="${certificateValidatedBy}"
									var="certificateValidatedBy">
									<c:choose>
										<c:when
											test="${equipmentForm.mainCertificateValidatedBy == certificateValidatedBy}">
											<option value="${equipmentForm.mainCertificateValidatedBy}"
												selected>${equipmentForm.mainCertificateValidatedBy}</option>
										</c:when>
										<c:otherwise>
											<option value="${certificateValidatedBy}">${certificateValidatedBy}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<option value="none">none</option>
							</form:select>
						</div>
					</div>

					<div class="row">
						<div class="col-sm-6" id="backgroundGrey">
							<label for="usr" id="labelCalibForCSS">Calibration Result</label>
						</div>
						<div class="col-sm-6">
							<form:select path="mainCalibrationResult" class="form-control"
								name="calibrationResult"
								style="height: 35px; font-weight: bold; border: 0px solid white;border-radius: 0px;background-color: #DCDCDC;font-family: Cambria;"
								id="calibResult" onchange='changeCalibResult(this.value);'>
								<c:forEach items="${calibrationResultLookup}" var="calibResult">
									<c:choose>
										<c:when
											test="${equipmentForm.mainCalibrationResult == calibResult}">
											<option value="${equipmentForm.mainCalibrationResult}"
												selected>${equipmentForm.mainCalibrationResult}</option>
										</c:when>
										<c:otherwise>
											<option value="${calibResult}">${calibResult}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</form:select>
							<div>
								<form:errors path="mainCalibrationResult" cssClass="error"></form:errors>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="col-sm-6" id="backgroundGreen">
							<label for="usr" id="labelCalibForCSS">Instrument/Gauge Supplier</label>
						</div>
						
						<div class="col-sm-6">
							<form:input path="mainCalibrationSource" list="browsers"
								id="backgroundGreen" name="myBrowser" class="form-control" />

							<datalist id="browsers"> <c:forEach
								items="${supplierNameLookup}" var="supplierName">
								<c:choose>
									<c:when
										test="${equipmentForm.mainSupplierName == supplierName}">
										<option value="${equipmentForm.mainSupplierName}" selected>${equipmentForm.mainSupplierName}</option>
									</c:when>
									<c:otherwise>
										<option value="${supplierName}">${supplierName}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach> </datalist>
							<div>
								<form:errors path="mainCalibrationSource" cssClass="error"></form:errors>
							</div>
						</div>
						
						<%-- <div class="col-sm-6">
							<form:select path="mainCalibrationSource" class="form-control"
								name="calibrationSource" id="backgroundGreen"
								style="height: 35px;">
								<c:forEach items="${calibrationSourceLookup}" var="calibSource">
								<c:choose>
										<c:when test="${equipmentForm.mainCalibrationSource == calibSource}">
											<option value="${equipmentForm.mainCalibrationSource}" selected>${equipmentForm.mainCalibrationSource}</option>
										</c:when>
										<c:otherwise>
											<option value="${calibSource}">${calibSource}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<option value="none">none</option>
							</form:select>
							<div>
								<form:errors path="mainCalibrationSource" cssClass="error"></form:errors>
							</div>
						</div> --%>
					</div>

					<div class="row">
						<div class="col-sm-6" id="backgroundGrey">
							<label for="usr" id="labelCalibForCSS">Part Code</label>
						</div>
						<div class="col-sm-6">
							<form:select path="mainPartCode" class="form-control"
								name="partCode" style="height: 35px;" id="backgroundGrey">
								<c:forEach items="${calibPartCodeListLookup}" var="partCode">
									<c:choose>
										<c:when test="${equipmentForm.mainPartCode == partCode}">
											<option value="${equipmentForm.mainPartCode}" selected>${equipmentForm.mainPartCode}</option>
										</c:when>
										<c:otherwise>
											<option value="${partCode}">${partCode}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<option value="none">none</option>
							</form:select>
						</div>
					</div>
				</div>
				<div class="col-sm-1"></div>
			</div>
			<div class="row">
				<div class="col-sm-4"></div>
				<div class="col-sm-1" id="searchBtnCss">
					<button type="submit" id="submit" name="submit"
						class="btn btn-primary">Update</button>
				</div>
				<div class="col-sm-3">
					<spring:url
						value="/printQR_BAR_Code/${equipmentForm.mainEcCalibId}"
						var="generatestickerURL"></spring:url>
					<a href="${generatestickerURL}"><button type="button"
							class="btn btn-primary" id="searchBtnCss">Generate
							Identification Sticker</button></a>
				</div>
				<div class="col-sm-3">
					<spring:url
						value="/uploadOrDownloadcrtificate/${equipmentForm.mainEcCalibId}"
						var="uploadOrDownloadcrtificateURL"></spring:url>
					<a href="${uploadOrDownloadcrtificateURL}"><button
							type="button" class="btn btn-primary" id="searchBtnCss">Upload/Download
							Calibration Certificate</button></a>
				</div>
				<div class="col-sm-4"></div>
			</div>
			<br>

		</form:form>

	</div>
</body>

</html>