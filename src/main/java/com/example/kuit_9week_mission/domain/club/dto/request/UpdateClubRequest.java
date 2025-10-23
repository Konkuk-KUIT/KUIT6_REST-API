package com.example.kuit_9week_mission.domain.club.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UpdateClubRequest(
        @NotBlank(message = "동아리 이름은 null 이거나 공백이어서는 안됩니다.")
        String name,
        String description
) {
}
