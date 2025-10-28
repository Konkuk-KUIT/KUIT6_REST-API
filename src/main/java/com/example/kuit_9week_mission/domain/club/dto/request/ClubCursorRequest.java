package com.example.kuit_9week_mission.domain.club.dto.request;

import com.example.kuit_9week_mission.domain.club.model.ClubStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ClubCursorRequest(
        @NotNull(message = "cursor 값은 필수입니다.")
        @Positive(message = "cursor 는 양의 정수여야 합니다.")
        Long cursor,
        @NotNull(message = "status 값은 필수입니다.")
        ClubStatus status
) {
}
