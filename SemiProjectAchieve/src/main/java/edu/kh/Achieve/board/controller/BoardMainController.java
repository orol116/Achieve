package edu.kh.Achieve.board.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.kh.Achieve.board.model.service.BoardService;

@WebServlet("/board/main")
public class BoardMainController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		try {
		
			int type = Integer.parseInt(req.getParameter("type"));
			
			int cp = 1;
			
			if (req.getParameter("cp") != null) cp = Integer.parseInt(req.getParameter("cp"));
			
			BoardService service = new BoardService();
			
			Map<String, Object> map = service.selectBoardMain(type, cp);
			
			req.setAttribute("map", map);
			
			String path = "/WEB-INF/views/board/board-main.jsp";
			
			RequestDispatcher dispatcher = req.getRequestDispatcher(path);
			dispatcher.forward(req, resp);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
