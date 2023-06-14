package com.alura.domain.responses;

import com.alura.domain.topics.Topics;
import com.alura.domain.users.Users;
import jakarta.validation.constraints.NotNull;

public record DataUpdateResponse(
        @NotNull
        Long id,
        String message,
        Boolean solution,
        Topics topic,
        Users author
) {

}