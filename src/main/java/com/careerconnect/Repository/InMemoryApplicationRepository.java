package com.careerconnect.Repository;



import com.careerconnect.Entity.Application;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class InMemoryApplicationRepository implements ApplicationRepository {

    private final Map<String, Application> applications = new HashMap<>();

    @Override
    public Application save(Application application) {
        applications.put(application.getApplicationId(), application);
        return application;
    }

    @Override
    public Optional<Application> findById(String applicationId) {
        return Optional.ofNullable(applications.get(applicationId));
    }

    @Override
    public List<Application> findAll() {
        return new ArrayList<>(applications.values());
    }

    @Override
    public List<Application> findByStudentId(String studentId) {
        return applications.values()
                .stream()
                .filter(application -> application.getStudentId().equals(studentId))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Application> findByStudentIdAndDriveId(String studentId, String driveId) {
        return applications.values()
                .stream()
                .filter(application ->
                        application.getStudentId().equals(studentId)
                                && application.getDriveId().equals(driveId))
                .findFirst();
    }

    @Override
    public void deleteById(String applicationId) {
        applications.remove(applicationId);
    }
}
