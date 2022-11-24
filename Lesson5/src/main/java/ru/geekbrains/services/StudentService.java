package ru.geekbrains.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.entities.Student;
import ru.geekbrains.exceptions.StudentNotFoundException;
import ru.geekbrains.repositories.StudentRepository;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;


    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    public void deleteStudent(Student student) {
        studentRepository.delete(student);
    }

    public void updateStudent(Student student) {
        studentRepository.save(student);
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Strudent with " + id + " not found"));
    }

    public void insert1000Student() {
        String studentName = "student";

        for (int i = 0; i < 1000; i++) {
            Student student = new Student();
            student.setName(studentName + i);
            student.setMark(randomMark());
            studentRepository.save(student);
        }
    }

    private int randomMark() {
        Random random = new Random();
        return random.nextInt(5);
    }

    public List<Student> findAllStudents(){
        return studentRepository.findAll();
    }

}
