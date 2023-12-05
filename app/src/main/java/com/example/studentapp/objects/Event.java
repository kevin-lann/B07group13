package com.example.studentapp.objects;

import android.annotation.SuppressLint;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class Event {

    public int eventId;
    public String organizer;
    public int numAttendees;
    public int maxAttendees;
    public String eventName;
    public String eventDescription;
    public String eventLocation;

    // int[2] {hours , min} using 24 hour clock
    public int startTime[];

    // int[2] {hours , min} using 24 hour clock
    public int endTime[];

    // int[3] {month, day, year}
    public int date[];

    public Event() {}

    public Event(int eventId, String organizer, int numAttendees, int maxAttendees, String eventName,
    String eventDescription, String eventLocation, int startTime[], int endTime[], int date[]) {
        this.eventId = eventId;
        this.organizer = organizer;
        this.numAttendees = numAttendees;
        this.maxAttendees = maxAttendees;
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.eventLocation = eventLocation;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
    }

    public String getFormattedTime() {
        String startMin, endMin;

        if(startTime[1] == 0) startMin = "00";
        else if(startTime[1] < 10) startMin = "0" + startTime[1];
        else startMin = "" + startTime[1];

        if(endTime[1] == 0) endMin = "00";
        else if(endTime[1] < 10) endMin = "0" + endTime[1];
        else endMin = "" + endTime[1];

        return startTime[0] + ":" + startMin + " - " + endTime[0] + ":" + endMin;
    }

    public String getFormattedDate() {
        return date[0] + "-" + date[1] + "-" + date[2];
    }

    @SuppressLint("NewApi")
    public boolean hasPassed() {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        // check if event day has passed already
        if(date.getYear() > this.date[2] ||
                date.getYear() == this.date[2] && date.getMonthValue() > this.date[0] ||
                date.getYear() == this.date[2] && date.getMonthValue() == this.date[0] && date.getDayOfMonth() > this.date[1]) {
            return true;
        }
        // Check if event hour has passed already
        if(date.getYear() == this.date[2] && date.getMonthValue() == this.date[0] && date.getDayOfMonth() == this.date[1]
        && time.getHour() > this.endTime[0]) {
            return true;
        }
        // Check if event minute has passed already
        if(date.getYear() == this.date[2] && date.getMonthValue() == this.date[0] && date.getDayOfMonth() == this.date[1]
                && time.getHour() == this.endTime[0] && time.getMinute() > this.endTime[1]) {
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        if(other == null || !(other instanceof Event)) {
            return false;
        }
        Event e = (Event)other;
        return e.eventId == this.eventId;
    }

    @Override
    public int hashCode() {
        int result = 31, d = 67;
        result += d * eventId;
        return result;
    }

    @Override
    public String toString() {
        String ret = "Event Name: " + eventName +
                "\nOrganizer: " + organizer +
                "\nid: " + eventId +
                "\nnum Attendees / max Attendees: " + numAttendees + " " + maxAttendees +
                "\nDescription: " + eventDescription +
                "\nLocation: " + eventLocation +
                "\ntime: " + getFormattedTime() +
                "\nDate: " + getFormattedDate();
        return ret;
    }

    public Map<String, String> createMap() {
        Map<String, String> info = new HashMap<>();
        info.put("eventName", eventName);
        info.put("organizer", organizer);
        info.put("eventLocation", eventLocation);
        info.put("eventDescription", eventDescription);
        info.put("timeStart", startTime[0] + ":" + startTime[1] );
        info.put("timeEnd", endTime[0] + ":" + endTime[1] );
        info.put("date", date[0] + "-" + date[1] + "-" + date[2]);
        return info;
    }

}
