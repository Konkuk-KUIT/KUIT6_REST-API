package com.example.kuit_9week_mission.global.common.exception_handler;

import com.example.kuit_9week_mission.global.common.exception.CustomException;
import com.example.kuit_9week_mission.global.common.response.ApiResponse;
import com.example.kuit_9week_mission.global.common.response.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private String resolveMessage(String customMessage, String defaultMessage) {
        return (customMessage != null && !customMessage.isBlank())
                ? customMessage
                : defaultMessage;
    }

    // 커스텀 예외
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiResponse<Void>> handleCustomException(CustomException e) {
        ErrorCode errorCode = e.getErrorCode();

        String customMessage = Optional.ofNullable(e.getCause())
                .map(Throwable::getMessage)
                .map(msg -> resolveMessage(msg, errorCode.getMessage()))
                .orElse(errorCode.getMessage());

        log.error("[CustomException] {} - {} - {}", errorCode.getHttpStatus(), errorCode.getCode(), customMessage, e);

        return new ResponseEntity<>(ApiResponse.fail(errorCode, customMessage), errorCode.getHttpStatus());
    }

    // Bean Validation 실패 (@Valid @RequestBody)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<Void> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        String detailMessage = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .collect(Collectors.joining(", "));

        String customMessage = resolveMessage(detailMessage, ErrorCode.BAD_REQUEST.getMessage());

        log.error("[Validation] {}", customMessage, e);

        return ApiResponse.fail(ErrorCode.BAD_REQUEST, customMessage);
    }

    // HTTP 메서드 불일치 (405)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ApiResponse<Void> handleMethodNotSupported(HttpRequestMethodNotSupportedException e) {
        log.error("[MethodNotAllowed] {}", e.getMessage(), e);

        return ApiResponse.fail(ErrorCode.METHOD_NOT_ALLOWED);
    }

    // RequestBody JSON 타입 불일치
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ApiResponse<Void> handleHttpMessageNotReadable(HttpMessageNotReadableException e) {
        log.error("[HttpMessageNotReadable] {}", e.getMessage(), e);

        return ApiResponse.fail(ErrorCode.BAD_REQUEST);
    }

    // 기타 모든 예외
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ApiResponse<Void> handleException(Exception e) {
        String customMessage = resolveMessage(e.getMessage(), ErrorCode.INTERNAL_ERROR.getMessage());

        log.error("[Unhandled Exception] {}", customMessage, e);

        return ApiResponse.fail(ErrorCode.INTERNAL_ERROR, customMessage);
    }
}
