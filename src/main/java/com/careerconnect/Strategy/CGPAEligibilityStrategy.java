package com.careerconnect.Strategy;



import com.careerconnect.Entity.PlacementDrive;
import com.careerconnect.Entity.Student;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CGPAEligibilityStrategy implements EligibilityPolicy {

    @Override
    public void checkEligibility(Student student,
                                 PlacementDrive drive,
                                 List<String> reasons) {

        if (student.getCgpa() < drive.getMinimumCgpa()) {

            reasons.add(
                "CGPA must be at least "
                + drive.getMinimumCgpa()
            );

        }
    }
}
