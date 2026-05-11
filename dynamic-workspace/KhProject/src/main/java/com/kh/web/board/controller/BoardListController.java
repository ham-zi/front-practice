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
import com.kh.web.common.model.dto.PageInfo;


@WebServlet("/boards.do")
public class BoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public BoardListController() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 페이징 처리!!!
		// 필요한 변수
		// 
		int listCount; // 현재 게시판의 총 게시글 개수  << 페이징 개수를 정할 수 있음
		// => WEB_BOARD테이블에서 COUNT(*) (STATUS='N') 조회
		int currentPage; // 현재 사용자가 요청한 페이지 //
		// request.getParameter("page")로 뽑아서 씀
		int pageLimit; // 페이지 하단에 버튼을 몇개 보여줄 것인지 정함 => 5개
		int boardLimit; // 한 페이지에 보여질 게시글의 최대 개수 => 3개
		int maxPage; // 가장 마지막 페이지(총 페이지의 개수)
		int startPage; // 페이지 하단에 보여질 페이징바의 시작 값;
		int endPage; // 페이지 하단에 보여질 페이징바의 끝 값;
		
		listCount = new BoardService().selectBoardCount();
		currentPage = Integer.parseInt(request.getParameter("page"));
		pageLimit = 3;
		boardLimit = 3;
		// * maxpage : 가장 마지막페이지가 몇 번 페이지 인가?
		/*
		 *  listCount, boardLimit에 영향을 받음
		 *  공식을 생각해보자
		 *  
		 *  총 개수     한 페이지  나눗셈 결과   마지막 페이지
		 *  100         10       =10          10
		 *  107         10       =10.7        11
		 *  113         10       =11.3        12
		 *  => 나눗셈의 결과를 올림처리하면 maxPage가 나오는구나
		 *  
		 *  1. listCount를 double로 변환
		 *  2. listCount / boardLimit
		 *  3. Math.ceil()
		 *  4. (int)
		 *  
		 */
		maxPage = (int)Math.ceil((double)listCount/boardLimit);
		
		// *startPage : 페이지 하단에 보여질 페이징 버튼 중 시작 값
		/*
		 * pageLimit, currentPage
		 * 
		 * - 공식 구하기
		 *   단, pageLimit이 10이라고 가정
		 *   
		 *   startPage : 1, 11, 21, 31, ...
		 *   
		 *   단, pageLimit이 5라고 가정
		 *   
		 *   startPage : 1, 6, 11, 16
		 *   
		 *   startPage == n*pageLimit+1
		 *   
		 *   
		 *   currntPage      startPage
		 *       1               1
		 *       5               1
		 *       10              1
		 *       11              11
		 *       17              11
		 *       20              11
		 *       21              21
		 *       30              21
		 *       
		 *       1~10 / 10 => 0~1
		 *      11~20 / 10 => 1~2
		 *      21~30 / 10 => 2~3
		 *      
		 *      (1~10) - 1 / 10 => 0
		 *     (11~20) - 1 / 10 => 1
		 *     (21~30) - 1 / 10 => 2
		 *     
		 *     n = (currntPage-1) / pageLimit
		 *     startPage = (currntPage-1)/pageLimit*pageLimit +1
		 */
		startPage = (currentPage -1) / pageLimit * pageLimit + 1;
		
		
		
		// * endPage : 페이지 하단에 보여질 페이징 버튼의 끝 수
		/*
		 *  startPage, pageLimit에 영향을 받음
		 *  (maxPage도 영향을 미침)
		 *  
		 *  - 공식을 생각해보자
		 *  단, pageLimit이 10이라는 가정
		 *  
		 *  startPage : 1 => endPage : 10
		 *  startPage : 21 => endPage : 30
		 *  endPage = startPage + pageLimit-1;
		 *  
		 */
		
		endPage= startPage + pageLimit -1;
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		//RowBounds 행들을 건너뛰며 >>>  매커니즘: 셀렉 전체조회하여 일부 갖고감-> 성능이슈로 안씀
		// 인라인뷰 , offset 방식
		/*
		 * 인라인뷰 : 카밴분석?? 
		 * 
		 * 실습겸 숙제 => 나만의 회원서비스 만들기
		 * 05/11 오늘의 숙제 ==> 공지사항 목록 조회 구현 =>
		 *  화면, 테이블, 페이징처리 (수요일까지)
		 * 
		 * AI활용하기
		 *  => 
		 *  1. 에러메세지 검색용
		 *  => 이런 문제가 발생했는데, 나랑 동일한 문제 발생한 URL을 검색해서 링크를 줘
		 *  2. 모르는 문법/메소드 의미 물어보기
		 *  => foreach태그 이거 뭐야? 어떻게 써?
		 *  
		 *  AI활용 X
		 *  1. 공지사항 목록 조회 구현해줘 X
		 *  2. 본인이 이해 못하는 코드를 그대로 붙여넣기 X
		 *  
		 *  
		 *  
		 *  
		 *  
		 *   (게시글 기준)
		 *  
		 *  클라이언트의 웹브라우저
		 *  URL로 요청
		 *  
		 *  
		 *  <a> : get방식 : 요청 시 url에 데이터가 노출됨
		 *  
		 *  
		 *  
		 *   동적자원요청 -> 톰켓 -> 컨텍스트루트
		 *                   -> 서블릿매핑값 
		 *                   
		 *                   
		 *   1. boardTable이 몇 행인가?
		 *   서비스에 요청                
		 *   service -> Dao -> DB
		 *             DB에요청
		 *   서블릿결과값반환<-   <- DB에서 응답  
		 *   
		 *   2. currentPage 요청페이지는
		 *   요청url에 get파라미터를 참조해서 뽑아냄
		 *   
		 *   3. 필요한 정수값들을 정리
		 *   정수값들을 DTO에 담음
		 *   PageInfo ax0033
		 *   
		 *   4. 이 DTO를 가지고 DB에 요청
		 *   
		 *   5. List<Board>bx3333
		 *      PageInfo   ax0033
		 *   
		 *   6. 이 정보들을 문자열로 만들기 위해서
		 *      JSP를 이용하여 문자열로 만든다.
		 *      
		 *      JSP에서 값을 정리하기 위해서는 값을 전달해야하는데
		 *      그것은 request 객체를 이용한다.
		 *      request.setAttribute(bx3333)
		 *      request.setAttribute(ax0033)
		 *      
		 *      + forward(request.response)
		 *      
		 *       랜더링  =>
		 *       
		 *       
		 */
		
		int offset = (currentPage - 1) * boardLimit;
		
		PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit, startPage, endPage, maxPage, offset);
		
		List<BoardDto> boards = new BoardService().selectBoardList(pi);
		//System.out.println(boards);
		
		request.setAttribute("pi", pi);
		request.setAttribute("boards", boards);
		
		request.getRequestDispatcher("/WEB-INF/views/board/boards.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
