<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="pageMaker" value="${dataMap.pageMaker }" />
<c:set var="cri" value="${dataMap.pageMaker.cri }" />
<c:set var="laundryItemsList" value="${dataMap.laundryItemsList }" />

<head>
</head>
<title>세탁 품목 등록</title>
<body>

<section class="content register-page">
<div class="register-box">
				<div class="login-logo">
					<a href=""><b>세탁 품목 등록</b></a>
				</div>
				<!-- form start -->
				<div class="card">
					<div class="register-card-body">
						<form role="form" name="registForm" class="form-horizontal" action="<%=request.getContextPath() %>/admin/laundryitems/regist" method="post">
							<input type="hidden" name="laundryitems">
							<div class="input-group mb-3">
								<div class="mailbox-attachments clearfix" style="text-align: center; margin: 0 auto">
										
								</div>
								<br>
							</div>
							<div class="form-group row">
								<label for="laundryCategory" class="col-sm-3" style="font-size: 0.9em;">
									<span style="color: red; font-weight: bold;">*</span>품목구분</label>
								<div class="col-sm-9 input-group-sm">
									<select class="form-control" name="laundryCategory" id="laundryCategory">
										<option value="CL">의류</option>
										<option value="BE">침구</option>
										<option value="SH">신발</option>
									</select>
								</div>
							</div>
							<div class="form-group row">
								<label for="itemsName" class="col-sm-3" style="font-size: 0.9em;">
									<span style="color: red; font-weight: bold;">*</span>품목명</label>
								<div class="col-sm-9 input-group-sm">
									<input class="form-control" name="itemsName" type="text" id="itemsName">
								</div>
							</div>
							<div class="form-group row">
								<label for="price" class="col-sm-3" style="font-size: 0.9em;">
									<span style="color: red; font-weight: bold;">*</span>세탁 가격</label>
								<div class="col-sm-9 input-group-sm">
									<input class="form-control" name="price" type="text" id="price">
								</div>
							</div>
						<!-- 	<div class="form-group row">
								<label for="pwd" class="col-sm-3" style="font-size: 0.9em;">
									비고
								</label>
								<div class="col-sm-9 input-group-sm">
									<input class="form-control" name="pwd" type="password" id="pwd">
								</div>
							</div> -->
						</form>
							<button onclick="regist_go()" id="sendBtn" class="btn btn-primary btn-sm float-right ">등록</button>
							&nbsp;&nbsp;
							
					</div>
				</div>
				</div>
			</section>
 <script>
	
	function regist_go(){
		var form = document.registForm;
		var itemsName = form.itemsName.value;
	    var price = form.price.value;
	    
	    if(itemsName==""){
	        alert("품목명과 가격을 모두 입력하세요.");
	        return;
	    }
	    if(price ==""){
	    	alert("가격을 입력하세요")
	    	return;
	    }
	    $("form[role='form']").submit();
	    
	}
			
 </script>
 
	 
	 <c:if test="${from eq 'regist'}" >
	 	<script>
			alert("등록되었습니다.");
			window.close();
			window.opener.location.reload();
		 </script>
	</c:if>
 


</body>
