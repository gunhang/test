<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="cri" value="${pageMaker.cri }" />



<body>

	<!-- 상세검색 카드 -->
	<div class="card ml-3 mr-3 mb-0">
		<form class="form-horizontal">
			<div class="card-body">
				<div class="form-group row m-0">
					<label for="orderstatus" class="col-sm-1 col-form-label">주문상태</label>
					<div class="col-sm-11 p-2 pl-4" style="display: inline-flex;">
						<c:forEach items="${orderCodeMap }" var="orderCode" >
							<div class="form-check ">
								<input class="form-check-input" type="checkbox" name="orderStatus" id="${orderCode.key}" onclick="list_go('1');" value="${orderCode.key}" ${fn:contains(cri.orderStatus, orderCode.key) ? 'checked' : ''} ><label class="form-check-label" for="${orderCode.key}">${orderCode.value}&nbsp;&nbsp;</label>
							</div>
						</c:forEach>
					</div>
				</div>
				<div class="form-group row m-0">
					<label for="dates" class="col-sm-1 col-form-label pr-0">수거요청일</label>
					<div class="col-sm-4 pl-4">
						<div class="input-group">
							<div class="input-group-prepend">
								<span class="input-group-text"> <i class="far fa-calendar-alt"></i></span>
							</div>
							<input type="text" id="dates" class="form-control" name="pickupRequestDate" value="${cri.pickupRequestDate }">
						</div>
					</div>
					<label for="branchCode" class="col-sm-1 col-form-label ml-3" style="text-align: end;">담당지점</label>
					<div class="col-sm-2">
						<select class="form-control" name="branchCode" id="branchCode" style="width: auto;" onchange="list_go('1')">
								<option value="">전체</option>
								<option value="notAssigned" ${cri.branchCode eq 'notAssigned' ? 'selected':'' }>미할당</option>
								<c:forEach items="${branchNameMap }" var="branchCode">
									<c:if test="${branchCode.key ne '000000'}">
									<option value="${branchCode.key }"
										${cri.branchCode eq branchCode.key ? 'selected':'' }>${branchCode.value }</option>
									</c:if>
								</c:forEach>
						</select>
					</div>
					<label for="area" class="col-sm-1 col-form-label ml-5" style="text-align: end;">지역분류</label>
					<div class="col-sm-2">
						<select class="form-control" name="area" id="area" style="width: auto;" onchange="list_go('1')">
								<option value="">전체</option>
								<c:forEach items="${areaCodeMap }" var="areaCode">
										<option value="${areaCode.key }"
											${cri.area eq areaCode.key ? 'selected':'' }>${areaCode.value }</option>
								</c:forEach>
						</select>
					</div>
				</div>
			</div>
		</form>
	</div>
	
	
	<div class="row ml-3 mr-3">
		<div class="col-12 p-0">
			<div class="card m-0 card-secondary card-outline">
			<div class="card-header">
					<h3 class="card-title" style="font-size: 1.75rem;">세탁 주문</h3>
					<span class="text-muted" style="display: inline-block;margin-top: 6px;padding-left: 15px;">검색결과 <fmt:formatNumber type="number" maxFractionDigits="3" value="${pageMaker.totalCount }" />개</span>
					<button type="button" class="btn btn-outline-primary ml-3" data-toggle="modal" data-target="#modal-lg">선택주문 지점할당</button>
					<div class="card-tools">
						<div class="input-group input-group-sm" style="width: 200px;margin-top: auto;">
							<input class="form-control" type="text" name="orderNo" placeholder="주문번호 입력" value="${cri.orderNo }"> <span class="input-group-append">
									<button class="btn btn-primary" type="button" onclick="list_go(1);" data-card-widget="search">
										<i class="fa fa-fw fa-search"></i>
									</button>
						</div>
					</div>
				</div>

				<div class="card-body table-responsive p-0 mt-0">
					<table
						class="table table-hover text-nowrap" style="text-align: center;">
						<thead>
							<tr>
								<th class="pr-0"><input type="checkbox" onclick="selectAll(this);" name="selectAllOrderNo" value="true"></th>
								<th style="width: 230px;">주문일시</th>
								<th>주문번호</th>
								<th>수거요청일</th>
								<th>지역분류</th>
								<th>담당지점</th>
								<th>주문상태</th>
							</tr>   
						</thead>    
						<tbody>
								<c:if test="${empty laundryOrderList }" >
									<tr>
										<td colspan="7">
											<strong>해당하는 주문내역이 없습니다.</strong>
										</td>
									</tr>
								</c:if>			
							<c:forEach items="${laundryOrderList }" var="laundryOrder" >
								<tr onclick="window.open('<%=request.getContextPath()%>/admin/laundryorder/detail.do?orderNo=${laundryOrder.orderNo }', '주문 상세', 'width=800, height=900');"
									style="cursor: pointer;">
									<td class="pr-0"><input type="checkbox" name="selectOrderNo" value="${laundryOrder.orderNo }" onclick="checkSelectAll();"></td>
									<td><fmt:formatDate value="${laundryOrder.orderDate }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
									<td>${laundryOrder.orderNo }</td>
									<td><fmt:formatDate value="${laundryOrder.pickupRequestDate }" pattern="yyyy-MM-dd"/></td>
									<td>${areaCodeMap[laundryOrder.area]}</td>
									<c:if test="${!empty laundryOrder.branchCode }"> 
									<td>${branchNameMap[laundryOrder.branchCode]}</td>
									</c:if>
									<c:if test="${empty laundryOrder.branchCode }"> 
									<td>미할당</td>
									</c:if>
									<td>${orderCodeMap[laundryOrder.orderStatus]}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>


					<div class="card-footer">
						<%@ include file="/WEB-INF/views/admin/laundryorder/laundry_order_pagination.jsp" %>
					</div>
					
					<div class="modal fade" id="modal-lg" style="display: none;"
						aria-hidden="true">
						<div class="modal-dialog modal-lg">
							<div class="modal-content">
								<div class="modal-header">
									<h4 class="modal-title">담당지점 직접할당</h4>
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">×</span>
									</button>
								</div>
								<div class="modal-body">
									<div class="row">
										<div class="col-md-6">
											<div class="card">
												<div class="card-header">
													<h3 class="card-title">선택한 주문</h3>
													<span class="text-muted float-right selectOrderCounts"></span>
												</div>

												<div class="card-body table-responsive p-0" style="max-height: 480px;">
													<table class="table table-sm table-head-fixed selectOrderList">
														<thead>
															<tr>
																<th>주문번호</th>
																<th>지역</th>
																<th>주문상태</th>
															</tr>
														</thead>
														<tbody>

														</tbody>
													</table>
												</div>

											</div>

										</div>

										<div class="col-md-6">


											<div class="card">
												<div class="card-header">
													<h3 class="card-title">지점목록</h3>
												</div>

												<div class="card-body table-responsive p-0" style="max-height: 480px;">
													<table class="table table-head-fixed branchList">
														<thead>
															<tr>
																<th>지점명</th>
																<th>할당량</th>
																<th colspan="2" style="text-align: center;">할당률</th>
															</tr>
														</thead>
														<tbody>
														
														</tbody>
													</table>
												</div>

											</div>

										</div>

									</div>



								</div>
								<div class="modal-footer justify-content-between">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">닫기</button>
									<button type="button" class="btn btn-primary" onclick="directAssignment()">할당</button>
								</div>
							</div>
						</div>
					</div>

				</div>

			</div>
		</div>
	</div>
	

	<script>
		//daterangepicker 설정
		moment.locale('ko'); //언어를 한국어로 설정함!
		  $('#dates').daterangepicker(
		    {
		      timePicker: false,
		      timePicker24Hour: true,
		      timePickerSeconds: true,
		      singleDatePicker: false,
		      autoUpdateInput: false,
		      locale :{ 
		        format: 'YYYY-MM-DD',
		        separator: '~',
		        applyLabel: "적용",
		        cancelLabel: "적용취소"
		      },
		    });
		  $('input[name="pickupRequestDate"]').on('apply.daterangepicker', function(ev, picker) {
		      $(this).val(picker.startDate.format('YYYY-MM-DD') + '~' + picker.endDate.format('YYYY-MM-DD'));
		      list_go('1');
		  });

		  $('input[name="pickupRequestDate"]').on('cancel.daterangepicker', function(ev, picker) {
		      $(this).val('');
		      list_go('1');
		  });
	</script>
	
	<script>
	//체크박스 전체선택
	function selectAll(selectAll)  {
		  const checkboxes  = document.querySelectorAll('input[name="selectOrderNo"]');
		  checkboxes.forEach((checkbox) => {
		    checkbox.checked = selectAll.checked
		  })
		}
	
    //체크박스 전체선택 자동 선택,해제
	function checkSelectAll()  {
		  // 전체 체크박스
		  const checkboxes = document.querySelectorAll('td>input[name=selectOrderNo]');
		  // 선택된 체크박스
		  const checked = document.querySelectorAll('td>input[name=selectOrderNo]:checked');
		  // select all 체크박스
		  const selectAll = document.querySelector('th>input[name=selectAllOrderNo]');
		  
		  console.log(checkboxes.length);
		  console.log(checked.length);
		  
		  if(checkboxes.length === checked.length)  {
		    selectAll.checked = true;
		  }else {
		    selectAll.checked = false;
		  }
		}
      
	//체크박스 선택시 tr에 걸린 상세정보 클릭되는 이벤트 버블링 막기
     function init() {
	  const checkboxes  = document.querySelectorAll('input[name="selectOrderNo"]');
	  checkboxes.forEach((checkbox) => {
		  checkbox.addEventListener('click', (event) => {
                  event.stopPropagation();
              })
           })
      }
      init();
      
	</script>
	
	<script>
	let assignSelectOrderNoArr = [];
	
	//선택한 주문 있는지 확인 후 모달창 띄울지말지
	$('#modal-lg').on('show.bs.modal', function (e) {
		const selectAllOrderNo = document.querySelector('input[name="selectAllOrderNo"]');
		
		let selectOrderNoArr = [];
		
		$('td>input[name="selectOrderNo"]:checked').each(function(i){
			selectOrderNoArr.push($(this).val());
		});
	if(!selectAllOrderNo.checked && selectOrderNoArr.length == 0){
		e.preventDefault();
		alert('주문을 선택해주세요.');
		$(".selectOrderList>tbody").empty();
		$(".selectOrderCounts").empty();
		$(".branchList>tbody").empty();
	}else{
		confirmDirectAssignment();
	}
		});


	
	function confirmDirectAssignment(){
		const selectAllOrderNo = document.querySelector('input[name="selectAllOrderNo"]');
		
			let orderStatusArr = [];
			let selectOrderNoArr = [];
			
		if(selectAllOrderNo.checked){
			$('input[name="orderStatus"]:checked').each(function(i){
				orderStatusArr.push($(this).val());
			});
		}else{
			$('td>input[name="selectOrderNo"]:checked').each(function(i){
				selectOrderNoArr.push($(this).val());
			});
		}
			 $.ajax({
				url: "<%=request.getContextPath()%>/admin/laundryorder/confirmAssignment",
				type:'POST',
				data: JSON.stringify ({
					"listOrderStatus": orderStatusArr,
					"pickupRequestDate": $('div.input-group>input[name="pickupRequestDate"]').val(),
					"branchCode": $('select[name="branchCode"]').val(),
					"area": $('select[name="area"]').val(),
					"orderNo": $('div.input-group>input[name="orderNo"]').val(),
					"listSelectOrderNo": selectOrderNoArr,
					"selectAllOrderNo": (selectAllOrderNo.checked)
				}),
				contentType:'application/json',
				dataType: "json",
				success:function(data){
						 let alertYN = false;
					 if(data.laundryOrderList.length==0){
						 $(".selectOrderList>tbody").append("<tr><td colspan='3'>선택된 주문정보가 없습니다.</td></tr>");
					 }else{
						 $(data.laundryOrderList).each(function(i) {
							 if(data.laundryOrderList[i].branchCode != null) alertYN = true;
							 assignSelectOrderNoArr.push(data.laundryOrderList[i].orderNo);
							 let orderAdd = "<tr>"+
							 "<td>"+data.laundryOrderList[i].orderNo+"</td>"+
							 "<td>"+data.areaCodeMap[data.laundryOrderList[i].area]+"</td>"+
							 "<td>"+data.orderCodeMap[data.laundryOrderList[i].orderStatus]+"</td>"+
							 "</tr>";
							$(".selectOrderList>tbody").append(orderAdd);
						 })
						$(".selectOrderCounts").text(data.totalCount+'개');
						 
					 }
					 $(data.branchList).each(function(i) {
						 if(data.branchList[i].branchCode == '000000'){
							 return;
						 }
						 //해당지점 현재 처리중인 주문량 / 지점의 세탁가능수량 * 100)
						  let excessCapacity = data.excessCapacityList.filter(branch => branch.branchCode == data.branchList[i].branchCode);
						  let processingRate = (data.branchList[i].branchLndrpcrymslmcoqy - excessCapacity[0].branchLndrpcrymslmcoqy)  / data.branchList[i].branchLndrpcrymslmcoqy * 100
						  let bgColor =  "bg-primary";
						  if(processingRate >= 90){  
							  bgColor = "bg-danger";
						  }else if(processingRate >= 70){
							  bgColor = "bg-warning";
						  }
							 let branchAdd = "<tr style='cursor: pointer;' onclick='selectbranch(this);'>"+
							 "<td>"+data.branchList[i].branchName+"</td>"+
							 "<td>"+(data.branchList[i].branchLndrpcrymslmcoqy - excessCapacity[0].branchLndrpcrymslmcoqy)+'/'+data.branchList[i].branchLndrpcrymslmcoqy+"</td>"+
							 "<td style='width: 90px;padding-top: 21px;'><div class='progress progress-xs'><div class='progress-bar "+bgColor+"' style='width: "+processingRate+"%'></div></div></td>"+
							 "<td style='width: 30px;padding-left: 0px;padding-right: 10px;text-align: center;'><span class='badge "+bgColor+"'>"+processingRate+"%</span><input type='radio' value='"+data.branchList[i].branchCode+"' name='branchradio'  style= 'display: none'/></td>"+
							 "</tr>"; 
							 
							$(".branchList>tbody").append(branchAdd);
						 
					 })
					 if(alertYN) alert('이미 담당지점이 할당된 주문건이 있습니다.\n정확히 검토 후 할당 바랍니다.');
				},
				error:function(error){
				alert("현재 세탁주문 지점할당이 불가합니다. \n관리자에게 연락바랍니다.");
					/* AjaxErrorSecurityRedirectHandler(error.status); */
				}
			});
	}
	//모달창 닫힐 때마다 데이터 지우기
	$('#modal-lg').on('hidden.bs.modal', function (e) {
		$(".selectOrderList>tbody").empty();
		$(".selectOrderCounts").empty();
		$(".branchList>tbody").empty();
	})
	//지점 tr클릭시 숨겨진 라디오버튼 체크
	function selectbranch(tr){
		tr.lastChild.lastChild.checked = true;
		tr.style.backgroundColor = 'lightblue';
		
		$('td>input[name="branchradio"]').each(function(i){
			if(!$(this).is(':checked')){
			this.parentNode.parentNode.style.backgroundColor = '#FFF';
			}
		});
	}
	
	</script>
	
	<script>
	//모달창에서 할당버튼 클릭시 지점할당
	function directAssignment(){
		if(!$('input[name=branchradio]:checked').val()){
			alert('주문을 할당할 지점을 선택해주세요.');
			return;
		}
	 $.ajax({
			url: "<%=request.getContextPath()%>/admin/laundryorder/assignmentOrder",
			type:'POST',
			data: JSON.stringify ({
				"branchCode": $('input[name=branchradio]:checked').val(),
				"listSelectOrderNo": assignSelectOrderNoArr,
			}),
			contentType:'application/json',
			dataType: "json",
			success:function(data){
				alert(data.countOrder+'개의 주문이 '+data.branchName+'에 할당되었습니다.');
				$('#modal-lg').modal('hide');
				window.location.reload();
			},
			error:function(error){
				alert("현재 세탁주문 지점할당이 불가합니다. \n관리자에게 연락바랍니다.");
			}
		});
	
	}
	</script>

</body>



