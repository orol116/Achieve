package edu.kh.Achieve.board.controller;

import java.io.IOException;
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


@WebServlet("/board/main")
public class BoardMainController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		try {
		
			int type = Integer.parseInt(req.getParameter("type"));
			int projectNo = Integer.parseInt(req.getParameter("projectNo"));
			int cp = 1;
			
			
			if (req.getParameter("cp") != null) cp = Integer.parseInt(req.getParameter("cp"));
			
			BoardService service = new BoardService();
			
<<<<<<< HEAD
			String projectName = service.selectProjectName(projectNo);
			req.setAttribute("projectName", projectName);

=======
>>>>>>> f4c703a0d5710db0d28d05da218376fe6e530c90
			List<Board> boardTypeList = service.selectboardTypeList(projectNo);
			req.setAttribute("boardTypeList", boardTypeList);
			
			
			Map<String, Object> map = null;
			
			if (req.getParameter("key") == null) {
				map = service.selectBoardMain(type, cp, projectNo);
			} else {
				String key = req.getParameter("key");
				String query = req.getParameter("query");
				
				map = service.searchBoardList(type, cp, key, query, projectNo);
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
