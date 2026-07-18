package com.careerconnect.Strategy;

import com.careerconnect.Entity.PlacementDrive;
import com.careerconnect.Entity.Student;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GraduationYearEligibilityStrategy implements EligibilityPolicy {

    @Override
    public void checkEligibility(Student student,
                                 PlacementDrive drive,
                                 List<String> reasons) {

        if (student.getGraduationYear() != drive.getGraduationYear()) {

            reasons.add(
                    "Graduation year must be "
                    + drive.getGraduationYear()
            );
        }
    }
}