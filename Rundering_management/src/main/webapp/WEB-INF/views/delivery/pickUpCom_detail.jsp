<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
				<button type="button" class="btn btn-tool p-3"
					title="Remove" style="" onclick="history.go(-1);">
					<i class="fas fa-times" style="font-size: 1.7rem;"></i>
				</button>
			</div>
		</div>
		
		<div class="card-body">
			<div class="row">
				<div class="col-12 col-md-12 col-lg-4 order-1 order-md-2">
					<div class="text-muted">
						<p class="text-lg p-2">
							주문번호 <b class="d-block">${pickup.orderNo }</b>
						</p>
						<p class="text-lg p-2">
							수거 요청일 <b class="d-block"><fmt:formatDate value="${pickup.pickupRequestDate }" pattern="yyyy-MM-dd" /></b>
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
						<div class="row pl-3">
							<div style="border: 1px solid black; height: 100px; width: 100px; margin-right:20px;" id="pictureView" class="goodsPicture"></div>
							<div style="border: 1px solid black; height: 100px; width: 100px; margin-right:20px;" id="pictureView" class="goodsPicture"></div>
							<div style="border: 1px solid black; height: 100px; width: 100px; margin-right:20px;" id="pictureView" class="goodsPicture"></div>
						</div>
						<div class="info-box bg-light mt-5">
							<div class="info-box-content">
								<span class="info-box-text text-center text-muted"
									style="font-size: 2rem;" onclick="pickup_cancel('02');">취 소</span>
							</div>
							<input type="hidden" id="orderNo" name="orderNo" value="${pickup.orderNo }">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 알림 sweetalert2 -->
<script	src="<%=request.getContextPath()%>/resources/bootstrap/plugins/sweetalert2/sweetalert2.all.min.js"></script>

<script>
let imgList = null;
function getImages(atchFileNo){ 
    $.ajax({
        url:"<%=request.getContextPath()%>/branch/laundrysituatuion/getimgs",
        type:"post",
        data: {
        	atchFileNo:atchFileNo
        },
        dataType:"json",
        success:function(data){
			imgList= new Array();
			for(let i of data){
				imgList.push(i);
			}
			let handleData= {
					count:countArray
			};
        	let html = template(handleData);
        	
        	document.querySelector("#imgsrc").src="data:image/jpg;base64,"+imgList[0];
        	document.querySelector("#imgsrc").style.display="flex";
        },
        error:function(error){
		//alert('댓글이 등록을 실패했습니다.');
		AjaxErrorSecurityRedirectHandler(error.status);
	}
    })
}
</script>

<script>
	function pickup_cancel(orderStatus){
		Swal.fire({
            title: '취소하시겠습니까?',
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
				url : '<%=request.getContextPath()%>/fordelivery/updatePickUpCom',
				data : {
					'orderStatus' : orderStatus,
					'orderNo' : $('#orderNo').val()
				},
				type : 'post',
				success : function(ok) {
					if(ok.toUpperCase() == "OK"){
						Swal.fire({
							icon: 'success', // 여기다가 아이콘 종류를 쓰면 됩니다.
							title: '취소 처리 되었습니다.',
						});
						setTimeout(function(){location.href='<%=request.getContextPath()%>/fordelivery/pickup';},1000);
					} else {
						Swal.fire({
							icon: 'warning', // 여기다가 아이콘 종류를 쓰면 됩니다.
							title: '시스템 오류로 반려 할 수 없습니다.'
						});
					}
				},
				error : function(error) {
					AjaxErrorSecurityRedirectHandler(error.status);
				}
			});
		}
	})
}
</script>

</body>
</html>