<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

    <section class="sideMenu">
        <div class="menu">

            <div id="projectName"><a href="${contextPath}/board/main?type=1&projectNo=${param.projectNo}">${projectName}</a></div>
            <ul>
                <li><a href="${contextPath}/member/myPage/profile" class="item"><div>프로필</div></a></li>
                <li><a href="${contextPath}/member/myPage/info" class="item"><div>개인정보변경</div></a></li>
                <li><a href="${contextPath}/member/list?projectNo=${param.projectNo}" class="item"><div>구성원</div></a></li>
                <li><a href="${contextPath}/board/main?type=4&projectNo=${param.projectNo}&cp=1" class="item"><div>과제</div></a></li>
                <li><a href="${contextPath}/board/main?type=1&projectNo=${param.projectNo}&cp=1" class="item"><div>공지사항</div></a></li>
                <li><a href="${contextPath}/board/write?mode=insert&type=3&projectNo=${param.projectNo}&cp=1" class="item"><div>글작성</div></a></li>
            </ul>
        </div>
    </section>
