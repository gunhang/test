<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>

<style>
h1 {
	color: white;
	font-size: 5em;
}

.text {
	color: white;
	font-size: 1.5em;	
}
</style>
<div class="col-lg-12" style="position: relative;">
	<div>
		<img style="width: 1280px; margin: auto;"
			src="<%=request.getContextPath()%>/resources/images/introduce.jpeg"
			alt="introduce_bg">
		<div class="b"
			style="text-align: center; position: absolute; top: 40%; left: 50%; transform: translate(-50%, -50%);">
			<div>
				<h1>Rundering</h1>
			</div>
			<div>
				<p class="text-slide">
					<span class="text">회사 이름은 세탁을 뜻하는 영어 laundry와
						<br>달린다는 단어Run을 합친 것으로
						<br>빠르고 신속 하게 세탁해 고객에게
						<br>가져다 드린다는 의미를 가지고있습니다.<br> 
						<br> Rundering 컴퍼니는 과감하고 창의적인 노력을 통해<br>
						복잡한 현대인들의 삶을 좀 더 단순하고 윤택하게<br> 만들겠다는 비전을 가지고 있습니다.
					</span>
				</p>
			</div>
		</div>
	</div>
</div>