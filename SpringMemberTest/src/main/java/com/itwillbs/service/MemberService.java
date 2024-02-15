package com.itwillbs.service;

import com.itwillbs.domain.MemberVO;

public interface MemberService {

	// 시간정보
	public String getTime();
	
	// 회원가입
	public void MemberJoin(MemberVO vo);
	
	// 로그인
	public MemberVO MemberLogin(MemberVO vo);
	
}
