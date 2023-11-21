package com.example.studentapp.objects;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class AdminUser extends User {

    private HashSet<Event> createdEvents;
    private HashSet<Announcement> createdAnnoucements;

    public AdminUser(String username, String password) {
        super(username, password);
        createdEvents = new HashSet<Event>();
        createdAnnoucements = new HashSet<Announcement>();
    }

    public AdminUser(String username, String password, HashSet<Event> createdEvents,
                     HashSet<Announcement> createdAnnouncements) {
        super(username, password);
        this.createdEvents = createdEvents;
        this.createdAnnoucements = createdAnnouncements;
    }

    @Override
    public String getUserType() {
        return "Admin";
    }

    public void addEvent(Event event) {
        createdEvents.add(event);
    }

    public void removeEvent(Event event) {
        createdEvents.remove(event);
    }

    public boolean ownsEvent(Event event) {
        return createdEvents.contains(event);
    }

    public void addCreatedAnnouncement(Announcement announcement) {
        createdAnnoucements.add(announcement);
    }

    public void removeAnnouncement(Announcement announcement) {
        createdAnnoucements.remove(announcement);
    }

    public boolean ownsAnnouncement(Announcement announcement) {
        return createdAnnoucements.contains(announcement);
    }

    public Map<String,String> createMap(){
        Map<String, String> userInfo = new HashMap<>();
        userInfo.put("password", getPassword());
        return userInfo;
    }
}
