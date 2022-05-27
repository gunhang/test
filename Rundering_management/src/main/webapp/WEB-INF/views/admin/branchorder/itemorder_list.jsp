<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
 <!--이쁜 알럽트창 -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.1.9/dist/sweetalert2.min.css">
  
<script type="text/x-handlebars-template" id="order_list" >
<div class="card" id="removeOrder">
         <div class="card-header">
            <div class="input-group input-group-sm">
               <h2 style="height: 20px;" class="card-title">
                  <b>발주 </b>
               </h2>

               <div class="col-7"></div>
            </div>
            <div class="card-tools"></div>

         </div>
          <div class="card-body p-0" style="height: 600px;">
            <table style="text-align: center;" class="table text-nowrap">
               <thead>
                  <tr>
                   <th>지점명</th>
					<th>총가격(원)</th>
					<th>신청일</th>
					<th>처리상태</th>
                  </tr>
               </thead>
						<tbody>
							{{#if itemOrderListFlag}}
								<td colspan="4" style="text-align:center;"><strong>발주내역이 존재하지 않습니다.</strong></td>
							{{/if}}
							{{#each itemOrderList }}
								<tr		onclick="window.open('<%=request.getContextPath()%>/admin/branchorder/detail?ordercode={{ordercode}}', '발주 상세', 'width=700, height=900');"
									style="cursor: pointer;">
									<td>{{branchMap branchCode}}</td>
									<td style="text-align:right">{{priceToString itemOrderPaymentPrice}}</td>
									<td>
										{{orderprettifyDate registDate}}
									</td>
										<td><span class="badge badge-{{itemOrderStatusBtnClass itemOrderStatus}}">{{itemOrderStatusName itemOrderStatus}}</span></td>
								</tr>
							{{/each}}
						</tbody>
					</table>
				</div>
					<div class="card-footer clearfix" id="orderPaging">
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

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.1.9/dist/sweetalert2.all.min.js"></script>
 
<script >
let order_page=1;


function order_page_go(url){
	if(url==null||url.trim()==""){
		Swal.fire({
			icon : 'error', // 여기다가 아이콘 종류를 쓰면 됩니다.
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
			let branchList=dataMap.branchList;
			let pageNumArray = new Array(pageMaker.endPage-pageMaker.startPage+1);
		    for(let i=0; i<pageMaker.endPage-pageMaker.startPage+1;i++){
		        pageNumArray[i]=pageMaker.startPage+i;
	    	}
		  	
		    let itemOrderListFlag= true;
			if(itemOrderList.length>0){
				itemOrderListFlag= false;
			}
		    console.log(dataMap)
		    
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
            	   return "<%=request.getContextPath()%>/admin/branchorder/orderlist?page="+pageNum;
               },"itemOrderStatusName":function(itemOrderStatus){
            	  return comCodeMap[itemOrderStatus];
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
            	  	
            	  	
               },'priceToString':function(price){
          		 return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
         	  },"branchMap":function(code){
         		  for(let i of branchList){
         			  if(i.branchCode==code){
         				  return i.branchName;
         			  }
         		  }
         		  
         	  }
         	  
			});
            
			let data={
					pageMaker:pageMaker,
					cri:cri,
					itemOrderList:itemOrderList,
					itemOrderListFlag:itemOrderListFlag
					
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