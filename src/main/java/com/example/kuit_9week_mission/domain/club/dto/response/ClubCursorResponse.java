package com.example.kuit_9week_mission.domain.club.dto.response;

import java.util.List;

public record ClubCursorResponse<T> (
        List<T> data,
        Long lastId,
        boolean hasNext
) {
}
