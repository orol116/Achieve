// 생년월일 입력 시 하이픈 자동 생성
function addHypen(obj) {
    var number = obj.value.replace(/[^0-9]/g, "");
    var bday = "";

    // 년 
    if(number.length < 5){
        return number;
    } else if(number.length < 7){ 
        // 6개의 숫자가 입력되었을 때 년(4자리) 뒤에 - 추가
        bday += number.substr(0,4);
        bday += "-";
        bday += number.substr(4);
    } else if(number.length < 9){ 
        // 8개의 숫자 입력되었을 때, 년(4자리)와 월(2자리) 뒤에 - 추가
        bday += number.substr(0,4);
        bday += "-";
        bday += number.substr(4,6);
        bday += "-";
        bday += number.substr(6);      
    } 
    obj.value = bday;
}

//////////////////////////////////////////////////////////////////////////////////////////////////

// *** 입력 정보(이름, 닉네임, 전화번호) 유효성 검사 ***

// 이름
const memberName = document.getElementsByName("memberName")[0];

// 생년월일
const memberBirthday = document.getElementsByName("memberBirthday")[0];

// *** 경고 출력, 입력값 무효, focus(), false 반환
function printAlert(el, message){
    alert(message);
    el.value = "";
    el.focus();
    return false;
}

function findIdInputValidate(){

    // 입력 여부 유효성 검사
    // 이름 
    if(memberName.value.length == 0){
        return printAlert(memberName, "이름을 입력해주세요.");
    }

    // 생년월일
    if(memberBirthday.value.length == 0){
        return printAlert(memberBirthday, "생년월일을 입력해주세요.")
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


function findIdList(){

    $.ajax({
        url : contextPath + "/findId",
        data : {"memberName" : memberName, "memberBirthday" : memberBirthday},
        type : "get",
        dataType : "JSON",
        success : function(idList){
            // idList : 반환 받은 아이디 목록

            // 메인화면 비우기
            const findIdContainer = document.getElementById("findId-container");
            findIdContainer.innerHTML = "";

            // idList에 저장된 요소에 하나씩 접근
            for(let id of idList){

                // 아이디 조회 div-container 생성
                const findIdList = document.createElement("div");
                findIdList.classList.add("findIdList");

                // 아이디 조회 리스트의 h1 생성
                const findIdListTitle = document.createElement("h1");
                findIdListTitle.innerText = "아이디 (E-mail)";

                // 아이디 데이터가 보이는 행 추가
                const idRow = document.createElement("div");
                idRow.classList.add("idList-row");
                idRow.innerText = id;

                // 아이디 조회 container에 h1, 행 추가
                findIdList.append(findIdListTitle, idRow);

            }
            
            
        }


    });

}
