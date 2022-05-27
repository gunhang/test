<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>

<style>
h1 {
	color: white;
	font-size: 3.5em;
	font-weight: bolder;
}

P {
	font-weight: bold;
	font-size: 24px;
}
</style>
<div class="col-lg-12" style="position: relative;">
	<div>
		<img style="width: 1280px; margin: auto;"
			src="<%=request.getContextPath()%>/resources/images/area.png"
			alt="introduce_bg">
		<div class="b"
			style="text-align: center; position: absolute; top: 10%; left: 18%; transform: translate(-50%, -50%);">
			<div>
				<h1>세특권 안내</h1>
			</div>
		</div>
	</div>
	<div style="margin-top: 50px; margin-bottom: 100px;">
		<div style="float: left; margin-right: 80px; margin-left: 100px;">
			<p>서비스 지역 안내</p>
		</div>
		<div style="font-size: 20px;font-weight:bold;">
			<span>유성구 · 대덕구 · 동구 · 중구 · 서구</span>
		</div>
	</div>
	<div style="margin-top: 100px; margin-bottom: 100px;">
		<div style="float: left; margin-right: 30px; margin-left: 100px;">
			<p style="color:red;">서비스 불가 지역 안내</p>
		</div>
		<div style="font-size: 16px;">
			<span>서비스 지역 내이나 아직 배송 밀도가 낮아 진출하지 못한 일부 지역이 있습니다.<br>빠른 시일내에 세특권이 될 수 있도록 노력하겠습니다.</span>
		</div>
	</div>
	<div style="margin-top: 100px; margin-bottom: 100px;">
		<div style="float: left; margin-right: 150px; margin-left: 100px;">
			<p>운영시간</p>
		</div>
		<div style="font-size: 16px;">
			<span><b style="font-weight:bold;font-size:22px;">새벽배송 매일 00:00 - 07:00</b>
	<br>
오늘 밤 10시 00분까지 세탁신청하면 당일 새벽에 수거하고 모레 문 앞까지 새벽배송</span>
		</div>
	</div>
</div>