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

	/** 게시판 전체 게시글 수 조회 DAO
	 * @param conn
	 * @return listCount
	 * @throws Exception
	 */
	public int getListCount(Connection conn) throws Exception{

		int listCount = 0;
		
		try{
			String sql = prop.getProperty("getCheckListCount");
			stmt= conn.createStatement();	
			rs=stmt.executeQuery(sql);
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
		}finally{
			close(rs);
			close(stmt);
		}
		return listCount;
	}

	/** 게시판에서 일정한 범위의 목록 조회 DAO
	 * @param conn
	 * @param pagination
	 * @param type
	 * @return boardList
	 * @throws Exception
	 */
	public List<CheckBoard> selectBoardList(Connection conn, CheckPagination pagination) throws Exception {
		
		List<CheckBoard> boardList = new ArrayList<CheckBoard>();
		
		try {
			String sql = prop.getProperty("selectBoardList");
			
			// BETWEEN 구문에 들어갈 범위 계산
			int start =  (pagination.getCurrentPage() - 1 ) * pagination.getLimit() + 1;
			int end = start + pagination.getLimit()-1;
			
			pstmt = conn.prepareStatement(sql);
			

			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CheckBoard board = new CheckBoard();
				
//				board.setBoardNo(rs.getInt("BOARD_NO"));
				board.setBoardTitle(rs.getString("BOARD_TITLE"));
//				board.setMemberNickname(rs.getString("MEMBER_NICK"));
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

	
	/** 다음 게시글 번호 조회 DAO
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

	/** 게시글 삭제 DAO
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

}
