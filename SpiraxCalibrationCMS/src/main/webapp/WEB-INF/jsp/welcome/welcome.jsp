<%@page import="com.spiraxcalibration.WebConfig.AppsPropertyFile"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, height=device-height, shrink-to-fit=no initial-scale=1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.3/Chart.min.js"
	type="text/javascript">
	</script>
<style>
</style>
<!-- Bootstrap CSS CDN -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<!-- Our Custom CSS -->
<link rel="stylesheet" href="customcss/style.css">
<!-- Scrollbar Custom CSS -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.5/jquery.mCustomScrollbar.min.css">

<!-- Font Awesome JS -->
<script defer
	src="https://use.fontawesome.com/releases/v5.0.13/js/solid.js"></script>
<script defer
	src="https://use.fontawesome.com/releases/v5.0.13/js/fontawesome.js"></script>

<link rel="SHORTCUT ICON" href="favicon.ico">

<link rel="stylesheet" href="customcss/formCss.css">

<style>
tr {
	color: #FFF;
}

table {
	border-color: white;
	border: 2px;
	border-width: thin;
}

.container1 {
	width: 100%;
	margin: 0px auto;
	height: 30%;
}

* {
	box-sizing: border-box;
}
</style>

</head>
<body>
	<div class="wrapper">
		<!-- Sidebar  -->
		<nav id="sidebar">
		<div id="dismiss">
			<i class="fas fa-arrow-left"></i>
		</div>

		<div class="sidebar-header">
			<h3>
				<img class="imgeye" style="width: 130px; height: 60px; margin: 1px;"
					src="/images/Spirax-Sarco-logo.png" alt="my eye">
			</h3>
		</div>

		<ul class="list-unstyled components">
			<%
				AppsPropertyFile appsPropertyFile = new AppsPropertyFile();
				String homePageURL = appsPropertyFile.getURLForKey("homePageURL");
			%>
			<li><security:authorize
					access="hasAuthority('${adminRole}') or hasAuthority('${userRole}')  or hasAuthority('${engineerRole}') or hasAuthority('${managerRole}') or hasAuthority('${approver1Role}') or hasAuthority('${approver2Role}')">

					<a href="${homePageURL}" id="sideBardHesdingCss"><%=appsPropertyFile.getSideBarContent("Home")%></a>
				</security:authorize> <security:authorize
					access="hasAuthority('${adminRole}') or hasAuthority('${userRole}')  or hasAuthority('${engineerRole}') or hasAuthority('${managerRole}') or hasAuthority('${approver1Role}') or hasAuthority('${approver2Role}')">
					<a href="/calibrationMain" id="sideBardHesdingCss"><%=appsPropertyFile.getSideBarContent("calibrationmain")%></a>
				</security:authorize> <security:authorize
					access="hasAuthority('${adminRole}') or hasAuthority('${userRole}')  or hasAuthority('${engineerRole}') or hasAuthority('${managerRole}') or hasAuthority('${approver1Role}') or hasAuthority('${approver2Role}')">
					<a href="/products_list" id="sideBardHesdingCss"><%=appsPropertyFile.getSideBarContent("masterView")%></a>
				</security:authorize>
			<li><security:authorize
					access="hasAuthority('${adminRole}') or hasAuthority('${userRole}')  or hasAuthority('${engineerRole}') or hasAuthority('${managerRole}') or hasAuthority('${approver1Role}') or hasAuthority('${approver2Role}')">
					<a href="/supplierList" id="sideBardHesdingCss"><%=appsPropertyFile.getSideBarContent("SupplierDetails")%></a>
				</security:authorize> <security:authorize
					access="hasAuthority('${adminRole}') or hasAuthority('${userRole}')  or hasAuthority('${engineerRole}') or hasAuthority('${managerRole}') or hasAuthority('${approver1Role}') or hasAuthority('${approver2Role}') or hasAuthority('${manufacture_associatesRole}')">
					<a href="/scanText" id="sideBardHesdingCss"><%=appsPropertyFile.getSideBarContent("ValidScanIdentityProduct")%>
					</a>
				</security:authorize> <security:authorize
					access="hasAuthority('${adminRole}') or hasAuthority('${userRole}')  or hasAuthority('${engineerRole}') or hasAuthority('${managerRole}') or hasAuthority('${approver1Role}') or hasAuthority('${approver2Role}')">
					<li><a href="#page" data-toggle="collapse"
						aria-expanded="false" class="dropdown-toggle"
						id="sideBardHesdingCss"><%=appsPropertyFile.getSideBarContent("PurchaseRequisitionProcessing")%></a>
						<ul class="collapse list-unstyled" id="page">
							<li><a href="/purchaseRequisitionList"
								id="sideBardHesdingCss"><%=appsPropertyFile.getSideBarContent("PRProcess")%></a></li>
							<li><a href="/viewApproverPr" id="sideBardHesdingCss"><%=appsPropertyFile.getSideBarContent("view_ApproverPR")%></a></li>

						</ul></li>
				</security:authorize> <security:authorize access="hasAuthority('${adminRole}')">
					<li><a href="#page2" data-toggle="collapse"
						aria-expanded="false" class="dropdown-toggle"
						id="sideBardHesdingCss"><%=appsPropertyFile.getSideBarContent("Administration")%></a>
						<ul class="collapse list-unstyled" id="page2">
							<li><a href="/lovMaintenance" id="sideBardHesdingCss"><%=appsPropertyFile.getSideBarContent("lovMaintenance")%></a></li>
							<li><a href="/userMaintenance" id="sideBardHesdingCss"><%=appsPropertyFile.getSideBarContent("Users")%></a></li>
							<li><a href="/register_Email_For_Notification" id="sideBardHesdingCss"><%=appsPropertyFile.getSideBarContent("Register_Mail")%></a></li>
						</ul></li>
				</security:authorize>
		</ul>
		</nav>
		<!-- Page Content  -->
		<div id="content">
			<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<div class="container-fluid">
				<button type="button" id="sidebarCollapse" class="btn btn-info"
					style="background-color: #20B2AA;">
					<i class="fas fa-align-left"></i> <span
						style="font-size: 16px; color: white; font-family: Cambria; font-weight: bold;">Main
						Menu</span>
				</button>
				<div>
					<h4 id="welcomeCss">
						Welcome : <b style="font-size: 20px; color: #20B2AA;"> <security:authentication
								property="principal.username" />
						</b>
					</h4>
					<div align="right">
						<form:form method="post"
							action="${pageContext.request.contextPath}/logout" id="nameform">

							<input type="submit" value="Logout" class="btn btn-default"
								id="loginbtnCss" />

						</form:form>
					</div>
				</div>
			</div>
			</nav>
			<div class="container-fluid">
				<h4 align="center" id="formtitle">Dashboard</h4>
				<div class="row" id="searchboxCss">
					<div class="col-sm-6">
						<div style="overflow-x: auto;">
							<div class="container1">
								<canvas id="plannedchart"></canvas>
								<!--  
								<table border="3" bgcolor="#20B2AA">
									<tr>
										<th>MONTH</th>
										<th>JAN</th>
										<th>FEB</th>
										<th>MAR</th>
										<th>APR</th>
										<th>MAY</th>
										<th>JUN</th>
										<th>JUL</th>
										<th>AUG</th>
										<th>SEP</th>
										<th>OCT</th>
										<th>NOV</th>
										<th>DEC</th>
									</tr>
									<tr>
										<td style="font-size: 14px;">Current Year
											(${PlanYear})-Plan</td>
										<c:forEach items="${instrumentCountCurrentNextYearDataList}"
											var="currentYearscrapped">
											<td>${currentYearscrapped}</td>
										</c:forEach>
									</tr>

									<tr>
										<td style="font-size: 14px;">Current Year
											(${ActualYear})-Actual</td>
										<c:forEach items="${instrumentCountCurrentYearActualDataList}"
											var="scrappedInstrumentPriorYear">
											<td>${scrappedInstrumentPriorYear}</td>
										</c:forEach>
									</tr>
								</table>
