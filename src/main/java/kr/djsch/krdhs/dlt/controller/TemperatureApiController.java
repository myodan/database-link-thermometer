package kr.djsch.krdhs.dlt.controller;

import kr.djsch.krdhs.dlt.model.Student;
import kr.djsch.krdhs.dlt.model.Temperature;
import kr.djsch.krdhs.dlt.repository.TemperatureRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TemperatureApiController {

    private final TemperatureRepository repository;

    TemperatureApiController(TemperatureRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/temperature")
    List<Temperature> all() {
        return repository.findAll();
    }

    @PostMapping("/temperature")
    Temperature newTemperature(@RequestBody Temperature newTemperature) {
        return repository.save(newTemperature);
    }

    @GetMapping("/temperature/{id}")
    Temperature one(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @GetMapping("/temperature/student/{student}")
    List<Temperature> studentAll(@PathVariable Student student) {
        return repository.findTop20ByStudentOrderByDateDesc(student);
    }

    @DeleteMapping("/temperature/{id}")
    void deleteTemperature(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
