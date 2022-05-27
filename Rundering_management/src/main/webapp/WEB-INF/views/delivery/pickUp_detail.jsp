<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set value="${dataMap.detail }" var="pickup"></c:set>
<c:set value="${dataMap.detailList }" var="pickupList"></c:set>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8s">
<title>배송 상세정보</title>

<!-- 이쁜알럽창♥ -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/bootstrap/plugins/sweetalert2/sweetalert2.min.css" />

</head>
<body>
	<div class="card">
		<div class="card-header" style="">
			<h3 class="card-title p-1" style="font-size: 1.4rem;">배송 상세정보</h3>
			<div class="card-tools">
				<button type="button" class="btn btn-tool p-3" title="Remove"
					style="" onclick="history.back();">
					<i class="fas fa-times" style="font-size: 1.7rem;"></i>
				</button>
			</div>
		</div>

		<div class="card-body">
			<div class="row">
				<form method="post" action="regist" enctype="multipart/form-data" id="fileform">
					<div class="col-12 col-md-12 col-lg-4 order-1 order-md-2">
						<div class="text-muted">
							<p class="text-lg p-2">
								주문번호 <b class="d-block">${pickup.orderNo }</b>
							</p>
							<p class="text-lg p-2">
								수거 요청일 <b class="d-block"><fmt:formatDate
										value="${pickup.pickupRequestDate }" pattern="yyyy-MM-dd" /></b>
							</p>
							<p class="text-lg p-2">
								연락처 <b class="d-block">${pickup.phone }</b>
							</p>
							<p class="text-lg p-2">
								주소지 <b class="d-block">${pickup.add1 }</b> <b class="d-block">${pickup.add2 }</b>
							</p>
							<p class="text-lg p-2">
								배송 세탁물
								<c:forEach items="${pickupList }" var="list">
									<b class="d-block">${list.itemsName } ${list.quantity }개</b>
								</c:forEach>
							</p>
						</div>
					</div>
					<div class="col-12 col-md-12 col-lg-8 order-2 order-md-1">
						<div class="col-12 col-sm-4">
							<div class="custom-file" style="padding: 1.5rem;">
								<input type="file" class="custom-file-input"
									id="orginalName" name="multi" onchange="fileUpload()"> <label
									class="custom-file-label" for="orginalName" id="lab">사진을
									첨부해주세요.</label>
									<input type="hidden" id="picture" name="picture" value="">
							</div>
							<div class="info-box bg-light" onclick="pickup_complete('03');">
								<input type="hidden" id="orderNo" name="orderNo" value="${pickup.orderNo}">
								<div class="info-box-content">
									<span class="info-box-text text-center text-muted"
										style="font-size: 2rem;" onclick="">수거완료</span>
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	
	
<!-- 알림 sweetalert2 -->
<script	src="<%=request.getContextPath()%>/resources/bootstrap/plugins/sweetalert2/sweetalert2.all.min.js"></script>
	
	
	<script>
	let orderNo1 = document.querySelector("#orderNo");
	var orderNo = orderNo1.value;
	var picture = document.querySelector("#picture").value;
	var lab = document.querySelector("#lab");
	
	function pickup_complete(orderStatus){
		Swal.fire({
            title: '수거 완료하시겠습니까?',
            icon : 'warning' ,
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: '승인',
            cancelButtonText: '취소',
            reverseButtons: true, // 버튼 순서 거꾸로
          }).then((result) => {
            if (result.isConfirmed) {			
			$.ajax({
				url : '<%=request.getContextPath()%>/fordelivery/regist',
				data : {
					'orderStatus' : orderStatus,
					'orderNo' : orderNo,
					'picture' : picture
				},
				type : 'post',
				success : function(ok) {
					Swal.fire({
						icon: 'success', // 여기다가 아이콘 종류를 쓰면 됩니다.
						title: '수거 완료 처리 되었습니다.',
					});
					setTimeout(function(){location.href='<%=request.getContextPath()%>/fordelivery/pickup';},1000);
				},
				error : function(error) {
					Swal.fire({
						icon: 'warning', // 여기다가 아이콘 종류를 쓰면 됩니다.
						title: '수거사진을 첨부해주세요.',
					});
				}
			});
			
		}
	})
	}
	function fileUpload(){
		let form = $("#fileform")[0];
		let formData = new FormData(form);
		console.log(form);
		
		console.log(formData);
		
		$.ajax({
			url:"<%=request.getContextPath()%>/fordelivery/pictureupload",
				data : formData,
				type : 'post',
				dataType : "json",
				processData : false,
				contentType : false,
				success : function(data) {
					picture = data.fileName;
					lab.innerHTML = data.orginalName;
				},
				error : function(error) {
					AjaxErrorSecurityRedirectHandler(error.status);
				}
			});
			
		}
	</script>
</body>
</html>