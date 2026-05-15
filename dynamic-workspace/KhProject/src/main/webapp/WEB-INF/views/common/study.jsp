<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>실패용 페이지</title>
<style>
	h1{
		color : red;
		font-size : 64px;
		text-align : center;
		height : 600px;
		line-height : 600px;
	}
</style>
</head>
<body>

	<jsp:include page="../include/header.jsp" />
	
	
	<h1> 아주 중요 매우 중요 ★★★★★★ </h1>
	<h2> AJAX! (진짜 매우 중요함)  </h2>
	
	
	<pre>
		AJAX == Asychronous JavaScript And XML
		
		"페이지를 새로고침 하지 않고 서버와 데이터를 주고 받을 수 있는 기술"
		"비동기 통신 기술"
		
		우리가 앞에서 개발했던 방식은 동기방식	
		
		동기방식 :
		1. 사용자가 요청을 보냄
		2. 서버가 요청을 받아서 전체 HTML데이터를 응답
		3. 브라우저는 응답받은 HTML 데이터를 처음부터 끝까지 렌더링 -> 전체페이지를 다시 로딩 -> 화면이 한 번씩 깜빡 (SSR)서버사이드랜더링

		비동기방식 :
		1. 사용자가 요청을 보냄
		2. JavaScript기술을 이용해서 데이터만 서버로 전송
		3. 서버는 JSON / XML 데이터만 응답
		4. JavaScript를 이용해서 필요한 부분만 갱신 -> 부드럽고 빠름 (CSR)클라이언트 서버사이드 랜더링
		
		<hr>
		
		<h3>AJAX 장단점</h3>
	
		장점 : 사용자 경험 (U.X) 향상
		      서버의 부하 감소 (매 번 HTML을 보내느게 아니라 JSON만 보냄)
		      네트워크 트래픽 절약
		단점 : SEO취약(검색엔진최적화 검색엔진노출에 취약함 << 무슨소리인지)
		      브라우저 히스토리 관리 복잡 (되돌아가기 등이 힘듬)
		      JavaScript 의존성이 높다
		      보안 취약점 증가(XSS)
		      
		      
		최신 실무 : 두 가지를 잘 섞어서 사용함

		SPA(Single Page Application) 하나의 페이지로 만드는것
		-> 전성시대
		
		이 비동기 방식을 도와주는 React, Vue, Angular => JavaScript/AJAX기반 라이브러리 및 프레임워크이다.
		

 	</pre>
	
	
	<h2>JSON</h2>
		AJAX는 XML을 이용
		
		김예찬(kyc0****)
		이러쿵저러쿵
		 
		(옛날방식)
		<reply>
			<name>김예찬</name>
			<maskedId>kyc0****</maskedId>
			<content>이러쿵저러쿵</content>
		</reply>
		
	<pre>
		(오늘방식) JSON
		"reply" : {
			"nickName" : "김예찬",
			"id"       :  "kyc0****",
			"content"  :  "이러쿵저러쿵"
		}
		
		JSON : JavaScript Object Notation
		-> 사람이 읽기 쉽고, 기계가 파싱하기 쉬운 데이터
		-> 교환형식이 텍스트기반이라 아주 가볍다
		-> 진짜 자바스크립트 객체 X (자바스크립트 객체 모양으로 문자열을 만든 것)
		
		특징
		1. 문법이 아주 엄격하다. 
		(자바스크립 객체)
		{
			name : "김예찬",   // 키에 따옴표 안적어도됨
			id : 'kyc0****'  // 작은 따옴표 사용가능
			content : '이러쿵저러쿵', // 마지막 속성에 컴마 가능
		}
		(JSON 형식)
		{
			"name" : "김예찬", // 키에 쌍따옴표
			"id" : "kyc0****", // 절대 쌍따옴표
			"content" : "이러쿵저러쿵" // 마지막 컴마 절대 안됨
		}
		
		2. 장점 : 가독성이 좋다
		         (XML과 비교했을 때 매우 좋다)
		         데이터 자체가 가볍다.
		         (XML대비해서 30% 가볍다 닫는태그가 없어도됨)
		         파싱속도 빠르다
		         언어 독립적
		         JavaScript 네이티브 지원
		 
		3. 단점 : 주석 불가
		         날짜 타입 없음 (문자열로 처리)
		         함수 불가능
		        
		현재 웹 개발의 표준!! 데이터 형식
		REST API의 기본 포맷!!
		설정파일 XML -> JSON(설정파일 -> YAML이 인기 많음)
	</pre>	
		
	<pre>
		
	
		우리는 AJAX를 이용해서 댓글 기능을 구현해볼 예정
		
		AJAX 사용방법
		
		1. XMLHttpRequest 객체 생성해서 사용하는 방법 (옛날) XXXXX
		2. JQery를 사용해서 ajax메소드를 호출하는 방법 
		3. fetch API 활용해서 fetch 호출하는 방법   (스프링)
		4. React 배울 때 Axios 라이브러리 설치해서 사용하는 방법 (스프링부트 때)
	
		2,3,4는 현업에서 사용하는 방법들임
	</pre>
	
	
	
	
	
	<h4> jQeury를 이용한 ajax활용</h4>
	
	<h5>요청을 보내고 응답받아오기</h5>
	
	<div class="form-group">
		<div class="form-control">
			<button class="btn btn-sm btn-secondary" onclick="fn1();"> 요청 보내기 </button>
		</div>
	</div>
	
	응답 : <label id="output1">아직 응답 없음</label>
	
	<jsp:include page="../include/footer.jsp" />



	<script>
	function fn1(){
	    // 비동기식 요청
	    $.ajax({
	        url : 'http://localhost:8088/kh/ajax1.do',
	        type : 'get',
	        success : result => {
	            console.log(result);
	            document.querySelector('#output1').innerHTML = result;
	        },
	        error : (xhr, status, error) => {
	            console.log('ajax요청 실패!');
	            document.querySelector('#output1').innerHTML = '통신에 실패';

	        },
	        complete : () => {
	            console.log('성공실패 무조건');
	        }
	    });
	}
	
	</script>


	<hr>
	
	
	<h3> 게시글 번호를 보내서 게시글 정보 받아오기</h3>
	
	게시글 번호 : <input type="number" id="boardNo"/> <br>
	
	<button onclick="infoBoard();"> 게시글 주세요 </button>

	<hr>
	
	게시글 제목 : <label id="title"> 현재 응답 없음 </label> <br>
	게시글 내용 : <label id="content"> 현재 응답 없음 </label> <br>
	
	<script>
		
		function infoBoard(){
			$.ajax({
				url : 'http://localhost:8088/kh/ajax2.do',
				type : 'get',
				data : {
					boardNo : document.querySelector('#boardNo').value
				},
				success : result => {
					console.log(result);
					document.querySelector('#title').innerHTML = result.boardTitle;
					document.querySelector('#content').innerHTML = result.boardContent;
				},
				error : e => {
					console.log(e);
				}

			});
		}
	
	</script>
	
	
	
	<hr>
	
	
	<h3> 사진 게시글 목록조회 </h3>
	

	<div id="result" style="width: 100%; height : 300px; margin: auto;">

		
		



	</div>
	<button class="btn btn-lg btn-danger" onclick="img();"> 사진게시글 조회하기 </button>

	<script >
	
			
		function img(){
			$.ajax({
				url : 'http://localhost:8088/kh/ajax3.do',
				type : 'get',
				success : result => {
					console.log(result)
					
					const el = result.map(e => 
						
							`<div>
								<label>글 번호 : \${e.boardNo}</label>
								<div><img src="\${e.src}" width="120" height="70" /></div>
								<p>제목 : \${e.boardTitle}</p>
							</div>`
					).join('');
					document.querySelector('#result').innerHTML = el;
					
					
				}
				
			});
		}
	
	</script>


</body>
</html>