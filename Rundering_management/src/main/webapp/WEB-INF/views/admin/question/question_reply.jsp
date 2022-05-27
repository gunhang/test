<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<head>
</head>

<title>문의 답변</title>

<body>
	<!-- Main content -->
	<section class="content-header">
		<div class="container-fluid">
			<div class="row md-2">
				<div class="col-sm-6">
					<h1></h1>
				</div>
				<div class="col-sm-6">
					<ol class="breadcrumb float-sm-right">
						<li class="breadcrumb-item"><a href="list.do"> <i
								class="fa fa-dashboard"></i>고객 문의
						</a></li>
						<li class="breadcrumb-item active">답변</li>
					</ol>
				</div>
			</div>
		</div>
	</section>

	<!-- Main content -->
	<section class="content container-fluid">
		<div class="row justify-content-center">
			<div class="col-md-10" style="max-width: 960px;">
				<div class="card card-outline card-info">
					<div class="card-header">
						<h3 class="card-title p-1">답변하기</h3>
					</div>
					<!--end card-header  -->
					<div class="card-body pad">
						<form action="reply" method="post" role="replyForm" name="replyForm">
							<input type="hidden" name="faqno" value="${faq.faqno }" />
							<div class="form-group">
								<label for="question">제 목</label> <input type="text" id="question" readonly
									name='question' class="form-control" value="${faq.question }">
							</div>
							<div class="form-group">
								<div class="row">
									<div class="col">
										<label for="writer">요청자 <input type="text"
											id="writer" readonly name="writer"
											class="form-control" value="${faq.writer }"></label>
									</div>
									<div class=" col">
										<label for="setbukdoorclcode">카테고리
											구분 <input type="text"
											id="setbukdoorclcode" readonly name="setbukdoorclcode"
											class="form-control" value="<c:choose>
												<c:when test="${faq.setbukdoorclcode == 'OR'}">주문문의</c:when>
												<c:when test="${faq.setbukdoorclcode == 'US'}">이용문의</c:when>
												<c:when test="${faq.setbukdoorclcode == 'ET'}">기타문의</c:when>
											   </c:choose>"></label>
									</div>
									<div class="col">
										<label for="registDate">등록일 <input type="text"
											id="registDate" readonly name="registDate"
											class="form-control" value="${faq.registDate }"></label>
									</div>
								</div>
								<div>						
								<label for="answer">답변하기</label>
								<input type="text" id="answer"
									name='answer' class="form-control" value="${faq.answer }">
							</div></div>
						</form>
					</div>
					<!--end card-body  -->
					<div class="card-footer">
						<div class="float-right">
							<button type="button" class="btn btn-primary" id="replyBtn"
								onclick="replyPOST_go();">완료</button>
							&nbsp;&nbsp;
							<button type="button" class="btn btn-warning" id="cancelBtn"
								onclick="history.go(-1);">취소</button>
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
		function replyPOST_go(){
			 $("form[role='replyForm']").submit();
		 }
	</script>

</body>
