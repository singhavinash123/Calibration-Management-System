

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

<link href="${bootstrapCssURL}" rel="stylesheet">
</head>
<body>
	<div id="header">
		<jsp:include page="/WEB-INF/jsp/header/header.jsp" />
	</div>
	<div align="center">
		<h3>SPIRAX SARCO</h3>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-sm-5"></div>
		</div>
		<div class="row">
		
			<div class="col-sm-1"></div>
			<div class="col-sm-10">
			
				<h3 align="center">
					<u>A.Calibration Tracer</u>
				</h3>

				<div class="row">
				
					<div class="col-sm-5" style="background-color: #48D1CC;">
					
					
						<label>Calibration type</label>
					</div>
					<div class="col-sm-1"></div>
					<div class="col-sm-5" style="background-color: #48D1CC;">
						<label>${CalibMainData.mainCalibrationType}</label>
					</div>
					
				</div>
				<div class="col-sm-5" style="background-color: #FFFFFF;">
					<label></label>
				</div>
				<div class="row">
					<div class="col-sm-5" style="background-color: #DCDCDC;">
						<label>Gauge Unique identification</label>
					</div>
					<div class="col-sm-1"></div>
					<div class="col-sm-5" style="background-color: #DCDCDC;">
						<label>${CalibMainData.mainIdentificationId}</label>
					</div>
				</div>
				<div class="col-sm-5" style="background-color: #FFFFFF;">
					<label></label>
				</div>
				<div class="row">
					<div class="col-sm-5" style="background-color: #48D1CC;">
						<label>Date Of Calibration</label>
					</div>
					<div class="col-sm-1"></div>
					<div class="col-sm-5" style="background-color: #48D1CC;">
						<label>${CalibMainData.mainCalibrationDate}</label>
					</div>
				</div>
				<div class="col-sm-5" style="background-color: #FFFFFF;">
					<label></label>
				</div>
				<div class="row">
					<div class="col-sm-5" style="background-color: #DCDCDC;">
						<label>Calibration Due Date</label>
					</div>
					<div class="col-sm-1"></div>
					<div class="col-sm-5" style="background-color: #DCDCDC;">
						<label>${CalibMainData.mainDueDate}</label>
					</div>
				</div>
				<div class="col-sm-5" style="background-color: #FFFFFF;">
					<label></label>
				</div>

				<div class="row">
					<div class="col-sm-5" style="background-color: #48D1CC;">
						<label>Calibration Status</label>
					</div>
					<div class="col-sm-1"></div>
					<div class="col-sm-5"
						style="font-size: 40px; background-color: black;">
						<label
							style="color: #7cfc00; font-size: 16px; font-weight: bold; font-family: Cambria">${CalibMainData.mainCalibrationStatus}</label>
					</div>
				</div>
				<div class="col-sm-6" style="background-color: #FFFFFF;">
					<label></label>
				</div>
				<div class="row">
					<div class="col-sm-5" style="background-color: #DCDCDC;">
						<label>Calibration Reminder Date</label>
					</div>
					<div class="col-sm-1"></div>
					<div class="col-sm-5" style="background-color: #DCDCDC;">
						<label>${CalibMainData.mainReminderDate}</label>
					</div>
				</div>
				<div class="col-sm-6" style="background-color: #FFFFFF;">
					<label></label>
				</div>
				<div class="row">
					<div class="col-sm-5" style="background-color: #48D1CC;">
						<label>Acceptance Criteria </label>
					</div>
					<div class="col-sm-1"></div>
					<div class="col-sm-5" style="background-color: #48D1CC;">
						<label>${CalibMainData.mainAcceptanceCriteria}</label>
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
				<div class="col-sm-6" style="background-color: #FFFFFF;">
					<label></label>
				</div>
				<div class="row">
					<div class="col-sm-5" style="background-color: #48D1CC;">
						<label>PR No</label>
					</div>
					<div class="col-sm-1"></div>
					<div class="col-sm-5" style="background-color: #48D1CC;">
						<label></label>
					</div>
				</div>
				<div class="col-sm-6" style="background-color: #FFFFFF;">
					<label></label>
				</div>
				<div class="row">
					<div class="col-sm-5" style="background-color: #DCDCDC;">
						<label>Approver 1</label>
					</div>
					<div class="col-sm-1"></div>
					<div class="col-sm-5" style="background-color: #DCDCDC;">
						<label></label>
					</div>
				</div>
				<div class="col-sm-6" style="background-color: #FFFFFF;">
					<label></label>
				</div>
				<div class="row">
					<div class="col-sm-5" style="background-color: #48D1CC;">
						<label>Approver 1 Date</label>
					</div>
					<div class="col-sm-1"></div>
					<div class="col-sm-5" style="background-color: #48D1CC;">
						<label></label>
					</div>
				</div>
				<div class="col-sm-6" style="background-color: #FFFFFF;">
					<label></label>
				</div>
				<div class="row">
					<div class="col-sm-5" style="background-color: #DCDCDC;">
						<label>Approver 2</label>
					</div>
					<div class="col-sm-1"></div>
					<div class="col-sm-5" style="background-color: #DCDCDC;">
						<label></label>
					</div>
				</div>
				<div class="col-sm-6" style="background-color: #FFFFFF;">
					<label></label>
				</div>
				<div class="row">
					<div class="col-sm-5" style="background-color: #48D1CC;">
						<label>Approver 2 Date</label>
					</div>
					<div class="col-sm-1"></div>
					<div class="col-sm-5" style="background-color: #DCDCDC;">
						<label></label>
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
			<div class="container-fluid">
				<!-- Control the column width, and how they should appear on different devices -->
				<div class="row">
					<div class="col-sm-6">
						<h3 align="center">
							<u>B.EquipmentTracer</u>
						</h3>
						<div class="row">
							<div class="col-sm-5" style="background-color: #48D1CC;">
								<label>Make</label>
							</div>
							<div class="col-sm-1"></div>
							<div class="col-sm-5" style="background-color: #48D1CC;">
								<label>${CalibMainData.mainMake}</label>
							</div>
						</div>
						<div class="col-sm-6" style="background-color: #FFFFFF;">
							<label></label>
						</div>
						<div class="row">
							<div class="col-sm-5" style="background-color: #DCDCDC;">
								<label>Instrument/GuageDescription</label>
							</div>
							<div class="col-sm-1"></div>
							<div class="col-sm-5" style="background-color: #DCDCDC;">
								<label>${CalibMainData.mainInstrumentGauge}</label>
							</div>
						</div>
						<div class="col-sm-6" style="background-color: #FFFFFF;">
							<label></label>
						</div>
						<div class="row">
							<div class="col-sm-5" style="background-color: #48D1CC;">
								<label>Model</label>
							</div>
							<div class="col-sm-1"></div>
							<div class="col-sm-5" style="background-color: #48D1CC;">
								<label>${CalibMainData.mainModel}</label>
							</div>
						</div>
						<div class="col-sm-6" style="background-color: #FFFFFF;">
							<label></label>
						</div>

						<div class="row">
							<div class="col-sm-5" style="background-color: #DCDCDC;">
								<label>Serial #</label>
							</div>
							<div class="col-sm-1"></div>
							<div class="col-sm-5" style="background-color: #DCDCDC;">
								<label>${CalibMainData.mainSerial}</label>
							</div>
						</div>
						<div class="col-sm-6" style="background-color: #FFFFFF;">
							<label></label>
						</div>
						<div class="row">
							<div class="col-sm-5" style="background-color: #48D1CC;">
								<label>Instrument/GaugeRange</label>
							</div>
							<div class="col-sm-1"></div>
							<div class="col-sm-5" style="background-color: #48D1CC;">
								<label>${CalibMainData.mainInstrumentGaugeRange}</label>
							</div>
						</div>
						<div class="col-sm-6" style="background-color: #FFFFFF;">
							<label></label>
						</div>

						<div class="row">
							<div class="col-sm-5" style="background-color: #DCDCDC;">
								<label>LeastCount/AccuracySpecification</label>
							</div>
							<div class="col-sm-1"></div>
							<div class="col-sm-5" style="background-color: #DCDCDC;">
								<label>${CalibMainData.mainLeast}</label>
							</div>
						</div>
						<div class="col-sm-6" style="background-color: #FFFFFF;">
							<label></label>
						</div>

						<div class="row">
							<div class="col-sm-5" style="background-color: #48D1CC;">
								<label>CalibrationStandardUsed</label>
							</div>
							<div class="col-sm-1"></div>
							<div class="col-sm-5" style="background-color: #48D1CC;">
								<label>${CalibMainData.mainCalibrationStandard}</label>
							</div>
						</div>
						<div class="col-sm-6" style="background-color: #FFFFFF;">
							<label></label>
						</div>
					</div>
					<div class="col-sm-6">
						<h3 align="center">
							<u>C.CertificateTracer</u>
						</h3>
						<div class="row">
							<div class="col-sm-5" style="background-color: #48D1CC;">
								<label>Instrument Location</label>
							</div>
							<div class="col-sm-1"></div>
							<div class="col-sm-5" style="background-color: #48D1CC;">
								<label>${CalibMainData.mainLocation}</label>
							</div>
						</div>
						<div class="col-sm-6" style="background-color: #FFFFFF;">
							<label></label>
						</div>
						<div class="row">
							<div class="col-sm-5" style="background-color: #DCDCDC;">
								<label>Calibration Category</label>
							</div>
							<div class="col-sm-1"></div>
							<div class="col-sm-5" style="background-color: #DCDCDC;">
								<label>${CalibMainData.mainCalibrationCategory}</label>
							</div>
						</div>
						<div class="col-sm-6" style="background-color: #FFFFFF;">
							<label></label>
						</div>
						<div class="row">
							<div class="col-sm-5" style="background-color: #48D1CC;">
								<label>CalibrationFrequency</label>
							</div>
							<div class="col-sm-1"></div>
							<div class="col-sm-5" style="background-color: #48D1CC;">
								<label>${CalibMainData.mainCalibrationFrequency}</label>
							</div>
						</div>
						<div class="col-sm-6" style="background-color: #FFFFFF;">
							<label></label>
						</div>
						<div class="row">
							<div class="col-sm-5" style="background-color: #DCDCDC;">
								<label>CalibrationAgency</label>
							</div>
							<div class="col-sm-1"></div>
							<div class="col-sm-5" style="background-color: #DCDCDC;">
								<label>${CalibMainData.mainCalibrationAgency}</label>
							</div>
						</div>
						<div class="col-sm-6" style="background-color: #FFFFFF;">
							<label></label>
						</div>
						<div class="row">
							<div class="col-sm-5" style="background-color: #48D1CC;">
								<label>CalibrationCertificate </label>
							</div>
							<div class="col-sm-1"></div>
							<div class="col-sm-5" style="background-color: #48D1CC;">
								<label>${CalibMainData.mainCalibrationCertificate}</label>
							</div>
						</div>
						<div class="col-sm-6" style="background-color: #FFFFFF;">
							<label></label>
						</div>
						<div class="row">
							<div class="col-sm-5" style="background-color: #DCDCDC;">
								<label>CertificateValidatedBy</label>
							</div>
							<div class="col-sm-1"></div>
							<div class="col-sm-5" style="background-color: #DCDCDC;">
								<label>${CalibMainData.mainCertificateValidatedBy}</label>
							</div>
						</div>
						<div class="col-sm-6" style="background-color: #FFFFFF;">
							<label></label>
						</div>
						<div class="row">
							<div class="col-sm-5" style="background-color: #48D1CC;">
								<div class="div1">
									<label>Part Code</label>
								</div>

							</div>
							<div class="col-sm-1"></div>
							<div class="col-sm-5" style="background-color: #48D1CC;">
								<label>${CalibMainData.mainPartCode}</label>
							</div>
						</div>
						<div class="col-sm-6" style="background-color: #FFFFFF;">
							<label></label>
						</div>


						<div class="col-sm-4"></div>
					</div>
					<br>
				</div>
			</div>
		</div>
	</div>

</body>

</html>