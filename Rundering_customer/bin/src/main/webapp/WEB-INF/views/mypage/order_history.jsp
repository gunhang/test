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

<div
	style="width: 60%; display: flex; flex-direction: column; margin-left: 20%;">
	<section class="content-header">
		<div class="container-fluid">
			<div class="row mb-2">
				<div class="col-sm-6">
					<h1>주문내역</h1>
				</div>
				<div class="col-sm-6">
					<ol class="breadcrumb float-sm-right">
						<li class="breadcrumb-item active">주문내역</li>
						<li class="breadcrumb-item"><a href="#">마이페이지</a></li>
					</ol>
				</div>
			</div>
		</div>
		<hr style="border: 1px solid rgb(170, 167, 167);">
	</section>

	<div class="card">
		<div class="card-header border-transparent">
			<h3 class="card-title">My Laundry Orders</h3>
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
								<td style="width: 25%" align="center">배송상태 : ${list.orderStatus}</td>
								<td style="width: 25%" align="center">주문일자 : <fmt:formatDate value="${list.orderDate}" pattern="yyyy-MM-dd"/></td>
								<td style="width: 25%"></td>
								<td rowspan="3"
									style="width: 25%; border-left: 1px solid rgba(0, 0, 0, .125); text-align: center; vertical-align: middle;">
									<button class="btn btn-primary btn-m col-10" onclick="">배송조회</button>
									<button class="btn btn-danger btn-m col-10" onclick="">주문ㆍ배송취소</button>
								</td>
							</tr>
							<tr style="border: none;">
								<td rowspan="2" align="center" style="border-right:none;border-top: none;"><img alt="${list.atchFileNo}" height="100px;" width="70px;" src="<%=request.getContextPath() %>/resources/images/자산 1.png"> </td>
								<td colspan="2" align="left;" style="border-top: none;">상품명 : ${list.paymentNo}</td>
							</tr>
							<tr style="border: none;">
								<td align="left;" style="border-left: none;border-top: none;">지점명 : ${list.branchCode }</td>
								<td style="text-align: right;border-top:none;">결제금액 : <fmt:formatNumber type="number" maxFractionDigits="3" value="${list.totalPrice}" />원</td>
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