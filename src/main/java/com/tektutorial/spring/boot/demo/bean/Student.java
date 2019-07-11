package com.tektutorial.spring.boot.demo.bean;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.PUBLIC_ONLY,
isGetterVisibility = JsonAutoDetect.Visibility.PUBLIC_ONLY)
public class Student {

    private static Integer studentId = 1;
    private Address address;
    private Name name;
    private Boolean isActive;


    public Student(Address address, Name name, Boolean isActive) {
        this.address = address;
        this.name = name;
        this.isActive = isActive;
        studentId++;
    }

    public String getFirstName() {
        return name.getFirstName();
    }

    public String getSecondName() {
        return name.getSecondName();
    }

    public String getFirstLine(){
        return address.getFirstLine();
    }

    public String getSecondLine(){
        return address.getSecondLine();
    }

    public String getThirdLine(){
        return address.getThirdLine();
    }

    public Boolean isActive() {
        return isActive;
    }

    public Integer getStudentId(){
        return studentId;
    }
}
