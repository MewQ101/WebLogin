package com.example.demo.controllers;

import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class StudentsController {
    private StudentService studentService;

    @GetMapping("/students")
    public String students(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "students";
    }

    @GetMapping("/students/new")
    public String createStudentsForm(Model model) {
        model.addAttribute("student", new Student());
        return "create_student";
    }

    @PostMapping("/students")
    public String saveStudents(@ModelAttribute("student") Student student) {
        studentService.saveStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/students/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudentById(id);
        return "redirect:/students";
    }

    @GetMapping("/edit")
    public String edit(Model model) {
        return "edit";
    }

    @PostMapping("/students/edit/{id}")
    public String updateStudent(@PathVariable Long id, @ModelAttribute("student") Student student) {
        Student existingStudent = studentService.getStudentById(id);
        existingStudent.setUserName(student.getUserName());
        existingStudent.setPassword(student.getPassword());
        existingStudent.setEmail(student.getEmail());

        studentService.updateStudent(existingStudent);
        return "create_student";
    }

    @GetMapping("/students/edit/{id}")
    public String editStudentForm(@PathVariable Long id, Model model) {
        model.addAttribute("student", studentService.getStudentById(id));
        return "create_student";
    }

}
