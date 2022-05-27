<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1>배송현황</h1>
	<div class="card card-primary card-outline ml-3 mr-3">
		<div class="card-header">
			<h3 class="card-title">Responsive Hover Table</h3>
			<div class="card-tools">
				<div class="input-group row">
					<!-- search bar -->
					<!-- sort num -->
					<select class="form-control col-md-3" name="perPageNum"
						id="perPageNum" onchange="list_go(1);">
						<option value="10" selected="">검색구분</option>
						<option value="2">배송담당자</option>
						<option value="3">날짜</option>
					</select>
					<!-- search bar -->
					<select class="form-control col-md-3" name="searchType"
						id="searchType">
						<option value="" selected="">상태</option>
						<option value="">수거중</option>
						<option value="">수거완료</option>
						<option value="">배송중</option>
						<option value="">배송완료</option>
					</select>
					<!-- keyword -->
					<input class="form-control" type="text" name="keyword"
						placeholder="검색어를 입력하세요." value=""> <span
						class="input-group-append">
						<button class="btn btn-primary" type="button" id="searchBtn"
							data-card-widget="search" onclick="list_go(1);">
							<i class="fa fa-fw fa-search"></i>
						</button>
					</span>
					<!-- end : search bar -->
				</div>
			</div>
		</div>

		<div class="card-body table-responsive p-0">
			<table class="table table-hover text-nowrap">
				<thead>
					<tr>
						<th>주문번호</th>
						<th>담당자</th>
						<th>날짜</th>
						<th>상태</th>
						<th>주소</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>183</td>
						<td><a onclick="OpenWindow('http://localhost/runderingmanage/admin/delivery/detail.do','글등록',800,700);" style="cursor:pointer;">게시글등록</a></td>
						<td>2022-03-30</td>
						<td><span class="badge bg-warning">배송중</span></td>
						<td>대전 유성구 노은동로 187 609동 402호</td>
					</tr>
					<tr>
						<td>219</td>
						<td>이수윤</td>
						<td>2022-03-30</td>
						<td><span class="badge bg-success">배송완료</span></td>
						<td>대전 유성구 노은동로 187 609동 402호</td>
					</tr>
					<tr>
						<td>657</td>
						<td>오세준</td>
						<td>2022-03-30</td>
						<td><span class="badge bg-warning">수거중</span></td>
						<td>대전 유성구 노은동로 187 609동 402호</td>
					</tr>
					<tr>
						<td>175</td>
						<td>백관우</td>
						<td>2022-03-30</td>
						<td><span class="badge bg-success">수거완료</span></td>
						<td>대전 유성구 노은동로 187 609동 402호</td>
					</tr>
				</tbody>
			</table>

		</div>
	</div>
<script>
function OpenWindow(UrlStr, WinTitle, WinWidth, WinHeight) {
	   winleft = (screen.width - WinWidth) / 2;
	   wintop = (screen.height - WinHeight) / 2;
	   var win = window.open(UrlStr , WinTitle , "scrollbars=yes,width="+ WinWidth +", " 
	                     +"height="+ WinHeight +", top="+ wintop +", left=" 
	                     + winleft +", resizable=yes, status=yes"  );
	   win.focus() ; 
	}
</script>
</body>
</html>