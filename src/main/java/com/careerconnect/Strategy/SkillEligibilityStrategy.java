package com.careerconnect.Strategy;



import com.careerconnect.Entity.PlacementDrive;
import com.careerconnect.Entity.Student;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SkillEligibilityStrategy implements EligibilityPolicy {

    @Override
    public void checkEligibility(Student student,
                                 PlacementDrive drive,
                                 List<String> reasons) {

        if (drive.getRequiredSkills() == null
                || drive.getRequiredSkills().isEmpty()) {
            return;
        }

        for (String skill : drive.getRequiredSkills()) {

            if (!student.getSkills().contains(skill)) {

                reasons.add(
                        "Missing required skill : " + skill
                );
            }

        }

    }

}
