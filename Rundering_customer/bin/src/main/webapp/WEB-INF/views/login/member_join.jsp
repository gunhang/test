<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>




<style>
.register-box {margin: auto;}
</style>

<!-- 주소api -->
<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<section class="content-header" style="margin-bottom: 0px;">
	<div class="container-fluid">
		<div class="row mb-2">
			<div class="col-sm-6">
				<h1>회원가입</h1>
			</div>
			<div class="col-sm-6">
				<ol class="breadcrumb float-sm-right">
					<li class="breadcrumb-item active">회원가입</li>
					<li class="breadcrumb-item"><a href="#">Home</a></li>

				</ol>
			</div>
		</div>
	</div>
	<hr />
</section>

<div class="col-6" style="margin:auto;">
	<div class="card-body">
		<h2 class="login-box-msg">join a new membership</h2>
		<form class="form-horizontal" onsubmit="return valid();" method="post">
			<div>
				<label for="id" class="col-mb-3"> <span	style="color: red; font-weight: bold;">*</span>아이디</label>
			</div>
			<div class="input-group mb-3 form-group">
				<input type="text" class="col-lg-4 form-control" placeholder="아이디 입력" name="id" id="id">
				<span class="sp"></span> 
				<span id="rst"></span>
			</div>
			<div>
				<label for="pw1" class="col-mb-3"> <span style="color: red; font-weight: bold;">*</span>비밀번호</label>
			</div>
			<div class="input-group mb-3 form-group">
				<input type="password" id="pw1" class="col-lg-4 form-control pass" placeholder="패스워드 입력" name="password"> 
				<span class="sp"></span>
			</div>
			<div class="input-group mb-3 form-group">
				<input type="password" id="pw2" class="col-lg-4 form-control pass" placeholder="패스워드 재입력"> 
				<span class="sp"></span>
			</div>
			<div>
				<label for="name" class="col-mb-3"> <span style="color: red; font-weight: bold;">*</span>이름</label>
			</div>
			<div class="input-group mb-3 form-group">
				<input type="text" class="col-lg-4 form-control" placeholder="예) 홍 길 동" name="name" id="name">
				<span class="sp"></span>
			</div>
			<div>
				<label for="email" class="col-mb-3"> <span
					style="color: red; font-weight: bold;">*</span>Email</label> 
			</div>
			<div class="input-group mb-3 form-group">
				<input type="email" class="col-lg-6 form-control" id="email" name="email" placeholder="Email" >
				<span class="sp"></span> 
				<span id="rst1"></span>
			</div>
			<div>
				<label for="phone" class="col-mb-3"> <span style="color: red; font-weight: bold;">*</span>연락처</label> <span class="sp"></span>
			</div>
			<div class="input-group mb-3 form-group">
				<input type="text" class="col-lg-6 form-control" id="phone" name="phone" pattern="010[0-9]{8}" placeholder="'-'없이  번호만 기재해주세요">
				<div class="input-group-append">
					<button type="button" onclick="phone_verification();" class="btn btn-primary">인증</button>
				</div>
			</div>
			<div class="form-group verificationCode" style="display: none;">
				<span style="color: red; font-weight: bold;">*</span>
				<label for="addr">인증번호</label>
				<div class="input-group" style="padding-top: 10px;">
					<input type="text" class="form-control col-4" id="Code" placeholder="인증번호">
					<button type="button" onclick="verificationCodeCheck()" style="margin-left: 10px;" class="btn btn-outline-primary btn-block col-2">인증하기</button>
					<div id="timeLimit" style="position: absolute;padding: 9px;margin-left: 140px;color: gray;font-size: 0.9rem; z-index: 10"></div>
				</div>
			</div>
			<div>
				<label for="zip" class="col-mb-3"> <span
					style="color: red; font-weight: bold;">*</span>주소</label>
			</div>
			<div class="input-group mb-3 form-group">
				<input type="text" class="col-lg-6 form-control" id="zip" name="zip" placeholder="우편번호 버튼 Click" readonly >
				<div class="input-group-append">
					<button type="button" id="modalBtn" class="btn btn-info btn-sm"	onclick="findZip();">우편검색</button>
				</div>
			</div>
			<div class="input-group mb-3 form-group">
				<input type="text" class="form-control" id="add1" name="add1" placeholder="기본주소" readonly >
			</div>
			<div class="input-group mb-3 form-group">
				<input type="text" class="form-control" id="add2" name="add2" placeholder="상세주소">
			</div>
			<div class="row">
				<div class="col-8">
				</div>

				<div class="col-4">
					<button type="submit" id="sendBtn"
						class="btn btn-primary btn-block">Join</button>
				</div>

			</div>
		</form>
	</div>
