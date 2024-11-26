package com.nishad.alumniupdate.dto.response;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class AlumniResponse {
    private Long id;
    private String studentId;
    private String firstName;
    private String lastName;
    private String email;
    private String contactNumber;
    private Integer graduationYear;
    private List<EducationResponse> educations;
    private List<OrganizationResponse> organizations;
}

