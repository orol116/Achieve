// 작성글 전체선택
function selectAll(selectAll)  {
    const checkboxes  = document.getElementsByName('chooseBoard');
        
    checkboxes.forEach((checkbox) => {checkbox.checked = selectAll.checked})
      
    
};


// 작성 댓글 전체선택
function selectAll(selectAll)  {
    const checkboxes = document.getElementsByName('chooseReply');

    checkboxes.forEach((checkbox) => {
        checkbox.checked = selectAll.checked;
    })
};