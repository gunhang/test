<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<c:set var="pageMaker" value="${dataMap.pageMaker }" />
<c:set var="cri" value="${dataMap.pageMaker.cri }" />
<c:set var="noticeList" value="${dataMap.noticeList }" />

<style>
</style>


<head>
</head>

<title>주문량</title>

<body>
	<div class="card">
		<div class="card-header">
			<h3 class="card-title">세탁 주문량</h3>
			<div class="card-tools">
				<button type="button" class="btn btn-tool"
					data-card-widget="collapse" title="Collapse">
					<i class="fas fa-minus"></i>
				</button>
				<button type="button" class="btn btn-tool" data-card-widget="remove"
					title="Remove">
					<i class="fas fa-times"></i>
				</button>
			</div>
		</div>
		<div class="card-body p-0">
			<table class="table table-striped projects">
				<thead>
					<tr>
						<th style="width: 20%">지점장</th>
						<th style="width: 20%">지점</th>
						<th>주문량</th>
						<th style="width: 15%" class="text-center">지점상태</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><a> Admin</a></td>
						<td><a>branch</a></td>
						<td class="project_progress">
							<div class="progress progress-sm">
								<div class="progress-bar bg-green" role="progressbar"
									aria-valuenow="57" aria-valuemin="0" aria-valuemax="100"
									style="width: 57%"></div>
							</div> <small> 57% Complete </small>
						</td>
						<td class="project-state"><span class="badge badge-success">원활</span>
						</td>
					</tr>
					<tr>
						<td><a> Admin</a></td>
						<td><a>branch</a></td>
						<td class="project_progress">
							<div class="progress progress-sm">
								<div class="progress-bar bg-green" role="progressbar"
									aria-valuenow="47" aria-valuemin="0" aria-valuemax="100"
									style="width: 47%"></div>
							</div> <small> 47% Complete </small>
						</td>
						<td class="project-state"><span class="badge badge-success">원활</span>
						</td>
					</tr>
					<tr>
						<td><a> Admin</a></td>
						<td><a>branch</a></td>
						<td class="project_progress">
							<div class="progress progress-sm">
								<div class="progress-bar bg-yellow" role="progressbar"
									aria-valuenow="82" aria-valuemin="0" aria-valuemax="100"
									style="width: 82%"></div>
							</div> <small> 82% Complete </small>
						</td>
						<td class="project-state"><span class="badge badge-warning">혼잡</span>
						</td>
					</tr>
					<tr>
						<td><a> Admin</a></td>
						<td><a>branch</a></td>
						<td class="project_progress">
							<div class="progress progress-sm">
								<div class="progress-bar bg-green" role="progressbar"
									aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"
									style="width: 60%"></div>
							</div> <small> 60% Complete </small>
						</td>
						<td class="project-state"><span class="badge badge-success">원활</span>
						</td>
					</tr>
					<tr>
						<td><a> Admin</a></td>
						<td><a>branch</a></td>
						<td class="project_progress">
							<div class="progress progress-sm">
								<div class="progress-bar bg-green" role="progressbar"
									aria-valuenow="12" aria-valuemin="0" aria-valuemax="100"
									style="width: 12%"></div>
							</div> <small> 12% Complete </small>
						</td>
						<td class="project-state"><span class="badge badge-success">원활</span>
						</td>
					</tr>
					<tr>
						<td><a> Admin</a></td>
						<td><a>branch</a></td>
						<td class="project_progress">
							<div class="progress progress-sm">
								<div class="progress-bar bg-green" role="progressbar"
									aria-valuenow="35" aria-valuemin="0" aria-valuemax="100"
									style="width: 35%"></div>
							</div> <small> 35% Complete </small>
						</td>
						<td class="project-state"><span class="badge badge-success">원활</span>
						</td>
					</tr>
					<tr>
						<td><a> Admin</a></td>
						<td><a>branch</a></td>
						<td class="project_progress">
							<div class="progress progress-sm">
								<div class="progress-bar bg-red" role="progressbar"
									aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"
									style="width: 100%"></div>
							</div> <small> 100% Complete </small>
						</td>
						<td class="project-state"><span class="badge badge-danger">초과</span>
						</td>
					</tr>
		
				</tbody>
			</table>
		</div>

	</div>

</body>



