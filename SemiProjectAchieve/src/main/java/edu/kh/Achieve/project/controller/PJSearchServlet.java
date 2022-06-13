package edu.kh.Achieve.project.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.kh.Achieve.member.model.service.MemberService;
import edu.kh.Achieve.member.model.vo.Member;
import edu.kh.Achieve.project.model.service.ProjectService;
import edu.kh.Achieve.project.model.vo.Project;

@WebServlet("/project/PJ/PJSearch" ) //"/project/PJ/member/login"
public class PJSearchServlet extends HttpServlet{
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//로그인 정보 있으면 가져오기
		HttpSession session = req.getSession();
		
		if(session.getAttribute("loginMember") != null){
			Member loginMember = (Member)(session.getAttribute("loginMember"));
		}
		
		
		//비공개가 아닌 모든 프로젝트를 검색해서 페이지네이션으로 가져오기
		
		String path = "/WEB-INF/views/project/PJSearch.jsp";
		
		req.getRequestDispatcher(path).forward(req, resp);
		
	}
	
	
	
	/** 프로젝트 검색페이지 내 로그인 기능
	 *
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//프로젝트 검색 페이지에서도 로그인은 가능
		
		String inputEmail = req.getParameter("inputEmail");
		String inputPw = req.getParameter("inputPw");
		String path = null;
		
		// 파라미터를 mem에 세팅
		Member mem = new Member();
		mem.setMemberEmail(inputEmail);
		mem.setMemberPw(inputPw);
		
		try {
			
			MemberService service = new MemberService();
			
			// 입력된 이메일과 비밀번호가 일치하는 회원 조회
			Member loginMember = service.login(mem);
			
			// Email, Pw 일치하는 회원 정보를 Session에 저장
			
			// 1. session 객체 얻어오기
			HttpSession session = req.getSession();
			
			if(loginMember != null) { //로그인 정보가 있으면
				
				// 로그인 한 회원의 회원 정보를 session에 세팅
				session.setAttribute("loginMember", loginMember);
				
				// 특정 시간 후 요청 없을 시 세션 만료
				session.setMaxInactiveInterval(3600);
				
				// 아이디 저장
				Cookie c = new Cookie("saveId", inputEmail);
				
				// 아이디 저장이 체크된 경우
				if(req.getParameter("saveId") != null) {
					c.setMaxAge(60*60*24*30);
				} else {
					c.setMaxAge(0);
				}
				
				c.setPath(req.getContextPath());
				
				resp.addCookie(c);
				
			} else {
				session.setAttribute("message", "아이디 또는 비밀번호가 일치하지 않습니다.");
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}