<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="pageMaker" value="${dataMap.pageMaker }" />
<c:set var="cri" value="${dataMap.pageMaker.cri }" />
<c:set var="employeesList" value="${dataMap.employeesList }" />

<head>
<style>
.no {
	width: 10%;
}

.boardtitle {
	width: 35%;
}

.writer {
	width: 20%;
}

.date {
	width: 25%;
}

.clicks {
	width: 10%;
}

#body {
	padding-left: 1%;
	padding-right: 1%;
}
</style>
</head>
<body>
	<div class="card card-secondary card-outline ml-3 mr-3">
		<div class="card-header" style="border-bottom: 0px;">
			<h3 class="card-title" style="font-size: 1.75rem;">사원 조회</h3>
			<div class="row">
				<div class="col-12 float-right" style="padding:5px;">
					<div class="input-group input-group-sm float-right" style="width: 400px; padding-top: 3px">
						<!-- sort -->
						<select class="form-control col-md-3" name="employeeSort"
							id="employeeSort" onchange="list_go(1);">
							<option value="" ${cri.employeeSort eq '' ? 'selected' : '' }>전체지점</option>
							<option value="000000" ${cri.employeeSort eq '000000' ? 'selected' : '' }>본사</option>
							<option value="060402" ${cri.employeeSort eq '060402' ? 'selected' : '' }>관평점</option>
							<option value="060101" ${cri.employeeSort eq '060101' ? 'selected' : '' }>가양점</option>
							<option value="060201" ${cri.employeeSort eq '060201' ? 'selected' : '' }>오류점</option>
							<option value="060301" ${cri.employeeSort eq '060301' ? 'selected' : '' }>둔산점</option>
							<option value="060401" ${cri.employeeSort eq '060401' ? 'selected' : '' }>봉명점</option>
							<option value="060501" ${cri.employeeSort eq '060501' ? 'selected' : '' }>대화점</option>
						</select>
	
						<!-- search bar -->
						<select class="form-control col-md-3" name="searchType"
							id="searchType">
							<option value="tc" ${cri.searchType eq 'tc' ? 'selected':'' }>전체사원</option>
							<option value="t" ${cri.searchType eq 't' ? 'selected':'' }>이름</option>
							<option value="c" ${cri.searchType eq 'c' ? 'selected':'' }>연락처</option>
						</select> <input class="form-control" type="text" name="keyword"
							placeholder="검색어를 입력하세요." value="" /> <span
							class="input-group-append">
							<button class="btn btn-primary" type="button"
								onclick="list_go(1);" data-card-widget="search">
								<i class="fa fa-fw fa-search"></i>
							</button>
						</span>
					</div>
				</div>
			</div>
		</div>
		<table class="table table-hover text-nowrap" style="text-align:center;">
			<thead>
				<tr>
					<th class="employeeId">사원번호</th>
					<th class="branchCode">지점</th>
					<th class="department">부서</th>
					<th class="position">직책</th>
					<th class="joiningCompanyDate">입사일</th>
					<th class="name">이름</th>
					<th class="phone">연락처</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${empty employeesList }">
					<tr>
						<td colspan="7"><strong>해당 내용이 없습니다.</strong></td>
					</tr>
				</c:if>
				<c:forEach items="${employeesList }" var="employee">
					<tr style='font-size: 0.85em;'>
						<td>${employee.employeeId }</td>
						<td>${employee.branchCode }</td>
						<td>${employee.department}</td>
						<td>${employee.position }</td>
						<td><fmt:formatDate value="${employee.joiningCompanyDate }" pattern="yyyy-MM-dd" /></td>
						<td>${employee.name }</td>
						<td>${employee.phone }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="card-footer">
			<%@ include file="/WEB-INF/views/admin/employee/pagination.jsp"%>
		</div>
	</div>
<script>



</script>

</body>



