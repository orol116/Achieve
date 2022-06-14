package edu.kh.Achieve.project.model.service;

import static edu.kh.Achieve.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

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

	
	/** 프로젝트 이름 중복검사 service
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


	/** 프로젝트 검색 조회 페이지 Service
	 * @return
	 */
	public List<Project> searchAll() throws Exception{
		
		Connection conn = getConnection();
		
		List<Project> list = dao.searchAll(conn);
		
		close(conn);
		
		return list;
    
  }
	
	/** 프로젝트 공개/ 비공개 변경 service
	 * @param openStatus
	 * @return result
	 * @throws Exception
	 */
	public int changeStatus(String openStatus)throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.changeStatus(conn,openStatus );
		
		if(result > 0) commit(conn);
		else		   rollback(conn);
		
		
		
		
		return result;
	}


	/** 프로젝트 소개 변경 service
	 * @param projectIntro
	 * @return result
	 * @throws Exception
	 */
	public int IntroEdit(String projectIntro) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.IntroEdit(conn, projectIntro);
		
		if(result > 0) commit(conn);
		else		   rollback(conn);
		
		
		
		return result;
	}


	
	public int changePJName(String projectName) throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.changePJName(conn,projectName);
		
		if(result > 0) commit(conn);
		else		   rollback(conn);
		
		
		
		return result;
	}

}
