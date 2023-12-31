package com.alura.domain.topics;

import com.alura.domain.courses.Courses;
import com.alura.domain.responses.Responses;
import com.alura.domain.users.Users;
import com.alura.model.StatusTopico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "topics")
@Entity(name = "Topics")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Topics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "message")
    private String message;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusTopico status = StatusTopico.NO_RESPONDIDO;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Users author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Courses course;

    //@OneToMany(mappedBy = "topic", fetch = FetchType.LAZY)
    //private Set<Responses> responses = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "topic", fetch = FetchType.LAZY)
    private List<Responses> responses;

    public Topics(Long id) {
        this.id = id;
    }

    public Topics(DataRegisterTopic dataRegisterTopic) {
        this.title = dataRegisterTopic.title();
        this.message = dataRegisterTopic.message();
        this.status = dataRegisterTopic.status();
        this.author = new Users(dataRegisterTopic.author().getId());
        this.course = new Courses(dataRegisterTopic.course().getId());
    }

    public void updateData(DataUpdateTopic dataUpdateTopic) {
        if (dataUpdateTopic.title() != null) {
            this.title = dataUpdateTopic.title();
        }
        if (dataUpdateTopic.message() != null) {
            this.message = dataUpdateTopic.message();
        }
        if (dataUpdateTopic.status() != null) {
            this.status = dataUpdateTopic.status();
        }
        if (dataUpdateTopic.author() != null) {
            this.author = new Users(dataUpdateTopic.author().getId());
        }
        if (dataUpdateTopic.course() != null) {
            this.course = new Courses(dataUpdateTopic.course().getId());
        }
    }


}