package com.example.studentapp.objects;

import java.util.HashMap;
import java.util.Map;

public class Announcement {
    public int announcementId;
    public AdminUser announcer;
    public String announcementName;
    public String announcementDescription;

    // int[2] {hours , min} using 24 hour clock
    public int time[];
    // int[3] {month, day, year}
    public int date[];

    public Announcement(int announcementId, AdminUser announcer) {
        this.announcementId = announcementId;
        this.announcer = announcer;
    }

    public Announcement(int announcementId, AdminUser announcer, String announcementName,
                        String announcementDescription, int[] time, int[] date) {
        this.announcementId = announcementId;
        this.announcer = announcer;
        this.announcementName = announcementName;
        this.announcementDescription = announcementDescription;
        this.time = time;
        this.date = date;
    }

    public String getFormattedTime() {
        return time[0] + ":" + time[1];
    }

    public String getFormattedDate() {
        return date[0] + "-" + date[1] + "-" + date[2];
    }

    public Map<String,String> createMap(){
        Map<String, String> info = new HashMap<>();
        info.put("announcer", announcer.getUsername());
        info.put("announcementName", announcementName);
        info.put("announcementDescription", announcementDescription);
        info.put("time", getFormattedTime());
        info.put("date", getFormattedDate());
        return info;
    }
}
