package com.example.kuit_9week_mission.domain.club.service;

import com.example.kuit_9week_mission.domain.club.repository.ClubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClubService {

    private final ClubRepository clubRepository;

    // TODO 1: 전체 동아리 목록 조회 기능 구현(토큰 불필요) - GET (무한 스크롤 - 각 페이지당 5개의 데이터를 보여줄 것)

    // TODO 2: 동아리 정보 수정 기능 구현(토큰 불필요) - PUT

    // TODO 3: 동아리 삭제 기능 구현(토큰 불필요) - DELETE
}
