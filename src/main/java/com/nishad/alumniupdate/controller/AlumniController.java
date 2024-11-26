package com.nishad.alumniupdate.controller;

import com.nishad.alumniupdate.dto.request.AlumniUpdateRequest;
import com.nishad.alumniupdate.dto.request.EducationRequest;
import com.nishad.alumniupdate.dto.request.OrganizationRequest;
import com.nishad.alumniupdate.dto.response.AlumniResponse;
import com.nishad.alumniupdate.dto.response.EducationResponse;
import com.nishad.alumniupdate.dto.response.OrganizationResponse;
import com.nishad.alumniupdate.service.AlumniService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/alumni")
@RequiredArgsConstructor
public class AlumniController {

    private final AlumniService alumniService;

    // Profile endpoints
    @GetMapping("/profile")
    public ResponseEntity<AlumniResponse> getProfile() {
        return ResponseEntity.ok(alumniService.getCurrentAlumniProfile());
    }

    @PutMapping("/profile")
    public ResponseEntity<AlumniResponse> updateProfile(
            @Valid @RequestBody AlumniUpdateRequest request) {
        return ResponseEntity.ok(alumniService.updateAlumniProfile(request));
    }

    // Education endpoints
    @GetMapping("/education")
    public ResponseEntity<List<EducationResponse>> getAllEducation() {
        return ResponseEntity.ok(alumniService.getAllEducation());
    }

    @PostMapping("/education")
    public ResponseEntity<EducationResponse> addEducation(
            @Valid @RequestBody EducationRequest request) {
        return ResponseEntity.ok(alumniService.addEducation(request));
    }

    @PutMapping("/education/{educationId}")
    public ResponseEntity<EducationResponse> updateEducation(
            @PathVariable Long educationId,
            @Valid @RequestBody EducationRequest request) {
        return ResponseEntity.ok(alumniService.updateEducation(educationId, request));
    }

    @DeleteMapping("/education/{educationId}")
    public ResponseEntity<Void> deleteEducation(@PathVariable Long educationId) {
        alumniService.deleteEducation(educationId);
        return ResponseEntity.noContent().build();
    }

    // Organization endpoints
    @GetMapping("/organizations")
    public ResponseEntity<List<OrganizationResponse>> getAllOrganizations() {
        return ResponseEntity.ok(alumniService.getAllOrganizations());
    }

    @PostMapping("/organizations")
    public ResponseEntity<OrganizationResponse> addOrganization(
            @Valid @RequestBody OrganizationRequest request) {
        return ResponseEntity.ok(alumniService.addOrganization(request));
    }

    @PutMapping("/organizations/{organizationId}")
    public ResponseEntity<OrganizationResponse> updateOrganization(
            @PathVariable Long organizationId,
            @Valid @RequestBody OrganizationRequest request) {
        return ResponseEntity.ok(alumniService.updateOrganization(organizationId, request));
    }

    @DeleteMapping("/organizations/{organizationId}")
    public ResponseEntity<Void> deleteOrganization(@PathVariable Long organizationId) {
        alumniService.deleteOrganization(organizationId);
        return ResponseEntity.noContent().build();
    }
}
