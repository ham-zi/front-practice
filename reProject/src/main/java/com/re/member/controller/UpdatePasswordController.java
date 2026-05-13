package com.re.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.re.member.model.dto.MemberDto;
import com.re.member.model.dto.PasswordDto;
import com.re.member.model.service.MemberService;


@WebServlet("/update.pwd")
public class UpdatePasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public UpdatePasswordController() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberDto userInfo = (MemberDto)session.getAttribute("userInfo");
		if(userInfo == null) {
			session.setAttribute("message", "비정상적인 접근 방식입니다.");
			response.sendRedirect(request.getContextPath()+"/fail.do");
		}
		
		Long userNo = userInfo.getUserNo();
		String currPwd = request.getParameter("currPwd");
		String newPwd = request.getParameter("newPwd");
		
		PasswordDto pwd = new PasswordDto(userNo, currPwd, newPwd);
		int result = new MemberService().updatePwd(pwd);
		if(result > 0) {
			session.setAttribute("success", "비밀번호 변경에 성공했습니다.");
			response.sendRedirect(request.getContextPath()+"/myInfo_page.do");
		} else {
			session.setAttribute("message", "비밀변호 변경에 실패했습니다.");
			response.sendRedirect(request.getContextPath()+"/fail.do");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
