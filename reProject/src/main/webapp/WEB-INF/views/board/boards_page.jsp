<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

	<style>
	
		table {
			width : 100%;
			border-collapse: collapse;
		}
	
		td, tr, th {
			border : 1px solid black;
		}
		
	</style>
	
</head>
<body>
	<jsp:include page="/WEB-INF/views/include/header.jsp"/>
		<table>
			<thead>
				<tr>
					<th>
						게시글 번호
					</th>
					<th>
						작성자
					</th>
					<th>
						제목
					</th>
					<th>
						조회수
					</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="board" items="${ boards }">
					<tr>
						<td>${ board.boardNo }</td>
						<td>${ board.userName }</td>
						<td>${ board.boardTitle }</td>
						<td>${ board.viewCount }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<c:if test="${ pi.currentPage gt 1 }">
			<button onclick="location.href='http://localhost:8088/re/boards_page.do?page='${pi.currentPage + 1}">이전</button>
		</c:if>
		<c:forEach var="i" begin="${ pi.startPage }" end="${ pi.endPage }">
			<button onclick="location.href='http://localhost:8088/re/boards_page.do?page=${i}'">${ i }</button>
		</c:forEach>
		<c:if test="${ pi.currentPage le pi.maxPage }">
			<button onclick="location.href='http://localhost:8088/re/boards_page.do?page='${pi.currentPage - 1}">다음</button>
		</c:if>
		
</body>
</html>