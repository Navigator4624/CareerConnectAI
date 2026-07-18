package com.careerconnect.Controller;


import com.careerconnect.Dto.ApplicationRequest;
import com.careerconnect.Dto.ApplicationResponse;
import com.careerconnect.Enums.ApplicationStatus;
import com.careerconnect.Service.ApplicationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    // Apply for a Placement Drive
    @PostMapping("/drives/{driveId}")
    public ResponseEntity<ApplicationResponse> applyForDrive(
            @PathVariable String driveId,
            @Valid @RequestBody ApplicationRequest request) {

        ApplicationResponse response =
                applicationService.applyForDrive(driveId, request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Get Application By ID
    @GetMapping("/{applicationId}")
    public ResponseEntity<ApplicationResponse> getApplicationById(
            @PathVariable String applicationId) {

        ApplicationResponse response =
                applicationService.getApplicationById(applicationId);

        return ResponseEntity.ok(response);
    }

    // Get All Applications
    @GetMapping
    public ResponseEntity<List<ApplicationResponse>> getAllApplications() {

        List<ApplicationResponse> applications =
                applicationService.getAllApplications();

        return ResponseEntity.ok(applications);
    }

    // Get Applications By Student
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<ApplicationResponse>> getApplicationsByStudent(
            @PathVariable String studentId) {

        List<ApplicationResponse> responses =
                applicationService.getApplicationsByStudent(studentId);

        return ResponseEntity.ok(responses);
    }

    // Update Application Status
    @PutMapping("/{applicationId}/status")
    public ResponseEntity<ApplicationResponse> updateApplicationStatus(
            @PathVariable String applicationId,
            @RequestParam ApplicationStatus status) {

        ApplicationResponse response =
                applicationService.updateStatus(applicationId, status);

        return ResponseEntity.ok(response);
    }

    // Delete Application
    @DeleteMapping("/{applicationId}")
    public ResponseEntity<String> deleteApplication(
            @PathVariable String applicationId) {

        String message =
                applicationService.deleteApplication(applicationId);

        return ResponseEntity.ok(message);
    }
}
