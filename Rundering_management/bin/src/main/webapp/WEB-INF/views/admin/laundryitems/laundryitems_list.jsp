<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="pageMaker" value="${dataMap.pageMaker }" />
<c:set var="cri" value="${dataMap.pageMaker.cri }" />
<c:set var="laundryItemsList" value="${dataMap.laundryItemsList }" />
<c:set var="codeMap" value="${dataMap.codeMap }" />

<style>
	th {
		text-align:center;
	}
</style>

<title>세탁 품목 관리</title>

<body>
 	<section class="content-header">
	   <div class="container-fluid">
	      <div class="row mb-2">
	         <div class="col-sm-6">
	            <h1>세탁품목</h1>
	         </div>
	      </div>
	   </div>
	</section>
	<div class="card ml-3 mr-3">
		<div class="card-header">
			<h3 class="card-title">세탁품목 리스트</h3>
			<div class="card-tools">
				<div class="input-group input-group-sm" style="width: 400px;">
					
					<!-- classification -->
					<select class="form-control col-md-3" name="laundryItemsCode"
						id="laundryItemsCode" onchange="list_go(1);">
						<option value="01" ${cri.laundryItemsCode eq '01' ? 'selected' : '' }>전체품목</option>
						<option value="BE" ${cri.laundryItemsCode eq 'BE' ? 'selected' : '' }>침구</option>
						<option value="CL" ${cri.laundryItemsCode eq 'CL' ? 'selected' : '' }>의류</option>
						<option value="SH" ${cri.laundryItemsCode eq 'SH' ? 'selected' : '' }>신발</option>
					</select>
				
					<!-- search bar -->
					<select class="form-control col-md-4" name="searchType" id="searchType">
							<option value="tc" ${cri.searchType eq 'tc' ? 'selected':'' }>전체</option>
							<option value="t" ${cri.searchType eq 't' ? 'selected':'' }>품목번호</option>
							<option value="c" ${cri.searchType eq 'c' ? 'selected':'' }>품목명</option>
						</select> <input class="form-control" type="text" name="keyword"
							placeholder="검색어를 입력하세요." value="" /> <span
							class="input-group-append">
							<button class="btn btn-primary" type="button"
								onclick="list_go(1);" data-card-widget="search" style="background-color: #82BBD8; border: 1px solid #82BBD8">
								<i class="fa fa-fw fa-search"></i>
							</button>
						</span>
				</div>
			</div>
		</div>
		<div class="card-body table-responsive p-0 mt-0">
		<form role="form" class="form-horizontal" action="regist"
				method="post" name="registForm">
			<table
				class="table table-hover text-nowrap m-0">
				<thead>
					<tr>
					    <th style="width:10%;">분류</th>
						<th style="width:10%;">품목번호</th>
						<th style="width:20%;">품목명</th>
						<th style="width:10%;">세탁가격</th>
						<th style="width:5%;">수정</th>
						<th style="width:5%;">삭제</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${empty laundryItemsList }">
							<tr>
								<td colspan="5"><strong>해당 내용이 없습니다.</strong></td>
							</tr>
						</c:if>
						<c:forEach items="${laundryItemsList }" var="laundryItems">
							<tr style='font-size: 0.85em;'>
								<td>${codeMap[laundryItems.laundryCategory] }</td>
								<td id="laundryItemsCode"
									style="text-align: left; max-width: 100px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
									${laundryItems.laundryItemsCode }</td>
								<td>${laundryItems.itemsName}</td>
								<td>${laundryItems.price }</td>
								<td><input type="button" class="btn btn-warning btn-sm"
								onclick="window.open('<%=request.getContextPath()%>/admin/laundryitems/modifyForm?laundryItemsCode=${laundryItems.laundryItemsCode }','세탁품목수정', 'width=600, height=600')" value="수정"></td>
								<td><input type="button" class="btn btn-danger btn-sm" 
								onclick="remove_go('remove','${laundryItems.laundryItemsCode}');" value="삭제"></td>
							</tr>
						</c:forEach>
				</tbody>
			</table>
		</form>
		<div class="card-footer" >
			<div class="float-right mb-3 mr-2">
					<button type="button" class="btn btn-primary"
					onclick="window.open('<%=request.getContextPath()%>/admin/laundryitems/regist','세탁품목등록', 'width=600, height=600')"
					 style="background-color: #82BBD8; border: 1px solid #82BBD8">
					물품등록</button>
			</div>
			<%@ include file="/WEB-INF/views/admin/ordergoods/pagination.jsp" %>
		</div>
	</div>
</div>
<script>

function remove_go(url,laundryItemsCode){
	   if(confirm("삭제하시겠습니까?")){
	      alert("삭제되었습니다.")   
	      location.href=url+"?laundryItemsCode="+laundryItemsCode
	   }
		window.opener.location.reload();
	}



</script>

</body>
</html>