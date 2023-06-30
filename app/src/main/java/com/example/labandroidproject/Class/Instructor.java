package com.example.labandroidproject.Class;

import java.util.ArrayList;

public class Instructor extends User {
    private String mobileNumber;
    private String address;
    private String specialization;
    private String degree;

    private ArrayList<Course> givenCourses;

    public ArrayList<Course> getGivenCourses() {
        return givenCourses;
    }

    public void setGivenCourses(ArrayList<Course> givenCourses) {
        this.givenCourses = givenCourses;
    }

    public Instructor() {
    }
    //TODO add course list
    public Instructor(String email, String firstName, String lastName, String password, String personalPhoto, String mobileNumber, String specialization, String degree) {
        super(email, firstName, lastName, password, personalPhoto, "Instructor");
        this.mobileNumber = mobileNumber;
        this.specialization = specialization;
        this.degree = degree;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}
