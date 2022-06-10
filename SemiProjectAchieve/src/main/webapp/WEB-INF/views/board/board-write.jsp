<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시판</title>

    <link rel="stylesheet" href="${contextPath}/resources/css/board_css/header.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/board_css/board-write-style.css">

    <script src="${contextPath}/resources/js/board/ckeditor/ckeditor.js"></script>

</head>
<body>

    <main>

        <header>
            <img src="${contextPath}/resources/images/Achieve_logo.png" id="logo"><span>OOO 프로젝트</span>
        </header>

        <form action="#" enctype="multipart/form-data" method="POST" class="board-write">

            <div class="title-area">
                <!-- 제목 -->
                <h1 class="board-title">
                    <input type="text" name="boardTitle" placeholder="제목을 입력해주세요.">
                </h1>
            </div>

            <div class="content-area">


                <div class="add-content">

                    <label for="img0">첨부파일
                        <img class="preview">
                    </label>
                    <input type="file" class="inputImage" id="img0" name="0" accept="image/*">

                </div>


                <!-- 내용 -->
                <div class="board-content">
                    <textarea name="boardContent" id="boardContent"></textarea>
                    <script>
                    CKEDITOR.replace('boardContent', {height: 500});
                    </script>

                </div>

                <!-- 게시판 종류 선택 기능 삽입하기 -->
                <!-- <select name="board-type" id="board-type">

                </select> -->


                <div class="bottom-area">

                    <!-- <label for="secret-content"><input type="checkbox" id="secret-content"> 비밀글</label> -->


                    <!-- 버튼 영역 -->
                    <div class="board-btn-area">
                        <button type="submit" id="writebtn">등록</button>
                        <button type="submit" id="writebtn">임시저장</button>
                        <button type="button" id="goToListBtn">목록으로</button>
                    </div>

                </div>

                <!-- 숨겨진 값(hidden) -->
                <!-- 동작 구분 -->
                <input type="hidden" name="mode" value="insert">

                <!-- 게시판 구분 -->
                <input type="hidden" name="type" value="1">

            </div>
        </form>


        
    </main>



</body>
</html>