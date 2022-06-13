package edu.kh.Achieve.member.model.service;

import static edu.kh.Achieve.common.JDBCTemplate.*;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import edu.kh.Achieve.member.model.dao.CheckBoardDAO;
import edu.kh.Achieve.member.model.vo.CheckBoard;
import edu.kh.Achieve.member.model.vo.CheckPagination;

public class CheckBoardService {

	private CheckBoardDAO dao = new CheckBoardDAO();

	/** 작성글 목록 조회 Service
	 * @param cp
	 * @return map
	 * @throws Exception
	 */
	public Map<String, Object> selectBoardList(int cp, int type, int memNo) throws Exception{

		Connection conn = getConnection();

		// 1. 작성글 수 조회
		int listCount = dao.getBoardListCount(conn, type, memNo);
		
		// 2. 작성글 수 + 현재 페이지(cp)를 이용해 페이지네이션 객체 생성
		CheckPagination pagination = new CheckPagination(cp, listCount);
		
		// 3. 작성글 목록 조회
		List<CheckBoard> boardList = dao.selectBoardList(conn, pagination, type, memNo);
		
		// 4. Map 객체를 생성하여 1,2,3 결과 객체를 모두 저장
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("type", type);
		map.put("memNo", memNo);
		map.put("pagination", pagination);
		map.put("boardList", boardList);
		map.put("listCount", listCount);
		
		
		close(conn);
		
		return map; // map 객체 반환
	}


	/** 게시글 삭제
	 * @param boardNo
	 * @return result
	 * @throws Exception
	 */
	public int deleteBoard(int boardNo) throws Exception {

		Connection conn= getConnection();
		
		int result = dao.deleteBoard(conn, boardNo);

		if(result >0 ) commit(conn);
		else  			rollback(conn);
		
		close(conn);
		
		return result;
	}
	/** 작성 댓글 조회 Service
	 * @param cp
	 * @param type
	 * @param memNo
	 * @return map
	 * @throws Exception
	 */
	public Map<String, Object> selectReplyList(int cp, int type, int memNo) throws Exception{

		Connection conn = getConnection();

		// 1. 작성댓글 수 조회
		int listCount = dao.getReplyListCount(conn, type, memNo);
		
		
		return null;
	}


}
