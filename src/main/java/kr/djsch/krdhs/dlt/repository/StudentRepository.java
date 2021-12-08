package kr.djsch.krdhs.dlt.repository;

import kr.djsch.krdhs.dlt.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("select count(distinct s.grade) from Student s")
    int countDistinctGrade();

    @Query("select count(distinct s.group) from Student s")
    int countDistinctGroup();

    Page<Student> findByGrade(long grade, Pageable pageable);

    Page<Student> findByGroup(long group, Pageable pageable);

    Page<Student> findByGradeAndGroup(long grade, long group, Pageable pageable);
}
