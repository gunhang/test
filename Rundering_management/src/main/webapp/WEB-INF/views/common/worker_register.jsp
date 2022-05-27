<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
<style>
    .bg{
        width: 100%;
        height: 100vh;
        opacity: 70%;
        background-size:cover;
        background-repeat : no-repeat;
    }
    .register-box{
        margin: 0;
        position: absolute;
        top: 50%;        
        left: 50%;        
        -ms-transform: translate(-50%, -50%);        
        transform: translate(-50%, -50%);
    }
</style>
<!-- 이쁜알림창 -->
 <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.1.9/dist/sweetalert2.min.css">

<!-- 주소api -->
<script	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</head>

<body>

    <div class="register-box">
        <div class="login-logo">
            <div><img class="logo" src="imgfile/RunderingLogo.png" alt="RunderingMember"></div>
            <!-- <b>회원</b> 로그인 -->
        </div>
        <div class="card">
            <div class="card-body register-card-body">
                <p class="login-box-msg">Register a new membership</p>
                <form class="form-horizontal" onsubmit="return valid();" method="post">
                    <div class="input-group mb-3">
                        <input type="text" class="form-control" id="name" name="name" placeholder="사원명 입력">
                        <div class="input-group-append">
                            <select class="btn btn-info btn-sm form-control" name="password" id="password">
                                <option value="">지점선택</option>
                                <option value="060101">동구지점</option>
                                <option value="060201">중구지점</option>
                                <option value="060301">서구지점</option>
                                <option value="060401">유성구지점</option>
                                <option value="060501">대덕구지점</option>
                                <option value="000000">본사</option>
                            </select>
                        </div>
                    </div>
                    <div class="input-group mb-3">
                        <input type="email" class="form-control" id="eamil" name="email" placeholder="Email">
                        <div class="input-group-append">
                            <div class="input-group-text">
                                <span class="fas fa-envelope"></span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group input-group mb-3">
                        <input type="text" class="form-control" id="phone" name="phone" pattern="010[0-9]{8}" placeholder="휴대폰  ex)01012345678">
                        <div class="input-group-append">
                            <button type="button" class="btn btn-info btn-ml" onclick="phone_verification();">인증</button>
                        </div>
                    </div>
                    <div class="form-group verificationCode" style="display: none;">
						<div class="input-group" >
							<input type="text" class="form-control col-8" id="Code" placeholder="인증번호">
							<button type="button" onclick="verificationCodeCheck()"  class="btn btn-outline-primary btn-block col-4">인증하기</button>
							<div id="timeLimit" style="position: absolute;padding: 9px;margin-left: 150px;color: gray;font-size: 0.9rem; z-index: 10"></div>
						</div>
					</div>
                    <div class="input-group mb-3">
                        <input type="text" class="form-control" id="zip" name="zip" placeholder="우편번호 버튼 Click" readonly>
                        <div class="input-group-append">
                            <button type="button" id="modalBtn" class="btn btn-info btn-ml" onclick="findZip();">우편검색</button>
                        </div>
                    </div>
                    <div class="input-group mb-3">
                        <input type="text" class="form-control" id="add1" name="add1" placeholder="기본주소" readonly>
                        <div class="input-group-append">
                            <div class="input-group-text">
                                <span class="fas fa-address-book"></span>
                            </div>
                        </div>
                    </div>
                    <div class="input-group mb-3">
                        <input type="text" class="form-control" id="add2" name="add2" placeholder="상세주소">
                        <div class="input-group-append">
                            <div class="input-group-text">
                                <span class="fas fa-address-book"></span>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-8">
                            <div class="icheck-primary">
                                <input type="checkbox" id="agreeTerms" name="terms" value="agree">
                                <label for="agreeTerms">
                                    I agree to the <a href="#">terms</a>
                                </label>
                            </div>
                        </div>
                        <div class="col-4">
                            <button type="submit" class="btn btn-primary btn-block" onclick="regist()">등록신청</button>
                        </div>
                    </div>
                </form>
            </div>            
        </div>
    </div>
    
<script>

	let phonchk = false;

	function regist(){
		event.preventDefault();	//submit 이벤트를 막아 페이지 리로드를 방지
		if(phonchk){
			let formData = $('form').serialize();
			
			var pw1 = $('input[name="password"]');
			var pw2 = pw1.val();
			if(pw2 != ""){
				$.ajax({
					url : '<%=request.getContextPath()%>/common/application_success',
					type : 'post',
					data : formData,
					success : function(response){
						if(response.toUpperCase() == "OK"){
							location.href = "<%=request.getContextPath()%>/common/loginform";
							Swal.fire('Rundering 사원등록 신청이 완료되었습니다.', '사원 등록 후 아이디 및 패스워드를 이메일로 보내드리니 이메일을 확인해주세요', 'success' )
							} else {
							Swal.fire('공백없이 형식에 맞게 작성해주세요!', 'error' )
							}
						},
						error : function(xhr) {
							alert(xhr.status);
						},
					});
			}else {
				Swal.fire('지점코드르 선택해주세요!', 'error' )
			}
		}else{
			Swal.fire('휴대폰 인증이 필요합니다!', 'error' )
			document.getElementById("phone").focus();
		}
	}
</script>


<!-- 알림 sweetalert2 -->
<script src="<%=request.getContextPath() %>/resources/bootstrap/plugins/sweetalert2/sweetalert2.all.min.js"></script>

<script>
	const certify_ajax = function (phoneNumber){
		const v_ajax = new XMLHttpRequest();
	    v_ajax.open("POST","<%=request.getContextPath() %>/common/certifyPhoneNum",true);
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
  let responseCode = 0;
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


</body>

</html>