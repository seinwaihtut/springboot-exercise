package com.springboot.exercise.services;

import com.springboot.exercise.models.Student;

import java.util.List;

public interface StudentService {
    Student addStudent(Student student);

//    Student updateStudent(Student student);

    Student studentDetail(Long id);

    void deleteStudent(Long id);

    List<Student> studentList();

    public Student getStudent(String id);

    public void updateStudent(String id, Student student);
}
