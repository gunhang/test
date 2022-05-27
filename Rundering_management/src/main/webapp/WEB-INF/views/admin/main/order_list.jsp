<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>

<script type="text/x-handlebars-template" id="order_list" >
			<div class="card m-0" id="removeOrder" style="height: 398px;">
				<div class="card-header">
					<h2 style="height: 20px;padding: 5px;font-size: 1.25rem;" class="card-title">
		            	<b>발주</b>
		            </h2>
					<div class="card-tools">
						<button class="btn btn-sm btn-outline-primary" onclick="goPage('<%=request.getContextPath() %>/admin/branchorder/list','A030100')" style="font-weight: 500;font-size: 0.97rem;">바로가기</button>
					</div>
				</div>

				<div class="card-body p-0" style="height:355px; overflow:auto">
					<table class="table table-hover text-nowrap listScroll">
						<thead>
							<tr>
								<th class="width30" style="text-align:center">지점명</th>
								<th class="width30" style="text-align:center">가격</th>
								<th class="width20" style="text-align:center">작성날짜</th>
								<th class="width20" style="text-align:center">상태</th>
							</tr>
						</thead>
						<tbody>
								{{#each itemOrderList }}
								<tr		onclick="window.open('<%=request.getContextPath()%>/admin/branchorder/detail?ordercode={{ordercode}}', '발주 상세', 'width=700, height=900');"
									style="cursor: pointer;">
									<td style="text-align:left">{{branchMap branchCode}}</td>
									<td style="text-align:right">{{priceToString itemOrderPaymentPrice}}원</td>
									<td style="text-align:right">
										{{orderPrettifyDate registDate}}
									</td >
										<td style="text-align:center"><span class="badge badge-{{itemOrderStatusBtnClass itemOrderStatus}}">{{itemOrderStatusName itemOrderStatus}}</span></td>
								</tr>
							{{/each}}
						</tbody>
					</table>
				</div>
			</div>
</script>
<script>
function order_list(pageInfo){
	$.ajax({
		url : pageInfo,
		type : 'get',
		dataType : "json",
		success : function(dataMap) {
			
			let source = $("#order_list").html();
			
			let template = Handlebars.compile(source); 
			
			let	itemOrderList =dataMap.itemOrderList;
			let comCodeMap = dataMap.comCodeMap
			let branchList=dataMap.branchList
			//console.log(dataMap);
			
			let now = new Date();
			
            Handlebars.registerHelper({
 			    "orderPrettifyDate":function(timeValue){
             	      var dateObj=new Date(timeValue);
             	      var year=dateObj.getFullYear();
             	      var month=dateObj.getMonth()+1;
             	      var date=dateObj.getDate();
             	      return year+"/"+month+"/"+date;
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
             	  	
             	  	
                },"branchMap":function(code){
          		  for(let i of branchList){
          			  if(i.branchCode==code){
          				  return i.branchName;
          			  }
          		  }
          		  
          	  },'priceToString':function(price){
           		 return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
          	  }
			});
            
			let data={
					itemOrderList:itemOrderList
			}
			
			let html = template(data);
			
			$("#appendOrder").innerHTML="";
			if($("#removeOrder")!=null){
				$("#removeOrder").remove()
			}
			
			$("#appendOrder").append(html)
		},
		error : function(error) {
			AjaxErrorSecurityRedirectHandler(error.status);
		}
	});
}

</script>

