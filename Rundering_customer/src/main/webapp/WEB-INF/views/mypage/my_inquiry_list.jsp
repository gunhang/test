<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="pageMaker" value="${dataMap.pageMaker }" />
<c:set var="cri" value="${dataMap.pageMaker.cri }" />
<c:set var="faqList" value="${dataMap.faqList }" />

<head>
<style>
.outer {
	display: flex;
	justify-content: center;
}

th, td {
	text-align: center;
}

.no {
	width: 10%;
}

.category {
	width: 15%
}

.title {
	width: 22%;
}

.writer {
	width: 18%;
}

.date {
	width: 25%;
}

.yn {
	width: 10%;
}
</style>
</head>

<body>
	<div style="width: 60%; display: flex; flex-direction: column; margin-left: 20%;">
		<section class="content-header">
			<div class="container-fluid">
				<div class="row mb-2">
					<div class="col-sm-6">
						<h1>문의내역</h1>
					</div>
					<div class="col-sm-6">
						<ol class="breadcrumb float-sm-right">
							<li class="breadcrumb-item active">문의내역</li>
							<li class="breadcrumb-item"><a href="#">마이페이지</a></li>
						</ol>
					</div>
				</div>
			</div>
		<hr style="border: 1px solid rgb(170, 167, 167);">
		</section>


	<div class="col-12">
		<div class="card">

			<div class="card-body table-responsive p-0">
				<table class="table table-hover text-nowrap">
					<thead>
						<tr>
							<th class="no">번호</th>
							<th class="category">카테고리</th>
							<th class="title">제목</th>
							<th class="date">문의일</th>
						</tr>
					</thead>
					<c:if test="${empty faqList }">
						<tr>
							<td colspan="5"><strong>해당 내용이 없습니다.</strong></td>
						</tr>
					</c:if>
					<c:forEach items="${faqList }" var="faq">
						<tr	onclick="OpenWindow('detail?from=list&faqno=${faq.faqno }','상세보기',900,700);">
							<td class="no">${faq.faqno }</td>
							<td class="category">${faq.setbukdoorclcode }</td>
							<td class="title">${faq.question }</td>
							<td class="date"><fmt:formatDate value="${faq.registDate }" pattern="yyyy-MM-dd" /></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>

		<div class="card-footer" style="font-size: 0.9em">
			<%@ include file="/WEB-INF/views/common/pagination.jsp"%>
		</div>
	</div>
</div>
</body>