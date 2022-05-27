<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!--이쁜 알럽트창 -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.1.9/dist/sweetalert2.min.css">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.1.9/dist/sweetalert2.all.min.js"></script>

<!-- 주소api -->
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

	<div style="width: 60%; display: flex; flex-direction: column; margin-left: 20%;">
		<section class="content-header">
			<div class="container-fluid">
				<div class="row mb-2">
					<div class="col-sm-6">
						<h1>회원정보 수정</h1>
					</div>
					<div class="col-sm-6">
						<ol class="breadcrumb float-sm-right">
							<li class="breadcrumb-item active">회원정보 수정</li>
							<li class="breadcrumb-item"><a href="#">마이페이지</a></li>
						</ol>
					</div>
				</div>
			</div>
		<hr style="border: 1px solid rgb(170, 167, 167);">
		</section>

	
<form class="form-horizontal" method="post">
<div class="card-body marginfont text-center">
	<div class="form-group">
		<div class="row ">
			<label for="name" class="col-2">
				<p>이름</p>
			</label>
			<div class="col-10">
				<div class="col-12 row">
					<div class="form-group col-8">
						<input type="text" class="form-control" name="name" id="name" value="${loginUser.getName() }" disabled style="border:none;background-color:transparent;" readonly>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="form-group">
		<div class="row ">
			<label for="password" class="col-2">비밀번호 </label>
			<div class="col-10" id="divPassword">
				<div class="col-12 row">
					<div class="form-group col-8">
						<input type="password" class="form-control" id="password" name="password" placeholder="변경 버튼을 통해 수정이 가능합니다.">
						<span class="sp"></span> 
					</div>
					<span class="form-group col-2">
						<button class="btn float-right" id="pw" name="pw" style="border-color: gray;" onclick="pwModify()">변경</button>
					</span>
				</div>
			</div>
		</div>
	</div>
	<div class="form-group">
		<div class="row ">
			<label for="call" class="col-2">
				<p>전화번호</p>
			</label>
			<div class="col-10" id="divCall">
				<div class="col-12 row">
					<div class="form-group col-8">
						<input type="text" class="form-control"  id="phone" name="phone" value="${loginUser.getPhone() }">
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="form-group">
		<div class="row ">
			<label for="email" class="col-2">
				<p>email</p>
			</label>
			<div class="col-10" id="divEmail">
				<div class="col-12 row">
					<div class="form-group col-8">
						<input type="email" class="form-control " id="email" name="email" value="${loginUser.getEmail() }" >
						<span class="sp1"></span> 
						<span id="rst1"></span>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="form-group">
		<div class="row ">
			<label for="call" class="col-2">
				<p>주소지</p>
			</label>
			<div class="col-10">
				<div class="col-12 row">
					<div class="form-group col-8">
						<input type="text" class="form-control" name="add1" id="add1" value="${memberAddress.getAdd1() }" readonly>
					</div>
					<span class="form-group col-2">
						<button class="btn float-right" id="modalBtn" style="border-color: gray;" onclick="findAdd();">검색</button>
					</span>
				</div>
			</div>
		</div>
	</div>
	<div class="form-group">
		<div class="row ">
			<label for="call" class="col-2">
				<p>상세주소</p>
			</label>
			<div class="col-10">
				<div class="col-12 row">
					<div class="form-group col-8">
						<input type="text" class="form-control" name="add2" id="add2" value="${memberAddress.getAdd2() }">
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="form-group">
		<div class="row ">
			<label for="call" class="col-2">
			</label>
			<div class="col-10">
				<div class="col-12 row">
					<div class="form-group col-8">
						<input type="hidden" class="form-control" name="zip" id="zip" value="${memberAddress.getZip() }" readonly>
						<input type="hidden" class="form-control" name="id" id="id" value="${loginUser.getId() }" readonly>
						<input type="hidden" class="form-control" name="memberNo" id="memberNo" value="${loginUser.getMemberNo() }" readonly>
					</div>
					<span class="form-group col-2">
						<button class="btn float-right" type="submit" onClick="modify();" id="sendBtn" style="border-color: gray;">수정</button>
					</span>
				</div>
			</div>
		</div>
	</div>
</div>
</form>
	</div>

