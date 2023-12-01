package com.example.studentapp.Login;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.CompletableFuture;

public class LoginModel {

    FirebaseDatabase db;

    public LoginModel() {
        db = FirebaseDatabase.getInstance("https://b07-group13-default-rtdb.firebaseio.com");
    }

    public CompletableFuture<Boolean> queryDB(String username, String password, String usertype) {
        CompletableFuture<Boolean> cf = new CompletableFuture<>();
        DatabaseReference query = db.getReference().child("UserInfo").child(usertype).child(username);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String passwordDb = String.valueOf(snapshot.child("password").getValue());
                if(passwordDb.equals(password)) {
                    cf.complete(true);
                } else {
                    cf.complete(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                cf.complete(false);
            }
        });

        return cf;
    }
}
