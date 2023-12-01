package com.example.myapplication;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ServerValue;

public class ConcreteFirebaseManager extends FirebaseManager {

    public ConcreteFirebaseManager() {
        super();
    }

    @Override
    public void saveFeedbackAndRating(float rating, String feedback) {
        DatabaseReference newFeedbackRef = eventFeedbackRef.push();

        newFeedbackRef.child("rating").setValue(rating);
        newFeedbackRef.child("feedback").setValue(feedback);
        newFeedbackRef.child("timestamp").setValue(ServerValue.TIMESTAMP);
    }
}
