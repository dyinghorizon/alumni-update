package com.nishad.alumniupdate.repository;

import com.nishad.alumniupdate.entity.Alumni;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AlumniRepository extends JpaRepository<Alumni, Long> {
    Optional<Alumni> findByEmail(String email);
    boolean existsByEmail(String email);

    @Query("SELECT a FROM Alumni a LEFT JOIN FETCH a.educations LEFT JOIN FETCH a.organizations " +
            "WHERE a.id = :alumniId")
    Optional<Alumni> findByIdWithDetails(@Param("alumniId") Long alumniId);

    Optional<Alumni> findByStudentStudentId(String studentId);

    // In AlumniRepository.java - Add this method
    @Query("SELECT a FROM Alumni a LEFT JOIN FETCH a.educations WHERE a.email = :email")
    Optional<Alumni> findByEmailWithEducations(@Param("email") String email);

    // In AlumniRepository.java - Add this method
    @Query("SELECT a FROM Alumni a LEFT JOIN FETCH a.organizations o LEFT JOIN FETCH o.organization WHERE a.email = :email")
    Optional<Alumni> findByEmailWithOrganizations(@Param("email") String email);
}
