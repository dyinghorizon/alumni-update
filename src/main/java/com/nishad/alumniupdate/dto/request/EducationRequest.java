package com.nishad.alumniupdate.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import lombok.Data;
import java.time.Year;

@Data
public class EducationRequest {
    @NotBlank(message = "Degree is required")
    private String degree;

    @NotBlank(message = "College name is required")
    private String collegeName;

    @NotBlank(message = "Address is required")
    private String address;

    @NotNull(message = "Joining year is required")
    @Min(value = 1900, message = "Invalid joining year")
    @Max(value = 2100, message = "Invalid joining year")
    private Integer joiningYear;

    @NotNull(message = "Passing year is required")
    @Min(value = 1900, message = "Invalid passing year")
    @Max(value = 2100, message = "Invalid passing year")
    private Integer passingYear;
}
