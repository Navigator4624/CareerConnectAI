package com.careerconnect.Repository;



import com.careerconnect.Entity.PlacementDrive;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InMemoryPlacementDriveRepository implements PlacementDriveRepository {

    private final Map<String, PlacementDrive> drives = new HashMap<>();

    @Override
    public PlacementDrive save(PlacementDrive drive) {
        drives.put(drive.getDriveId(), drive);
        return drive;
    }

    @Override
    public Optional<PlacementDrive> findById(String driveId) {
        return Optional.ofNullable(drives.get(driveId));
    }

    @Override
    public List<PlacementDrive> findAll() {
        return new ArrayList<>(drives.values());
    }

    @Override
    public void deleteById(String driveId) {
        drives.remove(driveId);
    }
}