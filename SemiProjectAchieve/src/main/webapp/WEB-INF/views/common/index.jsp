<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Achieve</title>

    <link rel="stylesheet" href="${contextPath}/resources/css/main.css">

    <script src="https://kit.fontawesome.com/35f111b89d.js" crossorigin="anonymous"></script>
    
    
</head>
<body>
    
    <main>

        <!-- 내부 접근 절대 경로 -->
		<jsp:include page="/WEB-INF/views/common/header.jsp" />

        <!-- 마이페이지 -->
        <section class="myPage-content">

            <section class="content">
                
                <!-- 왼쪽 회원 사이드 메뉴 -->
                <section class="content-1">

                    <form action="member/login" method="post" name="login-form">
                    
                        <!-- 아이디(이메일)/비밀번호/로그인 -->
                        <fieldset id="id-pw-area">
                            
                            <section>
                                <input type="text" name="inputEmail" placeholder="아이디(이메일)">
                                <input type="password" name="inputPw" placeholder="비밀번호">
                            </section>
                            
                            
                            <section>
                                
                                <button>로그인</button>
                                
                            </section>
                            
                        </fieldset>
                                                   
                        <label for="saveId">
                            <input type="checkbox" name="saveId" ${chk}> 아이디 저장
                        </label>
            
            
                        <!-- 회원가입/ID,PW찾기 -->
                        <article id="signup-find-area">
                            
                            <button><a href="#">회원가입</a></button>
                            <button><a href="#">ID/PW 찾기</a></button>
                            
                        </article>
                        
                    </form>

                    <article id="main-project-area">
                        
                        <button><a href="#">프로젝트 만들기</a></button><br>
                        <button><a href="#">프로젝트 찾기</a></button>

        
                    </article>
            
            
                
                    
                
            
                </section>


                <!-- 우측 어취브 홍보 부분 -->
                <section class="content-2">
                
                    <img >

                    
                </section>

            </section>




            



            
        </section>

    </main>
        
    <jsp:include page="/WEB-INF/views/common/footer.jsp" />

    <!-- jQuery Library -->
    <script    src="https://code.jquery.com/jquery-3.6.0.min.js"    integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="    crossorigin="anonymous"></script>



</body>
</html>
