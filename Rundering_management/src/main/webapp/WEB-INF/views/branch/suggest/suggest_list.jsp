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
th, td {
	text-align: center;
}

.no {
	width: 12%;
}

.boardtitle {
	width: 20%;
}

.writer {
	width: 12%;
}

.branchName {
	width: 13%;
}

.date {
	width: 20%;
}

.clicks {
	width: 11%;
}

.yn {
	width: 12%;
}
</style>
</head>

<body>
	<div id="body">
		<div class="row ml-2 mr-2">
			<div class="col-12">
				<div class="card card-primary card-outline">
					<div class="card-header">
						<h3 class="card-title">건의사항</h3>
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
										onclick="list_go(1);" data-card-widget="search">
										<i class="fa fa-fw fa-search"></i>
									</button>
								</span>
							</div>
						</div>
						<div class="card-body p-0">
							<table class="table table-hover text-nowrap">
								<thead>
									<tr>
										<th class="no">번호</th>
										<th class="boardtitle">제목</th>
										<th class="writer">작성자</th>
										<th class="branchName">지점</th>
										<th class="date">작성날짜</th>
										<th class="clicks">조회수</th>
										<th class="yn">확인여부</th>
									</tr>
								</thead>
								<c:if test="${empty suggestList }">
									<tr>
										<td colspan="5"><strong>해당 내용이 없습니다.</strong></td>
									</tr>
								</c:if>
								<c:forEach items="${suggestList }" var="suggest">
									<tr style='cursor: pointer;'
										onclick="OpenWindow('detail?from=list&sno=${suggest.sno }','상세보기',900,700);">
										<td class="no">${suggest.sno }</td>
										<td class="boardtitle" style="text-align: inherit;">${suggest.title }</td>
										<td class="writer">${suggest.writer }</td>
										<td class="branchName">${suggest.branchName }</td>
										<td class="date"><fmt:formatDate
												value="${suggest.registDate }" pattern="yyyy-MM-dd" /></td>
										<td class="clicks"><span class="tag tag-success">${suggest.viewcnt }</span></td>
										<td class="yn">${suggest.checkyn }</td>
									</tr>
								</c:forEach>
							</table>

							<div class="float-right mt-3 mr-3 mb-3">

								<button class="btn btn-primary" type="button" id="registBtn"
									onclick="location.href='<%=request.getContextPath()%>/branch/suggest/registForm'">
									작성하기</button>
							</div>
						</div>


						<div class="card-footer" >
							<%@ include file="/WEB-INF/views/common/pagination.jsp"%>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>