package com.alura.domain.topics;

import com.alura.domain.courses.Courses;
import com.alura.domain.users.Users;
import com.alura.model.StatusTopico;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataRegisterTopic(
        @NotBlank
        String title,
        @NotBlank
        String message,
        @NotNull
        StatusTopico status,
        @NotNull
        Users author,
        @NotNull
        Courses course

) {
}