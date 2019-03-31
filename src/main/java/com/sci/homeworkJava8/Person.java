package com.sci.homeworkJava8;


import java.time.LocalDate;

public class Person {

    private String firstName;
    private String surName;
    private LocalDate birthday;


    public Person() {
    }

    public Person(String firstName, String surName) {
        this.firstName = firstName;
        this.surName = surName;
    }

    public Person(String firstName, String surName, LocalDate birthday) {
        this.firstName = firstName;
        this.surName = surName;
        this.birthday = birthday;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String toString() {
        return firstName + ", " + surName;
    }

}
