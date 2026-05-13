<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Fresh Market - 로그인</title>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300;400;500;700&display=swap" rel="stylesheet">
    <style>
        /* 초기화 및 공통 스타일 */
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { font-family: 'Noto Sans KR', sans-serif; background-color: #fff; color: #333; }
        .container { width: 1280px; margin: 0 auto; }
        a { text-decoration: none; color: inherit; }

        /* 헤더 (일관성 유지) */
        header { padding: 20px 0; border-bottom: 1px solid #f4f4f4; }
        .header-content { display: flex; justify-content: space-between; align-items: center; }
        .logo h1 { color: #5f0080; font-size: 26px; cursor: pointer; }
        .header-util button { background: none; border: none; margin-left: 15px; color: #666; font-size: 14px; cursor: pointer; }

        /* 로그인 섹션 */
        .login-section { width: 100%; padding: 90px 0 120px; display: flex; flex-direction: column; align-items: center; }
        .login-title { font-size: 28px; font-weight: 700; margin-bottom: 30px; }
        
        .login-box { width: 340px; } /* 마켓컬리는 로그인 박스가 꽤 슬림합니다 */
        
        .input-group { margin-bottom: 10px; }
        .input-box { 
            width: 100%; 
            height: 54px; 
            padding: 0 19px; 
            border: 1px solid #ccc; 
            border-radius: 4px; 
            outline: none; 
            font-size: 14px; 
        }
        .input-box:focus { border: 1px solid #5f0080; }

        /* 유틸리티 (아이디 찾기 등) */
        .login-util { 
            display: flex; 
            justify-content: flex-end; 
            margin-top: 10px; 
            font-size: 13px; 
            color: #333; 
        }
        .login-util span { margin: 0 5px; color: #ccc; }

        /* 버튼들 */
        .btn-area { margin-top: 28px; }
        .btn { 
            width: 100%; 
            height: 54px; 
            border-radius: 4px; 
            font-size: 16px; 
            font-weight: 500; 
            cursor: pointer; 
            margin-bottom: 10px;
            display: flex;
            align-items: center;
            justify-content: center;
        }
        .btn-login { 
            background-color: #5f0080; 
            color: #fff; 
            border: none; 
        }
        .btn-join { 
            background-color: #fff; 
            color: #5f0080; 
            border: 1px solid #5f0080; 
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
            <div class="header-util">
                <button onclick="location.href='join.jsp'">회원가입</button>
                <button>로그인</button>
                <button>고객센터</button>
            </div>
        </div>
    </header>

    <main class="container">
        <section class="login-section">
            <h2 class="login-title">로그인</h2>
            
            <div class="login-box">
                <form action="login.do" method="post">
                    <div class="input-group">
                        <input type="text" class="input-box" name="userId" placeholder="아이디를 입력해주세요">
                    </div>
                    <div class="input-group">
                        <input type="password" class="input-box" name="userPwd" placeholder="비밀번호를 입력해주세요">
                    </div>

                    <div class="login-util">
                        <a href="#">아이디 찾기</a>
                        <span>|</span>
                        <a href="#">비밀번호 찾기</a>
                    </div>

                    <div class="btn-area">
                        <button type="submit" class="btn btn-login">로그인</button>
                        <a href="http://localhost:8088/market/endoll.do" class="btn btn-join">회원가입</a>
                    </div>
                </form>
            </div>
        </section>
    </main>

    <footer>
        <div class="container footer-content">
            <p>&copy; 2026 FRESH MARKET. All rights reserved.</p>
        </div>
    </footer>

</body>
</html>