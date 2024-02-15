package com.itwillbs.persistence;

import java.util.List;

import com.itwillbs.domain.MemberVO;

/**
 *  DB에서 사용될 동작들을 추상메서드 정의
 */
public interface MemberDAO {

	// 디비서버 시간정보 조회 동작
	public String getTime();
	
	// 회원 가입  동작
	public void insertMember(MemberVO vo);
	
	// 로그인  동작
	public MemberVO loginMember(MemberVO vo);
	public MemberVO loginMember(String userid,String userpw);
	
	// 회원정보 조회
	public MemberVO getMember(String userid);
	
	// 회원정보 수정
	public int updateMember(MemberVO uvo);
	
	// 회원정보 삭제
	public int deleteMember(MemberVO dvo);
	
	// 회원 리스트 조회
	public List<MemberVO> getMemberList();
	
	
}
