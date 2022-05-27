<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>

<body>
	<div id="body">
		<h3>매출 내역</h3>
		<div class="card card-primary card-outline ml-3 mr-3">

			<div class="card-body">
				*조회기간 &nbsp;<input type="radio">일별 &nbsp;<input type="radio">일주일
				&nbsp;<input type="radio">월별 &nbsp;<input type="radio">분기별
				<input type="date">
				<button class="btn btn-primary btn-sm" style="float: right;">조회하기</button>
			</div>

		</div>
		<div class="row ml-2 mr-2">

			<div class="col-12">
				<div class="card card-primary card-outline">
					<div class="card-header">


						<button class="btn btn-secondary btn-sm">표로보기</button>

						<div class="float-right">

							<div class="input-group input-group-sm">
								<div class="input-group-sm selectWidth">
									<select class="form-control" name="perPageNum" id="perPageNum"
										onchange="list_go();">
										<option value="10">10개</option>
										<option value="20">20개</option>
										<option value="50">50개</option>
										<option value="100">100개</option>

									</select>
								</div>


							</div>
						</div>
					</div>

					<div class="card-body p-0">
						<table class="table table-hover txet-nowrap">
							<thead>
								<tr>
									<th class="width30">월별</th>
									<th class="width30">금액</th>
									<th class="width30">세탁개수</th>

								</tr>
							</thead>
							<tbody>
								<tr>
									<td>1월</td>
									<td>4000000</td>
									<td>200개</td>
								</tr>
								<tr>
									<td>2월</td>
									<td>4000000</td>
									<td>200개</td>
								</tr>
								<tr>
									<td>3월</td>
									<td>4000000</td>
									<td>200개</td>
								</tr>
								<tr>
									<td>4월</td>
									<td>4000000</td>
									<td>200개</td>
								</tr>
								<tr>
									<td>5월</td>
									<td>4000000</td>
									<td>200개</td>
								</tr>
								<tr>
									<td>6월</td>
									<td>4000000</td>
									<td>200개</td>
								</tr>
								<tr>
									<td>7월</td>
									<td>4000000</td>
									<td>200개</td>
								</tr>
								<tr>
									<td>8월</td>
									<td>4000000</td>
									<td>200개</td>
								</tr>
								<tr>
									<td>9월</td>
									<td>4000000</td>
									<td>200개</td>
								</tr>
								<tr>
									<td>10월</td>
									<td>4000000</td>
									<td>200개</td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="card-footer">
						<nav aria-label="Contacts Page Navigation">
							<ul class="pagination justify-content-center m-0">
								<li class="page-item"><a class="page-link" href="#"> <i
										class="fas fa-angle-left"></i>
								</a></li>
								<li class="page-item"><a class="page-link" href="#"> <i
										class="fas fa-angle-double-left"></i>
								</a></li>
								<li class="page-item active"><a class="page-link" href="#">1</a></li>
								<li class="page-item"><a class="page-link" href="#">2</a></li>
								<li class="page-item"><a class="page-link" href="#">3</a></li>
								<li class="page-item"><a class="page-link" href="#">4</a></li>
								<li class="page-item"><a class="page-link" href="#">5</a></li>
								<li class="page-item"><a class="page-link" href="#"> <i
										class="fas fa-angle-right"></i>
								</a></li>
								<li class="page-item"><a class="page-link" href="#"> <i
										class="fas fa-angle-double-right"></i>
								</a></li>
							</ul>
						</nav>
					</div>
				</div>

			</div>
		</div>
	</div>
</body>