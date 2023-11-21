package com.example.studentapp.objects;

public class Event {

    public int eventId;
    public AdminUser organizer;
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

    private final String[] MONTHS = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "jul", "Aug", "Sep" +
            "Oct", "Nov", "Dec"};

    public Event(int eventId, AdminUser organizer) {
        this.eventId = eventId;
        this.organizer = organizer;
    }

    public Event(int eventId, AdminUser organizer, int numAttendees, int maxAttendees, String eventName,
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
        return startTime[0] + ":" + startTime[1] + " - " + endTime[0] + ":" + endTime[1];
    }

    public String getFormattedDate() {
        return MONTHS[date[0]] + " " + date[1] + ", " + date[2];
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

}
