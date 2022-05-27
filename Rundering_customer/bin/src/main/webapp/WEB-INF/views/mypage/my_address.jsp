<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>




<c:set var="memberAddressList" value="${memberAddressList }" />

<head>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/bootstrap/plugins/sweetalert2/sweetalert2.min.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/bootstrap/plugins/icheck-bootstrap/icheck-bootstrap.min.css" />

<style>
	#peter {
		width: 250px;
		list-style-type:none;
		margin:0;
		padding:0;
		border:solid 1px black;
	}
	aside ul li a{
		color:#000000;
		padding:10px;
		font-size:1.2em;
	}
	li h1{
	}
</style>

</head>


<body>
<div class="row" style="margin-left:10%;">
	<aside style="padding-top:70px;">
			<ul id="peter" style="">
				<li style="margin-top:15px; padding-bottom:15px;border-bottom:solid 1px black;">
					<h1 style="font-size:1.5em;text-align:center;">마이페이지</h1>
				</li>
				<li style="margin-top:30px; margin-bottom:15px; margin-left:30px;"><a>회원 정보 수정</a></li>
				<li style="margin-top:15px; margin-bottom:15px; margin-left:30px;"><a>주소 관리</a></li>
				<li style="margin-top:15px; margin-bottom:15px; margin-left:30px;"><a>주문 내역</a>
					<ul>
						<li style="margin-top:5px; margin-bottom:5px;padding-left:15px;"><a>진행중인 세탁물</a></li>
						<li style="margin-top:5px; margin-bottom:5px;padding-left:15px;"><a>배송 완료된 세탁</a></li>
						<li style="margin-top:5px; margin-bottom:5px;padding-left:15px;"><a>결제 내역</a></li>
						<li style="margin-top:5px; margin-bottom:5px;padding-left:15px;"><a>취소 내역</a></li>
					</ul>
				</li>
				<li style="margin-top:15px; margin-bottom:30px; margin-left:30px;"><a>회원 탈퇴</a></li>
			</ul>
	</aside>


	<div style="width: 67%; display: flex; flex-direction: column;">

		<section class="content-header">
			<div class="container-fluid">
				<div class="row mb-2">
					<div class="col-sm-6">
						<h1>내 주소 관리</h1>
					</div>
					<div class="col-sm-6">
						<ol class="breadcrumb float-sm-right">
							<li class="breadcrumb-item active">내 주소 관리</li>
							<li class="breadcrumb-item"><a href="#">마이페이지</a></li>
						</ol>
					</div>
				</div>
			</div>
			<hr style="border: 1px solid rgb(170, 167, 167);">
		<form role="form" class="form-horizontal form" method="post">

			<div class="card-body col-6" style="margin: auto; margin-top: 10px;">

				<div class="form-group">
					<label for="addr">주소지</label>
					<div class="input-group" style="padding-top: 10px;">
						<div class="input-group" style="padding-right: 0;">
							<div class="icheck-primary pt-3 pb-3 pl-1"
								style="width: 100%; border-top: 1px solid rgba(0, 0, 0, .125); border-bottom: 1px solid rgba(0, 0, 0, .125);">
								<input type="radio" value="${defaultMemberAddress.addressNo}"
									onchange="newAddr(this);" onclick="dfdetail(${defaultMemberAddress.addressNo});" name="addressNo"
									id="${defaultMemberAddress.addressNo}" > <label
									for="${defaultMemberAddress.addressNo}"
									style="font-weight: 600;">기본 주소지</label>
							</div>

							<c:if test="${!empty memberAddressList }">
								<c:forEach items="${memberAddressList }" var="memberAddress" varStatus="status">
									<c:if test="${memberAddress.defaultYn eq 'N' }">
										<div class="icheck-primary pt-1 pb-2 pl-1"
											style="width: 100%; border-bottom: 1px solid rgba(0, 0, 0, .125);">
											<input type="radio" value="${memberAddress.addressNo}" onclick="detail(${memberAddress.addressNo});"
												onchange="newAddr(this);" name="addressNo" class="토레타"
												id="${memberAddress.addressNo}"> <label
												for="${memberAddress.addressNo}" style="font-weight: 500;">주소${status.count }</label>
												<button class="btn btn-danger btn-sm float-right" onclick="remove(${memberAddress.addressNo})">삭제</button>
										</div>
									</c:if>
								</c:forEach>
							</c:if>

							<div class="icheck-primary pt-1 pb-3 pl-1"
								style="width: 100%; border-bottom: 1px solid rgba(0, 0, 0, .125);">
								<input type="radio" value="0" name="addressNo"
									id="newAddrInputBtn" onchange="newAddr(this);"> <label
									for="newAddrInputBtn" style="font-weight: 500;">새로운 주소지 등록</label>
							</div>

							<!--기존 등록지 수정 -->
							<div class="newAddrInput1 pt-2" style="display:; width: 100%;">
								<div class="input-group mb-3">
									<input type="text" class="form-control" id="zip" name="zip"
										placeholder="우편번호" readonly>
									<div class="input-group-append">
										<button type="button" id="modalBtn" class="btn btn-primary"
											onclick="findZip();">주소검색</button>
									</div>
								</div>
								<div class="input-group mb-3">
									<input type="text" class="form-control" id="add1" name="add1"
										placeholder="기본주소" readonly>
								</div>
								<div class="input-group mb-3">
									<input type="text" class="form-control" id="add2" name="add2"
										placeholder="상세주소" > <input type="hidden"
										class="form-control" id="addressNo" name="addressNo" value="">
								</div>
								<div class="row">
									<div class="pl-2">
										<div class="icheck-primary">
											<input type="checkbox" name="setDefaultAddr" value="Y"
												id="setDefaultAddr"> <label for="setDefaultAddr"
												style="font-weight: 500;"> 기본주소지로 등록 </label>
											<button type="button" class="btn btn-outline-secondary"
												style="margin-left: 140px;" onclick="addrmodify();">수정</button>
										</div>
									</div>
								</div>
							</div>

							<!--신규 주소지 추가 -->
							<div class="newAddrInput pt-2"
								style="display: none; width: 100%;">
								<div class="input-group mb-3">
									<input type="text" class="form-control" id="zip1" name="zip1"
										placeholder="우편번호" readonly>
									<div class="input-group-append">
										<button type="button" id="modalBtn" class="btn btn-primary"
											onclick="findZip1();">주소검색</button>
									</div>
								</div>
								<div class="input-group mb-3">
									<input type="text" class="form-control" id="add3" name="add3"
										placeholder="기본주소"  readonly>
								</div>
								<div class="input-group mb-3">
									<input type="text" class="form-control" id="add4" name="add4"
										placeholder="상세주소" > 
								</div>
								<div class="row">
									<div class="pl-2">
										<div class="icheck-primary">
											<input type="checkbox" name="setDefaultAddr1" value="Y"
												id="setDefaultAddr1"> <label for="setDefaultAddr1"
												style="font-weight: 500;"> 기본주소지로 등록 </label>
											<button type="button" class="btn btn-outline-secondary"
												style="margin-left: 140px;" onclick="addrregist();">등록</button>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

			</div>


		</form>
				</section>
