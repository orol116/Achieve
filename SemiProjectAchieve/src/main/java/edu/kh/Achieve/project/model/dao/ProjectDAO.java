package edu.kh.Achieve.project.model.dao;

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
import edu.kh.Achieve.member.model.vo.Member;
import edu.kh.Achieve.project.model.vo.Project;
import edu.kh.Achieve.project.model.vo.ProjectSign;

public class ProjectDAO {
	
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop;
	
	public ProjectDAO() {
		try {
			prop = new Properties();
			
			String filePath = ProjectDAO.class.getResource("/edu/kh/Achieve/sql/project-sql.xml").getPath();
			
			prop.loadFromXML(new FileInputStream(filePath));
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	/** 프로젝트 생성 DAO
	 * @param conn
	 * @param project
	 * @return result
	 * @throws Exception
	 */
	public int createProject(Connection conn, Project project, int memberNo) throws Exception {
		
		int result = 0;
		
		try {
			String sql = prop.getProperty("createProject");
			
			
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, project.getProjectName());
			pstmt.setInt(2,memberNo);
			pstmt.setString(3, project.getProjectQuota());
			pstmt.setString(4, project.getOpenStatus());
			pstmt.setString(5, project.getProjectIntro());
			pstmt.setInt(6, memberNo);
			
			
			
			
			
		
			
			result = pstmt.executeUpdate();
			
			
			
		}finally {
			
			
			close(pstmt);
			
		}
		
		
		return result;
	}
	
	

	/** 프로젝트 이름 중복검사 DAO
	 * @param conn
	 * @param projectName
	 * @return
	 * @throws Exception
	 */
	public int PJDupCheck(Connection conn, String projectName) throws Exception {
		
		int result = 0;
		
		try {
			String sql = prop.getProperty("PJDupCheck");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, projectName);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
					
					
					
		}finally {
			close(rs);
			close(pstmt);
			
		}
		
		
		return result;
	}
	
	
	

	/** 프로젝트 공개/ 비공개 변경  DAO
	 * @param conn
	 * @param openStatus
	 * @return
	 * @throws Exception
	 */
	public int changeStatus(Connection conn, String openStatus) throws Exception{

		
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("changeStatus");
			
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, openStatus);
			
			result = pstmt.executeUpdate();
			
		
		}finally {
			
			close(pstmt);
			
			
		}

		

		return result;
	}
	
	

  
	/** 프로젝트 소개 변경 DAO
	 * @param conn
	 * @param projecItntro
	 * @return result
	 * @throws Exception
	 */
	public int IntroEdit(Connection conn, String projectIntro)throws Exception {
		
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("IntroEdit");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, projectIntro);
			
			result = pstmt.executeUpdate();
			
			
			
			
		}finally {
			close(pstmt);
			
		}
		
		
		
		
		return result;
	}

	public int changePJName(Connection conn, String projectName) throws Exception {
		
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("changePJName");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, projectName);
			
			result = pstmt.executeUpdate();
			
			
			
			
		}finally {
			close(pstmt);
			
		}
		
		
		
		return result;
	}

	
	
	
	/**
	 * 전체 프로젝트 수 조회 DAO
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public int getListCount(Connection conn) throws Exception{
		
		int listCount = 0;
		
		try {
			
			String sql = prop.getProperty("getListCount");
			
				
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
			
			
			
		}finally {
			close(rs);
			close(stmt);
		}
		
		return listCount;
	}

	
	
	/**
	 * 프로젝트 목록 조회하는 DAO
	 * @param conn
	 * @param pagination
	 * @return
	 * @throws Exception
	 */
	public List<Project> selectProjectList(Connection conn, Pagination pagination) throws Exception{


		List<Project> projectList = new ArrayList<Project>();
		
		try {
			String sql = prop.getProperty("selectProjectList");
			
			// BETWEEN 구문에 들어갈 범위 계산
			int start =  ( pagination.getCurrentPage() - 1 ) * pagination.getLimit() + 1;
			int end = start + pagination.getLimit() - 1;
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Project pro = new Project();
				
				pro.setProjectName(rs.getString(1) );
				pro.setProjectManagerNickname(rs.getString(2)); //관리자 닉네임
				pro.setProjectQuota(rs.getString(3)); //정원
				pro.setProjectIntro(rs.getString(4));
				
				
				projectList.add(pro);
			}
			
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return projectList;
	}

	
	
	/**
	 * 분류를 만족하는 프로젝트 수 조회
	 * @param conn
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public int searchListCount(Connection conn, String condition) throws Exception{

		int listCount = 0;
		
		try {
			String sql = prop.getProperty("searchListCount")  + condition ;

			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
			
		}finally {
			close(rs);
			close(stmt);
		}
		
		return listCount;
	}

	/**
	 * 검색어를 만족하는 
	 * @param conn
	 * @param pagination
	 * @param condition
	 * @return
	 * @throws ex
	 */
	public List<Project> searchProjectList(Connection conn, Pagination pagination, String condition) throws Exception{

		List<Project> projectList = new ArrayList<Project>();
		
		try {
			
			String sql = prop.getProperty("searchProjectList1")
					   + condition
					   + prop.getProperty("searchProjectList2");
			
			// BETWEEN 구문에 들어갈 범위 계산
			int start =  ( pagination.getCurrentPage() - 1 ) * pagination.getLimit() + 1;
			int end = start + pagination.getLimit() - 1;
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				Project pro = new Project();
				
				pro.setProjectName(rs.getString(1) );
				pro.setProjectManagerNickname(rs.getString(2)); //관리자 닉네임
				pro.setProjectQuota(rs.getString(3)); //정원
				pro.setProjectIntro(rs.getString(4));
				
				
				projectList.add(pro);
			}
			
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		
		return projectList;
	}
	
	
	

	/** 가입승인할 회원 조회 DAO
	 * @param conn
	 * @return list
	 * @throws Exception
	 */
	public List<ProjectSign> selectPJSign(Connection conn)throws Exception{
		
		List<ProjectSign> list = new ArrayList<ProjectSign>();
		
		try {
			
			String sql = prop.getProperty("ProjectSign");
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				ProjectSign PJSign = new ProjectSign();
				
				
				PJSign.setMemberNo(rs.getInt(1));
				PJSign.setProjectNo(rs.getInt(2));
				PJSign.setAccountFlag(rs.getString(3));
				PJSign.setMemberNickname(rs.getString(4));
				PJSign.setProfileImage(rs.getString(5));
				
				
				list.add(PJSign);
				
			}
			
		}finally {
			
			close(rs);
			close(stmt);	
			
		}
		
		
		return list;
	}

	/** PJ 가입승인 총 인원 DAO
	 * @param conn
	 * @return count
	 * @throws Exception
	 */
	public int selectPJ(Connection conn) throws Exception{
		
		int count = 0;
		
		try {
			
			String sql = prop.getProperty("selectPJ");
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
			
		}finally {
			close(rs);
			close(stmt);
			
		}
		
		
		return count;
	}

	
	public int accountMember(Connection conn, int memberNo, int projectNo) throws Exception {
		
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("accountMember");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, projectNo);
			pstmt.setInt(2, memberNo);
			
			result = pstmt.executeUpdate();
			
			
			
		}finally {
			close(pstmt);
			
		}
		
		
		return result;
	}


	
}
