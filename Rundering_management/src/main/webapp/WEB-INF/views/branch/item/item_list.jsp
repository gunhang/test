<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<c:set var="pageMaker" value="${dataMap.pageMaker }" />
<c:set var="cri" value="${dataMap.pageMaker.cri }" />
<c:set var="itemList" value="${dataMap.itemList }"/>
<c:set var="clcodeList" value="${dataMap.CLCODEList }" />
<head>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.2.1/Chart.bundle.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
</head>

<body>
<section class="content-header">
	<div class="container-fluid">
		<div class="row mb-2">
			<div class="col-sm-6">
				<h1>입출고 조회</h1>
			</div>
		</div>
	</div>
</section>

    <div class="card-secondary  ml-3 mr-3 row">
    	<div class="col-6" style="padding-left: 0px;" id="appenRegist">
        
          </div>
    
    	<div class="col-6" style="padding-right: 0px;" id="appendOut">
        
        
        </div>
    </div>
    	<div class=" ml-3 mr-3 ">
       		 <div class="card  p-0" style="padding-bottom: 10px;margin-bottom: 10px;">
                <div class="card-header">
                   <h2 style="height: 20px;" class="card-title">
						<b>발주 리스트</b>
					</h2>
                    <div class="card-tools">
                        <div class="input-group input-group-sm" >
                            <select class="form-control" name="laundryItemsCode"
                                id="laundryItemsCode" onchange="list_go(1);">
                                <option value="asd">asdaadas</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="card-body table-responsive p-0 mt-0" style="height: 245px;overflow: auto;">
                   <table class="table table-hover text-nowrap">
							<thead>
								<tr style="text-align: center;">
									<th >발주번호</th>
									<th >총 가격</th>
									<th >발주신청일</th>
									<th >상태</th>
								</tr>
							</thead>
							
							
							
							<tbody>
							<tr style="cursor:pointer;" onclick="OpenWindow('detail.do?ordercode=221','상세보기',800,700);">
							<td style="text-align: center;">221</td>
							<td style="text-align: right;">30036원</td>
							<td style="text-align: center;">2022-05-03</td>			
							<td style="text-align: center;">
								
						</td></tr>
					</tbody>
					</table>
                </div>
              <div class="card-footer" >
				<%@ include file="/WEB-INF/views/common/pagination.jsp"%>
			</div>
		</div>
      </div>
    <script	src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.7.7/handlebars.min.js"></script>
    <%@ include file="./regist_list.jsp" %>
    <%@ include file="./out_list.jsp" %>
<script>
window.onload=function(){
	registList("<%=request.getContextPath()%>/branch/item/insertList?page="+page);
	out_List("<%=request.getContextPath()%>/branch/item/outlist?page="+out_page);
	
}   

function minusQuantity(){
	let input =event.target.parentNode.parentNode.querySelectorAll('.inputValue')[0]
	let intValue= parseInt(input.value)
	let result=intValue-1;
	if(result<0){
		result=0;
	}
	input.value=result;
}


</script>

</body>