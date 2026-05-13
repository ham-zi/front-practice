package com.re.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.re.member.model.dto.MemberDto;
import com.re.member.model.service.MemberService;


@WebServlet("/login.do")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public LoginController() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		
		MemberDto member = new MemberDto();
		member.setUserId(userId);
		member.setUserPwd(userPwd);
		
		MemberDto userInfo = new MemberService().login(member);
		if(userInfo != null) {
			session.setAttribute("userInfo", userInfo);
			session.setAttribute("success", "로그인에 성공했습니다.");
			response.sendRedirect(request.getContextPath());
		} else {
			session.setAttribute("message", "로그인에 실패했습니다.");
			response.sendRedirect(request.getContextPath()+"/fail.do");
		}
		
	}
	


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
