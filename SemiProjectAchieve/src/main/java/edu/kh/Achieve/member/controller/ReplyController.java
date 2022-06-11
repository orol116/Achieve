package edu.kh.Achieve.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.kh.Achieve.member.model.service.CheckBoardService;
import edu.kh.Achieve.member.model.service.CheckReplyService;

// Controller : 요청에 따라 알맞은 서비스를 호출하고
// 요청처리 결과를 내보내줄 응답할 view를 선택 (요청제어)
@WebServlet("/member/ReplyList")
public class ReplyController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		try {
			
			CheckReplyService service = new CheckReplyService();
			
			String path = "/WEB-INF/views/member/myPage-replyList.jsp";
			
			RequestDispatcher dispatcher = req.getRequestDispatcher(path);
			
			dispatcher.forward(req, resp);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
