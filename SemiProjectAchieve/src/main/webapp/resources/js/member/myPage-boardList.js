// 작성글 전체선택
function selectAll(selectAll)  {
    const checkboxes 
         = document.getElementsByName('chooseBoard');
    
    checkboxes.forEach((checkbox) => {
      checkbox.checked = selectAll.checked;
    })
};


// 작성 댓글 전체선택
function selectAll(selectAll)  {
    const checkboxes 
            = document.getElementsByName('chooseReply');

    checkboxes.forEach((checkbox) => {
        checkbox.checked = selectAll.checked;
    })
};



// 삭제 버튼 눌렀을 때 - 즉시 실행함수
(function(){
    const deleteBtn = document.getElementById("deleteBtn");

    // 체크박스 선택 되지않았을 때
    const checkbox = document.getElementById('myCheckbox')

        checkbox.addEventListener('change', (event) => {
        if (!event.currentTarget.checked) {
        //     alert('checked');
        //   } else {
            alert('삭제할 글을 선택해주세요.');
        }
        })

        deleteBtn.addEventListener("click", function(){
        // /member/List/delete?no=1&type=

        let url = "delete"; // 상대경로 형식으로 작성

        // 주소에 작성된 쿼리스트링에서 필요한 파라미터만 얻어와서 사용
        // 1) 쿼리스트링에 존재하는 파라미터 모두 얻어오기
        const params = new URL(location.href).searchParams;

        // 2) 원하는 파라미터만 얻어와 변수에 저장
        const no = "?no" + params.get("no"); // ?no=1

        const type ="&type=" + params.get("type") ; // &type=1

        //url에 쿼리스트링 추가
        url += no + type; // delete?no=1&type=1

        if(confirm("정말로 삭제 하시겠습니까?")){
            location.href = url; // get방식으로 url에 요청
        }
        // 삭제버튼 눌렀을 때
        //http://localhost:10005/SemiProjectAchieve/member/delete?nonull&type=1

        });


  
})();

