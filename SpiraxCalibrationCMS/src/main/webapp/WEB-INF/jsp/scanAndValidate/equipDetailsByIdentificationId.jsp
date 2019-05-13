<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.spiraxcalibration.WebConfig.AppsPropertyFile"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="X-UA Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
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

<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>


<link rel="stylesheet" href="customcss/scanTraceMe.css">

<style>
#headingCSS {
	color: #20B2AA;
}
</style>

<script>
    window.onload = function() {
    
	    var x = document.getElementById("searcheddata");
	    var cert = document.getElementById("viewCertificate");

	    
	    if(${flag}){
		    x.style.display = "none";
		    cert.style.display = "none";
	    }else{
		    x.style.display = "block";
		    cert.style.display = "block";
	    }
	    
	    if (document.getElementById("calibStatus").textContent == "Scrapped") {
			document.getElementById("calibStatus").style.color = "black";
			document.getElementById("calibStatus").style.fontSize = "20px";
			document.getElementById("calibStatus").style.backgroundColor = "red";
		}
    }
</script>
</head>
<body>
	<div id="header">
		<jsp:include page="/WEB-INF/jsp/header/header.jsp" />
	</div>
	<div align="center">
		<h3 id="formtitle">Scan and Validate</h3>
	</div>
	<div class="container-fluid">

		<span id="hereCss">You are here : </span>
		<%
			AppsPropertyFile appsPropertyFile = new AppsPropertyFile();
		%>
		<span><a href=<%=appsPropertyFile.getURLForKey("homePageURL")%>
			id="hrefCss">Home</a></span> <span>/</span> <span><a id="hrefCss"
			href=<%=appsPropertyFile.getURLForKey("scanTextURL")%>>detailsEquipment</a></span>
		<br> <br>

		<div class="row">
			<form action="/scanAndValidate" method="POST">
				<div class="d-flex justify-content-between">
					<div align="left" id="searchboxCss" style="margin-left: 8px;">
						<div class="input-group mb-3">
							<input type="text" name="scanText" id="scanText"
								class="form-control"
								placeholder="Enter Identification Number..." />
							<div class="input-group-append">
								<input type="submit" value="Scan" value="Search"
									class="btn btn-info" id="searchBtnCss" onclick="myFunction()" />
							</div>
						</div>
					</div>
                     
					<div align="right" id="searchboxCss" style="margin-left: 30px;">
						<div id="viewCertificate">
							<a
								href="/uploadOrDownloadcrtificate/${CalibMainData.mainEcCalibId}"
								class="btn"
								style="background-color: #ADD8E6; border-radius: 12px;"
								target="_blank">View Calibration Certificate</a>
						</div>
					</div>
				</div>
				<br> <input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
			</form>
		</div>
		<div align="center">
			<b id="errorMsgCss">${errorMsg}</b>
		</div>
		<div class="row" id="searcheddata">
			<div class="col-sm-12">

				<input type="hidden" name="USER_NAME" value="NOT_ADMIN">
				<div class="row">
					<div class="col-sm-1"></div>
					<div class="col-sm-10">
						<h3 align="center">
							<b id="headingCSS">Calibration Tracer</b>
						</h3>
						<!-- 
						<div class="row">
							<div class="col-sm-6" style="background-color: #48D1CC;">
								<label>Calibration type</label>
							</div>
							<div class="col-sm-1"></div>
							<div class="col-sm-5" style="background-color: #48D1CC;">
								<label>${CalibMainData.mainCalibrationType}</label>
							</div>
							<div class="col-sm-5" style="background-color: #FFFFFF;">
								<label></label>
							</div>
						</div>
						 -->
						<div class="row">
							<div class="col-sm-6" id="backgroundGreen">
								<label>Calibration Status</label>
							</div>
							<div class="col-sm-6"
								style="color: ${CalibMainData.colorFlag};background-color: black; font-size: 22px; font-weight: bold; font-family: Cambria">
								<label id="calibStatus">${CalibMainData.mainCalibrationStatus}</label>
							</div>


						</div>
						<div class="row">
							<div class="col-sm-6" id="backgroundGrey">
								<label>Gauge Unique Identification</label>
							</div>
							<div class="col-sm-6" id="backgroundGrey">
								<label>${CalibMainData.mainIdentificationId}</label>
							</div>
						</div>

						<div class="row">
							<div class="col-sm-6" id="backgroundGreen">
								<label>Date Of Calibration</label>
							</div>
							<div class="col-sm-6" id="backgroundGreen">
								<label>${CalibMainData.mainCalibrationDate}</label>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6" id="backgroundGrey">
								<label>Calibration Reminder Date</label>
							</div>
							<div class="col-sm-6" id="backgroundGrey">
								<label>${CalibMainData.mainReminderDate}</label>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6" id="backgroundGreen">
								<label>Acceptance Criteria </label>
							</div>
							<div class="col-sm-6" id="backgroundGreen">
								<label>&#177;${CalibMainData.mainAcceptanceCriteria}</label>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6" id="backgroundGrey">
								<label>Calibration Due Date</label>
							</div>
							<div class="col-sm-6" id="backgroundGrey">
								<label>${CalibMainData.mainDueDate}</label>
							</div>
						</div>
						<div class="col-sm-5" style="background-color: #FFFFFF;">
							<label></label>
						</div>
						<div class="row">
							<div class="col-sm-6" id="backgroundGreen">
								<label>PR. No.</label>
							</div>
							<div class="col-sm-6" id="backgroundGreen">
								<label>${pRPData.pRpPrNumber}</label>
							</div>
						</div>

						<div class="row">
							<div class="col-sm-6" id="backgroundGrey">
								<label>Approver1</label>
							</div>
							<div class="col-sm-6" id="backgroundGrey">
								<label>${pRPData.pRpApprover1FullName}</label>
							</div>
						</div>

						<div class="row">
							<div class="col-sm-6" id="backgroundGreen">
								<label>Approver1 Date</label>
							</div>
							<div class="col-sm-6" id="backgroundGreen">
								<label>${pRPData.pRpApprover1Date}</label>
							</div>
						</div>

						<div class="row">
							<div class="col-sm-6" id="backgroundGrey">
								<label>Approver2</label>
							</div>
							<div class="col-sm-6" id="backgroundGrey">
								<label>${pRPData.pRpApprover2FullName}</label>
							</div>
						</div>

						<div class="row">
							<div class="col-sm-6" id="backgroundGreen">
								<label>Approver2 Date</label>
							</div>
							<div class="col-sm-6" id="backgroundGreen">
								<label>${pRPData.pRpApprover2Date}</label>
							</div>
						</div>

						<div class="col-sm-6">
							<label></label>
						</div>
						<div class="col-sm-6">
							<label></label>
						</div>
					</div>
				</div>
				<div class="container-fluid">
				
				<div class="row">
					<div class="col-sm-1"></div>
					<div class="col-sm-10">
						<h3 align="center">
							<b id="headingCSS">Equipment Tracer</b>
						</h3>
						<!-- 
						<div class="row">
							<div class="col-sm-6" style="background-color: #48D1CC;">
								<label>Calibration type</label>
							</div>
							<div class="col-sm-1"></div>
							<div class="col-sm-5" style="background-color: #48D1CC;">
								<label>${CalibMainData.mainCalibrationType}</label>
							</div>
							<div class="col-sm-5" style="background-color: #FFFFFF;">
								<label></label>
							</div>
						</div>
						 -->
						<div class="row">
							<div class="col-sm-6" id="backgroundGreen">
								<label>Make</label>
							</div>
							<div class="col-sm-6" id="backgroundGreen">
									<label id="calibStatus">${CalibMainData.mainMake}</label>
							</div>


						</div>
						<div class="row">
							<div class="col-sm-6" id="backgroundGrey">
								<label>Instrument / Guage Description</label>
							</div>
							<div class="col-sm-6" id="backgroundGrey">
								<label>${CalibMainData.mainInstrumentGauge}</label>
							</div>
						</div>

						<div class="row">
							<div class="col-sm-6" id="backgroundGreen">
								<label>Model</label>
							</div>
							<div class="col-sm-6" id="backgroundGreen">
								<label>${CalibMainData.mainModel}</label>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6" id="backgroundGrey">
								<label>Serial #</label>
							</div>
							<div class="col-sm-6" id="backgroundGrey">
								<label>${CalibMainData.mainSerial}</label>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6" id="backgroundGreen">
								<label>Instrument / Gauge Range</label>
							</div>
							<div class="col-sm-6" id="backgroundGreen">
								<label>${CalibMainData.mainInstrumentGaugeRange}</label>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6" id="backgroundGrey">
								<label>LeastCount / Accuracy Specification</label>
							</div>
							<div class="col-sm-6" id="backgroundGrey">
								<label>${CalibMainData.mainLeast}</label>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6" id="backgroundGreen">
								<label>Calibration Standard Used</label>
							</div>
							<div class="col-sm-6" id="backgroundGreen">
								<label>${CalibMainData.mainCalibrationStandard}</label>
							</div>
						</div>

						<div class="col-sm-6">
							<label></label>
						</div>
						<div class="col-sm-6">
							<label></label>
						</div>
					</div>
				</div>
				
				
				<div class="row">
					<div class="col-sm-1"></div>
					<div class="col-sm-10">
						<h3 align="center">
							<b id="headingCSS">Certificate Tracer</b>
						</h3>
						<div class="row">
							<div class="col-sm-6" id="backgroundGreen">
								<label>Instrument Location</label>
							</div>
							<div class="col-sm-6" id="backgroundGreen">
									<label id="calibStatus">${CalibMainData.mainLocation}</label>
							</div>

						</div>
						<div class="row">
							<div class="col-sm-6" id="backgroundGrey">
								<label>Calibration Category</label>
							</div>
							<div class="col-sm-6" id="backgroundGrey">
								<label>${CalibMainData.mainCalibrationCategory}</label>
							</div>
						</div>

						<div class="row">
							<div class="col-sm-6" id="backgroundGreen">
								<label>Calibration Frequency</label>
							</div>
							<div class="col-sm-6" id="backgroundGreen">
								<label>${CalibMainData.mainCalibrationFrequency}</label>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6" id="backgroundGrey">
								<label>Calibration Agency</label>
							</div>
							<div class="col-sm-6" id="backgroundGrey">
								<label>${CalibMainData.mainCalibrationAgency}</label>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6" id="backgroundGreen">
								<label>Calibration Certificate</label>
							</div>
							<div class="col-sm-6" id="backgroundGreen">
								<label>${CalibMainData.mainCalibrationCertificate}</label>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6" id="backgroundGrey">
								<label>Certificate Validated By</label>
							</div>
							<div class="col-sm-6" id="backgroundGrey">
								<label>${CalibMainData.mainCertificateValidatedBy}</label>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6" id="backgroundGreen">
								<label>Part Code</label>
							</div>
							<div class="col-sm-6" id="backgroundGreen">
								<label>${CalibMainData.mainPartCode}</label>
							</div>
						</div>

						<div class="col-sm-6" style="background-color: #FFFFFF;">
							<label></label>
						</div>
						<div class="col-sm-6" style="background-color: #FFFFFF;">
							<label></label>
						</div>
					</div>
				</div>
				</div>
				
				
				<%-- <div class="container-fluid">
					<!-- Control the column width, and how they should appear on different devices -->
					<div class="row">
						<div class="col-sm-1"></div>
						<div class="col-sm-5">
							<h3 align="center">
								<b id="headingCSS">Equipment Tracer</b>
							</h3>
							<div class="row">
								<div class="col-sm-6" id="backgroundGreen">
									<label>Make</label>
								</div>
								<div class="col-sm-6" id="backgroundGreen">
									<label>${CalibMainData.mainMake}</label>
								</div>

							</div>
							<div class="row">
								<div class="col-sm-6" id="backgroundGrey">
									<label>Instrument / Guage Description</label>
								</div>
								<div class="col-sm-6" id="backgroundGrey">
									<label>${CalibMainData.mainInstrumentGauge}</label>
								</div>

							</div>
							<div class="row">
								<div class="col-sm-6" id="backgroundGreen">
									<label>Model</label>
								</div>
								<div class="col-sm-6" id="backgroundGreen">
									<label>${CalibMainData.mainModel}</label>
								</div>

							</div>
							<div class="row">
								<div class="col-sm-6" id="backgroundGrey">
									<label>Serial #</label>
								</div>
								<div class="col-sm-6" id="backgroundGrey">
									<label>${CalibMainData.mainSerial}</label>
								</div>

							</div>
							<div class="row">
								<div class="col-sm-6" id="backgroundGreen">
									<label>Instrument / GaugeRange</label>
								</div>
								<div class="col-sm-6" id="backgroundGreen">
									<label>${CalibMainData.mainInstrumentGaugeRange}</label>
								</div>

							</div>
							<div class="row">
								<div class="col-sm-6" id="backgroundGrey">
									<label>LeastCount / Accuracy Specification</label>
								</div>
								<div class="col-sm-6" id="backgroundGrey">
									<label>${CalibMainData.mainLeast}</label>
								</div>
							</div>

							<div class="row">
								<div class="col-sm-6" id="backgroundGreen">
									<label>Calibration Standard Used</label>
								</div>
								<div class="col-sm-6" id="backgroundGreen">
									<label>${CalibMainData.mainCalibrationStandard}</label>
								</div>
							</div>
						</div>
						<div class="col-sm-5">
							<h3 align="center">
								<b id="headingCSS">Certificate Tracer</b>
							</h3>
							<div class="row">
								<div class="col-sm-6" id="backgroundGreen">
									<label>Instrument Location</label>
								</div>
								<div class="col-sm-6" id="backgroundGreen">
									<label>${CalibMainData.mainLocation}</label>
								</div>

							</div>
							<div class="row">
								<div class="col-sm-6" id="backgroundGrey">
									<label>Calibration Category</label>
								</div>
								<div class="col-sm-6" id="backgroundGrey">
									<label>${CalibMainData.mainCalibrationCategory}</label>
								</div>

							</div>
							<div class="row">
								<div class="col-sm-6" id="backgroundGreen">
									<label>Calibration Frequency</label>
								</div>
								<div class="col-sm-6" id="backgroundGreen">
									<label>${CalibMainData.mainCalibrationFrequency}</label>
								</div>

							</div>
							<div class="row">
								<div class="col-sm-6" id="backgroundGrey">
									<label>Calibration Agency</label>
								</div>
								<div class="col-sm-6" id="backgroundGrey">
									<label>${CalibMainData.mainCalibrationAgency}</label>
								</div>

							</div>
							<div class="row">
								<div class="col-sm-6" id="backgroundGreen">
									<label>Calibration Certificate </label>
								</div>
								<div class="col-sm-6" id="backgroundGreen">
									<label>${CalibMainData.mainCalibrationCertificate}</label>
								</div>

							</div>
							<div class="row">
								<div class="col-sm-6" id="backgroundGrey">
									<label>Certificate Validated By</label>
								</div>
								<div class="col-sm-6" id="backgroundGrey">
									<label>${CalibMainData.mainCertificateValidatedBy}</label>
								</div>

							</div>
							<div class="row">
								<div class="col-sm-6" id="backgroundGreen">
									<div class="div1">
										<label>Part Code</label>
									</div>
								</div>
								<div class="col-sm-6" id="backgroundGreen">
									<label>${CalibMainData.mainPartCode}</label>
								</div>
							</div>
							<div class="col-sm-6" style="background-color: #FFFFFF;">
								<label></label>
								<div class="col-sm-6" style="background-color: #FFFFFF;">
									<label></label>
									<div class="col-sm-6" style="background-color: #FFFFFF;">
										<label></label>
									</div>
								</div>
							</div>
						</div>
						<div class="col-sm-1"></div>
					</div>
				</div> --%>
				<br>
			</div>
		</div>


	</div>
</body>
</html>