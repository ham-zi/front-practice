package com.kh.web.notice.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.web.board.model.dto.BoardDto;
import com.kh.web.common.Template;
import com.kh.web.common.model.dto.PageInfo;
import com.kh.web.notice.model.dao.NoticeDao;

public class NoticeService {
	NoticeDao nd = new NoticeDao();
	
	public int selectNoticeCount() {
		SqlSession sqlSession = Template.getSqlSession();
		int result = nd.selectNoticeCount(sqlSession);
		sqlSession.close();
		return result;
	}
	
	public List<BoardDto> selectNoticeList(PageInfo pi) {
		SqlSession sqlSession = Template.getSqlSession();
		
		List<BoardDto> boards = nd.selectNoticeList(sqlSession, pi);
		
		sqlSession.close();
		
		return boards;
	}

	public int insertNotice(BoardDto board) {
		SqlSession sqlSession = Template.getSqlSession();
		if(board.getBoardContent() != null && board.getBoardTitle() != null) {
		String newTitle = board.getBoardTitle().replaceAll("<", "&lt");
		board.setBoardTitle(newTitle);
		String newContent = board.getBoardContent().replace("<", "&lt");
		board.setBoardContent(newContent);
		} else {
			return 0;
		}
		
		int result = nd.insertNotice(sqlSession, board);
		
		if(result > 0) {
			sqlSession.commit();
		}
		sqlSession.close();
		
		return result;
	}
	
	public BoardDto selectNotice(Long boardNo) {
		SqlSession sqlSession = Template.getSqlSession();
		BoardDto board = nd.selectNotice(sqlSession, boardNo);
		sqlSession.close();
		return board;
	}
	
	public int deleteNotice(BoardDto board) {
		SqlSession sqlSession = Template.getSqlSession();
		int result = nd.deleteNotice(sqlSession, board);
		if(result>0) {
			sqlSession.commit();
		}
		sqlSession.close();
		return result;
	}
	
	
}
