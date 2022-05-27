<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="pageMaker" value="${dataMap.pageMaker }" />
<c:set var="cri" value="${dataMap.pageMaker.cri }" />
<c:set var="itemOrderList" value="${dataMap.itemOrderList }" />

<title>지점 발주 내역</title>

<body>

<section class="content-header">
   <div class="container-fluid">
      <div class="row mb-2">
         <div class="col-sm-6">
            <h1>지점 발주 내역</h1>
         </div>
      </div>
   </div>
</section>

<div class="row ml-3 mr-3">
   <div class="col-12">
      <div class="card">
         <div class="card-header">

            <div class="input-group input-group-sm">
               <h2 style="height: 20px;" class="card-title">
                  <b>지점 발주 리스트</b>
               </h2>

               <div class="col-7"></div>
            </div>
            <div class="card-tools"></div>

         </div>
          <div class="card-body p-0" style="height: 600px;">
            <table style="text-align: center;" class="table text-nowrap">
               <thead>
                  <tr>
                   <th>발주번호</th>
					<th>지점명</th>
					<th>요청일</th>
					<th>처리상태</th>
                  </tr>
               </thead>
						<tbody>
							<c:if test="${empty itemOrderList }">
								<td colspan="4" style="text-align:center;"><strong>발주내역이 존재하지 않습니다.</strong></td>
							</c:if>
							<c:forEach items="${itemOrderList }" var="itemOrder">
								<tr
									onclick="window.open('<%=request.getContextPath()%>/admin/branchorder/detail?ordercode=${itemOrder.ordercode }', '발주 상세', 'width=700, height=900');"
									style="cursor: pointer;">
									<td>${itemOrder.ordercode }</td>
									<td>${itemOrder.branchCode }</td>
									<td>
										<fmt:formatDate value="${itemOrder.registDate }" pattern="	yy-MM-dd HH:mm" /> 
									</td>
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
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
					<div class="card-footer clearfix">
						<%@ include file="/WEB-INF/views/common/pagination.jsp"%>
					</div>
			</div>
		</div>
	</div>
</body>