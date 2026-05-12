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


@WebServlet("/insert.no")
public class InsertNoticeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public InsertNoticeController() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		MemberDto member = (MemberDto)session.getAttribute("userInfo");
		
		if("admin".equals(member.getUserId())) {
			
			String boardTitle = request.getParameter("boardTitle");
			String boardContent = request.getParameter("boardContent");
			Long userNo = member.getUserNo();
			
			BoardDto board = new BoardDto();
			board.setBoardTitle(boardTitle);
			board.setBoardContent(boardContent);
			board.setUserNo(userNo);
			
			int result = new BoardService().insertNotice(board);
			
			if(result > 0) {
				response.sendRedirect(request.getContextPath() + "/notice.do?page=1");
			} else {
				session.setAttribute("message", "공지사항 작성에 실패했습니다.");
				response.sendRedirect(request.getContextPath()+"/fail.do");
			}
			
		} else {
			session.setAttribute("message", "관리자만 공지사항 작성이 가능합니다.");
			response.sendRedirect(request.getContextPath()+"/fail.do");
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
