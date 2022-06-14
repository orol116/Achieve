<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Page - changePw</title>

     <!-- 헤더, 푸터 위한 main.css -->
     <link rel="stylesheet" href="${contextPath}/resources/css/header-footer.css">

     <link rel="stylesheet" href="${contextPath}/resources/css/myPage-style.css">
     <link rel="stylesheet" href="${contextPath}/resources/css/myPage-sidebar.css">

    <!-- 사이드바 아이콘 사용을 위한 링크 -->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">     
 
</head>
<body>

    <!-- 비밀번호 변경 -->

    <main>
        <!-- header -->
        <jsp:include page="/WEB-INF/views/common/header.jsp"/>


        <!-- 마이페이지 -->
        <section class="myPage-content">

            <!-- 왼쪽 사이드 메뉴 -->
            <jsp:include page="/WEB-INF/views/member/sideMenu.jsp" />

            <!-- 마이페이지 - 메인 내용 -->
            <section class="myPage-main">
                <div id="myPage-detail">
                    <h1 id="myPage-title">비밀번호 변경</h1>

                    <form action="changePwSubmit" method="post" name="myPage-form" onsubmit="return pwUpdateValidate()">
                        <!-- onsubmit으로 input이 변화가 없으면 제출X -->
                            
                            <div class="myPage-detail-row" id="currentPw">
                                <div>현재 비밀번호</div>            
                                <input class="myPage-detail-input" type="password" name="currentPw" maxlength="30">
                            </div>

                            <div class="myPage-detail-row" id="newPw">
                                <div>새로운 비밀번호</div>            
                                <input class="myPage-detail-input" type="password" name="newPw" maxlength="30">
                            </div>

                            <div class="myPage-detail-row" id="newPwCheck">
                                <div>새로운 비밀번호 확인</div>            
                                <input class="myPage-detail-input" type="password" name="newPwCheck" maxlength="30">
                            </div>

                            <div class="myPage-detail-row" id="newPwCheck">
                                <div>보안문자 입력</div>            
                                <span>보안문자</span><input id="confirmWord" type="text">
                            </div>

                            <div class="myPage-detail-row">
                                <button id="modify">비밀번호 변경</button>
                            </div>

                    </form>
                </div>
            </section>
        </section>
    </main>

    <jsp:include page="/WEB-INF/views/common/footer.jsp"/>

    <!-- myPage.js 연결 -->
    <script src="${contextPath}/resources/js/member/myPage-changePw.js"></script>
</body>
</html>