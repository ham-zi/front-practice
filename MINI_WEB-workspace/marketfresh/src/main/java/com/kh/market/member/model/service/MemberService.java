package com.kh.market.member.model.service;

import org.apache.ibatis.session.SqlSession;


import com.kh.market.common.Template;
import com.kh.market.exception.ExistMemberIdException;
import com.kh.market.member.model.dao.MemberDao;
import com.kh.market.member.model.dto.MemberDto;
import com.kh.market.member.model.dto.PasswordDto;

public class MemberService {
	MemberDao md = new MemberDao();
	
	public int createMember(MemberDto member) {
		SqlSession sqlSession = Template.getSqlSession();
		
		MemberDto existMember = md.availableMemberId(sqlSession, member.getMemberId());
		if(existMember != null) {
			throw new ExistMemberIdException();
		}
		
		
		int result = md.createMember(sqlSession, member);
		if ( result > 0 ) {
			sqlSession.commit();
		}
		sqlSession.close();
		return result;
	}
	
	public MemberDto login(MemberDto member) {
		SqlSession sqlSession = Template.getSqlSession();
		MemberDto loginMember = md.login(sqlSession, member);
		sqlSession.close();
		return loginMember;
	}
	
	public MemberDto updateMember(MemberDto member) {
		SqlSession sqlSession = Template.getSqlSession();
		MemberDto updateMember = null;
		int result = md.updateMember(sqlSession, member);
		if(result > 0) {
			sqlSession.commit();
			updateMember = md.findMemberByNo(sqlSession, member.getMemberNo());
		}
		sqlSession.close();
		return updateMember;
	}
	
	public MemberDto updatePwd(PasswordDto pwdDto) {
		SqlSession sqlSession =Template.getSqlSession();
		MemberDto updateMember = null;

		int result = md.updatePwd(sqlSession, pwdDto);
		if(result > 0) {
			sqlSession.commit();	
			updateMember = md.findMemberByNo(sqlSession, pwdDto.getMemberNo());
		}
		sqlSession.close();
		return updateMember;
	}
	
	public int softDeleteMember(MemberDto member) {
		SqlSession sqlSession = Template.getSqlSession();
		int result = md.softDeleteMember(sqlSession, member);
		if(result > 0) {
			sqlSession.commit();
		}
		sqlSession.close();
		return result;
	}
}
