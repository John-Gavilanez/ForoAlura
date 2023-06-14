package com.alura.domain.topics;
import com.alura.domain.responses.Responses;
import com.alura.model.StatusTopico;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record DataResponseTopic(
        Long id,
        String title,
        String message,
        LocalDateTime createdAt,
        StatusTopico status,
        Long author,
        Long course,
        List<Long> responses

) {
    public DataResponseTopic(Topics topic){
        this(topic.getId(), topic.getTitle(),
                topic.getMessage(), topic.getCreatedAt(),
                topic.getStatus(), topic.getAuthor().getId(),
                topic.getCourse().getId(), topic.getResponses().stream().map(Responses::getId).collect(Collectors.toList()));
    }



}

