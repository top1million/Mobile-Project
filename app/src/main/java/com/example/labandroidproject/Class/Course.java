package com.example.labandroidproject.Class;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class Course {
    //    auto-generated course number, course
//title, course main topics, prerequisites, photo, etc.
//    instructor name, registration deadline, course start date, course
//      schedule, and venue
    private String courseTitle;
    private String courseMainTopics;
    private String prerequisites;
    private String photo;
    private String instructorName;
    private String registrationDeadline;
    private String courseStartDate;
    private String courseSchedule;
    private String venue;

    public Course(String courseTitle, String courseMainTopics, String prerequisites, String photo) {
        this.courseTitle = courseTitle;
        this.courseMainTopics = courseMainTopics;
        this.prerequisites = prerequisites;
        this.photo = photo;
    }


    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getRegistrationDeadline() {
        return registrationDeadline;
    }

    public void setRegistrationDeadline(String registrationDeadline) {
        this.registrationDeadline = registrationDeadline;
    }

    public String getCourseStartDate() {
        return courseStartDate;
    }

    public void setCourseStartDate(String courseStartDate) {
        this.courseStartDate = courseStartDate;
    }

    public String getCourseSchedule() {
        return courseSchedule;
    }

    public void setCourseSchedule(String courseSchedule) {
        this.courseSchedule = courseSchedule;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public Course() {
    }


    public String getCourseTitle() {
        return courseTitle;
    }

    public String getCourseMainTopics() {
        return courseMainTopics;
    }

    public String getPrerequisites() {
        return prerequisites;
    }

    public String getPhoto() {
        return photo;
    }


    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public void setCourseMainTopics(String courseMainTopics) {
        this.courseMainTopics = courseMainTopics;
    }

    public void setPrerequisites(String prerequisites) {
        this.prerequisites = prerequisites;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void makeCourseAvailable(String instructorName, String registrationDeadline, String courseStartDate, String courseSchedule, String venue) {
        this.instructorName = instructorName;
        this.registrationDeadline = registrationDeadline;
        this.courseStartDate = courseStartDate;
        this.courseSchedule = courseSchedule;
        this.venue = venue;
    }


}
