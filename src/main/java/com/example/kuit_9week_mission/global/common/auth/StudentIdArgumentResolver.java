package com.example.kuit_9week_mission.global.common.auth;

import com.example.kuit_9week_mission.global.common.exception.CustomException;
import com.example.kuit_9week_mission.global.jwt.JwtUtil;
import com.example.kuit_9week_mission.global.common.response.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@RequiredArgsConstructor
public class StudentIdArgumentResolver implements HandlerMethodArgumentResolver {

    private final JwtUtil jwtUtil;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(StudentId.class)
                && parameter.getParameterType().equals(Long.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String authHeader = webRequest.getHeader("Authorization");

        if (authHeader == null || authHeader.isBlank()) {
            throw new CustomException(ErrorCode.UNAUTHORIZED, new IllegalArgumentException("Authorization 헤더가 존재하지 않습니다."));
        }

        String token = extractBearerToken(authHeader);

        return jwtUtil.getStudentId(token);
    }

    private String extractBearerToken(String header) {
        String prefix = "Bearer ";

        if (!header.startsWith(prefix)) {
            throw new CustomException(ErrorCode.UNAUTHORIZED, new IllegalArgumentException("Authorization 헤더 형식이 올바르지 않습니다."));
        }

        return header.substring(prefix.length()).trim();
    }
}
