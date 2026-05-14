package com.kh.web.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.kh.web.board.model.dto.AttachmentDto;
import com.kh.web.board.model.dto.BoardDto;
import com.kh.web.board.model.service.BoardService;
import com.kh.web.common.MyRenamePolicy;
import com.kh.web.member.model.dto.MemberDto;
import com.oreilly.servlet.MultipartRequest;


@WebServlet("/insert.im")
public class ImageBoardInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ImageBoardInsertController() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(ServletFileUpload.isMultipartContent(request)) {//멀티파티 객체로 잘 왔나 검증해야한다.
			
			//용량제한
			int maxSize = 100000000;
			
			//경로
			String savePath = request.getServletContext().getRealPath("/resources/image_upfiles");
			
			//객체생성 MultipartRequest 변수명 = new 객체
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyRenamePolicy());
			// 파일업로드 끝!
			
			//값 추출
			String boardTitle = multiRequest.getParameter("title");
			String boardContent = multiRequest.getParameter("content");
			
			HttpSession session = request.getSession();
			MemberDto member = (MemberDto)session.getAttribute("userInfo");
			Long userNo = member.getUserNo();
			System.out.println("값추출");
			//가공 board
			BoardDto board = new BoardDto();
			board.setBoardTitle(boardTitle);
			board.setBoardContent(boardContent);
			board.setUserNo(userNo);
			System.out.println("가공");
			//가공 attachment
			// => 사진 게시글 작성 from required
			// 게시글 당 최소 한 개의 첨부파일이 존재
			/*
			AttachmentDto at1 = null;
			AttachmentDto at2 = null;
			AttachmentDto at3 = null;
			AttachmentDto at4 = null;
			if(multiRequest.getOriginalFileName("file1") != null) {
				at1 = new AttachmentDto();
			}
			if(multiRequest.getOriginalFileName("file2") != null) {
				at2 = new AttachmentDto();
			}
			if(multiRequest.getOriginalFileName("file3") != null) {
				at3 = new AttachmentDto();
			}
			if(multiRequest.getOriginalFileName("file4") != null) {
				at4 = new AttachmentDto();
			}
			*/
			
			List<AttachmentDto> files = new ArrayList<>();
			
			for(int i=1; i <= 4; i++) {
				String key = "file"+ i;
				
				if(multiRequest.getOriginalFileName(key)!=null) {
					//파일이 존재한다
					AttachmentDto at = new AttachmentDto();
					at.setOriginName(multiRequest.getOriginalFileName(key));
					at.setChangeName(multiRequest.getFilesystemName(key));
					at.setFilePath("resources/image_upfiles");
					at.setBoardType("I");
					/* 
					if(i == 1) {
						at.setFileLevel(1);
					} else {
						at.setFileLevel(2);
					}
					*///실무 방식 삼항연산자
					at.setFileLevel(i == 1 ? 1: 2)
;					
					files.add(at);
					// 신택틴슈가
					// 문법적설탕
					
				}
			}
			// 가공 끝
			int result = new BoardService().insertImage(board,files);
			System.out.println("게시판생성");
			
			if(result > 0) {
				response.sendRedirect(request.getContextPath()+"/boards.im");
			} else {
				session.setAttribute("message", "이미지 게시글 실패");
				response.sendRedirect(request.getContextPath()+"/fail.do");
			}
			
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
