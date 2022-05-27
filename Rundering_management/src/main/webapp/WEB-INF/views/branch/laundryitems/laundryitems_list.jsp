<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="pageMaker" value="${dataMap.pageMaker }" />
<c:set var="cri" value="${dataMap.pageMaker.cri }" />
<c:set var="laundryItemsList" value="${dataMap.laundryItemsList }" />
<c:set var="codeMap" value="${dataMap.codeMap }" />

<title>세탁 품목 관리</title>

<body>
 
	<div class="row ml-3 mr-3">
		<div class="col-12 p-0">
			<div class="card m-0">
				<div class="card-header">
					<h3 class="card-title" style="font-size: 1.75rem;">세탁 품목</h3>
					<div class="card-tools">
						<div class="input-group input-group-sm" style="width: 400px; margin-top:5px;">
						
						<!-- classification -->
							<select class="form-control col-md-3" name="laundryItemsCode"
								id="laundryItemsCode" onchange="list_go(1);">
								<option value="01" ${cri.laundryItemsCode eq '01' ? 'selected' : '' }>전체품목</option>
								<option value="BE" ${cri.laundryItemsCode eq 'BE' ? 'selected' : '' }>침구</option>
								<option value="CL" ${cri.laundryItemsCode eq 'CL' ? 'selected' : '' }>의류</option>
								<option value="SH" ${cri.laundryItemsCode eq 'SH' ? 'selected' : '' }>신발</option>
							</select>
						
							<!-- search bar -->
							<select class="form-control col-md-4" name="searchType"
									id="searchType">
									<option value="tc" ${cri.searchType eq 'tc' ? 'selected':'' }>전체</option>
									<option value="t" ${cri.searchType eq 't' ? 'selected':'' }>품목번호</option>
									<option value="c" ${cri.searchType eq 'c' ? 'selected':'' }>품목명</option>
								</select> <input class="form-control" type="text" name="keyword"
									placeholder="검색어를 입력하세요." value="" /> <span
									class="input-group-append">
									<button class="btn btn-primary" type="button"
										onclick="list_go(1);" data-card-widget="search">
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
						class="table table-hover text-nowrap card-secondary card-outline">
						<thead>
							<tr>
							    <th style="width:150px;">분류</th>
								<th style="width:180px;">품목번호</th>
								<th style="width:180px;">품목명</th>
								<th style="width:150px;">세탁가격</th>
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
									</tr>
								</c:forEach>
						</tbody>
					</table>
				</form>
					<div class="card-footer" >	
						<%@ include file="/WEB-INF/views/admin/ordergoods/pagination.jsp" %>
						
					</div>
				</div>

			</div>
		</div>
	</div>
</body>
</html>