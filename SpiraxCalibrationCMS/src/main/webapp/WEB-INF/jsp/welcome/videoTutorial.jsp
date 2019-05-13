<%@page import="com.spiraxcalibration.WebConfig.AppsPropertyFile"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<head>
<style>
.center {
	margin-left: auto;
	margin-right: auto;
	display: block;
}
</style>

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
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<link rel="stylesheet" href="customcss/formCss.css">
</head>
<html>
<body>
<div id="header">
		<jsp:include page="/WEB-INF/jsp/header/header.jsp" />
	</div>
	<video id="myvideo" width="100%" height="100%" controls
		style="background:black"> </video>
</body>
<script>
	var videoSource = new Array();
	videoSource[0] = '/tutorialVideos/VIDEO_SUPPLIER.mp4';
    videoSource[1] = '/tutorialVideos/CALIBRATION.mp4';
	videoSource[2] = '/tutorialVideos/PURCHASE_REQUISITION.mp4';
	videoSource[3] = '/tutorialVideos/PURCHASE_VIEW.mp4';
	var i = 0; videoCount = videoSource.length;
	function videoPlay(videoNum) {
		document.getElementById("myvideo").setAttribute(src = "src",
				videoSource[videoNum]);
		document.getElementById("myvideo").load();
		document.getElementById("myvideo").play();
	}
	document.getElementById('myvideo').addEventListener('ended', myHandler,false);
	videoPlay(0);
	function myHandler() {
		i++;
		if (i == (videoCount)) {
			i = 0;
			videoPlay(i);
		} else {
			videoPlay(i);
		}
	}
</script>
</html>