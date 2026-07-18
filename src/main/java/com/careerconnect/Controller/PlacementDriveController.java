package com.careerconnect.Controller;



import com.careerconnect.Dto.PlacementDriveRequest;
import com.careerconnect.Dto.PlacementDriveResponse;
import com.careerconnect.Service.PlacementDriveService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drives")
public class PlacementDriveController {

    private final PlacementDriveService placementDriveService;

    public PlacementDriveController(PlacementDriveService placementDriveService) {
        this.placementDriveService = placementDriveService;
    }

    // Create Placement Drive
    @PostMapping
    public ResponseEntity<PlacementDriveResponse> createPlacementDrive(
            @Valid @RequestBody PlacementDriveRequest request) {

        PlacementDriveResponse response =
                placementDriveService.createPlacementDrive(request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Get Placement Drive By ID
    @GetMapping("/{driveId}")
    public ResponseEntity<PlacementDriveResponse> getPlacementDriveById(
            @PathVariable String driveId) {

        PlacementDriveResponse response =
                placementDriveService.getPlacementDriveById(driveId);

        return ResponseEntity.ok(response);
    }

    // Get All Placement Drives
    @GetMapping
    public ResponseEntity<List<PlacementDriveResponse>> getAllPlacementDrives() {

        List<PlacementDriveResponse> drives =
                placementDriveService.getAllPlacementDrives();

        return ResponseEntity.ok(drives);
    }

    // Update Placement Drive
    @PutMapping("/{driveId}")
    public ResponseEntity<PlacementDriveResponse> updatePlacementDrive(
            @PathVariable String driveId,
            @Valid @RequestBody PlacementDriveRequest request) {

        PlacementDriveResponse response =
                placementDriveService.updatePlacementDrive(driveId, request);

        return ResponseEntity.ok(response);
    }

    // Close Placement Drive
    @PutMapping("/{driveId}/close")
    public ResponseEntity<PlacementDriveResponse> closePlacementDrive(
            @PathVariable String driveId) {

        PlacementDriveResponse response =
                placementDriveService.closePlacementDrive(driveId);

        return ResponseEntity.ok(response);
    }

    // Delete Placement Drive
    @DeleteMapping("/{driveId}")
    public ResponseEntity<String> deletePlacementDrive(
            @PathVariable String driveId) {

        String message =
                placementDriveService.deletePlacementDrive(driveId);

        return ResponseEntity.ok(message);
    }
}