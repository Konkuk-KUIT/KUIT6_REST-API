package com.example.kuit_9week_mission.global.common.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SuccessCode {
    SUCCESS(HttpStatus.OK, 20000, "요청에 성공했습니다.");

    private final HttpStatus httpStatus;
    private final int code;
    private final String message;
}
