package com.example.labandroidproject.Class;

public class Accommodation {
    private String imageUrl;
    private String phoneNumber;
    private String location;
    private String area;
    private int numberOfRooms;

    public Accommodation() {
        // Empty constructor required for Firestore
    }

    public Accommodation(String imageUrl, String phoneNumber, String location, String area, int numberOfRooms) {
        this.imageUrl = imageUrl;
        this.phoneNumber = phoneNumber;
        this.location = location;
        this.area = area;
        this.numberOfRooms = numberOfRooms;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }
}
