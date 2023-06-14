package com.alura.domain.topics;

import com.alura.domain.courses.Courses;
import com.alura.domain.users.Users;
import com.alura.model.StatusTopico;
import jakarta.validation.constraints.NotNull;

public record DataUpdateTopic(
        @NotNull
        Long id,
        String title,
        String message,
        StatusTopico status,
        Users author,
        Courses course
) {

}