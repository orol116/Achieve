package edu.kh.Achieve.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.kh.Achieve.member.model.service.MemberService;
import edu.kh.Achieve.member.model.vo.Member;

@WebServlet("/member/myPage/*") // myPage로 시작하는 모든 요청을 받음
public class MyPageController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String uri = req.getRequestURI();
		String contextPath = req.getContextPath();
		String command = uri.substring( (contextPath + "/myPage/").length() );
		
		MemberService service = new MemberService();
		
		try {
			
			if(command.equals("info")) { // 내 정보 수정
				// 파라미터 얻어오기(이름, 닉네임, 핸드폰번호)
				String memberName = req.getParameter("memberName");
				String memberNickname = req.getParameter("memberNickname");
				String memberTel = req.getParameter("memberTel");
				
				// 세션에서 로그인 회원정보 얻어오기
				HttpSession session = req.getSession();
				Member loginMember = (Member)(session.getAttribute("loginMember"));
				
				// 로그인 한 회원의 회원 번호
				int memberNo = loginMember.getMemberNo();
				
				// 수정에 필요한 정보를 모아둔 Member 객체 생성
				Member mem = new Member();
				
				mem.setMemberNo(memberNo);
				mem.setMemberName(memberName);
				mem.setMemberNickname(memberNickname);
				mem.setMemberTel(memberTel);
				
				// 회원 정보 수정 후 결과 반환
				int result = service.updateMember(mem);
				
				// 수정 성공/실패에 따른 메세지 출력 제어
				if(result > 0) {
					session.setAttribute("message", "회원 정보 수정 완료");
					
					// session의 로그인 회원 정보 수정 (DB와 동일하게)
					loginMember.setMemberName(memberName);
					loginMember.setMemberNickname(memberNickname);
					loginMember.setMemberTel(memberTel);
				} else {
					session.setAttribute("message", "회원 정보 수정 실패");
				}
				
				// 결과 상관 없이 내 정보 화면 재요청
				resp.sendRedirect(req.getContextPath() + "/member/myPage/info");
			} // info if문 끝
			
			
			// -------------------------------------------------------------
			
			
			if(command.equals("changePw")) { // 비밀번호 변경
				// 파라미터 얻어오기(현재 비밀번호, 새 비밀번호)
				String currentPw = req.getParameter("currentPw");
				String newPw = req.getParameter("newPw");
				
				// 세션에서 로그인 회원정보 얻어오기
				HttpSession session = req.getSession();
				Member loginMember = (Member)(session.getAttribute("loginMember"));
				
				// 로그인 한 회원의 회원 번호
				int memberNo = loginMember.getMemberNo();
				
				// 비밀번호 변경 후 결과 반환
				int result = service.changePw(currentPw, newPw, memberNo);
				
				
				// 리다이렉트 주소
				String path = null;
				
				if(result > 0) {
					session.setAttribute("message", "비밀번호 변경 완료");
					path = req.getContextPath() + "/member/myPage/info";
					loginMember.setMemberPw(newPw);
				} else {
					session.setAttribute("message", "비밀번호 변경 실패");
					path = req.getContextPath() + "/member/myPage/changePw";
				}

				// 결과에 따라 화면 재요청
				resp.sendRedirect(path);
				
			} // changePw if문 끝
			
			
			
			
			
			
			
			
			
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
			
			// ajax error 속성 활용을 위해 강제로 500에러 전달
			resp.setStatus(500); // 500번 : 서버 에러
			
			// 에러 내용 출력
			resp.getWriter().print(e.getMessage());
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
