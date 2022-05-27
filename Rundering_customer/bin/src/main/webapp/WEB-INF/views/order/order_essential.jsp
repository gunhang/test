<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/bootstrap/plugins/sweetalert2/sweetalert2.min.css" />  
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/bootstrap/plugins/icheck-bootstrap/icheck-bootstrap.min.css" />

</head>


<body>

	<div style="width: 60%; display: flex; flex-direction: column; margin: auto;max-width: 758px;">

		<section class="content-header">
			<div class="container-fluid">
				<div class="row mb-2">
					<div class="col-sm-6">
						<h1>세탁주문</h1>
					</div>
					<div class="col-sm-6">
						<ol class="breadcrumb float-sm-right">
							<li class="breadcrumb-item active">세탁주문</li>
							<li class="breadcrumb-item"><a href="#">Home</a></li>
						</ol>
					</div>
				</div>
			</div>
		</section>

		<div class="card" style="box-shadow: none;">
			<div class="card-header">
				<h3 class="" style="text-align: center; font-size: 1.3rem; font-weight: 400;">주소지 정보 확인</h3>
			</div>
			
			

			<form role="form" class="form-horizontal form" action="<%=request.getContextPath() %>/order/detail" method="post" onsubmit="return checkForm();">
				
				<p class="mt-3" style="text-align: center;">수거와 배달을 받을 주소지와 연락처를 입력해주세요.</p>
				
			<div class="card-body col-6" style="margin: auto; margin-top: 10px;">
			
					<div class="form-group">
						<label for="addr">주소지</label>
						<div class="input-group" style="padding-top: 10px;">
							<div class="input-group" style="padding-right: 0;">
								<div class="icheck-primary pt-3 pb-3 pl-1" style="width: 100%;border-top: 1px solid rgba(0,0,0,.125);border-bottom: 1px solid rgba(0,0,0,.125);">
									<input type="radio" value="${defaultMemberAddress.addressNo}" onchange="newAddr(this);" name="addressNo" id="${defaultMemberAddress.addressNo}" checked> <label for="${defaultMemberAddress.addressNo}" style="font-weight: 500;">${defaultMemberAddress.add1}&nbsp;&nbsp;${defaultMemberAddress.add2}</label>
								</div>
																
								<c:if test="${!empty memberAddressList }">
									<c:forEach items="${memberAddressList }" var="memberAddress">
										<c:if test="${memberAddress.defaultYn eq 'N' }">
											<div class="icheck-primary pt-1 pb-3 pl-1" style="width: 100%;border-bottom: 1px solid rgba(0,0,0,.125);">
												<input type="radio" value="${memberAddress.addressNo}" onchange="newAddr(this);" name="addressNo" id="${memberAddress.addressNo}"> <label for="${memberAddress.addressNo}" style="font-weight: 500;">${memberAddress.add1}&nbsp;&nbsp;${memberAddress.add2}</label>
											</div>
										</c:if>
									</c:forEach>
								</c:if>
								
								<div class="icheck-primary pt-1 pb-3 pl-1" style="width: 100%;border-bottom: 1px solid rgba(0,0,0,.125);">
										<input type="radio" value="0"  name="addressNo" id="newAddrInputBtn" onchange="newAddr(this);"> <label for="newAddrInputBtn" style="font-weight: 500;">새로운 주소지 등록</label>
								</div>
								<div class="newAddrInput pt-2" style="display: none;width: 100%;">
									<div class="input-group mb-3">
										<input type="text" class="form-control" id="zip" name="zip" placeholder="우편번호" value="">
										<div class="input-group-append">
											<button type="button" id="modalBtn" class="btn btn-primary" onclick="findZip();">주소검색</button>
										</div>
									</div>
									<div class="input-group mb-3">
										<input type="text" class="form-control" id="add1" name="add1" placeholder="기본주소" value="">
									</div>
									<div class="input-group mb-3">
										<input type="text" class="form-control" id="add2" name="add2" placeholder="상세주소" value="">
									</div>
									<div class="row">
										<div class="pl-2">
											<div class="icheck-primary">
												<input type="checkbox" name="setDefaultAddr" value="Y" id="setDefaultAddr"> <label
													for="setDefaultAddr" style="font-weight: 500;"> 기본주소지로 등록
												</label>
											</div>
										</div>


									</div>
								</div>
							</div>
						</div>
					</div>
			
				<label for="phone" style="display: block;">연락처</label>
				<p class="h4 mt-2 mb-3 showPhone" style="display: inline-block; width: 200px;">${phone}</p>
				<button type="button" onclick="form_phone_show();" class="btn btn-outline-secondary phoneChenge" style="margin-top: 0.5rem;margin-bottom: 1rem; margin-left: 80px;">변경</button>
				
				<input type="hidden" id="contactNumber" name="contactNumber" value="${loginUser.phone}" >
				
					<div class="form-group newphone" style="display: none;">
						<div class="input-group">
							<div class="input-group" style="padding-right: 0;">
								<input type="tel" class="form-control" id="tel" placeholder="휴대폰번호 숫자만 입력하세요.">
								<div class="input-group-append">
									<button type="button" onclick="tel_verification();" class="btn btn-primary">인증</button>
								</div>
							</div>
						</div>
					</div>
					<div class="form-group verificationCode" style="display: none;">
						<label for="addr">인증번호</label>
						<div class="input-group" style="padding-top: 10px;">
							<input type="text" class="form-control" id="Code" placeholder="인증번호">
							<button type="button" onclick="verificationCodeCheck()" style="margin-top: 10px;" class="btn btn-outline-primary btn-block">연락처 인증하기</button>
							<div id="timeLimit" style="position: absolute;padding: 9px;margin-left: 279px;color: gray;font-size: 0.9rem; z-index: 10"></div>
						</div>
					</div>
				<button type="submit" style="margin-top: 10px;" class="btn btn-primary btn-block">다음으로</button>
				</div>
				
				
			</form>
		</div>
	</div>

	<!-- 알림 sweetalert2 -->
	<script src="<%=request.getContextPath() %>/resources/bootstrap/plugins/sweetalert2/sweetalert2.all.min.js"></script>

	<!-- 주소api -->
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<script>
	const certify_ajax = function (phoneNumber){
		const v_ajax = new XMLHttpRequest();
	    v_ajax.open("POST","<%=request.getContextPath() %>/order/certifyPhoneNum",true);
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
		            	//
		            }
		     }
	    }
	};
	
