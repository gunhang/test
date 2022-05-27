<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<body>

	<section class="content-header">
		<div class="container-fluid">
			<div class="row mb-2">
				<div class="col-sm-6">
					<h1>공지사항 수정하기</h1>
				</div>
				<div class="col-sm-6">
					<ol class="breadcrumb float-sm-right">
						<li class="breadcrumb-item"><a href="#">Home</a></li>
						<li class="breadcrumb-item active">공지사항 수정하기</li>
					</ol>
				</div>
			</div>
		</div>
	</section>

	<div class="card card-primary card-outline">
		<div class="card-body">
			<form action="modify" method="post" role="modifyForm">
				<input type="hidden" name="noticeno" value="${notice.noticeno }" />
				<div class="form-group">
					<input class="form-control" placeholder="제목" name="title"
						value="${notice.title }">
				</div>
				<label for="content">내 용</label>
				<textarea class="textarea" name="content" id="content" rows="20"
					placeholder="1000자 내외로 작성하세요." style="display: none;">${notice.content }</textarea>
			</form>
		</div>

		<div class="card-footer">
			<div class="float-right">
				<button onclick="history.go(-1)" class="btn btn-warning">뒤로가기</button>
				<button type="submit" class="btn btn-primary"
					onclick="modifyPOST_go();">수정완료</button>
			</div>
		</div>
	</div>

	<script>
	window.onload=function(){
	summernote_go($('textarea[name="content"]'), '<%=request.getContextPath()%>');
		}

		function modifyPOST_go() {
			//alert("modify btn click");
			$("form[role='modifyForm']").submit();
		}
	</script>
	
	

</body>
