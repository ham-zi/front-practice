<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Fresh Market - 마이페이지</title>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300;400;500;700&display=swap" rel="stylesheet">
    <style>
        /* 1. 초기화 및 기본 레이아웃 */
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { font-family: 'Noto Sans KR', sans-serif; color: #333; line-height: 1.5; }
        .container { width: 1280px; margin: 0 auto; }
        button { cursor: pointer; font-family: inherit; }
        form { margin: 0; padding: 0; } /* 폼 마진 제거가 핵심! */

        /* 2. 헤더 */
        header { border-bottom: 1px solid #eee; padding: 20px 0; }
        .header-inner { display: flex; justify-content: space-between; align-items: center; }
        .logo h1 { color: #5f0080; font-size: 26px; }

        /* 3. 마이페이지 메인 구조 */
        .mypage-main { display: flex; padding: 50px 0; min-height: 700px; }
        
        /* 사이드바 */
        .sidebar { width: 200px; margin-right: 60px; }
        .sidebar h2 { font-size: 28px; margin-bottom: 30px; }
        .sidebar ul li { padding: 15px 10px; border-bottom: 1px solid #f4f4f4; color: #666; list-style: none; }
        .sidebar ul li.active { color: #5f0080; font-weight: 700; background: #fdf7ff; border-left: 3px solid #5f0080; }

        /* 컨텐츠 */
        .content-view { flex: 1; }
        .content-view h3 { font-size: 24px; padding-bottom: 20px; border-bottom: 2px solid #333; margin-bottom: 30px; }

        /* 정보 테이블 */
        .info-table { width: 100%; border-collapse: collapse; }
        .info-table th { width: 150px; text-align: left; padding: 20px 0; font-size: 14px; color: #555; }
        .info-table td { padding: 10px 0; }
        
        .input-main { width: 400px; height: 46px; border: 1px solid #ddd; border-radius: 4px; padding: 0 15px; outline: none; }
        .readonly-bg { background-color: #f4f4f4; color: #999; cursor: not-allowed; }

        /* 버튼 그룹 */
        .btn-group { margin-top: 50px; display: flex; justify-content: center; gap: 10px; }
        .btn { padding: 12px 30px; border-radius: 4px; font-size: 14px; font-weight: 500; border: 1px solid #5f0080; }
        .btn-purple { background: #5f0080; color: #fff; }
        .btn-white { background: #fff; color: #5f0080; }
        .btn-red { background: #fff; color: #e5433f; border-color: #e5433f; }

        /* 4. 모달 스타일 */
        .modal-overlay { display: none; position: fixed; top: 0; left: 0; width: 100%; height: 100%; background: rgba(0,0,0,0.5); z-index: 1000; }
        .modal-box { position: absolute; top: 50%; left: 50%; transform: translate(-50%, -50%); background: #fff; width: 480px; padding: 40px; border-radius: 12px; }
        .modal-header { font-size: 20px; font-weight: 700; color: #5f0080; text-align: center; margin-bottom: 30px; }
        
        .modal-label { display: block; font-size: 13px; font-weight: 700; margin-bottom: 8px; color: #666; }
        .modal-input { width: 100%; height: 46px; border: 1px solid #ddd; border-radius: 4px; padding: 0 15px; margin-bottom: 15px; outline: none; }
        .modal-input:focus { border-color: #5f0080; }
        
        .address-row { display: flex; gap: 8px; margin-bottom: 8px; }
        .btn-full { width: 100%; height: 54px; font-size: 16px; margin-top: 10px; border: none; }

        .close-x { position: absolute; right: 25px; top: 20px; font-size: 24px; color: #999; cursor: pointer; }
    </style>
</head>
<body>

    <header>
        <div class="container header-inner">
            <div class="logo"><h1>FRESH MARKET</h1></div>
        </div>
    </header>

    <div class="container">
        <div class="mypage-main">
            <aside class="sidebar">
                <h2>마이프레시</h2>
                <ul>
                    <li class="active">개인 정보 수정</li>
                    <li>주문 내역</li>
                    <li>알림 설정</li>
                </ul>
            </aside>

            <main class="content-view">
                <h3>개인 정보 수정</h3>
                <table class="info-table">
                    <tr>
                        <th>아이디</th>
                        <td><input type="text" class="input-main readonly-bg" value="${ userInfo.memberId }" readonly></td>
                    </tr>
                    <tr>
                        <th>이름</th>
                        <td><input type="text" class="input-main readonly-bg" value="${ userInfo.memberName }" readonly></td>
                    </tr>
                    <tr>
                        <th>이메일</th>
                        <td><input type="text" class="input-main readonly-bg" value="${ userInfo.email }" readonly></td>
                    </tr>
                    <tr>
                        <th>전화번호</th>
                        <td><input type="text" class="input-main readonly-bg" value="${ userInfo.phoneNumber }" readonly></td>
                    </tr>
                    <tr>
                        <th>주소</th>
                        <td><input type="text" class="input-main readonly-bg" value="${ userInfo.address }" readonly></td>
                    </tr>
                    <tr>
                        <th>최근 회원정보 수정날짜</th>
                        <td><input type="text" class="input-main readonly-bg" value="${ userInfo.modifyDate }" readonly></td>
                    </tr>
                </table>

                <div class="btn-group">
                    <button class="btn btn-purple" onclick="openModal('modalEdit')">회원정보 수정</button>
                    <button class="btn btn-white" onclick="openModal('modalPwd')">비밀번호 변경</button>
                    <button class="btn btn-red" onclick="openModal('modalQuit')">회원 탈퇴</button>
                </div>
            </main>
        </div>
    </div>

    <div id="modalEdit" class="modal-overlay">
        <div class="modal-box">
            <span class="close-x" onclick="closeModal('modalEdit')">&times;</span>
            <div class="modal-header">회원정보 수정</div>
            
            <form action="update.me" method="post">
                <label class="modal-label">이메일</label>
                <input type="email" class="modal-input" name="email" placeholder="변경할 이메일을 입력하세요">

                <label class="modal-label">휴대폰 번호</label>
                <input type="text" class="modal-input" name="phoneNumber" placeholder="숫자만 입력하세요">

                <label class="modal-label">주소 변경</label>

                <input type="text" class="modal-input" name="address" placeholder="주소를 입력하세요">

                <button type="submit" class="btn btn-purple btn-full">수정 완료</button>
            </form>
        </div>
    </div>

    <div id="modalPwd" class="modal-overlay">
        <div class="modal-box">
            <span class="close-x" onclick="closeModal('modalPwd')">&times;</span>
            <div class="modal-header">비밀번호 변경</div>
            
            <form action="update.pwd" method="post">
                <label class="modal-label">현재 비밀번호</label>
                <input type="password" class="modal-input" name="currentPwd" placeholder="현재 비밀번호">
                
                <label class="modal-label">새 비밀번호</label>
                <input type="password" class="modal-input" id="changePwd" name="changePwd" placeholder="새 비밀번호 (10자 이상)">
                
                <label class="modal-label">새 비밀번호 확인</label>
                <input type="password" class="modal-input" id="checkPwd" name="checkPwd" placeholder="비밀번호 확인">

                <button type="submit" class="btn btn-purple btn-full" onclick="return password();">변경하기</button>
            </form>
            <script>
            	function password(){
            		const changePwd = document.querySelector('#changePwd').value;
            		const checkPwd = document.querySelector('#checkPwd').value;
            		if(changePwd == checkPwd) {
            			return true;
            		} else{
            			alert('비밀번호가 일치하지 않습니다.');
            			return false;
            		}
            	}
            </script>
        </div>
    </div>

    <div id="modalQuit" class="modal-overlay">
        <div class="modal-box">
            <span class="close-x" onclick="closeModal('modalQuit')">&times;</span>
            <div class="modal-header" style="color:#e5433f;">회원 탈퇴</div>
            
            <form action="softDelete.me" method="post">
                <div style="text-align:center; margin-bottom:20px;">
                    <p>정말로 탈퇴하시겠습니까?</p>
                    <p style="font-size:12px; color:#999; margin-top:5px;">탈퇴 시 적립금 및 모든 혜택이 소멸됩니다.</p>
                </div>
                
                <label class="modal-label">본인 확인 비밀번호</label>
                <input type="password" class="modal-input" name="memberPwd" placeholder="비밀번호를 입력하세요">

                <button type="submit" class="btn btn-red btn-full" style="background:#e5433f; color:#fff;">탈퇴 확정</button>
            </form>
        </div>
    </div>

    <script>
        function openModal(id) {
            document.getElementById(id).style.display = 'block';
            document.body.style.overflow = 'hidden';
        }
        function closeModal(id) {
            document.getElementById(id).style.display = 'none';
            document.body.style.overflow = 'auto';
        }
        // 배경 클릭 시 닫기
        window.onclick = function(event) {
            if (event.target.classList.contains('modal-overlay')) {
                event.target.style.display = "none";
                document.body.style.overflow = 'auto';
            }
        }
    </script>
</body>
</html>