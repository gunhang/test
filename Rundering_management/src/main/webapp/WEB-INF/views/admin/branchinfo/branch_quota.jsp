<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set value="${dataMap.pageMaker }" var="pageMaker"></c:set>
<c:set value="${dataMap.pageMaker.cri }" var="cri"></c:set>
<c:set value="${dataMap.throughputList }" var="throughputList"></c:set>
<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" var="today" />


<style>
</style>
<head>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.2.1/Chart.bundle.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
</head>
<title>주문량</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.18.1/moment.min.js"></script>
<body>
	<section class="content-header">
		<div class="container-fluid">
			<div class="row mb-2">
				<div class="col-sm-6">
					<h1>지점전체조회</h1>
				</div>
				<div class="col-sm-6">
					<ol class="breadcrumb float-sm-right">
						<li class="breadcrumb-item"><a href="#">Home</a></li>
						<li class="breadcrumb-item active">지점관리</li>
						<li class="breadcrumb-item active">지점전체조회</li>
					</ol>
				</div>
			</div>
		</div>
	</section>
	<div class="row ml-3 mr-3 p-0">
		<div class="card col-12 p-0">
			<div class="card-header">
				<div class="input-group input-group-sm float-right" style="width: 300px;">
					<select class="form-control col-md-4" name="searchType"
						id="searchType">
						<option value="t" ${cri.searchType eq 't' ? 'selected':'' }>지점장</option>
						<option value="c" ${cri.searchType eq 'c' ? 'selected':'' }>지점명</option>
					</select> <input class="form-control" type="text" name="keyword"
						placeholder="검색어를 입력하세요." value=""> <span
						class="input-group-append">
						<button class="btn btn-primary" type="button"
							onclick="list_go(1);" data-card-widget="search">
							<i class="fa fa-fw fa-search"></i>
						</button>
					</span>
				</div>
				<input type="date" class="float-right mr-3" onchange='changeDate(this)' id="todayDate" max="2022-05-05">
			</div>
			<div class="card-body p-0">
				<table class="table table-striped projects"
					style="text-align: center;">
					<thead>
						<tr>
							<th style="width: 10%">지점장</th>
							<th style="width: 20%">지점</th>
							<th>주문량</th>
							<th style="width: 12%">세탁상세</th>
						</tr>
					</thead>
					<tbody style="font-size:0.9em;	">
						<c:if test="${empty throughputList }">
							<tr>
								<td colspan="5" style="text-align: center;"><strong>해당
										내용이 없습니다.</strong></td>
							</tr>
						</c:if>
						<c:forEach items="${throughputList }" var="throughput">
						<fmt:formatDate value="${throughput.date}" pattern="yyyy-MM-dd" var="throughputDate"/>
						<c:if test="${today == throughputDate}">
							<tr
								onclick="getWeeksBranchThroughput('${throughput.branchCode }');"
								style="cursor: pointer;">
								<td>${throughput.name }</td>
								<td>${throughput.branchName }</td>
								<td class="project_progress"><c:if
										test="${throughput.quotaPercent gt 0 and throughput.quotaPercent le 60}">
										<div class="progress progress-sm">
											<div class="progress-bar bg-green" role="progressbar"
												aria-valuenow="${throughput.quotaPercent }"
												aria-valuemin="0" aria-valuemax="100"
												style="width: ${throughput.quotaPercent}%"></div>
										</div>
									</c:if> <c:if
										test="${throughput.quotaPercent gt 60 and throughput.quotaPercent le 80 }">
										<div class="progress progress-sm">
											<div class="progress-bar bg-warning" role="progressbar"
												aria-valuenow="${throughput.quotaPercent }"
												aria-valuemin="0" aria-valuemax="100"
												style="width: ${throughput.quotaPercent}%"></div>
										</div>
									</c:if> <c:if
										test="${throughput.quotaPercent gt 80 and throughput.quotaPercent le 101 }">
										<div class="progress progress-sm">
											<div class="progress-bar bg-red" role="progressbar"
												aria-valuenow="${throughput.quotaPercent }"
												aria-valuemin="0" aria-valuemax="100"
												style="width: ${throughput.quotaPercent}%"></div>
										</div>
									</c:if> <small> ${throughput.quotaPercent }%</small></td>
								<td>
									<button class="btn btn-warning btn-sm"
										onclick="window.open('<%=request.getContextPath()%>/admin/branchinfo/infodetail?branchCode=${throughput.branchCode } ','지점상세', 'width=800, height=800')">세탁상세</button>
								</td>
							</tr>
							</c:if>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="card-footer" style="font-size:0.9em;">
				<%@ include file="/WEB-INF/views/admin/employee/pagination.jsp" %>
			</div>
		</div>
	</div>
	<div class="row ml-3 mr-3 p-0" style="height: 280px; width: 98%">
		<div class="card p-0" style="width: 49%; font-size:0.9em;">
			<div class="card-body">
				<div style="width: 100%;" id="canvasTag">
					<canvas id="canvas"></canvas>
				</div>
			</div>
		</div>
		<div style="width: 1%;"></div>

		<div style="width: 49%; font-size:0.9em;" class="card p-0">
			<div class="card-body" style="height:300px;">
				<table border="1" class="table table-sm throughputTable">
					<thead>
						<tr>
							<th style="width:112px;">날짜</th>
							<th style="width:112px;">세탁가능량</th>
							<th style="width:112px;">할당세탁량</th>
							<th style="width:112px;">세탁량(%)</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach begin="1" end="7">
							<tr>
								<td class="week"></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>
