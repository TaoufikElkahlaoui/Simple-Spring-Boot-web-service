package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @PostMapping
    public void add(@RequestBody Student student) {
        studentService.add(student);
    }

    @PutMapping(path = "{id}")
    public void update(
            @PathVariable("id") Long id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email
    ){
        studentService.update(id,name,email);
    }

    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable("id") Long id){
        studentService.delete(id);
    }
}
