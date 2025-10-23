package com.example.kuit_9week_mission.domain.auth.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record LoginRequest(
        @Positive(message = "학번은 반드시 양의 정수여야합니다.")
        @NotNull(message = "학번은 null 이 아니어야합니다.")
        Integer studentNumber
) {
}
