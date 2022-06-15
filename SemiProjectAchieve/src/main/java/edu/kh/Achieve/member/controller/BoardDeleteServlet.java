package edu.kh.Achieve.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import edu.kh.Achieve.member.model.service.CheckBoardService;

@WebServlet("/member/delete")
public class BoardDeleteServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			HttpSession session = req.getSession();
			// 쿼리스트링으로 타입 얻어옴
			int type =  Integer.parseInt(req.getParameter("type"));
			int memNo = ((int)session.getAttribute("memNo"));
			System.out.println("memberNo : " + memNo);
			int cBoard = Integer.parseInt(req.getParameter("cBoard"));
//			int cReply = Integer.parseInt(req.getParameter("cReply"));
			
			int result1 = new CheckBoardService().deleteBoard(cBoard);
//			int result2 = new CheckBoardService().deleteReply(cReply);
			
			
			String path = null;
			String message = null;
			
			if(result1 > 0) { // 성공
				
				message = "댓글이 삭제 되었습니다.";
				path = "List?memNo="+ memNo +"&type=" + type ; //해당 작성글 목록 1페이지로 이동
				// /List/list?type=1 /  /List?memNo=&type=1'
				
			}else { // 실패
				message = "댓글 삭제에 실패했습니다.";
				path = req.getHeader("referer"); // 이전 요청 페이지 주소 == 상세페이지 == referer
			}
			
			
//			if(result2 > 0) { // 성공
//				
//				message = "댓글이 삭제 되었습니다.";
//				path = "List?memNo"+ memNo +"&type=" + type ; //해당 댓글 목록 1페이지로 이동
//				// /List/list?type=2
//				
//			}else { // 실패
//				message = "댓글 삭제에 실패했습니다.";
//				path = req.getHeader("referer"); // 이전 요청 페이지 주소 == 상세페이지 == referer
//			}
//			
			session.setAttribute("message", message);
			
			resp.sendRedirect(path);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
		
}
