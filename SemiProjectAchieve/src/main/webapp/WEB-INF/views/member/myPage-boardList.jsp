<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<!-- map에 저장된 값을 각각 변수에 저장 -->
<c:set var = "pagination" value="${map.pagination}"/>
<c:set var = "boardList" value="${map.boardList}"/>
<c:set var = "memNick" value="${map.memNick}"/>
<c:set var = "pImage" value="${map.pImage}"/>
<c:set var = "replyList" value="${map.replyList}"/>
<c:set var = "listBoardCount" value="${map.listBoardCount}"/>
<c:set var = "listReplyCount" value="${map.listReplyCount}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>작성글</title>
    <link rel="stylesheet" href="${contextPath}/resources/css/myPage-inquire-main.css">

    <c:choose>
        <c:when test="${param.type==1}">
            <link rel="stylesheet" href="${contextPath}/resources/css/myPage-board.css"> 
        </c:when>

        <c:otherwise>
            <link rel="stylesheet" href="${contextPath}/resources/css/myPage-reply.css">
        </c:otherwise>
    </c:choose>
</head>    
<body>
    <main>

        <%-- <jsp:includ page = "/WEB-INF/views/common/header.jsp"> --%>
        <header>
            <!-- 클릭 시 메인페이지로 이동하는 로고 -->
            <section>
                <a href="${contextPath}">
                    <img src="${contextPath}/resources/images/Achieve_logo.png" id="home-logo">
                </a>
            </section>

            <section>
                <!-- form 내부 input태그 값을 서버 또는 페이지로 전달 -->
                <div id="team_name">팀 프로젝트 명</div>
            </section>

            <section>
                <div>
                    <a href="#">
                        <img src="${contextPath}/resources/images/favorit.png" id="fav-logo">
                    </a>
                </div>
                <div>
                    <a href="#">
                        <img src="${contextPath}/resources/images/note.png" id="note-logo">
                    </a>
                </div>
            </section>
    
        </header>
        <!-- 마이페이지- 내정보 -->
        <section class="myPage-content">
            <!-- 왼쪽 사이드 메뉴 -->
        
            <section class="left-side">
                <h1>마이페이지</h1>

                <ul class="list-group">
                    <li> <a href="${contextPath}/member/myPage/profile">회원정보 수정 </a> </li>
                    <li> <a href="${contextPath}/member/myPage/changePw">비밀번호 변경 </a> </li>
                    <li> <a href="${contextPath}/member/List?memNo=${param.memNo}&type=1">내가 쓴 글 보기 </a> </li>
                    <li> <a href="${contextPath}/member/List?memNo=${param.memNo}&type=2">내가 쓴 댓글 보기 </a> </li>
                    <li> <a href="${contextPath}/member/myPage/secession">회원 탈퇴 </a> </li>
                </ul>

            </section>
            
            <!-- 오른쪽 마이페이지 주요 내용 부분 -->
            <section class="myPage-main">
                <div class="myPage-first">
                    <a href="${contextPath}/member/myPage/profile"></a>
                    <c:choose>
                        <c:when test ="${!empty pImage}">
                            <img src="${contextPath}${pImage}" id="profile-logo"></a>
                        </c:when>
                   
                        <c:otherwise>
                        <img src="${contextPath}/resources/images/user.png" id="profile-logo"></a>
                        </c:otherwise>
                    </c:choose>
                </div>


            <c:choose>
                <c:when test="${param.type==1}">   
                    <div class="myPage-second">
                        <span class="myPage-nickname">${memNick}</span>
                        <span class="myPage-grade">등급 :</span>
                        <div class="myPage-info">
                            <span class="myPage-words">작성 글 : ${listBoardCount}</span>    
                        </div>
                    </div> 
                </c:when>
                               

                <c:otherwise>
                    <div class="myPage-second">
                        <span class="myPage-nickname">${memNick}</span>
                        <span class="myPage-grade">등급 :</span>
                        <div class="myPage-info">
                            <span class="myPage-reply">작성 댓글 : ${listReplyCount}</span>
                        </div>
                    </div> 
                </c:otherwise>
            </c:choose>    

                <!-- 쿼리스트링  -->
                <div class="myPage-third">
                    <div class="myPage-third1">
                    <a href="${contextPath}/member/List?memNo=${param.memNo}&type=1">작성글</a>
                    <a href="${contextPath}/member/List?memNo=${param.memNo}&type=2">작성댓글</a>
                    <a href="#">가입한 프로젝트 보기</a>
                    </div>
                    <div class="myPage-third2">
                    <a href="#">삭제한 게시글</a>
                    </div>
                </div>      

                <div class="list-wrapper">

                    <table class="list-table">
                        <form action="#" name="list-form">
                            <c:choose>
                                <c:when test="${param.type==1}">
                                    <thead>
                                        <tr>
                                            <th>선택</th>
                                            <th>글번호</th>
                                            <th>제목</th>
                                            <th>작성일</th>
                                            <th>조회</th>
                                        </tr>
                                    </thead>
                
                                    <tbody class="board-list">
                                        <c:choose>
                                            <c:when test="${empty boardList}" >
                                            <!-- 작성글 목록 조회 결과가 비어있다면 -->
                                                <tr>
                                                    <th colspan="5">작성글이 존재하지 않습니다.</th>
                                                </tr>
                                            </c:when>

                                            <c:otherwise>
                                            <!-- 작성글 목록 조회 결과가 비어있지않다면 -->
                                                <!-- 향상된 for문 처럼 사용 -->
                                                <c:forEach var ="board" items="${boardList}">
                                                    <tr>
                                                        <td><input type="checkbox" id="myCheckbox" name="chooseBoard"></td>
                                                        <td>${board.boardNo}</td>
                                                        <td><a href="#">${board.boardTitle}</a></td>
                                                        <td>${board.createDate}</td>
                                                        <td>${board.readCount}</td>
                                                    </tr>
                                                </c:forEach>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:when>
                            

                                    
                                    <c:otherwise>

                                        <thead>
                                            <tr>
                                                <th></th>
                                                <th id="head-content">댓글</th>
                                            </tr>
                                        </thead>
                    
                                        <tbody class="board-list">
                                            <c:choose>
                                                <c:when test="${empty replyList}" >
                                                <!-- 작성글 목록 조회 결과가 비어있다면 -->
                                                    <tr>
                                                        <th colspan="5">작성 댓글이 존재하지 않습니다.</th>
                                                    </tr>
                                                </c:when>

                                                <c:otherwise>
                                                <!-- 작성글 목록 조회 결과가 비어있지않다면 -->
                                                    <!-- 향상된 for문 처럼 사용 -->
                                                    <c:forEach var ="reply" items="${replyList}">
                                                        <tr>
                                                            <td class="list-chkbox">
                                                                <input type="checkbox" id="myCheckbox" name="chooseReply">
                                                            </td>
                                                            
                                                            <td id="reply-list-part">
                                                                <a href="#" >
                                                                    <div class="inner_list">${reply.replyContent}<br></div>
                                                                    <div class="comment-date">${reply.replyDate}<br></div>
                                                                    </c:forEach>

                                                                    <c:forEach var ="board" items="${boardList}">
                                                                        <div class="comment_title">${board.boardTitle}</div>
                                                                    </c:forEach>
                                                                </a>
                                                            </td>
                                                        </tr>
                                                    
                                                </c:otherwise>
                                            </c:choose>

                                    </c:otherwise>
                                </c:choose>
                            </form>
                        </tbody>
                    </table>
    
                </div>
    
                <div class="btn-area">
                    <div id="checkAll">
                        <input type="checkbox" value='selectall' onclick='selectAll(this)'>전체선택
                    </div>
                    <div>
                    <a class="insertBtn" id="deleteBtn">삭제</a>
                    <c:choose>
                        <c:when test="${param.type==1}">
                            <a class="insertBtn" href="${contextPath}/board/write?mode=insert&type=3&projectno=1&cp=">글쓰기</a>
                        </c:when>
                    </c:choose>
                    </div>
                </div>
    
                <div class="pagination-area">

                    <!-- pagination a태그에 사용될 공통주소 저장한 변수 선언 -->
                    <c:set var="url" value="List?type=${param.type}&cp="/>
                    <ul class="pagination">
                        <!-- 첫페이지로 이동 -->
                        <li><a href="${url}1">&lt;&lt;</a></li>

                        <!-- 이전목록 마지막 번호로 이동 -->
                        <li><a href="${url}${pagination.prevPage}">&lt;</a></li>
                        
                        <!-- 범위가 정해진 일반 for문 사용 -->
                        <c:forEach var="i" begin="${pagination.startPage}" end="${pagination.endPage}" step="1">
                            <c:choose>
                                <c:when test="${i == pagination.currentPage}">
                                    <li><a class="current">${i}</a></li>
                                </c:when>

                                <c:otherwise>
                                    <li><a href="${url}${i}">${i}</a></li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>

                        <!-- 다음 목록 시작 번호로 이동 -->
                        <li><a href="${url}${pagination.nextPage}">&gt;</a></li>

                        <!-- 끝 페이지로 이동-->
                        <li><a href="${url}${pagination.maxPage}">&gt;&gt;</a></li>
                        
                    </ul>
                </div>
            </section>
        </section>

    </main>

    <footer>
       
       <%-- <jsp:includ page = "/WEB-INF/views/common/header.jsp"> --%>
        <article>
            <a href="#">FAQ</a>
            <span>|</span>
            <a href="#">1:1문의</a>
            <span>|</span>
            <a href="#">이용약관</a>
            <span>|</span>
            <a href="#">개인정보취급방침</a>
        </article>
         <pre>서울특별시 중구 남대문로 120 대일빌딩 A강의장</pre>
         <pre>KH A-class 개발2팀</pre>
        
    </footer>


<script>
    const contextPath = "${contextPath}";
    const boardNo = "${detail.boardNo}";
    const loginMemberNo = "${loginMember.memberNo}";
    // -> 로그인 o -> "10";
    // -> 로그인 x -> "";
</script>
    


    <script src="${contextPath}/resources/js/member/myPage-boardList.js""></script>
</body>
</html>