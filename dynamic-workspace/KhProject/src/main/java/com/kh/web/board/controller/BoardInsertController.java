package com.kh.web.board.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
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


@WebServlet("/insert.bo")
public class BoardInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public BoardInsertController() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		// 값 뽑기 => 제목, 내용  => BoardDto로 가공
		//       => 파일       =>      ?       => FileDto로 가공
		// String boardTitle = request.getParameter("boardTitle");
		// System.out.println(boardTitle);
		// form 태그로 요청했을 때 multipart/form-date형식으로 요청한다면,
		// request.getParameter로는 요청 시 전달 값을 추출할 수 없음.
		
		HttpSession session = request.getSession();
		MemberDto member = (MemberDto)session.getAttribute("userInfo");
		if(member == null) {
			session.setAttribute("message","글쓰기는 로그인 이후 가능합니다.");
			response.sendRedirect("/kh/fail.do");
			return;
		}
		//요즘 가장 일반적인 방법은 => 테이블(ROLE)에 컬럼을 하나 만들기
		// => ADMIN / USER
		// 1) 요청이 multipart방식으로 잘 왔는가를 확인
		
		if(ServletFileUpload.isMultipartContent(request)) {
			//System.out.println("요청입니다");
			
			/*
			 * 1bit=한 칸
			 * 8bit = 1Byte = 옥텟
			 * 1024byte = 1kbyte
			 * 1024kbyte = 1mbyte
			 * 1024mbyte = 1gbyte
			 * 1024gbyte = 1tbyte
			 * 1024tbyte = 1pbyte
			 * 
			 * 10MegaByte
			 * 
			 */
			
			
			// 2) 파일 전송 시 필요한 세팅
			// 2_1) 파일 용량 제한                     // 1bit=
			int maxSize = 10 * 1024 * 1024;
			
			// 2_2) 서버의 파일 저장할 경로를 변수에 저장
			// PageContext
			// HttpServletRequest 요청받고 응답하는 JSP까지
			// HttpSession 모든서블릿과 모든JSP
			// ServletContext => getRealPath()
			
			ServletContext application = request.getServletContext();
			String savePath = application.getRealPath("/resources/board_upfiles"); // 가상의 경로를 가지고 실제경로를 얻어낸다.
			System.out.println(savePath);
			// 장점
			// 동적으로 실제 경로 확인 | 서버환경에 관계 없이 동작
			// 단점
			// WAR파일 배포 시 파일이 사라짐 // 개발에서만 이렇게 사용함
			// 실무에서는 bukect이라는 걸 사용해서 개발함
			
			// 3. 파일 업로드
			// a.jpg a2.jpg a3.jpg
			// ex) kakaoTalk_20260511_173607 <시간 123 <랜덤숫자 .jpg
			
			/*
			 * HttpServletRequest
			 * 
			 * => MultipartRequest 객체로 반환 
			 * 
			 * MultipartRequest multiRequest = 
			 * new MutiRequest(request, 저장경로, 용량제한, 인코딩방식, 파일명을 수저해주는 rename메소드를 가지고 있는 객체);
			 * 
			 * 생성자를 호출하면 자동으로 파일이 업로드
			 * 
			 * **일반적으로 파일의 경우 반드시 파일명을 변경해서 업로드하는 것이 관례
			 * 
			 * 똑같은 파일명을 방지하기 위해서 / 파일명에 한글, 특수문자, 공백문자 포함될 경우 서버에 따라 문제가 발생
			 * 
			 * 
			 * 
			 */
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyRenamePolicy());
			// 파일 업로드 작업
			
			
			// 꼭 해야하는 작업 => Board테이블에 Insert하기 위해서 값 뽑고 가공
			String boardTitle = multiRequest.getParameter("boardTitle");
			// System.out.println(boardTitle);
			String boardContent = multiRequest.getParameter("boardContent");
			Long userNo = member.getUserNo();
			
			BoardDto board = new BoardDto();
			board.setBoardTitle(boardTitle);
			board.setBoardContent(boardContent);
			board.setUserNo(userNo);
			// 첨부파일의 정보 => 선택적
			
			// 첨부파일이 있는지 없는지 확인하는 방법
			// System.out.println(multiRequest.getOriginalFileName("upfile"));
			
			// 트랜잭션에 처리해야하기 때문에 같이 넘겨야한다.
			AttachmentDto at = null;
			if(multiRequest.getOriginalFileName("upfile") != null) {
				at = new AttachmentDto();
				// orifinName
				at.setOriginName(multiRequest.getOriginalFileName("upfile"));
				// changeName
				at.setChangeName(multiRequest.getFilesystemName("upfile"));
				//filePath
				at.setFilePath("resources/board_upfiles");
				//boardType
				at.setBoardType("C");
				// fileLevel
				at.setFileLevel(2);
			}
			int result = new BoardService().insertBoard(board, at);
			
			//응답화면 지정
			if(result > 0) {
				//request.getRequestDispatcher("/WEB-INF/views/board/boards.jsp").forward(request, response);
				
				
				response.sendRedirect("/kh/boards.do?page=1");
				//다른 서블릿으로 재요청보낼 수 있구나 그 요청에 필요한 값을 전달만 잘 해준다면!
			} else {
				
				//영속성 작업 실패 => INSERT (BOARD/ATTACHMENT)
				//실패했을 경우 파일을 존재한다면 파일을 삭제해야함
				if(at != null) {
					new File(savePath + "/" + at.getChangeName()).delete();
				}
				session.setAttribute("message", "게시글 작성 실패");
				response.sendRedirect("/kh/fail.do");
			}
		}
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
