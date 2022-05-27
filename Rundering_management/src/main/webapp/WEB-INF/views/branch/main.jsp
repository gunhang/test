<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>

<body>
	<div class="row2 ml-3 mr-3">
		<div class="row">
			<!-- 1번째 화면 -->
			<div class="col-md-6" style="margin-top: 30px">
				<div class="card card-primary card-outline">
					<div class="card-header">
						<h3 class="card-title">공지사항</h3>
						<div class="card-tools">
							<button type="button" class="btn btn-tool"
								data-card-widget="collapse" title="Collapse">
								<i class="fas fa-minus"></i>
							</button>
						</div>
					</div>
					<div class="card-body" style="height: 250x">
						<div class="card-body table-responsive p-0">
							<table class="table table-hover text-nowrap">
								<thead>
									<tr>
										<th style="text-align: center;">글번호</th>
										<th>작성자</th>
										<th>날짜</th>
										<th>Title</th>

									</tr>
								</thead>
								<tbody>
								
									<tr>
										<td style="text-align: center;">1</td>
										<td>이민호</td>
										<td>2022-03-30</td>
										<td>회원가입</td>

									</tr>
									<tr>
										<td style="text-align: center;">2</td>
										<td>이수윤</td>
										<td>2022-03-30</td>
										<td>발주프로세스</td>

									</tr>
									<tr>
										<td style="text-align: center;">3</td>
										<td>오세준</td>
										<td>2022-03-30</td>
										<td>배달프로세스</td>

									</tr>
									<tr>
										<td style="text-align: center;">4</td>
										<td>백관우</td>
										<td>2022-03-30</td>
										<td>고객필수안내사항</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					<div class="card-footer">
						<div class="col-2 float-right" style="margin-right: 10px;">
							<a href="notice/list.do"><button type="button"
									class="btn btn-block btn-primary btn-sm" style="width: 80px;">More>></button></a>
						</div>
					</div>
				</div>
			</div>
			<!-- 2번째 화면 -->
			<div class="col-md-6" style="margin-top: 30px">
				<div class="card card-secondary card-outline">
					<div class="card-header">
						<h3 class="card-title">시설AS요청</h3>
						<div class="card-tools">
							<button type="button" class="btn btn-tool"
								data-card-widget="collapse" title="Collapse">
								<i class="fas fa-minus"></i>
							</button>
						</div>
					</div>
					<div class="card-body" style="height: 250x">
						<div class="card-body table-responsive p-0">
							<table class="table table-hover text-nowrap">
								<thead>
									<tr>
										<th>물품이름</th>
										<th>법인명</th>
										<th>고장부위</th>
										<th style="text-align: center;">상태</th>

									</tr>
								</thead>
								<tbody>
									<tr>
										<td>세탁기 30kg</td>
										<td>삼성</td>
										<td>전원 불량</td>
										<td style="text-align: center;">1</td>
									</tr>
									<tr>
										<td>세탁선반</td>
										<td>굿퍼니</td>
										<td>우측다리 파손</td>
										<td style="text-align: center;">2</td>
									</tr>
									<tr>
										<td>세탁바구니10kg</td>
										<td>굿퍼니</td>
										<td>좌측 파손</td>
										<td style="text-align: center;">2</td>
									</tr>
									<tr>
										<td>건조기20kg</td>
										<td>삼성</td>
										<td>코드 불량</td>
										<td style="text-align: center;">1</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					<div class="card-footer">
						<div class="col-2 float-right" style="margin-right: 10px;">
							<a href="asrequest/list.do"><button type="button"
									class="btn btn-block btn-primary btn-sm" style="width: 80px;">More>></button></a>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<!-- 3번째 화면 -->
			<div class="col-md-6" style="margin-top: 20px">
				<div class="card card-primary card-outline">
					<div class="card-header">
						<h3 class="card-title">세탁현황</h3>
						<div class="card-tools">
							<button type="button" class="btn btn-tool"
								data-card-widget="collapse" title="Collapse">
								<i class="fas fa-minus"></i>
							</button>
						</div>
					</div>
					<div class="card-body" style="height: 250x">
						<div class="card-body table-responsive p-0">
							<table class="table table-hover text-nowrap"  style="text-align: center;">
								<thead>
									<tr>
										<th>주문번호</th>
										<th>고객명</th>
										<th>세탁물 상태</th>
										<th>배송예정일</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>11121</td>
										<td>백관우</td>
										<td>세탁중</td>
										<td>2일</td>
									</tr>
									<tr>
										<td>11121</td>
										<td>구건회</td>
										<td>세탁완료</td>
										<td>1일</td>
									</tr>
									<tr>
										<td>11121</td>
										<td>이민호</td>
										<td>세탁준비중</td>
										<td>3일</td>
									</tr>
									<tr>
										<td>11121</td>
										<td>오세준</td>
										<td>세탁중</td>
										<td>2일</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					<div class="card-footer">
						<div class="col-2 float-right" style="margin-right: 10px;">
							<a href="laundry/situatuion_list.do"><button
									type="button" class="btn btn-block btn-primary btn-sm"
									style="width: 80px;">More>></button></a>
						</div>
					</div>
				</div>
			</div>
			<!-- 4번째 화면 -->
			<div class="col-md-6" style="margin-top: 20px">
				<div class="card card-secondary card-outline">
					<div class="card-header">
						<h3 class="card-title">매출관리</h3>
						<div class="card-tools">
							<button type="button" class="btn btn-tool"
								data-card-widget="collapse" title="Collapse">
								<i class="fas fa-minus"></i>
							</button>
						</div>
					</div>
					<div class="card-body" style="height: 250x">
						<div class="card-body table-responsive p-0">
							<table class="table table-hover text-nowrap"  style="text-align: center;">
								<thead>
									<tr>
										<th>일자별</th>
										<th>금 액</th>
										<th>세탁개수</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>2022.03.01</td>
										<td>547,300원</td>
										<td>747 건</td>
									</tr>
									<tr>
										<td>2022.03.02</td>
										<td>513,500원</td>
										<td>630 건</td>
									</tr>
									<tr>
										<td>2022.03.03</td>
										<td>599,400원</td>
										<td>795 건</td>
									</tr>
									<tr>
										<td>2022.03.04</td>
										<td>354,700원</td>
										<td>476 건</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					<div class="card-footer">
						<div class="col-2 float-right" style="margin-right: 10px;">
							<a href="financial/sales_list.do"><button
									type="button" class="btn btn-block btn-primary btn-sm"
									style="width: 80px;">More>></button></a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
