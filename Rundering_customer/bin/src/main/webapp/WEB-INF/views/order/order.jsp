<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="laundryItemsList" value="${dataMap.laundryItemsList }" />

<c:set var="now" value="<%=new java.util.Date()%>" />
<c:set var="nowDate">
	<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" />
</c:set>
<!-- 이주 후 -->
<c:set var="twoWeeksAfter"
	value="<%=new java.util.Date(new java.util.Date().getTime() + 60 * 60 * 24 * 1000 * 14)%>" />
<c:set var="twoWeeksAfterDate">
	<fmt:formatDate value="${twoWeeksAfter}" pattern="yyyy-MM-dd" />
</c:set>


<head>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/customer/src/main/webapp/resources/bootstrap/plugins/sweetalert2/sweetalert2.min.css" />
<style>
.laundryItemsListScroll thead, .laundryItemsListScroll tbody {
	display: block;
}

.laundryItemsListScroll th, .laundryItemsListScroll td {
	width: 100%;
}

.laundryItemsListScroll tbody {
	max-height: 333px; /* Just for the demo          */
	overflow-y: auto; /* Trigger vertical scroll    */
	overflow-x: hidden; /* Hide the horizontal scroll */
}

.selectedItemsTable {
	display: block;
	width: 758px;
}

.selectedItemsTable tbody {
	display: block;
	height: 333px;
	overflow: auto;
}

.selectedItemsTable th:nth-of-type(1), .selectedItemsTable td:nth-of-type(1)
	{
	width: 257px;
}

.selectedItemsTable th:nth-of-type(2), .selectedItemsTable td:nth-of-type(2)
	{
	width: 117px;
}

.selectedItemsTable th:nth-of-type(3), .selectedItemsTable td:nth-of-type(3)
	{
	width: 147px;
}

.selectedItemsTable th:nth-of-type(4), .selectedItemsTable td:nth-of-type(4)
	{
	width: 117px;
}

.selectedItemsTable th:last-child {
	width: 130px;
}

.selectedItemsTable td:last-child {
	width: calc(130px - 19px);
}

/* 스크롤바 설정*/
.laundryItemsListScroll tbody::-webkit-scrollbar, .selectedItemsTable tbody::-webkit-scrollbar
	{
	/* 스크롤바 막대 너비 설정 */
	width: 6px;
}
/* 스크롤바 막대 설정*/
.laundryItemsListScroll tbody::-webkit-scrollbar-thumb,
	.selectedItemsTable tbody::-webkit-scrollbar-thumb {
	/* 스크롤바 막대 높이 설정 */
	height: 17%;
	background-color: #d3d3d3;
	/* 스크롤바 둥글게 설정 */
	border-radius: 10px;
}
/* 스크롤바 뒷 배경 설정*/
.laundryItemsListScroll tbody::-webkit-scrollbar-track,
	.selectedItemsTable tbody::-webkit-scrollbar-track {
	background-color: rgba(0, 0, 0, 0);
}

.selectedItems td {
	vertical-align: middle;
}
</style>
</head>


