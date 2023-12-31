package com.alura.domain.users;

import jakarta.validation.constraints.NotNull;

public record DataUpdateUser(
        @NotNull
        Long id,
        String name,
        String email,
        String password
) {
}