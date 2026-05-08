package com.kh.web.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/logout.do")
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LogoutController() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인 => 인증(어센티케이션)에 성공한 사용자의 정보를 서버측 저장소인 세션에 저장
		//로그아웃 => 세션에 저장되어 있는 인증된 사용자의 정보를 제거한다.
		
		HttpSession session = request.getSession();
		// 특정 어트리뷰트 삭제하는 방법.
		// session.removeAttribute("userInfo");
		
		// 세션을 만료시킨다, 무효화한다.
		// 세션의 모든 attribute를 모두 제거한다.
		session.invalidate();
		response.sendRedirect("/kh");
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
