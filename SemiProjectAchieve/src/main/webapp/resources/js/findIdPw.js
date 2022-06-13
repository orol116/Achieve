// 유효성 어뷰 확인할 객체 생성
checkObj = {
    "memberName" : false,
    "memberBirthday" : false,
    "memberEmail" : false,
    "sendEmail" : false // 이메일 인증번호 발송 체크
}


// 생년월일 입력 시 하이픈 자동 생성
function addHypen(obj) {

    let birth_len = obj.value.length;
    if (this.keyCode==8){
        obj.value = obj.value.slice(0,birth_len)
        return 0;
    }else if(birth_len==4 || birth_len==7){
        obj.value += '-';
    }
}

//////////////////////////////////////////////////////////////////////////////////////////////////

// *** 입력 정보(이름, 닉네임, 전화번호) 유효성 검사 ***

// 이름
const memberName = document.getElementsByName("memberName")[0];

// 생년월일
const memberBirthday = document.getElementsByName("memberBirthday")[0];

// 이메일
const memberEmail = document.getElementsByName("memberEmail")[0];

// *** 경고 출력, 입력값 무효, focus(), false 반환
function printAlert(el, message){
    alert(message);
    el.value = "";
    el.focus();
    return false;
}

function findInputValidate(){

    // 입력 여부 유효성 검사
    // 이름 
    if(memberName.value.length == 0){
        return printAlert(memberName, "이름을 입력해주세요.");
    }

    // 생년월일
    if(memberBirthday.value.length == 0){
        return printAlert(memberBirthday, "생년월일을 입력해주세요.")
    }

    // 이메일
    if(memberEmail){ // 이메일이 존재할 때
        if(memberEmail.value.length == 0){ // 이메일 입력 X
            return printAlert(memberEmail, "아이디(이메일)을 입력해주세요.")

        } else{ // 이메일 입력 O

            const regExp3 = /^[\w\-\_]{3,}@[\w\-\_]+(\.\w+){1,3}$/;

            if(!regExp3.test(memberEmail.value)){ // 이메일 형식 유효 X
                return printAlert(memberEmail, "이메일 입력 형식이 올바르지 않습니다.");

            } // 여기서 else가 없으면 그냥 통과되는 건가???? 
        }

    } else{

        return true; // 없으면 그냥 통과되도록
    }

    // 형식 유효성 검사
    // 이름
    const regExp = /^[a-zA-Z0-9가-힣]{2,10}$/;

    if(!regExp.test(memberName.value)){
        return printAlert(memberName, "이름은 한글 2~10글자 사이로 작성해주세요.");
    }

    // 생년월일
    const regExp2 = /^(19[0-9][0-9]|20\d{2})-(0[0-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$/;
    if(regExp2.test(memberBirthday.value)){
        return true;

    } else{
        return printAlert(memberBirthday, "생년월일 형식이 올바르지 않습니다.");
    }  

}


//////////////////////////////////////////////////////////////////////////////////////////////////

// 조건과 일치하는 회원 목록(아이디) 조회(ajax)
// 일단 쓰긴 썼는데 그냥 jsp 페이지 하나 더 만들었으면 되지 않았나 싶고????? 
// 어떤 게 더 나을 지는... 물어보고 결정합쉬다~
// html 아이디 속성값들을 많이 바꿔서 나중에 일일히 확인해야함!!! 


function findIdList(){
console.log(memberName.value)
console.log(memberBirthday.value)
    $.ajax({
        // 현재 페이지 : 메인페이지
        url : contextPath + "/findId",
        data : {"memberName" : memberName.value, "memberBirthday" : memberBirthday.value},
        type : "post",
        dataType : "JSON",
        success : function(idList){
            console.log(idList)
            // 메인화면 비우기
            const findIdContainer = document.getElementById("find-container");
            findIdContainer.innerHTML = "";

            // 아이디 조회 div-container 생성
            const findIdList = document.createElement("div");
            findIdList.classList.add("findIdList");


            if(idList != null){
            // 쫌 생각해보자... 그냥 forward 하는 게 더 나을 수도... 
            // idList : 반환 받은 아이디 목록


            // 아이디 조회 리스트의 h1 생성
            const findIdListTitle = document.createElement("h1");
            findIdListTitle.innerText = "아이디 (E-mail)";

            // 아이디 조회 div-container에 h1 추가
            findIdList.append(findIdListTitle);

            // idList에 저장된 요소에 하나씩 접근
            for(let id of idList){

                // 아이디 데이터가 보이는 행 추가
                const idRow = document.createElement("div");
                idRow.classList.add("idList-row");
                idRow.innerText = id.memberEmail;

                // 아이디 조회 container에 행 추가
                findIdList.append(idRow);

            } 

            findIdContainer.append(findIdList)
        } else{ // 아이디 리스트 X

            // 안내 문구 생성
            const h2 = document.createElement("h2");
            h2.innerText = "일치하는 회원이 없습니다.";

            findIdList.append(h2);

        }


        },
        error : function(req, status, error){
            console.log("error!");
            console.log(req.responseText);
        }

    });
}

/*
//////////////////////////////////////////////////////////////////////////////////////////////////

// 비밀번호 재설정을 위한 인증번호 발송(메일) 버튼에 이벤트 추가
const sendEmailBtn = document.getElementById("sendEmailBtn");

sendEmailBtn.addEventListener("click", function(){

    if(checkObj.memberEmail){ // 유효한 이메일이 작성되어 있을 경우에만 메일 보내기
        $.ajax({
            // 현재 페이지 : 메인페이지 / 보내고자 하는 페이지 : /sendEmail
            url : "sendEmail",
            data : {"inputEmail" : memberEmail.value},
            type : "GET",
            success : function(result){
                console.log("이메일 발송 성공");
                console.log(result);

                // 인증 버튼이 클릭되어 정상적으로 메일이 보내졌음을 checkOnbj에 기록
                checkObj.sendEmail = true;
            },
            error : function(){
                console.log("이메일 발송 실패");
            }
        });

        alert("인증 번호가 발송되었습니다. 이메일을 확인해주세요.")
    }
});
*/