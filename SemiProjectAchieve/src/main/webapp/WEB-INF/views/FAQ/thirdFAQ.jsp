<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FAQ-project-Settings</title>

    <!-- 헤더, 푸터를 위한 css -->
    <link rel="stylesheet" href="${contextPath}/resources/css/header-footer.css">

    <!-- 사이드바 css 포함 -->
    <link rel="stylesheet" href="${contextPath}/resources/css/FAQ.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">


    <style>
    .selectBtn:nth-child(3){
    border: 1px solid #0e194e;
    background-color: #0e194e;
    color: white;
    }

    .selectBtn:nth-child(-n+2){
    border: 1px solid black;
    background-color: white;
    color: black;
    }

    .selectBtn:nth-child(-n+2):hover{
    background-color: #0e194e;
    color: white;
    }       
    </style>

</head>
<body>
    <main>  
        <jsp:include page="/WEB-INF/views/common/header.jsp" />
        

        <section class="content">
            <jsp:include page="/WEB-INF/views/common/sideMenu.jsp"/>

            <section class="main">

                <div id="FAQ-container">

                    <div id="FAQ-main">
                        <div id="select-area">
                            <button class="selectBtn" onclick="location.href='/SemiProjectAchieve/FAQ/firstFAQ'">자주 묻는 질문</button>
                            <button class="selectBtn" onclick="location.href='/SemiProjectAchieve/FAQ/secondFAQ'">프로젝트 생성/관리</button>
                            <button class="selectBtn" onclick="location.href='/SemiProjectAchieve/FAQ/thirdFAQ'">로그인/가입</button>

                        </div>
        
        
                        <ul class="FAQ">
                            <li>
                                <input type="checkbox" id="FAQ-1">
                                <label for="FAQ-1">계정을 탈퇴하고 싶어요.</label>
                                <div>
                                    <p>테스트중입니다. 프로젝트 화면 나오고 하면 추가할께요!!</p>
                                    <p>테스트중입니다. 프로젝트 화면 나오고 하면 추가할께요!!</p>
                                </div>
                            </li>
        
                            <li>
                                <input type="checkbox" id="FAQ-2">
                                <label for="FAQ-2">전화번호 외 다른 정보는 어떻게 등록하나요?</label>
                                <div>
                                    <p>테스트중입니다. 프로젝트 화면 나오고 하면 추가할께요!!</p>
                                    <p>테스트중입니다. 프로젝트 화면 나오고 하면 추가할께요!!</p>
                                    <p>테스트중입니다. 프로젝트 화면 나오고 하면 추가할께요!!</p>
                                </div>
                            </li>
        
                            <li>
                                <input type="checkbox" id="FAQ-3">
                                <label for="FAQ-3">계정을 탈퇴하고 싶어요.</label>
                                <div>
                                    <p>테스트중입니다. 프로젝트 화면 나오고 하면 추가할께요!!</p>
                                    <p>테스트중입니다. 프로젝트 화면 나오고 하면 추가할께요!!</p>
                                    <p>테스트중입니다. 프로젝트 화면 나오고 하면 추가할께요!!</p>
                                </div>
                            </li>
        
                            <li>
                                <input type="checkbox" id="FAQ-4">
                                <label for="FAQ-4">계정을 탈퇴하고 싶어요.</label>
                                <div>
                                    <p>테스트중입니다. 프로젝트 화면 나오고 하면 추가할께요!!</p>
                                    <p>테스트중입니다. 프로젝트 화면 나오고 하면 추가할께요!!</p>
                                    <p>테스트중입니다. 프로젝트 화면 나오고 하면 추가할께요!!</p>
                                </div>
                            </li>
                        </ul>  
                </div>

            </section>
        </section>
    </main>

    <jsp:include page="/WEB-INF/views/common/footer.jsp" />
 
</body>
</html>