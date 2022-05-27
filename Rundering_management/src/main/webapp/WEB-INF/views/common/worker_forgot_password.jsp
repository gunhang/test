<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    <img class="bg" src="imgfile/rundering_bg.jpg" alt="rundering_bg">
    <div class="login-box">
        <div class="login-logo">
            <div><img class="logo" src="imgfile/RunderingLogo.png" alt="Rundering"></div>
            <!-- <b>비밀번호 찾기 -->
        </div>
        
        <div class="card">
            <div class="card-body login-card-body">
                <p class="login-box-msg"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">비밀번호를 잊으셨나요?<br> </font><font style="vertical-align: inherit;">▼▼아래 정보를 입력해주세요▼▼</font></font></p>
                <form method="post">
                    <div class="input-group mb-3">
                        <input type="text" class="form-control" id="" placeholder="사번을 입력하세요." required>
                    </div>
                    <div class="input-group mb-3">
                        <input type="text" class="form-control" id="name" placeholder="정확한 이름을 입력하세요." required>
                    </div>
                    <div class="input-group mb-3">
                        <input type="email" class="form-control" id="mail" placeholder="이메일을 입력하세요." required>
                    </div>
                    <div class="row">
                        <div class="col-12">
                            <button type="submit" class="btn btn-primary btn-block"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">임시 비밀번호 요청</font></font></button>
                        </div>
                        
                    </div>
                </form>
                <p class="mt-3 mb-1">
                    <a href="worker_login.html"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">로그인</font></font></a>
                </p>
                <p class="mb-0">
                    <a href="worker_register.html" class="text-center"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">신규 회원가입</font></font></a>
                </p>
            </div>
            
        </div>
    </div>
</body>
