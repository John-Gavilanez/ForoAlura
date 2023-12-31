package com.alura.controller;

import com.alura.domain.courses.CoursesRepository;
import com.alura.domain.topics.*;
import com.alura.domain.users.UsersRepository;
import com.alura.infra.errors.HandleJson;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/topicos")
public class TopicsController {

    private final TopicsRepository topicsRepository;
    private final UsersRepository usersRepository;
    private final CoursesRepository coursesRepository;

    public TopicsController(TopicsRepository topicsRepository, UsersRepository usersRepository, CoursesRepository coursesRepository) {
        this.topicsRepository = topicsRepository;
        this.usersRepository = usersRepository;
        this.coursesRepository = coursesRepository;
    }


    @GetMapping
    public ResponseEntity<Page<DataListTopic>> getTopics(Pageable pageable) {
        return ResponseEntity.ok(topicsRepository.findAll(pageable).map(DataListTopic::new));
    }

    @PostMapping
    public ResponseEntity<DataListTopic> saveTopic(@RequestBody @Valid DataRegisterTopic dataRegisterTopic, UriComponentsBuilder uriComponentsBuilder) {

        if (!usersRepository.existsById(dataRegisterTopic.author().getId()))
            return new ResponseEntity(new HandleJson().withMessage("USER_NOT_FOUND"), HttpStatus.NOT_FOUND);

        if (!coursesRepository.existsById(dataRegisterTopic.course().getId()))
            return new ResponseEntity(new HandleJson().withMessage("COURSE_NOT_FOUND"), HttpStatus.NOT_FOUND);

        Topics topics = topicsRepository.save(new Topics(dataRegisterTopic));
        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topics.getId()).toUri();

        return ResponseEntity.created(url).body(new DataListTopic(topics));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponseTopic> getTopic(@PathVariable Long id) {
        if (!topicsRepository.existsById(id))
            return new ResponseEntity(new HandleJson().withMessage("TOPIC_NOT_FOUND"), HttpStatus.NOT_FOUND);
        Topics topic = topicsRepository.getReferenceById(id);
        return ResponseEntity.ok(new DataResponseTopic(topic));
    }

    @PutMapping()
    @Transactional
    public ResponseEntity<DataResponseTopic> updateTopic(@RequestBody @Valid DataUpdateTopic dataUpdateTopic) {
        if (!topicsRepository.existsById(dataUpdateTopic.id()))
            return new ResponseEntity(new HandleJson().withMessage("TOPIC_NOT_FOUND"), HttpStatus.NOT_FOUND);

        if (!usersRepository.existsById(dataUpdateTopic.author().getId()))
            return new ResponseEntity(new HandleJson().withMessage("USER_NOT_FOUND"), HttpStatus.NOT_FOUND);

        if (!coursesRepository.existsById(dataUpdateTopic.course().getId()))
            return new ResponseEntity(new HandleJson().withMessage("COURSE_NOT_FOUND"), HttpStatus.NOT_FOUND);

        Topics topic = topicsRepository.getReferenceById(dataUpdateTopic.id());
        topic.updateData(dataUpdateTopic);
        return ResponseEntity.ok(new DataResponseTopic(topic));
    }


    //LOGICAL DELETE
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteTopic(@PathVariable Long id) {
        if (!topicsRepository.existsById(id))
            return new ResponseEntity(new HandleJson().withMessage("TOPIC_NOT_FOUND"), HttpStatus.NOT_FOUND);
        Topics topics = topicsRepository.getReferenceById(id);
        topicsRepository.delete(topics);
        return ResponseEntity.noContent().build();

    }
}
