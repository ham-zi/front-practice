package com.kh.web.notice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.web.board.model.dto.BoardDto;
import com.kh.web.board.model.service.BoardService;
import com.kh.web.member.model.dto.MemberDto;


@WebServlet("/detail.no")
public class NoticeDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public NoticeDetailController() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		MemberDto member = (MemberDto)request.getAttribute("userInfo");
		


		Long boardNo = Long.parseLong(request.getParameter("boardNo"));
		
		BoardDto board = new BoardService().selectNotice(boardNo);
		System.out.println(board);
		if(board != null) {
			if(member != null) {
				member.getUserId();
			}
			
			
			request.setAttribute("board", board);
			
			
			
			request.getRequestDispatcher("/WEB-INF/views/board/detail-notice.jsp").forward(request, response);
		} else {
			session.setAttribute("message", "공지사항 조회에 실패했습니다.");
			response.sendRedirect(request.getContextPath()+"/fail.do");
			
		}
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
