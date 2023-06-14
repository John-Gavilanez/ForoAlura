package com.alura.domain.courses;

import jakarta.validation.constraints.NotBlank;

public record DataRegisterCourse(
        @NotBlank
        String name,
        @NotBlank
        String type
) {
}