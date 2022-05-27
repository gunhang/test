<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<title>세탁주문 상세</title>
<body>
<div class="row" style="width: 100%">
	<div class="col-6 pl-3">
		<h3 class="m-3">세탁주문 상세</h3>
		<div class="p-3 m-0 card-secondary card-outline">
			<div class="row">
				<div class="col-12">
					<h5><c:if test="${!empty laundryOrder.branchCode }">
							${laundryOrder.branchCode }
						</c:if> 
						<c:if test="${empty laundryOrder.branchCode }">
							지점미할당
						</c:if> 
						<span class="float-right">
						<span class="badge bg-secondary">${orderCodeMap[laundryOrder.orderStatus]}</span>
						</span>
					</h5>
					<p class="m-0">
						주문번호: <span class="float-right"> ${laundryOrder.orderNo }</span>
					</p>
					<p class="m-0">
						주문일: <span class="float-right"> <fmt:formatDate value="${laundryOrder.orderDate }" pattern="yyyy-MM-dd HH:mm:ss"/></span>
					</p>
				</div>
			</div>
			<div class="card-body p-0">
				<hr>
				<strong><i class="fas fa-user mr-1"></i> 주문자</strong>
				<p class="text-muted">${laundryOrder.memberNo}</p>
				<hr>
				<strong><i class="fas fa-mobile-alt mr-1"></i> 연락처</strong>
				<p class="text-muted">${laundryOrder.contactNumber}</p>
				<hr>
				<strong><i class="fas fa-map-marker-alt mr-1"></i> 주소지</strong>
				<p class="text-muted m-0">${laundryOrder.zip}</p>
				<p class="text-muted m-0">${laundryOrder.add1}</p>
				<p class="text-muted m-0">${laundryOrder.add2}</p>
				<hr>
				<strong><i class="fas fa-pencil-alt mr-1"></i> 요청사항</strong>
				<c:if test="${!empty laundryOrder.requestDetails }">
				<p class="text-muted">${laundryOrder.requestDetails}</p>
				</c:if>
				<c:if test="${empty laundryOrder.requestDetails }">
					<p class="text-muted">-</p>
				</c:if>
			</div>
			<hr>


			<div class="row invoice-info">
				<div class="col-sm-6 invoice-col">
					<strong>수거요청일</strong><br>
					<p class="text-muted"><fmt:formatDate value="${laundryOrder.pickupRequestDate }" pattern="yyyy-MM-dd"/></p>
					<hr>
					<strong>수거완료일</strong><br>
					<c:if test="${!empty laundryOrder.pickupDate }">
					<p class="text-muted">${laundryOrder.pickupDate}</p>
					</c:if>
					<c:if test="${empty laundryOrder.pickupDate }">
					<p class="text-muted">-</p>
					</c:if>
				</div>

				<div class="col-sm-6 invoice-col">
					<strong>배송요청일</strong><br>
					<p class="text-muted"><fmt:formatDate value="${laundryOrder.deliveryRequestDate }" pattern="yyyy-MM-dd"/></p>
					<hr>
					<strong>배송완료일</strong><br>
					<c:if test="${!empty laundryOrder.deliveryDate }">
					<p class="text-muted">${laundryOrder.pickupDate}</p>
					</c:if>
					<c:if test="${empty laundryOrder.deliveryDate }">
					<p class="text-muted">-</p>
					</c:if>
				</div>


			</div>

			<div class="row">
				<div class="col-12 table-responsive p-0">
					<table class="table table-striped m-0 card-secondary card-outline">
						<thead>
							<tr>
								<th colspan="4" style="text-align: end;">총 결제금액 : <fmt:formatNumber type="number" maxFractionDigits="3" value="${laundryOrder.totalPrice}" />원</th>
							</tr>
							<tr>
								<th>세탁물</th>
								<th>개수</th>
								<th style="text-align: end;">가격</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${laundryOrderDetailList }" var="laundryOrderDetail" >
								<tr>
									<td>${laundryOrderDetail.itemsName}</td>
									<td>${laundryOrderDetail.quantity}</td>
									<td style="text-align: end;"><fmt:formatNumber type="number" maxFractionDigits="3" value="${laundryOrderDetail.price}" />원</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>

			</div>
		</div>
	</div>
	
	<div class="col-6">
			<div class="card mt-1">
					<h4 class="m-3">담당지점 정보</h4>
				<div class="card-body p-0 card-secondary card-outline">
					<table class="table table-bordered">
						<tbody>
							<tr>
								<td style="background-color: #f7f5f5; width: 145px;">지점명</td>
								<td>${branchDetail.branchName }</td>
							</tr>

							<tr>
								<td style="background-color: #f7f5f5; width: 145px;">지점 주소</td>
								<td>${branchDetail.add1 } ${branchDetail.add2 }</td>
							</tr>

							<tr>
								<td style="background-color: #f7f5f5; width: 145px;">지점 연락처</td>
								<td>${branchDetail.branchContact }</td>
							</tr>

							<tr>
								<td style="background-color: #f7f5f5; width: 145px;">지점장명</td>
								<td>${branchDetail.name }</td>
							</tr>

							<tr>
								<td style="background-color: #f7f5f5; width: 145px;">지점장 연락처</td>
								<td>${branchDetail.phone }</td>
							</tr>

							<tr>
								<td style="background-color: #f7f5f5; width: 145px;">지점장 이메일</td>
								<td>${branchDetail.email }</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="card">
					<h4 class="m-3">주문관련문의</h4>
				<div class="card-body table-responsive p-0 card-secondary card-outline" style="height: 300px;">
					<table class="table table-head-fixed text-nowrap">
						<thead>
							<tr>
								<th>문의제목</th>
								<th>날짜</th>
								<th>답변여부</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>John Doe</td>
								<td>11-7-2014</td>
								<td>183</td>
							</tr>
							<tr>
								<td>John Doe</td>
								<td>11-7-2014</td>
								<td>183</td>
							</tr>

						</tbody>
					</table>
				</div>
			</div>
			
		</div>
		</div>
</body>
