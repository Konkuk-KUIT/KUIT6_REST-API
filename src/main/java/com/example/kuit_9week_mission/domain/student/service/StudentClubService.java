package com.example.kuit_9week_mission.domain.student.service;

import com.example.kuit_9week_mission.domain.student.repository.StudentClubRepository;
import com.example.kuit_9week_mission.domain.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentClubService {

    private final StudentRepository studentRepository;
    private final StudentClubRepository studentClubRepository;

    // TODO 5: 현재 로그인한 학생의 동아리 가입 기능 구현(토큰 필요) - POST

    // TODO 6: 현재 로그인한 학생이 속해있는 동아리 목록 조회(토큰 필요) - (학생의 이름 & 동아리 이름 모두 반환 => JOIN 잘 활용하기) - GET
    /*
     * 응답 DTO 구조는 반드시 아래 형태를 따를 것
     * {
     *   "studentName": "멤버1",
     *   "clubNames": ["Club 3", "Club 7", "Club 10"]
     * }
     */


}
