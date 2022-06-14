package edu.kh.Achieve.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import edu.kh.Achieve.board.model.service.BoardService;
import edu.kh.Achieve.board.model.vo.Board;
import edu.kh.Achieve.board.model.vo.BoardAttachment;
import edu.kh.Achieve.board.model.vo.BoardDetail;
import edu.kh.Achieve.common.MyRenamePolicy;
import edu.kh.Achieve.member.model.vo.Member;

@WebServlet("/board/write")
public class BoardWriteController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {

			int projectNo = Integer.parseInt(req.getParameter("projectNo"));
			String mode = req.getParameter("mode"); // insert mode / update mode 구분
			
			// insert는 별도 처리 없이 jsp로 위임만 하면 된다.
			
			// update는 기존 게시글 내용을 조회하는 처리가 필요함
			if(mode.equals("update")) {
				
				int boardNo = Integer.parseInt(req.getParameter("no")); 
				
				// 게시글 상세 조회 서비스를 이용해서 기존 내용 조회
				// new BoardService() : 객체를 생성해서 변수에 저장을 안한 상태 -> 1회성 사용하겠다는 의미!
				BoardDetail detail = new BoardService().selectBoardDetail(boardNo, projectNo);
				
				System.out.println(detail);
				// 개행 문자처리 해제(<br> -> \n)
				detail.setBoardContent(detail.getBoardContent().replaceAll("<br>", "\n"));
				
				req.setAttribute("detail", detail); // jsp에서 사용할 수 있도록 req에 값 세팅
				
			}
			
			String path = "/WEB-INF/views/board/board-write.jsp";
			
			List<Board> boardTypeList = new BoardService().selectboardTypeList(projectNo);
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
			int boardCode = Integer.parseInt(mpReq.getParameter("board-type"));
			int projectNo = Integer.parseInt(mpReq.getParameter("projectNo"));
			session.setAttribute("projectNo", projectNo);
			
			Member loginMember = (Member)session.getAttribute("loginMember");
			int memberNo = loginMember.getMemberNo();
			
			BoardDetail detail = new BoardDetail();
			detail.setBoardTitle(boardTitle);
			detail.setBoardContent(boardContent);
			detail.setMemberNo(memberNo);
			
			BoardService service = new BoardService();
	
			String mode = mpReq.getParameter("mode");
			
			if (mode.equals("insert")) { // 삽입
				
				int boardNo = service.insertBoard(detail, boardAttachmentList, boardCode, projectNo);
				String path = null;
				
				if (boardNo > 0) {
					session.setAttribute("message", "게시글이 등록되었습니다.");
					path = "detail?no=" + boardNo + "&type=" + boardCode + "&projectNo=" + projectNo;
					
				} else {
					session.setAttribute("message", "게시글 등록 실패");
					path = "write?mode=" + mode + "&type=" + boardCode + "&projectNo=" + projectNo;
				}
				
				resp.sendRedirect(path);
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
