<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Fresh Market - 회원가입</title>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300;400;500;700&display=swap" rel="stylesheet">
    <style>
        /* 초기화 및 공통 스타일 */
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { font-family: 'Noto Sans KR', sans-serif; background-color: #fff; color: #333; }
        .container { width: 1280px; margin: 0 auto; }

        /* 헤더 (메인과 동일하게 유지) */
        header { padding: 20px 0; border-bottom: 1px solid #f4f4f4; }
        .header-content { display: flex; justify-content: space-between; align-items: center; }
        .logo h1 { color: #5f0080; font-size: 26px; cursor: pointer; }
        .search-bar input { width: 400px; padding: 10px; border: 1px solid #5f0080; border-radius: 4px; outline: none; }
        .header-util button { background: none; border: none; margin-left: 15px; color: #666; font-size: 14px; cursor: pointer; }

        /* 회원가입 폼 섹션 */
        .join-section { width: 640px; margin: 60px auto; padding-bottom: 100px; }
        .join-title { text-align: center; font-size: 28px; font-weight: 700; margin-bottom: 50px; }
        
        .join-table { width: 100%; border-top: 2px solid #333; border-collapse: collapse; }
        .join-table th { width: 150px; padding: 20px 0; text-align: left; font-size: 14px; vertical-align: middle; }
        .join-table td { padding: 10px 0; }
        
        /* 입력창 스타일 */
        .input-box { width: 100%; padding: 12px; border: 1px solid #ccc; border-radius: 4px; outline: none; font-size: 14px; }
        .input-box:focus { border: 1px solid #5f0080; }
        
        /* 중복확인 버튼 등 */
        .sub-btn { 
            padding: 12px 20px; 
            border: 1px solid #5f0080; 
            background: #fff; 
            color: #5f0080; 
            border-radius: 4px; 
            font-weight: 500; 
            margin-left: 10px;
            cursor: pointer;
            min-width: 120px;
        }

        .row-flex { display: flex; align-items: center; }

        /* 가입하기 큰 버튼 */
        .submit-area { margin-top: 40px; text-align: center; border-top: 1px solid #eee; padding-top: 40px; }
        .btn-join { 
            width: 240px; 
            height: 56px; 
            background-color: #5f0080; 
            color: #fff; 
            border: none; 
            border-radius: 4px; 
            font-size: 16px; 
            font-weight: 700; 
            cursor: pointer; 
        }

        /* 푸터 */
        footer { background: #f7f7f7; padding: 40px 0; border-top: 1px solid #eee; }
        .footer-content { text-align: center; color: #999; font-size: 13px; }
    </style>
</head>
<body>

    <header>
        <div class="container header-content">
            <div class="logo"><h1>FRESH MARKET</h1></div>
            <div class="search-bar"><input type="text" placeholder="검색어를 입력해주세요"></div>
            <div class="header-util">
                <button>회원가입</button>
                <button>로그인</button>
                <button>고객센터</button>
            </div>
        </div>
    </header>

    <main class="container">
        <section class="join-section">
            <h2 class="join-title">회원가입</h2>
            
            <form action="create.me" method="post">
                <table class="join-table">
                    <tr>
                        <th>아이디</th>
                        <td>
                            <div class="row-flex">
                                <input type="text" class="input-box" name="userId" placeholder="아이디를 입력해주세요">
                                <button type="button" class="sub-btn">중복확인</button>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <th>비밀번호</th>
                        <td><input type="password" id="userPwd" class="input-box" name="userPwd" placeholder="비밀번호를 입력해주세요"></td>
                    </tr>
                    <tr>
                        <th>비밀번호확인</th>
                        <td><input type="password" id="checkPwd" class="input-box" name="checkPwd" placeholder="비밀번호를 한번 더 입력해주세요"></td>
                    </tr>
                    <tr>
                        <th>이름</th>
                        <td><input type="text" class="input-box" name="userName" placeholder="이름을 입력해주세요"></td>
                    </tr>
                    <tr>
                        <th>이메일</th>
                        <td>
                            <div class="row-flex">
                                <input type="email" class="input-box" name="email" placeholder="예: market@fresh.com">
                                <button type="button" class="sub-btn">중복확인</button>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <th>휴대폰</th>
                        <td><input type="text" class="input-box" name="phoneNumber" placeholder="숫자만 입력해주세요"></td>
                    </tr>
                    <tr>
                        <th>주소</th>
                        <td>
                            <div class="row-flex">
                                <input type="text" class="input-box" name="address" placeholder="주소를 검색해주세요">
                                <button type="button" class="sub-btn">주소검색</button>
                            </div>
                        </td>
                    </tr>
                </table>

                <div class="submit-area">
                    <button type="submit" class="btn-join" onclick="return password();">가입하기</button>
                </div>
            </form>
            <script>
            	function password(){
            		const userPwd = document.querySelector('#userPwd').value;
            		const checkPwd = document.querySelector('#checkPwd').value;
            		if(userPwd == checkPwd) {
            			return true
            		} else{
            			alert('비밀번호가 일치하지 않습니다.')
            			return false
            		}
            		
            	}
            
            </script>
            
            
        </section>
    </main>

    <footer>
        <div class="container footer-content">
            <p>&copy; 2026 FRESH MARKET. All rights reserved.</p>
        </div>
    </footer>

</body>
</html>

디자인 가이드와 실제 코드 모두 준비되었습니다! 진행하시다가 서블릿 연동이나 유효성 검사 자바스크립트 작성이 필요하시면 언제든 물어봐 주세요.>