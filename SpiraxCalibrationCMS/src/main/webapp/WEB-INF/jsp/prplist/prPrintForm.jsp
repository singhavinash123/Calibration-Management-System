<%@page import="com.spiraxcalibration.models.CalibMainData"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="com.spiraxcalibration.controllers.PrController"%>
<%@page import="com.spiraxcalibration.WebConfig.AppsPropertyFile"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Bootstrap CSS CDN -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">

<!-- Scrollbar Custom CSS -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.5/jquery.mCustomScrollbar.min.css">

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>

<script src="bootstrap/js/jspdf.js"></script>
<script src="bootstrap/js/jquery-2.1.3.js"></script>
<script src="bootstrap/js/pdfFromHTML.js"></script>

<!-- Font Awesome JS -->
<script defer
	src="https://use.fontawesome.com/releases/v5.0.13/js/solid.js"></script>

<script defer
	src="https://use.fontawesome.com/releases/v5.0.13/js/fontawesome.js"></script>

<script type="text/javascript" src="bootstrap/js/jquery-3.3.1.js"></script>
<title>Insert title here</title>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.2/jspdf.min.js"></script>
<script>
	function demoFromHTML() {
		var pdf = new jsPDF('p', 'pt', 'letter');
		pdf.canvas.height = 72 * 11;
		pdf.canvas.width = 72 * 8.5;
		pdf.fromHTML(document.body);
		pdf.save('test.pdf');
	};

	var element = document.getElementById("data");
	element.addEventListener("click", demoFromHTML);
</script>
<script>
	function printLayer(data) {
		console.log("hello layer Qr ::" + data);
		var restorepage = document.body.innerHTML;
		var printcontent = document.getElementById(data).innerHTML;
		document.body.innerHTML = printcontent;
		window.print();
		document.body.innerHTML = restorepage;
	};

	<!--
	function readfile(val) {
		if (document.getElementById("uploadBox").value != "") {
			document.getElementById("approver2submit").style.display = 'block';
		} else {
			document.getElementById("approver2submit").style.display = 'none';
		}
	};

	window.onload = function() {
		var element = document.getElementById('data').innerHTML;
		console.log(element);
		document.getElementById("approver2submit").style.display = 'none';

	};
// -->
</script>
<style type="text/css">
/* Basic styling for root. */
#data {
	width: 600px;
	background-color: white;
	border: 2px solid black;
	padding: 0px;
	margin: 0 auto;
}

#contentFontColorCss{
	color: blue;
}


#wrapTextCss{
	color: blue;
}

</style>
</head>

