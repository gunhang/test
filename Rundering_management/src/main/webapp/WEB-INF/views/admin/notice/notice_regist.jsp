<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<body>


<section class="content-header">
	<div class="container-fluid">
		<div class="row mb-2"> 
			<div class="col-sm-6">
				<h1>공지사항 작성</h1>
			</div>
			<div class="col-sm-6">
				<ol class="breadcrumb float-sm-right">
					<li class="breadcrumb-item"><a href="#">Home</a></li>
					<li class="breadcrumb-item active">공지사항 작성</li>
				</ol>
			</div>
		</div>
	</div>
</section>

<div class="card card-primary card-outline">     
	<div class="card-body">
	<form role="form" method="post" action="regist" name="registForm">
	<input type="hidden" name="employeeId" value="MA22001">
		<div class="form-group">
			<input class="form-control" placeholder="제목" name="title">
		</div>
<!-- 		<div class="form-group">
			<input class="form-control" placeholder="작성자:" name="employeeId" value="MA22001" readonly>
		</div> -->
		<div class="">
              <textarea class="form-control" rows="10" cols="30" id="" name="content"></textarea>
           	
          </div>
     </form>
	</div>

	<div class="card-footer">
		<div class="float-right">
			<button onclick="history.go(-1)" class="btn btn-warning">뒤로가기</button>
			<button onclick="regist_go()" type="submit" class="btn btn-primary">공지등록</button>
		</div>
	</div>
</div>
	<script>
	      window.onload=function(){	
	      summernote_go($('textarea[name="content"]'), '<%=request.getContextPath()%>');
	      }
	      
	</script>

  <script>

		function regist_go(){
			var form = document.registForm;
			if(form.title.value==""){
				alert("제목은 필수입니다.");
				return;
			}
			
			form.submit();
		}
    </script>

</body>