<body>

	<form role="form" class="form-horizontal" action="<%=request.getContextPath()%>/order/comfirm" method="post">
		<div style="width: 60%; display: flex; flex-direction: column; margin-left: 20%;">
			<section class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-sm-6">
							<h1>세탁주문 상세</h1>
						</div>
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item active">세탁주문 상세</li>
								<li class="breadcrumb-item"><a href="#">세탁주문</a></li>
							</ol>
						</div>
					</div>
				</div>
			</section>

			<div class="card mb-0" style="box-shadow: none;">
				<div class="card-header">
					<h3 class="" style="text-align: center; font-size: 1.3rem; font-weight: 400;">세탁정보 입력</h3>
				</div>

				<div class="hiddenInput">
					<input type="hidden" name="contactNumber" value="${command.contactNumber}" >
					<input type="hidden" name="addressNo" value="${command.addressNo}" >
					<input type="hidden" name="zip" value="${command.zip}" >
					<input type="hidden" name="add1" value="${command.add1}" >
					<input type="hidden" name="add2" value="${command.add2}" >
					<input type="hidden" name="setDefaultAddr" value="${command.setDefaultAddr}" >
				</div>

				<div class="card-body col-6" style="margin: auto; margin-top: 25px;">

					<div class="input-group mb-3">
						<label>수거날짜 입력</label>
					</div>



					<div class="">
						<input type="date" class="form-control" name="pickupRequestDate" min="${nowDate }" max="${twoWeeksAfterDate}" required> <span class="sp"></span>
					</div>

					<div class="input-group mb-3" style="margin-top: 20px;">
						<label>요청사항</label>
					</div>
					<div class="input-group mb-3 ">
						<input type="text" class="form-control" name="requestDetails" placeholder="ex) 공동현관 비밀번호 #12345*">
					</div>

				</div>
			</div>

		</div>

		<div style="width: 70%; margin-left: 15%">
			<p class="mb-3" style="text-align: center;">세탁을 맡기실 품목을 선택해 주세요.</p>
			<div class="row">
				<div class="col-4">
					<div class="card">
						<div class="card-header justify-content-center"
							style="display: flex;">
							<h3 class="card-title">의류</h3>
						</div>
						<div class="card-body table-responsive p-0">
							<table
								class="table table-hover text-nowrap laundryItemsListScroll">
								<thead>
									<tr style="display: flex; text-align: center;">
										<th>품목명</th>
										<th>가격</th>
									</tr>
								</thead>
								<tbody class="">
									<c:if test="${!empty laundryItemsList }">
										<c:forEach items="${laundryItemsList }" var="laundryItems">
											<c:if test="${laundryItems.laundryCategory eq 'CL' }">
												<tr style="cursor: pointer;" onclick="displayAddItems('${laundryItems.itemsName}','<fmt:formatNumber type="number" maxFractionDigits="3" value="${laundryItems.price}" />','${laundryItems.laundryItemsCode}')">
													<td>${laundryItems.itemsName}</td>
													<td style="text-align: end;"><fmt:formatNumber type="number" maxFractionDigits="3" value="${laundryItems.price}" />원</td>
												</tr>
											</c:if>
										</c:forEach>
									</c:if>
								</tbody>
							</table>
						</div>
					</div>
				</div>

				<div class="col-4 ">
					<div class="card">
						<div class="card-header justify-content-center" style="display: flex;">
							<h3 class="card-title">침구</h3>
						</div>
						<div class="card-body table-responsive p-0">
							<table class="table table-hover text-nowrap laundryItemsListScroll">
								<thead>
									<tr style="display: flex; text-align: center;">
										<th>품목명</th>
										<th>가격</th>
									</tr>
								</thead>
								<tbody>
									<c:if test="${!empty laundryItemsList }">
										<c:forEach items="${laundryItemsList }" var="laundryItems">
											<c:if test="${laundryItems.laundryCategory eq 'BE' }">
												<tr style="cursor: pointer;" onclick="displayAddItems('${laundryItems.itemsName}','<fmt:formatNumber type="number" maxFractionDigits="3" value="${laundryItems.price}" />','${laundryItems.laundryItemsCode}')">
													<td>${laundryItems.itemsName}</td>
													<td style="text-align: end;"><fmt:formatNumber type="number" maxFractionDigits="3" value="${laundryItems.price}" />원</td>
												</tr>
											</c:if>
										</c:forEach>
									</c:if>
								</tbody>
							</table>
						</div>
					</div>
				</div>

				<div class="col-4 ">
					<div class="card">
						<div class="card-header justify-content-center"	style="display: flex;">
							<h3 class="card-title">신발</h3>
						</div>
						<div class="card-body table-responsive p-0">
							<table class="table table-hover text-nowrap laundryItemsListScroll">
								<thead>
									<tr style="display: flex; text-align: center;">
										<th>품목명</th>
										<th>가격</th>
									</tr>
								</thead>
								<tbody>
									<c:if test="${!empty laundryItemsList }">
										<c:forEach items="${laundryItemsList }" var="laundryItems">
											<c:if test="${laundryItems.laundryCategory eq 'SH' }">
												<tr style="cursor: pointer;" onclick="displayAddItems('${laundryItems.itemsName}','<fmt:formatNumber type="number" maxFractionDigits="3" value="${laundryItems.price}" />','${laundryItems.laundryItemsCode}')">
													<td>${laundryItems.itemsName}</td>
													<td style="text-align: end;"><fmt:formatNumber type="number" maxFractionDigits="3" value="${laundryItems.price}" />원</td>
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

		<div style="width: 60%; display: flex; flex-direction: column; margin-left: 20%;">
			<div class="card" style="box-shadow: none;">
				<div class="card-header">
					<h3 class="" style="text-align: center; font-size: 1.3rem; font-weight: 400;">선택한 세탁품목</h3>
				</div>

				<div class="card-body p-0">
					<table class="table selectedItemsTable">
						<thead>
							<tr>
								<th colspan="5" style="text-align: right;">총 합계 : <span>0</span>원</th>
							</tr>
							<tr style="text-align: center;">
								<th>품목명</th>
								<th>가격</th>
								<th>수량</th>
								<th>합계</th>
								<th>삭제</th>
							</tr>
						</thead>
						<tbody class="selectedItems">
							<tr class="notselected">
								<td style="text-align: center; width: 100%; margin-top: 135px; margin-left: 192px; display: block; border: none;">선택된 품목이 없습니다.</td>
							</tr>
						</tbody>
					</table>
				</div>

			</div>
			<button type="submit" style="margin-top: 20px; margin: auto;" class="btn btn-primary btn-block col-6 mt-4 mb-4">주문하기</button>
		</div>


	</form>

	<!-- 알림 sweetalert2 -->
	<script src="<%=request.getContextPath()%>/resources/bootstrap/plugins/sweetalert2/sweetalert2.all.min.js"></script>


	<script>
	//sweetalert2 설정
	const Toast = Swal.mixin({
	      toast: true,
	      position: 'center',
	      showConfirmButton: false,
	      timer: 1200,
	      timerProgressBar: false,
	      didOpen: (toast) => {
	        toast.addEventListener('mouseenter', Swal.stopTimer)
	        toast.addEventListener('mouseleave', Swal.resumeTimer)
	      }
	    })
		
	// 선택한 품목 리스트에 추가
	function displayAddItems(itemsName,price,laundryItemsCode) {
		const trs = document.querySelectorAll(".selectedItems tr");
		if(trs.item(0).className == 'notselected') {

			trs.item(0).remove();
			
		}else{
			for (let tr of trs) {
				const classes = tr.classList;
				if (classes.contains(laundryItemsCode)) {
					//품목 개수 추가해야함
					console.log(laundryItemsCode);
					return;
				} 
			}
		}
		 const container = document.querySelector(".selectedItems");
		 container.append(createTrNode(itemsName,price,laundryItemsCode));
		  
		 const hiddenInput = document.querySelector(".hiddenInput");
		 hiddenInput.append(createHiddenInputNode(laundryItemsCode,1));
		 
		 totalPriceUpdate();
	}
	
	// 선택한 품목 삭제
	function displayRemoveItems(laundryItemsCode){
		const items = document.querySelectorAll('.'+laundryItemsCode.className.substring(0, 5));
		for (let item of items) {
			item.remove();
		}
		const container = document.querySelector(".selectedItems");
		const trs = document.querySelectorAll(".selectedItems tr");
		if(!trs.length) {
			container.innerHTML = `<tr class="notselected">
									  <td style="text-align: center;width: 100%;margin-top: 135px;margin-left: 192px;display: block;border: none;">선택된 품목이 없습니다.</td>
								   </tr>`;
		}
		totalPriceUpdate();

	}
	
	// 품목선택시 화면에 보여줄 tr태그 Node생성
	function createTrNode(itemsName,price,laundryItemsCode) {
    	let tr = document.createElement('tr');
    	let td1 = document.createElement('td');
    	let td2 = document.createElement('td');
    	let td3 = document.createElement('td');
    	let td4 = document.createElement('td');
    	let td5 = document.createElement('td');
    	
    	tr.setAttribute('class', itemsName+' '+laundryItemsCode);
    	tr.style.textAlign = 'center';
    	td1.innerHTML = itemsName;
    	tr.append(td1);
    	td2.style.textAlign= 'end';
    	td2.innerHTML = price+'원';
    	tr.append(td2);
    	td3.innerHTML = `<input type="text" class="${'${laundryItemsCode}'}quantity" name="quantity" value="1" style="width: 35px;text-align: end;" onchange="updateValue(this)">
				    	<div class="btn-group-vertical" style="width: 18px;">
							<button type="button" class="${'${laundryItemsCode}'} btn btn-sm btn-default p-0" style="height: 20px;" onclick="plusQuantity(this)">+</button>
							<button type="button" class="${'${laundryItemsCode}'} btn btn-sm btn-default p-0" style="height: 20px;" onclick="minusQuantity(this)">-</button>
						</div>`;
    	tr.append(td3);
    	td4.style.textAlign= 'end';
    	td4.innerHTML = price+'원';
    	tr.append(td4);
    	td5.innerHTML = `<button type="button" class="${'${laundryItemsCode}'} btn btn-outline-danger btn-sm" onclick="displayRemoveItems(this)">삭제</button>`;
    	tr.append(td5);
    	
    	return tr;
		}
	
	// 품목 코드,수량 넘겨줄 input태그 Node생성
	function createHiddenInputNode(laundryItemsCode,quantity) {
		let input = document.createElement('input');
		input.setAttribute('type', 'text');
		input.setAttribute('class', laundryItemsCode);
		input.setAttribute('name', 'laundryItemsCode');
		input.setAttribute('value', laundryItemsCode+','+quantity);
		input.style.display = 'none';
		return input;
		}
	
	// 수량 input태그에 직접 입력시 
	function updateValue(item){
		if(isNaN(item.value)){
			item.value = 1;
		}
		if(!item.value || (item.value < 1)){
			item.value = 1;
		}
		if(item.value > 99){
			 Toast.fire({
			      icon: 'warning',
			      title: '세탁주문 최대 수량은 99개입니다.'
			    });
			 item.value = 1;
		}
		const laundryItemsCodeString = item.className.substring(0,5);
		const inputHidden = document.querySelector('.hiddenInput .'+laundryItemsCodeString);
		inputHidden.setAttribute("value",laundryItemsCodeString+','+item.value);
		
		updatePrice(laundryItemsCodeString);
	}
	
	// 수량 더하기
	function plusQuantity(laundryItemsCode) {
		const laundryItemsCodeString = laundryItemsCode.className.substring(0, 5);
		const inputQuantity = document.querySelector('.'+laundryItemsCodeString+'quantity');
		inputQuantity.value = Number(inputQuantity.value) + 1;
		
		const inputHidden = document.querySelector('.hiddenInput .'+laundryItemsCodeString);
		inputHidden.setAttribute("value",laundryItemsCodeString+','+inputQuantity.value);
		
		updatePrice(laundryItemsCodeString);
	}
	// 수량 빼기
	function minusQuantity(laundryItemsCode) {
		const laundryItemsCodeString = laundryItemsCode.className.substring(0, 5);
		const inputQuantity = document.querySelector('.'+laundryItemsCodeString+'quantity');
		if(Number(inputQuantity.value) == 1) {
			return;
		}
		inputQuantity.value = Number(inputQuantity.value) - 1;
		
		const inputHidden = document.querySelector('.hiddenInput .'+laundryItemsCodeString);
		inputHidden.setAttribute("value",laundryItemsCodeString+','+inputQuantity.value);
		
		updatePrice(laundryItemsCodeString);
	}
	// 수량 변경 시 금액 변경
	function updatePrice(laundryItemsCode){
		const item = document.querySelector('.selectedItems .'+laundryItemsCode);
		let priceStr = item.childNodes[1].innerText;
		let price = Number(priceStr.replace(/[^0-9]/g,''));
		const quantity = Number(item.childNodes[2].childNodes[0].value);
		price = price * quantity;
		let changePrice = price.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ',');
		
		item.childNodes[3].innerText = changePrice+'원';
		totalPriceUpdate();
	}
	
	// 총 합계 업데이트
	function totalPriceUpdate(){
		const totalPriceSpan = document.querySelector(".selectedItemsTable span");
		const trs = document.querySelectorAll(".selectedItems tr");
		let totalPriceSum = 0;
		if(trs.item(0).className == 'notselected') {
			totalPriceSpan.innerText = 0;
			return;
		}else{
			for (let tr of trs) {
				const price = Number(tr.childNodes[3].innerText.replace(/[^0-9]/g,''));
				totalPriceSum += price;
			}
		}
		totalPriceSpan.innerText = totalPriceSum.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ',');
	}
	</script>

</body>