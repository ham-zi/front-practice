package com.kh.web.board.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.web.board.model.dto.BoardDto;
import com.kh.web.common.model.dto.PageInfo;

public class BoardDao {

	
	public int selectBoardCount(SqlSession sqlSession) {
		return sqlSession.selectOne("boardMapper.selectBoardCount");
	}
	
	
	
	public List<BoardDto> selectBoardList(SqlSession sqlSession, PageInfo pi) {
		return sqlSession.selectList("boardMapper.selectBoardList", pi);
 	}
	
	public int selectNoticeCount(SqlSession sqlSession) {
		return sqlSession.selectOne("boardMapper.selectNoticeCount");
	}
	
	public List<BoardDto> selectNoticeList(SqlSession sqlSession, PageInfo pi) {
		return sqlSession.selectList("boardMapper.selectBoardList", pi);
 	}
	
}
