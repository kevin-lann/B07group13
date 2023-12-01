package com.example.studentapp.NewEvent;

import com.example.studentapp.objects.Announcement;
import com.example.studentapp.objects.Event;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewEventModel {
    FirebaseDatabase db;

    public NewEventModel() {
        db = FirebaseDatabase.getInstance("https://b07-group13-default-rtdb.firebaseio.com");
    }

    public void postEvent(Event event) {
        String id = new Integer(event.eventId).toString();
        DatabaseReference ref = db.getReference().child("Announcements");
        ref.child(id).setValue(event.createMap());
    }
}
