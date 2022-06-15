package edu.kh.Achieve.project.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.kh.Achieve.project.model.service.ProjectService;

@WebServlet("/project/openStatusChange")
public class OpenStatusChangeServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String openStatus = req.getParameter("openStatus");
		
		// 아직 proejct에 들어가서 설정변경하는 페이지가 안나와서 projectNo는 기본 5로 설정
		
		try {
			
			ProjectService service = new ProjectService();
			
			int result = service.changeStatus(openStatus);
			
			resp.getWriter().print(result);
			
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		
		
	}

}
