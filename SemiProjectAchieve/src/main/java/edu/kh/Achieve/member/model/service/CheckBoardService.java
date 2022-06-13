package edu.kh.Achieve.member.model.service;

import static edu.kh.Achieve.common.JDBCTemplate.*;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.kh.Achieve.member.model.dao.CheckBoardDAO;
import edu.kh.Achieve.member.model.vo.CheckBoard;
import edu.kh.Achieve.member.model.vo.CheckPagination;
import edu.kh.Achieve.board.model.vo.Pagination;

public class CheckBoardService {

	private CheckBoardDAO dao = new CheckBoardDAO();

	/** 게시글 목록 조회 Service
	 * @param cp
	 * @return map
	 * @throws Exception
	 */
	public Map<String, Object> selectBoardList(int cp, int type, int memNo) throws Exception{

		Connection conn = getConnection();
		
		
		// 2-1. 특정 게시판 전체 게시글 수 조회
		int listCount = dao.getListCount(conn, type, memNo);
		
		// 2-2. 전체 게시글 수 + 현재 페이지(cp)를 이용해 페이지네이션 객체 생성
		CheckPagination pagination = new CheckPagination(cp, listCount);
		
		// 3. 게시글 목록 조회
		List<CheckBoard> boardList = dao.selectBoardList(conn, pagination, type, memNo);
		
		// 4. Map 객체를 생성하여 1,2,3 결과 객체를 모두 저장
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("type", type);
		map.put("memNo", memNo);
		map.put("pagination", pagination);
		map.put("boardList", boardList);
		
		close(conn);
		
		return map; // map 객체 반환
	}


}
