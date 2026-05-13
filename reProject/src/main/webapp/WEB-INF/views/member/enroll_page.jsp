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
	<jsp:include page="/WEB-INF/views/include/header.jsp"/>

	<c:if test="${ not empty success }">
		<script>
			alert('${ success }');
		</script>
		<c:remove var="success" scope="session" />
	</c:if>


	<h3> 회원 가입 </h3>
	
	<form action="http://localhost:8088${ pageContext.request.contextPath }/enroll.me" method="post">
		ID : <input type="text" id="userId" name="userId"/> <button type="button" id="idCheck-btn" onclick="idCheck();">중복체크</button> <br>
		PW : <input type="password" id="userPwd" name="userPwd"/> <br>
		PW(check) : <input type="password" id="checkPwd" /> <br>
		이름 : <input type="text" id="userName" name="userName"/> <br>
		<button type="submit" onclick="return check();">가입!</button>
		
	<script>
		function idCheck() {
			const userId = document.querySelector('#userId').value;
			const btn = document.querySelector('#idCheck-btn');
			if(!userId) {
				alert("아이디를 입력해주세요.");
				return;
			}
			location.href= `http://localhost:8088${pageContext.request.contextPath}/check.id?userId=`+userId;
			
		}
	
	
		function check() {
			const id  = checkId();
			const pwd = checkPwd();
			const name= checkName();
			
			if(pwd===1 && id===1 && name===1) {
				return true
			} else{
				return false
			}
		}
		
		function checkPwd() {
			const userPwd = document.querySelector('#userPwd').value;
			const checkPwd = document.querySelector('#checkPwd').value;
			const pwdReg = /^[a-zA-Z0-9가-힣]{4,15}$/;
			if(userPwd != checkPwd) {
				alert("비밀번호가 일치하지 않습니다.");
				return 0;
			}
			if(!pwdReg.test(userPwd)){				
				alert("비밀번호는 4~15자의 영문, 한글, 숫자만 가능합니다.")
				return 0;
			}
			return 1;
		}
		
		function checkId() {
			const userId = document.querySelector('#userId').value;
			const idReg = /^[a-zA-Z0-9가-힣]{4,15}$/;
			if(idReg.test(userId)) {
				return 1;
			} else {	
				alert("아이디는 4~15자의 영어한글숫자만 가능합니다.")
				return 0;
			}
		}
			
		function checkName() {
			const userName = document.querySelector('#userName').value;
			const nameReg = /^[a-zA-Z0-9가-힣]{2,}$/;
			if(nameReg.test(userName)) {
				return 1;
			} else{
				alert("이름은 2글자 이상의 영문,한글,숫자만 가능합니다.")
				return 0;
			}
		}
	</script>
	</form>
	
</body>
</html>