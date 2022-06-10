<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>구성원 조회 페이지</title>
    <link rel="stylesheet" href="${contextPath}/resources/css/member-list-style.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/main.css">

    <script src="https://kit.fontawesome.com/51bf4ad8d5.js" crossorigin="anonymous"></script>

</head>
<body>

    <jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>

    <main>


        <section id="board-member">

          <jsp:include page="/WEB-INF/views/common/sideMenu.jsp"></jsp:include>



            <div id="member-content">

                <div id="member-count">
                    <h4>클래스 구성원</h4>
                    <span>총 *명</span>
                </div>
    
    
                <div id="member-list">

                    <div class="manager-list">
                        <span class="member-status">프로젝트 관리자</span> <br>

                        <div class="member-img">
                            <i class="fa-solid fa-circle fa-2x"></i>
                            
                            <div class="member-status-name"> 
                                <span class="status">관리자</span> <br>
                                <span class="name">관리자</span>
                            </div>
                        </div>
                    </div>

                    <div class="member-list">
                        <span class="member-status">프로젝트 팀원</span> <br>
                        <div class="member-img">
                            <i class="fa-solid fa-circle fa-2x"></i>
                            <div class="member-status-name"> 
                                <span class="status">팀원</span> <br>
                                <span class="name">팀원1</span>
                            </div>
                        </div>
                    </div>

                    <div class="member-list">
                        <div class="member-img">
                            <i class="fa-solid fa-circle fa-2x"></i>
                            <div class="member-status-name"> 
                                <span class="status">팀원</span> <br>
                                <span class="name">팀원1</span>
                            </div>
                        </div>
                    </div>

                    <div class="member-list">
                        <div class="member-img">
                            <i class="fa-solid fa-circle fa-2x"></i>
                            <div class="member-status-name"> 
                                <span class="status">팀원</span> <br>
                                <span class="name">팀원1</span>
                            </div>
                        </div>
                    </div>

                    <div class="member-list">
                        <div class="member-img">
                            <i class="fa-solid fa-circle fa-2x"></i>
                            <div class="member-status-name"> 
                                <span class="status">팀원</span> <br>
                                <span class="name">팀원1</span>
                            </div>
                        </div>
                    </div>

                    <div class="member-list">
                        <div class="member-img">
                            <i class="fa-solid fa-circle fa-2x"></i>
                            <div class="member-status-name"> 
                                <span class="status">팀원</span> <br>
                                <span class="name">팀원1</span>
                            </div>
                        </div>
                    </div>

                    <div class="member-list">
                        <div class="member-img">
                            <i class="fa-solid fa-circle fa-2x"></i>
                            <div class="member-status-name"> 
                                <span class="status">팀원</span> <br>
                                <span class="name">팀원1</span>
                            </div>
                        </div>
                    </div>

                    <div class="member-list">
                        <div class="member-img">
                            <i class="fa-solid fa-circle fa-2x"></i>
                            <div class="member-status-name"> 
                                <span class="status">팀원</span> <br>
                                <span class="name">팀원1</span>
                            </div>
                        </div>
                    </div>

                    <div class="member-list">
                        <div class="member-img">
                            <i class="fa-solid fa-circle fa-2x"></i>
                            <div class="member-status-name"> 
                                <span class="status">팀원</span> <br>
                                <span class="name">팀원1</span>
                            </div>
                        </div>
                    </div>

                    

    
                </div>

            </div>



        </section>
    </main>

    <jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
    
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

</body>
    <script src="${contextPath}/resources/js/member/member-list.js"></script>

</html>