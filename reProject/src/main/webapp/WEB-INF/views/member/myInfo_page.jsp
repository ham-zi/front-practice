<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#info_btn{
		display : flex;
	}
	
	
	.modal-overlay {
        display: none; 
        position: fixed;
        top: 0; left: 0;
        width: 100%; height: 100%;
        background-color: rgba(0, 0, 0, 0.5);
        z-index: 1000;
    }

    /* 2. 모달창 중앙 배치 */
    .modal-content {
        position: absolute;
        top: 50%; left: 50%;
        transform: translate(-50%, -50%);
        background-color: white;
        padding: 20px;
        border: 1px solid #000; /* 사진처럼 테두리 주려면 추가 */
    }	
</style>

</head>

	
<body>
	<jsp:include page="/WEB-INF/views/include/header.jsp"/>
	
	<c:if test="${ not empty success }">
		<script>
		alert('${ success }');
		
		</script>
		<c:remove var="success" scope="session"/>
	</c:if>
	
	
	<h3> 로그인 정보 </h3>
		<form method="post" action="http://localhost:8088${ pageContext.request.contextPath }/update.me">
	
	유저 아이디 : <input type="text" value="${ userInfo.userId }" readonly style="background-color:lightgray"/> <br>
	유저 이름  : <input type="text" name="userName" value="${ userInfo.userName }"/> <br>
	가입 일자  : <input type="text" value="${ userInfo.enrollDate }" readonly style="background-color:lightgray"/> <br>
	최근 수정 날짜 : <input type="text" value="${ userInfo.modifyDate }" readonly style="background-color:lightgray"/> <br>
	

		<button type="submit">회원 정보 수정</button>

		</form>
		<button type="button" onclick="openModal()">비밀번호 변경</button>
	
	
	
	
	
	
	
	<div id="pwdModal" class="modal-overlay">
	    <div class="modal-content">
	        <div class="modal-header">
	            <h3>비밀번호 변경</h3>
	            <span class="close-btn" onclick="closeModal()">&times;</span>
	        </div>
	        
	        <form action="${pageContext.request.contextPath}/update.pwd" method="post" onsubmit="return validatePwd();">
	            <div class="modal-body">
	                <div class="input-group">
	                    <label>현재 비밀번호</label>
	                    <input type="password" id="currPwd" name="currPwd" required>
	                </div>
	                <div class="input-group">
	                    <label>새 비밀번호</label>
	                    <input type="password" id="newPwd" name="newPwd" required>
	                </div>
	                <div class="input-group">
	                    <label>새 비밀번호 확인</label>
	                    <input type="password" id="newPwdCheck" required>
	                </div>
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn-cancel" onclick="closeModal()">취소</button>
	                <button type="submit" class="btn-submit">변경하기</button>
	            </div>
	        </form>
	    </div>
	</div>
	
	<script>
    // 모달 열기
    function openModal() {
        document.getElementById('pwdModal').style.display = 'block';
    }

    // 모달 닫기
    function closeModal() {
        document.getElementById('pwdModal').style.display = 'none';
    }

    // 배경 클릭 시 닫기 기능 (선택)
    window.onclick = function(event) {
        let modal = document.getElementById('pwdModal');
        if (event.target == modal) {
            closeModal();
        }
    }

    // 비밀번호 검증
    function validatePwd() {
        const curr = document.getElementById('currPwd').value;
        const nPwd = document.getElementById('newPwd').value;
        const nCheck = document.getElementById('newPwdCheck').value;

        if (nPwd !== nCheck) {
            alert("새 비밀번호가 일치하지 않습니다.");
            return false;
        }
        if (curr === nPwd) {
            alert("현재 비밀번호와 새 비밀번호가 같습니다.");
            return false;
        }
        return true;
    }
</script>
	
	
</body>
</html>