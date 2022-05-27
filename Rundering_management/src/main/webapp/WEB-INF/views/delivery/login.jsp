<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Rundering Delivery</title>
    
    <script src="https://cdn.jsdelivr.net/npm/admin-lte@3.1/dist/js/adminlte.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/admin-lte@3.1/dist/css/adminlte.min.css">
	<!-- Pretendard  -->
	<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/font/pretendard/pretendard-subset.css" />
	<style type="text/css">
	body {
		font-family: Pretendard, -apple-system, BlinkMacSystemFont, system-ui,
			Roboto, 'Helvetica Neue', 'Segoe UI', 'Apple SD Gothic Neo',
			'Noto Sans KR', 'Malgun Gothic', 'Apple Color Emoji', 'Segoe UI Emoji',
			'Segoe UI Symbol', sans-serif;
	}
	</style>
	  
	  <!-- Font Awesome Icons -->
	  <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/bootstrap/plugins/fontawesome-free/css/all.min.css">
	  
</head>

<body class="login-page" style="min-height: 466px;">
    <div class="login-box">

        <div class="card card-outline card-info">
            <div class="card-header text-center">
                <h1><b>Rundering</b></h1>
                <p class="login-box-msg p-0">배송직원 전용</p>
            </div>
            <div class="card-body">

                <form action="<%=request.getContextPath() %>/common/login" method="post">
                    <div class="input-group mb-3">
                        <input type="text" class="form-control" name="id" placeholder="아이디">
                        <div class="input-group-append">
                            <div class="input-group-text">
                                <span class="fas fa-user"></span>
                            </div>
                        </div>
                    </div>
                    <div class="input-group mb-3">
                        <input type="password" class="form-control" name="password" placeholder="비밀번호">
                        <div class="input-group-append">
                            <div class="input-group-text">
                                <span class="fas fa-lock"></span>
                            </div>
                        </div>
                    </div>
	                <div class="social-auth-links text-center mt-2 mb-3">
	                    <button type="submit" class="btn btn-block btn-info">로그인</button>
	
	                </div>
                </form>

                <p class="mb-1">
                    <a href="forgot-password.html">아이디 / 비밀번호 찾기</a>
                </p>

            </div>

        </div>

    </div>



</body>

</html>