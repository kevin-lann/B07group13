package com.example.studentapp.RateEvent;

import android.annotation.SuppressLint;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ServerValue;

import java.time.LocalTime;


public class ConcreteFirebaseManager extends FirebaseManager {

    public ConcreteFirebaseManager() {
        super();
    }

    @SuppressLint("NewApi")
    public void saveFeedbackAndRating(float rating, String feedback, int eventId) {
        DatabaseReference newFeedbackRef = eventFeedbackRef.child(eventId+"");

        LocalTime localTime = LocalTime.now();
        String time = localTime.getHour() + ":" + localTime.getMinute();

        newFeedbackRef.child("rating").setValue(rating);
        newFeedbackRef.child("feedback").setValue(feedback);
        newFeedbackRef.child("timestamp").setValue(time);
    }
}
