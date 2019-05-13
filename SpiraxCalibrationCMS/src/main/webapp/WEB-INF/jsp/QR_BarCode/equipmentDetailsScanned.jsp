<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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


<link rel="stylesheet" href="customcss/formCss.css">

<style>
#headingCSS {
	color: #20B2AA;
}

#backgroundGreen {
	background-color: #20B2AA;
	font-family: Cambria;
	font-size: 16px;
		color: white;
	
}

#backgroundGrey {
	background-color: #DCDCDC;
	font-family: Cambria;
	font-size: 16px;
}
</style>
<script>
	function getCalibTypeChange(value) {
		document.getElementById("alertfrequency").value = value
		var month_abbrs = [ 'Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 
                            'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'
        ];
		var tt = document.getElementById('dueDate').value;
		var date = new Date(tt);
	    var newdate = new Date(date);
	    newdate.setDate(newdate.getDate() + parseInt(value));
	    var dd = newdate.getDate();
	    var mm = newdate.getMonth();
	    var y = newdate.getFullYear();

	    var someFormattedDate = dd  + '-' + month_abbrs[mm] + '-' + y;
	    document.getElementById('reminderDate').value = someFormattedDate;
	    
	};
	
	window.onload = function() {
			getCalibTypeChange(document.getElementById("alertfrequency").value);
			if(${equipmentForm.mainEcId} != null){
		        document.getElementById("equipmentType").disabled=true;
			}
	;
	
	function resetForm() {
		document.getElementById("equipmentType").disabled = false;
		getCalibTypeChange(document.getElementById("alertfrequency").value);
		
		var x = document.getElementById('equipmentType').value
		document.getElementById("equiptype").value = x;
		
		document.getElementById("mainform").reset();

	}
</script>
<script type="text/javascript">
	function CheckEquipType(val) {
		document.getElementById("equiptype").value = val;
	};
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
            minDate: 0
		}).datepicker("setDate", new Date())
		
		$("#calibrationScrapedDate").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd-M-yy"
		}).datepicker("setDate", new Date())
	
		$("#reminderDate").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd-M-yy"
		}).datepicker("setDate", new Date())
		
	});
