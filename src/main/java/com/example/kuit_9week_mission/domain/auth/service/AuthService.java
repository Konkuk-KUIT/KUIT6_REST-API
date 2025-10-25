package com.example.kuit_9week_mission.domain.auth.service;

import com.example.kuit_9week_mission.domain.auth.dto.request.LoginRequest;
import com.example.kuit_9week_mission.domain.auth.dto.response.LoginResponse;
import com.example.kuit_9week_mission.domain.student.model.Student;
import com.example.kuit_9week_mission.domain.student.repository.StudentRepository;
import com.example.kuit_9week_mission.global.common.exception.CustomException;
import com.example.kuit_9week_mission.global.jwt.JwtUtil;
import com.example.kuit_9week_mission.global.common.response.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final StudentRepository studentRepository;
    private final JwtUtil jwtUtil;

    public LoginResponse login(LoginRequest request) {
        Student student = studentRepository.findByStudentNumber(request.studentNumber())
                .orElseThrow(() -> new CustomException(ErrorCode.STUDENT_NOT_FOUND));

        Long studentId = student.studentId();

        String accessToken = jwtUtil.generateAccessToken(studentId);

        return LoginResponse.of(accessToken);
    }
}
