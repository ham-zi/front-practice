package com.re.member.model.dao;

import org.apache.ibatis.session.SqlSession;

import com.re.member.model.dto.MemberDto;
import com.re.member.model.dto.PasswordDto;

public class MemberDao {

	public MemberDto selectMember(SqlSession sqlSession, Long userNo) {
		return sqlSession.selectOne("memberMapper.selectMember", userNo);
	}

	public MemberDto availableMember(SqlSession sqlSession, String userId) {
		return sqlSession.selectOne("memberMapper.availableMember",userId);
	}
	
	public int createMember(SqlSession sqlSession, MemberDto member) {
		return sqlSession.insert("memberMapper.createMember", member);
	}
	
	public MemberDto login(SqlSession sqlSession, MemberDto member) {
		return sqlSession.selectOne("memberMapper.login", member);
	}
	
	public int updateMember(SqlSession sqlSession, MemberDto member) {
		return sqlSession.update("memberMapper.updateMember", member);
	}
	
	public int updatePwd(SqlSession sqlSession, PasswordDto pwd) {
		return sqlSession.update("memberMapper.updatePwd", pwd);
	}
}
