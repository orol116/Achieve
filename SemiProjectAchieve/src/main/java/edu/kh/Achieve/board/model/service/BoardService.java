package edu.kh.Achieve.board.model.service;

import static edu.kh.Achieve.common.JDBCTemplate.close;
import static edu.kh.Achieve.common.JDBCTemplate.commit;
import static edu.kh.Achieve.common.JDBCTemplate.getConnection;
import static edu.kh.Achieve.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.kh.Achieve.board.model.dao.BoardDAO;
import edu.kh.Achieve.board.model.vo.Board;
import edu.kh.Achieve.board.model.vo.BoardAttachment;
import edu.kh.Achieve.board.model.vo.BoardDetail;
import edu.kh.Achieve.board.model.vo.Pagination;
import edu.kh.Achieve.common.Util;

public class BoardService {
	
	private BoardDAO dao = new BoardDAO();

	/** 게시글 메인 페이지 Service
	 * @param type
	 * @param cp
	 * @return map
	 * @throws Exception
	 */
	public Map<String, Object> selectBoardMain(int type, int cp) throws Exception {
	
		Connection conn = getConnection();
		
		String boardName = dao.selectBoardName(conn, type);
		
		int listCount = 0;
		
		if (type == 1) {
			listCount = dao.getNewListCount(conn);
		} else {
			listCount = dao.getListCount(conn, type);
		}
		
		
		Pagination pagination = new Pagination(cp, listCount);
		
		List<Board> boardList = null;
		
		if (type == 1) { // 최신글 조회
			boardList = dao.selectBoardMainList(conn, pagination);
		} else {
			boardList = dao.selectBoardMain(conn, pagination, type);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("boardName", boardName);
		map.put("pagination", pagination);
		map.put("boardList", boardList);
		
		close(conn);
		
		return map;
	}

	/** 게시글 검색 목록 조회 Service
	 * @param type
	 * @param cp
	 * @param key
	 * @param query
	 * @return map
	 * @throws Exception
	 */
	public Map<String, Object> searchBoardList(int type, int cp, String key, String query) throws Exception {

		Connection conn = getConnection();
		
		String boardName = dao.selectBoardName(conn, type);
		
		String condition = null;
		
		switch (key) {
		case "t" : condition = " AND BOARD_TITLE LIKE '%"+query+"%' "; break;
		case "c" : condition = " AND BOARD_CONTENT LIKE '%"+query+"%' "; break;
		case "tc" : condition = " AND (BOARD_TITLE LIKE '%"+query+"%' OR BOARD_CONTENT LIKE '%"+query+"%') "; break;
		case "w" : condition = " AND MEMBER_NICK LIKE '%"+query+"%' "; break;
		}
		
		int listCount = dao.searchListCount(conn, type, condition);
		
		Pagination pagination = new Pagination(cp, listCount);
		
		List<Board> boardList = dao.searchBoardList(conn, pagination, type, condition);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("boardName", boardName);
		map.put("pagination", pagination);
		map.put("boardList", boardList);
		
		close(conn);
		
		return map;
		
		
	}

	/** 게시글 등록 Service
	 * @param detail
	 * @param imageList
	 * @param boardCode
	 * @return boardNo
	 * @throws Exception
	 */
	public int insertBoard(BoardDetail detail, List<BoardAttachment> boardAttachmentList, int boardCode) throws Exception {
		
		Connection conn = getConnection();

		int boardNo = dao.nextBoardNo(conn);

		detail.setBoardNo(boardNo);
		
		detail.setBoardTitle(Util.XSSHandling(detail.getBoardTitle()));
		detail.setBoardContent(Util.XSSHandling(detail.getBoardContent()));

		detail.setBoardContent(Util.newLineHandling(detail.getBoardContent()));
		
		int result = dao.insertBoard(conn, detail, boardCode);
		
		if (result > 0) {
			for (BoardAttachment image : boardAttachmentList) { 
				image.setBoardNo(boardNo);
				
				result = dao.insertBoardAttachment(conn, image);
				
				if (result == 0) {
					break;
				}
			}
			
		}
		
		if (result > 0) commit(conn);
		else {
			rollback(conn);
			boardNo = 0;
		}
		
		close(conn);

		return boardNo;
	}


	/** 게시판 종류 조회 Service
	 * @param type
	 * @return boardTypeList
	 * @throws Exception
	 */
	public List<Board> selectboardTypeList() throws Exception {

		Connection conn = getConnection();
		
		List<Board> boardTypeList = dao.selectboardType(conn);
	
		close(conn);
		
		return boardTypeList;
	}
	
	
	public int deleteBoard(int boardNo) throws Exception{

		Connection conn = getConnection();
		
		int result = dao.deleteBoard(boardNo, conn);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

	/** 게시글 상세조회 service
	 * 
	 * @param boardNo
	 * @return detail
	 * @throws Exception
	 */
	public BoardDetail selectBoardDetail(int boardNo) throws Exception{
		
		Connection conn = getConnection();
		
		// 1) 게시글 (BOARD 테이블) 내용만 조회
		BoardDetail detail = dao.selectBoarDetail(conn, boardNo);
		
		System.out.println(detail);
		
		if(detail != null) { // 게시글 상세 조회 결과가 있을 경우에 
			
		// 2) 게시글에 첨부된 이미지(BOARD_IMG 테이블) 조회
			List<BoardAttachment> attachmentList = dao.selectAttachmentList(conn, boardNo);
			
			// -> 조회된 imageList 를 BoardDetail 객체에 세팅
			
			detail.setAttachmentList(attachmentList);
		}
		
		close(conn);
		
		return detail;
	}
	

}
