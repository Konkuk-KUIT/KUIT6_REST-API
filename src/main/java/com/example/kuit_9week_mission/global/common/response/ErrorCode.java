package com.example.kuit_9week_mission.global.common.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 50000, "서버 내부 오류가 발생했습니다."),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, 40000, "잘못된 요청입니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, 40100, "인증에 실패했습니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, 40300, "권한이 없습니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND, 40400, "리소스를 찾을 수 없습니다."),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, 40500, "허용되지 않은 Http 메서드입니다."),
    CONFLICT(HttpStatus.CONFLICT, 40900, "요청이 현재 리소스 상태와 충돌합니다."),

    // Student 도메인
    STUDENT_NOT_FOUND(HttpStatus.NOT_FOUND, 40401, "해당 학생이 존재하지 않습니다."),

    // Club 도메인
    CLUB_NOT_FOUND(HttpStatus.NOT_FOUND, 40402, "해당 동아리가 존재하지 않습니다."),
    ALREADY_JOINED_CLUB(HttpStatus.CONFLICT, 40901, "이미 가입된 동아리입니다.");

    private final HttpStatus httpStatus;
    private final int code;
    private final String message;
}
