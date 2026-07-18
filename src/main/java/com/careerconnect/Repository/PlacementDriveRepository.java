package com.careerconnect.Repository;



import com.careerconnect.Entity.PlacementDrive;

import java.util.List;
import java.util.Optional;

public interface PlacementDriveRepository {

    PlacementDrive save(PlacementDrive drive);

    Optional<PlacementDrive> findById(String driveId);

    List<PlacementDrive> findAll();

    void deleteById(String driveId);
}
