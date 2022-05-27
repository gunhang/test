<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<head>
<style>
.pp {
	padding: 12px;
	box-sizing: border-box;
	overflow-y: scroll;
	font-size: 1.0rem;
	background: #EBF3FC;
	margin-bottom: 16px;
}
</style>
</head>
<body>
	<div style="width: 70%; margin: auto;">
		<section class="content-header"
			style="margin-bottom: 0px; padding-bottom: 0px">
			<div class="container-fluid">
				<div class="row mb-2">
					<div class="col-sm-6">
						<h1>지점 신청</h1>
					</div>
				</div>
			</div>
			<hr>
		</section>
		<div class="card">
			<div class="card-body" style="height: 500px; padding: 10px">
				<div class="pp">
					개인정보 수집 이용에 관한 사항(필수)<br> 회사는 개설 상담을 위해 아래와 같은 개인정보를 수집하고
					있습니다.<br> 1. 수집항목 : (필수) 이름, 연락처, 개설희망지역, 개설희망시기, 투자가능비용, 빨래방
					이용경험, 건물소유/임대<br> 2. 이용목적 : 개설 문의 상담, 기타 관련 문의사항 답변, 개설 관련 정보
					제공 및 관련 안내 문자(SMS) 발송<br> 3. 보유기간 : 3년간 보관<br> ＊위의 개인정보
					수집, 이용에 대한 동의를 거부할 권리가 있습니다. 그러나 동의를 거부하실 경우 상담이 불가합니다.
				</div>
				<div class="">
					<label> &nbsp;<input type="checkbox" name="" 
						id="check"> <span class="">&nbsp;개인정보 수집 및 이용에 동의합니다.</span>
					</label>
				</div>
				<div class="row">
					<div class=" col-6" style="padding-left: 10%;">
						<div style="margin-top: 16px">
							<label for="email" class="col-mb-3"> <span style="color: red; font-weight: bold;">*</span>이름</label>
						</div>
						<div class="input-group mb-3 form-group">
							<input type="text" class="col-lg-9 form-control" id="name" name="name" placeholder="이름">
						</div>

						<div style="margin-top: 16px">
							<label for="phone" class="col-mb-5"> <span style="color: red; font-weight: bold;">*</span>연락처</label> <span class="sp"></span>
						</div>
						<div class="input-group mb-3 form-group">
							<input placeholder="'-'없이  번호만 기재해주세요" pattern="010[0-9]{8}" name="phone" id="phone" class="col-lg-7 form-control" type="text">
							<div class="input-group-append">
								<button type="button" onclick="phone_verification();" class="btn btn-secondary" style="background-color: #82BBD8; border: 1px solid #82BBD8">인증</button>
							</div>
						</div>

						<div class="form-group verificationCode" style="display: none;">
							<span style="color: red; font-weight: bold;">*</span> <label
								for="addr">인증번호</label>
							<div class="input-group" style="padding-top: 10px;">
								<input type="text" class="form-control col-7" id="Code"
									placeholder="인증번호">
								<div class="input-group-append">
									<button type="button" onclick="verificationCodeCheck();" class="btn btn-secondary" style="background-color: #82BBD8; border: 1px solid #82BBD8">인증</button>
								</div>
								<div id="timeLimit" style="position: absolute; padding: 9px; margin-left: 140px; color: gray; font-size: 0.9rem; z-index: 10"></div>
							</div>
						</div>
					</div>
					<div class="col-6" style="padding-right: 10%;">

						<div style="margin-top: 16px">
							<label for="email" class="col-mb-3"> <span style="color: red; font-weight: bold;">*</span>Email
							</label>
						</div>
						<div class="input-group mb-3 form-group">
							<input type="email" class="col-lg-9 form-control" id="email" name="email" placeholder="Email">
						</div>


						<div class="card-header"
							style="border-bottom: 0px; padding-left: 0px; padding-bottom: 5px; padding-top: 3px;">
							<span style="margin-top: 0px"> <label for="email"
								class="col-mb-3"> <span style="color: red; font-weight: bold;">*</span>임대차 계약서 첨부
							</label>
							</span>
							<h5 style="display: inline;"></h5>
							<button class="btn btn-xs btn-secondary" onclick="addFile_go();"
								type="button" id="addFileBtn"
								style="background-color: #82BBD8; border: 1px solid #82BBD8">
								<i class="fas fa-file"></i>&nbsp; 양식 다운로드
							</button>
						</div>
						<div class=" fileInput" style="">
							<div class="inputRow" data-no="0">
								<label for="inputFile" data-no="0"
									class="btn btn-secondary btn-sm input-group-addon"
									style="background-color: #82BBD8; border: 1px solid #82BBD8"
									onclick="justPressed(this)">파일선택</label> <input
									id="inputFileName" type="text" name="tempPicture" data-no="0"
									readonly style="width: 192px;">
							</div>
						</div>
						<div class="overlay" style="display: none;">
							<i class="fas fa-2x fa-spinner fa-spin"></i>
						</div>
						<div style="margin-top: 45px">
							<button class="btn btn-md btn-secondary col-9"
								style="margin: auto; background-color: #82BBD8; border: 1px solid #82BBD8"
								onclick="regist()">신청하기</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	
	<div class="hiddenInput">
		<input type="hidden" id="uuidNm" name="uuidNm">
	</div>
	<form role="imageForm" method="post" enctype="multipart/form-data">
		<input id="inputFile" name="pictureFile" type="file" class="form-controll" accept="hwp, pdf, PDF" style="display: none;" />
	</form>
	<!-- jQuery -->
  	<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
	<!-- 알림 sweetalert2 -->
	<script
		src="<%=request.getContextPath()%>/resources/bootstrap/plugins/sweetalert2/sweetalert2.all.min.js"></script>


