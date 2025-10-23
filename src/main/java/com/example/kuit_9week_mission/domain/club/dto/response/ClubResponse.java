package com.example.kuit_9week_mission.domain.club.dto.response;

import com.example.kuit_9week_mission.domain.club.model.Club;

public record ClubResponse(
        Long clubId,
        String name,
        String description
) {
    public static ClubResponse from(Club club) {
        return new ClubResponse(club.clubId(), club.name(), club.description());
    }
}
