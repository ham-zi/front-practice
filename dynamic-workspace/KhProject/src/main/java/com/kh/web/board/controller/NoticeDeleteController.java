package com.kh.web.board.controller;

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


@WebServlet("/delete.no")
public class NoticeDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public NoticeDeleteController() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberDto member = (MemberDto)session.getAttribute("userInfo");
		if(member == null) {
			session.setAttribute("message", "로그인하셔야합니다.");
			response.sendRedirect(request.getContextPath()+"/fail.do");
		}
		
		Long boardNo =  Long.parseLong(request.getParameter("boardNo"));
		Long userNo = member.getUserNo();
		BoardDto board = new BoardDto();
		board.setBoardNo(boardNo);
		board.setUserNo(userNo);
		if(userNo != 1) {
			session.setAttribute("message", "관리자 이외에 수정/삭제가 불가능합니다..");
			response.sendRedirect(request.getContextPath()+"/fail.do");
		}
		
		
		
		int result = new BoardService().deleteNotice(board);
		if(result > 0 ) {
			response.sendRedirect(request.getContextPath() + "/notice.do?page=1");
		} else {
			session.setAttribute("message", "공지사항 삭제 실패했습니다..");
			response.sendRedirect(request.getContextPath()+"/fail.do");
		}
		
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
