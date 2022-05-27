<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<body>
    <div class="row">
        <div class="col-12">
            <div class="card card-primary card-outline">
                <div class="card-header">
                    <h3 class="card-title">세탁상태 변경 확인</h3>
                    <div class="card-tools">

                        <div class="input-group input-group-sm">


                            

                            <span class="input-group-append">
                                <button class="btn btn-primary" type="button">
                                    저장

                                </button>
                            </span>

                        </div>
                    </div>
                </div>

                <div class="card table-responsive p-0">
                    <table class="table table-hover text-nowrap">
                        <thead>
                            <tr>
                                <th class="width10">주문번호</th>
                                <th class="width10">이름</th>
                                <th class="width15">주소</th>
                                <th class="width40">세탁물 변경전 상태</th>
                                <th class="width10">세탁물 변경후상태</th>
                                <th class="width10">배송예정일</th>

                            </tr>
                        </thead>
                        <tbody>
                            <tr class="mouseHover">
                                <td>11121</td>
                                <td>백관우</td>
                                <td>대전 유성구 반석동</td>
                                <td>세탁중</td>
                                <td>세탁완료</td>
                                <td>2일</td>

                            </tr>
                            <tr class="mouseHover">
                                <td>11122</td>
                                <td>구건회</td>
                                <td>대전 유성구 노은동</td>
                                <td>세탁중</td>
                                <td>세탁완료</td>
                                <td>2일</td>

                            </tr>
                        

                        </tbody>
                    </table>
                </div>

            </div>

        </div>
    </div>

</body>
