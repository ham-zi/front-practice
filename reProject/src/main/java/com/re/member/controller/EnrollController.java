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


@WebServlet("/enroll.me")
public class EnrollController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public EnrollController() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String userId =  request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		String userName = request.getParameter("userName");
		
		MemberDto member = new MemberDto();
		member.setUserId(userId);
		member.setUserPwd(userPwd);
		member.setUserName(userName);
		
		int result = new MemberService().createMember(member);
		
		if(result > 0) {
			session.setAttribute("success", "계성 생성에 성공했습니다");
			response.sendRedirect(request.getContextPath());
		} else {
			session.setAttribute("message", "계정 생성에 실패했습니다.");
			response.sendRedirect(request.getContextPath()+"/fail.do");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
