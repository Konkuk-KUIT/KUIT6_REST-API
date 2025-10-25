package com.example.kuit_9week_mission.domain.club.service;

import com.example.kuit_9week_mission.domain.club.dto.response.MyClubsResponse;
import com.example.kuit_9week_mission.domain.club.repository.ClubMemberRepository;
import com.example.kuit_9week_mission.domain.club.repository.ClubRepository;
import com.example.kuit_9week_mission.domain.student.repository.StudentRepository;
import com.example.kuit_9week_mission.global.common.exception.CustomException;
import com.example.kuit_9week_mission.global.common.response.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClubMemberService {

    private final StudentRepository studentRepository;
    private final ClubRepository clubRepository;
    private final ClubMemberRepository clubMemberRepository;

    // 현재 로그인한 학생의 동아리 가입 기능
    @Transactional
    public void joinClub(Long studentId, Long clubId) {
        // 학생 존재 검증
        studentRepository.findById(studentId)
                .orElseThrow(() -> new CustomException(ErrorCode.STUDENT_NOT_FOUND));

        // 동아리 존재 검증
        clubRepository.findById(clubId)
                .orElseThrow(() -> new CustomException(ErrorCode.CLUB_NOT_FOUND));

        // 이미 가입되어있으면 에러 발생
        if (clubMemberRepository.existsByStudentIdAndClubId(studentId, clubId)) {
            throw new CustomException(ErrorCode.ALREADY_JOINED_CLUB);
        }

        clubMemberRepository.save(studentId, clubId, LocalDate.now());
    }

    // 현재 로그인한 학생이 속해있는 동아리 목록 조회 기능
    /*
     * 응답 DTO 구조는 반드시 아래 형태를 따를 것
     * {
     *   "isSuccess": true,
     *   "statusCode": 20000,
     *   "message": "요청에 성공했습니다",
     *   "data": {
     *       "studentName": "멤버1",
     *       "clubNames": ["동아리 3", "동아리 7", "동아리 10"]
     *   },
     *   "timestamp": "2025-10-24T00:37:07.469931"
     * }
     */
    public MyClubsResponse getMyClubs(Long studentId) {
        // 학생 조회 및 검증
        String studentName = studentRepository.findById(studentId)
                .orElseThrow(() -> new CustomException(ErrorCode.STUDENT_NOT_FOUND))
                .name();

        // Club_Members 와 JOIN 하여 동아리 이름 목록 조회
        List<String> clubNames = clubMemberRepository.findClubNamesByStudentId(studentId);

        return MyClubsResponse.of(studentName, clubNames);
    }



}
