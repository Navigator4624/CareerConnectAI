package com.careerconnect.Service;



import com.careerconnect.Entity.EligibilityResult;
import com.careerconnect.Entity.PlacementDrive;
import com.careerconnect.Entity.Student;
import com.careerconnect.Repository.PlacementDriveRepository;
import com.careerconnect.Repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EligibilityService {

    private final StudentRepository studentRepository;
    private final PlacementDriveRepository placementDriveRepository;

    public EligibilityService(StudentRepository studentRepository,
                              PlacementDriveRepository placementDriveRepository) {
        this.studentRepository = studentRepository;
        this.placementDriveRepository = placementDriveRepository;
    }

    public EligibilityResult checkEligibility(String studentId, String driveId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found."));

        PlacementDrive drive = placementDriveRepository.findById(driveId)
                .orElseThrow(() -> new RuntimeException("Placement Drive not found."));

        List<String> reasons = new ArrayList<>();

        // CGPA Check
        if (student.getCgpa() < drive.getMinimumCgpa()) {
            reasons.add("Minimum CGPA required: "
                    + drive.getMinimumCgpa()
                    + ", Current CGPA: "
                    + student.getCgpa());
        }

        // Backlog Check
        if (student.getActiveBacklogs() > drive.getMaximumBacklogs()) {
            reasons.add("Maximum allowed backlogs: "
                    + drive.getMaximumBacklogs()
                    + ", Current backlogs: "
                    + student.getActiveBacklogs());
        }

        // Programme Check
        if (!student.getProgramme()
                .equalsIgnoreCase(drive.getEligibleProgramme())) {

            reasons.add("Eligible Programme: "
                    + drive.getEligibleProgramme());
        }

        // Graduation Year Check
        if (student.getGraduationYear() != drive.getGraduationYear()) {

            reasons.add("Eligible Graduation Year: "
                    + drive.getGraduationYear());
        }

        // Skills Check
        for (String skill : drive.getRequiredSkills()) {

            boolean found = student.getSkills()
                    .stream()
                    .anyMatch(s -> s.equalsIgnoreCase(skill));

            if (!found) {
                reasons.add("Missing Required Skill: " + skill);
            }
        }

        EligibilityResult result = new EligibilityResult();

        result.setStudentId(studentId);
        result.setDriveId(driveId);
        result.setEligible(reasons.isEmpty());
        result.setReasons(reasons);

        return result;
    }
}
