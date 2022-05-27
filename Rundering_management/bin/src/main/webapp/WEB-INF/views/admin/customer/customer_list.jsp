<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="pageMaker" value="${dataMap.pageMaker }" />
<c:set var="cri" value="${dataMap.pageMaker.cri }" />
<c:set var="memberList" value="${dataMap.memberList }" />

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
			<h3 class="card-title" style="font-size: 1.75rem;">회원 관리</h3>

		</div>
		<div class="col-12 card">
			<div class="row">
				<div class="col-12 float-right" style="padding:5px;">
					<div class="input-group input-group-sm float-right" style="width: 400px; padding-top: 3px">

						<!-- sort -->
						<select class="form-control col-md-3" name="customerSort"
							id="customerSort" onchange="list_go(1);">
							<option value="01" ${cri.customerSort eq '01' ? 'selected' : '' }>전체회원</option>
							<option value="0" ${cri.customerSort eq '0' ? 'selected' : '' }>휴면회원</option>
							<option value="Y" ${cri.customerSort eq 'Y' ? 'selected' : '' }>탈퇴회원</option>
						</select>

						<!-- search bar -->
						<select class="form-control col-md-3" name="searchType"
							id="searchType">
							<option value="tc" ${cri.searchType eq 'tc' ? 'selected':'' }>전체</option>
							<option value="t" ${cri.searchType eq 't' ? 'selected':'' }>회원번호</option>
							<option value="c" ${cri.searchType eq 'c' ? 'selected':'' }>이름</option>
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

			<div class="card table-responsive p-0">
				<table class="table table-hover text-nowrap" style="text-align:center;">
					<thead>
						<tr>
							<th class="memberNo">회원번호</th>
							<th class="id">아이디</th>
							<th class="name">이름</th>
							<th class="phone">연락처</th>
							<th class="email">email</th>
							<th class="lastLogpsnHourLiver">마지막로그인</th>
							<th class="change">탈퇴상태</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${empty memberList }">
							<tr>
								<td colspan="5"><strong>해당 내용이 없습니다.</strong></td>
							</tr>
						</c:if>
						<c:if test="${!empty memberList }">
							<c:forEach items="${memberList }" var="member">
								<tr style='font-size: 0.85em;'>
									<td>${member.memberNo }</td>
									<td>${member.id }</td>
									<td>${member.name}</td>
									<td>${member.phone }</td>
									<td>${member.email }</td>
									<td><fmt:formatDate value="${member.lastLogpsnHourLiver }"
											pattern="yyyy-MM-dd hh:mm:ss" /></td>
									<td>
										<c:if test="${member.deleteYn eq 'Y' }">
											<button class="btn btn-warning btn-sm"
												onclick="modify_go('modify','${member.memberNo}');">변경</button>
										</c:if>
									</td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<div class="card-footer">
		<%@ include file="/WEB-INF/views/common/pagination.jsp"%>
	</div>
	
	
<script>

function modify_go(url,memberNo){
	   if(confirm("일반회원으로 변경하시겠습니까?")){
	      alert("변경되었습니다.")   
	   }
		window.opener.location.reload();
	}



</script>

</body>



