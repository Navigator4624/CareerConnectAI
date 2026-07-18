package com.careerconnect.Service;



import com.careerconnect.Dto.PlacementDriveRequest;
import com.careerconnect.Dto.PlacementDriveResponse;
import com.careerconnect.Entity.Company;
import com.careerconnect.Entity.PlacementDrive;
import com.careerconnect.Enums.DriveStatus;
import com.careerconnect.Repository.CompanyRepository;
import com.careerconnect.Repository.PlacementDriveRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PlacementDriveService {

    private final PlacementDriveRepository placementDriveRepository;
    private final CompanyRepository companyRepository;

    public PlacementDriveService(PlacementDriveRepository placementDriveRepository,
                                 CompanyRepository companyRepository) {
        this.placementDriveRepository = placementDriveRepository;
        this.companyRepository = companyRepository;
    }

    // Create Placement Drive
    public PlacementDriveResponse createPlacementDrive(PlacementDriveRequest request) {

        // Check Company Exists
        Company company = companyRepository.findById(request.getCompanyId())
                .orElseThrow(() ->
                        new RuntimeException("Company not found with ID : " + request.getCompanyId()));

        // Deadline Validation
        if (request.getDeadline().isBefore(LocalDate.now())) {
            throw new RuntimeException("Deadline cannot be before today's date.");
        }

        PlacementDrive drive = PlacementDrive.builder()
                .driveId("DRV-" + UUID.randomUUID().toString().substring(0, 8))
                .companyId(company.getCompanyId())
                .role(request.getRole())
                .location(request.getLocation())
                .packageOffered(request.getPackageOffered())
                .createdDate(LocalDate.now())
                .deadline(request.getDeadline())
                .requiredSkills(request.getRequiredSkills())
                .minimumCgpa(request.getMinimumCgpa())
                .maximumBacklogs(request.getMaximumBacklogs())
                .eligibleProgramme(request.getEligibleProgramme())
                .graduationYear(request.getGraduationYear())
                .driveStatus(DriveStatus.OPEN)
                .build();

        placementDriveRepository.save(drive);

        return mapToResponse(drive);
    }

    // Get Drive By Id
    public PlacementDriveResponse getPlacementDriveById(String driveId) {

        PlacementDrive drive = placementDriveRepository.findById(driveId)
                .orElseThrow(() ->
                        new RuntimeException("Placement Drive not found."));

        return mapToResponse(drive);
    }

    // Get All Drives
    public List<PlacementDriveResponse> getAllPlacementDrives() {

        return placementDriveRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Update Placement Drive
    public PlacementDriveResponse updatePlacementDrive(String driveId,
                                                       PlacementDriveRequest request) {

        PlacementDrive drive = placementDriveRepository.findById(driveId)
                .orElseThrow(() ->
                        new RuntimeException("Placement Drive not found."));

        companyRepository.findById(request.getCompanyId())
                .orElseThrow(() ->
                        new RuntimeException("Company not found."));

        if (request.getDeadline().isBefore(LocalDate.now())) {
            throw new RuntimeException("Deadline cannot be before today's date.");
        }

        drive.setCompanyId(request.getCompanyId());
        drive.setRole(request.getRole());
        drive.setLocation(request.getLocation());
        drive.setPackageOffered(request.getPackageOffered());
        drive.setDeadline(request.getDeadline());
        drive.setRequiredSkills(request.getRequiredSkills());
        drive.setMinimumCgpa(request.getMinimumCgpa());
        drive.setMaximumBacklogs(request.getMaximumBacklogs());
        drive.setEligibleProgramme(request.getEligibleProgramme());
        drive.setGraduationYear(request.getGraduationYear());

        placementDriveRepository.save(drive);

        return mapToResponse(drive);
    }

    // Close Placement Drive
    public PlacementDriveResponse closePlacementDrive(String driveId) {

        PlacementDrive drive = placementDriveRepository.findById(driveId)
                .orElseThrow(() ->
                        new RuntimeException("Placement Drive not found."));

        drive.setDriveStatus(DriveStatus.CLOSED);

        placementDriveRepository.save(drive);

        return mapToResponse(drive);
    }

    // Delete Placement Drive
    public String deletePlacementDrive(String driveId) {

        placementDriveRepository.findById(driveId)
                .orElseThrow(() ->
                        new RuntimeException("Placement Drive not found."));

        placementDriveRepository.deleteById(driveId);

        return "Placement Drive deleted successfully.";
    }

    // Entity -> DTO Mapping
    private PlacementDriveResponse mapToResponse(PlacementDrive drive) {

        return PlacementDriveResponse.builder()
                .driveId(drive.getDriveId())
                .companyId(drive.getCompanyId())
                .role(drive.getRole())
                .location(drive.getLocation())
                .packageOffered(drive.getPackageOffered())
                .createdDate(drive.getCreatedDate())
                .deadline(drive.getDeadline())
                .requiredSkills(drive.getRequiredSkills())
                .minimumCgpa(drive.getMinimumCgpa())
                .maximumBacklogs(drive.getMaximumBacklogs())
                .eligibleProgramme(drive.getEligibleProgramme())
                .graduationYear(drive.getGraduationYear())
                .driveStatus(drive.getDriveStatus())
                .build();
    }
}
