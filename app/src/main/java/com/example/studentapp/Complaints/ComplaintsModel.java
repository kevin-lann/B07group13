package com.example.studentapp.Complaints;

import com.example.studentapp.objects.Complaint;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ComplaintsModel {

    private FirebaseDatabase db;

    public ComplaintsModel() {
        db = FirebaseDatabase.getInstance("https://b07-group13-default-rtdb.firebaseio.com");
    }

    public void submitComplaintToFirebase(String username, String complaintTitle, String complaintDetails) {
        DatabaseReference ref = db.getReference().child("Complaints").child(username);
        Complaint complaint = new Complaint(complaintTitle, complaintDetails);
        ref.setValue(complaint);
    }
}
