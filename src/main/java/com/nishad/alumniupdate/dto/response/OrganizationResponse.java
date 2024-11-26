package com.nishad.alumniupdate.dto.response;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Data
@Builder
public class OrganizationResponse {
    private Long id;
    private Long organizationId;
    private String organizationName;
    private String organizationAddress;
    private String position;
    private LocalDate joiningDate;
    private LocalDate leavingDate;
}
