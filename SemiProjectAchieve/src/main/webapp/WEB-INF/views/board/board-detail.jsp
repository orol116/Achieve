<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>board - detail</title>

    <!-- 헤더, 푸터 위한 main.css -->
    <link rel="stylesheet" href="${contextPath}/resources/css/main.css">

    <link rel="stylesheet" href="${contextPath}/resources/css/board/board-detail-style.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/board/reply-style.css">

</head>
<body>
    <main>
        <!-- header -->
        <jsp:include page="/WEB-INF/views/common/header.jsp"/>

        









        <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

        <jsp:include page="/WEB-INF/views/board/reply.jsp"/>

    </main>
    
    <script src="${contextPath}/resources/js/board/reply.js"></script>

</body>
</html>
