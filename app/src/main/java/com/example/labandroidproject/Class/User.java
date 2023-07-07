package com.example.labandroidproject.Class;

import java.util.ArrayList;

public class User {

    //Email address. Must be in a correct email format. It is the primary key of the user.
//First name. Minimum 3 characters and maximum 20 characters.
//Last name. Minimum 3 characters and maximum 20 characters.
//Password. Minimum 8 characters and maximum 15 characters. It must contain at least one
//number, one lowercase letter, and one uppercase letter.
//Confirm password field.
//Personal photo

    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String personalPhoto;
    private String Role;
    ArrayList<Message> messages = new ArrayList<Message>();

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    public User() {

    }

    public User(String email, String firstName, String lastName, String password, String personalPhoto, String Role) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.personalPhoto = personalPhoto;
        this.Role = Role;
    }

    public String getRole() {
        return Role;
    }


    public void setRole(String role) {
        Role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPersonalPhoto() {
        return personalPhoto;
    }

    public void setPersonalPhoto(String personalPhoto) {
        this.personalPhoto = personalPhoto;
    }
}
