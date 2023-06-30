package com.example.labandroidproject.Class;

import java.util.ArrayList;

public class Trainee extends User {
    private String mobileNumber;
    private String address;

    private ArrayList<Course> takenCourses;

    public ArrayList<Course> getTakenCourses() {
        return takenCourses;
    }

    public void setTakenCourses(ArrayList<Course> takenCourses) {
        this.takenCourses = takenCourses;
    }

    public Trainee() {
    }

    public Trainee(String email, String firstName, String lastName, String password, String personalPhoto, String mobileNumber) {
        super(email, firstName, lastName, password, personalPhoto, "Trainee");
        this.mobileNumber = mobileNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
