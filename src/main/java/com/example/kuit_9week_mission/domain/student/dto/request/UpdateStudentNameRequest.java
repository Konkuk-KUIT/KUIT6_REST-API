package com.example.kuit_9week_mission.domain.student.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record UpdateStudentNameRequest(
        @NotBlank(message = "학생 이름은 null 이거나 공백이어서는 안됩니다.")
        String name
) {
}
