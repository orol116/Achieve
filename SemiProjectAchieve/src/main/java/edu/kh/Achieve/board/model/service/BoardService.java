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
		
		List<Board> boardList = dao.selectBoardMain(conn, pagination, type);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("boardName", boardName);
		map.put("pagination", pagination);
		map.put("boardList", boardList);
		
		close(conn);
		
		return map;
	}
	

}
