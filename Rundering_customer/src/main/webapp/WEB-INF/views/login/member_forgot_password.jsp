<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>

<body>
    <div class="login-box" style="margin:auto;">
        <div class="login-logo">
           <img class="logo" style="margin:auto;" src="<%=request.getContextPath()%>/resources/images/RunderingMemberLogo.png" alt="RunderingMember">
            <!-- <b>회원</b> 로그인 -->
        </div>
        
        <div class="card">
            <div class="card-body ">
                <p class="login-box-msg"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">비밀번호를 잊으셨나요?<br> </font><font style="vertical-align: inherit;">▼▼아래 정보를 입력해주세요▼▼</font></font></p>
                   
	                    <div class="input-group mb-3">
	                        <input type="text" class="form-control" placeholder="이름을 입력하세요." required id="input_name">
	                    </div>
	                    <div class="input-group mb-3">
	                        <input type="text" class="form-control" id="id" placeholder="아이디를 입력하세요." required>
	                    </div>
	                    <div class="input-group mb-3" id="email_div">
	                    	<div class="col-9" style="padding-left: 0px;" >
	                    		<input type="email" class="form-control" id="email" name="email"  placeholder="이메일을 입력하세요." required >
	                    	</div>
	                        <div class="col-3" style="padding-right: 0px;">
	                    		<button type="button" id="mailCheck" class="btn btn-primary btn-block" onclick="mailCheckGo()">인증</button>
	                    	</div>
	                    </div>
                    
                    <div class="row">
                        <div class="col-12">
                            <button type="button" class="btn btn-primary btn-block" onclick="passwordGetGo()" ><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">비밀번호 찾기</font></font></button>
                        </div>
                        
                    </div>
            </div>
            
        </div>
    </div>
     <form action="passwordmodifyform" method="post" id="hiddenForm">
     </form>
    <script>
    let email_authKey="";
    let id_check=false;
    let auth_flag=false;
    let checkTag_flag=false;
    let getId="";
    
    function mailCheckGo(){
    	if(id_check==true){
    		return;
    	}
    	
    	if(checkTag_flag==false){
    	let email_div=document.querySelector("#email_div");
    	let parentDiv=document.createElement("div")
    	let authDiv=document.createElement("div");
    	let buttonDiv=document.createElement("div");
    	let authInput=document.createElement("input");
    	let authButton=document.createElement("button");
    	
    	authButton.setAttribute("class","btn btn-primary btn-block")
    	authButton.setAttribute("type","button");
    	authButton.setAttribute("onclick","auth()")
    	authButton.innerText="확인"
    	parentDiv.setAttribute("class","input-group mb-3")
    	parentDiv.setAttribute("id","parentDiv")
    	
		authDiv.setAttribute("class","col-9");
    	authDiv.style["padding-left"]="0px";
    	
    	buttonDiv.setAttribute("class","col-3");
    	buttonDiv.style["padding-right"]="0px";
    	
		authInput.type="text";
		authInput.setAttribute("class","form-control");
		authInput.setAttribute("id","authInput");
		authInput.required="";
		
		buttonDiv.append(authButton);
		
    	authDiv.append(authInput);
    	
    	parentDiv.append(authDiv);
    	parentDiv.append(buttonDiv);
    	
    	email_div.after(parentDiv);
    	
    	checkTag_flag=true;
    	
    	}
    	alert("이메일 발송.")
    	
    	  
    	$.ajax({
			  url:'<%=request.getContextPath()%>/login/passwordfindmailcheck',
			  type:"post",
			  data: {
				  email:document.querySelector("#email").value ,
				  name:document.querySelector("#input_name").value,
				  id:document.querySelector("#id").value
			  },
			  success:function(data){
			  	if(data.check=="false"){
			  		alert("이름과 이메일에 일치하는 아이디가 없습니다 .");
			  		return;
			  	}
			  	if(data.check=="true"){
					
			  		auth_flag=true;
			  	}
				  
				if(auth_flag==true){
			  		document.querySelector("#mailCheck").innerText='재발송';
			  	}
				
			  	getEmail=data.email;
			  	getName=data.name;
			  	getId=data.id;
			  	email_authKey=data.authKey
			  },
			  error:function(error){
			  	alert('서버에러');
			  }
		   });
    }
    
    function auth(){
    	let authVal=document.querySelector("#authInput").value
    	if(!(authVal==email_authKey && auth_flag==true)){
    		alert("인증실패")
    		return;
    	}
    	id_check=true;
    	document.querySelector("#parentDiv").remove();
    	alert("인증성공!")
    } 
    
    function passwordGetGo(){	
    	if(id_check==false){
    		
    		alert("인증을 받으세요")
    		return;
    	}
    	let hiddenId = document.createElement("input");
    	hiddenId.type="hidden";
    	hiddenId.name="id";
    	hiddenId.value=""+getId;
    	
    	
    	let hiddenForm = document.querySelector("#hiddenForm");
    	hiddenForm.append(hiddenId);
    	hiddenForm.submit();
		    	
    } 
    
</script>
    
</body>
