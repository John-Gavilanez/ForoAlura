package com.alura.domain.responses;


import java.time.LocalDateTime;

public record DataResponseResponse(
        Long id,
        String message,
        LocalDateTime createdAt,
        Boolean solution,
        Long topic,
        Long author

) {
    public DataResponseResponse(Responses response) {
        this(
                response.getId(), response.getMessage(),
                response.getCreatedAt(), response.getSolution(),
                response.getTopic().getId(), response.getAuthor().getId());
    }
}