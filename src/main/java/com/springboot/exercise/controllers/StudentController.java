package com.springboot.exercise.controllers;

import com.springboot.exercise.models.Student;
import com.springboot.exercise.services.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class StudentController {
    @Autowired
    StudentService studentService;
    StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

//    @GetMapping("/index")
//    public String home() {
//        return "index";
//    }
//    @GetMapping("/login")
//    public String login() {
//        return "login";
//    }
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("submitAction", "Register");
        model.addAttribute("route", "register/save");

        Student std = new Student();
        model.addAttribute("student", std);
        return "edit";
    }

    @PostMapping("/register/save")
    public String studentSave(@Valid @ModelAttribute Student student, BindingResult result,  Model model){
        if(result.hasErrors()){
            model.addAttribute("submitAction", "Register");
            model.addAttribute("student", student);
            return "edit";
        }

        Student std = studentService.addStudent(student);

        return "redirect:/list";
    }
//    @PostMapping("/register/save")
//    public String studentSave(@RequestParam Map<String, String> student){
//        System.out.println(student.toString());
//        return "index";
//    }
//    @PostMapping(path = "/register/save", consumes = "application/x-www-form-urlencoded")
//    public String studentSave(@ModelAttribute Student std){
//        System.out.println(std.toString());
//        return "index";
//    }
//    @GetMapping(path = "/user/{id}")
//    public String studentSave(@PathVariable String id){
//        System.out.println(id);
//        return "index";
//    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("students", studentService.studentList());
        return "list";
    }

    @GetMapping("/student/{id}/edit")
    public String studentEdit(@PathVariable(value = "id") String id, Model model) {
        model.addAttribute("submitAction", "Update");
        model.addAttribute("id", id);

        model.addAttribute("student", studentService.getStudent(id));
        return "edit";
    }

    @PostMapping("/student/{id}/edit")
    public String studentUpdate(@PathVariable(value = "id") String id, @Valid @ModelAttribute Student student, BindingResult result,  Model model) {
        if(result.hasErrors()){
            model.addAttribute("submitAction", "Update");
            model.addAttribute("id", id);
            model.addAttribute("student", student);
            return "edit";
        }

        studentService.updateStudent(id, student);
        return "redirect:/list";
    }

    @GetMapping("/student/{id}/delete")
    public String delete(@PathVariable(value = "id") String id, Model model){
        studentService.deleteStudent(Long.parseLong(id));
        return "redirect:/list";
    }
}
