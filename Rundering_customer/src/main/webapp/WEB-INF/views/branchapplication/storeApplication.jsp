<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.card:hover{
		background-color:#EBF3FC;
	}
	
</style>

</head>

<body>
<div style="width: 70%;margin: auto; padding-top:120px;">
	<div class="card-body" style="height: 300px; padding: 10px;">
		<div class="row">
			<div style="width: 45%;">
				<div class="card" style="height: 300px; text-align:center; cursor:pointer; hover:rgb(213 235 253)" onclick="location.href='<%=request.getContextPath()%>/branchapplication/regist'">
					<div style="padding-left:150px;padding-top:20px;">
						<img alt="" src="<%=request.getContextPath() %>/resources/images/지점아이콘1.png" width="100px;" height="100px;">
					</div>
					<hr>
					<div style="text-align:center; font-size:2.5em; padding-top:35px;">
						<strong>지점 신청</strong>
					</div>
				</div>
			</div>
			<div style="width: 10%;"></div>
			<div style="width: 45%">
				<div class="card" style="height: 300px; cursor:pointer;
					" onclick="location.href='<%=request.getContextPath()%>/branchapplication/self_authentification'">
					<div style="padding-left:150px;padding-top:20px;">
						<img alt="" src="<%=request.getContextPath() %>/resources/images/지점아이콘2.png" width="100px;" height="100px;">
					</div>
					<hr>
					<div style="text-align:center; font-size:2.5em; padding-top:35px;">
						<strong>지점 신청 확인</strong>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>