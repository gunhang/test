<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.1.9/dist/sweetalert2.min.css">
<script	src="https://cdn.jsdelivr.net/npm/sweetalert2@11.1.9/dist/sweetalert2.all.min.js"></script>

<c:set var="pageMaker" value="${dataMap.pageMaker }" />
<c:set var="cri" value="${dataMap.pageMaker.cri }" />
<c:set var="myOrderList" value="${dataMap.myOrderList }" />




<style>
#peter {
	width: 200px;
	height: 430px;
	list-style-type:none;
	margin:0;
	padding:0;
	border:solid 1px #f3f3f3;
	background-color:#f3f3f3;
}
aside ul li a{
	color:#000000;
	font-size:1.1em;
}
</style>


<div class="row">
	<aside style="padding-top: 50px;">
		<ul id="peter" style="">
			<li
				style="margin-top: 15px; padding-bottom: 15px; border-bottom: solid 1px lightgray;">
				<h1 style="font-size: 1.5em; text-align: center;">마이페이지</h1>
			</li>
			<li onclick="location.href='<%=request.getContextPath()%>/mypage'"
				style="cursor: pointer; margin-top: 30px; margin-bottom: 15px; margin-left: 30px;"><a>회원
					정보 수정</a></li>
			<li
				onclick="location.href='<%=request.getContextPath()%>/mypage/myaddress'"
				style="cursor: pointer; margin-top: 15px; margin-bottom: 15px; margin-left: 30px;"><a>주소
					관리</a></li>
			<li style="margin-top: 15px; margin-bottom: 15px; margin-left: 30px;">
				<a href="<%=request.getContextPath()%>/mypage/myorder/histroy/main">주문 내역</a>
				<ul>
					<li	style="margin-top: 10px; margin-bottom: 5px; padding-left: 20px; font-size:0.9em;">
						<a href="<%=request.getContextPath()%>/mypage/myorder/histroy/ingList">진행중인 주문</a>
					</li>
					<li	style="margin-top: 10px; margin-bottom: 5px; padding-left: 20px; font-size:0.9em;">
						<a href="<%=request.getContextPath()%>/mypage/myorder/histroy/complete">완료된 주문</a>
					</li>
					<li	style="margin-top: 10px; margin-bottom: 5px; padding-left: 20px; font-size:0.9em;">
					<a href="<%=request.getContextPath()%>/mypage/myorder/histroy/comlist">결제 내역</a></li>
					<li	style="margin-top: 10px; margin-bottom: 5px; padding-left: 20px; font-size:0.9em;">
					<a href="<%=request.getContextPath()%>/mypage/myorder/histroy/cnacellist">취소 내역</a></li>
				</ul>
			</li>
			<li	onclick="location.href='<%=request.getContextPath()%>/mypage/myinquiry/list'"
				style="cursor: pointer; margin-top: 10px; margin-bottom: 15px; margin-left: 30px;">
				<a>문의 내역</a>
			</li>
			<li	onclick="location.href='<%=request.getContextPath()%>/mypage/secedeform'"
				style="cursor: pointer; margin-top: 10px; margin-bottom: 15px; margin-left: 30px;">
				<a>회원 탈퇴</a>
			</li>
		</ul>
	</aside>

	<div style="width: 60%; display: flex; flex-direction: column; margin-left: 50px; margin-top: 30px;">
		<section class="content-header">
			<div class="container-fluid">
				<div class="row mb-2">
					<div class="col-sm-6">
						<h1>완료된 주문</h1>
					</div>
					<div class="col-sm-6">
						<ol class="breadcrumb float-sm-right">
							<li class="breadcrumb-item active">완료된 주문</li>
							<li class="breadcrumb-item"><a href="#">주문내역</a></li>
						</ol>
					</div>
				</div>
			</div>
			<hr style="border: 1px solid rgb(170, 167, 167);">
		</section>

		<div class="card">
			<div class="card-header border-transparent">
				<h3 class="card-title">완료된 주문내역</h3>
			</div>

			<div class="card-body p-0">
				<div class="table-responsive">

					<c:if test="${empty myOrderList  }">
						<table class="table m-0">
							<tbody>
								<tr>
									<td colspan="5" rowspan="3" align="center"><strong>해당 내용이 없습니다.</strong></td>
								</tr>
							</tbody>
						</table>
					</c:if>
					<c:forEach items="${myOrderList }" var="list">
						<table class="table m-0">
							<tbody>
								<tr style="border: none;">
									<input type="hidden" id="orderNo" value="${list.orderNo }" />
									<td style="width:25%;padding:12px;" align="center">배송상태 : <span style="font-weight:600;font-size:1.2em;">${list.orderStatus}</span></td>
									<td style="width:25%" align="left">주문일자 : <fmt:formatDate value="${list.orderDate}" pattern="yyyy-MM-dd" /></td>
									<td style="width:25%"></td>
									<td rowspan="3"
										style="width: 25%; border-left: 1px solid rgba(0, 0, 0, .125); text-align: center; vertical-align: middle;">
										<c:if test="${list.orderStatus ne '배송정상완료' && list.orderStatus ne '배송지연완료'}">
											<button class="btn btn-primary btn-m col-10" onClick="window.open('<%=request.getContextPath()%>/mypage/order_detail?orderNo='+${list.orderNo}+'','주문내역상세','width=1000, height=830')">배송조회</button>
										</c:if> 
										<c:if test="${list.orderStatus eq '배송정상완료' || list.orderStatus eq '배송지연완료'}">
											<span style="font-weight: bold;">세탁물 배송 완료</span>
											<button class="btn btn-primary btn-m col-10" onClick="window.open('<%=request.getContextPath()%>/mypage/order_detail?orderNo='+${list.orderNo}+'','주문내역상세','width=1000, height=830')">내역조회</button>
										</c:if> 
										<c:if test="${list.orderStatus eq '수거대기' }">
											<button class="btn btn-danger btn-m col-10" onclick="">주문ㆍ배송취소</button>
										</c:if>
									</td>
								</tr>
								<tr style="border: none;">
									<td rowspan="2" align="center" class="p-0" style="border-right: none; border-top: none;padding:12px;">
										<div class="orderPicture mb-3" id="pictureView"	data-id="${list.atchFileNo}" data-aa="1" style="height: 100px; width: 120px;"></div>
									</td>
									<td colspan="2" align="left;" style="border-top: none;vertical-align:middle;">주문번호 : ${list.orderNo}</td>
								</tr>
								<tr style="border: none;">
									<td align="left;" style="border-left: none; border-top: none;padding:12px;vertical-align:middle;">
									<c:if test="${!empty list.branchCode}" >
									담당 : ${list.branchCode }
									</c:if>
									<c:if test="${empty list.branchCode}" >
									<span style="color:red; font-size:1em;">미정</span>
									</c:if>
									</td>
									<td style="text-align: right; border-top: none;vertical-align:middle;padding:12px;">결제금액 : <fmt:formatNumber type="number" maxFractionDigits="3" value="${list.totalPrice}" />원</td>
								</tr>
							</tbody>
						</table>
					</c:forEach>
				</div>
			</div>
			<div class="card-footer clearfix">
				<!--페이징 처리할 공간 -->
				<%@ include file="/WEB-INF/views/mypage/pagination.jsp"%>
			</div>
		</div>
	</div>
</div>



<script>
async function getImg(){
	 for(var target of document.querySelectorAll('.orderPicture')){	
		 var atchFileNo = target.getAttribute('data-id');
		 var atchFileSeq = target.getAttribute('data-aa');
		 target.style.backgroundImage="url('<%=request.getContextPath()%>/mypage/getPicture?atchFileNo="+atchFileNo+"&atchFileSeq=1')";
		 target.style.backgroundPosition="center";
		 target.style.backgroundRepeat="no-repeat";
		 target.style.backgroundSize="cover";
	}
}

getImg();

	function detail(){
		$.ajax({
			url : '<%=request.getContextPath()%>/mypage/order_detail',
			data : {
				'password' : $('#password').val()
			},
			type : 'post',
			success : function(result) {
				if (result.toUpperCase() == "OK") {
					Swal.fire('변경 완료', '비밀변호 변경이 완료되었습니다.', 'success' )
				} else {
					Swal.fire({
						icon: 'error', // 여기다가 아이콘 종류를 쓰면 됩니다.
						title: '비밀번호 변경에 실패하였습니다.',
					});
				}
			},
			error : function(error) {
				AjaxErrorSecurityRedirectHandler(error.status);
			}
		});
	}
</script>