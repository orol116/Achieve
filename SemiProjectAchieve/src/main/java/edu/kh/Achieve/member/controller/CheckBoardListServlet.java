package edu.kh.Achieve.member.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.kh.Achieve.member.model.service.CheckBoardService;
import edu.kh.Achieve.member.model.vo.Member;

@WebServlet("/member/BoardList")
public class CheckBoardListServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		try {
			//1.  현재페이지 cp
			int cp = 1;
			if(req.getParameter("cp") != null) { // 쿼리스트링에 "cp"가 존재 한다면
				cp = Integer.parseInt(req.getParameter("cp"));
			}
			
			// 로그인 회원 번호
			HttpSession session = req.getSession();
			Member loginMember = (Member)session.getAttribute("loginMember");
			int memNo = loginMember.getMemberNo();
		
			// /board/list?type=1 (작성글)
			// /board/list?type=2 (댓글)
			int type = Integer.parseInt(req.getParameter("type"));
	
			
			// 게시판 이름, 페이지네이션 객체, 게시글 리스트를 한 번에 반환하는 Service 호출
			CheckBoardService service = new CheckBoardService();
			
			Map<String, Object> map = null;
	
			
			if(type == 1) {
				map = service.selectBoardList(cp, type, memNo);
				
			}else if(type == 2) {
				
			}
			
			
//		if( req.getParameter("key") == null) { // 일반 목록 조회
//			
//		}
//		}else { // 검섹 목록 조회
//			
//			String key = req.getParameter("key");
//			String query = req.getParameter("query");
//			
//			map = service.searchBoardList(memberNo,cp, key, query);
		
			
			String path = "/WEB-INF/views/member/myPage-boardList.jsp";
			
			RequestDispatcher dispatcher = req.getRequestDispatcher(path);
			req.setAttribute("map", map);
			dispatcher.forward(req, resp);

		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
