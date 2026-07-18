package com.careerconnect.Service;


import com.careerconnect.Dto.StudentRequest;
import com.careerconnect.Dto.StudentResponse;
import com.careerconnect.Entity.Student;
import com.careerconnect.Repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Create Student
    public StudentResponse createStudent(StudentRequest request) {

        studentRepository.findByEmail(request.getEmail())
                .ifPresent(student -> {
                    throw new RuntimeException("Student with this email already exists.");
                });

        Student student = Student.builder()
                .studentId("STU-" + UUID.randomUUID().toString().substring(0, 8))
                .name(request.getName())
                .email(request.getEmail())
                .programme(request.getProgramme())
                .graduationYear(request.getGraduationYear())
                .cgpa(request.getCgpa())
                .activeBacklogs(request.getActiveBacklogs())
                .skills(request.getSkills())
                .build();

        studentRepository.save(student);

        return mapToResponse(student);
    }

    // Get Student By Id
    public StudentResponse getStudentById(String studentId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found."));

        return mapToResponse(student);
    }

    // Get All Students
    public List<StudentResponse> getAllStudents() {

        return studentRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Update Student
    public StudentResponse updateStudent(String studentId,
                                         StudentRequest request) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found."));

        student.setName(request.getName());
        student.setEmail(request.getEmail());
        student.setProgramme(request.getProgramme());
        student.setGraduationYear(request.getGraduationYear());
        student.setCgpa(request.getCgpa());
        student.setActiveBacklogs(request.getActiveBacklogs());
        student.setSkills(request.getSkills());

        studentRepository.save(student);

        return mapToResponse(student);
    }

    // Delete Student
    public String deleteStudent(String studentId) {

        studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found."));

        studentRepository.deleteById(studentId);

        return "Student deleted successfully.";
    }

    // Convert Entity -> Response DTO
    private StudentResponse mapToResponse(Student student) {

        return StudentResponse.builder()
                .studentId(student.getStudentId())
                .name(student.getName())
                .email(student.getEmail())
                .programme(student.getProgramme())
                .graduationYear(student.getGraduationYear())
                .cgpa(student.getCgpa())
                .activeBacklogs(student.getActiveBacklogs())
                .skills(student.getSkills())
                .build();
    }
}