-->
							</div>
						</div>

					</div>

					<script>
				var ctx = document.getElementById("plannedchart").getContext('2d');
				var myChart1 = new Chart(ctx, {
					//responsive: true,
					maintainAspectRatio : true,
					type : 'bar',
					data : {
						labels : [ 'JAN', 'FEB', 'MAR', 'APR', 'MAY', 'JUN','JUL', 'AUG', 'SEP', 'OCT', 'NOV', 'DEC'],
								 
						datasets : [ {
							label : 'PLAN'+'-'+${PlanYear},
							data : ${instrumentCountCurrentNextYearDataList},
							backgroundColor : "#228B22"

						}, {
							label : 'ACTUAL'+'-'+${ActualYear},
							data : ${instrumentCountCurrentYearActualDataList},
							backgroundColor : "brown"
							
						} ]
					},
					options : {
						title : {
							display : true,
							fontSize : 16,
							text : 'Instrument Calibration Plan vs Actual',
						    fontColor: "black",
						    fontWeight: "bold",
						    fontFamily: "Cambria",  
						    responsive: true,
						    maintainAspectRatio: false
						},
						scales : {
							yAxes : [ {
								ticks : {
									beginAtZero : true,
							        stepValue:5,
					                fontSize: 12


								}, gridLines: {
							          drawOnChartArea: false
						        }
							}
							
							],
							 xAxes: [{
								 
								 ticks: {
					                    fontSize: 12,
									    fontFamily: "Cambria" 

					                   },
							        gridLines: {
							          drawOnChartArea: false
							        }
							      }]
						}
					}, legend: {
						position: "none"
				      },
				      plugins: {
				            afterDatasetsDraw: function (context, easing) {
				              var ctx = context.chart.ctx;
				              context.data.datasets.forEach(function (dataset) {
				                for (var i = 0; i < dataset.data.length; i++) {
				                  if (dataset.data[i] != 0) {
				                    var model = dataset._meta[Object.keys(dataset._meta)[0]].data[i]._model;
				                    var textY = model.y + (dataset.type == "line" ? -3 : 13);

				                    ctx.font = Chart.helpers.fontString(Chart.defaults.global.defaultFontSize, 'normal', Chart.defaults.global.defaultFontFamily);
				                    ctx.textAlign = 'start';
				                    ctx.textBaseline = 'middle';
				                    ctx.fillStyle = dataset.type == "line" ? "black" : "black";
				                    ctx.save();
				                    ctx.translate(model.x, textY-15);
				                    ctx.rotate(4.7);
				                    ctx.fillText(dataset.data[i], 0, 0);
				                    ctx.restore();
				                  }
				                }
				              });
				            }
				          
				        }
				});
				
				ctx.aspectRatio = 0;

			</script>
					<div class="col-sm-6" id="searchboxCss">
						<div style="overflow-x: auto;">
							<div class="container1">
								<canvas id="myChart1"></canvas>
								<!--  
								<table border="1" bgcolor="#20B2AA">
									<tr>
										<th>MONTH</th>
										<th>JAN</th>
										<th>FEB</th>
										<th>MAR</th>
										<th>APR</th>
										<th>MAY</th>
										<th>JUN</th>
										<th>JUL</th>
										<th>AUG</th>
										<th>SEP</th>
										<th>OCT</th>
										<th>NOV</th>
										<th>DEC</th>
									</tr>
									<tr>
										<td>Year-Current (${currentYear})</td>
										<c:forEach items="${scrappedInstrumentCurrentYear}"
											var="currentYearscrapped">
											<td>${currentYearscrapped}</td>
										</c:forEach>
									</tr>

									<tr>
										<td>Year-Prior (${forcastYear})</td>
										<c:forEach items="${scrappedInstrumentPriorYear}"
											var="scrappedInstrumentPriorYear">
											<td>${scrappedInstrumentPriorYear}</td>
										</c:forEach>
									</tr>
								</table>
