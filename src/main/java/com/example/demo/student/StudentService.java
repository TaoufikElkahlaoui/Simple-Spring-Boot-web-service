package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void add(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent())
            throw new IllegalStateException("Email already exists");
        studentRepository.save(student);
    }

    public void delete(Long id) {
        boolean exists = studentRepository.existsById(id);
        if (!exists)
            throw new IllegalStateException(String.format("Student with id %d does not exist", id));
        studentRepository.deleteById(id);
    }


    @Transactional
    public void update(Long id, String name, String email) {
//        System.out.println("here");
        Student student = studentRepository.findById(id).orElseThrow(()
                -> new IllegalStateException(String.format("Student with id %d does not exist", id))
        );


        if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name))
            student.setName(name);

        if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {

            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if (studentOptional.isPresent())
                throw new IllegalStateException("Email already exists");
            student.setEmail(email);

        }

    }
}
