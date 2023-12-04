package com.example.studentapp.NewAnnouncement;

import androidx.annotation.NonNull;

import com.example.studentapp.objects.Announcement;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

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

    public void updateAnnouncementId(long id) {
        DatabaseReference ref= db.getReference().child("AnnouncementID");
        ref.setValue(id);
    }

    public CompletableFuture<Long> getAnnouncementId() {
        CompletableFuture<Long> res = new CompletableFuture<>();
        DatabaseReference ref= db.getReference().child("AnnouncementID");
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
