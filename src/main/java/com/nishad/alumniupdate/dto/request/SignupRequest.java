package com.nishad.alumniupdate.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class SignupRequest {
    @NotBlank(message = "Student ID is required")
    @Pattern(regexp = "^\\d{4}[A-Z]{2}\\d{2}$", message = "Invalid Student ID format. Example: 2020MT01")
    private String studentId;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "Contact number is required")
    @Pattern(regexp = "^\\+?[1-9][0-9]{9,14}$", message = "Invalid contact number format")
    private String contactNumber;
}
