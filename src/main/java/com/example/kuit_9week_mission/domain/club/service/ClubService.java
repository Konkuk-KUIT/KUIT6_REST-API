package com.example.kuit_9week_mission.domain.club.service;

import com.example.kuit_9week_mission.domain.club.dto.request.CursorRequest;
import com.example.kuit_9week_mission.domain.club.dto.request.UpdateClubRequest;
import com.example.kuit_9week_mission.domain.club.dto.response.ClubResponse;
import com.example.kuit_9week_mission.domain.club.dto.response.CursorResponse;
import com.example.kuit_9week_mission.domain.club.model.Club;
import com.example.kuit_9week_mission.domain.club.repository.ClubRepository;
import com.example.kuit_9week_mission.global.common.exception.CustomException;
import com.example.kuit_9week_mission.global.common.response.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClubService {

    private static final int PAGE_SIZE = 5;

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
    public CursorResponse<ClubResponse> getClubs(CursorRequest request) {
        long cursor = request.cursor();

        List<Club> clubs = clubRepository.findClubs(cursor, PAGE_SIZE);

        boolean hasNext = clubs.size() > PAGE_SIZE;

        if(hasNext) {
            clubs = clubs.subList(0, PAGE_SIZE);
        }

        long lastId = clubs.isEmpty() ? cursor : clubs.get(clubs.size() - 1).clubId();

        List<ClubResponse> data = clubs.stream()
                .map(ClubResponse::from)
                .toList();

        return new CursorResponse<>(data, lastId, hasNext);
    }

    // TODO 2: 동아리 정보 수정 기능 구현(토큰 불필요) - PUT
    @Transactional
    public void updateClub(Long clubId, UpdateClubRequest request) {
        // 존재 검증
        clubRepository.findById(clubId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND, new IllegalArgumentException("해당 동아리가 존재하지 않습니다.")));

        clubRepository.update(clubId, request.name(), request.description());
    }

    // TODO 3: 동아리 삭제 기능 구현(토큰 불필요) - DELETE
}