</div>
	</div>

	<!-- 알림 sweetalert2 -->
	<script src="<%=request.getContextPath()%>/resources/bootstrap/plugins/sweetalert2/sweetalert2.all.min.js"></script>

	<!-- 주소api -->
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<script src="https://code.jquery.com/jquery-3.4.1.js"></script>

<script>

	let check = document.querySelector('.토레타');
	function remove(no){
		$(check).prop("checked", true);

		detail(no);
		
		event.preventDefault(); // 이벤트를 막아 페이지 리로드를 방지	
		
		Swal.fire({
            title: '주소지를 삭제하시겠습니까?',
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
				url : '<%=request.getContextPath()%>/mypage/myaddress/remove',
				data : {
					'addressNo' : no
				},
				type : 'post',
				success : function(response){
                    if(response.toUpperCase() == "OK"){
                    	Swal.fire('삭제완료!', '주소가 삭제되었습니다.', 'success' )
                    	setTimeout(function(){location.href = "<%=request.getContextPath()%>/mypage/myaddress";},1000);
                       } else {
                          Swal.fire('삭제를 실패했습니다.', 'error' )
                       }
                    },
				error : function(error) {
					//alert("시스템장애로 가입이 불가합니다.");
					AjaxErrorSecurityRedirectHandler(error.status);
				}
			});
              }
          })	
	}
            
