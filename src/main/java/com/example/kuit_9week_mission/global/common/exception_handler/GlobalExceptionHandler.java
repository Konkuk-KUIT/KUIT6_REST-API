package com.example.kuit_9week_mission.global.common.exception_handler;

import com.example.kuit_9week_mission.global.common.exception.CustomException;
import com.example.kuit_9week_mission.global.common.response.ApiResponse;
import com.example.kuit_9week_mission.global.common.response.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 커스텀 예외
    @ExceptionHandler(CustomException.class)
    public ApiResponse<Void> handleCustomException(CustomException e) {
        ErrorCode errorCode = e.getErrorCode();

        log.error("[CustomException] {} - {}", errorCode.getStatusCode(), errorCode.getMessage(), e);

        return ApiResponse.fail(errorCode);
    }

    // Bean Validation 실패 (@Valid @RequestBody)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<Void> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        String msg = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .collect(Collectors.joining(", "));

        log.error("[Validation] {}", msg, e);

        return ApiResponse.fail(ErrorCode.BAD_REQUEST);
    }

    // HTTP 메서드 불일치 (405)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ApiResponse<Void> handleMethodNotSupported(HttpRequestMethodNotSupportedException e) {
        log.error("[MethodNotAllowed] {}", e.getMessage(), e);

        return ApiResponse.fail(ErrorCode.METHOD_NOT_ALLOWED);
    }

    // RequestBody JSON 타입 불일치
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ApiResponse<Void> handleHttpMessageNotReadable(HttpMessageNotReadableException e) {
        log.error("[HttpMessageNotReadable] {}", e.getMessage(), e);
        return ApiResponse.fail(ErrorCode.BAD_REQUEST);
    }

    // TODO: 더 세분화하고싶은 예외는 직접 추가

    // 기타 모든 예외
    @ExceptionHandler(Exception.class)
    public ApiResponse<Void> handleException(Exception e) {
        log.error("[Unhandled Exception]", e);

        return ApiResponse.fail(ErrorCode.INTERNAL_ERROR);
    }
}
