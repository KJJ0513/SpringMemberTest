package com.itwillbs.web;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.itwillbs.domain.MemberVO;
import com.itwillbs.persistence.MemberDAO;
import com.itwillbs.persistence.MemberDAOImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
public class MemberDAOTest {
	         //=>Controller + Action페이지동작
	
	
	// DAO 객체 생성 => 주입
	@Inject
	private MemberDAO mdao;
	// MemberDAO mdao = new MemberDAOImpl();

	//	@Inject
	//	private MemberDAOImpl mdaoImpl;
	// MemberDAOImpl mdao = new MemberDAOImpl()
	
	// 로그처리 객체
//	private static final Logger logger = 
//			          LoggerFactory.getLogger(MemberDAOTest.class);
	
	
	private static final Logger logger = LoggerFactory.getLogger(MemberDAOTest.class);
	
	//@Test
	public void 시간정보조회() {
		System.out.println(" Test : 시간정보조회() 실행 ");
		logger.info("Test : 시간정보조회() 실행");
		logger.debug("@@@@@@debug@@@@@@@@");
		
//		logger.info("바로 로그작성가능");
//		logger.debug("qqqqq");
		
		String time = mdao.getTime();
		
		System.out.println(" Test : 결과 : "+time);	
		logger.info(" Test : 결과 : "+time);
	}
	
	//@Test
	public void 회원가입테스트() {
		logger.debug(" 회원가입테스트() 실행 ");
		logger.debug(" DAO 회원가입 메서드 호출 ");
		
		// 임시 회원정보 객체
		MemberVO vo = new MemberVO();
		vo.setUserid("admin2");
		vo.setUserpw("1234");
		vo.setUsername("관리자");
		vo.setUseremail("admin@admin.com");
		
		mdao.insertMember(vo);
		
		logger.debug(" 회원가입 완료@@@@@@@@@@@ ");
		
	}
	
	//@Test
	public void 로그인테스트() {
		logger.debug(" 로그인테스트() 실행");
		
		//임시 사용자 계정
		MemberVO vo = new MemberVO();	
		vo.setUserid("admin"); // 가정) Member - ID
		vo.setUserpw("1234");  // 가정) Board - BNO
		
		//MemberVO resultvo = mdao.loginMember(vo);
		MemberVO resultvo = mdao.loginMember(vo.getUserid(),vo.getUserpw());
		
		if(resultvo != null) {
			logger.debug(" 로그인 성공! ");
			logger.debug(" 메인페이지로 이동 ");
		}else {
			logger.debug(" 로그인 실패! ");
		}
		
	}
	
	//@Test
	public void 회원정보조회() {
		logger.debug(" 특정 사용자의 정보를 조회하는 메서드 실행! ");
		logger.debug(" id: admin, pw : 1234 계정정보 사용 ");
		
		MemberVO vo = mdao.getMember("admin");
		logger.debug("vo : "+vo);
	}
	
	// 회원정보 수정 - admin/1234 계정의 이름,updatedate변경
	//@Test
	public void 회원정보수정() {
		logger.debug(" 회원정보수정() 호출 ");
		MemberVO uvo = new MemberVO();
		uvo.setUserid("admin");
		uvo.setUserpw("1234");
		uvo.setUsername("수정관리자");		
		
		mdao.updateMember(uvo);		
	}
	
	// 회원정보 삭제 - admin/1234 계정정보 삭제
	//                삭제 결과에 따른 메세지 출력 "삭제완료","삭제실패"
	//@Test
	public void 회원정보삭제() {
		logger.debug(" 회원정보삭제() 호출 ");
		
		MemberVO dvo = new MemberVO();
		dvo.setUserid("admin2");
		dvo.setUserpw("1234");
		
		int result = mdao.deleteMember(dvo);
		if(result == 1) {
			logger.debug(" 회원정보 삭제 완료! ");
		}else {
			logger.debug(" 회원정보 삭제 실패! ");
		}
	}
	
	
	//회원목록 출력
	@Test
	public void 회원목록출력() {
		logger.debug(" 회원목록출력() 확인 ");
		List<MemberVO> memberList = mdao.getMemberList();
		
		logger.debug("memberList : "+memberList);
		
	}
	
	
	
	

}//MemberDAOTest
