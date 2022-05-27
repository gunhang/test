<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!--이쁜 알럽트창 -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.1.9/dist/sweetalert2.min.css">
    
<script type="text/x-handlebars-template" id="item_list">
		
			<div id="removeItem">
                <div class="card-header">
                   <h2 style="height: 20px;" class="card-title">
						<b>재고</b>
					</h2>
                    <div class="card-tools">
                        <div class="input-group input-group-sm" >
                        </div>
                    </div>
                </div>
                <div class="card-body table-responsive p-0 mt-0" style="height: 600px;overflow: auto;">
                    <table
                        class="table table-hover text-nowrap card-outline">
                        <thead>
                            <tr>
                                <th class="width50" style="text-align: center;">물품이름</th>
                                <th class="width25" style="text-align: center;height: 24px;padding-bottom: 8px;padding-top: 0px;">
                                    <div class="input-group input-group-sm" >
										 <select class="form-control" style="width: 60px;" name="searchType"  id="searchType" onchange="list_go(1);">
											<option value="">전체</option>
											{{#each clcodeList}}                                           	 
                                             	<option value="{{comCode}}"  {{selected comCode}}>{{comCodeNm }}</option>
                                            {{/each}}
                                         </select>
                                     </div>
                                </th>
                                <th class="width25" style="text-align: center;height: 24px;padding-bottom: 8px;padding-top: 0px;">
									  <div class="input-group input-group-sm" >
										 <select class="form-control" style="width: 60px;" name="searchTypeOrderBy"  id="searchTypeOrderBy" onchange="list_go_orderBy(1);">
											<option value="" {{selected ""}}>기본</option>
											<option value="asc" {{selected asc}}>적은순</option>
											<option value="desc" {{selected desc}}>많은순</option>
                                         </select>
                                     </div>
								</th>
                            </tr>
                        </thead>
                        <tbody>
						{{#each itemList}}
                               <tr style="{{numberColor supplyCount}}">
                               		<td style="text-align: left;max-width:240px;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;">{{articlesName}}</td>
                               		<td style="text-align: center;">{{clcodeNm clcode}}</td>
                               		<td style="text-align: right; ">{{supplyCount}}({{getEach}})</td>
                               </tr>     
						{{/each}}
                        </tbody>
                    </table>
                </div>
              <div class="card-footer" id="itemPaging" >
			</div>
            </div>
             
         </script>
<script type="text/x-handlebars-template" id="item_pagination-template" >
<nav aria-label="Navigation" id="itemPageItem">
<ul class="pagination justify-content-center m-0">
<li class="paginate_button page-item" onclick="item_numberChange(1)">
   <a href="javascript:item_page_go('{{itempageurl 1}}')" aria-controls="example2" data-dt-idx="1" tabindex="0" class="page-link">
      <i class='fas fa-angle-double-left'></i>
   </a>
</li>
<li class="paginate_button page-item" onclick="item_numberChange({{#if prev}} {{prevPageNum}} {{/if}})">
   <a href="javascript:item_page_go('{{#if prev}}{{itempageurl prevPageNum}}{{/if}}')" aria-controls="example2" data-dt-idx="1" tabindex="0" class="page-link">
      <i class='fas fa-angle-left'></i>
   </a>

</li>
{{url}}
{{#each pageNum}}
<li class="paginate_button page-item {{itemsignActive this}}" onclick="item_numberChange({{this}})">
   <a href="javascript: item_page_go('{{itempageurl this}}')" aria-controls="example2" data-dt-idx="1" tabindex="0" class="page-link">
      {{this}}
   </a>
</li>
{{/each}}

<li class="paginate_button page-item" onclick="item_numberChange({{#if next}}{{nextPageNum}}{{/if}})" >
   <a href="javascript:item_page_go('{{#if next}}{{itempageurl nextPageNum}}{{/if}}')" aria-controls="example2" data-dt-idx="1" tabindex="0" class="page-link">
      <i class='fas fa-angle-right'></i>
   </a>
</li>
<li class="paginate_button page-item" onclick="item_numberChange({{realEndPage}})">
   <a href="javascript:item_page_go('{{itempageurl realEndPage}}')" aria-controls="example2" data-dt-idx="1" tabindex="0" class="page-link">
      <i class='fas fa-angle-double-right'></i>
   </a>
</li>   
</ul>
</nav>
</script>         
        
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.1.9/dist/sweetalert2.all.min.js"></script>
 
<script >
let item_page=1;
function list_go(page){
	
	let searchType = document.querySelector("#searchType").value;
		
	item_List("<%=request.getContextPath()%>/admin/branchorder/itemlist?page="+page+"&searchType="+searchType);
	
}
function list_go_orderBy(page){
	let searchType=document.querySelector("#searchTypeOrderBy").value
	item_List("<%=request.getContextPath()%>/admin/branchorder/itemlist?page="+page+"&searchType="+searchType);
}




function item_page_go(url){
	if(url==null||url.trim()==""){
		Swal.fire({
			icon : 'error', // 여기다가 아이콘 종류를 쓰면 됩니다.
			title : '페이지가 없습니다.'
		});
		return;
	}
	item_List(url);
} 
function item_numberChange(number){
	item_page=number;
	
	
}
function item_List(pageInfo){
	$.ajax({
		url : pageInfo,
		type : 'get',
		dataType : "json",
		success : function(dataMap) {
			
			
			let source = $("#item_list").html();
			let pageSource = $("#item_pagination-template").html();
			
			let pageTemplate = Handlebars.compile(pageSource); 
			let template = Handlebars.compile(source); 
			
			let pageMaker=dataMap.pageMaker;
			let cri=dataMap.pageMaker.cri;
			let	itemList =dataMap.itemList;
			
			console.log(dataMap);
			
			for(let i of itemList){
				i.getEach=i.each
			}
			
			let pageNumArray = new Array(pageMaker.endPage-pageMaker.startPage+1);
		    for(let i=0; i<pageMaker.endPage-pageMaker.startPage+1;i++){
		        pageNumArray[i]=pageMaker.startPage+i;
	    	}
		  
			
			pageMaker.pageNum=pageNumArray;
   			pageMaker.prevPageNum=pageMaker.startPage-1;
            pageMaker.nextPageNum=pageMaker.endPage+1;
            
            Handlebars.registerHelper({
            	  "itemsignActive":function(pageNum){
            		  
 					 if(pageNum == item_page) return 'active';
 			   }, "itemprettifyDate":function(timeValue){
             	      var dateObj=new Date(timeValue);
             	      var year=dateObj.getFullYear();
             	      var month=dateObj.getMonth()+1;
             	      var date=dateObj.getDate();
             	      return year+"/"+month+"/"+date;
             	},
               "itempageurl":function(pageNum){
            	   return "<%=request.getContextPath()%>/admin/branchorder/itemlist?page="+pageNum;
               },"clcodeNm":function(clcode){
               		for(let i of dataMap.CLCODEList){
               			if(clcode==i.comCode){
               				return i.comCodeNm
               			}
               		}
            	},"selected":function(code){
         			if(dataMap.pageMaker.cri.searchType==code){
	        	  		return 'selected';
	        	  	}
         	   },"numberColor":function(number){
         		   let num =parseInt(number);
         		   let result =""
         		   if(num<100){
         			   result = "background-color:#fbfbbd;";
         		   }
         		   if(num<40){
         			   result ="background-color:#ffb2b2;";
         		   }
         		   return result;
         	   }
            	
			});
            
			let data={
					pageMaker:pageMaker,
					cri:cri,
					itemList:itemList,
					clcodeList:dataMap.CLCODEList,
					asc:'asc',
					desc:'desc'
					
			}
			
			let html = template(data);
			let pagehtml = pageTemplate(pageMaker);
			
			$("#appenditem").innerHTML="";
			if($("#removeItem")!=null){
				$("#removeItem").remove()
			}
			if(document.querySelector("#itemPageItem")!=null){
				document.querySelector("#itemPageItem").remove()
			}
			
			$("#appenditem").append(html)
			$("#itemPaging").append(pagehtml);
		},
		error : function(error) {
			AjaxErrorSecurityRedirectHandler(error.status);
		}
	});
}
</script>
 