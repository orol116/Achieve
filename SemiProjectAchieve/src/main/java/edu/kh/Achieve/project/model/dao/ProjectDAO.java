package edu.kh.Achieve.project.model.dao;

import static edu.kh.Achieve.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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

}
