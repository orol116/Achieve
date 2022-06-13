<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<c:set var="boardName" value="${map.boardName}" />
<c:set var="pagination" value="${map.pagination}" />
<c:set var="boardList" value="${map.boardList}" />


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${boardName}</title>

    <link rel="stylesheet" href="${contextPath}/resources/css/header-footer.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/board_css/sideMenu.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/board_css/board-main-style.css">

    <script src="https://kit.fontawesome.com/a2e8ca0ae3.js" crossorigin="anonymous"></script>
</head>
<body>

    <main>

		<jsp:include page="/WEB-INF/views/common/header.jsp" />

        <div class="div-sec">

            <!-- 왼쪽 사이드 메뉴 -->
            <section class="myPage-sideMenu">
                <div id="sideMenu-list">

                    <div class="sideMenu-list-row project-main"><a href="${contextPath}/board/main?type=1">000 프로젝트</a></div>

                    <c:forEach var="boardType" items="${boardTypeList}">
                        <div class="sideMenu-list-row"><a href="${contextPath}/board/main?type=${boardType.boardCode}">${boardType.boardName}</a></div>
                    </c:forEach>

                    <br>
                    <div class="sideMenu-list-row"><a href="#">내가 쓴 글</a></div>
                    <div class="sideMenu-list-row"><a href="#">내가 쓴 댓글</a></div>
                    <div class="sideMenu-list-row"><a href="#">마이페이지</a></div>
                </div>
            </section>

            <c:if test="${!empty param.key}">
                <c:set var="sURL" value="&key=${param.key}&query=${param.query}" />
            </c:if>

            <section class="board-list">
                
                <div id="board-area">

                    <h1 class="board-name">${boardName}</h1>

                    <c:if test="${!empty param.key}">
                        <h3 style="margin-left: 30px;"> "${param.query}" 검색 결과</h3>
                    </c:if>


                    <div class="btn-area">
                        <c:if test="${param.type != 1}">
                            <button id="insertBtn" onclick="location.href='write?mode=insert&type=${param.type}&cp=${param.cp}'">글쓰기</button>
                        </c:if>
                    </div>

                    <div class="list-wrapper">
                        <table class="list-table">
        
                            <thead>
                                <tr>
                                    <th>글 번호</th>
                                    <th style="text-align: center;">제목</th>
                                    <th>작성자</th>
                                    <th>작성일</th>
                                    <th>조회수</th>
                                </tr>
                            </thead>
        
                            <tbody>
                                <c:choose>
                                    <c:when test="${empty boardList}">
                                        <tr>
                                            <th colspan="5">게시글이 존재하지 않습니다.</th>
                                        </tr>
                                    </c:when>

                                    <c:otherwise>
                                        <c:forEach var="board" items="${boardList}">
                                            <tr>
                                                <td>${board.boardNo}</td>
                                                <td>${board.boardTitle}</td>
                                                <td>${board.memberNickname}</td>
                                                <td>${board.createDate}</td>
                                                <td>${board.readCount}</td>
                                            </tr>
                                        </c:forEach>
                                    </c:otherwise>
                                </c:choose>
                            </tbody>
                        </table>
                    </div>
        
        

                    <div class="pagination-area">

                        <c:set var="url" value="main?type=${param.type}&cp="/>

                        <ul class="pagination">

                            <li><a href="${url}1${sURL}">&lt;&lt;</a></li>
                            <li><a href="${url}${pagination.prevPage}${sURL}">&lt;</a></li>
        
                            <c:forEach var="i" begin="${pagination.startPage}" end="${pagination.endPage}" step="1">

                                <c:choose>
                                    <c:when test="${i == pagination.currentPage}">
                                        <li><a class="current">${i}</a></li>
                                    </c:when>

                                    <c:otherwise>
                                        <li><a href="${url}${i}${sURL}">${i}</a></li>
                                    </c:otherwise>
                                </c:choose>

                            </c:forEach>
        
                            <li><a href="${url}${pagination.nextPage}${sURL}">&gt;</a></li>
                            <li><a href="${url}${pagination.maxPage}${sURL}">&gt;&gt;</a></li>
                        </ul>
                    </div>

                         
            </div>

    
    
                <form action="#" method="get" id="boardSearch">
                    <input type="hidden" name="type" value="${param.type}">
    
                    <select name="key" id="search-key">
                        <option value="t">제목</option>
                        <option value="c">내용</option>
                        <option value="tc">제목 + 내용</option>
                        <option value="w">작성자</option>
                    </select>
    
                    <input type="text" name="query" id="search-query" placeholder="검색어를 입력해주세요.">
    
                    <button>검색</button>
    
                </form>
    
            </section>

        </div>

    </main>

    <jsp:include page="/WEB-INF/views/common/footer.jsp"/>

    <script src="${contextPath}/resources/js/board/board.js"></script>
    
</body>
</html>