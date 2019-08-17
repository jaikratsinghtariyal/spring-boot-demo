package com.tektutorial.spring.boot.demo.controller;

import com.tektutorial.spring.boot.demo.bean.Address;
import com.tektutorial.spring.boot.demo.bean.Name;
import com.tektutorial.spring.boot.demo.bean.Student;
import com.tektutorial.spring.boot.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MyController {

	@Value("${HOSTNAME:not_set}")
	private String hostName;
	
    private StudentService studentService;

    @Autowired
    public MyController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/student")
    public List<Student> getStudent()  {
        return studentService.getStudent();
    }

    @PostMapping("/student")
    public String addStudent()  {
        return studentService.addStudent();
    }
	
	@GetMapping("/hello")
    public String hello()  {
        return hostName;
    }
}
