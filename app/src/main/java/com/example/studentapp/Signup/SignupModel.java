package com.example.studentapp.Signup;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.studentapp.objects.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.CompletableFuture;

public class SignupModel {

    FirebaseDatabase db;

    public SignupModel() {
        db = FirebaseDatabase.getInstance("https://b07-group13-default-rtdb.firebaseio.com");
    }

    // Add user to firebase
    public void createNewUser(User user) {
        String usertype = user.getUserType();
        DatabaseReference ref = db.getReference().child("UserInfo").child(usertype);
        ref.child(user.getUsername()).setValue(user.createMap());
    }

    // Check db for user existence
    public CompletableFuture<Boolean> queryDB(String username, String usertype) {

        CompletableFuture<Boolean> cf = new CompletableFuture<Boolean>();
        DatabaseReference query = db.getReference().child("UserInfo").child(usertype);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!(snapshot.hasChild(username))) {
                    cf.complete(true);
                }
                cf.complete(false);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                cf.complete(false);
            }
        });

        return cf;
    }
}
