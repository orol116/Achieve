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


import edu.kh.Achieve.project.model.vo.Project;

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
	
	

	public List<Project> searchAll(Connection conn) throws Exception{

		List<Project> list = new ArrayList<Project>();
		
		try {
			String sql = prop.getProperty("searchAll");
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while( rs.next() ) {
				Project pro = new Project();
				
				pro.setProjectName(rs.getString(1) );
				pro.setProjectManager(rs.getInt(2)); //매니저 번호
				pro.setProjectQuota(rs.getString(3)); //정원
				pro.setProjectIntro(rs.getString(4));
				
				
				list.add(pro); // 리스트 추가
			}
			
		}finally {
			close(rs);
			close(stmt);
		}
		
		return list;
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

	
	
	
	
	
	
}
