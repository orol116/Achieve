package edu.kh.Achieve.project.model.service;

import static edu.kh.Achieve.common.JDBCTemplate.*;

import java.sql.Connection;

import edu.kh.Achieve.project.model.dao.ProjectDAO;
import edu.kh.Achieve.project.model.vo.Project;

public class ProjectService {
	
	private ProjectDAO dao = new ProjectDAO();	

	/** 프로젝트 생성 Service
	 * @param project
	 * @return result
	 * @throws Exception
	 */
	public int createProject(Project project, int memberNo)throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.createProject(conn,project, memberNo);
		
		if(result > 0) commit(conn);
		else           rollback(conn);
		
		
		
		
		return result;
	}

	
	/** 프로젝트 이름 중복검사 servlet
	 * @param projectName
	 * @return result
	 * @throws Exception
	 */
	public int PJDupCheck(String projectName) throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.PJDupCheck(conn, projectName);
		
		if(result > 0) commit(conn);
		else		   rollback(conn);
		
		
		
		
		return result;
	}

}
