<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>지점상세</title>
<script
   src="https://cdn.jsdelivr.net/npm/admin-lte@3.2/dist/js/adminlte.min.js"></script>
<link rel="stylesheet"
   href="https://cdn.jsdelivr.net/npm/admin-lte@3.2/dist/css/adminlte.min.css">
</head>
<body>
   <div class="com ml-3 mr-3">
      <section class="content-header">
         <div class="row mb-2">
            <div class="col-sm-6">
               <h1>배달 현황 상세 정보</h1>
            </div>
         </div>
      </section>
      <div class="form-row">
         <div class="form-group col-md-6">
            <label for="inputname">name</label> <input type="text"
               class="form-control" placeholder="이름" disabled>
         </div>
         <div class="form-group col-md-6">
            <label for="inputphone">phone</label> <input type="text"
               class="form-control" placeholder="전화번호" disabled>
         </div>
      </div>
      <div class="form-row">
         <div class="form-group col-md-12">
            <label for="inputEmail4">Email</label> <input type="email"
               class="form-control" id="inputEmail" placeholder="Email" disabled>
         </div>
      </div>
      <div class="form-row">
         <div class="form-group col-md-12">
            <label for="inputAddress">Address</label> <input type="text"
               class="form-control" id="inputAddress" placeholder="주소" disabled>
         </div>
      </div>
   </div>

   <div class="card card-primary card-outline ml-3 mr-3">
      <div class="card-header">
         <h3 class="card-title">Fixed Header Table</h3>
         <div class="card-tools">
            <div class="input-group input-group-sm" style="width: 150px;">
               <input type="text" name="table_search"
                  class="form-control float-right" placeholder="Search">
               <div class="input-group-append">
                  <button type="submit" class="btn btn-default">
                     <i class="fas fa-search"></i>
                  </button>
               </div>
            </div>
         </div>
      </div>

      <div class="card-body table-responsive p-0" style="height: 550px;">
         <table class="table table-head-fixed text-nowrap">
            <thead>
               <tr>
                  <th>세탁물명</th>
                  <th>가격</th>
                  <th>수량</th>
               </tr>
            </thead>
            <tbody>
               <tr>
                  <td>와이셔츠</td>
                  <td>John Doe</td>
                  <td>11-7-2014</td>
               </tr>
               <tr>
                  <td>수건</td>
                  <td>Alexander Pierce</td>
                  <td>11-7-2014</td>
               </tr>
               <tr>
                  <td>양말</td>
                  <td>Bob Doe</td>
                  <td>11-7-2014</td>
               </tr>
               <tr>
                  <td>팬티</td>
                  <td>Mike Doe</td>
                  <td>11-7-2014</td>
               </tr>
               <tr>
                  <td>니트</td>
                  <td>Jim Doe</td>
                  <td>11-7-2014</td>
               </tr>
               <tr>
                  <td>신발</td>
                  <td>Victoria Doe</td>
                  <td>11-7-2014</td>
               </tr>
               <tr>
                  <td>뭐가있지</td>
                  <td>Michael Doe</td>
                  <td>11-7-2014</td>
               </tr>
               <tr>
                  <td>코트</td>
                  <td></td>
                  <td>11-7-2014</td>
               </tr>
            </tbody>
         </table>
      </div>

   </div>

</body>
</html>