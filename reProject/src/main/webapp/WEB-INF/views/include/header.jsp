<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
	#wrap {
		display : flex;
		flex-direction: column;
		width : 1280px;
		height : 800px;
	}
	
	header, nav, main, footer{
		border : 1px solid gray;
	}
	
	header{
		flex : 2;
		height : 100%;
	}
	
	nav {
		flex : 0.5;
		height : 100%;		
	}
	
	main {
		flex : 5;
		height : 100%;		
	}
	
	footer {
		flex : 1;
		height : 100%;		
	}
	
	a {
		color : black;
		font-weight : 700;
		font-style : none;
	}
	
	
	
	
	#header{
		display : flex;
	}
	
	#home {
		flex : 1;
	}
	
	#HelloWorld {
		flex : 8;
	}

	#HelloWorld > h1{
		text-align : center;
	}
	
	#utilHeader {
		flex : 1;
	}
</style>

</head>
<body>
	<header>
		<div id="header">
			<div id="home">
				<a href="http://localhost:8088${pageContext.request.contextPath }">homePage</a>
			</div>
			<div id="HelloWorld">
				<h1>HellowWorld!</h1>
			</div>
			<ul id="utilHeader">
			
			 	<c:choose>
					<c:when test="${ empty userInfo }">
						<li>
							<a href="http://localhost:8088${ pageContext.request.contextPath }/login_page.do">로그인</a>
						</li>
						<li>
							<a href="http://localhost:8088${ pageContext.request.contextPath }/enroll_page.do">회원가입</a>
						</li>
					</c:when>
					<c:otherwise>
						<li>
							<a href="http://localhost:8088${ pageContext.request.contextPath }/myInfo_page.do">내정보</a>					
						</li>
						<li>
							<a href="http://localhost:8088${ pageContext.request.contextPath }/logout.do">로그아웃</a>
						</li>
					</c:otherwise>
			 	</c:choose>
				<li>
					<a href="http://localhost:8088${ pageContext.request.contextPath }/boards_page.do?page=1">자유게시판</a>
				</li>
				<li>
					<a href="#">기타</a>
				</li>
				
			</ul>
		</div>
	</header>

</body>
</html>