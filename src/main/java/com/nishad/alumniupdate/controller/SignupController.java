package com.nishad.alumniupdate.controller;

import com.nishad.alumniupdate.dto.request.SignupRequest;
import com.nishad.alumniupdate.dto.response.AlumniResponse;
import com.nishad.alumniupdate.service.AlumniService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/signup")
@RequiredArgsConstructor
public class SignupController {

    private final AlumniService alumniService;

    @PostMapping
    public ResponseEntity<AlumniResponse> registerAlumni(@Valid @RequestBody SignupRequest request) {
        return ResponseEntity.ok(alumniService.signup(request));
    }
}
