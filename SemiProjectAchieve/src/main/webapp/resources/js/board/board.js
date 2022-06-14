const inputImage = document.getElementsByClassName("inputImage");
const attach = document.getElementById("img0");
const deleteSet = new Set();
const preview = document.getElementsByClassName("preview");
const deleteAttach = document.getElementsByClassName("deleteAttach")[0];

(function() {

    const goToListBtn = document.getElementById("goToListBtn");

    if(goToListBtn != null) { // 목록으로 버튼이 화면에 있을때만 이벤트 추가
        goToListBtn.addEventListener("click", function() {

            const pathname = location.pathname; // 주소상에서 요청 경로 반환

            let url = pathname.substring(0, pathname.indexOf("/", 1))

            url += "/board/main?";

            const params = new URL(location.href).searchParams;
            const type = "type=" + params.get("type"); // type=1
            const projectNo = "projectNo=" + params.get("projectNo");

            let cp;

            if (params.get("cp") != null) { 
                cp = "cp=" + params.get("cp"); 
            } else {
                cp = "cp=1";
            }

            url += type + "&" + projectNo + "&" + cp;
            location.href = url;
        });
    }

})();


// 검색창에 이전 검색기록 반영하기
(function(){
    const select = document.getElementById("search-key");
    const option = document.querySelectorAll("#search-key > option");
    const input = document.getElementById("search-query");

    if (select != null) { 

        const params = new URL(location.href).searchParams;
        const key = params.get("key");
        const query = params.get("query");

        input.value = query;

        for(let op of option) {
            if (op.value == key) {
                op.selected = true;
            }
        }
    }
})();

// 게시글 삭제 
(function(){
    const deleteBtn = document.getElementById("deleteBtn"); // 존재하지 않으면 null

    if(deleteBtn != null){ // 버튼이 화면에 존재할 때

        deleteBtn.addEventListener("click",function(){

            let url = "delete" // 상대경로 형식으로 작성
            
            // 1) 쿼리 스트링에 존재하는 파라미터 모두 얻어오기
            const params = new URL(location.href).searchParams;

            // 2) 원하는 파라미터만 얻어와 변수에 저장
            const no = "?no="+params.get("no"); // ?no=1508

            const type = "&type="+params.get("type"); // &type=1

            // url에 쿼리스트링 추가
            url+= no + type; // delete?no=1508&type=1

            if(confirm("정말로 삭제하시겠습니까?")){
                location.href= url; // get 방식으로 url에 요청
            }

        })
    }

})();


// 첨부파일 이름, 파일 크기 출력
attach.onchange = () => {
    const selectedFile = attach.files[0];
    const attachName = document.getElementById("attachName");
    const attachSize = document.getElementById("attachSize");

    attachName.innerText = selectedFile.name;
    attachSize.innerText = (selectedFile.size) / 1024 + 'KB';
};

// 게시글 작성 유효성 검사
function writeValidate(){

    const boardTitle = document.getElementsByName("boardTitle")[0];
    const boardContent = document.getElementById("boardContent");
    const boardOption = document.getElementById("boardOption");

    if (boardTitle.value.trim().length == 0) {
        alert("제목을 입력해주세요!");
        boardTitle.value = "";
        boardTitle.focus();
        return false;
    }
    if (boardContent.value.trim().length == 0) {
        alert("내용을 입력해주세요!");
        boardContent.value = "";
        boardContent.focus();
        return false;
    }
    if (boardOption.value.trim().length == 0){
        alert("게시판 종류를 선택해주세요!");
        boardOption.value = "";
        boardOption.focus();
        return false;
    }

    deleteList.value = Array.from(deleteSet);

    return true;
}

(function() {

    deleteAttach.addEventListener("click", function() {

        if (document.getElementById("img0").value != "") {
            document.getElementById("img0").value = "";
            document.getElementById("attachName").innerText = "";
            document.getElementById("attachSize").innerText = "";
        }

    })
        
})();
