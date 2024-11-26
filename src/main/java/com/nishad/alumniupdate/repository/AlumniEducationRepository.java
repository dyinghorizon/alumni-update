package com.nishad.alumniupdate.repository;

import com.nishad.alumniupdate.entity.AlumniEducation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface AlumniEducationRepository extends JpaRepository<AlumniEducation, Long> {
    List<AlumniEducation> findByAlumniIdOrderByPassingYearDesc(Long alumniId);
    Optional<AlumniEducation> findByIdAndAlumniId(Long id, Long alumniId);
    void deleteByIdAndAlumniId(Long id, Long alumniId);
}