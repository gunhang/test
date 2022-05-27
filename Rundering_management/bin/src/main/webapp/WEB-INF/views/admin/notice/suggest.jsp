<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>

<head>
    <style>
        .no {
            width: 10%;
        }

        .boardtitle {
            width: 35%;
        }

        .writer {
            width: 20%;
        }

        .date {
            width: 25%;
        }

        .clicks {
            width: 10%;

        }
        #body{
            padding-left: 1%;
            padding-right: 1%;
        }
    </style>
</head>
<body>
    <div id="body">
        <div class="row">
            <div class="col-12">
                <div class="card card-primary card-outline">
                    <div class="card-header">
                        <h3 class="card-title">건의사항</h3>
                        <div class="card-tools">
                            <div class="input-group input-group-sm" style="width: 400px;">
                                <select class="form-control col-md-3" name="perPageNum" id="perPageNum"
					  		onchange="list_go();">
					  		<option value="10" >정렬개수</option>
					  		<option value="20">20개씩</option>
					  		<option value="50">50개씩</option>
					  		<option value="100">100개씩</option>
					  		
					  	</select>						
						<select class="form-control col-md-4" name="searchType" id="searchType">
							<option value="tcw" >전 체</option>
							<option value="t">제 목</option>
							<option value="c" >내 용</option>
							<option value="tc" >제목+내용</option>						
						</select>	
                               
                        <input  class="form-control" type="text" name="keyword" placeholder="검색어를 입력하세요." value=""/>
						<span class="input-group-append">
							<button class="btn btn-primary" type="button" onclick="list_go(1);" 
							data-card-widget="search">
								<i class="fa fa-fw fa-search"></i>
							</button>
						</span>
                            </div>
                        </div>
                    </div>

                    <div class="card table-responsive p-0">
                        <table class="table table-hover text-nowrap">
                            <thead>
                                <tr>
                                    <th class="no">번호</th>
                                    <th class="boardtitle">제목</th>
                                    <th class="date">작성일</th>
                                    <th class="clicks">조회수</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr onclick="location.href='<%=request.getContextPath()%>/admin/notice/suggestdetail.do'">
                                    <td>183</td>
                                    <td>John Doe</td>
                                    <td><span class="tag tag-success">Approved</span></td>
                                    <td>1</td>
                                </tr>
                                <tr onclick="location.href='<%=request.getContextPath()%>/admin/notice/suggestdetail.do'">
                                    <td>183</td>
                                    <td>John Doe</td>
                                    <td><span class="tag tag-success">Approved</span></td>
                                    <td>2</td>
                                </tr>
                                <tr onclick="location.href='<%=request.getContextPath()%>/admin/notice/suggestdetail.do'">
                                    <td>183</td>
                                    <td>John Doe</td>
                                    <td><span class="tag tag-success">Approved</span></td>
                                    <td>1</td>
                                </tr>
                                <tr onclick="location.href='<%=request.getContextPath()%>/admin/notice/suggestdetail.do'">
                                    <td>183</td>
                                    <td>John Doe</td>
                                    <td><span class="tag tag-success">Approved</span></td>
                                    <td>1</td>
                                </tr>
                                <tr onclick="location.href='<%=request.getContextPath()%>/admin/notice/suggestdetail.do'">
                                    <td>183</td>
                                    <td>John Doe</td>
                                    <td><span class="tag tag-success">Approved</span></td>
                                    <td>1</td>
                                </tr>
                                <tr onclick="location.href='<%=request.getContextPath()%>/admin/notice/suggestdetail.do'">
                                    <td>183</td>
                                    <td>John Doe</td>
                                    <td><span class="tag tag-success">Approved</span></td>
                                    <td>1</td>
                                </tr>
                                <tr onclick="location.href='<%=request.getContextPath()%>/admin/notice/suggestdetail.do'">
                                    <td>183</td>
                                    <td>John Doe</td>
                                    <td><span class="tag tag-success">Approved</span></td>
                                    <td>1</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>

                </div>

            </div>
        </div>
    </div>

</body>

