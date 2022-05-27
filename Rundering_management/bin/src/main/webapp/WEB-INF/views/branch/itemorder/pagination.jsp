<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<nav aria-label="Navigation">
	<ul class="pagination justify-content-center m-0">
		<li class="page-item">
			<a class="page-link" href="javascript:list_go(1);">
				<i class="fas fa-angle-double-left" style="color:#82BBD8;"></i>
			</a>
		</li>
		<li class="page-item">
			<a class="page-link" href="javascript:list_go('${pageMaker.prev ? pageMaker.startPage-1 : pageMaker.cri.page}');">
				<i class="fas fa-angle-left" style="color:#82BBD8;"></i>
			</a>
		</li>
		<c:forEach var="pageNum" begin="${pageMaker.startPage }" end="${pageMaker.endPage }" >
			<li class="page-item ${pageMaker.cri.page == pageNum?'active':''}" >
				<a class="page-link" href="javascript:list_go('${pageNum}');"style="${pageMaker.cri.page == pageNum? 'background-color:#82BBD8; border:1px solid #82BBD8':''}
																					${pageMaker.cri.page != pageNum? 'color:#82BBD8;':''}" >${pageNum }</a>
			</li>
		</c:forEach>
		<li class="page-item">
			<a class="page-link" href="javascript:list_go('${pageMaker.next ? pageMaker.endPage+1 :pageMaker.cri.page}');">
				<i class="fas fa-angle-right" style="color:#82BBD8;"></i>
			</a>
		</li>
		<li class="page-item">
			<a class="page-link" href="javascript:list_go('${pageMaker.realEndPage}');">
				<i class="fas fa-angle-double-right" style="color:#82BBD8;"></i>
			</a>
		</li>	
	</ul>
</nav>


<form id="jobForm">	
	<input type='hidden' name="page" value="" />
	<input type='hidden' name="searchType" value="" />
	<input type='hidden' name="keyword" value="" />
</form>

<script>
	function list_go(page,url){
		
		var jobForm=$('#jobForm');
		
		jobForm.find("[name='page']").val(page);
		jobForm.find("[name='searchType']").val($('select[name="searchType"]').val());
		jobForm.find("[name='keyword']").val($('div.input-group>input[name="keyword"]').val());
		
		$.ajax({
			url : "<%=request.getContextPath()%>/branch/itemorder/orderGoodsList",
			type : 'get',
			data : jobForm,
			dataType : "json",
			success : function(dataMap) {
				let source = $("#laundryArticlesList").html();
				let template = Handlebars.compile(source); 
				
				let pageMaker=dataMap.pageMaker
				let cri=dataMap.pageMaker.cri 
				let	laundryArticlesList =dataMap.laundryArticlesList
				let pageNumArray = new Array(pageMaker.endPage-pageMaker.startPage+1);
			    for(var i=0; i<pageMaker.endPage-pageMaker.startPage+1;i++){
			       pageNumArray[i]=pageMaker.startPage+i;
			    }
			    pageMaker.pageNum=pageNumArray;
			    pageMaker.prevPageNum=pageMaker.startPage-1;
			    pageMaker.nextPageNum=pageMaker.endPage+1;
			   
			
				let data={
						pageMaker:pageMaker,
						cri:cri,
						laundryArticlesList:laundryArticlesList
				}
				let html = template(data);
				$("#listBody").innerHTML="";
				$("#listBody").append(html)
			},
			error : function(error) {
				AjaxErrorSecurityRedirectHandler(error.status);
			}
		});
	}
</script>