<script>
window.addEventListener('load', onloadWeek);
function onloadWeek(data){
	var now = moment(todayDate.value, "YYYY-MM-DD");
	//console.log(data);
	for(var i=0; i<7; i++){
		document.querySelectorAll('tr .week')[i].innerHTML = now.format('YYYY-MM-DD');
		now.subtract(1, "days").format('YYYY-MM-DD');
	}
}

var todayDate = document.getElementById('todayDate')
todayDate.value = new Date().toISOString().substring(0, 10);
document.querySelector('#todayDate').setAttribute("max", todayDate.value);


	function branchTable(branchCode){
		$.ajax({
			url : '<%=request.getContextPath()%>/admin/branchinfo/branchdata',
			data : {
				'branchCode' : branchCode
			},
			type : 'post',
			success : function(data) {
				onloadWeek(data);
			},
			error : function(error) {
				AjaxErrorSecurityRedirectHandler(error.status);
			}
		});
	}
</script>
<script>

function changeDate(cDate){
	$.ajax({
		url : '<%=request.getContextPath()%>/admin/branchinfo/datedata',
		data : {
			'date' : cDate.value
		},
		type : 'post',
		success : function(data) {
			onloadWeek(data);
		},
		error : function(error) {
			AjaxErrorSecurityRedirectHandler(error.status);
		}
	});
}

</script>

<script>
//지점번호와 날짜로 날짜기준으로 일주일 전까지의 데이터 가져오기
function getWeeksBranchThroughput(branchCode){
		document.querySelector("#canvas").remove;	
		document.querySelector("#canvasTag").innerHTML='<canvas id="canvas"></canvas>'
		
	
	  const todayDate = document.querySelector('#todayDate');
	
	$.ajax({
		url : '<%=request.getContextPath()%>/admin/branchinfo/getWeeksBranchThroughput',
		data : {
			'date' : todayDate.value,
			'branchCode' : branchCode
		},
		type : 'post',
		success : function(data) {
			a=time(data[0].date)
			b=time(data[1].date)
			c=time(data[2].date)
			d=time(data[3].date)
			e=time(data[4].date)
			f=time(data[5].date)
			g=time(data[6].date)
			data1=data[0].totalThroughput
			data2=data[1].totalThroughput
			data3=data[2].totalThroughput
			data4=data[3].totalThroughput
			data5=data[4].totalThroughput
			data6=data[5].totalThroughput
			data7=data[6].totalThroughput
			chart(a,b,c,d,e,f,g,data1,data2,data3,data4,data5,data6,data7)
			
			
			updateTable(data);
			
		},
		error : function(error) {
			AjaxErrorSecurityRedirectHandler(error.status);
		}
	});
}
function time(timeValue){
    var dateObj=new Date(timeValue);
    var year=dateObj.getFullYear();
    var month=dateObj.getMonth()+1;
    var date=dateObj.getDate();
    return year+"-"+month+"-"+date;
}

//테이블에 값 넣기
function updateTable(data){
 	const throughputTable = document.querySelectorAll('.throughputTable tbody tr');
 	throughputTable.forEach(
 			  function(el, Index) {
 				 const newIndex = 6-Index;
 				 el.children[1].innerText = data[newIndex].laundryQuota;
 				 el.children[2].innerText = data[newIndex].totalThroughput;
 				 el.children[3].innerText = Math.floor(data[newIndex].totalThroughput / data[newIndex].laundryQuota * 100);
 			  }
	);
}
</script>

<script>
function chart(a,b,c,d,e,f,g,data1,data2,data3,data4,data5,data6,data7){
	var CHARTEX = $('#canvas');
	var barChartExample = new Chart(CHARTEX , {
	    type: 'bar',
	    data: {
	        labels: [a,b,c,d,e,f,g],
	        datasets: [{
	                label: "세탁량(%)",
	                data: [data1, data2,data3,data4,data5,data6,data7],
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
}


</script>
</body>



