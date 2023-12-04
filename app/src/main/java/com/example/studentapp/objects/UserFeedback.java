package com.example.studentapp.objects;

public class UserFeedback {
    private double rating;
    private String feedback;
    private String date;
    private String time;

    public UserFeedback(double rating, String feedback, String date, String time) {
        this.rating = rating;
        this.feedback = feedback;
        this.date = date;
        this.time = time;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
