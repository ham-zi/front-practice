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


@WebServlet("/softDelete.me")
public class SoftDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public SoftDeleteController() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberDto userInfo = (MemberDto)session.getAttribute("userInfo");
		
		
		String memberPwd = request.getParameter("memberPwd");
		MemberDto member = new MemberDto();
		member.setMemberNo(userInfo.getMemberNo());
		member.setMemberPwd(memberPwd);
		
		int result = new MemberService().softDeleteMember(member);
		
		if(result > 0) {
			session.setAttribute("userInfo", null);
			response.sendRedirect("/market");
		} else {
			session.setAttribute("message", "ȸ�������� �����߽��ϴ�.");
			response.sendRedirect("/market/fail.do");
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
