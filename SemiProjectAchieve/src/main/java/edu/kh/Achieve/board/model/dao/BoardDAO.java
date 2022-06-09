package edu.kh.Achieve.board.model.dao;

import static edu.kh.Achieve.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.kh.Achieve.board.model.vo.Board;
import edu.kh.Achieve.board.model.vo.Pagination;

public class BoardDAO {
	
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Properties prop;
	
	public BoardDAO() {
		
		try {
			
			prop = new Properties();
			
			String filePath = BoardDAO.class.getResource("/edu/kh/Achieve/sql/board-sql.xml").getPath();
			
			prop.loadFromXML(new FileInputStream(filePath));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 게시판 이름 조회 DAO
	 * @param conn
	 * @param type
	 * @return boardName
	 * @throws Exception
	 */
	public String selectBoardName(Connection conn, int type) throws Exception {
		
		String boardName = null;
		
		try {
			String sql = prop.getProperty("selectBoardName");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, type);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) boardName = rs.getString(1);
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return boardName;
	}

	/** 특정 게시판 전체 게시글 수 조회 DAO
	 * @param conn
	 * @param type
	 * @return listCount
	 * @throws Exception
	 */
	public int getListCount(Connection conn, int type) throws Exception {
		
		int listCount = 0;
		
		try {
			String sql = prop.getProperty("selectListCount");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, type);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) listCount = rs.getInt(1);
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return listCount;
	}
	
	/** 최신글 (게시판 메인 뷰)조회 DAO
	 * @param conn
	 * @param pagination
	 * @param type
	 * @return boardList
	 * @throws Exception
	 */
	public List<Board> selectBoardMainList(Connection conn, Pagination pagination) throws Exception {

		List<Board> boardList = new ArrayList<Board>();
		
		try {
			String sql = prop.getProperty("selectBoardMainList");
			
			int start = (pagination.getCurrentPage() - 1) * pagination.getLimit() + 1;
			int end   = start + pagination.getLimit() - 1;
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				Board board = new Board();
				
				board.setBoardNo(rs.getInt("BOARD_NO"));
				board.setBoardTitle(rs.getString("BOARD_TITLE"));
				board.setCreateDate(rs.getString("CREATE_DT"));
				board.setReadCount(rs.getInt("READ_COUNT"));
				board.setMemberNickname(rs.getString("MEMBER_NICK"));

				boardList.add(board);
				
			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return boardList;
	}

	/** 특정 게시판에서 일정한 범위의 목록 조회 DAO
	 * @param conn
	 * @param pagination
	 * @param type
	 * @return boardList
	 * @throws Exception
	 */
	public List<Board> selectBoardMain(Connection conn, Pagination pagination, int type) throws Exception {
		
		List<Board> boardList = new ArrayList<Board>();
		
		try {
			String sql = prop.getProperty("selectBoardList");
			
			int start = (pagination.getCurrentPage() - 1) * pagination.getLimit() + 1;
			int end   = start + pagination.getLimit() - 1;
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, type);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				Board board = new Board();
				
				board.setBoardNo(rs.getInt("BOARD_NO"));
				board.setBoardTitle(rs.getString("BOARD_TITLE"));
				board.setCreateDate(rs.getString("CREATE_DT"));
				board.setReadCount(rs.getInt("READ_COUNT"));
				board.setMemberNickname(rs.getString("MEMBER_NICK"));

				boardList.add(board);
				
			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return boardList;
	}

	/** 특정 게시판에서 조건을 만족하는 게시글 수 조회 DAO
	 * @param conn
	 * @param type
	 * @param condition
	 * @return listCount
	 * @throws Exception
	 */
	public int searchListCount(Connection conn, int type, String condition) throws Exception {

		int listCount = 0;
		
		try {
			String sql = prop.getProperty("searchListCount") + condition;
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, type);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				listCount = rs.getInt(1);
			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return listCount;
	}

	/** 특정 게시판에서 조건을 만족하는 게시글 목록 조회 DAO
	 * @param conn
	 * @param pagination
	 * @param type
	 * @param condition
	 * @return boardList
	 * @throws Exception
	 */
	public List<Board> searchBoardList(Connection conn, Pagination pagination, int type, String condition) throws Exception {

		List<Board> boardList = new ArrayList<Board>();
		
		try {
			
			String sql = prop.getProperty("searchBoardList1")
					+ condition
					+ prop.getProperty("searchBoardList2");
			
			int start = (pagination.getCurrentPage() - 1) * pagination.getLimit() + 1;
			int end   = start + pagination.getLimit() - 1;
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, type);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				Board board = new Board();
				
				board.setBoardNo(rs.getInt("BOARD_NO"));
				board.setBoardTitle(rs.getString("BOARD_TITLE"));
				board.setMemberNickname(rs.getString("MEMBER_NICK"));
				board.setCreateDate(rs.getString("CREATE_DT"));
				board.setReadCount(rs.getInt("READ_COUNT"));
				
				boardList.add(board);
			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
		return boardList;
	}




}
