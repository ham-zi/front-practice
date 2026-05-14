package com.kh.web.board.controller;

import java.io.IOException;
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


@WebServlet("/update.bo")
public class BoardUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public BoardUpdateController() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if(ServletFileUpload.isMultipartContent(request)) {
			int maxSize = 10 * 1024 * 1024;
			String savePath = request.getServletContext().getRealPath("/resources/board_upfiles");
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyRenamePolicy());
			
			// case 1. 첨부파일이 없다. => DB : BOARD테이블만 업데이트
			// case 2. 기존 첨부파일 O, 새 첨부파일 O => DB : BOARD UPDATE + AT UPDATE 
			// case 3. 기존 첨부파일 X, 새 첨부파일 O => DB : BOARD UPDATE + AT INSERT
			
			String boardTitle = multiRequest.getParameter("boardTitle");
			String boardContent = multiRequest.getParameter("boardContent");
			Long boardNo = Long.parseLong(multiRequest.getParameter("boardNo"));
			
			Long userNo = ((MemberDto)request.getSession().getAttribute("userInfo")).getUserNo();
		
			
			BoardDto board = new BoardDto();
			board.setUserNo(userNo);
			board.setBoardNo(boardNo);
			board.setBoardTitle(boardTitle);
			board.setBoardContent(boardContent);
			
			AttachmentDto at = null;
			
			if(multiRequest.getOriginalFileName("reUpfile") != null) {
				// 새 첨부파일이 존재한다면 Attachment 생성
				at = new AttachmentDto();
				at.setOriginName(multiRequest.getOriginalFileName("reUpfile"));
				at.setChangeName(multiRequest.getFilesystemName("reUpfile"));
				at.setFilePath("resources/board_upfiles");
				at.setFileLevel(2);
				at.setBoardType("C");
				
				// INSERT / UPDATE
				// INSERT => 어떤 게시글에 달리는 첨부파일인가 => REF_BNO
				// UPDATE => 원래 파일이 몇번째 행인가?      => FILE_NO 조회 OR FILE_NO를 .jsp에서 갖고오기
				if(multiRequest.getParameter("fileNo") != null) {
					// 기존첨부파일이 있다.
					at.setFileNo(Long.parseLong(multiRequest.getParameter("fileNo")));
					
				} else {
					// 기존첨부파일이 없다.
					at.setRefBno(boardNo);
					
				}
			}  // 데이터 가공
			/* 1. 기능을 만들어야지 => 
			   2. 요구사항 분석 =>
			   3. SQL문을 생각을 완성 =>
			   4. 코드작성 시작
			*/
			
			int result = new BoardService().updateBoard(board,at);
			
			String key = "";
			String value = "";
			String path ="";
			
			if(result > 0) {
				//session.setAttribute("alertMsg", "게시글 수저 성공~");
				//response.sendRedirect(request.getContextPath()+"/detail.bo?boardNo="+boardNo);
				key = "alertMsg";
				value = "게시글수정성공";
				path = request.getContextPath() + "/detail.bo?boardNo=" + boardNo;
			} else {
				//session.setAttribute("message", "게시글 수정에 실패");
				//response.sendRedirect(request.getContextPath()+"fail.do");
				key = "message";
				value = "게시글 수정 실패...";
				path = request.getContextPath() + "/fail.do";
			}
			
			session.setAttribute(key, value);
			response.sendRedirect(path);
			
			
		} 
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
