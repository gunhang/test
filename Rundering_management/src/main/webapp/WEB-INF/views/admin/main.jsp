<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<head>
<meta charset="UTF-8">
</head>

<body>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>

   <div class="row2 ml-3 mr-3">
      <div class="row">
         <div class="col-md-6" id="appendNotice">
            
         </div>
         <div class="col-md-6" id="appendSuggest">
            
         </div>
      </div>
      <div class="row" style="margin-top: 8px;">
         <div class="col-md-6" id="appendOrder">
            

         </div>
         <div class="col-md-6"id="appendChart" >
				

         </div>
      </div>
   </div>
 
      <script	src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.7.7/handlebars.min.js"></script>
	 <%@include file="./main/notice_list.jsp" %>
   <%@include file="./main/suggest_list.jsp" %>
   <%@include file="./main/order_list.jsp" %>

<script type="text/x-handlebars-template" id="chart" >
<div class="card m-0" style="height: 398px;">
<div class="card-header" style="height: 56px;">
					<h2 style="height: 20px;padding: 5px;font-size: 1.25rem;" class="card-title">
		            	<b>주문량</b>
		            </h2>
					<div class="card-tools">
						<select onchange="getChart()" style="font-size: 1.08rem; margin-top: 3px;">
							{{#each branchList}}
								{{#if admin}}
									<option value="{{branchLndrpcrymslmcoqy}},{{count}},{{branchName}}">{{branchName}}</option>
								{{/if}}
							{{/each}}
						</select>
					</div>
				</div>

			<div class="card-body p-0" style="width:330px; height:330px; margin-left: 60px" id="canvasDiv" >
             <canvas id="pie-chart" ></canvas>
             </div>
</div>

</script>

<script>

function getChart(){
	let result= event.target.value.split(",");
	console.log(result)
	document.querySelector("#pie-chart").remove()
	document.querySelector('#canvasDiv').innerHTML="<canvas id='pie-chart' ></canvas>"
	pieChart(parseInt(result[0]),parseInt(result[1]),result[2])
}


function pieChart(totalCount,count,branchName){
 //console.log(event.target)
	new Chart(document.getElementById("pie-chart"), {
	    type: 'pie',
	    data: {
	      labels: ["가능세탁주문", "현재주문"],
	      datasets: [{
	        label: "Population (millions)",
	        backgroundColor: ["#82BBD8", "#8e5ea2"],
	        data: [(totalCount-count),count] 
	      }]
	    },
	    options: {
	    	legend: {
				labels: {
					fontSize: 18
				}
			},
	      title: {
	        display: true,
	      }
	    }
	});

}

function goPage(url,menuCode){
	

	this.parent.document.querySelector('iframe[name="ifr"]').src=url;
	

	// HTML5 지원브라우저에서 사용 가능
	if (typeof (this.parent.history.pushState) == 'function') {
		//현재 주소를 가져온다.
		var renewURL = this.parent.location.href;
		//현재 주소 중 .부분이 있다면 날려버린다.
		renewURL = renewURL.substring(0, renewURL.indexOf(".") );

		if (menuCode != 'A000000') {
			renewURL += "?menuCode=" + menuCode;
		}
		//페이지를 리로드하지 않고 페이지 주소만 변경할 때 사용
		this.parent.history.pushState(menuCode, null, renewURL);
	} else {
		this.parent.location.hash = "#" + menuCode;
	}
	
} 




window.onload= function (){
	notice_list("<%=request.getContextPath()%>/admin/main/noticelist")
	suggest_list("<%=request.getContextPath()%>/admin/main/suggestlist?searchType=n")
	order_list("<%=request.getContextPath()%>/admin/main/orderlist")
	$.ajax({
		url : "<%=request.getContextPath()%>/admin/main/chart",
		type : 'get',
		dataType : "json",
		success : function(dataMap) {
			console.log(dataMap)
			let branchList = dataMap.branchList;
			for(let i of branchList){
				i.count= dataMap[i.branchCode]
				if(i.branchCode!="000000"){
					i.admin=true;
				}else{
					i.admin=false;
				}
			}
			let source = $("#chart").html();
			
			let template = Handlebars.compile(source); 
			
			console.log(branchList)
			let data={
					branchList:branchList
			}
			
			let html = template(data);
			
			$("#appendChart").innerHTML="";
			$("#appendChart").append(html)
			
			pieChart(branchList[1].branchLndrpcrymslmcoqy,branchList[1].count,branchList[1].branchName)
			
		
		},
		error : function(error) {
			AjaxErrorSecurityRedirectHandler(error.status);
		}
	});
}

</script>

</body>
</html>