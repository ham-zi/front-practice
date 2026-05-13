package com.re.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.re.member.model.dto.MemberDto;


@WebServlet("/myInfo_page.do")
public class MyInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public MyInfoController() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberDto userInfo = (MemberDto)session.getAttribute("userInfo");
		
		
		if(userInfo != null) {
			request.getRequestDispatcher("/WEB-INF/views/member/myInfo_page.jsp").forward(request, response);
		} else {
			session.setAttribute("message", "비정상적인 접근방식입니다.");
			response.sendRedirect(request.getContextPath()+"/fail.do");
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
