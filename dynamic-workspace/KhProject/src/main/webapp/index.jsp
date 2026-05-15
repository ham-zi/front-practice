<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>KH 501</title>
</head>
<body>
	

	<!-- 
		WEB 환경에서의 CRUD
		
		* 회원서비스
		로그인 / 로그아웃 * / 회원가입 / 내정보조회 / 비밀번호변경 / 회원탈퇴
		
		* 일반게시판서비스
		게시글목록조회(페이징처리) / 상세조회 / 게시글작성(첨부파일 1개 업로드) / 게시글 수정
		게시글삭제 / 댓글서비스 / 게시글 검색
		
		
		수정btn => 수정양식을 보여줌 (제목내용첨부파일) / 수정하기btn=>수정요청 / 사용자검증 -> 추출및가공 -> 트랜잭션(게시글/첨부파일) -> 첨부파일 경우의수 ~  
		이런 기능을 생각하기 힘들다 -> 홈페이지들을 사용해보아라.
		
		
		
		* 사진게시판서비스
		사진게시글목록조회(썸네일) /  상세조회 / 게시글작성(다중파일업로드)
	 -->
	 
	 <!-- 
	 	Java, Oracle, JDBC, MYBatis
	 	
	 	HTML, CSS, JS
	 	
	 	+ Network(Web) -> 클라이언트, 서버 , DB
	 	
	 	
	 	실습 겸 숙제 ==> 
	 	나만의 회원 서비스 만들기
	 	0511 => 공지사항 목록 조회 구현 = > 화면, 테이블, 페이징처리
	 	0512 => 공지사항 작성(첨부파일X, 사용자검증), 상세조회, 사겢구현
	 
	  -->
	  
	 <a href="insert.notice">노티스 인서트</a>
	 <hr>
	 <a href="select.notice">노티스 셀렉트</a>
	 
	<jsp:include page="WEB-INF/views/include/header.jsp"/>
	<jsp:include page="WEB-INF/views/include/footer.jsp"/>
	<jsp:include page="WEB-INF/views/include/main.jsp"/>
	 
</body>
</html>