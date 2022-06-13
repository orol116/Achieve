
// 프로젝트 이름 유효성 검사
const regExp = /^[a-zA-Z0-9가-힣-_/]{2,20}/;

const nameChangeBtn = document.getElementById("nameChangeBtn");


// 프로젝트 이름변경 확인 O // 유효성검사 추가 중복되는 이름 거르기
nameChangeBtn.addEventListener("click", function(){

   const PJNameChange = document.getElementById("PJNameChange");

   PJNameChange.style.display = "block";

   nameChangeBtn.innerText = "적용";

   nameChangeBtn.addEventListener("click", function(){



    console.log(PJNameChange.value);

    

    });
   




});


// 전체 쪽지 값 확인 null 값 ok
document.getElementById("text-all").addEventListener("click", function(){

    const sendAll = document.getElementById("sendAll-text");

    console.log(sendAll.value);

    if(sendAll.value == ""){
        alert("전송할 내용이 없습니다!");
    }

});

// 소개 변경 확인 소개글 지우기 확인
document.getElementById("intro-edit").addEventListener("click", function(){

    const introText = document.getElementById("intro-text");

    console.log(introText.value);

    if(introText.value == ""){
    var result = confirm("소개글을 모두 지우시겠습니까?");

    if(result){
        introText.value == "";
        
    }else{
        return;
    }


    }
    
});





// 공개 여부 변경 확인
const selectPublic = document.getElementById("project-public");

const showValue = (target) =>{

    console.log(target.value);

    alert(target.value + "로 변경됩니다.");

    const openStatus = (target.options[target.selectedIndex].text);

    console.log("openStatus" + openStatus);


    if(openStatus == "공개"){

        openStatus.value == "Y";
    }

    if(openStatus == "클래스명 공개"){

        openStatus.value == "P"
    }

    if(openStatus == "비공개"){

        openStatus.value == "N"

    }

    $.ajax({
        url : "openStatusChange",
        data : {"openStatus" : openStatus.value},
        type : "GET",
        success : function(){

            alert(target.value + "로 변경됩니다.");

        },
        error : function(){
            console.log("에러발생");
        }
    });

};

// 









