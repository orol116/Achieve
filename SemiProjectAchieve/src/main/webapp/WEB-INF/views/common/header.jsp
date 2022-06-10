<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <header>
        <section>
            <a href="${contextPath}">
                <img src="${contextPath}/resources/images/Achieve_logo.png" id="home-logo">
            </a>
        </section>

        <section>
            <article class="project-title">

            <!-- 나중에 프로젝트 타이틀 들어오는 자리 -->

            <!-- 조건:param에 프로젝트 이름이 있으면 가져와서 h2로 세팅 -->
                
            </article>
        </section>
        
        
        <section class="right-header">
            
            <c:if test="${!empty sessionScope.loginMember}">
                
                <a href="">
                    <button type="button" id="chat-btn" class="fa-solid fa-envelope"></button>
                    
                </a>
            
            </c:if>
            

        </section>

    </header>
