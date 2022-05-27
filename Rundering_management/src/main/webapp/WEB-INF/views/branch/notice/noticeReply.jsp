<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.7.7/handlebars.min.js"></script>
<script type="text/x-handlebars-template" id="noticeReplyList" >
<div id="replyHandler">
	{{#replyList}}
	<div class="card-footer card-comments" style="border-bottom: 1px solid lightgray">
		<div class="card-comment">
			<div class="comment-text" style="margin-left:0px; ">
				<span class="username"> 사원번호 : {{employeeId}} &nbsp;&nbsp; {{branchName}}  <span
				class="text-muted float-right">작성일:{{prettifyDate registDate}}</span>
			</span>
				{{replyContent}}
				<button class="btn btn-sm btn-warning float-right" data-replynoseq="{{replynoSeq}}" style="display:{{VisibleByLoginCheck memberno}}; background-color:#b6c1c6; border: 1px solid white;" onclick="replyRemove()">삭제</button>				
				<button class="btn btn-sm btn-warning float-right" data-replycontent="{{replyContent}}" data-toggle="modal" data-target="#modal-modify" data-replynoseq="{{replynoSeq}}" style="display:{{VisibleByLoginCheck memberno}}; background-color:#b6c1c6; border: 1px solid white;" onclick="replyModifyModal()" >수정</button>
				
			</div>
		</div>
	</div>
	{{/replyList}}
</div>
</script>
<script type="text/x-handlebars-template" id="pagination-template" >
<nav aria-label="Navigation" id="pageItem">
<ul class="pagination justify-content-center m-0">
<li class="paginate_button page-item" onclick="numberChange(1)">
   <a href="javascript:page_go('{{pageurl 1}}')" aria-controls="example2" data-dt-idx="1" tabindex="0" class="page-link">
      <i class='fas fa-angle-double-left'></i>
   </a>
</li>
<li class="paginate_button page-item">
   <a href="javascript:page_go('{{#if prev}}{{pageurl prevPageNum}}{{/if}}')" aria-controls="example2" data-dt-idx="1" tabindex="0" class="page-link">
      <i class='fas fa-angle-left'></i>
   </a>

</li>
{{url}}
{{#each pageNum}}
<li class="paginate_button page-item {{signActive this}}" onclick="numberChange({{this}})">
   <a href="javascript: page_go('{{pageurl this}}')" aria-controls="example2" data-dt-idx="1" tabindex="0" class="page-link">
      {{this}}
   </a>
</li>
{{/each}}

<li class="paginate_button page-item" onclick="numberChange({{#if next}}{{nextPageNum}}{{/if}})" >
   <a href="javascript:page_go('{{#if next}}{{pageurl nextPageNum}}{{/if}}')" aria-controls="example2" data-dt-idx="1" tabindex="0" class="page-link">
      <i class='fas fa-angle-right'></i>
   </a>
</li>
<li class="paginate_button page-item" onclick="numberChange({{realEndPage}})">
   <a href="javascript:page_go('{{pageurl realEndPage}}')" aria-controls="example2" data-dt-idx="1" tabindex="0" class="page-link">
      <i class='fas fa-angle-double-right'></i>
   </a>
</li>   
</ul>
</nav>
</script>

<script>
let page= 1;


window.onload=function(){
	noticeReplyList("<%=request.getContextPath()%>/admin/notice/noticereplylist?replyno=${notice.replyNo}&page="+page);
}   

function page_go(url){
	if(url==null||url.trim()==""){
		alert("페이지가 없습니다");
		return;
	}
	noticeReplyList(url);
} 
function numberChange(number){
	page=number;
}
function noticeReplyList(pageInfo){
	$.ajax({
		url : pageInfo,
		type : 'get',
		dataType : "json",
		success : function(dataMap) {
			
			
			let source = $("#noticeReplyList").html();
			let pageSource = $("#pagination-template").html();
			
			let pageTemplate = Handlebars.compile(pageSource); 
			let template = Handlebars.compile(source); 
			
			let pageMaker=dataMap.pageMaker;
			let cri=dataMap.pageMaker.cri;
			let	replyList =dataMap.replyList;
			let branchMap=dataMap.branchMap;
			let employeeIdMap=dataMap.employeeIdMap;
			console.log(dataMap);
			for(let i of replyList){
				i.employeeId=employeeIdMap[i.memberno];
				i.branchName=branchMap[i.employeeId];
			}
			console.log(replyList);
			
			
			let pageNumArray = new Array(pageMaker.endPage-pageMaker.startPage+1);
		    for(let i=0; i<pageMaker.endPage-pageMaker.startPage+1;i++){
		        pageNumArray[i]=pageMaker.startPage+i;
	    	}
		
			
			pageMaker.pageNum=pageNumArray;
   			pageMaker.prevPageNum=pageMaker.startPage-1;
            pageMaker.nextPageNum=pageMaker.endPage+1;
            
            Handlebars.registerHelper({
			   "signActive":function(pageNum){
				   	
					 if(pageNum == page) return 'active';
			   },
               "pageurl":function(pageNum){
            	   
            	   return "<%=request.getContextPath()%>/admin/notice/noticereplylist?replyno=${notice.replyNo}&page="+pageNum;
               },"prettifyDate":function(timeValue){
            	      var dateObj=new Date(timeValue);
            	      var year=dateObj.getFullYear();
            	      var month=dateObj.getMonth()+1;
            	      var date=dateObj.getDate();
            	      return year+"/"+month+"/"+date;
               },"VisibleByLoginCheck" : function(replyer) {//LoginUser와 댓글작성자가 같을때 modify버튼 노출
            	      var result = "none";
            	      if(replyer == "${loginMember.memberNo }") result="visible";
            	      return result;
            	}
			});
            
			let data={
					pageMaker:pageMaker,
					cri:cri,
					replyList:replyList,
			}
			
			let html = template(data);
			let pagehtml = pageTemplate(pageMaker);
			
			$("#replyListTag").innerHTML="";
			if($("#replyHandler")!=null){
				$("#replyHandler").remove()
			}
			if(document.querySelector("#pageItem")!=null){
				document.querySelector("#pageItem").remove()
			}
			$("#cardfooter").append(pagehtml)
			$("#replyListTag").append(html)
		},
		error : function(error) {
			AjaxErrorSecurityRedirectHandler(error.status);
		}
	});
}
</script>

<script>
function registReply(){
    let replyContent= document.querySelector("#replyContent").value;
    let replyno =  "${notice.replyNo}";
    if(replyno==null||replyno==""){
    	return;
    }
    if(replyContent==null || replyContent.trim()==""){
        return
    }
     $.ajax({
        url:"<%=request.getContextPath()%>/admin/reply/regist",
        type:"post",
        data:{
        	replyno:replyno,
        	replyContent:replyContent
        },
        success:function(data){
        	console.log(data);
        	page=data;
        	noticeReplyList("<%=request.getContextPath()%>/admin/notice/noticereplylist?replyno=${notice.replyNo}&page="+page);
        	$("#replyContent").val("");
        	alert("댓글이 등록되었습니다");
        },
        error:function(error){
			AjaxErrorSecurityRedirectHandler(error.status);
		}
    }); 
}
</script>
<script>
function replyModifyModal(){
	let btn= document.querySelector("#modifyBtn");
	btn.dataset.replyno=event.target.dataset.replyno;
	btn.dataset.replynoseq=event.target.dataset.replynoseq;
	document.querySelector("#replyModifyContent").value=event.target.dataset.replycontent;
	btn.dataset.ordermemberno=event.target.dataset.ordermemberno;
	console.log(btn);
}
</script>

<script>
function replyModify(){
	//data-replyno="{{replyno}}" data-replynoseq={{replynoSeq}} data-replycontent=
	let replyno=event.target.dataset.replyno;
	let replynoSeq=event.target.dataset.replynoseq;
	let memberNo=event.target.dataset.ordermemberno;
	let replyContent= document.querySelector("#replyModifyContent").value;
	
	$.ajax({
        url:"<%=request.getContextPath()%>/admin/reply/modify",
        type:"post",
        data:{
        	page:page,
        	replyno:"${notice.replyNo}",
        	replynoSeq:replynoSeq,
        	replyContent:replyContent
        },
        success:function(data){
        	$("#replyModifyContent").val("");
        	page=data;
        	noticeReplyList("<%=request.getContextPath()%>/admin/notice/noticereplylist?replyno=${notice.replyNo}&page="+page);
        	$('#modal-modify').modal('hide');
        	alert("수정되었습니다");
            
        },
        error:function(error){
			AjaxErrorSecurityRedirectHandler(error.status);
		}
    }); 
}
function replyRemove(){
	let replynoSeq=event.target.dataset.replynoseq
	
	$.ajax({
        url:"<%=request.getContextPath()%>/admin/reply/remove",
        type:"post",
        data:{
        	replyno:${notice.replyNo},
        	page:page,
        	replynoSeq:replynoSeq
        },
        success:function(data){
        	alert("삭제되었습니다");
        	page=data
        	noticeReplyList("<%=request.getContextPath()%>/admin/notice/noticereplylist?replyno=${notice.replyNo}&page="+page);
        },
        error:function(error){
			AjaxErrorSecurityRedirectHandler(error.status);
		}
    }); 
	
}

</script>
