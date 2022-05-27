<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!--이쁜 알럽트창 -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.1.9/dist/sweetalert2.min.css">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.1.9/dist/sweetalert2.all.min.js"></script>

<c:set var="mv" value="${id}"/>
    
<style>
    .bg{
        width: 100%;
        height: 100vh;
        opacity: 70%;
        background-size:cover;
        background-repeat : no-repeat;
    }
    .login-box{
        margin: 0;
        position: absolute;
        top: 50%;        
        left: 50%;        
        -ms-transform: translate(-50%, -50%);        
        transform: translate(-50%, -50%);
    }
</style>

    <img class="bg" src="imgfile/rundering_bg.jpg" alt="rundering_bg">
    <div class="login-box">
        <div class="login-logo">
            <div><img class="logo" src="imgfile/RunderingLogo.png" alt="Rundering"></div>
            <!-- <b>비밀번호 찾기 -->
        </div>
        
        <div class="card">
            <div class="card-body login-card-body">
                <p class="login-box-msg"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">▼▼아래 정보를 입력해주세요▼▼</font></font></p>
                <form class="form-horizontal" onsubmit="return pwModify();" method="post" >
                    <div class="input-group">
                        <input type="password" class="form-control pass" id="newPw" name="password" placeholder="새로운 비밀번호를 입력해 주세요." required>
                    </div>
                    <div class="form-group"><span class="sp"></span></div>
                    <div class="input-group mb-3">
                        <input type="password" class="form-control pass" id="newPw1" placeholder="새로운 비밀번호를 재입력해 주세요." required>
                    </div>
                    <div class="form-group"><span class="sp"></span></div>
                    <div class="row">
                        <div class="col-12">
                            <button type="submit" id="sendBtn" class="btn btn-primary btn-block"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">비밀번호 변경</font></font></button>
                        </div>
                        <input type="hidden" class="form-control" id="id" name="id" value="${id}" required> 
                    </div>
                </form>
            </div>
        </div>
    </div>
    
<script>
function okProc(ele, str){
	let vs = $(ele).parents('.form-group').find('.sp');
	$(vs).html(str).css('color','green');
}
function noProc(ele, str){
	
	let vs = $(ele).parents('.form-group').find('.sp');
	$(vs).html(str).css('color','red');
}
</script>    
    
<script>
let pwchk = false;
let passchk = false;

	function pwModify(){
		
		event.preventDefault();	//submit 이벤트를 막아 페이지 리로드를 방지
		
		if(pwchk && passchk) {
			$.ajax({
				url : '<%=request.getContextPath()%>/common/change/newpassword',
				type : 'post',
				data : {
					'id' : $('#id').val(),
					'password' : $('#newPw').val()
				},
				success : function(response){
					if(response.toUpperCase() == "OK"){
						Swal.fire('비밀번호가 변경!', '새로운 비밀번호로 변경되었습니다.', 'success' )
						setTimeout(function(){location.reload();},1000);
						location.href = "<%=request.getContextPath()%>/common/loginform";
						} else {
							Swal.fire({
								icon: 'error', // 여기다가 아이콘 종류를 쓰면 됩니다.
								title: '비밀번호를 변경할 수 없습니다!'
							});
						}
					},
					error : function(xhr) {
						alert(xhr.status);
					},
				});
		}
		
	}



window.addEventListener('load',com);
function com(){
	//유효성검증 - pass
	$('#newPw').on('keyup',function() {
		let passValue = $('#newPw').val().trim();
		let regPass = /^(?=.*?[a-zA-Z])(?=.*?[0-9])(?=.*?[!@#$%^&*()_+|]).{8,}$/;

		if (regPass.test(passValue)) {
			okProc($('#newPw'), "사용 가능한 패스워드 입니다!");
			pwchk = true;
		} else if (passValue === "") {
			noProc($('#newPw'), "패스워드를 입력하세요");
			pwchk = false;
		} else {
			noProc($('#newPw'), "대/소문자,특수문자,숫자 포함 8자리 이상 입력해야함");
			pwchk = false;
		}
	});

	//비밀번호 일치 여부 확인
	$('.pass').on('keyup', function() {
		let pass1 = $("#newPw").val().trim();
		let pass2 = $("#newPw1").val().trim();

		if (pass1 != "" || pass2 != "") {
			if (pass1 == pass2) {
				okProc($('#newPw1'), "일치");
				passchk = true;
			} else {
				noProc($('#newPw1'), "불일치");
				passchk = false;
			}
		}
	});
	
	// 회원가입 전송
	$('#sendBtn').on('click', pwModify)
}
</script>
