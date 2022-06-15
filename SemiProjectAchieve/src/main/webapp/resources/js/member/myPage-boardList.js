
// 전체선택
function selectAll(selectAll){
    const checkboxes = document.querySelectorAll('input[type="checkbox"]');
    checkboxes.forEach((checkbox)=>{checkbox.checked=selectAll.checked})


};

(function(){
    const deleteBtn = document.getElementById("deleteBtn"); 

    //member/delete?type=1&cBoard=107
    if(deleteBtn != null){// 버튼이 화면에 존재할때
        deleteBtn.addEventListener("click", function(){

            let url = "delete"; // 상대경로 형식으로 작성
            // 주소에 작성된 쿼리스트링

            // 1) 쿼리스트링에 존재하는 파라미터 모두 얻어오기
            //현재 /List?memNo=&type=1'

            //이후 /delete?type=1&cBoard=1
            //이후 /delete/board?type=1&cBoard=95
            const params = new URL(location.href).searchParams;

            // 2) 원하는 파라미터만 얻어와 변수에 저장
            const cBoard = "?cBoard="+params.get("cBoard");
            const cReply = "?cReply="+params.get("cReply");
            const type = "&type="+params.get("type");
            const memNo = "&memNo=" + params.get("memNo");

            // url 쿼리스트링 추가
            url += memNo + type + cBoard  ; // delete?type=1&cBoard=1
            // url += memNo + type + cReply  ; // delete?type=1&cReply=1

            if(confirm("정말로 삭제 하시겠습니까?")){
                location.href = url; // get 방식으로 url 요청

            }
        });

    }
})();