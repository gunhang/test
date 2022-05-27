<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
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

  <!-- Google Font: Source Sans Pro -->
  <!-- <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback"> -->
  
  <!-- Font Awesome Icons -->
  <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/bootstrap/plugins/fontawesome-free/css/all.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/bootstrap/dist/css/adminlte.min.css">
  <!-- summernote -->
  <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/bootstrap/plugins/summernote/summernote.min.css"> 
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

/* [class*=sidebar-dark-] {
	background-color: rgb(37, 110, 159);
}
[class*=sidebar-dark-] .sidebar a {
	background-color: rgb(37, 110, 159);
} */
#sidebar {
	background-color: #DCE2F0;
}

#sidebar a, #sidebar p {
	color: #000000bd;
}

#navbar {
	background-color: #fcf6f5;
}

#content-wrapper {
	background-color: #fcf6f5;
}

#logoutbtn {
	background-color: #DCE2F0;
	margin-top: 4px;
}

body {
	background: #292e33;
}
</style>
     

<decorator:head />

</head>
<body>

<div class="wrapper">
	<%@ include file="/WEB-INF/include/admin_header.jsp" %>
	
	<%@ include file="/WEB-INF/include/admin_aside.jsp" %>
	
	
	<div class="content-wrapper" id="content-wrapper">
		<decorator:body />
	</div>
	
	<%@ include file="/WEB-INF/include/footer.jsp" %>
</div>



<!-- jQuery -->
<script src="<%=request.getContextPath() %>/resources/bootstrap/plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script src="<%=request.getContextPath() %>/resources/bootstrap/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- AdminLTE App -->
<script src="<%=request.getContextPath() %>/resources/bootstrap/dist/js/adminlte.min.js"></script>
<!-- summernote -->
<script src="<%=request.getContextPath() %>/resources/bootstrap/plugins/summernote/summernote-bs4.min.js"></script>

<script src="<%=request.getContextPath() %>/resources/js/common.js"></script>

<script>
    $('div.wrapper').css({
       "max-width":"1280px",
       "margin" : "0 auto"
    });
</script>

</body>
</html>