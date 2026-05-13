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


@WebServlet("/update.me")
public class updateMemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public updateMemberController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberDto userInfo = (MemberDto)session.getAttribute("userInfo");
		if(userInfo == null) {
			session.setAttribute("message", "잘못된 접근 방식입니다.");
			response.sendRedirect(request.getContextPath()+"/fail.do");
		}
		
		Long userNo = userInfo.getUserNo();
		String userName = request.getParameter("userName");
		
		MemberDto member = new MemberDto();
		member.setUserNo(userNo);
		member.setUserName(userName);
		
		MemberDto newInfo = new MemberService().updateMember(member);
		if( newInfo != null ) {
			session.setAttribute("userInfo", newInfo);
			session.setAttribute("success", "회원정보 수정에 성공했습니다.");
			response.sendRedirect(request.getContextPath()+"/myInfo_page.do");
		} else {
			session.setAttribute("message", "회원정보 수정에 실패했습니다.");
			response.sendRedirect(request.getContextPath()+"/fail.do");
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
