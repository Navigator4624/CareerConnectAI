package com.careerconnect.Repository;



import com.careerconnect.Entity.Student;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InMemoryStudentRepository implements StudentRepository {

    private final Map<String, Student> students = new HashMap<>();

    @Override
    public Student save(Student student) {
        students.put(student.getStudentId(), student);
        return student;
    }

    @Override
    public Optional<Student> findById(String studentId) {
        return Optional.ofNullable(students.get(studentId));
    }

    @Override
    public Optional<Student> findByEmail(String email) {
        return students.values()
                .stream()
                .filter(student -> student.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    @Override
    public List<Student> findAll() {
        return new ArrayList<>(students.values());
    }

    @Override
    public void deleteById(String studentId) {
        students.remove(studentId);
    }
}