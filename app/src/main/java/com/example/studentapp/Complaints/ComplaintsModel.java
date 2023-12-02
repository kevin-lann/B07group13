package com.example.studentapp.Complaints;

import androidx.annotation.NonNull;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.studentapp.objects.Complaint;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ComplaintsModel {
    private FirebaseDatabase db;

    public ComplaintsModel() {
        db = FirebaseDatabase.getInstance("https://b07-group13-default-rtdb.firebaseio.com");
    }

    public void submitComplaintToFirebase(String username, String complaintTitle, String complaintDetails) {
        DatabaseReference ref = db.getReference().child("Complaints").child("ComplaintID");

        ref.runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                Long complaintId = mutableData.getValue(Long.class);
                if (complaintId == null) {
                    mutableData.setValue(0); // Set initial ID if it doesn't exist
                } else {
                    mutableData.setValue(complaintId + 1); // Increment ID
                }
                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(@Nullable DatabaseError error, boolean committed, @Nullable DataSnapshot dataSnapshot) {
                if (error != null) {
                    // Handle error
                } else {
                    if (committed && dataSnapshot != null) {
                        long newComplaintId = dataSnapshot.getValue(Long.class);
                        saveComplaint(username, String.valueOf(newComplaintId), complaintTitle, complaintDetails);
                    }
                }
            }
        });
    }

    private void saveComplaint(String username, String complaintId, String complaintTitle, String complaintDetails) {
        DatabaseReference complaintsRef = db.getReference().child("Complaints");

        // Get current date and time
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());

        String currentDate = dateFormat.format(calendar.getTime());
        String currentTime = timeFormat.format(calendar.getTime());

        // Under Complaints node, create child nodes with complaint ID
        DatabaseReference complaintIdRef = complaintsRef.child(complaintId);
        complaintIdRef.child("Username").setValue(username);
        complaintIdRef.child("Title").setValue(complaintTitle);
        complaintIdRef.child("Description").setValue(complaintDetails);
        complaintIdRef.child("SubmissionDate").setValue(currentDate);
        complaintIdRef.child("SubmissionTime").setValue(currentTime);
    }

}