package com.careerconnect.Strategy;

import com.careerconnect.Entity.PlacementDrive;
import com.careerconnect.Entity.Student;

import java.util.List;

public interface EligibilityPolicy {

    void checkEligibility(Student student,
                          PlacementDrive drive,
                          List<String> reasons);

}
