package com.kh.market.member.model.dao;

import org.apache.ibatis.session.SqlSession;

import com.kh.market.member.model.dto.MemberDto;
import com.kh.market.member.model.dto.PasswordDto;



public class MemberDao {

	public MemberDto availableMemberId(SqlSession sqlSession, String memberId) {
		return sqlSession.selectOne("memberMapper.availableMemberId", memberId);
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
	
	public MemberDto findMemberByNo(SqlSession sqlSession, Long memberNo) {
		return sqlSession.selectOne("memberMapper.findMemberByNo", memberNo);
	}
	
	public int updatePwd(SqlSession sqlSession, PasswordDto passwordDto) {
		return sqlSession.update("memberMapper.updatePwd", passwordDto);
	}
	
	public int softDeleteMember(SqlSession sqlSession, MemberDto member) {
		return sqlSession.update("memberMapper.softDeleteMember",member);
	}
	
}
