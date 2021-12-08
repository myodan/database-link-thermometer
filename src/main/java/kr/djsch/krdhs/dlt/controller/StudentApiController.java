package kr.djsch.krdhs.dlt.controller;

import kr.djsch.krdhs.dlt.model.Student;
import kr.djsch.krdhs.dlt.repository.StudentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentApiController {

    private final StudentRepository repository;

    StudentApiController(StudentRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/students")
    List<Student> all() {
        return repository.findAll();
    }

    @GetMapping("/students/{id}")
    Student one(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

}