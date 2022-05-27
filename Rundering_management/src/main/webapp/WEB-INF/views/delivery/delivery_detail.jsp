<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set value="${dataMap.detail }" var="delivery"></c:set>
<c:set value="${dataMap.detailList }" var="deliveryList"></c:set>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8s">
<title>배송 상세정보</title>
</head>
<body>
	<div class="card">
		<div class="card-header" style="">
			<h3 class="card-title p-1" style="font-size: 1.4rem;">배송 상세정보</h3>
			<div class="card-tools">
				<button type="button" class="btn btn-tool p-3"
					title="Remove" style="" onClick="history.go(-1)">
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
								주문번호 <b class="d-block">${delivery.orderNo }</b>
							</p>
							<p class="text-lg p-2">
								배송 요청일 <b class="d-block"><fmt:formatDate value="${delivery.deliveryRequestDate }" pattern="yyyy-MM-dd" /><fmt:formatDate value="${delivery.deliveryRequestDate }"  var="dr_strange" pattern="yyyyMMdd" /></b>
							</p>
							<p class="text-lg p-2">
								연락처 <b class="d-block">${delivery.phone }</b>
							</p>
							<p class="text-lg p-2">
								주소지 <b class="d-block">${delivery.add1 }</b> <b class="d-block">${delivery.add2 }</b>
							</p>
							<p class="text-lg p-2">
								배송 세탁물 
								<c:forEach items="${deliveryList }" var="deliveryList">
									<b class="d-block">${deliveryList.itemsName } ${deliveryList.quantity }개</b> 
								</c:forEach>
							</p>
						</div>
					</div>
					<div class="col-12 col-md-12 col-lg-8 order-2 order-md-1">
						<div class="col-12 col-sm-4">
							<div class="custom-file" style="padding: 1.5rem;">
								<input type="file" class="custom-file-input" name="multi" id="orginalName" onchange="fileUpload()"> <label class="custom-file-label" for="orginalName" id="lab">사진을 첨부해주세요.</label>
								<input type="hidden" id="picture" name="picture" value="">
							</div>
							<div class="info-box bg-light" style="display:block;" id="jassi" onclick="delivery_complete('08','${delivery.orderNo }');">
								<input type="hidden" id="orderNo" name="orderNo" value="${delivery.orderNo}">
								<div class="info-box-content">
									<span class="info-box-text text-center text-muted" style="font-size: 2rem;" onclick="">배송완료</span>
								</div>
							</div>
							<div class="info-box bg-light" style="display:none;" id="jason" onclick="delivery_complete('09','${delivery.orderNo }');">
								<input type="hidden" id="orderNo" name="orderNo" value="${delivery.orderNo}">
								<div class="info-box-content">
									<span class="info-box-text text-center text-muted"
										style="font-size: 2rem;" onclick="">배송지연완료</span>
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
let 아가모토의눈 = new Date();
아가모토의눈 = 아가모토의눈.getFullYear()*10000 + (아가모토의눈.getMonth()+1)*100 + 아가모토의눈.getDate();
let 닥터스트레인지 = Number('${dr_strange}');

window.addEventListener('load', 도르마무)
function 도르마무() {
	const jassi= document.querySelector('#jassi');	
	const jason= document.querySelector('#jason');	
	if(아가모토의눈 <= 닥터스트레인지){
		jassi.style.display = 'block';
		jason.style.display = 'none';
	}else{
		jassi.style.display = 'none';
		jason.style.display = 'block';
	}
}
	let orderNo1 = document.querySelector("#orderNo");
	var orderNo = orderNo1.value;	
	var picture = document.querySelector("#picture").value;
	var lab = document.querySelector("#lab");

	function delivery_complete(orderStatus){
		Swal.fire({
            title: '배송완료 하시겠습니까?',
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
				success : function() {
					Swal.fire({
						icon: 'success', // 여기다가 아이콘 종류를 쓰면 됩니다.
						title: '배송 완료 처리 되었습니다.',
					});
					setTimeout(function(){location.href='<%=request.getContextPath()%>/fordelivery/delivery';},1000);
				},
				error : function(error) {
					Swal.fire({
						icon: 'warning', // 여기다가 아이콘 종류를 쓰면 됩니다.
						title: '배송사진을 첨부해주세요.',
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
						alert(data.fileName)
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