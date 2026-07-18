package com.careerconnect.Repository;



import com.careerconnect.Entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {

    Student save(Student student);

    Optional<Student> findById(String studentId);

    Optional<Student> findByEmail(String email);

    List<Student> findAll();

    void deleteById(String studentId);
}
