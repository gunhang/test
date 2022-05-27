<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<head>
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/resources/bootstrap/plugins/summernote/summernote.min.css">
</head>

<title>건의사항 수정</title>

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
								class="fa fa-dashboard"></i>건의사항
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
						<form action="modify" method="post" role="modifyForm">
							<input type="hidden" name="sno" value="${suggest.sno }" />
							<div class="form-group">
								<label for="title">제 목</label> <input type="text" id="title"
									name='title' class="form-control" value="${suggest.title }">
							</div>
							<div class="form-group">
								<div class="row">
									<div class="col">
										<label for="writer">작성자 <input type="text" id="writer"
											readonly name="writer" class="form-control"
											value="${suggest.writer }"></label>
									</div>
									<div class="col">
										<label for="branchName">소속지점 <input type="text"
											id="branchName" readonly name="branchName"
											class="form-control" value="${suggest.branchName }"></label>
									</div>
									<div class="col">
										<label for="registDate">등록일 <input type="text"
											id="registDate" readonly name="registDate"
											class="form-control" value="${suggest.registDate }"></label>
									</div>
								</div>
								<label for="content">내 용</label>
								<div class="form-group">
									<textarea style="border: none; height: 100px; resize: none;"
										class="textarea" rows="25" cols="50" name="content" id="content">${fn:escapeXml(suggest.content)}</textarea>
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
		summernote_go($('textarea[name="content"]'),'<%=request.getContextPath()%>');	
   	}   
	
	 function modifyPOST_go(){
		 $("form[role='modifyForm']").submit();
	 }
   </script>
</body>
