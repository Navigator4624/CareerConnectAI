package com.careerconnect.Controller;



import com.careerconnect.Dto.StudentRequest;
import com.careerconnect.Dto.StudentResponse;
import com.careerconnect.Service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // Create Student
    @PostMapping
    public ResponseEntity<StudentResponse> createStudent(
            @Valid @RequestBody StudentRequest request) {

        StudentResponse response = studentService.createStudent(request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Get Student By ID
    @GetMapping("/{studentId}")
    public ResponseEntity<StudentResponse> getStudentById(
            @PathVariable String studentId) {

        StudentResponse response = studentService.getStudentById(studentId);

        return ResponseEntity.ok(response);
    }

    // Get All Students
    @GetMapping
    public ResponseEntity<List<StudentResponse>> getAllStudents() {

        List<StudentResponse> students = studentService.getAllStudents();

        return ResponseEntity.ok(students);
    }

    // Update Student
    @PutMapping("/{studentId}")
    public ResponseEntity<StudentResponse> updateStudent(
            @PathVariable String studentId,
            @Valid @RequestBody StudentRequest request) {

        StudentResponse response =
                studentService.updateStudent(studentId, request);

        return ResponseEntity.ok(response);
    }

    // Delete Student
    @DeleteMapping("/{studentId}")
    public ResponseEntity<String> deleteStudent(
            @PathVariable String studentId) {

        String message = studentService.deleteStudent(studentId);

        return ResponseEntity.ok(message);
    }
}
