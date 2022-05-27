<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>

  <!--FOOTER-->
  <footer class="main-footer">
    <div class="inner">
  
      <ul class="menu">
        <li><a href="javascript:void(0)" class="gray">개인정보처리방침</a></li>
        <li><a href="javascript:void(0)" class="gray">영상정보처리기기 운영관리 방침</a></li>
        <li><a href="javascript:void(0)" class="gray">홈페이지 이용약관</a></li>
        <li><a href="javascript:void(0)" class="gray">위치정보 이용약관</a></li>
        <li><a href="javascript:void(0)" class="gray">세탁주문 이용약관</a></li>
      </ul>
  
      <div class="btn-group">
        <a href="<%=request.getContextPath()%>/branchapplication/storeApplication " class="btn btn--black">신규입점제의</a> 
        <a href="http://<%=request.getServerName()%>:<%=request.getServerPort() %>/runderingmanage/common/loginform" class="btn btn--black">사원 로그인</a> 
        <a href="javascript:void(window.open('http://<%=request.getServerName()%>:<%=request.getServerPort() %>/runderingmanage/fordelivery/login', 'Rundering delivery','width=420, height=860'))" class="btn btn--black">배송사원 로그인</a>
      </div>
  
      <div class="info">
        <span>사업자등록번호 511-73-37881</span> <span>(주)런드링 코리아 대표이사 구건회</span> <span>TEL
          : 042) 477-2733 / FAX : 042) 477-2734</span> <span>개인정보 책임자 : 구건희</span>
      </div>
  
      <p class="copyright">
        &copy; <span class="this-year"></span> Rundering Company. All
        Rights Reserved.
      </p>
      <img src="<%=request.getContextPath() %>/resources/images/logo1.png" alt="RUNDERING"
        class="logo" />
  
    </div>
  </footer>
  
    <!--TO TOP BUTTON-->
  <div id="to-top">
    <div class="material-icons">arrow_upward</div>
  </div>

