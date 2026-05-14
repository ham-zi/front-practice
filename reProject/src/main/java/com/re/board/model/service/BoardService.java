package com.re.board.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.re.Template.Template;
import com.re.board.model.dao.BoardDao;
import com.re.board.model.dto.BoardDto;
import com.re.board.model.dto.PageDto;

public class BoardService {
	BoardDao bd = new BoardDao();
	
	public int boardsCount() {
		SqlSession sqlSession = Template.getSqlSession();
		int result = bd.boardsCount(sqlSession);
		sqlSession.close();
		return result;
	}
	
	public List<BoardDto> selectBoards(PageDto page){
		SqlSession sqlSession = Template.getSqlSession();
		List<BoardDto> list = bd.selectBoards(sqlSession, page);
		sqlSession.close();
		return list;
	}
}
