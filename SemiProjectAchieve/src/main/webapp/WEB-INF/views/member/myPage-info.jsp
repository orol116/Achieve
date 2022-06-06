<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Page</title>

    <link rel="stylesheet" href="${contextPath}/resources/css/main-style.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/myPage-style.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/sideMenu.css">

</head>
<body>

    <main>
        <header>
            <!-- 헤더 생기면 include 코드로 변경하기 -->
            <!-- <jsp:include page="/WEB-INF/views/common/header.jsp"> -->
            <img src="${contextPath}/resources/images/Achieve_logo.png" id="logo"><span>header : Achieve</span>
        </header>


        <!-- 마이페이지 -->
        <section class="myPage-content">

            <!-- 왼쪽 사이드 메뉴 -->
            <jsp:include page="/WEB-INF/views/member/sideMenu.jsp" />

            <!-- 마이페이지 - 메인 내용 -->
            <section class="myPage-main">
                <div id="myPage-detail">
                    <h1 id="myPage-title">내 정보</h1>

                    <form action="info" method="post" name="myPage-form" onsubmit="return infoUpdateValidate()">
                        <!-- onsubmit으로 input이 변화가 없으면 제출X -->
                            <div class="myPage-detail-row" id="memberEmail">
                                <div>${loginMember.memberEmail}</div> 
                            </div>
                            
                            <div class="myPage-detail-row" id="memberName">
                                <div>이름</div>            
                                <input class="myPage-detail-input" type="text" name="memberName">
                            </div>

                            <div class="myPage-detail-row" id="memberNickname">
                                <div>닉네임</div>            
                                <input class="myPage-detail-input" type="text" name="memberNickname">
                            </div>

                            <div class="myPage-detail-row" id="memberTel">
                                <div>핸드폰 번호</div>            
                                <input class="myPage-detail-input" type="text" name="memberTel">
                            </div>

                            <div class="myPage-detail-row">
                                <button id="modify">회원 정보 수정</button>
                            </div>

                    </form>
                </div>
            </section>
        </section>
    </main>

    <!-- footer 완성되면 주석 해제 -->
    <!-- <jsp:include page="/WEB-INF/views/common/footer.jsp"> -->

    <!-- myPage.js 연결 -->
    <script src="${contextPath}/resources/js/member/myPage-info.js"></script>
</body>
</html>