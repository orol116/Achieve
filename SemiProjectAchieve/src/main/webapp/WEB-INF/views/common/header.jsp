<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <header>
        <section>
            <a href="${contextPath}">
                <img src="${contextPath}/resources/images/Achieve_logo.png" id="home-logo">
            </a>
        </section>

        <section>
            <!-- 프로젝트 타이틀 들어오는 자리 -->
            <c:if test="${!empty sessionScope.projectName}">
                
                <article class="project-title">

                    <!-- 조건:프로젝트 이름이 있으면 가져와서 el 세팅 / 임시세팅 -->
                    ${project.projectName}
                    
                </article>
            </c:if>
        </section>
        
        
        <section class="right-header">
            
            <c:if test="${!empty sessionScope.loginMember}">
                
                <a href="${contextPath}/note">
                    <button type="button" id="chat-btn" class="fa-solid fa-envelope"></button>
                    
                </a>
            
            </c:if>
            

        </section>

    </header>
