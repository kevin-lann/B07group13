package com.example.studentapp.NewEvent;

import androidx.annotation.NonNull;

import com.example.studentapp.objects.Announcement;
import com.example.studentapp.objects.Event;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.CompletableFuture;

public class NewEventModel {
    FirebaseDatabase db;

    public NewEventModel() {
        db = FirebaseDatabase.getInstance("https://b07-group13-default-rtdb.firebaseio.com");
    }

    public void postEvent(Event event) {
        String id = new Integer(event.eventId).toString();
        DatabaseReference ref = db.getReference().child("Events");
        ref.child(id).setValue(event.createMap());
    }

    public void updateEventId(long id) {
        DatabaseReference ref= db.getReference().child("EventID");
        ref.setValue(id);
    }

    public CompletableFuture<Long> getEventId() {
        CompletableFuture<Long> res = new CompletableFuture<>();
        DatabaseReference ref= db.getReference().child("EventID");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                res.complete((Long)snapshot.getValue());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                res.complete(null);
            }
        });
        return res;
    }
}
