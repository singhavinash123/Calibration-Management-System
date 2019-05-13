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

<style>
#headingCSS {
	color: #20B2AA;
}
</style>

<script>
	function getCalibTypeChange(value) {
		var month_abbrs = [ 'Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul',
				'Aug', 'Sep', 'Oct', 'Nov', 'Dec' ];
		var tt = document.getElementById('dueDate').value;
		var date = new Date(tt);
		var newdate = new Date(date);

		var frequency;
		if (value == "Statutory Calibration"
				|| value == "Environmental Calibration") {
			frequency = 30;
		} else if (value == "Utilities Calibration"
				|| value == "Process Calibration"
				|| value == "Product Calibration") {
			frequency = 15;
		} else if (value == "Trivial Calibration") {
			frequency = 45;
		} else if (value == "0") {
			frequency = 0;
		}
		document.getElementById("alertfrequency").value = frequency;
		newdate.setDate(newdate.getDate() - parseInt(frequency));
		var dd = newdate.getDate();
		var mm = newdate.getMonth();
		var y = newdate.getFullYear();

		var someFormattedDate = dd + '-' + month_abbrs[mm] + '-' + y;
		document.getElementById('reminderDate').value = someFormattedDate;

		var x = document.getElementById('equipmentType').value
		document.getElementById("equiptype").value = x;

	};

	function CheckYear(value) {
		var month_abbrs = [ 'Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul',
				'Aug', 'Sep', 'Oct', 'Nov', 'Dec' ];

		var selectedMonthsDays = document.getElementById('months').value;
		var selectedDays = document.getElementById('days').value;
		var total = parseInt(selectedMonthsDays) + parseInt(selectedDays)
				+ parseInt(value);

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

	function checkMonths(value) {

		var month_abbrs = [ 'Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul',
				'Aug', 'Sep', 'Oct', 'Nov', 'Dec' ];
		var tt = document.getElementById('calibrationDate').value;

		var selectedYearDays = document.getElementById('years').value;
		var selectedDays = document.getElementById('days').value;
		var total = parseInt(selectedYearDays) + parseInt(selectedDays)
				+ parseInt(value);

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

	function checkDays(value) {

		var month_abbrs = [ 'Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul',
				'Aug', 'Sep', 'Oct', 'Nov', 'Dec' ];

		var tt = document.getElementById('calibrationDate').value;

		var selectedYearDays = document.getElementById('years').value;
		var selectedDays = document.getElementById('months').value;
		var total = parseInt(selectedYearDays) + parseInt(selectedDays)
				+ parseInt(value);

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
			selectBox.options[0] = new Option('Inactive', 'Inactive', true,
					true);
			document.getElementById("calibStatus").style.color = "red";
			document.getElementById("calibStatus").style.fontSize = "22px";
			document.getElementById("calibStatus").style.backgroundColor = "black";

			selectCalibResult.options[0] = new Option('OK', 'OK', true, true);

		} else {
			var selectBox = document.getElementById("calibStatus");
			selectBox.options[0] = new Option('Calibrated', 'Calibrated', true,
					true);
			selectBox.style.fontFamily = "Arial";
			document.getElementById("calibStatus").style.color = "#7cfc00";
			document.getElementById("calibStatus").style.fontSize = "22px";
			document.getElementById("calibStatus").style.backgroundColor = "black";

			selectCalibResult.options[0] = new Option('OK', 'OK', true, true);

		}

		document.getElementById('dueDate').style.visibility = "visible";
		document.getElementById('reminderDate').style.visibility = "visible";

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
				&& TodayDate.getTime() > newReminderDateForCalibStatus) {
			var selectBox = document.getElementById("calibStatus");
			selectBox.options[0] = new Option('Inactive', 'Inactive', true,
					true);
			document.getElementById("calibStatus").style.color = "red";
			document.getElementById("calibStatus").style.fontSize = "22px";
			document.getElementById("calibStatus").style.backgroundColor = "black";

			selectCalibResult.options[0] = new Option('OK', 'OK', true, true);

		} else {
			var selectBox = document.getElementById("calibStatus");
			selectBox.options[0] = new Option('Calibrated', 'Calibrated', true,
					true);
			document.getElementById("calibStatus").style.color = "#7cfc00";
			document.getElementById("calibStatus").style.fontSize = "22px";
			document.getElementById("calibStatus").style.backgroundColor = "black";

			selectCalibResult.options[0] = new Option('OK', 'OK', true, true);
		}

		document.getElementById('dueDate').style.visibility = "visible";
		document.getElementById('reminderDate').style.visibility = "visible";
	};

	// Before refreshing the page, save the form data to sessionStorage
	window.onbeforeunload = function() {
		sessionStorage.setItem("alertFrequencyStorage", $('#alertfrequency')
				.val());
		sessionStorage.setItem("calibrationTypeStorage", $('#calibrationType')
				.val());
		sessionStorage.setItem("dueDateStorage", $('#dueDate').val());
		sessionStorage.setItem("yearStorage", $('#years').val());
		sessionStorage.setItem("monthsStorage", $('#months').val());
		sessionStorage.setItem("daysStorage", $('#days').val());
		sessionStorage.setItem("calibDateStorage", $('#calibrationDate').val());
	}

	window.onload = function() {

		var yearStorage = sessionStorage.getItem('yearStorage');
		var monthsStorage = sessionStorage.getItem('monthsStorage');
		var daysStorage = sessionStorage.getItem('daysStorage');
		var alertFrequencyStorage = sessionStorage
				.getItem('alertFrequencyStorage');
		var calibrationTypeStorage = sessionStorage
				.getItem('calibrationTypeStorage');
		var dueDateStorage = sessionStorage.getItem('dueDateStorage');
		var calibDateStorage = sessionStorage.getItem('calibDateStorage');

		if (yearStorage !== null)
			$('#years').val(yearStorage);
		if (monthsStorage !== null)
			$('#months').val(monthsStorage);
		if (daysStorage !== null)
			$('#days').val(daysStorage);
		if (alertFrequencyStorage !== null)
			$('#alertfrequency').val(alertFrequencyStorage);
		if (calibrationTypeStorage !== null)
			$('#calibrationType').val(calibrationTypeStorage);
		if (dueDateStorage !== null)
			$('#dueDate').val(dueDateStorage);
		if (calibDateStorage !== null)
			$('#calibrationDate').val(calibDateStorage);

		CheckEquipType(document.getElementById("equipmentType").value);
		//getCalibTypeChange(document.getElementById("alertfrequency").value);
		document.getElementById("calibrationScrapedDate").style.visibility = "hidden";

		document.getElementById('dueDate').style.visibility = "hidden";
		document.getElementById('reminderDate').style.visibility = "hidden";
		var selectCalibResult = document.getElementById("calibResult");

		if (document.getElementById("calibStatus").value == "Inactive") {
			document.getElementById("calibrationScrapedDate").style.visibility = "hidden";
			document.getElementById("calibStatus").style.color = "red";
			document.getElementById("calibStatus").style.fontSize = "20px";
			document.getElementById("calibStatus").style.backgroundColor = "black";

		//	selectCalibResult.options[0] = new Option('OK', 'OK', true, true);

		}
		if (document.getElementById("calibStatus").value == "Active") {
			document.getElementById("calibrationScrapedDate").style.visibility = "hidden";
			document.getElementById("calibStatus").style.color = "#7cfc00";
			document.getElementById("calibStatus").style.fontSize = "20px";
			document.getElementById("calibStatus").style.backgroundColor = "black";

		//	selectCalibResult.options[0] = new Option('OK', 'OK', true, true);

		}
		if (document.getElementById("calibStatus").value == "Calibrated") {
			document.getElementById("calibrationScrapedDate").style.visibility = "hidden";
			document.getElementById("calibStatus").style.color = "#7cfc00";
			document.getElementById("calibStatus").style.fontSize = "20px";
			document.getElementById("calibStatus").style.backgroundColor = "black";

			selectCalibResult.options[0] = new Option('OK', 'OK', true, true);
			selectCalibResult.options[1] = new Option('NOT OK', 'NOT OK', false, false);


		}
		if (document.getElementById("calibStatus").value == "Scrap") {
			document.getElementById("calibStatus").style.color = "black";
			document.getElementById("calibStatus").style.backgroundColor = "red";
			document.getElementById("calibStatus").style.fontSize = "20px";
			document.getElementById("calibrationScrapedDate").style.visibility = "visible";

		//	selectCalibResult.options[0] = new Option('NOT OK', 'NOT OK', true, true);

		}

	};

	function resetForm() {
		document.getElementById("equipmentType").disabled = false;
		getCalibTypeChange(document.getElementById("alertfrequency").value);

		var x = document.getElementById('equipmentType').value
		document.getElementById("equiptype").value = x;
		document.getElementById("mainform").reset();
	}
	
	function changeCalibResult(val){
		var selectBox = document.getElementById("calibStatus");
		if (val == "NOT OK") {
			document.getElementById("calibStatus").style.color = "black";
			document.getElementById("calibStatus").style.backgroundColor = "red";
			selectBox.options[0] = new Option('Scrap', 'Scrap', true, true);
		}

		if (val == "NOT OK") {
			document.getElementById("calibrationScrapedDate").style.visibility = "visible";
			$("#calibrationScrapedDate").datepicker({
				changeMonth : true,
				changeYear : true,
				dateFormat : "dd-M-yy"
			}).datepicker("setDate", new Date())
		} else {
			document.getElementById("calibrationScrapedDate").style.visibility = "hidden";
			$("#calibrationScrapedDate").datepicker({
				changeMonth : true,
				changeYear : true,
				dateFormat : "dd-M-yy"
			}).datepicker("setDate", null)
		}
	}
