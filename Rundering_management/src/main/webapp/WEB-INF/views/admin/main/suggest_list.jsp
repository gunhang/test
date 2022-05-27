<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/x-handlebars-template" id="suggest_list" >
			<div class="card m-0" id="removeSuggest" style="height: 398px;">
				<div class="card-header">
					<h2 style="height: 20px;padding: 5px;font-size: 1.25rem;" class="card-title">
		            	<b>건의사항</b>
		            </h2>
					<div class="card-tools">
						<button class="btn btn-sm btn-outline-primary" onclick="goPage('<%=request.getContextPath() %>/admin/suggest/list','A050200')" style="font-weight: 500;font-size: 0.97rem;">바로가기</button>

					</div>
				</div>

				<div class="card-body p-0" style="height:355px; overflow:auto">
					<table class="table table-hover text-nowrap listScroll">
						<thead>
							<tr>
								<th class="width50" style="text-align:center">제목</th>
								<th class="width25" style="text-align:center">지점명</th>
								<th class="width25" style="text-align:center">확인여부</th>
							</tr>
						</thead>
						<tbody>
							{{#each suggestList}}
								<tr style='cursor: pointer;'
									onclick="OpenWindow('<%=request.getContextPath() %>/admin/suggest/detail?from=list&sno={{sno}}','상세보기',800,700);">
									<td style="max-width:100px;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;">{{title}}</td>
									<td>{{branchName}}</td>
									<td style="text-align:center">
										{{checkyn}}
									</td>
								</tr>
							{{/each}}
						</tbody>
					</table>
				</div>
			</div>
</script>
<script>
function suggest_list(pageInfo){
	$.ajax({
		url : pageInfo,
		type : 'get',
		dataType : "json",
		success : function(dataMap) {
			
			let source = $("#suggest_list").html();
			
			let template = Handlebars.compile(source); 
			
			let	itemOrderList =dataMap.itemOrderList;
			//console.log(dataMap);
			
			let now = new Date();
			
			for(let i of dataMap.suggestList){
				let suggestDay =  i.registDate/(1000*60*60*24);
				let nowDay = now/(1000*60*60*24);
				if(3>(nowDay-suggestDay)){
					i.suggestDate=true;
				}else{
					i.suggestDate=false;
				}
				
			}
            
            Handlebars.registerHelper({
 			    "suggestPrettifyDate":function(timeValue){
             	      var dateObj=new Date(timeValue);
             	      var year=dateObj.getFullYear();
             	      var month=dateObj.getMonth()+1;
             	      var date=dateObj.getDate();
             	      return year+"/"+month+"/"+date;
             	}
			});
            
			let data={
					suggestList:dataMap.suggestList
			}
			
			let html = template(data);
			
			$("#appendSuggest").innerHTML="";
			if($("#removeSuggest")!=null){
				$("#removeSuggest").remove()
			}
			
			$("#appendSuggest").append(html)
		},
		error : function(error) {
			AjaxErrorSecurityRedirectHandler(error.status);
		}
	});
}

</script>

