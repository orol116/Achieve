package edu.kh.Achieve.board.model.dao;

import static edu.kh.Achieve.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.kh.Achieve.board.model.vo.Board;
import edu.kh.Achieve.board.model.vo.BoardAttachment;
import edu.kh.Achieve.board.model.vo.BoardDetail;
import edu.kh.Achieve.board.model.vo.Pagination;
import edu.kh.Achieve.project.model.vo.Project;

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

	/** 최신글 게시판 전체 게시글 수 조회 DAO
	 * @param conn
	 * @param type
	 * @return listCount
	 * @throws Exception
	 */
	public int getNewListCount(Connection conn) throws Exception {
		
		int listCount = 0;
		
		try {
			String sql = prop.getProperty("selectNewListCount");
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			if (rs.next()) listCount = rs.getInt(1);
			
		} finally {
			close(rs);
			close(stmt);
		}
		
		return listCount;
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
			
			if (rs.next()) {
				boardNo = rs.getInt(1);
			}
			
		} finally {
			close(rs);
			close(stmt);
		}
		
		return boardNo;

	}

	/** 게시글 삽입 DAO
	 * @param conn
	 * @param detail
	 * @param boardCode
	 * @return result
	 * @throws Exception
	 */
	public int insertBoard(Connection conn, BoardDetail detail, int boardCode) throws Exception {
		
		int result = 0;
		
		try {
			String sql = prop.getProperty("insertBoard");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, detail.getBoardNo());
			pstmt.setString(2, detail.getBoardTitle());
			pstmt.setString(3, detail.getBoardContent());
			pstmt.setInt(4, detail.getMemberNo());
			pstmt.setInt(5, boardCode);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	/** 게시글 첨부파일 삽입 DAO
	 * @param conn
	 * @param image
	 * @return result
	 * @throws Exception
	 */
	public int insertBoardAttachment(Connection conn, BoardAttachment attachmentt) throws Exception {

		int result = 0;
		
		try {
			String sql = prop.getProperty("insertBoardAttachment");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, attachmentt.getAttachmentReName());
			pstmt.setString(2, attachmentt.getAttachmentOriginal());
			pstmt.setInt(3, attachmentt.getAttachmentLevel());
			pstmt.setInt(4, attachmentt.getBoardNo());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	/** 게시판 종류 이름 조회 DAO
	 * @param conn
	 * @return boardTypeList
	 * @throws Exception
	 */
	public List<Board> selectboardType(Connection conn, Project projectNo) throws Exception {

		List<Board> boardType = new ArrayList<Board>();
		
		try {
			String sql = prop.getProperty("selectboardTypeList");
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				
				Board bd = new Board();

				bd.setBoardName(rs.getString("BOARD_NM"));
				bd.setBoardCode(rs.getInt("BOARD_CD"));

				boardType.add(bd);
			}
			
		} finally {
			close(rs);
			close(stmt);
		}
		
		return boardType;
		
	}

	public int deleteBoard(int boardNo, Connection conn) throws Exception{
		
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

	public BoardDetail selectBoarDetail(Connection conn, int boardNo) throws Exception{
		
		BoardDetail detail = null;
		
		try{
			System.out.println(boardNo);
			
			String sql = prop.getProperty("selectBoardDetail");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			rs=pstmt.executeQuery()	;
			
			if(rs.next()) {
				detail = new BoardDetail();
				
				detail.setBoardNo(rs.getInt(1));
				detail.setBoardTitle(rs.getString(2));
				detail.setBoardContent(rs.getString(3));
				detail.setCreateDate(rs.getString(4));
				detail.setUpdateDate(rs.getString(5));
				detail.setReadCount(rs.getInt(6));
				detail.setMemberNickname(rs.getString(7));
				detail.setProfileImage(rs.getString(8));
				detail.setMemberNo(rs.getInt(9));
				detail.setBoardName(rs.getString(10));
			}
			
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return detail;
	}

	public List<BoardAttachment> selectAttachmentList(Connection conn, int boardNo) throws Exception{
		
		List<BoardAttachment> attachmentList = new ArrayList<>();
		
		try {
			
			String sql = prop.getProperty("selectAttachmentList");
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				BoardAttachment attachment = new BoardAttachment();
				
				attachment.setAttachmentNo(rs.getInt(1));
				attachment.setAttachmentReName(rs.getString(2));
				attachment.setAttachmentOriginal(rs.getString(3));
				attachment.setAttachmentLevel(rs.getInt(4));
				attachment.setBoardNo(rs.getInt(5));
				
				attachmentList.add(attachment);
			}
		
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return attachmentList;
	}
	

}