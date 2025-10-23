package com.example.kuit_9week_mission.domain.auth.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record LoginRequest(
        @Min(value = 1, message = "학번은 반드시 1이상의 정수여야합니다.")
        @NotNull(message = "studentNumber 는 null 이 아니어야합니다.")
        Integer studentNumber
) {
}
