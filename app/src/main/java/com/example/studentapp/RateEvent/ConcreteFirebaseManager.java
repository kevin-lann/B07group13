package com.example.studentapp.RateEvent;

import android.annotation.SuppressLint;

import com.example.studentapp.MainActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ServerValue;

import java.time.LocalDate;
import java.time.LocalTime;


public class ConcreteFirebaseManager extends FirebaseManager {

    public ConcreteFirebaseManager() {
        super();
    }

    @SuppressLint("NewApi")
    public void saveFeedbackAndRating(float rating, String feedback, int eventId) {
        String username = MainActivity.currUser.getUsername();
        DatabaseReference newFeedbackRef = eventFeedbackRef.child(eventId+"").child(username);

        LocalTime localTime = LocalTime.now();
        String time = localTime.getHour() + ":" + localTime.getMinute();

        LocalDate localDate = LocalDate.now();
        String date = localDate.getMonthValue() + "-" + localDate.getDayOfMonth() + "-" + localDate.getYear();

        newFeedbackRef.child("rating").setValue(rating);
        newFeedbackRef.child("feedback").setValue(feedback);
        newFeedbackRef.child("time").setValue(time);
        newFeedbackRef.child("date").setValue(date);
    }
}
