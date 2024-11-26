package com.nishad.alumniupdate.repository;

import com.nishad.alumniupdate.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    List<Organization> findAllByOrderByNameAsc();
}

