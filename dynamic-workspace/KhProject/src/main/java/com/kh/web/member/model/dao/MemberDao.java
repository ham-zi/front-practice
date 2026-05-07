package com.kh.web.member.model.dao;

import org.apache.ibatis.session.SqlSession;

import com.kh.web.member.model.dto.MemberDto;

public class MemberDao {
	
	public int insertMember(SqlSession sqlSession, MemberDto member) {
		return sqlSession.insert("memberMapper.insertMember",member);
	}
	
	public MemberDto login(SqlSession sqlSession, MemberDto member) {
		return sqlSession.selectOne("memberMapper.login", member);
	}
	//where절에 검색할 때 유니크제약조건인 컬럼을 검사하기 때문에 결과는 1개이다.
}
