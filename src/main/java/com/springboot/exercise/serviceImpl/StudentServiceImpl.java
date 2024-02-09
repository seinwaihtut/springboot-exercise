package com.springboot.exercise.serviceImpl;

import com.springboot.exercise.models.Student;
import com.springboot.exercise.repositories.StudentRepository;
import com.springboot.exercise.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    List<Student> students = new ArrayList<>();
    @Autowired
    StudentRepository studentRepository;
    @Override
    public Student addStudent(Student student) {
//        students.add(student);
        studentRepository.save(student);
//        studentRepository.get
        return student;
    }

//    @Override
//    public Student updateStudent(Student student) {return student;}

    @Override
    public Student studentDetail(Long id) {
        Student std = new Student();
        return std;
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> studentList() {
//        Student std1 = new Student();
//        std1.setName("sein");
//        std1.setPhone("123456");
//        Student std2 = new Student();
//        std2.setPhone("654322");
//        std2.setName("wai");
//        this.students.add(std1);
//        this.students.add(std2);
        return studentRepository.findAll();
    }

    @Override
    public Student getStudent(String id)
    {
//        studentRepository.existsById(Long.parseLong(id));
        Optional<Student> std =  studentRepository.findById(Long.parseLong(id));
        if(std.isPresent()){
            System.out.println(std.get().toString());
            return std.get();
        }
        return new Student();
    }

    @Override
    public void updateStudent(String id, Student student)
    {
//        studentRepository.
        Student std = studentRepository.getReferenceById(Long.parseLong(id));
        std = student;
        studentRepository.save(std);
//        students.set(Integer.parseInt(id), student);
    }
}
