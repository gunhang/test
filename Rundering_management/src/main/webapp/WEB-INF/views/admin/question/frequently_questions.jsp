<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="pageMaker" value="${dataMap.pageMaker }" />
<c:set var="cri" value="${dataMap.pageMaker.cri }" />
<c:set var="frequentlyList" value="${dataMap.frequentlyList }" />

<head>
<style>
.content-header {
	padding: 10px 30px;
	padding-bottom: 1px;
}
</style>
</head>

<body>
	<section class="content-header">
		<div class="container-fluid">
			<div class="row mb-2">
				<div class="col-sm-6">
					<h1>자주 묻는 질문(FAQ)</h1>
				</div>
				<div class="col-sm-6">
					<ol class="breadcrumb float-sm-right">
						<li class="breadcrumb-item active">FAQ</li>
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
							<option value="t" ${cri.searchType eq 't' ? 'selected':'' }>제목</option>
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


			<div class="card-body">
				<div class="col-12" id="accordion" style='cursor: pointer;'>
					<c:forEach items="${frequentlyList }" var="faq">
						<div class="card card-default card-outline">
							<a class="d-block w-100 collapsed" data-toggle="collapse"
								href="#abc${faq.faqno }" aria-expanded="false">
								<div class="card-header">
									<div class="row">
										<div class="col-sm-8">
											<h4 class="card-title w-100" style="color: black;">${faq.question }
											</h4>
										</div>
									</div>
								</div>
							</a>
							<div id="abc${faq.faqno }" class="collapse"
								data-parent="#accordion" style="">
								<div class="card-body">
									<div class="row">
										<div class="col">${faq.answer }</div>
										<div class="float-right">
											<button type="button" id="modifyBtn" class="btn btn-warning"
												style="padding: 0.25rem 0.5rem; font-size: .875rem; line-height: 1.5;"
												onclick="modify_go('${faq.faqno}');">수정</button>
											<button type="button" id="removeBtn" class="btn btn-danger"
												style="padding: 0.25rem 0.5rem; font-size: .875rem; line-height: 1.5;"
												onclick="remove_go('${faq.faqno}');">삭제</button>
										</div>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>

			<!--end card-body  -->
			<div class="content-footer">
				<div>
					<%@ include file="/WEB-INF/views/admin/question/pagination.jsp"%>
				</div>
				<div class="card-footer">
					<form role="form" action="insert" method="post" name="insert">
						<div>
							<label for="question">질문입력</label> <input type="text"
								id="question" name='question' class="form-control"
								value="Q. ${faq.question }">
						</div>
						<div>
							<label for="answer">답변입력</label> <input type="text" id="answer"
								name='answer' class="form-control" value="A. ${faq.answer }">
						</div>
						<div class="float-right mt-3 mr-3 mb-3">
							<button class="btn btn-primary" onclick="regist_go()"
								type="submit" id="insertBtn" class="btn btn-primary">추가하기</button>
						</div>
					</form>
				</div>
			</div>

			<script>
				function regist_go() {
					var form = document.insert;
					alert("등록되었습니다.");
					form.submit();
					window.opener.location.reload();
				}
				function modify_go(faqno) {
					location.href = "modifyForm?faqno=" + faqno;
				}
				function remove_go(faqno) {
					location.href = "remove?faqno=" + faqno;
				}
				<c:if test="${from eq 'modify' }">
				alert("수정되었습니다.");
				</c:if>
				<c:if test="${from eq 'remove'}">
				alert("삭제되었습니다.");
				window.close();
				window.opener.location.reload();
				</c:if>
			</script>
</body>