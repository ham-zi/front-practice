package com.kh.web.notice.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.web.board.model.dto.BoardDto;
import com.kh.web.board.model.service.BoardService;
import com.kh.web.common.model.dto.PageInfo;
import com.kh.web.member.model.dto.MemberDto;


@WebServlet("/notice.do")
public class NoticePageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public NoticePageController() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//게시글 조회(페이징처리)
		HttpSession session = request.getSession();
				
		int listCount; // 현재 게시판의 총 게시글 개수 << 페이징 개수를 파악할 수있다
		int currentPage; // 현재 사용자가 요청한 페이지
		int pageLimit; // 한번에 보여주는 최대 페이지 개수
		int boardLimit; // 한번에 보여주는 최대 게시글 개수
		int maxPage; // 끝자락 페이지 번호
		int startPage; // 보여지는 페이지 시작번호
		int endPage; // 보여지는 페이지 마지막번호
		
		
		listCount = new BoardService().selectNoticeCount();
		currentPage = Integer.parseInt(request.getParameter("page"));
		pageLimit = 3;
		boardLimit = 3;
		maxPage = (int)(Math.ceil((double) listCount) / pageLimit);
		startPage = (currentPage-1)/pageLimit*pageLimit +1;
		endPage = startPage + pageLimit -1;
		
				
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		int offset = (currentPage - 1) * boardLimit;
		PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit, startPage, endPage, maxPage, offset);
		
		List<BoardDto> boards = new BoardService().selectNoticeList(pi);
		
		request.setAttribute("pi", pi);
		request.setAttribute("boards", boards);
		
		request.getRequestDispatcher("/WEB-INF/views/board/noticeBoards.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
