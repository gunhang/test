<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <div style="width: 70%; margin: auto; padding-top:70px;">
      <section class="content-header"
         style="margin-bottom: 0px; padding-bottom: 0px">
         <div class="container-fluid">
            <div class="row mb-2">
               <div class="col-sm-6">
                  <h1>지점 신청 확인</h1>
               </div>
            </div>
         </div>
         <hr>
      </section>
      <div class="card-body" style="height: 300px; padding: 10px">


         <div class="row">
            <div class=" col-9" style="padding-left: 25%;">
               <div style="margin-top: 16px">
                  <label for="email" class="col-mb-3"> <span
                     style="color: red; font-weight: bold;">*</span>이름
                  </label>
               </div>
               <div class="input-group mb-3 form-group">
                  <input type="text" class="col-lg-9 form-control" id="name"
                     name="name" placeholder="이름" value="">
               </div>

               <div style="margin-top: 16px">
                  <label for="phone" class="col-mb-5"> <span
                     style="color: red; font-weight: bold;">*</span>연락처
                  </label> <span class="sp"></span>
               </div>
               <div class="input-group mb-3 form-group" style="width: 470px;">
                  <input placeholder="'-'없이  번호만 기재해주세요" pattern="010[0-9]{8}"
                     name="phone" id="phone" class="col-lg-7 form-control" type="text" value="">
                  <div class="input-group-append">
                     <button type="button" onclick="phone_verification();"
                        class="btn btn-secondary"
                        style="background-color: #82BBD8; border: 1px solid #82BBD8">인증</button>
                  </div>
               </div>

               <div class="form-group verificationCode" style="display: none;">
                  <span style="color: red; font-weight: bold;">*</span> <label
                     for="addr">인증번호</label>
                  <div class="input-group" style="padding-top: 10px; width: 470px;">
                     <input type="text" class="form-control col-7" id="Code"
                        placeholder="인증번호">
                     <div class="input-group-append">
                        <button type="button" onclick="verificationCodeCheck();" class="btn btn-secondary" style="background-color: #82BBD8; border: 1px solid #82BBD8">인증</button>
                     </div>
                     <div id="timeLimit"
                        style="position: absolute; padding: 9px; margin-left: 200px; color: gray; font-size: 0.9rem; z-index: 10"></div>
                  </div>
               </div>
            </div>
            <div style="padding-left: 25%; margin-top: 20px;" class="col-12">
               <button style="width: 330px; background-color: #82BBD8; border: 1px solid #82BBD8" class="btn btn-primary" type="button"
                  onclick="nextz()">
                  <strong>지점 신청 확인</strong>
               </button>
            </div>
         </div>
      </div>
   </div>
   <!-- 알림 sweetalert2 -->
<script src="<%=request.getContextPath() %>/resources/bootstrap/plugins/sweetalert2/sweetalert2.all.min.js"></script>

<script>

   function nextz(){
	let nm = $('#name').val();
	let ph = $('#phone').val();
      $.ajax({
         url : '<%=request.getContextPath()%>/branchapplication/self_authentification/comfirm',
         data : {
            'applicateName' : nm,
            'phone' : ph
         },
         type : 'post',
         success : function(req) {
            if(req.toUpperCase() == "OK"){
               Swal.fire({
                  icon: 'success', // 여기다가 아이콘 종류를 쓰면 됩니다.
                  title: '본인 확인이 완료 되었습니다.',
               });
               setTimeout(function(){location.href='<%=request.getContextPath() %>/branchapplication/my_branch_request?applicateName='+nm+'&phone='+ph},1000);
            } else if(req.toUpperCase() == "NO") {
               Swal.fire({
                  icon: 'warning', // 여기다가 아이콘 종류를 쓰면 됩니다.
                  title: '일치하는 정보가 없습니다.'
               });
            }
         },
         error : function(error) {
            AjaxErrorSecurityRedirectHandler(error.status);
         }
      });
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
</body>
</html>