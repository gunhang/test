<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="">
</c:if>


<head>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/bootstrap/plugins/summernote/summernote.min.css">
</head>

<body>

	<div class="col-md-12">
		<div class="card card-primary card-outline">
			<div class="card-header">
				<h3 class="card-title">AS 요청</h3>
			</div>
			<form role="form" action="regist" method="post" name="registForm">
				<div class="card-body p-0">
					<div class="form-group">
						<label for="title"
							style="margin-left: 10px; margin-top: 10px; font-size: large;">제목</label>
						<input type="text" name="title" id="title" class="form-control"
							placeholder="ㅇ호 물품명 고장">
					</div>
					<div class="row">
						<div class="col">
							<div class="form-group">
								<label for="employeeId" style="margin-left: 10px; font-size: large;">요청자</label>
								<input type="text" id="employeeId" readonly name="employeeId"
									class="form-control" value="${loginEmployee.employeeId }">
							</div>
							</div>
							<div class="col">
							<div class="form-group">
								<label for="branchCode"	style="margin-left: 10px; font-size: large;">소속지점</label>
								<input type="text" id="branchCode" readonly name="branchCode"
									class="form-control" value="${loginEmployee.branchCode }">
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="row">
							<%-- div class=" col">
								<label for="articlesCode"
									style="margin-left: 10px; font-size: large;">물품
									<select	class="form-control" name="articlesCode" id="articlesCode"
									style=" width: 200px; margin-top: 6px;">
									<option selected>선택해주세요</option>
									<c:forEach items="${getItemList }" var="code" >
										<option><c:out value="${code.articlesCode }" /></option>
									</c:forEach>
								</select></label> 
							</div> --%>
							<div class=" col">
								<label for="articlesCode"
									style="margin-left: 10px; font-size: large;">물품<select
									class="form-control" name="articlesCode" id="articlesCode"
									style=" width: 200px; margin-top: 6px;">
									<option value="A001">세탁기</option>
									<option value="A002">건조기</option>
									<option value="A003">에어컨</option>
								</select></label> 
							</div>
							<div class=" col">
								<label for="fixturesCode"
									style="margin-left: 10px; font-size: large;">호수<select
									class="form-control" name="fixturesCode" id="fixturesCode"
									style=" width: 200px; margin-top: 6px;">
										<c:forEach items="${fixturesList }" var="fixtures">
											<option value="${fixtures.fixturesCode }">${fixtures.fixturesName }</option>
										</c:forEach>
								</select></label> 
							</div>
							<div class=" col">
								<label for="requestDate"
									style="margin-left: 10px; width: 200px; margin-top: 6px; font-size: large;">고장날짜<input
									type="date" class="form-control" id="requestDate"
									name="requestDate"></label>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="ascontent" style="margin-left: 10px; font-size: large;">내용</label>
						<textarea style="border: none; height: 100px; resize: none;"
							class="textarea" rows="10" cols="20" id="ascontent" name="ascontent"></textarea>
					</div>
				</div>
				
			</form>
			<div class="card-footer">
				<div class="float-right">
					<button onclick="history.go(-1)" class="btn btn-warning">뒤로가기</button>
					<button onclick="regist_go()" type="submit" id="registBtn" class="btn btn-primary">요청하기</button>
				</div>
			</div>
		</div>
	</div>
	
	<script>
	window.onload=function(){
		summernote_go($('textarea[name="ascontent"]'),'<%=request.getContextPath()%>');	
   	} 
	</script>

	<script>
		function regist_go() {
			var form = document.registForm;
			if (form.title.value == "") {
				alert("제목은 필수입니다.");
				return;
			}
			if (form.requestDate.value == "") {
				alert("고장난 날짜를 적용하세요.");
				return;
			}
			alert("요청 등록이 성공했습니다.");
			form.submit();
		}
	</script>
</body>
