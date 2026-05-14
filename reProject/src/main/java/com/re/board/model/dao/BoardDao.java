package com.re.board.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.re.board.model.dto.BoardDto;
import com.re.board.model.dto.PageDto;

public class BoardDao {

	public int boardsCount(SqlSession sqlSession) {
		return sqlSession.selectOne("boardMapper.boardsCount");
	}
	
	public List<BoardDto> selectBoards(SqlSession sqlSession, PageDto page){
		return sqlSession.selectList("boardMapper.selectBoards", page);
	}
	
}
