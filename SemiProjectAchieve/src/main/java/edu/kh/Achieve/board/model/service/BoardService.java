package edu.kh.Achieve.board.model.service;

import static edu.kh.Achieve.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.kh.Achieve.board.model.dao.BoardDAO;
import edu.kh.Achieve.board.model.vo.Board;
import edu.kh.Achieve.board.model.vo.Pagination;

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
		
		int listCount = dao.getListCount(conn, type);
		
		Pagination pagination = new Pagination(cp, listCount);
		
		List<Board> boardList = null;
		
		if (type == 4) { // 최신글 조회
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
	

}
