package edu.kh.Achieve.member.model.dao;

import static edu.kh.Achieve.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.kh.Achieve.board.model.vo.Pagination;
import edu.kh.Achieve.member.model.vo.CheckBoard;
import edu.kh.Achieve.member.model.vo.CheckPagination;

public class CheckBoardDAO {

	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Properties prop;
	
	public CheckBoardDAO() {
		
		try {
			prop = new Properties();
			String filePath = CheckBoardDAO.class.getResource("/edu/kh/Achieve/sql/checkBoard-sql.xml").getPath();
			prop.loadFromXML(new FileInputStream(filePath));
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/** 작성글 수 조회 DAO
	 * @param conn
	 * @param memNo 
	 * @return listCount
	 * @throws Exception
	 */
	public int getBoardListCount(Connection conn , int type, int memNo) throws Exception{
		
		int listCount = 0;
		
		try{
			String sql = prop.getProperty("getBoardListCount");
			
			pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, memNo);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
		}finally{
			close(rs);
			close(pstmt);
		}
		return listCount;
		
	}
	
	/** 작성글 목록 조회 DAO
	 * @param conn
	 * @param type
	 * @return listName
	 * @throws Exception
	 */
	public String selectListName(Connection conn, int type, int memNo) throws Exception{
		String listName = null;
		
		try{
			String sql = prop.getProperty("selectListName");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memNo);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listName = rs.getString(1);
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return listName;
	}


	/** 작성글에서 일정한 범위의 목록 조회 DAO
	 * @param conn
	 * @param pagination
	 * @param type
	 * @param memNo 
	 * @return boardList
	 * @throws Exception
	 */
	public List<CheckBoard> selectBoardList(Connection conn, CheckPagination pagination, int type, int memNo) throws Exception {
		
		List<CheckBoard> boardList = new ArrayList<CheckBoard>();
		
		try {
			String sql = prop.getProperty("selectBoardList");
			
			// BETWEEN 구문에 들어갈 범위 계산
			int start =  (pagination.getCurrentPage() - 1) * pagination.getLimit() +1;
			int end = start + pagination.getLimit()-1;
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memNo);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CheckBoard board = new CheckBoard();
				
				board.setBoardNo(rs.getInt("BOARD_NO"));
				board.setBoardTitle(rs.getString("BOARD_TITLE"));
				board.setCreateDate(rs.getString("CREATE_DT"));
				board.setReadCount(rs.getInt("READ_COUNT"));

				boardList.add(board);
			
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return boardList;
	}

	
	/** 다음 작성글 번호 조회 DAO
	 * @param conn
	 * @return boardNo
	 * @throws Exception
	 */
	public int nextBoardNo(Connection conn) throws Exception {

		int boardNo = 0;
		
		try {
			String sql = prop.getProperty("nextBoardNo");
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				boardNo = rs.getInt(1);
			}
			
		}finally {
			close(stmt);
			close(rs);
		}
		
		return boardNo;
	}

	/** 작성글 삭제 DAO
	 * @param conn 
	 * @param boardNo
	 * @return result
	 * @throws Exception
	 */
	public int deleteBoard(Connection conn, int boardNo) throws Exception {

		int result = 0;
		
		try {
			String sql = prop.getProperty("deleteBoard");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		
		return result;
	}
	/** 작성 댓글 조회 DAO
	 * @param conn
	 * @param type
	 * @param memNo
	 * @return listCount
	 * @throws Exception
	 */
	public int getReplyListCount(Connection conn, int type, int memNo) throws Exception{
		int listCount = 0;
		
		try{
			String sql = prop.getProperty("getReplyListCount");
			
			pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, memNo);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
		}finally{
			close(rs);
			close(pstmt);
		}
		return listCount;
	}



}
