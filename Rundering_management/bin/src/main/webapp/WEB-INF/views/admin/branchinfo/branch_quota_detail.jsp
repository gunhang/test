<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set value="${dataMap.throughput }" var="throughput"/>
<c:set value="${dataMap.throughputList }" var="throughputList"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.2.1/Chart.bundle.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
</head>
<body>
	<div class="row ml-3 mr-3">
		<div class="card col-12">
			<table class="table table-striped projects">
				<thead>
					<tr>
						<th style="width: 20%">지점장</th>
						<th style="width: 20%">지점</th>
						<th>주문량</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td></td>
						<td>${throughput.branchName }</td>
						<td class="project_progress">
							<div class="progress progress-sm">
								<div
									class="progress-bar
									<c:if test="${throughput.quotaPercent le 60}">
								 bg-green
								 	</c:if>
								 	<c:if test="${throughput.quotaPercent gt 60 and throughput.quotaPercent le 80 }">
								 bg-warning
								 	</c:if>
								 	<c:if test="${throughput.quotaPercent gt 80 and throughput.quotaPercent le 101 }">
								 bg-red
								 	</c:if>"
									role="progressbar" aria-valuenow="${throughput.quotaPercent }"
									aria-valuemin="0" aria-valuemax="100"
									style="width: ${throughput.quotaPercent}%"></div>
							</div> <small> ${throughput.quotaPercent }%</small>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<div class="card ml-3 mr-3">
		<div class="card-body" class="ml-3 mr-3">
			<div style="width: 280px;">
				<canvas id="canvas"></canvas>
			</div>
		</div>
	</div>

<script>
var CHARTEX = $('#canvas');
    var barChartExample = new Chart(CHARTEX    , {
        type: 'bar',
        data: {
            labels: [1, 2, 3, 4, 5, 6, 7],
            datasets: [{
                    label: "세탕량(%)",
                    data: [15, 60, 56, 60, 6, 45, 1],
                    backgroundColor: 'rgba(54, 162, 235, 0.5)',
                    borderColor: 'rgba(54, 162, 235, 1)',
                    borderWidth: 1
                }]
        },
        options: {
            responsive: true,
            legend: {
                position: 'bottom',
            },
            hover: {
                mode: 'label'
            },
            scales: {
	            yAxes: [{
	                    display: true,
	                    ticks: {
	                        beginAtZero: true,
	                        steps: 20,
	                        stepValue: 10,
	                        max: 100
	                    }
	                }]
            },
            title: {
                display: true,
                text: '일별 세탁량'
            }
        }
    });

</script>
</body>
</html>