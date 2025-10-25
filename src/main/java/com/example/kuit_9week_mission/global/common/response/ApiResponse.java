package com.example.kuit_9week_mission.global.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiResponse<T>(
        boolean isSuccess,
        int statusCode,
        String message,
        T data,
        LocalDateTime timestamp
) {
    public static <T> ApiResponse<T> ok(SuccessCode successCode, T data) {
        return new ApiResponse<>(true, successCode.getCode(), successCode.getMessage(), data, LocalDateTime.now());
    }

    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>(true, SuccessCode.SUCCESS.getCode(), SuccessCode.SUCCESS.getMessage(), data, LocalDateTime.now());
    }

    public static <T> ApiResponse<T> fail(ErrorCode errorCode) {
        return new ApiResponse<>(false, errorCode.getCode(), errorCode.getMessage(), null, LocalDateTime.now());
    }

    public static <T> ApiResponse<T> fail(ErrorCode errorCode, String message) {
        return new ApiResponse<>(false, errorCode.getCode(), message, null, LocalDateTime.now());
    }

}
