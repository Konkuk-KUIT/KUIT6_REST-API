package com.example.kuit_9week_mission.domain.club.controller;

import com.example.kuit_9week_mission.domain.club.dto.request.CursorRequest;
import com.example.kuit_9week_mission.domain.club.dto.request.UpdateClubRequest;
import com.example.kuit_9week_mission.domain.club.dto.response.ClubResponse;
import com.example.kuit_9week_mission.domain.club.dto.response.CursorResponse;
import com.example.kuit_9week_mission.domain.club.service.ClubMemberService;
import com.example.kuit_9week_mission.domain.club.service.ClubService;
import com.example.kuit_9week_mission.global.common.auth.StudentId;
import com.example.kuit_9week_mission.global.common.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clubs")
@RequiredArgsConstructor
public class ClubController {

    private final ClubService clubService;
    private final ClubMemberService clubMemberService;

    // 전체 동아리 목록 조회 기능
    @GetMapping
    public ApiResponse<CursorResponse<ClubResponse>> getClubs(@Valid CursorRequest request) {
        return ApiResponse.ok(clubService.getClubs(request));
    }

    // 동아리 정보 수정 기능
    @PutMapping("/{clubId}")
    public ApiResponse<Void> updateClub(
            @PathVariable Long clubId,
            @Valid @RequestBody UpdateClubRequest request
    ) {
        clubService.updateClub(clubId, request);

        return ApiResponse.ok(null);
    }

    // 동아리 삭제 기능
    @DeleteMapping("/{clubId}")
    public ApiResponse<Void> deleteClub(
            @PathVariable Long clubId
    ) {
        clubService.deleteClub(clubId);

        return ApiResponse.ok(null);
    }

    // 현재 로그인한 학생의 동아리 가입 기능
    @PostMapping("/{clubId}/members")
    public ApiResponse<Void> joinClub(
            @PathVariable Long clubId,
            @StudentId Long studentId
    ) {
        clubMemberService.joinClub(studentId, clubId);

        return ApiResponse.ok(null);
    }
}
