package com.nishad.alumniupdate.repository;

import com.nishad.alumniupdate.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    Optional<Student> findByEmail(String email);
    Optional<Student> findByStudentId(String studentId);  // Added this method
    boolean existsByEmailAndGraduationYearLessThanEqual(String email, Integer currentYear);
    boolean existsByStudentId(String studentId);
}