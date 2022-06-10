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
import edu.kh.Achieve.member.model.vo.CheckBoardDetail;
import edu.kh.Achieve.member.model.vo.CheckBoardImage;
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

//	/** 게시물 이름조회 DAO
//	 * @param conn
//	 * @param type
//	 * @return boardName
//	 * @throws Exception
//	 */
//	public String selectBoardName(Connection conn, int type) throws Exception  {
//		String boardName = null;
//		
//		try{
//			String sql = prop.getProperty("selectBoardName");
//			
//			pstmt = conn.prepareStatement(sql);
//			
//			pstmt.setInt(1, type);
//			
//			rs=pstmt.executeQuery();
//			
//			if(rs.next()) {
//				boardName = rs.getString(1);
//			}
//			
//		}finally {
//			close(rs);
//			close(pstmt);
//		}
//		
//		return boardName;
//	}

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

	/** 특정 게시판에서 일정한 범위의 목록 조회 DAO
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
				
				board.setBoardNo(rs.getInt("BOARD_NO"));
				board.setBoardTitle(rs.getString("BOARD_TITLE"));
				board.setMemberNickname(rs.getString("MEMBER_NICK"));
				board.setCreateDate(rs.getString("CREATE_DT"));
				board.setReadCount(rs.getInt("READ_COUNT"));
//				board.setThumbnail(rs.getString("THUMBNAIL"));
				
				boardList.add(board);
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		
		return boardList;
	}

	/** 게시글 상세 조회 DAO
	 * @param conn
	 * @param boardNo
	 * @return detail
	 * @throws Exception
	 */
	public CheckBoardDetail selectBoardDetail(Connection conn, int boardNo) throws Exception{

		CheckBoardDetail detail = null; 
		
		try {
			
			String sql = prop.getProperty("selectBoardDetail");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			rs= pstmt.executeQuery();
			
			if(rs.next()) {
				detail = new CheckBoardDetail();
				
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
			
			close(pstmt);
			close(rs);
		}
		
		return detail;
	}

	/** 게시글에 첨부된 이미지 리스트 조회
	 * @param conn
	 * @param boardNo
	 * @return imageList
	 * @throws Exception
	 */
	public List<CheckBoardImage> selectImageList(Connection conn, int boardNo) throws Exception {
		
		 List<CheckBoardImage> imageList = new ArrayList<>();
		 
		 try {
			 
			 String sql = prop.getProperty("selectImageList");
			 
			 pstmt = conn.prepareStatement(sql);
			 
			 pstmt.setInt(1, boardNo);
			 
			 rs=pstmt.executeQuery();
			 
			 while(rs.next()) {
				 
				 CheckBoardImage image = new CheckBoardImage();
				 
			 	image.setImageNo(rs.getInt(1));
			 	image.setImageReName(rs.getString(2));
				image.setImageOriginal(rs.getString(3));
				image.setImageLevel(rs.getInt(4));
				image.setBoardNo(rs.getInt(5));

				imageList.add(image);
			 }
			 
		 }finally {
			 close(rs);
			 close(pstmt);
			 
		 }

		 return imageList;
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

	/** 다음 게시글 내용 삽입 DAO
	 * @param conn
	 * @param detail
	 * @param boardCode
	 * @return result
	 * @throws Exception
	 */
	public int insertBoard(Connection conn, CheckBoardDetail detail, int boardCode) throws Exception{

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
					
		}finally {
			close(pstmt);
		}
		
		return result;
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

	/** 게시글 검색 목록 조회 DAO
	 * @param conn
	 * @param type
	 * @param condition
	 * @return listCount
	 * @throws Exception
	 */
	public int searchListCount(Connection conn, int type, String condition) throws Exception{

		int listCount = 0;
		
		try {
			String sql = prop.getProperty("searchListCount")+condition;
			
			pstmt= conn.prepareStatement(sql);
			
			pstmt.setInt(1, type);
			
			rs=pstmt.executeQuery();
					
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
			
		}finally {
			
			close(pstmt);
			close(rs);
		}
		
		return listCount;
	}

	/** 게시글 검색 목록 조회 DAO
	 * @param conn
	 * @param pagination
	 * @param type
	 * @param condition
	 * @return boardList
	 * @throws Exception
	 */
	public List<CheckBoard> searchBoardList(Connection conn, CheckPagination pagination, int type, String condition) throws Exception{

		List<CheckBoard> boardList = new ArrayList<CheckBoard>();
		
		try {
			String sql = prop.getProperty("searchBoardList1")
						+condition
						+prop.getProperty("searchBoardList2");
						
			pstmt= conn.prepareStatement(sql);
			
			// BETWEEN 구문에 들어갈 범위 계산
			int start =  (pagination.getCurrentPage() - 1 ) * pagination.getLimit() + 1;
			int end = start + pagination.getLimit()-1;
			
			pstmt.setInt(1, type);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CheckBoard board = new CheckBoard();
				
				board.setBoardNo(rs.getInt("BOARD_NO"));
				board.setBoardTitle(rs.getString("BOARD_TITLE"));
				board.setMemberNickname(rs.getString("MEMBER_NICK"));
				board.setCreateDate(rs.getString("CREATE_DT"));
				board.setReadCount(rs.getInt("READ_COUNT"));
//				board.setThumbnail(rs.getString("THUMBNAIL"));
				
				boardList.add(board);
			}
		}finally {
			
			close(rs);
			close(pstmt);
		}
		
		return boardList;
	}
}
