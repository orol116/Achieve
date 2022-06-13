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

            <div id="notice">쪽지</div>
            
            
            <div id="notice-area">

                <div class="notice-list"><p>프로젝트 A 안내사항입니다 (프로젝트 A) <br>
                    4월 31일까지 제출 안된 내용 확인해주시고, 기간내에 반드시 제출해주세요. <br>
                    미 제출시 강제 편성됩니다.</p>
                </div>
                <div class="notice-list"><p>보낸 이 : 팀원1 (프로젝트 A)<br>
                    이 부분 이렇게 하면요?</p>
                </div>
                <div class="notice-list"><p>보낸 이 : 팀원2 (BB 팀플) <br>
                    수정했어요</p>
                </div>

            </div>
                

        </section>




    </main>
        
    <jsp:include page="/WEB-INF/views/common/footer.jsp" />



</body>
</html>