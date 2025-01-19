package com.coffee.api.common.support.error

enum class ErrorCode {
    INTERNAL_SERVER_ERROR_500,  // 500: 서버 내부 오류
    CLIENT_REQUEST_ERROR_400,   // 400: 잘못된 요청
    UNAUTHORIZED_401,           // 401: 인증 실패
    FORBIDDEN_403,              // 403: 권한 없음
    NOT_FOUND_404,              // 404: 리소스를 찾을 수 없음
    CONFLICT_409,               // 409: 데이터 충돌
    VALIDATION_ERROR_422        // 422: 유효성 검사 실패
}
