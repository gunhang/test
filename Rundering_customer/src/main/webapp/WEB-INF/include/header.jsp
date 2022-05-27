<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>


<!--HEADER-->
<header>
	<nav class="inner">

		<div class="sub-menu">
			<ul class="menu">
				<sec:authorize access="isAnonymous()">
					<li><a href="<%=request.getContextPath()%>/login/form">로그인</a></li>
					<li><a href="<%=request.getContextPath()%>/3agree">회원가입</a></li>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
					<li><h2 style="padding:11px 16px;font-weight:600;">${loginUser.getName() }님</h2></li>
					<li><a href="<%=request.getContextPath()%>/mypage">마이페이지</a>
						<ul class="smul">
							<li class="smli"><a href="<%=request.getContextPath()%>/mypage">회원정보수정</a></li>
							<li class="smli"><a href="<%=request.getContextPath()%>/mypage/myaddress">내 주소 관리</a></li>
							<li class="smli"><a href="<%=request.getContextPath()%>/mypage/myorder/histroy/main">주문내역</a></li>
							<li class="smli"><a href="<%=request.getContextPath()%>/mypage/myinquiry/list">문의내역</a></li>
							<li class="smli"><a href="<%=request.getContextPath()%>/mypage/secedeform">회원탈퇴</a></li>
						</ul>
					</li>
					<li><a href="<%=request.getContextPath()%>/logout">로그아웃</a></li>
				</sec:authorize>
			</ul>
		</div>

		<a href="<%=request.getContextPath()%>/home" class="logo"> 
			<img src="<%=request.getContextPath()%>/resources/images/Rundering.png"alt="Rundreing" />
		</a>


		<div id="menu">
			<ul>
				<li><a href="<%=request.getContextPath()%>/introduce">회사소개</a></li>
				<li><a href="#">이용안내</a>
					<ul>
						<li><a href="<%=request.getContextPath()%>/guide/howuse">이용방법</a></li>
						<li><a href="<%=request.getContextPath()%>/guide/price/list">가격안내</a></li>
						<li><a href="<%=request.getContextPath()%>/guide/area">서비스 지역</a></li>
					</ul>
				</li>
				<li><a href="<%=request.getContextPath()%>/order">세탁주문</a></li>
				<li><a href="#">고객센터</a>
					<ul>
						<li><a href="<%=request.getContextPath()%>/question/faq">자주묻는질문</a></li>
						<li><a href="<%=request.getContextPath()%>/question/list">문의사항</a></li>
					</ul></li>
			</ul>
		</div>
	</nav>
</header>