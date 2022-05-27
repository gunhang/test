<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>




<!--VISUAL-->
<section class="visual">
	<div class="inner">

		<img src="<%=request.getContextPath()%>/resources/images/test2.jpg"
			alt="" />
		<div class="title fade-in">
			<div>
				<br>
				<br>
				<br>
				<br>
				<br>
				<p>&nbsp;&nbsp;집에서 세탁하기 힘든 것들</p>
				<p>&nbsp;&nbsp;Rundering에게 맡기세요</p>
				<p>&nbsp;&nbsp;전문적인 사람들의 기술력을 체험하세요</p>
				<a href="<%=request.getContextPath()%>/order.do" class="btn btn--brown">주문하기</a>
			</div>
		</div>
	</div>
</section>


<!--NOTICE-->
<section class="notice">

	<!--NOTICE LINE-->
	<div class="notice-line">
		<div class="bg-left"></div>
		<div class="bg-right"></div>
		<div class="inner">

			<div class="inner__left" style="margin-left: 20px">
				<h2>공지사항</h2>
				<div class="swiper-container">
					<div class="swiper-wrapper">
						<div class="swiper-slide">
							<a href="javascript:void(0)">Rundering 매장 영업시간 변경 안내</a>
						</div>
						<div class="swiper-slide">
							<a href="javascript:void(0)">[당첨자 발표] Rundering 후기 이벤트</a>
						</div>
						<div class="swiper-slide">
							<a href="javascript:void(0)">Rundering 애플리케이션 버전 업데이트 안내</a>
						</div>
						<div class="swiper-slide">
							<a href="javascript:void(0)">[당첨자 발표] 3월 행운의 회원 추첨 이벤트</a>
						</div>
					</div>
				</div>
				<a href="javascript:void(0)" class="notice-line__more"> <span
					class="material-icons">add_circle</span>
				</a>
			</div>

			<div class="inner__right">
				<h2>Rundering 프로모션</h2>
				<div class="toggle-promotion open">
					<div class="material-icons">upload</div>
				</div>
			</div>

		</div>
	</div>

	<!--PROMOTION-->
	<div class="promotion">

		<div class="swiper-container">
			<div class="swiper-wrapper">
				<div class="swiper-slide">
					<img src="<%=request.getContextPath()%>/resources/images/img01.jpg" width="500px;" height="500px;"
						style="display:block; margin:auto;" alt="사진1" /> <a href="javascript:void(0)" class="btn">자세히 보기</a>
				</div>
				<div class="swiper-slide">
					<img src="<%=request.getContextPath()%>/resources/images/img02.jpg" width="500px;" height="500px;"
						style="display:block; margin:auto;" alt="" /> <a href="javascript:void(0)" class="btn">자세히 보기</a>
				</div>
				<div class="swiper-slide">
					<img src="<%=request.getContextPath()%>/resources/images/img03.jpg" width="500px;" height="500px;"
						style="display:block; margin:auto;" alt="사진3" /> <a href="javascript:void(0)" class="btn">자세히 보기</a>
				</div>
			</div>
		</div>

		<div class="swiper-pagination"></div>

		<div class="swiper-prev">
			<span class="material-icons">arrow_back</span>
		</div>
		<div class="swiper-next">
			<span class="material-icons">arrow_forward</span>
		</div>

	</div>
</section>



<!--YOUTUBE VIDEO-->
<section class="youtube">
	<div class="youtube__area">
		<div id="player"></div>
	</div>
</section>