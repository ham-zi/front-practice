package com.re.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.re.board.model.dto.BoardDto;
import com.re.board.model.dto.PageDto;
import com.re.board.model.service.BoardService;


@WebServlet("/boards_page.do")
public class boardsPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public boardsPageController() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		// 페이징 처리 숫자들
		
		int listCount = new BoardService().boardsCount(); //board 모든열들을 count
		int pageLimit = 5; //내가정함
		int boardLimit = 5; //내가정함
		int currentPage = (int)Integer.parseInt(request.getParameter("page")); // 요청받은 값
		int maxPage = (int)Math.ceil(((double)listCount/boardLimit)); 
		int startPage = ((currentPage-1)/pageLimit)*pageLimit +1; // ((currentPage-1)/pageLimit) *pageLimit +1
		
		int endPage = startPage + pageLimit -1; // startpage + pageLimit -1
		if (endPage>maxPage) {
			endPage = maxPage;
		}
		
		int offset = (currentPage - 1) * boardLimit;
		PageDto pi = new PageDto(listCount, pageLimit, boardLimit, currentPage, maxPage , startPage, endPage, offset);
		System.out.println(startPage);
		System.out.println(endPage);
		System.out.println(pi.getStartPage());
		System.out.println(pi.getEndPage());
		List<BoardDto> list =new BoardService().selectBoards(pi);
		
		if(list.isEmpty()) {
			session.setAttribute("message", "게시글 조회 실패");
			response.sendRedirect(request.getContextPath()+"/fail.do");
		} else {
			request.setAttribute("boards", list);
			request.setAttribute("pi", pi);
			request.getRequestDispatcher("/WEB-INF/views/board/boards_page.jsp").forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
