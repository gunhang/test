<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="faqorderno" value="${faq.orderno }" />
<c:set var="faqanswer" value="${faq.answer }" />

<head>
</head>

<title>상세 보기</title>

<body>
	<!-- Main content -->
	<section class="content-header">
		<div class="container-fluid">
			<div class="row md-2">
				<div class="col-sm-6"></div>
			</div>
		</div>
	</section>

	<!-- Main content -->
	<section class="content container-fluid">
		<div class="row justify-content-center">
			<div class="col-md-10" style="max-width: 960px;">
				<div class="card card-secondary card-outline ">
					<div class="card-header">
						<h2 class="card-title p-1"><b>${faq.question }</b></h2>
						<c:choose>
							<c:when test="${not empty faqorderno }">
								<label class="float-right" for="orderno">주문번호 : ${faq.orderno }</label>
							</c:when>
							<c:when test="${empty faqorderno }"></c:when>
						</c:choose>
					</div>
					<!--end card-header  -->
					<div class="card-body pad col-12">
						<div class="form-group">
							<div class="col">
								
							</div>
						</div>
						<div class="form-group ">
							<div class="row">
								<div class="col">
									<label for="writer">작성자 : ${faq.writer }</label>
								</div>
								<div class="col">
									<label for="setbukdoorclcode">카테고리 : 
										<c:choose>
											<c:when test="${faq.setbukdoorclcode == 'OR'}">주문문의</c:when>
											<c:when test="${faq.setbukdoorclcode == 'US'}">이용문의</c:when>
											<c:when test="${faq.setbukdoorclcode == 'ET'}">기타문의</c:when>
										</c:choose>
									</label>
								</div>
								<div class="col">
									<label for="registDate">등록일 : ${faq.registDate }</label>

								</div>
							</div>
						</div>
						<div class="form-group">
							<label for="fcontent">문의 내용</label>
							<div>${faq.fcontent }</div>
						</div>
						<div class="form-group">
							<c:choose>
								<c:when test="${not empty faqanswer }">
									<label for="answer">문의 답변</label>
									<div>${faq.answer }</div>
								</c:when>
								<c:when test="${empty faqanswer }">
								</c:when>
							</c:choose>
						</div>
					</div>
					<!--end card-body  -->
					<div class="card-footer">
						<div class="float-right">
							<button type="button" class="btn btn-danger" id="removeBtn"
								onclick="remove_go('${faq.faqno}');">삭제</button>
							&nbsp;&nbsp;
							<button type="button" class="btn btn-warning" id="modifyBtn"
								onclick="modify_go('${faq.faqno}');">수정</button>
							&nbsp;&nbsp;
							<button type="button" class="btn btn-primary" id="cancelBtn"
								onclick="CloseWindow();">닫기</button>
						</div>
					</div>
					<!--end card-footer  -->
				</div>
				<!-- end card -->
			</div>
			<!-- end col-md-12 -->
		</div>
		<!-- end row -->
	</section>
	<!-- /.content -->
	<script>
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