</script>
<script type="text/javascript">
	function CheckEquipType(val) {
		document.getElementById("equiptype").value = val;
	};

	function changeCalibStatus(val) {
		var selectBox = document.getElementById("calibResult");

		if (val == "Inactive") {
			document.getElementById("calibStatus").style.color = "red";
			document.getElementById("calibStatus").style.backgroundColor = "black";
			selectBox.options[0] = new Option('OK', 'OK', true, true);

		}
		if (val == "Active") {
			document.getElementById("calibStatus").style.color = "#7cfc00";
			document.getElementById("calibStatus").style.backgroundColor = "black";
			selectBox.options[0] = new Option('OK', 'OK', true, true);

		}
		if (val == "Calibrated") {
			document.getElementById("calibStatus").style.color = "#7cfc00";
			document.getElementById("calibStatus").style.backgroundColor = "black";
			selectBox.options[0] = new Option('OK', 'OK', true, true);

		}
		if (val == "Scrap") {
			document.getElementById("calibStatus").style.color = "black";
			document.getElementById("calibStatus").style.backgroundColor = "red";
			selectBox.options[0] = new Option('NOT OK', 'NOT OK', true, true);
		}

		if (val == "Scrap") {
			document.getElementById("calibrationScrapedDate").style.visibility = "visible";
			$("#calibrationScrapedDate").datepicker({
				changeMonth : true,
				changeYear : true,
				dateFormat : "dd-M-yy"
			}).datepicker("setDate", new Date())
		} else {
			document.getElementById("calibrationScrapedDate").style.visibility = "hidden";
			$("#calibrationScrapedDate").datepicker({
				changeMonth : true,
				changeYear : true,
				dateFormat : "dd-M-yy"
			}).datepicker("setDate", null)
		}
	}
