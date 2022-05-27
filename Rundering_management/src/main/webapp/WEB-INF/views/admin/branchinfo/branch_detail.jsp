<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<style>
	#com{
		width:1280;
		margin :auto;
	}
</style>


<head>


<!-- Font Awesome Icons -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/bootstrap/plugins/fontawesome-free/css/all.min.css">
<!-- Theme style -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/bootstrap/dist/css/adminlte.min.css">

<!-- Pretendard  -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/font/pretendard/pretendard-subset.css" />

</head>

<title>지점상세</title>

<body>

<div id="com">
	<section class="content-header">
			<div class="row mb-2">
				<div class="col-sm-6">
					<h1>지점상세</h1>
				</div>
			</div>
	</section>
	<form>
		<div class="form-row">
			<div class="col-6">
				<label for="inputname">name</label> <input type="text"
				class="form-control" placeholder="이름">
			</div>
		</div>
		<div class="form-row">
			<div class="col-6">
				<label for="inputphone">phone</label> <input type="tel"
				class="form-control" placeholder="전화번호">
			</div>
		</div>
		<div class="form-row">
			<div class="form-group col-md-12">
				<label for="inputEmail4">Email</label> <input type="email"
					class="form-control" id="inputEmail4" placeholder="Email">
			</div>
		</div>
		<div class="form-group">
			<label for="inputAddress">Address</label> <input type="text"
				class="form-control" id="inputAddress" >
		</div>
		<div class="form-group">
			<label for="inputAddress2">Address 2</label> <input type="text"
				class="form-control" id="inputAddress2">
		</div>
		<div class="form-row">
			<div class="form-group col-md-6">
				<label for="inputCity">City</label> <input type="text"
					class="form-control" id="inputCity">
			</div>
			<div class="form-group col-md-4">
				<label for="inputState">State</label> <select id="inputState"
					class="form-control">
					<option selected>Choose...</option>
					<option>...</option>
				</select>
			</div>
			<div class="form-group col-md-2">
				<label for="inputZip">Zip</label> <input type="text"
					class="form-control" id="inputZip">
			</div>
		</div>
		<button type="submit" class="btn btn-primary">Sign in</button>
	</form>
</div>

	<!-- jQuery -->
	<script
		src="<%=request.getContextPath()%>/resources/bootstrap/plugins/jquery/jquery.min.js"></script>
	<!-- Bootstrap 4 -->
	<script
		src="<%=request.getContextPath()%>/resources/bootstrap/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- AdminLTE App -->
	<script
		src="<%=request.getContextPath()%>/resources/bootstrap/dist/js/adminlte.min.js"></script>

	<script src="<%=request.getContextPath()%>/resources/js/common.js"></script>

</body>