<script>
	function modify(){
		event.preventDefault(); // 이벤트를 막아 페이지 리로드를 방지
		
		 Swal.fire({
	         title: '회원 정보를 수정하시겠습니까?',
	         icon: 'warning',
	         showCancelButton: true,
	         confirmButtonColor: '#3085d6',
	         cancelButtonColor: '#d33',
	         confirmButtonText: '승인',
	         cancelButtonText: '취소',
	         reverseButtons: true // 버튼 순서 거꾸로
	         
	       }).then((result) => {
	           if (result.isConfirmed) {
	        	   $.ajax({
	        			url : '<%=request.getContextPath()%>/mypage/memberModify',
	        			data : {
	        				'id' : $('#id').val(),
	        				'email' : $('#email').val(),
	        				'phone' : $('#phone').val(),
	        				'add1' : $('#add1').val(),
	        				'add2' : $('#add2').val(),
	        				'zip' : $('#zip').val(),
	        				'memberNo' : $('#memberNo').val()
	        			},
	        			type : 'post',
	        			success : function(result) {
	        				if (result.toUpperCase() == "OK") {
	        					Swal.fire('수정 완료', '회원정보 수정이 완료되었습니다.', 'success' )
	        					location.href = "<%=request.getContextPath()%>/mypage";
	        				} else {
	        					Swal.fire({
	        						icon: 'error', // 여기다가 아이콘 종류를 쓰면 됩니다.
	        						title: '정보 수정에 실패하였습니다.',
	        					});
	        				}
	        			},
	        			error : function(error) {
	        				AjaxErrorSecurityRedirectHandler(error.status);
	        			},
	        		});
	           }
		
		})
	}
</script>

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
function pwModify(){
	let pw = document.querySelector('#password');
	event.preventDefault(); // 이벤트를 막아 페이지 리로드를 방지
	console.log(pw.value)
	if(pw.value!=""){
	
		 Swal.fire({
	         title: '비밀번호를 변경하시겠습니까?',
	         icon: 'warning',
	         showCancelButton: true,
	         confirmButtonColor: '#3085d6',
	         cancelButtonColor: '#d33',
	         confirmButtonText: '승인',
	         cancelButtonText: '취소',
	         reverseButtons: true // 버튼 순서 거꾸로
	         
	       }).then((result) => {
	           if (result.isConfirmed) {
	        	   $.ajax({
	        			url : '<%=request.getContextPath()%>/mypage/pwModify',
	        			data : {
	        				'password' : $('#password').val()
	        			},
	        			type : 'post',
	        			success : function(result) {
	        				if (result.toUpperCase() == "OK") {
	        					Swal.fire('변경 완료', '비밀변호 변경이 완료되었습니다.', 'success' )
	        				} else {
	        					Swal.fire({
	        						icon: 'error', // 여기다가 아이콘 종류를 쓰면 됩니다.
	        						title: '비밀번호 변경에 실패하였습니다.',
	        					});
	        				}
	        			},
	        			error : function(error) {
	        				AjaxErrorSecurityRedirectHandler(error.status);
	        			}
	        		});
	           }
	        })
	} else{
		Swal.fire({
				icon: 'warning', // 여기다가 아이콘 종류를 쓰면 됩니다.
				title: '비밀번호를 입력하세요',
		});
	}
}
</script>

<script>
	//email 중복체크 ajax
	function emailCheckAjax() {
		let sp = document.querySelectorAll('.sp1');
		let rst = document.querySelector('#rst1');

		$.ajax({
			url : '<%=request.getContextPath()%>/mypage/emailCheck',
			data : {
				'email' : $('#email').val()
			},
			type : 'post',
			success : function(result) {
				if (result.toUpperCase() == "OK") {
					$('#rst1').html("이미 존재하는 email입니다").css('color', 'red');
					sp.style.display = 'none';
					rst.style.display = "inline-block";
				} else {
					$('#rst1').html("사용 가능한 email입니다").css('color', 'green');
					sp.style.display = 'none';
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
	//유효성검증 - pass
	$('#password').on('keyup',function() {
		let passValue = $('#password').val().trim();
		let regPass = /^(?=.*?[a-zA-Z])(?=.*?[0-9])(?=.*?[!@#$%^&*()_+|]).{8,}$/;
	
		if (regPass.test(passValue)) {
			okProc($('#password'), "사용 가능한 패스워드 입니다!");
			pwchk = true;
		} else if (passValue === "") {
			noProc($('#password'), "패스워드를 입력하세요");
			pwchk = false;
		} else {
			noProc($('#password'), "대/소문자,특수문자,숫자 포함 8자리 이상 입력해야함");
			pwchk = false;
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
	
	// 회원수정 전송
	$('#sendBtn').on('click', modify);
	
}

	
</script>

<script>
function findAdd() {
	event.preventDefault(); // 이벤트를 막아 페이지 리로드를 방지
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

