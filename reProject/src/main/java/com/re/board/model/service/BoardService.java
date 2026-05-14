package com.re.board.model.service;

import org.apache.ibatis.session.SqlSession;

import com.re.Template.Template;
import com.re.board.model.dao.BoardDao;

public class BoardService {
	BoardDao bd = new BoardDao();
	
	public int boardsCount() {
		SqlSession sqlSession = Template.getSqlSession();
		int result = bd.boardsCount(sqlSession);
		sqlSession.close();
		return result;
	}
}
