<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<body>
        <div class="row ml-2 ml-2">
            <div class="col-12">
                <div class="card card-primary card-outline">
                    <div class="card-header">
                        <h3 class="card-title">세탁물품 목록</h3>
                            <div class="card-tools">

                                <div class="input-group input-group-sm ">
	                                	
                                	<div class="input-group-sm selectWidth">
	                                    <select class=" form-control" name="perPageNum" id="perPageNum"
	                                        onchange="list_go();">
	                                        <option value="10">정렬개수</option>
	                                        <option value="20">20개씩</option>
	                                        <option value="50">50개씩</option>
	                                        <option value="100">100개씩</option>
	
	                                    </select>
                                    </div>
                                    <div class="input-group-sm selectWidth">
	                                    <select class="form-control" name="searchType" id="searchType">
	                                        <option value="tcw">와이셔츠</option>
	                                        <option value="t">청바지</option>
	
	
	                                    </select>
									</div>
	


                                    <span class="input-group-append">
                                        <button class="btn btn-primary" type="button" onclick="list_go(1);"
                                            data-card-widget="search">
                                            <i class="fa fa-fw fa-search"></i>
                                        </button>
                                    </span>
                                </div>
                            </div>
                    </div>

                    <div class="card-body">
                        <table class="table table-hover text-nowrap">
                            <thead>
                                <tr>
                                    <th class="width15">세탁물품번호</th>
                                    <th class="width35">사진</th>
                                    <th class="width25">이름</th>
                                    <th class="width25">가격</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>1</td>
                                    <td>사진 예정</td>
                                    <td>와이셔츠</td>
                                    <td>2000원</td>

                                </tr>
                                 <tr>
                                    <td>1</td>
                                    <td>사진 예정</td>
                                    <td>와이셔츠</td>
                                    <td>2000원</td>

                                </tr>
                                 <tr>
                                    <td>1</td>
                                    <td>사진 예정</td>
                                    <td>와이셔츠</td>
                                    <td>2000원</td>

                                </tr>
                                 <tr>
                                    <td>1</td>
                                    <td>사진 예정</td>
                                    <td>와이셔츠</td>
                                    <td>2000원</td>

                                </tr>
                                 <tr>
                                    <td>1</td>
                                    <td>사진 예정</td>
                                    <td>와이셔츠</td>
                                    <td>2000원</td>
                                </tr>

                            </tbody>
                        </table>
                    </div>

                </div>

            </div>
        </div>

</body>