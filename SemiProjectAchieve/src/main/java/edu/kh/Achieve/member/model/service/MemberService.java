package edu.kh.Achieve.member.model.service;

import static edu.kh.Achieve.common.JDBCTemplate.close;
import static edu.kh.Achieve.common.JDBCTemplate.commit;
import static edu.kh.Achieve.common.JDBCTemplate.getConnection;
import static edu.kh.Achieve.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import edu.kh.Achieve.member.model.dao.MemberDAO;
import edu.kh.Achieve.member.model.vo.Member;

public class MemberService {
	
	private MemberDAO dao = new MemberDAO();
	
	
	/**
	 * 로그인 Service
	 * @param mem
	 * @return loginMember
	 * @throws Exception
	 */
	public Member login(Member mem) throws Exception {
		
		Connection conn = getConnection();
		Member loginMember = dao.login(conn, mem);
		close(conn);
		return loginMember;
	}
	

	/**
	 * 회원 정보 수정
	 * @param mem
	 * @return result
	 * @throws Exception
	 */
	public int updateMember(Member mem) throws Exception{
		
		Connection conn = getConnection();
		int result = dao.updateMember(conn, mem);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);

		return result;
	}


	/**
	 * 비밀번호 변경 Service
	 * @param currentPw
	 * @param newPw
	 * @param memberNo
	 * @return result
	 * @throws Exception
	 */
	public int changePw(String currentPw, String newPw, int memberNo) throws Exception {
		Connection conn = getConnection();
		
		int result = dao.changePw(conn, currentPw, newPw, memberNo);
		if(result > 0) commit(conn);
		else rollback(conn);
		
		
		close(conn);
		
		return result;
	}


	/** 회원가입 Service
	 * @param mem
	 * @return result
	 * @throws Exception
	 */
	public int signUp(Member mem) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.signUp(conn, mem);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}
		
	/**
	 * 회원 탈퇴 Service
	 * @param memberNo
	 * @return result
	 * @throws Exception
	 */
	public int secession(int memberNo) throws Exception {
		Connection conn = getConnection();
		int result = dao.secession(conn, memberNo);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);	
		return result;
	}

	/** 이메일 중복검사 Service
	 * 
	 * @param memberEmail
	 * @return
	 * @throws Exception
	 */
	public int emailDupCheck(String memberEmail) throws Exception{
		
		Connection conn = getConnection(); // DBCP에서 만들어둔 커넥션 얻어오기
		
		int result = dao.emailDupCheck(conn, memberEmail);
		
		close(conn);
		
		return result;
	}
	
	

	/** 닉네임 중복 체크
	 * 
	 * @param memberNickname
	 * @return
	 */
	public int nicknameDupCheck(String memberNickname) throws Exception{
		Connection conn = getConnection();
		
		int result = dao.nicknameDupCheck(conn,memberNickname);
		
		close(conn);
		
		return result;
	}


	/** 멤버 리스트 서비스
	 * 
	 * @return list
	 */
	public List<Member> selectAll() throws Exception{
		
		Connection conn = getConnection();
		
		List<Member> list  = dao.selectAll(conn);
		
		close(conn);
		
		return list;
	}



}
