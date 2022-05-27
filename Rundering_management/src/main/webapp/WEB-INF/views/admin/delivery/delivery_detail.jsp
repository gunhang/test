<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>배달 현황 상세 정보</title>
<script
	src="https://cdn.jsdelivr.net/npm/admin-lte@3.2/dist/js/adminlte.min.js"></script>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/admin-lte@3.2/dist/css/adminlte.min.css">
</head>
<body>
	<div class="com ml-3 mr-3">
		<section class="content-header">
			<div class="row mb-2">
				<div class="col-sm-6">
					<h1>배달 현황 상세 정보</h1>
				</div>
			</div>
		</section>
		<div class="form-row">
			<div class="form-group col-md-6">
				<label for="inputname">name</label> <input type="text"
					class="form-control" placeholder="구건회" disabled>
			</div>
			<div class="form-group col-md-6">
				<label for="inputphone">phone</label> <input type="text"
					class="form-control" placeholder="010-0000-0000" disabled>
			</div>
		</div>
		<div class="form-row">
			<div class="form-group col-md-7">
				<label for="inputEmail4">Email</label> <input type="email"
					class="form-control" id="inputEmail" placeholder="rnrjsghl1996@naver.com" disabled>
			</div>
			<div class="form-group col-md-5">
				<label for="inputEmail4">상태</label> 
				<select class="form-control col-md-12" name="perPageNum"
					id="perPageNum" onchange="list_go(1);" disabled>
					<option value="1">수거중</option>
					<option value="2">수거완료</option>
					<option value="3">배송중</option>
					<option value="4">배송완료</option>
				</select>
			</div>
				
		</div>
		<div class="form-row">
			<div class="form-group col-md-12">
				<label for="inputAddress">Address</label> <input type="text"
					class="form-control" id="inputAddress" placeholder="노은동로 187 609동 402호" disabled>
			</div>
		</div>
	</div>

	<div class="card card-primary card-outline ml-3 mr-3">
		<div class="card-header">
			<h3 class="card-title">Fixed Header Table</h3>
		</div>

		<div class="card-body table-responsive p-0" style="height: 550px;">
			<table class="table table-head-fixed text-nowrap">
				<thead>
					<tr>
						<th style="width:30%">세탁물명</th>
						<th style="width:20%">단가</th>
						<th style="width:30%">수량</th>
						<th style="width:20%">총 합계</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>와이셔츠</td>
						<td>2000</td>
						<td>5</td>
						<td>10000</td>
					</tr>
					<tr>
						<td>수건</td>
						<td>1500</td>
						<td>10</td>
						<td>15000</td>
					</tr>
					<tr>
						<td>양말</td>
						<td>1000</td>
						<td>4</td>
						<td>4000</td>
					</tr>
					<tr>
						<td>팬티</td>
						<td>1000</td>
						<td>11-7-2014</td>
						<td>11-7-2014</td>
					</tr>
					<tr>
						<td>니트</td>
						<td>1000</td>
						<td>11-7-2014</td>
						<td>11-7-2014</td>
					</tr>
					<tr>
						<td>신발</td>
						<td>1000</td>
						<td>11-7-2014</td>
						<td>11-7-2014</td>
					</tr>
					<tr>
						<td>뭐가있지</td>
						<td>1000</td>
						<td>11-7-2014</td>
						<td>11-7-2014</td>
					</tr>
					<tr>
						<td>코트</td>
						<td></td>
						<td>1000</td>
						<td>11-7-2014</td>
					</tr>
					<tr>
						<th></th>
						<th></th>
						<th></th>
						<th>29000</th>
					</tr>
					
				</tbody>
			</table>
		</div>

	</div>

</body>
</html>