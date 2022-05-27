<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.1.9/dist/sweetalert2.min.css">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.1.9/dist/sweetalert2.all.min.js"></script>

<c:set var="pageMaker" value="${dataMap.pageMaker }" />
<c:set var="cri" value="${dataMap.pageMaker.cri }" />
<c:set var="appList" value="${dataMap.appList }" />
<c:set var="branchList" value="${branchList}" />
<c:set var="dpList" value="${dpList}" />
<c:set var="poList" value="${poList}" />
<c:set var="bv" value="${bv}" />



<section class="content-header">
   <div class="container-fluid">
      <div class="row mb-2">
         <div class="col-sm-6">
            <h1>사원 등록 관리</h1>
         </div>
      </div>
   </div>
</section>

<div class="row ml-3 mr-3">
   <div class="col-12">
      <div class="card">
         <div class="card-header">

            <div class="input-group input-group-sm">
               <h2 style="height: 20px;" class="card-title">
                  <b>사원 신청 리스트</b>
               </h2>

               <div class="col-7"></div>
            </div>
            <div class="card-tools"></div>

         </div>

         <div class="card-body p-0" style="height:300px;">
            <table style="text-align: center;" class="table text-nowrap">
               <thead>
                  <tr>
                     <th>회원번호</th>
                     <th>이름</th>
                     <th>등록신청일</th>
                     <th>상태변경</th>
                  </tr>
               </thead>
               <tbody>
                  <c:if test="${empty appList  }">
                     <tr>
                        <td colspan="5" style="vertical-align:middle;"><strong>해당 내용이 없습니다.</strong></td>
                     </tr>
                  </c:if>
                  <c:forEach items="${appList }" var="list">
                     <tr>
                        <td class="list" id="memno" name="memno" value="${list.memberNo}">${list.memberNo}</td>
                        <td onClick="detail('${list.memberNo}')">${list.name }</td>
                        <td><fmt:formatDate
                              value="${list.registDate }" pattern="yyyy-MM-dd" /></td>
                        <td><button type="submit"
                              class="btn btn-danger btn-sm" onclick="remove('${list.memberNo}')">반려</button></td>
                     </tr>
                  </c:forEach>
               </tbody>
            </table>
         </div>
         <div class="card-footer">
            <%@ include file="/WEB-INF/views/admin/employee/pagination.jsp"%>
         </div>
      </div>
   </div>
</div>

<div class="row ml-3 mr-3">
   <div class="col-12">
      <div class="card card-default">

         <div class="card-header">
            <h3 class="card-title">
               <b>사원 등록</b>
            </h3>
         </div>

<form class="form-horizontal" method="post">
            <div class="card-body">
               <div class="row">
                  <div class="col-md-6">
                     <div class="form-group mb-1" style="display:flex;">
                        <label class="col-2 mt-2 ml-3">이름</label> 
                        <input type="text" class="form-control"   id="name" name="name" value="" readonly />
                     </div>
                     <div class="form-group mb-1" style="display:flex;">
                        <label class="col-2 mt-2 ml-3">연락처</label> 
                        <input type="text" class="form-control"   id="phone" name="phone" value="" readonly />
                     </div>
                     <div class="form-group mb-1" style="display:flex;">
                        <label class="col-2 mt-2 ml-3">이메일</label> 
                        <input type="text" class="form-control"   id="email" name="eamil" value="" readonly />
                     </div>
                  </div>
                  <div class="col-md-6">
                     <div class="form-group mb-1" style="display:flex;">
                        <label class="col-2 mt-2 ml-3">지점</label>
                        <input type="hidden" class="form-control"   id="branchCode" name="branchCode" value="${bv.branchCode }" readonly > 
                        <input type="text" class="form-control"   value="${bv.branchName }" readonly > 
                     </div>
                     <div class="form-group mb-1" style="display:flex;">
                        <label class="col-2 mt-2 ml-3">부서</label> <select
                           class="form-control select2 select2-hidden-accessible"
                           style="width: 100%;" data-select2-id="9" tabindex="-1"
                           aria-hidden="true" id="department" name="department" value="" >
                           <option selected="selected" data-select2-id="11" >부서명</option>
                           <c:forEach items="${dpList }" var="list">
                           <option value="${list.comCode }">${list.comCodeNm }</option>
                           </c:forEach>
                        </select>
                     </div>
                     <div class="form-group mb-1" style="display:flex;">
                        <label class="col-2 mt-2 ml-3">직책</label> <select
                           class="form-control select2 select2-hidden-accessible"
                           style="width: 100%;" data-select2-id="9" tabindex="-1"
                           aria-hidden="true" id="position" name="position" value="">
                           <option selected="selected" data-select2-id="11" >직책명</option>
                           <c:forEach items="${poList }" var="list">
                           <option value="${list.comCode }">${list.comCodeNm }</option>
                           </c:forEach>
                        </select>
                     </div>
                  </div>
               </div>
               <input type="hidden" id="memNo" name="memNo" value="" >
               <input type="hidden" id="jd" name="jd" value="" >
                     <div class="form-group" style="">
                        <div class="float-right col-2 p-0" style="margin-right: 0;">
                           <button onclick="regist()" class="btn col-12 btn-primary" type="submit" style="background-color: #82BBD8; border: 1px solid #82BBD8">등록</button>
                        </div>
                     </div>
            </div>
         </form>
      </div>
   </div>
