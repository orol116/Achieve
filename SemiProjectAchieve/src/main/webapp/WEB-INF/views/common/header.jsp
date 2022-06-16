<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <header>
        <section>
            <a href="${contextPath}">
                <img src="${contextPath}/resources/images/Achieve_logo.png" id="home-logo">
            </a>
        </section>

        <section>
            <!-- 샘플 이미지 -->

            <c:if test="${!empty sessionScope.loginMember}">
                <img src="${contextPath}/resources/images/HEADER-001 (1).png" style="height: 100%; width: 100%;">
            </c:if>
            <c:if test="${empty sessionScope.loginMember}">
                <img src="${contextPath}/resources/images/002.png" style="height: 100%;" >
            </c:if>

           
            <c:if test="${!empty sessionScope.projectName}">
                
                <article class="project-title">

                    
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
