<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>아이디 / 비밀번호 찾기</title>

    <link rel="stylesheet" href="${contextPath}/resources/css/main.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/findId-style.css">


</head>
<body>

    <main>
        <!-- header -->
        <jsp:include page="/WEB-INF/views/common/header.jsp"/>

        <!-- 아이디, 비번 찾기 -->
        <section class="findId-content">

            <!-- 아이디 찾기 -->
            <section class="findId-main">
                <div id="findId-container">
                    <h1 id="findId-title">아이디 / 비밀번호 찾기</h1>

                    <form action="findIdSubmit" method="post" name="findId-form" onsubmit="return findIdInputValidate()">
                        <!-- onsubmit으로 input이 변화가 없으면 제출X -->
                            
                            <div class="findId-detail-row" id="memberName">
                                <div>이름</div>            
                                <input class="findId-detail-input" name="memberName" type="text" placeholder="이름을 입력해주세요.">
                            </div>

                            <div class="findId-detail-row" id="memberBirthday">
                                <div>생년월일</div>            
                                <input class="findId-detail-input" name="memberBirthday" type="text" onkeyup="addHypen(this);" placeholder="- 없이 8자리">
                            </div>

                            <!-- 아이디 찾기는 ajax를 통해 비동기 조회 -->
                            <!-- 비밀번호 찾기는 메일 발송 -->
                            <div class="findId-button-area">
                                <button id="findIdBtn">아이디 찾기</button>
                                <button id="findPwBtn">비밀번호 찾기</button>
                            </div>
                    </form>
                </div>
            </section>
        </section>
    </main>

    <jsp:include page="/WEB-INF/views/common/footer.jsp"/>

    <!-- jQuery 추가 -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

    <!-- JS 코드에 필요한 값을 전역 변수로 선언 -->
    <script>
        // 최상위 주소
        const contextPath = "${contextPath}";

    </script>

    <!-- findIdPw.js 연결 -->
    <script src="${contextPath}/resources/js/member/findIdPw.js"></script>

</body>
</html>