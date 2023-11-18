package com.mareenraj.backend.service;

import com.mareenraj.backend.model.Student;

import java.util.List;

public interface StudentService {
    Student addNewStudent(Student student);
    List<Student> getAllStudents();
    Student updateStudentById(Student student, Long id);
    Student getStudentById(Long id);
    void deleteStudentById(Long id);
}
