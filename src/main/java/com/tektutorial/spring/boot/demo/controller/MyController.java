package com.tektutorial.spring.boot.demo.controller;

import com.tektutorial.spring.boot.demo.bean.Address;
import com.tektutorial.spring.boot.demo.bean.Name;
import com.tektutorial.spring.boot.demo.bean.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @GetMapping("/student")
    public Student getStudent(){
        Address address = new Address("first", "second", "third");
        Name name = new Name("first Name" , "second name");
        Student student = new Student(address, name, Boolean.TRUE);
        return student;
    }
}
