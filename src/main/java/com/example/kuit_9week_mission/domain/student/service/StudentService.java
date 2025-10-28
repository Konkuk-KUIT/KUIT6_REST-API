package com.example.kuit_9week_mission.domain.student.service;

import com.example.kuit_9week_mission.domain.student.dto.request.UpdateStudentNameRequest;
import com.example.kuit_9week_mission.domain.student.dto.response.StudentInfoResponse;
import com.example.kuit_9week_mission.domain.student.model.Student;
import com.example.kuit_9week_mission.domain.student.repository.StudentRepository;
import com.example.kuit_9week_mission.global.common.exception.CustomException;
import com.example.kuit_9week_mission.global.common.response.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    // 학생 본인 정보 조회 기능
    public StudentInfoResponse getStudentInfo(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND, new IllegalArgumentException("해당 학생이 존재하지 않습니다.")));

        return StudentInfoResponse.from(student);
    }

    // 현재 로그인한 학생 이름 수정 기능
    @Transactional
    public void updateStudentInfo(Long studentId, UpdateStudentNameRequest request) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND, new IllegalArgumentException("해당 학생이 존재하지 않습니다.")));

        studentRepository.update(studentId, student.studentNumber(), request.name());
    }
}
