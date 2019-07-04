package com.tektutorial.spring.boot.demo.bean;

public class Address {

    private String firstLine;
    private String secondLine;
    private String thirdLine;

    public Address(String firstLine, String secondLine, String thirdLine) {
        this.firstLine = firstLine;
        this.secondLine = secondLine;
        this.thirdLine = thirdLine;
    }

    public String getFirstLine() {
        return firstLine;
    }


    public String getSecondLine() {
        return secondLine;
    }


    public String getThirdLine() {
        return thirdLine;
    }


}
