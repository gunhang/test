<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/x-handlebars-template" id="examine" >
<div class="row ml-3 mr-3" id="removeTag">
		<div class="col-8">
			<div class="card card-default">
				<div class="card-header">
					<h3 class="card-title">
						<b>심사 내용</b>
					</h3>
				</div>
				
				

					{{#if content}}
						<form action="examineRegist" method="post" id="examineForm">
							<input type="hidden" value="{{applicationNo}}" name="applicationNo">	
							<textarea id="content" name="examinationDetails" style="height: 250px">
							</textarea>
							<input type="hidden" value="" name="examinationYn" id="examinationYn">
							<input type="hidden" value="" name="progressStatusCode" id="progressStatusCode">
						</form>
					{{else}}
						<div class="card-body" style="height: 250px">
							{{examinationDetails}}
						</div>
					{{/if}}
			</div>
		</div>
		<div class="col-4">
			<div class="card card-body">
				<div class="col-md-12 ">
					<div class="form-group col-12">
						<div>
							<label>임대차계약서</label>
						</div> 
						<button class="btn btn-xs btn-secondary" onclick="addFile_go();" type="button"  style="background-color:#82BBD8;border: 1px solid #82BBD8">
						<i class="fas fa-file"></i>
						&nbsp; 임대차계약서 다운로드</button>
						<button class="btn btn-xs btn-secondary" onclick="addFile_go();" type="button"  style="background-color:#82BBD8;border: 1px solid #82BBD8">
						임대차계약서 보기</button>
					</div>
					
					
					
				</div>
			</div>
			{{#if btnSee}}
				{{#if chargerBtn}}
					<div style="position: absolute; right: 20px; bottom: 20px;">
						<button class="btn btn-md btn-primary" onclick="examineOK()">승인</button>
						<button class="btn btn-md btn-danger" onclikc="examineReject()">반려</button>
					</div>	
				{{/if}}
			{{/if}}
		</div>
	</div>
	</script>
	
	<script type="text/javascript">
		function branch_examine(flag,applicationNo){
			let btnSee=true;
			if(flag==true){
				btnSee=false;
			}
			 $.ajax({
			        url:"<%=request.getContextPath()%>/admin/branchapplication/applicationData",
			        type:"get",
			        data: {
			        	applicationNo:applicationNo
			        },
			        dataType:"json",
			        success:function(application){
			        	let source = $("#examine").html(); 
			        	let template = Handlebars.compile(source);
			        	application.base=true;
			        	application.btnSee=btnSee;
			        	application.chargerBtn=false;
			        	if(application.charger=='${loginEmployee.employeeId}'){
			        		application.chargerBtn=true;
			        	}
			        	
			        	
			        	if(application.progressStatusCode=='04'){
			        		application.content=true
			        	}
			        	
			        	console.log(application)
			        	
			        	let html = template(application);
			        	
			        	
			        	
			        	if($('#removeTag')!=null){
			        		$('#removeTag').remove();	
			        	}
			        	$('#handleTag').append(html);
			        	
			        	examineSummerNote();
			        },
			        error:function(error){
					//alert('댓글이 등록을 실패했습니다.');
					AjaxErrorSecurityRedirectHandler(error.status);
				}
			    })
		}
		
		function examineSummerNote(){
			
			
			 $('#content').summernote({
	  		      height: 200,          // 기본 높이값

	  		      minHeight: 250,      // 최소 높이값(null은 제한 없음)

	  		      maxHeight: null,      // 최대 높이값(null은 제한 없음)

	  		      focus: true,          // 페이지가 열릴때 포커스를 지정함

	  		      lang: 'ko-KR',         // 한국어 지정(기본값은 en-US)
	  		      toolbar: [
	  		         
	  		      ],
	  		      placeholder: '최대 2048자'

	  		    });
			 summernote_go($('div[id="content"]'),'<%=request.getContextPath()%>');	
			
		}
		function examineReject(){
			if(document.querySelector('#content').value.trim()==""){
				alert("내용을 입력하세요");
				return;
			}
			let form =document.querySelector('#examineForm');
			document.querySelector('#examinationYn').value='N';
			document.querySelector('#progressStatusCode').value="05"
			
			
			form.submit();
		}
		function examineOK(){
			if(document.querySelector('#content').value.trim()==""){
				alert("내용을 입력하세요");
				return;
			}
			let form = document.querySelector("#examineForm");
			document.querySelector('#examinationYn').value='Y';
			document.querySelector('#progressStatusCode').value="06"

			form.submit();
		}
	
	</script>
	