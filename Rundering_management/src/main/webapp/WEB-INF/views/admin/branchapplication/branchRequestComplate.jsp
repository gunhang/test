<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
			
<script type="text/x-handlebars-template" id="branchRequestComplate" >
	<div class="row ml-3 mr-3" id="removeTag">
		<div class="col-8">
			<div class="card card-default">
				<div class="card-header">
					<h3 class="card-title">
						<b>지점 등록 완료</b>
					</h3>
				</div>
					<div class="card-body row">
							<div class="col-md-12 row">
								<div class="form-group col-4">
									<label>지점이름</label>
									<input type="text" class="form-control"	id="branchName" name="branchName" value="{{branch.branchName}}" readonly />
								</div>
								<div class="form-group col-4">
									<label>지점전화번호</label> 
									<input type="text" class="form-control"	id="branchContact" name="branchContact" value="{{branch.branchContact}}" readonly />
								</div>
								<div class="form-group col-4">
										<label>세탁가능수량</label> 
										<span class="input-group-append" >
										<input type="text" class="form-control quantity" style="text-align: end;"	id="branchLndrpcrymslmcoqy" name="branchLndrpcrymslmcoqy" value="{{branch.branchLndrpcrymslmcoqy}}"  readonly="readonly" />
									</span>
								</div>
							</div>
							<div class="col-md-12 row">
								<div class="form-group col-4">
									<label>우편번호</label> 
									<span class="input-group-append">
										<input type="text" class="form-control "	id="zip" name="zip" value="{{branch.zip}}"  readonly />
									
									</span>
								</div>
								<div class="form-group col-4">
									<label>주소</label> 
									<input type="text" class="form-control"	id="add1" name="add1" value="{{branch.add1}}" readonly  />
								</div>
								<div class="form-group col-4">
									<label>상세주소 </label> 
									<input type="text" class="form-control"	id="add2" name="add2" value="{{branch.add2}}" readonly />
								</div>
							</div>
						</div>
		
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
					
					<div class="form-group col-12">
						<div>
							<label>수의계약서</label>
						</div> 
						<button class="btn btn-xs btn-secondary" onclick="addFile_go();" type="button"  style="background-color:#82BBD8;border: 1px solid #82BBD8">
						<i class="fas fa-file"></i>
						&nbsp; 수의계약서 다운로드</button>
						<button class="btn btn-xs btn-secondary" onclick="addFile_go();" type="button"  style="background-color:#82BBD8;border: 1px solid #82BBD8">
						수의계약서 보기</button>
					</div>
					
				</div>
			</div>
		</div>

	</div>
	</script>

	<script type="text/javascript">
		function branch_request_complate(applicationNo){
			
			$.ajax({
			        url:"<%=request.getContextPath()%>/admin/branchapplication/applicationAreaComplateData",
			        type:"get",
			        data: {
			        	applicationNo:applicationNo
			        },
			        dataType:"json",
			        success:function(data){
			        	let source = $("#branchRequestComplate").html(); 
			        	let template = Handlebars.compile(source);
			        	let application=data.branchApplication;
			       		let branch= data.branch;
			        	console.log(data)
			        	//application.set
			        	let handleData={
			        		application:application,
			        		branch:branch
			        	}
			        	console.log(handleData)
			        	let html = template(handleData);
			        	
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
		</script>

	