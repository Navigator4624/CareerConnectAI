package com.careerconnect.Strategy;


import com.careerconnect.Entity.PlacementDrive;
import com.careerconnect.Entity.Student;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CompositeEligibilityPolicy {

    private final List<EligibilityPolicy> policies;

    public CompositeEligibilityPolicy(
            List<EligibilityPolicy> policies) {

        this.policies = policies;
    }

    public List<String> validate(Student student,
                                 PlacementDrive drive) {

        List<String> reasons = new ArrayList<>();

        for (EligibilityPolicy policy : policies) {

            policy.checkEligibility(
                    student,
                    drive,
                    reasons
            );

        }

        return reasons;
    }

}
