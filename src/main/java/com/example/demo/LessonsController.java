package com.example.demo;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/lessons")
public class LessonsController {

    private final LessonRepository repository;

    public LessonsController(LessonRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public Iterable<Lesson> all() {
        return this.repository.findAll();
    }

    @PostMapping("")
    public Lesson create(@RequestBody Lesson lesson) {
        return this.repository.save(lesson);
    }

    @GetMapping("/{id}")
    @JsonView(LessonViews.Concise.class)
    public Lesson read(@PathVariable Long id){
        Optional<Lesson> r = this.repository.findById(id);
        return r.get();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        this.repository.delete(this.repository.findById(id).get());
    }

    @PatchMapping("/{id}")
    public Lesson update(
            @PathVariable Long id,
            @RequestBody Lesson lesson){
        Lesson l = read(id);
        l.update(lesson);
        return this.repository.save(l);
    }

}
