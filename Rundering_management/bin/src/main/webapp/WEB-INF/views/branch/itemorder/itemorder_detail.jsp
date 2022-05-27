<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<body>

<c:if test="${from eq 'modify' }">
	<script>
	window.opener.location.reload();
	</script>
</c:if>

<div class="col-12">
		<h3 class="m-3">발주 상세 정보</h3>
		<div class="p-3 m-0 card-primary card-outline">
			<div class="row">
				<div class="col-12">
					<h4>
						주문번호: ${itemOrder.ordercode }
							<span class="badge bg-secondary float-right">${comCodeMap[itemOrder.itemOrderStatus] }</span>
						<span class="float-right">발주 일자: 
						<fmt:formatDate value="${itemOrder.registDate }" pattern="yyyy-MM-dd"/>
						<c:if test="${!empty itemOrder.receiptDate}">
							|수령 일자:
							<fmt:formatDate value="${itemOrder.receiptDate }" pattern="yyyy-MM-dd"/>
						</c:if>
						</span>
						
					</h4>
				</div> 
			</div>
			
			<div class="row">
				<div class="col-12 table-responsive p-0" style="height: 500px;overflow: auto;">
					<table class="table table-striped m-0 card-primary card-outline"> 
						<thead>
							<tr>
								<th>세탁 품목</th>
								<th>개수</th>
								<th>가격</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach items="${itemOrderDetailList }" var="itemOrderDetail">
							<tr>
								<td>${itemOrderDetail.articlesCode} </td>
								<td class="count">${itemOrderDetail.orderCount }</td>
								<td class="price">${itemOrderDetail.price }</td>
							</tr>
						</c:forEach>
							<tr>
								<td>합계</td>
								<td>총 개수:<p id="totalCount"></p></td>
								<td>총 가격:<p id="totalPrice"></p></td>
							</tr>
						</tbody>
						
					</table>
					
				</div>
			</div>
		</div>
		<div class="float-right mr-3">
			<c:if test="${comCodeMap[itemOrder.itemOrderStatus] eq '승인대기' }">
			<button type="button" class="btn btn-danger btn-m" onclick="ItemOrderRemove('${itemOrder.ordercode}')">취소</button>
			</c:if> 
			<c:if test="${comCodeMap[itemOrder.itemOrderStatus] eq '미수령' }">
				<button type="button" class="btn btn-primary btn-m" onclick="ItemOrderUpdate('${itemOrder.ordercode}')">수령</button>
			</c:if>
			<button type="button" class="btn btn-danger btn-m" onclick="window.close();">닫기</button>
		</div>
	</div>
	<div id="formTag"></div>
	

	
<script>
window.onload=function(){
	let count = document.querySelectorAll(".count");
	let price = document.querySelectorAll(".price");
	let countSum=0;
	let priceSum=0;
	for(let i = 0 ; i <count.length;i++){
	   countSum += parseInt(count[i].innerText);
	   priceSum += parseInt(price[i].innerText)
	}
	let totalCount = document.querySelector("#totalCount");
	let totalPrice = document.querySelector("#totalPrice");
	totalCount.innerText=countSum;
	totalPrice.innerText=priceSum;
}
</script>
<script>
function ItemOrderRemove(ordercode){
	let tag= document.querySelector("#formTag") 	
	let form = document.createElement("form");
    form.action="remove"
    form.method="post"
    let input = document.createElement("input")
    input.name="ordercode"
    input.setAttribute("value",ordercode)
    form.append(input);
    console.log(form)
     tag.append(form);
    form.submit();
} 
function ItemOrderUpdate(ordercode){
	let tag= document.querySelector("#formTag")
	let form = document.createElement("form");
    form.action="modify"
    form.method="post"
    let input = document.createElement("input")
    input.name="ordercode"
    input.setAttribute("value",ordercode)
    form.append(input);
    tag.append(form);
    form.submit();

	
}
</script>
	
</body>
