package edu.kh.Achieve.board.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.kh.Achieve.board.model.service.BoardService;
import edu.kh.Achieve.board.model.vo.Board;
import edu.kh.Achieve.board.model.vo.BoardDetail;

@WebServlet("/board/main")
public class BoardMainController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		try {
		
			int type = Integer.parseInt(req.getParameter("type"));
			
			int cp = 1;
			
			if (req.getParameter("cp") != null) cp = Integer.parseInt(req.getParameter("cp"));
			
			BoardService service = new BoardService();
			
			// 게시글 목록(자유게시판, 과제 ...) 조회
		
			Map<String, Object> boardTypeList = service.selectboardTypeList();
			req.setAttribute("boardTypeList", boardTypeList);
			
			Map<String, Object> map = null;
			
			if (req.getParameter("key") == null) {
				map = service.selectBoardMain(type, cp);
			} else {
				String key = req.getParameter("key");
				String query = req.getParameter("query");
				
				map = service.searchBoardList(type, cp, key, query);
			}
			
			req.setAttribute("map", map);
			
			String path = "/WEB-INF/views/board/board-main.jsp";
			
			RequestDispatcher dispatcher = req.getRequestDispatcher(path);
			dispatcher.forward(req, resp);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