</div>


<script>
   let nm = document.querySelector('#name');
   let jd = document.querySelector('#jd');
   let memNo = document.querySelector('#memNo');
   let name = document.querySelector('input[id="name"]');
   let phone = document.querySelector('input[id="phone"]');
   let email = document.querySelector('input[id="email"]');
   
   function regist(){
      
      event.preventDefault(); // 이벤트를 막아 페이지 리로드를 방지
      
      $.ajax({
         url : '<%=request.getContextPath()%>/admin/employeeapplication/regist',
         data : {
            'department' : $('#department').val()
            ,'branchCode' : $('#branchCode').val()
            ,'position' : $('#position').val()
            ,'memberno' : memNo.value
            
         },
         type : 'post',
         success : function(오케이) {
            if(ok.toUpperCase() == "OK"){
               Swal.fire({
                  icon: 'success', // 여기다가 아이콘 종류를 쓰면 됩니다.
                  title: '등록 성공',
                  text: '해당 신청을 등록하였습니다.'
               });
               setTimeout(function(){location.reload();},1000);
            } else {
               Swal.fire({
                  icon: 'warning', // 여기다가 아이콘 종류를 쓰면 됩니다.
                  title: '시스템 오류로 반려 할 수 없습니다.'
               });
            }
         },
         error : function(error) {
            AjaxErrorSecurityRedirectHandler(error.status);
         }
      });
   }
   
   function detail(no) {
      console.log(this);
      $.ajax({
         url : '<%=request.getContextPath()%>/admin/employeeapplication/detail',
         data : {
            'memberNo' : no
         },
         type : 'post',
         success : function(mv) {
            nm.value = mv.name
            phone.value = mv.phone
            email.setAttribute('value',mv.email)
            jd.setAttribute('value',mv.registDate)
            memNo.setAttribute('value',mv.memberNo)      
         },
         error : function(error) {
            AjaxErrorSecurityRedirectHandler(error.status);
         }
      });
   }
   
   function remove(no){
      //console.log(this);
      
      event.preventDefault(); // 이벤트를 막아 페이지 리로드를 방지
      
      $.ajax({
         url : '<%=request.getContextPath()%>/admin/employeeapplication/remove',
         data : {
            'memberNo' : no
         },
         type : 'post',
         success : function(오케이) {
            if(ok.toUpperCase() == "OK"){
               Swal.fire({
                  icon: 'error', // 여기다가 아이콘 종류를 쓰면 됩니다.
                  title: '반려',
                  text: '해당 신청을 반려하였습니다.'
               });
               setTimeout(function(){location.reload();},1000);
            } else {
               Swal.fire({
                  icon: 'warning', // 여기다가 아이콘 종류를 쓰면 됩니다.
                  title: '시스템 오류로 반려 할 수 없습니다.'
               });
            }
         },
         error : function(error) {
            AjaxErrorSecurityRedirectHandler(error.status);
            
         }
      });
      
   }
   
   
   
</script>
