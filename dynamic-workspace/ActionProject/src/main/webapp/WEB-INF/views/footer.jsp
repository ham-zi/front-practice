<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<script>
		const date = new date();
		date.getFullYears();
	</script>
	
	<%
		String year = new java.text.SimpleDateFormat("yyyy").format(new java.util.Date());
	%>

	Copyright © 1998-<%= year %>KH Information Educational Institute All Right Reserved
</body>

	<hr>
	
	include.jsp에서 전달받은 test라는 name 속성값의 value => ${ param.test }<br>

</html>