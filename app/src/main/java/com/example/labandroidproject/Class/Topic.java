package com.example.labandroidproject.Class;

public class Topic {


    String name;
    int quiz;
    int midterm;
    int project;
    int finalGrade;

    public Topic() {
    }

    public Topic(String name, int quiz, int midterm, int project, int finalGrade) {
        this.name = name;
        this.quiz = quiz;
        this.midterm = midterm;
        this.project = project;
        this.finalGrade = finalGrade;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public int getQuiz() {
        return quiz;
    }

    public void setQuiz(int quiz) {
        this.quiz = quiz;
    }

    public int getMidterm() {
        return midterm;
    }

    public void setMidterm(int midterm) {
        this.midterm = midterm;
    }

    public int getProject() {
        return project;
    }

    public void setProject(int project) {
        this.project = project;
    }

    public int getFinalGrade() {
        return finalGrade;
    }

    public void setFinalGrade(int finalGrade) {
        this.finalGrade = finalGrade;
    }
}
