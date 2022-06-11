package edu.kh.Achieve.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import com.oreilly.servlet.MultipartRequest;

import edu.kh.Achieve.board.model.service.BoardService;
import edu.kh.Achieve.board.model.vo.Board;
import edu.kh.Achieve.board.model.vo.BoardAttachment;
import edu.kh.Achieve.board.model.vo.BoardDetail;
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
			
			List<Board> boardTypeList = new BoardService().selectboardTypeList();
			req.setAttribute("boardTypeList", boardTypeList);
			
			
			req.getRequestDispatcher(path).forward(req, resp);
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		try {
			int maxSize = 1024 * 1024 * 500; // 500MB 제한 (수정 가능)
			
			HttpSession session = req.getSession();
			String root = session.getServletContext().getRealPath("/");
			String folderPath = "/resources/boardAttachment/";
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
			
			BoardDetail detail = new BoardDetail();
			detail.setBoardTitle(boardTitle);
			detail.setBoardContent(boardContent);
			detail.setMemberNo(memberNo);
			
			BoardService service = new BoardService();
	
			String mode = mpReq.getParameter("mode");
			
			if (mode.equals("insert")) { // 삽입
				
				int boardNo = service.insertBoard(detail, boardAttachmentList, boardCode);
				String path = null;
				
				if (boardNo > 0) {
					session.setAttribute("message", "게시글이 등록되었습니다.");
					path = "detail?no=" + boardNo + "&type=" + boardCode;
					
				} else {
					session.setAttribute("message", "게시글 등록 실패");
					path = "write?mode=" + mode + "&type=" + boardCode;
				}
				
				resp.sendRedirect(path);
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
