package com.kh.web.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.web.member.model.dto.MemberDto;


@WebServlet("/mypage.do")
public class MypageController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MypageController() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//그렇다면 이미 세션에 있으니까 의미없지않나?
		//로그인을 했냐 안했냐를 조건문을 걸어서
		//화면을 따로 보여줘야하기 때문에 필요하다
		
		//하지만 가장 좋은 방법은 DB에서 조회된 내용을 담시 담아주는 방법
		//이유 : 항상 최신 데이터로 응답해주어야하기 때문이다.
		HttpSession session = request.getSession();		
		MemberDto member =(MemberDto)session.getAttribute("userInfo");
		
		//이미 Session에 올라와있어서 필요없는 구문이었다.
		//request.setAttribute("userInfo", member);
		
		
		//즉, 권한 검증이 필요하다.
		if(member != null) {
			request.getRequestDispatcher("/WEB-INF/views/member/my_page.jsp").forward(request, response);
		} else {
			request.setAttribute("message","정상적이지 않은 접근입니다.");
			request.getRequestDispatcher("/WEB-INF/views/common/fail_page.jsp").forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
