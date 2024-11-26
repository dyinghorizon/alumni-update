package com.nishad.alumniupdate.service;

import com.nishad.alumniupdate.dto.request.AlumniUpdateRequest;
import com.nishad.alumniupdate.dto.request.EducationRequest;
import com.nishad.alumniupdate.dto.request.OrganizationRequest;
import com.nishad.alumniupdate.dto.request.SignupRequest;
import com.nishad.alumniupdate.dto.response.AlumniResponse;
import com.nishad.alumniupdate.dto.response.EducationResponse;
import com.nishad.alumniupdate.dto.response.OrganizationResponse;
import org.springframework.security.core.userdetails.UserDetailsService;
import java.util.List;

public interface AlumniService extends UserDetailsService {
    // Auth related
    AlumniResponse signup(SignupRequest request);

    // Profile management
    AlumniResponse getCurrentAlumniProfile();
    AlumniResponse updateAlumniProfile(AlumniUpdateRequest request);

    // Education management
    List<EducationResponse> getAllEducation();
    EducationResponse addEducation(EducationRequest request);
    EducationResponse updateEducation(Long educationId, EducationRequest request);
    void deleteEducation(Long educationId);

    // Organization management
    List<OrganizationResponse> getAllOrganizations();
    OrganizationResponse addOrganization(OrganizationRequest request);
    OrganizationResponse updateOrganization(Long organizationId, OrganizationRequest request);
    void deleteOrganization(Long organizationId);
}