</script>

<script>
	
	let zip = document.querySelector('#zip');
	let add1 = document.querySelector('#add1');
	let add2 = document.querySelector('#add2');
	let addrNo = document.querySelector('#addressNo');
	

	function dfdetail(no){
		$.ajax({
			url : '<%=request.getContextPath()%>/mypage/myaddress/detail',
			data : {
				'addressNo' : no
			},
			type : 'post',
			success : function(mv) {
				zip.value = mv.zip
				add1.value = mv.add1
				add2.value = mv.add2
				addrNo.value = mv.addrNo
			},
			error : function(error) {
				//alert("시스템장애로 가입이 불가합니다.");
				AjaxErrorSecurityRedirectHandler(error.status);
			}
		});
	}
	
	
	function detail(no){
		$.ajax({
			url : '<%=request.getContextPath()%>/mypage/myaddress/detail',
			data : {
				'addressNo' : no
			},
			type : 'post',
			success : function(mv) {
				zip.value = mv.zip
				add1.value = mv.add1
				add2.value = mv.add2
				addrNo.value = mv.addrNo
			},
			error : function(error) {
				//alert("시스템장애로 가입이 불가합니다.");
				AjaxErrorSecurityRedirectHandler(error.status);
			}
		});
	}
	
	
</script>


	<script>

	//주소 수정
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
	
	// 주소 등록
	function findZip1() {
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
				document.getElementById('zip1').value = data.zonecode;
				document.getElementById("add3").value = addr;
				// 커서를 상세주소 필드로 이동한다.
				document.getElementById("add4").focus();
			}
		}).open();
	}
	
	
	
	function newAddr(radioBtn) {
		const newAddrInput = document.querySelector('.newAddrInput');	
		const newAddrInput1 = document.querySelector('.newAddrInput1');	
		if(radioBtn.value == 0){
			newAddrInput.style.display = 'block';
			newAddrInput1.style.display = 'none';
		}else{
			newAddrInput.style.display = 'none';
			newAddrInput1.style.display = 'block';
		}
	}
	
	
