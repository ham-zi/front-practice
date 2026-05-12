package com.kh.web.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.web.member.model.dto.MemberDto;
import com.kh.web.member.model.service.MemberService;


@WebServlet("/login.do")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public LoginController() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//GET ? POST?
		// POST => 1) 인코딩설정
		request.setCharacterEncoding("UTF-8");
		// 2) 값을 추출
		// 요청 시 전달값이 있나?? => POST는 무조건 있음
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		// 3) 가공 작업 DTO
		MemberDto member = new MemberDto();
		member.setUserId(userId);
		member.setUserPwd(userPwd);
		// 4) 요청처리, 서비스에 전달
		// => 전통적인 session방식 로그인 => 규모가 작은 프로젝트에서 구현됨
		// ex) KH아카데미사이트 정도(단일플렛폼이고 웹밖에 없다)
		MemberDto loginMember = new MemberService().login(member);
		// 성공했을 때 경우 : 조회 성공한 컬럼값을 필드에 담은 멤버 객체의 주소 값
		// 실패했을 때 경우 : null 값
		// 5) 결과값 반환 / 응답화면 지정
		
		/*
		 * session : 모든 JSP와 서블릿에서 값을 꺼내서 쓸 수 있는 저장소
		 * 			 단, session에 값이 지워지기 전까지
		 * 			 세션종료시점 : 브라우저 종료, 서버종료, 코드로 지움
		 * 
		 * request : 해당 request를 포워딩한 응답 JSP에서 까지만 쓸 수 있음
		 * 			 요청부터 응답까지만 사용이 가능
		 * 
		 */
		if(loginMember != null) {
			// request.setAttribute("userInfo", loginMember);
			// response.sendRedirect("/kh"); // 요청을 다시하는 것, 새롭게 재요청방식 // 이전 데이터와는 별개의 요청
			
			HttpSession session = request.getSession();
			session.setAttribute("userInfo", loginMember);
			//session.invalidate(); // 만료시키다, 무효화한다.
			session.setAttribute("alertMsg", "로그인ㅊㅋㅊㅋ");
			response.sendRedirect("/kh");
			// 단점 사용자가 많을수록 부하가 커진다.
			// 장점 서버측에 데이터를 갖고 있기 때문에 보안상 안전하다
			
			
			// 서버입장에서 똑같은 userInfo인데 어떻게 구분하는가?
			// 쿠키라는 개념.
			// 톰켓이 임의의 JSESSIONID라는 토큰값을 정해준다
			// 이것으로 사용자를 식별
			// 요청보낼 때 기준으로 쿠키를 줌.

		} else {
			request.setAttribute("message", "로그인에 실패했습니다.");
			request.getRequestDispatcher("/WEB-INF/views/common/fail_page.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
