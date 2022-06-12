(function() {

    const goToListBtn = document.getElementById("goToListBtn");

    if(goToListBtn != null) { // 목록으로 버튼이 화면에 있을때만 이벤트 추가
        goToListBtn.addEventListener("click", function() {

            const pathname = location.pathname; // 주소상에서 요청 경로 반환

            let url = pathname.substring(0, pathname.indexOf("/", 1))

            url += "/board/main?";

            const params = new URL(location.href).searchParams;
            const type = "type=" + params.get("type"); // type=1

            let cp;

            if (params.get("cp") != null) { 
                cp = "cp=" + params.get("cp"); 
            } else {
                cp = "cp=1";
            }

            url += type + "&" + cp;
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


// 첨부파일 이름, 파일 크기 출력
const attach =  document.getElementById("img0");
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

    deleteList.value = Array.from(deleteSet);

    return true;
}

