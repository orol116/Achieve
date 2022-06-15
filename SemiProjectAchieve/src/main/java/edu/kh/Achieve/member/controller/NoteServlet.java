package edu.kh.Achieve.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import edu.kh.Achieve.member.model.service.NoteService;
import edu.kh.Achieve.member.model.vo.Note;

@WebServlet("/note")
public class NoteServlet extends HttpServlet{
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		try {
			
			NoteService service = new NoteService();
			
			int memberNo = Integer.parseInt(req.getParameter("memberNo"));
			
			List<Note> nList = service.noteList(memberNo);
			
			new Gson().toJson(nList, resp.getWriter());
			
			
			String path = "/WEB-INF/views/member/note.jsp";
			RequestDispatcher dispatcher = req.getRequestDispatcher(path);
			dispatcher.forward(req, resp);
			
		
		}catch (Exception e) {
			e.printStackTrace();
			
			//ajax error 속성 활용을 위해 강제 에러 전달
			resp.setStatus(500); //(서버에러)
			
			resp.getWriter().print(e.getMessage());
		}
		
	}
}
