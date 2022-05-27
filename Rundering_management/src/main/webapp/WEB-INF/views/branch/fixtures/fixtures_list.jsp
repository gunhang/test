<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<c:set var="pageMaker" value="${dataMap.pageMaker }" />
<c:set var="cri" value="${dataMap.pageMaker.cri }" />
<c:set var="fixturesList" value="${dataMap.fixturesList }"/>
<!--이쁜 알럽트창 -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.1.9/dist/sweetalert2.min.css">

<body>
<section class="content-header">
	<div class="container-fluid">
		<div class="row mb-2">
			<div class="col-sm-6">
				<h1>비품 현황</h1>
			</div>
		</div>
	</div>
</section>

    <div class="row ml-3 mr-3">
        <div class="col-12">
        <div class="card ">
                <div class="card-header">
                   <h2 style="height: 20px;" class="card-title">비품 목록</h2>
                    <div class="card-tools">
                        <div class="input-group input-group-sm" >
                        </div>
                    </div>
                </div>
                <div class="card-body table-responsive p-0 mt-0" style="height: 540px;overflow: auto;">
                    <table
                        class="table table-hover text-nowrap card-outline">
                        <thead>
                            <tr>
                                <th class="width25" style="text-align: center;">이름</th>
                                <th class="width15" style="text-align: center;height: 24px;padding-bottom: 8px;padding-top: 0px;">
                                    <div class="input-group input-group-sm" >
                                        <select class="form-control" style="width: 60px;" name="searchType"  id="laundryItemsCode" onchange="list_go(1);">
                                           	 
                                             	<option value="">전체</option>
                                             	<option value="A001" ${cri.searchType eq 'A001' ? 'selected':'' }>세탁기</option>
                                             	<option value="A002" ${cri.searchType eq 'A002' ? 'selected':'' }>건조기</option>
                                             	<option value="A003" ${cri.searchType eq 'A003' ? 'selected':'' }>에어컨</option>
                                         </select>
                                     </div>
                                </th>
                                <th class="width10" style="text-align: center;">상태</th>
                                <th class="width15" style="text-align: center;">등록일</th>
                                <th class="width25" style="text-align: center;">비품번호</th>
                                <th class="width10">AS요청</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${fixturesList}" var="fixtures">    
                        	<tr>
                        		<td style="text-align: center" >${fixtures.fixturesName}</td>
                        		<td style="text-align: center">
									<c:if test="${fixtures.articlesCode  eq 'A001'}">
									세탁기
									</c:if>
									<c:if test="${fixtures.articlesCode  eq 'A002'}">
									건조기
									</c:if>
									<c:if test="${fixtures.articlesCode  eq 'A003'}">
									에어컨
									</c:if>
								</td>
                        		
                        		<td style="text-align: center">
                        			<c:if test="${fixtures.status eq '정상' }">
                        				<button class="btn btn-sm btn-primary"   onclick="statusChange()" >${fixtures.status } </button>
                        			</c:if>
                        			<c:if test="${fixtures.status eq '고장' }">
                        				<button class="btn btn-sm btn-warning"  onclick="statusChange()">${fixtures.status }</button>
                        			</c:if>
                        		</td>
                        		<td style="text-align: center">
                        		<fmt:formatDate value="${fixtures.registDate}" pattern="yyyy-MM-dd"/></td>
                        		
                        		<td style="text-align: center" class="fixturesCode">${fixtures.fixturesCode}</td>
                        		<td class="as">
                        		<c:if test="${fixtures.status eq '정상' }">
                        			<button class="btn btn-sm btn-primary asBtn"  disabled="disabled" onclick="OpenWindow('asform?fixturesName=${fixtures.fixturesName}&articlesCode=${fixtures.articlesCode }&fixturesCode=${fixtures.fixturesCode}','as요청',800,780);">AS요청</button>
                        		</c:if>
                        		<c:if test="${fixtures.status eq '고장' }">
                        			<button class="btn btn-sm btn-primary asBtn" onclick="OpenWindow('asform?fixturesName=${fixtures.fixturesName}&articlesCode=${fixtures.articlesCode }&fixturesCode=${fixtures.fixturesCode}','as요청',800,780);">AS요청</button>
                        		</c:if>
                        		
                        		
                        		</td>
                            </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
              <div class="card-footer" >
				<%@ include file="/WEB-INF/views/common/pagination.jsp"%>
			</div>
			</div>
            </div>
    </div>
    
    <!-- 알림 sweetalert2 -->
	<script src="<%=request.getContextPath()%>/resources/bootstrap/plugins/sweetalert2/sweetalert2.all.min.js"></script>
    
    
    <script>
    	function statusChange(){
    		let button=event.target;
    		let status=""
    		let fixturesCode=event.target.parentNode.parentNode.querySelectorAll('.fixturesCode')[0]
    		asButton=event.target.parentNode.parentNode.querySelectorAll('.asBtn')[0]
    		if(button.innerText.trim()=='정상'){
    			status="고장";
    		}
    		else if(button.innerText.trim()=='고장'){
    			status="정상";
    		}
    		
    		
    		 $.ajax({
    			url : '<%=request.getContextPath()%>/branch/fixtures/statusModify',
    			type : 'get',
    			data:{
    				status:status,
    				fixturesCode:fixturesCode.innerText.trim()
    			},
    			success : function(data) {
    				if(status =="고장"){
    					asButton.disabled=false
    					button.className="btn btn-sm btn-warning"
    					button.innerText=status
    					
    				}else if (status=="정상"){
    					asButton.disabled=true
        				button.className="btn btn-sm btn-primary"
        				button.innerText=status
    				}
    				
    			},
    			error : function(error) {
    				Swal.fire({
						icon : 'error', // 여기다가 아이콘 종류를 쓰면 됩니다.
						title : '상태변경에 실패하셧습니다'
					});
    			}
    		});
    	}
    
    </script>
    
    
    </body>
    