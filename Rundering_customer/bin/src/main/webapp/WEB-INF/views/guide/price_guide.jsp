<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="laundryItemsList" value="${dataMap.laundryItemsList }" />


<section class="content-header">
	<div class="container-fluid">
		<div class="row mb-2">
			<div class="col-sm-6">
				<h1>가격안내</h1>
			</div>
			<div class="col-sm-6">
				<ol class="breadcrumb float-sm-right">
					<li class="breadcrumb-item active">가격안내</li>
					<li class="breadcrumb-item"><a href="#">이용안내</a></li>
				</ol>
			</div>
		</div>
	</div>
</section>

<div class="col-12 col-sm-12">
	<div class="card card-primary card-tabs">
		<div class="card-header p-0 pt-1">
			<ul class="nav nav-tabs" id="custom-tabs-two-tab" role="tablist">
				<li class="pt-2 px-3"><h3 class="card-title">구분</h3></li>
				<li class="nav-item"><a class="nav-link active"
					id="custom-tabs-two-home-tab" data-toggle="pill"
					href="#custom-tabs-two-home" role="tab"
					aria-controls="custom-tabs-two-home" aria-selected="true">의류</a></li>
				<li class="nav-item"><a class="nav-link"
					id="custom-tabs-two-profile-tab" data-toggle="pill"
					href="#custom-tabs-two-profile" role="tab"
					aria-controls="custom-tabs-two-profile" aria-selected="false">신발</a>
				</li>
				<li class="nav-item"><a class="nav-link"
					id="custom-tabs-two-messages-tab" data-toggle="pill"
					href="#custom-tabs-two-messages" role="tab"
					aria-controls="custom-tabs-two-profile" aria-selected="false">침구</a>
				</li>
			</ul>
		</div>
		<div class="card-body">
			<div class="tab-content" id="custom-tabs-two-tabContent">
				<div class="tab-pane fade show active" id="custom-tabs-two-home"
					role="tabpanel" aria-labelledby="custom-tabs-two-home-tab">
					<div class="card-body table-responsive p-0" style="height: 500px;">
						<table class="table table-head-fixed text-nowrap">
							<thead>
								<tr>
									<th>품목</th>
									<th>기본요금</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${empty laundryItemsList }">
										<tr>
											<td colspan="5" style="text-align: center;"><strong>물품이
													없습니다.</strong></td>
										</tr>
									</c:if>
								<c:if test="${!empty laundryItemsList }">
									<c:forEach items="${laundryItemsList }" var="laundryItems">
										<c:if test="${laundryItems.laundryCategory eq 'CL' }">
											<tr>
												<td>${laundryItems.itemsName }</td>
												<td>${laundryItems.price }원</td>
											</tr>
										</c:if>
									</c:forEach>
								</c:if>
							</tbody>
						</table>
					</div>
				</div>
				<div class="tab-pane fade" id="custom-tabs-two-profile"
					role="tabpanel" aria-labelledby="custom-tabs-two-profile-tab">
					<div class="card-body table-responsive p-0" style="height: 500px;">
						<table class="table table-head-fixed text-nowrap">
							<thead>
								<tr>
									<th>품목</th>
									<th>기본요금</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${!empty laundryItemsList }">
									<c:forEach items="${laundryItemsList }" var="laundryItems">
										<c:if test="${laundryItems.laundryCategory eq 'SH' }">
											<tr>
												<td>${laundryItems.itemsName }</td>
												<td>${laundryItems.price }원</td>
											</tr>
										</c:if>
									</c:forEach>
								</c:if>
							</tbody>
						</table>
					</div>
				</div>
				<div class="tab-pane fade" id="custom-tabs-two-messages"
					role="tabpanel" aria-labelledby="custom-tabs-two-messages-tab">
					<div class="card-body table-responsive p-0" style="height: 500px;">
						<table class="table table-head-fixed text-nowrap">
							<thead>
								<tr>
									<th>품목</th>
									<th>기본요금</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${!empty laundryItemsList }">
									<c:forEach items="${laundryItemsList }" var="laundryItems">
										<c:if test="${laundryItems.laundryCategory eq 'BE' }">
											<tr>
												<td>${laundryItems.itemsName }</td>
												<td>${laundryItems.price }원</td>
											</tr>
										</c:if>
									</c:forEach>
								</c:if>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>

	</div>
</div>