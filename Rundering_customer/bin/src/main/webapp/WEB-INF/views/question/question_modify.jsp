<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="orderList" value="${dataMap.orderList }" />

<head>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/bootstrap/plugins/summernote/summernote.min.css">
</head>

<title>문의 수정</title>

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
								class="fa fa-dashboard"></i>문의 하기
						</a></li>
						<li class="breadcrumb-item active">수정</li>
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
						<h3 class="card-title p-1">수정하기</h3>
					</div>
					<!--end card-header  -->
					<div class="card-body pad">
						<form action="<%=request.getContextPath()%>/question/modify" method="post" role="modifyForm" name="modifyForm">
							<input type="hidden" name="faqno" value="${faq.faqno }" />
							<div class="form-group">
								<label for="question">제 목</label> <input type="text" id="question"
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
											구분</label> <select id="setbukdoorclcode" name="setbukdoorclcode"
											class="form-control">
											<option value="US">이용 문의</option>
											<option value="OR">주문 문의</option>
											<option value="ET">기타 문의</option>
										</select>
									</div>
									<div class="col">
										<label for="registDate">등록일 <input type="text"
											id="registDate" readonly name="registDate"
											class="form-control" value="${faq.registDate }"></label>
									</div>
									<div class="col">
										<label for="secretyn">공개여부</label>
										<div class="form-group">
											<div style="margin-left: 10px; margin-top: 10px; font-size: large;">
												<input type="radio" name="secretyn" id="secretyn" value="N" />
												공개 &nbsp;&nbsp;
												<input type="radio" name="secretyn" id="secretyn" value="Y" />
												비공개
											</div>
										</div>
									</div>
								</div>								
								<label for="fcontent">내 용</label>
								<div class="form-group">
									<textarea style="border: none; height: 100px; resize: none;"
										class="textarea" rows="25" cols="50" id="fcontent" name="fcontent">${fn:escapeXml(faq.fcontent)}</textarea>
								</div>
							</div>
						</form>
					</div>
					<!--end card-body  -->
					<div class="card-footer">
						<div class="float-right">
							<button type="button" class="btn btn-primary" id="modifyBtn"
								onclick="modifyPOST_go();">수정</button>
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
	window.onload=function(){
		summernote_go($('textarea[name="fcontent"]'), '<%=request.getContextPath()%>');
	}
	
	function modifyPOST_go(){
		 $("form[role='modifyForm']")
		 var form = document.modifyForm;
		 if (form.secretyn.value == "") {
				alert("공개여부를 선택하세요.");
				return;
			}
			form.submit();
	 }
	</script>

</body>
