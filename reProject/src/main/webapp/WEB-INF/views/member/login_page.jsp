<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/include/header.jsp"/>
	
	<form method="post" action="http://localhost:8088${ pageContext.request.contextPath }/login.do">
		<ul>
			<li>
				아이디 : <input type="text" name="userId"/>
			</li>
			
			<li>
				비밀번호 : <input type="password" name="userPwd"/>
			</li>
			<li>
				<button type="submit"> 로그인 ! </button>
			</li>
			
		</ul>
	</form>
	
</body>
</html>