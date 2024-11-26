package com.nishad.alumniupdate.repository;

import com.nishad.alumniupdate.entity.AlumniOrganization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface AlumniOrganizationRepository extends JpaRepository<AlumniOrganization, Long> {
    @Query("SELECT ao FROM AlumniOrganization ao " +
            "JOIN FETCH ao.organization " +
            "WHERE ao.alumni.id = :alumniId " +
            "ORDER BY ao.joiningDate DESC")
    List<AlumniOrganization> findByAlumniIdOrderByJoiningDateDesc(@Param("alumniId") Long alumniId);

    Optional<AlumniOrganization> findByIdAndAlumniId(Long id, Long alumniId);

    void deleteByIdAndAlumniId(Long id, Long alumniId);

    @Query("SELECT CASE WHEN COUNT(ao) > 0 THEN true ELSE false END " +
            "FROM AlumniOrganization ao " +
            "WHERE ao.alumni.id = :alumniId " +
            "AND ao.organization.id = :organizationId " +
            "AND ao.leavingDate IS NULL")
    boolean existsByAlumniIdAndOrganizationIdAndCurrentlyWorking(
            @Param("alumniId") Long alumniId,
            @Param("organizationId") Long organizationId
    );
}