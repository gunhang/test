<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.1.9/dist/sweetalert2.min.css">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.1.9/dist/sweetalert2.all.min.js"></script>

<c:set var="pageMaker" value="${dataMap.pageMaker }" />
<c:set var="cri" value="${dataMap.pageMaker.cri }" />
<c:set var="comList" value="${dataMap.comList }" />

<style>
#peter {
	width: 200px;
	height: 430px;
	list-style-type: none;
	margin: 0;
	padding: 0;
	border: solid 1px #f3f3f3;
	background-color: #f3f3f3;
}

aside ul li a {
	color: #000000;
	font-size: 1.1em;
}
</style>


<div class="row">
	<aside style="padding-top: 50px;">
		<ul id="peter" style="">
			<li style="margin-top: 15px; padding-bottom: 15px; border-bottom: solid 1px lightgray;">
				<h1 style="font-size: 1.5em; text-align: center;">마이페이지</h1>
			</li>
			<li onclick="location.href='<%=request.getContextPath()%>/mypage'"
				style="cursor: pointer; margin-top: 30px; margin-bottom: 15px; margin-left: 30px;">
				<a>회원 정보 수정</a>
			</li>
			<li onclick="location.href='<%=request.getContextPath()%>/mypage/myaddress'"
				style="cursor: pointer; margin-top: 15px; margin-bottom: 15px; margin-left: 30px;">
				<a>주소 관리</a>
			</li>
			<li style="margin-top: 15px; margin-bottom: 15px; margin-left: 30px;">
				<a href="<%=request.getContextPath()%>/mypage/myorder/histroy/main">주문 내역</a>
				<ul>
					<li style="margin-top: 10px; margin-bottom: 5px; padding-left: 20px; font-size: 0.9em;">
						<a href="<%=request.getContextPath()%>/mypage/myorder/histroy/ingList">진행중인 주문</a>
					</li>
					<li style="margin-top: 10px; margin-bottom: 5px; padding-left: 20px; font-size: 0.9em;">
						<a href="<%=request.getContextPath()%>/mypage/myorder/histroy/complete">완료된 주문</a>
					</li>
					<li	style="margin-top: 10px; margin-bottom: 5px; padding-left: 20px; font-size:0.9em;">
					<a href="<%=request.getContextPath()%>/mypage/myorder/histroy/comlist">결제 내역</a></li>
					<li	style="margin-top: 10px; margin-bottom: 5px; padding-left: 20px; font-size:0.9em;">
					<a href="<%=request.getContextPath()%>/mypage/myorder/histroy/cnacellist">취소 내역</a></li>
				</ul>
			</li>
			<li onclick="location.href='<%=request.getContextPath()%>/mypage/myinquiry/list'"
				style="cursor: pointer; margin-top: 10px; margin-bottom: 15px; margin-left: 30px;">
				<a>문의 내역</a>
			</li>
			<li	onclick="location.href='<%=request.getContextPath()%>/mypage/secedeform'"
				style="cursor: pointer; margin-top: 10px; margin-bottom: 15px; margin-left: 30px;">
				<a>회원 탈퇴</a>
			</li>
		</ul>
	</aside>

	<div
		style="width: 60%; display: flex; flex-direction: column; margin-left: 50px; margin-top: 30px;">
		<section class="content-header">
			<div class="container-fluid">
				<div class="row mb-2">
					<div class="col-sm-6">
						<h1>결제내역</h1>
					</div>
					<div class="col-sm-6">
						<ol class="breadcrumb float-sm-right">
							<li class="breadcrumb-item active">결제내역</li>
							<li class="breadcrumb-item"><a href="#">주문내역</a></li>
						</ol>
					</div>
				</div>
			</div>
			<hr style="border: 1px solid rgb(170, 167, 167);">
		</section>

		<div class="card-body p-0">
			<div class="table-responsive">
				<table class="table m-0">
					<tbody>
						<tr>
							<th style="text-align:center;"><span style="font-weight:bold;">번호</span></th>
							<th style="text-align:left;"><span style="font-weight:bold;">주문번호</span></th>
							<th style="text-align:left;"><span style="font-weight:bold;">결제 주문명</span></th>
							<th style="text-align:center;"><span style="font-weight:bold;">결제 종류</span></th>
							<th style="text-align:center;"><span style="font-weight:bold;">결제 금액</span></th>
							<th style="text-align:center;"><span style="font-weight:bold;">결제일자</span></th>
						</tr>
						<c:if test="${empty comList }">
						<tr> 
							<td colspan="6" style="font-weight:400px;" align="center">해당 내용이 없습니다.</td>
						</tr>
						</c:if>
						<c:if test="${!empty comList }">
						<c:forEach items="${comList }" var="list" varStatus="status">
						<tr>
							<td align="center"><strong>${status.count}</strong></td>
							<td align="left">${list.orderNo}</td>
							<td align="left">${list.paymentName}</td>
							<td align="center">${list.paymentType}</td>
							<td align="right"><fmt:formatNumber type="number" maxFractionDigits="3" value="${list.paymentPrice}" />원</td>
							<td align="right"><fmt:formatDate value="${list.paymentDate}" pattern="yyyy-MM-dd" /></td>
						</tr>
						</c:forEach>
						</c:if>
					</tbody>
				</table>
			<hr class="m-0"/>
			</div>
		</div>
		<div class="card-footer clearfix">
			<!--페이징 처리할 공간 -->
			<%@ include file="/WEB-INF/views/mypage/pagination.jsp"%>
		</div>
	</div>
</div>



<script>
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
	
	function cancel(orderNo,atchFileNo,replyNo){
		Swal.fire({
            title: '세탁 주문을 취소하시겠습니까?',
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
					url : '<%=request.getContextPath()%>/mypage/cancelOrder',
					data : {
						'orderNo' : orderNo,
						'atchFileNo' : atchFileNo,
						'replyNo' : replyNo
					},
					type : 'post',
					success : function(result) {
						if (result.toUpperCase() == "OK") {
							Swal.fire({
								icon: 'success', // 여기다가 아이콘 종류를 쓰면 됩니다.
								title: '주문이 취소되었습니다.',
								content: '1~2일 이내에 환불 처리 됩니다.' 
							});
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
              }})	
	}
</script>