<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>농산물 마켓 - 프레시 푸드</title>
    <link rel="stylesheet" href="resources/css/welcome.css">
</head>
<body>

    <!-- 헤더 영역 -->
    <header>
        <div class="container header-content">
            <div class="logo">
                <h1>FRESH MARKET</h1>
            </div>
            
            <div class="search-bar">
                <form action="" method="">
                    <input type="text" name="" placeholder="신선한 농산물을 검색해보세요">
                    <button type="submit">🔍</button>
                </form>
            </div>

            <div class="header-util">
                <!-- 미로그인 상태 -->
                <div class="auth-guest">
					<c:choose>
						<c:when test="${ userInfo eq null }">
		                    <form method="get" action="http://localhost:8088/market/enroll.do">
		                    	<button type="submit">회원가입</button>
		                    </form>
		                    <form method="get" action="http://localhost:8088/market/login_page.do">
			                    <button type="submit">로그인</button>
		                    </form>
		    				<form>
		                    	<button type="button">고객센터</button>
		    				</form>
						</c:when>
						<c:otherwise>
			                <form method="get" action="http://localhost:8088/market/logout.do"> 
			                    <button type="submit">로그아웃</button>
			                </form>
			                <form method=get action="http://localhost:8088/market/myInfo_Page.do">    
			                    <button type="submit">내정보</button>
			                </form>
			                <form>
			                    <button type="button">고객센터</button>
			                </form>
						</c:otherwise>	    				
					</c:choose>
                </div> 

            </div>
        </div>
    </header>

    <!-- 네비게이션 바 -->
    <nav>
        <div class="container">
            <ul class="nav-list">
                <li><a href="#">인기</a></li>
                <li><a href="#">제철</a></li>
                <li><a href="#">지역</a></li>
                <li><a href="#">과일</a></li>
                <li><a href="#">채소</a></li>
                <li><a href="#">축산</a></li>
            </ul>
        </div>
    </nav>

    <!-- 메인 1: 히어로 배너 (1800px) -->
    <section class="hero-section">
        <div class="hero-image">
            <h2>산지 직송, 가장 신선한 순간</h2>
        </div>
    </section>

    <!-- 메인 컨텐츠 (1280px) -->
    <main class="container">
        <!-- 메인 2: 인기 상품 -->
        <section class="product-section">
            <h3>🔥 실시간 인기 상품</h3>
            <div class="product-grid">
                <!-- 상품카드 5개 -->
                <div class="product-card">
                    <div class="img-box"></div>
                    <p class="p-name">유기농 꿀사과 1kg</p>
                    <p class="p-price">12,500원</p>
                </div>
                <div class="product-card">
                    <div class="img-box"></div>
                    <p class="p-name">당일수확 아삭 오이고추</p>
                    <p class="p-price">3,200원</p>
                </div>
                <div class="product-card">
                    <div class="img-box"></div>
                    <p class="p-name">고소한 무항생제 삼겹살</p>
                    <p class="p-price">24,000원</p>
                </div>
                <div class="product-card">
                    <div class="img-box"></div>
                    <p class="p-name">달콤한 고창 수박</p>
                    <p class="p-price">18,900원</p>
                </div>
                <div class="product-card">
                    <div class="img-box"></div>
                    <p class="p-name">흙 묻은 신선 당근</p>
                    <p class="p-price">4,500원</p>
                </div>
            </div>
        </section>

        <!-- 메인 3: 제철 상품 -->
        <section class="product-section">
            <h3>🌿 지금이 딱! 제철 상품</h3>
            <div class="product-grid">
                <div class="product-card">
                    <div class="img-box s-bg"></div>
                    <p class="p-name">봄나물 모음전</p>
                    <p class="p-price">5,000원</p>
                </div>
                <div class="product-card">
                    <div class="img-box s-bg"></div>
                    <p class="p-name">성주 꿀참외 2kg</p>
                    <p class="p-price">15,000원</p>
                </div>
                <div class="product-card">
                    <div class="img-box s-bg"></div>
                    <p class="p-name">대저 짭짤이 토마토</p>
                    <p class="p-price">13,200원</p>
                </div>
                <div class="product-card">
                    <div class="img-box s-bg"></div>
                    <p class="p-name">부드러운 햇감자</p>
                    <p class="p-price">6,800원</p>
                </div>
                <div class="product-card">
                    <div class="img-box s-bg"></div>
                    <p class="p-name">달콤 새콤 산딸기</p>
                    <p class="p-price">9,900원</p>
                </div>
            </div>
        </section>
    </main>

    <!-- 푸터 -->
    <footer>
        <div class="container footer-content">
            <div class="footer-info">
                <p>고객행복센터 1234-5678</p>
                <p>평일 09:00 ~ 18:00 (주말/공휴일 휴무)</p>
            </div>
            <div class="footer-copy">
                <p>&copy; 2026 FRESH MARKET. All rights reserved.</p>
            </div>
        </div>
    </footer>

</body>
</html>