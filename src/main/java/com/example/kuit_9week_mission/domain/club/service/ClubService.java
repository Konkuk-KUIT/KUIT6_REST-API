package com.example.kuit_9week_mission.domain.club.service;

import com.example.kuit_9week_mission.domain.club.repository.ClubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClubService {

    private final ClubRepository clubRepository;

    // TODO 1: 동아리 목록 조회 기능 구현(토큰 불필요) - GET (무한 스크롤 - 각 페이지당 5개의 데이터를 보여줄 것 & status 기반 필터링)
    /**
     * 응답 DTO 구조는 아래와 같은 형태를 따를 것
     * {
     *   "isSuccess": true,
     *   "statusCode": 20000,
     *   "message": "요청에 성공했습니다",
     *   "data": {
     *       "data": [
     *           {
     *               "clubId": 4,
     *               "name": "동아리 4",
     *               "description": "동아리 4 설명"
     *           }
     *       ],
     *       "lastId": 100,
     *       "hasNext": true
     *   },
     *   "timestamp": "2025-10-24T00:37:07.469931"
     * }
     */

    // TODO 2: 동아리 정보 수정 기능 구현(토큰 불필요) - PUT

    // TODO 3: 동아리 삭제 기능 구현(토큰 불필요) - DELETE
}
