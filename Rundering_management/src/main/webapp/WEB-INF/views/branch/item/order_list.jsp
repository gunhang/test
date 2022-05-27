<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
  <!--이쁜 알럽트창 -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.1.9/dist/sweetalert2.min.css">
   
   
<script type="text/x-handlebars-template" id="order_list" >
	<div class="card  p-0" style="padding-bottom: 10px;margin-bottom: 10px;" id="removeOrder">
			<div class="card-header" >
                   <h2 style="height: 20px;" class="card-title">
						<b>발주 내역</b>
					</h2>
                    <div class="card-tools">
                        <div class="input-group input-group-sm" >
                            <select class="form-control" name="laundryItemsCode"
                                id="laundryItemsCode" onchange="order_list_go(1);">
								<option value="">전체</option>
								{{#each comCodeMap}}
                                <option value="{{@key}}" {{selected @key}}>{{this}}</option>
								{{/each}}
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
							{{#each itemOrderList}}
							<tr style="cursor:pointer;" onclick="OpenWindow('detail.do?ordercode={{ordercode}}','상세보기',800,700);">
								<td style="text-align: center;">{{ordercode}}</td>
								<td style="text-align: right;">{{priceToString itemOrderPaymentPrice}} 원</td>
								<td style="text-align: center;">{{orderprettifyDate registDate}}</td>			
								<td style="text-align: center;">
								<span class="badge badge-{{itemOrderStatusBtnClass itemOrderStatus}}">{{itemOrderStatusName itemOrderStatus}}</span>
								</td>

							</tr>
							{{/each}}
					</tbody>
					</table>
                </div>
              <div class="card-footer" id="orderPaging">
			</div>
</div>
		
</script>
<script type="text/x-handlebars-template" id="order_pagination-template" >
<nav aria-label="Navigation" id="orderpageItem">
<ul class="pagination justify-content-center m-0">
<li class="paginate_button page-item" onclick="order_numberChange(1)">
   <a href="javascript:order_page_go('{{orderpageurl 1}}')" aria-controls="example2" data-dt-idx="1" tabindex="0" class="page-link">
      <i class='fas fa-angle-double-left'></i>
   </a>
</li>
<li class="paginate_button page-item" onclick="order_numberChange({{#if prev}} {{prevPageNum}} {{/if}})">
   <a href="javascript:order_page_go('{{#if prev}}{{orderpageurl prevPageNum}}{{/if}}')" aria-controls="example2" data-dt-idx="1" tabindex="0" class="page-link">
      <i class='fas fa-angle-left'></i>
   </a>

</li>
{{url}}
{{#each pageNum}}
<li class="paginate_button page-item {{ordersignActive this}}" onclick="order_numberChange({{this}})">
   <a href="javascript: order_page_go('{{orderpageurl this}}')" aria-controls="example2" data-dt-idx="1" tabindex="0" class="page-link">
      {{this}}
   </a>
</li>
{{/each}}

<li class="paginate_button page-item" onclick="order_numberChange({{#if next}}{{nextPageNum}}{{/if}})" >
   <a href="javascript:order_page_go('{{#if next}}{{orderpageurl nextPageNum}}{{/if}}')" aria-controls="example2" data-dt-idx="1" tabindex="0" class="page-link">
      <i class='fas fa-angle-right'></i>
   </a>
</li>
<li class="paginate_button page-item" onclick="order_numberChange({{realEndPage}})">
   <a href="javascript:order_page_go('{{orderpageurl realEndPage}}')" aria-controls="example2" data-dt-idx="1" tabindex="0" class="page-link">
      <i class='fas fa-angle-double-right'></i>
   </a>
</li>   
</ul>
</nav>
</script>

<!-- 알림 sweetalert2 -->
<script src="<%=request.getContextPath()%>/resources/bootstrap/plugins/sweetalert2/sweetalert2.all.min.js"></script>


<script >
let order_page=1;

function order_list_go(page){
	let itemOrderStatus =event.target.value
	//console.log(itemOrderStatus)
	order_List("<%=request.getContextPath()%>/branch/item/orderlist?page="+page+"&searchType="+itemOrderStatus);
		
}



function order_page_go(url){
	if(url==null||url.trim()==""){
		Swal.fire({
			icon : 'warning', // 여기다가 아이콘 종류를 쓰면 됩니다.
			title : '페이지가 없습니다.'
		});
		return;
	}
	order_List(url);
} 
function order_numberChange(number){
	order_page=number;
	
	
}
function order_List(pageInfo){
	$.ajax({
		url : pageInfo,
		type : 'get',
		dataType : "json",
		success : function(dataMap) {
			
			let source = $("#order_list").html();
			let pageSource = $("#order_pagination-template").html();
			
			let pageTemplate = Handlebars.compile(pageSource); 
			let template = Handlebars.compile(source); 
			
			let pageMaker=dataMap.pageMaker;
			let cri=dataMap.pageMaker.cri;
			let	itemOrderList =dataMap.itemOrderList;
			let comCodeMap = dataMap.comCodeMap;
			//console.log(dataMap);
			
			
			let pageNumArray = new Array(pageMaker.endPage-pageMaker.startPage+1);
		    for(let i=0; i<pageMaker.endPage-pageMaker.startPage+1;i++){
		        pageNumArray[i]=pageMaker.startPage+i;
	    	}
		  
			
			pageMaker.pageNum=pageNumArray;
   			pageMaker.prevPageNum=pageMaker.startPage-1;
            pageMaker.nextPageNum=pageMaker.endPage+1;
            
            Handlebars.registerHelper({
            	  "ordersignActive":function(pageNum){
            		  
 					 if(pageNum == order_page) return 'active';
 			   }, "orderprettifyDate":function(timeValue){
             	      var dateObj=new Date(timeValue);
             	      var year=dateObj.getFullYear();
             	      var month=dateObj.getMonth()+1;
             	      var date=dateObj.getDate();
             	      return year+"/"+month+"/"+date;
             	},
               "orderpageurl":function(pageNum){
            	   return "<%=request.getContextPath()%>/branch/item/orderlist?page="+pageNum;
               },"itemOrderStatusName":function(itemOrderStatus){
            	  return comCodeMap[itemOrderStatus];
               },'priceToString':function(price){
            		 return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
        	   },"itemOrderStatusBtnClass":function(status){
	          	  	if(status=="01"){
	        	  		return 'warning';
	        	  	}if(status=="02"){
	        	  		return 'warning';
	        	  	}
	        	  	if(status=="03"){
	        	  		return 'success';
	        	  	}if(status=="04"){
	        	  		return 'warning';
	        	  	}            	  	
	        	  	if(status=="05"){
	        	  		return 'danger';
	        	  	}if(status=="06"){
	        	  		return 'success';
	        	  	}
         	   },"selected":function(status){
         			if(dataMap.pageMaker.cri.searchType==status){
	        	  		return 'selected';
	        	  	}
         	   }
         	   
			});
            
			let data={
					pageMaker:pageMaker,
					cri:cri,
					itemOrderList:itemOrderList,
					comCodeMap:comCodeMap,
					pageMaker:dataMap.pageMaker
			}
			
			let html = template(data);
			let pagehtml = pageTemplate(pageMaker);
			
			$("#appendOrder").innerHTML="";
			if($("#removeOrder")!=null){
				$("#removeOrder").remove()
			}
			if(document.querySelector("#orderpageItem")!=null){
				document.querySelector("#orderpageItem").remove()
			}
			
			$("#appendOrder").append(html)
			$("#orderPaging").append(pagehtml);
		},
		error : function(error) {
			AjaxErrorSecurityRedirectHandler(error.status);
		}
	});
}
</script>

