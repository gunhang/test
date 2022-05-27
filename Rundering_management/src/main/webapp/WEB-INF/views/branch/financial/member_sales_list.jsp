<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>

<body>
	<div id="body">
		<div class="row">
			<div class="col-12">
				<div class="card card-primary card-outline">
					<div class="card-header">
						<h3 class="card-title">회원매출 내역</h3>
						<div class="card-tools">
							<div class="input-group input-group-sm">
								<div class="input-group-sm selectWidth">
									<select class="form-control " name="perPageNum" id="perPageNum"
										onchange="list_go();">
										<option value="10">10개</option>
										<option value="20">20개</option>
										<option value="50">50개</option>
										<option value="100">100개</option>
	
									</select> 
								</div>
								<div class="input-group-sm selectWidth">
									<select class="form-control " name="searchType" id="searchType">
										<option value="tcw">월</option>
										<option value="t">주</option>
										<option value="w">일</option>
										<option value="c">분기</option>
										<option value="tc">년도</option>
									</select> 
								</div>
								<span class="input-group-append">
									<button class="btn btn-primary" type="button"
										onclick="list_go(1);" data-card-widget="search">
										<i class="fa fa-fw fa-search"></i>
									</button>
								</span> <span class="input-group-append">
									<button class="btn btn-secondary">표로보기</button>
								</span>
							</div>
						</div>
					</div>

					<div class="card table-responsive p-0">
						<table class="table table-hover text-nowrap">
							<thead>
								<tr>
									<th class="width20">회원이름</th>
									<th class="width20">월별</th>
									<th class="width20">금액</th>
									<th class="width20">세탁개수</th>

								</tr>
							</thead>
							<tbody>
								<tr>
									<td>백관우</td>
									<td>1월</td>
									<td>4000000</td>
									<td>200개</td>

								</tr>
								<tr>
									<td>와이셔츠</td>
									<td>2월</td>
									<td>4000000</td>
									<td>200개</td>

								</tr>
								<tr>
									<td>와이셔츠</td>
									<td>3월</td>
									<td>4000000</td>
									<td>200개</td>

								</tr>
								<tr>
									<td>와이셔츠</td>
									<td>4월</td>
									<td>4000000</td>
									<td>200개</td>

								</tr>
								<tr>
									<td>와이셔츠</td>
									<td>5월</td>
									<td>4000000</td>
									<td>200개</td>

								</tr>
								<tr>
									<td>와이셔츠</td>
									<td>6월</td>
									<td>4000000</td>
									<td>200개</td>

								</tr>
								<tr>
									<td>와이셔츠</td>
									<td>7월</td>
									<td>4000000</td>
									<td>200개</td>

								</tr>
								<tr>
									<td>와이셔츠</td>
									<td>8월</td>
									<td>4000000</td>
									<td>200개</td>

								</tr>
								<tr>
									<td>와이셔츠</td>
									<td>9월</td>
									<td>4000000</td>
									<td>200개</td>

								</tr>
								<tr>
									<td>와이셔츠</td>
									<td>10월</td>
									<td>4000000</td>
									<td>200개</td>

								</tr>
							</tbody>
						</table>
					</div>

				</div>

			</div>
		</div>
	</div>

</body>
