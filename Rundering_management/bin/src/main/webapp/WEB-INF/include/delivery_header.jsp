<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<body>
    <div class="warpper">
        <nav class="main-header navbar navbar-expand-md navbar-light navbar-white" >
            <div class="container">
                <a href="<%=request.getContextPath() %>/fordelivery/main.do" class="navbar-brand" style="">
                    <script type="text/javascript"
                        src="https://ajax.cloudflare.com/cdn-cgi/scripts/04b3eb47/cloudflare-static/mirage2.min.js"></script>
                    <span class="brand-text" style="font-size: 1.7rem;">Rundering Delivery</span>
                </a>

                <div class="navbar-collapse order-3 collapse" id="navbarCollapse" style="">
                    <ul class="navbar-nav" style="text-align: center; font-size: 1.3rem;">
                        <li class="nav-item pb-2">
                            <a href="<%=request.getContextPath() %>/fordelivery/pickup?orderStatus=02" class="nav-link">수거</a>
                        </li>
                        <li class="nav-item pb-2">
                            <a href="<%=request.getContextPath() %>/fordelivery/delivery?orderStatus=06" class="nav-link">배송</a>
                        </li>
                        <li class="nav-item pb-2">
                            <a href="#" class="nav-link">공지</a>
                        </li>
                        <li class="nav-item pb-2">
                            <a href="<%=request.getContextPath() %>/fordelivery/logout.do" class="nav-link">로그아웃</a>
                        </li>
                    </ul>
                </div>

                <ul class="order-1 order-md-3 navbar-nav navbar-no-expand ml-auto">
                    <button class="navbar-toggler order-1" type="button" data-toggle="collapse"
                        data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="true"
                        aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                </ul>

            </div>
        </nav>
    </div>

</body>