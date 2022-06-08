// 유효성 검사 여부를 기록할 객체 생성
const checkObj = {
    "memberEmail"    :false,
    "memberPw"       :false,
    "memberPwConfirm":false,
    "memberName" :false,
    "memberBirthYears" :false,
    "memberBirthMonth" :false,
    "memberBirthDays" :false,
    "memberTel"      :false
};



// 아이디 유효성 검사
const memberEmail = document.getElementById("memberEmail");
const IdMessage = document.getElementById("idMessage");

memberEmail.addEventListener("input", function(){
    // 입력이 되지 않은 경우
    if(memberEmail.value.length==0){
        idMessage.innerText="아이디를 입력해주세요.";
        idMessage.classList.remove("confirm");
        idMessage.classList.remove("error");

        checkObj.memberEmail = false; // 유효하지 않음을 기록
        return;
    }

    // 입력된 경우
    const regExp =/^[\w\-\_]{4,}@[\w\-\_]+(\.\w+){1,3}$/;
    // + : 1개 이상. {1,}이렇게 사용도 가능
    if(regExp.test(memberEmail.value)){

        $.ajax({
            url:"idDupCheck", // 필수 속성 url
            // 현재 주소 : /community2/member/signUp
            // 상대경로 : /community2/member/IdDupCheck
            date : { "memberEmail":memberEmail.value},
            // data 속성 : 비동기 통신 시 서버로 전달할 값을 작성(JS객체 형식)
            // -> 비동기 통신 시 input에 입력된 값을 "memberEmail" 이라는
            // key 값(파라미터)으로 전달

            type : "GET", // 데이터 전달 방식

            success : function(result){
                // 비동기 통신(ajax)가 오류 없이 요청/응답을 성공한 경우

                // 매개변수 result : servlet에서 출력된 result 값이 담겨있다.

                if(result == 1){ // 중복 O

                    idMessage.innerText="이미 사용 중인 아이디입니다.";
                    idMessage.classList.add("error");
                    idMessage.classList.remove("confirm");
                    checkObj.memberEmail = false; // 유효함을 기록

                }else{ //중복 x
                    idMessage.innerText="사용 가능한 아이디입니다.";
                    idMessage.classList.add("confirm");
                    idMessage.classList.remove("error");
                    checkObj.memberEmail = true; // 유효함을 기록
                }
            },

            error : function(){
                // 비동기 통신(ajax)중 오류가 생기는 경우
                console.log("에러발생");
            }   
        });
    }else{
        idMessage.innerText="이메일 형식이 올바르지 않습니다. .";
        idMessage.classList.add("error");
        idMessage.classList.remove("confirm");
        checkObj.memberEmail = false; // 유효하지 않음을 기록
    }
});

// 비밀번호 유효성 검사



// 이름 유효성 검사
const memberName = document.getElementById("memberName");
const nameMessage = document.getElementById("nameMessage");

memberName.addEventListener("input",function(){

    // 입력되지 않은 경우
    if(memberName.value.length==0){
        NameMessage.innerText = "영어/숫자/한글 2~10글자 사이로 작성해주세요.";
        NameMessage.classList.remove("confirm");
        NameMessage.classList.remove("error");

        checkObj.memberName = false;
        return;
    }

    const regExp = /^[a-zA-Z0-9가-힣]{2,10}$/;

    if(regExp.test(memberName.value)){

        NameMessage.innerText="사용가능한 닉네임 입니다. .";
        NameMessage.classList.add("confirm");
        NameMessage.classList.remove("error");
        checkObj.memberName = true;
        
    }else{
        NameMessage.innerText="닉네임 형식이 올바르지 않습니다. .";
        NameMessage.classList.add("error");
        NameMessage.classList.remove("confirm");
        checkObj.memberName = false;

    }

});

// 생년월일 유효성 검사



// 전화번호 유효성 검사
const memberTel = document.getElementById("memberTel");
const telMessage = document.getElementById("telMessage");

memberTel.addEventListener("input",function(){
    // 입력이 되지 않은 경우
    if(memberTel.value.length ==0){
        
        telMessage.innerText="전화번호를 입력해주세요.(- 제외)";

        telMessage.classList.remove("confirm");
        telMessage.classList.remove("error");

        checkObj.memberTel = false; // 유효하지 않은 상태임을 기록

        return;
    }

    // 전화번호 정규식
    const regExp = /^0(1[01679]|2|[3-6][1-5]|70)\d{3,4}\d{4}$/;

    if(regExp.test(memberTel.value)){ // 유효한 경우

        telMessage.innerText = "유효한 형식입니다.";
        telMessage.classList.add("confirm");
        telMessage.classList.remove("error");
        checkObj.memberTel = true; // 유효한 상태임을 기록

    }else{ // 유효하지 않은 경우
        
        telMessage.innerText = "전화번호 형식이 올바르지 않습니다.";
        telMessage.classList.add("error");
        telMessage.classList.remove("confirm");
        checkObj.memberTel = false; // 유효하지 않은 상태임을 기록
    }
});