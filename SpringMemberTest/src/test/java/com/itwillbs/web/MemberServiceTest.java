package com.itwillbs.web;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.itwillbs.service.MemberService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"}
		)
public class MemberServiceTest {
	
	// 서비스 객체를 주입
	@Inject
	private MemberService mService;
	
	
	private static final Logger logger = LoggerFactory.getLogger(MemberServiceTest.class);
	
	
	@Test
	public void 시간정보조회() {
		String time = mService.getTime();
		
		logger.debug(" 결과 : "+time);
		
	}
	
	
	
	
	
	
	
	

	
	
}
