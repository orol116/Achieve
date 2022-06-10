package edu.kh.Achieve.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import com.oreilly.servlet.MultipartRequest;

import edu.kh.Achieve.board.model.service.BoardService;
import edu.kh.Achieve.board.model.vo.BoardAttachment;
import edu.kh.Achieve.common.MyRenamePolicy;
import edu.kh.Achieve.member.model.vo.Member;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/board/write")
public class BoardWriteController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {

			// 게시글 수정 기능은 향후에 추가
			
			String path = "/WEB-INF/views/board/board-write.jsp";
			req.getRequestDispatcher(path).forward(req, resp);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		int maxSize = 1024 * 1024 * 500; // 500MB 제한 (수정 가능)
		
		HttpSession session = req.getSession();
		String root = session.getServletContext().getRealPath("/");
		String folderPath = "/resources/images/board/";
		String filePath = root + folderPath;
		
		String encoding = "UTF-8";
		
		MultipartRequest mpReq = new MultipartRequest(req, filePath, maxSize, encoding, new MyRenamePolicy());
		
		Enumeration<String> files = mpReq.getFileNames();
		
		List<BoardAttachment> boardAttachmentList = new ArrayList<BoardAttachment>();
		
		while (files.hasMoreElements()) {
			String name = files.nextElement();
			
			String rename = mpReq.getFilesystemName(name);
			String original = mpReq.getOriginalFileName(name);
			
			if (rename != null) {
				
				BoardAttachment attachment = new BoardAttachment();
				
				attachment.setAttachmentOriginal(original);
				attachment.setAttachmentReName(folderPath + rename);
				attachment.setAttachmentLevel(Integer.parseInt(name));
				
				boardAttachmentList.add(attachment);
			}
		}
		
		String boardTitle = mpReq.getParameter("boardTitle");
		String boardContent = mpReq.getParameter("boardContent");
		int boardCode = Integer.parseInt(mpReq.getParameter("type"));
		
		Member loginMember = (Member)session.getAttribute("loginMember");
		int memberNo = loginMember.getMemberNo();
		
//		BoardDetail detail = new BoardDetail();
//		detail.setBoardTitle(boardTitle);
//		detail.setBoardContent(boardContent);
//		detail.setMemberNo(memberNo);
		

		
		
	}

}
