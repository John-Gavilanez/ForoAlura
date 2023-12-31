package com.alura.domain.responses;

import com.alura.domain.topics.Topics;
import com.alura.domain.users.Users;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "responses")
@Entity(name = "Responses")
@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Responses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "message")
    private String message;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id")
    private Topics topic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Users author;

    @Column(name = "solution")
    private Boolean solution = false;

    public Responses(DataRegisterResponse dataRegisterResponse){
        this.message = dataRegisterResponse.message();
        this.solution = dataRegisterResponse.solution();
        this.topic = new Topics(dataRegisterResponse.topic().getId());
        this.author = new Users(dataRegisterResponse.author().getId());
    }
    public void updateData(DataUpdateResponse dataUpdateResponse) {
        if (dataUpdateResponse.message() != null) {
            this.message = dataUpdateResponse.message();
        }
        if (dataUpdateResponse.solution() != null) {
            this.solution = dataUpdateResponse.solution();
        }
        if (dataUpdateResponse.topic() != null) {
            this.topic = new Topics(dataUpdateResponse.topic().getId());
        }
        if (dataUpdateResponse.author() != null) {
            this.author = new Users(dataUpdateResponse.author().getId());
        }
    }


}