package com.example.studentapp.objects;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class StudentUser extends User {

    //Events the user RSVPed to
    private HashSet<Event> RSVP_Events;

    public StudentUser(String username, String password) {
        super(username, password);
        this.RSVP_Events = new HashSet<Event>();
    }

    @Override
    public String getUserType() {
        return "Student";
    }

    public void addEvent(Event event) {
        if(event.numAttendees < event.maxAttendees && RSVP_Events.add(event)) {
            event.numAttendees++;
        }
    }

    public void removeEvent(Event event) {
        RSVP_Events.remove(event);
    }

    public boolean inEvent(Event event) {
        return RSVP_Events.contains(event);
    }

    public Map<String,String> createMap(){
        Map<String, String> userInfo = new HashMap<>();
        userInfo.put("password", getPassword());
        return userInfo;
    }

}
