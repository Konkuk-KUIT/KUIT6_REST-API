package com.example.kuit_9week_mission.global.common.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SuccessCode {
    SUCCESS(20000, "요청에 성공했습니다.");

    private final int statusCode;
    private final String message;
}
