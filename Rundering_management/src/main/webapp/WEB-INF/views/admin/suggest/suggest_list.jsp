<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="pageMaker" value="${dataMap.pageMaker }" />
<c:set var="cri" value="${dataMap.pageMaker.cri }" />
<c:set var="suggestList" value="${dataMap.suggestList }" />

<head>

<style>
th, .td {
	text-align: center;
}
</style>
</head>

<body>
	<section class="content-header">
	   <div class="container-fluid">
	      <div class="row mb-2">
	         <div class="col-sm-6">
	            <h1>건의사항</h1>
	         </div>
	      </div>
	   </div>
	</section>
	<div id="body">
		<div class="row ml-3 mr-3">
			<div class="col-12">
				<div class="card">
					<div class="card-header">
						<h2 style="height: 20px;" class="card-title">
		                  <b>건의사항 리스트</b>
		               	</h2>
						<div class="card-tools">
							<div class="input-group input-group-sm" style="width: 300px;">
								<select class="form-control col-md-4" name="searchType"
									id="searchType">
									<option value="tcwb"
										${cri.searchType eq 'tcwb' ? 'selected':'' }>전체</option>
									<option value="t" ${cri.searchType eq 't' ? 'selected':'' }>제목</option>
									<option value="c" ${cri.searchType eq 'c' ? 'selected':'' }>내용</option>
									<option value="w" ${cri.searchType eq 'w' ? 'selected':'' }>작성자</option>
									<option value="b" ${cri.searchType eq 'b' ? 'selected':'' }>지점</option>
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
					<div class="card-body p-0">
						<table class="table table-hover text-nowrap">
							<thead>
								<tr>
									<th width="5%">번호</th>
									<th width="27%">제목</th>
									<th width="10%">작성자</th>
									<th width="10%">지점</th>
									<th width="10%">작성날짜</th>
									<th width="5%">조회수</th>
									<th width="8%">확인여부</th>
								</tr>
							</thead>
							<c:if test="${empty suggestList }">
								<tr>
									<td colspan="5"><strong>해당 내용이 없습니다.</strong></td>
								</tr>
							</c:if>
							<c:forEach items="${suggestList }" var="suggest">
								<tr style='cursor: pointer; font-size:0.9em;'
									onclick="OpenWindow('detail?from=list&sno=${suggest.sno }','상세보기',900,700);">
									<td class="td">${suggest.sno }</td>
									<td>${suggest.title }</td>
									<td class="td">${suggest.writer }</td>
									<td class="td">${suggest.branchName }</td>
									<td class="td"><fmt:formatDate
											value="${suggest.registDate }" pattern="yyyy-MM-dd" /></td>
									<td class="td"><span class="tag tag-success">${suggest.viewcnt }</span></td>
									<td class="td">${suggest.checkyn }</td>
								</tr>
							</c:forEach>
						</table>
					</div>
					<div class="card-footer" style="font-size: 0.9em">
						<%@ include file="/WEB-INF/views/common/pagination.jsp"%>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>