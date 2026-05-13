package com.kh.web.notice.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.web.board.model.dto.BoardDto;
import com.kh.web.common.model.dto.PageInfo;

public class NoticeDao {
	public int insertNotice(SqlSession sqlSession, BoardDto board) {
		return sqlSession.insert("boardMapper.insertNotice",board);
	}
	
	public BoardDto selectNotice(SqlSession sqlSession, Long boardNo) {
		return sqlSession.selectOne("boardMapper.selectNotice", boardNo);
	}
	
	public int deleteNotice(SqlSession sqlSession, BoardDto board) {
		return sqlSession.delete("boardMapper.deleteNotice", board);
	}
	
	public int selectNoticeCount(SqlSession sqlSession) {
		return sqlSession.selectOne("boardMapper.selectNoticeCount");
	}
	public List<BoardDto> selectNoticeList(SqlSession sqlSession, PageInfo pi) {
		return sqlSession.selectList("boardMapper.selectNoticeList", pi);
 	}
}
