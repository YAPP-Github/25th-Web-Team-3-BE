package com.coffee.api.common.support.error

enum class ErrorCode(val code: Int) {
    INTERNAL_SERVER_ERROR(500),  // 500: 서버 내부 오류
    BAD_REQUEST(400),            // 400: 잘못된 요청
    UNAUTHORIZED(401),           // 401: 인증 실패
    FORBIDDEN(403),              // 403: 권한 없음
    NOT_FOUND(404),              // 404: 리소스를 찾을 수 없음
    CONFLICT(409),               // 409: 데이터 충돌
    VALIDATION_ERROR(422)        // 422: 유효성 검사 실패
}
