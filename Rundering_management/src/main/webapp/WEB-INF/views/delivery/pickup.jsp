<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set value="${dataMap.pickupList }" var="pickupList"></c:set>
<c:set value="${dataMap.pickupCompleteList }" var="pickupCompleteList"></c:set>


<body>
	<div class="card card-outline card-outline-tabs card-info">
		<div class="card-header p-0 border-bottom-0"
			style="font-size: 1.4rem;">
			<ul class="nav nav-tabs justify-content-center"
				id="custom-tabs-four-tab" role="tablist">
				<li class="nav-item"><a class="nav-link active"
					id="custom-tabs-four-home-tab" data-toggle="pill"
					href="#custom-tabs-four-home" role="tab"
					aria-controls="custom-tabs-four-home" aria-selected="true">진행중
						수거 목록</a></li>
				<li class="nav-item"><a class="nav-link"
					id="custom-tabs-four-profile-tab" data-toggle="pill"
					href="#custom-tabs-four-profile" role="tab"
					aria-controls="custom-tabs-four-profile" aria-selected="false">완료된
						수거 목록</a></li>
			</ul>
		</div>
		<div class="card-body p-0">
			<div class="tab-content" id="custom-tabs-four-tabContent">
				<div class="tab-pane fade active show" id="custom-tabs-four-home"
					role="tabpanel" aria-labelledby="custom-tabs-four-home-tab">
					<table class="table table-striped" style="font-size: 1.2rem;" id="inventory">
						<thead>
							<tr>
								<th>주문번호</th>
								<th style="cursor:pointer;" onclick="sortAddr('${pickupList[0].branchCode}');">배송 주소지</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${empty pickupList }">
								<tr>
									<td colspan="2" style="text-align: center;"><strong>수거할 세탁물이 없습니다</strong></td>
								</tr>
							</c:if>
							<c:forEach items="${pickupList }" var="pList">
								<tr onclick="location.href='<%=request.getContextPath()%>/fordelivery/pickupdetail?orderNo=${pList.orderNo }&orderStatus=02'">
									<td class="aa">${pList.orderNo }</td>
									<td class="bb">${pList.add1 }${pList.add2 }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="tab-pane fade" id="custom-tabs-four-profile"
						role="tabpanel" aria-labelledby="custom-tabs-four-profile-tab">
					<table class="table table-striped" style="font-size: 1.2rem;">
						<thead>
							<tr>
								<th>주문번호</th>
								<th style="cursor:pointer;" onclick="sortAddr1('${pickupList[0].branchCode}');">배송 주소지</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${empty pickupCompleteList }">
								<tr>
									<td colspan="2" style="text-align: center;"><strong>수거완료된 세탁물이 없습니다</strong></td>
								</tr>
							</c:if>
							<c:forEach items="${pickupCompleteList }" var="list">
								<tr onclick="location.href='<%=request.getContextPath()%>/fordelivery/pickupdetail?orderNo=${list.orderNo }&orderStatus=03'">
									<td id="orderNo" class="aa">${list.orderNo }</td>
									<td class="bb">${list.add1 }${list.add2 }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<c:if test="${fn:length(pickupList) == 0 && fn:length(pickupCompleteList) != 0}">
						<div onclick="complete_all();"
							class="info-box bg-light" style="position: fixed;
								bottom: 0;">
							<div class="info-box-content" onclick="complete_all()">
								<span class="info-box-text text-center text-muted"
									style="font-size: 2rem;">지점 전달 완료</span>
							</div>
						</div>
					</c:if>
				</div>
			</div>
		</div>
	</div>




<!-- 알림 sweetalert2 -->
<script	src="<%=request.getContextPath()%>/resources/bootstrap/plugins/sweetalert2/sweetalert2.all.min.js"></script>
<script>
var sortValue = "0";
var aa = document.querySelector('.aa');
var bb = document.querySelector('.bb');

function sortAddr(branchCode){
	$.ajax({
		url : '<%=request.getContextPath()%>/fordelivery/sortAddress',
		data : {
			orderStatus : '02',
			branchCode : branchCode,
			sortValue : sortValue
		},
		type : 'post',
		success : function(haha) {
			for(var i=0; i<haha.length; i++){
				var aaClass = haha[i].orderNo
				var bbClass = haha[i].add1 + haha[i].add2
				
				document.querySelectorAll('tr .aa')[i].innerHTML = aaClass;
				document.querySelectorAll('tr .bb')[i].innerHTML = bbClass;
			}
			if(sortValue=="0"){
				sortValue = "1";
			}else if(sortValue=="1"){
				sortValue = "0";
			}
		},
		error : function(error) {
			AjaxErrorSecurityRedirectHandler(error.status);
		}
	});
}


function sortAddr1(branchCode){
	$.ajax({
		url : '<%=request.getContextPath()%>/fordelivery/sortAddress',
		data : {
			orderStatus : '03',
			branchCode : branchCode,
			sortValue : sortValue
		},
		type : 'post',
		success : function(haha) {
			for(var i=0; i<haha.length; i++){
				var aaClass = haha[i].orderNo
				var bbClass = haha[i].add1 + haha[i].add2
				
				document.querySelectorAll('tr .aa')[i].innerHTML = aaClass;
				document.querySelectorAll('tr .bb')[i].innerHTML = bbClass;
			}
			if(sortValue=="0"){
				sortValue = "1";
			}else if(sortValue=="1"){
				sortValue = "0";
			}
		},
		error : function(error) {
			AjaxErrorSecurityRedirectHandler(error.status);
		}
	});
}


</script>


		
	
<script>
	function complete_all(){
		Swal.fire({
            title: '일괄처리 하시겠습니까?',
            text: '확인시 지점으로 전달 완료됩니다.',
            icon : 'warning' ,
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: '승인',
            cancelButtonText: '취소',
            reverseButtons: true, // 버튼 순서 거꾸로
          }).then((result) => {
          if (result.isConfirmed) {
			$.ajax({
				url : '<%=request.getContextPath()%>/fordelivery/arrive/tobranch',
				data : {},
				type : 'post',
				success : function(ok) {
					if(ok.toUpperCase() == "OK"){
						Swal.fire({
							icon: 'success', // 여기다가 아이콘 종류를 쓰면 됩니다.
							title: '취소 처리 되었습니다.',
						});
						setTimeout(function(){location.href='<%=request.getContextPath()%>/fordelivery/pickup';},1000);
					} else {
						Swal.fire({
							icon: 'warning', // 여기다가 아이콘 종류를 쓰면 됩니다.
							title: '시스템 오류로 반려 할 수 없습니다.'
						});
					}
				},
				error : function(error) {
					AjaxErrorSecurityRedirectHandler(error.status);
				}
			});
		}
	})
}
</script>
</body>