<body>
	<div id="header">
		<jsp:include page="/WEB-INF/jsp/header/header.jsp" />
	</div>
	<div align="center">
		<h3 id="formtitle">View And Approve Purchase Requisition</h3>
	</div>
	<span id="hereCss">You are here : </span>
	<%
		AppsPropertyFile appsPropertyFile = new AppsPropertyFile();
	%>
	<span><a href=<%=appsPropertyFile.getURLForKey("homePageURL")%>
		id="hrefCss">Home</a></span>
	<span>/</span>
	<span><a href=<%=""%>><h id="hrefCss">approverPR</h></a></span>
	<br>
	<br>
	<div align="center">
		<b id="successMsgCss">${msg}</b>
	</div>

	<div class="container">
		<div class="row" id="searchboxCss">
			<div class="col-sm-3">
				<label for="usr">PR Number :</label> <input type="text"
					readonly="readonly" value="${pRPSingleDataObject.pRpPrNumber}"
					class="form-control" name="prpId">
			</div>
			<div class="col-sm-3"></div>
			<div class="col-sm-3"></div>
		</div>

		<form action="/save_Approval1" method="post">
			<input type="hidden" readonly="readonly"
				value="${pRPSingleDataObject.pRpPRId}" class="form-control"
				name="prpId"> <input type="hidden" readonly="readonly"
				value="${pRPSingleDataObject.pRpApprover2Name}" class="form-control"
				name="aprover2mailid"> <input type="hidden"
				readonly="readonly" value="${pRPSingleDataObject.pRpPrNumber}"
				class="form-control" name="prNumber">


			<div class="row" id="searchboxCss">
				<div class="col-sm-3">
					<div class="form-group">
						<label for="usr">Approver1 :</label> <input type="text"
							readonly="readonly" class="form-control"
							value="${pRPSingleDataObject.pRpApprover1Name}"
							name="approver1name" />
					</div>
				</div>

				<div class="col-sm-3">
					<div class="form-group">
						<label for="usr">Approval1 Status :</label> <select
							class="form-control" name="approver1status" style="height: 35px;">
							<c:choose>
								<c:when test="${pRPSingleDataObject.pRpApprover1Status != null}">
									<option value="${pRPSingleDataObject.pRpApprover1Status}"
										selected>${pRPSingleDataObject.pRpApprover1Status}</option>
									<option value="Pending">Pending</option>
									<option value="Approved">Approved</option>
									<option value="Rejected">Rejected</option>
								</c:when>
							</c:choose>
						</select>
					</div>
				</div>
				<div class="col-sm-3">
					<div class="form-group">
						<label for="usr">Approval1 Comments :</label> <input
							placeholder="Enter Comments" class="form-control"
							name="approver1comment"
							value="${pRPSingleDataObject.pRpApprover1Comments}" />
					</div>
				</div>
				
			<div class="col-sm-3" id="searchBtnCss">
			<br>
				<security:authorize var="loggedIn"
					access="hasAuthority('PR_APPROVER1')">
					<div class="row">
						<div class="col-sm-6">
							<c:choose>
								<c:when test="${loggedIn}">
									<input type="submit" value="Submit" class="btn btn-success" />
								</c:when>
								<c:otherwise>
									<security:authorize var="loggedIn"
										access="hasAuthority('PR_APPROVER2')">
										<input type="submit" value="Approve" class="btn btn-success"
											disabled="disabled" />
									</security:authorize>
								</c:otherwise>
							</c:choose>
						</div>
						<div class="col-sm-6">
							<div>
								<a
									href="/uploadOrDownloadcrtificate/${pRPSingleDataObject.pRpCalibId}"
									class="btn"
									style="background-color: #ADD8E6; border-radius: 12px;"
									target="_blank">View Quotation</a>
							</div>
						</div>
					</div>
				</security:authorize>
				</div>
			</div>
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />

		</form>

		<form:form action="/save_Approval2" enctype="multipart/form-data"
			method="post">
			<input type="hidden" readonly="readonly"
				value="${pRPSingleDataObject.pRpPRId}" class="form-control"
				name="prpId">
			<input type="hidden" readonly="readonly"
				value="${pRPSingleDataObject.pRpApprover1Name}" class="form-control"
				name="aprover2mailid">
			<input type="hidden" readonly="readonly"
				value="${pRPSingleDataObject.pRpSupplierId}" class="form-control"
				name="supplierID">

			<input type="hidden" readonly="readonly"
				value="${pRPSingleDataObject.pRpPrNumber}" class="form-control"
				name="prNumber">

			<div class="row" id="searchboxCss">
				<div class="col-sm-3">
					<div class="form-group">
						<label for="usr">Approver2 :</label> <input type="text"
							readonly="readonly" class="form-control"
							value="${pRPSingleDataObject.pRpApprover2Name}"
							name="approver2name" />
					</div>
				</div>

				<div class="col-sm-3">
					<div class="form-group">
						<label for="usr">Approval2 Status :</label> <select
							class="form-control" name="approver2status" style="height: 35px;">
							<c:choose>
								<c:when test="${pRPSingleDataObject.pRpApprover2Status != null}">
									<option value="${pRPSingleDataObject.pRpApprover2Status}"
										selected>${pRPSingleDataObject.pRpApprover2Status}</option>
									<option value="Approved">Approved</option>
									<option value="Rejected">Rejected</option>
								</c:when>
							</c:choose>
						</select>
					</div>
				</div>
				<div class="col-sm-3">
					<div class="form-group">
						<label for="usr">Approval2 Comments :</label> <input
							placeholder="Enter Comments" class="form-control"
							name="approver2comment"
							value="${pRPSingleDataObject.pRpApprover2Comments}" />
					</div>
				</div>
				<div class="col-sm-3" id="searchBtnCss">
					<br>
					<security:authorize var="loggedIn"
						access="hasAuthority('PR_APPROVER2')">
						<!-- 
						<br>
						<input type="file" name="attachedFile" id="uploadBox" size="50"
							onchange="readfile(this)">
						<br>
						
						<c:choose>
							<c:when test="${loggedIn}">
								<input type="submit" value="Approve" id="approver2submit"
									class="btn btn-success" />
							</c:when>
							<c:otherwise>
								<security:authorize access="hasAuthority('PR_APPROVER1')">
									<input type="submit" value="Approve" id="approver2submit"
										class="btn btn-success" disabled="disabled" />
								</security:authorize>
							</c:otherwise>
						</c:choose>
						 -->
						<div class="row">
							<br>
							<div class="col-sm-6">
								<c:choose>
									<c:when test="${loggedIn}">
										<input type="submit" value="Submit" class="btn btn-success" />
									</c:when>
									<c:otherwise>
										<security:authorize var="loggedIn"
											access="hasAuthority('PR_APPROVER1')">
											<input type="submit" value="Approve" class="btn btn-success"
												disabled="disabled" />
										</security:authorize>
									</c:otherwise>
								</c:choose>
							</div>
							<div class="col-sm-6">
								<div>
									<a
										href="/uploadOrDownloadcrtificate/${pRPSingleDataObject.pRpCalibId}"
										class="btn"
										style="background-color: #ADD8E6; border-radius: 12px;"
										target="_blank">View Quotation</a>
								</div>
							</div>
						</div>
					</security:authorize>
				</div>
			</div>
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form:form>
	</div>
	<br>

	<div id="data">
		<table border="1" id="searchboxCss">
			<thead>
				<tr>
					<th style="width: 90%"><h3 align="center">Purchase Requisition</h3></th>
					<th style="width: 10%; align: center;"><img class="imgeye"
						style="width: 250px; height: 70px; margin: 1px;"
						src="/images/unnamed.png" alt="my eye"></th>
				</tr>
				<tr>
				</tr>
				<tr>
					<td style="border-bottom: 2px solid white;">
					<td style="border-bottom: 2px solid white;">
						<table align="left" border="1"
							style="width: 300px; text-align: center;">
							<tr>
								<td width="50%" style="white-space: nowrap;"><strong>Purchase Requisition No.</strong></td>
								<td align="center" id="contentFontColorCss" style="white-space: nowrap;">${pRPSingleDataObject.pRpPrNumber}</td>
							</tr>
							<tr>
								<td><strong>Date</strong></td>
								<td align="center" id="contentFontColorCss">${pRPSingleDataObject.pRpGeneratePrDate}</td>
							</tr>
							<tr>
								<td style="white-space: nowrap;"><strong>Purchase Order No.</strong></td>
								<td align="center" id="contentFontColorCss"><strong>-</strong></td>
							</tr>
						</table>
				</tr>
			</thead>
		</table>
		<br>
		<table rules="rows" class="purchasenum1"
			style="width: 100%; border: 1px solid black; text-align: center;"
			id="searchboxCss">
			<thead>
				<tr>
					<th height="10px" style="width: 20%; white-space: nowrap;" ><strong>Name of
							the department</strong></th>
					<th class="div1" height="30px" width=20%>:</th>
					<th height="30px" style="width: 80%; border: 1px solid black;"
						align="center" id="contentFontColorCss">Quality Functions</th>
				</tr>
				<tr>
					<th height="30px" style="width: 20%; white-space: nowrap;"><strong>To be
							used(for a machine)</strong></th>
					<th class="div1" height="30px" width=20%>:</th>
					<th height="30px" style="width: 80%; border: 1px solid black"
						align="center" id="contentFontColorCss">Periodical Calibrations</th>
				</tr>
			</thead>
		</table>

		<br>
		<table class="purchasenum5" id="searchboxCss">
			<tfoot>
				<tr>
					<th colspan="10"
						style="text-align: left; height: 40px; padding: 10px; width: 10%;">Kindly
						approve and process procurement of following material(s):</th>
				</tr>
			</tfoot>
		</table>
		<table border="1" class="purchasenum6"
			style="width: 100%; margin-top: -4px; border-collapse: collapse;"
			id="searchboxCss">
			<tr>
				<th style="width: 7%;"><strong>Sr. No</strong></th>
				<th style="width: 17%;"><strong>Part Code(If Any)</strong></th>
				<th style="width: 40%;"><strong>Product Description</strong></th>
				<th style="width: 3%;"><strong>Quantity</strong></th>
				<th style="width: 10%;"><strong>Budgetry Cost</strong></th>
				<th style="width: 55%;"><strong>Delivery</strong></th>
			</tr>
			<%
				int i = 0;
			%>
			<c:set var="total" value="${0}" />
			<c:forEach items="${pRPData}" var="pRPData">
				<tr>
					<td align="center" id="contentFontColorCss"><strong><%=++i%></strong></td>
					<td align="center" id="contentFontColorCss"><strong>${pRPData.pRpPartCode}</strong></td>
					<td align="center" id="contentFontColorCss">${pRPData.pRpEquipmentDescription}</td>
					<td align="center" id="contentFontColorCss">1</td>
					<td align="center" id="contentFontColorCss"><strong>${pRPData.pRpBudgetryCost}</strong></td>
					<td align="center" id="contentFontColorCss"><strong>${pRPData.pRpDeliveryDate}</strong></td>
				</tr>
				<c:set var="total" value="${total + pRPData.pRpBudgetryCost}" />
			</c:forEach>
			<tr>
				<td colspan="4">Total Budgetry Cost :</td>
				<td colspan="2" id="contentFontColorCss">${total}</td>
			</tr>
		</table>

		<br>
		<table rules="rows" class="purchasenum1"
			style="width: 100%; border: 1px solid black; text-align: center;"
			id="searchboxCss">
			<thead>
				<tr>
					<th height="10px" style="width: 20%; white-space: nowrap;"><strong>Purchase
							Justification</strong></th>
					<th class="div1" height="30px" width=20%>:</th>
					<th height="30px"
						style="width: 80%; border: 1px solid black; align: center" id="contentFontColorCss"><strong>Periodic
							Calibration</strong></th>
				</tr>
				<tr>
					<th height="30px" style="white-space: nowrap;"><strong>Budgetry Quotation</strong></th>
					<th class="div1" height="30px">:</th>
					<th height="30px" style="border: 1px solid black;" id="contentFontColorCss">Yes</th>
				</tr>

				<tr>
					<th height="30px" style="width: 20%;white-space: nowrap;"><strong>Suggested/Preferred
							Supplier</strong></th>
					<th class="div1" height="30px" width=20%>:</th>
					<th height="30px" style="width: 80%; border: 1px solid black" id="contentFontColorCss">${pRPSingleDataObject.pRpSupplierName}</th>
				</tr>
		</table>

		<br>
		<table border="1" class="purchasenum10"
			style="width: 100%; margin-top: -4px; border-collapse: collapse; text-align: center"
			id="searchboxCss">
			<tr>
				<th style="padding: 30px; width: 33%;"><strong>Requested
						By(Name,E.code & Signature)</strong></th>
				<th style="width: 33%;"><strong>Approved
						By(Name,E.code & Signature)Head Engineering Quality</strong></th>
				<th style="width: 33%;"><strong>Approved
						By(Name,E.code & Signature)Head Operations</strong></th>
			</tr>
			<tr>
				<td style="padding: 20px; width: 33%;" id="contentFontColorCss">${pRPSingleDataObject.pRpRaisedPrFullName}</td>
				<td style="padding: 20px; width: 33%;" id="contentFontColorCss">${pRPSingleDataObject.pRpApprover1FullName}</td>
				<security:authorize access="hasAuthority('PR_APPROVER2')">
					<td style="padding: 20px; width: 33%;" id="contentFontColorCss">${pRPSingleDataObject.pRpApprover2FullName}</td>
				</security:authorize>
			</tr>
		</table>
	</div>
	<br>
	<div class="row">
		<div class="col-sm-6"></div>
		<div class="col-sm-4">
			<button type="button" onclick="printLayer('data')"
				class="btn btn-primary btn-sm" id="searchBtnCss">Print</button>
			<button onclick="test()" class="btn btn-primary btn-sm"
				id="searchBtnCss">Generate PDF</button>

		</div>

		<div class="col-sm-2"></div>
		<br>
	</div>
	<br>
	<script
		src="https://rawgit.com/eKoopmans/html2pdf/master/dist/html2pdf.bundle.min.js"></script>

	<script>
		function test() {
			// Get the element.
			var element = document.getElementById('data');
			// Generate the PDF.
			html2pdf().from(element).set({
				margin : 1,
				filename : 'Purchase_Requisition_Form.pdf',
				html2canvas : {
					scale : 2
				},
				jsPDF : {
					orientation : 'portrait',
					unit : 'in',
					format : 'letter',
					compressPDF : true
				}
			}).save();
		}
	</script>
</body>
</html>