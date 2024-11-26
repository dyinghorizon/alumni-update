package com.nishad.alumniupdate.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;

@Data
public class OrganizationRequest {
    @NotNull(message = "Organization ID is required")
    private Long organizationId;

    @NotBlank(message = "Position is required")
    private String position;

    @NotNull(message = "Joining date is required")
    private LocalDate joiningDate;

    private LocalDate leavingDate;  // Optional, null if currently working
}
