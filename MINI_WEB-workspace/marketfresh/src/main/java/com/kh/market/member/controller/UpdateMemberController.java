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


@WebServlet("/update.me")
public class UpdateMemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public UpdateMemberController() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberDto loginSession = (MemberDto)session.getAttribute("userInfo");
		
		String email = request.getParameter("email");
		String phoneNumber = request.getParameter("phoneNumber");;
		String address = request.getParameter("address");
		
		MemberDto member = new MemberDto();
		member.setMemberNo(loginSession.getMemberNo());
		member.setEmail(email);
		member.setPhoneNumber(phoneNumber);
		member.setAddress(address);
		
		MemberDto updateMember = new MemberService().updateMember(member);
		if(updateMember != null) {
			session.setAttribute("userInfo", updateMember);
			response.sendRedirect("/market/myInfo_Page.do");
		} else {
			session.setAttribute("message", "ȸ������ ���� �����߽��ϴ�.");
			response.sendRedirect("/market/fail.do");
		}
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
