package com.kh.market.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.market.member.model.dto.MemberDto;
import com.kh.market.member.model.service.MemberService;



@WebServlet("/create.me")
public class CreateMemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public CreateMemberController() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		String userName = request.getParameter("userName");
		String email = request.getParameter("email");
		String phoneNumber = request.getParameter("phoneNumber");
		String address = request.getParameter("address");
		
		MemberDto member = new MemberDto(userId, userPwd, userName, email, phoneNumber, address);
		
		int result = new MemberService().createMember(member);
		
		if(result > 0) {
			response.sendRedirect("/market");
		} else {
			request.setAttribute("message", "���� ������ �����߽��ϴ�.");
			response.sendRedirect("/market/fail_page.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
