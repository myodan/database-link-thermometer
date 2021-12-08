package kr.djsch.krdhs.dlt.repository;

import kr.djsch.krdhs.dlt.model.Student;
import kr.djsch.krdhs.dlt.model.Temperature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TemperatureRepository extends JpaRepository<Temperature, Long> {
    List<Temperature> findTop20ByStudentOrderByDateDesc(Student student);
}
