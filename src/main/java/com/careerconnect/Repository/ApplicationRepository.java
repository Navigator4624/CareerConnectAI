package com.careerconnect.Repository;



import com.careerconnect.Entity.Application;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepository {

    Application save(Application application);

    Optional<Application> findById(String applicationId);

    List<Application> findAll();

    List<Application> findByStudentId(String studentId);

    Optional<Application> findByStudentIdAndDriveId(String studentId, String driveId);

    void deleteById(String applicationId);
}
