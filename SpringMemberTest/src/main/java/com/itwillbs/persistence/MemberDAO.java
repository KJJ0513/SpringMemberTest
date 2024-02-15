package com.itwillbs.persistence;

import java.util.List;

import com.itwillbs.domain.MemberVO;

public interface MemberDAO {
 
	// 시간정보 조회
	public String getTime();
	
	// 회원가입
	public void insertMember(MemberVO vo);
	
	// 로그인
	public MemberVO loginMember(MemberVO vo);
	public MemberVO loginMember(String userid, String userpw);
	
	// 조회
    public MemberVO getMember(String userid);
	
	// 수정
	public int updateMember(MemberVO uvo);
	
	// 삭제
	public int deleteMember(MemberVO dvo);
	
	// 리스트
	public List<MemberVO> getMemberList();
	
}
