<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" href="resources/css/all.css" >
</head>
<body>

	<c:if test="${ not empty success }">
	<script>
		alert('${success}')
	</script>
		<c:remove var="success" scope="session"/>
	</c:if>


	<div id="wrap">
		<jsp:include page="/WEB-INF/views/include/header.jsp"/>
	
	
	
	
		<nav>
			<div id="nav">
			
			</div>
		</nav>
		
		
		<main>
			<div id="main">
			
			</div>
		</main>
		
		<footer>
			<div id="footer">
			
			</div>
	</footer>
	
	</div>
</body>
</html>