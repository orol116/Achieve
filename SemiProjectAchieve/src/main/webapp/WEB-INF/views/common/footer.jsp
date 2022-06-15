<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <footer>
        <p>
            <article id="footer">
                <a href="/SemiProjectAchieve/FAQ/firstFAQ">FAQ</a>
                <span> | </span>
                <a href="${contextPath}/board/main?type=99&projectNo=${param.projectNo}&cp=1"">1:1문의</a>
                <span> | </span>
                <a href="/SemiProjectAchieve/FAQ/terms/conditions">이용약관</a>
                <span> | </span>
                <a href="/SemiProjectAchieve/FAQ/personal/info">개인정보처리방침</a>
            </article>
            
            
            Copyright &copy; KH Information Educational Institute A-class DevTeam2
        </p>
    </footer>

	<%-- session에 message 속성이 존재하는 경우 alert창으로 해당 내용을 출력 --%>
	<c:if test="${ !empty sessionScope.message }">
		<script>
			alert("${message}");
			// EL 작성 시 scope를 지정하지 않으면
			// page -> request -> session -> application 순서로 검색하여
			// 일치하는 속성이 있으면 출력
		</script>
	
		<%-- message 1회 출력 후 session에서 제거 --%>
		<c:remove var="message" scope="session"/>
	</c:if>