-->
							</div>
						</div>

					</div>

					<script>
				var ctx = document.getElementById("myChart1").getContext('2d');
				var myChart1 = new Chart(ctx, {
					//responsive: true,
					maintainAspectRatio : true,
					type : 'bar',
					data : {
						labels : [ 'JAN', 'FEB', 'MAR', 'APR', 'MAY', 'JUN',
								'JUL', 'AUG', 'SEP', 'OCT', 'NOV', 'DEC' ],
						datasets : [ {
							label : 'YEAR CURRENT'+'-'+${currentYear},

							data : ${scrappedInstrumentCurrentYear},

							backgroundColor : "#228B22"
						}, {
							label : 'YEAR PRIOR'+'-'+${forcastYear},

							data : ${scrappedInstrumentPriorYear},

							backgroundColor : "brown"
						} ]
					},
					options : {
						title : {
							display : true,
							fontSize : 16,
							text : 'Instrument Scraped',
							    fontColor: "black",
							    fontWeight: "bold",
							    fontFamily: "Cambria",
						},
						scales : {
							yAxes : [ {
								ticks : {
									beginAtZero : true,
				                    fontSize: 12

								}, gridLines: {
							          drawOnChartArea: false
						        }
							} ],xAxes: [{
								
								 ticks: {
					                    fontSize: 12,
									    fontFamily: "Cambria" 

					                   },
						        gridLines: {
							          drawOnChartArea: false
							        }
							      }]
						}
					}, plugins: {
				          
			            afterDatasetsDraw: function (context, easing) {
			              var ctx = context.chart.ctx;
			              context.data.datasets.forEach(function (dataset) {
			                for (var i = 0; i < dataset.data.length; i++) {
			                  if (dataset.data[i] != 0) {
			                    var model = dataset._meta[Object.keys(dataset._meta)[0]].data[i]._model;
			                    var textY = model.y + (dataset.type == "line" ? -3 : 13);

			                    ctx.font = Chart.helpers.fontString(Chart.defaults.global.defaultFontSize, 'normal', Chart.defaults.global.defaultFontFamily);
			                    ctx.textAlign = 'start';
			                    ctx.textBaseline = 'middle';
			                    ctx.fillStyle = dataset.type == "line" ? "black" : "black";
			                    ctx.save();
			                    ctx.translate(model.x, textY-15);
			                    ctx.rotate(4.7);
			                    ctx.fillText(dataset.data[i], 0, 0);
			                    ctx.restore();
			                  }
			                }
			              });
			            }
			          
			        }
				});
			</script>
				</div>
				<div class="row" style="text-align: center;" id="searchboxCss">
					<div class="col-sm-3"></div>
					<div class="col-sm-6">
						<div style="overflow-x: auto;">
							<div class="container1">
								<canvas id="myChart"></canvas>
								<!--  
								<table border="1" bgcolor="#20B2AA">
									<tr class="forecast">
										<th>MONTH</th>
										<th>JAN</th>
										<th>FEB</th>
										<th>MAR</th>
										<th>APR</th>
										<th>MAY</th>
										<th>JUN</th>
										<th>JUL</th>
										<th>AUG</th>
										<th>SEP</th>
										<th>OCT</th>
										<th>NOV</th>
										<th>DEC</th>
									</tr>
									<tr>
										<td>Year-Current (${currentYear})</td>
										<c:forEach items="${currentYearCostPrice}"
											var="currentYearCost">
											<td>${currentYearCost}</td>
										</c:forEach>
									</tr>
									<tr>
										<td>Year-forecast (${forcastYear})</td>
										<c:forEach items="${forcastYearPrice}" var="forcastYearPrice">
											<td>${forcastYearPrice}</td>
										</c:forEach>
									</tr>
								</table>
								
								-->
							</div>
						</div>
					</div>
					<div class="col-sm-3"></div>


				</div>
				<script>
				var ctx = document.getElementById("myChart").getContext('2d');
				var myChart = new Chart(ctx, {
					//responsive: true,
					maintainAspectRatio : true,
					type : 'bar',
					data : {
						labels : [ 'JAN', 'FEB', 'MAR', 'APR', 'MAY', 'JUN',
								'JUL', 'AUG', 'SEP', 'OCT', 'NOV', 'DEC' ],
						datasets : [
								{
									label : 'YEAR CURRENT'+'-'+${currentYear},

									data : ${currentYearCostPrice},

									backgroundColor : "#228B22"
								},
								{
									label : 'YEAR FORECAST'+'-'+${forcastYear},

									data : ${forcastYearPrice},

									backgroundColor : "brown"
								} ]
					},
					options : {
						title : {
							display : true,
							fontSize : 16,
							text : 'Calibration Costing Forecast',
							    fontColor: "black",
							    fontWeight: "bold",
							    fontFamily: "Cambria",						
						},
						scales : {
							yAxes : [ {
								ticks : {
									beginAtZero : true,
	                            //    stepValue: 6000,
	                                max: 1000000,
				                    fontSize: 12


								}, gridLines: {
							          drawOnChartArea: false
						        }
							} ],xAxes: [{
								
								 ticks: {
					                    fontSize: 12,
									    fontFamily: "Cambria" 

					                   },
						        gridLines: {
							          drawOnChartArea: false
							        }
							      }]
						}
					}, plugins: {
				          
			            afterDatasetsDraw: function (context, easing) {
			              var ctx = context.chart.ctx;
			              context.data.datasets.forEach(function (dataset) {
			                for (var i = 0; i < dataset.data.length; i++) {
			                  if (dataset.data[i] != 0) {
			                    var model = dataset._meta[Object.keys(dataset._meta)[0]].data[i]._model;
			                    var textY = model.y + (dataset.type == "line" ? -3 : 13);

			                    ctx.font = Chart.helpers.fontString(Chart.defaults.global.defaultFontSize, 'normal', Chart.defaults.global.defaultFontFamily);
			                    ctx.textAlign = 'start';
			                    ctx.textBaseline = 'middle';
			                    ctx.fillStyle = dataset.type == "line" ? "black" : "black";
			                    ctx.save();
			                    ctx.translate(model.x, textY-15);
			                    ctx.rotate(4.7);
			                    ctx.fillText(dataset.data[i], 0, 0);
			                    ctx.restore();
			                  }
			                }
			              });
			            }
			          
			        }
				});
			</script>

			</div>
		</div>
	</div>

	<div class="overlay"></div>

	<!-- jQuery CDN - Slim version (=without AJAX) -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
	<!-- Popper.JS -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
	<!-- Bootstrap JS -->
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
	<!-- jQuery Custom Scroller CDN -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.5/jquery.mCustomScrollbar.concat.min.js"></script>

	<script type="text/javascript">
		$(document).ready(function() {
			$("#sidebar").mCustomScrollbar({
				theme : "minimal"
			});

			$('#dismiss, .overlay').on('click', function() {
				$('#sidebar').removeClass('active');
				$('.overlay').removeClass('active');
			});

			$('#sidebarCollapse').on('click', function() {
				$('#sidebar').addClass('active');
				$('.overlay').addClass('active');
				$('.collapse.in').toggleClass('in');
				$('a[aria-expanded=true]').attr('aria-expanded', 'false');
			});
		});
	</script>
</body>

</html>