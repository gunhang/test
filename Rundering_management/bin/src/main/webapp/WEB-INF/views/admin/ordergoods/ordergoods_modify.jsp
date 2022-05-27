<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<section class="content register-page">
		<div class="">
			<div class="login-logo">
				<a href=""><b>물품 수정</b></a>
			</div>
			<!-- form start -->
			<div class="card">
				<div class="card-body">
					<form role="modifyForm" class="form-horizontal" action="modify"
						method="post" name="modifyForm" enctype="multipart/form-data">
						<div class="row">
							<input type="hidden" name="oldPicture" value="" /> 
							<input type="file" id="inputFile" onchange="changePicture_go();"
								name="pictureFile" style="display: none" />
							<div class="input-group col-md-12">
								<div class="col-md-12" style="text-align: center;">
									<div class="goodsPicture" data-id="${laundryArticles.atchFileNo }"
										id="pictureView"
										style="border: 1px solid green; height: 140px; width: 140px; margin: 0 auto; margin-bottom: 5px;"></div>
									<div class="input-group input-group-sm">
										<label for="inputFile"
											class=" btn btn-warning btn-sm btn-flat input-group-addon">사진변경</label>
										<input id="inputFileName" class="form-control" type="text"
											name="tempPicture" disabled value="${laundryArticles.picture }" /> <input
											id="picture" class="form-control" type="hidden"
											name="uploadPicture" />
									</div>
								</div>
							</div>
						</div>
						<div class="form-group row">
							<label for="articlesName" class="col-sm-4"> <span
								style="color: red; font-weight: bold;">*</span>상품명
							</label>
							<div class="col-sm-8 input-group-sm">
								<input class="form-control" name="articlesName" type="text"
									id="articlesName" value="${laundryArticles.articlesName }">
							</div>
						</div>
						<div class="form-group row">
							<label for="articlesCode" class="col-sm-4"> <span
								style="color: red; font-weight: bold;">*</span>상품코드
							</label>
							<div class="col-sm-8 input-group-sm">
								<input class="form-control" name="articlesCode" type="text"
									id="articlesCode" value="${laundryArticles.articlesCode }">
							</div>
						</div>

						<div class="form-group row">
							<!-- sort num -->
							<label for="clcode" class="col-sm-4"> <span
								style="color: red; font-weight: bold;">*</span>상품분류
							</label>
							<div class="col-sm-8 input-group-sm">
								<select id="clcode" name="clcode" class="form-control">
									<option value="${laundryArticles.clcode }"
										${laundryArticles.clcode eq 'B' ? 'selected':'' }>가루세제</option>
									<option value="${laundryArticles.clcode }"
										${laundryArticles.clcode eq 'C' ? 'selected':'' }>엑체세제</option>
									<option value="${laundryArticles.clcode }"
										${laundryArticles.clcode eq 'D' ? 'selected':'' }>섬유유연제</option>
									<option value="${laundryArticles.clcode }"
										${laundryArticles.clcode eq 'E' ? 'selected':'' }>세탁비누</option>
									<option value="${laundryArticles.clcode }"
										${laundryArticles.clcode eq 'F' ? 'selected':'' }>세탁보조용품</option>
								</select>
							</div>
						</div>

						<div class="form-group row">
							<label for="price" class="col-sm-4"> <span
								style="color: red; font-weight: bold;">*</span>판매가
							</label>
							<div class="col-sm-8 input-group-sm">
								<input class="form-control" name="price" type="text" id="price"
									value="${laundryArticles.price }">
							</div>
						</div>
						<div class="form-group row">
							<label for="each" class="col-sm-4"> <span
								style="color: red; font-weight: bold;">*</span>단위
							</label>
							<div class="col-sm-5 input-group-sm">
								<input class="form-control" name="each" type="text" id="each"
									style="text-align: right;" value="${orderGoods.each }">
							</div>
							<div class="col-sm-3 input-group-sm">
								<select onchange="list_go(1);" id="perPageNum" name="perPageNum"
									class="form-control">
									<option value="${laundryArticles.each }"
										${laundryArticles.each eq 'n' ? 'selected':'' }>개</option>
									<option value="${laundryArticles.each }"
										${laundryArticles.each eq 'h' ? 'selected':'' }>매</option>
									<option value="${laundryArticles.each }"
										${laundryArticles.each eq 'g' ? 'selected':'' }>g</option>
									<option value="${laundryArticles.each }"
										${laundryArticles.each eq 'kg' ? 'selected':'' }>kg</option>
									<option value="${laundryArticles.each }"
										${laundryArticles.each eq 'ml' ? 'selected':'' }>ml</option>
									<option value="${laundryArticles.each }"
										${laundryArticles.each eq 'l' ? 'selected':'' }>L</option>
								</select>
							</div>
						</div>
						<div class="form-group row">
							<label for="note" class="col-sm-4"> <span
								style="font-weight: bold;">&nbsp;&nbsp;</span>비고
							</label>
							<div class="col-sm-8 input-group-sm">
								<input class="form-control" name="note" type="text" id="note"
									value="${laundryArticles.note }">
							</div>
						</div>

					</form>
					<div class="btn-group float-right">
						<div class="input-group-sm">
							<button type="button" id="sendBtn" class="btn btn-primary btn-sm"
								onclick="modifyPOST_go();">수정</button>
						</div>
						&nbsp;&nbsp;
						<div class="input-group-sm">
							<button class="btn btn-danger btn-sm" id="sendBtn" type="button"
								onclick="history.go(-1);">목록</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<form role="form">
		<input type="hidden" name="articlesCode"
			value="${laundryArticles.articlesCode }" />
	</form>
	
	<script>
		window.addEventListener('load', OrderGoodsPictureThumb)
		function OrderGoodsPictureThumb(){
			 for(var target of document.querySelectorAll('.goodsPicture')){	
				 var atchFileNo = target.getAttribute('data-id');
				 target.style.backgroundImage="url('<%=request.getContextPath() %>/admin/ordergoods/getPicture?atchFileNo="+atchFileNo+"')";
				 target.style.backgroundPosition="center";
				 target.style.backgroundRepeat="no-repeat";
				 target.style.backgroundSize="cover";
			}
		}
	</script>
	
	<script>
  	function changePicture_go(){
  		var picture = $('input#inputFile')[0];
  		
  		var fileFormat = picture.value.substr(picture.value.lastIndexOf(".")+1).toUpperCase();
  		
  		//이미지 확장자 jpg 확인
		if(!(fileFormat=="JPG" || fileFormat=="JPEG")){
			alert("이미지는 jpg 형식만 가능합니다.");
			return;
		} 
		//이미지 파일 용량 체크
		if(picture.files[0].size>1024*1024*1){
			alert("사진 용량은 1MB 이하만 가능합니다.");
			return;
		};
		
		document.getElementById('inputFileName').value=picture.files[0].name;
		
		if (picture.files && picture.files[0]) {
 			var reader = new FileReader();
			 
			 reader.onload = function (e) {
		        	//이미지 미리보기	        	
		        	$('div#pictureView')
		        	.css({'background-image':'url('+e.target.result+')',
						  'background-position':'center',
						  'background-size':'cover',
						  'background-repeat':'no-repeat'
		        		});
		        }
		       reader.readAsDataURL(picture.files[0]);
		}
		// 이미지 변경 확인
		$('input[name="uploadPicture"]').val(picture.files[0].name);
  	}
	</script>
	<script>
		function modifyPOST_go() {
			var form = document.modifyForm;
			if (form.articlesName.value == "") {
				alert("상품명은 필수입니다.");
				return;
			}
			if (form.articlesCode.value == "") {
				alert("세탁물품코드는 필수입니다.");
				return;
			}
			if (form.price.value == "") {
				alert("물품가격은 필수입니다.");
				return;
			}
			//alert("modify btn click");
			$("form[role='modifyForm']").submit();
		}
	</script>

</body>