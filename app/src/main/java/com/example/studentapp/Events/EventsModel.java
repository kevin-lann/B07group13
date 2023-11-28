package com.example.studentapp.Events;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.studentapp.objects.Event;
import com.example.studentapp.objects.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

public class EventsModel {

    FirebaseDatabase db;

    public EventsModel() {
        db = FirebaseDatabase.getInstance("https://b07-group13-default-rtdb.firebaseio.com");
    }

    // return arraylist of all of a given user's events
    // If user is AdminUser, then this returns their Created Events
    // If user is StudentUser, then this returns their RSVP Events
    public CompletableFuture<ArrayList<String>> getUserEvents(User user) {

        DatabaseReference ref;
        CompletableFuture<ArrayList<String>> res = new CompletableFuture<>();

        String event_list_name = user.getUserType().equals("Admin") ? "Created_Events" : "RSVP_Events";

        ref = db.getReference().child("UserInfo").child(user.getUserType())
                .child(user.getUsername()).child(event_list_name);

        ref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<String> eventIds = new ArrayList<>();
                for(DataSnapshot id : snapshot.getChildren()) {
                    eventIds.add(id.getKey());
                }
                res.complete(eventIds);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                res.complete(null);
            }
        });
        return res;
    }

    public CompletableFuture<Event> getEventFromId(String id) {

        CompletableFuture<Event> res = new CompletableFuture<>();
        DatabaseReference query = db.getReference().child("Events").child(id);
        Log.w("eventTest", "outside");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Log.w("eventTest", "inside");
                Event event;
                String date = String.valueOf(snapshot.child("date").getValue());
                String event_description = String.valueOf(snapshot.child("event_description").getValue());
                String event_location = String.valueOf(snapshot.child("event_location").getValue());
                String event_name = String.valueOf(snapshot.child("event_name").getValue());
                String max_attendees = String.valueOf(snapshot.child("max_attendees").getValue());
                String num_attendees = String.valueOf(snapshot.child("num_attendees").getValue());
                String organizer = String.valueOf(snapshot.child("organizer").getValue());

                // parse start and end times (string --> int[2] = {hour, min} )
                String end = String.valueOf(snapshot.child("time_end").getValue());
                int[] time_end = {
                        Integer.parseInt(end.substring(0, end.indexOf(':'))),
                        Integer.parseInt(end.substring(end.indexOf(':') + 1, 5))
                };

                String srt = String.valueOf(snapshot.child("time_start").getValue());
                int[] time_start = {
                        Integer.parseInt(srt.substring(0, srt.indexOf(':'))),
                        Integer.parseInt(srt.substring(srt.indexOf(':') + 1, 5))
                };

                String date_arr[] = date.split("-");

                // parse date (month, day, year)
                int[] event_date = {
                        Integer.parseInt(date_arr[0]),
                        Integer.parseInt(date_arr[1]),
                        Integer.parseInt(date_arr[2])
                };

                event = new Event(
                        Integer.parseInt(id),
                        organizer,
                        Integer.parseInt(num_attendees),
                        Integer.parseInt(max_attendees),
                        event_name,
                        event_description,
                        event_location,
                        time_start,
                        time_end,
                        event_date);

                Log.w("eventTest", event.toString());

                res.complete(event);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("eventTest", "fail");
                res.complete(null);
            }
        });

        return res;
    }
}