</script>


<script>
	window.onload = function(){
		 let responseCode = 0;
	}

	function findZip() {
		new daum.Postcode({
			oncomplete : function(data) {
				// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
				var addr = ''; // 주소 변수

				//사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
				if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
					addr = data.roadAddress;
				} else { // 사용자가 지번 주소를 선택했을 경우(J)
					addr = data.jibunAddress;
				}

				// 우편번호와 주소 정보를 해당 필드에 넣는다.
				document.getElementById('zip').value = data.zonecode;
				document.getElementById("add1").value = addr;
				// 커서를 상세주소 필드로 이동한다.
				document.getElementById("add2").focus();
			}
		}).open();
	}
	
	function newAddr(radioBtn) {
		const newAddrInput = document.querySelector('.newAddrInput');	
		if(radioBtn.value == 0){
			newAddrInput.style.display = 'block';
		}else{
			newAddrInput.style.display = 'none';
		}
	}
	
	
</script>

<script>

	const Toast = Swal.mixin({
	    toast: true,
	    position: 'center',
	    showConfirmButton: false,
	    timer: 1500,
	    timerProgressBar: false,
	    didOpen: (toast) => {
	      toast.addEventListener('mouseenter', Swal.stopTimer)
	      toast.addEventListener('mouseleave', Swal.resumeTimer)
	    }
	  })

  function form_phone_show(){
	  
	  let form_phone = document.querySelector('.newphone');	
	  let input_phone = document.querySelector('.newphone input');	
	  let form_code = document.querySelector('.verificationCode');	
	  let input_code = document.querySelector('.verificationCode input');	
	  let show_btn = document.querySelector('.phoneChenge');
	  
	  if(form_phone.style.display == 'block'){
		  show_btn.innerText = '변경';
		  form_phone.style.display = 'none';
		  form_code.style.display = 'none';
		  input_phone.value = '';
		  input_code.value = '';
		  
	  }else{
		  form_phone.style.display = 'block';
		  show_btn.innerText = '취소';
	  }
  }


  function tel_verification() {
	  
      let tel = document.getElementById('tel').value;
      
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
  let isRunning = false;
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
		     	let tel = document.querySelector('.newphone input');
		     	let contactNumber = document.querySelector('#contactNumber');
		     	
		     	contactNumber.setAttribute('value',tel.value);
		     	console.log('tel.value',tel.value);
		     	let showPhone = document.querySelector('.showPhone');
		     	showPhone.innerText = phoneFomatter(tel.value);
		     	
	  			Toast.fire({
	     		      icon: 'success',
	     		      title: '연락처 변경이 완료되었습니다.'
	     		    });
	  			
	  			form_phone_show();
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

	function checkForm(){
		
	}

  
</script>



</body>
