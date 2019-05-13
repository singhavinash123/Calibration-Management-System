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
<meta name="viewport"
	content="width=device-width, height=device-height, shrink-to-fit=no initial-scale=1">
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

.selected
{
  background-color:red;
}

</style>
<script>
	function changeGraph(value){
		if(value == "1"){
			document.getElementById("scrapeChart").style.display = 'none';
			document.getElementById("forecastChart").style.display = 'none';
			document.getElementById("plannedchart").style.display = 'block';
			
			document.getElementById("planCss").style.background = '#20B2AA';
			document.getElementById("forecastCss").style.background = 'white';
			document.getElementById("scrapCss").style.background = 'white';

		}if(value == "2"){
			document.getElementById("plannedchart").style.display = 'none';
			document.getElementById("forecastChart").style.display = 'none';
			document.getElementById("scrapeChart").style.display = 'block';
			
			document.getElementById("scrapCss").style.background = '#20B2AA';
			
			document.getElementById("forecastCss").style.background = 'white';
			document.getElementById("planCss").style.background = 'white';

		}if(value == "3"){
			document.getElementById("plannedchart").style.display = 'none';
			document.getElementById("scrapeChart").style.display = 'none';
			document.getElementById("forecastChart").style.display = 'block';
			
			document.getElementById("forecastCss").style.background = '#20B2AA';
			
			document.getElementById("planCss").style.background = 'white';
			document.getElementById("scrapCss").style.background = 'white';

		}if(value == "0"){
			document.getElementById("plannedchart").style.display = 'block';
			document.getElementById("scrapeChart").style.display = 'block';
			document.getElementById("forecastChart").style.display = 'block';
		}
	};
	
    window.onload = function() {
		document.getElementById("plannedchart").style.display = 'block';
		document.getElementById("forecastChart").style.display = 'none';
		document.getElementById("scrapeChart").style.display = 'none';
		
		document.getElementById("planCss").style.background = '#20B2AA';
    }
		
	
</script>
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
				</security:authorize> 
				
			<security:authorize
					access="hasAuthority('${adminRole}') or hasAuthority('${userRole}')  or hasAuthority('${engineerRole}') or hasAuthority('${managerRole}') or hasAuthority('${approver1Role}') or hasAuthority('${approver2Role}') or hasAuthority('${manufacture_associatesRole}')">
					<a href="/calibration_tutorial" id="sideBardHesdingCss"><%=appsPropertyFile.getSideBarContent("videotutorial")%>
					</a>
				</security:authorize>
				
				<security:authorize
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
							<%-- <li><a href="/register_Email_For_Notification"
								id="sideBardHesdingCss"><%=appsPropertyFile.getSideBarContent("Register_Mail")%></a></li> --%>
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
				<div class="row">
					<nav class="col-md-2 d-none d-md-block bg-light sidebar">

					<div class="sidebar-sticky">
						<ul class="nav flex-column">
						
						<li class="nav-item"><a class="nav-link"
								href="javascript:changeGraph(1);" style="font-family: Cambria;font-weight: bold;"> <span
									data-feather="bar-chart-2"><h2>Dashboard</h2></span>
							</a>
							</li>
							<li class="nav-item"><a class="nav-link" id="planCss"
								href="javascript:changeGraph(1);" style="font-family: Cambria;font-weight: bold;"> <span
									data-feather="bar-chart-2">Plan vs Actual</span>
							</a>
							</li>

							<li class="nav-item"><a class="nav-link" id="scrapCss"
								href="javascript:changeGraph(2);" style="font-family: Cambria;font-weight: bold;"> <span
									data-feather="bar-chart-2"></span> Instrument Scraped
							</a></li>

							<li class="nav-item"><a class="nav-link" id="forecastCss"
								href="javascript:changeGraph(3);" style="font-family: Cambria;font-weight: bold;"> <span
									data-feather="bar-chart-2"></span> Costing Forecast
							</a></li>
						</ul>
					</div>
					</nav>
					<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
					<!-- <div
						class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
						<div style="text-align: center;">
 						<h1 class="h3" style="font-family: Cambria;font-weight: bold;">Dashboard</h1>
						</div>
						<div class="btn-toolbar mb-2 mb-md-0">
						

							 <div class="btn-group mr-2">
            <button type="button" class="btn btn-sm btn-outline-secondary">Share</button>
            <button type="button" class="btn btn-sm btn-outline-secondary">Export</button>
          </div>
          
          <button type="button" class="btn btn-sm btn-outline-secondary dropdown-toggle">
            <span data-feather="calendar"></span>
            This week
          </button>

						</div>
					</div> -->
					<canvas id="plannedchart" class="my-4 w-100" id="myChart"
						width="1000" height="430"></canvas> 
						<canvas id="scrapeChart"
	class="my-4 w-100" id="myChart" width="1000" height="430"></canvas>
					<canvas id="forecastChart" class="my-4 w-100" id="myChart"
						width="1000" height="430"></canvas>
						
						 </main>

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
			<script>
				var ctx = document.getElementById("scrapeChart").getContext('2d');
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
			<script>
				var ctx = document.getElementById("forecastChart").getContext('2d');
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