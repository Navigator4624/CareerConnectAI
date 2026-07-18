package com.careerconnect.Service;

import com.careerconnect.Dto.ApplicationRequest;
import com.careerconnect.Dto.ApplicationResponse;
import com.careerconnect.Entity.Application;
import com.careerconnect.Entity.EligibilityResult;
import com.careerconnect.Entity.PlacementDrive;
import com.careerconnect.Enums.ApplicationStatus;
import com.careerconnect.Repository.ApplicationRepository;
import com.careerconnect.Repository.PlacementDriveRepository;
import com.careerconnect.Repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final StudentRepository studentRepository;
    private final PlacementDriveRepository placementDriveRepository;
    private final EligibilityService eligibilityService;

    public ApplicationService(ApplicationRepository applicationRepository,
                              StudentRepository studentRepository,
                              PlacementDriveRepository placementDriveRepository,
                              EligibilityService eligibilityService) {

        this.applicationRepository = applicationRepository;
        this.studentRepository = studentRepository;
        this.placementDriveRepository = placementDriveRepository;
        this.eligibilityService = eligibilityService;
    }

    // Apply for Placement Drive
    public ApplicationResponse applyForDrive(String driveId,
                                             ApplicationRequest request) {

        studentRepository.findById(request.getStudentId())
                .orElseThrow(() ->
                        new RuntimeException("Student not found."));

        PlacementDrive drive = placementDriveRepository.findById(driveId)
                .orElseThrow(() ->
                        new RuntimeException("Placement Drive not found."));

        // Check deadline
        if (drive.getDeadline().isBefore(LocalDate.now())) {
            throw new RuntimeException("Application deadline has expired.");
        }

        // Prevent duplicate application
        applicationRepository
                .findByStudentIdAndDriveId(request.getStudentId(), driveId)
                .ifPresent(app -> {
                    throw new RuntimeException(
                            "Student has already applied for this drive.");
                });

        // Check Eligibility
        EligibilityResult result =
                eligibilityService.checkEligibility(
                        request.getStudentId(),
                        driveId);

        if (!result.isEligible()) {
            throw new RuntimeException(
                    "Student is not eligible. Reasons : "
                            + result.getReasons());
        }

        Application application = Application.builder()
                .applicationId("APP-" +
                        UUID.randomUUID().toString().substring(0, 8))
                .studentId(request.getStudentId())
                .driveId(driveId)
                .submittedAt(LocalDateTime.now())
                .applicationStatus(ApplicationStatus.SUBMITTED)
                .build();

        applicationRepository.save(application);

        return mapToResponse(application);
    }

    // Get Application By Id
    public ApplicationResponse getApplicationById(String applicationId) {

        Application application =
                applicationRepository.findById(applicationId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Application not found."));

        return mapToResponse(application);
    }

    // Get Applications By Student
    public List<ApplicationResponse> getApplicationsByStudent(
            String studentId) {

        return applicationRepository.findByStudentId(studentId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Get All Applications
    public List<ApplicationResponse> getAllApplications() {

        return applicationRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Update Application Status
    public ApplicationResponse updateStatus(String applicationId,
                                            ApplicationStatus status) {

        Application application =
                applicationRepository.findById(applicationId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Application not found."));

        ApplicationStatus current =
                application.getApplicationStatus();

        switch (current) {

            case SUBMITTED:
                if (status != ApplicationStatus.UNDER_REVIEW
                        && status != ApplicationStatus.WITHDRAWN) {
                    throw new RuntimeException(
                            "Invalid status transition.");
                }
                break;

            case UNDER_REVIEW:
                if (status != ApplicationStatus.SHORTLISTED
                        && status != ApplicationStatus.REJECTED
                        && status != ApplicationStatus.WITHDRAWN) {
                    throw new RuntimeException(
                            "Invalid status transition.");
                }
                break;

            case SHORTLISTED:
                if (status != ApplicationStatus.SELECTED
                        && status != ApplicationStatus.REJECTED) {
                    throw new RuntimeException(
                            "Invalid status transition.");
                }
                break;

            case SELECTED:
            case REJECTED:
            case WITHDRAWN:
                throw new RuntimeException(
                        "Application status cannot be changed.");
        }

        application.setApplicationStatus(status);

        applicationRepository.save(application);

        return mapToResponse(application);
    }

    // Delete Application
    public String deleteApplication(String applicationId) {

        applicationRepository.findById(applicationId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Application not found."));

        applicationRepository.deleteById(applicationId);

        return "Application deleted successfully.";
    }

    // Entity -> DTO
    private ApplicationResponse mapToResponse(
            Application application) {

        return ApplicationResponse.builder()
                .applicationId(application.getApplicationId())
                .studentId(application.getStudentId())
                .driveId(application.getDriveId())
                .submittedAt(application.getSubmittedAt())
                .applicationStatus(
                        application.getApplicationStatus())
                .build();
    }
}
