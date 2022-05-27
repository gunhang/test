<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>



<title>메인페이지</title>

<head></head>

<body>
	<iframe name="ifr" src="" frameborder="0" style= "width: 100%; height:85vh;"></iframe>
	
	<script>
			
	console.log(history.pushState);
	
		function goPage(url,menuCode){
			
			getNotification();
			
			document.querySelector('iframe[name="ifr"]').src=url;
			

			// HTML5 지원브라우저에서 사용 가능
			if (typeof (history.pushState) == 'function') {
				//현재 주소를 가져온다.
				var renewURL = location.href;
				//현재 주소 중 .부분이 있다면 날려버린다.
				renewURL = renewURL.substring(0, renewURL.indexOf(".") );

				if (menuCode != 'B000000') {
					renewURL += "?menuCode=" + menuCode;
				}
				//페이지를 리로드하지 않고 페이지 주소만 변경할 때 사용
				history.pushState(menuCode, null, renewURL);
			} else {
				location.hash = "#" + menuCode;
			}
			
			
		} 
		
	</script>
	
	<script>
	// window.onload() => 콘텐츠 (이미지, CSS, 스크립트 등)를 포함하여 전체 페이지가 로드된 후! 시작 됩니다.
		 window.onload=function(){
			goPage('<%= request.getContextPath() %>${menu.menuUrl}','${menu.menuCode}');
			
		} 
	</script>
	
	<script>
	//알림 가져오기
	function getNotification(){
		$.getJSON("<%=request.getContextPath() %>/notification", function(dataMap){
			displayItems(dataMap.notificationList, dataMap.notificationTypeMap);
		});
	}
	
	// 알림 화면에 출력
	function displayItems(items,namemap) {
	  const container = document.querySelector('.notificationli');
	  
		if(!items.length){
			 container.innerHTML = `
				 <a class="nav-link" data-toggle="dropdown" href="#" aria-expanded="false"> 
					<i class="far fa-bell"></i> 
				</a>
					<div class="dropdown-menu dropdown-menu-right" style="left: inherit; right: 0px; width: 340px; padding: 0;">
					<div class="d-flex justify-content-center text-muted pt-3 pb-3">
						<span>
							새로운 알림이 없습니다.
						</span>
					</div>`;
		} else{		
	  
		  let firsthtml = `<a class="nav-link" data-toggle="dropdown" href="#" aria-expanded="false"> 
								<i class="far fa-bell"></i> 
								<span class="badge badge-warning navbar-badge">${'${items.length}'}</span>
							</a>
								<div class="dropdown-menu dropdown-menu-right" style="left: inherit; right: 0px; width: 340px; padding: 0;">
									<span class="dropdown-item dropdown-header">읽지 않은 알림 ${'${items.length}'}개</span>`;
									
		  let lasthtml = `<div class="dropdown-divider m-0"></div>
							<a onclick="delAllnt();" class="dropdown-item dropdown-footer" style="cursor: pointer;">모두 읽음 처리</a>
						  </div>`;
						  const result = items.filter(item => item.ntcnknd == '');
		  container.innerHTML = firsthtml + items.map(item => createHTMLString(item,namemap)).join('') + lasthtml;
		}
	}	
	
	//알림 종류에 따라 html생성
	function createHTMLString(item,namemap) {
		let iconClass =  "";
		if(item.ntcnknd == 'NT'){
			iconClass = "fas fa-bullhorn mr-2";
		}else if(item.ntcnknd == 'SG'){
			iconClass = "fas fa-comment-dots mr-2";
		}else if(item.ntcnknd == 'BA'){
			iconClass = "fas fa-building mr-2";
		}else if(item.ntcnknd == 'AS'){
			iconClass = "fas fa-tools mr-2";
		}else if(item.ntcnknd == 'PC'){
			iconClass = "fas fa-tasks mr-2";
		}
		
		let displaytime = displayedAt(item.occrrncdehour);
		let nttitle = namemap[item.ntcnknd]+' - '+item.ntcncn;
		nttitle = nttitle.length > 22 ? nttitle.substring(0, 20) + '..' : nttitle;
		
		let param = item.ntcnclickhourUrl+",'"+item.ntcnId+"'";
		
	  return `
	 	<div class="dropdown-divider m-0"></div>
		<a class="dropdown-item pt-2 pb-2" onclick="delnt(${'${param}'});" style="cursor: pointer;">
			<i class="${'${iconClass}'}"></i>
			${'${nttitle}'} <span class="float-right text-muted text-sm">${'${displaytime}'}</span>
		</a>
	    `;
	}
	
	//알림 클릭시 알림 읽음처리 및 페이지이동
	function delnt(url,menu,ntcnId){
		 $.ajax({
             url: "<%=request.getContextPath() %>/notification/"+ntcnId,
             method: "DELETE",
             dataType: "text",
             success: function (data) {
                 console.log(data)
	   			 goPage(url,menu);
             }
			});
	}
	
	//모든 알림 읽음처리
	function delAllnt(){
		 $.ajax({
             url: "<%=request.getContextPath() %>/notification/all",
             method: "DELETE",
             dataType: "text",
             success: function (data) {
                console.log(data)
		 		getNotification();
             }
			});
	}

	// 알림 등록된 시간 계산
	 function displayedAt(createdAt) {
			const milliSeconds = new Date().getTime() - createdAt;
			
			const seconds = milliSeconds / 1000;
			if (seconds < 60) return `방금 전`;
			
			const minutes = Math.floor(seconds / 60);
			if (minutes < 60) return minutes+"분 전";
			
			const hours = Math.floor(minutes / 60);
			if (hours < 24) return hours+"시간 전";
			
			const days = Math.floor(hours / 24);
			if (days < 7) return days+"일 전";
			
			return new Date(createdAt).format("yyyy.MM.dd");
	 }

	</script>
	
	
</body>
