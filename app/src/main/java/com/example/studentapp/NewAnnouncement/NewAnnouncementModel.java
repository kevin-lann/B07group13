package com.example.studentapp.NewAnnouncement;

import com.example.studentapp.objects.Announcement;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewAnnouncementModel {
    FirebaseDatabase db;

    public NewAnnouncementModel() {
        db = FirebaseDatabase.getInstance("https://b07-group13-default-rtdb.firebaseio.com");
    }

    public void postAnnouncement(Announcement announcement) {
        String id = new Integer(announcement.announcementId).toString();
        DatabaseReference ref = db.getReference().child("Announcements");
        ref.child(id).setValue(announcement.createMap());
    }
}
