<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>include</h1>
	
	<p>
		다른 페이지를 포함할 때 씀
	
	</p>
<%-- 정적 컴파일 방식 (컴파일 시점에 jsp를 포함 시킴)
	<%@ include file="footer.jsp" %>
	<%@ include file="footer.jsp" %>
	-> 동일한 변수명이 생기는 문제가 발생함
 --%>
 
 
 	<h4> JSP 표준 액션 태그를 이용한 방식 (동적 include) </h4>
		
	<jsp:include page="footer.jsp"/>
	 <!-- XML기반 기술이기 때문에 여는태그가 있으면 꼭 닫는태그가 있다.
	 	  닫는 태그를 작성하지 않으면 500에러 발생
	  -->
		<jsp:include page="footer.jsp"/>
		<jsp:include page="footer.jsp"/>
	<!-- 실행시점에 붙히는 방식이라 변수명이 중복되어도 상관이없다. -->
	
	<hr>
	
	<jsp:include page="footer.jsp">
		<jsp:param name="test" value="hi"/>
	</jsp:include>
	
	
	<jsp:include page="footer.jsp">
		<jsp:param name="test" value="Bye"/>
	</jsp:include>
	
	
</body>
</html>