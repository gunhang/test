<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<head>
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
</head>

<body>

<img class="bg" src="<%=request.getContextPath()%>/resources/images/rundering_bg.jpg" alt="rundering_bg">
<div class="login-box">
    <div class="login-logo">
        <div><a href="<%=request.getContextPath()%>/home.do"><img class="logo" src="<%=request.getContextPath()%>/resources/images/RunderingMemberLogo.png" alt="RunderingMember"></a></div>
        <!-- <b>회원</b> 로그인 -->
    </div>
    
    <div class="card">
        <div class="card-body login-card-body">
            <p class="login-box-msg">Sign in to start your session</p>
            <form action="<%=request.getContextPath() %>/login" method="post" id="formLogin">
                <div class="input-group mb-3">
                    <input type="text" class="form-control" placeholder="아이디입력"  id="id">
                    <input type="hidden" id="hiddenId" name="id">
                    <div class="input-group-append">
                        <div class="input-group-text">
                        </div>
                    </div>
                </div>
                <div class="input-group mb-3">
                    <input type="password" class="form-control" placeholder="Password" name="password">
                    <div class="input-group-append">
                        <div class="input-group-text">
                            <span class="fas fa-lock"></span>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-8">
                        <div class="icheck-primary">
                            <input type="checkbox" id="remember">
                            <label for="remember">
                                Remember Me
                            </label>
                        </div>
                    </div> 
                    
                    <div class="col-4">
                        <button id="loginButton" type="button" class="btn btn-primary btn-block" onclick="login_go()">로그인</button>
                    </div>
                    
                </div>
            </form>
            
            <p class="mb-1">
                    I forgot my <a href="javascript:OpenWindow('<%=request.getContextPath()%>/login/findaccount.do','아이디 찾기', 450, 700)">account</a> / 
                <a href="javascript:OpenWindow('<%=request.getContextPath()%>/login/findpassword.do','비밀번호 찾기', 450, 700)">password</a>
            </p>
            <p class="mb-0">
                <a href="<%=request.getContextPath()%>/join.do" class="text-center">join a new membership</a>
            </p>
            
        </div>
			<div id="captcha" style="margin-left: auto; margin-right: auto; text-align: center;">
				<label for="captcha" style="display: block">자동 로그인 방지</label>
				<div style="overflow: hidden">
					<div style="float: left">
						<img id="img" title="캡차이미지" src="" alt="캡차이미지" />
						<div id="ccaudio" style="display: none"></div>
					</div>
				</div>
				<div style="padding: 3px">
				</div>
				<div style="padding: 0px">
					<div class="input-group mb-3">
						<input style="width:161px" class="form-control" id="answer" type="text" value=""> 
						<span class="col-1"></span>
						<input class="btn btn-primary btn-block col-3" id="check" type="button" value="확인"   />
					</div>
				</div>
			</div>
	</div>
</div>
<script>
let login_flag=false;

function login_go() {
	if(login_flag==false){
		alert('그림을 제대로 입력하고 확인하세요.');
		return;
	}
	let idValue=document.querySelector('#id').value
	document.querySelector('#hiddenId').value=idValue+":==:true";
	document.querySelector('#formLogin').submit();
}

</script>

<script>


window.onload = function(){ 
	getImage();
	document.querySelector('#check').addEventListener('click',function(){
		var params = document.querySelector('#answer').value
		
		$.ajax({
			  url:'<%=request.getContextPath()%>/chkAnswer.do',
			  type:"post",
			  data: {
				  'answer':params
			  },
			  success:function(returnData){
				if(returnData == 200){
					
					alert('입력값이 일치합니다.');
					login_flag=true;
					document.querySelector('#captcha').innerHTML="";
				}else{ 
					alert('입력값이 일치하지 않습니다.');
					getImage(); 
					document.querySelector('#answer').setAttribute('value',''); 
				}

			  },
			  error:function(error){
				  alert('에러');
			  }
		   });
		
	})
}
function audio(){ 
	var rand = Math.random();
	var uAgent = navigator.userAgent; 
	var soundUrl = '<%=request.getContextPath()%>/captchaAudio.do?rand='+rand;
	if(uAgent.indexOf('Trident')>-1 || uAgent.indexOf('MISE')>-1){ 
		audioPlayer(soundUrl); 
	}else if(!!document.createElement('audio').canPlayType){ 
	try { 
		new Audio(soundUrl).play(); 
	} catch (e) {
		audioPlayer(soundUrl); 
		} 
	}else{
		window.open(soundUrl,'','width=1,height=1'); 
		}
	}


function getImage(){
	var rand = Math.random(); 
	var url = '<%=request.getContextPath()%>/captchaImg.do?rand='+rand;
	document.querySelector('#img').setAttribute('src', url); 
	}
function audioPlayer(objUrl){ 
	document.querySelector('#ccaudio').innerHTML = '<bgsoun src="' +objUrl +'">'; 
	}



</script>

</body>