</script>

	<script>

	//주소 수정 
	function addrmodify(){
		let defualt = document.querySelector('#setDefaultAddr'); 
		let ta = document.getElementById('add1').value;
		let topArea = ta.substr(0,2);
		
		console.log(ta)
		console.log(defualt.value)
		if(topArea == "대전"){
			if(defualt.checked){
				Swal.fire({
		               title: '기본 주소지로 수정하시겠습니까?',
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
		                     url : '<%=request.getContextPath()%>/mypage/myaddress/defaultmodify',
		                     type : 'post',
		                     data : {
		                    	 'add1' : $('#add1').val(),
		                    	 'add2' : $('#add2').val(),
		                    	 'zip' : $('#zip').val(),
		                    	 'addrNo' : $('#addressNo').val()
		                     },
		                     success : function(response){
		                        if(response.toUpperCase() == "OK"){
		                        	Swal.fire('수정 완료!', '기본 주소지 수정이 완료되었습니다.', 'success' )
		                        	setTimeout(function(){location.href = "<%=request.getContextPath()%>/mypage/myaddress";},1000);
		                           } else {
		                              Swal.fire('수정을 실패했습니다.', 'error' )
		                           }
		                        },
		                        error : function(xhr) {
		                           alert(xhr.status);
		                        },
		                     });
		                     
		           		}})
			}else{
				Swal.fire({
		               title: '주소지를 수정하시겠습니까?',
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
		                     url : '<%=request.getContextPath()%>/mypage/myaddress/modify',
		                     type : 'post',
		                     data : {
		                    	 'add1' : $('#add1').val(),
		                    	 'add2' : $('#add2').val(),
		                    	 'zip' : $('#zip').val(),
		                    	 'addrNo' : $('#addressNo').val()
		                     },
		                     success : function(response){
		                        if(response.toUpperCase() == "OK"){
		                        	Swal.fire('수정 완료!', '주소지 수정이 완료되었습니다.', 'success' )
		                        	setTimeout(function(){location.href = "<%=request.getContextPath()%>/mypage/myaddress";},1000);
		                           } else {
		                              Swal.fire('수정을 실패했습니다.', 'error' )
		                           }
		                        },
		                        error : function(xhr) {
		                           alert(xhr.status);
		                        },
		                     });
		                     
		           		}})
			}
			
		}else{
			if(defualt.checked){
			 Swal.fire({
	               title: '해당 주소지는 서비스 지역이 아닙니다. 기본 주소지로 수정 하시겠습니까?',
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
	                     url : '<%=request.getContextPath()%>/mypage/myaddress/defaultmodify',
	                     type : 'post',
	                     data : {
	                    	 'add1' : $('#add1').val(),
	                    	 'add2' : $('#add2').val(),
	                    	 'zip' : $('#zip').val(),
	                    	 'addrNo' : $('#addressNo').val()
	                     },
	                     success : function(response){
	                        if(response.toUpperCase() == "OK"){
	                        	Swal.fire('수정 완료!', '주소지 수정이 완료되었습니다.', 'success' )
	                        	setTimeout(function(){location.href = "<%=request.getContextPath()%>/mypage/myaddress";},1000);
	                        } else {
	                           	Swal.fire('주소지 수정을 실패하였습니다.', 'error' )
	                        }
	                     },
	                        error : function(xhr) {
	                           alert(xhr.status);
	                        },
	                     });
	                     
	           		}})
			}else{
				Swal.fire({
		               title: '해당 주소지는 서비스 지역이 아닙니다. 수정 하시겠습니까?',
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
		                     url : '<%=request.getContextPath()%>/mypage/myaddress/modify',
		                     type : 'post',
		                     data : {
		                    	 'add1' : $('#add1').val(),
		                    	 'add2' : $('#add2').val(),
		                    	 'zip' : $('#zip').val(),
		                    	 'addrNo' : $('#addressNo').val()
		                     },
		                     success : function(response){
		                        if(response.toUpperCase() == "OK"){
		                        	Swal.fire('수정 완료!', '주소지 수정이 완료되었습니다.', 'success' )
		                        	setTimeout(function(){location.href = "<%=request.getContextPath()%>/mypage/myaddress";},1000);
		                        } else {
		                           	Swal.fire('주소지 수정을 실패하였습니다.', 'error' )
		                        }
		                     },
		                        error : function(xhr) {
		                           alert(xhr.status);
		                        },
		                     });
		                     
		           		}})
			}
			
		}
		
		
	}
	
	// 주소지 등록
	function addrregist(){
		let defualt1 = document.querySelector('#setDefaultAddr1'); 
		let ta1 = document.getElementById('add3').value;
		let topArea1 = ta1.substr(0,2);
		
		console.log(ta1)
		if(${fn:length(memberAddressList)} < 3) {
			if(topArea1 == "대전"){
				if(defualt1.checked){
					Swal.fire({
			               title: '기본 주소지로 등록 하시겠습니까?',
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
			                     url : '<%=request.getContextPath()%>/mypage/myaddress/defaultregist',
			                     type : 'post',
			                     data : {
			                    	 'add1' : $('#add3').val(),
			                    	 'add2' : $('#add4').val(),
			                    	 'zip' : $('#zip1').val()
			                     },
			                     success : function(response){
			                        if(response.toUpperCase() == "OK"){
			                        	Swal.fire('등록 완료!', '기본 주소지 등록이 완료되었습니다.', 'success' )
			                        	setTimeout(function(){location.href = "<%=request.getContextPath()%>/mypage/myaddress";},1000);
			                           } else {
			                              Swal.fire('등록을 실패했습니다.', 'error' )
			                           }
			                        },
			                        error : function(xhr) {
			                           alert(xhr.status);
			                        },
			                     });
			                     
			           		}})
				}else{
					Swal.fire({
			               title: '주소지를 등록하시겠습니까?',
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
			                     url : '<%=request.getContextPath()%>/mypage/myaddress/regist',
			                     type : 'post',
			                     data : {
			                    	 'add1' : $('#add3').val(),
			                    	 'add2' : $('#add4').val(),
			                    	 'zip' : $('#zip1').val()
			                     },
			                     success : function(response){
			                        if(response.toUpperCase() == "OK"){
			                        	Swal.fire('등록 완료!', '주소지 등록이 완료되었습니다.', 'success' )
			                        	setTimeout(function(){location.href = "<%=request.getContextPath()%>/mypage/myaddress";},1000);
			                           } else {
			                              Swal.fire('수정을 실패했습니다.', 'error' )
			                           }
			                        },
			                        error : function(xhr) {
			                           alert(xhr.status);
			                        },
			                     });
			                     
			           		}})
				}
				
			}else{
				if(defualt1.checked){
				 Swal.fire({
		               title: '해당 지역은 서비스 지역이 아닙니다. 기본 주소지로 등록 하시겠습니까?',
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
		                     url : '<%=request.getContextPath()%>/mypage/myaddress/defaultregist',
		                     type : 'post',
		                     data : {
		                    	 'add1' : $('#add3').val(),
		                    	 'add2' : $('#add4').val(),
		                    	 'zip' : $('#zip1').val()
		                     },
		                     success : function(response){
		                        if(response.toUpperCase() == "OK"){
		                        	Swal.fire('등록 완료!', '주소지 등록이 완료되었습니다.', 'success' )
		                        	setTimeout(function(){location.href = "<%=request.getContextPath()%>/mypage/myaddress";},1000);
		                        } else {
		                           	Swal.fire('주소지 등록을 실패하였습니다.', 'error' )
		                        }
		                     },
		                        error : function(xhr) {
		                           alert(xhr.status);
		                        },
		                     });
		                     
		           		}})
				}else{
					Swal.fire({
			               title: '해당 지역은 서비스 지역이 아닙니다. 주소지로 등록 하시겠습니까?',
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
			                     url : '<%=request.getContextPath()%>/mypage/myaddress/regist',
			                     type : 'post',
			                     data : {
			                    	 'add1' : $('#add3').val(),
			                    	 'add2' : $('#add4').val(),
			                    	 'zip' : $('#zip1').val(),
			                     },
			                     success : function(response){
			                        if(response.toUpperCase() == "OK"){
			                        	Swal.fire('등록 완료!', '주소지 등록이 완료되었습니다.', 'success' )
			                        	setTimeout(function(){location.href = "<%=request.getContextPath()%>/mypage/myaddress";},1000);
			                        } else {
			                           	Swal.fire('주소지 등록을 실패하였습니다.', 'error' )
			                        }
			                     },
			                        error : function(xhr) {
			                           alert(xhr.status);
			                        },
			                     });
			                     
			           		}})
				}
				
			}			
			
		} else {
			Swal.fire({
				icon: 'warning', // 여기다가 아이콘 종류를 쓰면 됩니다.
				title: '주소지 등록은 최대 3개 까지 가능합니다.',
			});
		}
	}
</script>

</body>

