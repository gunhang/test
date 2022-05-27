<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="orderList" value="${dataMap.orderList }" />

<head>

<style >
.inputRow {
	margin-left: 15px;
	display: inline-block;
}
</style>


<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/bootstrap/plugins/summernote/summernote.min.css">
</head>

<body>

	<section class="content-header">
		<div class="container-fluid">
			<div class="row mb-2">
				<div class="col-sm-6">
					<h1>문의하기</h1>
				</div>
				<div class="col-sm-6">
					<ol class="breadcrumb float-sm-right">
						<li class="breadcrumb-item"><a href="#">Home</a></li>
						<li class="breadcrumb-item active">문의하기</li>
					</ol>
				</div>
			</div>
		</div>
	</section>

	<div class="card card-secondary card-outline">
		<form role="form" action="regist" method="post" name="registForm">
			<div class="card-body">
				<div class="form-group">
					<label for="question" style="margin-left: 10px; margin-top: 10px; font-size: large;">제목</label>
					<input type="text" name="question" id="question" class="form-control" placeholder="제목을 입력하세요">
				</div>
				<div class="form-group">
					<div class="row">
						<div class=" col">
							<label for="setbukdoorclcode"
								style="margin-left: 10px; margin-top: 10px; font-size: large;">카테고리
								구분</label> <select id="setbukdoorclcode" name="setbukdoorclcode"
								class="form-control">
								<option value="US">이용 문의</option>
								<option value="OR">주문 문의</option>
								<option value="ET">기타 문의</option>
							</select>
						</div>
						<div class=" col">
							<div class="form-group">
								<label for="writer"
									style="margin-left: 10px; margin-top: 10px; font-size: large;">요청자</label>
								<input type="hidden" name="writer" id="writer"
									class="form-control" value="${loginUser.memberNo }"> <input
									type="text" readonly class="form-control"
									value="${loginUser.name }">
							</div>
						</div>
						<div class="col">
							<div class="form-group">
								<label for="secretyn"
									style="margin-left: 10px; margin-top: 10px; font-size: large;">공개여부</label>
								<div class="form-group">
									<div style="margin-left: 10px; margin-top: 10px; font-size: large;">
										<input type="radio" name="secretyn" id="secretyn" value="N" />공개
										&nbsp;&nbsp;
										<input type="radio" name="secretyn" id="secretyn" value="Y" /> 비공개
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="fcontent" style="margin-left: 10px; font-size: large;">내용</label>
					<textarea style="border: none; height: 100px; resize: none;"
						class="textarea" rows="10" cols="20" id="fcontent" name="fcontent"></textarea>
				</div>
				<div class="card">
					<div class="card-header">
						<div class="row">
							<button class="btn btn-xs btn-secondary" onclick="addFile_go();"
								type="button" id="addFileBtn">
								<i class="fas fa-images"></i> Add File
							</button>
							&nbsp;&nbsp;
							<div class="card-footer fileInput p-0">
								<div class="inputRow" data-no="0">
									<label for="inputFile" data-no="0"
										class="btn btn-secondary btn-sm input-group-addon"
										onclick="justPressed(this)">파일선택</label> <input
										id="inputFileName" type="text" name="tempPicture" data-no="0"
										disabled />
									<button onclick="remove_go(0);"
										style="border: 0; outline: 0; padding: 6px; padding-bottom: 5px; margin-left: 5px;"
										class="badge bg-red" type="button">X</button>
								</div>
							</div>
							<div class="overlay" style="display: none;">
								<i class="fas fa-2x fa-spinner fa-spin"></i>
							</div>
						</div>
					</div>
				</div>
				<div class="card">
					<div class="row">
						<div class="col-2">
							<div class="orderno">
								<label for="orderno" style=" margin-left: 20px; margin-top: 10px; margin-bottom: 10px; font-size: large;">고객주문정보</label>
							</div>
						</div>
						<div class="col-4">
							<c:if test="${!empty orderList }">
								<select id="orderno" name="orderno"
									class="form-control" style=" margin-top: 3px;">
									<c:forEach var="order" items="${orderList }" varStatus="i">
										<option value="${order.orderno }">${order.orderno }</option>
									</c:forEach>	
								</select>
							</c:if>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>

	<div class="card-footer">
		<div class="float-right">
			<button onclick="history.go(-1)" class="btn btn-warning">뒤로가기</button>
			<button onclick="regist_go()" type="submit" id="registBtn"
				class="btn btn-primary">요청하기</button>
		</div>
	</div>
	
	<form role="imageForm" method="post" enctype="multipart/form-data">
			<input id="inputFile" name="pictureFile" type="file" class="form-controll" accept="image/jpeg, image/png, image/jpg" style="display: none;" />
		</form>
	
	
	<!-- jQuery -->
  	<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
	<!-- iamport.payment.js -->
	<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>

	<script>
	window.onload=function(){
		summernote_go($('textarea[name="fcontent"]'),'<%=request.getContextPath()%>');
	}
	</script>

	<script>
		function regist_go() {
			
			let files = $('input[name="tempPicture"]');
			for(let file of files){
				console.log(file.name + " : "+ file.value);
			}
			//payment_go();
			
			
			var form = document.registForm;
			if (form.question.value == "") {
				alert("제목은 필수입니다.");
				return;
			}
			if (form.secretyn.value == "") {
				alert("공개여부를 선택하세요.");
				return;
			}
			alert("등록되었습니다.");
			form.submit();
		}
	</script>
	
	<script>
	var dataNum = 1;

	function addFile_go(){
	   
	   if($('input[name="tempPicture"]').length >= 3){
	      alert("사진 첨부는 3개까지만 가능합니다.");
	      return;
	   }
	   
	   var div = $("<div>").addClass("inputRow").attr("data-no", dataNum);
	   
	   div.append("<label for='inputFile' data-no="+dataNum+" class='btn btn-secondary btn-sm input-group-addon' onclick='justPressed(this)''>파일선택</label>")
	   .append("<input id='inputFileName' type='text' name='tempPicture' data-no="+dataNum+" style='margin-left: 4px;' disabled/>")
	   .append("<button onclick='remove_go("+dataNum+");' style='border:0; outline:0;padding: 6px;padding-bottom: 5px;margin-left: 6px;' class='badge bg-red' type='button'>X</button>");
	   
	   $('.fileInput').append(div);
	   dataNum++;
	}

	function remove_go(dataNum){
		
		deleteUploadFile(dataNum);
		
		$('div[data-no="'+dataNum+'"]').remove();
		
	}
	
	function deleteUploadFile(dataNum){
		 let deleteFile = findByAttributeValue("data-uploadedno",dataNum,"input");
		 if(!deleteFile) {
			 return;
		 }
		 let deleteFileName = deleteFile.value;
		 
		 deleteFile.remove();
		 
		 const v_ajax = new XMLHttpRequest();
		    v_ajax.open("POST","<%=request.getContextPath()%>/order/deletePicture",true);
		    v_ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8');
		    v_ajax.send('deleteFileName=' + deleteFileName);
		    v_ajax.onreadystatechange = function(){
		    	 if (v_ajax.readyState === XMLHttpRequest.DONE) {
			            if (v_ajax.status === 200) {
			               //const response = JSON.parse(v_ajax.responseText);
			               console.log(v_ajax.responseText);
			               //console.log(data+"사진이 삭제 되었습니다.");
			            } else {
			            	//AjaxErrorSecurityRedirectHandler(error.status);
			            }
			     }
		    }
	}
