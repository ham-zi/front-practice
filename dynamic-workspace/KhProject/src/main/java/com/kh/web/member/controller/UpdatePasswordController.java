package com.kh.web.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.web.member.model.dto.MemberDto;
import com.kh.web.member.model.dto.UpdatePwdDto;
import com.kh.web.member.model.service.MemberService;


@WebServlet("/update-pwd.me")
public class UpdatePasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public UpdatePasswordController() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1) 인코딩은 이제 필터가 해줄것이다.
		
		// 2) 값을 추출 및 가공
		String userPwd = request.getParameter("userPwd");
		String updatePwd = request.getParameter("updatePwd");
		
		
		/*
		 * 
		 * UPDATE
		 * 	      WEB_MEMBER
		 *    SET
		 *        USER_PWD = #{}
		 *  WHERE
		 *        USER_NO = #{}
		 */
		HttpSession session = request.getSession();
		MemberDto member = (MemberDto)session.getAttribute("userInfo");
		//코드를 이렇게 쓰면 생기는 위험성 
		//null일 경우를 신경써줘야 한다.
		if(member == null) {
			session.setAttribute("message", "로그인을 먼저 진행해주세요.");
			response.sendRedirect("/kh/fail.do");
			return;
		}
		Long userNo = member.getUserNo();
		
		UpdatePwdDto upd = new UpdatePwdDto(userPwd, updatePwd, userNo);
		// 3)서비스단 호출
		int result = new MemberService().updatePassword(upd);
		
		if(result > 0) {
			//성공했을 때 마이페이지로 돌아간다
			response.sendRedirect("/kh/mypage.do");
		} else {
			session.setAttribute("message", "비밀번호 변경에 실패했습니다.");
			response.sendRedirect("/kh/fail.do");
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