<!-- 파일다운로드 -->
<script>
const dataSetting = function(){
	   let dataArr = [];
	   let dataObj = {};
	   
	   for(let i = 0; i < checkMark.length; i++){
	      if(checkMark[i].checked){
	         dataObj = {"unityatchmnflno" : uniflno.value,
	                  "ano" : checkMark[i].value}
	         dataArr.push(dataObj);   
	      }      
	   }
	   
	   return dataArr;
	};

	const sendDownloadFile = function(dataArr){
	   let data = dataSetting(dataArr);
	   let downUrl = "restDownload";
	   if(data.length > 1){
	      downUrl = "zipDownload"; 
	   }
	   
	   const xhr = new XMLHttpRequest();
	   xhr.onreadystatechange = function(){
	       if (this.readyState == 4 && this.status == 200){
	         
	          let filename = "";
	          let disposition = xhr.getResponseHeader('Content-Disposition');
	            if (disposition && disposition.indexOf('attachment') !== -1) {
	                let filenameRegex = /filename[^;=\n]*=((['"]).*?\2|[^;\n]*)/;
	                let matches = filenameRegex.exec(disposition);
	                if (matches != null && matches[1]) filename = matches[1].replace(/['"]/g, '');
	            }
	         
	           let a = document.createElement("a");
	           let url = URL.createObjectURL(this.response)
	           a.href = url;
	           a.download = filename;
	           document.body.appendChild(a);
	           a.click();
	           window.URL.revokeObjectURL(url);
	       }
	   }
	   xhr.open('POST', downUrl);
	   xhr.setRequestHeader('Content-type','application/json');
	   xhr.responseType = 'blob'; 
	   xhr.send(JSON.stringify(data));
	   
	}

</script>


<script>
var FileNm = document.getElementById("uuidNm");
var fn = FileNm.value;
var dataNum = 1;

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
	//파일 확장자 pdf 확인
	if(!(fileFormat == "pdf" || fileFormat == "hwp" || fileFormat == "PDF")){
		alert("계약서 파일은 pdf 형식만 가능합니다.");
		spinner.style.display = 'none';
		return;
	}
	// 파일 용량 체크
	if(picture.files[0].size>1024*1024*5){
		alert("첨부파일 용량은 5MB 이하만 가능합니다.");
		spinner.style.display = 'none';
		return;
	};
	
	
	if(findByAttributeValue("data-uploadedno",justPressedLabel,"input")){
		deleteUploadFile(justPressedLabel);
	}
	 
	let formData = new FormData(imageForm);
	
	 $.ajax({
		url: "<%=request.getContextPath()%>/branchapplication/picture",
		data:formData,
		type:'POST',
		processData:false,
		contentType:false,
		success:function(data){
			
			//저장된 파일명 input태그만들어 저장
			const hiddenInput = document.querySelector(".hiddenInput");
			hiddenInput.append(createHiddenInputNode(data));
			
			console.log(data+"임대계약서가 첨부 되었습니다.");
			inputFileName.value = picture.files[0].name;
			fn = data.fileName;
			
			spinner.style.display = 'none';
			
			
		},
		error:function(error){
			//alert("현재 사진 업로드가 불가합니다. \n관리자에게 연락바랍니다.");
			AjaxErrorSecurityRedirectHandler(error.status);
		}
	});

});
</script>




	<script>

var nm = document.getElementById("name");
var phone= document.getElementById('phone');
var email = document.getElementById('email');
var check = document.getElementById('check');
	
	function regist(){
		if(check.checked) {
			Swal.fire({
	            title: '지점 등록 신청 하시겠습니까?',
	            icon : 'warning' ,
	            showCancelButton: true,
	            confirmButtonColor: '#3085d6',
	            cancelButtonColor: '#d33',
	            confirmButtonText: '승인',
	            cancelButtonText: '취소',
	            reverseButtons: true, // 버튼 순서 거꾸로
	          }).then((result) => {
	            if (result.isConfirmed) {
					$.ajax({
						url : '<%=request.getContextPath()%>/branchapplication/registform',
						data : {
							'applicateName' : nm.value,
							'phone' : phone.value,
							'email' : email.value,
							'fileNm' : fn
							
						},
						type : 'post',
						success : function() {
							Swal.fire({
								icon: 'success', // 여기다가 아이콘 종류를 쓰면 됩니다.
								title: '지점 등록 신청이 완료되었습니다.',
							});
							setTimeout(function(){location.href='<%=request.getContextPath()%>/branchapplication/regist';},1000);
						},
						error : function(error) {
							//AjaxErrorSecurityRedirectHandler(error.status);
							Swal.fire({
								icon: 'warning', // 여기다가 아이콘 종류를 쓰면 됩니다.
								title: '시스템 오류로 반려 할 수 없습니다.'
							});
						}
					});
				}
			})
		}else{
			Swal.fire({
				icon: 'warning', // 여기다가 아이콘 종류를 쓰면 됩니다.
				title: '개인정보 수집 및 이용에 동의하세요',
			});
		}
	}
</script>



<!-- 문자인증 -->
<script> 
 const certify_ajax = function (phoneNumber){
    const v_ajax = new XMLHttpRequest();
       v_ajax.open("POST","<%=request.getContextPath()%>/order/certifyPhoneNum",true);
     v_ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8');
     v_ajax.send('phoneNumber=' + encodeURIComponent(phoneNumber));
     v_ajax.onreadystatechange = function(){
         if (v_ajax.readyState === XMLHttpRequest.DONE) {
            if (v_ajax.status === 200) {
               const response = JSON.parse(v_ajax.responseText);
                responseCode = Number(response.randomNum);
                Toast.fire({
                  icon: 'success',
                   title: '인증번호가 발송되었습니다.'
               });
            } else {
             }
         }
     }
 };
   
</script>
<script>
let isRunning = false;

const Toast = Swal.mixin({
   toast: true,
   position: 'center',
   showConfirmButton: false,
   timer: 1500,
   timerProgressBar: false,
   didOpen: (toast) => {
     toast.addEventListener('mouseenter', Swal.stopTimer);
     toast.addEventListener('mouseleave', Swal.resumeTimer);
   }
 });




  function phone_verification() {
     
      let tel = document.getElementById('phone').value;
      
      let regPhone = /^01([0|1|6|7|8|9])([0-9]{3,4})([0-9]{4})$/;
      
      if (regPhone.test(tel) !== true) {
      
          Toast.fire({
            icon: 'warning',
            title: '휴대폰번호를 다시 확인해주세요.'
          });
      }else{
         document.querySelector('.verificationCode').style.display = 'block';  
         certify_ajax(tel);
         const timeLimit = document.getElementById("timeLimit");
         startTimer(180,timeLimit);
      }
  }
 
  function startTimer(count, display) {
      
     let minutes, seconds;
      let timer = setInterval(function () {
      minutes = parseInt(count / 60, 10);
      seconds = parseInt(count % 60, 10);

      minutes = minutes < 10 ? "0" + minutes : minutes;
      seconds = seconds < 10 ? "0" + seconds : seconds;
      display.innerHTML = minutes + ":" + seconds;
     
      // 타이머 끝
      if (--count < 0) {
        clearInterval(timer);
        Toast.fire({
            icon: 'warning',
            title: '인증시간이 초과되었습니다.\n재인증 해주세요.'
          });
        isRunning = false;
      }
  }, 1000);
       isRunning = true;
}
  
  function verificationCodeCheck() {
     let codeInput = document.querySelector('#Code');
     
     if(isRunning && responseCode !== 0){
           if(codeInput.value == responseCode){
              let phone = document.querySelector('#phone');
              
              phone.setAttribute('value',phone.value);
              console.log('phone.value',phone.value);
              phonchk = true;
              Toast.fire({
                    icon: 'success',
                    title: '인증되었습니다.'
              });
              setTimeout(function(){document.querySelector('.verificationCode').style.display = 'none';  },1000);
              
           }else{
              Toast.fire({
                 icon: 'warning',
                 title: '인증번호가 틀렸습니다.'
               });
           }
        }
  }
  
  
  
  function phoneFomatter(num,type){
       let formatNum = '';

       if(num.length==11){
           if(type==0){
               formatNum = num.replace(/(\d{3})(\d{4})(\d{4})/, '$1-****-$3');
           }else{
               formatNum = num.replace(/(\d{3})(\d{4})(\d{4})/, '$1-$2-$3');
           }
       }else if(num.length==8){
           formatNum = num.replace(/(\d{4})(\d{4})/, '$1-$2');
       }else{
           if(num.indexOf('02')==0){
               if(type==0){
                   formatNum = num.replace(/(\d{2})(\d{4})(\d{4})/, '$1-****-$3');
               }else{
                   formatNum = num.replace(/(\d{2})(\d{4})(\d{4})/, '$1-$2-$3');
               }
           }else{
               if(type==0){
                   formatNum = num.replace(/(\d{3})(\d{3})(\d{4})/, '$1-***-$3');
               }else{
                   formatNum = num.replace(/(\d{3})(\d{3})(\d{4})/, '$1-$2-$3');
               }
           }
       }
       return formatNum;
   }

</script>

</body>
