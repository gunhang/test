<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


    <div class="col-md-12">
        <div class="card card-primary card-outline eventTarget">
            
            <div class="mailbox-read-info">
                <div class="row">
                    <div class="col-6">
                        <p><strong> 안되안되안되요</strong></p>
                    </div>
                    <div class="col-6 row">
                        <div class="col-3 ">
                            <o><strong> 문의종류</strong></p>
                        </div>
                       
                        <div class="col-4">
                            <span class="mailbox-read-time float-right">15 Feb. 2015 11:03 PM</span>
                        </div>
                        <div class="col-5">
                            <p class="float-right">답변완료</p>
                        </div>
                       
                    </div>
                </div>
            </div>
            <div  style="display: none;" data-display="false" >
                <div class="mailbox-read-info">
                    <div class="row">
                        <div class="col-2">
                            <p> <strong> 첨부파일 </strong></p>
                        </div>
                        <div class="col-10">
                            <a href="asdasd.pdf" download="테스트" type="file">테스트</a>
                        </div>
                    </div>
                </div>
                <div class="mailbox-read-info">
                    <div class="mailbox-read-message">
                        <p>이어진 지각 <br> 지각</p>
                    </div>
                </div>
                <div class="mailbox-read-info">
                    A. 답변내용
                    <div class="mailbox-read-message">
                        <p>이어진 지각 <br> 지각</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-md-12">
        <div class="card card-primary card-outline eventTarget">
            
            <div class="mailbox-read-info">
                <div class="row">
                    <div class="col-6">
                        <p><strong> 안되안되안되요</strong></p>
                    </div>
                    <div class="col-6 row">
                        <div class="col-3 ">
                            <o><strong> 문의종류</strong></p>
                        </div>
                       
                        <div class="col-4">
                            <span class="mailbox-read-time float-right">15 Feb. 2015 11:03 PM</span>
                        </div>
                        <div class="col-5">
                            <p class="float-right">답변완료</p>
                        </div>
           
                    </div>
                </div>
            </div>
            <div  style="display: none;" data-display="false" >
                <div class="mailbox-read-info">
                    <div class="row">
                        <div class="col-2">
                            <p> <strong> 첨부파일 </strong></p>
                        </div>
                        <div class="col-10">
                            <a href="asdasd.pdf" download="테스트" type="file">테스트</a>
                        </div>
                    </div>
                </div>
                <div class="mailbox-read-info">
                    <div class="mailbox-read-message">
                        <p>이어진 지각 <br> 지각</p>
                    </div>
                </div>
                <div class="mailbox-read-info">
                    A. 답변내용
                    <div class="mailbox-read-message">
                        <p>이어진 지각 <br> 지각</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
       
  

<script>
    let target = document.querySelectorAll(".eventTarget")
   
    for(let i of target){
        i.addEventListener("click",function(){
        let content=this.children[1]
        if(content.dataset.display=="false"){
            this.children[1].style.display="block"
            content.dataset.display="true";
            return
        }
        content.dataset.display="false";
        this.children[1].style.display="none"

    })
    }
    
</script>


