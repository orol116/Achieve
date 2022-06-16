<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>project-settings</title>

    <!-- 헤더, 푸터 위한 main.css -->
    <link rel="stylesheet" href="${contextPath}/resources/css/header-footer.css">
    
    <link rel="stylesheet" href="../resources/css/project-settings.css">

    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

    <link rel="stylesheet" href="${contextPath}/resources/css/FAQ.css">

    <!-- 글꼴 -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&family=Hahmlet:wght@100&family=Song+Myung&display=swap" rel="stylesheet">


    </style>

</head>
<body>
    <main>  
        <jsp:include page="/WEB-INF/views/common/header.jsp" />


		

        <div class="body">
            
                <div class="setting-main">

                    <div name="setting-header" id="infor-first">
                        프로젝트 설정 
                    </div>

                        <div name="setting-body" class="setting-body">
                            참여자 관리 (가입) <button id="member-pass"
                            onclick='location.href="${contextPath}/project/PJSign?projectNo=${param.projectNo}"'>변경</button>
                        </div>

                    
                        <div name="setting-body" class="setting-body">
                            참여자 관리 (차단, 탈퇴) <button id="member-control"
                            onclick='location.href="${contextPath}/member/dropMember?projectNo=${param.projectNo}"'>변경</button>
                        </div>
                  


                    
                        <div name="setting-body" class="setting-body">
                            프로젝트 공개
                        
                            <select name="project-public" id="project-public" onchange="showValue(this)">
                                <option value="Y">공개</option>
                                <option value="P">클래스명 공개</option>
                                <option value="N">비공개</option>

                            </select>
                                
                        </div>
                    

                    
                        <div name="setting-body" class="setting-body">
                                

                            클래스 이름 변경 <button id="nameChangeBtn">변경</button>
                            <input type="text" id="PJNameChange" name="PJNameChange"
                             maxlength="20" autocomplete="off">
                        </div>
                    

                    
                        <div name="setting-body" class="setting-body">
                            과제 관리 <button id="homework-control">변경</button>
                        </div>
                    

                    
                        <div name="setting-body" class="setting-body">
                            전체 쪽지 발송 <button id="text-all">발송</button>
                            
                        </div>
                        <textarea placeholder="전체 쪽지는 여기에 작성하세요." id="sendAll-text"></textarea>
                    

                    
                        <div name="setting-body" class="setting-body">
                            소개 수정 <button id="IntroEditBtn">수정</button>
                            
                        </div>
                        <textarea placeholder="바꿀 소개를 작성하세요." id="projectIntro">개발2팀입니당</textarea>

                        <div name="setting-body" class="setting-body">
                            공지 작성 <button id="write-notice" onclick="location.href='${contextPath}/board/write?mode=insert&type=2&projectNo=${param.projectNo}&cp=1'">작성</button>
                        </div>

                </div>
           
        </div>

        

    </main>




    <jsp:include page="/WEB-INF/views/common/footer.jsp" />

    <script> 
        // 댓글 관련 JS 코드에 필요한 값을 전역 변수로 선언

        // jsp 파일 : html, css, js, el, jstl 사용가능
        // js 파일  : js 만 사용 가능. 
        // -> 그렇기에 el을 사용하려면 여기 jsp에서 사용해야된다. 

        //** JS 코드에서 EL/JSTl을 작성하게 된다면 반드시 ""를 양쪽에 추가해야된다.**

        // 최상위 주소
        const contextPath = "${contextPath}"; 

        // 게시글 번호
        const boardNo = "${detail.boardNo}"; // "500"

        // 로그인한 회원 번호
        const loginMemberNo = "${loginMember.memberNo}";
        // -> 로그인 되어있으면 : "10"
        // -> 로그인 안되어있으면 : ""; (빈문자열)

        var projectNo = "${param.projectNo}";
        

    </script>
 
        

    
    <script src="${contextPath}/resources/js/PJSettings.js"></script>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

    

    


    
</body>
</html>