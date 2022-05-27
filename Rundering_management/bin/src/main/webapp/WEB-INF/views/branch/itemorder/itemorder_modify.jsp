<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<body>
    <div id="body">
        <div class="row">
            <div class="col-12">
                <div class="card card-primary card-outline">
                    <div class="card-header">
                        <h3 class="card-title">발주수정</h3>
                        <div class="card-tools">

                            <div class="input-group input-group-sm">
                            	<div class="input-group-sm textWidth">
                              		<input class="form-control" type="text" name="" id="" disabled="false" value="총가격">
                                </div>
                               	<span class="input-group-append">
                               	 	<button type="button" class="btn btn-primary ">저장</button>
                               	</span>    
                                  	
                          
                                
                            </div>
                        </div>
                    </div>

                    <div class="card table-responsive p-0">
                        <table class="table table-hover text-nowrap">
                            <thead>
                                <tr>
                                    <th class="width20">물품명</th>
                                    <th class="width15">사진</th>
                                    <th class="width15">수량</th>
                                    <th class="width15">금액</th>
                                    <th class="width15">총금액</th>
                                    <th class="width10">확인</th>
                                </tr>
                            </thead>
                            
                            <tbody>
                                <tr>
                                    <td>세제</td>
                                    <td>사진</td>
                                    <td><input type="text" name="content" class="form-control"style="width:80px" ></td></td>
                                    <td><span class="tag tag-success">3000</span></td>
                                    <td>30000</td>
                                    <td><input type="checkbox"></td>
                                </tr>
                                <tr>
                                    <td>옷걸이</td>
                                    <td>사진</td>
                                     <td><input type="text" name="content" class="form-control"style="width:80px" ></td></td>
                                    <td><span class="tag tag-success">1000</span></td>
                                    <td>10000</td>
                                    <td><input type="checkbox"></td>
                                </tr>
                               
                            </tbody>
                        </table>
                    </div>

                </div>

            </div>
        </div>
    </div>

</body>