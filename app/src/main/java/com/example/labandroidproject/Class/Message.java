package com.example.labandroidproject.Class;

public class Message {
    String sender;
    String message;
    String title;
    Boolean seen=false;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getSeen() {
        return seen;
    }

    public void setSeen(Boolean seen) {
        this.seen = seen;
    }

    public Message() {
    }


    public Message(String sender, String message, String title) {
        this.sender = sender;
        this.message = message;
        this.title = title;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
