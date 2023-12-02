package com.example.studentapp.RateEvent;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public abstract class FirebaseManager {

    protected DatabaseReference eventFeedbackRef;

    public FirebaseManager() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        eventFeedbackRef = database.getReference("EventFeedback");
    }

    public abstract void saveFeedbackAndRating(float rating, String feedback, int eventId);
}
