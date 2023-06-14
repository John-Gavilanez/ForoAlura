package com.alura.domain.responses;

import com.alura.domain.topics.Topics;
import com.alura.domain.users.Users;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataRegisterResponse(
        @NotBlank
        String message,
        @NotNull
        Boolean solution,
        @NotNull
        Topics topic,
        @NotNull
        Users author
) {
}