</div>

<!-- 알림 sweetalert2 -->
<script src="<%=request.getContextPath() %>/resources/bootstrap/plugins/sweetalert2/sweetalert2.all.min.js"></script>


<script>

function okProc(ele, str){
	let vs = $(ele).parents('.form-group').find('.sp');
	$(vs).html(str).css('color','green');
}

function noProc(ele, str){
	let vs = $(ele).parents('.form-group').find('.sp');
	$(vs).html(str).css('color','red');
}

</script>

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
	            }
		     }
	    }
	};
	
</script>

<script>

let idchk = false;
let pwchk = false;
let namechk = false;
let passchk = false;
let hpchk = false;
let mailchk = false;
let phonchk = false;

function valid(){
	
	event.preventDefault();	//submit 이벤트를 막아 페이지 리로드를 방지
	if(phonchk){
		if(idchk && pwchk && namechk && passchk && hpchk && mailchk && phonchk){
			let formData = $('form').serialize();
			
			var ta1 = $('input[name="add1"]');
			var ta2 = ta1.val();
			var topArea = ta2.substr(0,2);
			if(topArea == "대전"){
				$.ajax({
					url : '<%=request.getContextPath()%>/join',
					type : 'post',
					data : formData,
					success : function(response){
						if(response.toUpperCase() == "OK"){
							Swal.fire('Rundering 회원가입이 완료되었습니다.', '축하드립니다!', 'success' )
							location.href = "<%=request.getContextPath()%>/login/form";
							} else {
							Swal.fire('공백없이 형식에 맞게 작성해주세요!', 'error' )
							}
						},
						error : function(xhr) {
							alert(xhr.status);
						},
					});
			} else{
				 Swal.fire({
		               title: '해당 주소지는 서비스 지역이 아닙니다. 등록 하시겠습니까?',
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
		                     url : '<%=request.getContextPath()%>/join',
		                     type : 'post',
		                     data : formData,
		                     success : function(response){
		                        if(response.toUpperCase() == "OK"){
		                           Swal.fire('Rundering 회원가입이 완료되었습니다.', '축하드립니다!', 'success' )
		                           location.href = "<%=request.getContextPath()%>/login/form";
		                           } else {
		                              Swal.fire('공백없이 형식에 맞게 작성해주세요!', 'error' )
		                           }
		                        },
		                        error : function(xhr) {
		                           alert(xhr.status);
		                        },
		                     });
		                     
		           		}})
			}
		}
	}else {
		Swal.fire('연락처를 인증해주세요!' );
	}
}	
</script>

<script>
	//id 중복체크 ajax
	function idCheckAjax() {
		let sp = document.querySelectorAll('.sp');
		let rst = document.querySelector('#rst');

		$.ajax({
			url : '<%=request.getContextPath()%>/idCheck',
			data : {
				'id' : $('#id').val()
			},
			type : 'post',
			success : function(result) {
				if (result.toUpperCase() == "DUPLICATED") {
					$('#rst').html("이미 존재하는 ID입니다").css('color', 'red');
					sp[0].style.display = 'none';
					rst.style.display = "inline-block";
				} else {
					$('#rst').html("사용 가능한 ID입니다").css('color', 'green');
					sp[0].style.display = 'none';
					rst.style.display = "inline-block";
				}
			},
			error : function(error) {
				//alert("시스템장애로 가입이 불가합니다.");
				AjaxErrorSecurityRedirectHandler(error.status);
			}
		});
	}
</script>

<script>
	//email 중복체크 ajax
	function emailCheckAjax() {
		let sp = document.querySelectorAll('.sp');
		let rst = document.querySelector('#rst1');

		$.ajax({
			url : '<%=request.getContextPath()%>/emailCheck',
			data : {
				'email' : $('#email').val()
			},
			type : 'post',
			success : function(result) {
				if (result.toUpperCase() == "OK") {
					$('#rst1').html("이미 존재하는 email입니다").css('color', 'red');
					sp[4].style.display = 'none';
					rst.style.display = "inline-block";
				} else {
					$('#rst1').html("사용 가능한 email입니다").css('color', 'green');
					sp[4].style.display = 'none';
					rst.style.display = "inline-block";
				}
			},
			error : function(error) {
				//alert("시스템장애로 가입이 불가합니다.");
				AjaxErrorSecurityRedirectHandler(error.status);
			}
		});
	}
