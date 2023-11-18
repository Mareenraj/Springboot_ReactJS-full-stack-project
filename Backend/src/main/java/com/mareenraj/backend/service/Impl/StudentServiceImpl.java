package com.mareenraj.backend.service.Impl;

import com.mareenraj.backend.exception.StudentAlreadyExistException;
import com.mareenraj.backend.exception.StudentNotFoundException;
import com.mareenraj.backend.model.Student;
import com.mareenraj.backend.repository.StudentRepository;
import com.mareenraj.backend.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    @Override
    public Student addNewStudent(Student student) {
        if(studentAlreadyExists(student.getEmail())){
            throw new StudentAlreadyExistException(student.getEmail()+"already exists!");
        }
        return studentRepository.save(student);
    }

    private boolean studentAlreadyExists(String email) {
        return studentRepository.findByEmail(email).isPresent();
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student updateStudentById(Student student, Long id) {
        return studentRepository.findById(id).map(st ->{
            st.setFirstName(student.getFirstName());
            st.setLastName(student.getLastName());
            st.setDepartment(student.getDepartment());
            st.setEmail(student.getEmail());
            return studentRepository.save(st);
        }).orElseThrow(() -> new StudentNotFoundException("Sorry! student not found with this id: "+id));
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(()->new StudentNotFoundException("Sorry! student not found with this id: "+id));
    }

    @Override
    public void deleteStudentById(Long id) {
        if(!studentRepository.existsById(id)){
            throw new StudentNotFoundException("Sorry! student not found with this id: "+id);
        }
        studentRepository.deleteById(id);
    }
}
