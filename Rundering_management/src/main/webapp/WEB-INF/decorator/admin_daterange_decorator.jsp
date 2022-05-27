<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="decorator"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
<title><decorator:title default="Rundering"/></title>
 <!-- Pretendard  -->
  <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/font/pretendard/pretendard-subset.css" />

  <!-- Font Awesome Icons -->
  <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/bootstrap/plugins/fontawesome-free/css/all.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/bootstrap/dist/css/adminlte.min.css">
  <!-- summernote -->
  <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/bootstrap/plugins/summernote/summernote.min.css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/bootstrap/plugins/daterangepicker/daterangepicker.css"> 
  <!-- Pretendard  -->
  <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/font/pretendard/pretendard-subset.css" />
  <!--파비콘-->
  <link rel="shortcut icon" href="<%=request.getContextPath() %>/resources/image/Favicon.ico" />
  <link rel="icon" href="<%=request.getContextPath() %>/resources/image/Favicon.svg" />

  <style type="text/css">
	body {
		font-family: Pretendard, -apple-system, BlinkMacSystemFont, system-ui,
			Roboto, 'Helvetica Neue', 'Segoe UI', 'Apple SD Gothic Neo',
			'Noto Sans KR', 'Malgun Gothic', 'Apple Color Emoji', 'Segoe UI Emoji',
			'Segoe UI Symbol', sans-serif;
	}
  </style>
  <!-- width.css -->
  <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/width.css"/>
  
	  <!-- jQuery -->
	<script src="<%=request.getContextPath() %>/resources/bootstrap/plugins/jquery/jquery.min.js"></script>
	<!-- Bootstrap 4 -->
	<script src="<%=request.getContextPath() %>/resources/bootstrap/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- AdminLTE App -->
	<script src="<%=request.getContextPath() %>/resources/bootstrap/dist/js/adminlte.min.js"></script>
	<!-- summernote -->
	<script src="<%=request.getContextPath() %>/resources/bootstrap/plugins/summernote/summernote.min.js"></script>
	
	<script src="<%=request.getContextPath()%>/resources/bootstrap/plugins/moment/moment.min.js"></script>
	<script src="<%=request.getContextPath()%>/resources/bootstrap/plugins/moment/moment-with-locales.min.js"></script>
	<script src="<%=request.getContextPath()%>/resources/bootstrap/plugins/moment/locale/ko.js"></script>
	<script src="<%=request.getContextPath()%>/resources/bootstrap/plugins/daterangepicker/daterangepicker.js"></script>
	 
	<script src="<%=request.getContextPath() %>/resources/js/common.js"></script>  

<decorator:head />

</head>

<body>

		<decorator:body />




</body>
</html>
