package com.itwillbs.service;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.itwillbs.domain.MemberVO;
import com.itwillbs.persistence.MemberDAO;

@Service
public class MemberServiceImpl implements MemberService{

  
private static final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);

@Inject
private MemberDAO mdao;

@Override
public String getTime() {
	logger.debug(" getTime() 호출 ");
	return null;
}

@Override
public void MemberJoin(MemberVO vo) {
	logger.debug(" MemberJoin(MemberVO vo) ");
	
	mdao.insertMember(vo);
	
	logger.debug(" 회원가입 완료 ");
}

@Override
public MemberVO MemberLogin(MemberVO vo) {
	logger.debug(" MemberLogin(MemberVO vo) ");
	
	MemberVO resultVO = mdao.loginMember(vo);
	
	logger.debug(" 로그인 완료 ");
	
	return resultVO;						
}
	

}
