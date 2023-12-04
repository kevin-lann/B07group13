package com.example.studentapp.ViewEventFeedback;

import androidx.annotation.NonNull;

import com.example.studentapp.Events.EventsFragment;
import com.example.studentapp.objects.User;
import com.example.studentapp.objects.UserFeedback;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public class ViewEventFeedbackModel {
    FirebaseDatabase db;
    private ViewEventFeedbackFragment fragment;

    public ViewEventFeedbackModel(ViewEventFeedbackFragment fragment) {
        db = FirebaseDatabase.getInstance("https://b07-group13-default-rtdb.firebaseio.com");
        this.fragment = fragment;
    }

    /**
     * Retrieves event name
     */
    public CompletableFuture<String> getEventName(String eventId) {
        DatabaseReference ref;
        CompletableFuture<String> res = new CompletableFuture<>();

        ref = db.getReference().child("Events").child(eventId);

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                res.complete(String.valueOf(snapshot.child("eventName").getValue()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                res.complete(null);
            }
        });

        return res;
    }
    /**
     * Retrieves all user ratings for given event
     */
    public CompletableFuture<ArrayList<UserFeedback>> getEventFeedback(String eventId) {

        DatabaseReference ref;
        CompletableFuture<ArrayList<UserFeedback>> res = new CompletableFuture<>();

        ref = db.getReference().child("EventFeedback").child(eventId);

        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<UserFeedback> feedbackList = new ArrayList<>();
                for(DataSnapshot user : snapshot.getChildren()) {
                    UserFeedback userFeedback = new UserFeedback(
                            (Long)user.child("rating").getValue(),
                            String.valueOf(user.child("feedback").getValue()),
                            String.valueOf(user.child("date").getValue()),
                            String.valueOf(user.child("time").getValue())
                    );
                    feedbackList.add(userFeedback);
                }
                res.complete(feedbackList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                res.complete(null);
            }
        });
        return res;
    }
}
