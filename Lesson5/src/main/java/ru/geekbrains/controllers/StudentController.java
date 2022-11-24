package ru.geekbrains.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.entities.Student;
import ru.geekbrains.services.StudentService;

import java.util.List;

@RestController
@RequiredArgsConstructor

public class StudentController {


    private final StudentService studentService;


    //Знаю что так нельзя.

    @GetMapping("/test")
    public void getStudent() {
        Student student = new Student("Ваня", 5);

        studentService.insert1000Student();
        List<Student> list = studentService.findAllStudents();
        System.out.println("Колличество студентов - " + list.size());

        studentService.addStudent(student);

        Student stud = studentService.getStudentById(3L);
        System.out.println(stud);

        student.setName("Иван");
        studentService.updateStudent(student);

        studentService.deleteStudent(student);
    }


}
