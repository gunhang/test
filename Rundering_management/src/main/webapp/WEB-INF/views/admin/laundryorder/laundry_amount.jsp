<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<c:set var="pageMaker" value="${dataMap.pageMaker }" />
<c:set var="cri" value="${dataMap.pageMaker.cri }" />
<c:set var="noticeList" value="${dataMap.noticeList }" />

<style>
#row {
	padding: 20px;
}
</style>


<head>
</head>

<title>공지목록</title>

<body>
	<h3>세탁량 그래프/상세 게시물</h3>
	<div id="row">
		<div class="card card-secondary card-outline">
			<div class="card-header">
				<h3 class="card-title">
					<i class="far fa-chart-bar"></i> Bar Chart 
				</h3>
				<div class="card-tools">
					<button type="button" class="btn btn-tool"
						data-card-widget="collapse">
						<i class="fas fa-minus"></i>
					</button>
					<button type="button" class="btn btn-tool"
						data-card-widget="remove">
						<i class="fas fa-times"></i>
					</button>
				</div>
			</div>
			<div class="card-body">
				<div id="bar-chart"
					style="height: 300px; padding: 0px; position: relative;">
					<canvas class="flot-base" width="763" height="300"
						style="direction: ltr; position: absolute; left: 0px; top: 0px; width: 763.5px; height: 300px;"></canvas>
					<canvas class="flot-overlay" width="763" height="300"
						style="direction: ltr; position: absolute; left: 0px; top: 0px; width: 763.5px; height: 300px;"></canvas>
					<div class="flot-svg"
						style="position: absolute; top: 0px; left: 0px; height: 100%; width: 100%; pointer-events: none;">
						<svg style="width: 100%; height: 100%;">
						<g class="flot-x-axis flot-x1-axis xAxis x1Axis"
								style="position: absolute; inset: 0px;">
						<text x="298.0213068181818" y="294"
								class="flot-tick-label tickLabel"
								style="position: absolute; text-align: center;">March</text>
						<text x="429.8536931818182" y="294"
								class="flot-tick-label tickLabel"
								style="position: absolute; text-align: center;">April</text>
						<text x="559.4473100142045" y="294"
								class="flot-tick-label tickLabel"
								style="position: absolute; text-align: center;">May</text>
						<text x="36.23299893465909" y="294"
								class="flot-tick-label tickLabel"
								style="position: absolute; text-align: center;">January</text>
						<text x="684.0912198153409" y="294"
								class="flot-tick-label tickLabel"
								style="position: absolute; text-align: center;">June</text>
						<text x="160.83638139204544" y="294"
								class="flot-tick-label tickLabel"
								style="position: absolute; text-align: center;">February</text></g>
						<g class="flot-y-axis flot-y1-axis yAxis y1Axis"
								style="position: absolute; inset: 0px;">
						<text x="8.9521484375" y="269" class="flot-tick-label tickLabel"
								style="position: absolute; text-align: right;">0</text>
						<text x="8.9521484375" y="205.5" class="flot-tick-label tickLabel"
								style="position: absolute; text-align: right;">5</text>
						<text x="1" y="15" class="flot-tick-label tickLabel"
								style="position: absolute; text-align: right;">20</text>
						<text x="1" y="142" class="flot-tick-label tickLabel"
								style="position: absolute; text-align: right;">10</text>
						<text x="1" y="78.5" class="flot-tick-label tickLabel"
								style="position: absolute; text-align: right;">15</text></g></svg>
					</div>
				</div>
			</div>

		</div>

			<div class="col-12">
				<div class="card card-secondary card-outline">
					<div class="card-header">
						<h3 class="card-title">세탁량</h3>
						<div class="card-tools">
							<div class="input-group input-group-sm" style="width: 200px;">
								<input type="text" name="table_search"
									class="form-control float-right" placeholder="Search">
								<div class="input-group-append">
									<button type="submit" class="btn btn-default">
										<i class="fas fa-search"></i>
									</button>
								</div>
							</div>
						</div>
					</div>

					<div class="card-body table-responsive p-0">
						<table class="table table-hover text-nowrap">
							<thead>
								<tr>
									<th>글번호</th>
									<th>제목</th>
									<th>담당자</th>
									<th>첨부파일</th>
									<th>등록일자</th>
									<th>비고</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>183</td>
									<td>John Doe</td>
									<td>11-7-2014</td>
									<td><span class="tag tag-success">Approved</span></td>
									<td>Bacon </td>
									<td></td>
								</tr>
								<tr>
									<td>219</td>
									<td>Alexander Pierce</td>
									<td>11-7-2014</td>
									<td><span class="tag tag-warning">Pending</span></td>
									<td>Bacon</td>
									<td></td>
								</tr>
								<tr>
									<td>657</td>
									<td>Bob Doe</td>
									<td>11-7-2014</td>
									<td><span class="tag tag-primary">Approved</span></td>
									<td>Bacon </td>
									<td></td>
								</tr>
								<tr>
									<td>175</td>
									<td>Mike Doe</td>
									<td>11-7-2014</td>
									<td><span class="tag tag-danger">Denied</span></td>
									<td>Bacon </td>
									<td></td>
								</tr>
								<tr>
									<td>175</td>
									<td>Mike Doe</td>
									<td>11-7-2014</td>
									<td><span class="tag tag-danger">Denied</span></td>
									<td>Bacon </td>
									<td></td>
								</tr>
							</tbody>
						</table>
					</div>

				</div>

			</div>
		</div>
</body>



