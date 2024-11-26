package com.nishad.alumniupdate.service;

import com.nishad.alumniupdate.dto.request.AlumniUpdateRequest;
import com.nishad.alumniupdate.dto.request.EducationRequest;
import com.nishad.alumniupdate.dto.request.OrganizationRequest;
import com.nishad.alumniupdate.dto.request.SignupRequest;
import com.nishad.alumniupdate.dto.response.AlumniResponse;
import com.nishad.alumniupdate.dto.response.EducationResponse;
import com.nishad.alumniupdate.dto.response.OrganizationResponse;
import com.nishad.alumniupdate.entity.*;
import com.nishad.alumniupdate.exception.ResourceNotFoundException;
import com.nishad.alumniupdate.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AlumniServiceImpl implements AlumniService {

    private final AlumniRepository alumniRepository;
    private final StudentRepository studentRepository;
    private final OrganizationRepository organizationRepository;
    private final AlumniEducationRepository educationRepository;
    private final AlumniOrganizationRepository alumniOrganizationRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Alumni alumni = alumniRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        return org.springframework.security.core.userdetails.User
                .withUsername(alumni.getEmail())
                .password(alumni.getPassword())
                .authorities("ROLE_ALUMNI")
                .build();
    }

    @Override
    @Transactional
    public AlumniResponse signup(SignupRequest request) {
        // Verify student exists
        Student student = studentRepository.findByStudentId(request.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        // Check if already registered
        if (alumniRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already registered");
        }

        // Create alumni
        Alumni alumni = new Alumni();
        alumni.setStudent(student);
        alumni.setEmail(request.getEmail());
        alumni.setContactNumber(request.getContactNumber());
        alumni.setPassword(passwordEncoder.encode(request.getPassword()));

        Alumni savedAlumni = alumniRepository.save(alumni);
        return convertToAlumniResponse(savedAlumni);
    }

    @Override
    @Transactional(readOnly = true)
    public AlumniResponse getCurrentAlumniProfile() {
        return convertToAlumniResponse(getCurrentAlumni());
    }

    @Override
    @Transactional
    public AlumniResponse updateAlumniProfile(AlumniUpdateRequest request) {
        Alumni alumni = getCurrentAlumni();

        if (!alumni.getEmail().equals(request.getEmail()) &&
                alumniRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        alumni.setEmail(request.getEmail());
        alumni.setContactNumber(request.getContactNumber());

        Alumni updatedAlumni = alumniRepository.save(alumni);
        return convertToAlumniResponse(updatedAlumni);
    }

//    @Override
//    @Transactional(readOnly = true)
//    public List<EducationResponse> getAllEducation() {
//        return getCurrentAlumni().getEducations().stream()
//                .map(this::convertToEducationResponse)
//                .collect(Collectors.toList());
//    }

    @Override
    @Transactional(readOnly = true)
    public List<EducationResponse> getAllEducation() {
        Alumni alumni = getCurrentAlumni();
        List<AlumniEducation> educations = educationRepository.findByAlumniIdOrderByPassingYearDesc(alumni.getId());
        return educations.stream()
                .map(this::convertToEducationResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public EducationResponse addEducation(EducationRequest request) {
        Alumni alumni = getCurrentAlumni();

        AlumniEducation education = new AlumniEducation();
        education.setAlumni(alumni);
        education.setDegree(request.getDegree());
        education.setCollegeName(request.getCollegeName());
        education.setAddress(request.getAddress());
        education.setJoiningYear(request.getJoiningYear());
        education.setPassingYear(request.getPassingYear());

        AlumniEducation savedEducation = educationRepository.save(education);
        return convertToEducationResponse(savedEducation);
    }

    @Override
    @Transactional
    public EducationResponse updateEducation(Long educationId, EducationRequest request) {
        AlumniEducation education = educationRepository.findByIdAndAlumniId(
                        educationId, getCurrentAlumni().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Education record not found"));

        education.setDegree(request.getDegree());
        education.setCollegeName(request.getCollegeName());
        education.setAddress(request.getAddress());
        education.setJoiningYear(request.getJoiningYear());
        education.setPassingYear(request.getPassingYear());

        AlumniEducation updatedEducation = educationRepository.save(education);
        return convertToEducationResponse(updatedEducation);
    }

    @Override
    @Transactional
    public void deleteEducation(Long educationId) {
        educationRepository.deleteByIdAndAlumniId(educationId, getCurrentAlumni().getId());
    }

//    @Override
//    @Transactional(readOnly = true)
//    public List<OrganizationResponse> getAllOrganizations() {
//        return getCurrentAlumni().getOrganizations().stream()
//                .map(this::convertToOrganizationResponse)
//                .collect(Collectors.toList());
//    }

    @Override
    @Transactional(readOnly = true)
    public List<OrganizationResponse> getAllOrganizations() {
        Alumni alumni = getCurrentAlumni();
        List<AlumniOrganization> organizations = alumniOrganizationRepository
                .findByAlumniIdOrderByJoiningDateDesc(alumni.getId());
        return organizations.stream()
                .map(this::convertToOrganizationResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public OrganizationResponse addOrganization(OrganizationRequest request) {
        Alumni alumni = getCurrentAlumni();
        Organization organization = organizationRepository.findById(request.getOrganizationId())
                .orElseThrow(() -> new ResourceNotFoundException("Organization not found"));

        AlumniOrganization alumniOrg = new AlumniOrganization();
        alumniOrg.setAlumni(alumni);
        alumniOrg.setOrganization(organization);
        alumniOrg.setPosition(request.getPosition());
        alumniOrg.setJoiningDate(request.getJoiningDate());
        alumniOrg.setLeavingDate(request.getLeavingDate());

        AlumniOrganization savedOrg = alumniOrganizationRepository.save(alumniOrg);
        return convertToOrganizationResponse(savedOrg);
    }

    @Override
    @Transactional
    public OrganizationResponse updateOrganization(Long organizationId, OrganizationRequest request) {
        AlumniOrganization alumniOrg = alumniOrganizationRepository
                .findByIdAndAlumniId(organizationId, getCurrentAlumni().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Organization record not found"));

        alumniOrg.setPosition(request.getPosition());
        alumniOrg.setJoiningDate(request.getJoiningDate());
        alumniOrg.setLeavingDate(request.getLeavingDate());

        AlumniOrganization updatedOrg = alumniOrganizationRepository.save(alumniOrg);
        return convertToOrganizationResponse(updatedOrg);
    }

    @Override
    @Transactional
    public void deleteOrganization(Long organizationId) {
        alumniOrganizationRepository.deleteByIdAndAlumniId(organizationId, getCurrentAlumni().getId());
    }

    // Helper methods
    private Alumni getCurrentAlumni() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return alumniRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    private AlumniResponse convertToAlumniResponse(Alumni alumni) {
        return AlumniResponse.builder()
                .id(alumni.getId())
                .studentId(alumni.getStudent().getStudentId())
                .firstName(alumni.getStudent().getFirstName())
                .lastName(alumni.getStudent().getLastName())
                .email(alumni.getEmail())
                .contactNumber(alumni.getContactNumber())
                .graduationYear(alumni.getStudent().getGraduationYear())
                .educations(alumni.getEducations().stream()
                        .map(this::convertToEducationResponse)
                        .collect(Collectors.toList()))
                .organizations(alumni.getOrganizations().stream()
                        .map(this::convertToOrganizationResponse)
                        .collect(Collectors.toList()))
                .build();
    }

    private EducationResponse convertToEducationResponse(AlumniEducation education) {
        return EducationResponse.builder()
                .id(education.getId())
                .degree(education.getDegree())
                .collegeName(education.getCollegeName())
                .address(education.getAddress())
                .joiningYear(education.getJoiningYear())
                .passingYear(education.getPassingYear())
                .build();
    }

    private OrganizationResponse convertToOrganizationResponse(AlumniOrganization org) {
        return OrganizationResponse.builder()
                .id(org.getId())
                .organizationId(org.getOrganization().getId())
                .organizationName(org.getOrganization().getName())
                .organizationAddress(org.getOrganization().getAddress())
                .position(org.getPosition())
                .joiningDate(org.getJoiningDate())
                .leavingDate(org.getLeavingDate())
                .build();
    }
}