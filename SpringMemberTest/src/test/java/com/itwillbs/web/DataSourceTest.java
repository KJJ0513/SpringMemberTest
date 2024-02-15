package com.itwillbs.web;

import java.sql.Connection;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Spring + Junit사용해서 테스트
 *
 */

//@RunWith(SpringJUnit4ClassRunner.class)
// -> 스프링을 사용해서 테스트하겠다라는 설정
//@ContextConfiguration(
//		locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"}
//		)
// -> 스프링 테스트에 필요한 설정 정보를 가져오기(사용하도록 허용)

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"}
		)
public class DataSourceTest {
	
	// 디비연결정보 필요함 => 의존하고 있다 => 의존관계
	
	// @Inject : DI(Dependency Injection)의존성 주입
	// => 의존관계에 있는 객체를 주입(가져와서) 사용
	
	@Inject
	private DataSource ds;
	//	 new DataSource();(직접생성x) 의존관계 주입을 통한 객체 생성 
	
	
	private static final Logger logger = LoggerFactory.getLogger(DataSourceTest.class);
	
	
	// 디비 연결 테스트
	// junit을 사용한 테스트 수행(@Test)
	@Test
	public void testConnection() {
		// 디비연결정보 필요함
		//System.out.println(ds);
		try {
			Connection con = ds.getConnection();
			System.out.println("@@@@@@@@ con : "+ con );
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		
	}
	
	
}
