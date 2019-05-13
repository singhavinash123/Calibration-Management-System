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

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
</head>

<body>
	<table class="purchase" border="1">
		<thead>
			<tr>
				<th style="width: 90%"><h3>Purchase
						Requisition</h3></th>

				<th style="width: 10%"><img class="imgeye"
					style="width: 180px; height: 99px;" src="/images/unnamed.png"
					alt="my eye">
				</th>
			</tr>

			<tr>

			</tr>
			<tr>
			<td>
			<td>
			
			<table align="left" border ="1" >
			<tr>
							<td width="60%" ><strong>PurchaseRequisitionNo.</strong></td>
				<td><strong>Auto</strong></td>
				</tr>
			<tr>
				<td><strong>Date</strong></td>
				<td><strong>Auto</strong></td>
			</tr>
			<tr>
				<td><strong>Purchase Order No.</strong></td>
				<td><strong>Blank</strong></td>
			</tr>
			
			
			</table>
			
		</tr>	
	</table>

</body>
</html>