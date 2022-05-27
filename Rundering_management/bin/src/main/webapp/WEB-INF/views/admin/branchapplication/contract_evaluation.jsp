<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="pageMaker" value="${dataMap.pageMaker }" />
<c:set var="cri" value="${dataMap.pageMaker.cri }" />
<c:set var="branchApplicationList" value="${dataMap.branchApplicationList }" />
<body>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<section class="content-header">
	<div class="container-fluid">
		<div class="row mb-2">
			<div class="col-sm-6">
				<h1>지점 등록 관리</h1>
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
						<b>지점 신청 리스트</b>
					</h2>

					<div class="col-7"></div>
				</div>
				<div class="card-tools"></div>

			</div>

			<div class="card-body p-0">
				<table style="text-align: center;" class="table text-nowrap">
					<thead>
						<tr>
							<th class="width10">이름</th>
							<th class="WID">전화번호</th>
							<th>이메일</th>
							<th>상태</th>
						</tr>
					</thead>
					<tbody>
					<c:if test="${empty branchApplicationList }">
						<tr>
							<td colspan="5"><strong>해당 내용이 없습니다.</strong></td>
						</tr>
					</c:if>
						<c:forEach items="${branchApplicationList }" var="branchApplication">
							<tr>
								<td class="list"  style="vertical-align: middle" >${branchApplication.applicateName }</td>
								<td style="vertical-align: middle" >${branchApplication.phone }</td>
								<td style="vertical-align: middle">${branchApplication.email }</td>
								<td style="vertical-align: middle;padding-bottom: 6px;padding-top: 6px; text-align: left;">
									<c:if test="${branchApplication.progressStatusCode eq '01'}"  var="1">
										<button type="button" class="btn btn-outline-danger btn-sm" onclick="branch_request(false,'${branchApplication.applicationNo}')">신청승인</button>
									</c:if>
									
									<c:if test="${branchApplication.progressStatusCode eq '02'}" var="2">
										<button type="button" class="btn btn-outline-danger btn-sm" onclick="branch_request(true,'${branchApplication.applicationNo}')">신청반려</button>
									</c:if>
	
									<c:if test="${branchApplication.progressStatusCode eq '03'}" var="3">
										<button type="button" class="btn btn-outline-success btn-sm" onclick="branch_request(true,'${branchApplication.applicationNo}')">신청승인</button>
									</c:if>
	
									<c:if test="${branchApplication.progressStatusCode eq '04'}" var="4">
										<button type="button" class="btn btn-outline-success btn-sm" onclick="branch_request(true,'${branchApplication.applicationNo}')">신청승인</button>
										<button type="button" class="btn btn-outline-danger btn-sm" onclick="branch_examine(false,'${branchApplication.applicationNo}')">심사승인</button>
									</c:if>
									
									<c:if test="${branchApplication.progressStatusCode eq '05'}" var="5">
										<button type="button" class="btn btn-outline-success btn-sm" onclick="branch_request(true,'${branchApplication.applicationNo}')">신청승인</button>
										<button type="button" class="btn btn-outline-danger btn-sm" onclick="branch_examine(true,'${branchApplication.applicationNo}')">심사반려</button>
									</c:if>
									
									<c:if test="${branchApplication.progressStatusCode eq '06' }" var="6">
										<button type="button" class="btn btn-outline-success btn-sm" onclick="branch_request(true,'${branchApplication.applicationNo}')">신청승인</button>
										<button type="button" class="btn btn-outline-success btn-sm" onclick="branch_examine(true,'${branchApplication.applicationNo}')">심사승인</button>
									</c:if>
									
									<c:if test="${branchApplication.progressStatusCode eq '07' }" var="7">
										<button type="button" class="btn btn-outline-success btn-sm" onclick="branch_request(true,'${branchApplication.applicationNo}')">신청승인</button>
										<button type="button" class="btn btn-outline-success btn-sm" onclick="branch_examine(true,'${branchApplication.applicationNo}')">심사승인</button>
										<button type="button" class="btn btn-outline-danger btn-sm" onclick="branch_voluntary(false,'${branchApplication.applicationNo}')">수의계약신청</button>
									</c:if>
								
									
									<c:if test="${branchApplication.progressStatusCode eq '08' }" var="9">
										<button type="button" class="btn btn-outline-success btn-sm" onclick="branch_request(true,'${branchApplication.applicationNo}')">신청승인</button>
										<button type="button" class="btn btn-outline-success btn-sm" onclick="branch_examine(true,'${branchApplication.applicationNo}')">심사승인</button>
										<button type="button" class="btn btn-outline-success btn-sm" onclick="branch_voluntary(true,'${branchApplication.applicationNo}')">수의계약확인</button>
									</c:if>
									<c:if test="${branchApplication.progressStatusCode eq '09' }" var="09">
										<button type="button" class="btn btn-outline-success btn-sm" onclick="branch_request(true,'${branchApplication.applicationNo}')">신청승인</button>
										<button type="button" class="btn btn-outline-success btn-sm" onclick="branch_examine(true,'${branchApplication.applicationNo}')">심사승인</button>
										<button type="button" class="btn btn-outline-success btn-sm" onclick="branch_voluntary(true,'${branchApplication.applicationNo}')">수의계약확인</button>
										<button type="button" class="btn btn-outline-danger btn-sm" onclick="branch_enrollment(false,'${branchApplication.applicationNo}')">지점등록</button>
									</c:if>
									
									<c:if test="${branchApplication.progressStatusCode eq '10' }" var="10">
										<button type="button" class="btn btn-outline-success btn-sm" onclick="branch_request(true,'${branchApplication.applicationNo}')">신청승인</button>
										<button type="button" class="btn btn-outline-success btn-sm" onclick="branch_examine(true,'${branchApplication.applicationNo}')">심사승인</button>
										<button type="button" class="btn btn-outline-success btn-sm" onclick="branch_voluntary(true,'${branchApplication.applicationNo}')">수의계약확인</button>
										<button type="button" class="btn btn-outline-success btn-sm" onclick="branch_request_complate('${branchApplication.applicationNo}')">지점등록</button>
									</c:if>
									
								</td>
							</tr>
						</c:forEach>
							
					</tbody>
				</table>
			</div>
			<div class="card-footer">
				<%@ include file="./pagination.jsp"%>
			</div>
		</div>
	</div>
</div>


<script	src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.7.7/handlebars.min.js"></script>
<div id="handleTag">
	
	
</div>


<%@ include file="./request_js.jsp" %>
<%@ include file="./enrollment_js.jsp" %>
<%@ include file="./examine_js.jsp" %>
<%@ include file="./voluntary_js.jsp" %>
<%@ include file="./branchRequestComplate.jsp" %>
			
<script>
	
</script>	


</body>