</script>
<script>
	$(function() {
		$("#calibrationDate").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd-M-yy"

		}).datepicker("setDate", new Date())

		$("#dueDate").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd-M-yy",
			minDate : 0,
			maxDate : 0
		}).datepicker("setDate", new Date())

		$("#calibrationScrapedDate").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd-M-yy"
		}).datepicker("setDate", null)

		$("#reminderDate").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd-M-yy",
			minDate : 0,
			maxDate : 0
		}).datepicker("setDate", new Date())

	});
</script>

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
		var instrumentRequestor = document
				.getElementById("instrumentRequestor").value;

		if (name == "") {
			window.alert("Please enter Supplier name.");
			name.focus();
			return false;
		}
		if (instrumentRequestor == "") {
			window.alert("Please select instrument requestor.");
			name.focus();
			return false;
		}

		return true;
	};
</script>
<link rel="stylesheet" href="customcss/formCss.css">

</head>
<body>
	<div id="header">
		<jsp:include page="/WEB-INF/jsp/header/header.jsp" />
	</div>
	<div align="center" id="formtitle">
		<h3>Gauge and Equipment Master</h3>
	</div>
	<div class="container-fluid">

		<span id="hereCss">You are here : </span>
		<%
			AppsPropertyFile appsPropertyFile = new AppsPropertyFile();
		%>
		<span><a href=<%=appsPropertyFile.getURLForKey("homePageURL")%>
			id="hrefCss">Home</a></span> <span> / </span> <span><a
			href=<%=appsPropertyFile.getURLForKey("equipmentmaster")%>
			id="hrefCss">Gauge and Equipment Master</a></span> <span>/</span> <span><a
			href=<%=appsPropertyFile.getURLForKey("addequipmentandgauge")%>
			id="hrefCss">Add Gauge and Equipment Master</a></span> <br> <br>

		<div align="center">
			<b id="successMsgCss">${msg}</b>
		</div>

		<div align="center">
			<b id="errorMsgCss">${alreadyExist}</b>
		</div>

		<spring:url value="/save_main_calib_details" var="saveURL" />
		<form:form modelAttribute="equipmentForm" method="post"
			action="${saveURL}" style="font-size:12px;"
			onsubmit="return validateForm()" id="mainform">
			<form:hidden path="mainEcId" />
			<div class="row">
				<div class="col-sm-1"></div>
				<div class="col-sm-5" id="supplierCSS">
					<h3 align="center">
						<b id="headingCSS">Supplier Tracer</b>
					</h3>
					<br>
					<div class="row">
						<div class="col-sm-6" id="backgroundGreen">
							<label for="usr" id="labelCalibForCSS">Instrument
								Requestor</label>
						</div>
						<div class="col-sm-6">
							<form:input path="mainInstrumentRequestor"
								list="instrumentRequestors" id="instrumentRequestor"
								name="instrumentRequestor" class="form-control"
								style="border: 0px solid white;border-radius: 0px;font-weight: bold;background-color: #20B2AA;font-family: Cambria; color:white;"
								placeholder="--Select instrument requestor--" autocomplete="off" />
							<datalist id="instrumentRequestors"> <c:forEach
								items="${UserDataList}" var="instrumentRequestor">
								<option value="${instrumentRequestor.userFullName}">${instrumentRequestor.userFullName}</option>
							</c:forEach>
							<option value="none">none</option>
							</datalist>
							<div>
								<form:errors path="mainInstrumentRequestor" cssClass="error"></form:errors>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6" id="backgroundGrey">
							<label for="usr" id="labelCalibForCSS">Source Of Calibration</label>
						</div>
						<div class="col-sm-6">
							<form:input path="mainSupplierName" list="browsers"
								id="suppliername" name="myBrowser" class="form-control"
								style="border: 0px solid white;border-radius: 0px; font-weight: bold;background-color: #DCDCDC;font-family: Cambria;"
								placeholder="--Select supplier name--" />
							<datalist id="browsers"> <c:forEach
								items="${supplierNameLookup}" var="supplierName">
								<option value="${supplierName}">${supplierName}</option>
							</c:forEach> </datalist>
							<div>
								<form:errors path="mainSupplierName" cssClass="error"></form:errors>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6" id="backgroundGreen" style="width:400px;">
							<label for="usr" id="labelCalibForCSS">Purchase Order</label>
						</div>
						<div class="col-sm-6">
							<form:input path="mainPurchaseOrder" type="text"
								placeholder="Enter Purchase Order..." name="serialNumber"
								class="form-control" id="backgroundGreen" autocomplete="off" />
							<div>
								<form:errors path="mainPurchaseOrder" cssClass="error"></form:errors>
							</div>
						</div>

					</div>
					<div class="row">
						<div class="col-sm-6" id="backgroundGrey">
							<label for="usr" id="labelCalibForCSS">Supplier Invoice</label>
						</div>
						<div class="col-sm-6">
							<form:input path="mainInvoice" type="text"
								placeholder="Enter Supplier Invoice..." name="serialNumber"
								class="form-control" id="backgroundGrey" autocomplete="off" />
							<div>
								<form:errors path="mainInvoice" cssClass="error"></form:errors>
							</div>
						</div>

					</div>
					<div class="row">
						<div class="col-sm-6" id="backgroundGreen">
							<label for="usr" id="labelCalibForCSS">Unit Price (INR)</label>
						</div>
						<div class="col-sm-6">
							<form:input path="mainUnitPrice" type="number" step="0.01"
								placeholder="Enter Unit Price..." name="unitPrice"
								class="form-control" id="backgroundGreen" autocomplete="off" />
							<div>
								<form:errors path="mainUnitPrice" cssClass="error"></form:errors>
							</div>
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
								class="form-control" id="backgroundGrey" autocomplete="off" />
							<div>
								<form:errors path="mainAssetCode" cssClass="error"></form:errors>
							</div>

						</div>
					</div>
					<div class="row">
						<div class="col-sm-6" id="backgroundGreen">
							<label for="usr" id="labelCalibForCSS">Supplier Service
								Type</label>
						</div>
						<div class="col-sm-6">
							<form:input path="mainSupplierService" list="supplierServices"
								id="supplierService" name="supplierService" class="form-control"
								style="border: 0px solid white;border-radius: 0px; font-weight: bold;background-color: #20B2AA;font-family: Cambria; color:white;"
								placeholder="--Select supplier service--" autocomplete="off" />
							<datalist id="supplierServices"> <c:forEach
								items="${supplierServiceTypeLookup}" var="supplierService">
								<option value="${supplierService}">${supplierService}</option>
							</c:forEach>
							<option value="none">none</option>
							</datalist>
							<div>
								<form:errors path="mainSupplierService" cssClass="error"></form:errors>
							</div>
						</div>
					</div>
				</div>
				<div class="col-sm-5" id="supplierCSS">
					<h3 align="center">
						<b id="headingCSS">Equipment Tracer</b>
					</h3>
					<br>
					<div class="row">
						<div class="col-sm-6" id="backgroundGreen">
							<label for="usr" id="labelCalibForCSS">Make</label>
						</div>
						<div class="col-sm-6">
							<form:input path="mainMake" type="text"
								placeholder="Enter Make..." name="make" class="form-control"
								id="backgroundGreen" autocomplete="off" />
							<!-- 
						
							<form:input path="mainMake" list="mainMakes" id="mainMake"
								name="mainMake" class="form-control"
								style="border: 2px solid white;border-radius: 0px;background-color: #20B2AA;font-family: Cambria;"
								placeholder="--Select make--" />
							<datalist id="mainMakes"> <c:forEach
								items="${calibMakeLookup}" var="calibMake">
								<option value="${calibMake}">${calibMake}</option>
							</c:forEach>
							<option value="none">none</option>
							</datalist>
							 -->
							<div>
								<form:errors path="mainMake" cssClass="error"></form:errors>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6" id="backgroundGrey">
							<label for="usr" id="labelCalibForCSS">Instrument / Gauge
								Description</label>
						</div>
						<div class="col-sm-6">
							<form:input path="mainInstrumentGauge"
								list="mainInstrumentGauges" id="mainMake" name="instrumentGauge"
								class="form-control"
								style="border: 0px solid white;border-radius: 0px; font-weight: bold;background-color: #DCDCDC;font-family: Cambria;"
								placeholder="--Select guage--" autocomplete="off" />
							<datalist id="mainInstrumentGauges"> <c:forEach
								items="${equipmentDescWithNumber}" var="instrumentGauge">
								<option value="${instrumentGauge.getKey()}">${instrumentGauge.getKey()}</option>
							</c:forEach>
							<option value="none">none</option>
							</datalist>
							<div>
								<form:errors path="mainInstrumentGauge" cssClass="error"></form:errors>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6" id="backgroundGreen">
							<label for="usr" id="labelCalibForCSS">Model</label>
						</div>
						<div class="col-sm-6">
							<form:input path="mainModel" id="mainMake"
								name="model" class="form-control"
								style="border: 0px solid white;border-radius: 0px; font-weight: bold;background-color: #20B2AA;font-family: Cambria; color:white;"
								placeholder="--Enter Model" autocomplete="off" />
								
							<%-- <datalist id="mainModels"> <c:forEach
								items="${calibModelLookup}" var="calibModel">
								<option value="${calibModel}">${calibModel}</option>
							</c:forEach>
							<option value="none">none</option>
							</datalist>
							 --%>
							
							<div>
								<form:errors path="mainModel" cssClass="error"></form:errors>
							</div>
						</div>

					</div>
					<div class="row">
						<div class="col-sm-6" id="backgroundGrey">
							<label for="usr" id="labelCalibForCSS">Serial #</label>
						</div>
						<div class="col-sm-6">
							<form:input path="mainSerial" type="text"
								placeholder="Enter Serial No...." name="serial"
								class="form-control" id="backgroundGrey" autocomplete="off" />
							<div>
								<form:errors path="mainSerial" cssClass="error"></form:errors>
							</div>
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
								id="backgroundGreen" autocomplete="off" />
							<div>
								<form:errors path="mainInstrumentGaugeRange" cssClass="error"></form:errors>
							</div>

						</div>
					</div>
					<div class="row">
						<div class="col-sm-6" id="backgroundGrey" style="white-space: nowrap;">
							<label for="usr">Least Count / Accuracy Specification</label>
						</div>
						<div class="col-sm-6">
							<form:input path="mainLeast" type="text"
								placeholder="Enter Least Count..." name="serialNumber"
								class="form-control" id="backgroundGrey" autocomplete="off" />
							<div>
								<form:errors path="mainLeast" cssClass="error"></form:errors>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6" id="backgroundGreen">
							<label for="usr" id="labelCalibForCSS">Calibration
								Standard Used</label>
						</div>
						<div class="col-sm-6">
							<form:input path="mainCalibrationStandard"
								list="mainCalibrationStandards" id="mainMake"
								name="mainCalibrationStandard" class="form-control"
								style="border: 0px solid white;border-radius: 0px; font-weight: bold;background-color: #20B2AA;font-family: Cambria;height:35px; color:white;"
								placeholder="--Select calibration standard--" />
							<datalist id="mainCalibrationStandards"> <c:forEach
								items="${calibrationStandardLookUp}" var="calibrationStandard">
								<option value="${calibrationStandard}">${calibrationStandard}</option>
							</c:forEach>
							<option value="none">none</option>
							</datalist>

							<div>
								<form:errors path="mainCalibrationStandard" cssClass="error"></form:errors>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6" id="backgroundGrey" Style="white-space: nowrap;">
							<label for="usr" id="labelCalibForCSS">Calibration
								Forecast Price (INR)</label>
						</div>
						<div class="col-sm-6">
							<form:input path="mainForcastPrice" type="number" step="0.01"
								placeholder="Enter ForeCast Price..." name="forcastPrice"
								class="form-control" id="backgroundGrey" autocomplete="off"
								style="border: 0px solid white;border-radius: 0px;background-color: #DCDCDC;font-family: Cambria;" />
							<div>
								<form:errors path="mainForcastPrice" cssClass="error"></form:errors>
							</div>
						</div>
					</div>
					<br>
				</div>
				<div class="col-sm-1"></div>
			</div>
			<div class="row">
				<div class="col-sm-1"></div>
				<div class="col-sm-5" id="supplierCSS">
					<h3 align="center">
						<b id="headingCSS">Calibration Tracer</b>
					</h3>
					<br>
					<div class="row">
						<div class="col-sm-6" id="backgroundGreen">
							<label for="usr" id="labelCalibForCSS">Equipment Type</label>
						</div>
						<div class="col-sm-6">
							<form:select path="mainEquipmentType" class="form-control"
								name="equipmentdesc" onchange='CheckEquipType(this.value);'
								id="equipmentType"
								style="border: 0px solid white;border-radius: 0px; font-weight: bold;background-color: #20B2AA;font-family: Cambria;height:35px; color:white;">
								<c:forEach items="${equipmentDescWithNumber}"
									var="equipmentdesc">
									<option value="${equipmentdesc.getValue()}">${equipmentdesc.getKey()}</option>
								</c:forEach>
							</form:select>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6" id="backgroundGrey">
							<label for="usr" id="labelCalibForCSS">Guage Unique
								Identification #</label>
						</div>
						<div class="col-sm-6">
							<form:input readonly="true" path="mainIdentificationId"
								type="text" value="${equipmentForm.mainIdentificationId}"
								class="form-control" id="equiptype" autocomplete="off"
								style="border: 0px solid white;border-radius: 0px; font-weight: bold;background-color: #DCDCDC;font-family: Cambria;" />
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6" id="backgroundGreen">
							<label for="usr" id="labelCalibForCSS">Calibration type</label>
						</div>
						<div class="col-sm-6">
							<form:select path="mainCalibrationType" class="form-control"
								name="processNameLookup"
								onchange="getCalibTypeChange(this.value)" id="calibrationType"
								style="border: 0px solid white;border-radius: 0px; font-weight: bold;background-color: #20B2AA;font-family: Cambria;height:35px; color:white;">
								<option value="0">-- Select Calibration Type --</option>
								<c:forEach items="${calibrationTypeLookup}"
									var="calibrationType">
									<option value="${calibrationType.getKey()}">${calibrationType.getKey()}</option>
								</c:forEach>
							</form:select>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6" id="backgroundGrey">
							<label for="usr" id="labelCalibForCSS">Alert Frequency</label>
						</div>
						<div class="col-sm-6">
							<form:input id="alertfrequency" value="0" autocomplete="off"
								path="mainAlertFrequency" class="form-control" readonly="true"
								style="border: 0px solid white;border-radius: 0px; font-weight: bold;background-color: #DCDCDC;font-family: Cambria;" />
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6" id="backgroundWhite">
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
								autocomplete="off" name="calibrationDate" class="form-control"
								readonly="readonly" id="calibrationDate"
								onchange='changeCalibDate(this.value);'
								style="border: 0px solid white;border-radius: 0px;font-weight: bold;background-color: #20B2AA;font-family: Cambria;height:35px; color:white;" />
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6" id="backgroundGrey">
							<label for="usr" id="labelCalibForCSS">Calibration Due
								Date</label>
						</div>
						<div class="col-sm-6">
							<form:input path="mainDueDate" type="text" name="dueDate"
								class="form-control" readonly="true" id="dueDate"
								onchange='changeCalibStatusDate(this.value);' autocomplete="off"
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
								style="border: 0px solid white;border-radius: 0px;background-color: black;font-family: Cambria;height:37px;"
								id="calibStatus" onchange='changeCalibStatus(this.value);'>
								<option value="Calibrated">--Select Status--</option>
								<c:forEach items="${calibrationStatusLookup}"
									var="calibrationStatus">
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
								autocomplete="off"
								style="border: 0px solid white;border-radius: 0px; font-weight: bold;background-color: #DCDCDC;font-family: Cambria;" />
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
								id="reminderDate" autocomplete="off"
								style="border: 0px solid white;border-radius: 0px; font-weight: bold;background-color: #20B2AA;font-family: Cambria;height:35px; color:white;" />
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6" id="backgroundGrey">
							<label for="usr" id="labelCalibForCSS">Acceptance
								Criteria</label>
						</div>
						<div class="col-sm-6">
							<form:input path="mainAcceptanceCriteria"
								list="mainAcceptanceCriterias" id="mainMake"
								name="acceptanceCriteria" class="form-control"
								style="border: 0px solid white;border-radius: 0px; font-weight: bold;background-color: #DCDCDC;font-family: Cambria;"
								placeholder="--Select criteria--" autocomplete="off" />
							<datalist id="mainAcceptanceCriterias"> <c:forEach
								items="${calibAcceptanceCriteriaLookup}"
								var="acceptanceCriteria">
								<option value="${acceptanceCriteria}">${acceptanceCriteria}</option>
							</c:forEach>
							<option value="none">none</option>
							</datalist>

							<div>
								<form:errors path="mainAcceptanceCriteria" cssClass="error"></form:errors>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6" id="backgroundGreen">
							<label for="usr" id="labelCalibForCSS">Calibration Price
								(INR)</label>
						</div>
						<div class="col-sm-6">
							<form:input path="mainCalibrationPrice" class="form-control"
								id="backgroundGreen" placeholder="Enter calibration price"
								type="number" step="0.01" autocomplete="off"></form:input>
							<div>
								<form:errors path="mainCalibrationPrice" cssClass="error"></form:errors>
							</div>
						</div>
					</div>
				</div>

				<div class="col-sm-5" id="supplierCSS">
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
							<form:input path="mainLocation" list="mainLocations"
								id="mainMake" name="location" class="form-control"
								style="border: 0px solid white;border-radius: 0px; font-weight: bold;background-color: #20B2AA;font-family: Cambria;height:35px; color:white;"
								placeholder="--Select location--" />
							<datalist id="mainLocations"> <c:forEach
								items="${calibLocationLookup}" var="calibLocation">
								<option value="${calibLocation}">${calibLocation}</option>
							</c:forEach>
							<option value="none">none</option>
							</datalist>

							<div>
								<form:errors path="mainLocation" cssClass="error"></form:errors>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6" id="backgroundGrey">
							<label for="usr" id="labelCalibForCSS">Calibration
								Category</label>
						</div>
						<div class="col-sm-6">
							<form:input path="mainCalibrationCategory"
								list="mainCalibrationCategories" id="mainMake"
								name="calibrationCategory" class="form-control"
								style="border: 0px solid white;border-radius: 0px; font-weight: bold; background-color: #DCDCDC;font-family: Cambria;"
								placeholder="--Select Calibration category--" />
							<datalist id="mainCalibrationCategories"> <c:forEach
								items="${calibCategoryLookup}" var="calibCategory">
								<option value="${calibCategory}">${calibCategory}</option>
							</c:forEach>
							<option value="none">none</option>
							</datalist>

							<div>
								<form:errors path="mainCalibrationCategory" cssClass="error"></form:errors>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6" id="backgroundGreen">
							<label for="usr" id="labelCalibForCSS">Calibration Agency</label>
						</div>

						<div class="col-sm-6">
							<form:input path="mainCalibrationAgency"
								list="mainCalibrationAgencies" id="calibrationAgency"
								name="calibrationAgency" class="form-control"
								style="border: 0px solid white; font-weight: bold; border-radius: 0px;background-color: #20B2AA;font-family: Cambria;height:35px; color:white;"
								placeholder="--Select calibration agency--" autocomplete="off" />
							<datalist id="mainCalibrationAgencies"> <c:forEach
								items="${calibAgencyListLookup}" var="calibAgency">
								<option value="${calibAgency}">${calibAgency}</option>
							</c:forEach>
							<option value="none">none</option>
							</datalist>

							<div>
								<form:errors path="mainCalibrationAgency" cssClass="error"></form:errors>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6" id="backgroundGrey">
							<label for="usr" id="labelCalibForCSS">Calibration
								Certificate #</label>
						</div>
						<div class="col-sm-6">
							<form:input path="mainCalibrationCertificate" type="text"
								class="form-control" id="backgroundGrey" autocomplete="off"
								placeholder="Enter calibration certificate"></form:input>
							<div>
								<form:errors path="mainCalibrationCertificate" cssClass="error"></form:errors>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6" id="backgroundGreen">
							<label for="usr" id="labelCalibForCSS">Certificate
								Validated By</label>
						</div>
						<div class="col-sm-6">
							<form:input path="mainCertificateValidatedBy"
								list="mainCertificateValidates" id="mainCertificateValidate"
								name="mainCertificateValidate" class="form-control"
								style="border: 0px solid white;font-weight: bold;border-radius: 0px;background-color: #20B2AA;font-family: Cambria;height:35px; color:white;"
								placeholder="--Select validated by--" autocomplete="off" />
							<datalist id="mainCertificateValidates"> <c:forEach
								items="${certificateValidatedBy}" var="certificateValidatedBy">
								<option value="${certificateValidatedBy}">${certificateValidatedBy}</option>
							</c:forEach>
							<option value="none">none</option>
							</datalist>

							<div>
								<form:errors path="mainCertificateValidatedBy" cssClass="error"></form:errors>
							</div>
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
									<option value="${calibResult}">${calibResult}</option>
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
								id="suppliername" name="myBrowser" class="form-control"
								style="border: 0px solid white;border-radius: 0px; font-weight: bold;background-color: #20B2AA;font-family: Cambria;"
								placeholder="--Select supplier name--" />
							<datalist id="browsers"> <c:forEach
								items="${supplierNameLookup}" var="supplierName">
								<option value="${supplierName}">${supplierName}</option>
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
									<option value="${calibSource}">${calibSource}</option>
								</c:forEach>
								<option value="none">none</option>
							</form:select>
							<div>
								<form:errors path="mainCalibrationSource" cssClass="error"></form:errors>
							</div>
						</div> --%>
						<!-- 
						<div class="col-sm-6">
							<form:input path="mainCalibrationSource" type="text"
								class="form-control" id="backgroundGreen" autocomplete="off"
								placeholder="Enter calibration source.."></form:input>
							<div>
								<form:errors path="mainCalibrationSource" cssClass="error"></form:errors>
							</div>
						</div>
						 -->
					</div>

					<div class="row">
						<div class="col-sm-6" id="backgroundGrey">
							<label for="usr" id="labelCalibForCSS">Part Code</label>
						</div>
						<div class="col-sm-6">
							<form:input path="mainPartCode" list="browsers" id="partCode"
							 class="form-control"
								style="border: 0px solid white;border-radius: 0px;background-color: #DCDCDC;font-family: Cambria;"
								placeholder="--Select part code--" autocomplete="off" />
							<datalist id="browsers"> <c:forEach
								items="${calibPartCodeListLookup}" var="partCode">
								<option value="${partCode}">${partCode}</option>
							</c:forEach>
							<option value="none">none</option>
							</datalist>

							<div>
								<form:errors path="mainPartCode" cssClass="error"></form:errors>
							</div>
						</div>
					</div>
				</div>
				<div class="col-sm-1"></div>
			</div>
			<br>
			<div class="row">
				<div class="col-sm-5"></div>
				<div class="col-sm-2" id="searchBtnCss">
					<button type="submit" id="submit" name="submit"
						class="btn btn-primary">Save</button>
					<div class="col-sm-3"></div>
				</div>
				<div class="col-sm-4"></div>
			</div>
			<br>
		</form:form>
	</div>
</body>

</html>