</script> 

<script>
	function findByAttributeValue(attribute, value, element_type)    {
		  element_type = element_type || "*";
		  var All = document.getElementsByTagName(element_type);
		  for (var i = 0; i < All.length; i++)       {
		    if (All[i].getAttribute(attribute) == value) { return All[i]; }
		  }
		}
	
	let justPressedLabel = 0;
	
	function justPressed(label){
		justPressedLabel = label.dataset.no;
		console.log("justPressedLabel : "+justPressedLabel);
	}
	
	
	function createHiddenInputNode(saveFileNm) {
		let input = document.createElement('input');
		input.setAttribute('type', 'hidden');
		input.setAttribute('name', 'saveFileNm');
		input.setAttribute('value', saveFileNm);
		input.setAttribute('data-uploadedno', justPressedLabel);
		return input;
		}
	
	$('input[name="pictureFile"]').change(function(){
		
		let spinner = document.querySelector('.overlay');
		spinner.style.display = 'flex';
	
		let imageForm = $('form[role="imageForm"]')[0];
		let picture = $('form[role="imageForm"]').find('[name="pictureFile"]')[0]; 
		let inputFileName = findByAttributeValue("data-no",justPressedLabel,"input");
		
		let fileFormat = picture.value.substr(picture.value.lastIndexOf(".")+1).toUpperCase();
		
		if(picture.value == ""){
			spinner.style.display = 'none';
			return;
		}
		//이미지 확장자 jpg 확인
		if(!(fileFormat == "JPG" || fileFormat == "JPEG" || fileFormat == "PNG")){
			alert("이미지는 jpg/jpeg/png 형식만 가능합니다.");
			spinner.style.display = 'none';
			return;
		}
		// 이미지 파일 용량 체크
		if(picture.files[0].size>1024*1024*5){
			alert("사진 용량은 5MB 이하만 가능합니다.");
			spinner.style.display = 'none';
			return;
		};
		
		
		if(findByAttributeValue("data-uploadedno",justPressedLabel,"input")){
			deleteUploadFile(justPressedLabel);
		}
		 
		let formData = new FormData(imageForm);
		
		 $.ajax({
			url: "<%=request.getContextPath()%>/order/picture",
			data:formData,
			type:'POST',
			processData:false,
			contentType:false,
			success:function(data){
				//저장된 파일명 input태그만들어 저장
				const hiddenInput = document.querySelector(".hiddenInput");
				hiddenInput.append(createHiddenInputNode(data));
				
				console.log(data+"사진이 업로드 되었습니다.");
				inputFileName.value = picture.files[0].name;
				
				spinner.style.display = 'none';
			},
			error:function(error){
				//alert("현재 사진 업로드가 불가합니다. \n관리자에게 연락바랍니다.");
				AjaxErrorSecurityRedirectHandler(error.status);
			}
		});
	});
	</script>
</body>
