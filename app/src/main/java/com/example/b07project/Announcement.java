package com.example.b07project;

public class Announcement {

    String topic;
    String sender;
    String date;

    public Announcement(){

    };

    public Announcement(String topic, String sender, String date){
        this.topic = topic;
        this.sender = sender;
        this.date = date;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
