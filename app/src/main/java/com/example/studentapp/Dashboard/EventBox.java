package com.example.studentapp.Dashboard;

public class EventBox {

    String date;
    String event_description;
    String event_location;
    String event_name;
    String max_attendees;
    String num_attendees;
    String organizer;
    String time_end;
    String time_start;

    public EventBox(){

    }

    public EventBox(String date, String event_description, String event_location,
                    String event_name, String max_attendees, String num_attendees,
                    String organizer, String time_end, String time_start) {
        this.date = date;
        this.event_description = event_description;
        this.event_location = event_location;
        this.event_name = event_name;
        this.max_attendees = max_attendees;
        this.num_attendees = num_attendees;
        this.organizer = organizer;
        this.time_end = time_end;
        this.time_start = time_start;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEventDescription() {
        return event_description;
    } //error

    public void setEventDescription(String event_description) {
        this.event_description = event_description;
    }

    public String getEventLocation() {
        return event_location;
    } //x

    public void setEventLocation(String event_location) {
        this.event_location = event_location;
    }

    public String getEventName(){ //error
        System.out.println("Method get_event_name() called");
        return event_name;
    }

    public void setEventName(String event_name) {
        this.event_name = event_name;
    }

    public String getMaxAttendees() {
        return max_attendees;
    } //x

    public void setMaxAttendees(String max_attendees) {
        this.max_attendees = max_attendees;
    }

    public String getNumAttendees() {
        return num_attendees;
    } //x

    public void setNumAttendees(String num_attendees) {
        this.num_attendees = num_attendees;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public String getTimeEnd() {
        return time_end;
    } //x

    public void setTimeEnd(String time_end) {
        this.time_end = time_end;
    }

    public String getTimeStart() {
        return time_start;
    } //x

    public void setTimeStart(String time_start) {
        this.time_start = time_start;
    }
}
