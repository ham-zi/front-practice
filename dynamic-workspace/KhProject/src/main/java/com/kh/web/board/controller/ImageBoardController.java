package com.kh.web.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.web.board.model.dto.BoardDto;
import com.kh.web.board.model.service.BoardService;


@WebServlet("/boards.im")
public class ImageBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ImageBoardController() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1값을 뽑는다
		// 2가공한다
		// 3서비스호출
		// 4응답화면지정
		
		
		
		List<BoardDto> list = new BoardService().selectImageList();
		request.setAttribute("boards", list);
		System.out.println( list );
		request.getRequestDispatcher("/WEB-INF/views/image_board/list.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
