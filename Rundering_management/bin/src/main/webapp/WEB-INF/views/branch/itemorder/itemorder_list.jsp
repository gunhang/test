<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>\
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="pageMaker" value="${dataMap.pageMaker }" />
<c:set var="cri" value="${dataMap.pageMaker.cri }" />
<c:set var="itemOrderList" value="${dataMap.itemOrderList }" />
<c:set var="comCode" value="${dataMap.comCodeMap }"/>
<c:set var="totalPrice" value="${dataMap.totalPriceMap}"/>

<body>
<c:if test="${from eq 'remove' }">
	<script>
		alert("취소되었습니다.");
		window.close();
		window.opener.location.reload();
	</script>
</c:if>
		<div class="row ml-2 mr-2">
			<div class="col-12">
				<div class="card card-primary card-outline">
					<div class="card-header">
						<h3 class="card-title">발주내역</h3>
						<div class="card-tools">
							
							<div class="input-group input-group-sm">
								<div class="input-group-sm selectWidth">
									<select class="form-control " name="searchType" id="searchType">
										<option value="n" ${cri.searchType eq 'n' ? 'selected':'' }>전체</option>
									<c:forEach var="i" items="${ comCode}">
									
									<option value="${i.key}" ${cri.searchType eq i.key ? 'selected':'' }>${ i.value}</option>
									
									</c:forEach>
									</select>
								</div>
								<span class="input-group-append">
									<button class="btn btn-primary" type="button"
										onclick="list_go(1);" data-card-widget="search">
										<i class="fa fa-fw fa-search"></i>
									</button>
								</span>
`							</div>
						</div>
					</div>

					<div class="card-body p-0">
						<table class="table table-hover text-nowrap">
							<thead>
								<tr style="text-align: center;">
									<th class="width25">발주번호</th>
									<th class="width30">총 가격</th>
									<th class="width25">발주신청일</th>
									<th class="width20">상태</th>
								</tr>
							</thead>
							
							
							
							<tbody>
							<c:if test="${empty itemOrderList }" >
								<tr>
									<td colspan="5">
										<strong>해당 내용이 없습니다.</strong>
									</td>
								</tr>
							</c:if>				
					<c:forEach items="${itemOrderList }" var="itemOrder">
						<tr style='cursor:pointer;' onclick="OpenWindow('detail.do?ordercode=${itemOrder.ordercode }','상세보기',800,700);">
							<td style="text-align: center;">${itemOrder.ordercode }</td>
							<td style="text-align: right;">${totalPrice[itemOrder.ordercode] }원</td>
							<td style="text-align: center;"><fmt:formatDate value="${itemOrder.registDate }" pattern="yyyy-MM-dd"/></td>			
							<td style="text-align: center;">
								<%-- ${comCode[itemOrder.itemOrderStatus] } --%>
								<c:if test="${itemOrder.itemOrderStatus eq '01'}">
										<td><span class="badge badge-warning">승인대기</span></td>
									</c:if>
									<c:if test="${itemOrder.itemOrderStatus eq '02'}">
										<td><span class="badge badge-success">발주대기</span></td>
									</c:if>
									<c:if test="${itemOrder.itemOrderStatus eq '03'}">
										<td><span class="badge badge-warning">미수령</span></td>
									</c:if>
									<c:if test="${itemOrder.itemOrderStatus eq '04'}">
										<td><span class="badge badge-success">수령</span></td>
									</c:if>
									<c:if test="${itemOrder.itemOrderStatus eq '05'}">
										<td><span class="badge badge-danger">반려</span></td>
									</c:if>	
							<td>	
								
						</tr>
					</c:forEach>
								
							</tbody>
						</table>
					</div>
					<div class="card-footer">
					 	<%@ include file="/WEB-INF/views/common/pagination.jsp"%>
					</div>
				</div>

			</div>
		</div>
</body>
