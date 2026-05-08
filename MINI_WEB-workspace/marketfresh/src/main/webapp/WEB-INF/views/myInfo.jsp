<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>마이페이지</title>
    <!-- Bootstrap 5 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background-color: #f8f9fa; }
        .profile-card { max-width: 600px; margin: 50px auto; background: white; padding: 30px; border-radius: 15px; box-shadow: 0 4px 15px rgba(0,0,0,0.1); }
        .info-label { font-weight: bold; color: #6c757d; width: 120px; display: inline-block; }
    </style>
</head>
<body>

<div class="container">
    <div class="profile-card">
        <h2 class="text-center mb-4">내 정보</h2>
        <hr>
        
        <!-- 회원 정보 표시 영역 -->
        <div class="mb-3">
            <span class="info-label">아이디</span>
            <span>USER_ID_VALUE</span>
        </div>
        <div class="mb-3">
            <span class="info-label">이름</span>
            <span>USER_NAME_VALUE</span>
        </div>
        <div class="mb-3">
            <span class="info-label">주소</span>
            <span>ADDRESS_VALUE</span>
        </div>
        <div class="mb-3">
            <span class="info-label">연락처</span>
            <span>PHONE_NUMBER_VALUE</span>
        </div>
        <div class="mb-3">
            <span class="info-label">이메일</span>
            <span>EMAIL_VALUE</span>
        </div>

        <div class="d-flex justify-content-center gap-3 mt-5">
            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#updateModal">회원정보수정</button>
            <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteModal">회원탈퇴</button>
        </div>
    </div>
</div>

<!-- 1. 회원정보 수정 모달 -->
<div class="modal fade" id="updateModal" tabindex="-1" aria-labelledby="updateModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="updateModalLabel">회원 정보 수정</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form action="" method="post">
                <div class="modal-body">
                    <div class="mb-3">
                        <label class="form-label">아이디</label>
                        <input type="text" class="form-control" name="" value="USER_ID_VALUE" readonly>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">이름</label>
                        <input type="text" class="form-control" name="" value="USER_NAME_VALUE">
                    </div>
                    <div class="mb-3">
                        <label class="form-label">주소</label>
                        <input type="text" class="form-control" name="" value="ADDRESS_VALUE">
                    </div>
                    <div class="mb-3">
                        <label class="form-label">연락처</label>
                        <input type="text" class="form-control" name="" value="PHONE_NUMBER_VALUE">
                    </div>
                    <div class="mb-3">
                        <label class="form-label">이메일</label>
                        <input type="email" class="form-control" name="" value="EMAIL_VALUE">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                    <button type="submit" class="btn btn-primary">수정완료</button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- 2. 회원탈퇴 확인 모달 -->
<div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title text-danger" id="deleteModalLabel">회원 탈퇴 확인</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form action="" method="post">
                <div class="modal-body text-center">
                    <p class="fw-bold">정말로 탈퇴하시겠습니까?</p>
                    <p class="text-muted small">탈퇴 시 모든 정보가 삭제되며 복구할 수 없습니다.</p>
                    <input type="hidden" name="" value="USER_ID_VALUE">
                    <div class="mt-3">
                        <label class="form-label">비밀번호 확인</label>
                        <input type="password" class="form-control" name="" placeholder="비밀번호를 입력하세요">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                    <button type="submit" class="btn btn-danger">탈퇴하기</button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- Bootstrap 5 JS Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>