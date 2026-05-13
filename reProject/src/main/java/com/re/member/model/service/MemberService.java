package com.re.member.model.service;

import org.apache.ibatis.session.SqlSession;

import com.re.Template.Template;
import com.re.member.model.dao.MemberDao;
import com.re.member.model.dto.MemberDto;
import com.re.member.model.dto.PasswordDto;

public class MemberService {

	private MemberDao md = new MemberDao();
	
	public MemberDto selectMember(Long userNo) {
		SqlSession sqlSession = Template.getSqlSession();
		MemberDto member = md.selectMember(sqlSession, userNo);
		sqlSession.close();
		return member;
	}
	
	
	public int createMember(MemberDto member) {
		SqlSession sqlSession = Template.getSqlSession();
		int result = 0;
		MemberDto availableMember = md.availableMember(sqlSession, member.getUserId());
		if(availableMember == null) {
			result = md.createMember(sqlSession, member);
		}
		if(result > 0) {
			sqlSession.commit();
		}
		sqlSession.close();
		return result;
	}
	
	public MemberDto login(MemberDto member) {
		SqlSession sqlSession = Template.getSqlSession();
		MemberDto result = md.login(sqlSession, member);
		sqlSession.close();
		return result;
	}
	
	public MemberDto updateMember(MemberDto member) {
		SqlSession sqlSession = Template.getSqlSession();
		MemberDto userInfo = null;
		int result = md.updateMember(sqlSession, member);
		if(result > 0) {
			sqlSession.commit();
			userInfo = md.selectMember(sqlSession, member.getUserNo());
		}
		sqlSession.close();
		return userInfo;
	}
	
	public int updatePwd(PasswordDto pwd) {
		SqlSession sqlSession = Template.getSqlSession();
		int result = md.updatePwd(sqlSession, pwd);
		if(result > 0) {
			sqlSession.commit();
		}
		sqlSession.close();
		return result;
	}
	
	
	
}