</script>	
	
<script>

window.addEventListener('load',com);
	function com(){
	
	let responseCode = 0;
	
	//keyup 이벤트 : 키를 눌렀다가 떼는 순간
	
	//	아이디 중복검사 
	$('#id').on('keyup', function() {
		
		//유효성검증(validation check) - id
		let idValue = $('#id').val().trim();
		let regId = /^[a-zA-Z][a-zA-Z0-9]{5,11}$/;
		let sp = document.querySelectorAll('.sp');
		let rst = document.querySelector('#rst');

		//패턴체크
		if (regId.test(idValue)) {
			idCheckAjax();
			idchk = true;
		} else if (idValue === "") {
			sp[0].style.display = "inline-block"
			rst.style.display = "none";
			noProc($('#id'), "아이디를 입력하세요");
			idchk = false;
		} else {
			sp[0].style.display = "inline-block"
			rst.style.display = "none";
			noProc($('#id'), "영문,숫자,6~12자리 입력 가능");
			idchk = false;
		}
	});

	//유효성검증 - pass
	$('#pw1').on('keyup',function() {
		let passValue = $('#pw1').val().trim();
		let regPass = /^(?=.*?[a-zA-Z])(?=.*?[0-9])(?=.*?[!@#$%^&*()_+|]).{8,}$/;

		if (regPass.test(passValue)) {
			okProc($('#pw1'), "사용 가능한 패스워드 입니다!");
			pwchk = true;
		} else if (passValue === "") {
			noProc($('#pw1'), "패스워드를 입력하세요");
			pwchk = false;
		} else {
			noProc($('#pw1'), "대/소문자,특수문자,숫자 포함 8자리 이상 입력해야함");
			pwchk = false;
		}
	});

	//비밀번호 일치 여부 확인
	$('.pass').on('keyup', function() {
		let pass1 = $("#pw1").val().trim();
		let pass2 = $("#pw2").val().trim();

		if (pass1 != "" || pass2 != "") {
			if (pass1 == pass2) {
				okProc($('#pw2'), "일치");
				passchk = true;
			} else {
				noProc($('#pw2'), "불일치");
				passchk = false;
			}
		}
	});

	//유효성검증 - name
	$('#name').on('keyup', function() {
		let nameValue = $('#name').val().trim();
		let regName = /^[가-힣]{2,5}$/;

		if (regName.test(nameValue)) {
			okProc($('#name'), "");
			namechk = true;
		} else if (nameValue === "") {
			noProc($('#name'), "이름을 입력하세요");
			namechk = false;
		} else {
			noProc($('#name'), "한글 2~5자리 입력 가능");
			namechk = false;
		}
	});

	//유효성검증 - hp
	$('#phone').on('keyup', function() {
		let hpValue = $('#phone').val().trim();
		let regHp = /^[0-9]{3}[0-9]{3,4}[0-9]{4}$/;

		if (regHp.test(hpValue)) {
			okProc($('#phone'), "");
			hpchk = true;
		} else if (hpValue === "") {
			noProc($('#phone'), "전화번호를 입력하세요");
			hpchk = false;
		} else {
			noProc($('#phone'), "공백 없이 입력하세요");
			hpchk = false;
		}
	});
	
	//유효성검증 - mail
	$('#email').on('keyup', function() {
		//유효성검증(validation check) - email
		let mailValue = $('#email').val().trim();
		let regMail = /^[0-9a-zA-Z]+@[0-9a-zA-Z]+(\.[a-z]+){1,2}$/;
		let sp = document.querySelectorAll('.sp');
		let rst = document.querySelector('#rst1');
		
		if (regMail.test(mailValue)) {
			emailCheckAjax();
			okProc($('#email'), "");
			mailchk = true;
		} else if (mailValue === "") {
			sp[4].style.display = "inline-block"
			rst.style.display = "none";
			noProc('#email', " 메일을 입력하세요");
			mailchk = false;
		} else {
			sp[4].style.display = "inline-block"
			rst.style.display = "none";
			noProc($('#email'), "형식에 맞게 입력하세요");
			mailchk = false;
		}
	});

	// 회원가입 전송
	$('#sendBtn').on('click', valid)
}
</script>

<script>
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

</script>

