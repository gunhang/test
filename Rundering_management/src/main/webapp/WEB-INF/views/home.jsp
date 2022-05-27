<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
   <div class="row2 ml-3 mr-3">
      <div class="row">
         <div class="col-md-6">
            <div class="card card-primary card-outline">
               <div class="card-header">
                  <h3 class="card-title">General</h3>
                  <div class="card-tools">
                     <button type="button" class="btn btn-tool"
                        data-card-widget="collapse" title="Collapse">
                        <i class="fas fa-minus"></i>
                     </button>
                  </div>
               </div>
               <div class="card-body" style="height: 300px">
                  <div class="card-body table-responsive p-0">
                     <table class="table table-hover text-nowrap">
                        <thead>
                           <tr>
                              <th>주문번호</th>
                              <th>담당자</th>
                              <th>날짜</th>
                              <th>상태</th>

                           </tr>
                        </thead>
                        <tbody>
                           <tr>
                              <td>183</td>
                              <td><a
                                 onclick="OpenWindow('http://localhost/runderingmanage/admin/delivery/detail.do','글등록',800,700);"
                                 style="cursor: pointer;">게시글등록</a></td>
                              <td>2022-03-30</td>
                              <td><span class="badge bg-warning">배송중</span></td>

                           </tr>
                           <tr>
                              <td>219</td>
                              <td>이수윤</td>
                              <td>2022-03-30</td>
                              <td><span class="badge bg-success">배송완료</span></td>

                           </tr>
                           <tr>
                              <td>657</td>
                              <td>오세준</td>
                              <td>2022-03-30</td>
                              <td><span class="badge bg-warning">수거중</span></td>

                           </tr>
                           <tr>
                              <td>175</td>
                              <td>백관우</td>
                              <td>2022-03-30</td>
                              <td><span class="badge bg-success">수거완료</span></td>
                           </tr>
                        </tbody>
                     </table>
                  </div>
               </div>
               <div class="card-footer">
                  <div class="col-2 float-right">
                     <button type="button" class="btn btn-block btn-primary btn-sm">자세히</button>
                  </div>
               </div>
            </div>
         </div>
         <div class="col-md-6">
            <div class="card card-secondary card-outline">
               <div class="card-header">
                  <h3 class="card-title">Budget</h3>
                  <div class="card-tools">
                     <button type="button" class="btn btn-tool"
                        data-card-widget="collapse" title="Collapse">
                        <i class="fas fa-minus"></i>
                     </button>
                  </div>
               </div>
               <div class="card-body" style="height: 350px">
                  <div class="form-group">
                     <label for="inputEstimatedBudget">Estimated budget</label> <input
                        type="number" id="inputEstimatedBudget" class="form-control">
                  </div>
                  <div class="form-group">
                     <label for="inputSpentBudget">Total amount spent</label> <input
                        type="number" id="inputSpentBudget" class="form-control">
                  </div>
                  <div class="form-group">
                     <label for="inputEstimatedDuration">Estimated project
                        duration</label> <input type="number" id="inputEstimatedDuration"
                        class="form-control">
                  </div>
               </div>
            </div>
         </div>
      </div>
      <div class="row">
         <div class="col-md-6">
            <div class="card card-primary card-outline">
               <div class="card-header">
                  <h3 class="card-title">General</h3>
                  <div class="card-tools">
                     <button type="button" class="btn btn-tool"
                        data-card-widget="collapse" title="Collapse">
                        <i class="fas fa-minus"></i>
                     </button>
                  </div>
               </div>
               <div class="card-body" style="height: 350px">
                  <div class="form-group">
                     <label for="inputName">Project Name</label> <input type="text"
                        id="inputName" class="form-control">
                  </div>
                  <div class="form-group">
                     <label for="inputDescription">Project Description</label>
                     <textarea id="inputDescription" class="form-control" rows="4"></textarea>
                  </div>
               </div>
            </div>

         </div>
         <div class="col-md-6">
            <div class="card card-secondary card-outline">
               <div class="card-header">
                  <h3 class="card-title">세탁 주문량</h3>
                  <div class="card-tools">
                     <button type="button" class="btn btn-tool"
                        data-card-widget="collapse" title="Collapse">
                        <i class="fas fa-minus"></i>
                     </button>
                  </div>
               </div>
               <div class="card-body" style="height: 350px">
         <table class="table table-striped projects">
            <thead>
               <tr>
                  <th style="width: 20%">지점장</th>
                  <th style="width: 20%">지점</th>
                  <th>주문량</th>
                  <th style="width: 15%" class="text-center">상태</th>
               </tr>
            </thead>
            <tbody>
               <tr>
                  <td><a> 구건희</a></td>
                  <td><a>branch</a></td>
                  <td class="project_progress">
                     <div class="progress progress-sm">
                        <div class="progress-bar bg-yellow" role="progressbar" aria-valuenow="82" aria-valuemin="0" aria-valuemax="100" style="width: 82%"></div>
                     </div> <small> 82% Complete </small>
                  </td>
                  <td class="project-state"><span class="badge badge-warning">혼잡</span>
                  </td>
               </tr>
               <tr>
                  <td><a> 백관우</a></td>
                  <td><a>branch</a></td>
                  <td class="project_progress">
                     <div class="progress progress-sm">
                        <div class="progress-bar bg-green" role="progressbar" aria-valuenow="12" aria-valuemin="0" aria-valuemax="100" style="width: 12%"></div>
                     </div> <small> 12% Complete </small>
                  </td>
                  <td class="project-state"><span class="badge badge-success">원활</span>
                  </td>
               </tr>
               <tr>
                  <td><a> 이민호</a></td>
                  <td><a>branch</a></td>
                  <td class="project_progress">
                     <div class="progress progress-sm">
                        <div class="progress-bar bg-green" role="progressbar" aria-valuenow="35" aria-valuemin="0" aria-valuemax="100" style="width: 35%"></div>
                     </div> <small> 35% Complete </small>
                  </td>
                  <td class="project-state"><span class="badge badge-success">원활</span>
                  </td>
               </tr>
               <tr>
                  <td><a> 이어진</a></td>
                  <td><a>branch</a></td>
                  <td class="project_progress">
                     <div class="progress progress-sm">
                        <div class="progress-bar bg-red" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: 100%"></div>
                     </div> <small> 100% Complete </small>
                  </td>
                  <td class="project-state"><span class="badge badge-danger">초과</span>
                  </td>
               </tr>
      
            </tbody>
         </table>
               </div>

            </div>

         </div>
      </div>
   </div>
</body>
</html>