<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
	width: 15%;
}

.yn {
	width: 10%;
}
.answer {
	width: 10%
}
</style>
</head>

<body>
	<section class="content-header">
		<div class="container-fluid">
			<div class="row mb-2">
				<div class="col-sm-6">
					<h1>문의게시판</h1>
				</div>
				<div class="col-sm-6">
					<ol class="breadcrumb float-sm-right">
						<li class="breadcrumb-item active">문의게시판</li>
						<li class="breadcrumb-item"><a href="#">Home</a></li>
					</ol>
				</div>
			</div>
		</div>
	</section>

	<div class="col-12">
		<div class="card">
			<div class="card-header">
				<div class="card-tools">
					<div class="input-group input-group-sm" style="width: 300px;">
						<select class="form-control col-md-4" name="searchType"
							id="searchType">
							<option value="tc" ${cri.searchType eq 'tc' ? 'selected':'' }>전체</option>
							<option value="t" ${cri.searchType eq 't' ? 'selected':'' }>제목</option>
							<option value="c" ${cri.searchType eq 'c' ? 'selected':'' }>내용</option>
							<%-- <option value="w" ${cri.searchType eq 'w' ? 'selected':'' }>질문자</option> --%>
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

			<div class="card-body table-responsive p-0">
				<table class="table table-hover text-nowrap">
					<thead>
						<tr>
							<th class="no">번호</th>
							<th class="category">카테고리</th>
							<th class="title">제목</th>
							<th class="writer">작성자</th>
							<th class="date">문의일</th>
							<th class="yn">비밀글</th>
							<th class="answer">답변</th>
						</tr>
					</thead>
					<c:if test="${empty faqList }">
						<tr>
							<td colspan="5"><strong>해당 내용이 없습니다.</strong></td>
						</tr>
					</c:if>
					<c:forEach items="${faqList }" var="faq">
						<tr style='cursor: pointer;'
							onclick="OpenWindow('detail?from=list&faqno=${faq.faqno }','상세보기',900,700);">
							<td class="no">${faq.faqno }</td>
							<td class="category"><c:choose>
												<c:when test="${faq.setbukdoorclcode == 'OR'}">주문문의</c:when>
												<c:when test="${faq.setbukdoorclcode == 'US'}">이용문의</c:when>
												<c:when test="${faq.setbukdoorclcode == 'ET'}">기타문의</c:when>
											   </c:choose></td>
							<td class="title" style="text-align: inherit;">${faq.question }</td>
							<td class="writer">${faq.writer }</td>
							<td class="date">${faq.registDate }</td>
							<td class="yn"><c:choose>
												<c:when test="${faq.secretyn eq 'Y'}">
													<i class="fas fa-lock fa-fw" style="color: var(- -fa-navy);"></i>
												</c:when>
												<c:when test="${faq.secretyn eq 'N'}">
												</c:when>
											</c:choose></td>
							<td class="answer"><c:choose>
									<c:when test="${!empty faq.answer}">
										답변완료
									</c:when>
							</c:choose>
						</tr>
					</c:forEach>
				</table>
				<div class="float-right mt-3 mr-3 mb-3">

					<button class="btn btn-primary" type="button" id="registBtn"
						onclick="loginyn()">
						문의하기</button>
					<%-- <button class="btn btn-primary" type="button" id="registBtn"
						onclick="location.href='<%=request.getContextPath()%>/question/registForm'">
						문의하기</button> --%>
				</div>
			</div>
		</div>

		<div class="card-footer" style="font-size: 0.9em">
			<%@ include file="/WEB-INF/views/common/pagination.jsp"%>
		</div>
	</div>
	
<!-- 	알럽트 js -->
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.1.9/dist/sweetalert2.all.min.js"></script>
	
	
	<script>
		function loginyn(){
			let loginUser = "${loginUser}";			
			if (loginUser == ""){				
				event.preventDefault();	//submit 이벤트를 막아 페이지 리로드를 방지
				Swal.fire({
	               title: '로그인 후 사용가능합니다.',
	               icon : 'warning' ,
	               confirmButtonColor: '#3085d6',
	               confirmButtonText: '확인',
	               reverseButtons: true,
	               }).then((result) => {
	            	   if (result.isConfirmed){
	            		   location.href="<%=request.getContextPath()%>/login/form";
	            		   }
	            	   })
			} else {
				location.href="<%=request.getContextPath()%>/question/registForm";
			}
		}
	</script>
</body>

