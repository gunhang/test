<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<body class="sidebar-mini layout-boxed">

<nav class="main-header navbar navbar-expand navbar-white navbar-light" id="navbar">
    <!-- Left navbar links -->
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link" data-widget="pushmenu" href="#" role="button"><i class="fas fa-bars"></i></a>
      </li>
	</ul>


		<!-- Right navbar links -->
		<ul class="navbar-nav ml-auto">
		
		
			<li class="nav-item dropdown notificationli">
			</li>

			<li class="nav-item"><a class="nav-link" data-widget="control-sidebar" data-slide="true" href="#" role="button">${branchName }</a></li>
			<li class="nav-item">
				<button type="button" class="btn btn-block btn-sm btn-outline-dark" id="logoutbtn"
										onclick="location.href='<%=request.getContextPath() %>/common/logout';" >LOGOUT
				</button></li>
		</ul>
	</nav>

</body>