package com.kh.market.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.market.member.model.dto.MemberDto;
import com.kh.market.member.model.service.MemberService;


@WebServlet("/login.do")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public LoginController() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		HttpSession session = request.getSession();
		
		
		MemberDto member = new MemberDto();
		member.setMemberId(userId);
		member.setMemberPwd(userPwd);
		
		MemberDto loginMember = new MemberService().login(member);
		if(loginMember != null) {
			session.setAttribute("userInfo", loginMember);
			System.out.println("�α��� ���� ����: " + loginMember); // ���⼭ memberId�� null���� Ȯ��!
			response.sendRedirect("/market");
			
		} else {
			session.setAttribute("message", "�α��� �����߽��ϴ�.");
			response.sendRedirect("/market/fail.do");
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
