package com.re.board.model.dao;

import org.apache.ibatis.session.SqlSession;

public class BoardDao {

	public int boardsCount(SqlSession sqlSession) {
		return sqlSession.selectOne("boardMapper.boardsCount");
	}
	
}
