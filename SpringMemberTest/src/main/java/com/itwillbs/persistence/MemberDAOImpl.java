package com.itwillbs.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwillbs.domain.MemberVO;

public class MemberDAOImpl implements MemberDAO{
    
	private SqlSession sqlSession;
	
	
	private static final Logger logger = LoggerFactory.getLogger(MemberDAOImpl.class);
	
	// mapper의 위치정보(namespace)
	private static final String NAMESPACE = "com.itwillbs.mapper.MemberMapper";
	
	@Override
	public String getTime() {
		logger.debug(" DAOImpl : getTime() 실행 ");
		
		String time
		 = sqlSession.selectOne("com.itwillbs.mapper.MemberMapper.getTime");
		
		
		return time;
	}

	@Override
	public void insertMember(MemberVO vo) {
		logger.debug(" insertMember(MemberVO vo)  실행 ");
		
		sqlSession.insert(NAMESPACE+".insertMember",vo);
		
	}

	@Override
	public MemberVO loginMember(MemberVO vo) {
		logger.debug(" loginMember(MemberVO vo) 실행 ");
		
		MemberVO resultVO = sqlSession.selectOne(NAMESPACE+".loginMember",vo);
		
		return resultVO;
	}

	@Override
	public MemberVO loginMember(String userid, String userpw) {
        logger.debug(" loginMember(String userid, String userpw) 실행 ");
		
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userid", userid);
		paramMap.put("userpw",userpw);
	
		sqlSession.selectOne(NAMESPACE + ".loginMember",paramMap);

		return null;
	}

	@Override
	public MemberVO getMember(String userid) {
		
		logger.debug(" getMember(String userid) 호출 ");
		
		return sqlSession.selectOne(NAMESPACE+".getMember", userid);
	}

	@Override
	public int updateMember(MemberVO uvo) {
		
		logger.debug(" updateMember(MemberVO uvo) 호출 ");
		
		return sqlSession.update(NAMESPACE+".updateMember", uvo);
	}

	@Override
	public int deleteMember(MemberVO dvo) {
		
		logger.debug(" deleteMember(MemberVO dvo) 호출 ");
		
		return sqlSession.delete(NAMESPACE+".deleteMember", dvo);
	}

	@Override
	public List<MemberVO> getMemberList() {
		
		logger.debug(" getMemberList() 호출 ");
		
		return sqlSession.selectList(NAMESPACE+".getMemberList");
	}
 
	

}
