package com.example.studentapp.Dashboard;

public class AnnouncementBox {

    String announcementDescription;
    String announcementName;
    String announcer;
    String date;
    String time;

    public AnnouncementBox() {

    }


    public AnnouncementBox(String announcementDescription, String announcementName,
                           String announcer, String date, String time) {
        this.announcementDescription = announcementDescription;
        this.announcementName = announcementName;
        this.announcer = announcer;
        this.date = date;
        this.time = time;
    }

    public String getAnnouncementDescription() {
        return announcementDescription;
    }

    public void setAnnouncementDescription(String announcementDescription) {
        this.announcementDescription = announcementDescription;
    }

    public String getAnnouncementName() {
        return announcementName;
    }

    public void setAnnouncementName(String announcementName) {
        this.announcementName = announcementName;
    }

    public String getAnnouncer() {
        return announcer;
    }

    public void setAnnouncer(String announcer) {
        this.announcer = announcer;
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
