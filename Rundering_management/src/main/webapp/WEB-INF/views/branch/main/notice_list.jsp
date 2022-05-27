<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>

<script type="text/x-handlebars-template" id="notice_list" >
			<div class="card m-0" id="removeNotice" style="height: 398px;">
				<div class="card-header">
					<h2 style="height: 20px;padding: 5px;font-size: 1.25rem;" class="card-title">
		            	<b>공지사항</b>
		            </h2>
					<div class="card-tools">
						<button class="btn btn-sm btn-outline-primary" onclick="goPage('<%=request.getContextPath() %>/branch/notice/list','B040000')" style="font-weight: 500;font-size: 0.97rem;">바로가기</button>

					</div>
				</div>

				<div class="card-body p-0" style="height:355px; overflow:auto">
					<table class="table table-hover text-nowrap listScroll">
						<thead>
							<tr>
								<th class="width60" style="text-align:center">제목</th>
								<th class="width15" style="text-align:center">작성자</th>
								<th class="width25" style="text-align:center">작성날짜</th>
							</tr>
						</thead>
						<tbody>
							{{#each noticeList}}
								<tr style='cursor: pointer;'
									onclick="OpenWindow('<%=request.getContextPath() %>/branch/notice/detail?from=list&noticeno={{noticeno }}','상세보기',800,700);">
									<td style="max-width:100px;overflow:hidden;text-overflow:ellipsis;">{{title}}	
										{{#if noticeDate}}
										&nbsp;<span class="badge bg-red">new</span>
										{{/if}}
									</td>
									<td>{{employeeId}}</td>
									<td style="text-align:center">
										{{noticePrettifyDate registDate}}
									</td>
								</tr>
							{{/each}}
						</tbody>
					</table>
				</div>
			</div>
</script>
<script>
function notice_list(pageInfo){
	$.ajax({
		url : pageInfo,
		type : 'get',
		dataType : "json",
		success : function(dataMap) {
			
			let source = $("#notice_list").html();
			
			let template = Handlebars.compile(source); 
			
			let	itemOrderList =dataMap.itemOrderList;
			console.log(dataMap);
			
			let now = new Date();
			
			for(let i of dataMap.noticeList){
				let noticeDay =  i.registDate/(1000*60*60*24);
				let nowDay = now/(1000*60*60*24);
				if(3>(nowDay-noticeDay)){
					i.noticeDate=true;
				}else{
					i.noticeDate=false;
				}
				
			}
            
            Handlebars.registerHelper({
 			    "noticePrettifyDate":function(timeValue){
             	      var dateObj=new Date(timeValue);
             	      var year=dateObj.getFullYear();
             	      var month=dateObj.getMonth()+1;
             	      var date=dateObj.getDate();
             	      return year+"/"+month+"/"+date;
             	}
			});
            
			let data={
					noticeList:dataMap.noticeList
			}
			
			let html = template(data);
			
			$("#appendNotice").innerHTML="";
			if($("#removeNotice")!=null){
				$("#removeNotice").remove()
			}
			
			$("#appendNotice").append(html)
		},
		error : function(error) {
			AjaxErrorSecurityRedirectHandler(error.status);
		}
	});
}

</script>

