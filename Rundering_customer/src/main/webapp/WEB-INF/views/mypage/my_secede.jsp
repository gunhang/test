<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>

<!--이쁜 알럽트창 -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/sweetalert2@11.1.9/dist/sweetalert2.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/sweetalert2@11.1.9/dist/sweetalert2.all.min.js"></script>

	<div style="width: 60%; display: flex; flex-direction: column; margin-left: 20%;">
		<section class="content-header">
			<div class="container-fluid">
				<div class="row mb-2">
					<div class="col-sm-6">
						<h1>회원탈퇴</h1>
					</div>
					<div class="col-sm-6">
						<ol class="breadcrumb float-sm-right">
							<li class="breadcrumb-item active">회원탈퇴</li>
							<li class="breadcrumb-item"><a href="#">마이페이지</a></li>
						</ol>
					</div>
				</div>
			</div>
		<hr style="border: 1px solid rgb(170, 167, 167);">
		</section>


<h2 class="text-center display-6">탈퇴시 유의사항</h2>
<strong><p class="text-center display-10" style="font-size: 1.2em;">회원님의 정보를 안전하게 보호하기위해 비밀번호를 다시한번 입력해 주세요</p></strong>
<div class="card-body col-10 " style="margin-left: 15%;">
	<div class="form-group">
		<div class="row ">
			<label for="password" class="col-2">
				<p style="font-size: 1.5em;">비밀번호</p>
			</label>
			<div class="col-8">
				<div class="form-group col-8">
					<input type="password" class="form-control" name="password" id="password" value="">
				</div>
			</div>
		</div>
		<div class="row ">
			<label for="id" class="col-2">
				<p style="font-size: 1.5em;">탈퇴사유</p>
			</label>
			<div class="col-8">
				<div class="form-group col-8">
					<textarea style="resize: none;" class="col-12" rows="4"></textarea>
				</div>
			</div>
		</div>
		<span class="form-group col-2">
			<button class="btn  float-right" style="border-color: gray"
				type="submit" onclick="secede()">탈퇴</button>
		</span>
	</div>
</div>
</div>


<script>
	function secede(){
		
		$.ajax({
			url : '<%=request.getContextPath()%>/mypage/secede',
			type : 'post',
			data : {
				'password' : $('#password').val()
			},
			success : function(response){
				if(response.toUpperCase() == "OK"){
					Swal.fire({
						icon: 'success', // 여기다가 아이콘 종류를 쓰면 됩니다.
						title: 'Rundering 회원탈퇴되었습니다.',
						text: '다음에 다시 이용해주세요.' 
					});
					location.href = "<%=request.getContextPath()%>
	/login/form";
				} else {
					Swal.fire({
						icon : 'error', // 여기다가 아이콘 종류를 쓰면 됩니다.
						title : '비밀번호가 일치하지 않습니다.'
					});
				}
			},
			error : function(xhr) {
				alert(xhr.status);
			},
		});
	}
</script>
