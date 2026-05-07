<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--  
	URI > URL 상위개념
	
	URI:Uniform Resource Identifier(식별) : 자원을 식별하는 문자열
										 자원의 위치, 네이밍 상관없이
										 고유하게 가리킬 수 있는 모든 식별자
	URL:Uniform Resource Locator(위치) : Location(위치) 어디로 가면 그걸
										찾을 수 있는가에 대한 답
	두 개는 
 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>매우 중요!</title>
</head>
<body>

	<h1> JSTL이란 ?</h1>


	<pre>
		Jakarta Server Page Standard Tag Library의 약어
		'JSP에서 사용할 수 있는 커스텀 액션 태그'
		공통적으로 화면단에서 사용하게 되는 코드들의 어떤 집합을 보다 쉽게 사용할 수 있게 끔
		태그로 만들어서 표준으로 제공하는 라이브러리(클래스 모음집)	
	</pre>
	
	
	<hr>
	
	
	<h3> * 라이브러리를 추가 </h3>
	
	<ol>
		<li> http://tomcat.apache.org로 접속</li>
		<li> Standard-1.2.5.jar파일 4개 다 다운</li>
		<li> WEB_INF/lib폴더에 카피 (copy files)</li>
	</ol>
	
	<h4> JSTL 선언!! </h4>
	
	<%
		request.setAttribute("abc",10);
	%>
	<c:set var="bbb" value="10" />
	
	
	<p>
		JSTL을 
	</p>
	
	<h5> 속성 추가하기 (Attribute추가)</h5>
	
	<pre>
		활용빈도 낮음
		
		&lt; c:set var="키값" value="리터럴값" scope="스코프(생걁가능)"/>
		- scopte에 새로운 attribute를 추가하는 태그
		-scope 속성 생략 시 pageScope에 담김
	</pre>
	
	<c:set var="num1" value="10"/>
	<c:set var="num2" value="20" scope="request"/>
	<%-- request.setAttribute("num1", "10") --%>
	<c:set var="result" value="${ num1 + num2 }" scope="session" />
	
	
	set태그로 선언한 Attribute는 EL구문을 이용해서 출력<br>
	num1의 값 : ${ num1 } <br>
	num2의 값 : ${ num2 } <br>
	result의 값 : ${ result } <br>
	
	<c:set var="result" scope="request">9999</c:set>
	<!-- value속성을 명시하지않고 컨텐트 영역에 대입한 값을 기술할 수 있음. -->
	
	<hr>
	
	
	<pre>
		활용빈도 : 중간
		
		*속성 삭제(&lt;c:remove var="제거하고자하는 속성" scope="스코프영역(생략가능)"/> )
		
		-해당 Attribute를 scope영역에서 제거하는 태그
		-scope속성을 작성하지 않으면 모든 scope에서 해당 Attribute를 모두 제거 한다.
	</pre>
	
	삭제 전 result : ${ result } <br>
	
	<c:remove var="result" scope="request"/>
	삭제 후 result : ${ result } <br>
	<!-- 다른 scope에있는 같은 변수명이 있을 수 있다는걸 생각해! -->
	
	<hr>
	
	<pre>
		활용빈도 : 낮은데 꼭 필요한 곳이 생긴다.
		
		*출력(&lt;c:out value="출력할 값" default="기본값" escapeXml="t/f"/>)
		-속성을 출력하려고 할 때 사용하는 태그
		-디폴트값이 괜찮네
	</pre>
	num1 : <c:out value="${ num1 }" />
	
	<br>
	
	requestScope result : ${ requestScope.result } <br>
	out태그 사용 : <c:out value="${ requestScope.result }" default="값이 존재하지 않음"/>
	
	<c:set var="strong" value="<strong>강한정보</strong>" />
	
	${ strong }
	<:out value="${ strong }"/>
	<!-- escapeXml 설정으로 t/f 정할수있다. -->
	
	<h3> 조건문 </h3>
	
	<pre>
		활용빈도 : 높음
	
		&lt;c:if test="조건식">
			조건식이 true일 경우 출력할 내용
		&lt;/c:if>
		
		- 조건식을 작성할 때는 반드시 EL구문으로 작성
	</pre>
	
	
	<c:if test="${ num1 lt num2 }">
		<strong>num1이 num2보다 작습니다.</strong>
	</c:if>
	<!--  mybatis도 xml기술이라 xml을 쓰는 JSP와 구성이 비슷하다. -->
	<%--
	<% if ((int)pageContext.getAttribute("num1") > (int)request.getAttribute("num2")) {%>
	<%} %>
	--%>
	
	<h3> choose, when, otherwise</h3>
	
	<pre>
		활용빈도 : 매우 높음
		
		&lt;c:choose>
			&lt;c:when test="조건1">
				출력할 내용1
			&lt;/c:when>
			&lt;c:when test="조건2">
				출력할 내용2
			&lt;/c:when>
			&lt;c:otherwise>
				출력할 내용3
			&lt;/c:otherwise>
		&lt;/c:choose>
	
	</pre>
	
	<c:set var="point" value="400"/>
	
	회원등급 출력 :
	<c:choose>
		<c:when test="${ point le 100 }">
			일반회원
		</c:when>
		<c:when test="${ point le 300 }">
			우수회원
		</c:when>
		<c:otherwise>
			최우수회원
		</c:otherwise>
	</c:choose>
	<!-- 이런 주석, choose구문 안에 when/otherwise말고 다른 무언가가 들어가서는 안된다. 500에러가 발생한다.  -->
	
	
	<h3> 반복문 </h3>
	<% for(int i=0; i<10; i++) {%>
		<%= i %>
	<% } %>
	
	<pre>
		활용빈도 : 매우 높음
		
		for loop문
		&lt;c:forEach var="속성명" begin="초기값" end="끝깞" step="증가치">
			반복시킬 내용
		&lt;/c:forEach>
		
		- step은 생략 시 기본 값 == 1
		
		향상된 for문
		&lt;c:forEach var="속성명" items="순차적으로 요소에 접근할 배열/컬렉션" varStatus="상태값">
			반복시킬 내용
		&lt;/c:forEach>
		
		#공통점
		var로 선언된 제어변수의 경우 반드시 EL구문으로 접근해야함
	</pre>
	
	<c:forEach var="i" begin="0" end="9">
		${ i }
	</c:forEach>
	
	<br>
	
	<c:forEach var="i" begin="1" end="6">
		<h${i}>이것도 된다고 ㄷㄷ?</h${i}>
	</c:forEach>
	
	<c:set var="color">
		red, orangered, orange, yellow, yellowgreen
	</c:set>
	
	color : ${ colors }
	<br>

	<ul>
		<c:forEach var="c" items="${ colors }">
			<li style="color:${ c }">${ c }</li>
		</c:forEach>
	</ul>
	
	
	
	<hr>
	
	<table border="1">
		<thead>
			<tr>
				<th>순번</th>
				<th>이름</th>
				<th>나이</th>
				<th>주소</th>	
			</tr>
		</thead>
		<tbody>
		<c:choose>
			<c:when test="${ empty persons }">
				<tr>
					<th colspan="4">조회결과가 존재하지 않습니다.</th>
				</tr>			
			</c:when>
			<c:otherwise>
				<c:forEach var="p" items="${ persons }" varStatus="s">
					<tr>
						<td>${ s.count }</td> <!--  index => 0부터, count => 1부터  -->
						<td>${ p.name }</td>
						<td>${ p.age }</td>
						<td>${ p.address }</td>
					</tr>
				</c:forEach>
			</c:otherwise>
			</c:choose>
		</tbody>
		<tfoot>
			<tr>
				<th colspan="3">총합</th>
				<th>${ persons.size() }명</th>
			</tr>
		</tfoot>
	</table>
	
	
	<h5> forTokens </h5>
	
	<c:set var="device" value="컴퓨터,핸드폰,TV/에어컨.냉장고-세탁기" />
	
	<ul>
		<c:forTokens var="d" items="${ device }" delims=",/.-">
			<li>${ d }</li>
		</c:forTokens>
	
	</ul>
	

	<br><br><br><br><br><br><br><br>
</body>
</html>