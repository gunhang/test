<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
</head>
<title>세탁 품목 수정</title>
<body>

<section class="content register-page">
<div class="register-box">
				<div class="login-logo">
					<a href=""><b>세탁 품목 수정</b></a>
				</div>
				<!-- form start -->
				<div class="card">
					<div class="register-card-body">
						<form role="modifyForm" class="form-horizontal" action="modify" method="post" name="modifyForm">
						<input type="hidden" name="laundryItemsCode" value="${laundryItems.laundryItemsCode }" />
							<div class="input-group mb-3">
								<div class="mailbox-attachments clearfix" style="text-align: center; margin: 0 auto">
								</div>
								<br>
							</div>
							<div class="form-group row">
								<label for="pwd" class="col-sm-3" style="font-size: 0.9em;">
									<span style="color: red; font-weight: bold;">*</span>품목명</label>
								<div class="col-sm-9 input-group-sm">
									<input class="form-control" name="itemsName" value="${laundryItems.itemsName }">
								</div>
							</div>


							<div class="form-group row">
								<label for="pwd" class="col-sm-3" style="font-size: 0.9em;">
									<span style="color: red; font-weight: bold;">*</span>세탁 가격</label>
								<div class="col-sm-9 input-group-sm">
									<input class="form-control" name="price" value="${laundryItems.price }">
								</div>
							</div>
						</form>
						
						<div class="btn-group float-right">
							<button type="button" id="sendBtn" class="btn btn-primary btn-sm"
									onclick="modifyPOST_go();">수정</button>
							&nbsp;&nbsp;
							
						</div>
					</div>
					<!-- register-card-body -->
				</div>
			</div>
			</section>
			
	<script>
		function modifyPOST_go() {
			var form = document.modifyForm;
			if (form.itemsName.value == "") {
				alert("수정할 품목명을 입력하세요.");
				return;
			}
			if (form.price.value == "") {
				alert("수정할 가격을 입력하세요.");
				return;
			}
			//alert("modify btn click");
			$("form[role='modifyForm']").submit();
		}
	</script>
	
	 
	 <c:if test="${from eq 'laundryItems'}" >
	 	<script>
			alert("수정되었습니다.");
			window.close();
			window.opener.location.reload();
		 </script>
	</c:if>
	
</body>
