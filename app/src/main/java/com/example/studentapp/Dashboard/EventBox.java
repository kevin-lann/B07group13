package com.example.studentapp.Dashboard;

public class EventBox {

    String date;
    String eventName;
    String organizer;

    public EventBox(){

    }

    public EventBox(String date, String eventName, String organizer) {
        this.date = date;
        this.eventName = eventName;
        this.organizer = organizer;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public String getEventName(){
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

}
