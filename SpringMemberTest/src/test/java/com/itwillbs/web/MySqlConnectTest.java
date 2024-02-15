package com.itwillbs.web;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;

/**
 *   Junit사용하여 테스트를 진행
 *
 */
public class MySqlConnectTest {
	
	//DB연결정보
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String DBURL = "jdbc:mysql://localhost:3306/springdb";
	private static final String DBID = "root";
	private static final String DBPW = "1234";
	
	
	// @Test : 테스트를 수행하고자하는 메서드 위에 특정어노테이션(@Test)을 선언하면
	//        해당메서드를 Junit이 해당코드를 테스트용 코드로 인식해서 실행
	//         junit 4.11버전 이상 사용
		
	
	// DB연결
	@Test
	public void connect_test() {
		try {
			// 1) 드라이버 로드
			Class.forName(DRIVER);
			System.out.println(" 드라이버 로드 성공!!! ");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
//		Connection con=null;
//		try {
//			// 2) 디비연결
//			con = DriverManager.getConnection(DBURL, DBID, DBPW);
//			System.out.println(" 디비 연결  성공!!! ");
//			System.out.println(con);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			// 자원해제
//			try {
//				con.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
		
		// try-with 구문(JDK 1.7~사용가능)
		//		try(AutoCloseable 인터페이스를 구현한 객체사용){
		//			예외가 발생할지도 모르는 코드
		//		}catch(){
		//			예외발생 처리 구문
		//		}
		
		// 2) 디비연결
		try(Connection con = DriverManager.getConnection(DBURL, DBID, DBPW);) {
			System.out.println(" 디비 연결  성공!!! ");
			System.out.println(con);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		
	}
	
	
	
	
	

}
