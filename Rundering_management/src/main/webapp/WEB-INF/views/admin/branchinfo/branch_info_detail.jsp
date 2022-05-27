<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<body>
<h2 class="pl-3 m-3">지점상세정보<button type="button" id="sendBtn" class="btn btn-secondary btn-m float-right"
		onclick="CloseWindow();">목록</button></h2>
	<div class="card ml-2 mr-2">
		<div class="card-body">
			<span class="input-group-sm input-group-append float-right" style="height:36px;">
				<input type="text" class="inputValue" data-code="${branchDetail.branchCode }"  data-each="${branchDetail.branchLndrpcrymslmcoqy}" disabled value="${branchDetail.branchLndrpcrymslmcoqy }" style="width: 118px; text-align: right;">
				<button class="btn btn-sm btn-warning modifyBtn"
						onclick="autoModify()">수정</button>
				 <span
					class="btn-group-vertical modifySpan"
					style="width: 18px; display: none">
						<button type="button" class="btn btn-sm btn-default p-0"
							style="height: 18px;" onclick="plusQuantity(this)">+</button>
						<button type="button" class="btn btn-sm btn-default p-0 m-0"
							style="height: 18px;" onclick="minusQuantity(this)">-</button>
				</span>
				<button class="btn btn-sm btn-primary saveBtn"
					onclick="autoSaveCount()" style="display: none">저장</button>
			</span>
		</div>
	</div>
	<div class="row col-12 m-0">
		<div class="card col-6">
			<div class="card-header">
				<h3 class="card-title">지점장 정보</h3>
			</div>
			<div class="card-body">
				<div class="form-group">
					<label for="exampleInputName">이름</label> <input type="text"
						class="form-control" id="exampleInputName" placeholder="이름"
						value="${branchDetail.name }" name="name" readonly>
				</div>
				<div class="form-group">
					<label for="exampleInputPhone">전화번호</label> <input type="text"
						class="form-control" id="exampleInputPhone" placeholder="전화번호"
						value="${branchDetail.phone }" name="phone" disabled value="">
				</div>
				<div class="form-group">
					<label for="exampleInputEmail1">E-mail</label> <input type="email"
						class="form-control" id="exampleInputEmail1" placeholder="이메일"
						value="${branchDetail.email }" name="email" disabled>
				</div>
			<br><hr>
			<table>
				<thead>
					<tr style="text-align: center;">
						<th colspan="2">계약서</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td style="width: 280px;">심사내역서</td>
						<td><a class="btn p-0" href="상대경로/test.pdf" download=""><button
									type="button" class="btn btn-default">file.pdf</button></a>

						</td>
					</tr>
					<tr>
						<td>수의계약서</td>
						<td><a class="btn p-0" href="상대경로/test.pdf" download=""><button
									type="button" class="btn btn-default">file.pdf</button></a>

						</td>
					</tr>
					<tr>
						<td>임대차계약서</td>
						<td><a class="btn p-0" href="상대경로/test.pdf" download=""><button
									type="button" class="btn btn-default">file.pdf</button></a>

						</td>
					</tr>
				</tbody>
			</table>
			</div>
		</div>

		<div class="card col-6">
			<div class="card-header">
				<h3 class="card-title">지점등록정보</h3>
			</div>

			<div class="card-body">
				<div class="form-group">
					<label for="exampleInputName">매장명</label> <input type="text"
						class="form-control" value="${branchDetail.branchName }"
						name="branchName" readonly>
				</div>
				<div class="form-group">
					<label for="exampleNumber">지점번호</label> <input type="number"
						class="form-control" value="${branchDetail.branchCode }"
						name="branchCode" readonly>
				</div>
				<div class="form-group">
					<label for="exampleInputAddress">주소</label> <input type="text"
						class="form-control" value="${branchDetail.addr }" name="addr"
						readonly>
				</div>
				<div class="form-group">
					<label for="exampleInputPhone">전화번호</label> <input type="text"
						class="form-control" value="${branchDetail.branchContact }"
						name="branchContact" readonly>
				</div>
				<div class="form-group">
					<label for="exampleInputInfo">추가정보</label> <input type="text"
						class="form-control" id="exampleInputInfo" disabled>
				</div>
				<c:if test="${branchDetail.branchCode eq '000000' }">
					<div style="clear: both;" class="btn-group float-right">

						<button type="button" id="sendBtn" class="btn btn-primary btn-sm ">
							확인</button>
					</div>
				</c:if>
			</div>
		</div>
	</div>
<script>
function plusQuantity(){
	
	let input =event.target.parentNode.parentNode.querySelectorAll('.inputValue')[0]
	let intValue= parseInt(input.value)
	
	input.value=intValue+10;
	if(input.value>999){
		input.value=999;
	}
}
function minusQuantity(){
	let input =event.target.parentNode.parentNode.querySelectorAll('.inputValue')[0]
	let intValue= parseInt(input.value)
	let result=intValue-10;
	if(result<0){
		result=0;
	}
	input.value=result;
}

function autoModify(){
	let input = event.target.parentNode.querySelectorAll(".inputValue")[0]
	
	input.style.width="100px";
	
	input.value=input.value.split("(")[0]
	event.target.parentNode.querySelectorAll(".modifySpan")[0].style.display="inline-flex";
	event.target.parentNode.querySelectorAll(".saveBtn")[0].style.display="inline-block";
	event.target.parentNode.querySelectorAll(".modifyBtn")[0].style.display="none";
	
}

function autoSaveCount(){
	let input = event.target.parentNode.querySelectorAll(".inputValue")[0]
	input.style.width="118px";
	
	let modifySpan=event.target.parentNode.querySelectorAll(".modifySpan")[0];
	let saveBtn=event.target.parentNode.querySelectorAll(".saveBtn")[0];
	let modifyBtn= event.target.parentNode.querySelectorAll(".modifyBtn")[0];
	
	let code = input.dataset.code;
	let each = input.dataset.each;
	
	$.ajax({
		url : '<%=request.getContextPath()%>/admin/branchinfo/autosavepoint',
		type : 'post',
		data:{
			branchCode:code,
			branchLndrpcrymslmcoqy:input.value
		},
		success : function(data) {
			modifySpan.style.display="none";
			saveBtn.style.display="none";
			modifyBtn.style.display="inline-block";
			
		},
		error : function(error) {
			AjaxErrorSecurityRedirectHandler(error.status);
		}
	});
}

</script>

</body>



