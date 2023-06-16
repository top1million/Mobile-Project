package com.example.labandroidproject;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Course {
//    auto-generated course number, course
//title, course main topics, prerequisites, photo, etc.
//    instructor name, registration deadline, course start date, course
//      schedule, and venue
    private int courseNumber;
    private static final AtomicInteger count = new AtomicInteger(0);
    private String courseTitle;
    private String courseMainTopics;
    private ArrayList<Course> prerequisites;
    private String photo;
    private String instructorName;
    private String registrationDeadline;
    private String courseStartDate;
    private String courseSchedule;
    private String venue;

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

    public Course(String courseTitle, String courseMainTopics, ArrayList<Course> prerequisites, String photo) {
        this.courseNumber = count.incrementAndGet();
        this.courseTitle = courseTitle;
        this.courseMainTopics = courseMainTopics;
        this.prerequisites = prerequisites;
        this.photo = photo;
    }

    public int getCourseNumber() {
        return courseNumber;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public String getCourseMainTopics() {
        return courseMainTopics;
    }

    public ArrayList<Course> getPrerequisites() {
        return prerequisites;
    }

    public String getPhoto() {
        return photo;
    }

    public void setCourseNumber(int courseNumber) {
        this.courseNumber = courseNumber;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public void setCourseMainTopics(String courseMainTopics) {
        this.courseMainTopics = courseMainTopics;
    }

    public void setPrerequisites(ArrayList<Course> prerequisites) {
        this.prerequisites = prerequisites;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
    public void makeCourseAvailable(String instructorName, String registrationDeadline, String courseStartDate, String courseSchedule, String venue){
        this.instructorName = instructorName;
        this.registrationDeadline = registrationDeadline;
        this.courseStartDate = courseStartDate;
        this.courseSchedule = courseSchedule;
        this.venue = venue;
    }


}