</script>
</head>
<body>
	<div align="center">
		<h3>SPIRAX SARCO</h3>
	</div>
	<div class="container-fluid">
		<div class="row">
		<div align="right" id="searchboxCss" style="margin-left: 17px;">
						<div id="viewCertificate">
							<a
								href="/uploadOrDownloadcrtificate/${CalibMainData.mainEcCalibId}"
								class="btn"
								style="background-color: #ADD8E6; border-radius: 12px; color:black;"
								target="_blank">View Calibration Certificate</a>
						</div>
	    </div>
			<div class="col-sm-12">
				<div align="center">
					<b style="color: green; font-size: 15px;">${msg}</b>
				</div>
				<div class="row">
					<div class="col-sm-1"></div>
					<div class="col-sm-10">
						<h3 align="center">
							<b id="headingCSS">Calibration Tracer</b>
						</h3>

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

						<!-- 
						<div class="row">
							<div class="col-sm-6" id="backgroundGreen">
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
							<div class="col-sm-1"></div>
							<div class="col-sm-5"
								style="background-color:black; font-size: 22px; font-weight: bold; font-family: Cambria; color: ${CalibMainData.colorFlag};">
								<label>${CalibMainData.mainCalibrationStatus}</label>
							</div>
						</div>
						<div class="col-sm-6" style="background-color: #FFFFFF;">
							<label></label>
						</div>
						<div class="row">
							<div class="col-sm-6" id="backgroundGrey">
								<label>Gauge Unique identification</label>
							</div>
							<div class="col-sm-1"></div>
							<div class="col-sm-5" id="backgroundGrey">
								<label>${CalibMainData.mainIdentificationId}</label>
							</div>
						</div>
						<div class="col-sm-5" style="background-color: #FFFFFF;">
							<label></label>
						</div>
						<div class="row">
							<div class="col-sm-6" id="backgroundGreen">
								<label>Date Of Calibration</label>
							</div>
							<div class="col-sm-1"></div>
							<div class="col-sm-5" id="backgroundGreen">
								<label>${CalibMainData.mainCalibrationDate}</label>
							</div>
						</div>
						<div class="col-sm-5" style="background-color: #FFFFFF;">
							<label></label>
						</div>
						<div class="row">
							<div class="col-sm-6" id="backgroundGrey">
								<label>Calibration Reminder Date</label>
							</div>
							<div class="col-sm-1"></div>
							<div class="col-sm-5" id="backgroundGrey">
								<label>${CalibMainData.mainReminderDate}</label>
							</div>
						</div>
						<div class="col-sm-6" style="background-color: #FFFFFF;">
							<label></label>
						</div>
						<div class="row">
							<div class="col-sm-6" id="backgroundGreen">
								<label>Acceptance Criteria </label>
							</div>
							<div class="col-sm-1"></div>
							<div class="col-sm-5" id="backgroundGreen">
								<label>&#177;${CalibMainData.mainAcceptanceCriteria}</label>
							</div>
						</div>
						<div class="col-sm-6" style="background-color: #FFFFFF;">
							<label></label>
						</div>
						<div class="row">
							<div class="col-sm-6" id="backgroundGrey">
								<label>Calibration Due Date</label>
							</div>
							<div class="col-sm-1"></div>
							<div class="col-sm-5" id="backgroundGrey">
								<label>${CalibMainData.mainDueDate}</label>
							</div>
						</div>
						<div class="col-sm-5" style="background-color: #FFFFFF;">
							<label></label>
						</div>
						<div class="col-sm-6" style="background-color: #FFFFFF;">
							<label></label>
						</div>
						<div class="col-sm-6" style="background-color: #FFFFFF;">
							<label></label>
						</div>
						<div class="row">
							<div class="col-sm-6" id="backgroundGreen">
								<label>PR. No.</label>
							</div>
							<div class="col-sm-1"></div>
							<div class="col-sm-5" id="backgroundGreen">
								<label>${pRPData.pRpPrNumber}</label>
							</div>
						</div>
						<div class="col-sm-6" style="background-color: #FFFFFF;">
							<label></label>
						</div>
						<div class="row">
							<div class="col-sm-6" id="backgroundGrey">
								<label>Approver 1</label>
							</div>
							<div class="col-sm-1"></div>
							<div class="col-sm-5" id="backgroundGrey">
								<label>${pRPData.pRpApprover1FullName}</label>
							</div>
						</div>
						<div class="col-sm-6" style="background-color: #FFFFFF;">
							<label></label>
						</div>
						<div class="row">
							<div class="col-sm-6" id="backgroundGreen">
								<label>Approver 1 Date</label>
							</div>
							<div class="col-sm-1"></div>
							<div class="col-sm-5" id="backgroundGreen">
								<label>${pRPData.pRpApprover1Date}</label>
							</div>
						</div>
						<div class="col-sm-6" style="background-color: #FFFFFF;">
							<label></label>
						</div>
						<div class="row">
							<div class="col-sm-6" id="backgroundGrey">
								<label>Approver 2</label>
							</div>
							<div class="col-sm-1"></div>
							<div class="col-sm-5" id="backgroundGrey">
								<label>${pRPData.pRpApprover2FullName}</label>
							</div>
						</div>
						<div class="col-sm-6" style="background-color: #FFFFFF;">
							<label></label>
						</div>
						<div class="row">
							<div class="col-sm-6" id="backgroundGreen">
								<label>Approver 2 Date</label>
							</div>
							<div class="col-sm-1"></div>
							<div class="col-sm-5" id="backgroundGreen">
								<label>${pRPData.pRpApprover2Date}</label>
							</div>
						</div>
						<div class="col-sm-6" style="background-color: #FFFFFF;">
							<label></label>
						</div>
						<div class="col-sm-6" style="background-color: #FFFFFF;">
							<label></label>
						</div>
						<div class="col-sm-6" style="background-color: #FFFFFF;">
							<label></label>
						</div>
					</div>
				</div>
				<div class="container-fluid">
					<!-- Control the column width, and how they should appear on different devices -->
					<div class="row">
						<div class="col-sm-6">
							<h3 align="center">
								<b id="headingCSS">EquipmentTracer</b>
							</h3>
							<div class="row">
								<div class="col-sm-6" id="backgroundGreen">
									<label>Make</label>
								</div>
								<div class="col-sm-1"></div>
								<div class="col-sm-5" id="backgroundGreen">
									<label>${CalibMainData.mainMake}</label>
								</div>
								<div class="col-sm-5" style="background-color: #FFFFFF;">

									<label></label>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-6" id="backgroundGrey">
									<label>Instrument/GuageDescription</label>
								</div>
								<div class="col-sm-1"></div>
								<div class="col-sm-5" id="backgroundGrey">
									<label>${CalibMainData.mainInstrumentGauge}</label>
								</div>
								<div class="col-sm-5" style="background-color: #FFFFFF;">

									<label></label>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-6" id="backgroundGreen">
									<label>Model</label>
								</div>
								<div class="col-sm-1"></div>
								<div class="col-sm-5" id="backgroundGreen">
									<label>${CalibMainData.mainModel}</label>
								</div>
								<div class="col-sm-5" style="background-color: #FFFFFF;">
									<label></label>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-6" id="backgroundGrey">
									<label>Serial #</label>
								</div>
								<div class="col-sm-1"></div>
								<div class="col-sm-5" id="backgroundGrey">
									<label>${CalibMainData.mainSerial}</label>
								</div>
								<div class="col-sm-5" style="background-color: #FFFFFF;">

									<label></label>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-6" id="backgroundGreen">
									<label>Instrument/GaugeRange</label>
								</div>
								<div class="col-sm-1"></div>
								<div class="col-sm-5" id="backgroundGreen">
									<label>${CalibMainData.mainInstrumentGaugeRange}</label>
								</div>
								<div class="col-sm-5" style="background-color: #FFFFFF;">

									<label></label>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-6" id="backgroundGrey">
									<label>LeastCount/AccuracySpecification</label>
								</div>
								<div class="col-sm-1"></div>
								<div class="col-sm-5" id="backgroundGrey">
									<label>${CalibMainData.mainLeast}</label>
								</div>
							</div>
							<div class="col-sm-5" style="background-color: #FFFFFF;">

								<label></label>
							</div>
							<div class="row">
								<div class="col-sm-6" id="backgroundGreen">
									<label>CalibrationStandardUsed</label>
								</div>
								<div class="col-sm-1"></div>
								<div class="col-sm-5" id="backgroundGreen">
									<label>${CalibMainData.mainCalibrationStandard}</label>
								</div>
							</div>
						</div>

						<div class="col-sm-6">
							<h3 align="center">
								<b id="headingCSS">CertificateTracer</b>
							</h3>
							<div class="row">
								<div class="col-sm-5" id="backgroundGreen">
									<label>InstrumentLocation</label>
								</div>
								<div class="col-sm-1"></div>
								<div class="col-sm-6" id="backgroundGreen">
									<label>${CalibMainData.mainLocation}</label>
								</div>
								<div class="col-sm-5" style="background-color: #FFFFFF;">
									<label></label>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-5" id="backgroundGrey">
									<label>CalibrationCategory</label>
								</div>
								<div class="col-sm-1"></div>
								<div class="col-sm-6" id="backgroundGrey">
									<label>${CalibMainData.mainCalibrationCategory}</label>
								</div>
								<div class="col-sm-5" style="background-color: #FFFFFF;">
									<label></label>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-5" id="backgroundGreen">
									<label>CalibrationFrequency</label>
								</div>
								<div class="col-sm-1"></div>
								<div class="col-sm-6" id="backgroundGreen">
									<label>${CalibMainData.mainCalibrationFrequency}</label>
								</div>
								<div class="col-sm-5" style="background-color: #FFFFFF;">
									<label></label>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-5" id="backgroundGrey">
									<label>CalibrationAgency</label>
								</div>
								<div class="col-sm-1"></div>
								<div class="col-sm-6" id="backgroundGrey">
									<label>${CalibMainData.mainCalibrationAgency}</label>
								</div>
								<div class="col-sm-5" style="background-color: #FFFFFF;">
									<label></label>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-5" id="backgroundGreen">
									<label>CalibrationCertificate </label>
								</div>
								<div class="col-sm-1"></div>
								<div class="col-sm-6" id="backgroundGreen">
									<label>${CalibMainData.mainCalibrationCertificate}</label>
								</div>
								<div class="col-sm-5" style="background-color: #FFFFFF;">
									<label></label>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-5" id="backgroundGrey">
									<label>CertificateValidatedBy</label>
								</div>
								<div class="col-sm-1"></div>
								<div class="col-sm-6" id="backgroundGrey">
									<label>${CalibMainData.mainCertificateValidatedBy}</label>
								</div>
								<div class="col-sm-5" style="background-color: #FFFFFF;">
									<label></label>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-5" id="backgroundGreen">
									<div class="div1">
										<label>Part Code</label>
									</div>
								</div>
								<div class="col-sm-1"></div>
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
						<div class="col-sm-4"></div>
					</div>
				</div>
				<br>
			</div>
		</div>
	</div>
</body>
</html>