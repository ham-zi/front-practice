package com.kh.web.board.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.web.board.model.dao.BoardDao;
import com.kh.web.board.model.dto.AttachmentDto;
import com.kh.web.board.model.dto.BoardDto;
import com.kh.web.board.model.dto.BoardResponse;
import com.kh.web.common.Template;
import com.kh.web.common.model.dto.PageInfo;
import com.kh.web.member.model.dto.MemberDto;

public class BoardService {
	BoardDao bd = new BoardDao();
	
	public int selectBoardCount() {
		SqlSession sqlSession = Template.getSqlSession();
		int result = bd.selectBoardCount(sqlSession);
		sqlSession.close();
		return result;
	}
	
	public List<BoardDto> selectBoardList(PageInfo pi) {
		SqlSession sqlSession = Template.getSqlSession();
		
		List<BoardDto> boards = bd.selectBoardList(sqlSession, pi);
		
		sqlSession.close();
		
		return boards;
	}
	
	public int selectNoticeCount() {
		SqlSession sqlSession = Template.getSqlSession();
		int result = bd.selectNoticeCount(sqlSession);
		sqlSession.close();
		return result;
	}
	
	public List<BoardDto> selectNoticeList(PageInfo pi) {
		SqlSession sqlSession = Template.getSqlSession();
		
		List<BoardDto> boards = bd.selectNoticeList(sqlSession, pi);
		
		sqlSession.close();
		
		return boards;
	}
	
	
	public int insertBoard(BoardDto board, AttachmentDto at) {
		SqlSession sqlSession = Template.getSqlSession();
		
		String newTitle = board.getBoardTitle().replaceAll("<", "&lt");
		board.setBoardTitle(newTitle);
		String newContent = board.getBoardContent().replaceAll("<", "&lt");
		board.setBoardContent(newContent);
		
		// INSERT 두번
		// BOARD테이블에 한 번 => 무조건
		// Attachment는 할 수도 있고 안할 수 도 있음
		
		int result = bd.insertBoard(sqlSession, board);
		int atResult = 1;
		// ATTACHMENT테이블에 한 번 => 파일이 존재할 때만
		if(at != null) {
			at.setRefBno(board.getBoardNo());
			atResult = bd.insertAttachment(sqlSession,at);
		}
		
		
		// 트랜잭션 처리 => 두 개의 DML구문을 하나의 트랜잭션으로 묶어서 처리
		if(result * atResult > 0) {
			sqlSession.commit();
		} else {
			sqlSession.rollback();
		}
		
		sqlSession.close();
		
		return (result * atResult);
	}
	
	
	//상세조회
	public BoardResponse selectBoard(Long boardNo) {
		SqlSession sqlSession = Template.getSqlSession();
		
		// 총 DB에 세 번 가야함
		// UPDATE ( 조회수 증가 ) => 커밋
		// 커밋 성공시
		// SELECT => BOARD
		// SELECT => ATTACHMENT 
		
		int result = bd.increaseCount(sqlSession, boardNo);
		BoardResponse br = null;
		if(result > 0) {
			sqlSession.commit();
			BoardDto board = bd.selectBoard(sqlSession, boardNo);
			AttachmentDto attachment = bd.selectAttachment(sqlSession,boardNo);
			br = new BoardResponse();
			br.setBoard(board);
			br.setAttachment(attachment);
		} 
		sqlSession.close();
		return br;
		//BoardDto board = bd.selectBoard(sqlSession, boardNo);
	}
	
	public int deleteBoard(BoardDto board) {
		SqlSession sqlSession = Template.getSqlSession();
		// 1. 삭제요청을 보낸 사용자가 로그인도 안하고 요청을 보낼수도 있다 -> servlet에서 처리
		// 2. 삭제요청을 보낸 사용자가 BOARD의 작성자와 다르네?
		// => 요청보낸 사용자의 유저NO가 게시글 작성자 유저NO가 동일한가?
		// where절로 할수 있는거 아닌가?
		BoardDto boardResult = bd.selectBoard(sqlSession, board.getBoardNo());
		if(boardResult.getUserNo().longValue() != board.getUserNo().longValue()) {
			return 0;
		}
		
		// 1. Board 한행 STatus를 Y로 업데이트해야한다.
		int result = bd.deleteBoard(sqlSession,board);
		
		AttachmentDto attachment = bd.selectAttachment(sqlSession, board.getBoardNo());
		if(attachment != null) {
			result *= bd.deleteAttachment(sqlSession, board.getBoardNo());
		}
		
		if(result >0) {
			sqlSession.commit();
		} else {
			sqlSession.rollback();
		}
		
		return result;
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
		
		int result = bd.insertNotice(sqlSession, board);
		
		if(result > 0) {
			sqlSession.commit();
		}
		sqlSession.close();
		
		return result;
	}
	
	public BoardDto selectNotice(Long boardNo) {
		SqlSession sqlSession = Template.getSqlSession();
		BoardDto board = bd.selectNotice(sqlSession, boardNo);
		sqlSession.close();
		return board;
	}
	
	public int deleteNotice(BoardDto board) {
		SqlSession sqlSession = Template.getSqlSession();
		int result = bd.deleteNotice(sqlSession, board);
		if(result>0) {
			sqlSession.commit();
		}
		sqlSession.close();
		return result;
	}
	
}
