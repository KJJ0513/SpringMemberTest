package com.itwillbs.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.itwillbs.domain.MemberVO;
import com.itwillbs.persistence.MemberDAO;

// @Service : root-context.xml(스프링)에서 해당객체를 인식 

@Service
public class MemberServiceImpl  implements MemberService {

	
	private static final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);
	// DAO객체 주입
	@Inject
	private MemberDAO mdao;
	
	//시간정보 조회
	@Override
	public String getTime() {
		logger.debug(" getTime() 호출 ");
		
		return mdao.getTime();
	}

	// alt shift s + v
	@Override
	public void memberJoin(MemberVO vo) {
		logger.debug(" S : memberJoin(MemberVO vo) 실행 ");
		logger.debug(" S : DAO 회원가입 처리동작 호출 ");
		
		mdao.insertMember(vo);
		
		logger.debug(" S : 회원가입 완료! ");	
		logger.debug(" S : 다시 컨트롤러로 이동");
	}

	
	
	@Override
	public MemberVO memberLogin(MemberVO vo) {
		logger.debug(" memberLogin(MemberVO vo) ");
		
		MemberVO resultVO = mdao.loginMember(vo);
		
		logger.debug(" 로그인 처리 완료! ");
		
		return resultVO;
	}

	@Override
	public MemberVO memberInfo(String id) {
		logger.debug(" memberInfo(String id) 호출 ");
		MemberVO resultVO = mdao.getMember(id);
		logger.debug(" 회원정보 조회 끝, 정보를 컨트롤러로 전송 ");		
		return resultVO;
	}
	

	@Override
	public int memberUpdate(MemberVO vo) {
		logger.debug(" memberUpdate(MemberVO vo) ");
		
		return mdao.updateMember(vo);
	}

	
	@Override
	public int memberDelete(MemberVO vo) {
		logger.debug("  memberDelete(MemberVO vo) 호출 ");
		
		return mdao.deleteMember(vo);
	}

	
	@Override
	public List<MemberVO> memberList() {
		logger.debug("  memberList()  호출 ");
				
		return mdao.getMemberList();
	}

	
	
}
