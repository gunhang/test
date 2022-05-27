<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>

<head>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/bootstrap/plugins/summernote/summernote.min.css">
</head>

<body>
	<div class="col-md-12">
		<div class="card card-primary card-outline">
			<div class="card-header">
				<h3 class="card-title">건의사항 작성</h3>
			</div>
			<form role="form" action="regist" method="post" name="registForm">
				<div class="card-body p-0">
					<div class="form-group">
						<label for="title"
							style="margin-left: 10px; margin-top: 10px; font-size: large;">제목</label>
							<input type="text" name="title" id="title" class="form-control"
							placeholder="제목을 입력하세요">
					</div>
					<div class="row">
						<div class="col">
							<div class="form-group">
								<label for="writer" style="margin-left: 10px; font-size: large;">작성자</label>
								<input type="text" id="writer" readonly name="writer"
									class="form-control" value="${loginEmployee.employeeId }">
							</div>
						</div>
						<div class="col">
							<div class="form-group">
								<label for="branchName"
									style="margin-left: 10px; font-size: large;">소속지점</label> <input
									type="text" id="branchName" readonly name="branchName"
									class="form-control" value="${loginEmployee.branchCode }">
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="content" style="margin-left: 10px; font-size: large;">내용</label>
						<textarea style="border: none; height: 100px; resize: none;"
							class="textarea" rows="10" cols="20" id="content" name="content"></textarea>
					</div>
				</div>
			</form>
			<div class="card-footer">
				<div class="float-right">
					<button onclick="history.go(-1)" class="btn btn-warning">뒤로가기</button>
					<button onclick="regist_go()" type="submit" id="registBtn" class="btn btn-primary">등록하기</button>
				</div>
			</div>
		</div>
	</div>

	<script>
	window.onload=function(){
		summernote_go($('textarea[name="content"]'),'<%=request.getContextPath()%>');	
   	} 
	</script>

	<script>
		function regist_go() {
			var form = document.registForm;
			if (form.title.value == "") {
				alert("제목은 필수입니다.");
				return;
			}
			alert("요청 등록이 성공했습니다.");
			form.submit();
		}
	</script>

</body>
