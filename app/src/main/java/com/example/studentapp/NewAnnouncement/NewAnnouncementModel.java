package com.example.studentapp.NewAnnouncement;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.studentapp.Events.EventsModel;
import com.example.studentapp.MainActivity;
import com.example.studentapp.objects.Announcement;
import com.example.studentapp.objects.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
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

        ref = db.getReference().child("UserInfo").child("Admin")
                .child(announcement.announcer).child("Created_Announcements").child(id);
        ref.setValue(announcement.announcementName);
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
