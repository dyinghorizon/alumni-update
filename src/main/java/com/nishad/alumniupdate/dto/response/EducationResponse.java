package com.nishad.alumniupdate.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EducationResponse {
    private Long id;
    private String degree;
    private String collegeName;
    private String address;
    private Integer joiningYear;
    private Integer passingYear;
}
