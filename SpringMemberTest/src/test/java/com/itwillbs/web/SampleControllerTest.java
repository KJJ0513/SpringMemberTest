package com.itwillbs.web;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(
//		locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"}
//		)
// => 스프링 (DAO,Service)으로 테스트 하겠다.

// @WebAppConfiguration 
// => 스프링 MVC (컨트롤러) 로 테스트하겠다

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(
		locations = {"file:src/main/webapp/WEB-INF/spring/**/*-context.xml"}
		)
public class SampleControllerTest {
	
	private static final Logger logger = LoggerFactory.getLogger(SampleControllerTest.class);

	@Inject
	private WebApplicationContext wac;
	// => 테스트하려는 프로젝트 정보를 주입
	
	private MockMvc mockMvc;
	
	//@Test
	public void wac확인() {
		logger.debug(" wac : "+wac);
	}
	
	// @Before : @Test 메서드 실행전에 반드시 실행하는 동작 메서드 
	
	@Before
	public void setUp() {
		logger.debug(" MockMvc 객체를 준비 ");
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		
		logger.debug("MockMvc 객체 setUp완료");	
	}
	
	@Test
	public void testDoA() {
		logger.debug(" MockMvc 테스트 시작 ");
		
		try {
			this.mockMvc.perform(MockMvcRequestBuilders.get("/doA"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		logger.debug(" MockMvc 테스트 끝 ");		
	}
	
	
	

	
	
	
}
