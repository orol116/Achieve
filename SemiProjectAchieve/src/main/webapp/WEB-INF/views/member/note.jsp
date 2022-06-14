<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Achieve</title>

    <link rel="stylesheet" href="${contextPath}/resources/css/header-footer.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/note.css">

    <script src="https://kit.fontawesome.com/35f111b89d.js" crossorigin="anonymous"></script>
    
    
</head>
<body>
    
    <main>

        <jsp:include page="/WEB-INF/views/common/header.jsp" />


        <section class="content">                

            <div id="notice">알림</div>
            
            
            <div id="notice-area">

                <c:choose>
                        
                    <c:when test="${empty note.noteList}">
                        <div class="notice-list">                                
                            <p>알림이 없습니다</p>
                        </div>
                    </c:when>

                    <c:otherwise>
                        
                        <!-- 쪽지는 list로 확인만 가능하게, 링크 연결 X / 현재 el 임시 작성 -->
                        <c:forEach var="project" items="${noteList}">
                            <div class="notice-list">
                                <div>${note.sendeNo}</div>
                                <p>${note.noteContent}</p>
                            </div>
                            <input type="hidden" name="noteNo" value="${note.noteNo}">
                        </c:forEach>
                        

                    </c:otherwise>

                </c:choose>

                <div class="notice-list">
                    <div>프로젝트 A</div>
                    <p>프로젝트 A 안내사항입니다 (프로젝트 A) <br>
                    4월 31일까지 제출 안된 내용 확인해주시고, 기간내에 반드시 제출해주세요. <br>
                    미 제출시 강제 편성됩니다.</p>
                </div>
                <div class="notice-list">
                    <div>프로젝트 B</div>
                    <p>이번주 스터디 과제 안내입니다</p>
                </div>
                <div class="notice-list">
                    <div>발신자일곱글자</div>
                    <p>공지 확인 부탁드립니다</p>
                </div>


            </div>
                

        </section>




    </main>
        
    <jsp:include page="/WEB-INF/views/common/footer.jsp" />



</body>
</html>