<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<div style="width: 60%; display: flex; flex-direction: column; margin-left: 20%;">
	<section class="content-header">
		<div class="container-fluid">
			<div class="row mb-2">
				<div class="col-sm-6">
					<h1>회원정보 수정</h1>
				</div>
				<div class="col-sm-6">
					<ol class="breadcrumb float-sm-right">
						<li class="breadcrumb-item active">회원정보 수정</li>
						<li class="breadcrumb-item"><a href="#">마이페이지</a></li>
					</ol>
				</div>
			</div>
		</div>
	<hr style="border: 1px solid rgb(170, 167, 167);">
	</section>

	
    <strong><p class="text-center display-10" style="font-size: 1.2em;">회원님의 정보를 안전하게 보호하기위해 비밀번호를 다시한번 입력해 주세요</p></strong>
    <div class="card-body col-10 " style="margin-left: auto;">
        <div class="form-group">
                    <div class="row ">
                        <label for="password" class="col-2">
                            <p style="font-size: 1.5em;">비밀번호</p>

                        </label>
                        <div class="col-10">
                            <div class="col-12 row">
                                <div class="form-group col-6" >
                                    <input type="password" class="form-control" name="password" id="password"  value=""  />
                                </div>
                                <span class="form-group col-2">
                                    <button class="btn btn-secondary float-right" onClick="pwCkeck()" >확인</button>
                                </span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
     </div>
<script>
	
	document.getElementById("password").focus();
	
	function pwCkeck(){
		if($('#password').val()== null){
			Swal.fire('비밀번호를 입력해주세요.');
		} else {
			$.ajax({
				url : '<%=request.getContextPath()%>/mypage/pwCheck',
				data : {
					'password' : $('#password').val()
				},
				type : 'post',
				success : function(result) {
					if (result.toUpperCase() == "DUPLICATED") {
						 location.href = "<%=request.getContextPath()%>/mypage/memberModifyform";
					} else {
						 Swal.fire('비밀번호가 틀렸습니다!');				
					}
				},
				error : function(error) {
					//alert("시스템장애로 가입이 불가합니다.");
					AjaxErrorSecurityRedirectHandler(error.status);
				}
			});
		}
	}
	
	document.querySelector('#password').addEventListener('keyup', (e)=>{
	    if (e.keyCode === 13) {
	    	
	    	if($('#password').val()== null || $('#password').val().trim()==""){
				Swal.fire('비밀번호를 입력해주세요.');
			} else {
				$.ajax({
					url : '<%=request.getContextPath()%>/mypage/pwCheck',
					data : {
						'password' : $('#password').val()
					},
					type : 'post',
					success : function(result) {
						if (result.toUpperCase() == "DUPLICATED") {
							 location.href = "<%=request.getContextPath()%>/mypage/memberModifyform";
						} else {
							 Swal.fire('비밀번호가 틀렸습니다!');				
						}
					},
					error : function(error) {
						//alert("시스템장애로 가입이 불가합니다.");
						AjaxErrorSecurityRedirectHandler(error.status);
					}
				});
			}
	        // code for enter
	  }  
	});
	
</script>